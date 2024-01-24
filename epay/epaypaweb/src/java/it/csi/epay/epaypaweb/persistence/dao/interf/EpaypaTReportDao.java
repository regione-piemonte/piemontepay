/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.Date;
import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.ReportFilterDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameReportEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTReport;

public interface EpaypaTReportDao extends EpaypaDaoBase<Long, EpaypaTReport> {

    /**
     * Restituisce i report prenotati dall'utente per quel detterminato ente.
     * @param idEnte
     * @param idUtente
     * @return lista delle prenotazioni
     * @throws PersistenceException
     */
    TotalSizeAndLightListDto<EpaypaTReport> findAllByIdEnteAndIdUtente(Integer idEnte, Long idUtente, PaginazioneDto pag) throws PersistenceException;
    
    /**
     * Restituisce i report prenotati dall'utente per quel detterminato ente.
     * @param idEnte
     * @param idUtente
     * @param codiceTipoReport
     * @return lista delle prenotazioni
     * @throws PersistenceException
     */
    TotalSizeAndLightListDto<EpaypaTReport> findAllByIdEnteAndIdUtenteAndCodiceTipoReport(Integer idEnte, Long idUtente, String codiceTipoReport, PaginazioneDto pag) throws PersistenceException;
    
    /**
     * Restituisce i report prenotati che soddisfano il filtro di ricerca.
     * @param reportFilterDto
     * @return lista delle prenotazioni
     * @throws PersistenceException
     */
    TotalSizeAndLightListDto<EpaypaTReport> findAllByFilter(ReportFilterDto reportFilterDto, List<CriterioOrdinamentoDto<ColumnNameReportEnum>> ord, PaginazioneDto pag) throws PersistenceException;
    
    
    
    /**
     * Restituisce le prenotazioni dei report in base al codice dello stato.
     * @param statusCode
     * @param maxResultSize
     * @return lista delle prenotazioni
     * @throws PersistenceException
     */
    List<EpaypaTReport> findByStatusCode( String codiceStato, int maxResultSize ) throws PersistenceException;
    
    
    /**
     * Restituisce le prenotazioni dei report in base allo stato e alla data ultima modifica.
     * @param stateCodes
     * @param thresholdDate
     * @return lista delle prenotazioni
     * @throws PersistenceException
     */
    List<EpaypaTReport> findByStateCodesAndDataModificaBefore(String[] stateCodes, Date thresholdDate) throws PersistenceException;
    
}
