/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import it.csi.epay.epaymodric.business.epaymodric.manager.AuditManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FileReportManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.PrenotazioneReportManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ReportCreatorManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ReportFlussiCreatorManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StatoReportManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDTipoFileReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDTipoReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTDatiFiltroReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFileReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;
import it.csi.epay.epaymodric.business.epaymodric.repository.DatiFiltroReportRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FileReportRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ReportRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoReportRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.TipoCampoFiltroRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.TipoFileReportRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.TipoReportRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.custom.FlussiOrigineRepositorySpec;
import it.csi.epay.epaymodric.business.epaymodric.utils.ConversionUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTODatiFiltroReport;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFileReport;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOTipoFileReport;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOTipoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.NomeFiltroReportEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoReportEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoCampoFiltroEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoReportEnum;

@Service
@Transactional
public class PrenotazioneReportManagerImpl implements PrenotazioneReportManager {

	private static final String MISSING_PARAM_TIPO_FILE = "tipo file";
	private static final String MISSING_PARAM_NOMINATIVO = "nominativo";
	private static final String MISSING_PARAM_ENTE = "ente";
	private static final String MISSING_PARAM_UTENTE = "utente";
	private static final String MISSING_PARAM_TIPO_REPORT = "tipo report";
	private static final String CLASSNAME = "prenotazioneReportManager";
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final AtomicBoolean running = new AtomicBoolean(false);
	@Autowired
	private EnteRepository enteRepository;
	@Autowired
	private StatoReportRepository statoReportRepository;
	@Autowired
	private TipoCampoFiltroRepository tipoCampoFiltroRepository;
	@Autowired
	private TipoFileReportRepository tipoFileReportRepository;
	@Autowired
	private TipoReportRepository tipoReportRepository;
	@Autowired
	private ReportRepository reportRepository; 
	@Autowired
	private DatiFiltroReportRepository datiFiltroReportRepository; 
	@Autowired
	private ErroreRepository erroreRepository; 
	@Autowired
	private FileReportRepository fileReportRepository;
	@Autowired
	private ReportCreatorManager reportContabileCreator;
	@Autowired
	private ReportFlussiCreatorManager reportFlussiCompletiRiconciliatiCreator;
	@Autowired
	private StatoReportManager statoReportManager;
	@Autowired
	private FileReportManager fileReportManager;
	@Autowired
    private FlussiOrigineRepositorySpec flussiOrigineRepositorySpec;
	@Autowired
	private ConfigurazioneManager configurazioneManager;
	@Autowired
	private AuditManager auditManager;
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public DTOOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport ( DTOInputWsInserisciPrenotazioneReport input ) {
		logger.info ( "DTOOutputWsInserisciPrenotazioneReport.inserisciPrenotazioneReport: INIZIO" );

		DTOOutputWsInserisciPrenotazioneReport esito = new DTOOutputWsInserisciPrenotazioneReport ();
//		if (true )
//		{
//			return buildErrorParametroMancante(CostantiErrori.WS_ESITO_KO_IS_EMPTY,"");
//		}

		if ( StringUtils.isEmpty ( input.getIdUtente () ) || StringUtils.isEmpty ( input.getCodiceFiscaleUtente () ) ) {
			return buildErrorParametroMancante(CostantiErrori.WS_ESITO_KO_PARAM_MISSING,MISSING_PARAM_UTENTE);

		}

		if ( StringUtils.isEmpty ( input.getCodiceFiscaleEnte () ) ) {
			return buildErrorParametroMancante(CostantiErrori.WS_ESITO_KO_PARAM_MISSING, MISSING_PARAM_ENTE);
		}

		if ( StringUtils.isEmpty ( input.getNominativoExport () ) ) {
			return buildErrorParametroMancante(CostantiErrori.WS_ESITO_KO_PARAM_MISSING,MISSING_PARAM_NOMINATIVO);
		}

		if ( StringUtils.isEmpty ( input.getIdTipoFile () ) ) {
			return buildErrorParametroMancante(CostantiErrori.WS_ESITO_KO_PARAM_MISSING,MISSING_PARAM_TIPO_FILE);
		}
		
		if ( StringUtils.isEmpty ( input.getIdTipoReport() ) ) {
			return buildErrorParametroMancante(CostantiErrori.WS_ESITO_KO_PARAM_MISSING,MISSING_PARAM_TIPO_REPORT);
		}

		if ( StringUtils.isEmpty ( input.getIdentificativoFlusso () )
				&& StringUtils.isEmpty ( input.getIuv () )
				&& StringUtils.isEmpty ( input.getIdStatoFlusso () )
				&& StringUtils.isEmpty ( input.getIdCodiceVersamento () )
				&& StringUtils.isEmpty ( input.getPsp () )
				&& null == input.getDataElaborazioneDa ()
				&& null == input.getDataElaborazioneA ()
				&& null == input.getDataRicezioneDa ()
				&& null == input.getDataRicezioneA () ) {
			
			
			return buildErrorParametroMancante(CostantiErrori.WS_ESITO_KO_IS_EMPTY,"");
		}

		// inserire qui la ricerca per verificare il numero massimo di record 
		CblTReport cblTReport = new CblTReport ();
		cblTReport.setIdUtente ( new Long ( input.getIdUtente () ) );
		cblTReport.setCodiceFiscaleUtente ( input.getCodiceFiscaleUtente () );
		cblTReport.setCodiceFiscaleEnte ( input.getCodiceFiscaleEnte () );
		cblTReport.setCblTEnte ( enteRepository.findByCodiceFiscale ( input.getCodiceFiscaleEnte () ) );
		//Nomninativo export
		cblTReport.setNominativoExport ( input.getNominativoExport () );
		//stato report 
		cblTReport.setCblDStatoReport ( statoReportRepository.findById ( StatoReportEnum.INSERTED.getId () ) );
		//id file null 
		// tipo file 
		cblTReport.setCblDTipoFileReport ( tipoFileReportRepository.findById ( Integer.valueOf ( input.getIdTipoFile () ) ) );
		// tipo  report non obbligatorio
		if ( !StringUtils.isEmpty ( input.getIdTipoReport () ) ) {
			cblTReport.setCblDTipoReport ( tipoReportRepository.findByCodice ( input.getIdTipoReport () ) );
		}

		cblTReport.setDataOraPrenotazione(new Timestamp (System.currentTimeMillis()));

		List<DTODatiFiltroReport> datiFiltroReportList = buildDatiFiltroReportDto ( input );
		
		

		cblTReport = reportRepository.save ( cblTReport );

		if ( !CollectionUtils.isEmpty ( datiFiltroReportList ) ) {
			for ( DTODatiFiltroReport dfr: datiFiltroReportList ) {
				datiFiltroReportRepository.save (
						CblTDatiFiltroReport.builder ()
						.withId ( dfr.getId () )
						.withNomeFiltro ( dfr.getNomeFiltro ().getCode () )
						.withValore ( dfr.getValore () )
						.withCblDTipoCampoFiltro ( tipoCampoFiltroRepository.findByCodice ( ( dfr.getTipoCampoFiltro ().getCode () ) ) )
						.withCblTReport ( cblTReport )
						.build () );
			}
		}

		esito.setCodiceErrore ( "" );
		esito.setEsito ( CostantiErrori.RETURN_CODE_OK );
		return esito;
	}

	private DTOOutputWsInserisciPrenotazioneReport buildErrorParametroMancante(String codErore, String parametroMancante) {
		DTOOutputWsInserisciPrenotazioneReport esito = new DTOOutputWsInserisciPrenotazioneReport();
		CblDErrore errore= erroreRepository.findByCodiceErrore(codErore);
		esito.setCodiceErrore (errore.getCodiceErrore());
		esito.setEsito("KO");
		esito.setDescrizione (String.format (errore.getDescrizioneErrore(), parametroMancante));
		return esito;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DTOOutputWsRicercaPrenotazioneReport> ricercaReport (DTOInputWsRicercaPrenotazioneReport input) {

		List<CblTReport> listaReport = reportRepository.findAllByCodiceFiscaleUtenteAndCodiceFiscaleEnteAndCblDTipoReportId (
				input.getCaller ().getPrincipal ().getCodiceFiscale (), input.getCaller ().getPrincipal ().getCodiceEnte (),
				tipoReportRepository.findByCodice ( TipoReportEnum.FLUSSI_COMPLETI_RICONCILIATI.getCode () ).getId () );
		return listaReport.stream()
				.map(new Function<CblTReport, DTOOutputWsRicercaPrenotazioneReport> () {

					@Override
					public DTOOutputWsRicercaPrenotazioneReport apply ( CblTReport filteredReport ) {
						DTOOutputWsRicercaPrenotazioneReport tmpDto = new DTOOutputWsRicercaPrenotazioneReport();

						if(filteredReport.getCblDStatoReport() != null) {
							DTOStatoReport tmpStatoReport = DTOStatoReport.builder()
									.withId(filteredReport.getCblDStatoReport().getId())
									.withCodice(filteredReport.getCblDStatoReport().getCodice())
									.withDescrizione(filteredReport.getCblDStatoReport().getDescrizione())
									.build();
							tmpDto.setStatoReport(tmpStatoReport);
						}

						if(filteredReport.getCblDTipoFileReport() != null) {
							DTOTipoFileReport tmpTipoFileReport = DTOTipoFileReport.builder()
									.withId(filteredReport.getCblDTipoFileReport().getId())
									.withCodice(filteredReport.getCblDTipoFileReport().getCodice())
									.withDescrizione(filteredReport.getCblDTipoFileReport().getDescrizione())
									.build();
							tmpDto.setTipoFileReport(tmpTipoFileReport);
						}

						if(filteredReport.getCblDTipoReport() != null) {
							DTOTipoReport tmpTipoReport = new DTOTipoReport();
							tmpTipoReport.setId(filteredReport.getCblDTipoReport().getId());
							tmpTipoReport.setCodice(filteredReport.getCblDTipoReport().getCodice());
							tmpTipoReport.setDescrizione(filteredReport.getCblDTipoReport().getDescrizione());
							tmpDto.setTipoReport(tmpTipoReport);
						}

						if(filteredReport.getCblTFileReport() != null) {
							DTOFileReport tmpFileReport = new DTOFileReport();
							tmpFileReport.setId(filteredReport.getCblTFileReport().getId());
							tmpFileReport.setNomeFile(filteredReport.getCblTFileReport().getNomeFile());
							tmpFileReport.setDataInserimento(filteredReport.getCblTFileReport().getDataInserimento());
							tmpFileReport.setDataModifica(filteredReport.getCblTFileReport().getDataModifica());
							tmpFileReport.setDataInizioElaborazione(filteredReport.getCblTFileReport().getDataInizioElaborazione());
							tmpDto.setFileReport(tmpFileReport);
						}

						tmpDto.setCodiceFiscaleEnte(filteredReport.getCodiceFiscaleEnte());
						tmpDto.setCodiceFiscaleUtente(filteredReport.getCodiceFiscaleUtente());
						tmpDto.setIdUtente(filteredReport.getIdUtente());

						tmpDto.setId(filteredReport.getId());
						tmpDto.setNominativoExport(filteredReport.getNominativoExport());
						tmpDto.setDataOraPrenotazioneReport(filteredReport.getDataOraPrenotazione());
						return tmpDto;
					}
				})
				.collect(Collectors.toCollection ( new Supplier<LinkedList<DTOOutputWsRicercaPrenotazioneReport>> () {

					@Override
					public LinkedList<DTOOutputWsRicercaPrenotazioneReport> get () {
						return new LinkedList<> ();
					}
				} ));
	}

	private List<DTODatiFiltroReport> buildDatiFiltroReportDto(DTOInputWsInserisciPrenotazioneReport input) {
		List<DTODatiFiltroReport> datiFiltroReportDtoList = new LinkedList<DTODatiFiltroReport>();


		//IDENTIFICATIVO_FLUSSO
		if( !StringUtils.isEmpty(input.getIdentificativoFlusso()) ) {
			//        	if(null!= input.getIdentificativoFlusso() && !"".equals(input.getIdentificativoFlusso())) {

			//    		DTODatiFiltroReport filtro= new DTODatiFiltroReport();

			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					//        								.withNomeFiltro( NomeFiltroReportEnum.IDENTIFICATIVO_FLUSSO ) 
					.withNomeFiltro( NomeFiltroReportEnum.CAUSALE_PROVVISORIO ) // da indicazioni di Flavio Bovino 19/10/2020
					.withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
					.withValore( input.getIdentificativoFlusso() )
					.build());
		}

		//IUV
		if( !StringUtils.isEmpty(input.getIuv()) ) {

			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.IUV ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
					.withValore( input.getIuv() )
					.build());
		}

		//ID_CODICE_VERSAMENTO
		if( !StringUtils.isEmpty(input.getIdCodiceVersamento()) ) {

			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.ID_CODICE_VERSAMENTO ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.INTEGER )
					.withValore(  input.getIdCodiceVersamento()  )
					.build());
		}

		//ID_STATO_FLUSSO
		if( !StringUtils.isEmpty(input.getIdStatoFlusso()) ) {

			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.ID_STATO_FLUSSO ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.INTEGER )
					.withValore( input.getIdStatoFlusso()  )
					.build());
		}

		//DATA_ELABORAZIONE_DA
		if( null!= input.getDataElaborazioneDa()) {


			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.DATA_ELABORAZIONE_DA ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
					.withValore(ConversionUtils.convertXmlGregorianCalendarToString(input.getDataElaborazioneDa()) ) // PROVVISORIO!!! 
					.build());
		}
		//        	
		//        	//DATA_ELABORAZIONE_A
		if( null!= input.getDataElaborazioneA()) {


			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.DATA_ELABORAZIONE_A ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
					.withValore(ConversionUtils.convertXmlGregorianCalendarToString(input.getDataElaborazioneA()) )// PROVVISORIO!!! 
					.build());
		}
		//        	
		//        	//DATA_RICEZIONE_DA
		if( null!= input.getDataRicezioneDa()) {


			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.DATA_RICEZIONE_DA ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
					.withValore(ConversionUtils.convertXmlGregorianCalendarToString(input.getDataRicezioneDa()) )// PROVVISORIO!!! 


					.build());
		}
		//        	
		//        	//DATA_RICEZIONE_A
		if( null!= input.getDataRicezioneA()) {


			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.DATA_RICEZIONE_A ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
					.withValore(ConversionUtils.convertXmlGregorianCalendarToString(input.getDataRicezioneA()) )// PROVVISORIO!!! 
					.build());
		}

		//CODICE_DESCRIZIONE_PSP
		if( !StringUtils.isEmpty(input.getPsp()) ) {


			datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
					.withNomeFiltro( NomeFiltroReportEnum.CODICE_DESCRIZIONE_PSP ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
					.withValore( input.getPsp() )
					.build());
		}


		return datiFiltroReportDtoList;
	}

    @Override
    public DTOOutputWsAggiornaStatoReport aggiornaStatoReport ( DTOInputWsAggiornaStatoReport input ) {

        DTOOutputWsAggiornaStatoReport output = new DTOOutputWsAggiornaStatoReport ();

        Assert.notNull ( input, "L'input e' obbligatorio" );
        Assert.notNull ( input.getIdExport (), "L'id del report e' obbligatorio" );
        reportRepository.updateStatoReportById ( statoReportRepository.findByCodice ( StatoReportEnum.DOWNLOADED.getCode () ), input.getIdExport () );
        CblTReport report = reportRepository.findOne ( input.getIdExport () );
        List<CblTDatiFiltroReport> datiFilto= report.getCblTDatiFiltroReports();
        Assert.notNull ( report.getCblTFileReport (), "File non ancora generato" );
        Assert.notNull ( report.getCblTFileReport ().getId (), "File non ancora generato" );
        List<String> target= new ArrayList<String>();
        target.add("ID REPORT: "+input.getIdExport ());
        target.add("ID FILE: "+report.getCblTFileReport ().getId ());
        for (CblTDatiFiltroReport datoFiltro :datiFilto)
        {
        	target.add(datoFiltro.getNomeFiltro()+": "+ datoFiltro.getValore());
        }
        auditManager.preExport("(Download file) cbl_t_file_report",target);
        
        CblTFileReport file = fileReportRepository.findById ( report.getCblTFileReport ().getId () );
        DTOFileReport fileDto = new DTOFileReport ();
        fileDto.setId ( file.getId () );
        fileDto.setNomeFile ( file.getNomeFile () );
        fileDto.setContenuto ( file.getContenutoFile () );
        fileDto.setDataInserimento ( file.getDataInserimento () );
        fileDto.setDataModifica ( file.getDataModifica () );
        fileDto.setDataInizioElaborazione ( file.getDataInizioElaborazione () );
        output.setFile ( fileDto );
        output.setEsito ( "SUCCESS" );
        return output;
    }
	
	public void cancellazioneReport(List<String> reportStatusCodes, int thresholdDays) {
		String methodName = "cancellazioneReport";
		if (running.compareAndSet(false, true)) {
	        try {
	        	
	        	List<CblTReport> reportDacancellare = readReportDacancellare(reportStatusCodes, thresholdDays);
				if(null != reportDacancellare && !CollectionUtils.isEmpty (reportDacancellare)) {
					for(CblTReport reportDto:reportDacancellare) {
						
						try {
							logger.info("REPORT ID=" + reportDto.getId() +  " INIZIO CANCELLAZIONE...");
							cancellaReport(reportDto);
							logger.info("REPORT ID=" + reportDto.getId() +  " CANCELLATA CORRETTAMENTE");
						} catch (Throwable t) {
							logger.error("ERRORE IMPREVISTO DURANTE LA CANCELLAZIONE DEL REPORT ID=" + reportDto.getId(), t);
						}
					}
				}else {
					logger.info("Non sono presenti report da cancellare");
				}
	        } catch (Exception e) {
	        	logger.error("Errore imprevisto durante la creazione dei reports " + CLASSNAME + "::" + methodName);
	        	logger.error("Errore: " + e.getMessage());
			}finally {
				running.set(false);
			}
			
		}else {
			logger.warn("Il metodo " + CLASSNAME +"::" + methodName + "  ancora in esecuzione ");
		}
	}
	
	@Transactional
	public List<CblTReport> readReportDacancellare(List<String> reportStatusCodes, int thresholdDays){
		String methodName = "readReportDacancellare";
        
        List<CblTReport> result = null;
        
        try {
        	Date thresholdDate = new Date(System.currentTimeMillis() - (long)thresholdDays*1000*60*60*24 );
        	
        	result = reportRepository.findByCblDStatoReportCodiceAndCblTFileReportDataModificaBefore(reportStatusCodes, thresholdDate);
        	
        } catch (Exception e ) {
        	logger.error("Errore imprevisto in " + CLASSNAME + "::" + methodName);
        	logger.error("Errore: " + e.getMessage());
            

        }
        
        return result;
	}
	
	@Transactional
	public void cancellaReport(CblTReport report){
		String methodName = "cancellaReport";

       try {
           
        	reportRepository.delete(report);
        	CblTFileReport fileDaCancellare = fileReportRepository.findById(report.getCblTFileReport().getId());
        	fileReportRepository.delete(fileDaCancellare);
        	
       } catch ( Exception e ) {
    	   
    	   	logger.error("Errore imprevisto in " + CLASSNAME + "::" + methodName);
    	   	logger.error("Errore: " + e.getMessage());
       }        
	}

	@Override
	public boolean isRunning() {
		return running.get();
	}

	@Override
    public void crateReport ( int maxReportNumber, int recordsPageSize ) {
        String methodName = "crateReport";
        
        if ( running.compareAndSet ( false, true ) ) {
        	
            try {
                List<CblTReport> reportDaGenerare = readReportDaElaborare ( statoReportRepository.findByCodice(StatoReportEnum.INSERTED.getCode()), maxReportNumber );
                if ( CollectionUtils.isEmpty ( reportDaGenerare ) ) {
                    reportDaGenerare = readReportDaElaborare ( statoReportRepository.findByCodice(StatoReportEnum.EMPTY.getCode()), maxReportNumber );
                }
                if ( CollectionUtils.isEmpty ( reportDaGenerare ) ) {
                    reportDaGenerare = readReportDaElaborare ( statoReportRepository.findByCodice(StatoReportEnum.FAILED.getCode()), maxReportNumber );
                }

                if ( !CollectionUtils.isEmpty ( reportDaGenerare ) ) {
                    for ( CblTReport reportDto: reportDaGenerare ) {
                        logger.info ( "START elaborazione report id=" + reportDto.getId () + " stato='" + reportDto.getCblDStatoReport().getCodice () + "'" );

                        elaboraSingoloReport(reportDto, recordsPageSize);

                        logger.info ( "END elaborazione repord id=" + reportDto.getId () + " stato='" + reportDto.getCblDStatoReport().getCodice () + "'" );
                    }
                }
            } catch ( Exception e ) {
                logger.error ("Errore imprevisto durante la creazione dei reports " + CLASSNAME + "::" + methodName);
                logger.error (e.getMessage());
            } finally {
                running.set ( false );
            }

        } else {
            logger.warn ( "Il metodo " + CLASSNAME + "::" + methodName + " ancora in esecuzione " );
        }
    }

	private List<CblTReport> readReportDaElaborare(CblDStatoReport statoReport, int maxReportNumber){
		String methodName = "readReportDaElaborare";
		
        List<CblTReport> result = null;
        
        try {
        	result = reportRepository.findByCblDStatoReport(statoReport, new PageRequest(0, maxReportNumber));
        } catch ( Exception e ) {
            logger.error ("Errore imprevisto in " + CLASSNAME + "::" + methodName);
            logger.error(e.getMessage());
        }
        
		return result;
	}
	
	private void elaboraSingoloReport(CblTReport reportDto, int recordsPageSize) {
		String methodName = "elaboraSingoloReport";        
        List<String> tmpReportsFilesNames = null; 
        CblDStatoReport stato = new CblDStatoReport();
        try {
			logger.info("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL A WIP...");
			
			stato.setId(StatoReportEnum.WIP.getId());
			stato.setCodice(StatoReportEnum.WIP.getCode());
			stato.setDescrizione("Richiesta creazione report in lavorazione");
			statoReportManager.updateStatoReport(stato, reportDto.getId());
			logger.info("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A WIP CORRETTAMENTE");
			
			logger.info("REPORT ID=" + reportDto.getId() +  " INIZIO GENERAZIONE DEL FILE...");
			CblTFileReport fileReportDto = new CblTFileReport();
			fileReportDto.setDataInizioElaborazione ( new Date () );
			DTOInputWsRicercaFlussoOrigine flussoInput = new DTOInputWsRicercaFlussoOrigine();
			// inizializzazione parametri paginazione.
			flussoInput.setLength ( recordsPageSize );
			flussoInput.setStart ( 0 );
            for ( CblTDatiFiltroReport datoFiltro: reportDto.getCblTDatiFiltroReports () ) {
                switch ( NomeFiltroReportEnum.valueOf ( datoFiltro.getNomeFiltro () ) ) {
                case IUV :
                    flussoInput.setIuv ( datoFiltro.getValore () );
                    break;
                case ID_CODICE_VERSAMENTO :
                    flussoInput.setIdCodVersamento ( datoFiltro.getValore () );
                    break;
                case IDENTIFICATIVO_FLUSSO :
                    flussoInput.setIdentificativoFlusso ( datoFiltro.getValore () );
                    break;
                case CAUSALE_PROVVISORIO :
                    flussoInput.setIdentificativoFlusso ( datoFiltro.getValore () );
                    break;
                case ID_STATO_FLUSSO :
                    flussoInput.setIdStatoFlusso ( datoFiltro.getValore () );
                    break;
                case DATA_ELABORAZIONE_A :
                    flussoInput.setDataElaborazioneA ( ConversionUtils.convertStringToXmlGregorianCalendar ( datoFiltro.getValore () ) );
                    break;
                case DATA_ELABORAZIONE_DA :
                    flussoInput.setDataElaborazioneDa ( ConversionUtils.convertStringToXmlGregorianCalendar ( datoFiltro.getValore () ) );
                    break;
                case DATA_RICEZIONE_A :
                    flussoInput.setDataRicezioneA ( ConversionUtils.convertStringToXmlGregorianCalendar ( datoFiltro.getValore () ) );
                    break;
                case DATA_RICEZIONE_DA :
                    flussoInput.setDataRicezioneDa ( ConversionUtils.convertStringToXmlGregorianCalendar ( datoFiltro.getValore () ) );
                    break;
                case CODICE_DESCRIZIONE_PSP :
                    flussoInput.setPsp ( datoFiltro.getValore () );
                    break;
                }
            }
			flussoInput.setIdentificativoIstitutoRicevente(reportDto.getCodiceFiscaleEnte()/*flussoInput.getCaller().getPrincipal().getCodiceEnte()*/);
			
			
			int numeroMassimoFlussi = Integer.parseInt ( configurazioneManager.ricercaLimiteElaborazioneReport().getNumMaxRecordsReport ());
			Integer numeroFlussi = flussiOrigineRepositorySpec.contaFlussiPerFiltro ( flussoInput );
	        if (numeroMassimoFlussi > 0 && numeroFlussi.compareTo ( numeroMassimoFlussi )  > 0)
	        {
	        	 tmpReportsFilesNames = Collections.emptyList ();
	                logger.error ( "REPORT ID=" + reportDto.getId () + " IMPOSTO LO STATO DEL A OVERFULL..." );
	                CblDStatoReport statoOverfull = new CblDStatoReport ();
	                statoOverfull.setId ( StatoReportEnum.OVERFULL.getId () );
	                statoOverfull.setCodice ( StatoReportEnum.OVERFULL.getCode () );
	                statoOverfull.setDescrizione ( "Report con troppi flussi. Affinare la ricerca" );
	                statoReportManager.updateStatoReport ( statoOverfull, reportDto.getId () );
	                logger.info ( "REPORT ID=" + reportDto.getId () + " IMPOSTATO LO STATO A OVERFULL CORRETTAMENTE" );
	                return;
	        }
	        
			
            tmpReportsFilesNames = generaReport(flussoInput, reportDto, recordsPageSize);
            if ( CollectionUtils.isEmpty ( tmpReportsFilesNames ) ) {
                tmpReportsFilesNames = Collections.emptyList ();
                logger.error ( "REPORT ID=" + reportDto.getId () + " IMPOSTO LO STATO DEL A EMPTY..." );
                CblDStatoReport statoEmpty = new CblDStatoReport ();
                statoEmpty.setId ( StatoReportEnum.EMPTY.getId () );
                statoEmpty.setCodice ( StatoReportEnum.EMPTY.getCode () );
                statoEmpty.setDescrizione ( "Report privo di flussi" );
                statoReportManager.updateStatoReport ( statoEmpty, reportDto.getId () );
                logger.info ( "REPORT ID=" + reportDto.getId () + " IMPOSTATO LO STATO A EMPTY CORRETTAMENTE" );
                return;
            }
			logger.info("REPORT ID=" + reportDto.getId() +  " FINE GENERAZIONE DEI FILE '" + tmpReportsFilesNames + "'");
			fileReportDto.setDataInserimento ( new Date() );
			logger.info("REPORT ID=" + reportDto.getId() +  " INIZIO CREAZIONE ARRAY DI BYTE DEI FILE COMPRESSI '" + tmpReportsFilesNames + "'....");
			byte[] compressedReportBytes = compressReportsFiles(tmpReportsFilesNames);
			logger.info("REPORT ID=" + reportDto.getId() +  " FINE CREAZIONE ARRAY DI BYTE DEI FILE COMPRESSI '" + tmpReportsFilesNames + "'");
			
			
			fileReportDto.setContenutoFile(compressedReportBytes);
			fileReportDto.setNomeFile(reportDto.getNominativoExport() + ".zip");
			
			
			logger.info("REPORT ID=" + reportDto.getId() +  " SALVATAGGIO SU DB ARRAY DI BYTE DEI FILE COMPRESSO ...");
			fileReportDto.setDataModifica ( new Date());
			fileReportManager.salvaFileReport(fileReportDto, reportDto);
			logger.info("REPORT ID=" + reportDto.getId() +  " SALVATAGGIO SU DB ARRAY DI BYTE DEI FILE COMPRESSO TERMINATO");
			
			
			logger.info("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL REPORT A COMPLETED...");
			CblDStatoReport statoCompleted = new CblDStatoReport();
			statoCompleted.setId(StatoReportEnum.COMPLETED.getId());
			statoCompleted.setCodice(StatoReportEnum.COMPLETED.getCode());
			statoCompleted.setDescrizione("Richiesta creazione report completata con successo");
			statoReportManager.updateStatoReport(statoCompleted, reportDto.getId());
			logger.info("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A COMPLETED CORRETTAMENTE");
			
        }catch ( Exception e ) {
            logger.error ( "Errore imprevisto durante la generazione del report (id=" + reportDto.getId() + ") in " + CLASSNAME + "::" + methodName, e);
         
            try {
            	logger.error("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL A FAILED...");
            	CblDStatoReport statoFailed = new CblDStatoReport();
            	statoFailed.setId(StatoReportEnum.FAILED.getId());
            	statoFailed.setCodice(StatoReportEnum.FAILED.getCode());
            	statoFailed.setDescrizione("Elaborazione report fallita");
            	statoReportManager.updateStatoReport(statoFailed, reportDto.getId());
    			logger.error("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A FAILED CORRETTAMENTE");
            }catch ( Exception ex ) {
            	logger.error( "Errore imprevisto durante il cambio di stato del report (id=" + reportDto.getId() + ") in " + CLASSNAME + "::" + methodName, ex);
            	logger.error(e.getMessage());
            }
        }finally {
        	if( !CollectionUtils.isEmpty ( tmpReportsFilesNames)) {
        		deleteTmpReportsFiles(tmpReportsFilesNames);
        	}
        }
	}
	

    @Override
    public DTOOutputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare ( DTOInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare ) {
    	DTOOutputWsRicercaFlussiDaEsportare ret = new DTOOutputWsRicercaFlussiDaEsportare ();
    	// limite piu' basso 500
        int numeroMassimoFlussiDaEsportare = Integer.parseInt ( configurazioneManager.ricercaLimiteEsportazione ().getNumMaxRecordsReport ());
        int numeroMassimoRecordDaElaborare = 0;
        // limite piu' alto 30000
        DTOOutputWsRicercaLimiteElaborazioneReport limiteReport= configurazioneManager.ricercaLimiteElaborazioneReport();
        if (null != limiteReport && !StringUtils.isEmpty(limiteReport.getNumMaxRecordsReport()) )
        {
        	numeroMassimoRecordDaElaborare = Integer.parseInt (limiteReport.getNumMaxRecordsReport() );
        }
        Integer totaleDB = flussiOrigineRepositorySpec.contaFlussiPerFiltro ( ricercaFlussiDaEsportare );
        
        ricercaFlussiDaEsportare.setStart ( 0 );
        ricercaFlussiDaEsportare.setLength ( numeroMassimoFlussiDaEsportare );
        Assert.notNull ( totaleDB, "Nessun flusso trovato" );
        Assert.isTrue ( totaleDB > 0, "Nessun flusso trovato" );

        
        if (numeroMassimoRecordDaElaborare > 0 && totaleDB.compareTo ( numeroMassimoRecordDaElaborare ) > 0)
        {
        	ret.setEsito(esitoErrore(numeroMassimoRecordDaElaborare, Costanti.ATTRIBUTO_CONF_NUM_MAX_RECORDS_REPORT, 
        			"Numero di record massimo (" + numeroMassimoRecordDaElaborare + ") per la creazione del report superato."));
        	return ret;
        }
        
        if (totaleDB.compareTo ( numeroMassimoFlussiDaEsportare ) > 0 )
        {
        	
        	ret.setEsito( esitoErrore(numeroMassimoFlussiDaEsportare,  Costanti.ATTRIBUTO_CONF_NUM_MAX_RECORDS_EXPORT, 
        			"Numero di record massimo (" + numeroMassimoFlussiDaEsportare + ") elaborabili online superato."));
        	return ret;
        }
        
        CblTReport reportDto = new CblTReport ();
        reportDto.setCblDTipoReport ( new CblDTipoReport () );
        reportDto.getCblDTipoReport ().setCodice ( TipoReportEnum.FLUSSI_COMPLETI_RICONCILIATI.getCode () );
        reportDto.setCblDTipoFileReport ( new CblDTipoFileReport () );
        reportDto.getCblDTipoFileReport ().setCodice ( ricercaFlussiDaEsportare.getTipoFileReport ().getCodice () );
        reportDto.setNominativoExport ( "esportazione_flussi" );
        byte [] compressedReportBytes = null;
        try {
        	
        	auditManager.preExport("(Download file) cbl_t_flusso_origine", getFilterForAudit(ricercaFlussiDaEsportare));
            compressedReportBytes = compressReportsFiles ( generaReport ( ricercaFlussiDaEsportare, reportDto, numeroMassimoFlussiDaEsportare ) );
        } catch ( IOException e ) {
            throw new RuntimeException ( "Errore durante la generazione del report." );
        }
        ret.setEsito ( new DTOOutputWsEsito() );
        ret.getEsito ().setEsito ( DTOOutputWsEsito.ESITO_OK_DEFAULT  );
        ret.setContenuto ( compressedReportBytes );
        ret.setNomeFile ( "esportazione_flussi.zip" );
        return ret;
    }

	private DTOOutputWsEsito esitoErrore(int numeroMassimoFlussi,String codErrore, String desc ) {
		logger.info(desc);
		DTOOutputWsEsito esito= new DTOOutputWsEsito();
		esito.setEsito( DTOOutputWsEsito.ESITO_KO_DEFAULT );
		esito.setCodiceErrore ( codErrore );
		return esito;
		
//		 DTOOutputWsRicercaFlussiDaEsportare ret = new DTOOutputWsRicercaFlussiDaEsportare ();
//		 ret.setEsito ( new DTOOutputWsEsito() );
//		 ret.getEsito ().setEsito ( DTOOutputWsEsito.ESITO_KO_DEFAULT );
//		 ret.getEsito ().setCodiceErrore ( codErrore );
//		 return ret;
	}
	
	private List<String> generaReport(DTOInputWsRicercaFlussoOrigine flussoInput, CblTReport reportDto, int recordsPageSize){
		
		switch ( TipoReportEnum.fromId( reportDto.getCblDTipoReport().getCodice()) ) {
//		case CONTABILE:
//			return reportContabileCreator.generaReport( flussoInput, reportDto, recordsPageSize );
		case FLUSSI_COMPLETI_RICONCILIATI:
			return reportFlussiCompletiRiconciliatiCreator.generaReport( flussoInput, reportDto ,recordsPageSize );
			default:
				throw new IllegalArgumentException("TIPO DI REPORT NON DEFINITO");
			
		}
	}
	
	private byte[] compressReportsFiles(List<String> tmpReportsFilesNames) throws IOException{
		String methodName = "compressReportsFiles";
		byte[] result = null;
		ByteArrayOutputStream bos = null;
		ZipOutputStream zipOut = null;
		try {
        	bos = new ByteArrayOutputStream();
    		zipOut = new ZipOutputStream(bos);
    		
			for (String srcFile : tmpReportsFilesNames) {
				File fileToZip = null;
				FileInputStream fis = null;
				try {

					fileToZip = new File(srcFile);
					fis = new FileInputStream(fileToZip);
					
					ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
					zipOut.putNextEntry(zipEntry);

					byte[] bytes = new byte[1024];
					int length;
					while ((length = fis.read(bytes)) >= 0) {
						zipOut.write(bytes, 0, length);
					}
				} finally {
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							logger.error("Errore imprevisto durante la chiusura dello stream di input relativo al file '"
									+ srcFile + "  in " + CLASSNAME + "::" + methodName, e);
						}
					}
				}
			}
		}finally {
			if (zipOut != null) {
				try {
					zipOut.close();
				} catch (IOException e) {
					logger.error("Errore imprevisto durante la chiusura dello stream zip di output in " + CLASSNAME + "::"
							+ methodName);
					logger.error(e.getMessage());
				}
			}

			if (bos != null) {
				result = bos.toByteArray();
				bos.close();
			}
		}

		return result;
	}
	
	private void deleteTmpReportsFiles(List<String> tmpReportsFilesNames) {
		for ( String fileName : tmpReportsFilesNames ) {
			File reportFile = new File(fileName); 
		    if (reportFile.delete()) { 
		    	logger.info("Cancellazione del file temporaneo '" + fileName + "' terminata con successo");
		    } else {
		      logger.error("Cancellazione del file temporaneo '" + fileName + "' NON eseguita");
		    }
		}
	}
	
	public List<String> getFilterForAudit( DTOInputWsRicercaFlussoOrigine filter)
	{
		List<String> result= new ArrayList<String>();
		if (!StringUtils.isEmpty(filter.getIdentificativoFlusso()))
		{
			result.add("Identificativo flusso: "+filter.getIdentificativoFlusso());
		}
		if (!StringUtils.isEmpty(filter.getIdCodVersamento()))
		{
			result.add("Codice versamento: "+filter.getIdCodVersamento());
		}
		if (!StringUtils.isEmpty(filter.getIuv()))
		{
			result.add("Iuv: "+filter.getIuv());
		}
		if (!StringUtils.isEmpty(filter.getIdStatoFlusso()))
		{
			result.add("Id stato flusso: "+filter.getIdStatoFlusso());
		}
		if (null !=(filter.getDataElaborazioneDa()))
		{
			result.add("Data elaborazione da: "+DateUtils.xmlGregorianCalendarToString(filter.getDataElaborazioneDa()));
		}
		if (null !=(filter.getDataElaborazioneA()))
		{
			result.add("Data elaborazione a: "+DateUtils.xmlGregorianCalendarToString(filter.getDataElaborazioneA()));
		}
		if (null !=(filter.getDataRicezioneDa()))
		{
			result.add("Data ricezione da: "+DateUtils.xmlGregorianCalendarToString(filter.getDataRicezioneDa()));
		}
		if (null !=(filter.getDataRicezioneA()))
		{
			result.add("Data ricezione a: "+DateUtils.xmlGregorianCalendarToString(filter.getDataRicezioneA()));
		}
		if (!StringUtils.isEmpty(filter.getPsp()))
		{
			result.add("Psp: "+filter.getPsp());
		}
		
		
		return result;
		
	}
	

}
