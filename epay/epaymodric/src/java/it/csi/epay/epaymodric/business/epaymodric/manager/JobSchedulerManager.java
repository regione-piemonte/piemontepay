/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

public interface JobSchedulerManager {

	public void obtainLock ( long timestamp ) throws SchedulerException;
	
	public List<ScheduledJob> findAllJobs ();
	
	public List<ScheduledJob> findAllActiveJobs() throws SchedulerException;
	
	public void updateJob ( ScheduledJob scheduledJob );
}
