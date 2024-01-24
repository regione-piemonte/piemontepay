/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDChiamanteEsterno;
import it.csi.epay.epaybeapi.integration.dto.EpayDChiamanteEsternoDTO;

/**
 * MapStruct mapping specifications for "EpayDChiamanteEsterno" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayDChiamanteEsternoReferenceMapping.class,
    EpayTTracciabilitaChiamanteEsternoReferenceMapping.class,
} )
public interface EpayDChiamanteEsternoMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "codiceChiamante", target = "codiceChiamante" ),
		@Mapping ( source = "descrizioneChiamante", target = "descrizioneChiamante" ),
		@Mapping ( source = "dataInizioValidita", target = "dataInizioValidita" ),
		@Mapping ( source = "dataFineValidita", target = "dataFineValidita" ),
		@Mapping ( source = "passphrase", target = "passphrase" ),
		@Mapping ( source = "url", target = "url" ),
		@Mapping ( source = "listOfEpayTTracciabilitaChiamanteEsterno", target = "listOfEpayTTracciabilitaChiamanteEsterno" ),
    	*/
    } )
    EpayDChiamanteEsternoDTO toDTO ( EpayDChiamanteEsterno input );

}
