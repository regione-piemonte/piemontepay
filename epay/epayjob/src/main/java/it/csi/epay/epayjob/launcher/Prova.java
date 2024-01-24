/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;
import it.csi.epay.epayjob.utilities.XmlUtil;
import it.csi.epay.epayservices.interfaces.ejb.EnteFacade;

public class Prova {

	
	public static void main(String[] args) throws Exception {
		System.out.println("START");
		
		CommonCliUtil cli = new CommonCliUtil(args);
		//EjbUtil ejbUtil = new EjbUtil("10.202.52.49:12111");
		EjbUtil ejbUtil = new EjbUtil(cli.getServers());
		
		try {
			EnteFacade enteFacade = ejbUtil.getEjb(EnteFacade.class);
			System.out.println(XmlUtil.obj2Xml(enteFacade.listaEntiConTasse()));
		} finally {
			System.out.println("closing context.");
			ejbUtil.close();
			System.out.println("END");

		}
		System.exit(0);
	}


}
