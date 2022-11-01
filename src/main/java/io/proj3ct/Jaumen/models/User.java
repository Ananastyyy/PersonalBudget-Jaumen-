package io.proj3ct.Jaumen.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Users")

public class User {
    @Id
    private Long id;
    private Role role;

}
