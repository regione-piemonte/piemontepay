/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var dataTableRisultati = null;

	function doSearch() {
		clearAllAlerts();

		var val_idCodVersamento = $("select[name='idCodVersamento']").val();
		var val_dataRicezioneInizio = $("input[type='text'][name='dataRicezioneInizio']").val();
		var val_dataRicezioneFine = $("input[type='text'][name='dataRicezioneFine']").val();

		$.ajax({
			type : "POST",
			url : "/epaypaweb/valida-filtro-ricerca-flussi-avvisiscaduti-json.do",
			data : {
				"idCodVersamento" : val_idCodVersamento,
				"dataRicezioneInizio" : val_dataRicezioneInizio,
				"dataRicezioneFine" : val_dataRicezioneFine
			},
			success : function(data) {
				if (data['resultCode'] == "OK") {
					// hide filter panel
					$('#collapseFilterPanel').collapse("hide");

					// copy filters
					$("#param_idCodVersamento").val(val_idCodVersamento);
					$("#param_dataRicezioneInizio").val(val_dataRicezioneInizio);
					$("#param_dataRicezioneFine").val(val_dataRicezioneFine);

					if (dataTableRisultati == null) {
						// create data table
						dataTableRisultati = $("#tabellaRisultati").dataTable(optsDaUtilizzare);
						// shows the table
						$("#divTabellaRisultati").removeClass("hide");
					} else {
						dataTableRisultati.fnDraw();
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
		$("select[name='idCodVersamento']").val("");
		$(".datepicker").val("").datepicker("update");
	});

	$("#formSubmitButtonCerca").click(function() {
		$("#initTable").val("true");
		doSearch();
	});

	var opts = {
		language : {
			zeroRecords : 'Nessun esito trovato',
			emptyTable : 'Nessun esito trovato'
		},
		"ordering" : true,
		"order" : [[4, "desc"]],
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
            	"data" : "idMessaggio",
		    	"orderable" : true,
				"targets" : 0
            },
 		    {
            	"data" : "codVersamento",
		    	"orderable" : true,
		    	"render" : function(data, type, row, meta) {
                    return row["codVersamento"] + " - " + row["desCodVersamento"];
                },
				"targets" : 1
		    },
		    {
		    	"data" : "numeroElementi",
		    	"orderable" : true,
		    	"targets" : 2
		    },
		    {
		    	"data" : "importoTotale",
		    	"orderable" : true,
		    	"render" : function(data, type, row, meta) {
                    return (data != null) ? currencyEURFormatter.format(data) : "";
                },
				"targets" : 3
		    },
		    {
		    	"data" : "dataInserimento",
		    	"orderable" : true,
		    	"render" : function(data, type, row, meta) {
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
		    	},
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

					var visualizzaFlussoAction = clonedRow.find("#visualizzaFlussoAction");
					if (visualizzaFlussoAction.length > 0) {
						visualizzaFlussoAction.attr("data-row-id", rowData["id"]).click(submitFormAction);
						numActions++;
					}

					var scaricaCSVAction = clonedRow.find("#scaricaCSVAction");
					if (scaricaCSVAction.length > 0) {
						scaricaCSVAction.attr("data-row-id", rowData["id"]).click(submitFormAndWaitForCompletationAction);
						numActions++;
					}

					var scaricaExcelAction = clonedRow.find("#scaricaExcelAction");
					if (scaricaExcelAction.length > 0) {
						scaricaExcelAction.attr("data-row-id", rowData["id"]).click(submitFormAndWaitForCompletationAction);
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
			"url" : "/epaypaweb/ricerca-flussi-avvisiscaduti-json.do",
			"dataType" : 'json',
			"type" : "POST",
			"data" : function(data) {
				var newData = {};

				newData.draw = data.draw;
				newData.start = data.start;
				newData.length = data.length;

	            newData.idCodVersamento = $("#param_idCodVersamento").val();
	            newData.dataRicezioneInizio = $("#param_dataRicezioneInizio").val();
	            newData.dataRicezioneFine = $("#param_dataRicezioneFine").val();

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
		doSearch();
	}
})
