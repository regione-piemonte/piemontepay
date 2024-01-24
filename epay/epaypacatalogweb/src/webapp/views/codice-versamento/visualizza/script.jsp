<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

function eliminaCodiceVersamentoCollegato(target) {
	chiediConfermaEliminazione("Si e' sicuri di eliminare il codice versamento collegato selezionato?", function() {
		window.location.href = '${context}/codici-versamento/${modifica_codice_versamento.id}/codici-versamento-collegati/elimina/' + target;
	});	
}

$( document ).ready(function() {

	$('#results').DataTable({
		order: [],
		columnDefs: [ 
			{ orderable: false, targets: [$("#results th").size() - 1] } 
		]		
	});

// 	$(".eliminaAction").on("click", function(event) {
// 		chiediConfermaEliminazione("Si e' sicuri di eliminare il codice versamento collegato selezionato?", function() {
// 			window.location.href = '${context}/codici-versamento/${modifica_codice_versamento.id}/codici-versamento-collegati/elimina/' + 
// 				$(event.target).attr("data-action-target");
// 		});	
// 	});

	function getNumeroCodiciCollegati() {
		return ${scenario.equals('modifica') ? modifica_codice_versamento.getCodiciVersamentoCollegati().size() : 0};
	}
	
	function getScenario() {
		return '${scenario}';
	}

	function isNuovo() {
		return getScenario() === 'nuovo';
	}

	function isModifica() {
		return getScenario() === 'modifica';
	}
	
	$("#buttonBack").on('click',function(){
		location = baseLocation + "codici-versamento/ricerca";
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
		if (true) {
			return getCodiciVociEntrata() ;
		}
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
       	$("#descrizioneVoceEntrata").val(voceInizialeCodiceVoceEntrata.descrizione);
       	$("#codice").val(voceInizialeCodiceVoceEntrata.codice);
	}
});

</script>
