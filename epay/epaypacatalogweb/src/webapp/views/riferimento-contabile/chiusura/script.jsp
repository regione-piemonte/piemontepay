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

	$("#buttonSave").on('click',function(){
		$("#chiudi_riferimento_contabile").submit();
	});
	
	$("#buttonBack").on('click',function(){
		location = baseLocation + "riferimenti-contabili/ricerca";
	});

	$("#buttonClean").on('click',function(){

		$("#dataFineValidita").val("");
		syncForm();
	});

	$("#dataFineValidita").on('change',function(){
		syncForm();
	});

	function syncForm(initial) {
		
		var submitButton = $("#buttonSave");
		
		/*
		
		if ($("#dataFineValidita").val()) {
			submitButton.removeAttr("disabled");
		} else {
			submitButton.attr("disabled", "disabled");
		}
		
		*/
		
		submitButton.removeAttr("disabled");
	} 

	syncForm(true);
	$("#flagAssociaRifContPrimario").prop("checked", "true"== $("#flagAssociaRifContPrimarioValue").val());
    $("#flagAssociaRifContSecondario").prop("checked","true"== $("#flagAssociaRifContSecondarioValue").val());
    

	scrollToFirstError();

	setTimeout(function() {
		$("#dataFineValidita").focus();
	}, 500);
	
});

</script>
