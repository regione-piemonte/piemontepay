/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule.jobimpl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import it.csi.epay.epaymodric.business.epaymodric.bo.JobTypeEnum;
import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.schedule.JobTypes;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

@Service
@JobTypes(JobTypeEnum.JOB_PRINTER)
public class JobPrinter extends JobBase {
	
	private final Logger logger = LogManager.getLogger ( this.getClass () );

	@Override
	protected ScheduledJob executeJob(ScheduledJob scheduledJob) throws SchedulerException {
		logger.info("JobPrinter.execute : INIZIO");
		
		try {
			String msg = scheduledJob.getParams().getProperty("job.printer.message");
			String sleep = scheduledJob.getParams().getProperty("job.printer.sleep");
			
			logger.info("JobPrinter.execute : MESSAGGIO: " + msg);
			Thread.sleep(Long.parseLong(sleep));
			
			
		} catch (NumberFormatException | InterruptedException e) {
			logger.error("JobPrinter.execute : ERRORE",e);
			throw new SchedulerException( e );
		}
		
		logger.info("JobPrinter.execute : FINE");
		
		return scheduledJob;
	}
}
