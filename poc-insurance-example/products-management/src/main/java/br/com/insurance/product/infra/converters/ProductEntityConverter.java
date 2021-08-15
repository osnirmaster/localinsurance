package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.infra.entities.ProductEntity;

public class ProductEntityConverter {

    private final PartnersEntityConverter converterPartners;
    private final CategoryEntityConverter converterCategory;

    public ProductEntityConverter(PartnersEntityConverter converterPartners,
                                  CategoryEntityConverter converterCategory) {
        this.converterPartners = converterPartners;
        this.converterCategory = converterCategory;
    }

    public Product convertToProduct(ProductEntity entity){
        return new Product( entity.getMetaData(),
                            entity.getCode(),
                            entity.getName(),
                            entity.getImage(),
                            entity.getDescription(),
                            entity.getCovers(),
                            entity.getQuestions(),
                            entity.getMaxNumberOfInsured(),
                            entity.getIcon(),
                            entity.getCategory(),
                            entity.getCreatedDate(),
                            entity.getValidatyFrom(),
                            entity.getValidatyUntil(),
                converter.convertToPartners(entity.getPartner()));
    }

    public ProductEntity convertToProductEntity(Product product){
        return new ProductEntity(
                product.getMetaData(),
                product.getCode(),
                product.getName(),
                product.getImage(),
                product.getDescription(),
                product.getCovers(),
                product.getQuestions(),
                product.getMaxNumberOfInsured(),
                product.getIcon(),
                converterCategory.convertToCategoryEntity(product.getCategory()) ,
                product.getCreatedDate(),
                product.getValidatyFrom(),
                product.getValidatyUntil(),
                converterPartners.covertToPartnersEntity(product.getPartner()));
    }
}
