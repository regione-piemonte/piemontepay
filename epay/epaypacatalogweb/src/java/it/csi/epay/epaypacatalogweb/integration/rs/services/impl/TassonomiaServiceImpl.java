/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs.services.impl;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogweb.integration.rs.ElencoTassonomiaPerCodiceTipoEnteCreditoreInput;
import it.csi.epay.epaypacatalogweb.integration.rs.ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.EpaypacatalogsrvRsClient;
import it.csi.epay.epaypacatalogweb.integration.rs.RicercaTassonomiaInput;
import it.csi.epay.epaypacatalogweb.integration.rs.RicercaTassonomiaOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.services.IService;
import it.csi.epay.epaypacatalogweb.integration.rs.services.TassonomiaService;
import it.csi.epay.epaypacatalogweb.util.ServiceRestUtils;


/**
 * 
 */
@Service
@Transactional
public class TassonomiaServiceImpl implements TassonomiaService, IService {
	
	
	private Logger logger = LoggerFactory.getLogger ( TassonomiaService.class );
	  String url = java.util.ResourceBundle.getBundle("config")
              .getString("service.rest.epaypacatalogwebws.endpoint");
	
//	String url= "http://localhost:8080/epaypacatalogsrvApplEpaypacatalogsrvWs/api/getElencoTassonomiaPerCodiceTipoEnteCreditore";
	

	@Override
	public ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput getElencoTassonomiaPerCodiceTipoEnteCreditore(ElencoTassonomiaPerCodiceTipoEnteCreditoreInput input) {
		
		addCallerInfo(input);
		  EpaypacatalogsrvRsClient client = new EpaypacatalogsrvRsClient(url+ServiceRestUtils.ELENCO_TASSONOMIA_PER_CODICE_TIPO_ENTE_CREDITORE);
		  try {
			InputStream is= client.getService(input);
			ObjectMapper mapper = new ObjectMapper();
			ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput response = mapper.readValue(is, ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput.class);
			return response;
			
			
			
		} catch (Exception e) {
			logger.error("errore servizio di tassonomia ", e);
			throw new RuntimeException(e);
		} 
//		  return null;
		
	}

	@Override
	public RicercaTassonomiaOutput ricercaTassonomia(RicercaTassonomiaInput input) {
		addCallerInfo(input);
		  EpaypacatalogsrvRsClient client = new EpaypacatalogsrvRsClient(url+ServiceRestUtils.RICERCA_TASSONOMIA);
		  try {
			InputStream is= client.getService(input);
			ObjectMapper mapper = new ObjectMapper();
			RicercaTassonomiaOutput response = mapper.readValue(is, RicercaTassonomiaOutput.class);
			return response;
			
			
			
		} catch (Exception e) {
			logger.error("errore servizio ricerca tassonomia ", e);
			throw new RuntimeException(e);
		} 
	}
	
	
	
	

	


}
