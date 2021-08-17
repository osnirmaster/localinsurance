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

    public ProductBeanConfig(ProductRepository productRepository)
    {
        this.productRepository = productRepository;

    }

    @Bean
    public RegisterBusinessProduct registerProduct(){
        return new RegisterBusinessProduct( productRepository);
    }


}
