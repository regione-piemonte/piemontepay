/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.impl;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import it.csi.epay.epaybeapi.api.ComponentiImportoApi;
import it.csi.epay.epaybeapi.api.util.SpringSupportedResource;
import it.csi.epay.epaybeapi.business.EpayServicesClientService;
import it.csi.epay.epayservices.model.ComponentiImportoInput;
import it.csi.epay.epayservices.model.ComponentiImportoOutput;


/**
 * Risorsa RestEasy che fornisce le implementazioni dei servizi che agiscono sui componenti dell'importo del pagamento.
 *
 * Nota: questa risorsa vale anche come esempio per l'introduzione al supporto di InitializingBean
 */
@SpringSupportedResource
public class ComponentiImportoApiImpl implements ComponentiImportoApi {

    private static Logger logger = LoggerFactory.getLogger ( ComponentiImportoApi.class );

    @Autowired
    private EpayServicesClientService service;

    @Override
    public Response componentiImporto ( String codiceFiscaleEnte,
     String codiceVersamento,
	 String iuvDatiSingoloPagamento,
	 String importoDatiSingoloPagamento,
	 String transactionId,
	 String datiSpecificiRiscossione,
	 String esitoPagamento,
	 String anagraficaPagatore,
	 String codiceFiscalePagatore ) {

    	logger.info("Mapping input for underlying EJB");
    	ComponentiImportoInput componentiImportoInput = new ComponentiImportoInput();
    	componentiImportoInput.setCodiceFiscaleEnte(codiceFiscaleEnte);
    	componentiImportoInput.setCodiceVersamento(codiceVersamento);
    	componentiImportoInput.setIuvDatiSingoloPagamento(iuvDatiSingoloPagamento);
    	componentiImportoInput.setImportoDatiSingoloPagamento(importoDatiSingoloPagamento);
    	componentiImportoInput.setTransactionId(transactionId);
    	componentiImportoInput.setDatiSpecificiRiscossione(datiSpecificiRiscossione);
    	componentiImportoInput.setEsitoPagamento(esitoPagamento);
    	componentiImportoInput.setAnagraficaPagatore(anagraficaPagatore);
    	componentiImportoInput.setCodiceFiscalePagatore(codiceFiscalePagatore);
        ComponentiImportoOutput result = service.estraiComponentiImporto ( componentiImportoInput );
        logger.info("Returning response");
        return Response
        		.status(HttpStatus.OK.value()) 
            .status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                            : result.getCodiceEsito ().equals ( "400" ) ? 400 : 500 )
            .entity ( result ).build ();
    }

}
