/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.ReportCreatorTypes;
import it.csi.epay.epaypaweb.business.interf.ReportCreatorBusiness;
import it.csi.epay.epaypaweb.business.interf.TemplateBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapperRiversamento;
import it.csi.epay.epaypaweb.enumeration.TipoFileReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestioneFlussiDad;
import it.csi.epay.epaypaweb.util.Costanti;
import it.csi.epay.epaypaweb.util.spreadsheet.PageableSpreadsheetGenerator;
import it.csi.epay.epaypaweb.util.spreadsheet.PageableSpreadsheetGeneratorFactory;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.*;
import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ReportCreatorTypes(TipoReportEnum.RENDICONTAZIONE)
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionManagement ( TransactionManagementType.BEAN )
public class FlussiRendicontazioneReportCreatorBusinessImpl extends EPaypaBusinessBase implements ReportCreatorBusiness <ReportFlussoRendicontazioneDto> {
	
    private static final String CLASSNAME = FlussiRendicontazioneReportCreatorBusinessImpl.class.getSimpleName();
	
	@Inject
    private GestioneFlussiDad gestioneFlussiDad;
	
	@EJB
    private TemplateBusiness templateBusiness;

	@Override
	public List<String> generaReport(ReportFlussoRendicontazioneDto reportDto, int pageSize) throws BusinessException {

		String methodName = "generaReport";
		
		
		
		
		List<String> reportsFilesNames = null;
		FileOutputStream reportFileOutputStream = null;
		String prefix = reportDto.getNominativoExport();
		String suffix = ".tmp";

		TipoFormatoFileEnum tipoFormatoFile = null;
		
		TipoFileReportEnum tipoFileReportEnum = TipoFileReportEnum.fromId(reportDto.getTipoFileReport().getCodice());
		switch (tipoFileReportEnum) {
		case CSV:
			suffix = ".csv";
			tipoFormatoFile = TipoFormatoFileEnum.CSV;
			break;
		case XLS:
			suffix = ".xlsx";
			tipoFormatoFile = TipoFormatoFileEnum.XLSX;
			break;
		}
		
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			reportsFilesNames = new ArrayList<String>();
			
			TemplateMapperRiversamento templateMapperRiversamento = new TemplateMapperRiversamento ();
			TemplateDto template = templateBusiness.getTemplate ( reportDto.getIdEnte(), Costanti.ID_TEMPLATE_FLUSSI_RENDICONTAZIONE_BASE );
			
			int contatoreFile = 1;
			long contatoreRigheFile = 1;
			List<Long> idFlussi = getIdFlussi(reportDto);
			if(!CollectionUtils.isEmpty(idFlussi))
			{
				File reportFile = File.createTempFile(prefix + "_" + String.valueOf( contatoreFile ) + "_", suffix);
				reportsFilesNames.add( reportFile.getAbsolutePath() );
				reportFile.deleteOnExit();
				reportFileOutputStream = new FileOutputStream(reportFile);
				
				PageableSpreadsheetGenerator<RiversamentoDto> pageableSpreadsheetGenerator = PageableSpreadsheetGeneratorFactory
						.getPageableSpreadsheetGenerator(template, templateMapperRiversamento, reportFileOutputStream,
								tipoFormatoFile, pageSize);
		
				
				for (Long idFlusso : idFlussi) {
					
					int pageNumber = 0;
					long contatoreItemFlusso = gestioneFlussiDad.countAllRiversamentoByIdFlusso(idFlusso);
					
					//se supero il limite massimo di righe per file scrivo il file corrente e ne creo uno nuovo
					if ( ( contatoreRigheFile + contatoreItemFlusso ) > Costanti.MAX_ROWS_REPORT_FILE ) {
						
						pageableSpreadsheetGenerator.flushData();
						
						if (reportFileOutputStream != null) {
							try {
								reportFileOutputStream.close();
							} catch (IOException e) {
								log.error("ERRORE DURANTE LA CHIUSURA DELLO STREAM DI OUTPUT DEL FILE TEMPORANEO ", e);
								throw new BusinessException("ERRORE DURANTE LA CHIUSURA DELLO STREAM DI OUTPUT DEL FILE TEMPORANEO ", e);
							}
						}
						
						contatoreFile++;
						contatoreRigheFile = 0;
						reportFile = File.createTempFile(prefix + "_" + String.valueOf( contatoreFile ) + "_", suffix);
						reportsFilesNames.add( reportFile.getAbsolutePath() );
						reportFile.deleteOnExit();
						reportFileOutputStream = new FileOutputStream(reportFile);
						
						pageableSpreadsheetGenerator = PageableSpreadsheetGeneratorFactory
								.getPageableSpreadsheetGenerator(template, templateMapperRiversamento, reportFileOutputStream,
										tipoFormatoFile, pageSize);
						
					}
					
					
					while ((pageNumber * pageSize) < contatoreItemFlusso) {

						FlussoCompletoDto<RiversamentoDto> flussoCompleto = gestioneFlussiDad.findFlussoRendicontazione ( idFlusso, null, new PaginazioneDto(++pageNumber, pageSize) );
						
						pageableSpreadsheetGenerator.addData(flussoCompleto, true);
						
					}
					contatoreRigheFile = contatoreRigheFile + contatoreItemFlusso;
						
				}
				pageableSpreadsheetGenerator.flushData();
			}
			
			
						
		} catch (IOException e) {
			log.error("ERRORE NELLA CREAZIONE DEL FILE DEL REPORT FLUSSI RENDICONTAZIONE", e);
			throw new BusinessException("ERRORE NELLA CREAZIONE DEL FILE DEL REPORT  FLUSSI RENDICONTAZIONE", e);
			
		} catch (PersistenceException e) {
			log.error("ERRORE NELLA LETTURA DEI DATI DEL FLUSSO DI RENDICONTAZIONE", e);
			throw new BusinessException("ERRORE NELLA LETTURA DEI DATI DEL FLUSSO DI RENDICONTAZIONE", e);
			
		} finally {
			if (reportFileOutputStream != null) {
				try {
					reportFileOutputStream.close();
				} catch (IOException e) {
					log.error("ERRORE DURANTE LA CHIUSURA DELLO STREAM DI OUTPUT DEL FILE TEMPORANEO ", e);
				}
			}
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return reportsFilesNames; 
	}
	
	private List<Long> getIdFlussi(ReportFlussoRendicontazioneDto reportDto) throws PersistenceException{
		
		FlussoLightFilterDto filter = new FlussoLightFilterDto();
		
        filter.setTipoFlusso(TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE);
        filter.setIdEnte(reportDto.getIdEnte());
        filter.setDataInserimentoDa(reportDto.getDataRicezioneInizio());
        filter.setDataInserimentoA(reportDto.getDataRicezioneFine());
        filter.setCodiceDenominazioneMittente(reportDto.getCodiceDescrizionePSP());
        
		
		return gestioneFlussiDad.findIdFlussoByFilter(filter, reportDto.getCodiceFiscaleEnte());
	}
}
