/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.Formatter;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTTemplateDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTemplate;


public class EpaypaTTemplateDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTTemplate> implements EpaypaTTemplateDao {
	static private final String CLASSNAME = EpaypaTTemplateDaoImpl.class.getSimpleName();

	@Override
	public EpaypaTTemplate findOneByIdEnteTipoFlusso(Integer idEnte, Integer idTipoFlusso) throws PersistenceException {
		String methodName = "findOneByIdEnteTipoFlusso";
		
		
		

		EpaypaTTemplate epaypaTTemplate = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTTemplate> query = entityManager.createNamedQuery("EpaypaTTemplate.findTemplateByIdEnteTipoFlusso", EpaypaTTemplate.class);
			query.setParameter("idEnte", idEnte);
			query.setParameter("idTipoFlusso", idTipoFlusso);

			List<EpaypaTTemplate> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
                log.warn ( "nessun risultato trovato per idEnte: ".concat ( idEnte == null ? "null" : idEnte.toString () ) );
				break;
			case 1:
				epaypaTTemplate = resultList.get(0);
				break;
			default:
				String values = new Formatter().format("%d, %d", idEnte, idTipoFlusso).toString();
				throw new PersistenceFoundMoreThanOneResultException("template", "idEnte, idTipoFlusso", values);
			}

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return epaypaTTemplate;
	}

    @Override
    public EpaypaTTemplate findOneByTipoFlusso ( Integer idTipoFlusso ) throws PersistenceException {
        String methodName = "findOneByIdEnteTipoFlusso";
        
        

        EpaypaTTemplate epaypaTTemplate = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTTemplate> query = entityManager.createNamedQuery ( "EpaypaTTemplate.findTemplateByTipoFlusso", EpaypaTTemplate.class );
            query.setParameter ( "idTipoFlusso", idTipoFlusso );

            List<EpaypaTTemplate> resultList = query.getResultList ();
            switch ( resultList.size () ) {
            case 0 :
                log.warn ( "nessun risultato trovato per idTipoFlusso:".concat ( idTipoFlusso == null ? "null" : idTipoFlusso.toString () ) );
                break;
            case 1 :
                epaypaTTemplate = resultList.get ( 0 );
                break;
            default :
                String values = new Formatter ().format ( "%d", idTipoFlusso ).toString ();
                throw new PersistenceFoundMoreThanOneResultException ( "template", "idTipoFlusso", values );
            }

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return epaypaTTemplate;
    }

	@Override
	public EpaypaTTemplate findOneByIdEnteNomeTemplate(Integer idEnte, String nomeTemplate) throws PersistenceException {
		String methodName = "findOneByIdEnteNomeTemplate";
		
		
		

		EpaypaTTemplate epaypaTTemplate = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTTemplate> query = entityManager.createNamedQuery("EpaypaTTemplate.findTemplateByIdEnteNomeTemplate", EpaypaTTemplate.class);
			query.setParameter("idEnte", idEnte);
			query.setParameter("nomeTemplate", nomeTemplate);

			List<EpaypaTTemplate> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per idEnte:".concat(idEnte.toString()));
				break;
			case 1:
				epaypaTTemplate = resultList.get(0);
				break;
			default:
				String values = new Formatter().format("%d, %s", idEnte, nomeTemplate).toString();
				throw new PersistenceFoundMoreThanOneResultException("template", "idEnte, nomeTemplate", values);
			}

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return epaypaTTemplate;
	}

}
