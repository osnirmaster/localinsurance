package br.com.insurance.market.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quota {

    private String productCode;

    private Map<String, Cover> covers = new HashMap<>();
    private List<QuestionAnswer> answers;
    private BigDecimal totalPrice;

    public Quota(String productCode, Map<String, Cover> selectedCovers,
                 List<QuestionAnswer> answers) {
        this.productCode = productCode;

        this.covers = selectedCovers;
        this.answers = answers;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public Map<String, Cover> getCovers() {
        return covers;
    }

    public void setCovers(Map<String, Cover> covers) {
        this.covers = covers;
    }

    public List<QuestionAnswer> getAnswers() {

        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }


    public Quota calculatePrice(Product product){
        calculateCovers();
        totalPrice.add(product.getPrice());

        return this;
    }

    public void calculateCovers(){
        totalPrice = covers
                .values()
                .stream()
                .filter(c -> c.getPrice() != null)
                .map(Cover::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
