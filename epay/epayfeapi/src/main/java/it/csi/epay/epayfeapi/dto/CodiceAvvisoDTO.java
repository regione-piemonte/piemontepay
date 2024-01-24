/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class CodiceAvvisoDTO implements Serializable {

	private static final long serialVersionUID = -4928311288480553749L;

	private String auxDigit;

	private String applicationCode;

	private String iuv;

	public CodiceAvvisoDTO ( final String auxDigit, final String applicationCode, final String iuv ) {
		setAuxDigit ( auxDigit );
		setApplicationCode ( auxDigit, applicationCode );
		setIuv ( iuv );
	}

	public CodiceAvvisoDTO ( final String auxDigit, final String applicationCode, final String iuv, final Boolean force ) {
		if ( force ) {
			this.iuv = iuv;
			this.auxDigit = auxDigit;

			if ( ( auxDigit != null ) && ( auxDigit.equals ( "3" ) ) ) {
				this.applicationCode = "";
			} else {
				this.applicationCode = applicationCode;
			}
		} else {
			setAuxDigit ( auxDigit );
			setApplicationCode ( auxDigit, applicationCode );
			setIuv ( iuv );
		}

	}

	static public String codiceAvvisoString ( final String auxDigit, final String applicationCode, final String iuv ) {
		return testAuxDigit ( auxDigit ) + testApplicationCode ( auxDigit, applicationCode ) + testIuv ( iuv );
	}

	public String getAuxDigit () {
		return auxDigit;
	}

	public void setAuxDigit ( String auxDigit ) {
		this.auxDigit = testAuxDigit ( auxDigit );
	}

	public String getApplicationCode () {
		return applicationCode;
	}

	public void setApplicationCode ( String auxDigit, String applicationCode ) {
		this.applicationCode = testApplicationCode ( auxDigit, applicationCode );
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = testIuv ( iuv );
	}

	@Override
	public String toString () {
		return auxDigit + applicationCode + iuv;
	}

	private static String testAuxDigit ( final String auxDigit ) {
		return test ( auxDigit, "Il codice Aux Digit non può essere vuoto." );
	}

	private static String testApplicationCode ( final String auxDigit, final String applicationCode ) {
		if ( ( auxDigit != null ) && ( auxDigit.equals ( "3" ) ) ) {
			return "";
		}
		return test ( applicationCode, "L'application code non può essere vuoto." );
	}

	private static String testIuv ( final String iuv ) {
		return test ( iuv, "Il codice IUV non può essere vuoto." );
	}

	private static String test ( final String str, final String msg ) {
		if ( StringUtils.isBlank ( str ) ) {
			throw new IllegalArgumentException ( msg );
		}
		return StringUtils.trimToNull ( str );
	}
}
