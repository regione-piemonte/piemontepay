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
		"stateSave" : true,
		"stateLoadParams" : function(settings, data) {
			if ($("#initTable").val() == "true") {
				return false;
			}
		},
		"columnDefs" : [
		    {
		    	"data" : "IUV",
		    	"orderable" : true,
		    	"targets" : 0
		    },
		    {
		    	"data" : "IUR",
		    	"orderable" : true,
		    	"targets" : 1
		    },
		    {
		    	"data" : "indicePagamento",
		    	"orderable" : true,
		    	"targets" : 2
		    },
 		    {
		    	"data" : "importoPagato",
		    	"render" : function(data, type, row, meta) {
                    return (data != null) ? currencyEURFormatter.format(data) : "";
                },
		    	"orderable" : true,
				"targets" : 3
		    },
		    {
		    	"data" : "codAndDesEsito",
		    	"orderable" : true,
		    	"targets" : 4
		    },
		    {
		    	"data" : "dataEsito",
		    	"render" : function(data, type, row, meta) {
		    		return (data != null) ? dateFormatter.format(new Date(data)) : "";
		    	},
		    	"orderable" : true,
				"targets" : 5
		    }
		],
		"ajax" : {
			"url" : "/epaypaweb/visualizza-flusso-rendicontazione-json.do",
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
