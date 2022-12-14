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
        FunctionInterface FunctionInterface = null;
        FunctionInterface lastFunctionInterface = null;
        
        if (command != null) {
            FunctionInterface = command.FunctionInterface();
        }
        if (lastCommand != null) {
            lastFunctionInterface = lastCommand.FunctionInterface();
        }
        FunctionReply functionReply = new FunctionReply();

        if (FunctionInterface == null) {
            if (lastFunctionInterface == null) {
                functionReply.setText("Введите команду");
            } else {
                functionReply = lastFunctionInterface.doFunction(chatHistory, text);
            }
        } else {
            if (lastFunctionInterface != null) {
                lastFunctionInterface.stop(chatHistory);
            }
            if (command.isPrivate() == chatHistory.isLogIn() || !command.isPrivate()) {
                chatHistory.setLastCommand(text);
                functionReply = FunctionInterface.preprocess(chatHistory);
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
