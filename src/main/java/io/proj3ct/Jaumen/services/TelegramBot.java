package io.proj3ct.Jaumen.services;

import io.proj3ct.Jaumen.bot.Bot;
import io.proj3ct.Jaumen.bot.BotReply;
import io.proj3ct.Jaumen.bot.ChatUpdate;
import io.proj3ct.Jaumen.bot.functions.CommandNameAndDescription;
import io.proj3ct.Jaumen.configs.TelegramBotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    TelegramBotConfig botConfig;
    Bot bot;

    public TelegramBot(TelegramBotConfig config, Bot bot) {
        this.botConfig = config;
        this.bot = bot;
        createMenu();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message mess = update.getMessage();
            String text = mess.getText();
            Long userId = mess.getFrom().getId();
            Long chatId = mess.getChatId();
            ChatUpdate chatUpdate = new ChatUpdate(userId, chatId);
            chatUpdate.setText(text);
            BotReply botReply = bot.reply(chatUpdate);
            sendMessage(botReply);
        }
    }

    private void sendMessage(BotReply botReply) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(botReply.getChatId().toString());
        sendMessage.setText(botReply.getText());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void createMenu() {
        List<BotCommand> commands = new ArrayList<>();

        for (CommandNameAndDescription info : this.bot.getListCommandNameAndDescription()) {
            commands.add(new BotCommand(info.name(), info.description()));
        }
        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
