package br.com.insurance.product.infra.entities;

import br.com.insurance.product.domain.entity.Status;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Category")
@Table(name = "category")
public class CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID categoryId;
    private String categoryName;
    private String description;
    private Status status;

    public CategoryEntity(){}

    public CategoryEntity(String categoryName, String description, Status status) {
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public UUID getCategoryId() {
        return categoryId;
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

}
