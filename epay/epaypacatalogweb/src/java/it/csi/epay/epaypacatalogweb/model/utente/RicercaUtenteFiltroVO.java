/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;


public class RicercaUtenteFiltroVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String nome;

    private String cognome;

    private String email;

    private Boolean soloUtentiInVita;

    private String codiceCategoriaCdu;

    private String codiceCdu;

    private String descrizioneCategoriaCdu;

    private String descrizioneCdu;

    private String codiceTematica;

    private Long idCodiceVersamento;

    private String descrizioneCompletaCodiceVersamentoSelezionato;

    @Override
    public String toString () {
        return "RicercaUtenteFiltroVO [id=" + id + ", codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
            + ", soloUtentiInVita=" + soloUtentiInVita + ", codiceCategoriaCdu=" + codiceCategoriaCdu + ", codiceCdu=" + codiceCdu
            + ", descrizioneCategoriaCdu=" + descrizioneCategoriaCdu + ", descrizioneCdu=" + descrizioneCdu + ", codiceTematica=" + codiceTematica
            + ", idCodiceVersamento=" + idCodiceVersamento + "]";
    }

    public String getDescrizioneCompletaCodiceVersamentoSelezionato () {
        return descrizioneCompletaCodiceVersamentoSelezionato;
    }

    public void setDescrizioneCompletaCodiceVersamentoSelezionato ( String descrizioneCompletaCodiceVersamentoSelezionato ) {
        this.descrizioneCompletaCodiceVersamentoSelezionato = descrizioneCompletaCodiceVersamentoSelezionato;
    }

    public String getCodiceTematica () {
        return codiceTematica;
    }

    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getDescrizioneCategoriaCdu () {
        return descrizioneCategoriaCdu;
    }

    public void setDescrizioneCategoriaCdu ( String descrizioneCategoriaCdu ) {
        this.descrizioneCategoriaCdu = descrizioneCategoriaCdu;
    }

    public String getDescrizioneCdu () {
        return descrizioneCdu;
    }

    public void setDescrizioneCdu ( String descrizioneCdu ) {
        this.descrizioneCdu = descrizioneCdu;
    }

    public String getCodiceCategoriaCdu () {
        return codiceCategoriaCdu;
    }

    public void setCodiceCategoriaCdu ( String codiceCategoriaCdu ) {
        this.codiceCategoriaCdu = codiceCategoriaCdu;
    }

    public String getCodiceCdu () {
        return codiceCdu;
    }

    public void setCodiceCdu ( String codiceCdu ) {
        this.codiceCdu = codiceCdu;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
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

    public Boolean getSoloUtentiInVita () {
        return soloUtentiInVita;
    }

    public void setSoloUtentiInVita ( Boolean soloUtentiInVita ) {
        this.soloUtentiInVita = soloUtentiInVita;
    }

}
