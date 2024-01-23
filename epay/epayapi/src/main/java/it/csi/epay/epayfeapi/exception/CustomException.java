/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.exception;

import org.jfree.util.Log;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class CustomException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -4254601484385405856L;

	public CustomException () {
	}

	public CustomException ( String message ) {
		super ( message );
		Log.error ( "Created new CustomException" );
	}

	public CustomException ( String message, Throwable cause ) {
		super ( message, cause );
	}

	public CustomException ( Throwable cause ) {
		super ( cause );
	}

	public CustomException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
		super ( message, cause, enableSuppression, writableStackTrace );
	}
}
