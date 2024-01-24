<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

function eliminaUtente(target) {
	chiediConfermaEliminazione("Si e' sicuri di eliminare l'utente selezionato?", function() {
		window.location.href = '${context}/utenti/elimina/' + target;
	});		
}

$( document ).ready(function() {

	var submit_button_selector = "#form-search-submit";
	var form_selector = "#filtro_ricerca_utenti";
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
	
// 	$(".eliminaAction").on("click", function(event) {
// 		chiediConfermaEliminazione("Si e' sicuri di eliminare l'utente selezionato?", function() {
// 			window.location.href = '${context}/utenti/elimina/' + 
// 				$(event.target).attr("data-action-target");
// 		});	
// 	});

});

</script>
