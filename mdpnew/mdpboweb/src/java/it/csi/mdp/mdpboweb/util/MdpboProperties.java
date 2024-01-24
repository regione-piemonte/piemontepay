/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MdpboProperties {
	public String getDefwsMdpboProperties(String value){
		String MDPBO_SERVICE_RESOURCE = "/META-INF/defws_mdpbo.properties";
		Properties props = new Properties();
		InputStream is = getClass().getResourceAsStream(
				MDPBO_SERVICE_RESOURCE);
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty(value);
	}
}
