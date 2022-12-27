package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.Cheque;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCheque extends Functions{

    final String template = "\nВведите данные нового чека:\n(название категории) (сумма) [dd.mm.yy]";
    CategoryRepository categoryRepository;
    UserRepository userRepository;

    public AddCheque(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public record Arguments(String category, Float cost, LocalDate date){
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply functionReply = new FunctionReply();
        Arguments arguments;

        try {
            arguments = parser(text);
        } catch (Exception e) {
            functionReply.setText("Ошибка добавления чека!" + template);
            return functionReply;
        }

        List<Category> categories = categoryRepository.findAllByNameCategoryAndUserLogin(arguments.category, chatHistory.getLogin());

        if (categories.isEmpty()) {
            functionReply.setText(String.format("Категория \"%s\" не существует", arguments.category));
        } else {
            Cheque newCheque = new Cheque();
            newCheque.setCategory(categories.get(0));
            newCheque.setCost(arguments.cost);
            newCheque.setDate(arguments.date);

            categories.get(0).addCheque(newCheque);
            categoryRepository.save(categories.get(0));
            functionReply.setText("Чек добавлен!"+template);
        }
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText(template);
        return functionReply;
    }

    public Arguments parser(String text) throws Exception {
        Pattern pattern = Pattern.compile("(.+?)\\s+(\\d+?\\.?\\d*)\\s*(\\d\\d\\.\\d\\d\\.\\d\\d)?$");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String category = matcher.group(1);
            Float cost = Float.valueOf(matcher.group(2));
            LocalDate date;

            if (matcher.group(3) != null) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
                    date = LocalDate.parse(matcher.group(3), formatter);
                } catch (Exception e) {
                    throw new Exception();
                }
            } else {
                date = LocalDate.now();
            }
            return new Arguments(category, cost, date);
        } else {
            throw new Exception();
        }
    }
}
