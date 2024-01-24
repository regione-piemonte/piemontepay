/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function () {
	$("#backToHomeAction1").click(function () {
		confirmBackAction(true, "#backToHomeForm");
	});
	$("#backToHomeAction2").click(function () {
		confirmBackAction(true, "#backToHomeForm");
	});
	$("#backToRicercaListadicaricoAction1").click(function () {
		confirmBackAction(true, "#backToRicercaListadicaricoForm");
	});
	$("#backToRicercaListadicaricoAction2").click(function () {
		confirmBackAction(true, "#backToRicercaListadicaricoForm");
	});
	$("#backToVisualizzaListadicaricoAction1").click(function () {
		confirmBackAction(true, "#backToVisualizzaListadicaricoForm");
	});
	$("#backToVisualizzaListadicaricoAction2").click(function () {
		confirmBackAction(true, "#backToVisualizzaListadicaricoForm");
	});
	$("#ripristinaAction").click(function () {
		confirmBackAction(true, "#ripristinaForm");
	});
	$("#eliminaComponenteImporto0").click(function () {
		eliminaComponenteImporto(0);
	});
	$("#eliminaComponenteImporto1").click(function () {
		eliminaComponenteImporto(1);
	});
	$("#eliminaComponenteImporto2").click(function () {
		eliminaComponenteImporto(2);
	});
	$("#eliminaComponenteImporto3").click(function () {
		eliminaComponenteImporto(3);
	});
	$("#eliminaComponenteImporto4").click(function () {
		eliminaComponenteImporto(4);
	});
	$("#eliminaRiferimentoPagamento0").click(function () {
		eliminaRiferimentoPagamento(0);
	});
	$("#eliminaRiferimentoPagamento1").click(function () {
		eliminaRiferimentoPagamento(1);
	});
	$("#eliminaRiferimentoPagamento2").click(function () {
		eliminaRiferimentoPagamento(2);
	});
	$("#eliminaRiferimentoPagamento3").click(function () {
		eliminaRiferimentoPagamento(3);
	});
	$("#eliminaRiferimentoPagamento4").click(function () {
		eliminaRiferimentoPagamento(4);
	});

	function eliminaComponenteImporto(start) {
		for (r = start; r < 4; r++) {
			$("#compimpId" + r).val($("#compimpId" + (r + 1)).val());
			$("#compimpImporto" + r).val($("#compimpImporto" + (r + 1)).val());
			$("#strCompimpImporto" + r).val($("#strCompimpImporto" + (r + 1)).val());
			$("#compimpCausale" + r).val($("#compimpCausale" + (r + 1)).val());
			//$("#compimpDatiSpec" + r).val($("#compimpDatiSpec" + (r + 1)).val());
			$("#compimpAnnoAccert" + r).val($("#compimpAnnoAccert" + (r + 1)).val());
			$("#strCompimpAnnoAccert" + r).val($("#strCompimpAnnoAccert" + (r + 1)).val());
			$("#compimpNumeroAccert" + r).val($("#compimpNumeroAccert" + (r + 1)).val());
			$("#strCompimpNumeroAccert" + r).val($("#strCompimpNumeroAccert" + (r + 1)).val());
		}
		$("#compimpId4").val(null);
		$("#compimpImporto4").val(null);
		$("#strCompimpImporto4").val(null);
		$("#compimpCausale4").val(null);
		//$("#compimpDatiSpec4").val(null);
		$("#compimpAnnoAccert4").val(null);
		$("#strCompimpAnnoAccert4").val(null);
		$("#compimpNumeroAccert4").val(null);
		$("#strCompimpNumeroAccert4").val(null);
		return false;
	}

	function eliminaRiferimentoPagamento(start) {
		for (r = start; r < 4; r++) {
			$("#rifpagId" + r).val($("#rifpagId" + (r + 1)).val());
			$("#rifpagNome" + r).val($("#rifpagNome" + (r + 1)).val());
			$("#rifpagValore" + r).val($("#rifpagValore" + (r + 1)).val());
		}
		$("#rifpagId4").val(null);
		$("#rifpagNome4").val(null);
		$("#rifpagValore4").val(null);
		return false;
	}

	$("#soggettoIdTipo-1").click(hidePersonaGiuridica);
	$("#soggettoIdTipo-2").click(hidePersonaFisica);

	function hidePersonaFisica() {
		$("#divPersonaFisica").addClass("hide");
		$("#divPersonaGiuridica").removeClass("hide");
	}

	function hidePersonaGiuridica() {
		$("#divPersonaFisica").removeClass("hide");
		$("#divPersonaGiuridica").addClass("hide");
	}

	var radioSoggettoIdTipo = $("input[name='soggettoIdTipo']");
	var personaChecked = radioSoggettoIdTipo.filter(":checked").val();
	if (personaChecked == 'F')
		hidePersonaGiuridica();
	else
		hidePersonaFisica();

	// messaggio di esito di una elaborazione
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}
})

$( document ).ready(function() {
	$("#strCompimpImporto0Secondario").on("change keyup", function() {
		$("#strImportoSecondario").val( $("#strCompimpImporto0Secondario").val() );
	});

	$("#eliminaComponenteImportoSecondario0").click(function () {
		$("#strCompimpImporto0Secondario").val(null);
		$("#compimpCausale0Secondario").val(null);
		$("#strImportoSecondario").val(0);
	});
});
