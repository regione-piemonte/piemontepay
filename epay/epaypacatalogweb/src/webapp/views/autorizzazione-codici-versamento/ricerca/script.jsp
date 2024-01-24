<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

$( document ).ready(function() {

	var submit_button_selector = "#form-search-submit";
	var form_selector = "#filtro_ricerca_utenti_acv";
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
            	if ($(this).val().length > 0) {
            		mostraInfoEdEseguiLogica('Il codice versamento "' + $(this).val() + 
                    		'" non &egrave; censito a livello di ente', function() {
            			$("#descrizioneCompletaCodiceVersamentoSelezionato").val("");
                        $("#idCodiceVersamento").val("");
                    });
            	} else {
            		$("#descrizioneCompletaCodiceVersamentoSelezionato").val("");
                    $("#idCodiceVersamento").val("");
                }
                return false;
            }
        }
	});

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

	var valoreInizialeIdCodiceVersamento = $("#idCodiceVersamento").val();
	if (valoreInizialeIdCodiceVersamento) {
		var voceInizialeIdCodiceVersamento = getCodiceVersamentoById(valoreInizialeIdCodiceVersamento);
       	$("#descrizioneCompletaCodiceVersamentoSelezionato").val(voceInizialeIdCodiceVersamento.codice + " - " + voceInizialeIdCodiceVersamento.descrizione);
	}
	
});

</script>
