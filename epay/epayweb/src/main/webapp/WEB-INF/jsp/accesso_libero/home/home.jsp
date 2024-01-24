<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="../../common/tag_lib.jsp"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="../../common/header_html.jsp"%>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-home.css" />
<script src="<c:url value="/resources/js/home.js" />"></script>
</head>
<body>
	<c:set var="menu" scope="request" value="homepage" />
	<c:set var="tipoAccesso" scope="request" value="accessoLibero" />
	
	<%@ include file="../../common/covid-19HelpModal.jsp"%>
<%-- 	<%@ include file="../../common/alertStampaAvvisoModal.jsp"   %> --%>
        </script>
	<header>
		<div class="container-fluid">
			<a class="" href="${urlHomePage}"> <img alt="PiemontePay"
				src="${risorseStatiche}/images/logo1.svg" title="PiemontePay"
				class="logo">
			</a>
		</div>
	</header>
	<div class="container">
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
							Se hai ricevuto un <strong>avviso di pagamento</strong>, usa il codice avviso 
							o lo IUV in esso contenuto per pagare su PIEMONTEPAY e per verificare lo stato del tuo pagamento. 
						</p>
						<p>
							Su PIEMONTEPAY puoi effettuare anche <strong>pagamenti
							spontanei</strong> verso la PA: puoi fare tutto on line. Puoi creare e stampare un avviso di 
							pagamento che ti servira' per pagare presso i centri autorizzati. La funzione di stampa e' gia' disponibile. 
						</p>
					</div>
					<div class="col-xs-12 col-sm-5">
						<p class="question">Hai ricevuto un avviso di pagamento?</p>
						<p class="puls">
							<a
								href="/epayweb/accessoLibero/pagaConIuv"
								class="senzaiuv">Paga con Piemontepay <small>con IUV</small></a>
						</p>
						<p>
							Hai gia' pagato? <a
								href="/epayweb/accessoLibero/verifica"
								class="b">Verifica</a>
						</p>
						<br>
						<p class="question">Vuoi effettuare un pagamento spontaneo?</p>
						<p class="puls">
							<a
								href="/epayweb/accessoLibero/pagaSenzaIuv"
								class="senzaiuv">Paga con Piemontepay</a>
						</p>
						<p>
							Per pagare in un centro autorizzato <a
								href="/epayweb/accessoLibero/stampaAvviso"
								
								class="b">Stampa l'avviso</a>
								
<!-- 								Per pagare in un centro autorizzato <a -->
<!--                                 href="#" onclick="$(document.getElementById('AlertStampaAvvisoModal')).modal().show()" -->
                                
<!--                                 class="b">Stampa l'avviso</a> -->
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row pannello clearfix">
					<div class="col-xs-12 col-sm-12 col-md-7">
						<p>
							E' possibile accedere alla tua "AREA PERSONALE" utilizzando le credenziali SPID, CIE, CNS.
							<br>
							Qui trovi lo storico dei tuoi pagamenti e sei facilitato nella compilazione di un nuovo pagamento poiche' puoi evitare di inserire, ogni volta che paghi, una serie di dati personali.</strong>
						</p>
						<p>
							Se non hai delle credenziali puoi richiedere <strong>SPID</strong>
							<br>
							<a href="https://www.spid.gov.it/richiedi-spid" class="register">(https://www.spid.gov.it/richiedi-spid)</a>
						</p>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-5">
						<p class="question">Sei gia' registrato?</p>
						<p class="puls">
							<a href="/epayweb/private/home" class="coniuv">ACCEDI alla tua AREA PERSONALE</a>
						</p>
					</div>

				</div>

			</div>
		</div>
	</div>
	<div class="container-fluid" id="section1">
		<div class="row-fluid">
			<div class="container">
				<div class="row">
					<div class="intro">
						<h2>Cos'è PiemontePAY?</h2>
						<p>
							E' il luogo dove puoi pagare bollette, tasse o altri pagamenti
							dovuti alla <strong>Pubblica Amministrazione piemontese</strong> in modo
							facile, intuitivo e veloce, senza trascurare la sicurezza delle
							transazioni.
						</p>
						<p>
							Puoi farlo con un accesso libero oppure richiedendo le credenziali <strong>SPID</strong> <a href="https://www.spid.gov.it/richiedi-spid" target="_blank">(https://www.spid.gov.it/richiedi-spid)</a>.
						</p>
						<p>
							Perche' registrarsi? Perche' con la registrazione puoi accedere alla tua area personale e tenere sempre sotto controllo lo storico dei tuoi pagamenti.
						</p>
						<p>
							<strong><a href="http://www.sistemapiemonte.it/cms/privati/piemontepay" target="_blank">PiemontePAY</a></strong> aderisce al circuito nazionale <strong>pagoPA</strong>.
						</p>
						<p>
							<strong>pagoPA</strong> <a href="https://www.pagopa.gov.it" target="_blank">(https://www.pagopa.gov.it)</a> e' un'iniziativa nazionale, gestica dalla societa' pagoPA S.p.A, che consente a cittadini e imprese di pagare in modalita' elettronica la Pubblica Amministrazione.
						</p>
						<p>
							PER MALFUNZIONAMENTI compilare <a
								href="http://www.sistemapiemonte.it/cms/assistenza/index_ass_ppay_privati.php"
								target="_blank"> il modulo di segnalazione</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid" id="section3">
		<div class="row-fluid">
			<div class="container">
				<div class="row">
					<div class="intro">
						<h2>Puoi eseguire pagamenti ai seguenti enti</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-10 col-md-5 col-lg-5">
						<label for="pagamentiSpontanei" class="tasse">Enti a cui
							si possono fare pagamenti spontanei, ossia non noti a priori
							all'ente creditore</label> <select id="pagamentiSpontanei"
							class="form-control">
						</select>
					</div>
					<div class="col-sm-10 col-md-5 col-lg-5">
						<label for="pagamentiConIuv" class="tasse">Enti a cui si
							possono fare pagamenti con IUV, ossia gia' noti all'ente creditore</label>
						<select id="pagamentiConIuv" class="form-control">
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../common/footer_page.jsp"%>
</body>
</html>
