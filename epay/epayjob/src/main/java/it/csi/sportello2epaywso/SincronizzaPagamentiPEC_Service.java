/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.sportello2epaywso;

import org.apache.logging.log4j.LogManager;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.5
 * 2020-01-22T17:44:32.112+01:00
 * Generated source version: 3.2.5
 *
 */
@WebServiceClient(name = "SincronizzaPagamentiPEC",
                  wsdlLocation = "http://localhost/epaypaweb-business/SincronizzaPagamentiPEC/SincronizzaPagamentiPEC?wsdl",
                  targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec")
public class SincronizzaPagamentiPEC_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.csi.it/epay/epaypa/sincronizzapagamentipec", "SincronizzaPagamentiPEC");
    public final static QName SincronizzaPagamentiPEC = new QName("http://www.csi.it/epay/epaypa/sincronizzapagamentipec", "SincronizzaPagamentiPEC");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost/epaypaweb-business/SincronizzaPagamentiPEC/SincronizzaPagamentiPEC?wsdl");
        } catch (MalformedURLException e) {
			LogManager.getLogger(SincronizzaPagamentiPEC_Service.class.getName())
                .info(
                     "Can not initialize the default wsdl from {0}", "http://localhost/epaypaweb-business/SincronizzaPagamentiPEC/SincronizzaPagamentiPEC?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SincronizzaPagamentiPEC_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public SincronizzaPagamentiPEC_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SincronizzaPagamentiPEC_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns SincronizzaPagamentiPEC
     */
    @WebEndpoint(name = "SincronizzaPagamentiPEC")
    public SincronizzaPagamentiPEC getSincronizzaPagamentiPEC() {
        return super.getPort(SincronizzaPagamentiPEC, SincronizzaPagamentiPEC.class);
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
        return super.getPort(SincronizzaPagamentiPEC, SincronizzaPagamentiPEC.class, features);
    }

}