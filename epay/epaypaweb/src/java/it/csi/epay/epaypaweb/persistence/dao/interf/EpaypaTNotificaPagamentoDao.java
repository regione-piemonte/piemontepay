/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;

//@formatter:off
public interface EpaypaTNotificaPagamentoDao extends EpaypaDaoBase<Long, EpaypaTNotificaPagamento> {

	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException;

	public Long countAllByIUV(String iuv) throws PersistenceException;

	public List<EpaypaTNotificaPagamento> findAllByIdFlusso(Long idFlusso) throws PersistenceException;

}
//@formatter:on
