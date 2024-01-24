/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.exception.SchedulerException;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class JobExecutorImpl extends JobExecutorBase implements JobExecutor {
	static private final String CLASSNAME = JobExecutorImpl.class.getSimpleName();

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void executeScheduledJob(JobDto jobDto) throws SchedulerException {
		String methodName = "executeScheduledJob";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			log.debug ( "job id " + jobDto.getId () );
			executeJob ( jobDto );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void requestExecution(JobTypeEnum jobTypeEnum) throws SchedulerException {
		String methodName = "requestExecution";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			log.debug("type " + (jobTypeEnum != null ? jobTypeEnum.name() : null));

			JobDto jobDto = schedulerDad.findOneActiveJob(jobTypeEnum);
			if (jobDto != null) {
				executeJob ( jobDto );
			}
			else {
				log.debug ( "no active job found" );
			}

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

}
