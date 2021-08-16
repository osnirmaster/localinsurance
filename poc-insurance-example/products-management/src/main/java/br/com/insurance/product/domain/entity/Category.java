package br.com.insurance.product.domain.entity;

import java.util.Objects;
import java.util.UUID;

public class Category {

    private UUID categoryId;
    private String categoryCode;
    private String categoryName;
    private String description;
    private Status status;

    public Category(UUID categoryId){
        this.categoryId = categoryId;
    }

    public Category(String categoryCode,String categoryName, String description, Status status) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName) && Objects.equals(description, category.description) && status == category.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, description, status);
    }
}
