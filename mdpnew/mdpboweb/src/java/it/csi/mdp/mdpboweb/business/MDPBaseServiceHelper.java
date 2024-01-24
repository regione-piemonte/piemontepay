/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;

import it.csi.mdp.mdpboweb.business.ws.*;
import it.csi.mdp.mdpboweb.util.Constants;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class MDPBaseServiceHelper {
	protected static final Logger log = Logger.getLogger(Constants.APPLICATION_CODE + ".business");
	public final static String MDPBO_SERVICE_RESOURCE = "/META-INF/defws_mdpbo.properties";
	protected String MDPBOWS_ENDPOINT = null;

	public MDPBaseServiceHelper() throws Exception {
		try {
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream(MDPBO_SERVICE_RESOURCE);
			props.load(is);
			MDPBOWS_ENDPOINT = props.getProperty("service.endpoint");
		} catch (Exception ex) {
			log.error("[MDPEntiServiceHelper::MDPServiceHelper]  ", ex);
			ex.printStackTrace();
			throw ex;
		} finally {
		}
	}

	protected MdpBoServicesCxfServiceSoapBindingStub autenticazione(
			MdpBoServicesCxfServiceSoapBindingStub stub) {
		return stub;
	}

}
