package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.repositories.Repositories;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandHandler {
    private HashMap<String, Command> navigation;

    public CommandHandler(Repositories repositories) {
        this.navigation = new HashMap<>();
        navigation.put("/login", new Command("Авторизация", false, new LogIn(repositories.getUserRepository())));
        navigation.put("/newuser", new Command("Регистрация", false, new CreateUser(repositories.getUserRepository())));
        navigation.put("/delcategory", new Command("Удалить категорию", true, new DelCategory(repositories.getUserRepository(), repositories.getCategoryRepository())));
        navigation.put("/createcategory", new Command("Создать категорию", true, new CreateCategory(repositories.getUserRepository(), repositories.getCategoryRepository())));
        navigation.put("/allcategory", new Command("Вывести все категории", true, new AllCategory(repositories.getCategoryRepository())));
        navigation.put("/addcheque", new Command("Добавить чек", true, new AddCheque(repositories.getCategoryRepository(), repositories.getUserRepository())));
        navigation.put("/requestcheques", new Command("Показать расходы", true, new FilterCheque(repositories.getCategoryRepository())));
    }

    public Command getCommand(String nameCommand) {
        return navigation.get(nameCommand);
    }

    public List<CommandNameAndDescription> getAllCommandNameAndDescription() {
        List<CommandNameAndDescription> answer = new ArrayList<>();

        for (Map.Entry<String, Command> pair : navigation.entrySet()) {
            answer.add(new CommandNameAndDescription(pair.getKey(), pair.getValue().description()));
        }
        return answer;
    }
}
