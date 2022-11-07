package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.bot.ChatUpdate;
import io.proj3ct.Jaumen.models.Family;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.FamilyRepository;
import io.proj3ct.Jaumen.repositories.Repositories;

import java.util.Optional;

public class LogIn implements Function {

    FamilyRepository repository;

    public LogIn(FamilyRepository repository) {
        this.repository = repository;
    }

    @Override
    public FunctionReply doFunction(User user, String text) {
        FunctionReply reply = new FunctionReply();
        if (text == null){
            reply.setText("Введите логин");
        }
        else if (user.getLogin().isEmpty()){
            Optional<Family> family = repository.findById(text);
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
                user.setBotStatus(Status.WAITING_COMMAND);
                return null;
            }
        }
        return reply;
    }
}
