/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.StatoReportManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoReport;
import it.csi.epay.epaymodric.business.epaymodric.repository.ReportRepository;

@Service
public class StatoReporManagerImpl implements StatoReportManager{
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	ReportRepository reportRepository;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateStatoReport(CblDStatoReport statoReport, Long reportId) {
		String methodName = "updateStatoReport";
		
		try {
			reportRepository.updateStatoReportById(statoReport, reportId);
		}catch(Exception e) {
			logger.error("errore imprevisto in " + this.getClass().getSimpleName() + "::" + methodName, e);
		}
	}

}
