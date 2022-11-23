package io.proj3ct.Jaumen.models;

import javax.persistence.*;

@Entity(name = "Users")
public class ChatHistory {
    @Id
    private Long id;
    private String login;
    private String lastCommand;
    private boolean isLogIn;

    public Long getId() {
        return id;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
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

    public boolean isLogIn() {
        return isLogIn;
    }

    public void setLogInStatus(boolean status) {
        isLogIn = status;
    }
}
