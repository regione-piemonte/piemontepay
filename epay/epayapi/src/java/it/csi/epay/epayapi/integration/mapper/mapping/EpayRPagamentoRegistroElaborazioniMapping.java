/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayRPagamentoRegistroElaborazioni;
import it.csi.epay.epayapi.integration.dto.EpayRPagamentoRegistroElaborazioniDTO;

/**
 * MapStruct mapping specifications for "EpayRPagamentoRegistroElaborazioni" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayRPagamentoRegistroElaborazioniReferenceMapping.class,
} )
public interface EpayRPagamentoRegistroElaborazioniMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idPagamento", target = "idPagamento" ),
		@Mapping ( source = "idRegistroElaborazioni", target = "idRegistroElaborazioni" ),
    	*/
    } )
    EpayRPagamentoRegistroElaborazioniDTO toDTO ( EpayRPagamentoRegistroElaborazioni input );

}
