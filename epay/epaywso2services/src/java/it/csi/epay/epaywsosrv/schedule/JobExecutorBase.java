/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule;

import it.csi.epay.epaywsosrv.dto.JobDto;
import it.csi.epay.epaywsosrv.enumeration.JobTypeEnum;
import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.persistence.dad.SchedulerDad;
import it.csi.epay.epaywsosrv.schedule.jobimpl.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Date;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

public class JobExecutorBase {
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".scheduler");

	@Inject
	protected SchedulerDad schedulerDad;

	@Inject
	@JobTypes(JobTypeEnum.ACTIVEMQ_CHECK)
	protected Job activeMQCheckJob;

	@Inject
	@JobTypes(JobTypeEnum.RICHIESTE_IN_ERRORE)
	protected Job richiesteInErroreJob;

	@Inject
	@JobTypes(JobTypeEnum.RICHIESTE_SCARTATE)
	protected Job richiesteScartateJob;

	@Inject
	@JobTypes(JobTypeEnum.RICHIESTE_NON_INVIATE)
	protected Job richiesteNonInviateJob;

	// ottiene l'impl del job da eseguire, lo esegue e aggiorna la data di ultima esecuzione per tale job
	protected void executeJob(JobDto jobDto) throws SchedulerException {
		Job jobImpl = getJobImplByType(jobDto.getJobTypeEmum());
		JobDto updatedJobDto = jobImpl.execute(jobDto);
		updatedJobDto.setUltimaEsecuzione(new Date());
		schedulerDad.updateJob(updatedJobDto);
	}

	protected Job getJobImplByType(JobTypeEnum type) {
		Job jobImpl = null;

		switch (type) {
		case ACTIVEMQ_CHECK:
			jobImpl = activeMQCheckJob;
			break;
		case RICHIESTE_IN_ERRORE:
			jobImpl = richiesteInErroreJob;
			break;
		case RICHIESTE_SCARTATE:
			jobImpl = richiesteScartateJob;
			break;
		case RICHIESTE_NON_INVIATE:
			jobImpl = richiesteNonInviateJob;
			break;
		}

		return jobImpl;
	}
}
