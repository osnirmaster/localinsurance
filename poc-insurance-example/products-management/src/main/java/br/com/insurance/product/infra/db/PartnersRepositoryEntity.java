package br.com.insurance.product.infra.db;

import br.com.insurance.product.infra.entities.PartnersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartnersRepositoryEntity extends JpaRepository<PartnersEntity, UUID> {
    PartnersEntity findByPartnerId(UUID id);
}
