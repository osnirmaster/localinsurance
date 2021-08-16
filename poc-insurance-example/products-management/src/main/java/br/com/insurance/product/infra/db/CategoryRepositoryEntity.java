package br.com.insurance.product.infra.db;

import br.com.insurance.product.domain.entity.Category;
import br.com.insurance.product.infra.entities.CategoryEntity;
import br.com.insurance.product.infra.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepositoryEntity extends JpaRepository<CategoryEntity, UUID> {
    CategoryEntity findByCategoryId(UUID id);
    CategoryEntity findByCategoryCode(String categoryCode);
}
