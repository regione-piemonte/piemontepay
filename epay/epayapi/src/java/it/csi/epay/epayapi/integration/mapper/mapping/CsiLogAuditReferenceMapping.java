/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.CsiLogAudit;
import it.csi.epay.epayapi.integration.dto.CsiLogAuditReferenceDTO;

/**
 * MapStruct mapping specifications for "CsiLogAudit" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface CsiLogAuditReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idLog", target = "idLog" ),
		@Mapping ( source = "dataOra", target = "dataOra" ),
		@Mapping ( source = "idApp", target = "idApp" ),
		@Mapping ( source = "ipAddress", target = "ipAddress" ),
		@Mapping ( source = "utente", target = "utente" ),
		@Mapping ( source = "operazione", target = "operazione" ),
		@Mapping ( source = "oggOper", target = "oggOper" ),
		@Mapping ( source = "keyOper", target = "keyOper" ),
    } )
    CsiLogAuditReferenceDTO toDTO ( CsiLogAudit input );

}
