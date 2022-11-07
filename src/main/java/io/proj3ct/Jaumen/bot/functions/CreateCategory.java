package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.User;

public class CreateCategory implements Function{
    @Override
    public FunctionReply doFunction(User user, String text) {
        Category category = new Category();
        category.setNameCategory(text);
        return null;
    }
}
