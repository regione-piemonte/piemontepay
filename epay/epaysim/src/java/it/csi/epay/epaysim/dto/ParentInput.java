/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto;

import java.io.Serializable;


public class ParentInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private CallerInputDTO caller;

    public CallerInputDTO getCaller () {
        return caller;
    }

    public void setCaller ( CallerInputDTO caller ) {
        this.caller = caller;
    }

}
