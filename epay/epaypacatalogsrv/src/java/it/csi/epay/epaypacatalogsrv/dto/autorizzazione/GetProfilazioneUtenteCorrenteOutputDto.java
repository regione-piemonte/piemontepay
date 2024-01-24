/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class GetProfilazioneUtenteCorrenteOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String nome;

    private String cognome;

    private String email;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    //EPAY-80
    private Date dataInizioValiditaCurrent;

    //EPAY-80
    private Date dataFineValiditaCurrent;

    private List<GetProfilazioneUtenteCorrenteCduOutputDto> cdu;

    @Override
    public String toString () {
        return "GetProfilazioneUtenteCorrenteOutputDto [id=" + id
                        + ", codiceFiscale=" + codiceFiscale
                        + ", nome=" + nome
                        + ", cognome=" + cognome
                        + ", email=" + email
                        + ", dataInizioValidita=" + dataInizioValidita
                        + ", dataFineValidita=" + dataFineValidita
                        + ", dataInizioValiditaCurrent=" + dataInizioValiditaCurrent
                        + ", dataFineValiditaCurrent=" + dataFineValiditaCurrent
                        + ", cdu=" + cdu + "]";
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public List<GetProfilazioneUtenteCorrenteCduOutputDto> getCdu () {
        return cdu;
    }

    public void setCdu ( List<GetProfilazioneUtenteCorrenteCduOutputDto> cdu ) {
        this.cdu = cdu;
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

    /**
     * @return the dataInizioValiditaCurrent
     */
    public Date getDataInizioValiditaCurrent () {
        return dataInizioValiditaCurrent;
    }

    /**
     * @param dataInizioValiditaCurrent the dataInizioValiditaCurrent to set
     */
    public void setDataInizioValiditaCurrent ( Date dataInizioValiditaCurrent ) {
        this.dataInizioValiditaCurrent = dataInizioValiditaCurrent;
    }

    /**
     * @return the dataFineValiditaCurrent
     */
    public Date getDataFineValiditaCurrent () {
        return dataFineValiditaCurrent;
    }

    /**
     * @param dataFineValiditaCurrent the dataFineValiditaCurrent to set
     */
    public void setDataFineValiditaCurrent ( Date dataFineValiditaCurrent ) {
        this.dataFineValiditaCurrent = dataFineValiditaCurrent;
    }

}
