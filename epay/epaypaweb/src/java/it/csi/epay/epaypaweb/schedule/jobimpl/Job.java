/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule.jobimpl;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.exception.SchedulerException;

public interface Job {

	public JobDto execute(JobDto jobDto) throws SchedulerException;

}
