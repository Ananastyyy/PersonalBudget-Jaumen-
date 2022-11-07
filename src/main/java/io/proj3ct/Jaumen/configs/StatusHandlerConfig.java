package io.proj3ct.Jaumen.configs;

import io.proj3ct.Jaumen.bot.functions.*;
import io.proj3ct.Jaumen.repositories.FamilyRepository;
import io.proj3ct.Jaumen.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StatusHandlerConfig {
    private Map<Status, Function> navigation = new HashMap<>();;


    public StatusHandlerConfig(FamilyRepository familyRepository) {
        navigation.put(Status.SLEEPING, new Sleep());
        navigation.put(Status.LOG_IN, new LogIn(familyRepository));
        navigation.put(Status.WAITING_COMMAND, new WaitingCommand());
        navigation.put(Status.CREATE_FAMILY, new CreateFamily(familyRepository));
    }

    public Map<Status, Function> getNavigation() {
        return navigation;
    }
}
