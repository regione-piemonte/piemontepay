/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRiversamento;

//@formatter:off
public interface EpaypaTRiversamentoDao extends EpaypaDaoBase<Long, EpaypaTRiversamento> {

	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException;

	public List<EpaypaTRiversamento> findAllByIdFlusso(Long idFlusso) throws PersistenceException;

	public List<EpaypaTRiversamento> findAllByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag) throws PersistenceException;

}
//@formatter:on
