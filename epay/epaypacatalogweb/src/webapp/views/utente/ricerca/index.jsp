<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../../include/head.jsp"%>

<body>
	<%@ include file="../../include/portal-header.jsp"%>
	<%@ include file="breadcrumb.jsp"%>
	<div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>
					<div id="alert-div"></div>
					
					<h3><spring:message code="ricercautenti" /></h3>
			
					<%@ include file="pannello_ricerca.jsp"%>
					<%@ include file="pannello_risultati.jsp"%>
				</div>
			</div>
			
			<sec:authorize access="hasRole('INSERISCI_UTENTE')"> 
				<div class="row-fluid" style="padding-bottom: 2em;">
					<p class="margin-medium">
						<a id="buttonNew" class="btn btn-primary btn-action" href="${context}/utenti/nuovo"><spring:message code="button.add" /></a> 
					</p>
				</div>
			</sec:authorize>

			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" class="btn btn-secondary btn-action" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> 
				</p>
			</div>
		</div>
	</div>
	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>

