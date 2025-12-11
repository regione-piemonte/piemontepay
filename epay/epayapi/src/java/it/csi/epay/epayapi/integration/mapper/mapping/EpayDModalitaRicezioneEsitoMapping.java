/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayDModalitaRicezioneEsito;
import it.csi.epay.epayapi.integration.dto.EpayDModalitaRicezioneEsitoDTO;

/**
 * MapStruct mapping specifications for "EpayDModalitaRicezioneEsito" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayDModalitaRicezioneEsitoReferenceMapping.class,
} )
public interface EpayDModalitaRicezioneEsitoMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idModalitaRicezioneEsito", target = "idModalitaRicezioneEsito" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    	*/
    } )
    EpayDModalitaRicezioneEsitoDTO toDTO ( EpayDModalitaRicezioneEsito input );

}
