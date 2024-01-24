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
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDStatoReportDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDStatoReport;


public class EpaypaDStatoReportDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDStatoReport> implements EpaypaDStatoReportDao {

	static private final String CLASSNAME = EpaypaDStatoReportDaoImpl.class.getSimpleName();
	
	@Override
	public EpaypaDStatoReport findOneByCodice(String cod) throws PersistenceException {
		String methodName = "findOneByCodice";
		
		

		EpaypaDStatoReport epaypaDStatoReport = null;
		
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			
			TypedQuery<EpaypaDStatoReport> query = entityManager.createNamedQuery("EpaypaDStatoReport.findOneByCodice", EpaypaDStatoReport.class);
			query.setParameter("codice", cod);
			
			List<EpaypaDStatoReport> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codice:".concat(cod));
				break;
			case 1:
				epaypaDStatoReport = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("stato report", "codice", cod);
			}
			
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		
		return epaypaDStatoReport;
	}

}
