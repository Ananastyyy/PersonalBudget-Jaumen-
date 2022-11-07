package io.proj3ct.Jaumen.configs;

import io.proj3ct.Jaumen.bot.functions.*;
import io.proj3ct.Jaumen.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StatusHandlerConfig {
    private Map<Status, Function> navigation = new HashMap<>();;


    public StatusHandlerConfig(UserRepository userRepository) {
        navigation.put(Status.SLEEPING, new Sleep());
        navigation.put(Status.LOG_IN, new LogIn(userRepository));
        navigation.put(Status.WAITING_COMMAND, new WaitingCommand());
    }

    public Map<Status, Function> getNavigation() {
        return navigation;
    }
}
