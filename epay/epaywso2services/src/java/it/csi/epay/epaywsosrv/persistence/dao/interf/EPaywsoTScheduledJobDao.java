/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTScheduledJob;

//@formatter:off
public interface EPaywsoTScheduledJobDao {

	public EPaywsoTScheduledJob findOneJob(Integer id) throws SchedulerException;
	public List<EPaywsoTScheduledJob> findAllJobs() throws SchedulerException;
	public EPaywsoTScheduledJob findOneActiveJob(String jobType) throws SchedulerException;
	public EPaywsoTScheduledJob findNextActiveJob() throws SchedulerException;
	public List<EPaywsoTScheduledJob> findAllActiveJobs() throws SchedulerException;

	public void persist(EPaywsoTScheduledJob entity) throws SchedulerException;
	public void merge(EPaywsoTScheduledJob entity) throws SchedulerException;

}
//@formatter:on
