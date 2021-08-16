package br.com.insurance.product.infra.repository;

import br.com.insurance.product.domain.entity.Category;
import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.infra.converters.CategoryEntityConverter;
import br.com.insurance.product.infra.db.CategoryRepositoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CategoryRepositorySpringData implements CategoryRepository {
    private final CategoryRepositoryEntity categoryRepositoryEntity;
    private final CategoryEntityConverter categoryEntityConverter;

    public CategoryRepositorySpringData(CategoryRepositoryEntity categoryRepositoryEntity,
                                        CategoryEntityConverter categoryEntityConverter) {
        this.categoryRepositoryEntity = categoryRepositoryEntity;
        this.categoryEntityConverter = categoryEntityConverter;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findByCategoryId(UUID categoryId) {

        return categoryEntityConverter
                .convertToCategory(categoryRepositoryEntity.findByCategoryId(categoryId)) ;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public Category findByCategoryCode(String categoryCode) {
        return categoryEntityConverter
                .convertToCategory(categoryRepositoryEntity.findByCategoryCode(categoryCode)) ;
    }
}
