/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import it.csi.epay.epayjob.business.GeneratePdf;
import it.csi.epay.epayjob.business.mail.InviaMailRicezioneRt;
import it.csi.epay.epayjob.model.RtData;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.Parametro;

public class GeneratePdfRt {
	
	private static LogUtil log = new LogUtil(GeneratePdfRt.class);
	
	public static void main(String[] args) throws Exception {
		log.infoStart("main");
		
		CommonCliUtil cli = new CommonCliUtil(args);
		//EjbUtil ejbUtil = new EjbUtil("10.202.52.49:12111");
		EjbUtil ejbUtil = new EjbUtil(cli.getServers());
		
		try {
			JobFacade jobFacade = ejbUtil.getEjb(JobFacade.class);
			GeneratePdf pdf = new GeneratePdf(jobFacade);
			RtData rtData = pdf.execute();
			
			Parametro inviaMailParam = jobFacade.ricercaParametro("job_generaPdfRt", "inviaMailRicezioneRt");
			if (Boolean.parseBoolean(inviaMailParam.getValore())) {
				MailFacade mailFacade = ejbUtil.getEjb(MailFacade.class);
				InviaMailRicezioneRt mail = new InviaMailRicezioneRt(jobFacade, mailFacade);
				mail.execute(rtData);
			}
			
		} finally {
			ejbUtil.close();
		}
		log.infoEnd("main");
		System.exit(0);
	}	
}
