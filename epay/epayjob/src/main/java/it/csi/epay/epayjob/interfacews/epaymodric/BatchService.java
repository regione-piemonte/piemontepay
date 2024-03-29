/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.5
 * Thu Mar 02 15:03:21 CET 2023
 * Generated source version: 2.2.5
 * 
 */


@WebServiceClient(name = "BatchService", 
                  wsdlLocation = "file:/C:/Users/abora/AppData/Local/Temp/tempdir126956978610974770.tmp/BatchService_1.wsdl",
                  targetNamespace = "http://batch.epaymodric.business.epaymodric.epay.csi.it/") 
public class BatchService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://batch.epaymodric.business.epaymodric.epay.csi.it/", "BatchService");
    public final static QName BatchServicePort = new QName("http://batch.epaymodric.business.epaymodric.epay.csi.it/", "BatchServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/abora/AppData/Local/Temp/tempdir126956978610974770.tmp/BatchService_1.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/C:/Users/abora/AppData/Local/Temp/tempdir126956978610974770.tmp/BatchService_1.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public BatchService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public BatchService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BatchService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns EpaymodricJob
     */
    @WebEndpoint(name = "BatchServicePort")
    public EpaymodricJob getBatchServicePort() {
        return super.getPort(BatchServicePort, EpaymodricJob.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EpaymodricJob
     */
    @WebEndpoint(name = "BatchServicePort")
    public EpaymodricJob getBatchServicePort(WebServiceFeature... features) {
        return super.getPort(BatchServicePort, EpaymodricJob.class, features);
    }

}
