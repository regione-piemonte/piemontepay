/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule.jobimpl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import it.csi.epay.epaymodric.business.epaymodric.bo.JobTypeEnum;
import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.schedule.JobTypes;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

@Component
@JobTypes(JobTypeEnum.JOB_PRINTER3)
public class JobPrinter3 implements Job {
	
	private final Logger logger = LogManager.getLogger ( this.getClass () );

	@Override
	public ScheduledJob execute(ScheduledJob scheduledJob) throws SchedulerException {
		logger.info("JobPrinter3.execute : INIZIO");
		
		try {
			String msg = scheduledJob.getParams().getProperty("job.printer.message");
			String sleep = scheduledJob.getParams().getProperty("job.printer.sleep");
			
			logger.info("JobPrinter3.execute : MESSAGGIO: " + msg);
			Thread.sleep(Long.parseLong(sleep));
		} catch (NumberFormatException | InterruptedException e) {
			logger.error("JobPrinter3.execute : ERRORE",e);
			throw new SchedulerException( e );
		}
		
		logger.info("JobPrinter3.execute : FINE");
		
		return scheduledJob;
	}

}
