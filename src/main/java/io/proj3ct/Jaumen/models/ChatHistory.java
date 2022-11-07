package io.proj3ct.Jaumen.models;

import io.proj3ct.Jaumen.bot.functions.Status;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "chat_history")
public class ChatHistory {
    @Id
    private Long id;
    private Status botStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getBotStatus() {
        return botStatus;
    }

    public void setBotStatus(Status botStatus) {
        this.botStatus = botStatus;
    }
}
