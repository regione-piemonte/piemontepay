<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<c:url var="urlFormAction" value="${commonData.formAction}" />
<c:url var="urlRicevutaPdf" value="/stampe/ricevutaPdf" />
<c:url var="urlRicevutaXml" value="/stampe/ricevutaXml" />
<c:url var="urlQuietanzaPdf" value="/stampe/quietanzaPdf" />

<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="../common/header_html.jsp"%>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />

<link rel="stylesheet" type="text/css" href="${risorseStatiche}/ion.rangeSlider/css/ion.rangeSlider.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/ion.rangeSlider/css/ion.rangeSlider.skinHTML5.css" />
<script type="text/javascript" src="${risorseStatiche}/ion.rangeSlider/js/ion.rangeSlider.min.js"></script>

<script type="text/javascript" src="<c:url value="/resources/js/generic.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datatables_datesort.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/private_verifica.js" />"></script>

</head>

<body class="interna">
	<c:set var="menu" scope="request" value="autenticato" />
	<%@ include file="../common/header_page.jsp"%>
	<%@ include file="../common/titolo.jsp"%>


			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">


						<%@ include file="../common/messaggi.jsp"%>

						<form class="form-horizontal">
							<div class="well clearfix white">

								<div class="row">
									<div class="col-xs-6 col-sm-3">
										<button type="submit" class="btn btn-primary pull-right" onClick="getPagamenti(2, '${urlFormAction}'); return false;">Ultimi
											2 mesi</button>
									</div>
									<div class="col-xs-6 col-sm-3">
										<button type="submit" class="btn btn-primary pull-right" onClick="getPagamenti(4, '${urlFormAction}'); return false;">Ultimi
											4 mesi</button>
									</div>
									<div class="clearfix visible-xs"></div>
									<div class="col-xs-6 col-sm-3">
										<button type="submit" class="btn btn-primary pull-right" onClick="getPagamenti(6, '${urlFormAction}'); return false;">Ultimi
											6 mesi</button>
									</div>
									<div class="col-xs-6 col-sm-3">
										<a class="pull-right avanzata collapsed" role="button" data-toggle="collapse" href="#collapseDate" aria-expanded="false"
											aria-controls="collapseDate">Ricerca avanzata</a>
									</div>
								</div>
								<br />

								<div class="collapse" id="collapseDate">
									<p>Seleziona un periodo di riferimento e clicca su "cerca" per ottenere i risultati.</p>

									<div class="row">
										<div class="form-group">
											<label for="datada" class="col-sm-2 control-label">da</label>
											<div class="col-sm-2 hero-unit">
												<input type="text" class="form-control" placeholder="gg/mm/aaaa" id="datada" />
											</div>

											<label for="dataa" class="col-sm-2 control-label">a</label>

											<div class="col-sm-2 hero-unit">
												<input type="text" class="form-control" placeholder="gg/mm/aaaa" id="dataa" />
											</div>

											<div class="col-sm-4">
												<button type="submit" class="btn btn-primary pull-right" onClick="getPagamenti(0, '${urlFormAction}'); return false;">
													<i class="fa fa-search" aria-hidden="true"></i> Cerca
												</button>
											</div>
										</div>

									</div>
								</div>

								<div class="row">
									<div class="col-xs-3 col-sm-1">
										<a class="pull-left avanzata collapsed" role="button" data-toggle="collapse" href="#collapseFiltri" aria-expanded="false"
											aria-controls="collapseFiltri">Filtri</a>
									</div>
									<div class="col-xs-9 col-sm-11">Usa i filtri per affinare i risultati presenti</div>
								</div>

								<div class="collapse" id="collapseFiltri">
									<div class="row">
										<div class="form-group">
											<label for="sliderImporto" class="col-sm-2 control-label">Importo</label>
											<div class="col-sm-10">
												<input type="text" id="sliderImporto" name="sliderImporto" data-da="${importoMin}" data-a="${importoMax}" />
											</div>
										</div>
										<div class="form-group">
											<label for="esito" class="col-sm-2 control-label">Esito</label>
											<div class="col-sm-2">
												<select class="form-control" id="esito">
													<option></option>
													<c:forEach items="${esiti}" var="esito">
														<option>${esito}</option>
													</c:forEach>
												</select>
											</div>

											<label for="ente" class="col-sm-2 control-label">Ente</label>
											<div class="col-sm-2">
												<select class="form-control" id="ente">
													<option></option>
													<c:forEach items="${enti}" var="ente">
														<option>${ente}</option>
													</c:forEach>
												</select>
											</div>

											<label for="dettaglio" class="col-sm-2 control-label">Dettaglio</label>
											<div class="col-sm-2">
												<select class="form-control" id="dettaglio">
													<option></option>
													<c:forEach items="${dettagli}" var="dettaglio">
														<option>${dettaglio}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>

						</form>

						<div class="row">

							<h2>Risultati della ricerca</h2>

							<div class="table-responsive">
								<table id="dataTable" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>Data</th>
											<th>Importo</th>
											<th>Esito</th>
											<th>Ente</th>
											<th>Dettaglio</th>
											<th>RICEVUTA TELEMATICA DI PAGAMENTO o RECEIPT</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${elencoPagamenti}" var="pagamento">
											<tr>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pagamento.dataStatoCorrente}" /></td>
												<td><fmt:formatNumber type="CURRENCY" currencySymbol="&euro;" value="${pagamento.importo}" /></td>
												<td>${pagamento.descStatoCorrente}</td>
												<td>${pagamento.ente.nome}</td>
												<td>${pagamento.tipoPagamento.descrizionePortale}</td>
												<td>
													<button type="submit" ${pagamento.pulsantePdf}
														onClick="printRt('${urlRicevutaPdf}', '${pagamento.pagatore.codiceFiscale}', '${pagamento.iuv}', ${pagamento.idStatoCorrente}); return false;"
														title="Scarica PDF" class="btn btn-primary pull-left" style="padding: 3px 15px !important;">
														<i class="fa fa-file-pdf-o fa-lg" aria-hidden="true"></i>RT PDF
													</button>
													<button type="submit" ${pagamento.pulsanteXml}
														onClick="printRt('${urlRicevutaXml}', '${pagamento.pagatore.codiceFiscale}', '${pagamento.iuv}', ${pagamento.idStatoCorrente}); return false;"
														title="Scarica XML" class="btn btn-primary pull-right" style="padding: 3px 15px !important;">
														<i class="fa fa-file-code-o fa-lg" aria-hidden="true"></i>RT XML
													</button>
													<button type="submit" ${pagamento.pulsanteReceiptPdf}
                                                        onClick=""
                                                        title="Receipt PDF" class="btn btn-primary pull-right" style="padding: 3px 15px !important;">
                                                        <i class="fa fa-file-code-o fa-lg" aria-hidden="true"></i> Receipt PDF
                                                    </button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		



	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
