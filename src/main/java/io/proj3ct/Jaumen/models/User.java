package io.proj3ct.Jaumen.models;

import io.proj3ct.Jaumen.bot.functions.Status;

import javax.persistence.*;

@Entity(name = "Users")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String secondName;


    private String login;
    private Status botStatus;

    public Status getBotStatus() {
        return botStatus;
    }

    public void setBotStatus(Status botStatus) {
        this.botStatus = botStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
