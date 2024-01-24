/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTRt;
import it.csi.epay.epaybeapi.integration.dto.EpayTRtDTO;

/**
 * MapStruct mapping specifications for "EpayTRt" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTRtReferenceMapping.class,
	EpayTRegistroVersamentiReferenceMapping.class,
} )
public interface EpayTRtMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idRt", target = "idRt" ),
		@Mapping ( source = "ricevutaPdf", target = "ricevutaPdf" ),
		@Mapping ( source = "rtXml", target = "rtXml" ),
		@Mapping ( source = "idApplicazione", target = "idApplicazione" ),
		@Mapping ( source = "idTransazione", target = "idTransazione" ),
		@Mapping ( source = "dataoraMsgRicevuta", target = "dataoraMsgRicevuta" ),
		@Mapping ( source = "idMsgRicevuta", target = "idMsgRicevuta" ),
		@Mapping ( source = "tipoFirma", target = "tipoFirma" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "codEsitoPagamento", target = "codEsitoPagamento" ),
		@Mapping ( source = "descEsitoPagamento", target = "descEsitoPagamento" ),
		@Mapping ( source = "idMsgRichiesta", target = "idMsgRichiesta" ),
		@Mapping ( source = "epayTRegistroVersamenti", target = "epayTRegistroVersamenti" ),
    	*/
    } )
    EpayTRtDTO toDTO ( EpayTRt input );

}
