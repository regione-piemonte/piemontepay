/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTEntiTemp;
import it.csi.epay.epayapi.integration.dto.EpayTEntiTempDTO;

/**
 * MapStruct mapping specifications for "EpayTEntiTemp" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTEntiTempReferenceMapping.class,
	EpayTEntiReferenceMapping.class,
} )
public interface EpayTEntiTempMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idEnteTemp", target = "idEnteTemp" ),
		@Mapping ( source = "idOperazione", target = "idOperazione" ),
		@Mapping ( source = "nome", target = "nome" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "logo", target = "logo" ),
		@Mapping ( source = "codiceGs1Gln", target = "codiceGs1Gln" ),
		@Mapping ( source = "orari", target = "orari" ),
		@Mapping ( source = "flagInvioPagamenti", target = "flagInvioPagamenti" ),
		@Mapping ( source = "codiceInterbancario", target = "codiceInterbancario" ),
		@Mapping ( source = "epayTEnti", target = "epayTEnti" ),
    	*/
    } )
    EpayTEntiTempDTO toDTO ( EpayTEntiTemp input );

}
