/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBase;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;

public class DaoBaseTest {

	private static final String PERSISTENCE_UNIT_NAME = "epaypa";

	Logger log;
	EntityManager entityManager;
	EntityTransaction transaction;

	public void setup() throws IOException {
		Properties log4jConfig = new Properties();
		log4jConfig.load(DaoBaseTest.class.getResourceAsStream("/resources/log4j.properties"));
		PropertyConfigurator.configure(log4jConfig);

		log = Logger.getRootLogger();

		entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
		transaction = entityManager.getTransaction();
	}

	protected <K, E> void setEntityManager(EpaypaDaoBase<K, E> daoBase) throws SecurityException, IllegalArgumentException, IllegalAccessException {
		boolean found = false;
		Field entityManagerField = null;
		Class<?> clazz = daoBase.getClass();

		while (!found) {
			try {
				entityManagerField = clazz.getDeclaredField("entityManager");
				found = true;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			}
		}

		entityManagerField.setAccessible(true);
		entityManagerField.set(daoBase, entityManager);
	}

	protected <K, E> void setDao(EPaypaDadBase dad, EpaypaDaoBase<K, E> dao) throws IllegalArgumentException, IllegalAccessException {
		Field daoField = null;
		Class<?> clazz = dad.getClass();

		for (Field field : clazz.getDeclaredFields()) {
			if (field.getType().isAssignableFrom(dao.getClass())) {
				daoField = field;
				break;
			}
		}

		if (daoField != null) {
			daoField.setAccessible(true);
			daoField.set(dad, dao);
		}

	}

}
