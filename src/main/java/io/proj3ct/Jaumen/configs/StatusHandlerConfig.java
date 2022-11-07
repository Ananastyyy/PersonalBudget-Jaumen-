package io.proj3ct.Jaumen.configs;

import io.proj3ct.Jaumen.bot.functions.Function;
import io.proj3ct.Jaumen.bot.functions.LogIn;
import io.proj3ct.Jaumen.bot.functions.Status;
import io.proj3ct.Jaumen.repositories.Repositories;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class StatusHandlerConfig {
    private Map<Status, Function> navigation;
    private Repositories repositories;

    public StatusHandlerConfig(Map<Status, Function> navigation, Repositories repositories) {
        this.navigation = navigation;
        this.repositories = repositories;
    }

    public Map<Status, Function> getNavigation() {
        return navigation;
    }

    public Repositories getRepositories() {
        return repositories;
    }
}
