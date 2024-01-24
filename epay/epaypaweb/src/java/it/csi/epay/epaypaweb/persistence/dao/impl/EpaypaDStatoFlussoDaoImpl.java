/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.enumeration.DirezioneEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDStatoFlussoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDStatoFlusso;


public class EpaypaDStatoFlussoDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDStatoFlusso> implements EpaypaDStatoFlussoDao {
	static private final String CLASSNAME = EpaypaDaoBaseImpl.class.getSimpleName();

	@Override
	public List<EpaypaDStatoFlusso> findAllByDirezione(DirezioneEnum direzione) throws PersistenceException {
		String methodName = "findAllByDirezione";
		
		

		List<EpaypaDStatoFlusso> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaDStatoFlusso> query = entityManager.createNamedQuery("EpaypaDStatoFlusso.findAllByDirezione", EpaypaDStatoFlusso.class);
			query.setParameter("direzione", direzione.getId());
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
