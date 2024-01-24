/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDAutorizzazioneChiamante;
import it.csi.epay.epaybeapi.integration.dto.EpayDAutorizzazioneChiamanteReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDAutorizzazioneChiamante"
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {
    CSIBooleanStringMapping.class
} )
public interface EpayDAutorizzazioneChiamanteReferenceMapping {

    @Mappings ( {
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    } )
    EpayDAutorizzazioneChiamanteReferenceDTO toDTO ( EpayDAutorizzazioneChiamante input );

}
