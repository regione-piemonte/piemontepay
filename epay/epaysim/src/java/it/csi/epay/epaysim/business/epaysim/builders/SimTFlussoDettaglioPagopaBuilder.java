/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoDettaglioPagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;


/**
 * builder per la classe {@link SimTFlussoDettaglioPagopa}
 */
public class SimTFlussoDettaglioPagopaBuilder extends AbstractBuilder<SimTFlussoDettaglioPagopa> {

    private Long id;

    private String anagraficaPagatore;

    private String causale;

    private Timestamp dataPagamento;

    private String descrizioneCausaleVersamento;

    private String esitoPagamento;

    private String identificativoUnicoRiscossione;

    private String identificativoUnicoVersamento;

    private BigDecimal importoSingoloVersamento;

    private Integer indiceSingoloVersamento;

    private String transactionid;

    private SimTFlussoSintesiPagopa simTFlussoSintesiPagopa;

    public SimTFlussoDettaglioPagopaBuilder () {
    }

    public SimTFlussoDettaglioPagopaBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withCausale ( String causale ) {
        this.causale = causale;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withDataPagamento ( Timestamp dataPagamento ) {
        this.dataPagamento = dataPagamento;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withDataPagamento ( Date dataPagamento ) {
        this.dataPagamento = getTimestamp ( dataPagamento );
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
        return this;
    }

    public SimTFlussoDettaglioPagopaBuilder withSimTFlussoSintesiPagopa ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa ) {
        this.simTFlussoSintesiPagopa = simTFlussoSintesiPagopa;
        return this;
    }

    @Override
    public SimTFlussoDettaglioPagopa build () {
        return new SimTFlussoDettaglioPagopa ( id, anagraficaPagatore, causale, dataPagamento, descrizioneCausaleVersamento, esitoPagamento,
            identificativoUnicoRiscossione, identificativoUnicoVersamento, importoSingoloVersamento, indiceSingoloVersamento, transactionid,
            simTFlussoSintesiPagopa, null == id ? dataInserimento : null, dataModifica, null == id ? utenteInserimento : null, utenteModifica );
    }
}
