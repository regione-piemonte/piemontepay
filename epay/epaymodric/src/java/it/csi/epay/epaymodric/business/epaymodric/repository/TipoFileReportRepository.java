/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDTipoFileReport;


public interface TipoFileReportRepository extends JpaRepository<CblDTipoFileReport, Long> {
	
	CblDTipoFileReport findByCodice(String codice);
	
	CblDTipoFileReport findById(Integer id);

}
