package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.User;

public interface Function {
    public FunctionReply doFunction(User user, String text);
}
