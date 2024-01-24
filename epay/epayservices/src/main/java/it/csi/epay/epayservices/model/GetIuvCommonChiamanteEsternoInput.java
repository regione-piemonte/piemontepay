/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;


/**
 *
 */

public class GetIuvCommonChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String codiceFiscaleEnte;

    protected String causale;

    protected String nome;

    protected String cognome;

    protected String ragioneSociale;

    protected String email;

    protected String codiceFiscalePartitaIVAPagatore;

    protected Date dataScadenza;

    protected Date dataInizioValidita;

    protected Date dataFineValidita;

    protected String notePerIlPagatore;

    protected Boolean requiresCostUpdate;

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public Boolean getRequiresCostUpdate () {
        return requiresCostUpdate;
    }

    public void setRequiresCostUpdate ( Boolean requiresCostUpdate ) {
        this.requiresCostUpdate = requiresCostUpdate;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    public String getRagioneSociale () {
        return ragioneSociale;
    }

    public void setRagioneSociale ( String ragioneSociale ) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getCodiceFiscalePartitaIVAPagatore () {
        return codiceFiscalePartitaIVAPagatore;
    }

    public void setCodiceFiscalePartitaIVAPagatore ( String codiceFiscalePartitaIVAPagatore ) {
        this.codiceFiscalePartitaIVAPagatore = codiceFiscalePartitaIVAPagatore;
    }

    /**
     * @return the dataScadenza
     */
    public Date getDataScadenza () {
        return dataScadenza;
    }

    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    /**
     * @return the notePerIlPagatore
     */
    public String getNotePerIlPagatore () {
        return notePerIlPagatore;
    }

    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    /**
     * @param notePerIlPagatore the notePerIlPagatore to set
     */
    public void setNotePerIlPagatore ( String notePerIlPagatore ) {
        this.notePerIlPagatore = notePerIlPagatore;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "GetIuvCommonChiamanteEsternoInput [codiceFiscaleEnte=" );
        builder.append ( codiceFiscaleEnte );
        builder.append ( ", causale=" );
        builder.append ( causale );
        builder.append ( ", nome=" );
        builder.append ( nome );
        builder.append ( ", cognome=" );
        builder.append ( cognome );
        builder.append ( ", ragioneSociale=" );
        builder.append ( ragioneSociale );
        builder.append ( ", email=" );
        builder.append ( email );
        builder.append ( ", codiceFiscalePartitaIVAPagatore=" );
        builder.append ( codiceFiscalePartitaIVAPagatore );
        builder.append ( ", dataScadenza=" );
        builder.append ( dataScadenza );
        builder.append ( ", dataInizioValidita=" );
        builder.append ( dataInizioValidita );
        builder.append ( ", dataFineValidita=" );
        builder.append ( dataFineValidita );
        builder.append ( ", notePerIlPagatore=" );
        builder.append ( notePerIlPagatore );
        builder.append ( ", toString()=" );
        builder.append ( super.toString () );
        builder.append ( "]" );
        return builder.toString ();
    }

}
