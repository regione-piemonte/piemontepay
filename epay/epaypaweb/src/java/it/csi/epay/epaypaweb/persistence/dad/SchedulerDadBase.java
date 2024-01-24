/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.persistence.entity.EPaypaTScheduledJob;
import it.csi.epay.epaypaweb.util.LogAndWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class SchedulerDadBase {
	static private final String CLASSNAME = SchedulerDadBase.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".persistence");

	protected JobDto toJobDto(EPaypaTScheduledJob entity) {
		JobDto dto = null;

		if (entity != null) {
			dto = new JobDto(entity.getIdScheduledJob());
			dto.setActive( entity.getActive () );
			dto.setScheduleExpression(entity.getScheduleExpression());
			dto.setJobTypeEmum(JobTypeEnum.valueOf(entity.getJobType()));
			dto.setUltimaEsecuzione(entity.getUltimaEsecuzione());
			//
			if (entity.getParams() != null) {
				Properties params = new Properties();
				try {
					params.loadFromXML(new ByteArrayInputStream(entity.getParams().getBytes()));

				} catch (Exception e) {
					log.error(LogAndWatch.buildLogpx(CLASSNAME, "toScheduledJobDto") + "Cannot parse params", e);
				}
				dto.setParams(params);
			}
			//
			if (entity.getStatus() != null) {
				Properties status = new Properties();
				try {
					status.loadFromXML(new ByteArrayInputStream(entity.getStatus().getBytes()));

				} catch (Exception e) {
					log.error(LogAndWatch.buildLogpx(CLASSNAME, "toScheduledJobDto") + "Cannot parse status", e);
				}
				dto.setStatus(status);
			}
		}

		return dto;
	}

	protected List<JobDto> toScheduledJobDtoList(List<EPaypaTScheduledJob> scheduledJobList) {
		List<JobDto> dtoList = null;

		if (scheduledJobList != null) {
			dtoList = new ArrayList<JobDto>();
			for (EPaypaTScheduledJob entity : scheduledJobList) {
				dtoList.add(toJobDto(entity));
			}
		}

		return dtoList;
	}
}
