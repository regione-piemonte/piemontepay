/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDModalitaPagamento;
import it.csi.epay.epaybeapi.integration.dto.EpayDModalitaPagamentoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDModalitaPagamento" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayDModalitaPagamentoReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idModalitaPagamento", target = "idModalitaPagamento" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    } )
    EpayDModalitaPagamentoReferenceDTO toDTO ( EpayDModalitaPagamento input );

}
