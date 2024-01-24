/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.interf.ReportCleanerBusiness;
import it.csi.epay.epaypaweb.dto.ReportBaseDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestionePrenotazioneReportDad;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionManagement ( TransactionManagementType.BEAN )
public class ReportCleanerBusinessImpl extends EPaypaBusinessBase implements ReportCleanerBusiness {
	
static private final String CLASSNAME = ReportCleanerBusinessImpl.class.getSimpleName();
	
	private final AtomicBoolean running = new AtomicBoolean(false);
	
	@Inject
    private GestionePrenotazioneReportDad gestionePrenotazioneReportDad;
	
	@Resource
    private UserTransaction transaction;
	
	@Override
	public boolean isRunning() {
		return running.get();
	}

	@Override
	public void cancellazioneReport(String[] reportStatusCodes, int thresholdDays) {
		String methodName = "deleteOldReport";
		
		if (running.compareAndSet(false, true)) {
			
			
	        
	        log.info ( CLASSNAME + " " + methodName + " - START" );
	        
	        try {
	        	
	        	List<ReportBaseDto> reportDacancellare = readReportDacancellare(reportStatusCodes, thresholdDays);
				if(null != reportDacancellare && !CollectionUtils.isEmpty (reportDacancellare)) {
					for(ReportBaseDto reportDto:reportDacancellare) {
						
						try {
							log.info("REPORT ID=" + reportDto.getId() +  " INIZIO CANCELLAZIONE...");
							cancellaReport(reportDto);
							log.info("REPORT ID=" + reportDto.getId() +  " CANCELLATA CORRETTAMENTE");
						} catch (Throwable t) {
							log.error("ERRORE IMPREVISTO DURANTE LA CANCELLAZIONE DEL REPORT ID=" + reportDto.getId(), t);
						}
					}
				}else {
					log.info("Non sono presenti report da cancellare");
				}
	        } catch (Throwable t) {
				log.error( "Errore imprevisto durante la creazione dei reports " + CLASSNAME + "::" + methodName, t );
			}finally {
				log.info ( CLASSNAME + " " + methodName + " - STOP" );
				running.set(false);
			}
			
		}else {
			log.warn("Il metodo " + CLASSNAME +"::" + methodName + "  ancora in esecuzione ");
		}
	}

	private void cancellaReport(ReportBaseDto report) throws BusinessException {
		String methodName = "cancellaReport";
        
        

        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
            transaction.begin ();
        	gestionePrenotazioneReportDad.deleteReport(report);
        	transaction.commit();
        } catch ( Exception t ) {
            try {
                transaction.rollback ();
            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            }
            if ( t instanceof BusinessException ) {
                throw (BusinessException) t;
            }
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }        
	}
	
	private List<ReportBaseDto> readReportDacancellare(String[] reportStatusCodes, int thresholdDays) throws BusinessException {
		String methodName = "readReportDacancellare";
        
        
        
        
        List<ReportBaseDto> result = null;
        
        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
        	Date thresholdDate = new Date(System.currentTimeMillis() - (long)thresholdDays*1000*60*60*24 );
        	transaction.begin ();
        	result = gestionePrenotazioneReportDad.ricercaReportByStateCodesAndDataModificaBefore(reportStatusCodes, thresholdDate);
        	
        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        }finally {
        	try {
                transaction.rollback ();
            } catch ( Exception e ) {
                log.error ( "errore nel rollback", e );
            }
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        
        return result;
	}
}
