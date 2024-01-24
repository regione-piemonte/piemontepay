/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTColonnaTemplate;

//@formatter:off
public interface EpaypaTColonnaTemplateDao extends EpaypaDaoBase<Long, EpaypaTColonnaTemplate> {

	public List<EpaypaTColonnaTemplate> findAllByIdTemplate(Long idTemplate) throws PersistenceException;

}
//@formatter:on
