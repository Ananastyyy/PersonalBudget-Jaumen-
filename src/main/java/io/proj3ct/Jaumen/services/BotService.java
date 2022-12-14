package io.proj3ct.Jaumen.services;

import io.proj3ct.Jaumen.bot.Bot;
import io.proj3ct.Jaumen.bot.BotReply;
import io.proj3ct.Jaumen.bot.ChatUpdate;
import io.proj3ct.Jaumen.bot.functions.CommandNameAndDescription;
import io.proj3ct.Jaumen.bot.functions.FunctionReply;
import io.proj3ct.Jaumen.configs.BotConfig;
import io.proj3ct.Jaumen.models.ChatHistory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BotService implements Bot {
    private BotConfig config;

    public BotService(BotConfig config) {
        this.config = config;
    }

    private ChatHistory createChatHistory(Long userid) {
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setId(userid);
        return chatHistory;
    }
    private ChatHistory getChatHistory(Long userId) {
        Optional<ChatHistory> chatHistory = config.chatHistoryRepository().findById(userId);
        if (chatHistory.isEmpty()) {
            return createChatHistory(userId);
        }
        return chatHistory.get();

    }
    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        ChatHistory chatHistory = getChatHistory(chatUpdate.getUserId()); // История чата
        String text = chatUpdate.getText();
        FunctionReply functionReply = config.textHandler().process(chatHistory, text);
        botReply.setText(functionReply.getText());
        config.chatHistoryRepository().save(chatHistory);
        return botReply;
    }

    public List<CommandNameAndDescription> getListCommandNameAndDescription() {
        return this.config.textHandler().getCommandHandler().getAllCommandNameAndDescription();
    }
}
