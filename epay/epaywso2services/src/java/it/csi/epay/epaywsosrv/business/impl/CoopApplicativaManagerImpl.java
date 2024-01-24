/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaywsosrv.business.impl;

import it.csi.epay.epaywsosrv.business.CoopApplicativaManager;
import it.csi.epay.epaywsosrv.exception.BusinessException;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.exception.coopPec.CoopPecException;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.*;
import it.csi.epay.epaywsosrv.persistence.dao.impl.EPaywsoTApplicativoDaoImpl;
import it.csi.epay.epaywsosrv.persistence.dao.impl.EPaywsoTCodiceVersamentoDaoImpl;
import it.csi.epay.epaywsosrv.persistence.dao.impl.EPaywsoTEnteDaoImpl;
import it.csi.epay.epaywsosrv.persistence.dao.impl.cooppec.EPaywsoDErroreDaoImpl;
import it.csi.epay.epaywsosrv.persistence.dao.impl.cooppec.EPaywsoTCodiceVersamentoTempDaoImpl;
import it.csi.epay.epaywsosrv.persistence.dao.impl.cooppec.EPaywsoTEnteTempDaoImpl;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTApplicativo;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTCodiceVersamento;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEnte;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoDErrore;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTCodiceVersamentoTemp;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTEnteTemp;
import it.csi.epay.epaywsosrv.util.CostantiErrori;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static it.csi.epay.epaywsosrv.util.TempUtility.*;


@Stateless
@TransactionManagement ( TransactionManagementType.BEAN )
public class CoopApplicativaManagerImpl implements CoopApplicativaManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );
    
    private final String DESCR_APP_TEMPORANEO = "Applicativo temporaneo";
    private final String DESCR_APP_TEMPORANEO_SHORT = "TMP";
    

    @Inject
    private EPaywsoTEnteTempDaoImpl enteTempRepository;

    @Inject
    private EPaywsoTEnteDaoImpl enteRepository;

    @Inject
    private EPaywsoDErroreDaoImpl erroreRepository;

    @Inject
    private EPaywsoTCodiceVersamentoDaoImpl codiceVersamentoRepository;

    @Inject
    private EPaywsoTCodiceVersamentoTempDaoImpl codiceVersamentoTempRepository;

    @Inject
    private EPaywsoTApplicativoDaoImpl applicativoRepository;

    @Resource
    private UserTransaction transaction;

    //OK
    @Override
    public ResponseType aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest aggiornaCodiceVersamentoRequest ) throws PersistenceException {

        ResponseType response = returnStandardSuccessfulOutput ();

        if ( null == aggiornaCodiceVersamentoRequest || !StringUtils.isNotBlank ( aggiornaCodiceVersamentoRequest.getIdOperazione () ) ) {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
        EPaywsoTCodiceVersamentoTemp codiceVersamentoTemp = null;

        try {
            transaction.begin ();

            String azione = "";
            ProtocolloAggiornamentoAzioneType azioneRequest = ( null != aggiornaCodiceVersamentoRequest.getProtocolloAggiornamentoAzione () )
                            ? aggiornaCodiceVersamentoRequest.getProtocolloAggiornamentoAzione () : null;

            if ( null != azioneRequest ) {

                switch ( azioneRequest ) {
                case INSERIMENTO :
                    azione = AZIONE_INSERIMENTO;
                    break;
                case MODIFICA :
                    azione = AZIONE_MODIFICA;
                    break;
                case CANCELLAZIONE :
                    azione = AZIONE_CANCELLAZIONE;
                    break;
                default :
                    throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                        "Azione non riconosciuta" );
                }
            } else {
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    "Azione non presente" );
            }

            if ( null == aggiornaCodiceVersamentoRequest.getCodiceVersamento ()
                || !StringUtils.isNotBlank ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getCFEnte () ) ) {
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "Codice fiscale ente non presente" ) );
            }

            EPaywsoTEnte enteEntity = enteRepository.findOneByCodFiscale ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getCFEnte (), new Timestamp ( new Date().getTime() ) );
            codiceVersamentoTemp = createCodiceVersamento ( enteEntity, aggiornaCodiceVersamentoRequest.getCodiceVersamento (), azione,
                aggiornaCodiceVersamentoRequest.getIdOperazione () );

            if ( null != codiceVersamentoTemp ) {
            	
//            	try {
//                    Integer idEnte = enteRepository.findIdEnteByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );
//
//                    EPaywsoTCodiceVersamento cblTCodiceVersamento
//                        = codiceVersamentoRepository.findOneByCodAndEnte ( codiceVersamentoTemp.getCodVersamento (), idEnte );
//                    if ( AZIONE_MODIFICA == azione || AZIONE_CANCELLAZIONE == azione ) {
//                        if ( null == cblTCodiceVersamento ) {
//                            logger.error ( "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
//                            try {
//                                transaction.rollback ();
//                            } catch ( IllegalStateException | SecurityException | SystemException e ) {
//                                logger.error ( "Errore in fase di rollback: ", e );
//                            }
//                            return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
//                                "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
//                        }
//                    }
//                } catch ( Exception e ) {
//                    logger.error ( "Errore in fase di controllo del catalogo: " + codiceVersamentoTemp.getCodVersamento (), e );
//                    try {
//                        transaction.rollback ();
//                    } catch ( IllegalStateException | SecurityException | SystemException er ) {
//                        logger.error ( "Errore in fase di rollback: ", er );
//                    }
//                    return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
//                        "Codice Versamento" + codiceVersamentoTemp.getCodVersamento () + "non censito." );
//                }
//            	
            	
                codiceVersamentoTempRepository.persist ( codiceVersamentoTemp );
                transaction.commit ();
            } else {
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    "Non e' stato possibile recuperare la entity da salvare" );
            }
        } catch ( Throwable t ) {
            try {
                transaction.rollback ();
            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                logger.error ( "Errore in fase di rollback: ", e );
            }
            
            if(t instanceof CoopPecException) {
                logger.error ( t );
                String stringErrorCode = Integer.toString ( ( (CoopPecException) t ).getErrorCode () );
                response = returnErrorOutput ( stringErrorCode );
            } else {
                logger.error ( "Errore in fase di aggiornaCodiceVersamento: ", t );
                response = returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            }
        }
        
        return response;
    }

    //OK
    @Override
    public ResponseType aggiornaEnte ( AggiornaEnteRequest aggiornaEnteRequest ) throws PersistenceException {
        if ( null != aggiornaEnteRequest &&
            StringUtils.isNotBlank ( aggiornaEnteRequest.getIdOperazione () ) &&
            null != aggiornaEnteRequest.getEnte () &&
            StringUtils.isNotBlank ( aggiornaEnteRequest.getEnte ().getCodiceFiscale () ) ) {
            try {
                transaction.begin ();

                EPaywsoTEnteTemp enteTemp = buildEnteTemp ( aggiornaEnteRequest );
                if ( null != aggiornaEnteRequest.getCodiciVersamento () && null != aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ()
                    && !CollectionUtils.isEmpty ( aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ().getCodiceVersamento () ) ) {
                    List<EPaywsoTCodiceVersamentoTemp> codiceVersamentoTempList = creaCodiciVersamento ( enteTemp,
                        aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ().getCodiceVersamento () );
                    if ( !CollectionUtils.isEmpty ( codiceVersamentoTempList ) ) {
                        for ( EPaywsoTCodiceVersamentoTemp codiceVersamentoTemp: codiceVersamentoTempList ) {
                            try {
                                Integer idEnte = enteRepository.findIdEnteByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );

                                List<EPaywsoTCodiceVersamento> codiceVersamento = codiceVersamentoRepository.findAllByCodAndEnteAttivo ( codiceVersamentoTemp.getCodVersamento (), idEnte );
                                if ( CollectionUtils.isEmpty ( codiceVersamento )) {
                                    transaction.rollback ();
                                    logger.error ( "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
                                    try {
                                        transaction.rollback ();
                                    } catch ( IllegalStateException | SecurityException | SystemException e ) {
                                        logger.error ( "Errore in fase di rollback: ", e );
                                    }
                                    return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                        "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
                                }
                            } catch ( Exception e ) {
                                transaction.rollback ();
                                logger.error ( "Errore in fase di controllo del catalogo: " + codiceVersamentoTemp.getCodVersamento (), e );
                                try {
                                    transaction.rollback ();
                                } catch ( IllegalStateException | SecurityException | SystemException er ) {
                                    logger.error ( "Errore in fase di rollback: ", er );
                                }
                                return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                    "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
                            }
                        }
                        codiceVersamentoTempRepository.save ( codiceVersamentoTempList );
                    }
                }
                enteTempRepository.persist ( enteTemp );
                
                transaction.commit ();
                
                return returnStandardSuccessfulOutput ();
            } catch ( Throwable t ) {
                try {
                    transaction.rollback ();
                } catch ( IllegalStateException | SecurityException | SystemException e ) {
                    logger.error ( "Errore in fase di rollback: ", e );
                }
                logger.error ( "Errore in fase di salvataggio della tabella CblTEnteTemp: ", t );
                return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            }
        } else {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
    }

    //OK
    @Override
    public ResponseType annullaOperazione ( AnnullaOperazioneRequest annullaOperazioneRequest ) throws PersistenceException {
        if ( null != annullaOperazioneRequest && StringUtils.isNotBlank ( annullaOperazioneRequest.getIdOperazione () ) ) {
            EPaywsoTEnteTemp enteTemp = null;
            List<EPaywsoTCodiceVersamentoTemp> codiceVersamentoList = null;
            try {
                transaction.begin ();

                enteTemp = enteTempRepository.findOneByIdOperazione ( annullaOperazioneRequest.getIdOperazione () );

                codiceVersamentoList = codiceVersamentoTempRepository.findAllByIdOperazione ( annullaOperazioneRequest.getIdOperazione () );
                
                if ( null != enteTemp ) {
                    ResponseType enteTempResp = deleteEntityEnteTemp ( enteTempRepository, enteTemp );
                    if ( enteTempResp.getResult ().getCodice ().equals ( CostantiErrori.WS_ESITO_OK_DEFAULT ) ) {
                        //if ( !CollectionUtils.isEmpty ( catalogoTempList ) ) {
                        //    return deleteEntityTemp ( catalogoTempRepository, catalogoTempList );
                        //}
                    }
                    transaction.commit ();
                    return enteTempResp;
                    //} else if ( !CollectionUtils.isEmpty ( catalogoTempList ) ) {
                    //    return deleteEntityTemp ( catalogoTempRepository, catalogoTempList );
                } else if ( !CollectionUtils.isEmpty ( codiceVersamentoList ) ) {
                    ResponseType item = deleteEntityTemp ( codiceVersamentoTempRepository, codiceVersamentoList );
                    
                    transaction.commit ();
                    return item;
                } else {
                    transaction.commit ();
                    return returnErrorResponseType ( CostantiErrori.ERRORE_DATI_MANCANTI, String.format ( "L'operazione con id %s non e' presente su base dati.", annullaOperazioneRequest.getIdOperazione () ) );
                }
            } catch ( Throwable t ) {
                try {
                    transaction.rollback ();
                } catch ( IllegalStateException | SecurityException | SystemException e ) {
                    logger.error ( "Errore in fase di rollback: ", e );
                }
                logger.error ( "Errore in fase di annullaOperazione : ", t );
                return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            }
        } else {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
    }

    //OK
    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    private ResponseType deleteEntityTemp ( EPaywsoTCodiceVersamentoTempDaoImpl repository, List<EPaywsoTCodiceVersamentoTemp> itemList )
                    throws PersistenceException {
        try {
            for ( EPaywsoTCodiceVersamentoTemp cblTCodiceVersamentoTemp: itemList ) {
                repository.remove ( cblTCodiceVersamentoTemp );
            }
            return returnStandardSuccessfulOutput ();
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di deleteEntityTemp: ", t );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
    }

    private void completeCodVers ( EPaywsoTCodiceVersamento codiceVersamento, EPaywsoTCodiceVersamentoTemp codiceVersamentoTemp )
                    throws PersistenceException, NumberFormatException, CoopPecException {

        Integer ente = enteRepository.findIdEnteByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte() );

        if ( ente == null ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ),
                String.format ( "Ente %s non riconosciuto", codiceVersamentoTemp.getCodFiscaleEnte() ) );
        }
        
        EPaywsoTEnte enteCurr = enteRepository.findOne ( ente );
        
        logger.info("Codice modalita integrazione TEMP " + codiceVersamentoTemp.getCodiceModalitaIntegrazione ());
        
        if(StringUtils.isEmpty ( codiceVersamentoTemp.getCodiceModalitaIntegrazione()) ) {
            
            String codVersamentoPadre = codiceVersamentoTemp.getCodVersamento ().substring ( 0, 3 ) + "0";
            
            logger.debug("Codice modalita integrazione EREDITA DA CV PADRE " + codVersamentoPadre);
            
            List<EPaywsoTCodiceVersamento> cvPadre = codiceVersamentoRepository.findAllByIdEnteAndCodiceVersamento ( enteCurr.getIdEnte (), codVersamentoPadre );
            
            if(cvPadre != null && cvPadre.size () > 0) {
                codiceVersamentoTemp.setCodiceModalitaIntegrazione(cvPadre.get ( 0 ).getEpaywsoTApplicativo ().getCodiceModalitaIntegrazione ());
            }
        }
        
        if (ModalitaDiIntegrazioneType.PEC.toString().equalsIgnoreCase(codiceVersamentoTemp.getCodiceModalitaIntegrazione())) {
            
            logger.info("################### PEC ############################");
            
        	try {
                EPaywsoTApplicativo applicativo = applicativoRepository.findOneByIdEnteAndCodiceModalitaIntegrazione ( ente, new Timestamp ( new Date ().getTime () ), ModalitaDiIntegrazioneType.PEC.toString());
        		codiceVersamento.setEpaywsoTApplicativo(applicativo);
                logger.warn("CODICE VERSAMENTO CON MODALITA A " + applicativo.getCodiceModalitaIntegrazione ());
        	} catch(PersistenceException e) {
        		logger.error(e);
        		logger.warn("record PEC (applicativo) non trovato");
        			try {
        			    EPaywsoTApplicativo applicativo = applicativoRepository.findOneByIdEnteAndDescrizioneAndCodiceModalitaIntegrazione ( ente, new Timestamp ( new Date ().getTime () ), DESCR_APP_TEMPORANEO + "%", ModalitaDiIntegrazioneType.PEC.toString());
        				codiceVersamento.setEpaywsoTApplicativo(applicativo);
                        logger.warn("CODICE VERSAMENTO CON MODALITA A " + applicativo.getCodiceModalitaIntegrazione ());
        			} catch(PersistenceException e1) {
        					logger.warn("record temporaneo (applicativo) non trovato");
        				}
	        	
        	}
        } else {
            logger.info("################### NON PEC ############################");
            
            try {
                EPaywsoTApplicativo applicativo = applicativoRepository.findOneByIdEnteAndCodiceModalitaIntegrazione ( ente, new Timestamp ( new Date ().getTime () ), ModalitaDiIntegrazioneType.SRV.toString());
                codiceVersamento.setEpaywsoTApplicativo(applicativo);
                logger.warn("CODICE VERSAMENTO CON MODALITA A " + applicativo.getCodiceModalitaIntegrazione ());
            } catch(PersistenceException e) {
                logger.error(e);
                logger.warn("record SRV (applicativo) non trovato");
                    try {
                        EPaywsoTApplicativo applicativo = applicativoRepository.findOneByIdEnteAndDescrizioneAndCodiceModalitaIntegrazione ( ente, new Timestamp ( new Date ().getTime () ), DESCR_APP_TEMPORANEO + "%", ModalitaDiIntegrazioneType.SRV.toString());
                        codiceVersamento.setEpaywsoTApplicativo(applicativo);
                        logger.warn("CODICE VERSAMENTO CON MODALITA A " + applicativo.getCodiceModalitaIntegrazione ());
                    } catch(PersistenceException e1) {
                            logger.warn("record temporaneo (applicativo) non trovato");
                    }
            }
        }
    }
    

    //OK
    private void confermaCodiceVersamento ( EPaywsoTCodiceVersamentoTemp codiceVersamentoTemp ) throws CoopPecException, PersistenceException, BusinessException {
//         creare lista 
    	EPaywsoTCodiceVersamento codiceVersamento = null;
    	
    	List<EPaywsoTCodiceVersamento> codiciVersamento= new LinkedList<EPaywsoTCodiceVersamento>();
        

        Integer idEnte = enteRepository.findIdEnteByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );

        try {
            switch ( codiceVersamentoTemp.getAzione () ) {

            case AZIONE_INSERIMENTO :
//                EPaywsoTCodiceVersamento codiceVersamentoOriginale = codiceVersamentoRepository.findOneByCodAndEnte ( codiceVersamentoTemp.getCodVersamento (), idEnte );

                codiceVersamento = mapCblTCodiceVersamentoSenzaIdEnte ( new EPaywsoTCodiceVersamento (), codiceVersamentoTemp );
                
                completeCodVers(codiceVersamento,codiceVersamentoTemp);
               
                
                EPaywsoTEnte enteIns = enteRepository.findOneByCodFiscale( codiceVersamentoTemp.getCodFiscaleEnte () );
                //Succede quando la completeCodVers fallisce per via di PEC->SRV o SRV->PEC e non completa con T APPLICATIVO
                
                if(codiceVersamento.getEpaywsoTApplicativo() != null) {
                    codiceVersamento.getEpaywsoTApplicativo ().setEPaywsoTEnte ( enteIns );

                    /*
                     * Parte deprecata grazie alla sequence
                    if ( null != codiceVersamentoOriginale ) {
                        codiceVersamento.setIdCodiceVersamento ( codiceVersamentoOriginale.getIdCodiceVersamento () + 1 );
    
                        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                        //codiceVersamento.setDataInserimento ( codiceVersamentoOriginale.getDataInserimento () );
                    } else {
                        codiceVersamento.setIdCodiceVersamento (codiceVersamentoRepository.findCodVersIdMax() + 1);
                    }*/
                }
                codiciVersamento.add(codiceVersamento);
                logger.debug ( "ID per codice versamento : " + codiceVersamento.getIdCodiceVersamento ());

                break;
            case AZIONE_MODIFICA :

//                codiceVersamento = codiceVersamentoRepository.findOneByCodAndEnte ( codiceVersamentoTemp.getCodVersamento (), idEnte);
            	codiciVersamento= (codiceVersamentoRepository.findAllByCodAndEnteAttivo ( codiceVersamentoTemp.getCodVersamento (), idEnte));
            	EPaywsoTEnte enteCvMod = enteRepository.findOneByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );
                
//            	codiceVersamento = mapCblTCodiceVersamentoSenzaIdEnte ( codiceVersamento, codiceVersamentoTemp );
//            	 completeCodVers(codiceVersamento,codiceVersamentoTemp);
//            	 codiceVersamento.getEpaywsoTApplicativo ().setEPaywsoTEnte ( enteCvMod );
            	if (null!= codiciVersamento && !codiciVersamento.isEmpty())
            	{
            		for(EPaywsoTCodiceVersamento temp: codiciVersamento)
                	{
                		temp= mapCblTCodiceVersamentoSenzaIdEnte ( temp, codiceVersamentoTemp );
                		 completeCodVers(temp,codiceVersamentoTemp);
                		 temp.getEpaywsoTApplicativo ().setEPaywsoTEnte ( enteCvMod );
                	}
            	}
            	else
            	{
            		codiciVersamento= new LinkedList<EPaywsoTCodiceVersamento>();
            		codiceVersamento = mapCblTCodiceVersamentoSenzaIdEnte ( new EPaywsoTCodiceVersamento (), codiceVersamentoTemp );
                    completeCodVers(codiceVersamento,codiceVersamentoTemp);
                    codiciVersamento.add(codiceVersamento);
            	}
            	
                

                break;

            case AZIONE_CANCELLAZIONE :
                
//                codiceVersamento = codiceVersamentoRepository.findOneByCodAndEnte ( codiceVersamentoTemp.getCodVersamento (), idEnte);
                codiciVersamento= (codiceVersamentoRepository.findAllByCodAndEnteAttivo ( codiceVersamentoTemp.getCodVersamento (), idEnte));
                EPaywsoTEnte enteCvDel = enteRepository.findOneByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );

//                codiceVersamento = mapCblTCodiceVersamentoSenzaIdEnte ( codiceVersamento, codiceVersamentoTemp );
                
//                codiceVersamento.getEpaywsoTApplicativo ().setEPaywsoTEnte ( enteCvDel );

                //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                //codiceVersamento.setFlagAnnullato ( true );
                
                //Visto che non c'e' il flag annullato e la data di fine non mi arriva da servizio...
//                codiceVersamento.setDtFineValidita (new Timestamp ( new Date ().getTime () ));
                
                for(EPaywsoTCodiceVersamento temp: codiciVersamento)
            	{
            		temp= mapCblTCodiceVersamentoSenzaIdEnte ( temp, codiceVersamentoTemp );
            		 temp.getEpaywsoTApplicativo ().setEPaywsoTEnte ( enteCvDel );
            		 temp.setDtFineValidita (new Timestamp ( new Date ().getTime () ));
            	}
                
                break;
            default :
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ),
                    String.format ( "Azione %s non riconosciuta", codiceVersamentoTemp.getAzione () ) );

            }


            for(EPaywsoTCodiceVersamento temp: codiciVersamento)
            {
              
              if(temp.getEpaywsoTApplicativo() != null) {
                  codiceVersamentoRepository.persist ( temp );
                  codiceVersamentoTempRepository.remove ( codiceVersamentoTemp );
                  
                  if (DESCR_APP_TEMPORANEO.equalsIgnoreCase(temp.getEpaywsoTApplicativo().getDescrizione())) {
                  	throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ),
                              String.format ( "E necessario integrare la configurazione temporanea creata (epaywso_t_applicativo) per il codice versamento %s afferente all'ente %s", 
                            		  temp.getCodVersamento(), temp.getEpaywsoTApplicativo().getEPaywsoTEnte().getCodFiscaleEnte()));
                  }
              } else {
                  throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ),
                      String.format ( "E necessario gestire la configurazione temporanea creata (epaywso_t_ente) per una discrepanza sul codice modalita integrazione afferente all'ente %s",
                    		  codiceVersamentoTemp.getCodFiscaleEnte ()));
              }
            	
            }
//            
            
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di confermaCodiceVersamento : ", t );
            throw new BusinessException(t.getMessage());
        }
    }

    //OK
    private void confermaEnte ( EPaywsoTEnteTemp enteTempIn ) throws PersistenceException {
        EPaywsoTEnte enteTemp = buildEnte ( enteTempIn );
        EPaywsoTEnte enteToPersist = enteRepository.findOneByCodFiscale ( enteTemp.getCodFiscaleEnte (), new Timestamp ( new Date ().getTime () ) );
        
        boolean newEnte = null == enteToPersist;
        
        if ( newEnte ) {
            enteToPersist = new EPaywsoTEnte ();
            //Parte deprecata grazie alla sequence
            // enteToPersist.setIdEnte ( enteRepository.findIdEnteMax()+1 );
        }
        
        enteToPersist.setCodFiscaleEnte ( enteTemp.getCodFiscaleEnte () );
        enteToPersist.setDenominazione ( enteTemp.getDenominazione () );
            //enteOld.setEmail ( enteTemp.getEmail () );
        enteToPersist.setDtInizioValidita ( enteTemp.getDtInizioValidita () );
        enteToPersist.setDtFineValidita ( enteTemp.getDtFineValidita () );

        enteRepository.persist ( enteToPersist );
        
        if ( newEnte ) {
        	enteToPersist = enteRepository.findOneByCodFiscale(enteToPersist.getCodFiscaleEnte());
            EPaywsoTApplicativo applicativo = new EPaywsoTApplicativo();
            //Parte deprecata grazie alla sequence
            // applicativo.setIdApplicativo(applicativoRepository.findIdApplicativoMax() + 1);
            applicativo.setCodiceModalitaIntegrazione(null);
            applicativo.setDescrizione(DESCR_APP_TEMPORANEO);
            applicativo.setDtFineValidita(null);
            applicativo.setDtInizioValidita(new Timestamp ( new Date ().getTime () ));
            applicativo.setEPaywsoTEnte(enteToPersist);
            applicativo.setMsInbound(DESCR_APP_TEMPORANEO_SHORT);
            applicativo.setMsOutbound(DESCR_APP_TEMPORANEO_SHORT);
            applicativo.setUtente("SYSTEM");
            applicativoRepository.persist(applicativo);
        }

        //Elaborazione CodiciVersamento
        List<EPaywsoTCodiceVersamento> codVersList = codiceVersamentoRepository.findByIdEnte ( enteToPersist.getIdEnte () );
        List<EPaywsoTCodiceVersamentoTemp> codVersTempList = codiceVersamentoTempRepository.findAllByIdOperazione ( enteTempIn.getIdOperazione () );
        List<EPaywsoTCodiceVersamento> codVersListToUpdate = elaboraCodiciVersamento ( codVersList, codVersTempList );

        if ( !CollectionUtils.isEmpty ( codVersListToUpdate ) ) {
            for ( EPaywsoTCodiceVersamento EPaywsoTCodiceVersamento: codVersListToUpdate ) {
                codiceVersamentoRepository.persist ( EPaywsoTCodiceVersamento );
            }
        }
        if ( !CollectionUtils.isEmpty ( codVersTempList ) ) {
            codiceVersamentoTempRepository.delete ( codVersTempList );
        }

        enteTempRepository.remove ( enteTempIn );
    }

    //OK
    @Override
    public ResponseType confermaOperazione ( ConfermaOperazioneRequest confermaOperazioneRequest ) throws PersistenceException {
        ResponseType response = returnStandardSuccessfulOutput ();
        try {
            transaction.begin ();

            EPaywsoTEnteTemp enteTemp = enteTempRepository.findOneByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );
            List<EPaywsoTCodiceVersamentoTemp> codiceVersamentoTemp
                = codiceVersamentoTempRepository.findAllByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );

            if ( null != enteTemp ) {
                confermaEnte ( enteTemp );
            } else if ( null != codiceVersamentoTemp ) {
                for ( EPaywsoTCodiceVersamentoTemp cblTCodiceVersamentoTemp: codiceVersamentoTemp ) {
                    confermaCodiceVersamento ( cblTCodiceVersamentoTemp );
                }
            } else {
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "L'operazione con id %s non e' presente su base dati.", confermaOperazioneRequest.getIdOperazione () ) );
            }
            
            transaction.commit ();
            
            return returnStandardSuccessfulOutput ();
        } catch (BusinessException be)  {
        	try {
				transaction.commit ();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicMixedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 response = returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        	 response.getResult().setMessaggio(be.getErrorCod());
        } catch ( Throwable t ) {
            try {
                transaction.rollback ();
            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                logger.error ( "Errore in fase di rollback: ", e );
            }
            logger.error ( "Errore in fase di confermaOperazione: ", t );
            response = returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
        
        return response;
    }

    //OK
    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    private ResponseType deleteEntityEnteTemp ( EPaywsoTEnteTempDaoImpl repository, EPaywsoTEnteTemp entityTemp ) throws PersistenceException {
        try {
            repository.remove ( entityTemp );
            return returnStandardSuccessfulOutput ();
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di deleteEntityEnteTemp : ", t );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
    }

    private ResponseType returnErrorOutput ( String codiceErrore ) throws PersistenceException {    
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();

        EPaywsoDErrore errore = erroreRepository.findByCodiceErrore ( codiceErrore );

        if ( null == errore || !StringUtils.isNotBlank ( errore.getDescrizioneErrore () ) ) {
            result.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            result.setMessaggio ( "Si e' verificato un errore durante l'elaborazione" );
            logger.warn (
                String.format ( "Non e' stato possibile recuperare l'errore associato al codice %s, verra' utilizzato un errore di default.", codiceErrore ) );
        } else {
            result.setCodice ( codiceErrore );
            result.setMessaggio ( errore.getDescrizioneErrore () );
        }

        responseType.setResult ( result );
        return responseType;
    }

    private ResponseType returnErrorResponseType ( String codiceErrore, String descrizione ) {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();

        if ( StringUtils.isNotBlank ( codiceErrore ) && StringUtils.isNotBlank ( descrizione ) ) {
            result.setCodice ( codiceErrore );
            result.setMessaggio ( descrizione );
        } else {
            result.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            result.setMessaggio ( "Si e' verificato un errore durante l'elaborazione" );
        }
        responseType.setResult ( result );
        return responseType;
    }

    private ResponseType returnStandardSuccessfulOutput () {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();
        result.setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        result.setMessaggio ( "Operazione completata correttamente" );
        responseType.setResult ( result );
        return responseType;
    }

}
