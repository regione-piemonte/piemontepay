<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

$( document ).ready(function() {

	function getScenario() {
		return '${scenario}';
	}

	$("#buttonBack").on('click',function(){
		location = baseLocation + "riferimenti-contabili/ricerca";
	});

	/* dati da controller */

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
	
	function getCodiceVersamentoByCodice(codice) {
		var input = getCodiciVersamento();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].codice == codice) {
				return input[i];
			}
		}
		
		return null;
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
       	$("#descrizioneCodiceVersamento").val(voceInizialeIdCodiceVersamento.codice + " - " + voceInizialeIdCodiceVersamento.descrizione);
	}
	
});

</script>
