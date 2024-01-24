/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.builders;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoOutputResponse;


public class RicercaFlussoOutputResponseBuilder extends AbstractBuilder<RicercaFlussoOutputResponse> {

    private List<FlussoOriginePagopaOutputDTO> testata = new ArrayList<> ();

    public RicercaFlussoOutputResponseBuilder () {

    }

    @Override
    public RicercaFlussoOutputResponse build () {
        RicercaFlussoOutputResponse ricercaFlussoOutput = new RicercaFlussoOutputResponse ();
        ricercaFlussoOutput.setTestata ( testata );
        return ricercaFlussoOutput;
    }

    public RicercaFlussoOutputResponseBuilder withTestata ( List<FlussoOriginePagopaOutputDTO> testata ) {
        this.testata = testata;
        return this;
    }
}
