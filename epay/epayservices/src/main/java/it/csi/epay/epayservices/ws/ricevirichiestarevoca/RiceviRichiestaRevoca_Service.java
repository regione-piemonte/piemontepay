/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

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
@WebServiceClient(name = "RiceviRichiestaRevoca", targetNamespace = "http://www.csi.it/epay/epaywso/ricevirichiestarevoca", wsdlLocation = "file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epayservices/docs/wsdl/CoopApplicativaPEC.wsdl")
public class RiceviRichiestaRevoca_Service
    extends Service
{

    private final static URL RICEVIRICHIESTAREVOCA_WSDL_LOCATION;
    private final static WebServiceException RICEVIRICHIESTAREVOCA_EXCEPTION;
    private final static QName RICEVIRICHIESTAREVOCA_QNAME = new QName("http://www.csi.it/epay/epaywso/ricevirichiestarevoca", "RiceviRichiestaRevoca");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/ilaria/epayservices/docs/wsdl/RiceviRichiestaRevoca.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RICEVIRICHIESTAREVOCA_WSDL_LOCATION = url;
        RICEVIRICHIESTAREVOCA_EXCEPTION = e;
    }

    public RiceviRichiestaRevoca_Service() {
        super(__getWsdlLocation(), RICEVIRICHIESTAREVOCA_QNAME);
    }

    public RiceviRichiestaRevoca_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), RICEVIRICHIESTAREVOCA_QNAME, features);
    }

    public RiceviRichiestaRevoca_Service(URL wsdlLocation) {
        super(wsdlLocation, RICEVIRICHIESTAREVOCA_QNAME);
    }

    public RiceviRichiestaRevoca_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RICEVIRICHIESTAREVOCA_QNAME, features);
    }

    public RiceviRichiestaRevoca_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RiceviRichiestaRevoca_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RiceviRichiestaRevoca
     */
    @WebEndpoint(name = "RiceviRichiestaRevoca")
    public RiceviRichiestaRevoca getRiceviRichiestaRevoca() {
        return super.getPort(new QName("http://www.csi.it/epay/epaywso/ricevirichiestarevoca", "RiceviRichiestaRevoca"), RiceviRichiestaRevoca.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RiceviRichiestaRevoca
     */
    @WebEndpoint(name = "RiceviRichiestaRevoca")
    public RiceviRichiestaRevoca getRiceviRichiestaRevoca(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.csi.it/epay/epaywso/ricevirichiestarevoca", "RiceviRichiestaRevoca"), RiceviRichiestaRevoca.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RICEVIRICHIESTAREVOCA_EXCEPTION!= null) {
            throw RICEVIRICHIESTAREVOCA_EXCEPTION;
        }
        return RICEVIRICHIESTAREVOCA_WSDL_LOCATION;
    }

}