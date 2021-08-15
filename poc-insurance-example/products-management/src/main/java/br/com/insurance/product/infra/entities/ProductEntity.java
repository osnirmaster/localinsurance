package br.com.insurance.product.infra.entities;

import br.com.insurance.product.domain.entity.*;
import br.com.insurance.product.domain.entity.Version;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "product")
public class ProductEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private Meta metaData;
    private String code;
    private String name;
    private String image;
    private String description;
    private List<Cover> covers;
    private List<Question> questions;
    private int maxNumberOfInsured;
    private String icon;

    @ManyToOne
    private Category category;

    private LocalDateTime createdDate = LocalDateTime.now();;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate validatyFrom;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate validatyUntil;

    private Version version;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToOne
    private PartnersEntity partner;

    //JPA requires
    public ProductEntity(){}

    public ProductEntity(UUID idProduct,Meta metaData, String code, String name, String image,
                   String description, List<Cover> covers, List<Question> questions,
                   int maxNumberOfInsured, String icon, Category category, LocalDateTime createdDate,
                   LocalDate validatyFrom, LocalDate validatyUntil, PartnersEntity partner) {
        this.idProduct = idProduct;
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
        this.partner = partner;
    }

    public UUID getIdProduct() {
        return idProduct;
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

    public PartnersEntity getPartner() {
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

    public void setPartner(PartnersEntity partner) {
        this.partner = partner;
    }

    public void addCover(String code, String name, String description, boolean isOptional, BigDecimal sumInsured) {
        covers.add(new Cover(code, name, description, isOptional, sumInsured));
    }

    public void addQuestions(List<Question> questions) {
        if (this.questions == null) {
            this.questions = new ArrayList<>();
        }
        this.questions.addAll(questions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return maxNumberOfInsured == that.maxNumberOfInsured && Objects.equals(idProduct, that.idProduct) && Objects.equals(metaData, that.metaData) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(image, that.image) && Objects.equals(description, that.description) && Objects.equals(covers, that.covers) && Objects.equals(questions, that.questions) && Objects.equals(icon, that.icon) && Objects.equals(category, that.category) && Objects.equals(createdDate, that.createdDate) && Objects.equals(validatyFrom, that.validatyFrom) && Objects.equals(validatyUntil, that.validatyUntil) && Objects.equals(version, that.version) && Objects.equals(price, that.price) && status == that.status && Objects.equals(partner, that.partner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, metaData, code, name, image, description, covers, questions, maxNumberOfInsured, icon, category, createdDate, validatyFrom, validatyUntil, version, price, status, partner);
    }
}
