/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

public class ApplicationConfiguration {
	private static ApplicationConfiguration applicationConfiguration;

	private Properties config;

	public static synchronized ApplicationConfiguration getApplicationConfiguration() {
		if (applicationConfiguration == null) {
			applicationConfiguration = new ApplicationConfiguration();
		}
		return applicationConfiguration;
	}

	private ApplicationConfiguration() {
		config = new Properties();
		try {
			ServletContext servletContext = (ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT);
			InputStream configInputStream = servletContext.getResourceAsStream("/WEB-INF/application-config.properties");
			config.load(configInputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getStringProperty(String key) {
		return config.getProperty(key);
	}

}
