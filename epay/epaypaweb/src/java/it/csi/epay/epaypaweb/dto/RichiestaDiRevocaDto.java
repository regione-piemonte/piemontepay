/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;


/** dto facade <-> business <-> persistence */
public class RichiestaDiRevocaDto extends NotificaPagamentoLightDto implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String identificativoDominio;

    protected String applicationId;

    protected String identificativoMessaggioRevoca;

    protected XMLGregorianCalendar dataOraMessaggioRevoca;

    protected SoggettoAnagraficoDto istitutoAttestante;

//    protected BigDecimal importoPagato;

 //   protected String iuv;

    protected String codiceContestoPagamento;

    protected int tipoRevoca;

    protected byte [] xml;

    protected List<DatiSingolaRevocaDto> listaDatiSingolaRevoca;

    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }

    public String getApplicationId () {
        return applicationId;
    }

    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    public String getIdentificativoMessaggioRevoca () {
        return identificativoMessaggioRevoca;
    }

    public void setIdentificativoMessaggioRevoca ( String identificativoMessaggioRevoca ) {
        this.identificativoMessaggioRevoca = identificativoMessaggioRevoca;
    }

    public XMLGregorianCalendar getDataOraMessaggioRevoca () {
        return dataOraMessaggioRevoca;
    }

    public void setDataOraMessaggioRevoca ( XMLGregorianCalendar dataOraMessaggioRevoca ) {
        this.dataOraMessaggioRevoca = dataOraMessaggioRevoca;
    }

  
    public String getCodiceContestoPagamento () {
        return codiceContestoPagamento;
    }

    public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
        this.codiceContestoPagamento = codiceContestoPagamento;
    }

    public int getTipoRevoca () {
        return tipoRevoca;
    }

    public void setTipoRevoca ( int tipoRevoca ) {
        this.tipoRevoca = tipoRevoca;
    }

    public byte [] getXml () {
        return xml;
    }

    public void setXml ( byte [] xml ) {
        this.xml = xml;
    }

    public SoggettoAnagraficoDto getIstitutoAttestante () {
        return istitutoAttestante;
    }

    public void setIstitutoAttestante ( SoggettoAnagraficoDto istitutoAttestante ) {
        this.istitutoAttestante = istitutoAttestante;
    }

    public List<DatiSingolaRevocaDto> getListaDatiSingolaRevoca () {
        return listaDatiSingolaRevoca;
    }

    public void setListaDatiSingolaRevoca ( List<DatiSingolaRevocaDto> listaDatiSingolaRevoca ) {
        this.listaDatiSingolaRevoca = listaDatiSingolaRevoca;
    }

    @Override
    public String toString () {
        return "RichiestaDiRevocaDto [identificativoDominio=" + identificativoDominio + ", applicationId=" + applicationId + ", identificativoMessaggioRevoca="
            + identificativoMessaggioRevoca + ", dataOraMessaggioRevoca=" + dataOraMessaggioRevoca + ", codiceContestoPagamento=" + codiceContestoPagamento + ", tipoRevoca=" + tipoRevoca + ", xml=" + Arrays.toString ( xml ) + "]";
    }

}
