package br.com.insurance.product.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.insurance.product.adapters.ProductDto;
import br.com.insurance.product.adapters.ProdutcForm;
import br.com.insurance.product.domain.entity.Product;
import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.domain.repository.PartnersRepository;
import br.com.insurance.product.domain.usecase.RegisterBusinessProduct;

@RestController
@RequestMapping("/insurance/product")
public class ProductController {
    private final RegisterBusinessProduct registerBusinessProduct;
    private final CategoryRepository      categoryRepository;
    private final PartnersRepository      partnersRepository;

//
    public ProductController(RegisterBusinessProduct registerBusinessProduct, CategoryRepository categoryRepository,
                             PartnersRepository partnersRepository) {
        this.registerBusinessProduct = registerBusinessProduct;
        this.categoryRepository      = categoryRepository;
        this.partnersRepository      = partnersRepository;
    }

    @PostMapping
    public ResponseEntity<ProductDto> register(@RequestBody ProdutcForm request, UriComponentsBuilder uriBuilder) {
        Product product      = request.convertTo(categoryRepository, partnersRepository);
        Product productSaved = registerBusinessProduct.cadastrarProduto(product);
        URI     uri          = uriBuilder.path("/product/{id}").buildAndExpand(productSaved.getProductId()).toUri();

        return ResponseEntity.created(uri).body(new ProductDto(productSaved));
    }
}



