/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule;

import it.csi.epay.epaypaweb.dto.JobDto;
import it.csi.epay.epaypaweb.exception.CannotAcquireLockException;
import it.csi.epay.epaypaweb.persistence.dad.SchedulerDad;
import it.csi.epay.epaypaweb.schedule.plan.SchedulePlanConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import static it.csi.epay.epaypaweb.util.Util.JOB_CONFIGFILENAME;


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
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			tx.begin();
			schedulerDad.obtainLock();

			List<JobDto> jobDtoList = schedulerDad.findAllActiveJobs();
			for (JobDto jobDto : jobDtoList) {
					log.debug("active job found with id " + jobDto.getId() + " and type " + jobDto.getJobTypeEmum());

				SchedulePlanConfig schedulePlan = buildSchedulePlanConfig(jobDto.getScheduleExpression());
				if (isInRange(new Date(), schedulePlan))
					jobExecutor.executeScheduledJob(jobDto);
				else
					log.debug("out of schedule plan");
			}

			tx.commit();

		} catch (CannotAcquireLockException e) {
				log.debug("cannot acquire lock");

			try {
				tx.rollback();
			} catch (Exception e1) {
				log.error(e1);
			}

		} catch (Throwable e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				log.error(e1);
			}
			log.error(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	@PostConstruct
	public void initialize() {
		String methodName = "initialize";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			try {
				cancelAllTimers();

				Properties props = new Properties();
				props.loadFromXML(JobSchedulerImpl.class.getResourceAsStream("/META-INF/" + JOB_CONFIGFILENAME));
				String cronExpression = props.getProperty("timer.cron.expression");
				log.info("cron expression: " + cronExpression);
				timerService.createCalendarTimer(buildScheduleExpression(cronExpression), new TimerConfig(null, false));

			} catch (InvalidPropertiesFormatException e) {
				log.error("error reading " + JOB_CONFIGFILENAME, e);

			} catch (IOException e) {
				log.error("error reading " + JOB_CONFIGFILENAME, e);

			} catch (IllegalArgumentException e) {
				log.error("invalid schedule expression", e);
			}
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	@PreDestroy
	public void stop() {
		String methodName = "stop";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			cancelAllTimers();

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	private void cancelAllTimers() {
		for (Timer timer : timerService.getTimers()) {
			timer.cancel();
		}
	}

}
