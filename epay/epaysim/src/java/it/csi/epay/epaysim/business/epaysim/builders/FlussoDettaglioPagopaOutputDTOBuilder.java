/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;


/**
 *
 */
public class FlussoDettaglioPagopaOutputDTOBuilder extends AbstractBuilder<FlussoDettaglioPagopaOutputDTO> {

    private Long id;

    private String anagraficaPagatore;

    private String causale;

    private XMLGregorianCalendar dataPagamento;

    private String descrizioneCausaleVersamento;

    private String esitoPagamento;

    private String identificativoUnicoRiscossione;

    private String identificativoUnicoVersamento;

    private BigDecimal importoSingoloVersamento;

    private Integer indiceSingoloVersamento;

    private String transactionid;

    private FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO;

    public FlussoDettaglioPagopaOutputDTOBuilder () {
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withCausale ( String causale ) {
        this.causale = causale;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withDataPagamento ( XMLGregorianCalendar dataPagamento ) {
        this.dataPagamento = dataPagamento;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withDataPagamento ( Timestamp dataPagamento ) {
        this.dataPagamento = getXMLGregorianCalendar ( dataPagamento );
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
        return this;
    }

    public FlussoDettaglioPagopaOutputDTOBuilder withFlussoSintesiPagopaOutputDTO ( FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO ) {
        this.flussoSintesiPagopaOutputDTO = flussoSintesiPagopaOutputDTO;
        return this;
    }

    @Override
    public FlussoDettaglioPagopaOutputDTO build () {
        return new FlussoDettaglioPagopaOutputDTO ( id, anagraficaPagatore, causale, dataPagamento, descrizioneCausaleVersamento, esitoPagamento,
            identificativoUnicoRiscossione, identificativoUnicoVersamento, importoSingoloVersamento, indiceSingoloVersamento, transactionid,
            flussoSintesiPagopaOutputDTO );
    }

}
