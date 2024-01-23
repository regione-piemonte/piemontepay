/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.adapters.business;

import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class CoreUtilities {
	
	/**
	 * Costruisce un map dall'array degli acf
	 * @param acf
	 * @return
	 */
	public static Map<String, String> costruisciMappaApplicationCustomFields(Applicationcustomfields[] acf) {
		Map<String, String> mappa = new HashMap<String, String>(acf.length);
		
		for (Applicationcustomfields c : acf) {
			mappa.put(c.getFieldname().trim(), StringUtils.trimToNull(c.getFieldvalue()));
		}
		
		return mappa;
	}
	
	
	/**
	 * Costruisce una Map<String, String> a partire dall'array di
	 * TransazioneExtraAttribute
	 * 
	 * @param tea
	 * @return
	 */
	public static Map<String, String> costruisciMappaTransazioneExtraAttributes(TransazioneExtraAttribute[] tea) {

		if (tea == null) 
			return new HashMap<String, String>();
		HashMap<String, String> mapTea = new HashMap<String, String>(tea.length);

		for (TransazioneExtraAttribute attr : tea) {
			if (attr != null)
				mapTea.put(attr.getName(), attr.getValue());
		}

		return mapTea;
	}
	

	/**
	 * Inizializza un servizio esposto tramite SPCoop
	 * @param endponitURL
	 * @param azione che va appeso per l'uso di SPCoop
	 * @return
	 */
	public static JaxWsProxyFactoryBean inizializzaServizio(String endponitURL, String azione) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(PagamentiTelematiciRPT.class);
		factory.setAddress(endponitURL+"/"+azione);
		return factory;
	}
}
