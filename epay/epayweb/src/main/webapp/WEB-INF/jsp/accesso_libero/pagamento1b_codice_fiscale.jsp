<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>
<%@ include file="../common/profile_configuration.jsp"%>

<c:url var="urlCodiceFiscale" value="${commonData.formAction}" />

<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="../common/header_html.jsp"%>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />
<script type="text/javascript"
	src="<c:url value="/resources/js/generic.js" />"></script>
<script src="https://www.google.com/recaptcha/api.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var configEnableCaptcha = ('<c:out value="${recaptcha_verify}" />'.toLowerCase().trim() == "true");
	
	if( !configEnableCaptcha ) {
		$('#action_prosegui').removeAttr('disabled');
		$('#action_stampa').removeAttr('disabled');
		$('#action_prosegui').prop('disabled', false);
		$('#action_stampa').prop('disabled', false);
	} else {
		$('#action_prosegui').prop('disabled', true);
		$('#action_stampa').prop('disabled', true);
	}
});	
function recaptchaCallback() {
	$('#action_prosegui').removeAttr('disabled');
	$('#action_stampa').removeAttr('disabled');
	$('#action_prosegui').prop('disabled', false);
	$('#action_stampa').prop('disabled', false);
};
function recaptchaExpired() {
	$('#action_prosegui').prop('disabled', true);
	$('#action_stampa').prop('disabled', true);
};
</script>
</head>

<body class="interna">
	<c:set var="menu" scope="request" value="libero" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>
	<%@ include file="../common/progressione.jsp"%>

	<div class="container">
		<div class="row">
			
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@ include file="../common/messaggi.jsp"%>
			</div>
			
			<spring:hasBindErrors name="riferimento">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<c:if test="${errors.globalErrorCount > 0}">
						<div class="alert alert-danger alert-dismissible" role="alert">Impossibile
							proseguire, il pagamento risulta in corso. Si invita a riprovare
							piu' tardi</div>
					</c:if>
					<c:if
						test="${errors.globalErrorCount <= 0 && errors.errorCount > 0}">
						<div class="alert alert-danger alert-dismissible" role="alert">Attenzione!
							Verificare i dati contrassegnati in rosso.</div>
					</c:if>
					<!-- 					<div class="alert alert-danger alert-dismissible" role="alert">Attenzione! Verificare i dati contrassegnati in LOLLO.</div> -->
					<!-- 					<div class="alert alert-danger alert-dismissible" role="alert">Impossibile proseguire, il pagamento risulta in corso. Si invita a riprovare piu' tardi</div> -->
				</div>
			</spring:hasBindErrors>

			<div class="col-md-12 col-sm-12 col-xs-12">

				<p class="nota">* Dati obbligatori</p>

				<form:form class="form-horizontal" method="POST"
					action="${urlCodiceFiscale}" modelAttribute="riferimento">

					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="iuv" cssClass="control-label"
									cssErrorClass="control-label has-error">* Codice Avviso / IUV</form:label>
									<form:input path="iuv" id="iuv" type="text" 
									cssClass="form-control" cssErrorClass="form-control has-error"
									style="text-transform: uppercase"
									value="${riferimentoSenzaCodiceFiscale.iuv}" readonly="true" />
								<form:errors path="iuv" cssClass="help-block has-error"
									element="small" />
							</div>
						</div>

						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="codiceFiscale" cssClass="control-label"
									cssErrorClass="control-label has-error">
										* Codice Fiscale o Partita IVA
									</form:label>
								<form:input path="codiceFiscale" id="codiceFiscale"
									cssClass="form-control" cssErrorClass="form-control has-error"
									maxlength="16" style="text-transform: uppercase" />
								<form:errors path="codiceFiscale"
									cssClass="help-block has-error" element="small" />

							</div>
						</div>
					</div>


					<!-- Andrea Avanzo: RDI 10.6 | START -->
					<c:if test="${recaptcha_verify == 'true'}">
						<div class="form-group" data-callback="<c:out value="${recaptcha_sitekey}" />">
							<div class="col-sm-6 g-recaptcha pull-right"  
								data-sitekey="<c:out value="${recaptcha_sitekey}" />"
								data-callback="recaptchaCallback" 
								data-expired-callback="recaptchaExpired" 
								title="captcha"></div>
							<label for="g-recaptcha-response" style="display: none;">google recaptcha response</label>
							<div class="col-sm-1 pull-right">*&nbsp;</div>
						</div>
					</c:if>
					
					<%@ include file="../common/pulsantiNavigazione.jsp"%>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
