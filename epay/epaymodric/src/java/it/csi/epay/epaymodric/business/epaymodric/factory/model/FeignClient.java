/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.factory.model;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 *
 */
@Documented
@Retention ( value = java.lang.annotation.RetentionPolicy.RUNTIME )
@Target ( value = { java.lang.annotation.ElementType.TYPE } )
public @interface FeignClient {

    String value () default "";

}
