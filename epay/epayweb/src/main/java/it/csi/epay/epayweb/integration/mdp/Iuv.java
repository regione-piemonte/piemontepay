/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.integration.mdp;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.mdpiuv.CSIException_Exception;
import it.csi.mdpiuv.MdpIuvSrvException_Exception;
import it.csi.mdpiuv.MdpiuvWSInterface;
import it.csi.mdpiuv.MdpiuvWSInterface_Service;
import it.csi.mdpiuv.SOAPException_Exception;
import it.csi.mdpiuv.SystemException_Exception;
import it.csi.mdpiuv.UnrecoverableException_Exception;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Iuv {
	
	@Value("${mdpIuv.url.wsdl}")
	private String url;
	
	@Value("${mdpIuv.apiManager.flag:false}")
	private String flagApiManager;

	@Value("${mdpIuv.apiManager.oAuthUrl:''}")
	private String oAuthUrl; 				
		
	@Value("${mdpIuv.apiManager.consumerKey:''}")
	private String consumerKey;
		
	@Value("${mdpIuv.apiManager.consumerSecret:''}")
	private String consumerSecret;
		
	@Value("${mdpIuv.apiManager.endPoint:''}")
	private String endPoint;
		
	private MdpiuvWSInterface mdpiuvWSInterface;
	
	@PostConstruct
	public void initialization() throws MalformedURLException, ClassNotFoundException {
		if (flagApiManager.equals("true")) {
			URL wsdlLocation = Iuv.class.getClassLoader().getResource("wsdl/MdpiuvsrvServiceWS.wsdl");
			MdpiuvWSInterface_Service service = new MdpiuvWSInterface_Service(wsdlLocation);
			MdpiuvWSInterface mdpIuvWS = service.getMdpiuvWSPort();
			
			TokenRetryManager trm = new TokenRetryManager();
			trm.setOauthHelper(new OauthHelper(oAuthUrl,  consumerKey, consumerSecret));
			trm.setWsProvider(new CxfImpl());
			
			GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();			                
			gwfb.setEndPoint(endPoint);
			gwfb.setWrappedInterface(MdpiuvWSInterface.class);
			gwfb.setPort(mdpIuvWS);
			gwfb.setTokenRetryManager(trm);
			
			mdpiuvWSInterface = (MdpiuvWSInterface)gwfb.create();
		} else {
			URL urlService = new URL(url);
			MdpiuvWSInterface_Service service = new MdpiuvWSInterface_Service(urlService);
			MdpiuvWSInterface mdpIuvWS = service.getMdpiuvWSPort();
			
			mdpiuvWSInterface = mdpIuvWS;
		}
	}

	public String generateNewIuv(final TipoPagamento tipoPagamento) throws MdpException{
		try {
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call mdpiuvWSInterface.getSingleIUV");
			System.out.println("     Param IdApplicazione        : " + tipoPagamento.getIdApplicazione());
			System.out.println("     Param Codice Versamento     : " + tipoPagamento.getCodiceVersamento());
			String iuv = mdpiuvWSInterface.getSingleIUV(tipoPagamento.getIdApplicazione(), tipoPagamento.getCodiceVersamento());
			System.out.println("Response mdpiuvWSInterface.getSingleIUV : " + iuv);
			System.out.println("--------------------------------------------------------------------------------");

			return iuv;
		} catch (CSIException_Exception | MdpIuvSrvException_Exception | SOAPException_Exception
				| SystemException_Exception | UnrecoverableException_Exception e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione getSingleIUV", e);
		}
	}
	
	public String generateNewIuv(final TipoPagamento tipoPagamento, int numberTry) throws MdpException {
		try {
			return generateNewIuv(tipoPagamento);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return generateNewIuv(tipoPagamento, numberTry);
		}
	}	
	
	
	public static void main(String[] args) {
		try {
			URL urlService;
			try {
				urlService = new URL("http://tst-applogic.reteunitaria.piemonte.it/mdpiuv/MdpiuvsrvServiceWS?wsdl");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return ;
			}
			MdpiuvWSInterface_Service service = new MdpiuvWSInterface_Service(urlService);
			MdpiuvWSInterface mdpIuvWS = service.getMdpiuvWSPort();
			
			String iuv = mdpIuvWS.getSingleIUV("EPAY_RP", "test");
			System.out.println("Codice Iuv generato : >" + iuv + "<");
		} catch (CSIException_Exception | MdpIuvSrvException_Exception | SOAPException_Exception
				| SystemException_Exception | UnrecoverableException_Exception e) {
			e.printStackTrace();
			throw new MdpException("Errore invocazione getSingleIUV", e);
		}
	}
}
