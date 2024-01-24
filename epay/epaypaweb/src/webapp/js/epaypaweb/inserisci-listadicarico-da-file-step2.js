/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {

	$('#collapseTemplateDownloadPanel').collapse("hide");

	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}

	$("#formSubmitButtonEsegui").click(function() {
		clearAllAlerts();
		$("#modal-pleasewait").modal();
		$("#elabora-listadicarico").submit();
	});
})
