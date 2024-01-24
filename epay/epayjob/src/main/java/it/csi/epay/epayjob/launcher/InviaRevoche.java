/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayjob.business.TrasmettiRichiesteDiRevoca;
import it.csi.epay.epayjob.utilities.EjbUtil;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;

public class InviaRevoche {
	
	private static LogUtil log = new LogUtil(InviaRevoche.class);
	
	public static void main(String[] args) throws Exception {
		final String methodName = "main";
		log.infoStart("main");		
		
		//CommonCliUtil cli = new CommonCliUtil(args);
        //EjbUtil ejbUtil = new EjbUtil("10.202.52.49:12111");
		//log.info(methodName, "server ejb : " + cli.getServers());

        EjbUtil ejbUtil = new EjbUtil("localhost:8081");

		//EjbUtil ejbUtil = new EjbUtil(cli.getServers(), cli.getUsername(), cli.getPassword());
		
		JobFacade jobFacade = ejbUtil.getEjb(JobFacade.class);
		MailFacade mailFacade = ejbUtil.getEjb(MailFacade.class);
		
		try {
			TrasmettiRichiesteDiRevoca trr = new TrasmettiRichiesteDiRevoca(jobFacade, mailFacade);
			trr.execute();
		} catch (Exception e) {
			log.error(methodName, ExceptionUtils.getStackTrace(e));
		} finally {
			ejbUtil.close();
		}
		log.infoEnd("main");
		System.exit(0);
	}


}
