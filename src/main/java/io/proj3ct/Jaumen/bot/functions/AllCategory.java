package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.repositories.CategoryRepository;

import java.util.List;

public class AllCategory implements Function {
    private CategoryRepository categoryRepository;

    public AllCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        List<Category> allCategories = categoryRepository.findAllByUserLogin(chatHistory.getLogin());
        StringBuilder outText = new StringBuilder();
        FunctionReply functionReply = new FunctionReply();

        if (allCategories.isEmpty()) {
            functionReply.setText("Список категорий пуст");
        } else {
            for (Category category : allCategories) {
                outText.append(category.getNameCategory()).append('\n');
            }
        }
        functionReply.setText(outText.toString());
        chatHistory.setLastCommand(null);
        return functionReply;
    }

    @Override
    public FunctionReply start(ChatHistory chatHistory) {
        return doFunction(chatHistory, null);
    }

    @Override
    public void stop(ChatHistory chatHistory) {}
}
