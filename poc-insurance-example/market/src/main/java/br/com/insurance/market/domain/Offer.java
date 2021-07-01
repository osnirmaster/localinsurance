package br.com.insurance.market.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Offer {

    private Product product;
    private BigDecimal price;
    private Cover covers;
    private Map<String, BigDecimal> coversPrices;
    private OfferStatus status;
    private LocalDate creationDate;


        /*
    Offers are valid only for 30 days
    */
    public boolean isExpired(LocalDate theDate) {
        return creationDate.plusDays(30).isBefore(theDate);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Cover getCovers() {
        return covers;
    }

    public void setCovers(Cover covers) {
        this.covers = covers;
    }

    public Map<String, BigDecimal> getCoversPrices() {
        return coversPrices;
    }

    public void setCoversPrices(Map<String, BigDecimal> coversPrices) {
        this.coversPrices = coversPrices;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
