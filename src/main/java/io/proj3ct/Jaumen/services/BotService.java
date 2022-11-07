package io.proj3ct.Jaumen.services;

import io.proj3ct.Jaumen.bot.Bot;
import io.proj3ct.Jaumen.bot.BotReply;
import io.proj3ct.Jaumen.bot.ChatUpdate;
import io.proj3ct.Jaumen.bot.functions.Status;
import io.proj3ct.Jaumen.bot.functions.StatusHandler;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.Repositories;
import org.springframework.stereotype.Component;

@Component
public class BotService implements Bot {
    private StatusHandler statusHandler;

    public BotService(StatusHandler statusHandler) {
        this.statusHandler = statusHandler;
    }

    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        return null;
    }
}
