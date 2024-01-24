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
				<li><a href="${context}/provvisori/ricerca"><spring:message code="provvisorio.dettaglio.ricercaprovvisori"/></a> <span class="divider"></span></li>
				<li class="active"><spring:message code="provvisorio.dettaglio.dettaglioprovvisori"/></li>
			</ul>
		</div>
	</div>

	<form:form id="salvataggio_provvisorio" name="salvataggio_provvisorio"
		action="${context}/provvisori/salva" method="post" class=""
		modelAttribute="dettaglio_provvisorio">
		<c:if test="${msgInfo != null}">
			<div class="stdMessagePanel">
				<div class="alert alert-success" role="alert">
					<p>
						<c:out value="${msgInfo}"></c:out>
					</p>
				</div>
			</div>
		</c:if>
		<c:if test="${msgError != null}">
			<div class="stdMessagePanel">
				<div class="alert alert-error" role="error">
					<p>
						<c:out value="${msgError}"></c:out>
					</p>
				</div>
			</div>
		</c:if>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<div class="container-fluid">
						<div class="row">
							<c:if test="${not empty provvisorio_errore}">
								<p class="alert alert-danger"><spring:message code="provvisorio.dettaglio.erroreinfasedicaricamento"/>
									<br /> <br />
									<c:out value="${provvisorio_errore}" />
								</p>
							</c:if>
							<c:if test="${empty provvisorio_errore}">

								<h3><spring:message code="provvisorio.dettaglio.provvisorio"/></h3>
								<%@ include file="riassunto-provvisorio.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" href="${context}/provvisori/ricerca"
						class="btn btn-secondary"><spring:message code="button.indietro"/></a>
					<c:if test="${disable_input == 'false'}">
						<button id="btnInserisciProvvisorio" type="submit" value="Salva"
							class="btn btn-primary pull-right"><spring:message code="button.save"/></button>
					</c:if>
				</p>
			</div>
		</div>
	</form:form>
	<%@ include file="../include/portal-footer.jsp"%>
	<%@ include file="script-dettaglio.jsp"%>
</body>
