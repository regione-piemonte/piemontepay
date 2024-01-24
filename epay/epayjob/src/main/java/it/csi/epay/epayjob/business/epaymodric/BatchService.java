/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business.epaymodric;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.apache.logging.log4j.LogManager;

import it.csi.epay.epayjob.interfacews.epaymodric.EpaymodricJob;;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-11-19T11:11:26.693+01:00
 * Generated source version: 3.2.5
 *
 */
@WebServiceClient(name = "BatchService",
wsdlLocation = "http://dev-jboss5-epay.bilancio.csi.it:13010/epaymodricOfflineWs/BatchService?WSDL",
targetNamespace = "http://batch.epaymodric.business.epaymodric.epay.csi.it/")
public class BatchService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://batch.epaymodric.business.epaymodric.epay.csi.it/", "BatchService");
    public final static QName BatchServicePort = new QName("http://batch.epaymodric.business.epaymodric.epay.csi.it/", "BatchServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://dev-jboss5-epay.bilancio.csi.it:13010/epaymodricOfflineWs/BatchService?WSDL");
        } catch (MalformedURLException e) {
			LogManager.getLogger(BatchService.class.getName())
            .info(
                "Can not initialize the default wsdl from {0}", "http://dev-jboss5-epay.bilancio.csi.it:13010/epaymodricOfflineWs/BatchService?WSDL");
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

    public BatchService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public BatchService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public BatchService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns Epaymodric
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
     *     returns Epaymodric
     */
    @WebEndpoint(name = "BatchServicePort")
    public EpaymodricJob getBatchServicePort(WebServiceFeature... features) {
        return super.getPort(BatchServicePort, EpaymodricJob.class, features);
    }

}
