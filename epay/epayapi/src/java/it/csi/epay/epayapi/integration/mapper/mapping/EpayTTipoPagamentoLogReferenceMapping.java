/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTTipoPagamentoLog;
import it.csi.epay.epayapi.integration.dto.EpayTTipoPagamentoLogReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTTipoPagamentoLog" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTTipoPagamentoLogReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "seq", target = "seq" ),
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
        @Mapping ( source = "flagMultibeneficiario", target = "flagMultibeneficiario" ),
		@Mapping ( source = "flagInvioPagamenti", target = "flagInvioPagamenti" ),
		@Mapping ( source = "dataTrigger", target = "dataTrigger" ),
    } )
    EpayTTipoPagamentoLogReferenceDTO toDTO ( EpayTTipoPagamentoLog input );

}
