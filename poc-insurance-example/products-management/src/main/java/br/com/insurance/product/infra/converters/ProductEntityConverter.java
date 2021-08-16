package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.infra.entities.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductEntityConverter {

    private final PartnersEntityConverter converterPartners;
    private final CategoryEntityConverter converterCategory;
    private final QuestionEntityConverter converterQuestion;
    private final CategoryRepository categoryRepository;
    private CoverEntityConverter converterCover;

    public ProductEntityConverter(PartnersEntityConverter converterPartners,
                                  CategoryEntityConverter converterCategory,
                                  QuestionEntityConverter converterQuestion,
                                  CategoryRepository categoryRepository) {
        this.converterPartners = converterPartners;
        this.converterCategory = converterCategory;
        this.converterQuestion = converterQuestion;
        this.categoryRepository = categoryRepository;
        ;
    }

    public Product convertToProduct(ProductEntity entity){
        return new Product( entity.getMetaData(),
                            entity.getCode(),
                            entity.getName(),
                            entity.getImage(),
                            entity.getDescription(),
                            entity.getCoverEntity()
                                    .stream()
                                    .map(entityCover -> converterCover.convertToCover(entityCover))
                                            .collect(Collectors.toList()),
                            entity.getQuestionEntity()
                                    .stream()
                                    .map(entityQuestion -> converterQuestion.convertToQuestion(entityQuestion))
                                    .collect(Collectors.toList()),
                            entity.getMaxNumberOfInsured(),
                            entity.getIcon(),
                            converterCategory.convertToCategory(entity.getCategory()) ,
                            entity.getCreatedDate(),
                            entity.getValidatyFrom(),
                            entity.getValidatyUntil(),
                            entity.getVersion(),
                            entity.getPrice(),
                            converterPartners.convertToPartners(entity.getPartner()));
    }

    public ProductEntity convertToProductEntity(Product product){
        return new ProductEntity(
                product.getMetaData(),
                product.getCode(),
                product.getName(),
                product.getImage(),
                product.getDescription(),
                product.getCovers()
                        .stream()
                        .map(entity -> converterCover.covertToCoverEntity(entity))
                        .collect(Collectors.toList()),
                product.getQuestions()
                        .stream()
                        .map(entity -> converterQuestion.convertToQuestionEntity(entity))
                        .collect(Collectors.toList()),
                product.getMaxNumberOfInsured(),
                product.getIcon(),
                converterCategory.convertToCategoryEntity(product.getCategory()),
                product.getCreatedDate(),
                product.getValidatyFrom(),
                product.getValidatyUntil(),
                product.getVersion(),
                product.getPrice(),
                converterPartners.covertToPartnersEntity(product.getPartner()));
    }
}
