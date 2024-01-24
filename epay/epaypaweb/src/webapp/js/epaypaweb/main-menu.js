/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {

	// messaggio di esito di una elaborazione
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}

})
