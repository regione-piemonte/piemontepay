/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import static it.csi.epay.epaymodric.business.epaymodric.repository.custom.CustomSpecifications.esistonoElaborazioniTerminateOggiSpecification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.manager.ElaborazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EnteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoOrigineManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StoricoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ElaborazioneUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.EnteUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.repository.ElaborazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoOrigineRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoElaborazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciFlussiDaRielaborare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;


/**
 * @author vsgro
 */
@Service
public class ElaborazioneManagerImpl implements ElaborazioneManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private ElaborazioneRepository elaborazioneRepository;

    @Autowired
    ErroreRepository erroreRepository;

    @Autowired
    FlussoOrigineManager flussoOrigineManager;

    @Autowired
    StoricoManager storicoManager;

    @Autowired
    private StatoElaborazioneRepository statoElaborazioneRepository;

    @Autowired
    private FlussoOrigineRepository flussoOrigineRepository;

    @Autowired
    private EnteManager enteManager;

    @Autowired
    private EnteRepository enteReporitory;

    @Override
    public Elaborazione leggi ( Long id ) {
        Elaborazione elaborazione = new Elaborazione ();
        logger.info ( "ElaborazioneManagerImpl.findAll: INIZIO" );
        CblTElaborazione cblTElaborazione = elaborazioneRepository.findById ( id );
        elaborazione = ElaborazioneUtility.getElaborazione ( cblTElaborazione );
        logger.info ( "ElaborazioneManagerImpl.findAll: FINE" );
        return elaborazione;
    }

    @Override
    public List<Elaborazione> recuperaElaborazioniForzate ( String idEnte ) {
        logger.info ( "ElaborazioneManagerImpl.recuperaElaborazioniForzate: INIZIO" );
        List<Elaborazione> elaborazioni = new ArrayList<> ();
        List<CblTElaborazione> cblTElaborazioneList = elaborazioneRepository
                        .findAllByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStato (
                            idEnte, StatoElaborazioneEnum.FORZATA.getCodice () );

        for ( CblTElaborazione elaborazioneEntity: cblTElaborazioneList ) {
            elaborazioni.add ( ElaborazioneUtility.getElaborazione ( elaborazioneEntity ) );
        }
        logger.info ( "ElaborazioneManagerImpl.recuperaElaborazioniForzate: FINE" );
        return elaborazioni;
    }


    @Override
    @Transactional
    public void salva ( Elaborazione elaborazione ) {
        logger.info ( "ElaborazioneManagerImpl.salva: INIZIO" );

        if ( elaborazione.getId () != null ) {
            CblTElaborazione elaborazioneEntityAttuale = elaborazioneRepository.findById ( elaborazione.getId () );
            storicoManager.storicizzaElaborazione ( ElaborazioneUtility.getElaborazione ( elaborazioneEntityAttuale ) );
        }
        CblTElaborazione elaborazioneEntity = ElaborazioneUtility.getElaborazione ( elaborazione );

        elaborazioneEntity.setCblTEnte ( enteReporitory.findByIdEnte ( elaborazione.getIdEnte () ) );
        elaborazioneEntity.setCblDStatoElaborazione ( statoElaborazioneRepository.findByCodiceStato ( elaborazione.getStatoElaborazione ().getCodice () ) );

        elaborazioneRepository.saveAndFlush ( elaborazioneEntity );
        logger.info ( "ElaborazioneManagerImpl.salva: FINE" );
    }

    public ElaborazioneRepository getRepository () {
        return elaborazioneRepository;
    }

    public void setRepository ( ElaborazioneRepository repository ) {
        elaborazioneRepository = repository;
    }

    // Nuru 2.2.12 aggiorna Stato Elaborazione
    @Override
    @Transactional
    public CblTElaborazione aggiornaStatoElaborazioneInternal ( Long idElaborazione, String nuovoStato ) {
        // CallerInputDto utenteModifica = RequestContextUtils.getRequestContext ().getCaller ();

        logger.info ( "ElaborazioneManagerImpl.cambiaStatoElaborazione: INIZIO" );
        CblTElaborazione elaboration = elaborazioneRepository.findById ( idElaborazione );

        if ( elaboration != null ) {
            logger.info ( "Stato elaborazione from database :" + " " + elaboration.getCblDStatoElaborazione ().getCodiceStato () );
            elaboration.setCblDStatoElaborazione ( statoElaborazioneRepository.findByCodiceStato ( nuovoStato ) );
            logger.info ( "## Aggiornamento stato elaborazione from database  a :" + nuovoStato );

            storicoManager.storicizzaElaborazione ( ElaborazioneUtility.getElaborazione ( elaboration ) );

            elaboration = elaborazioneRepository.saveAndFlush ( elaboration );
        }
        logger.info ( "ElaborazioneManagerImpl.aggiornaStatoElaborazione: : FINE" );
        return elaboration;
    }

    @Override
    @Transactional
    public DTOOutputWsEsito inserisciElaborazione ( DTOInputWsElaborazione in ) {
        logger.info ( "ElaborazioniServiceImpl.inserisciElaborazione:INIZIO" );

        Assert.notNull ( in, "L'oggetto DTOInputWsElaborazione di input deve essere sempre valorizzato!" );

        DTOOutputWsEsito result = new DTOOutputWsEsito ();
        List<String> listaFlussiDatrattare = new ArrayList<> ();
        String listaFlussi = "";
        Ente ente = enteManager.leggiPerCodiceFiscaleEnte ( in.getCodiceFiscaleEnte () );
        if ( StringUtils.hasText ( in.getIdElaborazionePrecedente () ) ) {
            CblTElaborazione elabo = elaborazioneRepository.findById ( Long.valueOf ( in.getIdElaborazionePrecedente () ) );
            listaFlussi = elabo.getListaFlussi ();
            listaFlussiDatrattare.addAll ( Arrays.asList ( listaFlussi.split ( ";" ) ) );
            in.setFlussiDaElaborare ( listaFlussiDatrattare );
            List<CblTFlussoOrigine> flussiDaRielaborare = null;
            if ( !CollectionUtils.isEmpty ( in.getFlussiDaElaborare () )
                            && String.join ( Costanti.SEPARATORE_LISTA_FLUSSI, in.getFlussiDaElaborare () ).trim ().length () > 0 ) {
                listaFlussi = String.join ( Costanti.SEPARATORE_LISTA_FLUSSI, in.getFlussiDaElaborare () );
                flussiDaRielaborare = flussoOrigineRepository
                                .findAllByIdentificativoFlussoInAndCblTEnteIdEnte ( in.getFlussiDaElaborare (), ente.getIdEnte () );
            } else {
                flussiDaRielaborare = flussoOrigineRepository.findAllByCblTElaborazioneId ( Long.valueOf ( in.getIdElaborazionePrecedente () ) );
            }

            //imposto tutti i flussi dell'elaborazione "in bozza"

            for ( CblTFlussoOrigine flussoOrigine: flussiDaRielaborare ) {
                flussoOrigineManager.cambiaStatoFlusso ( FlussiUtility.getFlussoOrigine ( flussoOrigine ), StatoFlussoEnum.FORZATO );
            }
        }

        CblTElaborazione elaborazione = new CblTElaborazione ();

        //Inserimento in emergenza -> in popolato con i flussi da elaborare (?)


        //Questo controllo puo' essere ignorato in quanto viene fatto dal 2.2.6

        //        Ogni giorno viene verificato se la data odierna e' quella in cui deve partire l'elaborazione;
        //        se si bisognera' inserire un record sulla tabella delle elaborazioni che da il via al processo.
        //        Tale occorrenza analogamente viene inserita quando partono altri cdu (ad esempio la ri-elaborazione
        //            in caso di errore o di forzatura).
        //
        //        Partira' l'elaborazione di tutto quello che si trova in stato SCHEDULATO / FORZATO sulla tabella delle
        //        elaborazioni per la giornata in corso.



        elaborazione.setDataElaborazione ( new Timestamp ( System.currentTimeMillis () ) );

        elaborazione.setCblTEnte ( EnteUtility.getEnte ( ente ) );
        elaborazione.setListaFlussi ( listaFlussi.trim () );
        elaborazione.setUtenteInserimento ( in.getUtenteElaborazione () );
        elaborazione.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );

        if ( StringUtils.hasText ( in.getStatoDaImpostare () ) ) {

            StatoElaborazioneEnum statoAtteso = StatoElaborazioneEnum.valueOf ( in.getStatoDaImpostare () );

            CblDStatoElaborazione statoElaborazioneEntity = null;
            if ( statoAtteso != null ) {
                statoElaborazioneEntity = statoElaborazioneRepository.findByCodiceStato ( statoAtteso.getCodice () );
            } else {
                statoElaborazioneEntity = statoElaborazioneRepository.findByCodiceStato ( in.getStatoDaImpostare () );
            }
            elaborazione.setCblDStatoElaborazione ( statoElaborazioneEntity );
        }


        elaborazioneRepository.save ( elaborazione );
        elaborazioneRepository.flush ();

        result.setCodiceErrore ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        result.setDescrizione ( "Operazione eseguita correttamente" );

        logger.info ( "ElaborazioniServiceImpl.inserisciElaborazione:FINE" );

        return result;
    }


    @Override
    public boolean esistonoElaborazioniTerminateOggi ( String idEnte ) {

        logger.info ( "ElaborazioniServiceImpl.inserisciElaborazione:INIZIO" );

        Long elaborazioniTerminateOggi = elaborazioneRepository.count ( esistonoElaborazioniTerminateOggiSpecification ( idEnte ) );

        logger.info ( "ElaborazioniServiceImpl.inserisciElaborazione:FINE" );

        return elaborazioniTerminateOggi != null && elaborazioniTerminateOggi.intValue () > 0;
    }

    @Override
    public Elaborazione recuperaElaborazioneSchedulata ( String idEnte ) {
        CblTElaborazione elaborazione
        = elaborazioneRepository.findOneByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStato ( idEnte, StatoElaborazioneEnum.SCHEDULATA.getCodice () );
        return ElaborazioneUtility.getElaborazione ( elaborazione );
    }

    @Override
    public boolean isElaborazioneTerminataPerEnti ( List<CblTEnte> enti ) {

        List<String> idEnteList = new ArrayList<> ();

        for ( CblTEnte cblTEnte: enti ) {
            idEnteList.add ( cblTEnte.getIdEnte () );
        }

        List<String> statiElaborazione = new ArrayList<> ();

        statiElaborazione.add ( StatoElaborazioneEnum.SCHEDULATA.getCodice () );
        statiElaborazione.add ( StatoElaborazioneEnum.AVVIATA.getCodice () );

        List<CblTElaborazione> entiPendenti
        = elaborazioneRepository.findByCblTEnteIdEnteInAndCblDStatoElaborazioneCodiceStatoIn ( idEnteList, statiElaborazione );
        return CollectionUtils.isEmpty ( entiPendenti );
    }

    @Override
    public DTOOutputWsEsito inserisciElaborazionePerRielaborareFlussi ( DTOInputWsInserisciFlussiDaRielaborare input ) {

        DTOOutputWsEsito esito = new DTOOutputWsEsito ();

        if ( null == input.getIdentificativiFlussoDaRielaborare () ) {
            esito.setCodiceErrore ( CostantiErrori.WS_ESITO_KO_PARAM_REQUIRED );
            esito.setDescrizione ( "Lista flussi non presente" );
        } else if ( null == input.getCaller () || null == input.getCaller ().getPrincipal () || null == input.getCaller ().getPrincipal ().getCodiceEnte () ) {
            esito.setCodiceErrore ( CostantiErrori.WS_ESITO_KO_PARAM_REQUIRED );
            esito.setDescrizione ( "Ente non presente" );
        } else {
            Ente ente = enteManager.leggiPerCodiceFiscaleEnte ( input.getCaller ().getPrincipal ().getCodiceEnte () );
            if ( null != ente ) {
                String listaFlussi = String.join ( Costanti.SEPARATORE_LISTA_FLUSSI, input.getIdentificativiFlussoDaRielaborare () );

                List<CblTElaborazione> elaborazioniInCoda
                = elaborazioneRepository.findByListaFlussiAndCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStato ( listaFlussi, ente.getIdEnte (),
                    StatoElaborazioneEnum.FORZATA.getCodice () );

                if ( CollectionUtils.isEmpty ( elaborazioniInCoda ) ) {

                    List<CblTFlussoOrigine> flussiDaRielaborare
                    = flussoOrigineRepository.findByIdentificativoFlussoIn ( input.getIdentificativiFlussoDaRielaborare () );

                    for ( CblTFlussoOrigine flussoOrigine: flussiDaRielaborare ) {
                        FlussoOrigine flusso = FlussiUtility.getFlussoOrigine ( flussoOrigine );
                        flusso.setUtenteModifica ( input.getCaller ().getPrincipal ().getCodiceFiscale () );
                        flussoOrigineManager.cambiaStatoFlusso ( flusso , StatoFlussoEnum.FORZATO );
                    }

                    Elaborazione elaborazione = new Elaborazione ();
                    elaborazione.setDataElaborazione ( new Timestamp ( Calendar.getInstance ().getTimeInMillis () ) );
                    elaborazione.setDataInserimento ( new Timestamp ( Calendar.getInstance ().getTimeInMillis () ) );
                    elaborazione.setIdEnte ( ente.getIdEnte () );
                    elaborazione.setListaFlussi ( (ArrayList<String>) input.getIdentificativiFlussoDaRielaborare () );
                    elaborazione.setStatoElaborazione ( StatoElaborazioneEnum.FORZATA );
                    elaborazione.setUtenteInserimento ( input.getCaller ().getPrincipal ().getCodiceFiscale () != null
                                    ? input.getCaller ().getPrincipal ().getCodiceFiscale () : Costanti.DEFAULT_UTENTE_SISTEMA );
                    CblTElaborazione elaborazioneEntity = ElaborazioneUtility.getElaborazione ( elaborazione );
                    elaborazioneEntity
                    .setCblDStatoElaborazione ( statoElaborazioneRepository.findByCodiceStato ( StatoElaborazioneEnum.FORZATA.getCodice () ) );
                    elaborazioneEntity.setCblTEnte ( enteReporitory.findByIdEnte ( ente.getIdEnte () ) );
                    elaborazioneRepository.saveAndFlush ( elaborazioneEntity );


                    esito.setCodiceErrore ( Costanti.RESPONSE_WS_OK );
                    esito.setDescrizione ( "Elaborazione completata con successo" );
                } else {
                    esito.setCodiceErrore ( CostantiErrori.WS_ESITO_KO_DEFAULT );
                    esito.setDescrizione ( "Flussi non aggiornati, e' gia' presente un' elaborazione in stato FORZATA per i flussi richiesti" );
                }
            } else {
                esito.setCodiceErrore ( CostantiErrori.WS_ESITO_KO_PARAM_REQUIRED );
                esito.setDescrizione ( "Ente non trovato" );
            }
        }
        return esito;
    }

}
