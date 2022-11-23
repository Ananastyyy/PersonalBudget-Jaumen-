package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.UserRepository;

import java.util.Optional;

public class LogIn implements Function {

    UserRepository repository;

    public LogIn(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply reply = new FunctionReply();

        if (chatHistory.getLogin() == null) {
            Optional<User> user = repository.findById(text);

            if (user.isEmpty()) {
                reply.setText("Логин неверный, попробуйте снова");
            } else {
                chatHistory.setLogin(text);
                reply.setText("Введите пароль");
            }
        } else {
            User user = repository.findById(chatHistory.getLogin()).get();

            if (!text.equals(user.getPassword())) {
                reply.setText("Пароль неверный, попробуйте снова");
            } else {
                chatHistory.setLogInStatus(true);
                chatHistory.setLastCommand(null);
                reply.setText("Вы вошли");
            }
        }
        return reply;
    }

    @Override
    public FunctionReply start(ChatHistory chatHistory) {
        FunctionReply reply = new FunctionReply();
        chatHistory.setLogInStatus(false);
        chatHistory.setLogin(null);
        reply.setText("Введите логин");
        return reply;
    }

    @Override
    public void stop(ChatHistory chatHistory) {
        chatHistory.setLogin(null);
    }
}
