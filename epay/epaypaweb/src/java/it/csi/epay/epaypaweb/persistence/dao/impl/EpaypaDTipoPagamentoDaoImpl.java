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
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoPagamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoPagamento;



public class EpaypaDTipoPagamentoDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDTipoPagamento> implements EpaypaDTipoPagamentoDao {
	static private final String CLASSNAME = EpaypaDTipoPagamentoDaoImpl.class.getSimpleName();

	@Override
    public EpaypaDTipoPagamento findOneByCodice ( String cod ) throws PersistenceException {
        String methodName = "findOneByCodice";
		
        

        EpaypaDTipoPagamento entity = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaDTipoPagamento> query
                = entityManager.createNamedQuery ( "EpaypaDTipoPagamento.findOneByCodice", EpaypaDTipoPagamento.class );
            query.setParameter ( "codice", cod );

            List<EpaypaDTipoPagamento> resultList = query.getResultList ();
			switch (resultList.size()) {
			case 0:
                log.debug ( "nessun risultato trovato per codice:".concat ( cod ) );
				break;
			case 1:
				entity = resultList.get(0);
				break;
			default:
                throw new PersistenceFoundMoreThanOneResultException ( "tipo pagamento", "codice", cod );
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
