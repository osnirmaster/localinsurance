package br.com.insurance.product.infra.db;

import br.com.insurance.product.infra.entities.ProductEntity;
import br.com.insurance.product.infra.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionRepositoryEntity extends JpaRepository<QuestionEntity, UUID> {
}
