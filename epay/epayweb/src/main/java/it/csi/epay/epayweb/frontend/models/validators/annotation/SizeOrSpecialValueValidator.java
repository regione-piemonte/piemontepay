/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeOrSpecialValueValidator implements ConstraintValidator<SizeOrSpecialValue, String> {
	
    private int min;
    private int max;
    private String specialValue;
    
    public void initialize(SizeOrSpecialValue constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.specialValue = constraintAnnotation.specialValue();
    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	if (object == null)
            return true;

    	return ((object.length() >= min && object.length() <= max) || object.equals(specialValue));
    }

}
