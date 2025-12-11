/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTPagamento;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoDTO;

/**
 * MapStruct mapping specifications for "EpayTPagamento"
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {
    EpayTPagamentoReferenceMapping.class,
    EpayTRegistroVersamentiReferenceMapping.class,
    EpayTDatiSingoliVersamentiReferenceMapping.class,
    EpayDStatoPagamentoReferenceMapping.class,
    EpayTPagamentoRiferimentiReferenceMapping.class,
    EpayTRegistroElaborazioniReferenceMapping.class,
    EpayTTipoPagamentoReferenceMapping.class,
    EpayTAnagraficaReferenceMapping.class,
    EpayTPagamentoComponentiReferenceMapping.class,
} )
public interface EpayTPagamentoMapping {

    @Mappings ( {
    	/*
         * @Mapping ( source = "idPagamento", target = "idPagamento" ),
         * @Mapping ( source = "dataInserimento", target = "dataInserimento" ),
         * @Mapping ( source = "causale", target = "causale" ),
         * @Mapping ( source = "importo", target = "importo" ),
         * @Mapping ( source = "note", target = "note" ),
         * @Mapping ( source = "consensoPrivacy", target = "consensoPrivacy" ),
         * @Mapping ( source = "inizioValidita", target = "inizioValidita" ),
         * @Mapping ( source = "fineValidita", target = "fineValidita" ),
         * @Mapping ( source = "iuvNumeroAvviso", target = "iuvNumeroAvviso" ),
         * @Mapping ( source = "auxDigit", target = "auxDigit" ),
         * @Mapping ( source = "applicationCode", target = "applicationCode" ),
         * @Mapping ( source = "codicePagamentoRifEnte", target = "codicePagamentoRifEnte" ),
         * @Mapping ( source = "annoRiferimento", target = "annoRiferimento" ),
         * @Mapping ( source = "dataScadenza", target = "dataScadenza" ),
         * @Mapping ( source = "numeroRata", target = "numeroRata" ),
         * @Mapping ( source = "pagamentoSpontaneo", target = "pagamentoSpontaneo" ),
         * @Mapping ( source = "flagInviato", target = "flagInviato" ),
         * @Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
         * @Mapping ( source = "flagPagamentoAutenticato", target = "flagPagamentoAutenticato" ),
         * @Mapping ( source = "importoPrincipale", target = "importoPrincipale" ),
         * @Mapping ( source = "identificativoDominio", target = "identificativoDominio" ),
         * @Mapping ( source = "listOfEpayTRegistroVersamenti", target = "listOfEpayTRegistroVersamenti" ),
         * @Mapping ( source = "listOfEpayTDatiSingoliVersamenti", target = "listOfEpayTDatiSingoliVersamenti" ),
         * @Mapping ( source = "epayDStatoPagamento", target = "epayDStatoPagamento" ),
         * @Mapping ( source = "listOfEpayTPagamentoRiferimenti", target = "listOfEpayTPagamentoRiferimenti" ),
         * @Mapping ( source = "listOfEpayTRegistroElaborazioni", target = "listOfEpayTRegistroElaborazioni" ),
         * @Mapping ( source = "epayTTipoPagamento", target = "epayTTipoPagamento" ),
         * @Mapping ( source = "epayTAnagrafica", target = "epayTAnagrafica" ),
         * @Mapping ( source = "listOfEpayTPagamentoComponenti", target = "listOfEpayTPagamentoComponenti" ),
         */
    } )
    EpayTPagamentoDTO toDTO ( EpayTPagamento input );

}
