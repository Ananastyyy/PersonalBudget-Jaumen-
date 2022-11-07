package io.proj3ct.Jaumen.models;

import io.proj3ct.Jaumen.bot.functions.Status;

import javax.persistence.*;

@Entity(name = "Users")
public class ChatHistory {
    @Id
    private Long id;
    private String login;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
