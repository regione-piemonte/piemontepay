/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import it.csi.epay.epaywsosrv.dto.JobDto;
import it.csi.epay.epaywsosrv.enumeration.JobTypeEnum;
import it.csi.epay.epaywsosrv.exception.CannotAcquireLockException;
import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTScheduledJobDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTSchedulerLockDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTScheduledJob;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class SchedulerDadImpl extends SchedulerDadBase implements SchedulerDad {
	static private final String CLASSNAME = SchedulerDadImpl.class.getSimpleName();

	@Inject
	private EPaywsoTScheduledJobDao ePayTScheduledJobDao;

	@Inject
	private EPaywsoTSchedulerLockDao ePayTSchedulerLockDao;

	@Override
	public void obtainLock() throws CannotAcquireLockException, SchedulerException {
		ePayTSchedulerLockDao.getLock();
	}

	@Override
	public List<JobDto> findAllJobs() throws SchedulerException {
		List<EPaywsoTScheduledJob> entityList = ePayTScheduledJobDao.findAllJobs();
		List<JobDto> dtoList = toScheduledJobDtoList(entityList);
		return dtoList;
	}

	@Override
	public JobDto findOneActiveJob(JobTypeEnum jobTypeEnum) throws SchedulerException {
		JobDto dto;
		if (jobTypeEnum != null) {
			EPaywsoTScheduledJob entity = ePayTScheduledJobDao.findOneActiveJob(jobTypeEnum.name());
			dto = toJobDto(entity);
		} else {
			dto = null;
		}
		return dto;
	}

	@Override
	public JobDto findNextActiveJob() throws SchedulerException {
		EPaywsoTScheduledJob entity = ePayTScheduledJobDao.findNextActiveJob();
		JobDto dto = toJobDto(entity);
		return dto;
	}

	@Override
	public List<JobDto> findAllActiveJobs() throws SchedulerException {
		List<EPaywsoTScheduledJob> entityList = ePayTScheduledJobDao.findAllActiveJobs();
		List<JobDto> dtoList = toScheduledJobDtoList(entityList);
		return dtoList;
	}

	@Override
	public void updateJob(JobDto jobDto) throws SchedulerException {
		EPaywsoTScheduledJob entity = ePayTScheduledJobDao.findOneJob(jobDto.getId());
		entity.setUltimaEsecuzione(new Timestamp(jobDto.getUltimaEsecuzione().getTime()));

		if (jobDto.getStatus() != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				jobDto.getStatus().storeToXML(baos, null);
				entity.setStatus(baos.toString());
				baos.close();

			} catch (IOException e) {
				log.error(LogAndWatch.buildLogpx(CLASSNAME, "updateJob") + "Error in status serialization");
			}
		}

		ePayTScheduledJobDao.persist(entity);
	}

}
