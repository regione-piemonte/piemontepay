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

import it.csi.epay.epaypacatalogweb.integration.rs.EpaypacatalogsrvRsClient;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaNumeroRiferimentiContabiliInVitaPerCovInput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaNumeroRiferimentiContabiliInVitaPerCovOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaPresenzaRiferimentiContabiliOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.services.IService;
import it.csi.epay.epaypacatalogweb.integration.rs.services.RiferimentiContabiliService;
import it.csi.epay.epaypacatalogweb.util.ServiceRestUtils;


/**
 * 
 */
@Service
@Transactional
public class RiferimentiContabiliServiceImpl implements RiferimentiContabiliService, IService {
	
	
	private Logger logger = LoggerFactory.getLogger ( RiferimentiContabiliService.class );
	  String url = java.util.ResourceBundle.getBundle("config")
              .getString("service.rest.epaypacatalogwebws.endpoint");
	  
//		String url= "http://localhost:8080/epaypacatalogsrvApplEpaypacatalogsrvWs/api/getElencoTassonomiaPerCodiceTipoEnteCreditore";
		

	@Override
	public VerificaPresenzaRiferimentiContabiliOutput verificaPresenzaRiferimentiContabili(
			VerificaPresenzaRiferimentiContabiliInput input) {
		
		  addCallerInfo(input);
		  EpaypacatalogsrvRsClient client = new EpaypacatalogsrvRsClient(url+ServiceRestUtils.VERIFICA_PRESENZA_RIFERIMENTI_CONTABILI);
		  try {
			InputStream is= client.getService(input);
			ObjectMapper mapper = new ObjectMapper();
			VerificaPresenzaRiferimentiContabiliOutput response = mapper.readValue(is, VerificaPresenzaRiferimentiContabiliOutput.class);
			return response;
			
			
			
		} catch (Exception e) {
			logger.error("errore servizio di tassonomia ", e);
			throw new RuntimeException(e);
		} 
	}

    @Override
    public VerificaNumeroRiferimentiContabiliInVitaPerCovOutput
    verificaNumeroRiferimentiContabiliInVitaPerCov ( VerificaNumeroRiferimentiContabiliInVitaPerCovInput input ) {
        
        addCallerInfo(input);
        EpaypacatalogsrvRsClient client = new EpaypacatalogsrvRsClient(url+ServiceRestUtils.VERIFICA_NUMERO_RIFERIMENTI_CONTABILI_IN_VITA_PER_COV);
        try {
            InputStream is= client.getService(input);
            ObjectMapper mapper = new ObjectMapper();
            VerificaNumeroRiferimentiContabiliInVitaPerCovOutput response = mapper.readValue(is, VerificaNumeroRiferimentiContabiliInVitaPerCovOutput.class);
            return response;
            
            
            
        } catch (Exception e) {
            logger.error("errore servizio di tassonomia ", e);
            throw new RuntimeException(e);
        } 
    }
	

	

	


}
