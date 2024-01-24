/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.exception.SchedulerException;
import it.csi.epay.epaypaweb.persistence.entity.EPaypaTScheduledJob;

//@formatter:off
public interface EPaypaTScheduledJobDao {

	public EPaypaTScheduledJob findOneJob(Integer id) throws SchedulerException;
	public List<EPaypaTScheduledJob> findAllJobs() throws SchedulerException;
	public EPaypaTScheduledJob findOneActiveJob(String jobType) throws SchedulerException;
	public EPaypaTScheduledJob findNextActiveJob() throws SchedulerException;
	public List<EPaypaTScheduledJob> findAllActiveJobs() throws SchedulerException;

	public void persist(EPaypaTScheduledJob entity) throws SchedulerException;
	public void merge(EPaypaTScheduledJob entity) throws SchedulerException;

}
//@formatter:on
