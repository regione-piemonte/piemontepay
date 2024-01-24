<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->
<%@ page language="java"
	import="org.apache.log4j.Logger,java.text.*,it.csi.mdp.intnodospc.util.*,javax.mail.*,javax.mail.internet.*,java.util.*,javax.naming.*,it.csi.mdp.core.business.paymentmanager.*,it.csi.mdp.core.business.dto.*,javax.rmi.*,java.net.*"
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
//java.net.URL url=Thread.currentThread().getContextClassLoader().getResource("log4j.properties");

Logger log = Logger.getLogger(Constants.APPLICATION_CODE + ".business");

%>
<html>
<c:if test="${portal == 'SP' }">
	<mdp:Include
		uri="/ris/include/motorepagamenti/head.html"
		host="${hostSP}"
	/>
</c:if>
<c:if test="${portal == 'TF' }">
	<mdp:Include
		uri="/applicazioni/motore_pagamenti/head_motorepagamenti.htm"
		host="${hostTF}"
	/>
</c:if>
<c:if test="${portal == 'default' }">
	<head>
</c:if>
    <title>Motore dei pagamenti - errore</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my error page">
  </head>

 <body>
<form runat="server">
<div class="colonnaUnica" id="pagina"><!-- INTESTAZIONE -->
<div id="intestazione"><c:if test="${portal == 'SP' }">
	<mdp:Include
		uri="/ris/include/headerSisp.html"
		host="${hostSP}"
	/>
	<mdp:Include
		uri="/ris/include/motorepagamenti/filoarianna.html"
		host="${hostSP}"
	></mdp:Include>
</c:if> <c:if test="${portal == 'TF' }">
	<mdp:Include
		uri="/applicazioni/motore_pagamenti/header_Tofa.htm"
		host="${hostTF}"
	/>
	<mdp:Include
		uri="/applicazioni/motore_pagamenti/header_motorepagamenti.htm"
		host="${hostTF}"
	></mdp:Include>
</c:if> <!--FINE INTESTAZIONE-->
<div id="contenitore">

<!-- CORPO -->
<div id="corpo"><!-- PRIMA COLONNA -->
<h3>ERRORE DI SISTEMA</h3>
<div class="ocra_box">
PARAMETRI NON VALIDI
</div>
<div id="pulsanti">
<div id="indietro">
<c:if test="${portal == 'SP' }">
	
<a href="${hostSP}/index.shtml">chiudi sessione</a></div>
</c:if> 
<c:if test="${portal == 'TF' }">
	
<a href="${hostTF}">chiudi sessione</a></div>	
</c:if> 

<div class="stampa" id="barra"><A onkeypress="window.print()"
	onclick="window.print()" href="javascript:"
>Stampa questa pagina</A></div>
</div>
</form>
<hr>
</DIV>
<hr>
<!--pièpagina-->
<c:if test="${portal == 'SP' }">
	<mdp:Include
		uri="/ris/include/footerSisp.html"
		host="${hostSP}"
	></mdp:Include>
</c:if>
<c:if test="${portal == 'TF' }">
	<mdp:Include
		uri="/applicazioni/motore_pagamenti/footer.htm"
		host="${hostTF}"
	></mdp:Include>
</c:if>
<c:if test="${portal == 'default' }">
</body>
</html>
</c:if>
