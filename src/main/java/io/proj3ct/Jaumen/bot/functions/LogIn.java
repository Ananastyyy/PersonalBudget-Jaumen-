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
    public FunctionReply doFunction(ChatHistory history, String text) {
        FunctionReply reply = new FunctionReply();
        if (text == null){
            reply.setText("Введите логин");
        } else if (text.equals("/new_user")) {
            history.setStatus(Status.CREATE_USER);
            history.setLogin(null);
            return null;
        } else if (history.getLogin() == null){
            Optional<User> user = repository.findById(text);
            if (user.isEmpty()) {
                reply.setText("Логин неверный, попробуйте снова");
            } else{
                history.setLogin(text);
                reply.setText("Введите пароль");
            }
        }
        else{
            User user = repository.findById(history.getLogin()).get();
            if(text.equals("/back")) {
                history.setLogin(null);
                return null;
            }else if (!text.equals(user.getPassword())){
                reply.setText("Пароль неверный, попробуйте снова");
            }
            else{
                history.setStatus(Status.WAIT_COMMAND);
                return null;
            }
        }
        return reply;
    }
}
