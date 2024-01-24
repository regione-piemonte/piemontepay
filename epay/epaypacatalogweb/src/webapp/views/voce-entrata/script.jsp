<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

$( document ).ready(function() {

	var submit_button_selector = "#formSubmitButtonCerca";
	var form_selector = "#entry-ricerca-vocientrata";
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

	if (false) {
		$("#descrizioneCompleta").autocomplete({
			minLength : 1,
			source : getAutocompleteVoceEntrata(),
			select : function(event, ui) {
				if (ui.item.val == -1) {
	                $(this).val("");
	                return false;
				} else {
					var voce = ui.item.object;
	               	$(this).val(voce.codice + " - " + voce.descrizione);
	               	$("#codice").val(voce.codice);
				}
			},
			change: function (e, u) {
	            if (u.item == null) {
	            	$(this).val("");
	               	$("#codice").val("");
	                return false;
	            }
	        }
		});
	}
	
	function getVociEntrata() {
		var options = <c:out value="${modelVociEntrata_JS}" escapeXml="false"  />;
		
		return options;
	};

	function getAutocompleteVoceEntrata() {

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

	if (false) {
		var valoreInizialeCodiceVoceEntrata = $("#codice").val();
		if (valoreInizialeCodiceVoceEntrata) {
			var valoreInizialeCodiceVoceEntrata = getVoceEntrataByCodice(valoreInizialeCodiceVoceEntrata);
	       	$("#descrizioneCompleta").val(valoreInizialeCodiceVoceEntrata.codice + " - " + valoreInizialeCodiceVoceEntrata.descrizione);
		}
	}
	
});

</script>
