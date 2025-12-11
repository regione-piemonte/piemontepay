/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTPagamentoStorico;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoStoricoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTPagamentoStorico"
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTPagamentoStoricoReferenceMapping {

    @Mappings ( {
        @Mapping ( source = "id", target = "id" ),
        @Mapping ( source = "dataStoricizzazione", target = "dataStoricizzazione" ),
        @Mapping ( source = "idPagamento", target = "idPagamento" ),
        @Mapping ( source = "idTipoPagamento", target = "idTipoPagamento" ),
        @Mapping ( source = "dataInserimento", target = "dataInserimento" ),
        @Mapping ( source = "causale", target = "causale" ),
        @Mapping ( source = "importo", target = "importo" ),
        @Mapping ( source = "note", target = "note" ),
        @Mapping ( source = "consensoPrivacy", target = "consensoPrivacy" ),
        @Mapping ( source = "inizioValidita", target = "inizioValidita" ),
        @Mapping ( source = "fineValidita", target = "fineValidita" ),
        @Mapping ( source = "iuvNumeroAvviso", target = "iuvNumeroAvviso" ),
        @Mapping ( source = "auxDigit", target = "auxDigit" ),
        @Mapping ( source = "applicationCode", target = "applicationCode" ),
        @Mapping ( source = "codicePagamentoRifEnte", target = "codicePagamentoRifEnte" ),
        @Mapping ( source = "annoRiferimento", target = "annoRiferimento" ),
        @Mapping ( source = "dataScadenza", target = "dataScadenza" ),
        @Mapping ( source = "idStatoCorrente", target = "idStatoCorrente" ),
        @Mapping ( source = "numeroRata", target = "numeroRata" ),
        @Mapping ( source = "idAnagraficaPagatore", target = "idAnagraficaPagatore" ),
        @Mapping ( source = "pagamentoSpontaneo", target = "pagamentoSpontaneo" ),
        @Mapping ( source = "flagInviato", target = "flagInviato" ),
        @Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
        @Mapping ( source = "importoPrincipale", target = "importoPrincipale" ),
        @Mapping ( source = "identificativoDominio", target = "identificativoDominio" ),
    } )
    EpayTPagamentoStoricoReferenceDTO toDTO ( EpayTPagamentoStorico input );

}
