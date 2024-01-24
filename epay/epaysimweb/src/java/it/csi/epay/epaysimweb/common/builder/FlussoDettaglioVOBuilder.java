/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.common.builder;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysimweb.model.flussi.FlussoDettaglioVO;


/**
 *
 */
public class FlussoDettaglioVOBuilder implements AbstractBuilder<FlussoDettaglioVO> {

    private Long id;

    private String anagraficaPagatore;

    private String causale;

    private Date dataPagamento;

    private String descrizioneCausaleVersamento;

    private String esitoPagamento;

    private String identificativoUnicoRiscossione;

    private String identificativoUnicoVersamento;

    private Double importoSingoloVersamento;

    private Integer indiceSingoloVersamento;

    private String transactionid;

    public FlussoDettaglioVOBuilder () {
    }

    public FlussoDettaglioVOBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public FlussoDettaglioVOBuilder withAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
        return this;
    }

    public FlussoDettaglioVOBuilder withCausale ( String causale ) {
        this.causale = causale;
        return this;
    }

    public FlussoDettaglioVOBuilder withDataPagamento ( Date dataPagamento ) {
        this.dataPagamento = dataPagamento;
        return this;
    }

    public FlussoDettaglioVOBuilder withDataPagamento ( XMLGregorianCalendar dataPagamento ) {
        this.dataPagamento = getDate ( dataPagamento );
        return this;
    }

    public FlussoDettaglioVOBuilder withDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
        return this;
    }

    public FlussoDettaglioVOBuilder withEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
        return this;
    }

    public FlussoDettaglioVOBuilder withIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
        return this;
    }

    public FlussoDettaglioVOBuilder withIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
        return this;
    }

    public FlussoDettaglioVOBuilder withImportoSingoloVersamento ( Double importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
        return this;
    }

    public FlussoDettaglioVOBuilder withImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = ( null != importoSingoloVersamento ? importoSingoloVersamento.doubleValue () : null );
        return this;
    }

    public FlussoDettaglioVOBuilder withIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
        return this;
    }

    public FlussoDettaglioVOBuilder withTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
        return this;
    }

    @Override
    public FlussoDettaglioVO build () {
        return new FlussoDettaglioVO ( id, anagraficaPagatore, causale, dataPagamento, descrizioneCausaleVersamento, esitoPagamento,
            identificativoUnicoRiscossione, identificativoUnicoVersamento, importoSingoloVersamento, indiceSingoloVersamento, transactionid );
    }
}
