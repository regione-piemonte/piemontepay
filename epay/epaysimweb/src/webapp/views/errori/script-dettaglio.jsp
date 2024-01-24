<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<script>

$(function(){

	$('#results').dataTable( {
		order: [],
		columnDefs: [ 
			{ orderable: false, targets: [$("#results th").size() - 1] } 
		]
	});
	$(".datepicker").datepicker({
		format : 'dd/mm/yyyy',
		language : 'it'
	});
});

</script>
