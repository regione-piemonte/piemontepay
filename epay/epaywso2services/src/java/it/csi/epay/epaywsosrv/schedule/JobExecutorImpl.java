/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import it.csi.epay.epaywsosrv.dto.JobDto;
import it.csi.epay.epaywsosrv.enumeration.JobTypeEnum;
import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

@Stateless
public class JobExecutorImpl extends JobExecutorBase implements JobExecutor {
	static private final String CLASSNAME = JobExecutorImpl.class.getSimpleName();

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void executeScheduledJob(JobDto jobDto) throws SchedulerException {
		String methodName = "executeScheduledJob";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();
			if (lw.isDebugEnabled())
				lw.debug("job id " + jobDto.getId());

			executeJob(jobDto);

		} finally {
			lw.stop();
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void requestExecution(JobTypeEnum jobTypeEnum) throws SchedulerException {
		String methodName = "requestExecution";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();
			if (lw.isDebugEnabled())
				lw.debug("type " + (jobTypeEnum != null ? jobTypeEnum.name() : null));

			JobDto jobDto = schedulerDad.findOneActiveJob(jobTypeEnum);
			if (jobDto != null)
				executeJob(jobDto);
			else if (lw.isDebugEnabled())
				lw.debug("no active job found");

		} finally {
			lw.stop();
		}
	}

}
