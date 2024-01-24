/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.mdp.mdppagopaapi.dto.security.ClientInfo;
import it.csi.mdp.mdppagopaapi.dto.security.ClientInfoDTO;


/**
 * MapStruct mapping specifications for "ClientInfo"
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface ClientInfoMapping {

    @Mappings ( {
		@Mapping ( source = "codice", target = "codice" ),
    } )
    ClientInfoDTO toDTO ( ClientInfo input );

}
