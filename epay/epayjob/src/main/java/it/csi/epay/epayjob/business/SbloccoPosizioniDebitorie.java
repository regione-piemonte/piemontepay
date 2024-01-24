/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;

public class SbloccoPosizioniDebitorie extends RtXml {
	
	private static final String CONFIG_PROPERTIES = "config.properties";
	
	private final LogUtil log;
	private final JobFacade jobFacade;
//	private final MailFacade mailFacade;
//	private final String url;
	
	public SbloccoPosizioniDebitorie(JobFacade jobFacade) throws IllegalArgumentException {
		super();
		final String methodName = "SbloccoPosizioniDebitorie";
		log = new LogUtil(this.getClass());
		log.infoStart(methodName);
		this.jobFacade = jobFacade;
//		this.mailFacade = mailFacade;
		
	}

	public void execute() throws Exception {
		final String methodName = "execute";
		log.infoStart(methodName);
		
		try {
//		    Sblocca le posizioni debitorie pagate  bloccate in stato in attesa, chiudendole
		    jobFacade.chiudiPosiszioniDebitoriePagate ();
		    
		    
//		    Sblocca le posizioni debitorie da pagare in stato in attesa, aprendole
		    jobFacade.apriPosiszioniDebitoriePagabili ();
		    
            
				
		} catch (Exception nde) {
			log.info(methodName, "errore in sblocco posizioni debitorie");
		}
		
		log.infoEnd(methodName);
	}


}
