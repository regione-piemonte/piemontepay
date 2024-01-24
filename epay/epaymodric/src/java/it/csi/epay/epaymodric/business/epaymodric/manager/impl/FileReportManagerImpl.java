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

import it.csi.epay.epaymodric.business.epaymodric.manager.FileReportManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFileReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;
import it.csi.epay.epaymodric.business.epaymodric.repository.FileReportRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ReportRepository;

@Service
public class FileReportManagerImpl implements FileReportManager {

	@Autowired
	FileReportRepository fileReportRepository;
	@Autowired
	ReportRepository reportRepository;
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void salvaFileReport(CblTFileReport fileReport, CblTReport report) {
		String methodName = "SalvaFileReport";
        try {
            fileReport = fileReportRepository.saveAndFlush(fileReport);
            reportRepository.updateFileReportById(fileReport, report.getId());
        }catch ( Exception e ) {
        	logger.error("Errore imprevisto in " + this.getClass().getSimpleName() + "::" + methodName);
        	throw new RuntimeException("Errore imprevisto in " + this.getClass().getSimpleName() + "::" + methodName, e);
        }
	}
}
