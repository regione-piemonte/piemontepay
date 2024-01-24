/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.interf.AccessoBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.exception.BusinessTipiFormatoFileEmptyException;
import it.csi.epay.epaypaweb.exception.IntegrationException;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.facade.cooppec.dto.TipoPagamentoType;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.impl.EpayPaCatalogSrvServiceMgrImpl;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.interf.EpayPaCatalogSrvServiceMgr;
import it.csi.epay.epaypaweb.persistence.dad.interf.AccessoDad;
import it.csi.epay.epaypaweb.persistence.dad.interf.CommonDad;
import it.csi.epay.epaypaweb.presentation.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;


@Stateless
public class AccessoBusinessImpl extends EPaypaBusinessBase implements AccessoBusiness {

    static private final String CLASSNAME = AccessoBusinessImpl.class.getSimpleName ();

    static protected Logger log = LogManager.getLogger ( APPLICATION_CODE + ".business.accesso" );

    static private final boolean DERIVA_CODICI_DA_TEMATICHE = false;

    @Inject
    private AccessoDad accessoDad;

    @Inject
    private CommonDad commonDad;

    private EpayPaCatalogSrvServiceMgr getEpayPaCatalogSrvService ( String endpoint ) throws IntegrationException {

        EpayPaCatalogSrvServiceMgr service;
        try {
            service = new EpayPaCatalogSrvServiceMgrImpl ( endpoint );
        } catch ( IntegrationException e ) {
            log.error ( "errore nella creazione del servizio remoto", e );
            throw e;
        }

        return service;
    }

    //    @Override
    //    public UtenteDto getUtente ( String codUtente ) throws BusinessException {
    //        String methodName = "getUtente";
    //        LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
    //        lw.addParam ( "codUtente", codUtente );
    //
    //        UtenteDto resDto = null;
    //
    //        try {
    //            resDto = accessoDad.findUtenteByCod ( codUtente );
    //            if ( resDto == null ) {
    //                lw.error ( "utente non trovato per codUtente:".concat ( quote ( codUtente ) ) );
    //                throw new BusinessNotFoundException ( codUtente, "Utente", "codUtente" );
    //            }
    //
    //        } catch ( BusinessNotFoundException e ) {
    //            throw e;
    //
    //        } catch ( Throwable e ) {
    //            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
    //
    //        } finally {
    //            lw.addResult ( "result", resDto );
    //            lw.stop ();
    //        }
    //
    //        return resDto;
    //    }

    @Override
    public RuoloDto getRuolo ( Integer idRuolo ) throws BusinessException {
        String methodName = "getRuolo";
		log.info ( CLASSNAME + " " + methodName + " - START" );

        RuoloDto resDto = null;

        try {
            resDto = accessoDad.findRuoloById ( idRuolo );

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" + resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resDto;
    }

    @Override
    public ProfiloDto getProfilo ( Integer idProfilo ) throws BusinessException {
        String methodName = "getProfilo";
        
        

        ProfiloDto resDto = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            resDto = accessoDad.findProfiloById ( idProfilo );

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" +  resDto );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resDto;
    }

    @Override
    public EnteDto getEnte ( Integer idEnte, Collection<EnteDto> entiCaricati ) throws BusinessException {
        String methodName = "getEnte";
		log.info ( CLASSNAME + " " + methodName + " - START" );

        if ( entiCaricati == null ) {
            throw new InvalidParameterException ( "Lista dei ruoli precaricati non disponibile" );
        }

        EnteDto resDto = null;

        try {
            // resDto = accessoDad.findEnteById(idEnte);
            for ( EnteDto candidate: entiCaricati ) {
                if ( candidate.getId ().equals ( idEnte ) ) {
                    resDto = candidate;
                    break;
                }
            }

            if ( resDto == null ) {
                throw new InvalidParameterException ( "ID ente non valida: " + idEnte );
            }

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" + resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resDto;
    }

    @Override
    public List<EnteDto> getEnteList ( String endpoint, String codUtente ) throws BusinessException {
        String methodName = "getEnteList";

        List<EnteDto> resList = null;

        try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

            // verifica l'esistenza del codice utente
            //            if ( !accessoDad.existsUtenteByCod ( codUtente ) ) {
            //                lw.error ( "utente non trovato per codUtente:".concat ( quote ( codUtente ) ) );
            //                throw new BusinessNotFoundException ( codUtente, "Utente", "codUtente" );
            //            }

            // ottiene la lista degli enti di competenza dell'utente

            // OLD: PRE INTEGRAZIONE CON CONFIGURATORE
            // resList = accessoDad.findAllEnteByCodUtente(codUtente);
            // NEW: POST INTEGRAZIONE CON CONFIGURATORE
            resList = getEpayPaCatalogSrvService ( endpoint ).getEntiAssociati ( codUtente );

            //        } catch ( BusinessNotFoundException e ) {
            //            throw e;

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resList;
    }

    @Override
    public List<RuoloDto> getRuoloList ( Long idUtente, Integer idEnte ) throws BusinessException {
        String methodName = "getRuoloList";

        List<RuoloDto> resList = null;

        try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

            resList = accessoDad.findAllRuoloByIdUtenteAndIdEnte ( idUtente, idEnte );

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resList;
    }

    @Override
    public List<ProfiloDto> getProfiloList ( Long idUtente, Integer idEnte ) throws BusinessException {
        String methodName = "getProfiloList";

        List<ProfiloDto> resList = null;

        try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

            resList = accessoDad.findAllProfiloByIdUtenteAndIdEnte ( idUtente, idEnte );

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" + resList );
			log.info ( CLASSNAME + " " + methodName + " - START" );
        }

        return resList;
    }

    @Override
    public List<CduDto> getCduList ( Integer idRuolo ) throws BusinessException {
        String methodName = "getCduList";

        List<CduDto> resList = null;

        try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

            resList = accessoDad.findAllCduByIdRuolo ( idRuolo );

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resList;
    }

    @Override
    public List<CodiceVersamentoDto> getCodiceVersamentoList ( Integer idProfilo, Integer idEnte ) throws BusinessException {
        String methodName = "getCodiceVersamentoList";
		log.info ( CLASSNAME + " " + methodName + " - START" );

        List<CodiceVersamentoDto> resList = null;

        try {
            resList = accessoDad.findAllCodiceVersamentoByIdProfiloAndIdEnte ( idProfilo, idEnte );

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result:" + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resList;
    }

    @Override
    public List<TipoFlussoDto> getTipoFlussoList () throws BusinessException {
        String methodName = "getTipoFlussoList";
        

        List<TipoFlussoDto> resList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            resList = commonDad.findAllTipoFlusso ();

        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( "result: " + resList );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resList;
    }

    @Override
    public List<StatoFlussoDto> getStatoFlussoList () throws BusinessException {
        String methodName = "getStatoFlussoList";
        

        List<StatoFlussoDto> resList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            resList = commonDad.findAllStatoFlusso ();

        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( "result: " + resList );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resList;
    }

    @Override
    public List<TipoFormatoFileDto> getTipoFormatoFileList () throws BusinessException {
        String methodName = "getTipoFormatoFileList";
        

        List<TipoFormatoFileDto> resList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            resList = commonDad.findAllTipoFormatoFile ();
            if ( resList.isEmpty () ) {
                log.error ( "tipi formato file vuoto" );
                throw new BusinessTipiFormatoFileEmptyException ();
            }

        } catch ( BusinessTipiFormatoFileEmptyException e ) {
            throw e;

        } catch ( Throwable t ) {
            log.error ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

        } finally {
            log.info ( "result: " + resList );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resList;
    }

    @Override
    public ProfilazioneEpayPaCatalogSrvDto getProfilazione ( String endpoint, String codUtente, String codEnte ) throws BusinessException {
        String methodName = "getProfilazione";
        
        
        

        ProfilazioneEpayPaCatalogSrvDto output = null;

        if ( isEmpty ( codUtente ) ) {
            throw new InvalidParameterException ( "Il campo codice fiscale e' obbligatorio" );
        }

        if ( isEmpty ( codEnte ) ) {
            throw new InvalidParameterException ( "Il campo codice ente e' obbligatorio" );
        }

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            // verifica l'esistenza del codice utente
            //            if ( !accessoDad.existsUtenteByCod ( codUtente ) ) {
            //                lw.error ( "utente non trovato per codUtente:".concat ( quote ( codUtente ) ) );
            //                throw new BusinessNotFoundException ( codUtente, "Utente", "codUtente" );
            //            }

            /*
             * Richiedo i dati della profilazione dal servizio esposto da CATALOG passando la coppia UTENTE, ENTE
             */
            output = getEpayPaCatalogSrvService ( endpoint ).getProfilazioneUtente ( codUtente, codEnte );

            /*
             * I dati da CATALOG hanno ID differenti dagli oggetti corrispondenti sul database del PEC occorre quindi rimappare i vari ID cercando le
             * corrispondenze per chiavi naturali per gli utenti la chiave naturale e' il codice fiscale per gli enti la chiave naturale e' il codice fiscale
             * per i codici versamento la chiave naturale e' la coppia (ente, codice)
             */
            mappaIdDaCatalogAPEC ( output );

            /*
             * Su CATALOG l'utente puo' essere autorizzato ad una tematica intera con visibilita' totale in questo caso non viene esposta la lista dei singoli
             * codici versamento autorizzati Sul PEC non esiste il concetto di tematica, quindi bisogna calcolare quali codici versamento risultano autorizzati
             * a partire dalle tematiche su cui l'utente ha visibilita' totale Il calcolo si puo' effettuare a partire dal codice della tematica, perche' la
             * convenzione prevede che il codice dei codici versamento inizi con la stessa lettera del codice della tematica ad esempio, se la tematica ha
             * codice 'F000', consideriamo autorizzati tutti i codici versamento il cui codice inizi per 'F'
             */
            if ( DERIVA_CODICI_DA_TEMATICHE ) {
                calcolaAutorizzazioniCodiciVersamentoDaVisibilitaTematicheSuConfiguratore ( output );
            }

            /*
             * RDI 02 2019: FILTRO I CODICI VERSAMENTO ELIMINANDO I REDS
             */
            filtraCodiciVersamentoPerVisibilita ( output );
            
            
            /*
             * EPAY 374  : FILTRO I CODICI VERSAMENTO PER LE LISTE DI CARICO ELIMINANDO I REDS, I MABL e i PS
             */
            filtraCodiciVersamentoPerListeDiCarico(output);
            
            

            //        } catch ( BusinessNotFoundException e ) {
            //            throw e;

        } catch ( Throwable e ) {
            //EPAY-80
            log.error ( "errore in fase di getProfilazione", e );
            if ( StringUtils.contains ( e.getMessage (), Constants.RESULT_CODES.NOT_ALLOWED ) ) {
                throw new BusinessException (
                    "Accesso all'applicativo non consentito. Contattare l'amministratore dell'Ente. " + e.getMessage () );
            }
            throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( "result: " + output );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return output;
    }

    private void calcolaAutorizzazioniCodiciVersamentoDaVisibilitaTematicheSuConfiguratore (
        ProfilazioneEpayPaCatalogSrvDto input ) throws PersistenceException {
        /*
         * Su CATALOG l'utente puo' essere autorizzato ad una tematica intera con visibilita' totale in questo caso non viene esposta la lista dei singoli
         * codici versamento autorizzati Sul PEC non esiste il concetto di tematica, quindi bisogna calcolare quali codici versamento risultano autorizzati a
         * partire dalle tematiche su cui l'utente ha visibilita' totale Il calcolo si puo' effettuare a partire dal codice della tematica, perche' la
         * convenzione prevede che il codice dei codici versamento inizi con la stessa lettera del codice della tematica ad esempio, se la tematica ha codice
         * 'F000', consideriamo autorizzati tutti i codici versamento il cui codice inizi per 'F'
         */

        String methodName = "calcolaAutorizzazioniCodiciVersamentoDaVisibilitaTematicheSuConfiguratore";
        

        try {

            if ( input.getEnte () != null && input.getTematiche () != null && !input.getTematiche ().isEmpty () ) {
                for ( TematicaDto tematica: input.getTematiche () ) {
                    if ( !tematica.getVisibilitaTotale () ) {
                        continue;
                    }

                    String codiceExpression = tematica.getCod ().substring ( 0, 1 ) + "%";

                    List<CodiceVersamentoDto> codiciVersamentoAppartenentiATematica = accessoDad.findAllCodVersamentoByIdEnteAndCodiceLike (
                        input.getEnte ().getId (), codiceExpression );

                    if ( codiciVersamentoAppartenentiATematica != null && !codiciVersamentoAppartenentiATematica.isEmpty () ) {
                        input.getCodiciVersamento ().addAll ( codiciVersamentoAppartenentiATematica );
                    }
                }
            }

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

    }

    private void mappaIdDaCatalogAPEC ( ProfilazioneEpayPaCatalogSrvDto input ) throws PersistenceException {
        /*
         * I dati da CATALOG hanno ID differenti dagli oggetti corrispondenti sul database del PEC occorre quindi rimappare i vari ID cercando le corrispondenze
         * per chiavi naturali per gli utenti la chiave naturale e' il codice fiscale per gli enti la chiave naturale e' il codice fiscale per i codici
         * versamento la chiave naturale e' la coppia (ente, codice)
         */
        String methodName = "mappaIdDaCatalogAPEC";
        

        try {
            //TODO GESTIONE RIMOSSA PER ELIMINAZIONE UTENTEDao
            //            if ( input.getUtente () != null && false ) {
            //                UtenteDto utenteMappato = accessoDad.findUtenteByCod ( input.getUtente ().getCod () );
            //                if ( utenteMappato == null ) {
            //                    throw new RuntimeException ( "Utente " + input.getUtente ().getCod () + " non censito sulla piattaforma EpayPA" );
            //                } else {
            //                    lw.debug ( "l'utente " + input.getUtente ().getCod () + " e' stato mappato da ID " +
            //                                    input.getUtente ().getId () + " a ID " + utenteMappato.getId () );
            //
            //                    input.getUtente ().rimappaId ( utenteMappato.getId () );
            //                    input.getUtente ().setNomeCompleto ( utenteMappato.getNome () );
            //                }
            //            }

            if ( input.getEnte () != null ) {
                EnteDto enteMappato = accessoDad.findEnteByCod ( input.getEnte ().getCodFiscale () );
                if ( enteMappato == null ) {
                    log.warn ( "Ente " + input.getEnte ().getCodFiscale () + " non censito sulla piattaforma EpayPA" );
                } else {
                    log.debug ( "l'ente " + input.getEnte ().getCodFiscale () + " e' stato mappato da ID " +
                        input.getEnte ().getId () + " a ID " + enteMappato.getId () );
                    input.setEnte ( enteMappato );
                }
            }

            if ( input.getCodiciVersamento () != null && !input.getCodiciVersamento ().isEmpty () ) {
                List<String> codiciVersamentoConfiguratore = new ArrayList<> ();

                for ( CodiceVersamentoDto codiceSuCatalog: input.getCodiciVersamento () ) {
                    codiciVersamentoConfiguratore.add ( codiceSuCatalog.getCod () );
                }

                List<CodiceVersamentoDto> mappatura = accessoDad.findAllCodVersamentoByIdEnteAndCodiceIn (
                    input.getEnte ().getId (),
                    codiciVersamentoConfiguratore );

                List<CodiceVersamentoDto> codiciVersamentoMappati = new ArrayList<> ();

                for ( CodiceVersamentoDto codiceSuCatalog: input.getCodiciVersamento () ) {
                    // cerco corrispondenza in mappatura
                    CodiceVersamentoDto mappato = null;

                    for ( CodiceVersamentoDto candidate: mappatura ) {
                        if ( candidate.getCod ().equals ( codiceSuCatalog.getCod () ) ) {
                            mappato = candidate;
                            break;
                        }
                    }

                    if ( mappato == null ) {
                        log.warn ( "Mappatura non trovata per codice " + codiceSuCatalog.getCod () + " per ente " + input.getEnte ().getCodFiscale () );
                    } else {
                        log.debug ( "il codice di versamento " + codiceSuCatalog.getCod () + " dell'ente " +
                            input.getEnte ().getCodFiscale () + " e' stato mappato da ID " +
                            codiceSuCatalog.getId () + " a ID " + mappato.getId () );

                        codiciVersamentoMappati.add ( mappato );
                    }
                }

                Collections.sort ( codiciVersamentoMappati, new CodiceVersamentoDtoComparator () );
                input.getCodiciVersamento ().clear ();
                input.getCodiciVersamento ().addAll ( codiciVersamentoMappati );
            }
        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

    }

    private void filtraCodiciVersamentoPerVisibilita ( ProfilazioneEpayPaCatalogSrvDto output ) {
        Integer idREDS;
        try {
            idREDS = accessoDad.findIdTipoPagamentoREDS ();
        } catch ( PersistenceException e ) {
            log.error ( "errore nel filtraggio dei codici versamento", e );
            throw new RuntimeException ( "errore nel filtraggio dei codici versamento", e );
        }

        if ( idREDS != null ) {

            output.getCodiciVersamentoVisibili ().clear ();

            for ( CodiceVersamentoDto cv: output.getCodiciVersamento () ) {
                if ( cv.getIdTipoPagamento () != null && cv.getIdTipoPagamento ().equals ( idREDS ) ) {
                    // filtra
                    log.debug ( "nascondo codice versamento REDS " + cv.getId () );
                } else {
                    output.getCodiciVersamentoVisibili ().add ( cv );
                }
            }
        }
    }
    
    
    private void filtraCodiciVersamentoPerListeDiCarico ( ProfilazioneEpayPaCatalogSrvDto output ) {
    	output.getCodiciVersamentoListeDiCarico().clear();
    	 Integer idTipoPagamentoReds;
    	 Integer idTipoPagamentoMabl;
    	 Integer idTipoPagamentoPS;
    	 
    	 try {
    		 idTipoPagamentoReds = accessoDad.findIdTipoPagamento ( TipoPagamentoType.REDS.value());
    		 idTipoPagamentoMabl = accessoDad.findIdTipoPagamento ( TipoPagamentoType.MABL.value());
    		 idTipoPagamentoPS = accessoDad.findIdTipoPagamento ( TipoPagamentoType.PS.value());
         } catch ( PersistenceException e ) {
             log.error ( "errore nel filtraggio dei codici versamento", e );
             throw new RuntimeException ( "errore nel filtraggio dei codici versamento", e );
         }
    	 
    	  if ( idTipoPagamentoReds != null && idTipoPagamentoMabl != null && idTipoPagamentoPS != null) {

              for ( CodiceVersamentoDto cv:output.getCodiciVersamento() ) {
                  if ( cv.getIdTipoPagamento () != null && 
                		  (cv.getIdTipoPagamento ().equals ( idTipoPagamentoReds ) || 
                				  cv.getIdTipoPagamento ().equals ( idTipoPagamentoMabl ) ||
                				  cv.getIdTipoPagamento ().equals ( idTipoPagamentoPS ))) {
                      // filtra
                      log.debug ( "nascondo codici versamento "+cv.getIdTipoPagamento ()+" "+ cv.getId () );
                  } else {
                	  output.getCodiciVersamentoListeDiCarico().add ( cv );
                  }
              }
          }
    	 
    	
    }


}
