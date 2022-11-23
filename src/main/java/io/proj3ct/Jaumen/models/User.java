package io.proj3ct.Jaumen.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "FamilyMembers")
public class User {
    @Id
    private String login;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categoryList = new ArrayList<>();


    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void addCategory(Category category) {
        categoryList.add(category);
        category.setUser(this);
    }

    public void removeCategory(Category category) {
        categoryList.remove(category);
        category.setUser(null);
    }

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
}
