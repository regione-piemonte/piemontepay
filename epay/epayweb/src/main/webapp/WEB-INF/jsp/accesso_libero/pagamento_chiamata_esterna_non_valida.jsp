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
<script type="text/javascript"
	src="<c:url value="/resources/js/generic.js" />"></script>
</head>

<body class="interna">
	<c:set var="menu" scope="request" value="libero" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>
	<%@ include file="../common/progressione.jsp"%>

	<div class="container">
		<div class="row">

			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="alert alert-danger alert-dismissible" role="alert">Attenzione!
                    Siamo spiacenti ma il pagamento non &egrave; stato riconosciuto.
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="form-group pulsantiera">

					<a class="btn btn-default" id="action_indietro"
						href="${urlHomePage}"> <i class="fa fa-chevron-left"
						aria-hidden="true"></i> Torna alla pagina principale di PiemontePay
					</a>

				</div>
			</div>
		</div>
	</div>



	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
