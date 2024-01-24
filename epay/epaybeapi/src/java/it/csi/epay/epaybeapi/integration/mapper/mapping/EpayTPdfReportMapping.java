/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTPdfReport;
import it.csi.epay.epaybeapi.integration.dto.EpayTPdfReportDTO;

/**
 * MapStruct mapping specifications for "EpayTPdfReport" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTPdfReportReferenceMapping.class,
} )
public interface EpayTPdfReportMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "id", target = "id" ),
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "nomeTemplate", target = "nomeTemplate" ),
		@Mapping ( source = "template", target = "template" ),
		@Mapping ( source = "templateCompilato", target = "templateCompilato" ),
    	*/
    } )
    EpayTPdfReportDTO toDTO ( EpayTPdfReport input );

}
