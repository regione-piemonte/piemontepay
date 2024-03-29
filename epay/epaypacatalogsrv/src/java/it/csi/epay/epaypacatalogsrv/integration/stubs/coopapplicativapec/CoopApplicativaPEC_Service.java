/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-10-25T10:06:22.744+02:00
 * Generated source version: 3.2.5
 *
 */
@WebServiceClient(name = "CoopApplicativaPEC",
                  wsdlLocation = "file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epaypacatalogsrv-svn/docs/wso2/wsdl/WSCoopApplicativaPEC.wsdl",
                  targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
public class CoopApplicativaPEC_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.csi.it/epay/epaywso/coopapplicativapec", "CoopApplicativaPEC");
    public final static QName CoopApplicativaPEC = new QName("http://www.csi.it/epay/epaywso/coopapplicativapec", "CoopApplicativaPEC");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epaypacatalogsrv-svn/docs/wso2/wsdl/WSCoopApplicativaPEC.wsdl");
        } catch (MalformedURLException e) {
			Logger log = LogManager.getLogger(CoopApplicativaPEC_Service.class.getName());
			log.info("Can not initialize the default wsdl from file:/C:/Users/fabfenogli/workspaces/ws-eng-0/epaypacatalogsrv-svn/docs/wso2/wsdl/WSCoopApplicativaPEC.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CoopApplicativaPEC_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public CoopApplicativaPEC_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public CoopApplicativaPEC_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns CoopApplicativaPEC
     */
    @WebEndpoint(name = "CoopApplicativaPEC")
    public CoopApplicativaPEC getCoopApplicativaPEC() {
        return super.getPort(CoopApplicativaPEC, CoopApplicativaPEC.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CoopApplicativaPEC
     */
    @WebEndpoint(name = "CoopApplicativaPEC")
    public CoopApplicativaPEC getCoopApplicativaPEC(WebServiceFeature... features) {
        return super.getPort(CoopApplicativaPEC, CoopApplicativaPEC.class, features);
    }

}
