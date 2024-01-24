/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;

//@formatter:off
public interface EpaypaTFlussoNotificaPagamentoDao extends EpaypaDaoBase<Long, EpaypaTFlussoNotificaPagamento> {

    public Long countAllByFilter ( FlussoLightFilterDto filter, String codUtente ) throws PersistenceException;

    public List<EpaypaTNotificaPagamento> findAllLightByFilter ( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord,
        PaginazioneDto pag, String codUtente ) throws PersistenceException;

}
//@formatter:on
