/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


/**
 *
 */

public class AbstractOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7496366391102968778L;

    private Esito result;

    /**
     * @return the result
     */
    public Esito getResult () {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult ( Esito result ) {
        this.result = result;
    }
}
