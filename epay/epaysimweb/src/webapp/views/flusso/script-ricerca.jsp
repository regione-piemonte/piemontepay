<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<script>

$(function(){
	$(".datepicker").datepicker({
	    format: 'dd/mm/yyyy',
	    language: 'it'
	});

	$("#results input[type=checkbox]").on("change", function() {
		selezioneCambiata();
	});
	
	$('#results').dataTable( {
		order: [],
		columnDefs: [ 
			{ orderable: false, targets: [0, $("#results th").size() - 1] } 
		]
	});

	function selezioneCambiata() {
		var button = $(".btnRielabora");
		if ($("#results input[type=checkbox]:checked").size() > 0) {
			button.removeAttr("disabled");
		} else {
			button.attr("disabled", true);
		}
	}

	$('#results').on( 'draw.dt', function () {
		selezioneCambiata();
	});

	$(".btnUpdateFlussiElaborato").on("click", function() {
		chiamaControllerPost('updateFlussiElaborato');
	});
	
	$(".btnUpdateFlussiErrore").on("click", function() {
		chiamaControllerPost('updateFlussiErrore');
	});

	function chiamaControllerPost( urlController){
		//mostraInfo("La funzionalita' richiesta non e' al momento disponibile.");
				var arrayOfIdSelected = [];
		$('[name^=selezioneIdFlusso]:checked').each(function() {
			arrayOfIdSelected.push($(this).attr('value'));
		});

		jQuery.ajax({
	        'type': 'POST',
	        'url': urlController,
	        'contentType': 'application/json;charset=utf-8',
			headers: {
				'Content-Type': 'application/json'
			},
	        'data': JSON.stringify(arrayOfIdSelected)
	    }).done(function() {
			$( "#divResults" ).load( "ricerca #divResults" );
		}).fail(function(xhr, textStatus, errorThrown) {
			console.log(xhr, textStatus, errorThrown);
			mostraErrore( xhr.responseText );
		});
	}
	
});

</script>
