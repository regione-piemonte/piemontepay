/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCduDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCdu;


public class EpaypaTCduDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaTCdu> implements EpaypaTCduDao {
	static private final String CLASSNAME = EpaypaTCduDaoImpl.class.getSimpleName();

	@Override
	public List<EpaypaTCdu> findAllByIdRuolo(Integer idRuolo) throws PersistenceException {
		String methodName = "findAllByIdRuolo";
		
		

		List<EpaypaTCdu> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTCdu> query = entityManager.createNamedQuery("EpaypaTCdu.findAllByIdRuolo", EpaypaTCdu.class);
			query.setParameter("idRuolo", idRuolo);
			//
			entityList = query.getResultList();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

}
