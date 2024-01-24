/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;

//@formatter:off
public interface EpaypaTFlussoDao extends EpaypaDaoBase<Long, EpaypaTFlusso> {

	Long countAllByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException;

}
//@formatter:on
