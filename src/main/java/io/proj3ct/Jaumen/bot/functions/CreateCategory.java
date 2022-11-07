package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;

public class CreateCategory implements Function{
    @Override
    public FunctionReply doFunction(ChatHistory user, String text) {
        Category category = new Category();
        category.setNameCategory(text);
        return null;
    }
}
