/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule;

import javax.ejb.Local;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.exception.SchedulerException;

@Local
public interface JobExecutor {

	public void executeScheduledJob(JobDto jobDto) throws SchedulerException;

	public void requestExecution(JobTypeEnum jobTypeEnum) throws SchedulerException;

}
