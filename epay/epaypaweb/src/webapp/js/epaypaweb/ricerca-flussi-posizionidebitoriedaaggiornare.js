/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var dataTableRisultati = null;

	function doSearch() {
		clearAllAlerts();

		const val_idStatoFlusso = $("select[name='idStatoFlusso']").val();
		const val_idCodVersamento = $("select[name='idCodVersamento']").val();
		const val_dataStatoFlussoInizio = $("input[type='text'][name='dataStatoFlussoInizio']").val();
		const val_dataStatoFlussoFine = $("input[type='text'][name='dataStatoFlussoFine']").val();
		const val_iuv = $("input[type='text'][name='iuv']").val();
		const val_cfisc = $("input[type='text'][name='cfisc']").val();
		const val_idPosizioneDebitoriaEsterna = $("input[type='text'][name='idPosizioneDebitoriaEsterna']").val();

		$.ajax({
			type : "POST",
			url : "/epaypaweb/valida-filtro-ricerca-listeaggiornamentoposizionidebitorie-json.do",
			data : {
				"idStatoFlusso" : val_idStatoFlusso,
				"idCodVersamento" : val_idCodVersamento,
				"dataStatoFlussoInizio" : val_dataStatoFlussoInizio,
				"dataStatoFlussoFine" : val_dataStatoFlussoFine,
				"iuv" : val_iuv,
				"cfisc" : val_cfisc,
				"idPosizioneDebitoriaEsterna" : val_idPosizioneDebitoriaEsterna
			},
			success : function(data) {
				if (data['resultCode'] === "OK") {
					// hide filter panel
					$('#collapseFilterPanel').collapse("hide");

					// copy filters
					$("#param_idStatoFlusso").val(val_idStatoFlusso);
					$("#param_idCodVersamento").val(val_idCodVersamento);
					$("#param_dataStatoFlussoInizio").val(val_dataStatoFlussoInizio);
					$("#param_dataStatoFlussoFine").val(val_dataStatoFlussoFine);
					$("#param_iuv").val(val_iuv);
					$("#param_cfisc").val(val_cfisc);
					$("#param_idPosizioneDebitoriaEsterna").val(val_idPosizioneDebitoriaEsterna);

					if (dataTableRisultati == null) {
						// create data table
						dataTableRisultati = $("#tabellaRisultati").dataTable(optsDaUtilizzare);
						// shows the table
						$("#divTabellaRisultati").removeClass("hide");
					} else {
						dataTableRisultati.fnDraw();
					}
				} else {
					displayAlert(data['message'], "warning");
				}
			},
			error : function() {
				displayAlert("IMPOSSIBILE CONTATTARE IL SERVER", "error");
			} 
		});
	}
		
	$("#formSubmitButtonPulisci").click(function() {
		clearAllAlerts();
		$("select[name='idStatoFlusso']").val("");
		$("select[name='idCodVersamento']").val("");
		$("input[type='text'][name='iuv']").val("");
        $("input[type='text'][name='cfisc']").val("");
        $("input[type='text'][name='idPosizioneDebitoriaEsterna']").val("");
		$(".datepicker").val("").datepicker("update");
	});

	$("#formSubmitButtonCerca").click(function() {
		$("#initTable").val("true");
		doSearch();
	});

	const opts = {
		language: {
			zeroRecords: 'Nessun esito trovato',
			emptyTable: 'Nessun esito trovato'
		},
		"ordering": true,
		"order": [[4, "desc"]],
		"serverSide": true,
		"processing": true,
		"stateSave": true,
		"stateLoadParams": function (settings, data) {
			if ($("#initTable").val() == "true") {
				return false;
			}
		},
		"columnDefs": [
			{
				"data": "idMessaggio",
				"orderable": true,
				"targets": 0
			},
			{
				"data": "codVersamento",
				"orderable": true,
				"render": function (data, type, row, meta) {
					return row["codVersamento"] + " - " + row["desCodVersamento"];
				},
				"targets": 1
			},
			{
				"data": "numeroElementi",
				"orderable": true,
				"targets": 2
			},
			{
				"data": "desStatoFlusso",
				"orderable": true,
				"targets": 3
			},
			{
				"data": "dataUltimaVariazione",
				"orderable": true,
				"render": function (data, type, row, meta) {
					return (data != null) ? dateFormatter.format(new Date(data)) : "";
				},
				"targets": 4
			},
			{
				"data": null,
				"orderable": false,
				"class": "tab_Right",
				"render": function (data, type, row, meta) {
					return "<span class='hide'></span>";
				},
				"createdCell": function (td, cellData, rowData, row, col) {
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

					var inviaFlussoAction = clonedRow.find("#inviaFlussoAction");
					if (inviaFlussoAction.length > 0) {
						if (rowData["statoFlusso"] == "IN_CORSO_DI_REDAZIONE" || rowData["statoFlusso"] == "ERRORE_IN_FASE_DI_INVIO") {
							inviaFlussoAction.attr("data-row-id", rowData["id"]).click(
								function () {
									var params = {
										idFlusso: $(this).attr("data-row-id"),
										idMessaggio: rowData["idMessaggio"]
									};

									confirmAction("Sei sicuro di voler inviare il flusso selezionato (lotto:" + rowData["idMessaggio"] + ")?", function () {
										clearAllAlerts();
										$("#confirm-action-modal").modal("hide");
										$.ajax({
											"url": "/epaypaweb/invia-flusso-posizionidebitoriedaaggiornare-json.do",
											"method": "POST",
											"data": params
										}).success(function (data) {
											displayAlert(data["message"], data['resultCode']);
											dataTableRisultati.fnDraw();
										}).error(function (data) {
											displayAlert("IMPOSSIBILE CONTATTARE IL SERVER", "danger");
										});
									});
								});
							numActions++;
						} else {
							inviaFlussoAction.addClass("hide");
						}
					}

					var modificaFlussoAction = clonedRow.find("#modificaFlussoAction");
					if (modificaFlussoAction.length > 0) {
						if (rowData["statoFlusso"] == "BOZZA" || rowData["statoFlusso"] == "IN_CORSO_DI_REDAZIONE" || rowData["statoFlusso"] == "ERRORE_IN_FASE_DI_INVIO") {
							var form = $("#" + $(this).attr("data-form-id"));
							modificaFlussoAction.attr("data-row-id", rowData["id"]).click(submitFormAndWaitForCompletationAction);
							numActions++;
						} else {
							modificaFlussoAction.addClass("hide");
						}
					}

					var eliminaFlussoAction = clonedRow.find("#eliminaFlussoAction");
					if (eliminaFlussoAction.length > 0) {
						if (rowData["statoFlusso"] == "BOZZA" || rowData["statoFlusso"] == "IN_CORSO_DI_REDAZIONE" || rowData["statoFlusso"] == "ERRORE_IN_FASE_DI_INVIO") {
							eliminaFlussoAction.attr("data-row-id", rowData["id"]).click(
								function () {
									var params = {
										idFlusso: $(this).attr("data-row-id"),
										idMessaggio: rowData["idMessaggio"],
										tipoFlusso: "AGPD"
									};

									confirmAction("Sei sicuro di voler eliminare il flusso selezionato (lotto: " + rowData["idMessaggio"] + ")?", function () {
										clearAllAlerts();
										$("#confirm-action-modal").modal("hide");
										$.ajax({
											"url": "/epaypaweb/elimina-flusso-json.do",
											"method": "POST",
											"data": params
										}).success(function (data) {
											displayAlert(data["message"], data['resultCode']);
											dataTableRisultati.fnDraw();
										}).error(function (data) {
											displayAlert("IMPOSSIBILE CONTATTARE IL SERVER", "danger");
										});
									});
								});
							numActions++;
						} else {
							eliminaFlussoAction.addClass("hide");
						}
					}

					var pubblicaFlussoAction = clonedRow.find("#pubblicaFlussoAction");
					if (pubblicaFlussoAction.length > 0) {
						if (rowData["statoFlusso"] == "BOZZA") {
							pubblicaFlussoAction.attr("data-row-id", rowData["id"]).click(
								function () {
									var params = {
										idFlusso: $(this).attr("data-row-id"),
										idMessaggio: rowData["idMessaggio"]
									};
									clearAllAlerts();
									$.ajax({
										"url": "/epaypaweb/pubblica-flusso-json.do",
										"method": "POST",
										"data": params
									}).success(function (data) {
										displayAlert(data["message"], data['resultCode']);
										dataTableRisultati.fnDraw();
									}).error(function (data) {
										displayAlert("IMPOSSIBILE CONTATTARE IL SERVER", "danger");
									});
								});
							numActions++;
						} else {
							pubblicaFlussoAction.addClass("hide");
						}
					}

					if (numActions == 0) {
						clonedRow.find("button").attr("disabled", "true");
					}
					clonedRow.removeClass("hide");
					$(td).append(clonedRow);
				},
				"targets": 5
			}
		],
		"ajax": {
			"url": "/epaypaweb/ricerca-listeaggiornamentoposizionidebitorie-json.do",
			"dataType": 'json',
			"type": "POST",
			"data": function (data) {
				const newData = {};

				newData.draw = data.draw;
				newData.start = data.start;
				newData.length = data.length;

				newData.idStatoFlusso = $("#param_idStatoFlusso").val();
				newData.idCodVersamento = $("#param_idCodVersamento").val();
				newData.dataStatoFlussoInizio = $("#param_dataStatoFlussoInizio").val();
				newData.dataStatoFlussoFine = $("#param_dataStatoFlussoFine").val();
				newData.iuv = $("#param_iuv").val();
				newData.cfisc = $("#param_cfisc").val();
				newData.idPosizioneDebitoriaEsterna = $("#param_idPosizioneDebitoriaEsterna").val();

				if (data.order.length > 0) {
					newData.sortingCol = data.columns[data.order[0].column].data;
					newData.sortingDir = data.order[0].dir;
				}
				return newData;
			}
		}
	};

	var optsDaUtilizzare = $.extend(true, {}, dataTableOptions, opts);

	if ($("#executeSearch").val() == "true") {
		doSearch();
	}

	// messaggio di esito di una elaborazione
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}
})
