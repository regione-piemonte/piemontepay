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
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoCampoFiltroDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoCampoFiltro;


public class EpaypaDTipoCampoFiltroDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDTipoCampoFiltro> implements EpaypaDTipoCampoFiltroDao {

	static private final String CLASSNAME = EpaypaDTipoCampoFiltroDaoImpl.class.getSimpleName();
	
	@Override
	public EpaypaDTipoCampoFiltro findOneByCodice(String cod) throws PersistenceException {
		
		String methodName = "findOneByCodice";
		
		
		
		EpaypaDTipoCampoFiltro epaypaDTipoCampoFiltro = null;
		
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			
			TypedQuery<EpaypaDTipoCampoFiltro> query  = entityManager.createNamedQuery("EpaypaDTipoCampoFiltro.findOneByCodice", EpaypaDTipoCampoFiltro.class);
			
			query.setParameter("codice", cod);
			
			List<EpaypaDTipoCampoFiltro> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codice:".concat(cod));
				break;
			case 1:
				epaypaDTipoCampoFiltro = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("stato report", "codice", cod);
			}
			
			
		}catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		
		return epaypaDTipoCampoFiltro;
	}

}
