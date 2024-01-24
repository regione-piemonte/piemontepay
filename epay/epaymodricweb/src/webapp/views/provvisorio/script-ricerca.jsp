<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<script>
	<c:url value="/flussi/forza-elaborazione" var="rielaboraFlussiUrl" />
	$(function() {
		$(".datepicker").datepicker({
			format : 'dd/mm/yyyy',
			language : 'it'
		});

		$("#results input[type=checkbox]").on("change", function() {
			selezioneCambiata();
		});

		$('#results').dataTable({
			order : [],
			columnDefs : [ {
				orderable : false,
				targets : [ 0, $("#results th").size() - 1 ]
			} ]
		});

		function selezioneCambiata() {
			var button = $(".btnRielabora");
			if ($("#results input[type=checkbox]:checked").size() > 0) {
				button.removeAttr("disabled");
			} else {
				button.attr("disabled", true);
			}
		}

		$('#results').on('draw.dt', function() {
			selezioneCambiata();
		});

		$(".btnRielabora")
				.on(
						"click",
						function() {
							var checkedValues = $(
									'input.rielaborazioneFlussi:checked').map(
									function() {
										return this.value;
									}).get();
							$
									.ajax(
											{
												type : "GET",
												url : "${rielaboraFlussiUrl}",
												data : {
													identificativiFlussoDaRielaborare : checkedValues
												},
												contentType : "application/json; charset=utf-8"

											})
									.done(
											function(data) {
												if (data == null
														|| data == undefined) {
													mostraErrore("Non e' stato possibile forzare i flussi richiesti");
												} else if (data.codice !== "000") {
													mostraErrore("Errore durante la forzatura dei flussi: "
															+ data.descrizione);
												} else {
													location.reload(true);
												}
											})
									.fail(
											function() {
												mostraErrore("Non e' stato possibile forzare i flussi richiesti");
											});

						});
	});

	function eliminaProvvisorio(target) {
		chiediConfermaEliminazione("Si e' sicuri di eliminare il provvisorio selezionato?", function() {
			window.location.href = target;
		});		
	}
</script>
