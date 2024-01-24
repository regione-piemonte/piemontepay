/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.AuditActionDao;
import it.csi.epay.epaypaweb.persistence.entity.AuditAction;


public class AuditActionDaoImpl extends EpaypaDaoBaseImpl<String, AuditAction> implements AuditActionDao {

	private static final String CLASSNAME = AuditActionDaoImpl.class.getSimpleName();

	@Override
	public AuditAction findByIdAction(String idAction) {
		String methodName = "findByIdAction";

		
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<AuditAction> query = entityManager.createNamedQuery("AuditAction.findById", AuditAction.class);
			query.setParameter("id", idAction);

			AuditAction entity = query.getSingleResult();

			if (entity != null) {
				return entity;
			}

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return null;
	}

}
