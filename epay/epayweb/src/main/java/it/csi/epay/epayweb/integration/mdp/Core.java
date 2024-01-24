/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.integration.mdp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayweb.utilities.XmlUtil;
import it.csi.mdpcore.AppGateway;
import it.csi.mdpcore.AppGatewayInformativa;
import it.csi.mdpcore.BusinessException_Exception;
import it.csi.mdpcore.CSIException_Exception;
import it.csi.mdpcore.CreateException_Exception;
import it.csi.mdpcore.DaoException_Exception;
import it.csi.mdpcore.DatatypeConfigurationException_Exception;
import it.csi.mdpcore.ElencoRPT;
import it.csi.mdpcore.IMdpCoreCxf;
import it.csi.mdpcore.InformativePSPScelto;
import it.csi.mdpcore.MdpCoreCxfService;
import it.csi.mdpcore.MissingParameterException_Exception;
import it.csi.mdpcore.NamingException_Exception;
import it.csi.mdpcore.ParametriUrlWisp;
import it.csi.mdpcore.SOAPFaultException_Exception;
import it.csi.mdpcore.SystemException_Exception;
import it.csi.mdpcore.Transazione;
import it.csi.mdpcore.TransazioneExtraAttribute;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Core {

	@SuppressWarnings("unused")
	private static final String URL_WISP_SI = "SI";
	private static final String URL_WISP_NO = "NO";
	
	@Value("${mdpCore.url.wsdl}")
	private String url;
	
	@Value("${mdpCore.apiManager.flag:false}")
	private Boolean flagApiManager;
	
	@Value("${mdpCore.apiManager.oAuthUrl:''}")
	private String oAuthUrl; 				
		
	@Value("${mdpCore.apiManager.consumerKey:''}")
	private String consumerKey;
		
	@Value("${mdpCore.apiManager.consumerSecret:''}")
	private String consumerSecret;
		
	@Value("${mdpCore.apiManager.endPoint:''}")
	private String endPoint;
		
	private IMdpCoreCxf iMdpCoreCxf;
	
	@PostConstruct
	public void initialization() throws MalformedURLException, ClassNotFoundException {
		if (flagApiManager) {
			URL wsdlLocation = Iuv.class.getClassLoader().getResource("wsdl/MdpCoreCxf.wsdl");
			MdpCoreCxfService service = new MdpCoreCxfService(wsdlLocation);
			IMdpCoreCxf mdpCoreWS = service.getMdpCoreCxfPort();
			
			TokenRetryManager trm = new TokenRetryManager();
			trm.setOauthHelper(new OauthHelper(oAuthUrl,  consumerKey, consumerSecret));
			trm.setWsProvider(new CxfImpl());
			
			GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();			                
			gwfb.setEndPoint(endPoint);
			gwfb.setWrappedInterface(IMdpCoreCxf.class);
			gwfb.setPort(mdpCoreWS);
			gwfb.setTokenRetryManager(trm);
			
			iMdpCoreCxf = (IMdpCoreCxf) gwfb.create();
		} else {
			URL urlService = new URL(url);
			MdpCoreCxfService service = new MdpCoreCxfService(urlService);
			IMdpCoreCxf mdpCoreWS = service.getMdpCoreCxfPort();
			
			iMdpCoreCxf = mdpCoreWS;
		}
	}
	
	public Transazione initializzaTransazione(final Pagamento pagamento) throws MdpException {
		try {
			String appId = pagamento.getTipoPagamento().getIdApplicazione();
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call iMdpCoreCxf.initTransazione");
			System.out.println("     Param IdApplicazione        : " + appId);
			Transazione transazione = iMdpCoreCxf.initTransazione(appId, null);
			System.out.println("Response iMdpCoreCxf.initTransazione : ");
			System.out.println(XmlUtil.obj2Xml(transazione));
			System.out.println("--------------------------------------------------------------------------------");
			
			return transazione;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Un elemento non e' valorizzato correttamente", e);
		} catch (CSIException_Exception | CreateException_Exception | DaoException_Exception
				| MissingParameterException_Exception | NamingException_Exception 
				| DatatypeConfigurationException_Exception | SOAPFaultException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione initTransazione", e);
		}
	}
	
	public Transazione initializzaTransazione(final Pagamento pagamento, int numberTry) throws MdpException {
		try {
			return initializzaTransazione(pagamento);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return initializzaTransazione(pagamento, numberTry);
		}
	}	

	public Transazione recuperaTransazione(final String transactionId) throws MdpException {
		try {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call iMdpCoreCxf.getStatoTransazione");
			System.out.println("     Param transactionId        : " + transactionId);
			Transazione transazione = iMdpCoreCxf.getStatoTransazione(transactionId);
			System.out.println("Response iMdpCoreCxf.initTransazione : ");
			System.out.println(XmlUtil.obj2Xml(transazione));
			System.out.println("--------------------------------------------------------------------------------");
			
			return transazione;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Un elemento non e' valorizzato correttamente", e);
		} catch (CSIException_Exception | CreateException_Exception | DaoException_Exception
				| MissingParameterException_Exception | NamingException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione initTransazione", e);
		}
	}
	
	public Transazione recuperaTransazione(final String transactionId, int numberTry) throws MdpException {
		try {
			return recuperaTransazione(transactionId);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return recuperaTransazione(transactionId, numberTry);
		}
	}	
	
	public String urlSceltaPsp(final Pagamento pagamento, final Transazione transazione, final URL urlBack, final URL urlNext) throws MdpException {
		try {
			String applicationId = pagamento.getTipoPagamento().getIdApplicazione();
			ParametriUrlWisp parametriUrlWisp = new ParametriUrlWisp();
			parametriUrlWisp.setUrlBackWisp(urlBack.toString());
			parametriUrlWisp.setUrlReturnWisp(urlNext.toString());
			parametriUrlWisp.setBolloDigitale(URL_WISP_NO);
			parametriUrlWisp.setStornoPagamento(URL_WISP_NO);
			parametriUrlWisp.setTerzoModelloPagamento(URL_WISP_NO);
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call iMdpCoreCxf.getUrlWisp");
			System.out.println("     Param IdApplicazione        : " + applicationId);
			System.out.println("     Param transazione           : ");
			System.out.println(XmlUtil.obj2Xml(transazione));
			System.out.println("     Param parametriUrlWisp      : ");
			System.out.println(XmlUtil.obj2Xml(parametriUrlWisp));
			String urlWisp = iMdpCoreCxf.getUrlWisp(applicationId, transazione, parametriUrlWisp, null);
			System.out.println("Response iMdpCoreCxf.getUrlWisp  : " + urlWisp);
			System.out.println("--------------------------------------------------------------------------------");
		
			return urlWisp;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Un elemento non e' valorizzato correttamente", e);
		} catch (CSIException_Exception | CreateException_Exception | DaoException_Exception
				| MissingParameterException_Exception | NamingException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione getUrlWisp", e);
		}
	}
	
	public String urlSceltaPsp(final Pagamento pagamento, final Transazione transazione, final URL urlBack, final URL urlNext, int numberTry) throws MdpException {
		try {
			return urlSceltaPsp(pagamento, transazione, urlBack, urlNext);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return urlSceltaPsp(pagamento, transazione, urlBack, urlNext, numberTry);
		}
	}
	
	public InformativePSPScelto datiPspScelto(final String idApplicazione, final String idTransazione) throws MdpException {
		try {
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call iMdpCoreCxf.getSceltaWisp");
			System.out.println("     Param IdApplicazione        : " + idApplicazione);
			System.out.println("     Param Codice Versamento     : " + idTransazione);
			InformativePSPScelto informativa = iMdpCoreCxf.getSceltaWisp(idApplicazione, idTransazione);
			System.out.println("Response iMdpCoreCxf.getSceltaWisp : ");
			System.out.println(XmlUtil.obj2Xml(informativa));
			System.out.println("--------------------------------------------------------------------------------");
			
			return informativa;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Un elemento non e' valorizzato correttamente", e);
		} catch (CSIException_Exception | CreateException_Exception | DaoException_Exception
				| MissingParameterException_Exception | NamingException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione getSceltaWisp", e);
		}
	}
	
	public InformativePSPScelto datiPspScelto(final String idApplicazione, final String idTransazione, int numberTry) throws MdpException {
		try {
			return datiPspScelto(idApplicazione, idTransazione);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return datiPspScelto(idApplicazione, idTransazione, numberTry);
		}
	}
	
	public List<AppGatewayInformativa> modalitaInformativePagamento(final String idApplicazione, Transazione transazione) {
		try {
			List<AppGatewayInformativa> infos = iMdpCoreCxf.getModalitaInformativePagamento(transazione, idApplicazione);
			return infos;
		} catch (CSIException_Exception | CreateException_Exception | DaoException_Exception
				| MissingParameterException_Exception | NamingException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione getModalitaInformativePagamento", e);
		}		
	}
	
	public List<AppGatewayInformativa> modalitaInformativePagamento(final String idApplicazione, Transazione transazione, int numberTry) throws MdpException {
		try {
			return modalitaInformativePagamento(idApplicazione, transazione);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return modalitaInformativePagamento(idApplicazione, transazione, numberTry);
		}
	}	
	
	public String paymentURL(final Transazione transazione, final List<TransazioneExtraAttribute> transazioneExtraAttributeList) throws MdpException {
		try {
			transazione.setCcy("EUR");
			transazione.setLanguage("ITA");
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call iMdpCoreCxf.startTransazione");
			System.out.println("     Param transazione : ");
			System.out.println(XmlUtil.obj2Xml(transazione));
			System.out.println("     Param transazioneExtraAttributeList : ");
			System.out.println(XmlUtil.obj2Xml(transazioneExtraAttributeList));
			String urlTransazione = iMdpCoreCxf.startTransazione(transazione, transazioneExtraAttributeList);
			System.out.println("Response iMdpCoreCxf.startTransazione : " + urlTransazione);
			System.out.println("--------------------------------------------------------------------------------");
			
			return urlTransazione;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Un elemento non e' valorizzato correttamente", e);
		} catch (CSIException_Exception | CreateException_Exception | DaoException_Exception
				| MissingParameterException_Exception | NamingException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione startTransazione", e);
		}
	}
	
	public String paymentURL(final Transazione transazione, final List<TransazioneExtraAttribute> transazioneExtraAttributeList, int numberTry) throws MdpException {
		try {
			return paymentURL(transazione, transazioneExtraAttributeList);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return paymentURL(transazione, transazioneExtraAttributeList, numberTry);
		}
	}
	
	public String paymentURL(final Transazione transazione, final ElencoRPT elencoRPT, Boolean multiBeneficiario) throws MdpException {
		try {
			transazione.setCcy("EUR");
			transazione.setLanguage("ITA");
			
			List<AppGateway> appGatewayList = iMdpCoreCxf.getModalitaPagamento(transazione, transazione.getApplicationId());
			if (appGatewayList.isEmpty()) {
				throw new Exception("Nessun gateway configurato.");
			}
			if (appGatewayList.size() != 1) {
				throw new Exception("Trovati piu' di un gateway configurato.");
			}
			AppGateway appGateway = appGatewayList.get(0);
			transazione.setGatewayId(appGateway.getGatewayId());
			transazione.setGatewaypaymodeid(appGateway.getPaymentmodeId());
						
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call iMdpCoreCxf.startTransazione");
			System.out.println("     Param transazione : ");
			System.out.println(XmlUtil.obj2Xml(transazione));
			System.out.println("     Param elencoRPT : ");
			System.out.println(XmlUtil.obj2Xml(elencoRPT));
            System.out.println ( "     Param multiBeneficiario : " );
            System.out.println ( multiBeneficiario );
			String urlTransazione = iMdpCoreCxf.startTransazioneCarrello(transazione, null, elencoRPT, multiBeneficiario);
			System.out.println("Response iMdpCoreCxf.startTransazione : " + urlTransazione);
			System.out.println("--------------------------------------------------------------------------------");
			
			return urlTransazione;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Un elemento non e' valorizzato correttamente", e);
		} catch (CSIException_Exception | CreateException_Exception | DaoException_Exception
				| MissingParameterException_Exception | NamingException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione getModalitaPagamento", e);
		} catch (BusinessException_Exception | SystemException_Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione startTransazioneCarrello", e);
		} catch (Exception e) {
			System.out.println("Responce error : " + e.getMessage());
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore getModalitaPagamento/startTransazioneCarrello", e);
		}
	}	
	
	public String paymentURL(final Transazione transazione, final ElencoRPT elencoRPT, int numberTry, Boolean multiBeneficiario) throws MdpException {
		try {
			return paymentURL(transazione, elencoRPT, multiBeneficiario);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return paymentURL(transazione, elencoRPT, numberTry, multiBeneficiario);
		}
	}

	/*
	 * public static void main(String[] args) { MdpiuvWSInterface_Service
	 * service = new MdpiuvWSInterface_Service(); MdpiuvWSInterface mdpIuvWS =
	 * service.getMdpiuvWSPort(); try { String iuv =
	 * mdpIuvWS.getSingleIUV("EPAY_RP", "TEST"); System.out.println(
	 * "Codice Iuv generato : >" + iuv + "<"); } catch (CSIException_Exception |
	 * MdpIuvSrvException_Exception | SOAPException_Exception |
	 * SystemException_Exception | UnrecoverableException_Exception e) { throw
	 * new mdpException(e); } }
	 */
	
}
