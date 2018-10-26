package com.unete.kvalenzuela.unete_2.api.model;

import com.unete.kvalenzuela.unete_2.api.model.CategoryListBody;

import java.util.List;

public class ApiResponseCategories {

    private List<CategoryDisplayList> results;

    public ApiResponseCategories(List<CategoryDisplayList> results) {
        this.results = results;
    }

    public List<CategoryDisplayList> getResults() {
        return results;
    }

    public void setResults(List<CategoryDisplayList> results) {
        this.results = results;
    }
}
