package io.proj3ct.Jaumen.bot.functions;

import java.time.LocalDate;
import java.util.Date;

public class Request {
    private boolean allCategoriesRequest = false;
    private String category = null;
    private LocalDate date = null;

    public boolean isAllCategoriesRequest() {
        return allCategoriesRequest;
    }

    public void setAllCategoriesRequest(boolean allCategoriesRequest) {
        this.allCategoriesRequest = allCategoriesRequest;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
