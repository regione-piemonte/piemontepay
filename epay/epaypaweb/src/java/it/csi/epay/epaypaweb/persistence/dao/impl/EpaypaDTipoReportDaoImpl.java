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
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoReportDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoReport;


public class EpaypaDTipoReportDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDTipoReport> implements EpaypaDTipoReportDao {

	static private final String CLASSNAME = EpaypaDTipoReportDaoImpl.class.getSimpleName();
	
	@Override
	public EpaypaDTipoReport findOneByCodice(String cod) throws PersistenceException {

		String methodName = "findOneByCodice";
		
		

		EpaypaDTipoReport epaypaDTipoReport = null;
		
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			
			TypedQuery<EpaypaDTipoReport> query = entityManager.createNamedQuery("EpaypaDTipoReport.findOneByCodice", EpaypaDTipoReport.class);
			
			query.setParameter("codice", cod);
			
			List<EpaypaDTipoReport> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codice:".concat(cod));
				break;
			case 1:
				epaypaDTipoReport = resultList.get(0);
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
		
		return epaypaDTipoReport;
	}

}
