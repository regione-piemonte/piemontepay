/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.gestioneente.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.gestioneente.RicercaEnteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.validators.EPayValidatorUtils;

/**
 * Validatore del form di modifica Ente
 * 
 * @author Alessandro
 *
 */
public class RicercaEnteValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return RicercaEnteFiltroVO.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		if (!EPayValidatorUtils.almenoUnCampoValidato(obj)) {
			errors.rejectValue("id", "id.validitaGenerica", "E' necessario inserire almeno un criterio di ricerca.");
		}
	}

}
