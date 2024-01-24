/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.5
 * Fri Nov 27 11:46:40 CET 2020
 * Generated source version: 2.2.5
 * 
 */


@WebServiceClient(name = "EPaywsoBusinessServiceImplService", 
                  wsdlLocation = "http://localhost:8080/epaywsosrv/EPaywsoBusinessService?wsdl",
                  targetNamespace = "http://www.csi.it/epay/epaywso/business") 
public class EPaywsoBusinessServiceImplService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.csi.it/epay/epaywso/business", "EPaywsoBusinessServiceImplService");
    public final static QName EPaywsoBusinessServiceSOAP = new QName("http://www.csi.it/epay/epaywso/business", "EPaywsoBusinessServiceSOAP");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/epaywsosrv/EPaywsoBusinessService?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/epaywsosrv/EPaywsoBusinessService?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public EPaywsoBusinessServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EPaywsoBusinessServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EPaywsoBusinessServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns EPaywsoBusinessService
     */
    @WebEndpoint(name = "EPaywsoBusinessServiceSOAP")
    public EPaywsoBusinessService getEPaywsoBusinessServiceSOAP() {
        return super.getPort(EPaywsoBusinessServiceSOAP, EPaywsoBusinessService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EPaywsoBusinessService
     */
    @WebEndpoint(name = "EPaywsoBusinessServiceSOAP")
    public EPaywsoBusinessService getEPaywsoBusinessServiceSOAP(WebServiceFeature... features) {
        return super.getPort(EPaywsoBusinessServiceSOAP, EPaywsoBusinessService.class, features);
    }

}