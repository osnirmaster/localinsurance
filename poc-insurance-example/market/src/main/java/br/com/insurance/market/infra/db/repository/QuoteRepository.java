package br.com.insurance.market.infra.db.repository;

import br.com.insurance.market.domain.Quote;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

@EnableScan
public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}
