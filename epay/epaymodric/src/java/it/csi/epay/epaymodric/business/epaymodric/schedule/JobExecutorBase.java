/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import it.csi.epay.epaymodric.business.epaymodric.bo.JobTypeEnum;
import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.manager.JobSchedulerManager;
import it.csi.epay.epaymodric.business.epaymodric.schedule.jobimpl.Job;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

public class JobExecutorBase {
	
	@Autowired
	@JobTypes(JobTypeEnum.JOB_PRINTER)
	Job printer;
	
	@Autowired
	@JobTypes(JobTypeEnum.JOB_PRINTER2)
	Job printer2;
	
	@Autowired
	@JobTypes(JobTypeEnum.JOB_PRINTER3)
	Job printer3;
	
	@Autowired
	@JobTypes(JobTypeEnum.REPORT_CLEANER)
	Job reportCleaner;
	
	@Autowired
	@JobTypes(JobTypeEnum.REPORT_BUILDER)
	Job reportBuilder;
	
	@Autowired
	private JobSchedulerManager jobSchedulerManager;

	protected void executeJob(ScheduledJob scheduledJob) throws SchedulerException {
		Job jobImpl = getJobImplByType(scheduledJob.getJobTypeEmum());
		
		scheduledJob.setInizioUltimaEsecuzione(new Date());
		scheduledJob.setFineUltimaEsecuzione(null);
		jobSchedulerManager.updateJob(scheduledJob);
		
		jobImpl.execute(scheduledJob);
		
	}
	
	protected Job getJobImplByType(JobTypeEnum jobType) {
		Job jobImpl = null;
		switch( jobType ) {
		case JOB_PRINTER:
			jobImpl = printer;
			break;
		case JOB_PRINTER2:
			jobImpl = printer2;
			break;
		case JOB_PRINTER3:
			jobImpl = printer3;
			break;
		case REPORT_CLEANER:
			jobImpl = reportCleaner;
			break;
		case REPORT_BUILDER:
			jobImpl = reportBuilder;
			break;
		default:
			throw new IllegalArgumentException("Scheduled job type not defined " + jobType);
		}
		
		return jobImpl;
	}
}
