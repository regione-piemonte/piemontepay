/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

function doSubmitForm(idForm, idFlusso, token) {
	var form = $("#" + idForm);
	form.find("input[name='idFlusso']").val(idFlusso);
	if (token !== null) {
		form.find("input[name='pleaseWaitTokenValue']").val(token);
	}
	form.submit();
};

function submitFormAction() {
	doSubmitForm($(this).attr("data-form-id"), $(this).attr("data-row-id"), null);
}

function submitFormAndWaitForCompletationAction() {
	var token = showModalPleaseWait();    
    doSubmitForm($(this).attr("data-form-id"), $(this).attr("data-row-id"), token);
};
