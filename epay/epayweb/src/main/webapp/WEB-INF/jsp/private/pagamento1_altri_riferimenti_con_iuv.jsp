<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<c:url var="urlRiferimenti" value="${commonData.formAction}" />

<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="../common/header_html.jsp"%>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />
<script type="text/javascript" src="<c:url value="/resources/js/generic.js" />"></script>
</head>

<body class="interna">
	<c:set var="menu" scope="request" value="autenticato" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>
	<%@ include file="../common/progressione.jsp"%>

	<div class="container">
		<div class="row">

			<spring:hasBindErrors name="riferimentoSenzaCodiceFiscale">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<c:if test="${errors.globalErrorCount > 0}">
						<div class="alert alert-danger alert-dismissible" role="alert">Impossibile proseguire, il pagamento risulta in corso. Si invita a riprovare piu' tardi</div>
					</c:if>
					<c:if test="${errors.globalErrorCount <= 0 && errors.errorCount > 0}">
						<div class="alert alert-danger alert-dismissible" role="alert">Attenzione! Verificare i dati contrassegnati in rosso.</div>
					</c:if>
					<!-- 					<div class="alert alert-danger alert-dismissible" role="alert">Attenzione! Verificare i dati contrassegnati in LOLLO.</div> -->
					<!-- 					<div class="alert alert-danger alert-dismissible" role="alert">Impossibile proseguire, il pagamento risulta in corso. Si invita a riprovare piu' tardi</div> -->
				</div>
			</spring:hasBindErrors>

			<div class="col-md-12 col-sm-12 col-xs-12">

				<p class="nota">* Dati obbligatori</p>

				<form:form class="form-horizontal" method="POST" action="${urlRiferimenti}" modelAttribute="riferimentoSenzaCodiceFiscale">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="iuv" cssClass="control-label" cssErrorClass="control-label has-error">
										* Codice Avviso / IUV
								</form:label>
								<form:input path="iuv" id="iuv" cssClass="form-control" cssErrorClass="form-control has-error" maxlength="25"
									style="text-transform: uppercase" />
								<form:errors path="iuv" cssClass="help-block has-error" element="small" />
							</div>
						</div>

					<%@ include file="../common/pulsantiNavigazione.jsp"%>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
