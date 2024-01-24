/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTScheduledJobDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTScheduledJob;
import it.csi.epay.epaywsosrv.util.LogAndWatch;
import it.csi.epay.epaywsosrv.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EPaywsoTScheduledJobDaoImpl implements EPaywsoTScheduledJobDao {
	static private final String CLASSNAME = EPaywsoTScheduledJobDaoImpl.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(Util.APPLICATION_CODE + ".persistence");

	@PersistenceContext(unitName = "epaywso-scheduler")
	protected EntityManager entityManager;

	@Override
	public EPaywsoTScheduledJob findOneJob(Integer id) throws SchedulerException {
		String methodName = "findOneJob";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);

		EPaywsoTScheduledJob entity = null;

		try {
			lw.start();

			entity = entityManager.find(EPaywsoTScheduledJob.class, id);

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

	@Override
	public List<EPaywsoTScheduledJob> findAllJobs() throws SchedulerException {
		String methodName = "findAllJobs";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		List<EPaywsoTScheduledJob> entityList = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTScheduledJob> query = entityManager.createNamedQuery("EPaywsoTScheduledJob.findAllJobs", EPaywsoTScheduledJob.class);
			//
			entityList = query.getResultList();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

	@Override
	public EPaywsoTScheduledJob findOneActiveJob(String jobType) throws SchedulerException {
		String methodName = "findOneActiveJob";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("jobType", jobType);

		EPaywsoTScheduledJob entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTScheduledJob> query = entityManager.createNamedQuery("EPaywsoTScheduledJob.findOneActiveJob", EPaywsoTScheduledJob.class);
			query.setParameter("jobType", jobType);
			//
			entity = query.setMaxResults(1).getSingleResult();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

	@Override
	public EPaywsoTScheduledJob findNextActiveJob() throws SchedulerException {
		String methodName = "findNextActiveJob";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		EPaywsoTScheduledJob entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTScheduledJob> query = entityManager.createNamedQuery("EPaywsoTScheduledJob.findNextActiveJob", EPaywsoTScheduledJob.class);
			query.setMaxResults(1);
			//
			entity = query.getSingleResult();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

	@Override
	public List<EPaywsoTScheduledJob> findAllActiveJobs() throws SchedulerException {
		String methodName = "findAllActiveJobs";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		List<EPaywsoTScheduledJob> entityList = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTScheduledJob> query = entityManager.createNamedQuery("EPaywsoTScheduledJob.findNextActiveJob", EPaywsoTScheduledJob.class);
			//
			entityList = query.getResultList();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

	@Override
	public void persist(EPaywsoTScheduledJob entity) throws SchedulerException {
		String methodName = "persist";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		try {
			lw.start();

			entityManager.persist(entity);

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			lw.stop();
		}
	}

	@Override
	public void merge(EPaywsoTScheduledJob entity) throws SchedulerException {
		String methodName = "merge";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		try {
			lw.start();

			entityManager.merge(entity);

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			lw.stop();
		}
	}

}
