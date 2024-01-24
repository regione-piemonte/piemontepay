/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDEsitoChiamataEsterna;
import it.csi.epay.epaybeapi.integration.dto.EpayDEsitoChiamataEsternaDTO;

/**
 * MapStruct mapping specifications for "EpayDEsitoChiamataEsterna" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayDEsitoChiamataEsternaReferenceMapping.class,
} )
public interface EpayDEsitoChiamataEsternaMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    	*/
    } )
    EpayDEsitoChiamataEsternaDTO toDTO ( EpayDEsitoChiamataEsterna input );

}
