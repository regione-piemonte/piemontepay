/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.ReportCreatorTypes;
import it.csi.epay.epaypaweb.business.interf.AccessoBusiness;
import it.csi.epay.epaypaweb.business.interf.ReportCreatorBusiness;
import it.csi.epay.epaypaweb.business.interf.TemplateBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapperReportEnti;
import it.csi.epay.epaypaweb.enumeration.*;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.interf.EpayPaCatalogSrvServiceMgr;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestionePagamentiDad;
import it.csi.epay.epaypaweb.util.Costanti;

import it.csi.epay.epaypaweb.util.spreadsheet.PageableSpreadsheetGenerator;
import it.csi.epay.epaypaweb.util.spreadsheet.PageableSpreadsheetGeneratorFactory;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@ReportCreatorTypes(TipoReportEnum.PAGAMENTI)
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionManagement(TransactionManagementType.BEAN)
public class PagamentiReportCreatorBusinessImpl extends EPaypaBusinessBase implements ReportCreatorBusiness <ReportPagamentiDto> {// extends ReportManagerBusinessImpl {

	static private final String CLASSNAME = PagamentiReportCreatorBusinessImpl.class.getSimpleName();
	
	private Properties config;
	
	@Inject
    private GestionePagamentiDad gestionePagamentiDad;
	
	@EJB
	protected AccessoBusiness accessoBusiness;
	
	@EJB
    private TemplateBusiness templateBusiness;
		
	@Resource
    protected UserTransaction transaction;
	

	@PostConstruct
    public void init() {
		loadProperties();
	}
	
	public List<String> generaReport(ReportPagamentiDto reportDto, int pageSize) throws BusinessException {
		
		String methodName = "generaReport";
		
		
		
		
		List<String> reportsNames = null;

		FileOutputStream reportFileOutputStream = null;
		TipoFlussoEnum tipoFlusso = null;
		String prefix = reportDto.getNominativoExport();
		String suffix = ".tmp";

		TipoFormatoFileEnum tipoFormatoFile = null;

		TipoFileReportEnum tipoFileReportEnum = TipoFileReportEnum.fromId(reportDto.getTipoFileReport().getCodice());
		switch (tipoFileReportEnum) {
		case CSV:
			suffix = ".csv";
			tipoFlusso = TipoFlussoEnum.REPORT_PAGAMENTI_ENTE_CSV;
			tipoFormatoFile = TipoFormatoFileEnum.CSV;
			break;
		case XLS:
			suffix = ".xlsx";
			tipoFlusso = TipoFlussoEnum.REPORT_PAGAMENTI_ENTE_XLS;
			tipoFormatoFile = TipoFormatoFileEnum.XLSX;
			break;
		}

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			int pageNumber = 0;
			Long totalSize = 1L;

			PagamentiFilterDto filter = getPagamentiFilterDto(reportDto);

			File reportFile = File.createTempFile(prefix, suffix);
			reportFile.deleteOnExit();
			reportFileOutputStream = new FileOutputStream(reportFile);

			TemplateMapperReportEnti reportEntiTemplate = new TemplateMapperReportEnti();
			TemplateDto template = templateBusiness.getTemplate ( reportDto.getIdEnte(), Costanti.ID_TEMPLATE_PAGAMENTI );

			FlussoCompletoDto<PagamentiExportDto> flussoCompleto = new FlussoCompletoDto<>();
			FlussoDto flusso = new FlussoDto();
			flusso.setTipoFlusso(tipoFlusso);
			flussoCompleto.setFlusso(flusso);

			PageableSpreadsheetGenerator<PagamentiExportDto> pageableSpreadsheetGenerator = PageableSpreadsheetGeneratorFactory
					.getPageableSpreadsheetGenerator(template, reportEntiTemplate, reportFileOutputStream,
							tipoFormatoFile, pageSize);

			while ((pageNumber * pageSize) < totalSize) {

				TotalSizeAndLightListDto<PagamentiExportDto> totalSizeAndLightList = readPagamentiExportDto(filter,
						new PaginazioneDto(++pageNumber, pageSize));
				totalSize = totalSizeAndLightList.getTotalSize();
				flussoCompleto.setItemList(totalSizeAndLightList.getLightList());

				pageableSpreadsheetGenerator.addData(flussoCompleto, true);
			}
			pageableSpreadsheetGenerator.flushData();
			
			reportsNames = new ArrayList<String>();
			reportsNames.add(reportFile.getAbsolutePath());

		} catch (IOException e) {
			log.error("ERRORE NELLA CREAZIONE DEL FILE DEL REPORT FLUSSI RENDICONTAZIONE", e);
			throw new BusinessException("ERRORE NELLA CREAZIONE DEL FILE DEL REPORT  FLUSSI RENDICONTAZIONE", e);
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

		return reportsNames;
	}

	private TotalSizeAndLightListDto<PagamentiExportDto> readPagamentiExportDto(PagamentiFilterDto filter,
			PaginazioneDto pag) throws BusinessException {
		String methodName = "readPagamentiExportDto";
		
		
		
		TotalSizeAndLightListDto<PagamentiExportDto> result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			transaction.begin();
			result = gestionePagamentiDad.esporta(filter, null, pag);
		} catch (Throwable t) {
			log.error("Errore imprevisto in " + CLASSNAME + "::" + methodName, t);
			throw new BusinessException("Errore imprevisto in " + CLASSNAME + "::" + methodName, t);

		} finally {
			try {
				transaction.rollback();
			} catch (Exception e) {
				log.error("errore nel rollback", e);
			}
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	private PagamentiFilterDto getPagamentiFilterDto(ReportPagamentiDto reportDto) throws BusinessException {
		PagamentiFilterDto filter = new PagamentiFilterDto();
		filter.setCodiceFiscale(reportDto.getCodiceFiscale());
		filter.setCognome(reportDto.getCognome());
		filter.setDataPagamentoFine(reportDto.getDataPagamentoFine());
		filter.setDataPagamentoInizio(reportDto.getDataPagamentoInizio());
		filter.setIdCodiceVersamento(reportDto.getIdCodiceVersamento());
		filter.setIuv(reportDto.getIuv());
		filter.setProfilazioneUtente(getProfilazioneUtenteDto(reportDto));
		filter.setCostiNotifica(reportDto.getCostiNotifica());

		TipoPagamentoEnum tipoPagamentoEnum = TipoPagamentoEnum.fromId(reportDto.getTipoPagamento());

		switch (tipoPagamentoEnum) {
		case TIPO_PAGAMENTO_TUTTI:
			filter.setPagamentiSpontanei(null);
			break;
		case TIPO_PAGAMENTO_SPONTANEI:
			filter.setPagamentiSpontanei(true);
			break;
		case TIPO_PAGAMENTO_DOVUTI:
			filter.setPagamentiSpontanei(false);
			break;
		default:
			filter.setPagamentiSpontanei(null);
		}

		return filter;
	}

	private ProfilazioneUtenteDto getProfilazioneUtenteDto (ReportPagamentiDto reportDto) throws BusinessException {
		String endPointCatalogoSrv = getEyapPaCatalogSrvEndpoint ();
		ProfilazioneEpayPaCatalogSrvDto profilazione = accessoBusiness.getProfilazione (
				endPointCatalogoSrv, 
                reportDto.getCodiceFiscaleUtente (),
                reportDto.getCodiceFiscaleEnte ()
                );
		
		if( null == profilazione ) {
			log.error( "PROFILAZIONE NON TROVATA PER codiceFiscaleEnte='" + reportDto.getCodiceFiscaleEnte() + "' codiceFiscaleUtente='" + reportDto.getCodiceFiscaleUtente() + "' ( endPoint='" + endPointCatalogoSrv +"')" );
			throw new BusinessException( "PROFILAZIONE NON TROVATA PER codiceFiscaleEnte='" + reportDto.getCodiceFiscaleEnte() + "' codiceFiscaleUtente='" + reportDto.getCodiceFiscaleUtente() + "' ( endPoint='" + endPointCatalogoSrv +"')" );
		}
		
        ProfilazioneUtenteDto output = new it.csi.epay.epaypaweb.dto.ProfilazioneUtenteDto ();

        output.setCategorieCdu ( profilazione.getCategorieCdu() );
        output.setCodiciVersamentoVisibili ( profilazione.getCodiciVersamentoVisibili() );
        output.setEnte ( profilazione.getEnte() );
        output.setListaCdu ( profilazione.getListaCdu() );
        output.setUtente ( profilazione.getUtente() );

        return output;
    }
	
	private String getEyapPaCatalogSrvEndpoint () {
        String key = EpayPaCatalogSrvServiceMgr.URL_PROPERTY;

        String endpoint = getStringProperty ( key );

        if ( StringUtils.isEmpty ( endpoint ) ) {
        	log.error( "PARAMETRO DI CONFIGURAZIONE NON DISPONIBILE PER KEY='" + key + "'" );
            throw new RuntimeException ( "PARAMETRO DI CONFIGURAZIONE NON DISPONIBILE PER KEY='" + key + "'" );
        }

        return endpoint;
    }
	
	private String getStringProperty(String key) {
		if(config==null) {
			config = new Properties();
			loadProperties();
		}
		return config.getProperty(key);
	}
	
	private void loadProperties() {
		config = new Properties();
		try {
			InputStream configInputStream = PagamentiReportCreatorBusinessImpl.class.getResourceAsStream("/WEB-INF/application-config.properties");
			config.load(configInputStream);

		} catch (IOException e) {
			log.error("EEORE DURANTE IL CARICAMENTO DEL FILE DI PROPERTIES: /WEB-INF/application-config.properties ", e);
			e.printStackTrace();
		}
	}
}
