/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.dispatcher;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.MessageDto;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


public class OperationDispatchingContext<InputType, OutputType> {

    private InputType input;

    private PrincipalVO principal;

    private OutputType output = null;

    private List<MessageDto> messaggi = new ArrayList<> ();

    public OperationDispatchingContext ( InputType input ) {
        super ();
        this.input = input;
    }

    public List<MessageDto> getMessaggi () {
        return messaggi;
    }

    public InputType getInput () {
        return input;
    }

    public PrincipalVO getPrincipal () {
        return principal;
    }

    public void setPrincipal ( PrincipalVO principal ) {
        this.principal = principal;
    }

    public OutputType getOutput () {
        return output;
    }

    public void setOutput ( OutputType output ) {
        this.output = output;
    }

}
