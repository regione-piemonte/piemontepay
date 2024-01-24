/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * NG
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "flussoErrorePagopaDTO" )
public class FlussoErrorePagopaDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String identificativoFlusso;

    private String identificativoPsp;

    private Integer numeroTotalePagamenti;

    private BigDecimal importoTotalePagamenti;

    private String ibanRiversamentoFlusso;

    @XmlSchemaType(name="dateTime")
    private XMLGregorianCalendar dataoraFlusso;

    @XmlSchemaType(name="dateTime")
    private XMLGregorianCalendar dataInserimento;

    private String xmlFlusso;

    private Integer contatoreTentativi;

    private Integer idElaborazione;

    private Integer idStatoFlusso;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamentiIntermediati;

    @XmlSchemaType(name="dateTime")
    private XMLGregorianCalendar dataRegolamento;

    private String codiceVersamento;

    private String codiceErrore;

    private String descrizioneErrore;

    private String idMessaggio;

    private String identificativoUnivocoRegolamento;

    private String utenteInserimento;

    private String utenteModifica;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataModifica;

    private String cfEnteRicevente;

    public FlussoErrorePagopaDTO () {

    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public XMLGregorianCalendar getDataoraFlusso () {
        return dataoraFlusso;
    }

    public void setDataoraFlusso ( XMLGregorianCalendar dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
    }

    public XMLGregorianCalendar getDataInserimento () {
        return dataInserimento;
    }

    public void setDataInserimento ( XMLGregorianCalendar dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public String getXmlFlusso () {
        return xmlFlusso;
    }

    public void setXmlFlusso ( String xmlFlusso ) {
        this.xmlFlusso = xmlFlusso;
    }

    public Integer getContatoreTentativi () {
        return contatoreTentativi;
    }

    public void setContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }

    public Integer getIdElaborazione () {
        return idElaborazione;
    }

    public void setIdElaborazione ( Integer idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public Integer getIdStatoFlusso () {
        return idStatoFlusso;
    }

    public void setIdStatoFlusso ( Integer idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    public XMLGregorianCalendar getDataRegolamento () {
        return dataRegolamento;
    }

    public void setDataRegolamento ( XMLGregorianCalendar dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getCodiceErrore () {
        return codiceErrore;
    }

    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }

    public String getDescrizioneErrore () {
        return descrizioneErrore;
    }

    public void setDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }

    public String getIdMessaggio () {
        return idMessaggio;
    }

    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    public String getUtenteModifica () {
        return utenteModifica;
    }

    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

    public XMLGregorianCalendar getDataModifica () {
        return dataModifica;
    }

    public void setDataModifica ( XMLGregorianCalendar dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public String getCfEnteRicevente () {
        return cfEnteRicevente;
    }

    public void setCfEnteRicevente ( String cfEnteRicevente ) {
        this.cfEnteRicevente = cfEnteRicevente;
    }

}
