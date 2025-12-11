/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTTipoPagamentoTemp;
import it.csi.epay.epayapi.integration.dto.EpayTTipoPagamentoTempDTO;

/**
 * MapStruct mapping specifications for "EpayTTipoPagamentoTemp" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTTipoPagamentoTempReferenceMapping.class,
	EpayTEntiReferenceMapping.class,
	EpayTTipoPagamentoReferenceMapping.class,
} )
public interface EpayTTipoPagamentoTempMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idTipoPagamentoTemp", target = "idTipoPagamentoTemp" ),
		@Mapping ( source = "idOperazione", target = "idOperazione" ),
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
        @Mapping ( source = "flagMultibeneficiario", target = "flagMultibeneficiario" ),
		@Mapping ( source = "numeroAccertamento", target = "numeroAccertamento" ),
		@Mapping ( source = "annoAccertamento", target = "annoAccertamento" ),
		@Mapping ( source = "chiaveIntersistema", target = "chiaveIntersistema" ),
		@Mapping ( source = "tipoOperazione", target = "tipoOperazione" ),
		@Mapping ( source = "epayTEnti", target = "epayTEnti" ),
		@Mapping ( source = "epayTTipoPagamento", target = "epayTTipoPagamento" ),
    	*/
    } )
    EpayTTipoPagamentoTempDTO toDTO ( EpayTTipoPagamentoTemp input );

}
