/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtListaInformativePSP;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.SSLUtils;
import it.csi.mdp.mdpetl.util.Utility;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediInformativaPSP;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediInformativaPSPRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.xml.sax.SAXException;

public class InvocaServizio {

	static LogUtil log = new LogUtil(InvocaServizio.class);

	public CtListaInformativePSP elabora(String endPointUrl,
											String azione,
											String identificativoDominio,
											String identificativoIntermediarioPA,
											String identificativoStazioneIntermediarioPA,
											String password,
											int numeroRetry, boolean gadEnabled) throws Exception {
		String methodName="elabora";
		log.startMethod(methodName);
		
		//log.info(methodName, "azione= " + azione + " identificativoDominio=" + identificativoDominio + 
//				              " identificativoIntermediarioPA=" + identificativoIntermediarioPA +
//				              " identificativoStazioneIntermediarioPA=" + identificativoStazioneIntermediarioPA +
//				              " password=" + password);
		
		JaxWsProxyFactoryBean factory = inizializzaServizio(endPointUrl,azione);
		CtListaInformativePSP ris = creaListaInfo (factory,identificativoDominio,identificativoIntermediarioPA,identificativoStazioneIntermediarioPA,password, numeroRetry, gadEnabled);
		
		log.stopMethod(methodName);
		return ris;
	}

	private JaxWsProxyFactoryBean inizializzaServizio(String endponitURL, String azione) {
		String methodName="inizializzaServizio";
		log.startMethod(methodName);
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(PagamentiTelematiciRPT.class);
		//log.info(methodName, " url " + endponitURL+azione);
		factory.setAddress(endponitURL+"/"+azione);
		
		log.stopMethod(methodName);
		return factory;
	}

	private CtListaInformativePSP creaListaInfo (JaxWsProxyFactoryBean factory,
													String identificativoDominio,
													String identificativoIntermediarioPA,
													String identificativoStazioneIntermediarioPA,
													String password,
													int numeroRetry, boolean gadEnabled) throws Exception {

		String methodName="creaListaInfo";
		log.startMethod(methodName);
		
		PagamentiTelematiciRPT pagamentiTelematiciService = (PagamentiTelematiciRPT)factory.create();
		
		if(gadEnabled){
			SSLUtils.setTLSContext(pagamentiTelematiciService);
			log.debug("creaListaInfo", "********************   setTSLContex effettuato  ***************************");
		}
			
		
		NodoChiediInformativaPSP bodyrichiesta = new NodoChiediInformativaPSP();
		bodyrichiesta.setIdentificativoDominio(identificativoDominio);
		bodyrichiesta.setIdentificativoIntermediarioPA(identificativoIntermediarioPA);
		bodyrichiesta.setIdentificativoStazioneIntermediarioPA(identificativoStazioneIntermediarioPA);
		bodyrichiesta.setPassword(password);    
		//fine settaggio NodoChiediInformativaPSP
		NodoChiediInformativaPSPRisposta risposta = tentativiInformativaPSP(methodName, pagamentiTelematiciService, bodyrichiesta, numeroRetry);
		
		CtListaInformativePSP listaInformativePSP = null;
		JAXBContext jContext = JAXBContext.newInstance(CtListaInformativePSP.class);
		Unmarshaller unmarshaller = jContext.createUnmarshaller();
		
//		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
//		Source so = new StreamSource(this.getClass().getResourceAsStream("/CatalogoDatiInformativi_1_0_8.xsd"));
//		Schema s = sf.newSchema(so);
//		unmarshaller.setSchema(s);
		
		if (risposta.getXmlInformativa()!= null){
			log.info(methodName, Utility.convertStreamToString(risposta.getXmlInformativa().getInputStream()));
			
			// se l'xml non e' valido mostra l'errore
//			unmarshaller.setEventHandler(new ValidationEventHandler() {public boolean handleEvent(ValidationEvent event ) {throw new RuntimeException(event.getMessage(),event.getLinkedException());}});
			
			listaInformativePSP = (CtListaInformativePSP)unmarshaller.unmarshal(risposta.getXmlInformativa().getInputStream());
			
			log.debug(methodName , Utility.fieldsToString(listaInformativePSP));
		}
		else
			log.error(methodName, "Informativa PSP non acquisita");
		
		
		log.stopMethod(methodName);
		return listaInformativePSP;
	}

	private NodoChiediInformativaPSPRisposta tentativiInformativaPSP(String methodName, PagamentiTelematiciRPT pagamentiTelematiciService, NodoChiediInformativaPSP bodyrichiesta, int numeroRetry) {
		
		NodoChiediInformativaPSPRisposta risposta = null;
		
		for (int i=1;i<=numeroRetry;i++) {
			try {
				risposta =  pagamentiTelematiciService.nodoChiediInformativaPSP(bodyrichiesta);
				break;//Rompo il retry se andato bene
			} catch (Exception e) {
				log.error(methodName, "INVOCAZIONE nodoChiediInformativaPSP  NUMERO " + i + " FALLITA, RITENTO ", e);
			}
			try {
				Thread.sleep(3000);//min 3 secondi tra retry
			} catch (InterruptedException e) {
				log.debug(methodName, "interrotto");
			}
		}
		return risposta;
	}
}
