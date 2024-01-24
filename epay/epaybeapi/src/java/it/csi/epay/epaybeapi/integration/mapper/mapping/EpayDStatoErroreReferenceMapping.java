/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDStatoErrore;
import it.csi.epay.epaybeapi.integration.dto.EpayDStatoErroreReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDStatoErrore" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayDStatoErroreReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idStato", target = "idStato" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    } )
    EpayDStatoErroreReferenceDTO toDTO ( EpayDStatoErrore input );

}
