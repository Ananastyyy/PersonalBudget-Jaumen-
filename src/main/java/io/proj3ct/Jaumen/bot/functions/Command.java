package io.proj3ct.Jaumen.bot.functions;

import org.springframework.stereotype.Component;


public record Command(boolean isPrivate, Function function) {}
