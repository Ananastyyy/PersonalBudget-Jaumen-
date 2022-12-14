package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.Cheque;
import io.proj3ct.Jaumen.repositories.CategoryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FilterCheque extends Functions {
    private CategoryRepository categoryRepository;

    public FilterCheque(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply functionReply = new FunctionReply();
        Request request = parser(text);
        List<Cheque> cheques = new ArrayList<>();

        if (request.isAllCategoriesRequest()) {
            List<Category> categories = categoryRepository.findAllByUserLogin(chatHistory.getLogin());

            for (Category category : categories) {
                cheques.addAll(category.getChequeList());
            }
        } else {
            if (request.getCategory() == null) {
                functionReply.setText("Некорректный запрос. Попробуйте снова");
                return functionReply;
            } else {
                List<Category> category = categoryRepository.findAllByNameCategoryAndUserLogin(request.getCategory(), chatHistory.getLogin());

                if (category.isEmpty()) {
                    functionReply.setText("Категория \"%s\" не существует".formatted(request.getCategory()));
                    return functionReply;
                } else {
                    cheques = category.get(0).getChequeList();
                }
            }
        }
        if (cheques.isEmpty()) {
            functionReply.setText("Список чеков пуст");
        } else {
            StringBuilder outText = new StringBuilder();

            for (Cheque cheque : cheques) {
                if (request.getDate() == null || cheque.getDate().toString().equals(request.getDate().toString()))
                    outText.append(cheque.toString()).append("\n");
            }
            functionReply.setText(outText.toString());
        }
        return functionReply;
    }

    public Request parser(String text) {
        String[] args = text.split("\s");
        Request request = new Request();

        for (String arg : args) {
            if (arg.equals("-a")) {
                request.setAllCategoriesRequest(true);
            } else if (!request.isAllCategoriesRequest() && request.getCategory() == null) {
                request.setCategory(arg);
            } else if (request.getDate() == null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
                formatter = formatter.withLocale(Locale.ENGLISH);
                request.setDate(LocalDate.parse(arg, formatter));
            }
        }
        return request;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("Введите запрос [(all | название категории)], от [dd.MM.yy] до [dd.MM.yy]");
        return functionReply;
    }

}
