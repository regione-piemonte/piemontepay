/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import it.csi.epay.epaywsosrv.exception.CannotAcquireLockException;
import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTSchedulerLockDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTSchedulerLock;
import it.csi.epay.epaywsosrv.util.LogAndWatch;
import it.csi.epay.epaywsosrv.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

public class EPaywsoTSchedulerLockDaoImpl implements EPaywsoTSchedulerLockDao {
	static private final String CLASSNAME = EPaywsoTScheduledJobDaoImpl.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(Util.APPLICATION_CODE + ".persistence");

	@PersistenceContext(unitName = "epaywso-scheduler")
	protected EntityManager entityManager;

	@Override
	public EPaywsoTSchedulerLock getLock() throws SchedulerException {
		String methodName = "getLock";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		try {
			TypedQuery<EPaywsoTSchedulerLock> query = entityManager.createNamedQuery("EPaywsoTSchedulerLock.getLock", EPaywsoTSchedulerLock.class);
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
			lw.stop();
		}
	}

}
