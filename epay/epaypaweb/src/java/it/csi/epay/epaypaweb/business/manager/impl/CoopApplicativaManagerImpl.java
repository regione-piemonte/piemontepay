/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.business.manager.impl;

import it.csi.epay.epaypaweb.business.manager.CoopApplicativaManager;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.coopPec.CoopPecException;
import it.csi.epay.epaypaweb.facade.cooppec.dto.*;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaDTipoPagamentoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTCodiceVersamentoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTEnteDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.cooppec.EpaypaDErroreDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.cooppec.EpaypaTCodiceVersamentoTempDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.cooppec.EpaypaTEnteTempDaoImpl;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaDErrore;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaTCodiceVersamentoTemp;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaTEnteTemp;
import it.csi.epay.epaypaweb.util.CostantiErrori;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

import static it.csi.epay.epaypaweb.util.TempUtility.*;


@Stateless
@TransactionManagement ( TransactionManagementType.BEAN )
public class CoopApplicativaManagerImpl implements CoopApplicativaManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Inject
    private EpaypaTEnteTempDaoImpl enteTempRepository;

    @Inject
    private EpaypaTEnteDaoImpl enteRepository;

    @Inject
    private EpaypaDErroreDaoImpl erroreRepository;

    @Inject
    private EpaypaTCodiceVersamentoDaoImpl codiceVersamentoRepository;

    @Inject
    private EpaypaTCodiceVersamentoTempDaoImpl codiceVersamentoTempRepository;

    @Inject
    private EpaypaDTipoPagamentoDaoImpl tipoPagamentoRepository;

    @Resource
    private UserTransaction transaction;

    //OK
    @Override
    public ResponseType aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest aggiornaCodiceVersamentoRequest ) throws PersistenceException {

        ResponseType response = returnStandardSuccessfulOutput ();

        if ( null == aggiornaCodiceVersamentoRequest || !StringUtils.isNotBlank ( aggiornaCodiceVersamentoRequest.getIdOperazione () ) ) {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
        EpaypaTCodiceVersamentoTemp codiceVersamentoTemp = null;

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

            EpaypaTEnte enteEntity = enteRepository.findOneByCodFiscale ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getCFEnte () );

            EpaypaDTipoPagamento tipoPagamento = ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getTipoPagamento () != null )
                            ? tipoPagamentoRepository.findOneByCodice ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getTipoPagamento ().name () )
                            : null;

            // logger.debug ( "calcolo TP: " + aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getTipoPagamento () + " -> " + tipoPagamento );

            codiceVersamentoTemp = createCodiceVersamento ( enteEntity, aggiornaCodiceVersamentoRequest.getCodiceVersamento (), azione,
                aggiornaCodiceVersamentoRequest.getIdOperazione (), tipoPagamento );

            if ( null != codiceVersamentoTemp ) {

                try {
                    Integer idEnte = enteRepository.findIdEnteByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );

                    EpaypaTCodiceVersamento cblTCodiceVersamento
                        = codiceVersamentoRepository.findOneByCodAndEnte ( codiceVersamentoTemp.getCodVersamento (), idEnte );
                    if ( AZIONE_MODIFICA.equals ( azione ) || AZIONE_CANCELLAZIONE.equals ( azione ) ) {
                        if ( null == cblTCodiceVersamento ) {
                            logger.error ( "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
                            try {
                                transaction.rollback ();
                            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                                logger.error ( "Errore in fase di rollback: ", e );
                            }
                            return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
                        }
                    }
                } catch ( Exception e ) {
                    logger.error ( "Errore in fase di controllo del catalogo: " + codiceVersamentoTemp.getCodVersamento (), e );
                    try {
                        transaction.rollback ();
                    } catch ( IllegalStateException | SecurityException | SystemException e1 ) {
                        logger.error ( "Errore in fase di rollback: ", e1 );
                    }
                    return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                        "Codice Versamento" + codiceVersamentoTemp.getCodVersamento () + "non censito." );
                }

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

            if ( t instanceof CoopPecException ) {
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

                EpaypaTEnteTemp enteTemp = buildEnteTemp ( aggiornaEnteRequest );

                if ( null != aggiornaEnteRequest.getCodiciVersamento () && null != aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ()
                    && !CollectionUtils.isEmpty ( aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ().getCodiceVersamento () ) ) {
                    List<EpaypaTCodiceVersamentoTemp> codiceVersamentoTempList = creaCodiciVersamento ( enteTemp,
                        aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ().getCodiceVersamento () );
                    if ( !CollectionUtils.isEmpty ( codiceVersamentoTempList ) ) {
                        for ( EpaypaTCodiceVersamentoTemp codiceVersamentoTemp: codiceVersamentoTempList ) {
                            try {
                                Integer idEnte = enteRepository.findIdEnteByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );

                                EpaypaTCodiceVersamento codiceVersamento
                                    = codiceVersamentoRepository.findOneByCodAndEnte ( codiceVersamentoTemp.getCodVersamento (), idEnte );
                                if ( null == codiceVersamento ) {
                                    logger.error ( "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
                                    return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                        "Codice Versamento " + codiceVersamentoTemp.getCodVersamento () + " non censito." );
                                }
                            } catch ( Exception e ) {
                                logger.error ( "Errore in fase di controllo del catalogo: " + codiceVersamentoTemp.getCodVersamento (), e );
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
                logger.error ( "Errore in fase di salvataggio della tabella EnteTemp: ", t );
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
            EpaypaTEnteTemp enteTemp = null;
            List<EpaypaTCodiceVersamentoTemp> codiceVersamentoList = null;
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
                    return returnErrorResponseType ( CostantiErrori.ERRORE_DATI_MANCANTI,
                        String.format ( "L'operazione con id %s non e' presente su base dati.", annullaOperazioneRequest.getIdOperazione () ) );
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
    private ResponseType deleteEntityTemp ( EpaypaTCodiceVersamentoTempDaoImpl repository, List<EpaypaTCodiceVersamentoTemp> itemList )
                    throws PersistenceException {
        try {
            for ( EpaypaTCodiceVersamentoTemp cblTCodiceVersamentoTemp: itemList ) {
                repository.remove ( cblTCodiceVersamentoTemp );
            }
            return returnStandardSuccessfulOutput ();
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di deleteEntityTemp: ", t );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
    }

    //OK
    private void confermaCodiceVersamento ( EpaypaTCodiceVersamentoTemp codiceVersamentoTemp ) throws CoopPecException, PersistenceException {
        EpaypaTCodiceVersamento codiceVersamento = null;
        List<EpaypaTCodiceVersamento> codiceVersamentoList = null;

        Integer idEnte = enteRepository.findIdEnteByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );

        try {
            switch ( codiceVersamentoTemp.getAzione () ) {

            case AZIONE_INSERIMENTO :
                EpaypaTCodiceVersamento codiceVersamentoOriginale
                    = codiceVersamentoRepository.findOneByCodAndEnte ( codiceVersamentoTemp.getCodVersamento (), idEnte );

                codiceVersamento = mapCblTCodiceVersamentoSenzaIdEnte ( new EpaypaTCodiceVersamento (), codiceVersamentoTemp );
                EpaypaTEnte enteIns = enteRepository.findOneByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );
                codiceVersamento.setEpaypaTEnte ( enteIns );

                if ( null != codiceVersamentoOriginale ) {
                    codiceVersamento.setIdCodiceVersamento ( codiceVersamentoOriginale.getIdCodiceVersamento () );

                    //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                    //codiceVersamento.setDataInserimento ( codiceVersamentoOriginale.getDataInserimento () );
                }
                codiceVersamentoRepository.persist ( codiceVersamento );

                break;
            case AZIONE_MODIFICA :

                codiceVersamentoList = codiceVersamentoRepository.findAllByIdEnteAndCodiceVersamento ( idEnte,
                    codiceVersamentoTemp.getCodVersamento () );

                if ( null == codiceVersamentoList || codiceVersamentoList.size () != 1 ) {
                    throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                        String.format ( "Azione %s non riconosciuta", codiceVersamentoTemp.getAzione () ) );
                }

                codiceVersamento = codiceVersamentoList.get ( 0 );

                codiceVersamento = mapCblTCodiceVersamentoSenzaIdEnte ( codiceVersamento, codiceVersamentoTemp );
                EpaypaTEnte enteCvMod = enteRepository.findOneByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );
                codiceVersamento.setEpaypaTEnte ( enteCvMod );

                codiceVersamentoRepository.persist ( codiceVersamento );
                break;

            case AZIONE_CANCELLAZIONE :
                codiceVersamentoList = codiceVersamentoRepository.findAllByIdEnteAndCodiceVersamento ( idEnte,
                    codiceVersamentoTemp.getCodVersamento () );

                if ( null == codiceVersamentoList || codiceVersamentoList.size () != 1 ) {
                    throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                        String.format ( "Azione %s non riconosciuta", codiceVersamentoTemp.getAzione () ) );
                }

                codiceVersamento = codiceVersamentoList.get ( 0 );

                codiceVersamento = mapCblTCodiceVersamentoSenzaIdEnte ( codiceVersamento, codiceVersamentoTemp );
                EpaypaTEnte enteCvDel = enteRepository.findOneByCodFiscale ( codiceVersamentoTemp.getCodFiscaleEnte () );
                codiceVersamento.setEpaypaTEnte ( enteCvDel );

                //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                //codiceVersamento.setFlagAnnullato ( true );

                //LF 16/04/2019 - rimuovo fisicamente il codice versamento
                codiceVersamentoRepository.remove ( codiceVersamento );

                break;
            default :
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ),
                    String.format ( "Azione %s non riconosciuta", codiceVersamentoTemp.getAzione () ) );

            }

            codiceVersamentoTempRepository.remove ( codiceVersamentoTemp );
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di confermaCodiceVersamento : ", t );
        }
    }

    //OK
    private void confermaEnte ( EpaypaTEnteTemp enteTempIn ) throws PersistenceException {
        EpaypaTEnte enteTemp = buildEnte ( enteTempIn );

        EpaypaTEnte enteCurrent = enteRepository.findOneByCodFiscale ( enteTemp.getCodFiscaleEnte () );

        if ( null == enteCurrent ) {
            enteCurrent = new EpaypaTEnte ();
        }

        enteCurrent.setCodFiscaleEnte ( enteTemp.getCodFiscaleEnte () );
        enteCurrent.setDenominazione ( enteTemp.getDenominazione () );
        enteCurrent.setEmail ( enteTemp.getEmail () );
        enteCurrent.setCodGs1Gln ( enteTemp.getCodGs1Gln () );
        enteCurrent.setCodInterbancario ( enteTemp.getCodInterbancario () );
        enteCurrent.setLogo ( enteTemp.getLogo () );

        enteRepository.persist ( enteCurrent );

        //Elaborazione CodiciVersamento
        List<EpaypaTCodiceVersamento> codVersList = codiceVersamentoRepository.findByIdEnte ( enteCurrent.getIdEnte () );
        List<EpaypaTCodiceVersamentoTemp> codVersTempList = codiceVersamentoTempRepository.findAllByIdOperazione ( enteTempIn.getIdOperazione () );
        List<EpaypaTCodiceVersamento> codVersListToUpdate = elaboraCodiciVersamento ( codVersList, codVersTempList );

        if ( !CollectionUtils.isEmpty ( codVersListToUpdate ) ) {
            for ( EpaypaTCodiceVersamento epaypaTCodiceVersamento: codVersListToUpdate ) {
                codiceVersamentoRepository.persist ( epaypaTCodiceVersamento );
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

            EpaypaTEnteTemp enteTemp = enteTempRepository.findOneByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );
            List<EpaypaTCodiceVersamentoTemp> codiceVersamentoTemp
                = codiceVersamentoTempRepository.findAllByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );

            if ( null != enteTemp ) {
                confermaEnte ( enteTemp );
            } else if ( null != codiceVersamentoTemp ) {
                for ( EpaypaTCodiceVersamentoTemp cblTCodiceVersamentoTemp: codiceVersamentoTemp ) {
                    confermaCodiceVersamento ( cblTCodiceVersamentoTemp );
                }
            } else {
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "L'operazione con id %s non e' presente su base dati.", confermaOperazioneRequest.getIdOperazione () ) );
            }

            transaction.commit ();

            return returnStandardSuccessfulOutput ();
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
    private ResponseType deleteEntityEnteTemp ( EpaypaTEnteTempDaoImpl repository, EpaypaTEnteTemp entityTemp ) throws PersistenceException {
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

        EpaypaDErrore errore = erroreRepository.findByCodiceErrore ( codiceErrore );

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
