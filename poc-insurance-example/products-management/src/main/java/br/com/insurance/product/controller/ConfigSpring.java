package br.com.insurance.product.controller;

import br.com.insurance.product.domain.repository.CategoryRepository;
import br.com.insurance.product.domain.repository.PartnersRepository;
import br.com.insurance.product.domain.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSpring {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PartnersRepository partnersRepository;


    @Bean
    public ListagemDeCargos listagemDeCargos() {
        return new ListagemDeCargos(cargoRepository);
    }

    @Bean
    public CadastroDeCargo cadastroDeCargo() {
        return new CadastroDeCargo(validadoresCadastroCargo(), cargoRepository);
    }

}
