/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;


public class MdpTEnteTemp extends MdpTEnte implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idOperazione;

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

}
