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
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType ( XmlAccessType.FIELD )
public class FlussoOriginePagopaDTO extends ParentInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataInserimento;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataRegolamento;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataoraFlusso;

    private String ibanRiversamentoFlusso;

    private Long idElaborazione;

    private String idMessaggio;

    private String identificativoFlusso;

    private String identificativoPsp;

    private String identificativoUnivocoRegolamento;

    private BigDecimal importoTotalePagamenti;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamenti;

    private Integer numeroTotalePagamentiIntermediati;

    private String codiceFiscaleEnte;

    private Boolean statoElaborazioneFlusso;



    public FlussoOriginePagopaDTO () {
    }

    public FlussoOriginePagopaDTO ( Long id, XMLGregorianCalendar dataInserimento, XMLGregorianCalendar dataRegolamento,
        XMLGregorianCalendar dataoraFlusso, String ibanRiversamentoFlusso, Long idElaborazione, String idMessaggio, String identificativoFlusso,
        String identificativoPsp, String identificativoUnivocoRegolamento, BigDecimal importoTotalePagamenti, BigDecimal importoTotalePagamentiIntermediati,
        Integer numeroTotalePagamenti, Integer numeroTotalePagamentiIntermediati, String codiceFiscaleEnte,
        Boolean statoElaborazioneFlusso ) {

        super ();
        this.id = id;
        this.dataInserimento = dataInserimento;
        this.dataRegolamento = dataRegolamento;
        this.dataoraFlusso = dataoraFlusso;
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
        this.idElaborazione = idElaborazione;
        this.idMessaggio = idMessaggio;
        this.identificativoFlusso = identificativoFlusso;
        this.identificativoPsp = identificativoPsp;
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        this.importoTotalePagamenti = importoTotalePagamenti;
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        this.codiceFiscaleEnte = codiceFiscaleEnte;
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;

    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public XMLGregorianCalendar getDataInserimento () {
        return this.dataInserimento;
    }

    public void setDataInserimento ( XMLGregorianCalendar dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public XMLGregorianCalendar getDataRegolamento () {
        return this.dataRegolamento;
    }

    public void setDataRegolamento ( XMLGregorianCalendar dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public XMLGregorianCalendar getDataoraFlusso () {
        return this.dataoraFlusso;
    }

    public void setDataoraFlusso ( XMLGregorianCalendar dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
    }

    public String getIbanRiversamentoFlusso () {
        return this.ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public Long getIdElaborazione () {
        return this.idElaborazione;
    }

    public void setIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public String getIdMessaggio () {
        return this.idMessaggio;
    }

    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

    public String getIdentificativoFlusso () {
        return this.identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoPsp () {
        return this.identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public String getIdentificativoUnivocoRegolamento () {
        return this.identificativoUnivocoRegolamento;
    }

    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return this.importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return this.importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Integer getNumeroTotalePagamenti () {
        return this.numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return this.numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    public Boolean getStatoElaborazioneFlusso () {
        return statoElaborazioneFlusso;
    }

    public void setStatoElaborazioneFlusso ( Boolean statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
    }

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }



}
