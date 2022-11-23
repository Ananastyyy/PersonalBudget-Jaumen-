package io.proj3ct.Jaumen.bot.functions;


import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateUser implements Function {
    private UserRepository repository;

    public CreateUser(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply functionReply = new FunctionReply();

        if (chatHistory.getLogin() == null) {
            Optional<User> user = repository.findById(text);

            if (user.isEmpty()) {
                chatHistory.setLogin(text);
                functionReply.setText("Придумайте пароль");
            } else {
                functionReply.setText("Это логин уже занят");
            }
        } else {
            User new_user = new User();
            new_user.setLogin(chatHistory.getLogin());
            new_user.setPassword(text);
            repository.save(new_user);
            functionReply.setText("Пользователь создан");
            chatHistory.setLastCommand(null);
            chatHistory.setLogInStatus(true);
        }
        return functionReply;
    }

    @Override
    public FunctionReply start(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        chatHistory.setLogInStatus(false);
        chatHistory.setLogin(null);
        functionReply.setText("Придумайте логин");
        return functionReply;
    }

    @Override
    public void stop(ChatHistory chatHistory) {
        chatHistory.setLogin(null);
    }
}
