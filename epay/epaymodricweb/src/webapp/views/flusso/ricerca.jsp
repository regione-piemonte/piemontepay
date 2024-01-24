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
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span></li>
				<li class="active"><spring:message code="flusso.ricerca.ricercaflussi" /></li>
			</ul>
		</div>
		<c:if test="${limite_esportazione_superato}">
			<div class="col-sm-12">
				<p class="alert alert-warning"><spring:message code="flusso.ricerca.limiteesportazionesuperatoinizio" /> <a style="color:#465166" href="${pagina_prenotazione_report}">link</a><spring:message code="flusso.ricerca.limiteesportazionesuperatofine" /></p>
			</div>
		</c:if>
		<c:if test="${limite_report_superato}">
			<div class="col-sm-12">
				<p class="alert alert-warning"><spring:message code="flusso.ricerca.limitereportsuperatoinizio" />
				${num_max_fussi} 
				<spring:message code="flusso.ricerca.limitereportsuperatofine" /> </p>
			</div>
		</c:if>
	</div>
	

	<div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<%@ include file="form-ricerca.jsp"%>

					<div class="container-fluid">
						<div class="row">

							<c:if test="${lista_risultati_vuota}">
								<div class="col-sm-12">
									<p class="alert alert-warning"><spring:message code="flusso.ricerca.nessunrisultato" /></p>
								</div>
							</c:if>

							<c:if test="${not empty lista_risultati}">
								<sec:authorize access="hasRole('FORZATURA_ELABORAZIONE')">
									<div class="col-sm-12" style="margin-top: 0.5em; margin-bottom: 0.5em;">
										<button id="btnRielaboraTop" type="button" disabled class="btnRielabora btn btn-primary pull-right">
											<spring:message code="flusso.ricerca.prenotarielaborazione" />
										</button>
									</div>
								</sec:authorize>

								<div class="col-sm-12">
									<%@ include file="tabella-risultati.jsp"%>
								</div>

								<sec:authorize access="hasRole('FORZATURA_ELABORAZIONE')">
									<div class="col-sm-12" style="margin-top: 0.5em; margin-bottom: 0.5em;">
										<button id="btnRielaboraBottom" type="button" disabled class="btnRielabora btn btn-primary pull-right">
											<spring:message code="flusso.ricerca.prenotarielaborazione" />
										</button>
									</div>
								</sec:authorize>
								<div class="col-sm-12">
									<div class="row-fluid">
										<p class="margin-medium">
											<a></a>
											<a id="formButtonExportCsv" name="EsportaCsv" class="btn btn-default btn-action pull-right" href="${context}/flussi/esporta-csv" style="margin-right: 0em;"> <span class="fas fa-file-alt"></span><spring:message code="button.exportCSVlimite" ><c:out value=" (${NUM_MAX_RECORDS_EXPORT})"/></spring:message>
											</a> <a id="formButtonExportExcel" name="EsportaExcel" class="btn btn-default btn-action pull-right" href="${context}/flussi/esporta-excel" style="margin-right: 1em;"> <span class="fas fa-file-excel"></span><spring:message code="button.exportXLSlimite"><c:out value="(${NUM_MAX_RECORDS_EXPORT})"/></spring:message>
											</a>
										</p>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" class="btn btn-secondary" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a>
				</p>
			</div>
		</div>
	</div>
	<%@ include file="../include/portal-footer.jsp"%>
	<%@ include file="script-ricerca.jsp"%>
</body>
