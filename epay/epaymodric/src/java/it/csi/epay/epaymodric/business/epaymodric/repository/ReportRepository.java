/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFileReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;

public interface ReportRepository extends JpaRepository<CblTReport, Long> {

    /**
     * Ricerca tutti i report prenotati per tipo report, utente ed ente.
     * @param cfUtente
     * @param cfEnte
     * @param idTipoReport
     * @return Lista di record prenotati.
     */
	List<CblTReport> findAllByCodiceFiscaleUtenteAndCodiceFiscaleEnteAndCblDTipoReportId(String cfUtente, String cfEnte, Long idTipoReport);
	
	@Modifying
	@Query("UPDATE CblTReport set cblDStatoReport=?1 where id=?2")
	void updateStatoReportById(CblDStatoReport statoReport, Long id);
	
	@Query("SELECT r FROM CblTReport r WHERE cblDStatoReport.codice IN :statoReport AND cblTFileReport.dataModifica < :dataModifica")
	List<CblTReport> findByCblDStatoReportCodiceAndCblTFileReportDataModificaBefore(@Param("statoReport") List<String> statoReport, @Param("dataModifica") Date dataModifica);
	
	@Query("SELECT r FROM CblTReport r WHERE cblDStatoReport=?")
	List<CblTReport> findByCblDStatoReport(CblDStatoReport cblDStatoReport, Pageable pageable);
	
	@Modifying
	@Query("UPDATE CblTReport set cblTFileReport=?1 where id=?2")
	void updateFileReportById(CblTFileReport fileReport, Long id);
}	
