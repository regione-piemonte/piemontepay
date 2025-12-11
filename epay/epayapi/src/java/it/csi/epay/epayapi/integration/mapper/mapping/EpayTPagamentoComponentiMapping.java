/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTPagamentoComponenti;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoComponentiDTO;

/**
 * MapStruct mapping specifications for "EpayTPagamentoComponenti" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTPagamentoComponentiReferenceMapping.class,
	EpayTPagamentoReferenceMapping.class,
} )
public interface EpayTPagamentoComponentiMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idComponente", target = "idComponente" ),
		@Mapping ( source = "progressivo", target = "progressivo" ),
		@Mapping ( source = "importo", target = "importo" ),
		@Mapping ( source = "causale", target = "causale" ),
		@Mapping ( source = "datiSpecificiRiscossione", target = "datiSpecificiRiscossione" ),
		@Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
		@Mapping ( source = "annoAccertamento", target = "annoAccertamento" ),
		@Mapping ( source = "numeroAccertamento", target = "numeroAccertamento" ),
		@Mapping ( source = "epayTPagamento", target = "epayTPagamento" ),
    	*/
    } )
    EpayTPagamentoComponentiDTO toDTO ( EpayTPagamentoComponenti input );

}
