package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Question;
import br.com.insurance.product.infra.entities.QuestionEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionEntityConverter {
    private ProductEntityConverter productConverter;


    public Question convertToQuestion(QuestionEntity entity) {
        return new Question(entity.getCode(),
                        entity.getIndex(),
                        entity.getText(),
                        productConverter.convertToProduct(entity.getProductEntity()));
    }

    public QuestionEntity convertToQuestionEntity(Question question){
        return new QuestionEntity(question.getCode(),
                                question.getIndex(),
                                question.getText(),
                                productConverter.convertToProductEntity(question.getProduct()));
    }
}
