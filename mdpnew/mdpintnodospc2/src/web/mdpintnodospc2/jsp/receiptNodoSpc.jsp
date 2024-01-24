<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java"
	import="it.csi.mdp.intnodospc2.util.*,org.apache.log4j.Logger,java.text.*,java.util.*,javax.naming.*,it.csi.mdp.core.business.paymentmanager.*,it.csi.mdp.core.business.dto.*,javax.rmi.*,java.net.*"
%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@ taglib uri="/WEB-INF/mdp/mdp.tld" prefix="mdp"%>
<c:set var="portal" value="" scope="page"/>
<c:set var="transazione" value="" scope="page"/> 
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server


Properties envp = new Properties();
envp.load(application.getResourceAsStream("/WEB-INF/classes/env.properties"));
pageContext.setAttribute("hostSP",envp.getProperty("portal.hostSP"));
pageContext.setAttribute("hostTF",envp.getProperty("portal.hostTF"));
java.net.URL url=Thread.currentThread().getContextClassLoader().getResource("log4j.properties");



%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 

http://dev-wls9-07.csi.it:9111/mdpintkeyclient/jsp/okKeyclient.jsp?session_id=&codAut=&alias=payment_testm_urlmac&orario=163626&data=20110707&mac=OGZkN2FmMTk3MmFiNTlmODI4OGE1OThmNWY1OTRmZGU%253D&importo=100&$BRAND=MasterCard&cognome=ee&nazionalita=ITA&pan=525599XXXXXX9992&divisa=EUR&email=&scadenza_pan=201201&esito=KO&codTrans=PSC6&nome=ee

-->
<%
LoggerUtil.debug("[receiptNodoSpc] RICHIESTA: " + request.getRequestURL());
	String pagEsito = "";

	String idSession = request.getParameter("idSession");
	String esito =  request.getParameter("esito");
	LoggerUtil.debug("[receiptNodoSpc] PARAMETRI idSession: " + idSession + " ESITO: " + esito);
	if (idSession != null)
	{
		InitialContext ctx = new InitialContext();
		Object o = ctx.lookup("mdppaymentsrv/ejb/PaymentManager");
		PaymentHome home = (PaymentHome) o;
		Payment p = home.create();
		
		
		if (p != null)
		{		
			Transazione t = p.findTransazioneByidSession(idSession);
			
			if (t == null) {
				request.getRequestDispatcher("/jsp/sessioneScaduta.jsp").forward(request, response);
				return;
			}
			
			LoggerUtil.debug("[receiptNodoSpc] TRANSAZIONE: " + t.getTransactionId());
			
			if (t.getCodStato() != 3 && t.getCodStato() != 4) {
				out.println("SESSIONE NON VALIDA");
				return;
			}
			if (t.getCodStato() == 3) {
				
				t.setOldstate(t.getCodStato());
				t.setChangestatedate(new Date());
				
				t.setCodStato(9);
				p.setStatoTransazione(t.getTransactionId(),9,"0");
	
				p.setTransazione(t);
			}
			

			String gwid = t.getGatewayId();
			String pmid = t.getGatewaypaymodeid();
			String appid = t.getApplicationId();
			ApplicationDetail ad = p.getApplicationDetail(appid,gwid,pmid);
			if (esito.equalsIgnoreCase("OK") || esito.equalsIgnoreCase("DIFFERITO")){
				pagEsito = ad.getApplicationurlresponseok();
				if (ad.getApplicationurlresponseok() == null)
					pagEsito = "okNodoSpc.jsp";
			} else {
				pagEsito = ad.getApplicationurlresponseko();
				if (ad.getApplicationurlresponseok() == null)
					pagEsito = "koNodoSpc.jsp";
			}
		
			
			
			response.resetBuffer();
			response.sendRedirect(pagEsito+"?tranId="+t.getTransactionId());
		}
	} 
	else
	{
		request.getRequestDispatcher("/jsp/sessioneScaduta.jsp").forward(request, response);
		return;
		
	}
%>
