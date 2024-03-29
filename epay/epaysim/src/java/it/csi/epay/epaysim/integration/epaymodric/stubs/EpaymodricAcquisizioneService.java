/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.integration.epaymodric.stubs;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2018-07-30T12:17:55.707+02:00
 * Generated source version: 2.4.6
 * 
 */
@WebServiceClient(name = "EpaymodricAcquisizioneService", 
                  wsdlLocation = "/EpaymodricAcquisizione.wsdl",
                  targetNamespace = "http://epaymodric.business.epaymodric.epay.csi.it/") 
public class EpaymodricAcquisizioneService extends Service {

    public final static URL WSDL_LOCATION;

	public final static QName SERVICE = new QName("http://epaymodric.business.epaymodric.epay.csi.it/",
			"EpaymodricAcquisizioneService");
	public final static QName EpaymodricPort = new QName("http://epaymodric.business.epaymodric.epay.csi.it/",
			"EpaymodricPort");
	static {
		URL url = null;
		url = EpaymodricAcquisizioneService.class.getResource("/EpaycatalogsrvService.wsdl");
		WSDL_LOCATION = url;
	}

    public EpaymodricAcquisizioneService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EpaymodricAcquisizioneService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EpaymodricAcquisizioneService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EpaymodricAcquisizioneService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EpaymodricAcquisizioneService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EpaymodricAcquisizioneService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns EpaymodricAcquisizione
     */
    @WebEndpoint(name = "EpaymodricPort")
    public EpaymodricAcquisizione getEpaymodricPort() {
        return super.getPort(EpaymodricPort, EpaymodricAcquisizione.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EpaymodricAcquisizione
     */
    @WebEndpoint(name = "EpaymodricPort")
    public EpaymodricAcquisizione getEpaymodricPort(WebServiceFeature... features) {
        return super.getPort(EpaymodricPort, EpaymodricAcquisizione.class, features);
    }

}
