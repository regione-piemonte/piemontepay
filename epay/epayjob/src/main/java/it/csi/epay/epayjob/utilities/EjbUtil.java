/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.utilities;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbUtil {

	private Context context;

	public EjbUtil(final String serverList) throws NamingException {
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, getProviders(serverList));
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put("jboss.naming.client.ejb.context", true);
		context = new InitialContext(prop);
	}
	
	public EjbUtil(final String serverList, final String user, final String password) throws NamingException {
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		//"10.202.52.49:12111"
		prop.put(Context.PROVIDER_URL, getProviders(serverList));
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put("jboss.naming.client.ejb.context", true);
		//prop.put(Context.SECURITY_PRINCIPAL, "guest");
		prop.put(Context.SECURITY_PRINCIPAL, user);
		//prop.put(Context.SECURITY_CREDENTIALS, "gu3st-17");
		prop.put(Context.SECURITY_CREDENTIALS, password);
		context = new InitialContext(prop);
	}
	

	private String getProviders(final String serverList) {
		String separator = "";
		StringBuffer provider = new StringBuffer();
		for (String server : serverList.split(",")) {
			provider.append(separator);
			provider.append("remote://");
			provider.append(server);
			separator = ",";
		}
		return provider.toString();
	}
	
	public void close() throws NamingException {
		if (context != null) {
            context.close();
        }
	}

	public <T> T getEjb(Class<T> clazz) throws NamingException {
		final String appName = "epayservicesbe";
	    final String moduleName = "epayservices-ejb";
	    final String sessionBeanName = clazz.getSimpleName();
	    final String viewClassName = clazz.getName();
	    @SuppressWarnings("unchecked")
		T ejb =(T)context.lookup(appName+"/"+moduleName+"/"+sessionBeanName+"!"+viewClassName);
	    return ejb;
	}	
}
