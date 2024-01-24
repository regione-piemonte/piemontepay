/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTErrori;
import it.csi.epay.epaybeapi.integration.dto.EpayTErroriDTO;

/**
 * MapStruct mapping specifications for "EpayTErrori" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTErroriReferenceMapping.class,
	EpayDStatoErroreReferenceMapping.class,
} )
public interface EpayTErroriMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "id", target = "id" ),
		@Mapping ( source = "data", target = "data" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
		@Mapping ( source = "idPagamento", target = "idPagamento" ),
		@Mapping ( source = "idRegistroVersamento", target = "idRegistroVersamento" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "idTransazione", target = "idTransazione" ),
		@Mapping ( source = "descCorrezione", target = "descCorrezione" ),
		@Mapping ( source = "applicativo", target = "applicativo" ),
		@Mapping ( source = "epayDStatoErrore", target = "epayDStatoErrore" ),
    	*/
    } )
    EpayTErroriDTO toDTO ( EpayTErrori input );

}
