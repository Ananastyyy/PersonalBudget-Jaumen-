package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.Cheque;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddCheque implements Function{

    CategoryRepository categoryRepository;

    UserRepository userRepository;

    public AddCheque(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public record Arguments(String category, Long cost, LocalDate date){
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply functionReply = new FunctionReply();

        Arguments arguments = null;
        try {
            arguments = parser(text);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Optional<Category> categories = categoryRepository.findByNameCategoryAndUserLogin(arguments.category, chatHistory.getLogin());

        if (categories.isEmpty()) {
            functionReply.setText("Такой категории не существует");
        } else {
            Cheque newCheque = new Cheque();
            newCheque.setCategory(categories.get());
            newCheque.setCost(arguments.cost);
            newCheque.setDate(arguments.date);

            categories.get().addCheque(newCheque);
            categoryRepository.save(categories.get());
            functionReply.setText("Чек добавлен\nВведите данные нового чека");
        }
        return functionReply;
    }


    @Override
    public FunctionReply start(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("Введите данные чека");
        return functionReply;
    }

    @Override
    public void stop(ChatHistory chatHistory) {

    }

    public Arguments parser(String text) throws ParseException {
        ArrayList<String> parsedMessage = new ArrayList<>(Arrays.asList(text.split(" ")));
        String category = parsedMessage.get(0);
        Long cost = Long.valueOf(parsedMessage.get(1));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        formatter = formatter.withLocale(Locale.ENGLISH);
        LocalDate date;
        try {
            date = LocalDate.parse(parsedMessage.get(2), formatter);
        } catch (IndexOutOfBoundsException e){
            date = LocalDate.now();
        }
        return new Arguments(category, cost, date);
    }
}
