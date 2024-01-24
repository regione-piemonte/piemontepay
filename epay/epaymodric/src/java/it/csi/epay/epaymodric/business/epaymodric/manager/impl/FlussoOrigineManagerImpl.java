/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import static it.csi.epay.epaymodric.business.epaymodric.repository.custom.CustomSpecifications.selezioneFlussiAggiornatiESchedulati;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.business.epaymodric.bo.Errore;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.facade.RiconciliazioneVersamentiFacade;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EnteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ErroreManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoOrigineManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StoricoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.DTOFlussoOrigineUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtilityWS;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.StatoFlussoUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine_;
import it.csi.epay.epaymodric.business.epaymodric.repository.ElaborazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoOrigineRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ProvvisoriRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.PspRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoFlussoRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.custom.FlussiOrigineRepositorySpec;
import it.csi.epay.epaymodric.business.epaymodric.repository.specifications.FlussoOrigineByFlussoSpecification;
import it.csi.epay.epaymodric.business.epaymodric.repository.specifications.FlussoOrigineByStatusSpecification;
import it.csi.epay.epaymodric.business.epaymodric.utils.ConversionUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCambioStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.EsitoFlussiPagoPAResponse;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ResultType;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TestataTrasmissioneFlussiType;


/**
 * @author vsgro
 */
@Service
public class FlussoOrigineManagerImpl implements FlussoOrigineManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FlussoOrigineRepository repository;

    @Autowired
    private FlussiOrigineRepositorySpec FlussiOrigineRepositorySpec;

    @Autowired
    ErroreRepository erroreRepository;

    @Autowired
    private StatoFlussoRepository statoFlussoRepository;

    @Autowired
    private ElaborazioneRepository elaborazioneRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private EnteManager enteManager;

    @Autowired
    ErroreManager erroreManager;

    @Autowired
    StoricoManager storicoManager;

    //Nuru
    @Autowired
    private RiconciliazioneVersamentiFacade riconciliazioneVersamentiFacade;

    @Autowired
    private ConfigurazioneManager configurazioneManager;

    @Autowired
    PspRepository pspRepository;

    @Autowired
    ProvvisoriRepository provvisoriRepository;

    @Override
    @Transactional
    public int aggiornaContatoreTentativi ( Integer contatoreTentativi, String identificativoFlusso ) {
        int retValue = 0;
        logger.info ( "FlussoOrigineManagerImpl.aggiornaContatoreTentativi: INIZIO" );
        retValue = repository.aggiornaContatoreTentativi (
            contatoreTentativi, identificativoFlusso );
        logger.info ( "FlussoOrigineManagerImpl.aggiornaContatoreTentativi: FINE" );
        return retValue;
    }

    @Override
    @Transactional
    public DTOOutputWsCambioStatoFlusso aggiornaStatoFlusso(List<String> identificativoFlusso, String idEnte, String nuovoStatoFlusso) {

        DTOOutputWsCambioStatoFlusso output = new DTOOutputWsCambioStatoFlusso();

        try {
            if (StringUtils.isEmpty(idEnte)) {
                throw new EpaymodricException ( "Identificativo ente obbligatorio" );
            }

            if (null == identificativoFlusso || identificativoFlusso.size() == 0) {
                throw new EpaymodricException ( "Indicare almeno un identificativo flusso" );
            }
            boolean emptyFlussoList = true;
            if ( null != identificativoFlusso ) {
                for ( String identificativo: identificativoFlusso ) {

                    if ( !StringUtils.isEmpty ( identificativo ) ) {
                        emptyFlussoList = false;
                    }
                }
            }
            if (emptyFlussoList) {
                throw new EpaymodricException ( "Indicare almeno un identificativo flusso" );
            }
            if (StringUtils.isEmpty(nuovoStatoFlusso)) {
                throw new EpaymodricException ( "Indicare il nuovo stato flusso desiderato" );
            }

            Ente ente = enteManager.leggiPerIdEnte(idEnte);

            if (ente == null) {
                Errore errore = erroreManager.leggi(CostantiErrori.ENTE_NON_PRESENTE);
                throw new EpaymodricException ( errore.getDescrizioneErrore () );
            }

            StatoFlussoEnum statoFlusso = StatoFlussoEnum.valueOf(nuovoStatoFlusso);

            if (null == statoFlusso) {
                throw new EpaymodricException ( "Stato flusso inesistente" );
            }

            CblDStatoFlusso statoFlussoEntity = statoFlussoRepository.findByCodiceStato(statoFlusso.getCodice());

            if (null == statoFlussoEntity) {
                throw new EpaymodricException ( "Stato flusso inesistente" );
            }

            List<CblTFlussoOrigine> flussi = repository.findByIdentificativoFlussoIn ( identificativoFlusso );

            storicoManager.storicizzaDatiFlusso(flussi);

            repository.aggiornaStatoFlussoMassivo(statoFlussoEntity, identificativoFlusso);

            output.getEsito().setCodiceErrore(CostantiErrori.WS_ESITO_OK_DEFAULT);
            output.getEsito ().setDescrizione ( "Elaborazione completata con successo" );
        } catch ( EpaymodricException e ) {
            output.getEsito ().setDescrizione ( e.getMessage () );
            output.getEsito ().setCodiceErrore ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }

        return output;
    }


    @Override
    @Transactional
    public void cambiaStatoFlusso ( FlussoOrigine flussoOrigine, StatoFlussoEnum statoFlussoEnum ) {
        logger.info ( "FlussoOrigineManagerImpl.cambiaStatoFlusso: INIZIO" );
        CblTFlussoOrigine cblTFlussoOrigine = repository.findOne(flussoOrigine.getId());

        storicoManager.storicizzaDatiFlusso(flussoOrigine.getId());

        CblDStatoFlusso cblDStatoFlusso = statoFlussoRepository.findByCodiceStato(statoFlussoEnum.getCodice());
        cblTFlussoOrigine.setCblDStatoFlusso(cblDStatoFlusso);
        cblTFlussoOrigine.setUtenteModifica ( StringUtils.isNotEmpty ( flussoOrigine.getUtenteModifica () )?flussoOrigine.getUtenteModifica ():Costanti.DEFAULT_UTENTE_SISTEMA );
        cblTFlussoOrigine.setDataModifica ( new Timestamp ( System.currentTimeMillis () ) );
        repository.saveAndFlush ( cblTFlussoOrigine );
        logger.info ( "FlussoOrigineManagerImpl.cambiaStatoFlusso: FINE" );
    }

    //Recupera i flussi che hanno lo stato 7 sulla CblTFlussoOrigine
    //chiamo il servizio esitoFlussiPagoPA
    //In base alla risposta faccio update nella origine
    @Override
    public EsitoFlussiPagoPAResponse esitoFlussiPagoPA () {
        EsitoFlussiPagoPAResponse outputWsEsitoPagoPA = new EsitoFlussiPagoPAResponse ();
        List<Long> idStatoFlusso = new ArrayList<> ();
        String codice = StatoFlussoEnum.ACQUISITO.getCodice ();
        CblDStatoFlusso cblDStatoFlusso = statoFlussoRepository.findByCodiceStato ( codice );
        Long statoAcquisito = cblDStatoFlusso.getId ();
        idStatoFlusso.add ( statoAcquisito );
        List<CblTFlussoOrigine> listaFlussiOrigine
        = repository.findAll ( selezioneFlussiAggiornatiESchedulati ( idStatoFlusso ) );
        //Decodificare CblTFlussoOrigine in FlussoOrigine
        if ( !listaFlussiOrigine.isEmpty () ) {
            for ( CblTFlussoOrigine cblTFlussoOrigine: listaFlussiOrigine ) {

                TestataTrasmissioneFlussiType testata = new TestataTrasmissioneFlussiType ();
                testata.setCFEnteCreditore ( cblTFlussoOrigine.getIdentificativoIstitutoRicevente () );
                testata.setDataOraFlusso ( ConversionUtils.convertDateToXmlGregorianCalendar ( cblTFlussoOrigine.getDataoraFlusso () ) );
                testata.setIdentificativoFlusso ( cblTFlussoOrigine.getIdentificativoFlusso () );
                testata.setIdMessaggio ( TrasmissioneFlussiManagerImpl.getIdMessaggio ( cblTFlussoOrigine.getId () ) );
                testata.setIdPSP ( cblTFlussoOrigine.getCblTPsp ().getIdentificativoPsp () );

                String idEnte = cblTFlussoOrigine.getCblTEnte ().getIdEnte ();

                Configurazione endpoint = configurazioneManager.leggi ( idEnte, Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI );

                if ( null != endpoint && null != endpoint.getValore () ) {

                    outputWsEsitoPagoPA = riconciliazioneVersamentiFacade
                                    .esitoFlussiPagoPA ( endpoint.getValore (), testata );

                    FlussoOrigine flussoOrigine = FlussiUtility.getFlussoOrigine ( cblTFlussoOrigine );
                    //Se l'esito e' ELABORATO faccio l'update nella flussi origine con stato GENERATO
                    if ( null != outputWsEsitoPagoPA && null != outputWsEsitoPagoPA.getStatoElaborazioneFlusso () ) {
                        if ( Costanti.STATOFLUSSO_CONFRONTO_WS_ESITOFLUSSIPAGOPA_ELABORATO
                                        .equalsIgnoreCase ( outputWsEsitoPagoPA.getStatoElaborazioneFlusso ().toString () ) ) {
                            //UPDATE NELLA ORIGINE A GENERATO
                            cambiaStatoFlusso ( flussoOrigine, StatoFlussoEnum.GENERATO );

                        }
                        //Se l'esito e' IN_ERRORE faccio l'update nella flussi origine con stato RIFIUTATO
                        if ( Costanti.STATOFLUSSO_CONFRONTO_WS_ESITOFLUSSIPAGOPA_IN_ERRORE
                                        .equalsIgnoreCase ( outputWsEsitoPagoPA.getStatoElaborazioneFlusso ().toString () ) ) {
                            //UPDATE NELLA ORIGINE A RIFIUTATO (9)
                            cambiaStatoFlusso ( flussoOrigine, StatoFlussoEnum.RIFIUTATO );
                        }

                    }

                    logger.info ( "Chiamata con identificativoFlusso: " + flussoOrigine.getIdentificativoFlusso () + " " + "StatoFlusso ricevuto "
                                    + outputWsEsitoPagoPA.getStatoElaborazioneFlusso () );
                } else {
                    ResultType resultType = new ResultType ();
                    resultType.setCodice ( CostantiErrori.ERRORE_DATI_MANCANTI );
                    resultType.setMessaggio ( String.format ( "Parametro di configurazione %s assente", Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI ) );
                    outputWsEsitoPagoPA.setResult ( resultType );
                }
            }

        }
        return outputWsEsitoPagoPA;

    }

    public FlussoOrigineRepository getRepository () {
        return repository;
    }

    @Override
    @Transactional
    @Modifying
    public void impostaStatoFlussoEdElaborazione(FlussoOrigine flussoOrigine, Elaborazione elaborazione) {
        CblDStatoFlusso  cblDStatoFlusso = statoFlussoRepository.findByCodiceStato(StatoFlussoEnum.IN_ELABORAZIONE.getCodice());
        CblTElaborazione cblTElaborazione = elaborazioneRepository.findOne(elaborazione.getId());
        CblTFlussoOrigine cblTFlussoOrigine = repository.findOne(flussoOrigine.getId());
        CblTEnte cblTEnte = enteRepository.findByIdEnte ( elaborazione.getIdEnte () );

        storicoManager.storicizzaDatiFlusso(cblTFlussoOrigine.getId());

        cblTFlussoOrigine.setCblTElaborazione(cblTElaborazione);
        cblTFlussoOrigine.setCblDStatoFlusso(cblDStatoFlusso);
        cblTFlussoOrigine.setCblTEnte ( cblTEnte );

        cblTFlussoOrigine.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
        cblTFlussoOrigine.setDataModifica ( new Timestamp ( System.currentTimeMillis () ) );

        repository.saveAndFlush(cblTFlussoOrigine);
    }

    @Override
    public List<FlussoOrigine> leggi (ArrayList<Long> ids) {
        logger.info ( "FlussoOrigineManagerImpl.FindByIds: INIZIO" );
        List<FlussoOrigine> flussiToReturn = new LinkedList<> ();
        List<CblTFlussoOrigine> cblFlussoOrigine = repository.findByIdIn ( ids );
        for ( int i = 0; i < cblFlussoOrigine.size (); i++ ) {
            flussiToReturn.add ( FlussiUtility.getFlussoOrigine ( cblFlussoOrigine.get ( i ) ) );
        }
        logger.info ( "FlussoOrigineManagerImpl.FindByIds: FINE" );
        return flussiToReturn;
    }

    @Override
    public FlussoOrigine leggi (Long id) {
        logger.info ( "FlussoOrigineManagerImpl.findOne: INIZIO" );
        FlussoOrigine flussoToReturn = new FlussoOrigine ();
        CblTFlussoOrigine cblFlussoOrigine = repository.findOne ( id );
        flussoToReturn = FlussiUtility.getFlussoOrigine ( cblFlussoOrigine );
        logger.info ( "FlussoOrigineManagerImpl.findOne: FINE" );
        return flussoToReturn;
    }

    @Override
    public List<FlussoOrigine> recuperaFlussiDaElaborare ( String identificativoIstitutoRicevente, List<String> identificativiFlusso, int pageNumber,
        int pageSize, List<String> statiDaEscludere, Long idElaborazione ) {

        List<FlussoOrigine> flussiDaElaborare = new ArrayList<> ();
        List<String> statiAmmessi=  StatoFlussoUtility.getStatiFlussoDaElaborare (statiDaEscludere);

        if ( null != statiAmmessi &&  statiAmmessi.size () > 0 )
        {
//            List<CblDStatoFlusso> cblDStatiFlusso = statoFlussoRepository.findByCodiceStatoIn ( statiAmmessi);

//            List<CblTFlussoOrigine> entities = new ArrayList<>();

            Page<CblTFlussoOrigine> entitiesPage = null;

            if ( null == identificativiFlusso || identificativiFlusso.size () == 0 ) {

                Pageable page = new PageRequest ( 0, pageSize, new Sort ( Sort.Direction.ASC, CblTFlussoOrigine_.id.getName () ) );

                entitiesPage = repository
                                .findAll ( new FlussoOrigineByStatusSpecification ( identificativoIstitutoRicevente,
                                		statiAmmessi, idElaborazione), page );
            } else {

                Pageable page = new PageRequest ( pageNumber, pageSize, new Sort ( Sort.Direction.ASC, CblTFlussoOrigine_.id.getName () ) );

                entitiesPage = repository
                                .findAll ( new FlussoOrigineByFlussoSpecification ( identificativoIstitutoRicevente, identificativiFlusso ), page );
            }

            if ( null != entitiesPage && entitiesPage.getContent () != null ) {
                for ( CblTFlussoOrigine entity: entitiesPage.getContent () ) {
                    flussiDaElaborare.add(FlussiUtility.getFlussoOrigine(entity));
                }
            }
        }

        return flussiDaElaborare;
    }

    @Override
    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine ( DTOInputWsRicercaFlussoOrigine inputws ) {
        DTOOutputWsFlussoOrigine dtoOutputWs = new DTOOutputWsFlussoOrigine ();
        logger.info ( "FlussoOrigineManagerImpl.ricercaFlussoOrigine: INIZIO" );

        DTOOutputWsEsito dtoEsito = null;
        List<DTOFlussoOrigine> lista = new LinkedList<> ();
        try {

            if ( null != inputws.getIdentificativiFlusso () && inputws.getIdentificativiFlusso ().size () > 0 ) {
                CblTEnte ente = enteRepository.findByCodiceFiscale ( inputws.getCaller ().getPrincipal ().getCodiceEnte () );
                List<CblTFlussoOrigine> flussiEntities
                = repository.findAllByIdentificativoFlussoInAndCblTEnteIdEnte ( inputws.getIdentificativiFlusso (), ente.getIdEnte () );

                for ( CblTFlussoOrigine flussoEntity: flussiEntities ) {
                    lista.add ( DTOFlussoOrigineUtility.getFlusso ( flussoEntity ) );
                }
                dtoOutputWs.setFlussiOrigine ( lista );
                dtoOutputWs.setLength ( flussiEntities.size () );
                dtoOutputWs.setStart ( 0 );
                dtoOutputWs.setTotalElements ( Long.valueOf ( flussiEntities.size () ) );
            } else {
                dtoOutputWs = FlussiOrigineRepositorySpec.cercaPerFiltro ( inputws );
            }
        } catch ( Exception e ) {
            logger.error ( "FlussoOrigineManagerImpl.ricercaFlussoOrigine: errore " + e.getMessage () );
            CblDErrore cblDErrore = erroreRepository.findByCodiceErrore ( CostantiErrori.ERRORE_DI_SISTEMA );
            dtoEsito = FlussiUtilityWS.getDtoEsitoFromCblDErrore ( cblDErrore );
            dtoOutputWs.setEsito ( dtoEsito );
        }
        logger.info ( "FlussoOrigineManagerImpl.ricercaFlussoOrigine: FINE" );
        return dtoOutputWs;
    }

    public void setRepository ( FlussoOrigineRepository repository ) {
        this.repository = repository;
    }

    @Override
    public long contaFlussiOrigineDaElaborare ( String identificativoIstitutoRicevente, List<String> identificativiFlusso, List<String> statiDaEscludere ) {

        long numFlussiDaElaborare = 0;
        List<String> statiDaElaborare= StatoFlussoUtility.getStatiFlussoDaElaborare ( statiDaEscludere );
        if ( null != statiDaElaborare &&  !statiDaElaborare.isEmpty () )
        {
        	 List<CblDStatoFlusso> cblDStatiFlusso = statoFlussoRepository.findByCodiceStatoIn ( statiDaElaborare );

             //Mi posiziono alle ore 1 del giorno corrente o del precedente se sono per es. alle 12:30
             Calendar now = Calendar.getInstance ();
             now.set ( Calendar.HOUR_OF_DAY, 1 );
             now.set ( Calendar.MINUTE, 0 );
             now.set ( Calendar.SECOND, 0 );

             //Se sono per es. alle 12:00 mi posiziono all'1 del giorno precedente (-24 ore)
             if ( now.after ( new Date () ) ) {
                 now.add ( Calendar.HOUR, -24 );
             }

             if ( null == identificativiFlusso || identificativiFlusso.isEmpty () ) {

                 numFlussiDaElaborare
                 = repository.countByIdentificativoIstitutoRiceventeAndCblDStatoFlussoInOrderByIdAsc ( identificativoIstitutoRicevente, cblDStatiFlusso,
                     now.getTime () );

             } else {

                 numFlussiDaElaborare = repository
                                 .countByIdentificativoIstitutoRiceventeAndCblDStatoFlussoInAndIdentificativoFlussoIn ( identificativoIstitutoRicevente, identificativiFlusso,
                                     now.getTime () );

             }
        }

       
        return numFlussiDaElaborare;
    }

}
