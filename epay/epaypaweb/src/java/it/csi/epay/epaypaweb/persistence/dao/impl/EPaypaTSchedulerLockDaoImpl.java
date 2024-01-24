/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.exception.CannotAcquireLockException;
import it.csi.epay.epaypaweb.exception.SchedulerException;
import it.csi.epay.epaypaweb.persistence.dao.interf.EPaypaTSchedulerLockDao;
import it.csi.epay.epaypaweb.persistence.entity.EPaypaTSchedulerLock;

import it.csi.epay.epaypaweb.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

public class EPaypaTSchedulerLockDaoImpl implements EPaypaTSchedulerLockDao {
	static private final String CLASSNAME = EPaypaTScheduledJobDaoImpl.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(Util.APPLICATION_CODE + ".persistence");

	@PersistenceContext(unitName = "epaypa-scheduler")
	protected EntityManager entityManager;

	@Override
	public EPaypaTSchedulerLock getLock() throws SchedulerException {
		String methodName = "getLock";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EPaypaTSchedulerLock> query = entityManager.createNamedQuery("EPaypaTSchedulerLock.getLock", EPaypaTSchedulerLock.class);
			query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
			query.setHint("javax.persistence.lock.timeout", 0);
			return query.setMaxResults(1).getSingleResult();

		} catch (PessimisticLockException e) {
			throw new CannotAcquireLockException();

		} catch (LockTimeoutException e) {
			throw new CannotAcquireLockException();

		} catch (NoResultException e) {
			return null;

		} catch (Exception e) {
			throw new SchedulerException(e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

}
