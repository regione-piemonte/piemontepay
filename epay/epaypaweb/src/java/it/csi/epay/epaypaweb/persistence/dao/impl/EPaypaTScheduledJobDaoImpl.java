/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.exception.SchedulerException;
import it.csi.epay.epaypaweb.persistence.dao.interf.EPaypaTScheduledJobDao;
import it.csi.epay.epaypaweb.persistence.entity.EPaypaTScheduledJob;

import it.csi.epay.epaypaweb.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EPaypaTScheduledJobDaoImpl implements EPaypaTScheduledJobDao {
	static private final String CLASSNAME = EPaypaTScheduledJobDaoImpl.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(Util.APPLICATION_CODE + ".persistence");

	@PersistenceContext(unitName = "epaypa-scheduler")
	protected EntityManager entityManager;

	@Override
	public EPaypaTScheduledJob findOneJob(Integer id) throws SchedulerException {
		String methodName = "findOneJob";
		
		

		EPaypaTScheduledJob entity = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			entity = entityManager.find(EPaypaTScheduledJob.class, id);

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entity;
	}

	@Override
	public List<EPaypaTScheduledJob> findAllJobs() throws SchedulerException {
		String methodName = "findAllJobs";
		

		List<EPaypaTScheduledJob> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EPaypaTScheduledJob> query = entityManager.createNamedQuery("EPaypaTScheduledJob.findAllJobs", EPaypaTScheduledJob.class);
			//
			entityList = query.getResultList();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

	@Override
	public EPaypaTScheduledJob findOneActiveJob(String jobType) throws SchedulerException {
		String methodName = "findOneActiveJob";
		
		

		EPaypaTScheduledJob entity = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EPaypaTScheduledJob> query = entityManager.createNamedQuery("EPaypaTScheduledJob.findOneActiveJob", EPaypaTScheduledJob.class);
			query.setParameter("jobType", jobType);
			//
			entity = query.setMaxResults(1).getSingleResult();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entity;
	}

	@Override
	public EPaypaTScheduledJob findNextActiveJob() throws SchedulerException {
		String methodName = "findNextActiveJob";
		

		EPaypaTScheduledJob entity = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EPaypaTScheduledJob> query = entityManager.createNamedQuery("EPaypaTScheduledJob.findNextActiveJob", EPaypaTScheduledJob.class);
			query.setMaxResults(1);
			//
			entity = query.getSingleResult();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entity;
	}

	@Override
	public List<EPaypaTScheduledJob> findAllActiveJobs() throws SchedulerException {
		String methodName = "findAllActiveJobs";
		

		List<EPaypaTScheduledJob> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EPaypaTScheduledJob> query = entityManager.createNamedQuery("EPaypaTScheduledJob.findNextActiveJob", EPaypaTScheduledJob.class);
			//
			entityList = query.getResultList();

		} catch (NoResultException e) {
			// ammesso trovare niente

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

	@Override
	public void persist(EPaypaTScheduledJob entity) throws SchedulerException {
		String methodName = "persist";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			entityManager.persist(entity);

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	public void merge(EPaypaTScheduledJob entity) throws SchedulerException {
		String methodName = "merge";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			entityManager.merge(entity);

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

}
