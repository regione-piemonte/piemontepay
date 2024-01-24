/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.ReportCreatorTypes;
import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.business.interf.ReportCreatorBusiness;
import it.csi.epay.epaypaweb.business.interf.ReportManagerBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.enumeration.StatoReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestionePrenotazioneReportDad;

import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Singleton
//@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionManagement ( TransactionManagementType.BEAN )
public class ReportManagerBusinessImpl extends EPaypaBusinessBase  implements ReportManagerBusiness {

	static private final String CLASSNAME = ReportManagerBusinessImpl.class.getSimpleName();
	
	private AtomicBoolean running = new AtomicBoolean(false);
	
	@Inject
    private GestionePrenotazioneReportDad gestionePrenotazioneReportDad;
		
	@Resource
    protected UserTransaction transaction;
	
	@EJB
	public GestioneFlussiBusiness gestioneFlussiBusiness;
	
	@Inject
	@ReportCreatorTypes(TipoReportEnum.PAGAMENTI)
	ReportCreatorBusiness<ReportPagamentiDto> pagamentiReportCreator;
	
	@Inject
	@ReportCreatorTypes(TipoReportEnum.RENDICONTAZIONE)
	ReportCreatorBusiness<ReportFlussoRendicontazioneDto> flussiRendicontazioneReportCreator;
	
	@Override
	public boolean isRunning() {
		return running.get();
	}
	
    @Override
    @Asynchronous
    public void crateReport ( int maxReportNumber, int recordsPageSize ) {
        String methodName = "crateReport";
        
        if ( running.compareAndSet ( false, true ) ) {
            
            
            log.info ( CLASSNAME + " " + methodName + " - START" );

            try {
                List<ReportBaseDto> reportDaGenerare = readReportDaElaborare ( StatoReportEnum.INSERTED, maxReportNumber );
                if ( CollectionUtils.isEmpty ( reportDaGenerare ) ) {
                    reportDaGenerare = readReportDaElaborare ( StatoReportEnum.EMPTY, maxReportNumber );
                }
                if ( CollectionUtils.isEmpty ( reportDaGenerare ) ) {
                    reportDaGenerare = readReportDaElaborare ( StatoReportEnum.FAILED, maxReportNumber );
                }
                

                if ( !CollectionUtils.isEmpty ( reportDaGenerare ) ) {
                    for ( ReportBaseDto reportDto: reportDaGenerare ) {
                        log.info ( "START elaborazione report id=" + reportDto.getId () + " stato='" + reportDto.getStatoReport ().getCodice () + "'" );

                        elaboraSingoloReport ( reportDto, recordsPageSize );

                        log.info ( "END elaborazione repord id=" + reportDto.getId () + " stato='" + reportDto.getStatoReport ().getCodice () + "'" );
                    }
                }
            } catch ( Throwable t ) {
                log.error ( "Errore imprevisto durante la creazione dei reports " + CLASSNAME + "::" + methodName, t );
            } finally {
                log.info ( CLASSNAME + " " + methodName + " - STOP" );
                running.set ( false );
            }

        } else {
            log.warn ( "Il metodo " + CLASSNAME + "::" + methodName + " ancora in esecuzione " );
        }
    }
	
	private void elaboraSingoloReport(ReportBaseDto reportDto, int recordsPageSize) {
		String methodName = "elaboraSingoloReport";
        
        
        
        
        List<String> tmpReportsFilesNames = null; 
        
        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
			log.info("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL A WIP...");
			reportDto.setStatoReport(StatoReportDto.builder().withId(StatoReportEnum.WIP.getId()).withCodice(StatoReportEnum.WIP.getCode()).build());
			salvaReportDto(reportDto);
			log.info("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A WIP CORRETTAMENTE");
			
			FlussoLightFilterDto filter = new FlussoLightFilterDto();
            if ( reportDto instanceof ReportFlussoRendicontazioneDto ) {
                filter.setTipoFlusso(TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE);
                filter.setIdEnte(reportDto.getIdEnte());
                filter.setDataInserimentoDa ( ( (ReportFlussoRendicontazioneDto) reportDto ).getDataRicezioneInizio () );
                filter.setDataInserimentoA ( ( (ReportFlussoRendicontazioneDto) reportDto ).getDataRicezioneFine () );
                filter.setCodiceDenominazioneMittente ( ( (ReportFlussoRendicontazioneDto) reportDto ).getCodiceDescrizionePSP () );
                if (!gestioneFlussiBusiness.isNumeroFlussiElaborabile(filter, reportDto.getCodiceFiscaleUtente()))
                {
                    log.error("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL A OVERFULL...");
                    reportDto.setStatoReport(StatoReportDto.builder().withId(StatoReportEnum.OVERFULL.getId()).withCodice(StatoReportEnum.OVERFULL.getCode()).build());
                    salvaReportDto(reportDto);
                    log.info("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A OVERFULL CORRETTAMENTE");
                    return;
                    
                }
            }
			
			log.info("REPORT ID=" + reportDto.getId() +  " INIZIO GENERAZIONE DEL FILE...");
			tmpReportsFilesNames = generaReport(reportDto, recordsPageSize);
			
			if (!CollectionUtils.isEmpty(tmpReportsFilesNames))
			{
				log.info("REPORT ID=" + reportDto.getId() +  " FINE GENERAZIONE DEI FILE '" + tmpReportsFilesNames + "'");
				
				log.info("REPORT ID=" + reportDto.getId() +  " INIZIO CREAZIONE ARRAY DI BYTE DEI FILE COMPRESSI '" + tmpReportsFilesNames + "'....");
				byte[] compressedReportBytes = compressReportsFiles(tmpReportsFilesNames);
				log.info("REPORT ID=" + reportDto.getId() +  " FINE CREAZIONE ARRAY DI BYTE DEI FILE COMPRESSI '" + tmpReportsFilesNames + "'");
				FileReportDto fileReportDto = new FileReportDto();
				fileReportDto.setContenutoFile(compressedReportBytes);
				fileReportDto.setNomeFile(reportDto.getNominativoExport() + ".zip");
				log.info("REPORT ID=" + reportDto.getId() +  " SALVATAGGIO SU DB ARRAY DI BYTE DEI FILE COMPRESSO ...");
				salvaFileReport(fileReportDto, reportDto);
				log.info("REPORT ID=" + reportDto.getId() +  " SALVATAGGIO SU DB ARRAY DI BYTE DEI FILE COMPRESSO TERMINATO");
				
				log.info("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL REPORT A COMPLETED...");
				reportDto.setStatoReport(StatoReportDto.builder().withId(StatoReportEnum.COMPLETED.getId()).withCodice(StatoReportEnum.COMPLETED.getCode()).build());
				salvaReportDto(reportDto);
				log.info("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A COMPLETED CORRETTAMENTE");
				
			}
			else
			{
				
				log.error("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL A EMPTY...");
            	reportDto.setStatoReport(StatoReportDto.builder().withId(StatoReportEnum.EMPTY.getId()).withCodice(StatoReportEnum.EMPTY.getCode()).build());
            	salvaReportDto(reportDto);
            	log.info("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A EMPTY CORRETTAMENTE");
            	return;
				
			}
			
			
        }catch ( Throwable t ) {
            log.error ( "Errore imprevisto durante la generazione del report (id=" + reportDto.getId() + ") in " + CLASSNAME + "::" + methodName, t );
         
            try {
            	log.error("REPORT ID=" + reportDto.getId() +  " IMPOSTO LO STATO DEL A FAILED...");
            	reportDto.setStatoReport(StatoReportDto.builder().withId(StatoReportEnum.FAILED.getId()).build());
    			salvaReportDto(reportDto);
    			log.error("REPORT ID=" + reportDto.getId() +  " IMPOSTATO LO STATO A FAILED CORRETTAMENTE");
            }catch ( Throwable tr ) {
            	log.error ( "Errore imprevisto durante il cambio di stato del report (id=" + reportDto.getId() + ") in " + CLASSNAME + "::" + methodName, tr );
            }
        }finally {
        	if( null != tmpReportsFilesNames && tmpReportsFilesNames.size() > 0) {
        		deleteTmpReportsFiles(tmpReportsFilesNames);
        	}
        	log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
	}
	
	private byte[] compressReportsFiles(List<String> tmpReportsFilesNames) throws IOException {
		String methodName = "compressReportsFiles";
		
		

		byte[] result = null;
		ByteArrayOutputStream bos = null;
		ZipOutputStream zipOut = null;
		try {
			
			log.info ( CLASSNAME + " " + methodName + " - START" );
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
							log.error("Errore imprevisto durante la chiusura dello stream di input relativo al file '"
									+ srcFile + "  in " + CLASSNAME + "::" + methodName, e);
						}
					}
				}
			}
		} finally {
			if (zipOut != null) {
				try {
					zipOut.close();
				} catch (IOException e) {
					log.error("Errore imprevisto durante la chiusura dello stream zip di output in " + CLASSNAME + "::"
							+ methodName, e);
				}
			}

			if (bos != null) {
				result = bos.toByteArray();
				bos.close();
			}
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}
	
	
	private List<String> generaReport(ReportBaseDto reportDto, int recordsPageSize) throws BusinessException {
		
		switch ( TipoReportEnum.fromId( reportDto.getTipoReportDto().getCodice() ) ) {
		case PAGAMENTI:
			return pagamentiReportCreator.generaReport( (ReportPagamentiDto) reportDto, recordsPageSize );
		case RENDICONTAZIONE:
			return flussiRendicontazioneReportCreator.generaReport( (ReportFlussoRendicontazioneDto) reportDto, recordsPageSize );
			default:
				throw new IllegalArgumentException("TIPO DI REPORT NON DEFINITO");
			
		}
	}

	private void deleteTmpReportsFiles(List<String> tmpReportsFilesNames) {
		for ( String fileName : tmpReportsFilesNames ) {
			File reportFile = new File(fileName); 
		    if (reportFile.delete()) { 
		    	log.info("Cancellazione del file temporaneo '" + fileName + "' terminata con successo");
		    } else {
		      log.error("Cancellazione del file temporaneo '" + fileName + "' NON eseguita");
		    }
		}
	}
	
	private List<ReportBaseDto> readReportDaElaborare(StatoReportEnum statoReport, int maxReportNumber) throws BusinessException{
		String methodName = "readReportDaElaborare";
        
        
        
        List<ReportBaseDto> result = null;
        
        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
        	transaction.begin ();
        	result = gestionePrenotazioneReportDad.ricercaReportDaGenerareByStatoReport(statoReport,maxReportNumber);
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

	private void salvaReportDto ( ReportBaseDto report ) throws BusinessException {
        String methodName = "SalvaReport";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            transaction.begin ();
            gestionePrenotazioneReportDad.updateReportRicerca ( report );
            transaction.commit ();
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
	
	private void salvaFileReport(FileReportDto fileReport, ReportBaseDto report) throws BusinessException {
		String methodName = "SalvaFileReport";
        
        
        

        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
            transaction.begin ();
            gestionePrenotazioneReportDad.inserisciFileReport(fileReport, report);
            transaction.commit();
        }catch ( Exception t ) {
            try {
                transaction.rollback ();
            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            }
            if ( t instanceof BusinessException ) {
                throw (BusinessException) t;
            }
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
        }finally {
        	log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
	}	

}
