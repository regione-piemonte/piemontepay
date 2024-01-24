<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<c:url var="urlRiferimentiPagamento" value="${commonData.formAction}" />

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

			<spring:hasBindErrors name="riferimentoSenzaCodiceFiscale">
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
				<form:form class="form-horizontal" method="POST"
					action="${urlRiferimentiPagamento}" modelAttribute="riferimentoSenzaCodiceFiscale">
					<p>
						Riferimenti pagamento del Codice Avviso / IUV : <strong>${riferimentoSenzaCodiceFiscale.iuv}</strong>
					</p>
					<form:input id="iuv" path="iuv" type="hidden"
						value="${riferimento.iuv}" />
					<br>
					<c:forEach items="${riferimentoSenzaCodiceFiscale.riferimentiPagamento}"
						var="riferimentoPagamento" varStatus="pStatus">
						<c:if test="${not empty riferimentoPagamento.nome}">
							<div class="row">
								<div class="form-group">
									<div class="col-sm-4 col-md-2 col-lg-2">
										<form:label path="riferimentiPagamento[${pStatus.index}].nome"
											id="riferimentiPagamento[${pStatus.index}].nome"
											style="text-align: left" cssClass="control-label" 
											cssErrorClass="control-label has-error">${riferimentoPagamento.nome}*</form:label>
										<form:input path="riferimentiPagamento[${pStatus.index}].nome"
											id="riferimentiPagamento[${pStatus.index}].nome"
											type="hidden" value="${riferimentoPagamento.nome}" />
									</div>
									<div class="col-sm-6 col-md-4 col-lg-4">
										<form:input
											path="riferimentiPagamento[${pStatus.index}].valore"
											id="riferimentiPagamento[${pStatus.index}].valore"
											cssClass="form-control"
											cssErrorClass="form-control has-error" />
										<form:errors
											path="riferimentiPagamento[${pStatus.index}].valore"
											cssClass="help-block has-error" element="small" />
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<%@ include file="../common/pulsantiNavigazione.jsp"%>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
