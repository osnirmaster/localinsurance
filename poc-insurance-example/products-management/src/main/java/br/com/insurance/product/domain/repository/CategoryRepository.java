package br.com.insurance.product.domain.repository;

import br.com.insurance.product.domain.entity.Category;
import br.com.insurance.product.domain.entity.Product;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll();

    Category findByCategoryIdString(Long categoryId);

    void save(Category category);
}
