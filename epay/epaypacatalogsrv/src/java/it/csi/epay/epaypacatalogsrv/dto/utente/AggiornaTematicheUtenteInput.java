/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class AggiornaTematicheUtenteInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    private List<AggiornaTematicheUtenteTematicaInputDto> tematiche;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public List<AggiornaTematicheUtenteTematicaInputDto> getTematiche () {
        return tematiche;
    }

    public void setTematiche ( List<AggiornaTematicheUtenteTematicaInputDto> tematiche ) {
        this.tematiche = tematiche;
    }

    @Override
    public String toString () {
        return "AggiornaTematicheUtenteInput [id=" + id + ", tematiche=" + tematiche + "]";
    }
}
