/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad;

import java.util.List;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.exception.CannotAcquireLockException;
import it.csi.epay.epaypaweb.exception.SchedulerException;

//@formatter:off
public interface SchedulerDad {

	public void obtainLock() throws CannotAcquireLockException, SchedulerException;

	public List<JobDto> findAllJobs() throws SchedulerException;
	public JobDto findOneActiveJob(JobTypeEnum jobTypeEnum) throws SchedulerException;
	public JobDto findNextActiveJob() throws SchedulerException;
	public List<JobDto> findAllActiveJobs() throws SchedulerException;

	public void updateJob(JobDto jobDto) throws SchedulerException;

}
//@formatter:on
