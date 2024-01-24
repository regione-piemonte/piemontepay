/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.facade;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

public class EPaypaFacadeAdapter {

	static public Date toDate(XMLGregorianCalendar xmlGregorianCalendar) {
		Date date = null;

		if (xmlGregorianCalendar != null) {
			date = xmlGregorianCalendar.toGregorianCalendar().getTime();
		}

		return date;
	}

}
