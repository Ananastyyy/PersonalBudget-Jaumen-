package io.proj3ct.Jaumen.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "FamilyMember")

public class FamilyMember {

    @Id
    private Long id;
    private Role idFamily;
    private Long idUser;

}
