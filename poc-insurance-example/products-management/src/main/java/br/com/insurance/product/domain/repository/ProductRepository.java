package br.com.insurance.product.domain.repository;

import br.com.insurance.product.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {

    List<Product> findAll();

    Product findByCode(String productCode);

    Product save(Product product);
}
