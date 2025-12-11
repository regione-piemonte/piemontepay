/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_APPLICATION_CODE_NOT_VALUED;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_AUX_DIGIT_NOT_VALUED;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_AVVISO_CON_AUX_DIGIT_NOT_VALUED_AND_IUV_NOT_RF;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_NOT_VALUED;
import static it.csi.epay.epayfeapi.util.Constants.RF;


@SuppressWarnings ( "unused" )
public class CodiceAvvisoDTO implements Serializable {

	private static final long serialVersionUID = -4796859698053178218L;

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

	/* RDI-54 MODELLO UNICO - BEGIN */

	static public String codiceAvvisoString ( final String auxDigit, final String applicationCode, final String iuv ) throws IllegalArgumentException {
		if ( StringUtils.isBlank ( iuv ) ) {
			throw new IllegalArgumentException ( ERROR_IUV_NOT_VALUED );
		}
		var iuvOk = StringUtils.trimToEmpty ( iuv );

		if ( StringUtils.isBlank ( auxDigit ) ) {
			if ( !iuvOk.startsWith ( RF ) ) {
				throw new IllegalArgumentException ( ERROR_CODICE_AVVISO_CON_AUX_DIGIT_NOT_VALUED_AND_IUV_NOT_RF );
			}
		}
		var auxDigitOk = StringUtils.trimToEmpty ( auxDigit );

		var applicationCodeOk = StringUtils.trimToEmpty ( applicationCode );

		return String.format ( "%s%s%s", auxDigitOk, applicationCodeOk, iuvOk );
	}
	/* RDI-54 MODELLO UNICO - END */

	private static String testAuxDigit ( final String auxDigit ) {
		return test ( auxDigit, ERROR_AUX_DIGIT_NOT_VALUED );
	}

	private static String testApplicationCode ( final String auxDigit, final String applicationCode ) {
		if ( ( auxDigit != null ) && ( auxDigit.equals ( "3" ) ) ) {
			return "";
		}
		return test ( applicationCode, ERROR_APPLICATION_CODE_NOT_VALUED );
	}

	private static String testIuv ( final String iuv ) {
		return test ( iuv, ERROR_IUV_NOT_VALUED );
	}

	private static String test ( final String str, final String msg ) {
		if ( StringUtils.isBlank ( str ) ) {
			throw new IllegalArgumentException ( msg );
		}
		return StringUtils.trimToNull ( str );
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
}
