package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;

import java.util.List;

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
            history.setStatus(Status.WAIT_COMMAND);
            return null;
        } else {
            User user = repository.findById(history.getLogin()).get();
            List<Category> categories = categoryRepository.findAllByUserAndNameCategory(user, text);
            if (!categories.isEmpty()) {
                functionReply.setText("Такая категория уже есть");
                return functionReply;
            }
            Category new_category = new Category();
            new_category.setNameCategory(text);
            user.addCategory(new_category);
            repository.save(user);
            functionReply.setText("Категория добавлена\n Введите название категории");
        }
        return functionReply;
    }
}
