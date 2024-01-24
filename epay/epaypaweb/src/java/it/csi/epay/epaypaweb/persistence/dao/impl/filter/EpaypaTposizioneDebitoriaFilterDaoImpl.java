/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl.filter;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.filter.EpaypaTPosizioneDebitoriaFilterDao;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTPosizioneDebitoriaFilter;

import javax.persistence.TypedQuery;
import java.util.List;


public class EpaypaTposizioneDebitoriaFilterDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTPosizioneDebitoriaFilter> implements
																													 EpaypaTPosizioneDebitoriaFilterDao {

	private static final Object CLASSNAME = "EpaypaTposizioneDebitoriaFilterDaoImpl";

	@Override
	public List<EpaypaTPosizioneDebitoriaFilter> findAllByIdFlusso ( Long idFlusso ) throws PersistenceException {
		String methodName = "findAllByIdFlusso";
		List<EpaypaTPosizioneDebitoriaFilter> entityList;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			TypedQuery<EpaypaTPosizioneDebitoriaFilter>
							query =
							entityManager.createNamedQuery ( "EpaypaTPosizioneDebitoria.findAllByIdFlussoFilter", EpaypaTPosizioneDebitoriaFilter.class );
			query.setParameter ( "idFlusso", idFlusso );
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
