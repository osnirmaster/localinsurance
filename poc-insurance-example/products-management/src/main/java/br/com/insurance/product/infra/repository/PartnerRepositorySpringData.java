package br.com.insurance.product.infra.repository;

import br.com.insurance.product.domain.entity.Partners;
import br.com.insurance.product.domain.repository.PartnersRepository;
import br.com.insurance.product.infra.converters.PartnersEntityConverter;
import br.com.insurance.product.infra.db.PartnersRepositoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PartnerRepositorySpringData implements PartnersRepository {
    public final PartnersRepositoryEntity partnersRepositoryEntity;
    public final PartnersEntityConverter partnersEntityConverter;

    public PartnerRepositorySpringData(PartnersRepositoryEntity partnersRepositoryEntity,
                                       PartnersEntityConverter partnersEntityConverter) {
        this.partnersRepositoryEntity = partnersRepositoryEntity;
        this.partnersEntityConverter = partnersEntityConverter;
    }

    @Override
    public List<Partners> findAll() {
        return null;
    }

    @Override
    public Partners findByName(String partnerName) {
        return null;
    }

    @Override
    public void save(Partners partner) {

    }

    @Override
    public Partners findByPartnerId(UUID partnerId) {

        return partnersEntityConverter
                .convertToPartners(partnersRepositoryEntity.findByPartnerId(partnerId));
    }
}
