package br.com.insurance.market.infra.db.repositories;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


@EnableScan
public interface QuoteRepository extends CrudRepository<Quote, QuoteId> {
}
