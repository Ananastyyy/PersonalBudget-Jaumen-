package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;
import org.springframework.stereotype.Component;

@Component
public class TextHandler {
    private CommandHandler commandHandler;

    public TextHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public FunctionReply process(ChatHistory chatHistory, String text) {
        Command command = commandHandler.getCommand(text);
        Command lastCommand = commandHandler.getCommand(chatHistory.getLastCommand());
        Function function = null;
        Function lastFunction = null;
        
        if (command != null) {
            function = command.function();
        }
        if (lastCommand != null) {
            lastFunction = lastCommand.function();
        }
        FunctionReply functionReply = new FunctionReply();

        if (function == null) {
            if (lastFunction == null) {
                functionReply.setText("Введите команду");
            } else {
                functionReply = lastFunction.doFunction(chatHistory, text);
            }
        } else {
            if (lastFunction != null) {
                lastFunction.stop(chatHistory);
            }
            if (command.isPrivate() == chatHistory.isLogIn() || !command.isPrivate()) {
                chatHistory.setLastCommand(text);
                functionReply = function.preprocess(chatHistory);
            } else {
                functionReply.setText("Вы не авторизировались");
            }
        }
        return functionReply;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
