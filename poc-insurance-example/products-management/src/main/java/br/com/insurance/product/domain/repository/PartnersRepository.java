package br.com.insurance.product.domain.repository;

import br.com.insurance.product.domain.entity.Partners;
import br.com.insurance.product.domain.entity.Product;

import java.util.List;

public interface PartnersRepository {

    List<Partners> findAll();

    Partners findByName(String partnerName);

    void save(Partners partner);
}
