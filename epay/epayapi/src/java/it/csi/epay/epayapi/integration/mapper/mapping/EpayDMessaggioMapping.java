/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayDMessaggio;
import it.csi.epay.epayapi.integration.dto.EpayDMessaggioDTO;

/**
 * MapStruct mapping specifications for "EpayDMessaggio" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayDMessaggioReferenceMapping.class,
} )
public interface EpayDMessaggioMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "valore", target = "valore" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    	*/
    } )
    EpayDMessaggioDTO toDTO ( EpayDMessaggio input );

}
