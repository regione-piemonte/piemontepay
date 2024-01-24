/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.interf.ReportEntiBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.common.*;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameReportEnum;
import it.csi.epay.epaypaweb.enumeration.CsiLogAuditOperationEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.exception.RecordNumberGreaterThanThresholdException;
import it.csi.epay.epaypaweb.persistence.dad.interf.CsiLogAuditDad;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestionePagamentiDad;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestionePrenotazioneReportDad;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;


@Stateless
@TransactionManagement ( TransactionManagementType.BEAN )
public class ReportEntiBusinessImpl extends EPaypaBusinessBase implements ReportEntiBusiness {

    private static final String CLASSNAME = ReportEntiBusinessImpl.class.getSimpleName ();

    @Inject
    private GestionePagamentiDad gestionePagamentiDad;

    @Inject
    private GestionePrenotazioneReportDad gestionePrenotazioneReportDad;
    
    @Inject
    private CsiLogAuditDad csiLogAuditDad;

    @Resource
    private UserTransaction transaction;

    @Override
    public TotalSizeAndLightListDto<PagamentiDto> ricerca (
        RicercaReportEntiDto ricerca,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord,
        PaginazioneDto pag ) throws BusinessException {

        String methodName = "ricerca";
        
        
        
        

        TotalSizeAndLightListDto<PagamentiDto> resPagamenti = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            resPagamenti = gestionePagamentiDad.ricerca ( ricerca.getFilter(), ord, pag );
            
            logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, ricerca, "epaypa_t_pagamenti", "FILTRO= "+ricerca.getFilter().getAuditMessage() , "Ricerca report enti");
//            logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, totalSizeAndFlussoRevocheListRequestDto, "epaypa_t_rr", "FILTRO="+ totalSizeAndFlussoRevocheListRequestDto.getFilter().getAuditMessage(), "Ricerca Flussi Revoche");



        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( "result:" + resPagamenti );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resPagamenti;
    }

    @Override
    public List<PagamentiExportDto> esporta (
    		RicercaReportEntiDto ricercaReportEntiDto,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord ) throws BusinessException, RecordNumberGreaterThanThresholdException {

        String methodName = "esporta";
        
        
        

        List<PagamentiExportDto> resPagamenti = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            transaction.begin ();

            resPagamenti = gestionePagamentiDad.esporta ( ricercaReportEntiDto.getFilter(), ord, null).getLightList();
            
          

        } catch ( RecordNumberGreaterThanThresholdException e ) {
            log.warn ( e.getMessage() + " " + CLASSNAME + "::" + methodName);
            throw e;

        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            try {
                transaction.rollback ();
            } catch ( Exception e ) {
                log.error ( "errore nel rollback", e );
            }

            log.info ( "result:" + resPagamenti );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, ricercaReportEntiDto, "epaypa_t_pagamenti", "Filtro= "+ricercaReportEntiDto.getFilter().getAuditMessage() , "Esporta report enti (download file)");


        return resPagamenti;
    }

    @Override
    public DettaglioPagamentiDto getDettaglio (  RicercaDettaglioReportEntiDto ricercaDettaglioReportEntiDto) throws BusinessException {
        String methodName = "getDettaglio";

        DettaglioPagamentiDto pagamento = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            transaction.begin ();

            pagamento = gestionePagamentiDad.getDettaglio ( ricercaDettaglioReportEntiDto.getIuv()  );
          
        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            try {
                transaction.rollback ();
            } catch ( Exception e ) {
                log.error ( "errore nel rollback", e );
            }

            log.info ( "result:" + pagamento );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, ricercaDettaglioReportEntiDto, "epaypa_t_pagamenti", "IUV= "+ricercaDettaglioReportEntiDto.getIuv() , "Ricerca dettaglio report enti");


        return pagamento;
    }

    @Override
    public TotalSizeAndLightListDto<ReportBaseDto> ricercaReport( RicercaPrenotazioneReportEntiDto ricercaPrenotazioneReportEntiDto) throws BusinessException {
        String methodName = "ricercaReport";
        
        
        
        
        TotalSizeAndLightListDto<ReportBaseDto> result= null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            result=  gestionePrenotazioneReportDad.ricercaReport ( ricercaPrenotazioneReportEntiDto.getIdEnte() , 
            		ricercaPrenotazioneReportEntiDto.getIdUtente(), ricercaPrenotazioneReportEntiDto.getPag(),
            		ricercaPrenotazioneReportEntiDto.getTipoReport() );
            
//            logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, ricercaPrenotazioneReportEntiDto, "epaypa_t_report", 
//            		"ID ENTE = "+ricercaPrenotazioneReportEntiDto.getIdEnte()+" ID UTENTE = "+ricercaPrenotazioneReportEntiDto.getIdUtente() 
//            		+" TIPO REPORT = "+ricercaPrenotazioneReportEntiDto.getTipoReport().getCode(),
//            		"Ricerca prenota report enti");


        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return result;
    }

    @Override
    public void salvaPrenotazioneReport (SalvaPrenotazioneReportEntiDto salvaPrenotazioneReportEntiDto ) throws BusinessException {
        String methodName = "SalvaReportRicerca";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            transaction.begin ();
            gestionePrenotazioneReportDad.salvaReportRicerca (  salvaPrenotazioneReportEntiDto.getReportBaseDto() );
            transaction.commit ();
            
            logAuditOperation(CsiLogAuditOperationEnum.INSERT, salvaPrenotazioneReportEntiDto, "epaypa_t_report", 
            		"FILTRO = "+salvaPrenotazioneReportEntiDto.getReportBaseDto().getAuditMessage(),
            		"Salva prenotazione report enti");
            
            
        } catch ( Exception t ) {
            try {
                transaction.rollback ();
            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            }
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        
    }

    @Override
    public FileReportDto downloadFileReportRicerca (DownloadPrenotazioneReportEntiRequestDto downloadPrenotazioneReportEntiRequestDto) throws BusinessException {
        String methodName = "downloadFileReportRicerca";
        
        
        
        
        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            transaction.begin ();
            FileReportDto file = gestionePrenotazioneReportDad.downloadFileReportRicerca ( downloadPrenotazioneReportEntiRequestDto.getIdEnte(), 
            		downloadPrenotazioneReportEntiRequestDto.getIdUtente(), downloadPrenotazioneReportEntiRequestDto.getIdFile() );
            transaction.commit ();
            
            logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, downloadPrenotazioneReportEntiRequestDto, "epaypa_t_file_report", 
            		"ID ENTE = "+downloadPrenotazioneReportEntiRequestDto.getIdEnte()+" ID UTENTE = "+downloadPrenotazioneReportEntiRequestDto.getIdUtente() 
            		+" ID FILE = "+downloadPrenotazioneReportEntiRequestDto.getIdFile()+"; "+ getFiltroReport(file.getReport().buildDatiFiltroReportDto()),
            		"Download prenota report enti");
            
            return file;
        } catch ( Exception t ) {
            try {
                transaction.rollback ();
            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            }
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

	private String getFiltroReport(List<DatiFiltroReportDto> buildDatiFiltroReportDto) {
		
		String result= "";
		if (null!= buildDatiFiltroReportDto)
		{
			for (DatiFiltroReportDto temp : buildDatiFiltroReportDto)
			{
				
				result= result+ temp.getNomeFiltro().getCode()+": "+temp.getValore()+"; ";
			}
		}
		return result;
	}

	@Override
	public <T extends ReportBaseDto> TotalSizeAndLightListDto<T> ricercaReport(ReportFilterDto reportFilterDto,  List<CriterioOrdinamentoDto<ColumnNameReportEnum>> ord,
			PaginazioneDto pag) throws BusinessException {
		String methodName = "ricercaReport";
        
        
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            return gestionePrenotazioneReportDad.ricercaReport ( reportFilterDto, ord, pag);

        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
	}
	
	 private void logAuditOperation(CsiLogAuditOperationEnum operation, PrincipalDto principalDto, String tabella, String oggetto, String descrizione){
	    	
	    	try {
	    		transaction.begin ();
	    		csiLogAuditDad.logAuditOperation(operation, principalDto, tabella, oggetto, descrizione);
	    		
				transaction.commit();
			} catch (Throwable t ) {
				try {
					transaction.rollback ();
				} catch (IllegalStateException | SecurityException | SystemException e) {
					log.error("["+CLASSNAME + " :: logAuditOperation] Errore durante il rollback della transazione ",e);
				}
				log.error("["+CLASSNAME + " :: logAuditOperation] Errore durante la chiamata ai metodi della classe CsiLogAuditDad",t);
			}
	    }

}
