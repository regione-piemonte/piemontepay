/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

@Service
public class JobExecutorImpl extends JobExecutorBase implements JobExecutor {
	
	private final Logger logger = LogManager.getLogger ( this.getClass () );

	@Override
	@Transactional( propagation = Propagation.REQUIRES_NEW )
	public void executeScheduledJob(ScheduledJob scheduledJob) throws SchedulerException {
		logger.info("JobExecutorImpl.executeScheduledJob INIZIO");
		executeJob(scheduledJob);
		logger.info("JobExecutorImpl.executeScheduledJob FINE");
	}

}
