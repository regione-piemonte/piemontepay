<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:div class="navbarLogin">
        <script>
        function logout() {
        	var host = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ":" + window.location.port : "");
        	$.get(host + "/epaypacatalogweb/remote-logout").always(function() {
        		$.get(host + "/epaysimweb/remote-logout").always(function() {
        			$.get(host + "/epaymodricweb/remote-logout").always(function() {
        				window.location = "/epaypaweb/logout.do";
        			});
        		});
        	});
        }
        </script>
    <s:div class="container-fluid pull-right">
    	<s:if test="sessionContext != null && sessionContext.utente != null">
			<span class="login-text">Utente: <s:property value="sessionContext.utente.getNomeCompleto ()"/></span> 
		</s:if>
		
		<%--
		<s:if test="sessionContext != null && sessionContext.profilo != null">
			<span class="login-text">Profilo: <s:property value="sessionContext.profilo.des"/></span>
		</s:if>
		<s:if test="sessionContext != null && sessionContext.ruolo != null">
			<span class="login-text">Ruolo: <s:property value="sessionContext.ruolo.des"/></span> 
		</s:if>
		 --%>
		
		<s:if test="sessionContext != null && sessionContext.ente != null">
			<span class="login-text">Ente: <s:property value="sessionContext.ente.denominazione"/></span> 
		</s:if>
		<s:a href="javascript:logout();" class="btn" aria-label="Logout">
			<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
		</s:a>
	</s:div>
</s:div>
