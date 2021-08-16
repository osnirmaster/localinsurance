package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Cover;
import br.com.insurance.product.infra.entities.CoverEntity;
import org.springframework.stereotype.Service;

@Service
public class CoverEntityConverter {
    private final ProductEntityConverter productConverter;

    public CoverEntityConverter(ProductEntityConverter productConverter) {
        this.productConverter = productConverter;
    }

    public Cover convertToCover(CoverEntity entity){
        return new Cover(entity.getCode(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.isOptional(),
                        entity.getSumInsured(),
                        productConverter.convertToProduct(entity.getProductEntity()));
    }

    public CoverEntity covertToCoverEntity(Cover cover){
        return new CoverEntity(cover.getCode(),
                            cover.getName(),
                            cover.getDescription(),
                            cover.isOptional(),
                            cover.getSumInsured(),
                            productConverter.convertToProductEntity(cover.getProduct()));
    }
}
