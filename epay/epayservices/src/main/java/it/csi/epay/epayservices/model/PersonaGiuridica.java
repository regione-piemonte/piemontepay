/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class PersonaGiuridica implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String ragioneSociale;

    public String getRagioneSociale () {
        return ragioneSociale;
    }

    public void setRagioneSociale ( String ragioneSociale ) {
        this.ragioneSociale = ragioneSociale;
    }

}
