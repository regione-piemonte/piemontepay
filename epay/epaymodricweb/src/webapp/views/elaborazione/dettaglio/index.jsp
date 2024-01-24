<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../../include/head.jsp"%>

<body>
	<%@ include file="../../include/portal-header.jsp"%>
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li>
					<a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
				</li>
				<li>
					<a href="${context}/elaborazione/ricercaElaborazionePrecedente"><spring:message code="elaborazione.dettaglio.index.ricercaelaborazioneprecedente" /></a>
					<span class="divider"></span>
				</li>
				<li class="active"><spring:message code="elaborazione.dettaglio.index.dettaglioelaborazione" /></li>
			</ul>
		</div>
	</div>

	<div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>
	
					<div class="container-fluid">
						<div class="row">
								
								<c:if test="${not empty elaborazione_selezionata}">
									<%@ include file="riassunto-elaborazione.jsp"%>
								</c:if>

								<div style="margin-top: 1em;">
									<c:if test="${empty flussi_associati_a_elaborazione}">
										<p class="alert alert-info">
											<spring:message code="elaborazione.dettaglio.index.noflussoorigineelaborabile" />
										</p>
									</c:if>
									
									<c:if test="${not empty flussi_associati_a_elaborazione}">
										<%@ include file="tabella-risultati.jsp"%>
									</c:if>
								</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a 
						id="buttonHome" 
						href="${context}/elaborazione/ricercaElaborazionePrecedente"
						class="btn btn-secondary"
					><spring:message code="button.indietro" /></a> 
				</p>
			</div>
		</div>
	</div>
	<%@ include file="../../include/portal-footer.jsp"%>
	<%@ include file="script-elaborazione.jsp"%>
</body>
