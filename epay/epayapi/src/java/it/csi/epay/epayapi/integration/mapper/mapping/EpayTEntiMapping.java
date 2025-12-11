/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTEnti;
import it.csi.epay.epayapi.integration.dto.EpayTEntiDTO;

/**
 * MapStruct mapping specifications for "EpayTEnti" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTEntiReferenceMapping.class,
    EpayTEntiTempReferenceMapping.class,
    EpayTTipoPagamentoLogReferenceMapping.class,
    EpayTEntiLogReferenceMapping.class,
    EpayTConfigurazioneReferenceMapping.class,
    EpayTTipoPagamentoReferenceMapping.class,
    EpayTTipoPagamentoTempReferenceMapping.class,
} )
public interface EpayTEntiMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idEnte", target = "idEnte" ),
		@Mapping ( source = "nome", target = "nome" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "logo", target = "logo" ),
		@Mapping ( source = "codiceGs1Gln", target = "codiceGs1Gln" ),
		@Mapping ( source = "orari", target = "orari" ),
		@Mapping ( source = "flagInvioPagamenti", target = "flagInvioPagamenti" ),
		@Mapping ( source = "codiceInterbancario", target = "codiceInterbancario" ),
		@Mapping ( source = "listOfEpayTEntiTemp", target = "listOfEpayTEntiTemp" ),
		@Mapping ( source = "listOfEpayTTipoPagamentoLog", target = "listOfEpayTTipoPagamentoLog" ),
		@Mapping ( source = "listOfEpayTEntiLog", target = "listOfEpayTEntiLog" ),
		@Mapping ( source = "listOfEpayTConfigurazione", target = "listOfEpayTConfigurazione" ),
		@Mapping ( source = "listOfEpayTTipoPagamento", target = "listOfEpayTTipoPagamento" ),
		@Mapping ( source = "listOfEpayTTipoPagamentoTemp", target = "listOfEpayTTipoPagamentoTemp" ),
    	*/
    } )
    EpayTEntiDTO toDTO ( EpayTEnti input );

}
