package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;

public class Help extends Functions {

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply functionReply = new FunctionReply();
        String message = """
                Это бот, реализующий отслеживание твоего бюджета.
                /login - войти в свою учетную запись,
                /newuser - создать новую учетную запись,
                
                Функции, доступные после авторизации:
                /createcategory - создать новую категорию расходов,
                /delcategory - удалить существующую категорию,
                /allcategory - вывести все существующие категории,
                /addcheque - добавить чек,
                /requestcheques - вывести чеки по определенным параметрам (необходим хотя бы один аргумент!!!):
                                  *вывод по категориям
                                  *вывод по датам
                """;
        functionReply.setText(message);
        chatHistory.setLastCommand(null);
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        return doFunction(chatHistory, null);
    }
}
