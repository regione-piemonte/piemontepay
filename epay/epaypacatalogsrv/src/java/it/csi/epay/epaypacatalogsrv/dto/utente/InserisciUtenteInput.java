/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.util.Date;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class InserisciUtenteInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private String codiceFiscale;

    private String nome;

    private String cognome;

    private String email;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    @Override
    public String toString () {
        return "InserisciUtenteInput [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
            + ", dataInizioValidita=" + dataInizioValidita + ", dataFineValidita=" + dataFineValidita + "]";
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
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

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

}
