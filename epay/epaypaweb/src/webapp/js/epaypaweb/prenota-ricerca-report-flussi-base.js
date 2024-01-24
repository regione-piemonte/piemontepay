/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var dataTableRisultati = null;

	function doSearch(urlToCall) {

		clearAllAlerts();

		var val_nomeReport = $("input[type='text'][name='nomeReport']").val();
		var val_dataRicezioneInizio = $("input[type='text'][name='dataRicezioneInizio']").val();
		var val_dataRicezioneFine = $("input[type='text'][name='dataRicezioneFine']").val();
		var val_codiceDescrizionePSP = $("input[type='text'][name='codiceDescrizionePSP']").val();
		var val_tipoFormato = $("#param_tipoFormato").val();

		$.ajax({
			type : "POST",
			url : urlToCall,
			data : {
				"nomeReport" : val_nomeReport,
				"dataRicezioneInizio" : val_dataRicezioneInizio,
				"dataRicezioneFine" : val_dataRicezioneFine,
				"codiceDescrizionePSP" : val_codiceDescrizionePSP,
				"tipoFormato" : val_tipoFormato
			},
			success : function(data) {
				if (data['resultCode'] !== "KO") {
					
					// copy filters
					$("#param_nomeReport").val(val_nomeReport);
					$("#param_dataRicezioneInizio").val(val_dataRicezioneInizio);
					$("#param_dataRicezioneFine").val(val_dataRicezioneFine);
					$("#param_codiceDescrizionePSP").val(val_codiceDescrizionePSP);

					if (dataTableRisultati == null) {
						// create data table
						dataTableRisultati = $("#tabellaRisultati").dataTable(optsDaUtilizzare);
						// shows the table
						$("#divTabellaRisultati").removeClass("hide");
					} else {
						dataTableRisultati.fnDraw();
					}
					if (data["message"]) {
						displayAlert(data["message"], "success");
					}
				} else {
					displayAlert(data["message"], "warning");
				}
			},
			error : function() {
				displayAlert("IMPOSSIBILE CONTATTARE IL SERVER", "error");
			} 
		});
	}
		
	$("#formSubmitButtonPulisci").click(function() {
		clearAllAlerts();
		$(".datepicker").val("").datepicker("update");
		
		$("input[type='text'][name='codiceDescrizionePSP']").val("");
		$("input[type='text'][name='nomeReport']").val("");
	});

	$("#formSubmitButtonCerca").click(function() {
		$("#initTable").val("true");
		doSearch("/epaypaweb/salva-prenotazione-report-flussi-rendicontazione-base-json.do");
	});
	var opts = {
			language : {
				zeroRecords : 'Nessun esito trovato',
				emptyTable : 'Nessun esito trovato'
			},
			"ordering" : true,
			"order" : [[1, "desc"]],
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
			    	"data" : "nominativoExport",
			    	"orderable" : true,
			    	"targets" : 0
			    },
	 		    {
			    	"data" : "statoReport",
			    	"render" : function(data, type, row, meta) {
			    		return (data != null) ?  data.descrizione: "-";
			    	},
			    	"orderable" : true,
					"targets" : 1
			    },
	 		    {
			    	"data" : "nomeFile",
			    	"orderable" : true,
					"targets" : 2
			    },
			    {
			    	"data" : "dataInserimento",
			    	"render" : function(data, type, row, meta) {
			    		return (data != null) ? dateTimeFormatter.format(new Date(data)) : "";
			    	},
			    	"orderable" : true,
					"targets" : 3
			    },
			    {
			    	"data" : "dataModifica",
			    	"render" : function(data, type, row, meta) {
			    		return (data != null) ? dateTimeFormatter.format(new Date(data)) : "";
			    	},
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
				
						var visualizzaReportEntiAction = clonedRow.find("#scaricaAction");					
						if (visualizzaReportEntiAction.length > 0) {
								visualizzaReportEntiAction.attr("data-row-id", rowData["idFile"]).click(function() {
								var form = $("#" + $(this).attr("data-form-id"));
								form.find("input[name='idFile']").val($(this).attr("data-row-id"));
								form.submit();
							});
							numActions++;
						}

						if (numActions == 0 || !rowData["idFile"]) {
							clonedRow.find("button").attr("disabled", "true");
						}
						clonedRow.removeClass("hide");
						$(td).append(clonedRow);
			        },
			    	"targets" : 5
			    }
		],
	    "ajax" : {
			"url" : "/epaypaweb/prenota-ricerca-report-flussi-base-json.do",
			"dataType" : 'json',
			"type" : "POST",
			"data" : function(data) {
				var newData = {};

				newData.draw = data.draw;
				newData.start = data.start;
				newData.length = data.length;

				newData.nomeReport = $("#param_nomeReport").val();
	            newData.dataRicezioneInizio = $("#param_dataRicezioneInizio").val();
	            newData.dataRicezioneFine = $("#param_dataRicezioneFine").val();
	            newData.codiceDescrizionePSP = $("#param_codiceDescrizionePSP").val();
	            newData.tipoFormato = $("#param_tipoFormato").val();

	    		if (data.order.length > 0) {
	    			newData.sortingCol = data.columns[ data.order[0].column ].data;
	    			newData.sortingDir = data.order[0].dir;
	    		}
	    		return newData;
			}
	    }
	};
	var optsDaUtilizzare = $.extend(true, {}, dataTableOptions, opts);

	if ($('#executeSearch').val() == 'true') {
		doSearch('/epaypaweb/valida-filtro-prenota-ricerca-report-flussi-rendicontazione-base-json.do');
	}
	
	// messaggio di esito di una elaborazione
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}
})
