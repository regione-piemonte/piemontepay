/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule.jobimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import it.csi.epay.epaywsosrv.dto.JobDto;
import it.csi.epay.epaywsosrv.dto.RichiestaDto;
import it.csi.epay.epaywsosrv.enumeration.JobTypeEnum;
import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.persistence.dad.EPaywsoDad;
import it.csi.epay.epaywsosrv.schedule.JobTypes;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

@JobTypes(JobTypeEnum.RICHIESTE_IN_ERRORE)
public class RichiesteInErroreJob extends JobBase implements Job {
	static private final String CLASSNAME = RichiesteInErroreJob.class.getSimpleName();
	static private final String TEMPLATENAME = "richieste-in-errore.ftl";

	@Inject
	private EPaywsoDad dad;

	@Override
	public JobDto execute(JobDto jobDto) throws SchedulerException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "execute", false);

		try {
			lw.start();

			Properties params = jobDto.getParams();
			if (params != null) {
				Date dataUltimaEsecuzioneJob = jobDto.getUltimaEsecuzione();
				Long sogliaInSecondiDataUltimaEsecuzione = Long.parseLong(params.getProperty("soglia.data.ultima.esecuzione")) * 60;
				List<RichiestaDto> list = dad.findAllRichiesteInErrore(dataUltimaEsecuzioneJob, sogliaInSecondiDataUltimaEsecuzione);

				if (list != null && list.size() > 0) {
					Map<String, Object> templateCtx = new HashMap<String, Object>();
					templateCtx.put("elencoRichieste", list);
					sendEmail(TEMPLATENAME, templateCtx, params);
				}
			} else {
				lw.warn("missing scheduler params");
			}

		} catch (Throwable e) {
			lw.error(e);

		} finally {
			lw.stop();
		}

		return jobDto;
	}

}
