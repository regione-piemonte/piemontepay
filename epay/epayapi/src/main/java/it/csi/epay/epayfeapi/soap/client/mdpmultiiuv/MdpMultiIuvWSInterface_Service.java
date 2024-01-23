/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpmultiiuv;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by Apache CXF 3.3.0
 * 2023-03-17T15:23:39.468+01:00
 * Generated source version: 3.3.0
 */
@WebServiceClient ( name = "MdpMultiIuvWSInterface",
				wsdlLocation = "http://tst-applogic.reteunitaria.piemonte.it/mdpmultiiuv/MdpMultiIuvsrvServiceWS?wsdl",
				targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/" )
@SuppressWarnings ( "unused" )
public class MdpMultiIuvWSInterface_Service extends Service {

	public final static URL WSDL_LOCATION;

	public final static QName SERVICE = new QName ( "http://mdpnew.csi.it/mdpmultiiuv/", "MdpMultiIuvWSInterface" );

	public final static QName MdpMultiIuvWSPort = new QName ( "http://mdpnew.csi.it/mdpmultiiuv/", "MdpMultiIuvWSPort" );

	static {
		URL url = null;
		try {
			url = new URL ( "http://tst-applogic.reteunitaria.piemonte.it/mdpmultiiuv/MdpMultiIuvsrvServiceWS?wsdl" );
		} catch ( MalformedURLException e ) {
			java.util.logging.Logger.getLogger ( MdpMultiIuvWSInterface_Service.class.getName () )
							.log ( java.util.logging.Level.INFO,
											"Can not initialize the default wsdl from {0}",
											"http://tst-applogic.reteunitaria.piemonte.it/mdpmultiiuv/MdpMultiIuvsrvServiceWS?wsdl" );
		}
		WSDL_LOCATION = url;
	}

	public MdpMultiIuvWSInterface_Service ( URL wsdlLocation ) {
		super ( wsdlLocation, SERVICE );
	}

	public MdpMultiIuvWSInterface_Service ( URL wsdlLocation, QName serviceName ) {
		super ( wsdlLocation, serviceName );
	}

	public MdpMultiIuvWSInterface_Service () {
		super ( WSDL_LOCATION, SERVICE );
	}

	public MdpMultiIuvWSInterface_Service ( WebServiceFeature... features ) {
		super ( WSDL_LOCATION, SERVICE, features );
	}

	public MdpMultiIuvWSInterface_Service ( URL wsdlLocation, WebServiceFeature... features ) {
		super ( wsdlLocation, SERVICE, features );
	}

	public MdpMultiIuvWSInterface_Service ( URL wsdlLocation, QName serviceName, WebServiceFeature... features ) {
		super ( wsdlLocation, serviceName, features );
	}

	/**
	 * @return returns MdpMultiIuvWSInterface
	 */
	@WebEndpoint ( name = "MdpMultiIuvWSPort" )
	public MdpMultiIuvWSInterface getMdpMultiIuvWSPort () {
		return super.getPort ( MdpMultiIuvWSPort, MdpMultiIuvWSInterface.class );
	}

	/**
	 * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
	 * @return returns MdpMultiIuvWSInterface
	 */
	@WebEndpoint ( name = "MdpMultiIuvWSPort" )
	public MdpMultiIuvWSInterface getMdpMultiIuvWSPort ( WebServiceFeature... features ) {
		return super.getPort ( MdpMultiIuvWSPort, MdpMultiIuvWSInterface.class, features );
	}

}
