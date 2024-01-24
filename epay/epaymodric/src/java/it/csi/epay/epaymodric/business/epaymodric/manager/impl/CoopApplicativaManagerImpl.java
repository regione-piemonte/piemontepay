/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.AZIONE_CANCELLAZIONE;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.AZIONE_INSERIMENTO;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.AZIONE_MODIFICA;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.buildEnte;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.buildEnteTemp;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.creaCodiciVersamento;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.createCodiceVersamento;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.createRiferimentoContabile;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.elaboraCodiciVersamento;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.mapCatalogo;
import static it.csi.epay.epaymodric.business.epaymodric.manager.utils.TempUtility.mapCblTCodiceVersamento;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodric.business.epaymodric.manager.CoopApplicativaManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogo;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogoTemp;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamentoTemp;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnteTemp;
import it.csi.epay.epaymodric.business.epaymodric.model.DatiTecniciParentEntity;
import it.csi.epay.epaymodric.business.epaymodric.repository.CatalogoRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.CatalogoTempRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiceVersamentoRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiceVersamentoTempRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteTempRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaRiferimentoContabileRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AnnullaOperazioneRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ConfermaOperazioneRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ProtocolloAggiornamentoAzioneType;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ResultType;


/**
 *
 */
@Service
@Transactional ( rollbackFor = Exception.class )
public class CoopApplicativaManagerImpl implements CoopApplicativaManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private EnteTempRepository enteTempRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private CatalogoTempRepository catalogoTempRepository;

    @Autowired
    private ErroreRepository erroreRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private CodiceVersamentoTempRepository codiceVersamentoTempRepository;

    @Override
    public ResponseType aggiornaCatalogo ( AggiornaRiferimentoContabileRequest aggiornaRiferimentoContabileRequest ) {

        ResponseType response = returnStandardSuccessfulOutput ();

        if ( null == aggiornaRiferimentoContabileRequest ||
                        !StringUtils.hasText ( aggiornaRiferimentoContabileRequest.getIdOperazione () ) ||
                        !StringUtils.hasText ( aggiornaRiferimentoContabileRequest.getRiferimentoContabile ().getChiaveIntersistema () ) ) {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }

        CblTCatalogoTemp catalogoTemp = null;

        try {

            String azione = "";
            ProtocolloAggiornamentoAzioneType azioneRequest
            = null != aggiornaRiferimentoContabileRequest.getProtocolloAggiornamentoAzione ()
            ? aggiornaRiferimentoContabileRequest.getProtocolloAggiornamentoAzione () : null;

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
                    throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                    "Azione non riconosciuta" );
                }
            } else {
                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                "Azione non presente" );
            }

            if ( null == aggiornaRiferimentoContabileRequest.getRiferimentoContabile ()
                            || !StringUtils.hasText ( aggiornaRiferimentoContabileRequest.getRiferimentoContabile ().getCodiceFiscaleEnte () ) ) {
                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "Codice fiscale ente non presente" ) );
            }

            CblTEnte enteEntity
            = enteRepository.findByCodiceFiscale ( aggiornaRiferimentoContabileRequest.getRiferimentoContabile ().getCodiceFiscaleEnte () );
            catalogoTemp = createRiferimentoContabile ( enteEntity, aggiornaRiferimentoContabileRequest.getRiferimentoContabile (), azione,
                aggiornaRiferimentoContabileRequest.getIdOperazione () );

            if ( null != catalogoTemp ) {

                try {
                    CblTCatalogo catalogo
                    = catalogoRepository.findOneByChiaveIntersistema ( catalogoTemp.getChiaveIntersistema () );
                    if ( AZIONE_MODIFICA == azione || AZIONE_CANCELLAZIONE == azione ) {
                        if ( null == catalogo ) {
                            logger.error ( "Riferimento Contabile " + catalogoTemp.getCodiceVersamento () + " non censito." );
                            return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                "Riferimento Contabile " + catalogoTemp.getCodiceVersamento () + " non censito." );
                        } else {
                            if ( AZIONE_MODIFICA == azione || AZIONE_CANCELLAZIONE == azione ) {
                                CblTCodiceVersamento codiceVersamento
                                = codiceVersamentoRepository.findOneByIdEnteAndCodiceVersamento ( catalogoTemp.getIdEnte (),
                                    catalogoTemp.getCodiceVersamento () );
                                if ( null != codiceVersamento ) {
                                    catalogoTemp.setDescrizioneVersamento ( codiceVersamento.getDescrizioneVersamento () );
                                }
                            }
                        }
                    } else if ( AZIONE_INSERIMENTO == azione ) {
                        CblTCodiceVersamento codiceVersamento
                        = codiceVersamentoRepository.findOneByIdEnteAndCodiceVersamento ( catalogoTemp.getIdEnte (), catalogoTemp.getCodiceVersamento () );
                        if ( null == codiceVersamento ) {
                            logger.error ( "Codice Versamento " + catalogoTemp.getCodiceVersamento () + " non censito." );
                            return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                "Codice Versamento " + catalogoTemp.getCodiceVersamento () + " non censito." );
                        } else {
                            catalogoTemp.setDescrizioneVersamento ( codiceVersamento.getDescrizioneVersamento () );
                        }
                    }
                } catch ( Exception e ) {
                    logger.error ( "Errore in fase di controllo del catalogo: " + catalogoTemp.getCodiceVersamento (), e );
                    return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                        "Codice Versamento" + catalogoTemp.getCodiceVersamento () + "non censito." );
                }

                catalogoTempRepository.save ( catalogoTemp );
            } else {
                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                "Non e' stato possibile recuperare la entity da salvare" );
            }
        } catch ( EpaymodricException e ) {
            logger.error ( e );
            String stringErrorCode = Integer.toString ( e.getErrorCode () );
            response = returnErrorOutput ( stringErrorCode );
        } catch ( Exception e ) {
            logger.error ( e );
            response = returnErrorOutput ( null );
        }

        return response;
    }

    @Override
    public ResponseType aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest aggiornaCodiceVersamentoRequest ) {

        ResponseType response = returnStandardSuccessfulOutput ();

        if ( null == aggiornaCodiceVersamentoRequest || !StringUtils.hasText ( aggiornaCodiceVersamentoRequest.getIdOperazione () ) ) {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
        CblTCodiceVersamentoTemp codiceVersamentoTemp = null;

        try {

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
                                    throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                                    "Azione non riconosciuta" );
                                }
                            } else {
                                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                                "Azione non presente" );
                            }

                            if ( null == aggiornaCodiceVersamentoRequest.getCodiceVersamento ()
                                            || !StringUtils.hasText ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getCFEnte () ) ) {
                                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                    String.format ( "Codice fiscale ente non presente" ) );
                            }

                            CblTEnte enteEntity = enteRepository.findByCodiceFiscale ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getCFEnte () );

                            codiceVersamentoTemp = createCodiceVersamento ( enteEntity, aggiornaCodiceVersamentoRequest.getCodiceVersamento (), azione, aggiornaCodiceVersamentoRequest.getIdOperazione () );

                            if ( null != codiceVersamentoTemp ) {

                                try {
                                    CblTCodiceVersamento cblTCodiceVersamento = codiceVersamentoRepository.findOneByIdEnteAndCodiceVersamento ( codiceVersamentoTemp.getIdEnte (), codiceVersamentoTemp.getCodiceVersamento () );
                                    if ( AZIONE_MODIFICA == azione || AZIONE_CANCELLAZIONE == azione ) {
                                        if ( null == cblTCodiceVersamento ) {
                                            logger.error ( "Codice Versamento " + codiceVersamentoTemp.getCodiceVersamento () + " non censito." );
                                            return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                                "Codice Versamento " + codiceVersamentoTemp.getCodiceVersamento () + " non censito." );
                                        }
                                    }

                                    //Qui cblTCodiceVersamento e' nullo per forza...
                                    if(AZIONE_INSERIMENTO == azione) {
                                        if ( null != cblTCodiceVersamento ) {
                                            cblTCodiceVersamento.setDataInizioValidita ( new Timestamp ( System.currentTimeMillis () ));
                                        }
                                    }
                                } catch ( Exception e ) {
                                    logger.error ( "Errore in fase di controllo del catalogo: " + codiceVersamentoTemp.getCodiceVersamento (), e );
                                    return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                        "Codice Versamento" + codiceVersamentoTemp.getCodiceVersamento () + "non censito." );
                                }

                                codiceVersamentoTempRepository.save ( codiceVersamentoTemp );
                            } else {
                                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                                "Non e' stato possibile recuperare la entity da salvare" );
                            }
        } catch ( EpaymodricException e ) {
            logger.error ( e );
            String stringErrorCode = Integer.toString ( e.getErrorCode () );
            response = returnErrorOutput ( stringErrorCode );
        } catch ( Exception e ) {
            logger.error ( e );
            response = returnErrorOutput ( null );
        }

        return response;
    }

    @Override
    public ResponseType aggiornaEnte ( AggiornaEnteRequest aggiornaEnteRequest ) {
        if ( null != aggiornaEnteRequest &&
                        StringUtils.hasText ( aggiornaEnteRequest.getIdOperazione () ) &&
                        null != aggiornaEnteRequest.getEnte () &&
                        StringUtils.hasText ( aggiornaEnteRequest.getEnte ().getCodiceFiscale () ) ) {
            try {
                CblTEnteTemp enteTemp = buildEnteTemp ( aggiornaEnteRequest );
                if ( null != aggiornaEnteRequest.getCodiciVersamento () && null != aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ()
                                && !CollectionUtils.isEmpty ( aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ().getCodiceVersamento () ) ) {

                    CblTEnte enteRequest = enteRepository.findByCodiceFiscale ( enteTemp.getCodiceFiscale () );

                    if(enteRequest != null) {
                        enteTemp.setIdEnte ( enteRequest.getIdEnte () );
                    }

                    List<CblTCodiceVersamentoTemp> codiceVersamentoAttiviTempList = creaCodiciVersamento ( enteTemp,
                        aggiornaEnteRequest.getCodiciVersamento ().getElencoCodiciVersamento ().getCodiceVersamento () );

                    if ( !CollectionUtils.isEmpty ( codiceVersamentoAttiviTempList ) ) {
                        for ( CblTCodiceVersamentoTemp codiceVersamentoAttiviTemp: codiceVersamentoAttiviTempList ) {
                            try {
                                //ENG:fix
                                //CblTCodiceVersamento codiceVersamentoAttivo = codiceVersamentoRepository.findOneByIdEnteAndCodiceVersamento (
                                //    codiceVersamentoAttiviTemp.getIdEnte (),
                                //    codiceVersamentoAttiviTemp.getCodiceVersamento () );

                                CblTCodiceVersamento codiceVersamentoAttivo = codiceVersamentoRepository.findOneByCodiceFiscaleEnteAndCodiceVersamento (
                                    codiceVersamentoAttiviTemp.getCodiceFiscaleEnte (),
                                    codiceVersamentoAttiviTemp.getCodiceVersamento () );

                                //Se cerco per data inizio e fine potrei non trovare il cv che mi serve
                                //qui devo cercare su tutti i cv presenti a prescindere
                                //CblTCodiceVersamento codiceVersamento = codiceVersamentoRepository
                                //                .findOneByCodiceFiscaleEnteAndCodiceVersamentoAndDataInizioValiditaAndDataFineValidita (
                                //        codiceVersamentoAttiviTemp.getCodiceFiscaleEnte (), codiceVersamentoAttiviTemp.getCodiceVersamento (),
                                //        new Timestamp ( System.currentTimeMillis () ), new Timestamp ( System.currentTimeMillis () ) );

                                if ( null == codiceVersamentoAttivo ) {
                                    logger.error ( "Codice Versamento " + codiceVersamentoAttiviTemp.getCodiceVersamento () + " non censito." );
                                    return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                        "Codice Versamento " + codiceVersamentoAttiviTemp.getCodiceVersamento () + " non censito." );
                                }
                            } catch ( Exception e ) {
                                logger.error ( "Errore in fase di controllo del catalogo: " + codiceVersamentoAttiviTemp.getCodiceVersamento (), e );
                                return returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                                    "Codice Versamento " + codiceVersamentoAttiviTemp.getCodiceVersamento () + " non censito." );
                            }
                        }
                        codiceVersamentoTempRepository.save ( codiceVersamentoAttiviTempList );
                    }
                }
                enteTempRepository.save ( enteTemp );
                return returnStandardSuccessfulOutput ();
            } catch ( Exception e ) {
                logger.error ( "Errore in fase di salvataggio della tabella CblTEnteTemp: ", e );
                return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            }
        } else {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
    }

    @Override
    public ResponseType annullaOperazione ( AnnullaOperazioneRequest annullaOperazioneRequest ) {
        if ( null != annullaOperazioneRequest &&
                        StringUtils.hasText ( annullaOperazioneRequest.getIdOperazione () ) ) {
            CblTEnteTemp enteTemp = null;
            List<CblTCatalogoTemp> catalogoTempList = null;
            List<CblTCodiceVersamentoTemp> codiceVersamentoList = null;
            try {
                enteTemp = enteTempRepository.findOneByIdOperazione ( annullaOperazioneRequest.getIdOperazione () );
                catalogoTempList = catalogoTempRepository.findAllByIdOperazione ( annullaOperazioneRequest.getIdOperazione () );
                codiceVersamentoList = codiceVersamentoTempRepository.findAllByIdOperazione ( annullaOperazioneRequest.getIdOperazione () );
            } catch ( Exception e ) {
            }
            if ( null != enteTemp ) {
                ResponseType enteTempResp = deleteEntityTemp ( enteTempRepository, enteTemp );
                if ( enteTempResp.getResult ().getCodice ().equals ( CostantiErrori.WS_ESITO_OK_DEFAULT ) ) {
                    if ( !CollectionUtils.isEmpty ( catalogoTempList ) ) {
                        return deleteEntityTemp ( catalogoTempRepository, catalogoTempList );
                    }
                }
                return enteTempResp;
            } else if ( !CollectionUtils.isEmpty ( catalogoTempList ) ) {
                return deleteEntityTemp ( catalogoTempRepository, catalogoTempList );
            } else if ( !CollectionUtils.isEmpty ( codiceVersamentoList ) ) {
                //Paolo scrive quello che dice Lollo ossia "mi sembra sbagliato: dovrebbe essere cosi'"
                //return deleteEntityTemp ( codiceVersamentoTempRepository, codiceVersamentoList );
                //Forse e' un refuso che usa repo sbagliato
                return deleteEntityTemp ( catalogoTempRepository, codiceVersamentoList );
            } else {
                return returnErrorResponseType ( CostantiErrori.ERRORE_DATI_MANCANTI,
                    String.format ( "L'operazione con id %s non e' presente su base dati.", annullaOperazioneRequest.getIdOperazione () ) );
            }
        } else {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
    }

    private void confermaCatalogo ( List<CblTCatalogoTemp> catalogoTempList ) throws EpaymodricException {

        //        CblTCatalogo catalogo = null;
        //        List<CblTCatalogo> catalogoList
        //            = catalogoRepository.findByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizio ( catalogoTemp.getIdEnte (),
        //                catalogoTemp.getCodiceVersamento (), catalogoTemp.getDatiSpecificiRiscossione (), catalogoTemp.getAnnoEsercizio () );
        for ( CblTCatalogoTemp catalogoTemp: catalogoTempList ) {

            CblTCatalogo catalogo = catalogoRepository.findOneByChiaveIntersistema ( catalogoTemp.getChiaveIntersistema () );

            switch ( catalogoTemp.getAzione () ) {

            case AZIONE_INSERIMENTO :

                catalogo = new CblTCatalogo ();

                catalogo = mapCatalogo ( catalogo, catalogoTemp );

                break;
            case AZIONE_MODIFICA :

                if ( null == catalogo ) {
                    throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                        String.format ( "Azione %s non riconosciuta", catalogoTemp.getAzione () ) );
                }

                catalogo = mapCatalogo ( catalogo, catalogoTemp );

                break;

            case AZIONE_CANCELLAZIONE :

                if ( null == catalogo ) {
                    throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                        String.format ( "Azione %s non riconosciuta", catalogoTemp.getAzione () ) );
                }

                catalogo = mapCatalogo ( catalogo, catalogoTemp );

                catalogo.setFlagAnnullato ( true );

                break;
            default :
                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ),
                    String.format ( "Azione %s non riconosciuta", catalogoTemp.getAzione () ) );
            }

            catalogoRepository.save ( catalogo );
            catalogoTempRepository.delete ( catalogoTemp );
        }
    }

    private void confermaCodiceVersamento ( CblTCodiceVersamentoTemp codiceVersamentoTemp ) throws EpaymodricException {
        CblTCodiceVersamento codiceVersamento = null;
        List<CblTCodiceVersamento> codiceVersamentoList = null;

        switch ( codiceVersamentoTemp.getAzione () ) {

        case AZIONE_INSERIMENTO :

            CblTCodiceVersamento codiceVersamentoOriginale = codiceVersamentoRepository.findOneByIdEnteAndCodiceVersamento ( codiceVersamentoTemp.getIdEnte (),
                codiceVersamentoTemp.getCodiceVersamento () );

            codiceVersamento = mapCblTCodiceVersamento ( new CblTCodiceVersamento (), codiceVersamentoTemp, true );

            if ( null != codiceVersamentoOriginale ) {
                codiceVersamento.setId ( codiceVersamentoOriginale.getId () );
                codiceVersamento.setDataInserimento ( codiceVersamentoOriginale.getDataInserimento () );
                codiceVersamento.setDataInizioValidita ( new Timestamp ( System.currentTimeMillis () ) );
            } else {
                codiceVersamento.setDataInizioValidita ( new Timestamp ( System.currentTimeMillis () ) );
            }

            break;
        case AZIONE_MODIFICA :

            codiceVersamentoList = codiceVersamentoRepository.findAllByIdEnteAndCodiceVersamento ( codiceVersamentoTemp.getIdEnte (),
                codiceVersamentoTemp.getCodiceVersamento () );

            if ( null == codiceVersamentoList || codiceVersamentoList.size () != 1 ) {
                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "Azione %s non riconosciuta", codiceVersamentoTemp.getAzione () ) );
            }

            codiceVersamento = codiceVersamentoList.get ( 0 );

            codiceVersamento = mapCblTCodiceVersamento ( codiceVersamento, codiceVersamentoTemp, false );

            break;

        case AZIONE_CANCELLAZIONE :

            codiceVersamentoList = codiceVersamentoRepository.findAllByIdEnteAndCodiceVersamento ( codiceVersamentoTemp.getIdEnte (),
                codiceVersamentoTemp.getCodiceVersamento () );

            if ( null == codiceVersamentoList || codiceVersamentoList.size () != 1 ) {
                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "Azione %s non riconosciuta", codiceVersamentoTemp.getAzione () ) );
            }

            codiceVersamento = codiceVersamentoList.get ( 0 );

            codiceVersamento = mapCblTCodiceVersamento ( codiceVersamento, codiceVersamentoTemp, false );

            codiceVersamento.setFlagAnnullato ( true );

            break;
        default :
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ),
                String.format ( "Azione %s non riconosciuta", codiceVersamentoTemp.getAzione () ) );
        }

        codiceVersamentoRepository.save ( codiceVersamento );
        codiceVersamentoTempRepository.delete ( codiceVersamentoTemp );
    }

    private void confermaEnte ( CblTEnteTemp enteTemp ) throws EpaymodricException {
        CblTEnte enteToPersist = buildEnte ( enteTemp );
        Assert.notNull ( enteToPersist, "Errore in fase di creazione dell'ente!" );
        CblTEnte enteCurrent = enteRepository.findByCodiceFiscale ( enteTemp.getCodiceFiscale () );

        if ( null != enteCurrent ) {
            //
            //Logica per la modifica dell'ente
            //
            enteToPersist.setId ( enteCurrent.getId () );
            enteToPersist.setIdEnte ( enteCurrent.getIdEnte () );
        } else {
            //Logica per l'inserimento dell'ente
            Integer indice = enteRepository.getEnteMaxId ();

            if ( null == indice ) {
                indice = 0;
            }

            //Id e' nullo volutamente in quanto c'e' un autoincrementale per quel valore
            //la build ente ne setta dentro uno proveniente dalla ente temp. E' gia' nullo
            //per questo ma puntualizzarlo non fa male
            enteToPersist.setId( null );
            enteToPersist.setIdEnte ( org.apache.commons.lang.StringUtils.leftPad ( "" + ( indice + 1 ), 4, "0" ) );
        }

        enteToPersist = enteRepository.save ( enteToPersist );

        //Elaborazione CodiciVersamento associati all'ente
        List<CblTCodiceVersamento> catalogoList = codiceVersamentoRepository.findByIdEnte ( enteToPersist.getIdEnte () );

        List<CblTCodiceVersamentoTemp> catalogoTempList = codiceVersamentoTempRepository.findAllByIdOperazione ( enteTemp.getIdOperazione () );


       //TODOPPU DA TOGLIERE - DEBUG

        for ( CblTCodiceVersamento codiceVersamento: catalogoList ) {
            Boolean trovato = false;
            for ( CblTCodiceVersamentoTemp codiceVersamentoNuovoTemp: catalogoTempList ) {

                logger.info ( "----------------------------------------------------------------------------------" );
                logger.info ( "Compare CV -> DB:" + codiceVersamento.getCodiceVersamento () + " VS - temp :" + codiceVersamentoNuovoTemp.getCodiceVersamento ());
                logger.info ( "Compare ID -> DB:" + codiceVersamento.getIdEnte ()           + " VS - temp :" + codiceVersamentoNuovoTemp.getIdEnte ());

                if ( codiceVersamento.compareTo ( codiceVersamentoNuovoTemp ) == 0 ) {
                    //Se trovo una corrispondenza nel dubbio elimino la data fine validita'.
                    trovato = true;
                    break;
                }

                logger.info ( "FOUND IS " + trovato );
                logger.info ( "----------------------------------------------------------------------------------" );
            }
        }
        //TODOPPU FINE

        List<CblTCodiceVersamento> catalogoListToUpdate = elaboraCodiciVersamento ( catalogoList, catalogoTempList );

        if ( !CollectionUtils.isEmpty ( catalogoListToUpdate ) ) {
            codiceVersamentoRepository.save ( catalogoListToUpdate );
        }

        if ( !CollectionUtils.isEmpty ( catalogoTempList ) ) {
            codiceVersamentoTempRepository.delete ( catalogoTempList );
        }

        Assert.isTrue ( null != enteToPersist && null != enteToPersist.getId (), "Operazione di conferma dell'ente non riuscita." );
        enteTempRepository.delete ( enteTemp.getId () );
    }

    @Override
    public ResponseType confermaOperazione ( ConfermaOperazioneRequest confermaOperazioneRequest ) {
        ResponseType response = returnStandardSuccessfulOutput ();
        try {
            CblTEnteTemp enteTemp = enteTempRepository.findOneByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );
            List<CblTCatalogoTemp> catalogoTempList = catalogoTempRepository.findAllByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );
            List<CblTCodiceVersamentoTemp> codiceVersamentoTemp
            = codiceVersamentoTempRepository.findAllByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );
            if ( null != enteTemp ) {
                confermaEnte ( enteTemp );
            } else if ( !CollectionUtils.isEmpty ( catalogoTempList ) ) {
                confermaCatalogo ( catalogoTempList );
            } else if ( null != codiceVersamentoTemp ) {
                for ( CblTCodiceVersamentoTemp cblTCodiceVersamentoTemp: codiceVersamentoTemp ) {
                    confermaCodiceVersamento ( cblTCodiceVersamentoTemp );
                }
            } else {
                throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "L'operazione con id %s non e' presente su base dati.", confermaOperazioneRequest.getIdOperazione () ) );
            }
            return returnStandardSuccessfulOutput ();
        } catch ( EpaymodricException e ) {
            logger.error ( e );
            String stringErrorCode = Integer.toString ( e.getErrorCode () );
            response = returnErrorOutput ( stringErrorCode );
        }

        return response;
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    private ResponseType deleteEntityTemp ( IRepository repository, DatiTecniciParentEntity entityTemp ) {
        try {
            repository.delete ( entityTemp );
            return returnStandardSuccessfulOutput ();
        } catch ( Exception e ) {
            logger.error ( "Errore in fase di salvataggio della tabella CblTEnteTemp: ", e );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    private ResponseType deleteEntityTemp ( IRepository repository, List<?> catalogoTempList ) {
        try {
            repository.delete ( catalogoTempList );
            return returnStandardSuccessfulOutput ();
        } catch ( Exception e ) {
            logger.error ( "Errore in fase di salvataggio della tabella CblTEnteTemp: ", e );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
    }

    private ResponseType returnErrorOutput ( String codiceErrore ) {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();

        CblDErrore errore = erroreRepository.findByCodiceErrore ( codiceErrore );

        if ( null == errore || !StringUtils.hasText ( errore.getDescrizioneErrore () ) ) {
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

        if ( StringUtils.hasText ( codiceErrore ) && StringUtils.hasText ( descrizione ) ) {
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
