/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDStatoErrore;
import it.csi.epay.epaybeapi.integration.dto.EpayDStatoErroreDTO;

/**
 * MapStruct mapping specifications for "EpayDStatoErrore" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayDStatoErroreReferenceMapping.class,
    EpayTErroriReferenceMapping.class,
} )
public interface EpayDStatoErroreMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idStato", target = "idStato" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
		@Mapping ( source = "listOfEpayTErrori", target = "listOfEpayTErrori" ),
    	*/
    } )
    EpayDStatoErroreDTO toDTO ( EpayDStatoErrore input );

}
