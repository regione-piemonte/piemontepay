/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.DettaglioPagamentiDto;
import it.csi.epay.epaypaweb.dto.PagamentiDto;
import it.csi.epay.epaypaweb.dto.PagamentiExportDto;
import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.RecordNumberGreaterThanThresholdException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPagamenti;


/** cuscinetto intermedio tra business e dao, contiene semplice logica di accesso ai dati */
public interface GestionePagamentiDad extends EPaypaDadBase {

    TotalSizeAndLightListDto<PagamentiDto> ricerca ( PagamentiFilterDto filter,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord,
        PaginazioneDto pag ) throws PersistenceException, RecordNumberGreaterThanThresholdException;

    DettaglioPagamentiDto getDettaglio ( String iuv ) throws PersistenceException;

    TotalSizeAndLightListDto<PagamentiExportDto> esporta ( PagamentiFilterDto filter, List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, PaginazioneDto pag ) throws PersistenceException, RecordNumberGreaterThanThresholdException;

}
