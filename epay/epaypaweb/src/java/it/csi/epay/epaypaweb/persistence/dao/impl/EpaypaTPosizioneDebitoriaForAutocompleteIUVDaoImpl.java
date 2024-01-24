/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaForAutocompleteIUVDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTposizioneDebitoriaForAutocompleteIUV;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class EpaypaTPosizioneDebitoriaForAutocompleteIUVDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTposizioneDebitoriaForAutocompleteIUV>
				implements EpaypaTPosizioneDebitoriaForAutocompleteIUVDao {

	private static final String CLASSNAME = EpaypaTPosizioneDebitoriaForAutocompleteIUVDaoImpl.class.getName ();

	@Override public List<EpaypaTposizioneDebitoriaForAutocompleteIUV> getAutocompleteData ( String partialiuv, Integer enteId, Integer idCodVersamento )
					throws PersistenceException {
		String methodName = "getAutocompleteData";
		List<EpaypaTposizioneDebitoriaForAutocompleteIUV> entityList;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder ();
			CriteriaQuery<EpaypaTposizioneDebitoriaForAutocompleteIUV> criteriaQuery
				= criteriaBuilder.createQuery ( EpaypaTposizioneDebitoriaForAutocompleteIUV.class );
			Root<EpaypaTposizioneDebitoriaForAutocompleteIUV> root = criteriaQuery.from ( EpaypaTposizioneDebitoriaForAutocompleteIUV.class );
			Join<EpaypaTposizioneDebitoriaForAutocompleteIUV, EpaypaTFlusso> joinIdFlusso = root.join ( "epaypaTFlusso" );
			criteriaQuery.where (
				criteriaBuilder.and (
								criteriaBuilder.like ( root.get ( "iuv" ), "%" + partialiuv + "%" ),
								criteriaBuilder.equal ( joinIdFlusso.get ( "epaypaTEnte" ), enteId ),
								criteriaBuilder.equal ( joinIdFlusso.get ( "epaypaTCodiceVersamento" ), idCodVersamento ) ) );
			TypedQuery<EpaypaTposizioneDebitoriaForAutocompleteIUV> query = entityManager.createQuery ( criteriaQuery );
			query.setMaxResults ( 25 );
			entityList = query.getResultList ();
		} catch ( Throwable e ) {
			log.error ( "Errore imprevisto", e );
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}
}
