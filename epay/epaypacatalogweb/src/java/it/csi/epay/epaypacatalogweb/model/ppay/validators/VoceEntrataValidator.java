/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.ppay.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.ppay.RicercaVoceEntrataPPayFiltroVO;

/**
 * Validatore custom
 * 
 * @author fabio.fenoglio@eng.it
 *
 */
public class VoceEntrataValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return RicercaVoceEntrataPPayFiltroVO.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		if (obj == null) {
			return;
			
		} else if (obj instanceof RicercaVoceEntrataPPayFiltroVO) {
			
			new RicercaVoceEntrataValidator().validate(obj, errors);
			
		}
	}

}
