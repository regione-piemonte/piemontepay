/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTDatiSingoliVersamenti;
import it.csi.epay.epayapi.integration.dto.EpayTDatiSingoliVersamentiDTO;

/**
 * MapStruct mapping specifications for "EpayTDatiSingoliVersamenti" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTDatiSingoliVersamentiReferenceMapping.class,
	EpayTPagamentoReferenceMapping.class,
} )
public interface EpayTDatiSingoliVersamentiMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "id", target = "id" ),
		@Mapping ( source = "importo", target = "importo" ),
		@Mapping ( source = "descrizioneCausale", target = "descrizioneCausale" ),
		@Mapping ( source = "datiSpecificiRiscossione", target = "datiSpecificiRiscossione" ),
		@Mapping ( source = "epayTPagamento", target = "epayTPagamento" ),
    	*/
    } )
    EpayTDatiSingoliVersamentiDTO toDTO ( EpayTDatiSingoliVersamenti input );

}
