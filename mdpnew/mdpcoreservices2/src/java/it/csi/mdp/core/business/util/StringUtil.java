/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;
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
	
}
	
