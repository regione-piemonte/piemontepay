/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static boolean isBlank(String val){
		if(val==null || val.trim().equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean isNotBlank(String val){
		return !isBlank(val);
	}
	
    public static boolean isValidApplicationId ( String val ) {
        String appIdToValidate = StringUtils.isBlank ( val ) ? "" : new String ( val );
        return StringUtils.isAlphanumeric ( appIdToValidate.replace ( "_", "" ).replace ( "-", "" ) );
    }
}
	
