/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.CsiLogAudit;
import it.csi.epay.epaybeapi.integration.dto.CsiLogAuditDTO;

/**
 * MapStruct mapping specifications for "CsiLogAudit" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	CsiLogAuditReferenceMapping.class,
} )
public interface CsiLogAuditMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idLog", target = "idLog" ),
		@Mapping ( source = "dataOra", target = "dataOra" ),
		@Mapping ( source = "idApp", target = "idApp" ),
		@Mapping ( source = "ipAddress", target = "ipAddress" ),
		@Mapping ( source = "utente", target = "utente" ),
		@Mapping ( source = "operazione", target = "operazione" ),
		@Mapping ( source = "oggOper", target = "oggOper" ),
		@Mapping ( source = "keyOper", target = "keyOper" ),
    	*/
    } )
    CsiLogAuditDTO toDTO ( CsiLogAudit input );

}
