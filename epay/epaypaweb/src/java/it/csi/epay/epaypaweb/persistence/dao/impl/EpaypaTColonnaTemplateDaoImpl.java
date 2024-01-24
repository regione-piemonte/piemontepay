/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTColonnaTemplateDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTColonnaTemplate;


public class EpaypaTColonnaTemplateDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTColonnaTemplate> implements EpaypaTColonnaTemplateDao {
	static private final String CLASSNAME = EpaypaTColonnaTemplateDaoImpl.class.getSimpleName();

	@Override
	public List<EpaypaTColonnaTemplate> findAllByIdTemplate(Long idTemplate) throws PersistenceException {
		String methodName = "findAllByIdTemplate";
		
		

		List<EpaypaTColonnaTemplate> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTColonnaTemplate> query = entityManager.createNamedQuery("EpaypaTColonnaTemplate.findAllByIdTemplate", EpaypaTColonnaTemplate.class);
			query.setParameter("idTemplate", idTemplate);
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
