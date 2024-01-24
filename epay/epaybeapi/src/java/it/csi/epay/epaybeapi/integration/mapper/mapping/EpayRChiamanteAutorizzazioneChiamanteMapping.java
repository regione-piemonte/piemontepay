/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayRChiamanteAutorizzazioneChiamante;
import it.csi.epay.epaybeapi.integration.dto.EpayRChiamanteAutorizzazioneChiamanteDTO;

/**
 * MapStruct mapping specifications for "EpayRChiamanteAutorizzazioneChiamante" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayRChiamanteAutorizzazioneChiamanteReferenceMapping.class,
} )
public interface EpayRChiamanteAutorizzazioneChiamanteMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "codiceChiamante", target = "codiceChiamante" ),
		@Mapping ( source = "codiceAutorizzazioneChiamante", target = "codiceAutorizzazioneChiamante" ),
    	*/
    } )
    EpayRChiamanteAutorizzazioneChiamanteDTO toDTO ( EpayRChiamanteAutorizzazioneChiamante input );

}
