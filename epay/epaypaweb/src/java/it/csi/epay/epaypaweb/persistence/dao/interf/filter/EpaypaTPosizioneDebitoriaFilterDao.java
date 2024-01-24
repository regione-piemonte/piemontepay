/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf.filter;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTPosizioneDebitoriaFilter;

import java.util.List;


public interface EpaypaTPosizioneDebitoriaFilterDao extends EpaypaDaoBase<Long, EpaypaTPosizioneDebitoriaFilter> {

	List<EpaypaTPosizioneDebitoriaFilter> findAllByIdFlusso ( Long idFlusso ) throws PersistenceException;
}
