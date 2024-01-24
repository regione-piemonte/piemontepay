/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTemplate;

public interface EpaypaTTemplateDao extends EpaypaDaoBase<Long, EpaypaTTemplate> {

	public EpaypaTTemplate findOneByIdEnteTipoFlusso(Integer idEnte, Integer idTipoFlusso) throws PersistenceException;
	public EpaypaTTemplate findOneByIdEnteNomeTemplate(Integer idEnte, String nomeTemplate) throws PersistenceException;

    EpaypaTTemplate findOneByTipoFlusso ( Integer idTipoFlusso ) throws PersistenceException;

}
