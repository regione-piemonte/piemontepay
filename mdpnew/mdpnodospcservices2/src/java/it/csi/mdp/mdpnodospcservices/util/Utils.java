/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpnodospcservices.util;

import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.clientmod3.UnrecoverableException;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.paymentmanager.Payment;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.IntestazionePPT;
import it.csi.mdp.utility.CostantiNodoSpc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class Utils {

	public static long getStatoTransazioneMdp(String statoBank) {
		long statoMdp = 5;
		if (statoBank.equals("00")) {
			statoMdp = 4;
		} else {
			statoMdp = 5;
		}

		return statoMdp;
	}

	public static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	public static String randomString(int length) {
		
		Random rand = new Random();
		StringBuffer tempStr = new StringBuffer();
		tempStr.append("");
		for (int i = 0; i < length; i++) {
			int c = rand.nextInt(122 - 48) + 48;
			if ((c >= 58 && c <= 64) || (c >= 91 && c <= 96)) {
				i--;
				continue;
			}
			tempStr.append((char) c);

		}
		return tempStr.toString();
	}
	
	public static Map<String, String> convertiUrlInMapParametri (String urlStr) throws MalformedURLException, UnsupportedEncodingException {
		Map<String, String> mappaParametri = new HashMap<String, String>();
		URL url = new URL(urlStr);
	    String query = url.getQuery();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        mappaParametri.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    return mappaParametri;
	}
	
	public static Timestamp getSqlTimestampFromCalendar(Calendar original) {
		
		if (original == null)
			return null;
		
		return new Timestamp(original.getTimeInMillis());
	}
	
	public static Timestamp getSqlTimestampFromXmlGregorianCalendar(XMLGregorianCalendar original) {
		
		if (original == null)
			return null;
		
		return new Timestamp(original.toGregorianCalendar().getTimeInMillis());
	}

	public static String substring(String inputString, int start, int forChars) {
		if (inputString == null) return null;
		
		int end = (start + forChars) > inputString.length() ? inputString.length() : (start + forChars);
		return inputString.substring(start, end);
	}
	
	public static XMLGregorianCalendar xmlDateNow() {
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void inviaEmailErrore(String iuv, String codiceContestoPagamento, Payment p, Transazione t, Throwable e, String metodo) {
		inviaEmailErrore(iuv, codiceContestoPagamento, p, t, e, metodo, "");
	}
	
	public static void inviaEmailErrore(String iuv, String codiceContestoPagamento, Payment p, Transazione t, Throwable e, String metodo, String testoAggiuntivo) {
		LoggerUtil.begin();
		StringBuilder testoEmail = new StringBuilder("SI E' VERIFICATO UN ERRORE NON GESTITO IN ");
		try {
			testoEmail.append(metodo);
			testoEmail.append("\n\nIUV= ").append(iuv);
			testoEmail.append("\n\nCodice contesto pagamento= ").append(codiceContestoPagamento);
			testoEmail.append("\n\n").append("SEGUONO DETTAGLI ERRORE\n\n");
			if (e != null) {
				testoEmail.append(concatenaMessaggiCausa(e)).append("\n\n");
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				Throwable trueCause = trovaCausa(e);
				trueCause.printStackTrace(pw);
				testoEmail.append(sw.getBuffer());
			}
			testoEmail.append("\n\n").append(testoAggiuntivo);
			p.errorMail(t == null ? "N/A" : t.getApplicationId() , t, testoEmail.toString());
		} catch (Exception eInvio) {
			LoggerUtil.error("IMPOSSIBILE INVIARE EMAIL PER ERRORE", eInvio);
		}
		LoggerUtil.end();
	}

	public static Throwable trovaCausa(Throwable e) {
		if (e == null || e.getCause() == null) return e;
		return trovaCausa(e.getCause());
	}
	
	public static StringBuilder concatenaMessaggiCausa (Throwable e) {
		StringBuilder messaggio = new StringBuilder();
		
		if (e == null) return messaggio;
		
		messaggio.append(e.getMessage());
		Throwable cause = e.getCause();
		while (cause != null) {
			messaggio.append("; ").append(cause.getMessage());
			cause = cause.getCause();
		}
		return messaggio;
	}
	
	/**
	 * Metodo con scopo di creazione dell'istanza di un proxy di comunicazione verso una API remota.
	 * Incapsula la logica di reperimento del proxy tramite lo strato client offerto dalla API JaxWS.
	 * Imposta sul conduit HTTP valori di soglia di timeout minori rispetto al default:
	 * - connect timeout => 5 sec
	 * - read timeour => 10 sec
	 * 
	 * @param serviceInterfaceClass l'oggetto che rappresenta la classe di servizio implementata remotamente
	 * @param address La stringa indicante l'endpoint del servizio remoto
	 * @param eventuale mappa di proprietà per una configurazione specifica
	 * @return L'istanza del proxy creato; il codice che richiama il metodo lo casterà all'interfaccia desiderata
	 * @throws Exception
	 */
	public static Object getProxyAPIService(Class serviceInterfaceClass, 
			String address,
			Map<String, Object> props) throws UnrecoverableException
	{
		try {
			LoggerUtil.begin();
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			if(props != null && props.size() > 0)
				factory.setProperties(props);
	        factory.getInInterceptors().add(new LoggingInInterceptor());
	        factory.getOutInterceptors().add(new LoggingOutInterceptor());
	        factory.setServiceClass(serviceInterfaceClass);
	        factory.setAddress(address);
	        Object proxy = factory.create();
	        
	        if (proxy == null) {
	        	LoggerUtil.fatal("Reperimento del proxy fallito");
	        	throw new UnrecoverableException();
			}
	        
	        // impostazione custom dei valori soglia dei timeout
	        // TODO: leggere i valori da file di properties
	        LoggerUtil.info("Endpoint di Servizio remoto: <"+address+">"); 
	        
	        Client client = ClientProxy.getClient(proxy);
	        if (client != null) {
	            HTTPConduit conduit = (HTTPConduit) client.getConduit();
	            HTTPClientPolicy policy = new HTTPClientPolicy();
	           	policy.setConnectionTimeout(5000L);
	            policy.setReceiveTimeout(10000L);
	            conduit.setClient(policy);
	        }
	        LoggerUtil.info("Proxy di Servizio remoto reperito con successo ..."); 
	        return proxy;
		} catch(Exception e) {
			LoggerUtil.fatal("Reperimento del proxy fallito - causa: "+e.getMessage());
			throw new UnrecoverableException(e.getMessage(),e);
		}
		finally {
			LoggerUtil.end();
		}
		
	}
	
}
