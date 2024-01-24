/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.facade;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.util.wsdl.epaywso.EPaywsoBusinessService;
import it.csi.epay.epaymodric.util.wsdl.epaywso.EPaywsoBusinessServiceImplService;
import it.csi.epay.epaymodric.util.wsdl.epaywso.RicercaStatiAggregatiWsoRequestType;
import it.csi.epay.epaymodric.util.wsdl.epaywso.RicercaStatiAggregatiWsoResponseType;

@Service
public class EpaywsoServicesFacade {
	
	private EPaywsoBusinessServiceImplService service;
	
	private EPaywsoBusinessService port;
	
	public EpaywsoServicesFacade() {
		service = null;
		port = null;
	}
	
	private EPaywsoBusinessService getPort() {
		if(port == null) {
			try { 
				
				String endPointUrl = java.util.ResourceBundle.getBundle("config")
                        .getString ( "service.wso.endpoint" );
				try {
				service = new EPaywsoBusinessServiceImplService(new URL ( endPointUrl ));
				}catch(MalformedURLException e) {
					throw new RuntimeException(e);
				}
				port = service.getEPaywsoBusinessServiceSOAP();
				
				BindingProvider bp = (BindingProvider) port;
				
				Map<String, Object> context = bp.getRequestContext();
				context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointUrl);
				
				Client client = ClientProxy.getClient ( port );
	            client.getOutInterceptors ().add ( new LoggingInInterceptor () );
	            client.getOutInterceptors ().add ( new LoggingOutInterceptor () );
	            
	            return port;
				
			}catch(Exception e) {
				service = null;
                port = null;
                throw new RuntimeException("Error contacting remote service", e);
			}
		}
		else {
			return port;
		}
	}
	
	public RicercaStatiAggregatiWsoResponseType ricercaStatiAggregatiWso (RicercaStatiAggregatiWsoRequestType input) {
		return getPort().ricercaStatiAggregatiWso(input);
	}

}
