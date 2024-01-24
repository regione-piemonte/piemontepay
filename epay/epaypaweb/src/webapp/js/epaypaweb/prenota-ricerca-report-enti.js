/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var dataTableRisultati = null;

	function doSearch( urlToCall ) {
		clearAllAlerts();

		var val_nomeReport = $("input[type='text'][name='nomeReport']").val();
		var val_idTipoPagamento = $("input[type='radio'][name='idTipoPagamento']:checked").val();
		var val_idTipoCostiNotifica = $("input[type='radio'][name='idTipoCostiNotifica']:checked").val();
		var val_idCodVersamento = $("select[name='idCodVersamento']").val();
		var val_dataPagamentoInizio = $("input[type='text'][name='dataPagamentoInizio']").val();
		var val_dataPagamentoFine = $("input[type='text'][name='dataPagamentoFine']").val();
		var val_codiceFiscale = $("input[type='text'][name='codiceFiscale']").val();
		var val_cognome = $("input[type='text'][name='cognome']").val();
		var val_iuv = $("input[type='text'][name='iuv']").val();
		var val_dataPagamentoScadenzaInizio = $("input[type='text'][name='dataPagamentoScadenzaInizio']").val();
		var val_dataPagamentoScadenzaFine = $("input[type='text'][name='dataPagamentoScadenzaFine']").val();
		const val_idFormatoFile = $("input[type='radio'][name='idFormatoFile']:checked").val();

		$.ajax({
			type : "POST",
			url : urlToCall,
			data : {
				"nomeReport" : val_nomeReport,
				"idTipoPagamento" : val_idTipoPagamento,
				"idTipoCostiNotifica" : val_idTipoCostiNotifica,
				"idCodVersamento" : val_idCodVersamento,
				"dataPagamentoInizio" : val_dataPagamentoInizio,
				"dataPagamentoFine" : val_dataPagamentoFine,
				"codiceFiscale" : val_codiceFiscale,
				"cognome" : val_cognome,
				"iuv" : val_iuv,
				"dataPagamentoScadenzaInizio" : val_dataPagamentoScadenzaInizio,
				"dataPagamentoScadenzaFine" : val_dataPagamentoScadenzaFine,
				"idFormatoFile" : val_idFormatoFile,
			},
			success : function(data) {
				if (data['resultCode'] !== "KO") {
					// copy filters
					$("#param_nomeReport").val(val_nomeReport);
					$("#param_idTipoPagamento").val(val_idTipoPagamento);
					$("#param_idTipoCostiNotifica").val(val_idTipoCostiNotifica);
					$("#param_idCodVersamento").val(val_idCodVersamento);
					$("#param_dataPagamentoInizio").val(val_dataPagamentoInizio);
					$("#param_dataPagamentoFine").val(val_dataPagamentoFine);
					$("#param_cognome").val(val_cognome);
					$("#param_iuv").val(val_iuv);
					$("#param_codiceFiscale").val(val_codiceFiscale);
					$("#param_dataPagamentoScadenzaInizio").val(val_dataPagamentoScadenzaInizio);
					$("#param_dataPagamentoScadenzaFine").val(val_dataPagamentoScadenzaFine);
					$("#param_idFormatoFile").val(val_idFormatoFile);

					if (dataTableRisultati == null) {
						// create data table
						dataTableRisultati = $("#tabellaRisultati").dataTable(optsDaUtilizzare);
						// shows the table
						$("#divTabellaRisultati").removeClass("hide");
						$("#scaricaExcelAction").removeClass("hide");
						$("#scaricaCSVAction").removeClass("hide");
						
					} else {
						dataTableRisultati.fnDraw();
						$('#esportaDiv').addClass("show");
						$("#scaricaExcelAction").addClass("show");
						$("#scaricaCSVAction").addClass("show");
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
		$("input[type='radio'][name='idTipoPagamento']").val(["1"]);
		$("input[type='radio'][name='idTipoCostiNotifica']").val(["2"]);
		$("select[name='idCodVersamento']").val("");
		$("input[type='text'][name='codiceFiscale']").val("");
		$("input[type='text'][name='cognome']").val("");
		$("input[type='text'][name='iuv']").val("");
		$("input[type='text'][name='nomeReport']").val("");
		$("input[type='text'][name='dataPagamentoScadenzaInizio']").val("");
		$("input[type='text'][name='dataPagamentoScadenzaFine']").val("");
		$("input[type='radio'][name='idFormatoFile']").val(["2"]);
		$(".datepicker").val("").datepicker("update");
	});

	$("#formSubmitButtonCerca").click(function() {
		$("#initTable").val("true");
		doSearch('/epaypaweb/valida-filtro-prenota-ricerca-report-enti-json.do');
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
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
		    	},
		    	"orderable" : true,
				"targets" : 3
		    },
		    {
		    	"data" : "dataModifica",
		    	"render" : function(data, type, row, meta) {
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
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
			"url" : "/epaypaweb/prenota-ricerca-report-enti-json.do",
			"dataType" : 'json',
			"type" : "POST",
			"data" : function(data) {
				var newData = {};

				newData.draw = data.draw;
				newData.start = data.start;
				newData.length = data.length;
	            newData.nomeReport = $("#param_nomeReport").val();
	            newData.idTipoPagamento = $("#param_idTipoPagamento").val();
	            newData.idTipoCostiNotifica = $("#param_idTipoCostiNotifica").val();
				newData.idFormatoFile = $("#param_idFormatoFile").val();
	            newData.idCodVersamento = $("#param_idCodVersamento").val();
	            newData.dataPagamentoInizio = $("#param_dataPagamentoInizio").val();
	            newData.dataPagamentoFine = $("#param_dataPagamentoFine").val();
	            newData.cognome = $("#param_cognome").val();
	            newData.iuv = $("#param_iuv").val();
	            newData.codiceFiscale = $("#param_codiceFiscale").val();
	            newData.dataPagamentoScadenzaFine = $("#param_dataPagamentoScadenzaFine").val();
	            newData.dataPagamentoScadenzaInizio = $("#param_dataPagamentoScadenzaInizio").val();

	    		if (data.order.length > 0) {
	    			newData.sortingCol = data.columns[ data.order[0].column ].data;
	    			newData.sortingDir = data.order[0].dir;
	    		}

	    		return newData;
			}
	    }
	};

	var optsDaUtilizzare = $.extend(true, {}, dataTableOptions, opts);

	var scaricaCSVAction = $("#scaricaCSVAction");
	if (scaricaCSVAction.length > 0) {
		scaricaCSVAction.click(submitFormAndWaitForCompletationAction);
	}

	var scaricaExcelAction = $("#scaricaExcelAction");
	if (scaricaExcelAction.length > 0) {
		scaricaExcelAction.click(submitFormAndWaitForCompletationAction);
	}
	
	if ($('#executeSearch').val() == 'true') {
		doSearch('/epaypaweb/prenota-ricerca-report-enti-json.do');
	}
	
	// messaggio di esito di una elaborazione
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}
})
