<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<c:url var="urlRiferimenti" value="${commonData.formAction}" />
<c:url var="urlTasseAjax" value="/ajax/tasse" />
<c:url var="urlListeEnti" value="/ajax/listaEnti" />

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
		var ente = '#enteId';
		var enteView = '#enteView';
		var pagamento = '#pagamentoId';
		var pagamentoView = '#pagamentoView';
		var pagamentoBtn = '#pagamentoBtn';

		$(ente).val("");
		$(enteView).val("");
		$(pagamento).val("");
		$(pagamentoView).val("");

		$(pagamentoView).attr('disabled', 'disabled');
		$(pagamentoBtn).attr('disabled', 'disabled');

		$(enteView)
				.autocomplete(
						{
							minlength : 3,
							delay : 300,
							source : function(request, response) {
								if (request.term.length >= 3) {
									$.ajax({
										type : "GET",
										url : "${urlListeEnti}",
										data : "nomeEnte=" + request.term,
										dataType : 'json',
										success : function(data) {
											if (data.length < 1) {
												$("#enteMessage").text(
														"Nessun risultato");
											} else {
												$("#enteMessage").empty();
												response($.map(data, function(
														item) {
													return {
														value : item.nome,
														idEnte : item.idEnte
													}
												}));
											}
										}
									});
								}
							},
							change : function(event, ui) {
								if (!$(enteView).val()) {
									$(pagamento).val("");
									$(pagamentoView).val("");
									$(pagamento).empty();
									$(pagamentoView).empty();
									$(ente).val("");

									$(pagamentoView).attr('disabled',
											'disabled');
									$(pagamentoBtn)
											.attr('disabled', 'disabled');
								}
							},
							select : function(event, ui) {

								$(pagamento).val("");
								$(pagamentoView).val("");
								$(pagamento).empty();
								$(pagamentoView).empty();
								pagamentoOld = $('#oldPagamentoId').val();

								if (ui.item.label) {
									$
											.ajax({
												type : "GET",
												url : "${urlTasseAjax}",
												data : "idEnte="
														+ ui.item.idEnte,
												dataType : 'json',
												success : function(data) {
													if (data.length < 1) {
														$("#pagamentoMessage")
																.text(
																		"Nessun risultato");
													} else {
														$("#pagamentoMessage")
																.empty();
														$
																.each(
																		data,
																		function(
																				index,
																				tipoPagamento) {
																			$(
																					pagamentoView)
																					.append(
																							'<option value="'
																									+ tipoPagamento.idTipoPagamento
																									+ '" '
																									+ (tipoPagamento.idTipoPagamento == pagamentoOld ? 'selected'
																											: '')
																									+ ' >'
																									+ tipoPagamento.descrizionePortale
																									+ '</option>');
																		});

														$(ente).val(
																ui.item.idEnte);
														$(pagamentoView)
																.removeAttr(
																		'disabled');
														$(pagamentoBtn)
																.removeAttr(
																		'disabled');

														$
																.widget(
																		"custom.combobox",
																		{
																			_create : function() {
																				var self = this, select = this.element
																						.show(), selected = select
																						.children(":selected"), value = selected
																						.val() ? selected
																						.text()
																						: "";
																				$(
																						pagamentoView)
																						.val(
																								value)
																						.autocomplete(
																								{
																									delay : 0,
																									minLength : 0,
																									source : function(
																											request,
																											response) {
																										var matcher = new RegExp(
																												$.ui.autocomplete
																														.escapeRegex(request.term),
																												"i");
																										response(select
																												.children(
																														"option")
																												.map(
																														function() {
																															var text = $(
																																	this)
																																	.text();
																															if (this.value
																																	&& (!request.term || matcher
																																			.test(text)))
																																return {
																																	label : text
																																			.replace(
																																					new RegExp(
																																							"(?![^&;]+;)(?!<[^<>]*)("
																																									+ $.ui.autocomplete
																																											.escapeRegex(request.term)
																																									+ ")(?![^<>]*>)(?![^&;]+;)",
																																							"gi"),
																																					"<strong>$1</strong>"),
																																	value : text,
																																	option : this
																																};
																														}));
																									},
																									select : function(
																											event,
																											ui) {
																										ui.item.option.selected = true;
																										self
																												._trigger(
																														"selected",
																														event,
																														{
																															item : ui.item.option
																														});
																									},
																									change : function(
																											event,
																											ui) {
																										if (!ui.item) {
																											var matcher = new RegExp(
																													"^"
																															+ $.ui.autocomplete
																																	.escapeRegex($(
																																			this)
																																			.val())
																															+ "$",
																													"i"), valid = false;
																											select
																													.children(
																															"option")
																													.each(
																															function() {
																																if ($(
																																		this)
																																		.text()
																																		.match(
																																				matcher)) {
																																	this.selected = valid = true;
																																	return false;
																																}
																															});
																											if (!valid) {
																												$(
																														this)
																														.val(
																																"");
																												select
																														.val("");
																												$(
																														pagamentoView)
																														.data(
																																"autocomplete").term = "";
																												return false;
																											}
																										}
																									}
																								});

																				$(
																						pagamentoView)
																						.data(
																								"autocomplete")._renderItem = function(
																						ul,
																						item) {
																					return $(
																							"<li></li>")
																							.data(
																									"item.autocomplete",
																									item)
																							.append(
																									"<a>"
																											+ item.label
																											+ "</a>")
																							.appendTo(
																									ul);
																				};

																				$(
																						pagamentoBtn)
																						.removeAttr(
																								'disabled')
																						.click(
																								function() {
																									if ($(
																											pagamentoView)
																											.autocomplete(
																													"widget")
																											.is(
																													":visible")) {
																										$(
																												pagamentoView)
																												.autocomplete(
																														"close");
																										return;
																									}

																									$(
																											this)
																											.blur();

																									$(
																											pagamentoView)
																											.autocomplete(
																													"search",
																													"");
																									$(
																											pagamentoView)
																											.focus();
																								});
																			},

																			destroy : function() {
																				$(
																						pagamentoView)
																						.remove();
																				this.element
																						.show();
																				$.Widget.prototype.destroy
																						.call(this);
																			}
																		});

														$(pagamentoView)
																.combobox(
																		{
																			selected : function(
																					e,
																					ui) {
																				$(
																						pagamento)
																						.val(
																								ui.item.value);
																			}
																		});
													}
												},
												error : function(xhr, status,
														error) {
													alert('Si e verificato un errore.');
												}
											});
								}
							}
						});
	});

	$(function() {
		$('[data-toggle="popover"]').popover()
	});
</script>


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

				<p class="nota">* Dati obbligatori</p>

				<form:form class="form-horizontal" method="POST"
					action="${urlRiferimenti}" modelAttribute="riferimento">
					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="enteId" cssClass="control-label"
									cssErrorClass="control-label has-error">* Ente</form:label>
								<a tabindex="0" data-toggle="popover" data-trigger="focus"
									data-html="true"
									data-content="Il filtro 'Ente' prevede il suggerimento degli Enti disponibili a partire dal terzo carattere digitato all&rsquo;interno del campo.<br /><br />
											Una volta selezionato l&rsquo;Ente, verra' abilitato anche il filtro successivo dedicato ai 'Pagamenti' (utilizzabile sia come il filtro precedente con il suggerimento a partire dal terzo carattere sia come menu a tendina selezionando l&rsquo;apposita freccia presente all&rsquo;interno del campo)">
									<i class="fa fa-question-circle-o"></i>
								</a>
								<form:input path="enteId" cssClass="form-control"
									cssErrorClass="form-control has-error" type="hidden" />
								<input id="enteView" class="form-control" />
								<form:errors path="enteId" cssClass="help-block has-error"
									element="small" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div class="form-group">
								<form:label path="pagamentoId" cssClass="control-label"
									cssErrorClass="control-label has-error">* Pagamento</form:label>
								<input type="hidden" id="oldPagamentoId"
									value="${riferimento.pagamentoId}" />
								<div class="input-group" style="z-index: 0;">
									<input id="pagamentoView" class="form-control"
										cssErrorClass="form-control has-error" />
									<form:input path="pagamentoId" cssClass="form-control"
										cssErrorClass="form-control has-error" type="hidden" />
									<span class="input-group-btn">
										<button id="pagamentoBtn" class="btn" type="button">
											<i class="fa fa-chevron-down"></i>
										</button>
									</span>
								</div>
								<form:errors path="pagamentoId" cssClass="help-block has-error"
									element="small" />
							</div>
						</div>
					</div>

					<%@ include file="../common/pulsantiNavigazione.jsp"%>
				</form:form>

			</div>

		</div>
	</div>


	<%@ include file="../common/footer_page.jsp"%>
</body>
</html>
