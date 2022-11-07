package io.proj3ct.Jaumen.models;

import io.proj3ct.Jaumen.bot.functions.Status;

import javax.persistence.*;

@Entity(name = "Users")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String secondName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login")
    private  Family family;
    private Status botStatus;

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

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

    public Family getFamilyId() {
        return family;
    }

    public void setFamilyId(Family family) {
        this.family = family;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
