/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	$("#ripristinaTestataAction").click(function() {
		$("#initTable").val("true");
		$("#formId").attr("action", "entry-visualizza-listadicarico.do");
	});
	$("#salvaTestataAction").click(function() {
		$("#initTable").val("true");
		$("#formId").attr("action", "salva-testata-listadicarico.do");
	});
	$("#backToHomeAction1").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToHomeForm");
	});
	$("#backToHomeAction2").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToHomeForm");
	});
	$("#backToRicercaListadicaricoAction1").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToRicercaListadicaricoForm");
	});
	$("#backToRicercaListadicaricoAction2").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToRicercaListadicaricoForm");
	});

	var opts = {
		language : {
			zeroRecords : 'Nessun elemento',
			emptyTable : 'Nessun elemento'
		},
		"ordering" : true,
		"serverSide" : true,
		"processing" : true,
		"stateSave" : false, // N.B. deve essere false, altrimenti non imposta displayStart
		"displayStart" : $("#initTable").val() == "true" ? 0 : parseInt($("#param_restartFrom").val()),
		"order" : [[$("#param_sortingIdx").val(), $("#param_sortingDir").val()]],
		"pageLength" : $("#param_pageLength").val(),
		"columnDefs" : [
		    {
		    	"data" : "IUV",
		    	"orderable" : true,
		    	"targets" : 0
		    },
 		    {
		    	"data" : "importoTotale",
		    	"render" : function(data, type, row, meta) {
                    return (data != null) ? currencyEURFormatter.format(data) : "";
                },
		    	"orderable" : true,
				"targets" : 1
		    },
		    {
		    	"data" : "desCausaleVersamento",
		    	"orderable" : true,
				"targets" : 2
		    },
		    {
		    	"data" : "dataScadenza",
		    	"render" : function(data, type, row, meta) {
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
		    	},
		    	"orderable" : true,
				"targets" : 3
		    },
		    {
		    	"data" : "soggettoDebitore.idUnivocoFiscale",
		    	"orderable" : true,
		    	"targets" : 4
		    },
		    {
		    	"data" : null,
		    	"orderable" : false,
				"class" : "tab_Right",
		    	"render" : function(data, type, row, meta) {
		    		return "<span class='hide'></span>";
		    	},
		        "createdCell" : function(td, cellData, rowData, row, col) {
					var clonedRow = $("#tableRowActions").clone();
					clonedRow.attr("id", "tableRowActions_" + rowData["id"]);
					
					var numActions = 0;

					var dettaglioPosizioneDebitoriaAction = clonedRow.find("#dettaglioPosizioneDebitoriaAction");
					if (dettaglioPosizioneDebitoriaAction.length > 0) {
						dettaglioPosizioneDebitoriaAction.attr("data-row-id", rowData["id"]).click(function() {
							var form = $("#" + $(this).attr("data-form-id"));
							form.find("input[name='idPosizioneDebitoria']").val($(this).attr("data-row-id"));
							form.submit();
						});
						numActions++;
					}
					
					var modificaPosizioneDebitoriaAction = clonedRow.find("#modificaPosizioneDebitoriaAction");
					if (modificaPosizioneDebitoriaAction.length > 0) {
						modificaPosizioneDebitoriaAction.attr("data-row-id", rowData["id"]).click(function() {
							var form = $("#" + $(this).attr("data-form-id"));
							form.find("input[name='idPosizioneDebitoria']").val($(this).attr("data-row-id"));
							form.find("input[name='idCodVersamento']").val($("#testata_idCodVersamento").val());
							form.find("input[name='strDataInizioValidita']").val($("#testata_strDataInizioValidita").val());
							form.find("input[name='strDataFineValidita']").val($("#testata_strDataFineValidita").val());
							form.submit();
						});
						numActions++;
					}

					var eliminaPosizioneDebitoriaAction = clonedRow.find("#eliminaPosizioneDebitoriaAction");
					if (eliminaPosizioneDebitoriaAction.length > 0) {
						eliminaPosizioneDebitoriaAction.attr("data-row-id", rowData["id"]).click(function() {
							var form = $("#" + $(this).attr("data-form-id"));
							var idPosizioneDebitoria = $(this).attr("data-row-id");

							confirmAction("Sei sicuro di voler eliminare la posizione debitoria del soggetto debitore: " + rowData["soggettoDebitore"]["idUnivocoFiscale"] + "?", function() {
								// confirm...
								clearAllAlerts();
								$("#confirm-action-modal").modal("hide");
								
								// submit
								form.find("input[name='idPosizioneDebitoria']").val(idPosizioneDebitoria);
								form.submit();
							});
						});
						numActions++;
					}

					var stampaAvvisoPagamentoAction = clonedRow.find("#stampaAvvisoPagamentoAction");
					if (stampaAvvisoPagamentoAction.length > 0) {
						if (rowData["IUV"] !== null) {
							stampaAvvisoPagamentoAction.attr("data-row-id", rowData["id"]).click(function() {
								// please wait...
								var token = showModalPleaseWait();    

								// submit
								var form = $("#" + $(this).attr("data-form-id"));
								form.find("input[name='iuv']").val(rowData["IUV"]);
								form.find("input[name='idPosizioneDebitoria']").val($(this).attr("data-row-id"));
								form.find("input[name='pleaseWaitTokenValue']").val(token);
								form.submit();
							});
							numActions++;
						} else {
							stampaAvvisoPagamentoAction.addClass("hide");
						}
					}

					if (numActions == 0) {
						clonedRow.find("button").attr("disabled", "true");
					}
					clonedRow.removeClass("hide");
					$(td).append(clonedRow);
		        },
		    	"targets" : 5
		    }
		],
		"ajax" : {
			"url" : "/epaypaweb/visualizza-listadicarico-json.do",
			"dataType" : 'json',
			"type" : "POST",
			"data" : function(data) {
				var newData = {};

				newData.draw = data.draw;
				newData.start = data.start;
				newData.length = data.length;

	            newData.idFlusso = $("#param_idFlusso").val();

	    		if (data.order.length > 0) {
	    			newData.sortingCol = data.columns[ data.order[0].column ].data;
	    			newData.sortingDir = data.order[0].dir;
	    		}
	    		return newData;
			}
		}
	};

	var optsDaUtilizzare = $.extend(true, {}, dataTableOptions, opts);

	// create data table
	dataTableRisultati = $("#tabellaRisultati").dataTable(optsDaUtilizzare);

	// hide memo panel
//	$("#collapseMemoPanel").collapse("hide");

	// messaggio di esito di una elaborazione
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}
})
