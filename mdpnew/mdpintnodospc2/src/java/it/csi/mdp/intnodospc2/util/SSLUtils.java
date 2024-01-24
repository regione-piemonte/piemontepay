/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.intnodospc2.util;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * @author 630
 *
 */
public class SSLUtils {

	/**
	 * 
	 * @param serviceInterface
	 * @throws Exception
	 */
	public static void setTLSContext(Object serviceInterface) throws Exception {
		TLSClientParameters tlsCP = new TLSClientParameters();

		LoggerUtil.begin();
		try {
			// necessario per farsi autenticare dal server
			// System.getProperties().setProperty("javax.net.debug", "true");
			KeyStore ks = KeyStore.getInstance("JKS");
			char[] ks_password = System.getProperty("javax.net.ssl.keyStorePassword").toCharArray();
			ks.load(new FileInputStream(System.getProperty("javax.net.ssl.keyStore")), ks_password);

			if (ks != null && ks.size() != 0)
				LoggerUtil.info("Keystore trovato e caricato: <"+System.getProperty("javax.net.ssl.keyStore")+">");

			LoggerUtil.debug("Il keystore contiene i seguenti alias:");
			Enumeration<String> e = ks.aliases();
			while (e.hasMoreElements()) {
				String alias = (String) e.nextElement();
				LoggerUtil.debug("<"+alias+">");
			}
			

			String defualtAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
			LoggerUtil.info("default key manager algorithm: "+defualtAlgorithm);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(defualtAlgorithm);
			kmf.init(ks, ks_password);
			
			/*
			char[] privateKey_password = System.getProperty("mdp.keystore.private.key.password").toCharArray();
			Key privateKey = ks.getKey(System.getProperty("mdp.keystore.private.key.alias"), privateKey_password);
			if (privateKey != null)
				LoggerUtil.info("Private key found: " + privateKey);
				*/

			// necessario per autenticare il server
			/*
			KeyStore ts = KeyStore.getInstance("JKS");
			char[] ts_password = System.getProperty("javax.net.ssl.trustStorePassword").toCharArray();
			ts.load(new FileInputStream(System.getProperty("javax.net.ssl.trustStore")), ts_password);
			if (ts != null && ks.size() != 0)
				LoggerUtil.info("trustStore trovato e caricato: <"+System.getProperty("javax.net.ssl.trustStore")+">");
				TrustManagerFactory tmf = TrustManagerFactory.getInstance(defualtAlgorithm);
			tmf.init(ts);
				*/
			
			tlsCP.setKeyManagers(kmf.getKeyManagers());
			// tlsCP.setTrustManagers(tmf.getTrustManagers());
			tlsCP.setDisableCNCheck(true);
			
			/*
			SSLContext sslContext = SSLContext.getInstance("TLS");
			  sslContext.init(
					  kmf.getKeyManagers(),
			    null,
			    null);
			  
			  HTTPClientPolicy policy = new HTTPClientPolicy();
			
			 
			  HttpsURLConnection
		        .setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		        */

			HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(serviceInterface).getConduit();
			httpConduit.setTlsClientParameters(tlsCP);
			LoggerUtil.info("Contesto TLS settato");

			return;
		} catch (Exception e) {
			LoggerUtil.fatal(e.getMessage());
			throw e;
		} finally {
			LoggerUtil.end();
		}
	}
}
