/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.util.LogAndWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

public class EPaywsoDaoBaseImpl<K, E> implements EPaywsoDaoBase<K, E> {
	static private final String CLASSNAME = EPaywsoDaoBaseImpl.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".dao");

	@PersistenceContext(unitName = "epaywso")
	protected EntityManager entityManager;

	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public EPaywsoDaoBaseImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	protected void handlePersistenceException(Throwable e) throws PersistenceException {
		String methodName = "handlePersistenceException";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();

			PersistenceException p;

			if (e instanceof PersistenceException) {
				p = (PersistenceException) e;

			} else {
				// eccezione imprevista
				p = new PersistenceException(IssueEnum.GENERIC_SYSTEM_ERROR.getCod(), e.getMessage(), e);
			}

			lw.error("errorCod:" + p.getErrorCod() + " errorDes:" + p.getErrorDes(), p.getCause());
			throw p;

		} finally {
			lw.stop();
		}
	}

	protected Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	@Override
	public E findOne(K pk) throws PersistenceException {
		String methodName = "findOne";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		E result = null;

		try {
			lw.start();

			result = entityManager.find(entityClass, pk);

		} catch (Throwable e) {
			lw.error("Errore imprevisto", e);
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() throws PersistenceException {
		String methodName = "findAll";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		List<E> resultList = null;

		try {
			lw.start();

			Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e ", entityClass);
			resultList = q.getResultList();

		} catch (Throwable e) {
			lw.error("Errore imprevisto", e);
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return resultList;
	}

	@Override
	public void persist(E entity) throws PersistenceException {
		String methodName = "persist";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		try {
			lw.start();

			entityManager.persist(entity);
			entityManager.flush();

		} catch (Throwable e) {
			lw.error("Errore imprevisto", e);
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}
	}

	@Override
	public void merge(E entity) throws PersistenceException {
		String methodName = "merge";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		try {
			lw.start();

			entityManager.merge(entity);

		} catch (Throwable e) {
			lw.error("Errore imprevisto", e);
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}
	}

	@Override
	public void remove(E entity) throws PersistenceException {
		String methodName = "remove";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		try {
			lw.start();

			entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
			entityManager.flush();

		} catch (Throwable e) {
			lw.error("Errore imprevisto", e);
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}
	}

}
