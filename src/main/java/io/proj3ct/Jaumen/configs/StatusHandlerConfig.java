package io.proj3ct.Jaumen.configs;

import io.proj3ct.Jaumen.bot.functions.*;
import io.proj3ct.Jaumen.repositories.CategoryRepository;
import io.proj3ct.Jaumen.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StatusHandlerConfig {
    private Map<Status, Function> navigation = new HashMap<>();;

    public StatusHandlerConfig(UserRepository userRepository, CategoryRepository categoryRepository) {
        navigation.put(Status.SLEEP, new Sleep());
        navigation.put(Status.LOG_IN, new LogIn(userRepository));
        navigation.put(Status.CREATE_USER, new CreateUser(userRepository));
        navigation.put(Status.CREATE_CATEGORY, new CreateCategory(userRepository, categoryRepository));
        navigation.put(Status.WAIT_COMMAND, new WaitingCommand(userRepository, categoryRepository));
    }

    public Map<Status, Function> getNavigation() {
        return navigation;
    }
}
