package br.com.insurance.product.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Product {
    private UUID productId;
    private Meta metaData;
    private String code;
    private String name;
    private String image;
    private String description;
    private List<Cover> covers = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();
    private int maxNumberOfInsured;
    private String icon;
    private Category category;
    private LocalDateTime createdDate;
    private LocalDate validatyFrom;
    private LocalDate validatyUntil;
    private Version version;
    private BigDecimal price;
    private Status status;
    private Partners partner;


    public Product(UUID productId,  Meta metaData, String code, String name, String image,
                   String description, List<Cover> covers, List<Question> questions,
                   int maxNumberOfInsured, String icon, Category category, LocalDateTime createdDate,
                   LocalDate validatyFrom, LocalDate validatyUntil, Version version,
                   BigDecimal price,Partners partner) {
        this.productId = productId;
        this.metaData = metaData;
        this.code = code;
        this.name = name;
        this.image = image;
        this.description = description;
        this.covers = covers;
        this.questions = questions;
        this.maxNumberOfInsured = maxNumberOfInsured;
        this.icon = icon;
        this.category = category;
        this.createdDate = createdDate;
        this.validatyFrom = validatyFrom;
        this.validatyUntil = validatyUntil;
        this.version = version;
        this.price = price;
        this.partner = partner;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Meta getMetaData() {
        return metaData;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public List<Cover> getCovers() {
        return covers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getMaxNumberOfInsured() {
        return maxNumberOfInsured;
    }

    public String getIcon() {
        return icon;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDate getValidatyFrom() {
        return validatyFrom;
    }

    public LocalDate getValidatyUntil() {
        return validatyUntil;
    }

    public Version getVersion() {
        return version;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }

    public Partners getPartner() {
        return partner;
    }

    public void setMetaData(Meta metaData) {
        this.metaData = metaData;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCovers(List<Cover> covers) {
        this.covers = covers;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setMaxNumberOfInsured(int maxNumberOfInsured) {
        this.maxNumberOfInsured = maxNumberOfInsured;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setValidatyFrom(LocalDate validatyFrom) {
        this.validatyFrom = validatyFrom;
    }

    public void setValidatyUntil(LocalDate validatyUntil) {
        this.validatyUntil = validatyUntil;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPartner(Partners partner) {
        this.partner = partner;
    }

    public void addCover(String code, String name, String description, boolean isOptional,
                         BigDecimal sumInsured, Product product) {
        covers.add(new Cover(code, name, description, isOptional, sumInsured, product));
    }

    public void addQuestions(List<Question> questions) {
        if (this.questions == null) {
            this.questions = new ArrayList<>();
        }
        this.questions.addAll(questions);
    }
}
