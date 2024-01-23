/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public final class DateUtil {

	public static Timestamp currentTimestamp () {
		Calendar calendar = Calendar.getInstance ();
		Date now = calendar.getTime ();
		return new Timestamp ( now.getTime () );
	}
}
