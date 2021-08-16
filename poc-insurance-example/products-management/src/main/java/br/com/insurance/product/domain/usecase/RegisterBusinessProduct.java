package br.com.insurance.product.domain.usecase;

import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.domain.repository.PartnersRepository;
import br.com.insurance.product.domain.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public class RegisterBusinessProduct {

    private final ProductRepository productRepository;
    private final PartnersRepository partnersRepository;
    private final CategoryRepository categoryRepository;

    public RegisterBusinessProduct(ProductRepository productRepository,
                                   PartnersRepository partnersRepository,
                                   CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.partnersRepository = partnersRepository;
        this.categoryRepository = categoryRepository;
    }

    public UUID cadastrarProduto(Product produto){
        return productRepository.save(produto);
    }

}
