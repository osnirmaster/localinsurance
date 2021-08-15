package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Category;
import br.com.insurance.product.infra.entities.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryEntityConverter {

    public Category convertToCategory(CategoryEntity entity) {
        return new Category(entity.getCategoryName(),
                    entity.getDescription(),
                    entity.getStatus());
    }

    public CategoryEntity convertToCategoryEntity(Category category) {
        return new CategoryEntity(  category.getCategoryName(),
                                    category.getDescription(),
                                    category.getStatus());
    }

}
