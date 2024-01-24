/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.manager.JobSchedulerManager;
import it.csi.epay.epaymodric.business.epaymodric.schedule.plan.SchedulePlanConfig;

@Service
public class JobSchedulerImpl extends JobSchedulerBase implements JobScheduler {
	
	private static final long RATE_MILLIS = 30000L; // 30 secondi
	
	private final Logger logger = LogManager.getLogger ( this.getClass () );
	
	@Autowired
	private JobExecutor jobExecutor;
	
	@Autowired
	private JobSchedulerManager jobSchedulerManager;

	@Override
	@Scheduled ( fixedRate = RATE_MILLIS )
	//@Async
	@Transactional ( readOnly = true )
	public void execute() {
		long timestamp = System.currentTimeMillis();
		try {
			jobSchedulerManager.obtainLock(timestamp);
//			logger.info("JobSchedulerImpl.execute: INIZIO " + timestamp);
			
			List<ScheduledJob> jobs = jobSchedulerManager.findAllActiveJobs();
			for ( ScheduledJob job : jobs ) {
				logger.debug ( "JobSchedulerImpl.execute: JOB " + job.toString());
				
				SchedulePlanConfig schedulePlan = buildSchedulePlanConfig(job.getScheduleExpression());
				if ( isInRange ( new Date(), schedulePlan ) ) {
					jobExecutor.executeScheduledJob( job );
				} else {
					logger.debug("out of schedule plan");
				}
			}
			
			//Thread.sleep(RATE_MILLIS*2);
//			logger.info("JobSchedulerImpl.execute: FINE " + timestamp);
		} catch (Exception e) {
			logger.error("JobSchedulerImpl.execute: ERRORE " + timestamp, e);
			e.printStackTrace();
		}

	}
}
