<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>
<%@ include file="../common/profile_configuration.jsp"%>

<c:url var="urlDatiPersonali" value="${commonData.formAction}" />

<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="../common/header_html.jsp"%>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />
<script type="text/javascript" src="<c:url value="/resources/js/generic.js" />"></script>
<script src="https://www.google.com/recaptcha/api.js"></script>
</head>

<body class="interna">
	<script>
		$(function() {
			$('[data-toggle="popover"]').popover()
		});

		function toggle(divsToShow, divsToHide) {
			$(divsToHide.join(', ')).slideUp();
			$(divsToShow.join(', ')).slideDown();

			if (divsToHide.indexOf('#collapseOne') > -1) {
				$('#nome').val('');
				$('#cognome').val('');
			}

			if (divsToHide.indexOf('#collapseTwo') > -1) {
				$('#ragioneSociale').val('');
			}
		}
		
		$(document).ready(function() {

			var userLang = navigator.language || navigator.userLanguage; 
			
			if( "it-IT" == userLang ||"it" == userLang ){
				$('#checkbox2Label').remove();
				$("#importo").inputFilter(function(value) {
					return /^-?\d*[,]?\d{0,2}$/.test(value);
				});
			}
			else
				{
				$('#checkbox1Label').remove();
			    if(!$('#importo').val()) { 
			    	$('#importo').attr('placeholder', '0.00');
			    }
				$("#importo").inputFilter(function(value) {
					return /^-?\d*[.]?\d{0,2}$/.test(value);
				});
				}
// 			$('#myCheck').remove();

			var checkBox = document.getElementById("myCheck");
			 var initFromSession = ("true" == document.getElementById("flagInitFromSession").value );
				
			  if (checkBox.checked == true){
				  $('#codiceFiscale').val("AAAAAA00A00A000I");
				  $('#codiceFiscale').prop('readonly', true); 
			  }else{
// //		 		 $('#codiceFiscale').val("");
//                 if( initFromSession )
//                 	{
//                 	$('#codiceFiscale').val("");
//                 	}

				 $('#codiceFiscale').prop('readonly', false); 
			  }
// 			  $('#flagInitFromSession').val("false");

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


<script>
function setCodiceFiscaleEnte() {
  var checkBox = document.getElementById("myCheck");
  var codiceFiscale = document.getElementById("codiceFiscale");
  if (checkBox.checked == true){
	  $('#codiceFiscale').val("AAAAAA00A00A000I");
	  $('#codiceFiscale').prop('readonly', true); 
  }else{
	 $('#codiceFiscale').val("");
	 $('#codiceFiscale').prop('readonly', false); 
  }
}


</script>
	<c:set var="menu" scope="request" value="libero" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>
	<%@ include file="../common/progressione.jsp"%>


	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@ include file="../common/messaggi.jsp"%>
			</div>
			
			<spring:hasBindErrors name="datiPersonali">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="alert alert-danger alert-dismissible" role="alert">Attenzione! Verificare i dati contrassegnati in rosso.</div>

					<c:forEach var="error" items="${errors.globalErrors}">
						<div class="alert alert-danger alert-dismissible" role="alert">
							<spring:message message="${error}" />
						</div>
						<c:if test="${error.code == 'FieldMatch'}">
							<c:set var="emailCssErrorClass" scope="request" value="has-error" />
						</c:if>
					</c:forEach>
				</div>
			</spring:hasBindErrors>

			<%@ include file="../common/riepilogo_riferimenti.jsp"%>

			<div class="col-md-12 col-sm-12 col-xs-12">

				<p class="nota small">* Dati obbligatori</p>

				<form:form class="form-horizontal" method="POST" action="${urlDatiPersonali}" modelAttribute="datiPersonali">
				
				<c:if test="${commonData.flagDonazione}">
					<form:input id="donazione" path="donazione" type="hidden"
						value="true" />
				</c:if>
				<form:input id="flagInitFromSession" path="flagInitFromSession" type="hidden" />

					<c:if test="${commonData.pagamentoSpontaneo}">
						<div class="row">
							<div class="col-sm-12 col-md-6 col-lg-6">
								<div class="form-group">
									<form:label path="importo" cssClass="control-label" cssErrorClass="control-label has-error">* Importo (&euro;)</form:label>
									<form:input path="importo" placeholder="0,00" cssClass="form-control" cssErrorClass="form-control has-error" maxlength="16" />
									<form:errors path="importo" cssClass="help-block has-error" element="small" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-12">
								<div class="form-check form-check-inline">
									<b>* Soggetto giuridico </b>
									<form:radiobutton id="personaFisica" path="soggettoGiuridico" value="personaFisica" cssClass="ml-4"
										cssErrorClass="ml-4 has-error" onchange="toggle(['#collapseOne'], ['#collapseTwo'])" />
									<label for="personaFisica" class="form-check-label"> Persona Fisica </label>
									<form:radiobutton id="personaGiuridica" path="soggettoGiuridico" value="personaGiuridica" cssClass="ml-4"
										cssErrorClass="ml-4 has-error" onchange="toggle(['#collapseTwo'], ['#collapseOne'])" />
									<label for="personaGiuridica" class="form-check-label"> Persona Giuridica </label>
									<form:errors path="soggettoGiuridico" cssClass="help-block has-error" element="small" />
								</div>
							</div>
						</div>

						<div class="row" id="collapseOne" ${datiPersonali.soggettoGiuridico=='personaGiuridica' ? 'hidden=""' : ''}>
							<div class="col-sm-12 col-md-6 col-lg-6">
								<div class="form-group">
									<form:label path="nome" class="control-label" cssErrorClass="control-label has-error">* Nome</form:label>
									<form:input path="nome" cssClass="form-control" cssErrorClass="form-control has-error" />
									<form:errors path="nome" cssClass="help-block has-error" element="small" />
								</div>
							</div>
							<div class="col-sm-12 col-md-6 col-lg-6">
								<div class="form-group">
									<form:label path="cognome" class="control-label" cssErrorClass="control-label has-error">* Cognome</form:label>
									<form:input path="cognome" cssClass="form-control" cssErrorClass="form-control has-error" />
									<form:errors path="cognome" cssClass="help-block has-error" element="small" />
								</div>
							</div>
						</div>

						<div class="row" id="collapseTwo" ${datiPersonali.soggettoGiuridico=='personaFisica' ? 'hidden=""' : '' }>
						<div class="col-sm-12 col-md-12 col-lg-12">
							<div class="form-group">
								<form:label path="ragioneSociale" class="control-label" cssErrorClass="control-label has-error">* Ragione Sociale</form:label>
								<form:input path="ragioneSociale" cssClass="form-control" cssErrorClass="form-control has-error" maxlength="70" />
								<form:errors path="ragioneSociale" cssClass="help-block has-error" element="small" />
							</div>
							</div>
						</div>
					</c:if>

					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="email" cssClass="control-label ${emailCssErrorClass}" cssErrorClass="control-label has-error">* E-mail</form:label>
								<a tabindex="0" data-toggle="popover" data-trigger="focus"
									data-content="Ti verr&agrave; inviata conferma del pagamento a questo indirizzo"><i class="fa fa-question-circle-o"></i></a>
								<span class="sr-only">Informazioni</span>
								<form:input path="email" cssClass="form-control ${emailCssErrorClass}" cssErrorClass="form-control has-error" />
								<form:errors path="email" cssClass="help-block has-error" element="small" />
							</div>
						</div>
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="confirmEmail" cssClass="control-label ${emailCssErrorClass}" cssErrorClass="control-label has-error">* Ripeti E-mail</form:label>
								<form:input path="confirmEmail" cssClass="form-control ${emailCssErrorClass}" cssErrorClass="form-control has-error" />
								<form:errors path="confirmEmail" cssClass="help-block has-error" element="small" />
							</div>
						</div>
					</div>

					<c:if test="${commonData.pagamentoSpontaneo}">
						<div class="row">
							<div class="col-sm-12 col-md-6 col-lg-6">
								<div class="form-group">
									<form:label path="codiceFiscale" class="control-label" cssErrorClass="control-label has-error">* Codice Fiscale / Partita Iva</form:label>
									<form:input path="codiceFiscale" cssClass="form-control" cssErrorClass="form-control has-error" />
									<form:errors path="codiceFiscale" cssClass="help-block has-error" element="small" />
								</div>
							</div>
<%-- 							<div class="col-sm-12 col-md-6 col-lg-6" ${riferimento.codiceVersamento!='PN00' ? 'hidden=""' : '' && riferimento.codiceFiscaleEnte!='80087670016' ? 'hidden=""' : '' }> --%>
                              <div class="col-sm-12 col-md-6 col-lg-6" ${!commonData.flagCodFiscStraniero ? 'hidden=""' : '' }>
                             <div class="form-check" style="margin-top:45px">
                                  <input  type="checkbox" id="myCheck" Onclick="setCodiceFiscaleEnte()">
                                  <label for="checkbox" id="checkbox1Label">Qualora non si disponesse di un identificativo fiscale italiano valido, contrassegnare questa casella</label>
                                  <label for="checkbox" id="checkbox2Label">If you do not have a valid Italian tax code, flag this checkbox</label>
                                  
<!--                                     <label for="checkbox" id="checkbox2Label">Qualora non si disponesse di un identificativo fiscale italiano valido, contrassegnare questa casella</label>  -->
<%--                                   <c:choose> --%>
<%-- 							<c:when test="${commonData.linguaLocale == 'it'}"> --%>
<!-- 								 <label for="checkbox" id="checkbox2Label">Qualora non si disponesse di un identificativo fiscale italiano valido, contrassegnare questa casella</label> -->
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								 <label for="checkbox" id="checkbox1Label">If you do not have a valid Italian tax code, flag this checkbox</label> -->
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
						
                                </div>
                            </div>  
						</div>

						<div class="row">
							<c:set var="asteriscoNota" value="" scope="page" />
							<c:if test="${not empty riferimento.compilazioneNote}">
								<c:set var="asteriscoNota" value="*&nbsp;" scope="page" />
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="alert alert-warning alert-dismissible" role="alert">
										<span style="font-weight: bold;">Attenzione! Il campo "NOTE" deve essere compilato come segue:</span><br />
										<p>${riferimento.compilazioneNote}</p>
									</div>
								</div>
							</c:if>

							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="form-group">
									<form:label path="note" cssClass="control-label" cssErrorClass="control-label has-error">${asteriscoNota}Note</form:label>
									<form:textarea path="note" cssClass="form-control" cssErrorClass="form-control has-error" rows="3" />
									<form:errors path="note" cssClass="help-block has-error" element="small" />
								</div>
							</div>
						</div>
					</c:if>


					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<label for="informativaPrivacy" class="control-label">* Privacy</label>
								<iframe style="width:1100px !important; height:250px !important; overflow: auto;"
										title="Informativa sulla privacy" src="/ris/privacy/cittadini/informativaPrivacyPagamento.html"></iframe>
							<br>
							<form:checkbox id="flagPrivacy" path="flagPrivacy" cssErrorClass="has-error" />
								<form:label path="flagPrivacy" class="privacy" cssErrorClass="privacy has-error">Do il consenso al trattamento dei miei dati personali. </form:label>
								<form:errors path="flagPrivacy" cssClass="help-block has-error" element="small" />
							</div>
						</div>

						<c:if test="${recaptcha_verify == 'true'}">
							<div class="form-group">
								<div class="col-sm-2" class="text-right">*&nbsp;</div>
								<div class="col-sm-6 g-recaptcha" 
									data-sitekey="<c:out value="${recaptcha_sitekey}" />"
									data-callback="recaptchaCallback" 
									data-expired-callback="recaptchaExpired" 
									title="captcha"></div>
								<label for="g-recaptcha-response" style="display: none;">google recaptcha response</label>
							</div>
						</c:if>
					</div>

					<%@ include file="../common/pulsantiNavigazione.jsp"%>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer_page.jsp"%>
	<%@ include file="../common/modal_componentiImporto.jsp"%>
</body>
</html>
