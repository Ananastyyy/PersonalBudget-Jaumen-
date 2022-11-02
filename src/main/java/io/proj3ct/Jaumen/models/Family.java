package io.proj3ct.Jaumen.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Families")
public class Family {
    @Id
    private String login;
    private String password;

    @OneToOne(mappedBy = "family")
    private User user;
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<FamilyMember> familyMemberList;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FamilyMember> getFamilyMemberList() {
        return familyMemberList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFamilyMemberList(List<FamilyMember> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }
}

