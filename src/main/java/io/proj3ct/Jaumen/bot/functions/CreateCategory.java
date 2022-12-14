package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;

import java.util.List;

public class CreateCategory implements Function {
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public CreateCategory(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply functionReply = new FunctionReply();
        List<Category> categories = categoryRepository.findAllByNameCategoryAndUserLogin(text, chatHistory.getLogin());

        if (!categories.isEmpty()) {
            functionReply.setText("Такая категория уже существует");
        } else {
            User user = userRepository.findById(chatHistory.getLogin()).get();
            Category new_category = new Category();
            new_category.setNameCategory(text);
            user.addCategory(new_category);
            userRepository.save(user);
            functionReply.setText("Категория добавлена!\nВведите название новой категории");
        }
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("Введите название новой категории");
        return functionReply;
    }

    @Override
    public void stop(ChatHistory chatHistory) {
    }
}
