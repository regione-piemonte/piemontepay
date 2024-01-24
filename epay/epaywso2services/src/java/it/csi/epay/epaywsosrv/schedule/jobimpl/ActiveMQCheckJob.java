/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule.jobimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.inject.Inject;

import it.csi.epay.epaywsosrv.business.MonitoringBusiness;
import it.csi.epay.epaywsosrv.dto.JobDto;
import it.csi.epay.epaywsosrv.dto.MonitorResultDto;
import it.csi.epay.epaywsosrv.dto.QueueStatusDto;
import it.csi.epay.epaywsosrv.dto.ReportQueueStatusDto;
import it.csi.epay.epaywsosrv.enumeration.AlertLevelEnum;
import it.csi.epay.epaywsosrv.enumeration.JobTypeEnum;
import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.schedule.JobExecutor;
import it.csi.epay.epaywsosrv.schedule.JobTypes;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

@JobTypes(JobTypeEnum.ACTIVEMQ_CHECK)
public class ActiveMQCheckJob extends JobBase implements Job {
	static private final String CLASSNAME = ActiveMQCheckJob.class.getSimpleName();
	static private final String TEMPLATENAME = "activemq-queue-alert.ftl";

	@EJB
	private JobExecutor jobExecutor;

	@Inject
	private MonitoringBusiness monitoringBusiness;

	//@formatter:off
	@Override
	public JobDto execute(JobDto jobDto) throws SchedulerException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "execute", false);

		try {
			lw.start();

			boolean sendMail = false;
			// boolean activateErrorJob = false;

			MonitorResultDto status = monitoringBusiness.getStatus();

			Properties currStatus = new Properties();
			Properties params = jobDto.getParams();
			if (params != null) {
				// ottiene lo stato precedente dal database
				Properties prevStatus = jobDto.getStatus();

				boolean currActiveMQAlive = true;
				boolean prevActiveMQAlive = (prevStatus != null) ? Boolean.parseBoolean(prevStatus.getProperty("activeMQ.alive")) : true;

				Map<String, Object> templateCtx = new HashMap<String, Object>();
				List<QueueStatusDto> queueStatusDtoList = status.getActiveMQInfo();
				if (queueStatusDtoList != null) {
					jobDto.setAlertLevelEnum(AlertLevelEnum.NONE);

					List<ReportQueueStatusDto> reportQueues = new ArrayList<ReportQueueStatusDto>();
					for (QueueStatusDto queueStatusDto : queueStatusDtoList) {

						String monitoringEnabled = params.getProperty("queue.".concat(queueStatusDto.getName()).concat(".monitoring.enabled"));
						if (monitoringEnabled != null && Boolean.parseBoolean(monitoringEnabled)) {
							ReportQueueStatusDto reportQueue = new ReportQueueStatusDto();

							// lettura dello stato precedente
							boolean prevMPDisabled;
							boolean prevQueueSizeAlert;
							if (prevStatus != null) {
								prevMPDisabled     = Boolean.parseBoolean(prevStatus.getProperty("queue.".concat(queueStatusDto.getName()).concat(".mpDisabled")));
								prevQueueSizeAlert = Boolean.parseBoolean(prevStatus.getProperty("queue.".concat(queueStatusDto.getName()).concat(".queueSizeAlert")));

							} else {
								prevMPDisabled     = false;
								prevQueueSizeAlert = false;
							}

							// lettura dei parametri di soglia
							int queueSizeAlertLevel = Integer.MAX_VALUE;
							try {
								queueSizeAlertLevel = Integer.parseInt(params.getProperty("queue.".concat(queueStatusDto.getName()).concat(".alert.threshold")));

							} catch (NumberFormatException e) {
								if (lw.isDebugEnabled())
									lw.debug("alert level not set");
							}

							// determina se la soglia di allerta risulta superata
							boolean queueSizeAlert = queueStatusDto.getQueueSize() > queueSizeAlertLevel;

							// determina se il processor risulta disabilitato
							boolean mpDisabled = queueStatusDto.getConsumerCount() == 0;

							reportQueue.setOverThresholdEvent ( queueSizeAlert && !prevQueueSizeAlert);
							reportQueue.setUnderThresholdEvent(!queueSizeAlert &&  prevQueueSizeAlert);
							reportQueue.setDisabledEvent( mpDisabled && !prevMPDisabled);
							reportQueue.setEnabledEvent (!mpDisabled &&  prevMPDisabled);
							reportQueue.setSizeThreshold(queueSizeAlertLevel);

							// memorizza lo stato corrente
							currStatus.setProperty("queue.".concat(queueStatusDto.getName()).concat(".mpDisabled"    ), Boolean.toString(mpDisabled));
							currStatus.setProperty("queue.".concat(queueStatusDto.getName()).concat(".queueSizeAlert"), Boolean.toString(queueSizeAlert));

							if (   reportQueue.isDisabledEvent()
								|| reportQueue.isEnabledEvent()
								|| reportQueue.isOverThresholdEvent()
								|| reportQueue.isUnderThresholdEvent())
							{
								reportQueue.setStatus(queueStatusDto);
								reportQueues.add(reportQueue);
								sendMail = true;
							}
							if (reportQueue.isDisabledEvent()) {
								jobDto.setAlertLevelEnum(AlertLevelEnum.ERROR);
								// activateErrorJob = true;
							}
							if (queueSizeAlert && jobDto.getAlertLevelEnum() != AlertLevelEnum.ERROR) {
								jobDto.setAlertLevelEnum(AlertLevelEnum.WARNING);
							}
						}
					}

					if (reportQueues.size() > 0) {
						templateCtx.put("queues", reportQueues);
					}
				} else {
					jobDto.setAlertLevelEnum(AlertLevelEnum.ERROR);
					currActiveMQAlive = false;
				}

				templateCtx.put("activateAMQEvent"  , !prevActiveMQAlive &&  currActiveMQAlive);
				templateCtx.put("deactivateAMQEvent",  prevActiveMQAlive && !currActiveMQAlive);

				currStatus.setProperty("activeMQ.alive", Boolean.toString(currActiveMQAlive));

				if ((!prevActiveMQAlive && currActiveMQAlive) || (prevActiveMQAlive && !currActiveMQAlive)) {
					sendMail = true;
				}

				if (sendMail) {
					sendEmail(TEMPLATENAME, templateCtx, params);
				}
				/*
				if (activateErrorJob) {
					try {
						jobExecutor.requestExecution(JobTypeEnum.RICHIESTE_IN_ERRORE);

					} catch (SchedulerException e) {
						log.error(lw.getLogpx(), e);
					}
				}
				 */
			} else {
				lw.warn("missing scheduler params");
			}
			jobDto.setStatus(currStatus);

		} finally {
			lw.stop();
		}

		return jobDto;
	}

}
