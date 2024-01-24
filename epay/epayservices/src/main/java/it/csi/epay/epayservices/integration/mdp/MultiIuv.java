/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.mdp;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import it.csi.epay.epayservices.integration.db.manager.ParametriManager;
import it.csi.epay.epayservices.interfaces.rs.ApiClient;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.XmlUtil;
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

@AccessTimeout(value = 30, unit = TimeUnit.SECONDS)
@Singleton
@Startup
public class MultiIuv {
	
	protected static LogUtil log = new LogUtil ( MultiIuv.class );

	@EJB
	ParametriManager parametriManager;
	
	private MdpMultiIuvWSInterface mdpMultiIuvWSInterface;
	
	private String passphrase;
		
	@PostConstruct
	public void initialization() throws MalformedURLException, ClassNotFoundException {
		
		Properties properties = new Properties();
				
		List<Parametro> listaParametri = parametriManager.elencoPerGruppo("epayservices");
		for (Parametro parametro : listaParametri) {
			properties.put(parametro.getCodice(), parametro.getValore());
		}
		
		passphrase = parametriManager.getParametro("mdp", "passphrase").getValore();
				
		if (((String)properties.get("mdpMultiIuv.apiManager.flag")).equals("true")) {
			//TODO aggiungerlo nel build
			URL wsdlLocation = MultiIuv.class.getClassLoader().getResource("wsdl/MdpMultiIuvsrvServiceWS.wsdl");
			MdpMultiIuvWSInterface_Service service = new MdpMultiIuvWSInterface_Service(wsdlLocation);
			MdpMultiIuvWSInterface mdpMultiIuvWS = service.getMdpMultiIuvWSPort();
			
			TokenRetryManager trm = new TokenRetryManager();
			trm.setOauthHelper(new OauthHelper(
					(String)properties.get("mdpMultiIuv.apiManager.oAuthUrl"),
					(String)properties.get("mdpMultiIuv.apiManager.consumerKey"),
					(String)properties.get("mdpMultiIuv.apiManager.consumerSecret")));
			trm.setWsProvider(new CxfImpl());
			
			GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();			                
			gwfb.setEndPoint((String)properties.get("mdpMultiIuv.apiManager.endPoint"));
			gwfb.setWrappedInterface(MdpMultiIuvWSInterface.class);
			gwfb.setPort(mdpMultiIuvWS);
			gwfb.setTokenRetryManager(trm);
			
			mdpMultiIuvWSInterface = (MdpMultiIuvWSInterface)gwfb.create();
		} else {
			URL urlService = new URL((String)properties.get("mdpMultiIuv.url.wsdl"));
			MdpMultiIuvWSInterface_Service service = new MdpMultiIuvWSInterface_Service(urlService);
			MdpMultiIuvWSInterface mdpIuvWS = service.getMdpMultiIuvWSPort();
			
			mdpMultiIuvWSInterface = mdpIuvWS;
		}
	}

	public List<IuvComplex> generateNewIuv(final TipoPagamento tipoPagamento, final int numeroOccorrenze) throws MdpException{
		String methodName = "generateNewIuv";
		log.info ( methodName, "BEGIN" );

		try {
			String timestamp = getTimestamp();
			String mac = getMac(tipoPagamento.getIdApplicazione(), tipoPagamento.getCodiceVersamento(), passphrase, timestamp);
			
			log.info ( methodName, "--------------------------------------------------------------------------------");
			log.info ( methodName, "Call mdpMultiIuvWSInterface.getIuvComplex");
			log.info ( methodName, "     Param IdApplicazione        : " + tipoPagamento.getIdApplicazione());
			log.info ( methodName, "     Param Codice Versamento     : " + tipoPagamento.getCodiceVersamento());
			log.info ( methodName, "     Param numero occorrenze iuv : " + numeroOccorrenze);
			log.info ( methodName, "     Param timestamp             : " + timestamp);
			log.info ( methodName, "     Param mac                   : " + mac);
			log.info ( methodName, "     x info - passphrase         : " + passphrase);
			IuvComplexResponse response = mdpMultiIuvWSInterface.getIuvComplex(tipoPagamento.getIdApplicazione(), tipoPagamento.getCodiceVersamento(), numeroOccorrenze, timestamp, mac);
			log.info ( methodName, "Response mdpMultiIuvWSInterface.getIuvComplex :");
			log.info ( methodName, XmlUtil.obj2Xml(response));
			
			List<IuvComplex> iuvComplexes = response.getIuvComplex();
			return iuvComplexes;

		} catch (ErrorParameterException_Exception | MdpMultiIuvSrvException_Exception | MissingParameterException_Exception e) {
			e.printStackTrace();
			throw new MdpException("Errore invocazione generateNewIuv", e);
		
		} catch (SOAPException_Exception e) {
			e.printStackTrace();
			throw new MdpException("Errore invocazione generateNewIuv (" + e.getMessage() + ")", e);

		} finally {
			log.info ( methodName, "--------------------------------------------------------------------------------");
			log.info ( methodName, "END" );
		}
	}
	
	public List<IuvComplex> generateNewIuv(final TipoPagamento tipoPagamento, final int numeroOccorrenze, int numberTry) throws MdpException {
		try {
			return generateNewIuv(tipoPagamento, numeroOccorrenze);
		} catch (Exception e ) {
			if (--numberTry == 0 ) { 
				throw e;
			}
			return generateNewIuv(tipoPagamento, numeroOccorrenze, numberTry);
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
	
	/*public static void main(String[] args) {
		String applicationId = "EPAY_RP";
		String codiceVersamento = "CT01";
		String passphrase = "N3lm3zz0d3lcammindin0stravitamiritr0vaiP3runas3lva0scurach3ladirittavia3rasmarrita";
		String timestamp = "16062017-03:42:04:562";
		String mac = getMac(applicationId, codiceVersamento, passphrase, timestamp); 
		System.out.println(mac);
	}*/
	
	/*public static void main(String[] args) {
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
	}*/
}
