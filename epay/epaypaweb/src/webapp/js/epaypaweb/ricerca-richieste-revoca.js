/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var dataTableRisultati = null;

	function doSearchRevoche() {
		clearAllAlerts();

		var val_denominazioneIstituto= $("input[type='text'][name='denominazioneIstituto']").val();
		//alert("denom istituto: "+val_denominazioneIstituto);
		var val_tipoRevoca = $("input[type='text'][name='tipoRevoca']").val();
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
			url : "/epaypaweb/valida-filtro-ricerca-richieste-revoca-json.do",
			data : {
				"denominazioneIstituto" : val_denominazioneIstituto,
				"tipoRevoca" : val_tipoRevoca,
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
					$("#param_denominazioneIstituto").val(val_denominazioneIstituto);
					$("#param_tipoRevoca").val(val_tipoRevoca);
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

		$("input[type='text'][name='tipoRevoca']").val("Annullo Tecnico");
		$("input[type='text'][name='denominazioneIstituto']").val("");
		$("input[type='text'][name='cognome']").val("");
		$("input[type='text'][name='iuv']").val("");

		$(".datepicker").val("").datepicker("update");
	});

	$("#formSubmitButtonCerca").click(function() {
		//alert("chiamata script");
		$("#initTable").val("true");
		//alert("chiamata script ricerca");
		doSearchRevoche();
	});
	
//	$(document).ready(function() {
//	    var table = $('#tabellaRisultati').DataTable();
//	 
//	    $('#tabellaRisultati tbody').on( 'click', 'tr', function () {
//	        $(this).toggleClass('selected');
//	    } );
//	 
//	    $('#button').click( function () {
//	        alert( table.rows('.selected').data().length +' row(s) selected' );
//	    } );
//	} );

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
				{
					"data" : "identificativoMessaggioRevoca",
					"orderable" : true,
					"targets" : 0
				},
				{
					"data" : "dataOraMessaggioRevocaAl",
					"render" : function(data, type, row, meta) {
						return (data != null) ? dateFormatter.format(new Date(data)) : "";
					},
					"orderable" : true,
					"targets" : 1
				},
				{
					"data" : "denominazioneIstitutoAttestante",
					"orderable" : true,
					"targets" : 2
				},

				{
					"data" : "codiceFiscale",
					"orderable" : true,
					"targets" : 3
				},


				{
					"data" : "nomeCognome",
					"orderable" : true,
					"targets" : 4
				},

				{
					"data" : "importoTotaleTevocato",
					"render" : function(data, type, row, meta) {
						return (data != null) ? currencyEURFormatter.format(data) : "";

					},

					"orderable" : true,
					"targets" : 5
				},

				{
					"data" : "iuv",
					"orderable" : true,
					"targets" : 6
				},
				{
					"data" : "tipoRevoca",
					"orderable" : true,
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

						//--------------------------------//
					    //RDI-048 - START
			  		    //--------------------------------//					
						var visualizzaFlussoDettaglioAction = clonedRow.find("#visualizzaFlussoDettaglioAction");					
						if (visualizzaFlussoDettaglioAction.length > 0) {
							visualizzaFlussoDettaglioAction.attr("data-row-id", rowData["idRr"]).click(function() {
							//	alert("Value is" + $(this).attr("data-row-id"));
								var form = $("#" + $(this).attr("data-form-id"));
								form.find("input[name='idRr']").val($(this).attr("data-row-id"));
								form.submit();
							});
							numActions++;
						}
						//--------------------------------//
					    //RDI-048 - STOP
			  		    //--------------------------------//
						




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
					"url" : "/epaypaweb/ricerca-revoca-pagamenti-json.do",
					"dataType" : 'json',
					"type" : "POST",
					"data" : function(data) {
						var newData = {};

						newData.draw = data.draw;
						newData.start = data.start;
						newData.length = data.length;


						newData.tipoRevoca = $("#param_tipoRevoca").val();
						newData.dataRicezioneInizio = $("#param_dataRicezioneInizio").val();
						newData.dataRicezioneFine = $("#param_dataRicezioneFine").val();
						newData.denominazioneIstituto = $("#param_denominazioneIstituto").val();
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
		doSearchRevoche();
	}
})
