package br.com.insurance.product.infra.repository;

import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.domain.repository.ProductRepository;
import br.com.insurance.product.infra.converters.ProductEntityConverter;
import br.com.insurance.product.infra.db.CategoryRepositoryEntity;
import br.com.insurance.product.infra.db.PartnersRepositoryEntity;
import br.com.insurance.product.infra.db.ProductRepositoryEntity;
import br.com.insurance.product.infra.entities.CategoryEntity;
import br.com.insurance.product.infra.entities.ProductEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductRepositorySpringData implements ProductRepository {

    private final ProductRepositoryEntity productRepositoryEntity;
    private final ProductEntityConverter converter;

    public ProductRepositorySpringData(ProductRepositoryEntity productRepositoryEntity,
                                       ProductEntityConverter converter){
        this.productRepositoryEntity = productRepositoryEntity;
        this.converter = converter;
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntity = productRepositoryEntity.findAll();
        return productEntity.stream()
                .map(entity -> converter.convertToProduct(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Product findByCode(String productCode) {
        return converter.convertToProduct(
                productRepositoryEntity.findByCode(productCode));
    }

    @Transactional
    @Override
    public Product save(Product product) {
        ProductEntity productEntity = converter.convertToProductEntity(product);
        productRepositoryEntity.save(productEntity);
        return converter.convertToProduct(productEntity) ;
    }
}

