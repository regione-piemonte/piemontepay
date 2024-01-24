/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.integration.mdp;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayweb.utilities.XmlUtil;
import it.csi.mdpmultiiuv.ErrorParameterException_Exception;
import it.csi.mdpmultiiuv.IuvComplex;
import it.csi.mdpmultiiuv.IuvComplexResponse;
import it.csi.mdpmultiiuv.MdpMultiIuvSrvException_Exception;
import it.csi.mdpmultiiuv.MdpMultiIuvWSInterface;
import it.csi.mdpmultiiuv.MdpMultiIuvWSInterface_Service;
import it.csi.mdpmultiiuv.MissingParameterException_Exception;
import it.csi.mdpmultiiuv.SOAPException_Exception;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MultiIuv {
	
	private final static int NUMEROOCCORRENZEIUV = 1;
	
	@Value("${mdpMultiIuv.url.wsdl}")
	private String url;
	
	@Value("${mdpMultiIuv.apiManager.flag:false}")
	private String flagApiManager;

	@Value("${mdpMultiIuv.apiManager.oAuthUrl:''}")
	private String oAuthUrl; 				
		
	@Value("${mdpMultiIuv.apiManager.consumerKey:''}")
	private String consumerKey;
		
	@Value("${mdpMultiIuv.apiManager.consumerSecret:''}")
	private String consumerSecret;
		
	@Value("${mdpMultiIuv.apiManager.endPoint:''}")
	private String endPoint;
	
	private MdpMultiIuvWSInterface mdpMultiIuvWSInterface;
		
	@PostConstruct
	public void initialization() throws MalformedURLException, ClassNotFoundException {
		if (flagApiManager.equals("true")) {
			URL wsdlLocation = MultiIuv.class.getClassLoader().getResource("wsdl/MdpMultiIuvsrvServiceWS.wsdl");
			MdpMultiIuvWSInterface_Service service = new MdpMultiIuvWSInterface_Service(wsdlLocation);
			MdpMultiIuvWSInterface mdpMultiIuvWS = service.getMdpMultiIuvWSPort();
			
			TokenRetryManager trm = new TokenRetryManager();
			trm.setOauthHelper(new OauthHelper(oAuthUrl,  consumerKey, consumerSecret));
			trm.setWsProvider(new CxfImpl());
			
			GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();			                
			gwfb.setEndPoint(endPoint);
			gwfb.setWrappedInterface(MdpMultiIuvWSInterface.class);
			gwfb.setPort(mdpMultiIuvWS);
			gwfb.setTokenRetryManager(trm);
			
			mdpMultiIuvWSInterface = (MdpMultiIuvWSInterface)gwfb.create();
		} else {
			URL urlService = new URL(url);
			MdpMultiIuvWSInterface_Service service = new MdpMultiIuvWSInterface_Service(urlService);
			MdpMultiIuvWSInterface mdpIuvWS = service.getMdpMultiIuvWSPort();
			
			mdpMultiIuvWSInterface = mdpIuvWS;
		}
	}

	public IuvComplex generateNewIuv(final TipoPagamento tipoPagamento, final String passphrase) throws MdpException{
		try {
			String timestamp = getTimestamp();
			String mac = getMac(tipoPagamento.getIdApplicazione(), tipoPagamento.getCodiceVersamento(), passphrase, timestamp);
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call mdpMultiIuvWSInterface.getIuvComplex");
			System.out.println("     Param IdApplicazione        : " + tipoPagamento.getIdApplicazione());
			System.out.println("     Param Codice Versamento     : " + tipoPagamento.getCodiceVersamento());
			System.out.println("     Param numero occorrenze iuv : " + NUMEROOCCORRENZEIUV);
			System.out.println("     Param timestamp             : " + timestamp);
			System.out.println("     Param mac                   : " + mac);
			IuvComplexResponse response = mdpMultiIuvWSInterface.getIuvComplex(tipoPagamento.getIdApplicazione(), tipoPagamento.getCodiceVersamento(), NUMEROOCCORRENZEIUV, timestamp, mac);
			System.out.println("Response mdpMultiIuvWSInterface.getIuvComplex :");
			System.out.println(XmlUtil.obj2Xml(response));
			System.out.println("--------------------------------------------------------------------------------");
			
			IuvComplex iuvComplex = response.getIuvComplex().get(0);
			return iuvComplex;
		} catch (ErrorParameterException_Exception | MdpMultiIuvSrvException_Exception | MissingParameterException_Exception | SOAPException_Exception e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione getSingleIUV", e);
		}
	}
	
	public IuvComplex generateNewIuv(final TipoPagamento tipoPagamento, final String passphrase, int numberTry) throws MdpException {
		try {
			return generateNewIuv(tipoPagamento, passphrase);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return generateNewIuv(tipoPagamento, passphrase, numberTry);
		}
	}	
	
	private static String getTimestamp() {
		Date today = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-hh:mm:ss:SSS");
        String timestamp = dateFormat.format(today);
		return timestamp;
	}
	
	private static String getMac(final String  applicationId, final String codiceVersamento, final String passphrase, final String timestamp) {
		String sToDigest = passphrase + "%%%%" + codiceVersamento + applicationId + "%%%%" + timestamp + passphrase;
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		String mac = Base64.encodeBase64String(bMac).substring(0, 35);
		return mac;
	}
	
	public static void main(String[] args) {
		try {
			URL urlService;
			try {
				urlService = new URL("http://tst-applogic.reteunitaria.piemonte.it/mdpmultiiuv/MdpMultiIuvsrvServiceWS?wsdl");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return ;
			}
			MdpMultiIuvWSInterface_Service service = new MdpMultiIuvWSInterface_Service(urlService);
			MdpMultiIuvWSInterface mdpMultiIuvWS = service.getMdpMultiIuvWSPort();
			
			String passphrase = "N3lm3zz0d3lcammindin0stravitamiritr0vaiP3runas3lva0scurach3ladirittavia3rasmarrita";
			String timestamp = getTimestamp();
			String mac = getMac("EPAY_RP", "test", passphrase, timestamp);
			IuvComplexResponse response = mdpMultiIuvWS.getIuvComplex("EPAY_RP", "test", 1, timestamp, mac);
			IuvComplex iuvComplex = response.getIuvComplex().get(0);
			System.out.println("Aux Digit generato : >" + iuvComplex.getAuxDigit() + "<");
			System.out.println("Application Codee generato : >" + iuvComplex.getApplicationCode() + "<");
			System.out.println("Iuv Ottico generato : >" + iuvComplex.getIuvOttico() + "<");
			
		} catch (ErrorParameterException_Exception | MdpMultiIuvSrvException_Exception | MissingParameterException_Exception | SOAPException_Exception e) {
			e.printStackTrace();
			throw new MdpException("Errore invocazione getSingleIUV", e);
		}
	}
}
