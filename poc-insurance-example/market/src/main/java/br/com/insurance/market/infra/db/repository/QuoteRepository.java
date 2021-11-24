package br.com.insurance.market.infra.db.repository;

import br.com.insurance.market.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}
