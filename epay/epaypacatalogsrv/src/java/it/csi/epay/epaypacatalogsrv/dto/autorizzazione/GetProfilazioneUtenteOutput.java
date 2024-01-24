/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class GetProfilazioneUtenteOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private GetProfilazioneUtenteCorrenteOutputDto utente;

    private GetProfilazioneUtenteCorrenteEnteOutputDto ente;

    private List<GetProfilazioneUtenteCorrenteTematicaOutputDto> tematiche;

    @Override
    public String toString () {
        return "GetProfilazioneUtenteOutput [utente=" + utente + ", ente=" + ente + ", tematiche=" + tematiche + "]";
    }

    public List<GetProfilazioneUtenteCorrenteTematicaOutputDto> getTematiche () {
        return tematiche;
    }

    public void setTematiche ( List<GetProfilazioneUtenteCorrenteTematicaOutputDto> tematiche ) {
        this.tematiche = tematiche;
    }

    public GetProfilazioneUtenteCorrenteOutputDto getUtente () {
        return utente;
    }

    public void setUtente ( GetProfilazioneUtenteCorrenteOutputDto utente ) {
        this.utente = utente;
    }

    public GetProfilazioneUtenteCorrenteEnteOutputDto getEnte () {
        return ente;
    }

    public void setEnte ( GetProfilazioneUtenteCorrenteEnteOutputDto ente ) {
        this.ente = ente;
    }

}
