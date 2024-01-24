/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule;

import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

public interface JobExecutor {

	public void executeScheduledJob(ScheduledJob scheduledJob) throws SchedulerException;
}
