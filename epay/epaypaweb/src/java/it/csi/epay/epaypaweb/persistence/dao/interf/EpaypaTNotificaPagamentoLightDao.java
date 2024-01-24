/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;

//@formatter:off
public interface EpaypaTNotificaPagamentoLightDao extends EpaypaDaoBase<Long, EpaypaTNotificaPagamentoLight> {

	public List<EpaypaTNotificaPagamentoLight> findAllLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag) throws PersistenceException;

}
//@formatter:on
