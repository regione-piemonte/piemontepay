/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.util;

import java.text.DecimalFormat;


public class PDFUtil {

	private static final String TEN_DOUBLE_FORMAT = "0000000000";

	public static String doubleWithTenZeros ( double db ) {
		DecimalFormat df = new DecimalFormat ( TEN_DOUBLE_FORMAT );
		return df.format ( db * 100.0 );
	}
}
