/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;

import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;


public class LogTransazioneDTO implements Serializable {

    private static final long serialVersionUID = 6632443777179392268L;

    private Long idSottoscrittore;

    private String codice;

    private EsitoPropagazione esito;

    private String messaggio;

    public LogTransazioneDTO () {
    }

    public LogTransazioneDTO ( Long idSottoscrittore, String codice, EsitoPropagazione esito, String messaggio ) {
        super ();
        this.idSottoscrittore = idSottoscrittore;
        this.esito = esito;
        this.codice = codice;
        this.messaggio = messaggio;
    }

    /**
     * @return the idSottoscrittore
     */
    public final Long getIdSottoscrittore () {
        return idSottoscrittore;
    }

    /**
     * @param idSottoscrittore the idSottoscrittore to set
     */
    public final void setIdSottoscrittore ( Long idSottoscrittore ) {
        this.idSottoscrittore = idSottoscrittore;
    }

    /**
     * @return the esito
     */
    public final EsitoPropagazione getEsito () {
        return esito;
    }

    /**
     * @param esito the esito to set
     */
    public final void setEsito ( EsitoPropagazione esito ) {
        this.esito = esito;
    }

    /**
     * @return the messaggio
     */
    public final String getMessaggio () {
        return messaggio;
    }

    /**
     * @param messaggio the messaggio to set
     */
    public final void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

    /**
     * @return the codice
     */
    public final String getCodice () {
        return codice;
    }

    /**
     * @param codice the codice to set
     */
    public final void setCodice ( String codice ) {
        this.codice = codice;
    }

}
