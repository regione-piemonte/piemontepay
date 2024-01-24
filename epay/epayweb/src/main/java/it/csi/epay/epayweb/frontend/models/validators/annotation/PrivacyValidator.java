/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PrivacyValidator implements ConstraintValidator<Privacy, Boolean> {

    public void initialize(Privacy constraintAnnotation) {
    }

    public boolean isValid(Boolean object, ConstraintValidatorContext constraintContext) {
    	if (object == null)
            return true;
    	
    	return object;
    }
}
