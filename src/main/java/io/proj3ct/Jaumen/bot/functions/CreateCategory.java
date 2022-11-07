package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.FamilyRepository;

public class CreateCategory implements Function {
    FamilyRepository familyRepository;

    @Override
    public FunctionReply doFunction(User user, String text) {


        Category category = new Category();
        category.setNameCategory(text);
        return null;
    }
}
