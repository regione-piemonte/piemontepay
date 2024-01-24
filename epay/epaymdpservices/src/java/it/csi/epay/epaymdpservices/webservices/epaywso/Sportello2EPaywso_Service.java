/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.epaywso;

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
@WebServiceClient(name = "Sportello2EPaywso", targetNamespace = "http://www.csi.it/epay/epaywso/sportello2epaywso", wsdlLocation = "file:/C:/Esl/Progetti_CSI/epaymdpservices/docs/wsdl/Sportello2EPaywsoProxy.wsdl")
public class Sportello2EPaywso_Service
    extends Service
{

    private final static URL SPORTELLO2EPAYWSO_WSDL_LOCATION;
    private final static WebServiceException SPORTELLO2EPAYWSO_EXCEPTION;
    private final static QName SPORTELLO2EPAYWSO_QNAME = new QName("http://www.csi.it/epay/epaywso/sportello2epaywso", "Sportello2EPaywso");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Esl/Progetti_CSI/epaymdpservices/docs/wsdl/Sportello2EPaywsoProxy.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SPORTELLO2EPAYWSO_WSDL_LOCATION = url;
        SPORTELLO2EPAYWSO_EXCEPTION = e;
    }

    public Sportello2EPaywso_Service() {
        super(__getWsdlLocation(), SPORTELLO2EPAYWSO_QNAME);
    }

    public Sportello2EPaywso_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SPORTELLO2EPAYWSO_QNAME, features);
    }

    public Sportello2EPaywso_Service(URL wsdlLocation) {
        super(wsdlLocation, SPORTELLO2EPAYWSO_QNAME);
    }

    public Sportello2EPaywso_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SPORTELLO2EPAYWSO_QNAME, features);
    }

    public Sportello2EPaywso_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Sportello2EPaywso_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Sportello2EPaywso
     */
    @WebEndpoint(name = "Sportello2EPaywsoSOAP")
    public Sportello2EPaywso getSportello2EPaywsoSOAP() {
        return super.getPort(new QName("http://www.csi.it/epay/epaywso/sportello2epaywso", "Sportello2EPaywsoSOAP"), Sportello2EPaywso.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Sportello2EPaywso
     */
    @WebEndpoint(name = "Sportello2EPaywsoSOAP")
    public Sportello2EPaywso getSportello2EPaywsoSOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.csi.it/epay/epaywso/sportello2epaywso", "Sportello2EPaywsoSOAP"), Sportello2EPaywso.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SPORTELLO2EPAYWSO_EXCEPTION!= null) {
            throw SPORTELLO2EPAYWSO_EXCEPTION;
        }
        return SPORTELLO2EPAYWSO_WSDL_LOCATION;
    }

}