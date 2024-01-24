/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoriaLight;

//@formatter:off
public interface EpaypaTAggPosizioneDebitoriaLightDao extends EpaypaDaoBase<Long, EpaypaTAggPosizioneDebitoriaLight> {

	public List<EpaypaTAggPosizioneDebitoriaLight> findAllLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag) throws PersistenceException;

}
//@formatter:on
