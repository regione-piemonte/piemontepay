/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GenericRicercaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * metodo che verifica la presenza di almeno un valore valido 
	 * (non null e non "") in tutto il bean.
	 */
	@Override
	public void validate(Object target, Errors errors) {
		if (!EPayValidatorUtils.almenoUnCampoValidato(target)) {
			errors.rejectValue("id", "id.validitaGenerica", "E' necessario inserire almeno un criterio di ricerca.");
		}
	}
	
}
