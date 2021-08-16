package br.com.insurance.product.infra.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Cover")
@Table(name = "cover")
public class CoverEntity {

    @Id
    @Column(name = "cover_id", updatable = false, nullable = false)
    private UUID idCover = UUID.randomUUID();
    private String code;
    private String name;
    private String description;
    private boolean optional;
    private BigDecimal sumInsured;
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    public CoverEntity(){}

    public CoverEntity(String code, String name, String description, boolean optional,
                       BigDecimal sumInsured, ProductEntity productEntity) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.optional = optional;
        this.sumInsured = sumInsured;
        this.product= product;
    }

    public UUID getIdCover() {
        return idCover;
    }

    public void setIdCover(UUID idCover) {
        this.idCover = idCover;
    }

    public ProductEntity getProductEntity() {
        return product;
    }

    public void setProductEntity(ProductEntity product) {
        this.product = product;
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
