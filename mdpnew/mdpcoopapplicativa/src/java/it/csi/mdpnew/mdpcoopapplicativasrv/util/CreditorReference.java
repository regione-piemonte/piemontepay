/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.util;

import java.util.HashMap;

/**
 * Calcolo dei check digits del Creditor reference
 * 
 * @author Paolo 
 *
 */
public final class CreditorReference {

	
	public final HashMap<String, Integer> getMappaValori(){
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		
		hm.put("0", 0);
		hm.put("1", 1);
		hm.put("2", 2);
		hm.put("3", 3);
		hm.put("4", 4);
		hm.put("5", 5);
		hm.put("6", 6);
		hm.put("7", 7);
		hm.put("8", 8);
		hm.put("9", 9);
		hm.put("A", 10);
		hm.put("B", 11);
		hm.put("C", 12);
		hm.put("D", 13);
		hm.put("E", 14);
		hm.put("F", 15);
		hm.put("G", 16);
		hm.put("H", 17);
		hm.put("I", 18);
		hm.put("J", 19);
		hm.put("K", 20);
		hm.put("L", 21);
		hm.put("M", 22);
		hm.put("N", 23);
		hm.put("O", 24);
		hm.put("P", 25);
		hm.put("Q", 26);
		hm.put("R", 27);
		hm.put("S", 28);
		hm.put("T", 29);
		hm.put("U", 30);
		hm.put("V", 31);
		hm.put("W", 32);
		hm.put("X", 33);
		hm.put("Y", 34);
		hm.put("Z", 35);
		hm.put("a", 10);
		hm.put("b", 11);
		hm.put("c", 12);
		hm.put("d", 13);
		hm.put("e", 14);
		hm.put("f", 15);
		hm.put("g", 16);
		hm.put("h", 17);
		hm.put("i", 18);
		hm.put("j", 19);
		hm.put("k", 20);
		hm.put("l", 21);
		hm.put("m", 22);
		hm.put("n", 23);
		hm.put("o", 24);
		hm.put("p", 25);
		hm.put("q", 26);
		hm.put("r", 27);
		hm.put("s", 28);
		hm.put("t", 29);
		hm.put("u", 30);
		hm.put("v", 31);
		hm.put("w", 32);
		hm.put("x", 33);
		hm.put("y", 34);
		hm.put("z", 35);
		
		return hm;
		
	}
	
}
