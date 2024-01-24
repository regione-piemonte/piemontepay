/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;


public class CodiceAvviso implements Serializable {

	private static final long serialVersionUID = -1260731513589069198L;

	private String auxDigit;

	private String applicationCode;

	private String iuv;

	public CodiceAvviso ( final String auxDigit, final String applicationCode, final String iuv ) {
		setAuxDigit ( auxDigit );
		setApplicationCode ( auxDigit, applicationCode );
		setIuv ( iuv );
	}

	public CodiceAvviso ( final String auxDigit, final String applicationCode, final String iuv, final Boolean force ) {
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

	/* Pagamento model emette IllegalArgumentException che va gestita esternamente - BEGIN */
	static public String codiceAvvisoString ( final String auxDigit, final String applicationCode, final String iuv ) throws IllegalArgumentException {
		return testAuxDigit ( auxDigit ) + testApplicationCode ( auxDigit, applicationCode ) + testIuv ( iuv );
	}
	/* Pagamento model emette IllegalArgumentException che va gestita esternamente - END */

	/**
	 * @return the auxDigit
	 */
	public String getAuxDigit () {
		return auxDigit;
	}

	/**
	 * @param auxDigit the auxDigit to set
	 */
	public void setAuxDigit ( String auxDigit ) throws IllegalArgumentException {
		this.auxDigit = testAuxDigit ( auxDigit );
	}

	/**
	 * @return the applicationCode
	 */
	public String getApplicationCode () {
		return applicationCode;
	}

	/**
	 * @param applicationCode the applicationCode to set
	 */
	public void setApplicationCode ( String auxDigit, String applicationCode ) throws IllegalArgumentException {
		this.applicationCode = testApplicationCode ( auxDigit, applicationCode );
	}

	/**
	 * @return the iuv
	 */
	public String getIuv () {
		return iuv;
	}

	/**
	 * @param iuv the iuv to set
	 */
	public void setIuv ( String iuv ) throws IllegalArgumentException {
		this.iuv = testIuv ( iuv );
	}

	@Override
	public String toString () {
		return auxDigit + applicationCode + iuv;
	}

	static private String testAuxDigit ( final String auxDigit ) throws IllegalArgumentException {
		return test ( auxDigit, "Il codice Aux Digit non puo' essere vuoto." );
	}

	static private String testApplicationCode ( final String auxDigit, final String applicationCode ) throws IllegalArgumentException {
		if ( ( auxDigit != null ) && ( auxDigit.equals ( "3" ) ) ) {
			return "";
		}
		return test ( applicationCode, "L'application code non puo' essere vuoto." );
	}

	static private String testIuv ( final String iuv ) throws IllegalArgumentException {
		return test ( iuv, "Il codice IUV non puo' essere vuoto." );
	}

	static private String test ( final String str, final String msg ) throws IllegalArgumentException {
		if ( StringUtils.isBlank ( str ) ) {
			throw new IllegalArgumentException ( msg );
		}
		return StringUtils.trimToNull ( str );
	}
}
