/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.coopapplicativapec;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CoopApplicativaPEC", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", wsdlLocation = "file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epayservices/docs/wsdl/CoopApplicativaPEC.wsdl")
public class CoopApplicativaPEC_Service
    extends Service
{

    private final static URL COOPAPPLICATIVAPEC_WSDL_LOCATION;
    private final static WebServiceException COOPAPPLICATIVAPEC_EXCEPTION;
    private final static QName COOPAPPLICATIVAPEC_QNAME = new QName("http://www.csi.it/epay/epaywso/coopapplicativapec", "CoopApplicativaPEC");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epayservices/docs/wsdl/CoopApplicativaPEC.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        COOPAPPLICATIVAPEC_WSDL_LOCATION = url;
        COOPAPPLICATIVAPEC_EXCEPTION = e;
    }

    public CoopApplicativaPEC_Service() {
        super(__getWsdlLocation(), COOPAPPLICATIVAPEC_QNAME);
    }

    public CoopApplicativaPEC_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), COOPAPPLICATIVAPEC_QNAME, features);
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation) {
        super(wsdlLocation, COOPAPPLICATIVAPEC_QNAME);
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, COOPAPPLICATIVAPEC_QNAME, features);
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CoopApplicativaPEC
     */
    @WebEndpoint(name = "CoopApplicativaPEC")
    public CoopApplicativaPEC getCoopApplicativaPEC() {
        return super.getPort(new QName("http://www.csi.it/epay/epaywso/coopapplicativapec", "CoopApplicativaPEC"), CoopApplicativaPEC.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CoopApplicativaPEC
     */
    @WebEndpoint(name = "CoopApplicativaPEC")
    public CoopApplicativaPEC getCoopApplicativaPEC(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.csi.it/epay/epaywso/coopapplicativapec", "CoopApplicativaPEC"), CoopApplicativaPEC.class, features);
    }

    private static URL __getWsdlLocation() {
        if (COOPAPPLICATIVAPEC_EXCEPTION!= null) {
            throw COOPAPPLICATIVAPEC_EXCEPTION;
        }
        return COOPAPPLICATIVAPEC_WSDL_LOCATION;
    }

}
