/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDMessaggio;
import it.csi.epay.epaybeapi.integration.dto.EpayDMessaggioReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDMessaggio" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayDMessaggioReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "valore", target = "valore" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    } )
    EpayDMessaggioReferenceDTO toDTO ( EpayDMessaggio input );

}
