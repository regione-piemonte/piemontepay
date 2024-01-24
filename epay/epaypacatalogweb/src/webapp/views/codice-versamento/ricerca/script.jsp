<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

function eliminaCodiceVersamento(target) {
	chiediConfermaEliminazione("Si e' sicuri di eliminare il codice versamento selezionato?", function() {
		window.location.href = '${context}/codici-versamento/elimina/' + target + '?source=ricerca';
	});	
}

function eliminaCodiceVersamentoCollegato(target, id_base) {
	chiediConfermaEliminazione("Si e' sicuri di eliminare il codice versamento collegato selezionato?", function() {
		window.location.href = '${context}/codici-versamento/' + id_base + '/codici-versamento-collegati/elimina/' + target + '?source=modifica';
	});	
}

$( document ).ready(function() {

	var submit_button_selector = "#form-search-submit";
	var form_selector = "#filtro_ricerca_codici_versamento";
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
		order: [],
		columnDefs: [
			{ orderable: false, targets: [0, 5, 6] } ,
			{ orderData: [ 1 ], targets: 1 },
			{ orderData: [ 2, 4, 1 ], targets: 2 },
			{ orderData: [ 3, 1 ], targets: 3 },
			{ orderData: [ 4, 1 ], targets: 4 },
			{ orderable: false, targets: [$("#results th").size() - 1] } ,
		],
		rowGroup: {
	        dataSrc: 0,
	        startRender: function ( rows, group ) {

	        	return $('<tr/ class="hidden">');

				// da qui in poi tutto disabilitato, ma lo mantengo perche' potrebbe servire
	        	
				// console.log("GROUP", group);
				// console.log("ROWS", rows);
	        	
				var firstRow = rows.data()[0];
				var firstRowJquery = $(rows.nodes()[0]);
				
				if (firstRow[0].trim() == "NO_PARENT") {
					return $('<tr/ class="hidden">');
				} else {
					return $('<tr/>')
		            	.append( '<td class="hidden"></td>' )
		                .append( '<td colspan="1">' + firstRow[0] + '</td>' )
		                .append( '<td>' + firstRow[2] + '</td>' )
		                .append( '<td>' + firstRow[3] + '</td>' )
		                .append( '<td>' + firstRow[4] + '</td>' )
		            	.append( $('<td/>').text( firstRowJquery.attr("custom-data-tipo-pagamento") ) )
		            	.append( $('<td/>').text( firstRowJquery.attr("custom-data-iban") ) )
		            	.append( '<td></td>' );
				}
	        },
	    },
	});
	
// 	$(".eliminaAction").on("click", function(event) {
// 		chiediConfermaEliminazione("Si e' sicuri di eliminare il codice versamento selezionato?", function() {
// 			window.location.href = '${context}/codici-versamento/elimina/' + 
// 				$(event.target).attr("data-action-target");
// 		});	
// 	});

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

	var valoreInizialeCodiceVoceEntrata = $("#codiceVoceEntrata").val();
	if (valoreInizialeCodiceVoceEntrata) {
		var voceInizialeCodiceVoceEntrata = getVoceEntrataByCodice(valoreInizialeCodiceVoceEntrata);
       	$("#descrizioneVoceEntrata").val(voceInizialeCodiceVoceEntrata.codice + " - " + voceInizialeCodiceVoceEntrata.descrizione);
	}
});

</script>
