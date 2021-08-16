package br.com.insurance.product.controller;

import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.domain.repository.PartnersRepository;
import br.com.insurance.product.domain.repository.ProductRepository;
import br.com.insurance.product.domain.usecase.RegisterBusinessProduct;
import br.com.insurance.product.infra.converters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfig {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PartnersRepository partnersRepository;


    public ProductBeanConfig(ProductRepository productRepository,
                             CategoryRepository categoryRepository,
                             PartnersRepository partnersRepository)
  {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.partnersRepository = partnersRepository;

    }

    @Bean
    public RegisterBusinessProduct registerProduct(){
        return new RegisterBusinessProduct( productRepository,
                                            partnersRepository,
                                            categoryRepository);
    }


}
