/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayDStatoErrore;
import it.csi.epay.epayapi.integration.dto.EpayDStatoErroreReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDStatoErrore" 
 *
 * @author fabio.fenoglio
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
