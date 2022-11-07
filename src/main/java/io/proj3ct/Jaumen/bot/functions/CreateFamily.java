package io.proj3ct.Jaumen.bot.functions;


import io.proj3ct.Jaumen.models.Family;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.FamilyRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateFamily implements Function {
    private FamilyRepository familyRepository;

    public CreateFamily(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }
    @Override
    public FunctionReply doFunction(User user, String text) {
        FunctionReply functionReply = new FunctionReply();

        if (text == null || text.equals("/back")) {
            user.setLogin(null);
            functionReply.setText("Придумайте логин");
            return functionReply;
        }
        Optional<Family> family = familyRepository.findById(text);
        if (user.getLogin() == null) {
            if (family.isEmpty()) {
                user.setLogin(text);
                functionReply.setText("Придумайте пароль");
                return functionReply;
            } else {
                functionReply.setText("Это логин уже занят");
                return functionReply;
            }
        } else {
            Family new_family = new Family();
            new_family.setLogin(user.getLogin());
            new_family.setPassword(text);
            familyRepository.save(new_family);
            user.setStatus(Status.WAITING_COMMAND);
            return null;
        }



    }
}
