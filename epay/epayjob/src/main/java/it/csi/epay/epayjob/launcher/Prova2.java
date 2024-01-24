/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;


import java.util.UUID;


public class Prova2 {

	
	public static void main(String[] args) throws Exception {
		System.out.println("START - 35");
		
		String r35 = "TNP".concat(UUID.randomUUID().toString().replace("-", ""));
		
		System.out.println(r35);
		System.out.println(r35.length());
		
		
		System.exit(0);
	}


}
