<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

$( document ).ready(function() {

	var submit_button_selector = "#form-search-submit";
	var form_selector = "#filtro_ricerca_utenti_af";
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
			{ orderable: false, targets: [$("#results th").size() - 1] } 
		]		
	});


	/* opzioni autocomplete */
	
	$("#descrizioneCategoriaCdu").autocomplete({
		minLength : 1,
		source : getAutocompleteCategorieCdu(),
		select : function(event, ui) {
			if (ui.item.val == -1) {
                $(this).val("");
                $("#codiceCategoriaCdu").val("");
                return false;
			} else {
				var voce = getCategoriaCduByCodice(ui.item.object.codice);
               	$("#codiceCategoriaCdu").val(voce.codice);
               	// $(this).val(voce.codice + " - " + voce.descrizione);
               	$(this).val(voce.descrizione);
			}
		},
		change: function (e, u) {
            if (u.item == null) {
                $(this).val("");
                $("#codiceCategoriaCdu").val("");
                return false;
            }
        }
	});

	$("#descrizioneCdu").autocomplete({
		minLength : 1,
		source : getAutocompleteCdu(),
		select : function(event, ui) {
			if (ui.item.val == -1) {
                $(this).val("");
                $("#codiceCdu").val("");
                return false;
			} else {
				var voce = getCduByCodice(ui.item.object.codice);
               	$("#codiceCdu").val(voce.codice);
             // $(this).val(voce.codice + " - " + voce.descrizione);
               	$(this).val(voce.descrizione);
			}
		},
		change: function (e, u) {
            if (u.item == null) {
                $(this).val("");
                $("#codiceCdu").val("");
                return false;
            }
        }
	});

	function getCategorieCdu() {
		var options = <c:out value="${lista_opzioni_categoria_cdu_js}" escapeXml="false"  />;
		return options;
	};

	function getCdu() {
		var options = <c:out value="${lista_opzioni_cdu_js}" escapeXml="false"  />;
		return options;
	};

	function getCodiciCategorieCdu() {
		var input = getCategorieCdu();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push(input[i].codice);
		}
		
		return output;
	}

	function getCodiciCdu() {
		var input = getCdu();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push(input[i].codice);
		}
		
		return output;
	}
	
	function getAutocompleteCategorieCdu() {

		var input = getCategorieCdu();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push({
				value: input[i].descrizione,
				label: input[i].descrizione,
				object: input[i]
			});
		}
		
		return output;
	}

	function getAutocompleteCdu() {

		var input = getCdu();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push({
				value: input[i].descrizione,
				label: input[i].descrizione,
				object: input[i]
			});
		}
		
		return output;
	}
	
	function getCategoriaCduByCodice(codice) {
		var input = getCategorieCdu();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].codice == codice) {
				return input[i];
			}
		}
		
		return null;
	}

	function getCduByCodice(codice) {
		var input = getCdu();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].codice == codice) {
				return input[i];
			}
		}
		
		return null;
	}

	var valoreInizialeCodiceCategoriaCdu = $("#codiceCategoriaCdu").val();
	if (valoreInizialeCodiceCategoriaCdu) {
		var voceInizialeCodiceCategoriaCdu = getCategoriaCduByCodice(valoreInizialeCodiceCategoriaCdu);
       	$("#descrizioneCategoriaCdu").val(voceInizialeCodiceCategoriaCdu.descrizione);
	}

	var valoreInizialeCodiceCdu = $("#codiceCdu").val();
	if (valoreInizialeCodiceCdu) {
		var voceInizialeCodiceCdu = getCduByCodice(valoreInizialeCodiceCdu);
       	$("#descrizioneCdu").val(voceInizialeCodiceCdu.descrizione);
	}
	
});

</script>
