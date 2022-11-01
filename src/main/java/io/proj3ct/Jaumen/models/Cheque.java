package io.proj3ct.Jaumen.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "Cheques")

public class Cheque {

    @Id
    private Long id;
    private Role idCategory;
    private Date date;
    private

}
