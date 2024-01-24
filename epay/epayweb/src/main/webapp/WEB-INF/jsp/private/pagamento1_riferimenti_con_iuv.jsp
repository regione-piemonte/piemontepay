<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<c:url var="urlRiferimenti" value="${commonData.formAction}" />
<c:url var="urlPulsanteStampaAvviso" value="/stampe/avvisoPagamento" />

<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="../common/header_html.jsp"%>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />

<script type="text/javascript"
	src="<c:url value="/resources/js/generic.js" />"></script>

<script type="text/javascript">
	$(function() {
		options = {
			language : {
				info : 'Hai _MAX_ pagamenti attesi. Visualizzati dal _START_ al _END_.',
				infoEmpty : "Nessun pagamento atteso.",
			},
			columns : [ null, null, null, null, null, {
				orderable : false,
				searchable : false
			} ]
		};

		$('#dataTable').DataTable(options);
	});

	function pagamento(idPagamento) {
	    var pagamento = document.createElement("input");
	    pagamento.name="idPagamentoCifrato";
	    pagamento.value=idPagamento;

	    var form = document.createElement("form");
	    form.method = "POST";
	    form.action = "${urlRiferimenti}";
   	    form.appendChild(pagamento);

	    document.body.appendChild(form);
	    form.submit();
	}
</script>

</head>
<body class="interna">
	<c:set var="menu" scope="request" value="autenticato" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>
	<%@ include file="../common/progressione.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="../common/messaggi.jsp"%>

			<div class="col-md-12 col-sm-12 col-xs-12">

				<c:choose>
					<c:when
						test="${elencoPagamenti == null || elencoPagamenti.isEmpty()}">
						<div class="alert alert-info" role="alert">Nessun pagamento
							atteso.</div>
					</c:when>
					<c:otherwise>
						<table id="dataTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Scadenza</th>
									<th>Importo</th>
									<th>IUV</th>
									<th>Ente</th>
									<th>Pagamento</th>
									<th>Azione</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${elencoPagamenti}" var="pagamento">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yyyy"
												value="${pagamento.dataScadenza}" /></td>
										<td><fmt:formatNumber type="CURRENCY"
												currencySymbol="&euro;" value="${pagamento.importo}" /></td>
										<td>${pagamento.iuv}</td>
										<td>${pagamento.ente.nome}</td>
										<td>${pagamento.tipoPagamento.descrizionePortale}</td>
										<td><a class="btn btn-primary pull-left" target="_blank"
											href="${urlPulsanteStampaAvviso}/${pagamento.idPagamentoCifrato}"
											style="padding: 3px 15px !important;"> Stampa </a> <a
											class="btn btn-primary pull-right"
											onClick="pagamento('${pagamento.idPagamentoCifrato}'); return false;"
											style="padding: 3px 15px !important;"> Paga <%-- <i class="fa fa-chevron-right" aria-hidden="true"></i> --%>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="col-md-12 col-sm-12 col-xs-12 pull-right">
					<p ><strong>Devi eseguire un pagamento con un codice
						identificativo IUV non presente tra quelli dovuti?</strong></p>
					<p>
						<a  href="${urlPrivatePagaAltroRiferimentoConIuv}" class="btn-primary pull-right">
							Prosegui</a>
					</p>
				</div>

			</div>
		</div>
        <div class="row">
                <%@ include file="../common/footer_page.jsp"%>
        </div>

		
</body>
</html>
