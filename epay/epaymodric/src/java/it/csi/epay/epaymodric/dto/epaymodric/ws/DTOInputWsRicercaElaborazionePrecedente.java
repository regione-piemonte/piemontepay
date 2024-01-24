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

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsRicercaElaborazionePrecedente" )
public class DTOInputWsRicercaElaborazionePrecedente extends DTOInputBase implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String validitaGenerica;

    private String idEnte;

    private String codiceFiscaleEnte;

    private String utenteElaborazione;

    private String statoElaborazione;

    private Date dataInizio;

    private Date dataFine;

    public DTOInputWsRicercaElaborazionePrecedente() {

    }

    public String getValiditaGenerica() {
        return validitaGenerica;
    }

    public void setValiditaGenerica(String validitaGenerica) {
        this.validitaGenerica = validitaGenerica;
    }

    public String getIdEnte() {
        return idEnte;
    }

    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }

    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getUtenteElaborazione() {
        return utenteElaborazione;
    }

    public void setUtenteElaborazione(String utenteElaborazione) {
        this.utenteElaborazione = utenteElaborazione;
    }

    public String getStatoElaborazione() {
        return statoElaborazione;
    }

    public void setStatoElaborazione(String statoElaborazione) {
        this.statoElaborazione = statoElaborazione;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }


}
