/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaForAutocompleteDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaForAutocomplete;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;



public class EpaypaTPosizioneDebitoriaForAutocompleteDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTPosizioneDebitoriaForAutocomplete>
implements EpaypaTPosizioneDebitoriaForAutocompleteDao {

	private static final String CLASSNAME = EpaypaTPosizioneDebitoriaForAutocompleteDaoImpl.class.getName ();

	@Override
	public List<EpaypaTPosizioneDebitoriaForAutocomplete> getAutocompleteData ( String partialPosizioneDebitoria, Integer enteId, Integer idCodVersamento )
					throws PersistenceException {
		String methodName = "getAutocompleteData";
		List<EpaypaTPosizioneDebitoriaForAutocomplete> entityList;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder ();
			CriteriaQuery<EpaypaTPosizioneDebitoriaForAutocomplete> criteriaQuery
			= criteriaBuilder.createQuery ( EpaypaTPosizioneDebitoriaForAutocomplete.class );
			Root<EpaypaTPosizioneDebitoriaForAutocomplete> root = criteriaQuery.from ( EpaypaTPosizioneDebitoriaForAutocomplete.class );
			Join<EpaypaTPosizioneDebitoriaForAutocomplete, EpaypaTFlusso> joinIdFlusso = root.join ( "epaypaTFlusso" );
			criteriaQuery
			.where (
				criteriaBuilder.and (
					criteriaBuilder.like ( root.get ( "idPosizioneDebitoriaEst" ), "%" + partialPosizioneDebitoria + "%" ),
					criteriaBuilder.equal ( joinIdFlusso.get ( "epaypaTEnte" ), enteId ),
					criteriaBuilder.equal ( joinIdFlusso.get ( "epaypaTCodiceVersamento" ), idCodVersamento ) ) );

			TypedQuery<EpaypaTPosizioneDebitoriaForAutocomplete> query = entityManager.createQuery ( criteriaQuery );
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
