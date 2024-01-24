/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Comparatore custom per tipi DateTime  (da usare con la DisplayTag)
 *
 * @author [GUIGEN]
 */
public class CsiDateTimeComparator
		implements
			Comparator<String>,
			java.io.Serializable {

	/** Costante per il formato DATE */
	private static final String DATE_FORMAT = "dd/MM/yyyy";

	/** Costante per il formato HOUR SHORT */
	private static final String HOUR_SHORT_FORMAT = "hh:mm";

	/** Costante per il formato HOUR EXTENDED */
	private static final String HOUR_EXTENDED_FORMAT = "hh:mm:ss";

	/** Costante per il formato DATETIME SHORT */
	private static final String DATETIME_SHORT_FORMAT = DATE_FORMAT + "-"
			+ HOUR_SHORT_FORMAT;

	/** Costante per il formato DATETIME EXTENDED */
	private static final String DATETIME_EXTENDED_FORMAT = DATE_FORMAT + "-"
			+ HOUR_EXTENDED_FORMAT;

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String o1, String o2) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		try {
			if (o1.equals("") && !o2.equals(""))
				return -1;
			if (!o1.equals("") && o2.equals(""))
				return 1;
			if (o1.equals("") && o2.equals(""))
				return 0;

			// estrapolo la data dalle stringhe
			int sepIdx1 = o1.indexOf("-");
			int sepIdx2 = o2.indexOf("-");
			String sd1 = sepIdx1 > -1 ? o1.substring(0, sepIdx1) : o1;
			String sd2 = sepIdx2 > -1 ? o2.substring(0, sepIdx2) : o2;

			// converto le stringhe della date in oggetti di tipo Date
			Date d1 = sdf.parse(sd1);
			Date d2 = sdf.parse(sd2);

			// confronto gli oggetti Date
			if (d1.compareTo(d2) != 0) {
				// le due date sono differenti, ne ritorno il confronto
				return d1.compareTo(d2);
			} else {
				// le due date sono uguali, confronto la parte delle ore, se presenti
				String sm1 = sepIdx1 > -1
						? o1.substring(o1.indexOf("-") + 1)
						: "00:00";
				String sm2 = sepIdx2 > -1
						? o2.substring(o2.indexOf("-") + 1)
						: "00:00";
				return sm1.compareTo(sm2);
			}
		} catch (ParseException e) {
			return -1;
		}
	}

}
