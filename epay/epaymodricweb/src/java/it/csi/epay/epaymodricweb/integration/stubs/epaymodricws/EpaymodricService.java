/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.5
 * Thu Dec 03 18:11:27 CET 2020
 * Generated source version: 2.2.5
 * 
 */


@WebServiceClient(name = "EpaymodricService", 
                  wsdlLocation = "http://localhost:8080/epaymodricApplEpaymodricWs/EpaymodricService?WSDL",
                  targetNamespace = "http://epaymodric.business.epaymodric.epay.csi.it/") 
public class EpaymodricService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://epaymodric.business.epaymodric.epay.csi.it/", "EpaymodricService");
    public final static QName EpaymodricPort = new QName("http://epaymodric.business.epaymodric.epay.csi.it/", "EpaymodricPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/epaymodricApplEpaymodricWs/EpaymodricService?WSDL");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/epaymodricApplEpaymodricWs/EpaymodricService?WSDL");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public EpaymodricService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EpaymodricService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EpaymodricService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns Epaymodric
     */
    @WebEndpoint(name = "EpaymodricPort")
    public Epaymodric getEpaymodricPort() {
        return super.getPort(EpaymodricPort, Epaymodric.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Epaymodric
     */
    @WebEndpoint(name = "EpaymodricPort")
    public Epaymodric getEpaymodricPort(WebServiceFeature... features) {
        return super.getPort(EpaymodricPort, Epaymodric.class, features);
    }

}