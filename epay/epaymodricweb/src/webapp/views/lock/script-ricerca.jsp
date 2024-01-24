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

	$(".btnRielabora").on("click", function() {
		chiediConfermaEliminazione("I lock indicati saranno eliminati",function() {
			document.getElementById('lista_risultati').submit();
		},
		function() {
			//Dummy
		});
	});	
});

</script>
