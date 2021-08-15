package br.com.insurance.product.domain.repository;

import br.com.insurance.product.domain.entity.Category;
import br.com.insurance.product.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository {

    List<Category> findAll();

    Category findByCategoryId(UUID categoryId);

    void save(Category category);
}
