/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpnodospcservices.util;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;

/**
 * @author 630
 * 
 *         Classe di utilita' con ruolo di interfacciamento keyStore e
 *         trustStore per recupero dei certificati di chiave privata e pubblica
 *         necessari per instaurare un colloquio TLS
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
			KeyStore ks = KeyStore.getInstance("JKS");
			char[] ks_password = System.getProperty("javax.net.ssl.keyStorePassword").toCharArray();
			ks.load(new FileInputStream(System.getProperty("javax.net.ssl.keyStore")), ks_password);

			if (ks != null && ks.size() != 0)
				LoggerUtil.info("Keystore trovato e caricato: <" + System.getProperty("javax.net.ssl.keyStore") + ">");
			
			String defualtAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
			LoggerUtil.info("default key manager algorithm: " + defualtAlgorithm);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(defualtAlgorithm);
			kmf.init(ks, ks_password);

			// necessario per autenticare il server
			/*
			KeyStore ts = KeyStore.getInstance("JKS");
			char[] ts_password = System.getProperty("javax.net.ssl.trustStorePassword").toCharArray();
			ts.load(new FileInputStream(System.getProperty("javax.net.ssl.trustStore")), ts_password);
			if (ts != null && ks.size() != 0)
				LoggerUtil.info(
						"trustStore trovato e caricato: <" + System.getProperty("javax.net.ssl.trustStore") + ">");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(defualtAlgorithm);
			tmf.init(ts);
			*/

			tlsCP.setKeyManagers(kmf.getKeyManagers());
			// tlsCP.setTrustManagers(tmf.getTrustManagers());
			tlsCP.setDisableCNCheck(true);

			HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(serviceInterface).getConduit();
			httpConduit.setTlsClientParameters(tlsCP);
			LoggerUtil.info("Contesto TLS settato");

			return;
		} catch (Exception e) {
			LoggerUtil.fatal(e.getMessage(),e);
			throw e;
		} finally {
			LoggerUtil.end();
		}
	}
}
