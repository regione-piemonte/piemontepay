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


@Target ( { METHOD, FIELD, ANNOTATION_TYPE } )
@Retention ( RUNTIME )
@Constraint ( validatedBy = SizeOrSpecialValueValidator.class )
@Documented
public @interface SizeOrSpecialValue {

	String message () default "Deve essere lungo tra 11 e 35 caratteri.";

	Class<?> [] groups () default {};

	Class<? extends Payload> [] payload () default {};

	int min ();

	int max ();

	String specialValue ();

}
