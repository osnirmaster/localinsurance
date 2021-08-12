package br.com.insurance.product.controller;

import br.com.insurance.product.domain.usecase.GetBusinessProduct;
import br.com.insurance.product.domain.usecase.RegisterBusinessProduct;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final RegisterBusinessProduct registerBusinessProduct;
    private final GetBusinessProduct getBusinessProduct;


    public ProductController(RegisterBusinessProduct registerBusinessProduct, GetBusinessProduct getBusinessProduct) {
        this.registerBusinessProduct = registerBusinessProduct;
        this.getBusinessProduct = getBusinessProduct;
    }


}
