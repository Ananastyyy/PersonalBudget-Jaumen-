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
////        Optional<Family> family = repository.findById(text);
////        family.isEmpty();
////        Family f = family.get();
////        user.setFamily(f);
//
//        FunctionReply reply = new FunctionReply();
//        if (text == null){
//            reply.setText("Введите логин");
//        }
//        else {
//
//        }
//    }
        return null;
    }
}
