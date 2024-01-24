/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class EliminaUtenteInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    @Override
    public String toString () {
        return "EliminaUtenteInput [id=" + id + "]";
    }

}
