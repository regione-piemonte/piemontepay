/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTParametri;
import it.csi.epay.epayapi.integration.dto.EpayTParametriDTO;

/**
 * MapStruct mapping specifications for "EpayTParametri" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTParametriReferenceMapping.class,
} )
public interface EpayTParametriMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "gruppo", target = "gruppo" ),
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "valore", target = "valore" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
		@Mapping ( source = "progressivo", target = "progressivo" ),
    	*/
    } )
    EpayTParametriDTO toDTO ( EpayTParametri input );

}
