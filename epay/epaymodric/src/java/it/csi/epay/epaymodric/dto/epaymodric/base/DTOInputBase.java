/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;


public class DTOInputBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private DTOCaller caller;

    public DTOCaller getCaller () {
        return caller;
    }

    public void setCaller ( DTOCaller caller ) {
        this.caller = caller;
    }

}
