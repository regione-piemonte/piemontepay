/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao;

import it.csi.epay.epaypaweb.exception.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class EpaypaDaoBaseImpl<K, E> implements EpaypaDaoBase<K, E> {
	static private final String CLASSNAME = EpaypaDaoBaseImpl.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".dao");

	@PersistenceContext(unitName = "epaypa")
	protected EntityManager entityManager;

	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public EpaypaDaoBaseImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	protected Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	@Override
	public E findOne(K pk) throws PersistenceFindOneException {
		String methodName = "findOne";
		

		E result = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			result = entityManager.find(entityClass, pk);
			if (result == null) {
				log.warn("nessun risultato trovato per primary key:".concat(pk.toString()));
			}

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceFindOneException(e.getMessage());

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() throws PersistenceFindAllException {
		String methodName = "findAll";
		

		List<E> resultList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e ", entityClass);
			resultList = q.getResultList();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceFindAllException(e.getMessage());

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resultList;
	}

	@Override
	public void persist(E entity) throws PersistencePersistException {
		String methodName = "persist";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			entityManager.persist(entity);
			entityManager.flush();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistencePersistException(e.getMessage());

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	public void merge(E entity) throws PersistenceMergeException {
		String methodName = "merge";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			entityManager.merge(entity);

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceMergeException(e.getMessage());

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	public void remove(E entity) throws PersistenceRemoveException {
		String methodName = "remove";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
			entityManager.flush();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceRemoveException(e.getMessage());

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

}
