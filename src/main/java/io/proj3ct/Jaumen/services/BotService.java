package io.proj3ct.Jaumen.services;

import io.proj3ct.Jaumen.bot.Bot;
import io.proj3ct.Jaumen.bot.BotReply;
import io.proj3ct.Jaumen.bot.ChatUpdate;
import io.proj3ct.Jaumen.bot.functions.FunctionReply;
import io.proj3ct.Jaumen.bot.functions.Status;
import io.proj3ct.Jaumen.bot.functions.StatusHandler;
import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.repositories.Repositories;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class BotService implements Bot {
    private Repositories repositories;
    private StatusHandler statusHandler;

    public BotService(StatusHandler statusHandler, Repositories repositories) {
        this.statusHandler = statusHandler;
        this.repositories = repositories;
    }

    private ChatHistory createUser(Long userid) {
        ChatHistory user = new ChatHistory();
        user.setId(userid);
        user.setStatus(Status.SLEEPING);
        return user;
    }
    private ChatHistory getUser(Long userId) {
        Optional<ChatHistory> user = repositories.getUserRepository().findById(userId);
        if (user.isEmpty()) {
            return createUser(userId);
        }
        return user.get();

    }
    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        ChatHistory user = getUser(chatUpdate.getUserId()); // История чата
        String text = chatUpdate.getText();
        Status status = user.getStatus();
        FunctionReply functionReply = statusHandler.getFunction(status).doFunction(user, text);
        if (functionReply == null) {
            status = user.getStatus();
            functionReply = statusHandler.getFunction(status).doFunction(user, null);
        }
        botReply.setText(functionReply.getText());
        repositories.getUserRepository().save(user);
        return botReply;
    }
}
