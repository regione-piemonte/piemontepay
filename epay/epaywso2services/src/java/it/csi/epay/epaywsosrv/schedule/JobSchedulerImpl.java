/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule;

import static it.csi.epay.epaywsosrv.util.Util.MONITORING_CONFIGFILENAME;

import java.io.IOException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import it.csi.epay.epaywsosrv.dto.JobDto;
import it.csi.epay.epaywsosrv.exception.CannotAcquireLockException;
import it.csi.epay.epaywsosrv.persistence.dad.SchedulerDad;
import it.csi.epay.epaywsosrv.schedule.plan.SchedulePlanConfig;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class JobSchedulerImpl extends JobSchedulerBase implements JobScheduler {
	static private final String CLASSNAME = JobSchedulerImpl.class.getSimpleName();

	@EJB
	private JobExecutor jobExecutor;

	@Inject
	private SchedulerDad schedulerDad;

	@Resource
	private TimerService timerService;

	@Resource
	private UserTransaction tx;

	@Timeout
	public void timeout(Timer timer) {
		String methodName = "timeout";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();

			tx.begin();
			schedulerDad.obtainLock();

			List<JobDto> jobDtoList = schedulerDad.findAllActiveJobs();
			for (JobDto jobDto : jobDtoList) {
				if (lw.isDebugEnabled())
					lw.debug("active job found with id " + jobDto.getId() + " and type " + jobDto.getJobTypeEmum());

				SchedulePlanConfig schedulePlan = buildSchedulePlanConfig(jobDto.getScheduleExpression());
				if (isInRange(new Date(), schedulePlan))
					jobExecutor.executeScheduledJob(jobDto);
				else
					lw.debug("out of schedule plan");
			}

			tx.commit();

		} catch (CannotAcquireLockException e) {
			if (lw.isDebugEnabled())
				lw.debug("cannot acquire lock");

			try {
				tx.rollback();
			} catch (Exception e1) {
				lw.error(e1);
			}

		} catch (Throwable e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				lw.error(e1);
			}
			lw.error(e);

		} finally {
			lw.stop();
		}
	}

	@Override
	@PostConstruct
	public void initialize() {
		String methodName = "initialize";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();

			try {
				cancelAllTimers();

				Properties props = new Properties();
				props.loadFromXML(JobSchedulerImpl.class.getResourceAsStream("/META-INF/" + MONITORING_CONFIGFILENAME));
				String cronExpression = props.getProperty("timer.cron.expression");
				lw.info("cron expression: " + cronExpression);
				timerService.createCalendarTimer(buildScheduleExpression(cronExpression), new TimerConfig(null, false));

			} catch (InvalidPropertiesFormatException e) {
				lw.error("error reading " + MONITORING_CONFIGFILENAME, e);

			} catch (IOException e) {
				lw.error("error reading " + MONITORING_CONFIGFILENAME, e);

			} catch (IllegalArgumentException e) {
				lw.error("invalid schedule expression", e);
			}
		} finally {
			lw.stop();
		}
	}

	@Override
	@PreDestroy
	public void stop() {
		String methodName = "stop";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();

			cancelAllTimers();

		} finally {
			lw.stop();
		}
	}

	private void cancelAllTimers() {
		for (Timer timer : timerService.getTimers()) {
			timer.cancel();
		}
	}

}
