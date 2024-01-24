/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var dataTableRisultati = null;

	function doSearch() {
		clearAllAlerts();

		var val_idTipoPagamento = $("input[type='radio'][name='idTipoPagamento']:checked").val();
		//var val_idTipoCostiNotifica = $("input[type='radio'][name='idTipoCostiNotifica']:checked").val();
		var val_idCodVersamento = $("select[name='idCodVersamento']").val();
		var val_dataRicezioneInizio = $("input[type='text'][name='dataRicezioneInizio']").val();
		var val_dataRicezioneFine = $("input[type='text'][name='dataRicezioneFine']").val();
		//--------------------------------//
	    //RDI-048 - START
	    //--------------------------------//
		var val_cognome = $("input[type='text'][name='cognome']").val();
		var val_iuv = $("input[type='text'][name='iuv']").val();
		//--------------------------------//
	    //RDI-048 - STOP
	    //--------------------------------//

		$.ajax({
			type : "POST",
			url : "/epaypaweb/valida-filtro-ricerca-flussi-pagamenti-json.do",
			data : {
				"idTipoPagamento" : val_idTipoPagamento,
				//"idTipoCostiNotifica" : val_idTipoCostiNotifica,
				"idCodVersamento" : val_idCodVersamento,
				"dataRicezioneInizio" : val_dataRicezioneInizio,
				"dataRicezioneFine" : val_dataRicezioneFine,
				//--------------------------------//
			    //RDI-048 - START
	  		    //--------------------------------//
				"cognome" : val_cognome,
				"iuv" : val_iuv,
				//--------------------------------//
			    //RDI-048 - STOP
	  		    //--------------------------------//
			},
			success : function(data) {
				if (data['resultCode'] == "OK") {
					// hide filter panel
					$('#collapseFilterPanel').collapse("hide");

					// copy filters
					$("#param_idTipoPagamento").val(val_idTipoPagamento);
					$("#param_idCodVersamento").val(val_idCodVersamento);
					$("#param_dataRicezioneInizio").val(val_dataRicezioneInizio);
					$("#param_dataRicezioneFine").val(val_dataRicezioneFine);
					
					//--------------------------------//
				    //RDI-048 - START
		  		    //--------------------------------//
					$("#param_cognome").val(val_cognome);
					$("#param_iuv").val(val_iuv);					
					//--------------------------------//
				    //RDI-048 - STOP
		  		    //--------------------------------//

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
		$("input[type='radio'][name='idTipoPagamento']").val(["1"]);
		$("select[name='idCodVersamento']").val("");
		
		$("input[type='text'][name='cognome']").val("");
		$("input[type='text'][name='iuv']").val("");

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
		"order" : [[5, "desc"]],
		"serverSide" : true,
		"processing" : true,
		"stateSave" : true,
		"stateLoadParams" : function(settings, data) {
			if ($("#initTable").val() == "true") {
				return false;
			}
		},
		"columnDefs" : [
		    //--------------------------------//
		    //RDI-048 - START
  		    //--------------------------------//

		    {
		    	"data" : "posizioneDebitoria",
		    	"orderable" : true,
		    	"targets" : 0
		    },
		    {
		    	"data" : "iuv",
		    	"orderable" : true,
		    	"targets" : 1
		    },
 		    {
		    	"data" : "importoPagato",
		    	"render" : function(data, type, row, meta) {
                    return (data != null) ? currencyEURFormatter.format(data) : "";
                },
		    	"orderable" : true,
				"targets" : 2
		    },
		    {
		    	"data" : "dataEsitoPagamento",
		    	"render" : function(data, type, row, meta) {
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
		    	},
		    	"orderable" : true,
				"targets" : 3
		    },
		    {
		    	"data" : "causaleVersamento",
		    	"orderable" : true,
				"targets" : 4
		    },
		    {
		    	"data" : "cfPivaSoggettoDebitore",
		    	"orderable" : true,
		    	"targets" : 5
		    },
		    {
		    	"data" : "cognome",
		    	"orderable" : true,
		    	"targets" : 6
		    },

//          {
//        	"data" : "idMessaggio",
//	    	"orderable" : true,
//			"targets" : 0
//        },
		    {
        	"data" : "codVersamento",
	    	"orderable" : true,
	    	"render" : function(data, type, row, meta) {
                return row["codVersamento"] + " - " + row["desCodVersamento"];
            },
			"targets" : 7
	    },
//	    {
//	    	"data" : "numeroElementi",
//	    	"orderable" : true,
//	    	"targets" : 2
//	    },
//	    {
//	    	"data" : "importoTotale",
//	    	"orderable" : true,
//	    	"render" : function(data, type, row, meta) {
//                return (data != null) ? currencyEURFormatter.format(data) : "";
//            },
//			"targets" : 3
//	    },
//	    {
//	    	"data" : "pagamentiSpontanei",
//	    	"orderable" : false,
//	    	"render" : function(data, type, row, meta) {
//	    		if (data != null) {
//	    			if (data == true) {
//	    				return "spontaneo";
//	    			} else {
//	    				return "dovuto";
//	    			}
//	    		} else {
//	    			return "";
//	    		}
//            },
//			"targets" : 4
//	    },
	    {
	    	"data" : "dataInserimento",
	    	"orderable" : true,
	    	"render" : function(data, type, row, meta) {
	    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
	    	},
			"targets" : 8
	    },//<!-- CSI_PAG-184 -->
	    {
	    	"data" : "revoca",
	    	"orderable" : true,
	    	"targets" : 9
	    },
	    		    
		    //--------------------------------//
		    //RDI-048 - STOP
  		    //--------------------------------//
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

					//--------------------------------//
				    //RDI-048 - START
		  		    //--------------------------------//					
					var visualizzaFlussoDettaglioAction = clonedRow.find("#visualizzaFlussoDettaglioAction");					
					if (visualizzaFlussoDettaglioAction.length > 0) {
						visualizzaFlussoDettaglioAction.attr("data-row-id", rowData["idNotificaPagamento"]).click(function() {
							//alert("Value is" + $(this).attr("data-row-id"));
							var form = $("#" + $(this).attr("data-form-id"));
							form.find("input[name='idNotificaPagamento']").val($(this).attr("data-row-id"));
							form.submit();
						});
						numActions++;
					}
					//--------------------------------//
				    //RDI-048 - STOP
		  		    //--------------------------------//
					
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
		    	"targets" : 10
		    }
		],
	    "ajax" : {
			"url" : "/epaypaweb/ricerca-flussi-pagamenti-json.do",
			"dataType" : 'json',
			"type" : "POST",
			"data" : function(data) {
				var newData = {};

				newData.draw = data.draw;
				newData.start = data.start;
				newData.length = data.length;

	            newData.idTipoPagamento = $("#param_idTipoPagamento").val();
	            newData.idCodVersamento = $("#param_idCodVersamento").val();
	            newData.dataRicezioneInizio = $("#param_dataRicezioneInizio").val();
	            newData.dataRicezioneFine = $("#param_dataRicezioneFine").val();
	            
	            //--------------------------------//
			    //RDI-048 - START
	  		    //--------------------------------//
	            newData.cognome = $("#param_cognome").val();
	            newData.iuv = $("#param_iuv").val();
	            //--------------------------------//
			    //RDI-048 - STOP
	  		    //--------------------------------//

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
