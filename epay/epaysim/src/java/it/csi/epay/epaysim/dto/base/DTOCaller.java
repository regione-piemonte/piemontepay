/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto.base;

import java.io.Serializable;


public class DTOCaller implements Serializable {

    private static final long serialVersionUID = 8404972200559120082L;

    private String codiceApplicativo;

    private DTOPrincipal principal;

    private String ip;

    public String getCodiceApplicativo () {
        return codiceApplicativo;
    }

    public void setCodiceApplicativo ( String codiceApplicativo ) {
        this.codiceApplicativo = codiceApplicativo;
    }

    public DTOPrincipal getPrincipal () {
        return principal;
    }

    public void setPrincipal ( DTOPrincipal principal ) {
        this.principal = principal;
    }

    public String getIp () {
        return ip;
    }

    public void setIp ( String ip ) {
        this.ip = ip;
    }

    @Override
    public String toString () {
        return "DTOCaller [codiceApplicativo=" + codiceApplicativo + ", principal=" + principal + ", ip=" + ip + "]";
    }

}
