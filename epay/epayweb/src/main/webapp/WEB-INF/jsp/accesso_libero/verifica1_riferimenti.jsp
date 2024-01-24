<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<c:url var="urlRiferimenti" value="${commonData.formAction}" />

<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="../common/header_html.jsp"%>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />
<script type="text/javascript" src="<c:url value="/resources/js/generic.js" />"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body class="interna">
	<c:set var="menu" scope="request" value="libero" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>
	<%@ include file="../common/progressione.jsp"%>

	<div class="container">
		<div class="row">
			<%@ include file="../common/messaggi.jsp"%>

			<spring:hasBindErrors name="riferimento">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="alert alert-danger alert-dismissible" role="alert">Attenzione! Verificare i dati contrassegnati in rosso.</div>
				</div>
			</spring:hasBindErrors>

			<div class="col-md-12 col-sm-12 col-xs-12">
				
				<p class="nota small">* Dati obbligatori</p>

				<form:form class="form-horizontal" method="POST" action="${urlRiferimenti}" modelAttribute="riferimento">
					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="codiceFiscale" cssClass="control-label" cssErrorClass="control-label has-error">* Codice Fiscale o Partita IVA</form:label>
								<form:input path="codiceFiscale" cssClass="form-control" cssErrorClass="form-control has-error"	maxlength="16" />
								<form:errors path="codiceFiscale" cssClass="help-block has-error" element="small" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="iuv" cssClass="control-label" cssErrorClass="control-label has-error">* IUV <small>(Identificativo Unico Versamento)</form:label>
								<form:input path="iuv" cssClass="form-control" cssErrorClass="form-control has-error" maxlength="25" />
								<form:errors path="iuv" cssClass="help-block has-error" element="small" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-6">
							*
							<div class="col-sm-6 g-recaptcha" data-sitekey="6Lec_AgUAAAAAEtkZ-_AbMQCVB_8954Mb3wQWtXX" title="captcha"></div>
							<label for="g-recaptcha-response" style="display: none;">google recaptcha response</label>
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
