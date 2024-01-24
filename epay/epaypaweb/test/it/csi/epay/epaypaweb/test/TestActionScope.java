/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.util.Date;

import org.junit.Test;

import it.csi.epay.epaypaweb.presentation.dto.ActionScope;

public class TestActionScope {

	private String date2String(Date d) {
		return d.toString();
	}

	@Test
	public void testDateValue() {
		ActionScope actionScope = new ActionScope();

		actionScope.putValue("date", new Date());

		System.out.println(date2String(actionScope.getValue("date")));
		
		// ---------- test cast
		
		Integer value = 0;
		
		double numval = 3.0; // 2.017063E7;

		System.out.println("numval:" + numval);
		
		if ((numval % 1) == 0) {
			System.out.println("it is integer");
			
		} else {
			System.out.println("it is NOT integer");
		}
		value = (int) numval;
		
		System.out.println("value:" + value);
		
		// ------------ print object
		Object x = new Integer(123);
		System.out.println("x:" + x);
		System.out.println("x.toString():" + x.toString());
		
	}
}
