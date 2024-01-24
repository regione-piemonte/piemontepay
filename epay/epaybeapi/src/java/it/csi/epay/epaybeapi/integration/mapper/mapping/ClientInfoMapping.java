/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.dto.security.ClientInfo;
import it.csi.epay.epaybeapi.dto.security.ClientInfoDTO;


/**
 * MapStruct mapping specifications for "ClientInfo"
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface ClientInfoMapping {

    @Mappings ( {
		@Mapping ( source = "codice", target = "codice" ),
    } )
    ClientInfoDTO toDTO ( ClientInfo input );

}
