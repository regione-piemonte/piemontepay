/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.csi.epay.epayweb.utilities.CodeTest;

public class IuvValidator implements ConstraintValidator<Iuv, String> {

    public void initialize(Iuv constraintAnnotation) {
    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	if (object == null)
        	return true;

    	if (object.startsWith("RF"))
    		return CodeTest.checkIuvStarndard(object) == null;
    	else
    		return true;
    }
}
