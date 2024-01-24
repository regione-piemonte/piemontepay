/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class UtenteVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String nome;

    private String cognome;

    private String email;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    private List<AssociazioneUtenteCduVO> cdu;

    private List<AssociazioneUtenteTematicaVO> tematiche;

    public List<AssociazioneUtenteCduVO> getCdu () {
        return cdu;
    }

    public void setCdu ( List<AssociazioneUtenteCduVO> cdu ) {
        this.cdu = cdu;
    }

    public List<AssociazioneUtenteTematicaVO> getTematiche () {
        return tematiche;
    }

    public void setTematiche ( List<AssociazioneUtenteTematicaVO> tematiche ) {
        this.tematiche = tematiche;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
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

    @Override
    public String toString () {
        return "UtenteVO [id=" + id + ", codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
            + ", dataInizioValidita=" + dataInizioValidita + ", dataFineValidita=" + dataFineValidita + ", cdu=" + cdu + ", tematiche=" + tematiche + "]";
    }

}
