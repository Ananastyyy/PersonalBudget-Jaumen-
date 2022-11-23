package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;

import java.util.Optional;

public class DelCategory implements Function {
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public DelCategory(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        User user = userRepository.findById(chatHistory.getLogin()).get();
        Optional<Category> cat = categoryRepository.findByUserAndNameCategory(user, text);
        FunctionReply functionReply = new FunctionReply();

        if (cat.isEmpty()) {
            functionReply.setText(String.format("Категория: %s не существует", text));
        } else {
            Category category = cat.get();
            category.getUser().removeCategory(category);
            userRepository.save(category.getUser());
            categoryRepository.deleteById(category.getId());
            functionReply.setText(String.format("Категория: %s удалена.\nВведите название категории, которую хотите удалить.", text));
        }
        return functionReply;
    }

    @Override
    public FunctionReply start(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("Введите название категории, которую хотите удалить.");
        return functionReply;
    }

    @Override
    public void stop(ChatHistory chatHistory) {}
}
