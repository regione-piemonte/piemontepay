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
	<c:set var="menu" scope="request" value="libero" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>
	<%@ include file="../common/progressione.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">

				<%@ include file="../common/messaggi.jsp"%>

				<form class="form-horizontal">
					<div class="well">
						<p>Ente: <c:out value="${riferimento.enteDesc}" /></p>
						<p>Pagamento: <c:out value="${riferimento.pagamentoDesc}" /></p>
						<p>Data operazione: <fmt:formatDate pattern="dd/MM/yyyy" value="${riferimento.dataOperazione}" /></p>
						<p>Importo: <fmt:formatNumber type="CURRENCY" currencySymbol="&euro;" value="${datiPersonali.importo}" /></p>
						<p>E-mail: <c:out value="${datiPersonali.email}" /></p>
						<p>Codice Fiscale: <c:out value="${datiPersonali.codiceFiscale}" /></p>
					</div>
					

					<c:if test="${not empty ricevutaTelematica.idRR}">
					    <div class="well">
							<p>La posizione debitoria risulta annullata tecnicamente: non ? pi? possibile pagarla.</p>
					    	<!-- p>Descrizione esito pagamento:  <c:out value="${ricevutaTelematica.descEsitoPagamento}" /></p -->
					    </div>
					</c:if>
										
						
					<div class="form-group pulsantiera">
						<div class="col-sm-12">Esegui download della Ricevuta Telematica (formato PDF o XML) oppure della receipt (solo formato PDF)</div>
						<div class="col-sm-12">
							<a class="btn btn-default" ${pulsantePdf} target="_blank"
								href="${urlRicevutaPdf}_${datiPersonali.codiceFiscale}_${riferimento.iuv}"> <i class="fa fa-file-pdf-o"
								aria-hidden="true"></i> RT Formato PDF
							</a> <a class="btn btn-default" ${pulsanteXml} target="_blank"
								href="${urlRtXml}_${datiPersonali.codiceFiscale}_${riferimento.iuv}"> <i class="fa fa-print" aria-hidden="true"></i>
								RT Formato XML
							</a>
							</a> <a class="btn btn-default" ${pulsanteReceiptPdf} target="_blank"
                                href="${urlQuietanzaPdf}_${datiPersonali.codiceFiscale}_${riferimento.iuv}"> <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                                Receipt Formato PDF
                            </a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	


	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
