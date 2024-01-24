<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

$( document ).ready(function() {

	$('.datepicker').datepicker({
	    format: 'dd/mm/yyyy',
	    language: 'it'
	});

	function getScenario() {
		return '${scenario}';
	}

	function isNuovo() {
		return getScenario() === 'nuovo';
	}

	function isModifica() {
		return getScenario() === 'modifica';
	}
	
	$("#buttonSave").on('click',function(){
		$("#modifica_utente").submit();
	});
	
	$("#buttonBack").on('click',function(){
		location = baseLocation + "utenti/ricerca";
	});
	
	$("#buttonClean").on('click',function(){

		$("#formParametriContainer input:not([readonly])").val("");
		$("#formParametriContainer select").val("");
		$("#formParametriContainer input[type=checkbox]").prop("checked", false);
		$("span.error").html("");

		$("#modifica_utente").setDirty();
		syncForm();
	});

	function syncForm(initial) {
		// NOP
	} 

	$("#TODO").on('change',function(){
		syncForm();
	});

	syncForm(true);

	/* dati da controller */

	scrollToFirstError();

});

</script>
