/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.dispatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Operation {

	Class<? extends ParentInput> consumes();
	Class<? extends ParentOutput> produces();

    boolean skipAuthentication () default false;

}
