/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.servlet;



import it.csi.mdp.core.util.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class LogServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	[className::methodName] message
	protected static Logger log; //= Logger.getLogger(Constants.APPLICATION_CODE + ".business");
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
//		java.net.URL url=Thread.currentThread().getContextClassLoader().getResource("log4j.properties");
//		PropertyConfigurator.configure(url);
		LogServlet.log = Logger.getLogger(Constants.APPLICATION_CODE);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	{	
		try
		{
			resp.getOutputStream().println("<html>Logger Activator Servlet</html>");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		log.debug("*** do get end");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException 
	{
	  this.doGet(request,response);
	}      
 
	public String getServletInfo()
	{
		return super.getServletInfo();
	}
}
