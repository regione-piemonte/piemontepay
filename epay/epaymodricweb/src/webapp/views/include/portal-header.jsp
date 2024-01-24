<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

 <%@ include file="modals.jsp"%>
    
    <div class="container-fluid-banner container-portal-header">
        <div id="portalHeader">
            <noscript class="alert_js">
                <p>
                    ATTENZIONE! Il browser in uso non supporta le applicazioni Javascript.<br />
                    Per usufruire dei servizi presenti, &egrave; necessario utilizzare i Javascript.
                </p>
            </noscript>
            <div id="portalHeader_title">
		       <div class="container-fluid">
		         <div class="row-fluid">
		            <div class="col-sm-4">
		                <p>
		                    <a href="https://www.regione.piemonte.it/web" target="_blank">
		                    <img alt="Regione Piemonte" src="/epaymodricweb/ris/css/im/test_fesr.jpg" title="Regione Piemonte"></a>
		                </p>
		            </div>
		        </div>
		      </div>
            </div>
        </div>
        
        
     
        <script>
        function logout() {
        	var host = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ":" + window.location.port : "");
        	$.get(host + "/epaypacatalogweb/remote-logout").always(function() {
        		$.get(host + "/epaysimweb/remote-logout").always(function() {
        			$.get(host + "/epaypaweb/remote-logout.do").always(function() {
        				window.location = '${context}' + "/custom-logout";
        			});
        		});
        	});
        }
        </script>
        
        <div class="navbarLogin">     
            <div class="container-fluid pull-right">
                    <span class="login-text"><spring:message code="portal.utente"/>
						 <sec:authentication property="principal.utente.nome" /> <sec:authentication property="principal.utente.cognome" />
					</span>
                    <span class="login-text">
                    	<spring:message code="portal.ente"/><sec:authentication property="principal.ente.denominazione" />
                    </span> 
                <a href="javascript:logout();" class="btn" aria-label="Logout">
                	<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                </a>
            </div>
        </div>
        <a title="A-contenuti"></a>
    </div>
