/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.security;

import java.io.Serializable;

import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.CallerInputDto;


public class RequestContext implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Class<?> outputDataType;

    private Object inputData;

    private CallerInputDto caller;

    private PrincipalVO principal;

    public Class<?> getOutputDataType () {
        return outputDataType;
    }

    public RequestContext setOutputDataType ( Class<?> outputDataType ) {
        this.outputDataType = outputDataType;
        return this;
    }

    public Object getInputData () {
        return inputData;
    }

    public RequestContext setInputData ( Object inputData ) {
        this.inputData = inputData;
        return this;
    }

    public CallerInputDto getCaller () {
        return caller;
    }

    public RequestContext setCaller ( CallerInputDto caller ) {
        this.caller = caller;
        return this;
    }

    public PrincipalVO getPrincipal () {
        return principal;
    }

    public RequestContext setPrincipal ( PrincipalVO principal ) {
        this.principal = principal;
        return this;
    }

}
