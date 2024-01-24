/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.exception.SchedulerException;
import it.csi.epay.epaypaweb.persistence.dad.SchedulerDad;
import it.csi.epay.epaypaweb.schedule.jobimpl.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Date;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class JobExecutorBase {
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".scheduler");

	@Inject
	protected SchedulerDad schedulerDad;

	@Inject
	@JobTypes(JobTypeEnum.MAIL_SENDER)
	protected Job mailSenderJob;
	
	
	@Inject
	@JobTypes(JobTypeEnum.REPORT_BUILDER)
	protected Job reportBuilderJob;
	
	@Inject
	@JobTypes(JobTypeEnum.REPORT_CLEANER)
	protected Job reportCleanerJob;

	// ottiene l'impl del job da eseguire, lo esegue e aggiorna la data di ultima esecuzione per tale job
	protected void executeJob(JobDto jobDto) throws SchedulerException {
		Job jobImpl = getJobImplByType(jobDto.getJobTypeEmum());
		JobDto updatedJobDto = jobImpl.execute(jobDto);
		updatedJobDto.setUltimaEsecuzione(new Date());
		schedulerDad.updateJob(updatedJobDto);
	}

	protected Job getJobImplByType(JobTypeEnum type) {
		Job jobImpl = null;

		switch (type) {
		case MAIL_SENDER:
			jobImpl = mailSenderJob;
			break;
		case REPORT_BUILDER:
			jobImpl = reportBuilderJob;
			break;
		case REPORT_CLEANER:
			jobImpl = reportCleanerJob;
			break;
		default:
			throw new IllegalArgumentException("Scheduled job type not defined " + type);
		}

		return jobImpl;
	}
}
