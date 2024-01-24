<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<script>
	<c:url value="/flussi/forza-elaborazione" var="rielaboraFlussiUrl" />

	var dataTableRisultati = null;

	var dateTimeFormatter = new Intl.DateTimeFormat('it-IT', {
		year: 'numeric', month: '2-digit', day: '2-digit',
		hour: 'numeric', minute: 'numeric', second: 'numeric',
		hour12: false
	});

	var currencyEURFormatter = new Intl.NumberFormat('it-IT', {
		style: 'currency',
		currency: 'EUR',
		minimumFractionDigits: 2
	});
	
	function displayAlert(message, level) {
		var messageDiv = $("<div class='alert'><button type='button' class='close' data-dismiss='alert'>x</button><p><span></span></p></div>");
		messageDiv.find("span").html(message);
		messageDiv.addClass("alert-" + level);
		messageDiv.appendTo($('#alert-div'))
	};

	function clearAllAlerts() {
		$(".alert").remove();
	}

	// Standard
	var dataTableOptions = {
		pageLength : 5,
		filter : false,
		destroy : true,
		lengthMenu : [ 5, 10, 15, 20 ],
		language : {
			processing : 'Elaborazione in corso...',
			zeroRecords : 'Nessun risultato trovato',
			infoFiltered : ' - filtrato da _MAX_ risultati',
			infoEmpty : '0 risultati',
			info : '_START_ - _END_ di _TOTAL_ risultati',
			lengthMenu : '_MENU_ record per pagina',
			emptyTable : 'Nessun risultato trovato',
			paginate : {
				first : 'Inizio',
				last : 'Fine',
				next : 'Succ.',
				previous : 'Prec.'
			}
		}
	};
	$(function() {
		$(".datepicker").datepicker({
			format : 'dd/mm/yyyy',
			language : 'it'
		});

		$("#results input[type=checkbox]").on("change", function() {
			selezioneCambiata();
		});

		<c:if test="${not empty lista_risultati}">
			$(document).ready(function() {
				 $("#initTable").val("true");
					if (!dataTableRisultati) {
						// create data table
						dataTableRisultati = $("#tableResults").dataTable(
								optsDaUtilizzare);
						dataTableRisultati.fnPageChange(0);
					} else {
						dataTableRisultati.fnDraw();
					}
			});
		 </c:if>
		 
		var opts = {
			language : {
				zeroRecords : 'Nessun esito trovato',
				emptyTable : 'Nessun esito trovato'
			},
			"ordering" : true,
			"order" : [ [ 1, "desc" ] ],
			"serverSide" : true,
			"processing" : true,
			"stateSave" : true,
			"stateLoadParams" : function(settings, data) {
				if ($("#initTable").val() == "true") {
					return false;
				}
			},
			"columnDefs" : [
					{
						"data" : null,
						"render" : function(data, type, row, meta) {
							if(row.permettiRielaborazione === true){
// 								return "<input type='checkbox' name='selezioneIdFlusso" + data.id + "' value='" + data.identificativoFlusso + "' class='rielaborazioneFlussi'/>";
								return ""; // valutare sicurezza: access="hasRole('FORZATURA_ELABORAZIONE')"
							} else {
								return "";
							}
						},
						"orderable" : false,
						"targets" : 0
					},
					{
						"data" : "identificativoFlusso",
						"orderable" : true,
						"targets" : 1
					},
					{
						"data" : "dataOraFlusso",
						"render" : function(data, type, row, meta) {
							return (data != null) ? dateTimeFormatter
									.format(new Date(data)) : "";
						},
						"orderable" : true,
						"targets" : 2
					},
					{
						"data" : "identificativoIstitutoRicevente",
						"orderable" : true,
						"targets" : 3
					},
					{
						"data" : "identificativoPsp",
						"orderable" : true,
						"targets" : 4
					},
					{
						"data" : "importoTotalePagamenti",
						"orderable" : true,
				    	"render" : function(data, type, row, meta) {
		                    return (data != null) ? currencyEURFormatter.format(data) : "";
		                },
						"targets" : 5
					},
					{
						"data" : "numeroTotalePagamenti",
						"orderable" : true,
						"targets" : 6
					},
					{
						"data" : "Data Inserimento",
						"render" : function(data, type, row, meta) {
							return (data != null) ? dateTimeFormatter
									.format(new Date(data)) : "";
						},
						"orderable" : true,
						"targets" : 7
					}, {
						"data" : "contatoreTentativi",
						"orderable" : true,
						"targets" : 8
					}, {
						"data" : "descrizioneStato",
						"orderable" : true,
						"targets" : 9
					}, {
						"data" : "flussoPlurintermediato",
						"orderable" : true,
						"targets" : 10
					} , {
						"data" : null,
						"render" : function(data, type, row, meta) {
								return "<div id='tableRowActions' class='btn-group'><button class='btn btn-sm dropdown-toggle' data-toggle='dropdown'>Azioni<span class='caret'></span></button><ul class='dropdown-menu pull-right'><li><a href='/epaymodricweb/flussi/" + row.id + "/sintesi' id='visualizzaFlussoAction'>visualizza flusso</a></li></ul></div>";
						},
						"orderable" : false,
						"targets" : 11
					}],
			"ajax" : {
				"url" : "/epaymodricweb/flussi/ricercaJSON",
				"dataType" : 'json',
				"type" : "POST",
				"data" : function(data) {
					var newData = {};
					newData.draw = data.draw;
					newData.start = Math.floor(data.start / data.length);
					newData.length = data.length;
					newData.identificativoFlusso = $("input[type='text'][name='identificativoFlusso']").val();
					newData.idCodVersamento = $("select[name='idCodVersamento']").val();
					newData.iuv = $("input[type='text'][name='iuv']").val();
					newData.statoFlusso = $("select[name='statoFlusso']").val();
					newData.dataElaborazioneDa = $("input[type='text'][name='dataElaborazioneDa']").val();
					newData.dataElaborazioneA = $("input[type='text'][name='dataElaborazioneA']").val();
					newData.dataRicezioneDa = $("input[type='text'][name='dataRicezioneDa']").val();
					newData.dataRicezioneA = $("input[type='text'][name='dataRicezioneA']").val();
					newData.psp = $("input[type='text'][name='psp']").val();
					if (data.order.length > 0) {
		    			newData.sortingCol = data.columns[ data.order[0].column ].data;
		    			newData.sortingDir = data.order[0].dir;
		    		}
					return newData;
				}
			}
		};

		var optsDaUtilizzare = $.extend(true, {}, dataTableOptions, opts);

		function selezioneCambiata() {
			var button = $(".btnRielabora");
			if ($("#results input[type=checkbox]:checked").size() > 0) {
				button.removeAttr("disabled");
			} else {
				button.attr("disabled", true);
			}
		}

		$('#results').on('draw.dt', function() {
			selezioneCambiata();
		});

		$(".btnRielabora")
				.on(
						"click",
						function() {
							var checkedValues = $(
									'input.rielaborazioneFlussi:checked').map(
									function() {
										return this.value;
									}).get();
							$
									.ajax(
											{
												type : "GET",
												url : "${rielaboraFlussiUrl}",
												data : {
													identificativiFlussoDaRielaborare : checkedValues
												},
												contentType : "application/json; charset=utf-8"

											})
									.done(
											function(data) {
												if (data == null
														|| data == undefined) {
													mostraErrore("Non e' stato possibile forzare i flussi richiesti");
												} else if (data.codice !== "000") {
													mostraErrore("Errore durante la forzatura dei flussi: "
															+ data.descrizione);
												} else {
													mostraInfoEdEseguiLogica(
															'Rielaborazione richiesta correttamente. Sar&agrave; rieseguita nei tempi stabiliti',
															function() {
																location
																		.reload(true);
															});
												}
											})
									.fail(
											function() {
												mostraErrore("Non e' stato possibile forzare i flussi richiesti");
											});

						});
	});
</script>
