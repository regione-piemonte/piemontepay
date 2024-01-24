/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDModalitaRicezioneEsito;
import it.csi.epay.epaybeapi.integration.dto.EpayDModalitaRicezioneEsitoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDModalitaRicezioneEsito" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayDModalitaRicezioneEsitoReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idModalitaRicezioneEsito", target = "idModalitaRicezioneEsito" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    } )
    EpayDModalitaRicezioneEsitoReferenceDTO toDTO ( EpayDModalitaRicezioneEsito input );

}
