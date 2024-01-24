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
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;


public class EpaypaTEnteDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaTEnte> implements EpaypaTEnteDao {

	private static final String CLASSNAME = EpaypaTEnteDaoImpl.class.getSimpleName ();

	@Override
	public String findCodFiscaleEnteById(Integer idEnte) throws PersistenceException {
		String methodName = "findCodFiscaleEnteById";
		
		

		String cod = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<String> query = entityManager.createNamedQuery("EpaypaTEnte.findCodFiscaleEnteByIdEnte", String.class);
			query.setParameter("idEnte", idEnte);
			//
			List<String> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per idEnte:".concat(idEnte.toString()));
				break;
			case 1:
				cod = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("codFiscaleEnte", "idEnte", idEnte);
			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return cod;
	}

	@Override
	public Integer findIdEnteByCodFiscale(String codFiscaleEnte) throws PersistenceException {
		String methodName = "findIdEnteByCodFiscale";
		
		

		Integer id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<Integer> query = entityManager.createNamedQuery("EpaypaTEnte.findIdEnteByCodFiscaleEnte", Integer.class);
			query.setParameter("codFiscaleEnte", codFiscaleEnte);
			//
			List<Integer> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codFiscaleEnte: ".concat(codFiscaleEnte));
				break;
			case 1:
				id = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("idEnte", "codFiscaleEnte", codFiscaleEnte);
			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	@Override
	public EpaypaTEnte findOneByCodFiscale(String codFiscaleEnte) throws PersistenceException {
		String methodName = "findOneByCodFiscale";
		
		

		EpaypaTEnte entity = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTEnte> query = entityManager.createNamedQuery("EpaypaTEnte.findOneByCodFiscaleEnte", EpaypaTEnte.class);
			query.setParameter("codFiscaleEnte", codFiscaleEnte);
			//
			List<EpaypaTEnte> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codFiscaleEnte:".concat(codFiscaleEnte));
				break;
			case 1:
				entity = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("ente", "codFiscaleEnte", codFiscaleEnte);
			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entity;
	}

	@Override
	public List<EpaypaTEnte> findAllByCodUtente(String codUtente) throws PersistenceException {
		String methodName = "findAllByCodUtente";
		
		

		List<EpaypaTEnte> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTEnte> query = entityManager.createNamedQuery("EpaypaTEnte.findAllByCodUtente", EpaypaTEnte.class);
			query.setParameter("codUtente", codUtente);
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

    public Integer findIdEnteMax () throws PersistenceException {
        String methodName = "findIdEnteMax";
        

        Integer id = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTEnte> query = entityManager.createNamedQuery("EpaypaTEnte.findIdEnteMax", EpaypaTEnte.class);
            //
            query.setMaxResults ( 1 );
             
            EpaypaTEnte item = query.getSingleResult ();
            
            if(item == null) id = 0;
            else 
                id = item.getIdEnte ();

        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return id;
    }

}
