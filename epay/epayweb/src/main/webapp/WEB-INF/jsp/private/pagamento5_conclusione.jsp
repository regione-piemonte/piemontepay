<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

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

	<div class="tab-pane fade in active container" role="tabpanel">
		<div class="row">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="${messaggi.firstMessage.level.cssMsgClass}">
							<p class="primo">
								<c:out value="${messaggi.firstMessage.testo}"/>
								<br /> riferimento IUV: <c:out value="${riferimento.iuv}"/> 
								<br /> Data operazione: <fmt:formatDate pattern="dd/MM/yyyy" value="${riferimento.dataOperazione}" />
							</p>
							<p>
								Siamo in attesa di ricevere la Ricevuta Telematica contenente l'esito della transazione di pagamento.
								<br />La comunicazione dell'esito e la Ricevuta Telematica saranno inviati alla casella di posta che ci hai indicato: "<c:out value="${datiPersonali.email}"/>".
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
