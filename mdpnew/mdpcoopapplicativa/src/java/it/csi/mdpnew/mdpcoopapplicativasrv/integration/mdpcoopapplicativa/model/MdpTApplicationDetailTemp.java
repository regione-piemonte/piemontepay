/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class MdpTApplicationDetailTemp extends MdpTApplicationDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    String idOperazione;

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    @Override
    public String toString () {
        return super.toString () + " - MdpTApplicationDetailTemp [idOperazione=" + idOperazione + "]";
    }

    
}
