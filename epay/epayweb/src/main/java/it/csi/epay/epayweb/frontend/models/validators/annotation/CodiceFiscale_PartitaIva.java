/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CodiceFiscale_PartitaIvaValidator.class)
@Documented
public @interface CodiceFiscale_PartitaIva {
	String message() default "Codice Fiscale errato o Partita Iva errata.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    CfPi value();

    public enum CfPi {
    	ANONIMO,
    	CODICEFISCALE, 
        PARTITAIVA,
        CODICEFISCALE_OR_PARTITAIVA,
        ANONIMO_OR_CODICEFISCALE_OR_PARTITAIVA;
    }
}
