/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.Calendar;

import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTInvioMailDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTInvioMail;


public class EpaypaTInvioMailDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTInvioMail> implements EpaypaTInvioMailDao {

	private static final String CLASSNAME = EpaypaTInvioMailDaoImpl.class.getName();

	@Override
	public EpaypaTInvioMail findNextMailToSend(int seconds) throws PersistenceException {
		String methodName = "findByIdFlusso";
		
		

		EpaypaTInvioMail richiestaInvioMail = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTInvioMail> query = entityManager.createNamedQuery("EpaypaTInvioMail.findNextMailToSend", EpaypaTInvioMail.class);

			query.setMaxResults(1);

			Calendar secondsCal = Calendar.getInstance();
			secondsCal.add(Calendar.SECOND, -seconds);
			query.setParameter("limit", secondsCal, TemporalType.TIMESTAMP);

			try {
				richiestaInvioMail = query.getSingleResult();

			} catch (NoResultException e) {
			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return richiestaInvioMail;
	}

}
