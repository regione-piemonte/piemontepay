/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.enti2epaywso.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import it.csi.epay.epaypaweb.dto.EsitoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.exception.IntegrationException;
import it.csi.epay.epaypaweb.facade.dto.common.ResponseType;
import it.csi.epay.epaypaweb.integration.EPaypaIntegrationServiceBase;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.AggiornaPosizioniDebitorieRequest;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.InserisciListaDiCaricoRequest;
import it.csi.epay.epaypaweb.integration.enti2epaywso.interf.Enti2EPaywsoServiceMgr;
import it.csi.epay.epaypaweb.integration.enti2epaywso.service.Enti2EPaywsoService;


public class Enti2EPaywsoServiceMgrImpl extends EPaypaIntegrationServiceBase implements Enti2EPaywsoServiceMgr {
	static private final String CLASSNAME = Enti2EPaywsoServiceMgrImpl.class.getSimpleName();

	private URL endpointURL;
	private final String TARGET_NAMESPACE_URI = "http://www.csi.it/epay/epaywso/enti2epaywsosrv";
	private final QName QNAME_WS_SERVICE = new QName(TARGET_NAMESPACE_URI, "Enti2EPaywsoService");
	private final QName QNAME_WS_PORT = new QName(TARGET_NAMESPACE_URI, "Enti2EPaywsoServiceSOAP");

	public Enti2EPaywsoServiceMgrImpl(String endpointStr) throws IntegrationException {
		try {
			endpointURL = new URL(endpointStr);

		} catch (MalformedURLException e) {
			throw new IntegrationException(e.getMessage(), e);
		}
	}

	@Override
	public EsitoDto inserisciListaCarico(FlussoCompletoDto<PosizioneDebitoriaDto> flusso) throws IntegrationException {
		String methodName = "inserisciListaCarico";
		
		

		EsitoDto esito = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			InserisciListaDiCaricoRequest request = null;

			if (flusso != null) {
				request = new InserisciListaDiCaricoRequest();
				request.setTestata(toTestaListaCaricoType(flusso.getFlusso()));
				request.setListaDiCarico(toListaDiCaricoType(flusso.getItemList()));
				//
				Enti2EPaywsoService soapService = getSoapService();
				ResponseType response = soapService.inserisciListaDiCarico(request);
				esito = new EsitoDto(response.getResult().getCodice(), response.getResult().getMessaggio());
			}
		} catch (Exception e) {
			throw new IntegrationException(e.getMessage(), e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return esito;
	}

	@Override
	public EsitoDto aggiornaPosizioniDebitorie(FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flusso) throws IntegrationException {
		String methodName = "aggiornaPosizioniDebitorie";
		
		

		EsitoDto esito = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			AggiornaPosizioniDebitorieRequest request = null;

			if (flusso != null) {
				request = new AggiornaPosizioniDebitorieRequest();
				request.setTestata(toTestaAggiornaPosizioniDebitorieType(flusso.getFlusso()));
				request.setElencoPosizioniDaAggiornare(toElencoPosizioniDaAggiornareType(flusso.getItemList()));
				//
				Enti2EPaywsoService soapService = getSoapService();
				ResponseType response = soapService.aggiornaPosizioniDebitorie(request);
				esito = new EsitoDto(response.getResult().getCodice(), response.getResult().getMessaggio());
			}
		} catch (Exception e) {
			throw new IntegrationException(e.getMessage(), e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return esito;
	}

	private Enti2EPaywsoService getSoapService() throws MalformedURLException {
		Service service = Service.create(QNAME_WS_SERVICE);
		service.addPort(QNAME_WS_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointURL.toString());
		return service.getPort(QNAME_WS_PORT, Enti2EPaywsoService.class);
	}

}
