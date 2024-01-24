/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.IUV;
import static it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTNotificaPagamentoCommon.getColumnPath;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTNotificaPagamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;


public class EpaypaTNotificaPagamentoDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTNotificaPagamento> implements EpaypaTNotificaPagamentoDao {
	private static final String CLASSNAME = EpaypaTNotificaPagamentoDaoImpl.class.getName();

	@Override
	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "countAllByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTNotificaPagamentoLight> root = cquery.from(EpaypaTNotificaPagamentoLight.class);
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
	public List<EpaypaTNotificaPagamento> findAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "findAllByIdFlusso";
		
		

		List<EpaypaTNotificaPagamento> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTNotificaPagamento> query = entityManager.createNamedQuery("EpaypaTNotificaPagamento.findAllByIdFlusso", EpaypaTNotificaPagamento.class);
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
    public Long countAllByIUV ( String iuv ) throws PersistenceException {
        String methodName = "countAllByIUV";
        
        

        Long num = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            // costruisce la query dinamicamente
            CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
            Root<EpaypaTNotificaPagamentoLight> root = cquery.from(EpaypaTNotificaPagamentoLight.class);
            cquery.select(cbuilder.count(getColumnPath(root, IUV)));
            if (iuv != null) {
                cquery.where(cbuilder.equal(getColumnPath(root, IUV), iuv));
            }

            // esegue la query
            num = entityManager.createQuery(cquery).getSingleResult();

        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return num;    }

}
