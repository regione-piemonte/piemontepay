/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpmultiiuv;

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
@WebServiceClient(name = "MdpMultiIuvWSInterface", targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/", wsdlLocation = "http://tst-applogic.reteunitaria.piemonte.it/mdpmultiiuv/MdpMultiIuvsrvServiceWS?wsdl")
public class MdpMultiIuvWSInterface_Service
    extends Service
{

    private final static URL MDPMULTIIUVWSINTERFACE_WSDL_LOCATION;
    private final static WebServiceException MDPMULTIIUVWSINTERFACE_EXCEPTION;
    private final static QName MDPMULTIIUVWSINTERFACE_QNAME = new QName("http://mdpnew.csi.it/mdpmultiiuv/", "MdpMultiIuvWSInterface");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://tst-applogic.reteunitaria.piemonte.it/mdpmultiiuv/MdpMultiIuvsrvServiceWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MDPMULTIIUVWSINTERFACE_WSDL_LOCATION = url;
        MDPMULTIIUVWSINTERFACE_EXCEPTION = e;
    }

    public MdpMultiIuvWSInterface_Service() {
        super(__getWsdlLocation(), MDPMULTIIUVWSINTERFACE_QNAME);
    }

    public MdpMultiIuvWSInterface_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), MDPMULTIIUVWSINTERFACE_QNAME, features);
    }

    public MdpMultiIuvWSInterface_Service(URL wsdlLocation) {
        super(wsdlLocation, MDPMULTIIUVWSINTERFACE_QNAME);
    }

    public MdpMultiIuvWSInterface_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MDPMULTIIUVWSINTERFACE_QNAME, features);
    }

    public MdpMultiIuvWSInterface_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MdpMultiIuvWSInterface_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MdpMultiIuvWSInterface
     */
    @WebEndpoint(name = "MdpMultiIuvWSPort")
    public MdpMultiIuvWSInterface getMdpMultiIuvWSPort() {
        return super.getPort(new QName("http://mdpnew.csi.it/mdpmultiiuv/", "MdpMultiIuvWSPort"), MdpMultiIuvWSInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MdpMultiIuvWSInterface
     */
    @WebEndpoint(name = "MdpMultiIuvWSPort")
    public MdpMultiIuvWSInterface getMdpMultiIuvWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://mdpnew.csi.it/mdpmultiiuv/", "MdpMultiIuvWSPort"), MdpMultiIuvWSInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MDPMULTIIUVWSINTERFACE_EXCEPTION!= null) {
            throw MDPMULTIIUVWSINTERFACE_EXCEPTION;
        }
        return MDPMULTIIUVWSINTERFACE_WSDL_LOCATION;
    }

}