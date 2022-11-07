package io.proj3ct.Jaumen.services;

import io.proj3ct.Jaumen.bot.Bot;
import io.proj3ct.Jaumen.bot.BotReply;
import io.proj3ct.Jaumen.bot.ChatUpdate;
import io.proj3ct.Jaumen.bot.functions.Status;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.Repositories;
import org.springframework.stereotype.Component;

@Component
public class BotService implements Bot {
    private Repositories repositories;
    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
      return null;
    }

    public BotService(Repositories repositories) {
        this.repositories = repositories;
    }
    private void saveUser(ChatUpdate chatUpdate) {
        User user = new User();
        user.setId(chatUpdate.getUserId());
        user.setBotStatus(Status.LOG_IN);
        user.setFirstName(chatUpdate.getFirstName());
        user.setSecondName(chatUpdate.getSecondName());
        repositories.getUserRepository().save(user);
    }
}
