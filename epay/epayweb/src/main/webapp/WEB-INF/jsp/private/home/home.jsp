<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/tag_lib.jsp"%>
<%@ include file="../../common/url.jsp"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<%@ include file="../../common/header_html.jsp"%>
		<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-home.css" />
		<script src="<c:url value="/resources/js/home.js" />"></script>
		<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
		<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />
	</head>
	<body class="home_auth">
		<c:set var="menu" scope="request" value="homepage autenticato"/>
		<c:set var="tipoAccesso" scope="request" value="private" />
        <%@ include file="../../common/header_page.jsp"%>
		<%@ include file="../../common/covid-19HelpModal.jsp"%>
<%-- 		<%@ include file="../../common/alertStampaAvvisoModal.jsp"   %> --%>
	<div class="container">
		<h2>I tuoi pagamenti sicuri e veloci</h2>
		<div class="card">
			<div class="card-body">
				<div class="logo" style="padding-left: 40px;position: absolute">
					<img src="${risorseStatiche}/images/pagoPA_blu.png" alt="logo" style="height: 70px">
				</div>
				<div class="pannello clearfix">
					<div class="col-xs-12 col-sm-7 mt-5">
						<p>
							<strong>IUV</strong> = Identificativo Unico di Versamento
						</p>
						<p>
							Se hai ricevuto un <strong>avviso di pagamento</strong>, usa il 
							codice avviso o lo IUV in esso contenuto per pagare su PIEMONTEPAY e per
							verificare lo stato del tuo pagamento.
						</p>
						<p>
							Su PIEMONTEPAY puoi effettuare anche <strong>pagamenti
							spontanei</strong> verso la PA: puoi fare tutto on line oppure 
							puoi creare e stampare un avviso di pagamento che ti servira' per 
							pagare presso i centri autorizzati.

						</p>
					</div>
					<div class="col-xs-12 col-sm-5">
						<p class="question">Hai ricevuto un avviso di pagamento?</p>
						<p class="puls">
							<a href="${urlPrivatePagaConIuv}" class="senzaiuv">Paga con PIEMONTEPAY <small>con IUV&nbsp;&nbsp;( ${numeroPagamenti} in attesa )</small></a>
						</p>
						<p>
							Hai gia' pagato? <a href="${urlPrivateVerifica}"
								class="b">Verifica</a>
						</p>

						<br>
						<p class="question">Vuoi effettuare un pagamento spontaneo?</p>
						<p class="puls">
							<a href="${urlPrivatePagaSenzaIuv}" class="senzaiuv">Paga
								con Piemontepay</a>
						</p>
						<p>
							Per pagare in un centro autorizzato <a
								href="${urlPrivateStampaAvviso}" class="b">Stampa
								l'avviso</a>
								
								Per pagare in un centro autorizzato <a
                                href="${urlPrivateStampaAvviso}" class="b">Stampa l'avviso</a>
                                
<!--                                 Per pagare in un centro autorizzato <a -->
<!--                                 href="#" onclick="$(document.getElementById('AlertStampaAvvisoModal')).modal().show()" -->
                                
<!--                                 class="b">Stampa l'avviso</a> -->
								
								
								
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

		<%@ include file="../../common/footer_page.jsp"%>
	</body>
</html>
