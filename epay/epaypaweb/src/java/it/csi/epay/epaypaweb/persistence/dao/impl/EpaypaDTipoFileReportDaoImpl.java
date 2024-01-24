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
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoFileReportDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoFileReport;


public class EpaypaDTipoFileReportDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDTipoFileReport> implements EpaypaDTipoFileReportDao {

	static private final String CLASSNAME = EpaypaDTipoFileReportDaoImpl.class.getSimpleName();

	@Override
	public EpaypaDTipoFileReport findOneByCodice(String cod) throws PersistenceException {
		String methodName = "findOneByCodice";
		
		

		EpaypaDTipoFileReport epaypaDTipoFileReport = null;
		
		try {
log.info ( CLASSNAME + " " + methodName + " - START" );
			
			TypedQuery<EpaypaDTipoFileReport> query = entityManager.createNamedQuery("EpaypaDTipoFileReport.findOneByCodice", EpaypaDTipoFileReport.class);
			query.setParameter("codice", cod);
			
			List<EpaypaDTipoFileReport> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codice:".concat(cod));
				break;
			case 1:
				epaypaDTipoFileReport = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("tipo file report", "codice", cod);
			}
			
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return epaypaDTipoFileReport;
	}
}
