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
					
					<h3><spring:message code="codiceversamento.breadcrumb.ricercacodiciversamento" /></h3>
			
					<%@ include file="pannello_ricerca.jsp"%>
					<%@ include file="pannello_risultati.jsp"%>
				</div>
			</div>

			<sec:authorize access="hasRole('INSERISCI_CODICE_VERSAMENTO')">
				<div class="row-fluid" style="padding-bottom: 2em;">
					<p class="margin-medium">
						<a id="buttonNew" class="btn btn-primary btn-action" 
							href="${context}/codici-versamento/nuovo"
						><spring:message code="codiceversamento.ricerca.form.inseriscicodicebase" /></a> 
					</p>
				</div>
			</sec:authorize>
			
			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" class="btn btn-secondary btn-action" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}
					<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> 
				</p>
			</div>
		</div>
	</div>
	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>

<style>

tr.base {
    background-color: #ffffb5;
    font-style: normal !important;
    font-weight: normal !important;
}

tr.group.group-start td {
    background-color: #ffffb5 !important;
    font-style: normal !important;
    font-weight: normal !important;
}

</style>
