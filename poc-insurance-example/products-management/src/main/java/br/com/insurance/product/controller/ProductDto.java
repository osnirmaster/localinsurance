package br.com.insurance.product.controller;

import br.com.insurance.product.domain.entity.Product;

import java.util.UUID;

public class ProductDto {

    private UUID idProduct;
    private String code;
    private String name;
    private String image;
    private String description;

    public ProductDto(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.image = product.getImage();
        this.description = product.getDescription();
    }

    public UUID getIdProduct() {
        return idProduct;
    }

    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
