package io.proj3ct.Jaumen.services;

import io.proj3ct.Jaumen.configs.TelegramBotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    TelegramBotConfig botConfig;

    public TelegramBot(TelegramBotConfig config) {
        this.botConfig = config;
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
            String chatId = mess.getChatId().toString();
            switch (text) {
                case "/start":
                    sendMessage(chatId, "Hi, " + mess.getFrom().getFirstName() + "!");
                    break;
                default:
                    sendMessage(chatId, "Unknown command :(");

            }
        }
    }

    private void sendMessage(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
