/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

public final class CodeTest {
	
	public class ErrMessage{
		private String msg;
		
		public ErrMessage(String msg) {
			this.msg = msg;
		}
		
		public String get(){
			return msg;
		}
	}

	static final int posizioniDispari[] = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16,
			10, 22, 25, 24, 23 };
	static final int contributi[] = { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };
	static final char[][] codificaIuv = { { '1', '0' }, { '1', '1' }, { '1', '2' }, { '1', '3' }, { '1', '4' },
			{ '1', '5' }, { '1', '6' }, { '1', '7' }, { '1', '8' }, { '1', '9' }, { '2', '0' }, { '2', '1' },
			{ '2', '2' }, { '2', '3' }, { '2', '4' }, { '2', '5' }, { '2', '6' }, { '2', '7' }, { '2', '8' },
			{ '2', '9' }, { '3', '0' }, { '3', '1' }, { '3', '2' }, { '3', '3' }, { '3', '4' }, { '3', '5' } };

	static final String MSG10 = "La lunghezza del codice fiscale non &egrave; corretta.\n"
			+ "Il codice fiscale dovrebbe essere lungo esattamente 16 caratteri.";
	static final String MSG11 = "Il codice fiscale contiene dei caratteri non validi.\n"
			+ "I soli caratteri validi sono le lettere e le cifre.";
	static final String MSG12 = "Il codice fiscale non &egrave; corretto.";

	static final String MSG20 = "La lunghezza della partita IVA non &egrave;" + "corretta.\n"
			+ "La partita IVA dovrebbe essere lunga esattamente 11 caratteri.";
	static final String MSG21 = "La partita IVA contiene dei caratteri non ammessi.\n"
			+ "La partita IVA dovrebbe contenere solo cifre.";
	static final String MSG22 = "La partita IVA non &egrave; valida.";

	static final String MSG30 = "La lunghezza del codice IUV non &egrave;" + "corretta.\n"
			+ "Il codice IUV dovrebbe essere lungo esattamente 25 caratteri.";
	static final String MSG31 = "Il codice IUV contiene dei caratteri non ammessi.\n"
			+ "I soli caratteri validi sono le lettere e le cifre.";
	static final String MSG32 = "Il codice IUV non incomincia per RF.";
	static final String MSG33 = "Il codice IUV non &egrave; valido.";

	/*************************************************
	 * Funzione per il controllo del codice fiscale.
	 **************************************************/

	public static String checkCodiceFiscale(final String cf) {
		try {
			if (cf.length() != 16)
				throw new Exception(MSG10);
			if (!StringUtils.isAlphanumeric(cf))
				throw new Exception(MSG11);
			int checkDigit = 0;
			/* pari */
			for (int i = 1; i < 15; i += 2)
				checkDigit += charToPosition(cf.charAt(i));
			/* dispari */
			for (int i = 0; i < 15; i += 2)
				checkDigit += posizioniDispari[charToPosition(cf.charAt(i))];
			if (Character.toUpperCase(cf.charAt(15)) != (char) (checkDigit % 26 + (int) 'A'))
				throw new Exception(MSG12);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}

	private static int charToPosition(char ch) {
		if (CharUtils.isAsciiNumeric(ch)) {
			return CharUtils.toIntValue(ch);
		} else {
			return (int) (Character.toUpperCase(ch)) - (int) 'A';
		}
	}

	/****************************************************
	 * Funzione per il controllo della partita IVA.
	 *****************************************************/

	public static String checkPartitaIVA(final String pi) {
		try {
			if (pi.length() != 11)
				throw new Exception(MSG20);
			if (!StringUtils.isNumeric(pi))
				throw new Exception(MSG21);

			int somma = 0;
			/* pari */
			for (int i = 0; i < 11; i += 2)
				somma += CharUtils.toIntValue(pi.charAt(i));
			/* dispari */
			for (int i = 1; i < 11; i += 2)
				somma += contributi[CharUtils.toIntValue(pi.charAt(i))];
			if (somma % 10 != 0)
				throw new Exception(MSG22);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}

	/****************************************************
	 * Funzione per il controllo del codice IUV - Creditor Reference -  ISO 11649
	 *****************************************************/

	// Standard ISO 11649:2009
	public static String checkIuvStarndard(final String iuv) {
		try {
			if (iuv.length() != 25)
				throw new Exception(MSG30);
			if (!StringUtils.isAlphanumeric(iuv))
				throw new Exception(MSG31);
			if (!iuv.regionMatches(true, 0, "RF", 0, 2))
				throw new Exception(MSG32);
			
			String invIuv = iuv.substring(4).toUpperCase().concat(iuv.substring(0,4)).toUpperCase(); 
			List<Character> listIuv = new ArrayList<Character>();

			for (int i = 0 ; i < invIuv.length() ; i++) {
				char c = invIuv.charAt(i);
				if (CharUtils.isAsciiNumeric(c)) {
					listIuv.add(c);
				} else {
					listIuv.add(codificaIuv[(int)c-'A'][0]);
					listIuv.add(codificaIuv[(int)c-'A'][1]);
				}
			}
			BigDecimal numIuv = new BigDecimal(ArrayUtils.toPrimitive(listIuv.toArray(new Character[0])));
			if (!numIuv.remainder(new BigDecimal(97)).equals(BigDecimal.ONE))
				throw new Exception(MSG33);
			
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}

	public static void main(String[] args) {
		String cf = "MRACAI00A01L219J";
		String rt = checkCodiceFiscale(cf);
		System.out.println('*'+rt+'*');
	}
}
