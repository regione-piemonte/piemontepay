/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule.jobimpl;

import it.csi.epay.epaypaweb.business.interf.ReportCleanerBusiness;
import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.exception.SchedulerException;
import it.csi.epay.epaypaweb.schedule.JobTypes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

@JobTypes(JobTypeEnum.REPORT_CLEANER)
public class ReportCleanerJob implements Job {
	
	static private final String CLASSNAME = ReportCleanerJob.class.getSimpleName();

	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".business");
	
	@EJB
	private ReportCleanerBusiness reportCleanerBusiness;

	@Override
	public JobDto execute(JobDto jobDto) throws SchedulerException {
		String methodName = "execute";
		String reportCleanerStatesStr = jobDto.getParams().getProperty("report.cleaner.report.states");
		String reportCleanerThresholdDays = jobDto.getParams().getProperty("report.cleaner.threshold.days");
		
		if(StringUtils.isEmpty(reportCleanerThresholdDays)) {
			log.error("IL PARAMETRO DENOMINATO 'report.cleaner.report.states' PER IL JOB 'REPORT_CLEANER' NON CONFIGURATO");
			return jobDto;
		}
		if(StringUtils.isEmpty(reportCleanerThresholdDays)) {
			log.error("IL PARAMETRO DENOMINATO 'report.cleaner.threshold.days' PER IL JOB 'REPORT_CLEANER' NON CONFIGURATO");
			return jobDto;
		}
		
		log.info("START JOB 'REPORT_CLEANER'");
		log.info ( CLASSNAME + " " + methodName + " - START" );
		
		log.info("JOB 'REPORT_CLEANER' PARAMETRI DI CONFIGURAZIONE: report.cleaner.report.states='" + reportCleanerStatesStr + "' - report.cleaner.threshold.days='" + reportCleanerThresholdDays + "'");
		
		
		boolean running = reportCleanerBusiness.isRunning();
		log.info("STATO EJB ReportCleanerBusiness: " + running + " ");
		
		if(!running) {
			log.info("START EJB ReportCleanerBusiness");
			reportCleanerBusiness.cancellazioneReport(reportCleanerStatesStr.split(","), NumberUtils.toInt(reportCleanerThresholdDays, 10));
			log.info("ATTIVATO EJB ReportBuilderBusiness" );
		}else {
			log.info("STATO EJB ReportCleanerBusiness ANCORA IN ESECUZIONE");
		}
		
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		log.info("STOP JOB 'REPORT_CLEANER'");
		return jobDto;
	}
}
