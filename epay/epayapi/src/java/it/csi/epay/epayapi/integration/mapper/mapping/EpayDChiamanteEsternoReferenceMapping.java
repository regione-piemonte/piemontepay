/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayDChiamanteEsterno;
import it.csi.epay.epayapi.integration.dto.EpayDChiamanteEsternoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDChiamanteEsterno" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayDChiamanteEsternoReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "codiceChiamante", target = "codiceChiamante" ),
		@Mapping ( source = "descrizioneChiamante", target = "descrizioneChiamante" ),
		@Mapping ( source = "dataInizioValidita", target = "dataInizioValidita" ),
		@Mapping ( source = "dataFineValidita", target = "dataFineValidita" ),
    } )
    EpayDChiamanteEsternoReferenceDTO toDTO ( EpayDChiamanteEsterno input );

}
