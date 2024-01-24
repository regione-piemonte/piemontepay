/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;


public class CallerInputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codiceApplicativo;

    private PrincipalInputDto principal;

    private String ip;

    public CallerInputDto () {
    }

    public CallerInputDto ( String codiceApplicativo, PrincipalInputDto principal ) {
        super ();
        this.codiceApplicativo = codiceApplicativo;
        this.principal = principal;
    }

    public String getIp () {
        return ip;
    }

    public void setIp ( String ip ) {
        this.ip = ip;
    }

    public PrincipalInputDto getPrincipal () {
        return principal;
    }

    public void setPrincipal ( PrincipalInputDto principal ) {
        this.principal = principal;
    }

    public String getCodiceApplicativo () {
        return codiceApplicativo;
    }

    public void setCodiceApplicativo ( String codiceApplicativo ) {
        this.codiceApplicativo = codiceApplicativo;
    }

    @Override
    public String toString () {
        return "CallerInputDto [codiceApplicativo=" + codiceApplicativo + ", principal=" + principal + ", ip=" + ip + "]";
    }

}
