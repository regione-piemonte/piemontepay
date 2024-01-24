/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule.jobimpl;

import it.csi.epay.epaypaweb.business.interf.ReportManagerBusiness;
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

@JobTypes(JobTypeEnum.REPORT_BUILDER)
public class ReportBuilderJob implements Job {
	static private final String CLASSNAME = ReportBuilderJob.class.getSimpleName();

	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".business");
		
	@EJB
	private ReportManagerBusiness reportManagerBusiness;
	
	@Override
	public JobDto execute(JobDto jobDto) throws SchedulerException {
		String methodName = "execute";
		String reportBuilderSizeStr = jobDto.getParams().getProperty("report.builder.size");
		String reportBuilderRecordsPageSize = jobDto.getParams().getProperty("report.builder.records.page.size");
		
		if(StringUtils.isEmpty(reportBuilderSizeStr)) {
			log.error("IL PARAMETRO DENOMINATO 'report.builder.size' PER IL JOB 'REPORT_BUILDER' NON CONFIGURATO");
			return jobDto;
		}
		if(StringUtils.isEmpty(reportBuilderRecordsPageSize)) {
			log.error("IL PARAMETRO DENOMINATO 'report.builder.records.page.size' PER IL JOB 'REPORT_BUILDER' NON CONFIGURATO");
			return jobDto;
		}
		
		log.info("START JOB 'REPORT_BUILDER'");
		log.info ( CLASSNAME + " " + methodName + " - START" );
		
		log.info("JOB 'REPORT_BUILDER' PARAMETRI DI CONFIGURAZIONE: report.builder.records.page.size='" + reportBuilderSizeStr + "' - report.builder.records.page.size='" + reportBuilderRecordsPageSize + "'");
		
		boolean running = reportManagerBusiness.isRunning();
		log.info("STATO EJB ReportBuilderBusiness: " + running + " ");
		
		if(!running) {
			log.info("START EJB ReportBuilderBusiness");
			reportManagerBusiness.crateReport(NumberUtils.toInt(reportBuilderSizeStr, 1), NumberUtils.toInt(reportBuilderRecordsPageSize, 100));
			log.info("ATTIVATO EJB ReportBuilderBusiness" );
		}else {
			log.info("STATO EJB ReportBuilderBusiness ANCORA IN ESECUZIONE");
		}
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		log.info("STOP JOB 'REPORT_BUILDER'");
		return jobDto;
	}
}
