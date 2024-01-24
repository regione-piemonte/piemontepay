/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.XmlUtil;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.AggiornaPosizioniDebitorieRequest;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.AggiornaPosizioniDebitorieResponse;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.InserisciListaDiCaricoRequest;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.InserisciListaDiCaricoResponse;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.ResultType;
import it.csi.epay.epayservices.ws.business.EPaywso2SportelloService.AggiornaPosizioniDebitorie;
import it.csi.epay.epayservices.ws.business.EPaywso2SportelloService.InserisciListaDiCarico;
import it.csi.epay.epayservices.ws.interfaces.EPaywso2SportelloService;

@Stateless
@WebService(
        targetNamespace="http://www.csi.it/epay/epaywso/enti2epaywsosrv",
        portName="EPaywso2SportelloServiceSOAP",
        serviceName="EPaywso2SportelloService",
        endpointInterface="it.csi.epay.epayservices.ws.interfaces.EPaywso2SportelloService")

public class EPaywso2Sportello implements EPaywso2SportelloService {

	protected LogUtil log = new LogUtil(this.getClass());
	static final private int maxErrorMessageWidth = 200;

	@EJB
	InserisciListaDiCarico inserisciListaDiCarico;

	@EJB
	AggiornaPosizioniDebitorie aggiornaPosizioniDebitorie;

    private Long callSequenceILDC = 0L;

    private Long callSequenceAPD = 0L;

	@Override
    public InserisciListaDiCaricoResponse inserisciListaDiCarico ( InserisciListaDiCaricoRequest parameters ) {

        final Long callId = ( ++callSequenceILDC );
        final String methodName = "inserisciListaDiCaricoWSCall_" + callId;
        log.infoStart ( methodName );

        try {
            return doInserisciListaDiCarico ( callId, parameters );
        } finally {
            log.infoEnd ( methodName );
        }
    }

    private InserisciListaDiCaricoResponse doInserisciListaDiCarico ( Long callId, InserisciListaDiCaricoRequest parameters ) {
        final String methodName = "inserisciListaDiCarico_" + callId;
		log.debugStart(methodName);
		log.debug(methodName, "Request\n : " + XmlUtil.obj2Xml(parameters));

		InserisciListaDiCaricoResponse res = null;
		try {
			 res = inserisciListaDiCarico.exec(parameters);
        } catch ( Throwable t ) {
            log.error ( methodName, "Errore nell'esecuzione del servizio:" );
            log.error ( methodName, t );
			res = new InserisciListaDiCaricoResponse();
			CodiciEsito ce = CodiciEsito.CARICAMENTO_ERRORE;
			ResultType result = new ResultType();
			result.setCodice(ce.getCodice());
			result.setMessaggio(ce.getMessaggio(maxErrorMessageWidth));
			res.setResult(result);
		}

		log.debug(methodName, "Response\n : " + XmlUtil.obj2Xml(res));
		log.debugEnd(methodName);
		return res;
	}

    @Override
    public AggiornaPosizioniDebitorieResponse aggiornaPosizioniDebitorie ( AggiornaPosizioniDebitorieRequest parameters ) {

        final Long callId = ( ++callSequenceAPD );
        final String methodName = "aggiornaPosizioniDebitorieWSCall_" + callId;
        log.infoStart ( methodName );

        try {
            return doAggiornaPosizioniDebitorie ( callId, parameters );
        } finally {
            log.infoEnd ( methodName );
        }
    }

    private AggiornaPosizioniDebitorieResponse doAggiornaPosizioniDebitorie ( Long callId, AggiornaPosizioniDebitorieRequest parameters ) {
        final String methodName = "aggiornaPosizioniDebitorie_" + callId;
		log.debugStart(methodName);
		log.debug(methodName, "Request\n : " + XmlUtil.obj2Xml(parameters));

		AggiornaPosizioniDebitorieResponse res = null;
		try {
			 res = aggiornaPosizioniDebitorie.exec(parameters);
        } catch ( Throwable t ) {
			log.error(methodName, "Errore servizio:");
            log.error ( methodName, t );
			res = new AggiornaPosizioniDebitorieResponse();
			CodiciEsito ce = CodiciEsito.CARICAMENTO_ERRORE;
			ResultType result = new ResultType();
			result.setCodice(ce.getCodice());
			result.setMessaggio(ce.getMessaggio(maxErrorMessageWidth));
			res.setResult(result);
		}
		log.debug(methodName, "Response\n : " + XmlUtil.obj2Xml(res));
		log.debugEnd(methodName);
		return res;
	}



}
