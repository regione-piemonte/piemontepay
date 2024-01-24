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
					
					<h3><spring:message code="autorizzazionefunzione.ricerca.index.gestioneautorizzazionefunzioni" /></h3>
			
					<%@ include file="pannello_ricerca.jsp"%>
					<%@ include file="pannello_risultati.jsp"%>
				</div>
			</div>
			
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

