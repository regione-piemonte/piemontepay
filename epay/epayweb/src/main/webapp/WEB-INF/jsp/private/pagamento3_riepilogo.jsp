<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<c:url var="urlRiepilogo" value="${commonData.formAction}" />

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

			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@ include file="../common/messaggi.jsp"%>
			</div>

			<div class="col-md-12 col-sm-12 col-xs-12">
			
			
				<%@ include file="../common/riepilogo_riferimenti.jsp"%>

				<form:form class="form-horizontal" method="POST" action="${urlRiepilogo}" modelAttribute="datiPersonali">

					<%--
					<p>
						Ente:
						<c:out value="${riferimento.enteDesc}" />
					</p>
					<p>
						Pagamento:
						<c:out value="${riferimento.pagamentoDesc}" />
					</p>
					<c:if test="${not empty riferimento.iuv}">
						<p>
							IUV:
							<c:out value="${riferimento.iuv}" />
						</p>
					</c:if>
					<p>
						Data operazione:
						<fmt:formatDate pattern="dd/MM/yyyy" value="${riferimento.dataOperazione}" />
					</p>
					 --%>
					<p>
						Importo:&nbsp;
						<fmt:formatNumber type="CURRENCY" currencySymbol="&euro;" value="${datiPersonali.importo}" />
					</p>
					<p>Soggetto Giuridico: ${datiPersonali.soggettoGiuridico == 'personaFisica' ? 'Persona Fisica' : 'Persona Giuridica'}</p>
					<c:choose>
						<c:when test="${datiPersonali.soggettoGiuridico == 'personaFisica'}">
							<p>
								Nome:
								<c:out value="${datiPersonali.nome}" />
							</p>
							<p>
								Cognome:
								<c:out value="${datiPersonali.cognome}" />
							</p>
						</c:when>
						<c:otherwise>
							<p>
								Ragione Sociale:
								<c:out value="${datiPersonali.ragioneSociale}" />
							</p>
						</c:otherwise>
					</c:choose>
					<p>
						Codice Fiscale / Partita Iva:
						<c:out value="${datiPersonali.codiceFiscale}" />
					</p>
					<p>
						E-mail:
						<c:out value="${datiPersonali.email}" />
					</p>

					<c:if test="${!empty datiPersonali.note}">
						<p>
							Note:
							<c:out value="${datiPersonali.note}" />
						</p>
					</c:if>

					<c:if test="${(!empty commonData.prosegui) && commonData.prosegui}">
						<div class="alert alert-warning alert-dismissible" role="alert">Selezionando "Prosegui" confermi i dati e potrai
							accedere alla pagina di PagoPA dove potrai completare il pagamento.</div>
					</c:if>

					<%@ include file="../common/pulsantiNavigazione.jsp"%>
				</form:form>
			</div>
		</div>
	</div>



	<%@ include file="../common/footer_page.jsp"%>
	<%@ include file="../common/modal_componentiImporto.jsp"%>
</body>
</html>
