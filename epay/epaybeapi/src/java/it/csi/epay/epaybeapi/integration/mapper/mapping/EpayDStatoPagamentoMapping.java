/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDStatoPagamento;
import it.csi.epay.epaybeapi.integration.dto.EpayDStatoPagamentoDTO;

/**
 * MapStruct mapping specifications for "EpayDStatoPagamento" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayDStatoPagamentoReferenceMapping.class,
    EpayTRegistroVersamentiReferenceMapping.class,
    EpayTPagamentoReferenceMapping.class,
} )
public interface EpayDStatoPagamentoMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idStato", target = "idStato" ),
		@Mapping ( source = "descStato", target = "descStato" ),
		@Mapping ( source = "pagabile", target = "pagabile" ),
		@Mapping ( source = "modificabile", target = "modificabile" ),
		@Mapping ( source = "listOfEpayTRegistroVersamenti", target = "listOfEpayTRegistroVersamenti" ),
		@Mapping ( source = "listOfEpayTPagamento", target = "listOfEpayTPagamento" ),
    	*/
    } )
    EpayDStatoPagamentoDTO toDTO ( EpayDStatoPagamento input );

}
