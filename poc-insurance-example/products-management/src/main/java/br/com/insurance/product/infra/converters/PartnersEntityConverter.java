package br.com.insurance.product.infra.converters;

import br.com.insurance.product.domain.entity.Partners;
import br.com.insurance.product.infra.entities.PartnersEntity;

import java.util.UUID;

public class PartnersEntityConverter {

    public Partners convertToPartners(PartnersEntity entity){

        return new Partners(entity.getPartnerName(),
                entity.getDescription(),
                entity.getPaymentsMethod(),
                entity.getCallbackUrl());

    }

    public PartnersEntity covertToPartnersEntity( Partners partner){

        return new PartnersEntity(
                    partner.getPartnerName(),
                    partner.getDescription(),
                    partner.getPaymentsMethod(),
                    partner.getCallbackUrl());
    }
}
