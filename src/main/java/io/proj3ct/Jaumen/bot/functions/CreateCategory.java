package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class CreateCategory implements Function{
    UserRepository repository;
    CategoryRepository categoryRepository;

    public CreateCategory(UserRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public FunctionReply doFunction(ChatHistory history, String text) {
        FunctionReply functionReply = new FunctionReply();
        if (text == null) {
            functionReply.setText("Введите название категории");
        } else if (text.equals("/back")) {
//            history.setStatus(Status.WAIT_COMMAND);
            return null;
        } else {
            User user = repository.findById(history.getLogin()).get();
            Optional<Category> categories = categoryRepository.findByUserAndNameCategory(user, text);
            if (!categories.isEmpty()) {
                functionReply.setText("Такая категория уже есть");
                return functionReply;
            }
            Category new_category = new Category();
            new_category.setNameCategory(text);
            user.addCategory(new_category);
            repository.save(user);
            functionReply.setText("Категория добавлена\nВведите название категории");
        }
        return functionReply;
    }

    @Override
    public FunctionReply start(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        if (chatHistory.isLogIn()) {
            functionReply.setText("Введите название категории");
        }
        return null;
    }

    @Override
    public void stop(ChatHistory chatHistory) {

    }
}
