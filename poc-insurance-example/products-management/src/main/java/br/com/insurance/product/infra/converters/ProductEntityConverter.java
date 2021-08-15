package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.infra.entities.ProductEntity;

import java.util.UUID;

public class ProductEntityConverter {

    private final PartnersEntityConverter converter;

    public ProductEntityConverter(PartnersEntityConverter converter) {
        this.converter = converter;
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
                product.getCategory(),
                product.getCreatedDate(),
                product.getValidatyFrom(),
                product.getValidatyUntil(),
                converter.covertToPartnersEntity(product.getPartner()));
    }
}
