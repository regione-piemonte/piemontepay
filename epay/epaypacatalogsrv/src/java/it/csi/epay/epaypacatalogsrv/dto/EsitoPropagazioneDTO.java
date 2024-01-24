/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;


public class EsitoPropagazioneDTO implements Serializable {

    private static final long serialVersionUID = 6632443777179392268L;

    private EsitoPropagazione esito;

    private List<LogTransazioneDTO> esitiSottoscrittori;

    private String messaggio;

    private Long idTransazione;

    public EsitoPropagazioneDTO () {
    }

    public EsitoPropagazioneDTO ( EsitoPropagazione esito, List<LogTransazioneDTO> esitiSottoscrittori ) {
        super ();
        this.esito = esito;
        this.esitiSottoscrittori = esitiSottoscrittori;
    }

    public EsitoPropagazioneDTO ( EsitoPropagazione esito, String messaggio ) {
        super ();
        this.esito = esito;
        this.messaggio = messaggio;
    }

    /**
     * @return the idTransazione
     */
    public final Long getIdTransazione () {
        return idTransazione;
    }

    /**
     * @param idTransazione the idTransazione to set
     */
    public final void setIdTransazione ( Long idTransazione ) {
        this.idTransazione = idTransazione;
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
     * @return the esitiSottoscrittori
     */
    public final List<LogTransazioneDTO> getEsitiSottoscrittori () {
        return esitiSottoscrittori;
    }

    /**
     * @param esitiSottoscrittori the esitiSottoscrittori to set
     */
    public final void setEsitiSottoscrittori ( List<LogTransazioneDTO> esitiSottoscrittori ) {
        this.esitiSottoscrittori = esitiSottoscrittori;
    }

}
