/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto;

import java.io.Serializable;


public class CallerInputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codiceApplicativo;

    private PrincipalInputDTO principal;

    public CallerInputDTO () {
    }

    public CallerInputDTO ( String codiceApplicativo, PrincipalInputDTO principal ) {
        super ();
        this.codiceApplicativo = codiceApplicativo;
        this.principal = principal;
    }

    public PrincipalInputDTO getPrincipal () {
        return principal;
    }

    public void setPrincipal ( PrincipalInputDTO principal ) {
        this.principal = principal;
    }

    public String getCodiceApplicativo () {
        return codiceApplicativo;
    }

    public void setCodiceApplicativo ( String codiceApplicativo ) {
        this.codiceApplicativo = codiceApplicativo;
    }

}
