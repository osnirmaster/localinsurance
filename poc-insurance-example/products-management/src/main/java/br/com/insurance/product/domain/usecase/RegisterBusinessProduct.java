package br.com.insurance.product.domain.usecase;

import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.domain.repository.PartnersRepository;
import br.com.insurance.product.domain.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public class RegisterBusinessProduct {

    private final ProductRepository productRepository;


    public RegisterBusinessProduct(ProductRepository productRepository)
    {
        this.productRepository = productRepository;

    }

    public Product cadastrarProduto(Product produto){
        return productRepository.save(produto);
    }

}
