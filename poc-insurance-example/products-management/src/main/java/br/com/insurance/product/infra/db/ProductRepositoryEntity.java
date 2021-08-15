package br.com.insurance.product.infra.db;

import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.infra.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProductRepositoryEntity extends JpaRepository<ProductEntity, UUID> {

    public ProductEntity findByCode(String productCode);
}
