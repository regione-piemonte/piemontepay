/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayRChiamanteAutorizzazioneChiamante;
import it.csi.epay.epayapi.integration.dto.EpayRChiamanteAutorizzazioneChiamanteDTO;

/**
 * MapStruct mapping specifications for "EpayRChiamanteAutorizzazioneChiamante" 
 *
 * @author fabio.fenoglio
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
