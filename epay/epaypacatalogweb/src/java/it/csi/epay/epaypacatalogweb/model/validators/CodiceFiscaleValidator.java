/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.validators;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodiceFiscaleValidator implements ConstraintValidator<CodiceFiscale, String> {
	
	@Override
	public void initialize(CodiceFiscale paramA) {
	}
	
	@Override
	public boolean isValid(String codFiscale, ConstraintValidatorContext ctx) {
		boolean ret = EPayValidatorUtils.isValidCodiceFiscale(codFiscale);
		
		return ret;
	}

}
