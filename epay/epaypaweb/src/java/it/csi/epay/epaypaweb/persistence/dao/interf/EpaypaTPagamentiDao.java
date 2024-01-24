/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.RecordNumberGreaterThanThresholdException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPagamenti;

import java.util.Collection;
import java.util.List;


public interface EpaypaTPagamentiDao extends EpaypaDaoBase<Long, EpaypaTPagamenti> {

    TotalSizeAndLightListDto<EpaypaTPagamenti> ricerca ( PagamentiFilterDto filter,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord,
        PaginazioneDto pag,
        int thresholdRecordsNum) throws PersistenceException, RecordNumberGreaterThanThresholdException;

    EpaypaTPagamenti findPagamentoByFilter ( PagamentiFilterDto filter ) throws PersistenceException;

    List<EpaypaTPagamenti> findPagamentiByIuvIn ( Collection<String> iuv ) throws PersistenceException;
    
    EpaypaTPagamenti findOneByIuv ( String iuv ) throws PersistenceException;
}
