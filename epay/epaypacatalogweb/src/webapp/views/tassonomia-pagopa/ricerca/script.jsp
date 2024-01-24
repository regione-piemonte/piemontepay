<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>


$( document ).ready(function() {

	var submit_button_selector = "#form-search-submit";
	var form_selector = "#filtro_ricerca_tassonomia_pagopa";
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
	

	function isInserimentoAllowed() {
		<sec:authorize access="hasRole('INSERISCI_RIFERIMENTO_CONTABILE')">
		return true;
		</sec:authorize>
		return false;
	}
	
});

</script>
