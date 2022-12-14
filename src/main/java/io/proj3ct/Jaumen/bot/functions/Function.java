package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;

public interface Function {
    FunctionReply doFunction(ChatHistory chatHistory, String text);
    FunctionReply preprocess(ChatHistory chatHistory);
    void stop(ChatHistory chatHistory);
}
