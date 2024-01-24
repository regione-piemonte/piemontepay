/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.manager.EsportazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ReportCreatorManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoFileReportEnum;

@Service
public class ReportContabileCreatorManagerImpl implements ReportCreatorManager {
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private EsportazioneManager esportazioneManager;
	
	@Override
	public List<String> generaReport(List<CblTFlussoOrigine> flussi, CblTReport reportDto , int pageSize) {
			
		List<String> reportsNames = null;
		FileOutputStream reportFileOutputStream = null;
		String prefix = reportDto.getNominativoExport();
		String suffix = ".tmp";

		TipoFileReportEnum tipoFormatoFile = null;

		TipoFileReportEnum tipoFileReportEnum = TipoFileReportEnum.fromId(reportDto.getCblDTipoFileReport().getCodice());
		switch (tipoFileReportEnum) {
			case CSV:
				suffix = ".csv";
				tipoFormatoFile = TipoFileReportEnum.CSV;
				break;
			case XLS:
				suffix = ".xlsx";
				tipoFormatoFile = TipoFileReportEnum.XLS;
				break;
			default:
				throw new RuntimeException("Formato file non supportato");		
		}

		try {

			File reportFile = File.createTempFile(prefix, suffix);
			reportFile.deleteOnExit();
			reportFileOutputStream = new FileOutputStream(reportFile);			
			reportsNames = new LinkedList<>();
			reportsNames.add(reportFile.getAbsolutePath());

			switch(tipoFormatoFile) {
			case CSV:
				esportazioneManager.generaFileReportContabileCsv(flussi, reportFileOutputStream);
				break;
			case XLS:
				esportazioneManager.generaFileReportContabileXlsx(flussi, reportFileOutputStream);
				break;
		}
		} catch (IOException e) {
			logger.error("ERRORE NELLA CREAZIONE DEL FILE DEL REPORT FLUSSI RENDICONTAZIONE: ", e);
			
		} finally {
			if (reportFileOutputStream != null) {
				try {
					reportFileOutputStream.close();
				} catch (IOException e) {
					logger.error("ERRORE DURANTE LA CHIUSURA DELLO STREAM DI OUTPUT DEL FILE TEMPORANEO: ", e);
				}
			}
		}

		return reportsNames;
	}
	
}
