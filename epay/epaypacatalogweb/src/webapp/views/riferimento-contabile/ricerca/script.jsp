<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

function eliminaRiferimentoContabile(target) {

	chiediConfermaEliminazione("<spring:message code='riferimenticontabili.action.elimina.confirm' />", function() {
		window.location.href = '${context}/riferimenti-contabili/elimina/' + target;
	});
}

function mostraErroreTroppiRiferimenti(target) {
	
	
	mostraErrore("<spring:message code='riferimenticontabili.action.inserisci.limitreached' />");
}

$( document ).ready(function() {

	var submit_button_selector = "#form-search-submit";
	var form_selector = "#filtro_ricerca_riferimenti_contabili";
	var table_selector = "#results";

	$(submit_button_selector).on("click", function(event) {
		clearPage(table_selector);
		$(form_selector).submit();
	});

	var clean_button_selector = "#formSubmitButtonPulisci";

	$(clean_button_selector).on("click", function(event) {
		clearPage(table_selector);
		return true;
	});
	
	$('#results').DataTable({
		order: [ [ 0, "asc" ], [ 5, "asc" ], [ 6, "asc" ]  ],
		columnDefs: [
			{ orderData: [ 0, 5, 6 ], targets: 0 },
			{ orderData: [ 1, 0, 5, 6 ], targets: 1 },
			{ orderData: [ 2, 0, 5, 6 ], targets: 2 },
			{ orderData: [ 3, 0, 5, 6 ], targets: 3 },
			{ orderable: false, targets: 4 },
			{ orderable: false, targets: 5 },
			{ orderable: false, targets: 6 },
			{ orderable: false, targets: 7 },
			{ orderable: false, targets: 8 },
			{ orderable: false, targets: 9 },
		],
		rowGroup: {
	        dataSrc: 10,
	        startRender: function ( rows, group ) {
				// console.log("GROUP", group);
				// console.log("ROWS", rows);
	        	
				var firstRow = rows.data()[0];
				var firstRowJquery = $(rows.nodes()[0]);

				// verifico se posso inserire altri riferimenti
				var elementoAzioni = $('<td />');
				if (isInserimentoAllowed()) {
					if (firstRowJquery.attr("custom-ps-rif-cont") > 0) {						
						if (firstRowJquery.attr("custom-numero-riferimenti") < 5 ) {							
							var idCodiceVersamento = firstRowJquery.attr("custom-id-codice-versamento");
							elementoAzioni.html(
								'<div id="tableRowActions" class="btn-group">' +
								'<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown"> Azioni <span class="caret"></span></button>' +
								'<ul class="dropdown-menu pull-right"><li>' +
									'<a class="inserisciContextualAction" href="${context}/riferimenti-contabili/nuovo?codiceVersamento=' + idCodiceVersamento + '" >' +
									' <spring:message code="riferimenticontabili.action.inserisci" /> </a>' +
								'</li></ul></div>'
							);
							
						} else {
							// elementoAzioni.html('<a class="btn btn-sm inserisciContextualAction disabled"> Inserisci </a>');
							// elementoAzioni.html('');
	
	// 						elementoAzioni.html(
	// 							'<div id="tableRowActions" class="btn-group">' +
	// 							'<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown"> Azioni <span class="caret"></span></button>' +
	// 							'<ul class="dropdown-menu pull-right"><li>' +
	// 								'<a onclick="mostraErroreTroppiRiferimenti()" >' +
	// 								' Inserisci riferimento contabile </a>' +
	// 							'</li></ul></div>'
	// 						);
							var idCodiceVersamento = firstRowJquery.attr("custom-id-codice-versamento");
							elementoAzioni.html(
								'<div id="tableRowActions" class="btn-group">' +
								'<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown"> Azioni <span class="caret"></span></button>' +
								'<ul class="dropdown-menu pull-right"><li>' +
									'<a class="inserisciContextualAction" href="${context}/riferimenti-contabili/nuovo?codiceVersamento=' + idCodiceVersamento + '" >' +
									' <spring:message code="riferimenticontabili.action.inserisci" /> </a>' +
								'</li></ul></div>'
							);
						}
					}
				}
				
				return $('<tr/>')
	            	.append( $('<td />').text(firstRow[0]['@data-order']) )
	                .append( $('<td />').text(firstRow[1]['@data-order']) )
	                .append( $('<td />').text(firstRow[2]['@data-order']) )
	                .append( $('<td />').text(firstRow[3]['@data-order']) )
	                .append( '<td></td>' )
	                .append( '<td></td>' )
	                .append( '<td></td>' )
	                .append( '<td></td>' )
	                .append( '<td></td>' )
	                .append( elementoAzioni );
	        },
	    },
	});
	
	$("#descrizioneVoceEntrata").autocomplete({
		minLength : 1,
		source : getAutocompleteVociEntrata(),
		select : function(event, ui) {
			if (ui.item.val == -1) {
                $(this).val("");
                $("#codiceVoceEntrata").val("");
                return false;
			} else {
				var voce = getVoceEntrataByCodice(ui.item.object.codice);
               	$("#codiceVoceEntrata").val(voce.codice);
               	$(this).val(voce.codice + " - " + voce.descrizione);
			}
		},
		change: function (e, u) {
            if (u.item == null) {
                $(this).val("");
                $("#codiceVoceEntrata").val("");
                return false;
            }
        }
	});

	$("#descrizioneCompletaCodiceVersamentoSelezionato").autocomplete({
		minLength : 1,
		source : getAutocompleteCodiciVersamento(),
		select : function(event, ui) {
			if (ui.item.val == -1) {
                $(this).val("");
                $("#idCodiceVersamento").val("");
                return false;
			} else {
				var voce = getCodiceVersamentoById(ui.item.object.id);
               	$("#idCodiceVersamento").val(voce.id);
               	$(this).val(voce.codice + " - " + voce.descrizione);
			}
		},
		change: function (e, u) {
            if (u.item == null) {
                $(this).val("");
                $("#idCodiceVersamento").val("");
                return false;
            }
        }
	});

	function getVociEntrata() {
		var options = <c:out value="${modelVociEntrata_JS}" escapeXml="false"  />;
		
		return options;
	};

	function getCodiciVociEntrata() {
		var input = getVociEntrata();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push(input[i].codice);
		}
		
		return output;
	}

	function getAutocompleteVociEntrata() {

		var input = getVociEntrata();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push({
				value: input[i].codice + " - " + input[i].descrizione,
				label: input[i].codice + " - " + input[i].descrizione,
				object: input[i]
			});
		}
		
		return output;
	}
	
	function getVoceEntrataByCodice(codice) {
		var input = getVociEntrata();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].codice == codice) {
				return input[i];
			}
		}
		
		return null;
	}

	function getCodiciVersamento() {
		var options = <c:out value="${modelCodiciVersamento_JS}" escapeXml="false"  />;
		
		return options;
	};

	function getCodiciCodiciVersamento() {
		var input = getCodiciVersamento();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push(input[i].codice);
		}
		
		return output;
	}

	function getAutocompleteCodiciVersamento() {

		var input = getCodiciVersamento();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push({
				value: input[i].codice + " - " + input[i].descrizione,
				label: input[i].codice + " - " + input[i].descrizione,
				object: input[i]
			});
		}
		
		return output;
	}
	
	function getCodiceVersamentoById(id) {
		var input = getCodiciVersamento();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].id == id) {
				return input[i];
			}
		}
		
		return null;
	}

	var valoreInizialeCodiceVoceEntrata = $("#codiceVoceEntrata").val();
	if (valoreInizialeCodiceVoceEntrata) {
		var voceInizialeCodiceVoceEntrata = getVoceEntrataByCodice(valoreInizialeCodiceVoceEntrata);
       	$("#descrizioneVoceEntrata").val(voceInizialeCodiceVoceEntrata.codice + " - " + voceInizialeCodiceVoceEntrata.descrizione);
	}

	var valoreInizialeIdCodiceVersamento = $("#idCodiceVersamento").val();
	if (valoreInizialeIdCodiceVersamento) {
		var voceInizialeIdCodiceVersamento = getCodiceVersamentoById(valoreInizialeIdCodiceVersamento);
       	$("#descrizioneCompletaCodiceVersamentoSelezionato").val(voceInizialeIdCodiceVersamento.codice + " - " + voceInizialeIdCodiceVersamento.descrizione);
	}

	function isInserimentoAllowed() {
		<sec:authorize access="hasRole('INSERISCI_RIFERIMENTO_CONTABILE')">
		return true;
		</sec:authorize>
		return false;
	}
	
});

</script>
