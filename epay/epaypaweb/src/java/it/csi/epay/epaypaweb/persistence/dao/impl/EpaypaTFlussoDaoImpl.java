/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTFlussoCommon;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class EpaypaTFlussoDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTFlusso> implements EpaypaTFlussoDao {

	private static final String CLASSNAME = EpaypaDaoBaseImpl.class.getSimpleName ();

	@Override
	public Long countAllByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException {
		String methodName = "countAllByFilter";
		Long num;
		try {
			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder ();
			CriteriaQuery<Long> cquery = cbuilder.createQuery ( Long.class );
			Root<EpaypaTFlussoLight> root = cquery.from ( EpaypaTFlussoLight.class );
			cquery.select ( cbuilder.count ( root ) );
			List<Predicate> predicates = EpaypaTFlussoCommon.buildPredicates ( filter, cbuilder, root );
			Predicate userRestrictions = EpaypaTFlussoCommon.buildUserRestrictions ( codUtente, cbuilder, root );
			if ( userRestrictions != null ) {
				predicates.add ( userRestrictions );
			}
			if ( predicates != null && !predicates.isEmpty () ) {
				cquery.where ( cbuilder.and ( predicates.toArray ( new Predicate[0] ) ) );
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

}
