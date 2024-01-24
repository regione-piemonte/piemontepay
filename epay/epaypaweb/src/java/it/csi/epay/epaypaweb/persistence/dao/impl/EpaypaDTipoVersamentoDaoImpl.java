/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoVersamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoVersamento;


public class EpaypaDTipoVersamentoDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDTipoVersamento> implements EpaypaDTipoVersamentoDao {
	static private final String CLASSNAME = EpaypaDTipoVersamentoDaoImpl.class.getSimpleName();

	@Override
	public EpaypaDTipoVersamento findOneByCod(String cod) throws PersistenceException {
		String methodName = "findOneByCod";
		
		

		EpaypaDTipoVersamento entity = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaDTipoVersamento> query = entityManager.createNamedQuery("EpaypaDTipoVersamento.findOneByCodTipoVersamento", EpaypaDTipoVersamento.class);
			query.setParameter("codTipoVersamento", cod);
			//
			List<EpaypaDTipoVersamento> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codTipoVersamento:".concat(cod));
				break;
			case 1:
				entity = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("tipo versamento", "codTipoVersamento", cod);
			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entity;
	}

}
