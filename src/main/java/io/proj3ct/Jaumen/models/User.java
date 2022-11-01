package io.proj3ct.Jaumen.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Users")
public class User {
    @Id
    private Long userId;
    private String firstName;
    private String secondName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login")
    private  Family family;
    private BotStatus botStatus;
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

    public Family getFamilyId() {
        return family;
    }

    public void setFamilyId(Family family) {
        this.family = family;
    }

    public BotStatus getBotStatus() {
        return botStatus;
    }

    public void setBotStatus(BotStatus botStatus) {
        this.botStatus = botStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
