package io.proj3ct.Jaumen.bot;

import io.proj3ct.Jaumen.bot.functions.CommandNameAndDescription;

import java.util.List;

public interface Bot {
    BotReply reply(ChatUpdate chatUpdate);
    List<CommandNameAndDescription> getListCommandNameAndDescription();
}