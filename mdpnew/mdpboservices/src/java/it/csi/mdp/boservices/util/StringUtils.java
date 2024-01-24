/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.util;

public class StringUtils {
	
	public static String trimToEmpty(String str) {
		return trimToDefault(str, "");
	}
	
	public static String trimToDefault(String str, String defaultStr) {
		String result = defaultStr;
		if(str != null) {
			result = str.trim();
		}
		return result;
	}
	
}
