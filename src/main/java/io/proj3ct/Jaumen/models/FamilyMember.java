package io.proj3ct.Jaumen.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "FamilyMembers")
public class FamilyMember {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Role role;

    @ManyToOne
    @JoinColumn(name = "family_login")
    private Family family;

    @OneToMany(mappedBy = "familyMember", cascade = CascadeType.ALL)
    private List<Category> categoryList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
