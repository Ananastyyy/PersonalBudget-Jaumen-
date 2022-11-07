package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;

public interface Function {
    public FunctionReply doFunction(ChatHistory user, String text);
}
