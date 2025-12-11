/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTEnti;
import it.csi.epay.epayapi.integration.dto.EpayTEntiReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTEnti" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTEntiReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idEnte", target = "idEnte" ),
		@Mapping ( source = "nome", target = "nome" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "logo", target = "logo" ),
		@Mapping ( source = "codiceGs1Gln", target = "codiceGs1Gln" ),
		@Mapping ( source = "orari", target = "orari" ),
		@Mapping ( source = "flagInvioPagamenti", target = "flagInvioPagamenti" ),
		@Mapping ( source = "codiceInterbancario", target = "codiceInterbancario" ),
    } )
    EpayTEntiReferenceDTO toDTO ( EpayTEnti input );

}
