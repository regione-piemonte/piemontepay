/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.interf;

import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.common.*;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameReportEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.exception.RecordNumberGreaterThanThresholdException;

import javax.ejb.Local;
import java.util.List;


/** logica di business */
@Local
public interface ReportEntiBusiness {

    TotalSizeAndLightListDto<PagamentiDto> ricerca ( RicercaReportEntiDto  filter,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, PaginazioneDto pag ) throws BusinessException;

    List<PagamentiExportDto> esporta ( RicercaReportEntiDto ricercaReportEntiDto,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord ) throws BusinessException, RecordNumberGreaterThanThresholdException ;

    DettaglioPagamentiDto getDettaglio (  RicercaDettaglioReportEntiDto ricercaDettaglioReportEntiDto ) throws BusinessException;

    <T extends ReportBaseDto> TotalSizeAndLightListDto<T> ricercaReport( RicercaPrenotazioneReportEntiDto ricercaPrenotazioneReportEntiDto) throws BusinessException;
    
    <T extends ReportBaseDto> TotalSizeAndLightListDto<T> ricercaReport( ReportFilterDto reportFilterDto,  List<CriterioOrdinamentoDto<ColumnNameReportEnum>> ord, PaginazioneDto pag ) throws BusinessException;

    void salvaPrenotazioneReport (SalvaPrenotazioneReportEntiDto salvaPrenotazioneReportEntiDto) throws BusinessException;
        
    FileReportDto downloadFileReportRicerca (DownloadPrenotazioneReportEntiRequestDto downloadPrenotazioneReportEntiRequestDto) throws BusinessException;
    
}
