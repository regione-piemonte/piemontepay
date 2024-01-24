/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class AggiornaCduUtenteInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    private List<String> codiciCdu;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public List<String> getCodiciCdu () {
        return codiciCdu;
    }

    public void setCodiciCdu ( List<String> codiciCdu ) {
        this.codiciCdu = codiciCdu;
    }

    @Override
    public String toString () {
        return "AggiornaCduUtenteInput [id=" + id + ", codiciCdu=" + codiciCdu + "]";
    }

}
