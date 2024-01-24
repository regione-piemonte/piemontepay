/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTFlussoFilter;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;

import java.util.List;

//@formatter:off
public interface EpaypaTFlussoLightDao extends EpaypaDaoBase<Long, EpaypaTFlussoLight> {

	List<EpaypaTFlussoLight> findAllLightByFilter(FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag, String codUtente) throws PersistenceException;
	
	List<Long> findIdFlussoByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException;

	List<EpaypaTFlussoFilter> findAllbyFilter ( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag,
					String codUtente )
					throws PersistenceException;
}
//@formatter:on
