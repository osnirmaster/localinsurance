package br.com.insurance.product.domain.repository;

import br.com.insurance.product.domain.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product findByCode(String productCode);

    void save(Product product);
}
