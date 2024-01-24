/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "dtoProvvisorio" )
public class DTOProvvisorio implements Serializable {

    private static final long serialVersionUID = -8198510841103407404L;

    private Long id;

    private Integer annoEsercizio;

    private Integer annoProvvisorio;

    private String causale;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataFine;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataMovimento;

    private String descrizione;

    private String idEnte;

    private String identificativoFlusso;

    private BigDecimal importoDisponibilita;

    private BigDecimal importoProvvisorio;

    private Integer numeroProvvisorio;

    private String stato;

    private String tipoMovimento;

    public DTOProvvisorio () {
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    public Integer getAnnoProvvisorio () {
        return annoProvvisorio;
    }

    public void setAnnoProvvisorio ( Integer annoProvvisorio ) {
        this.annoProvvisorio = annoProvvisorio;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public XMLGregorianCalendar getDataFine () {
        return dataFine;
    }

    public void setDataFine ( XMLGregorianCalendar dataFine ) {
        this.dataFine = dataFine;
    }

    public XMLGregorianCalendar getDataMovimento () {
        return dataMovimento;
    }

    public void setDataMovimento ( XMLGregorianCalendar dataMovimento ) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public BigDecimal getImportoDisponibilita () {
        return importoDisponibilita;
    }

    public void setImportoDisponibilita ( BigDecimal importoDisponibilita ) {
        this.importoDisponibilita = importoDisponibilita;
    }

    public BigDecimal getImportoProvvisorio () {
        return importoProvvisorio;
    }

    public void setImportoProvvisorio ( BigDecimal importoProvvisorio ) {
        this.importoProvvisorio = importoProvvisorio;
    }

    public Integer getNumeroProvvisorio () {
        return numeroProvvisorio;
    }

    public void setNumeroProvvisorio ( Integer numeroProvvisorio ) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    public String getStato () {
        return stato;
    }

    public void setStato ( String stato ) {
        this.stato = stato;
    }

    public String getTipoMovimento () {
        return tipoMovimento;
    }

    public void setTipoMovimento ( String tipoMovimento ) {
        this.tipoMovimento = tipoMovimento;
    }

}
