/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoFlussoOrigine" )
public class DTOFlussoOrigine implements Serializable {

    private static final long serialVersionUID = -4468246577150483232L;

    private String id;

    private String identificativoFlusso;

    private String identificativoIstitutoRicevente;

    private String identificativoIstitutoRiceventeDescrizione;
    
    private String identificativoPsp;

    private String identificativoPspDescrizione;
    
    private BigDecimal importoTotalePagamenti;

    private Integer numeroTotalePagamenti;

    private String ibanRiversamentoFlusso;

    private Date dataOraFlusso;

    private Date dataInserimento;

    private Integer contatoreTentativi;
    
    private Boolean flagFlussoIntermediato;

    private DTOStatoFlusso statoFlusso;
    
    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoIstitutoRicevente () {
        return identificativoIstitutoRicevente;
    }

    public void setIdentificativoIstitutoRicevente ( String identificativoIstitutoRicevente ) {
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public Date getDataOraFlusso () {
        return dataOraFlusso;
    }

    public void setDataOraFlusso ( Date dataOraFlusso ) {
        this.dataOraFlusso = dataOraFlusso;
    }

    public Date getDataInserimento () {
        return dataInserimento;
    }

    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public Integer getContatoreTentativi () {
        return contatoreTentativi;
    }

    public void setContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }
    
    public DTOStatoFlusso getStatoFlusso () {
        return statoFlusso;
    }
    
    public void setStatoFlusso ( DTOStatoFlusso statoFlusso ) {
        this.statoFlusso = statoFlusso;
    }

    
    public String getIdentificativoIstitutoRiceventeDescrizione () {
        return identificativoIstitutoRiceventeDescrizione;
    }

    
    public void setIdentificativoIstitutoRiceventeDescrizione ( String identificativoIstitutoRiceventeDescrizione ) {
        this.identificativoIstitutoRiceventeDescrizione = identificativoIstitutoRiceventeDescrizione;
    }

    
    public String getIdentificativoPspDescrizione () {
        return identificativoPspDescrizione;
    }

    
    public void setIdentificativoPspDescrizione ( String identificativoPspDescrizione ) {
        this.identificativoPspDescrizione = identificativoPspDescrizione;
    }

	public Boolean getFlagFlussoIntermediato() {
		return flagFlussoIntermediato;
	}

	public void setFlagFlussoIntermediato(Boolean flagFlussoIntermediato) {
		this.flagFlussoIntermediato = flagFlussoIntermediato;
	}

	    
    

}
