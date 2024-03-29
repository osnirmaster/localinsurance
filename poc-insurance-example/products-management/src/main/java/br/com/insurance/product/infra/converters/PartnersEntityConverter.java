package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Partners;
import br.com.insurance.product.infra.entities.PartnersEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PartnersEntityConverter {

    public Partners convertToPartners(PartnersEntity entity){

        return new Partners(
                entity.getPartnerId(),
                entity.getPartnerName(),
                entity.getDescription(),
                entity.getPaymentsMethod(),
                entity.getCallbackUrl());

    }

    public PartnersEntity covertToPartnersEntity( Partners partner){

        return new PartnersEntity(
                    partner.getPartnerId(),
                    partner.getPartnerName(),
                    partner.getDescription(),
                    partner.getPaymentsMethod(),
                    partner.getCallbackUrl());
    }
}
