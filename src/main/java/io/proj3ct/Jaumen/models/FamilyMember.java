package io.proj3ct.Jaumen.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "FamilyMembers")
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @OneToMany(mappedBy = "familyMember")
    private List<Category> categoryList;



}
