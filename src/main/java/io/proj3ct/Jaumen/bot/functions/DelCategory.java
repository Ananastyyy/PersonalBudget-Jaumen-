package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;

import java.util.List;

public class DelCategory extends Functions {
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public DelCategory(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        List<Category> cat = categoryRepository.findAllByNameCategoryAndUserLogin(text, chatHistory.getLogin());
        FunctionReply functionReply = new FunctionReply();

        if (cat.isEmpty()) {
            functionReply.setText(String.format("Категория \"%s\" не существует", text));
        } else {
            Category category = cat.get(0);
            User user = category.getUser();
            user.removeCategory(category);
            userRepository.save(user);
            functionReply.setText(String.format("Категория \"%s\" удалена.\nВведите название категории, которую хотите удалить.", text));
        }
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("Введите название категории, которую хотите удалить.");
        return functionReply;
    }
}
