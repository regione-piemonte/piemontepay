/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTPagamentoComponentiStorico;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoComponentiStoricoDTO;

/**
 * MapStruct mapping specifications for "EpayTPagamentoComponentiStorico" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTPagamentoComponentiStoricoReferenceMapping.class,
} )
public interface EpayTPagamentoComponentiStoricoMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "id", target = "id" ),
		@Mapping ( source = "dataStoricizzazione", target = "dataStoricizzazione" ),
		@Mapping ( source = "idComponente", target = "idComponente" ),
		@Mapping ( source = "idPagamento", target = "idPagamento" ),
		@Mapping ( source = "progressivo", target = "progressivo" ),
		@Mapping ( source = "importo", target = "importo" ),
		@Mapping ( source = "causale", target = "causale" ),
		@Mapping ( source = "datiSpecificiRiscossione", target = "datiSpecificiRiscossione" ),
		@Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
    	*/
    } )
    EpayTPagamentoComponentiStoricoDTO toDTO ( EpayTPagamentoComponentiStorico input );

}
