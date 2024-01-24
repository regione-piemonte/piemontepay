/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule.jobimpl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.epay.epaymodric.business.epaymodric.bo.JobTypeEnum;
import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.manager.PrenotazioneReportManager;
import it.csi.epay.epaymodric.business.epaymodric.schedule.JobTypes;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

@Component
@JobTypes(JobTypeEnum.REPORT_BUILDER)
public class ReportBuilderJob extends JobBase implements InitializingBean {

	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired
	PrenotazioneReportManager prenotazioneReportManager;

	@Override
	public void afterPropertiesSet() throws Exception {
	}
	

	@Override
	protected ScheduledJob executeJob(ScheduledJob scheduledJob) throws SchedulerException {
		String reportBuilderSizeStr = scheduledJob.getParams().getProperty("report.builder.size");
		String reportBuilderRecordsPageSize = scheduledJob.getParams().getProperty("report.builder.records.page.size");	
		
		if(StringUtils.isEmpty(reportBuilderSizeStr)) {
			log.error("IL PARAMETRO DENOMINATO 'report.builder.size' PER IL JOB 'REPORT_BUILDER' NON CONFIGURATO");
			return scheduledJob;
		}
		if(StringUtils.isEmpty(reportBuilderRecordsPageSize)) {
			log.error("IL PARAMETRO DENOMINATO 'report.builder.records.page.size' PER IL JOB 'REPORT_BUILDER' NON CONFIGURATO");
			return scheduledJob;
		}
		
		log.info("START JOB 'REPORT_BUILDER'");
		log.info("JOB 'REPORT_BUILDER' PARAMETRI DI CONFIGURAZIONE: report.builder.records.page.size='" + reportBuilderSizeStr + "' - report.builder.records.page.size='" + reportBuilderRecordsPageSize + "'");
		
		boolean running = prenotazioneReportManager.isRunning();
		log.info("STATO EJB ReportBuilderBusiness: " + running + " ");
		
		if(!running) {
			log.info("START EJB ReportBuilderBusiness");
			prenotazioneReportManager.crateReport(NumberUtils.toInt(reportBuilderSizeStr, 1), NumberUtils.toInt(reportBuilderRecordsPageSize, 100));
			log.info("ATTIVATO EJB ReportBuilderBusiness" );
		}else {
			log.info("STATO EJB ReportBuilderBusiness ANCORA IN ESECUZIONE");
		}
		log.info("STOP JOB 'REPORT_BUILDER'");
		
		return scheduledJob;
	}
}
