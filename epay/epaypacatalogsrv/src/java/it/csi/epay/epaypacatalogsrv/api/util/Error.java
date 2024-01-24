/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.api.util;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */

@XmlRootElement
public class Error{
   private String message;

/**
 * @return the message
 */
public String getMessage () {
    return message;
}

/**
 * @param message the message to set
 */
public void setMessage ( String message ) {
    this.message = message;
}
}
