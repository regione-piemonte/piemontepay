/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var dataTableRisultati = null;

	function doSearch() {
		clearAllAlerts();

		var val_dataRicezioneInizio = $("input[type='text'][name='dataRicezioneInizio']").val();
		var val_dataRicezioneFine = $("input[type='text'][name='dataRicezioneFine']").val();
		var val_codiceDescrizionePSP = $("input[type='text'][name='codiceDescrizionePSP']").val();

		$.ajax({
			type : "POST",
			url : "/epaypaweb/valida-filtro-ricerca-flussi-rendicontazioni-json.do",
			data : {
				"dataRicezioneInizio" : val_dataRicezioneInizio,
				"dataRicezioneFine" : val_dataRicezioneFine,
				"codiceDescrizionePSP" : val_codiceDescrizionePSP
			},
			success : function(data) {
				
				if (data['resultCode'] == "OK") {
					
					// hide filter panel
					$('#collapseFilterPanel').collapse("hide");

					// copy filters
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
            	"data" : "idMessaggio",
		    	"orderable" : true,
				"targets" : 0
            },
		    {
		    	"data" : "dataInserimento",
		    	"orderable" : true,
		    	"render" : function(data, type, row, meta) {
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
		    	},
				"targets" : 1
		    },
 		    {
            	"data" : "dataRegolamento",
		    	"orderable" : true,
		    	"render" : function(data, type, row, meta) {
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
                },
				"targets" : 2
		    },
		    {
		    	"data" : "idAndDesTipoMittente",
		    	"orderable" : true,
				"targets" : 3
		    },
		    {
		    	"data" : "codIdUnivocoMittente",
		    	"orderable" : true,
		    	"targets" : 4
		    },
		    {
		    	"data" : "denominazioneMittente",
		    	"orderable" : true,
		    	"targets" : 5
		    },
		    {
		    	"data" : "numeroElementi",
		    	"orderable" : true,
		    	"targets" : 6
		    },
		    {
		    	"data" : "importoTotale",
		    	"orderable" : true,
		    	"render" : function(data, type, row, meta) {
                    return (data != null) ? currencyEURFormatter.format(data) : "";
                },
				"targets" : 7
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
		    	"targets" : 8
		    }
		],
	    "ajax" : {
			"url" : "/epaypaweb/ricerca-flussi-rendicontazioni-json.do",
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
	            newData.codiceDescrizionePSP = $("#param_codiceDescrizionePSP").val();

	    		if (data.order.length > 0) {
	    			newData.sortingCol = data.columns[ data.order[0].column ].data;
	    			newData.sortingDir = data.order[0].dir;
	    		}
	    		
				return newData;
			}
	    }
	};

	var optsDaUtilizzare = $.extend(true, {}, dataTableOptions, opts);
	
	
	$("#tabellaRisultati").on('draw.dt', function() {
	    if ($(this).DataTable().data().length==0) {
	    	
	    	$('#prenotaReportDiv').addClass("hide");
	    	$("#prenotaReportExcelAction").addClass("hide");
	    	$("#prenotaReportCSVAction").addClass("hide");
						
	    	$('#prenotaReportDiv').removeClass("show");
	    	$("#prenotaReportExcelAction").removeClass("show");
	    	$("#prenotaReportCSVAction").removeClass("show");
	    } else {
	    	
	    	$('#prenotaReportDiv').removeClass("hide");
	    	$("#prenotaReportExcelAction").removeClass("hide");
	    	$("#prenotaReportCSVAction").removeClass("hide");
						
	    	$('#prenotaReportDiv').addClass("show");
	    	$("#prenotaReportExcelAction").addClass("show");
	    	$("#prenotaReportCSVAction").addClass("show");
	    }    
	})

	if ($('#executeSearch').val() == 'true') {
		doSearch();
	}
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}
	
	var prenotaReportCSVAction = $("#prenotaReportCSVAction");
	if (prenotaReportCSVAction.length > 0) {
		prenotaReportCSVAction.click(submitFormPrenotaReportAction);
	}

	var prenotaReportExcelAction = $("#prenotaReportExcelAction");
	if (prenotaReportExcelAction.length > 0) {
		prenotaReportExcelAction.click(submitFormPrenotaReportAction);
	}
})

function submitFormPrenotaReportAction() {
	var form = $("#" + $(this).attr("data-form-id"));
		
	form.find("input[name='dataRicezioneInizio']").val($("#param_dataRicezioneInizio").val());
	form.find("input[name='dataRicezioneFine']").val($("#param_dataRicezioneFine").val());
	form.find("input[name='codiceDescrizionePSP']").val($("#param_codiceDescrizionePSP").val());
	
	form.submit();
};

function doSubmitFormPrenotaReport_old(idForm, idFlusso, token) {
	var form = $("#" + idForm);
	form.find("input[name='idFlusso']").val(idFlusso);
	
	form.find("input[name='dataRicezioneInizio']").val($("#param_dataRicezioneInizio").val());
	form.find("input[name='dataRicezioneFine']").val($("#param_dataRicezioneFine").val());
	form.find("input[name='codiceDescrizionePSP']").val($("#param_codiceDescrizionePSP").val());
	
	console.log(form.find("input[name='dataRicezioneInizio']").val());
	console.log(form.find("input[name='dataRicezioneFine']").val());
	console.log(form.find("input[name='codiceDescrizionePSP']").val());
	
	if (token !== null) {
		form.find("input[name='pleaseWaitTokenValue']").val(token);
	}
	form.submit();
};

function submitFormPrenotaReportAction_old() {
	doSubmitFormPrenotaReport($(this).attr("data-form-id"), $(this).attr("data-row-id"), null);
}
