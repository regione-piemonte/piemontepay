/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTEntiLog;
import it.csi.epay.epaybeapi.integration.dto.EpayTEntiLogReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTEntiLog" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTEntiLogReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "seq", target = "seq" ),
		@Mapping ( source = "nome", target = "nome" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "logo", target = "logo" ),
		@Mapping ( source = "codiceGs1Gln", target = "codiceGs1Gln" ),
		@Mapping ( source = "orari", target = "orari" ),
		@Mapping ( source = "flagInvioPagamenti", target = "flagInvioPagamenti" ),
		@Mapping ( source = "codiceInterbancario", target = "codiceInterbancario" ),
		@Mapping ( source = "dataTrigger", target = "dataTrigger" ),
    } )
    EpayTEntiLogReferenceDTO toDTO ( EpayTEntiLog input );

}
