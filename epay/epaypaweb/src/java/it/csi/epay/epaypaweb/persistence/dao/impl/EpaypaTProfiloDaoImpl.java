/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTProfiloDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTProfilo;


public class EpaypaTProfiloDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaTProfilo> implements EpaypaTProfiloDao {
	static private final String CLASSNAME = EpaypaTProfiloDaoImpl.class.getSimpleName();

	@Override
	public List<EpaypaTProfilo> findAllByIdUtenteAndIdEnte(Long idUtente, Integer idEnte) throws PersistenceException {
		String methodName = "findAllByIdUtenteAndIdEnte";
		
		
		

		List<EpaypaTProfilo> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTProfilo> query = entityManager.createNamedQuery("EpaypaTProfilo.findAllByIdUtenteAndIdEnte", EpaypaTProfilo.class);
			query.setParameter("idUtente", idUtente);
			query.setParameter("idEnte", idEnte);
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
