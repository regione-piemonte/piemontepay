/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
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
		    	"data" : "idPosizioneDebitoria",
		    	"orderable" : true,
		    	"targets" : 0
		    },
		    {
		    	"data" : "IUV",
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
		    	"data" : "desCausaleVersamento",
		    	"orderable" : true,
				"targets" : 4
		    },
		    {
		    	"data" : "soggettoDebitore.idUnivocoFiscale",
		    	"orderable" : true,
		    	"targets" : 5
		    },
		    {
		    	"data" : "soggettoDebitore.cognomeNomeOrRagioneSociale",
		    	"orderable" : true,
		    	"targets" : 6
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
					clonedRow.find("a").attr("data-row-id", rowData["id"]).click(function() {
						var form = $("#" + $(this).attr("data-form-id"));
						form.find("input[name='idNotificaPagamento']").val($(this).attr("data-row-id"));
						form.submit();
					});
					clonedRow.removeClass("hide");
					$(td).append(clonedRow);
		        },
		    	"targets" : 7
		    }
		],
		"ajax" : {
			"url" : "/epaypaweb/visualizza-flusso-pagamenti-json.do",
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
})
