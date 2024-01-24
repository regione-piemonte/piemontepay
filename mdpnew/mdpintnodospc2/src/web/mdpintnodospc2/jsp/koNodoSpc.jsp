<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" import="org.apache.log4j.Logger,java.text.*,java.util.*,javax.naming.*,it.csi.mdp.core.business.paymentmanager.*,it.csi.mdp.core.business.dto.*,javax.rmi.*,java.net.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/mdp/mdp.tld" prefix="mdp"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server


Properties envp = new Properties();
envp.load(application.getResourceAsStream("/WEB-INF/classes/env.properties"));
pageContext.setAttribute("hostSP",envp.getProperty("portal.hostSP"));
%>
<mdp:Include uri="/ris/include/motorepagamenti/head.html" host="${hostSP}" />
</head>
<body>
	<div class="colonnaUnica" id="pagina"><!-- INTESTAZIONE -->
		<div id="intestazione">
			<mdp:Include
				uri="/ris/include/headerSisp.html"
				host="http://www.sistemapiemonte.it"
			/>
			<mdp:Include
				uri="/ris/include/motorepagamenti/filoarianna.html"
				host="http://www.sistemapiemonte.it"
			></mdp:Include>
		</div>
	</div>
</body>

<h3>RIEPILOGO</h3>
<div class="ocra_box">
	<p><strong>Pagamento annullato o non autorizzato. Si prega di vedere i dettagli sulla Ricevuta Telematica</strong></p>
</div>
