package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;
import java.util.List;
import java.util.Optional;

public class WaitingCommand implements Function{
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public WaitingCommand(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply reply = new FunctionReply();
        if (text != null) {
            String[] args = text.split(" ");
            if (text.equals("/sleep")) {
//                chatHistory.setStatus(Status.SLEEP);
                chatHistory.setLogin(null);
                return null;
            } else if (text.equals("/all_cat")) {
                reply.setText(getAllCategories(chatHistory.getLogin()));
            } else if (text.equals("/add_cat")) {
//                chatHistory.setStatus(Status.CREATE_CATEGORY);
                return null;
            } else if (args[0].equals("/del_cat")) {
                if (args.length > 1) {
                    reply.setText(removeCategory(args[1], chatHistory.getLogin()));
                } else{
                    reply.setText("Используйте: /del_cat <nameCategory>");
                }
            } else {
                reply.setText("Unknown command :(");
            }
        } else {
            reply.setText("Waiting command");
        }
        return reply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        return null;
    }

    @Override
    public void stop(ChatHistory chatHistory) {

    }

    private String getAllCategories(String login) {
        User user = userRepository.findById(login).get();
        String listCategory = "";
        List<Category> categories = user.getCategoryList();
        if (categories.isEmpty()) {
            return "Список категорий пуст";
        } else {
            for (Category category : user.getCategoryList()) {
                listCategory += category.getNameCategory() + "\n";
            }
            return listCategory;
        }
    }
    private String removeCategory(String name, String login) {
        User user = userRepository.findById(login).get();
        Optional<Category> cat = categoryRepository.findByUserAndNameCategory(user, name);
        if (cat.isEmpty()) {
            return String.format("Категория: %s не существует", name);
        } else {
            Category category = cat.get();
            category.getUser().removeCategory(category);
            userRepository.save(category.getUser());
            categoryRepository.deleteById(category.getId());
            return String.format("Категория: %s удалена", name);
        }
    }
}
