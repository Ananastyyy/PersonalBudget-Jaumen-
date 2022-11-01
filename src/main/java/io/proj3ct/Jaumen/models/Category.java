package io.proj3ct.Jaumen.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Category")
public class Category {
    @Id
    private Long id;
    private String category;
}
