/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoria;

import java.util.List;

//@formatter:off
public interface EpaypaTAggPosizioneDebitoriaDao extends EpaypaDaoBase<Long, EpaypaTAggPosizioneDebitoria> {

	Long countAllByIdFlusso(Long idFlusso) throws PersistenceException;

	List<EpaypaTAggPosizioneDebitoria> findAllByIdFlusso(Long idFlusso) throws PersistenceException;

	List<EpaypaTAggPosizioneDebitoria> findAllByIdPosizioneDebitoriaEst(String idPosizioneDebitoriaEst) throws PersistenceException;
	
}
//@formatter:on
