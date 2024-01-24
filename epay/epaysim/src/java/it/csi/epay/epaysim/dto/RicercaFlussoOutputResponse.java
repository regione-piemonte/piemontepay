/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.util.ArrayList;
import java.util.List;


public class RicercaFlussoOutputResponse extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private List<FlussoOriginePagopaOutputDTO> testata = new ArrayList<FlussoOriginePagopaOutputDTO> ();

    public List<FlussoOriginePagopaOutputDTO> getTestata () {
        return testata;
    }

    public void setTestata ( List<FlussoOriginePagopaOutputDTO> testata ) {
        this.testata = testata;
    }

}
