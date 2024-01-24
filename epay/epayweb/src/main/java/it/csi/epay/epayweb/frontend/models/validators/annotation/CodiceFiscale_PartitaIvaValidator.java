/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.csi.epay.epayweb.frontend.models.validators.annotation.CodiceFiscale_PartitaIva.CfPi;
import it.csi.epay.epayweb.utilities.CodeTest;

public class CodiceFiscale_PartitaIvaValidator implements ConstraintValidator<CodiceFiscale_PartitaIva, String> {
	
    private CfPi test;
    
    public void initialize(CodiceFiscale_PartitaIva constraintAnnotation) {
        this.test = constraintAnnotation.value();
    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	if (object == null)
            return true;

    	return (object.length() == 11 || object.length() == 16 || object.equals(CfPi.ANONIMO.name())) && (checkAnonimo(object) || checkPartitaIVA(object) || checkCodiceFiscale(object));
    }
	
	private Boolean checkAnonimo(String object) {
		if (CfPi.CODICEFISCALE.equals(test) || CfPi.PARTITAIVA.equals(test) || CfPi.CODICEFISCALE_OR_PARTITAIVA.equals(test)) {
			return false;
		} 
		return object.equals(CfPi.ANONIMO.name());
	}

	private Boolean checkCodiceFiscale(String object) {
		if (CfPi.ANONIMO.equals(test) || CfPi.PARTITAIVA.equals(test)) {
			return false;
		} 
		return CodeTest.checkCodiceFiscale(object) == null;
	}

	private Boolean checkPartitaIVA(String object) {
		if (CfPi.ANONIMO.equals(test) || CfPi.CODICEFISCALE.equals(test)) {
        	return false;
        }
		return CodeTest.checkPartitaIVA(object) == null;
	}

}
