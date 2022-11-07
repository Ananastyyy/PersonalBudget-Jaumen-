package io.proj3ct.Jaumen.bot;

import io.proj3ct.Jaumen.models.BotStatus;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.Repositories;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Bot {
    private Repositories repositories;

    public BotReply reply(ChatUpdate chatUpdate) {
        Optional<User> users = repositories.getUserRepository().findById(chatUpdate.getUserId());
        User user;
        BotStatus botStatus;
        if (users.isEmpty()) {
            saveUser(chatUpdate);
            botStatus = BotStatus.LOG_IN;
        } else {
            user = users.get();
            botStatus = user.getBotStatus();
        }
        return null;
    }

    public Bot(Repositories repositories) {
        this.repositories = repositories;
    }

    public Repositories getRepositories() {
        return repositories;
    }
    private void saveUser(ChatUpdate chatUpdate) {
        User user = new User();
        user.setId(chatUpdate.getUserId());
        user.setBotStatus(BotStatus.LOG_IN);
        user.setFirstName(chatUpdate.getFirstName());
        user.setSecondName(chatUpdate.getSecondName());
        repositories.getUserRepository().save(user);
    }
}
