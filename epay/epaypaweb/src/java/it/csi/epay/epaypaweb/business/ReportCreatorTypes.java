/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;

@Qualifier
@Retention(RUNTIME)
@Target({ FIELD, TYPE, METHOD })
public @interface ReportCreatorTypes {
	TipoReportEnum value();
}
