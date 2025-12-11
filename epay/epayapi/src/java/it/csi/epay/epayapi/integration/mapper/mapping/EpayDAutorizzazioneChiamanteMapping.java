/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayDAutorizzazioneChiamante;
import it.csi.epay.epayapi.integration.dto.EpayDAutorizzazioneChiamanteDTO;

/**
 * MapStruct mapping specifications for "EpayDAutorizzazioneChiamante"
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {
    CSIBooleanStringMapping.class,
	EpayDAutorizzazioneChiamanteReferenceMapping.class,
    EpayDChiamanteEsternoReferenceMapping.class,
} )
public interface EpayDAutorizzazioneChiamanteMapping {

    @Mappings ( {
    	/*
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
		@Mapping ( source = "listOfEpayDChiamanteEsterno", target = "listOfEpayDChiamanteEsterno" ),
    	*/
    } )
    EpayDAutorizzazioneChiamanteDTO toDTO ( EpayDAutorizzazioneChiamante input );

}
