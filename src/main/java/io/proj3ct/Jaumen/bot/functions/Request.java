package io.proj3ct.Jaumen.bot.functions;

import java.time.LocalDate;
import java.util.Date;

public class Request {
    private boolean allCategoriesRequest = false;
    private String category = null;
    private DateRange dateRange = null;

    private boolean error = false;

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

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean hasError() {
        return error;
    }
}
