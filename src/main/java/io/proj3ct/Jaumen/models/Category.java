package io.proj3ct.Jaumen.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Categories")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String nameCategory;
    @ManyToOne()
    @JoinColumn(name = "user_login")
    private User user;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cheque> chequeList = new ArrayList<>();

    public void addCheque(Cheque cheque) {
        chequeList.add(cheque);
        cheque.setCategory(this);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Cheque> getChequeList() {
        return chequeList;
    }

    public void setChequeList(List<Cheque> chequeList) {
        this.chequeList = chequeList;
    }
}
