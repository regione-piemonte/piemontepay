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
 * Comparatore custom per tipi Date (da usare con la DisplayTag)
 *
 * @author [GUIGEN]
 */
public class CsiDateComparator
		implements
			Comparator<String>,
			java.io.Serializable {

	/** Costante per il formato DATE */
	private static final String DATE_FORMAT = "dd/MM/yyyy";

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String o1, String o2) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		if (o1.equals("") && !o2.equals(""))
			return -1;
		if (!o1.equals("") && o2.equals(""))
			return 1;
		if (o1.equals("") && o2.equals(""))
			return 0;

		try {
			Date d1 = sdf.parse(o1);
			Date d2 = sdf.parse(o2);
			return d1.compareTo(d2);
		} catch (ParseException e) {
			return -1;
		}
	}

}
