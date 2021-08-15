package br.com.insurance.product.controller;

import br.com.insurance.product.domain.usecase.GetBusinessProduct;
import br.com.insurance.product.domain.usecase.RegisterBusinessProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/insurance/product")
public class ProductController {
    private final RegisterBusinessProduct registerBusinessProduct;
    private final GetBusinessProduct getBusinessProduct;


    public ProductController(RegisterBusinessProduct registerBusinessProduct, GetBusinessProduct getBusinessProduct) {
        this.registerBusinessProduct = registerBusinessProduct;
        this.getBusinessProduct = getBusinessProduct;
    }

    @PostMapping
    public ResponseEntity<ProductDto> register(@RequestBody ProdutcForm request, UriComponentsBuilder uriBuilder){

         registerBusinessProduct.cadastrarProduto( request);

    }

}
