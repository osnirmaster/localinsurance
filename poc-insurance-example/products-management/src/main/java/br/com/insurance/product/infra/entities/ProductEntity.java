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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idProductEntity;
    private Meta metaData;
    private String code;
    private String name;
    private String image;
    private String description;
    @OneToMany(mappedBy = "product")
    private List<CoverEntity> coverEntity;
    @OneToMany(mappedBy = "product")
    private List<QuestionEntity> questionEntity;
    private int maxNumberOfInsured;
    private String icon;
    @ManyToOne
    private CategoryEntity category;
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

    public ProductEntity(Meta metaData, String code, String name, String image,
                   String description, List<CoverEntity> coverEntity, List<QuestionEntity> questionEntity,
                   int maxNumberOfInsured, String icon, CategoryEntity category, LocalDateTime createdDate,
                   LocalDate validatyFrom, LocalDate validatyUntil, PartnersEntity partner) {
        this.metaData = metaData;
        this.code = code;
        this.name = name;
        this.image = image;
        this.description = description;
        this.coverEntity = coverEntity;
        this.questionEntity = questionEntity;
        this.maxNumberOfInsured = maxNumberOfInsured;
        this.icon = icon;
        this.category = category;
        this.createdDate = createdDate;
        this.validatyFrom = validatyFrom;
        this.validatyUntil = validatyUntil;
        this.partner = partner;
    }

    public UUID getidProductEntity() {
        return idProductEntity;
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

    public List<CoverEntity> getCoverEntity() {
        return coverEntity;
    }

    public List<QuestionEntity> getQuestionEntitys() {
        return questionEntity;
    }

    public int getMaxNumberOfInsured() {
        return maxNumberOfInsured;
    }

    public String getIcon() {
        return icon;
    }

    public CategoryEntity getCategory() {
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

    public void setCoverEntity(List<CoverEntity> coverEntity) {
        this.coverEntity = coverEntity;
    }

    public void setQuestionEntitys(List<QuestionEntity> questionEntitys) {
        this.questionEntity = questionEntity;
    }

    public void setMaxNumberOfInsured(int maxNumberOfInsured) {
        this.maxNumberOfInsured = maxNumberOfInsured;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCategory(CategoryEntity category) {
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

    public void addCoverEntity(String code, String name, String description,
                               boolean isOptional, BigDecimal sumInsured, ProductEntity productEntity) {
        coverEntity.add(new CoverEntity(code, name, description, isOptional, sumInsured, productEntity));
    }

    public void addQuestionEntitys(List<QuestionEntity> QuestionEntitys) {
        if (this.questionEntity == null) {
            this.questionEntity = new ArrayList<>();
        }
        this.questionEntity.addAll(QuestionEntitys);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return maxNumberOfInsured == that.maxNumberOfInsured && Objects.equals(idProductEntity, that.idProductEntity) && Objects.equals(metaData, that.metaData) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(image, that.image) &&
                Objects.equals(description, that.description) && Objects.equals(coverEntity, that.coverEntity) &&
                Objects.equals(questionEntity, that.questionEntity) && Objects.equals(icon, that.icon) &&
                Objects.equals(category, that.category) && Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(validatyFrom, that.validatyFrom) && Objects.equals(validatyUntil, that.validatyUntil) &&
                Objects.equals(version, that.version) && Objects.equals(price, that.price) &&
                status == that.status && Objects.equals(partner, that.partner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductEntity, metaData, code, name, image, description, coverEntity, questionEntity, maxNumberOfInsured, icon, category, createdDate, validatyFrom, validatyUntil, version, price, status, partner);
    }
}
