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
			if ("it-IT" == userLang || "it" == userLang) {
				$("#importo").inputFilter(function(value) {
					return /^-?\d*[,]?\d{0,2}$/.test(value);
				});
			} else {
				if (!$('#importo').val()) {
					$('#importo').attr('placeholder', '0.00');
				}
				$("#importo").inputFilter(function(value) {
					return /^-?\d*[.]?\d{0,2}$/.test(value);
				});
			}
		});
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
								<form:input path="ragioneSociale" cssClass="form-control" cssErrorClass="form-control has-error" />
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
								<textarea readonly="readonly" id="informativaPrivacy" title="Informativa sulla privacy" rows="10" cols="4"
									class="form-control">INFORMAZIONI SUL TRATTAMENTO DEI DATI PERSONALI AI SENSI DELL'ART. 13 e 14 DEL GDPR 2016/679

In questa pagina si descrivono le modalita' con le quali vengono trattati i dati personali degli utenti che accedono - tramite il Portale SistemaPiemonte.it - al sistema PIEMONTEPAY, che consente di pagare bollette, tasse o altri pagamenti dovuti alla Pubblica Amministrazione piemontese in modo semplice, standardizzato e affidabile.
PiemontePay aderisce a sua volta al circuito nazionale PagoPA, servizio a livello nazionale, che consente a cittadini e imprese di pagare in modalita' elettronica. L'infrastruttura tecnologica Nodo dei Pagamenti-SPC di PagoPA, che assicura l'interoperabilita' fra gli attori coinvolti nel sistema, e' stata realizzata da AGID, Agenzia per l'Italia Digitale.
I dati personali forniti dall'Utente con la compilazione del form o pervenuti da SPID per l'Utente che accede tramite questo Sistema, sono trattati secondo quanto previsto dal "Regolamento Europeo 2016/679 relativo alla protezione delle persone fisiche con riguardo al trattamento dei dati personali, nonche' alla libera circolazione di tali dati e che abroga la direttiva 95/46/CE" (di seguito GDPR).
Ai sensi degli artt. 13 e 14 del GDPR, la informiamo pertanto, di quanto segue:
a. Il trattamento dei suoi dati personali ha l'esclusiva finalita' di svolgere tutte le attivita' amministrative e tecniche connesse al servizio di pagamento richiesto dall'Utente. L'acquisizione dei Suoi dati ed il relativo trattamento sono obbligatori in relazione alle finalita' sopradescritte; ne consegue che l'eventuale rifiuto a fornirli potra' determinare l'impossibilita' del Titolare del trattamento ad erogare il servizio richiesto. Il trattamento dei dati trova pertanto la sua base giuridica nell'art. 6 comma 1 lett. b) del GDPR;
b. Il Titolare del trattamento dei dati personali e' l'Ente Pubblico Creditore che ha aderito al Sistema dei pagamenti informatici. L'identita' e i suoi dati di contatto, nonche' di quelli del suo Responsabile della Protezione dei dati personali, sono rinvenibili sul sito istituzionale dell'Ente.
c. Sono destinatari dei dati degli utenti che utilizzano il servizio: AGID, che mette a disposizione, attraverso il servizio pubblico di connettivita', la piattaforma tecnologica per l'interconnessione e l'interoperabilita' tra le pubbliche amministrazioni; Regione Piemonte quale intermediario tecnologico che offre l'infrastruttura tecnologica per consentire il colloquio con i soggetti aderenti (pubbliche amministrazioni e/o gestori di pubblici servizi che aderiscono al sistema dei pagamenti) nonche' Responsabile del trattamento dei dati nominato dall'Ente Pubblico Creditore; CSI Piemonte, ente strumentale per l'informatica della Pubblica Amministrazione Piemontese; prestatori di servizio di pagamento quali banche, poste e altri PSP che aderiscono al servizio;
d. i Suoi dati sono trattati, nel rispetto dei principi di correttezza, liceita', minimizzazione dei dati e tutela della riservatezza, con modalita' cartacee e informatiche, esclusivamente da soggetti incaricati dal Titolare, autorizzati ed istruiti in tal senso, o da imprese espressamente nominate come Responsabili del trattamento, adottando tutte quelle misure tecniche ed organizzative adeguate per tutelare i diritti, le liberta' e i legittimi interessi che sono riconosciuti per legge in qualita' di Interessato
e. i Suoi dati personali sono conservati per il tempo definito nel piano di conservazione degli archivi del Titolare.
f. i Suoi dati personali non saranno in alcun modo oggetto di trasferimento in un Paese terzo extraeuropeo, nè di comunicazione a terzi fuori dai casi previsti dalla normativa in vigore nè di processi decisionali automatizzati compresa la profilazione.
In qualita' di interessato, potra' esercitare i diritti previsti dagli artt. da 15 a 22 del GDPR (tra cui quelli di ottenere la conferma dell'esistenza o meno dei suoi Dati personali e la loro messa a disposizione in forma intellegibile; di avere conoscenza della logica e delle finalita' su cui si basa il trattamento; di ottenere la cancellazione, la trasformazione in forma anonima o la limitazione o il blocco dei Dati trattati in violazione di legge, nonche' l'aggiornamento, la rettifica o, se vi e' interesse, l'integrazione dei dati; di opporsi per motivi legittimi al trattamento stesso) rivolgendosi al Titolare o al Responsabile per la Protezione dei dati, ai dati di contatto dell'Ente Pubblico Creditore.
Potra' anche esercitare il diritto di proporre reclamo all'Autorita' di controllo o di ricorrere alle autorita' giurisdizionali competenti qualora il trattamento dei dati personali avvenga in violazione di quanto previsto dal GDPR, in conformita' agli artt. 77 e 79 del GDPR.</textarea>

							<form:checkbox id="flagPrivacy" path="flagPrivacy" cssErrorClass="has-error" />
								<form:label path="flagPrivacy" class="privacy" cssErrorClass="privacy has-error">Do il consenso al trattamento dei miei dati personali. </form:label>
								<form:errors path="flagPrivacy" cssClass="help-block has-error" element="small" />
							</div>
						</div>

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
