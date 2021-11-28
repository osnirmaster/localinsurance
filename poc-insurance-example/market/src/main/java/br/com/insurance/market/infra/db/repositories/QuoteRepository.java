package br.com.insurance.market.infra.db.repositories;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.infra.db.QuoteId;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@EnableScan
@Repository
public interface QuoteRepository extends CrudRepository<Quote, QuoteId> {
}
