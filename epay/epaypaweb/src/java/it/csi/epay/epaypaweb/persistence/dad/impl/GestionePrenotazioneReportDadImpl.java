/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.enumeration.ColumnNameReportEnum;
import it.csi.epay.epaypaweb.enumeration.StatoReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestionePrenotazioneReportDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.*;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiFiltroReport;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFileReport;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTReport;

import it.csi.epay.epaypaweb.util.Util;
import org.apache.commons.collections.CollectionUtils;

import javax.inject.Inject;
import java.util.*;


public class GestionePrenotazioneReportDadImpl extends EPaypaDadBaseImpl implements GestionePrenotazioneReportDad {

    private static final String CLASSNAME = "GestionePrenotazioneReportDadImpl";

    @Inject
    private EpaypaTReportDao epaypaTReportDao;

    @Inject
    private EpaypaDStatoReportDao epaypaDStatoReportDao;

    @Inject
    private EpaypaDTipoFileReportDao epaypaDTipoFileReportDao;

    @Inject
    private EpaypaTFileReportDao epaypaTFileReportDao;

    @Inject
    private EpaypaDTipoReportDao epaypaDTipoReportDao;

    @Inject
    private EpaypaDTipoCampoFiltroDao epaypaDTipoCampoFiltroDao;


    @Override
    public <T extends ReportBaseDto> TotalSizeAndLightListDto<T> ricercaReport ( Integer idEnte, Long idUtente, PaginazioneDto pag, TipoReportEnum tipoReport ) throws PersistenceException {
        String methodName = "ricerca";
        
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            final Class<T> type = getReportDtoClass ( tipoReport );
            List<T> dtoList = new LinkedList<> ();

            TotalSizeAndLightListDto<EpaypaTReport> entityList = epaypaTReportDao.findAllByIdEnteAndIdUtenteAndCodiceTipoReport ( idEnte, idUtente, epaypaDTipoReportDao.findOneByCodice(tipoReport.getCode()).getCodice(), pag);

            if ( null != entityList && !CollectionUtils.isEmpty ( entityList.getLightList () ) ) {
                entityList.getLightList ().forEach ( r -> dtoList.add ( Util.toDto ( r, type ) ) );
            }

			assert entityList != null;
			return new TotalSizeAndLightListDto<> (entityList.getTotalSize (), dtoList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @Override
    public void salvaReportRicerca ( ReportBaseDto report ) throws PersistenceException {
        String methodName = "salvaReportRicerca";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            if ( report != null ) {
                //insert
                EpaypaTReport epaypaTReport = EpaypaTReport.builder ()
                                .withCodiceFiscaleEnte ( report.getCodiceFiscaleEnte () )
                                .withCodiceFiscaleUtente ( report.getCodiceFiscaleUtente () )
                                .withDataInserimento ( new Date () )
                                .withEpaypaDStatoReport ( epaypaDStatoReportDao.findOne ( report.getStatoReport ().getId () ) )
                                .withEpaypaDTipoFileReport ( epaypaDTipoFileReportDao.findOne ( report.getTipoFileReport ().getId () ) )
                                .withIdEnte ( report.getIdEnte () )
                                .withIdUtente ( report.getIdUtente () )
                                .withNominativoExport ( report.getNominativoExport () )
                                .withEpaypaDTipoReport(epaypaDTipoReportDao.findOneByCodice(report.getTipoReportDto().getCodice()))
                                .build ();

                List<DatiFiltroReportDto> datiFiltroReportDtoList = report.buildDatiFiltroReportDto();
                if ( null != datiFiltroReportDtoList && !CollectionUtils.isEmpty ( datiFiltroReportDtoList ) ) {
                    List<EpaypaTDatiFiltroReport> epaypaTDatiFiltroReports = new ArrayList<EpaypaTDatiFiltroReport> () ;

                    for( DatiFiltroReportDto dfr : datiFiltroReportDtoList ) {
                        epaypaTDatiFiltroReports.add( EpaypaTDatiFiltroReport.builder()
                            .withId(dfr.getId())
                            .withNomeFiltro(dfr.getNomeFiltro().getCode())
                            .withValore(dfr.getValore())
                            .withEpaypaDTipoCampoFiltro(epaypaDTipoCampoFiltroDao.findOneByCodice( dfr.getTipoCampoFiltro().getCode() ))
                            .withEpaypaTReport(epaypaTReport)
                            .build() );
                    }

                    epaypaTReport.setEpaypaTDatiFiltroReports ( epaypaTDatiFiltroReports );

                }
                epaypaTReportDao.persist ( epaypaTReport );
            }
        } catch ( Exception e ) {
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @Override
    public List<ReportBaseDto> ricercaReportDaGenerareByStatoReport(StatoReportEnum statoReport, int maxResultSize ) throws PersistenceException {

        String methodName = "ricercaReportDaGenerare";
        
        

        List<ReportBaseDto> result = new ArrayList<>();

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            List<EpaypaTReport> epaypaTReportList = epaypaTReportDao.findByStatusCode(statoReport.getCode(), maxResultSize);

            if(null != epaypaTReportList && !CollectionUtils.isEmpty ( epaypaTReportList )) {
                epaypaTReportList.forEach(report -> result.add(Util.toDto ( report, getReportDtoClass(
								Objects.requireNonNull ( TipoReportEnum.fromId ( report.getEpaypaDTipoReport ().getCodice () ) ) ) )));
            }

        }finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return result;
    }


    @Override
    public List<ReportBaseDto>  ricercaReportByStateCodesAndDataModificaBefore(String[] stateCodes, Date thresholdDate)
                    throws PersistenceException {

        String methodName = "ricercaReportByStateCodesAndDataModificaBefore";
        
        
        

        List<ReportBaseDto> result = new ArrayList<>();

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            List<EpaypaTReport> epaypaTReportList = epaypaTReportDao.findByStateCodesAndDataModificaBefore(stateCodes, thresholdDate);

            if(null != epaypaTReportList && !CollectionUtils.isEmpty ( epaypaTReportList )) {
                epaypaTReportList.forEach(report -> result.add(Util.toDto ( report, getReportDtoClass(
								Objects.requireNonNull ( TipoReportEnum.fromId ( report.getEpaypaDTipoReport ().getCodice () ) ) ) )));
            }

        }finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return result;
    }

    @Override
    public void updateReportRicerca(ReportBaseDto report) throws PersistenceException {
        String methodName = "updateReportRicerca";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            EpaypaTReport reportEntity = epaypaTReportDao.findOne(report.getId());
            if(reportEntity != null) {
                reportEntity.setEpaypaDStatoReport(epaypaDStatoReportDao.findOne ( report.getStatoReport ().getId () ) );
                reportEntity.setNominativoExport(report.getNominativoExport() + "-" + report.getId () );
                reportEntity.setDataModifica(getTimestampNow());

                epaypaTReportDao.merge(reportEntity);
            }else {
                throw new PersistenceException ( CLASSNAME + "::" + methodName + " Report non presente (id="+ report.getId()+")");
            }
        } catch ( Exception e ) {
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

    }

    @Override
    public void inserisciFileReport(FileReportDto fileReport, ReportBaseDto report) throws PersistenceException {
        String methodName = "inserisciFileReport";
        
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            EpaypaTReport reportEntity = epaypaTReportDao.findOne(report.getId());
            if(reportEntity != null) {
                EpaypaTFileReport fileReportEntity = reportEntity.getEpaypaTFileReport();
                if(fileReportEntity == null) {
                    fileReportEntity = new EpaypaTFileReport();
                    fileReportEntity.setNomeFile(fileReport.getNomeFile());
                    fileReportEntity.setDataInserimento(getTimestampNow());

                    reportEntity.setEpaypaTFileReport(fileReportEntity);

                    List<EpaypaTReport> reportEntitiesList = new ArrayList<>();
                    reportEntitiesList.add(reportEntity);
                    fileReportEntity.setEpaypaTReports(reportEntitiesList);

                    epaypaTFileReportDao.persist(fileReportEntity);
                }
                fileReportEntity.setContenutoFile(fileReport.getContenutoFile());
                fileReportEntity.setDataModifica(getTimestampNow());
                fileReportEntity.setNomeFile(fileReport.getNomeFile());
                reportEntity.setDataModifica(getTimestampNow());

                epaypaTReportDao.merge(reportEntity);
            }else {
                throw new PersistenceException ( CLASSNAME + "::" + methodName + " Report non presente (id="+ report.getId()+")");
            }

        } catch ( Exception e ) {
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @Override
    public void deleteReport(ReportBaseDto report) throws PersistenceException {
        String methodName = "deleteReportRicerca";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            EpaypaTReport reportEntity = epaypaTReportDao.findOne(report.getId());
            if(reportEntity != null) {
                EpaypaTFileReport fileReportEntity = reportEntity.getEpaypaTFileReport();

                if(fileReportEntity != null) {
                    reportEntity.setEpaypaTFileReport(null);
                    epaypaTFileReportDao.remove(fileReportEntity);
                }
                epaypaTReportDao.remove(reportEntity);
            }
        } catch ( Exception e ) {
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @Override
    public FileReportDto downloadFileReportRicerca ( Integer idEnte, Long idUtente, Long idFile ) throws PersistenceException {
        String methodName = "downloadFileReportRicerca";
        
        
        
        

        FileReportDto result = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            EpaypaTFileReport file = epaypaTFileReportDao.findOne ( idFile );
            if ( null != file && !CollectionUtils.isEmpty ( file.getEpaypaTReports () ) ) {
                if ( file.getEpaypaTReports ().size () == 1 &&
                                file.getEpaypaTReports ().get ( 0 ).getIdEnte ().equals ( idEnte ) &&
                                file.getEpaypaTReports ().get ( 0 ).getIdUtente ().equals ( idUtente ) ) {
                    result = FileReportDto.builder ()
                                    .withContenutoFile ( file.getContenutoFile () )
                                    .withNomeFile ( file.getNomeFile () )
                                    .build ();
                    file.getEpaypaTReports ().get ( 0 )
                    .setEpaypaDStatoReport ( epaypaDStatoReportDao.findOneByCodice ( StatoReportEnum.DOWNLOADED.getCode () ) );
                    result.setReport( Util.toDto ( file.getEpaypaTReports ().get ( 0 ) 
                    		 , getReportDtoClass( Objects.requireNonNull (
													TipoReportEnum.fromId ( file.getEpaypaTReports ().get ( 0 ).getEpaypaDTipoReport ().getCodice () ) ) ) ) );
                    epaypaTReportDao.merge ( file.getEpaypaTReports ().get ( 0 ) );
                } else {
                    //errore in fase di download del file
                    log.warn ( "Impossibile scaricare il file con id: "+ idFile );
                }
            } else {
                // file non presente
                log.warn ( "Nessun file trovato con id: "+ idFile );
            }
        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return result;
    }

    @Override
    public <T extends ReportBaseDto> TotalSizeAndLightListDto<T> ricercaReport(ReportFilterDto reportFilterDto, List<CriterioOrdinamentoDto<ColumnNameReportEnum>> ord,
        PaginazioneDto pag) throws PersistenceException {
        String methodName = "ricerca";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            final Class<T> type = getReportDtoClass ( reportFilterDto.getTipoReportEnum() );
            List<T> dtoList = new LinkedList<T> ();

            TotalSizeAndLightListDto<EpaypaTReport> entityList = epaypaTReportDao.findAllByFilter(reportFilterDto, ord, pag);

            if ( null != entityList && !CollectionUtils.isEmpty ( entityList.getLightList () ) ) {
                entityList.getLightList ().forEach ( r -> dtoList.add ( Util.toDto ( r, type ) ) );
            }

			assert entityList != null;
			return new TotalSizeAndLightListDto<> (entityList.getTotalSize (), dtoList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @SuppressWarnings ( "unchecked" )
    private <T extends ReportBaseDto> Class<T> getReportDtoClass( TipoReportEnum tipoReport ){

        final Class<T> type;
        switch (tipoReport) {
        case PAGAMENTI :
            type = (Class<T>) ReportPagamentiDto.class;
            break;
        case RENDICONTAZIONE:
            type = (Class<T>) ReportFlussoRendicontazioneDto.class;
            break;
        default:
            throw new IllegalArgumentException("TIPO REPORT NON VALIDO: " + tipoReport);
        }

        return type;
    }

}
