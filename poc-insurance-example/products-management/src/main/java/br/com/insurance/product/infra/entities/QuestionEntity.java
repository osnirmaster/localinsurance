package br.com.insurance.product.infra.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Question")
@Table(name = "question")
public class QuestionEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idQuestion;
    private String code;
    private int index;
    private String text;
    @ManyToOne
    private ProductEntity productEntity;

    public QuestionEntity(){}

    public QuestionEntity(String code, int index, String text, ProductEntity productEntity) {
        this.code = code;
        this.index = index;
        this.text = text;
        this.productEntity = productEntity;
    }


    public UUID getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(UUID idQuestion) {
        this.idQuestion = idQuestion;
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

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionEntity question = (QuestionEntity) o;
        return index == question.index && Objects.equals(idQuestion, question.idQuestion) && Objects.equals(code, question.code) && Objects.equals(text, question.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuestion, code, index, text);
    }
}
