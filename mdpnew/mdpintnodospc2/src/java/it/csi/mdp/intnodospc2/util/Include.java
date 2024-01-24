/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.intnodospc2.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



public class Include extends TagSupport
{
	private String uri = "";
	private String host="";
	public String getUri()
	{
		return uri;
	}
	public void setUri(String url)
	{
		this.uri = url;
	}
	public String getHost()
	{
		return host;
	}
	public void setHost(String host)
	{
		this.host = host;
	}
	

		 public int doStartTag() throws JspException
		    
		    {
		        try
		        {
		        	JspWriter out = pageContext.getOut();
		            HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
		            
		        	URL yahoo = new URL(this.host+this.uri);
		            URLConnection yc = yahoo.openConnection();
		            BufferedReader in = new BufferedReader(
		                                    new InputStreamReader(
		                                    yc.getInputStream()));
		            String inputLine;

		            while ((inputLine = in.readLine()) != null) 
		            {
		            	
		            	inputLine = inputLine.replace("\"/","\""+this.host+"/");
		            	out.println(inputLine);
		            }
		            	
		            in.close();
		        }
		        catch(Exception e)
		        {   
		            throw new JspException(e.getMessage());
		        }
		        return EVAL_PAGE;
		    }



	    
	  


	
}
