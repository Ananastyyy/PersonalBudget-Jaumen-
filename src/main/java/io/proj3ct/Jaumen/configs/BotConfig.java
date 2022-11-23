package io.proj3ct.Jaumen.configs;

import io.proj3ct.Jaumen.bot.functions.TextHandler;
import io.proj3ct.Jaumen.repositories.ChatHistoryRepository;
import org.springframework.stereotype.Component;

@Component
public record BotConfig(ChatHistoryRepository chatHistoryRepository, TextHandler textHandler) {}
