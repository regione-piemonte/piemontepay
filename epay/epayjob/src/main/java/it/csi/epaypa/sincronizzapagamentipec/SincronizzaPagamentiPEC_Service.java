/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epaypa.sincronizzapagamentipec;

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
@WebServiceClient(name = "SincronizzaPagamentiPEC", targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec", wsdlLocation = "file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epayjob/docs/wsdl/SincronizzaPagamentiPEC.xml")
public class SincronizzaPagamentiPEC_Service
    extends Service
{

    private final static URL SINCRONIZZAPAGAMENTIPEC_WSDL_LOCATION;
    private final static WebServiceException SINCRONIZZAPAGAMENTIPEC_EXCEPTION;
    private final static QName SINCRONIZZAPAGAMENTIPEC_QNAME = new QName("http://www.csi.it/epay/epaypa/sincronizzapagamentipec", "SincronizzaPagamentiPEC");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epayjob/docs/wsdl/SincronizzaPagamentiPEC.xml");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SINCRONIZZAPAGAMENTIPEC_WSDL_LOCATION = url;
        SINCRONIZZAPAGAMENTIPEC_EXCEPTION = e;
    }

    public SincronizzaPagamentiPEC_Service() {
        super(__getWsdlLocation(), SINCRONIZZAPAGAMENTIPEC_QNAME);
    }

    public SincronizzaPagamentiPEC_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SINCRONIZZAPAGAMENTIPEC_QNAME, features);
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation) {
        super(wsdlLocation, SINCRONIZZAPAGAMENTIPEC_QNAME);
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SINCRONIZZAPAGAMENTIPEC_QNAME, features);
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SincronizzaPagamentiPEC
     */
    @WebEndpoint(name = "SincronizzaPagamentiPEC")
    public SincronizzaPagamentiPEC getSincronizzaPagamentiPEC() {
        return super.getPort(new QName("http://www.csi.it/epay/epaypa/sincronizzapagamentipec", "SincronizzaPagamentiPEC"), SincronizzaPagamentiPEC.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SincronizzaPagamentiPEC
     */
    @WebEndpoint(name = "SincronizzaPagamentiPEC")
    public SincronizzaPagamentiPEC getSincronizzaPagamentiPEC(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.csi.it/epay/epaypa/sincronizzapagamentipec", "SincronizzaPagamentiPEC"), SincronizzaPagamentiPEC.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SINCRONIZZAPAGAMENTIPEC_EXCEPTION!= null) {
            throw SINCRONIZZAPAGAMENTIPEC_EXCEPTION;
        }
        return SINCRONIZZAPAGAMENTIPEC_WSDL_LOCATION;
    }

}
