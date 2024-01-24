/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.exception.CannotAcquireLockException;
import it.csi.epay.epaypaweb.exception.SchedulerException;
import it.csi.epay.epaypaweb.persistence.dao.interf.EPaypaTScheduledJobDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EPaypaTSchedulerLockDao;
import it.csi.epay.epaypaweb.persistence.entity.EPaypaTScheduledJob;
import it.csi.epay.epaypaweb.util.LogAndWatch;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class SchedulerDadImpl extends SchedulerDadBase implements SchedulerDad {
	static private final String CLASSNAME = SchedulerDadImpl.class.getSimpleName();

	@Inject
	private EPaypaTScheduledJobDao ePayTScheduledJobDao;

	@Inject
	private EPaypaTSchedulerLockDao ePayTSchedulerLockDao;

	@Override
	public void obtainLock() throws CannotAcquireLockException, SchedulerException {
		ePayTSchedulerLockDao.getLock();
	}

	@Override
	public List<JobDto> findAllJobs() throws SchedulerException {
		List<EPaypaTScheduledJob> entityList = ePayTScheduledJobDao.findAllJobs();
		List<JobDto> dtoList = toScheduledJobDtoList(entityList);
		return dtoList;
	}

	@Override
	public JobDto findOneActiveJob(JobTypeEnum jobTypeEnum) throws SchedulerException {
		JobDto dto;
		if (jobTypeEnum != null) {
			EPaypaTScheduledJob entity = ePayTScheduledJobDao.findOneActiveJob(jobTypeEnum.name());
			dto = toJobDto(entity);
		} else {
			dto = null;
		}
		return dto;
	}

	@Override
	public JobDto findNextActiveJob() throws SchedulerException {
		EPaypaTScheduledJob entity = ePayTScheduledJobDao.findNextActiveJob();
		JobDto dto = toJobDto(entity);
		return dto;
	}

	@Override
	public List<JobDto> findAllActiveJobs() throws SchedulerException {
		List<EPaypaTScheduledJob> entityList = ePayTScheduledJobDao.findAllActiveJobs();
		List<JobDto> dtoList = toScheduledJobDtoList(entityList);
		return dtoList;
	}

	@Override
	public void updateJob(JobDto jobDto) throws SchedulerException {
		EPaypaTScheduledJob entity = ePayTScheduledJobDao.findOneJob(jobDto.getId());
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
