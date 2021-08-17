package br.com.insurance.product.adapters;

import br.com.insurance.product.domain.entity.*;
import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.domain.repository.PartnersRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProdutcForm {

    private Meta metaData;
    private String code;
    private String name;
    private String image;
    private String description;
    private List<Cover> covers;
    private List<Question> questions;
    private int maxNumberOfInsured;
    private String icon;
    private UUID categoryId;
    private String categoryCode;
    private LocalDateTime createdDate;
    private LocalDate validatyFrom;
    private LocalDate validatyUntil;
    private Version version;
    private BigDecimal price;
    private Status status;
    private UUID partnerId;

    public Meta getMetaData() {
        return metaData;
    }

    public void setMetaData(Meta metaData) {
        this.metaData = metaData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public List<Cover> getCovers() {
        return covers;
    }

    public void setCovers(List<Cover> covers) {
        this.covers = covers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getMaxNumberOfInsured() {
        return maxNumberOfInsured;
    }

    public void setMaxNumberOfInsured(int maxNumberOfInsured) {
        this.maxNumberOfInsured = maxNumberOfInsured;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID category) {
        this.categoryId = category;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getValidatyFrom() {
        return validatyFrom;
    }

    public void setValidatyFrom(LocalDate validatyFrom) {
        this.validatyFrom = validatyFrom;
    }

    public LocalDate getValidatyUntil() {
        return validatyUntil;
    }

    public void setValidatyUntil(LocalDate validatyUntil) {
        this.validatyUntil = validatyUntil;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partner) {
        this.partnerId = partner;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Product convertTo(CategoryRepository categoryRepository, PartnersRepository partnersRepository){
        Category category = new Category(categoryId);
        System.out.println("Teste Cat: " + category.getCategoryName());
        Partners partner = partnersRepository.findByPartnerId(partnerId);

        return new Product(
                            null,
                            this.metaData,
                            this.code,
                            this.name,
                            this.image,
                            this.description,
                            this.covers,
                            this.questions,
                            this.maxNumberOfInsured,
                            this.icon,
                            category,
                            this.createdDate,
                            this.validatyFrom,
                            this.validatyUntil,
                            this.version,
                            this.price,
                            partner);
    }
}
