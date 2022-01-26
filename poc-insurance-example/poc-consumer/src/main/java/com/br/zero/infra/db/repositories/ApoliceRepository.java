package com.br.zero.infra.db.repositories;

import com.br.zero.domain.Apolice;
import com.br.zero.domain.ApoliceKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ApoliceRepository extends CrudRepository<Apolice, ApoliceKey> {
}
