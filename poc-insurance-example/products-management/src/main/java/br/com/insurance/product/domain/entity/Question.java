package br.com.insurance.product.domain.entity;

import br.com.insurance.product.infra.entities.ProductEntity;

public class Question {

    private String code;
    private int index;
    private String text;
    private Product product;

    public Question(String code, int index, String text, Product product) {
        this.code = code;
        this.index = index;
        this.text = text;
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
