package br.com.insurance.product.infra.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Cover")
@Table(name = "cover")
public class CoverEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idCover;
    private String code;
    private String name;
    private String description;
    private boolean optional;
    private BigDecimal sumInsured;
    @ManyToOne
    private ProductEntity productEntity;

    public CoverEntity(){}

    public CoverEntity(String code, String name, String description, boolean optional,
                       BigDecimal sumInsured, ProductEntity productEntity) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.optional = optional;
        this.sumInsured = sumInsured;
        this.productEntity = productEntity;
    }

    public UUID getIdCover() {
        return idCover;
    }

    public void setIdCover(UUID idCover) {
        this.idCover = idCover;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverEntity that = (CoverEntity) o;
        return optional == that.optional && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(sumInsured, that.sumInsured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, description, optional, sumInsured);
    }
}
