/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

/**
 *  Custom JS for CodiceVersamento
 */


function listenForFlagChange() {
	$('#ricezioneFlussoRendicontazione').change(function() {
		if ($(this).is(':checked')) {
			$("#eMailLabel").addClass("required");
		} else {
			$("#eMailLabel").removeClass("required");
		}
	});
}