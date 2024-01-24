/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 *
 */

public final class BusinessUtil {

	private BusinessUtil () {
	}

	//SE NON INIZIA PER 0 E PER 3 -> NON E' UNO IUV STACCATO DAL NOSTRO SISTEMA
	//SE INIZIA CON 0, SUBSTRING DA 3 IN AVANTI
	//SE INIZIA CON 3, SUBSTRING DA 1 IN AVANTI
	//SE INIZIA CON ALTRO NUMERO, NESSUN SUBSTRING
	public static String fixIUV ( String iuv ) {

		char startingChar = iuv.charAt ( 0 );
		switch ( startingChar ) {
		case '0' :
			return iuv.substring ( 3 );
		case '3' :
			return iuv.substring ( 1 );
		default :
			return iuv;
		}
	}

	public static String componiCausaleVersamento ( String iuv, double importo, String descrizioneCausaleVersamento ) {
		StringBuffer composizioneCausale = new StringBuffer (); 
		if ( iuv.length () == 25 ) {
			composizioneCausale.append ( "/RFS/" );
		} else {
			composizioneCausale.append ( "/RFB/" );
		}

		composizioneCausale.append ( iuv );
		composizioneCausale.append ( "/" );
		composizioneCausale.append ( BigDecimal.valueOf ( importo ).setScale ( 2, RoundingMode.HALF_UP ) );
		if ( StringUtils.isNotEmpty ( descrizioneCausaleVersamento ) ) {
			composizioneCausale.append ( "/TXT/" ).append ( descrizioneCausaleVersamento );
		}

		return composizioneCausale.toString ();
	}
}
