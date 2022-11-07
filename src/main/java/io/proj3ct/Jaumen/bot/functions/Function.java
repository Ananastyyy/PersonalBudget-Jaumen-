package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.bot.ChatUpdate;
import io.proj3ct.Jaumen.models.User;
import io.proj3ct.Jaumen.repositories.Repositories;

public interface Function {
    public FunctionReply doFunction(Repositories repositories, ChatUpdate chatUpdate);
}
