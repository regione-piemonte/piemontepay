<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<script>
function forzaElaborazione(id) {
	$.ajax({
        type: "POST",
        data: JSON.stringify(id),
        contentType: "application/json",
        url: "rilanciaElaborazione"
             }).done(function(response) {
            	 mostraInfoEdEseguiLogica('Rielaborazione richiesta correttamente. Sar&agrave; rieseguita nei tempi stabiliti', function() {location.reload(true);});
             }).error(function(xhr) {
                alert("ERROR");
                console.log("ERROR: ", xhr);
				  display(xhr);
             });
}

function refreshTable() {
    $("#results" ).load( "ricercaElaborazionePrecedente #results" );
}
function chiediConfermaForzaturaElaborazione(id) {
	chiediConfermaProcedi("Attenzione, si e' sicuri di voler forzare l'elaborazione selezionata?", function() {
		forzaElaborazione(id);
	});	
}
$(function(){
	$(".datepicker").datepicker({
	    format: 'dd/mm/yyyy',
	    language: 'it'
	});
	
	$('#results').dataTable( {
		order: [],
		columnDefs: [ 
			{ orderable: false, targets: [$("#results th").size() - 1] } 
		]
	});

});

</script>
