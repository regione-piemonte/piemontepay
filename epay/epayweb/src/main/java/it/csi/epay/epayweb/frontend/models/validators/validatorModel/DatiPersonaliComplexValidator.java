/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.validatorModel;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.Riferimento;

@Component
public class DatiPersonaliComplexValidator implements Validator {
	
	@Autowired
	HttpSession session;

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiPersonali.class.equals(clazz); 
	}

	@Override
	@Deprecated
	public void validate(Object target, Errors errors) {
	}
	
	public void validate(Object target, Errors errors, Boolean flagSpontaneo) {
		if (target == null) return;
		testNome(target, errors);
		if (flagSpontaneo) {
			testNote(errors);
		}
	}
	
	private void testNome(Object target, Errors errors) {
		DatiPersonali datiPersonali = (DatiPersonali)target;
		if ("personaFisica".equals(datiPersonali.getSoggettoGiuridico())) 
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", null, "Dato obbligatorio.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", null, "Dato obbligatorio.");
			datiPersonali.setRagioneSociale(null);
		} else {
			datiPersonali.setNome(null);
			datiPersonali.setCognome(null);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ragioneSociale", null, "Dato obbligatorio.");
		}
	}
	
	private void testNote(Errors errors) {
		Riferimento riferimento = (Riferimento)session.getAttribute("riferimento");
		if (StringUtils.isNotEmpty(riferimento.getCompilazioneNote())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "note", null, "Dato obbligatorio.");
		}
	}
}
