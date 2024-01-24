/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	$("#ripristinaTestataAction").click(function() {
		$("#initTable").val("true");
		$("#formId").attr("action", "entry-visualizza-flusso-posizionidebitoriedaaggiornare.do");
	});
	$("#salvaTestataAction").click(function() {
		$("#initTable").val("true");
		$("#formId").attr("action", "salva-testata-flusso-posizionidebitoriedaaggiornare.do");
	});
	$("#backToHomeAction1").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToHomeForm");
	});
	$("#backToHomeAction2").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToHomeForm");
	});
	$("#backToRicercaListeAggiornamentoPosizioniDebitorieAction1").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToRicercaListeAggiornamentoPosizioniDebitorieForm");
	});
	$("#backToRicercaListeAggiornamentoPosizioniDebitorieAction2").click(function() {
		operativita = $("#operativita").val();
		confirmBackAction(operativita == "INSERISCI" || operativita == "MODIFICA", "#backToRicercaListeAggiornamentoPosizioniDebitorieForm");
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
		    	"data" : "tipoAggiornamento.descrizione",
		    	"orderable" : true,
		    	"targets" : 0
		    },
		    {
		    	"data" : "idPosizioneDebitoriaEsterna",
		    	"orderable" : true,
		    	"targets" : 1
		    },
 		    {
		    	"data" : "motivazione",
		    	"orderable" : true,
				"targets" : 2
		    },
		    {
		    	"data" : "codAvviso",
		    	"orderable" : true,
				"targets" : 3
		    },
		    {
		    	"data" : "esito",
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

					var dettaglioPosizioneDebitoriaDaAggiornareAction = clonedRow.find("#dettaglioPosizioneDebitoriaDaAggiornareAction");
					if (dettaglioPosizioneDebitoriaDaAggiornareAction.length > 0) {
						dettaglioPosizioneDebitoriaDaAggiornareAction.attr("data-row-id", rowData["id"]).click(function() {
							var form = $("#" + $(this).attr("data-form-id"));
							form.find("input[name='idPosizioneDebitoriaDaAggiornare']").val($(this).attr("data-row-id"));
							form.submit();
						});
						numActions++;
					}

					var modificaPosizioneDebitoriaDaAggiornareAction = clonedRow.find("#modificaPosizioneDebitoriaDaAggiornareAction");
					if (modificaPosizioneDebitoriaDaAggiornareAction.length > 0) {
						modificaPosizioneDebitoriaDaAggiornareAction.attr("data-row-id", rowData["id"]).click(function() {
							var form = $("#" + $(this).attr("data-form-id"));
							form.find("input[name='idPosizioneDebitoriaDaAggiornare']").val($(this).attr("data-row-id"));
							form.find("input[name='idCodVersamento']").val($("#testata_idCodVersamento").val());
							form.submit();
						});
						numActions++;
					}

					var eliminaPosizioneDebitoriaDaAggiornareAction = clonedRow.find("#eliminaPosizioneDebitoriaDaAggiornareAction");
					if (eliminaPosizioneDebitoriaDaAggiornareAction.length > 0) {
						eliminaPosizioneDebitoriaDaAggiornareAction.attr("data-row-id", rowData["id"]).click(function() {
							var form = $("#" + $(this).attr("data-form-id"));
							var idPosizioneDebitoriaDaAggiornare = $(this).attr("data-row-id");

							confirmAction("Sei sicuro di voler eliminare la posizione debitoria esterna: " + rowData["idPosizioneDebitoriaEsterna"] + "?", function() {
								// confirm...
								clearAllAlerts();
								$("#confirm-action-modal").modal("hide");
								
								// submit
								form.find("input[name='idPosizioneDebitoriaDaAggiornare']").val(idPosizioneDebitoriaDaAggiornare);
								form.submit();
							});
						});
						numActions++;
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
			"url" : "/epaypaweb/visualizza-flusso-posizionidebitoriedaaggiornare-json.do",
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
