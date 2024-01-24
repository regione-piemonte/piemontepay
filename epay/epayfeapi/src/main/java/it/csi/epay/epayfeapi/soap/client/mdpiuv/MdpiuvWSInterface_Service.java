/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpiuv;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by Apache CXF 3.3.0
 * 2023-03-09T15:15:50.420+01:00
 * Generated source version: 3.3.0
 */
@WebServiceClient ( name = "MdpiuvWSInterface",
				wsdlLocation = "file:/C:/dev/soapclient/MdpiuvsrvServiceWS.wsdl",
				targetNamespace = "http://mdpnew.csi.it/mdpiuv/" )
@SuppressWarnings ( "unused" )
public class MdpiuvWSInterface_Service extends Service {

	public final static URL WSDL_LOCATION;

	public final static QName SERVICE = new QName ( "http://mdpnew.csi.it/mdpiuv/", "MdpiuvWSInterface" );

	public final static QName MdpiuvWSPort = new QName ( "http://mdpnew.csi.it/mdpiuv/", "MdpiuvWSPort" );

	static {
		URL url = null;
		try {
			url = new URL ( "file:/C:/dev/soapclient/MdpiuvsrvServiceWS.wsdl" );
		} catch ( MalformedURLException e ) {
			java.util.logging.Logger.getLogger ( MdpiuvWSInterface_Service.class.getName () )
							.log ( java.util.logging.Level.INFO,
											"Can not initialize the default wsdl from {0}", "file:/C:/dev/soapclient/MdpiuvsrvServiceWS.wsdl" );
		}
		WSDL_LOCATION = url;
	}

	public MdpiuvWSInterface_Service ( URL wsdlLocation ) {
		super ( wsdlLocation, SERVICE );
	}

	public MdpiuvWSInterface_Service ( URL wsdlLocation, QName serviceName ) {
		super ( wsdlLocation, serviceName );
	}

	public MdpiuvWSInterface_Service () {
		super ( WSDL_LOCATION, SERVICE );
	}

	public MdpiuvWSInterface_Service ( WebServiceFeature... features ) {
		super ( WSDL_LOCATION, SERVICE, features );
	}

	public MdpiuvWSInterface_Service ( URL wsdlLocation, WebServiceFeature... features ) {
		super ( wsdlLocation, SERVICE, features );
	}

	public MdpiuvWSInterface_Service ( URL wsdlLocation, QName serviceName, WebServiceFeature... features ) {
		super ( wsdlLocation, serviceName, features );
	}

	/**
	 * @return returns MdpiuvWSInterface
	 */
	@WebEndpoint ( name = "MdpiuvWSPort" )
	public MdpiuvWSInterface getMdpiuvWSPort () {
		return super.getPort ( MdpiuvWSPort, MdpiuvWSInterface.class );
	}

	/**
	 * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
	 * @return returns MdpiuvWSInterface
	 */
	@WebEndpoint ( name = "MdpiuvWSPort" )
	public MdpiuvWSInterface getMdpiuvWSPort ( WebServiceFeature... features ) {
		return super.getPort ( MdpiuvWSPort, MdpiuvWSInterface.class, features );
	}

}