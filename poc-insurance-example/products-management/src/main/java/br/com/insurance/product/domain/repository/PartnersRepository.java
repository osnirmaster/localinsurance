package br.com.insurance.product.domain.repository;

import br.com.insurance.product.domain.entity.Partners;
import br.com.insurance.product.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface PartnersRepository {

    List<Partners> findAll();

    Partners findByName(String partnerName);

    void save(Partners partner);

    Partners findByPartnerId(UUID partner);
}
