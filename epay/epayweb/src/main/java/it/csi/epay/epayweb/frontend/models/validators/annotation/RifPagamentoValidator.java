/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.csi.epay.epayweb.frontend.models.Riferimento;

public class RifPagamentoValidator implements ConstraintValidator<RifPagamento, Riferimento>
{
    

    @Override
    public void initialize(final RifPagamento constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(final Riferimento riferimento, final ConstraintValidatorContext context)
    {
    	//TODO
        return true;
    }
}
