package br.com.insurance.product.infra.entities;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class CoverEntity {

    private UUID idCover;
    private String code;
    private String name;
    private String description;
    private boolean optional;
    private BigDecimal sumInsured;

    public CoverEntity(){}

    public CoverEntity(String code, String name, String description, boolean optional, BigDecimal sumInsured) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.optional = optional;
        this.sumInsured = sumInsured;
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
