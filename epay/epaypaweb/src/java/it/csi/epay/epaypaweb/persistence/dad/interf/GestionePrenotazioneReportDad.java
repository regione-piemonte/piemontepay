/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import java.util.Date;
import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FileReportDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.ReportBaseDto;
import it.csi.epay.epaypaweb.dto.ReportFilterDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameReportEnum;
import it.csi.epay.epaypaweb.enumeration.StatoReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBase;

//@formatter:off
/** cuscinetto intermedio tra business e dao, contiene semplice logica di accesso ai dati */
public interface GestionePrenotazioneReportDad extends EPaypaDadBase {

    /**
     * Ricerca report prenotati per ente e per utente
     * @param idEnte
     * @param idUtente
     * @param tipoReport
     * @return
     * @throws PersistenceException
     */
	<T extends ReportBaseDto> TotalSizeAndLightListDto<T> ricercaReport(Integer idEnte, Long idUtente, PaginazioneDto pag, TipoReportEnum tipoReport ) throws PersistenceException;
	
	/**
     * Ricerca report prenotati per ente e per utente
     * @param reportFilterDto
     * @return
     * @throws PersistenceException
     */
	<T extends ReportBaseDto> TotalSizeAndLightListDto<T> ricercaReport(ReportFilterDto reportFilterDto, List<CriterioOrdinamentoDto<ColumnNameReportEnum>> ord, PaginazioneDto pag) throws PersistenceException;
    
    /**
     * Salva report da elaborare
     * @param report
     * @throws PersistenceException
     */
    void salvaReportRicerca(ReportBaseDto report) throws PersistenceException;
    
    
    /**
     * Modifica report da elaborare
     * @param report
     * @throws PersistenceException
     */
    void updateReportRicerca(ReportBaseDto report) throws PersistenceException;
    
    /**
     * Ricerca i report da elaborare
     * @param maxResultSize
     * @return
     * @throws PersistenceException
     */
    public List<ReportBaseDto> ricercaReportDaGenerareByStatoReport(StatoReportEnum statoReport, int maxResultSize) throws PersistenceException;
    
    /**
     * Inserisce il file al report specificato
     * @param fileReport
     * @param report
     * @throws PersistenceException
     */
    void inserisciFileReport(FileReportDto fileReport, ReportBaseDto report) throws PersistenceException;
    
    
    /**
     * Ricerca i report per stato e 
     * @param stateCodes
     * @param thresholdDate
     * @return
     * @throws PersistenceException
     */
    public  List<ReportBaseDto> ricercaReportByStateCodesAndDataModificaBefore(String[] stateCodes, Date thresholdDate) throws PersistenceException;
    
    /**
     * Cancella il report e il relativo file
     * @param report
     * @throws PersistenceException
     */
    void deleteReport(ReportBaseDto report) throws PersistenceException;
    
    /**
     * Recupera il contenuto di un file
     * @param idEnte
     * @param idUtente
     * @param idFile
     * @return
     * @throws PersistenceException
     */
    FileReportDto downloadFileReportRicerca (Integer idEnte, Long idUtente, Long idFile) throws PersistenceException;
}
//@formatter:on
