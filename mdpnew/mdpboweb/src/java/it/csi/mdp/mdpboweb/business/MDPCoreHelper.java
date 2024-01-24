/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;

import it.csi.mdp.mdpboweb.util.Constants;
import it.csi.mdp.mdpboweb.business.ws.coreCxf.CreateException_Exception;
import it.csi.mdp.mdpboweb.business.ws.coreCxf.DaoException_Exception;
import it.csi.mdp.mdpboweb.business.ws.coreCxf.IMdpCoreCxf;
import it.csi.mdp.mdpboweb.business.ws.coreCxf.MdpCoreCxfService;
import it.csi.mdp.mdpboweb.business.ws.coreCxf.MissingParameterException_Exception;
import it.csi.mdp.mdpboweb.business.ws.coreCxf.NamingException_Exception;
import it.csi.mdp.mdpboweb.business.ws.coreCxf.Transazione;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;

public class MDPCoreHelper {

	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");
	public final static String MDPBO_SERVICE_RESOURCE = "/META-INF/defws_mdpbo.properties";
	private String CORE_ENDPOINT = null;

	public MDPCoreHelper() throws Exception {
		//log.info("[MDPCoreHelper::MDPServiceHelper]  BEGIN ");
		try {
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream(MDPBO_SERVICE_RESOURCE);
			props.load(is);
			CORE_ENDPOINT = props.getProperty("core.endpoint");

		} catch (Exception ex) {
			log.error("[MDPCoreHelper::MDPServiceHelper]  ", ex);
			ex.printStackTrace();
			throw ex;
		} finally {
			//log.info("[MDPCoreHelper::MDPServiceHelper]  END ");
		}
	}
	
	/**
	 * 
	 * @param transactionId
	 * @return
	 * @throws MalformedURLException
	 * @throws DaoException_Exception
	 * @throws NamingException_Exception
	 * @throws CreateException_Exception
	 * @throws MissingParameterException_Exception
	 */
	public it.csi.mdp.mdpboweb.business.ws.coreCxf.Transazione getStatoTransazione(String transactionId) throws MalformedURLException, DaoException_Exception, NamingException_Exception, CreateException_Exception, MissingParameterException_Exception {
		log.info("[MDPCoreHelper::getStatoTransazione]  BEGIN ");
		MdpCoreCxfService ss = new MdpCoreCxfService(new URL(CORE_ENDPOINT), new QName(
				"http://interfacecxf.core.mdp.csi.it/", "MdpCoreCxfService"));		
		IMdpCoreCxf clientcxfmdp;
		try {
			clientcxfmdp = ss.getMdpCoreCxfPort();
		} catch (Exception e) {
			log.debug("cxf eception -->" + e.getMessage());
			throw (new DaoException_Exception (e.getMessage()));
		}
		Transazione t = new Transazione();
		try {
			t = clientcxfmdp.getStatoTransazione(transactionId.trim());		
		}catch (Exception e) {
				throw (new DaoException_Exception (e.getMessage()));
			}
		log.info("[MDPCoreHelper::getStatoTransazione]  END ");
		return t;
	}
}
