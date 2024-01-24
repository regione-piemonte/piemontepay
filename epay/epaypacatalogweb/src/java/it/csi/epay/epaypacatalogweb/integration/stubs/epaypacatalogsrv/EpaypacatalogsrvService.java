/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.5
 * Mon Jan 10 16:03:31 CET 2022
 * Generated source version: 2.2.5
 * 
 */


@WebServiceClient(name = "EpaypacatalogsrvService", 
                  wsdlLocation = "http://localhost:8080/epaypacatalogsrvApplEpaypacatalogsrvWs/EpaypacatalogsrvService?wsdl",
                  targetNamespace = "http://business.epaypacatalogsrv.epay.csi.it/") 
public class EpaypacatalogsrvService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://business.epaypacatalogsrv.epay.csi.it/", "EpaypacatalogsrvService");
    public final static QName EpaypacatalogsrvPort = new QName("http://business.epaypacatalogsrv.epay.csi.it/", "EpaypacatalogsrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/epaypacatalogsrvApplEpaypacatalogsrvWs/EpaypacatalogsrvService?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/epaypacatalogsrvApplEpaypacatalogsrvWs/EpaypacatalogsrvService?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public EpaypacatalogsrvService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EpaypacatalogsrvService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EpaypacatalogsrvService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns Epaypacatalogsrv
     */
    @WebEndpoint(name = "EpaypacatalogsrvPort")
    public Epaypacatalogsrv getEpaypacatalogsrvPort() {
        return super.getPort(EpaypacatalogsrvPort, Epaypacatalogsrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Epaypacatalogsrv
     */
    @WebEndpoint(name = "EpaypacatalogsrvPort")
    public Epaypacatalogsrv getEpaypacatalogsrvPort(WebServiceFeature... features) {
        return super.getPort(EpaypacatalogsrvPort, Epaypacatalogsrv.class, features);
    }

}