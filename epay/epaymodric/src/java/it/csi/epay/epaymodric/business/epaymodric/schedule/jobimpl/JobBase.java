/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule.jobimpl;

import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.manager.JobSchedulerManager;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

public abstract class JobBase implements Job {
	
private final Logger logger = LogManager.getLogger ( this.getClass () );
	
	@Autowired
	private JobSchedulerManager jobSchedulerManager;
	
	
	protected abstract ScheduledJob executeJob(ScheduledJob scheduledJob) throws SchedulerException; 

	@Override
	@Async
	@Transactional( propagation = Propagation.REQUIRES_NEW )
	public ScheduledJob execute(ScheduledJob scheduledJob) throws SchedulerException {
		logger.info("JobBase.execute : INIZIO");
		
		try {
			executeJob ( scheduledJob );
			
		} catch (Exception e) {
			logger.error("JobBase.execute : ERRORE DURANTE L'ESECUZIONE DEL JOB " + scheduledJob.getJobTypeEmum().name() + " (ID=" + scheduledJob.getId() + ") " ,e);
		}
		
		try {
			scheduledJob.setFineUltimaEsecuzione(new Date());
			jobSchedulerManager.updateJob(scheduledJob);
		} catch (Exception e) {
			logger.error("JobBase.execute : ERRORE DURANTE LA MODIFICA DELLA DATA FINE ULTIMA ESECUZIONE DEL JOB " + scheduledJob.getJobTypeEmum().name() + " (ID=" + scheduledJob.getId() + ") " ,e);
		}
		
		logger.info("JobBase.execute : FINE");
		
		return scheduledJob;
	}

}
