package br.com.insurance.product.infra.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Question")
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "question_id", updatable = false, nullable = false)
    private UUID idQuestion;
    private String code;
    private int index;
    private String text;
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    public QuestionEntity(){}

    public QuestionEntity(String code,
                          int index,
                          String text,
                          ProductEntity product) {
        this.code = code;
        this.index = index;
        this.text = text;
        this.product = product;
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
        return product;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.product= product;
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
