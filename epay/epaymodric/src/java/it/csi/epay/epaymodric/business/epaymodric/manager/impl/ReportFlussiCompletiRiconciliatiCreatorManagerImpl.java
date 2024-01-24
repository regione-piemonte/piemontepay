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

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.EsportazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ReportFlussiCreatorManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoFileReportEnum;

@Service
public class ReportFlussiCompletiRiconciliatiCreatorManagerImpl implements ReportFlussiCreatorManager {

	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private EsportazioneManager esportazioneManager;
	
	@Override
    @Transactional(readOnly=true)
	public List<String> generaReport(DTOInputWsRicercaFlussoOrigine flussoInput , CblTReport reportDto, int pageSize) {
		
		List<String> reportsFilesNames = null;
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

			reportsFilesNames = new LinkedList<>();
			
			int contatoreFile = 1;
			
			File reportFile = File.createTempFile(prefix + "_" + String.valueOf( contatoreFile ) + "_", suffix);
			reportsFilesNames.add( reportFile.getAbsolutePath() );
			reportFile.deleteOnExit();
			reportFileOutputStream = new FileOutputStream(reportFile);
			
			switch(tipoFormatoFile) {
				case CSV:
					esportazioneManager.generaFileReportCsv(flussoInput, reportFileOutputStream);
					break;
				case XLS:
				    reportsFilesNames = esportazioneManager.generaFileReportXlsx(flussoInput, prefix);
					break;
			}
						
		} catch (IOException e) {
			logger.error("ERRORE NELLA CREAZIONE DEL FILE DEL REPORT FLUSSI RENDICONTAZIONE: ", e);
			
		} catch (PersistenceException e) {
			logger.error("ERRORE NELLA LETTURA DEI DATI DEL FLUSSO DI RENDICONTAZIONE: ", e);
		} finally {
			if (reportFileOutputStream != null) {
				try {
					reportFileOutputStream.close();
				} catch (IOException e) {
					logger.error("ERRORE DURANTE LA CHIUSURA DELLO STREAM DI OUTPUT DEL FILE TEMPORANEO: ", e);
				}
			}
		}
		return reportsFilesNames;
	}

}
