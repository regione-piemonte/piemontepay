<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../include/head.jsp"%>

<body>
	<%@ include file="../include/portal-header.jsp"%>
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
				</li>
				<li><a href="${context}/errori/ricerca"><spring:message code="home.flussi.errore.ricerca.page" /></a>
					<span class="divider"></span></li>
				<li class="active"><spring:message code="home.flussi.errore.ricerca.dettaglio" /></li>
			</ul>
		</div>
	</div>

	<form:form id="salvataggio_errore" name="salvataggio_errore"
		action="${context}/errori/salva" method="post" class=""
		modelAttribute="dettaglio_errore">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<div class="container-fluid">
						<div class="row">
							<c:if test="${not empty errore_errore}">
								<p class="alert alert-danger">
									<spring:message code="home.flussi.errore.ricerca.dettaglio.error" /><br /> <br />
									<c:out value="${errore_errore}" />
								</p>
							</c:if>
							<c:if test="${empty errore_errore}">

								<h3><spring:message code="home.flussi.errore.ricerca.dettaglio" /></h3>
								<%@ include file="riassunto-errore.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
			</div>

			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" href="${context}/errori/ricerca"
						class="btn btn-secondary"><spring:message code="button.back" /></a>
				</p>
			</div>
		</div>
	</form:form>
	<%@ include file="../include/portal-footer.jsp"%>
	<%@ include file="script-dettaglio.jsp"%>
</body>
