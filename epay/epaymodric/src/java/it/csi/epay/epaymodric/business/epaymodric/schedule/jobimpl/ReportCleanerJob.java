/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule.jobimpl;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.epay.epaymodric.business.epaymodric.manager.PrenotazioneReportManager;
import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.schedule.JobTypes;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;
import it.csi.epay.epaymodric.business.epaymodric.bo.JobTypeEnum;

@Component
@JobTypes(JobTypeEnum.REPORT_CLEANER)
public class ReportCleanerJob implements Job {
	
	private final static String CLASSNAME = ReportCleanerJob.class.getSimpleName();

	private final Logger logger = LogManager.getLogger ( this.getClass () );
	
	@Autowired
	PrenotazioneReportManager prenotazioneReportManager;

	@Override
	public ScheduledJob execute(ScheduledJob scheduledJob) throws SchedulerException {
		
		String reportCleanerStatesStr = scheduledJob.getParams().getProperty("report.cleaner.report.states");
		String reportCleanerThresholdDays = scheduledJob.getParams().getProperty("report.cleaner.threshold.days");
		
		if(StringUtils.isEmpty(reportCleanerStatesStr)) {
			logger.error("IL PARAMETRO DENOMINATO 'report.cleaner.report.states' PER IL JOB 'REPORT_CLEANER' NON CONFIGURATO");
			return scheduledJob;
		}
		if(StringUtils.isEmpty(reportCleanerThresholdDays)) {
			logger.error("IL PARAMETRO DENOMINATO 'report.cleaner.threshold.days' PER IL JOB 'REPORT_CLEANER' NON CONFIGURATO");
			return scheduledJob;
		}
		
		logger.info("START JOB 'REPORT_CLEANER'");
		
		logger.info("JOB 'REPORT_CLEANER' PARAMETRI DI CONFIGURAZIONE: report.cleaner.report.states='" + reportCleanerStatesStr + "' - report.cleaner.threshold.days='" + reportCleanerThresholdDays + "'");
		
		
		boolean running = prenotazioneReportManager.isRunning();
		logger.info("STATO EJB ReportCleanerBusiness: " + running + " ");
		
		if(!running) {
			logger.info("START EJB ReportCleanerBusiness");
			prenotazioneReportManager.cancellazioneReport(Arrays.asList(reportCleanerStatesStr.split(",")), NumberUtils.toInt(reportCleanerThresholdDays, 10));
			logger.info("ATTIVATO EJB ReportBuilderBusiness" );
		}else {
			logger.info("STATO EJB ReportCleanerBusiness ANCORA IN ESECUZIONE");
		}
		
		logger.info("STOP JOB 'REPORT_CLEANER'");
		return scheduledJob;
	}
	
}

