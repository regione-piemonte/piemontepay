/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTAggPosizioneDebitoriaCommon.getColumnPath;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTAggPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoriaLight;


public class EpaypaTAggPosizioneDebitoriaDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTAggPosizioneDebitoria> implements EpaypaTAggPosizioneDebitoriaDao {
	private static final String CLASSNAME = EpaypaTAggPosizioneDebitoriaDaoImpl.class.getName();

	@Override
	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "countAllByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTAggPosizioneDebitoriaLight> root = cquery.from(EpaypaTAggPosizioneDebitoriaLight.class);
			cquery.select(cbuilder.count(root));
			if (idFlusso != null) {
				cquery.where(cbuilder.equal(getColumnPath(root, ID_FLUSSO), idFlusso));
			}

			// esegue la query
			num = entityManager.createQuery(cquery).getSingleResult();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return num;
	}

	@Override
	public List<EpaypaTAggPosizioneDebitoria> findAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "findAllByIdFlusso";
		
		

		List<EpaypaTAggPosizioneDebitoria> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTAggPosizioneDebitoria> query = entityManager.createNamedQuery("EpaypaTAggPosizioneDebitoria.findAllByIdFlusso", EpaypaTAggPosizioneDebitoria.class);
			query.setParameter("idFlusso", idFlusso);
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
	
	@Override
	public List<EpaypaTAggPosizioneDebitoria> findAllByIdPosizioneDebitoriaEst(String idPosizioneDebitoriaEst) throws PersistenceException {
	    String methodName = "findAllByIdPosizioneDebitoriaEst";
	    
	    
	    
	    List<EpaypaTAggPosizioneDebitoria> entityList = null;
	    
	    try {
	        log.info ( CLASSNAME + " " + methodName + " - START" );
	        
	        TypedQuery<EpaypaTAggPosizioneDebitoria> query = entityManager.createNamedQuery("EpaypaTAggPosizioneDebitoria.findAllByIdPosizioneDebitoriaEst", EpaypaTAggPosizioneDebitoria.class);
	        query.setParameter("idPosizioneDebitoriaEst", idPosizioneDebitoriaEst);
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
