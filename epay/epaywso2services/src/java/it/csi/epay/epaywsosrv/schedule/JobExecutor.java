/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule;

import javax.ejb.Local;

import it.csi.epay.epaywsosrv.dto.JobDto;
import it.csi.epay.epaywsosrv.enumeration.JobTypeEnum;
import it.csi.epay.epaywsosrv.exception.SchedulerException;

@Local
public interface JobExecutor {

	public void executeScheduledJob(JobDto jobDto) throws SchedulerException;

	public void requestExecution(JobTypeEnum jobTypeEnum) throws SchedulerException;

}
