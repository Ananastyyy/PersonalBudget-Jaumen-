package io.proj3ct.Jaumen.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Cheques")
public class Cheque {
    @Id
    @GeneratedValue
    private Long id;
    private Float cost;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return "%s: %s руб.    %d.%d.%d".formatted(category.getNameCategory(), cost,
                date.getDayOfMonth(), date.getMonth().getValue(), date.getYear());
    }

}
