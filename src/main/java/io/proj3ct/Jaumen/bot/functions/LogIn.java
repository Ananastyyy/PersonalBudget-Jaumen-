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
    public FunctionReply doFunction(ChatHistory user, String text) {
        FunctionReply reply = new FunctionReply();
        if (text == null){
            reply.setText("Введите логин");
        } else if (text.equals("/new_user")) {
            user.setStatus(Status.CREATE_FAMILY);
            user.setLogin(null);
            return null;
        } else if (user.getLogin() == null){
            Optional<User> family = repository.findById(text);
            if (family.isEmpty()) {
                reply.setText("Логин неверный, попробуйте снова");
            } else{
                user.setLogin(text);
                reply.setText("Введите пароль");
            }
        }
        else{
            Family family = repository.findById(user.getLogin()).get();
            if(text.equals("/back")) {
                user.setLogin(null);
                reply.setText("Введите логин");
            }
            if (!text.equals(family.getPassword())){
                reply.setText("Пароль неверный, попробуйте снова");
            }
            else{
                user.setStatus(Status.WAITING_COMMAND);
                return null;
            }
        }
        return reply;
    }
}
