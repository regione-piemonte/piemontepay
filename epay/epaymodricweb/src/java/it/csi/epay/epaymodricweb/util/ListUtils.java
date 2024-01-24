/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListUtils implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2696628117323923562L;

	public static boolean isEmptyList(ArrayList<?> list){
		if (list == null || list.isEmpty() || (list.size() == 1 && list.get(0) == null))
			return true;
		else
			return false;
	}
	
	
	public static boolean isNotEmptyList(ArrayList<?> list){
		if (list == null || list.isEmpty() || (list.size() == 1 && list.get(0) == null))
			return false;
		else
			return true;
	}
	
	public static List<?> convertArrayToList(Object[] input){
		List<Object> output=new ArrayList<Object>();
		if (input !=null && input.length>0)
		{
			for(int i=0;i<input.length;i++)
				output.add(input[i]);
		}
		return output;
	}
	
	public static ArrayList<?> convertArrayToArrayList(Object[] input){
		ArrayList<Object> output=new ArrayList<Object>();
		if (input !=null && input.length>0)
		{
			for(int i=0;i<input.length;i++)
				output.add(input[i]);
		}
		return output;
	}
}
