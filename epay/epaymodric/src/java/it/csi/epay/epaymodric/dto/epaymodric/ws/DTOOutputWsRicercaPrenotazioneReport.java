/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFileReport;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOTipoFileReport;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOTipoReport;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsRicercaPrenotazioneReport" )
public class DTOOutputWsRicercaPrenotazioneReport implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscaleEnte;

    private String codiceFiscaleUtente;

    private Long idUtente;
    
    private Date dataOraPrenotazioneReport;

    private DTOStatoReport statoReport;

    private DTOTipoFileReport tipoFileReport;

    private DTOTipoReport tipoReport;

    private DTOFileReport fileReport;

    private String nominativoExport;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getCodiceFiscaleUtente () {
        return codiceFiscaleUtente;
    }

    public void setCodiceFiscaleUtente ( String codiceFiscaleUtente ) {
        this.codiceFiscaleUtente = codiceFiscaleUtente;
    }

    public Long getIdUtente () {
        return idUtente;
    }

    public void setIdUtente ( Long idUtente ) {
        this.idUtente = idUtente;
    }

    public DTOStatoReport getStatoReport () {
        return statoReport;
    }

    public void setStatoReport ( DTOStatoReport statoReport ) {
        this.statoReport = statoReport;
    }

    public DTOTipoFileReport getTipoFileReport () {
        return tipoFileReport;
    }

    public void setTipoFileReport ( DTOTipoFileReport tipoFileReport ) {
        this.tipoFileReport = tipoFileReport;
    }

    public DTOTipoReport getTipoReport () {
        return tipoReport;
    }

    public void setTipoReport ( DTOTipoReport tipoReport ) {
        this.tipoReport = tipoReport;
    }

    public DTOFileReport getFileReport () {
        return fileReport;
    }

    public void setFileReport ( DTOFileReport fileReport ) {
        this.fileReport = fileReport;
    }
    

    public Date getDataOraPrenotazioneReport() {
		return dataOraPrenotazioneReport;
	}

	public void setDataOraPrenotazioneReport(Date dataOraPrenotazioneReport) {
		this.dataOraPrenotazioneReport = dataOraPrenotazioneReport;
	}

	/**
     * @return the nominativoExport
     */
    public String getNominativoExport () {
        return nominativoExport;
    }

    /**
     * @param nominativoExport the nominativoExport to set
     */
    public void setNominativoExport ( String nominativoExport ) {
        this.nominativoExport = nominativoExport;
    }

}
