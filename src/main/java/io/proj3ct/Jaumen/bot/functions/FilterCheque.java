package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.Cheque;
import io.proj3ct.Jaumen.repositories.CategoryRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (request.hasError()) {
            functionReply.setText("Некорректный запрос. Попробуйте снова");
            return functionReply;
        }
        if (request.isAllCategoriesRequest()) {
            List<Category> categories = categoryRepository.findAllByUserLogin(chatHistory.getLogin());

            for (Category category : categories) {
                cheques.addAll(category.getChequeList());
            }
        } else {
            List<Category> category = categoryRepository.findAllByNameCategoryAndUserLogin(request.getCategory(), chatHistory.getLogin());

            if (category.isEmpty()) {
                functionReply.setText("Категория \"%s\" не существует".formatted(request.getCategory()));
                return functionReply;
            } else {
                cheques = category.get(0).getChequeList();
            }
        }
        if (cheques.isEmpty()) {
            functionReply.setText("В категории \"{}\" нет чеков");
        } else {
            StringBuilder outText = new StringBuilder();

            for (Cheque cheque : cheques) {
                if (request.getDateRange() == null ||
                        dateInRange(request.getDateRange().start(), request.getDateRange().end(), cheque.getDate()))
                    outText.append(cheque.toString()).append("\n");
            }
            if (outText.isEmpty()) {
                outText.append("По вашему запросу ничего не найдено");
            }
            functionReply.setText(outText.toString());
        }
        return functionReply;
    }

    public Request parser(String text) {
        Pattern pattern = Pattern.compile("(.*?)\\s*(\\d\\d\\.\\d\\d\\.\\d\\d)?\\s*(\\d\\d\\.\\d\\d\\.\\d\\d)?$");
        Matcher matcher = pattern.matcher(text);
        Request request = new Request();

        if (matcher.find()) {
            String category = matcher.group(1);
            String date1 = matcher.group(2);
            String date2 = matcher.group(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

            if (!category.isEmpty() && !category.equals("all")) {
                request.setCategory(category);
            } else {
                request.setAllCategoriesRequest(true);
            }
            try {
                if (date1 != null) {
                    if (date2 != null) {
                        LocalDate d1 = LocalDate.parse(date1, formatter);
                        LocalDate d2 = LocalDate.parse(date2, formatter);
                        if (d1.isBefore(d2))
                            request.setDateRange(new DateRange(d1, d2));
                        else
                            request.setDateRange(new DateRange(d2, d1));
                    } else {
                        LocalDate d = LocalDate.parse(date1, formatter);
                        request.setDateRange(new DateRange(d, d));
                    }
                }
            } catch (DateTimeException e) {
                request.setError(true);
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

    @Override
    public void stop(ChatHistory chatHistory) {}

    public boolean dateInRange(LocalDate start, LocalDate end, LocalDate suspect) {
        return suspect.equals(start) || suspect.equals(end) || (suspect.isBefore(end) && suspect.isAfter(start));
    }
}
