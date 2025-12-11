/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTTipoPagamento;
import it.csi.epay.epayapi.integration.dto.EpayTTipoPagamentoDTO;

/**
 * MapStruct mapping specifications for "EpayTTipoPagamento" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTTipoPagamentoReferenceMapping.class,
    EpayTTipoPagamentoTempReferenceMapping.class,
    EpayTPagamentoReferenceMapping.class,
    EpayTDatiAvvisoPagamentoReferenceMapping.class,
	EpayTEntiReferenceMapping.class,
} )
public interface EpayTTipoPagamentoMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idTipoPagamento", target = "idTipoPagamento" ),
		@Mapping ( source = "codiceVersamento", target = "codiceVersamento" ),
		@Mapping ( source = "descrizionePortale", target = "descrizionePortale" ),
		@Mapping ( source = "compilazioneNote", target = "compilazioneNote" ),
		@Mapping ( source = "inizioValidita", target = "inizioValidita" ),
		@Mapping ( source = "fineValidita", target = "fineValidita" ),
		@Mapping ( source = "idApplicazione", target = "idApplicazione" ),
		@Mapping ( source = "contatoreSelezioni", target = "contatoreSelezioni" ),
		@Mapping ( source = "contatoreCompilazioni", target = "contatoreCompilazioni" ),
		@Mapping ( source = "contatorePagamenti", target = "contatorePagamenti" ),
		@Mapping ( source = "pagamentoSpontaneo", target = "pagamentoSpontaneo" ),
		@Mapping ( source = "datiSpecificiRiscossione", target = "datiSpecificiRiscossione" ),
		@Mapping ( source = "flagInvioPagamenti", target = "flagInvioPagamenti" ),
		@Mapping ( source = "numeroAccertamento", target = "numeroAccertamento" ),
		@Mapping ( source = "annoAccertamento", target = "annoAccertamento" ),
        @Mapping ( source = "flagMultibeneficiario", target = "flagMultibeneficiario" ),
		@Mapping ( source = "chiaveIntersistema", target = "chiaveIntersistema" ),
		@Mapping ( source = "listOfEpayTTipoPagamentoTemp", target = "listOfEpayTTipoPagamentoTemp" ),
		@Mapping ( source = "listOfEpayTPagamento", target = "listOfEpayTPagamento" ),
		@Mapping ( source = "listOfEpayTDatiAvvisoPagamento", target = "listOfEpayTDatiAvvisoPagamento" ),
		@Mapping ( source = "epayTEnti", target = "epayTEnti" ),
    	*/
    } )
    EpayTTipoPagamentoDTO toDTO ( EpayTTipoPagamento input );

}
