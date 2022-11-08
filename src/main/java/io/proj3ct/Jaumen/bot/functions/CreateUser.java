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
    public FunctionReply doFunction(ChatHistory history, String text) {
        FunctionReply functionReply = new FunctionReply();

        if (text == null || text.equals("/back")) {
            history.setLogin(null);
            functionReply.setText("Придумайте логин");
            return functionReply;
        }
        Optional<User> user = repository.findById(text);
        if (history.getLogin() == null) {
            if (user.isEmpty()) {
                history.setLogin(text);
                functionReply.setText("Придумайте пароль");
                return functionReply;
            } else {
                functionReply.setText("Это логин уже занят");
                return functionReply;
            }
        } else {
            User new_user = new User();
            new_user.setLogin(history.getLogin());
            new_user.setPassword(text);
            repository.save(new_user);
            history.setStatus(Status.WAIT_COMMAND);
            return null;
        }



    }
}
