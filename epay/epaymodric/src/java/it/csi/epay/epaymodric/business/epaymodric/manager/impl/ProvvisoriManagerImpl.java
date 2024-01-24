/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.business.epaymodric.bo.Provvisori;
import it.csi.epay.epaymodric.business.epaymodric.bo.StatoProvvisorio;
import it.csi.epay.epaymodric.business.epaymodric.facade.RiconciliazioneVersamentiFacade;
import it.csi.epay.epaymodric.business.epaymodric.manager.EnteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EsitoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.InvioMailManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ProvvisoriManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ProvvisoriUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTProvvisorio;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ProvvisoriRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOProvvisorio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsCancellaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsProvvisori;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.ModalitaAcquisizioneProvvisoriEnum;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ProvvisorioType;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.RicercaProvvisoriPagoPARequest;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.RicercaProvvisoriPagoPAResponse;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.StatoProvvisorioType;

/**
 * @author vsgro
 */
@Service
public class ProvvisoriManagerImpl implements ProvvisoriManager {

    private static java.util.ResourceBundle res;

    static {
              try {
        res = java.util.ResourceBundle.getBundle ( "config" );
              } catch(MissingResourceException e) {
                  System.out.println(e);
              }
    }

    public static java.util.ResourceBundle getRes () {
        return res;
    }

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private ProvvisoriRepository provvisorioRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private InvioMailManager invioMailManager;

    @Autowired
    private RiconciliazioneVersamentiFacade riconciliazioneVersamentiFacade;

    @Autowired
    private EnteManager enteManager;

    @Autowired
    private EsitoManager esitoManager;

    @Override
    public DTOOutputWsEsito aggiornaProvvisori ( DTOInputWsAggiornaProvvisori paramProvvisori) {

        DTOOutputWsEsito esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );

        esito.setEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        esito.setCodiceErrore ( CostantiErrori.WS_ESITO_OK_DEFAULT );

        String idEnte = paramProvvisori.getIdentificativoEnte ();

        if ( ( idEnte == null ) || ( idEnte.isEmpty () ) ) {
            idEnte = paramProvvisori.getCaller ().getPrincipal ().getCodiceEnte ();
        }

        if ( paramProvvisori.getDtoProvvisorioList () != null && paramProvvisori.getDtoProvvisorioList ().size () > 0 && !StringUtils.isEmpty ( idEnte ) ) {

            List<Provvisori> provvisoriDaSalvare = new ArrayList<> ();

            for ( DTOProvvisorio dtoProvvisorioCorrente: paramProvvisori.getDtoProvvisorioList () ) {
                Provvisori provvisorioDaSalvare = ProvvisoriUtility.getProvvisorioFromDTOProvvisorio ( dtoProvvisorioCorrente );
                provvisoriDaSalvare.add ( provvisorioDaSalvare );
            }

            Ente ente = enteManager.leggiPerCodiceFiscaleEnte ( idEnte );

            if ( null == ente ) {
                esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
            }

            if ( esito != null && CostantiErrori.WS_ESITO_OK_DEFAULT.equalsIgnoreCase ( esito.getCodiceErrore () ) ) {

                boolean result = aggiornaProvvisoriLocal ( provvisoriDaSalvare, ente.getId (), paramProvvisori );

                if ( !result ) {
                    esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_DEFAULT );
                }

            }

        } else {
            esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }

        return esito;

    }

    private boolean aggiornaProvvisoriLocal ( List<Provvisori> provvisoriDaSalvare, Long idEnte, DTOInputWsAggiornaProvvisori dtoInput ) {
        logger.info ( "ProvvisoriManagerImpl.salvaProvvisori: INIZIO" );

        boolean result = false;

        if ( !CollectionUtils.isEmpty ( provvisoriDaSalvare ) ) {
            for ( Provvisori provvisorio: provvisoriDaSalvare ) {
                CblTProvvisorio provvisorioCorrente = ProvvisoriUtility.getProvvisoriEntity ( provvisorio );

                try {
                    List<CblTProvvisorio> provvisoriDaAnnullare = provvisorioRepository.findAllByIdentificativoFlussoAndStato ( provvisorioCorrente.getIdentificativoFlusso (), StatoProvvisorio.VALIDO.value () );

                    if ( !CollectionUtils.isEmpty ( provvisoriDaAnnullare ) ) {
                        for ( CblTProvvisorio provvisorioDaAnnullare: provvisoriDaAnnullare ) {

                            provvisorioDaAnnullare
                            .setUtenteModifica ( null != dtoInput && StringUtils.isNotEmpty ( dtoInput.getCaller ().getPrincipal ().getCodiceFiscale () )
                            ? dtoInput.getCaller ().getPrincipal ().getCodiceFiscale () : Costanti.DEFAULT_UTENTE_SISTEMA );
                            provvisorioDaAnnullare.setDataModifica ( new Timestamp ( System.currentTimeMillis () ) );
                            provvisorioDaAnnullare.setStato ( StatoProvvisorio.ANNULLATO.value () );

                            completaProvvisorio ( provvisorioDaAnnullare, dtoInput, false );

                            provvisorioRepository.save ( provvisorioDaAnnullare );
                        }
                    }
                    provvisorioCorrente
                    .setUtenteInserimento ( null != dtoInput && StringUtils.isNotEmpty ( dtoInput.getCaller ().getPrincipal ().getCodiceFiscale () )
                    ? dtoInput.getCaller ().getPrincipal ().getCodiceFiscale () : Costanti.DEFAULT_UTENTE_SISTEMA );
                    provvisorioCorrente.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );
                    try {
                        completaProvvisorio ( provvisorioCorrente, dtoInput, true );

                        //Forzo una insert sempre e comunque
                        provvisorioCorrente.setId ( null );
                        provvisorioCorrente.setStato ( StatoProvvisorio.VALIDO.value () );
                        provvisorioRepository.save ( provvisorioCorrente );

                    } catch ( Exception e ) {
                    	logger.error ( String.format ( "Errore nel salvataggio dei provvisori ", provvisorio.getIdentificativoFlusso () ), e );
                        invioMailManager.invioMail ( EmailEnum.ERRORE_DI_SISTEMA, CostantiErrori.ERRORE_SALVATAGGIO_PROVVISORI,
                            null != idEnte ? idEnte.toString () : null, null, null );
                    }
                } catch ( IncorrectResultSizeDataAccessException e ) {
                    logger.error ( String.format ( "Trovati piu provvisori per l'identificativo flusso %s", provvisorio.getIdentificativoFlusso () ), e );
                }
                result = true;
            }
        }

        logger.info ( "ProvvisoriManagerImpl.salvaProvvisori: FINE" );
        return result;
    }

    @Override
    public DTOOutputWsEsito cancellaProvvisori ( DTOInputWsCancellaProvvisori in ) {

        DTOOutputWsEsito esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );

        if ( null != in && in.getDtoProvvisorioList () != null && ( in.getDtoProvvisorioList ().size () > 0 ) ) {

            for ( DTOProvvisorio dtoProvvisorio: in.getDtoProvvisorioList () ) {
                Provvisori provvisorio = ProvvisoriUtility.getProvvisorioFromDTOProvvisorio ( dtoProvvisorio );

                provvisorioRepository.delete ( provvisorio.getId () );
            }
        } else {
            esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }

        return esito;

    }

    private void completaProvvisorio ( CblTProvvisorio cblTProvvisorio, DTOInputWsAggiornaProvvisori dtoInputBase, boolean inserimento ) {
        if ( dtoInputBase != null ) {
            if ( cblTProvvisorio.getCausale () == null || cblTProvvisorio.getCausale ().isEmpty () ) {
                cblTProvvisorio.setCausale ( Costanti.PREFISSO_CAUSALE_PROVVISORI + cblTProvvisorio.getIdentificativoFlusso () );
            }

            if ( cblTProvvisorio.getIdEnte () == null || cblTProvvisorio.getIdEnte ().isEmpty () ) {
                CblTEnte ente = enteRepository.findByCodiceFiscale ( dtoInputBase.getCaller ().getPrincipal ().getCodiceEnte ());
                cblTProvvisorio.setIdEnte ( ente.getIdEnte () );
            }

            if ( cblTProvvisorio.getUtenteInserimento () == null || cblTProvvisorio.getUtenteInserimento ().isEmpty () ) {
                cblTProvvisorio.setUtenteModifica ( dtoInputBase.getCaller ().getPrincipal ().getCodiceFiscale () );
            }

            if (( dtoInputBase.getNonBatch () == true ) && (inserimento == false)) {
                cblTProvvisorio.setUtenteModifica ( dtoInputBase.getCaller ().getPrincipal ().getCodiceFiscale () );
            }

            if ( inserimento == true ) {
                cblTProvvisorio.setUtenteInserimento ( dtoInputBase.getCaller ().getPrincipal ().getCodiceFiscale () );
            }
        }

        //Se non setto id a nullo fa un update su db al posto di update ad annullato ed insert
        cblTProvvisorio.setImportoDisponibilita ( cblTProvvisorio.getImportoProvvisorio () );
        cblTProvvisorio.setTipoMovimento ( ProvvisoriUtility.DESCRIZIONE_DEFAULT );
    }

    @Override
    public List<Provvisori> leggi () {
        List<Provvisori> provvisori = null;
        logger.info ( "ProvvisoriManagerImpl.findAll: INIZIO" );
        List<CblTProvvisorio> cblTProvvisorio = provvisorioRepository.findAll ();
        if ( cblTProvvisorio != null && !cblTProvvisorio.isEmpty () ) {
            provvisori = new LinkedList<> ();
            for ( int i = 0; i < cblTProvvisorio.size (); i++ ) {
                provvisori.add (
                    ProvvisoriUtility.getProvvisori ( cblTProvvisorio.get ( i ) ) );
            }
        }
        logger.info ( "ProvvisoriManagerImpl.findAll: FINE" );
        return provvisori;
    }

    @Override
    public List<Provvisori> leggi ( String idEnte, String identificativoFlusso ) {
        List<Provvisori> provvisori = null;
        logger.info ( "ProvvisoriManagerImpl.findByIdEnteAndIdentificativoFlusso: INIZIO" );
        List<CblTProvvisorio> cblTProvvisorio
        = provvisorioRepository.findAllByIdEnteAndIdentificativoFlussoAndStato ( idEnte, identificativoFlusso, StatoProvvisorio.VALIDO.value () );
        if ( cblTProvvisorio != null && !cblTProvvisorio.isEmpty () ) {
            provvisori = new ArrayList<> ();
            for ( int i = 0; i < cblTProvvisorio.size (); i++ ) {
                provvisori.add ( ProvvisoriUtility.getProvvisori ( cblTProvvisorio.get ( i ) ) );
            }
        }
        logger.info ( "ProvvisoriManagerImpl.findByIdEnteAndIdentificativoFlusso: FINE" );
        return provvisori;
    }

    @Override
    public DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori ) {

        if ( ( null == dtoInputWsRicercaProvvisori.getDataDa () && null != dtoInputWsRicercaProvvisori.getDataA () )
                        || ( null != dtoInputWsRicercaProvvisori.getDataDa () && null == dtoInputWsRicercaProvvisori.getDataA () ) ) {
            throw new IllegalArgumentException ( "Le date di inizio e fine devono essere valorizzate contemporaneamente" );
        }

        if ( null != dtoInputWsRicercaProvvisori.getDataDa ()
                        && dtoInputWsRicercaProvvisori.getDataA ().toGregorianCalendar ().before ( dtoInputWsRicercaProvvisori.getDataDa () ) ) {
            throw new IllegalStateException ( "La data di fine deve essere uguale o successiva a quella di inizio" );
        }

        DTOOutputWsProvvisori response = new DTOOutputWsProvvisori ();

        List<CblTProvvisorio> provvisoriList = new ArrayList<> ();

        ArrayList<DTOProvvisorio> provvisoriDto = new ArrayList<> ();

        response.setEsito ( esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT ) );
        response.getEsito ().setEsito (  CostantiErrori.WS_ESITO_OK_DEFAULT );
        response.getEsito ().setCodiceErrore (  CostantiErrori.WS_ESITO_OK_DEFAULT );
        response.setProvvisori ( provvisoriDto );

        try {
            if ( dtoInputWsRicercaProvvisori.getId () != null ) {
                CblTProvvisorio item = provvisorioRepository.findOne ( dtoInputWsRicercaProvvisori.getId () );
                provvisoriList.add ( item );
            } else {
                if ( dtoInputWsRicercaProvvisori.getCaller () == null || dtoInputWsRicercaProvvisori.getCaller ().getPrincipal () == null
                                || dtoInputWsRicercaProvvisori.getCaller ().getPrincipal ().getCodiceEnte () == null
                                || dtoInputWsRicercaProvvisori.getCaller ().getPrincipal ().getCodiceEnte ().isEmpty () ) {
                    throw new Exception ( "Codice fiscale ente non valorizzato dal chiamante" );
                }

                CblTEnte enteProvvisorio = enteRepository.findByCodiceFiscale ( dtoInputWsRicercaProvvisori.getCaller ().getPrincipal ().getCodiceEnte () );

                GregorianCalendar gcal = new GregorianCalendar ();
                gcal.setTimeInMillis ( 0 );

                if ( dtoInputWsRicercaProvvisori.getDataDa () == null ) {
                    dtoInputWsRicercaProvvisori.setDataDa ( DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gcal ) );
                }

                Date fromDate = DateUtils.xmlGregorianCalendarToDate ( dtoInputWsRicercaProvvisori.getDataDa () );
                Date toDate = null;

                if ( dtoInputWsRicercaProvvisori.getDataA () != null ) {
                    toDate = DateUtils.xmlGregorianCalendarToDate ( dtoInputWsRicercaProvvisori.getDataA () );
                }

                if ( dtoInputWsRicercaProvvisori.getIdentificativoFlusso () == null ) {
                    dtoInputWsRicercaProvvisori.setIdentificativoFlusso ( "" );
                }

                Collection<String> stati = new ArrayList<> ();

                if ( dtoInputWsRicercaProvvisori.getIdStatoFlusso () == null || dtoInputWsRicercaProvvisori.getIdStatoFlusso ().isEmpty () ) {
                    for ( int i = 0; i < StatoProvvisorio.values ().length; i++ ) {
                        stati.add ( StatoProvvisorio.values () [i].value () );
                    }
                } else {
                    stati.add ( dtoInputWsRicercaProvvisori.getIdStatoFlusso () );
                }

                //repositoryFilter

                if ( enteProvvisorio != null ) {
                    if ( toDate != null ) {
                        provvisoriList
                        = provvisorioRepository
                        .findByIdEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqualAndDataMovimentoLessThanEqual (
                            enteProvvisorio.getIdEnte (),
                            "%" + dtoInputWsRicercaProvvisori.getIdentificativoFlusso () + "%",
                            stati,
                            fromDate,
                            toDate );
                    } else {
                        provvisoriList = provvisorioRepository.findByIdEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqual (
                            enteProvvisorio.getIdEnte (),
                            "%" + dtoInputWsRicercaProvvisori.getIdentificativoFlusso () + "%",
                            stati,
                            fromDate );
                    }
                }
            }

            for ( CblTProvvisorio provvisorio: provvisoriList ) {
                DTOProvvisorio dto = new DTOProvvisorio ();

                dto.setAnnoEsercizio ( provvisorio.getAnnoEsercizio () );
                dto.setAnnoProvvisorio ( provvisorio.getAnnoProvvisorio () );
                dto.setCausale ( provvisorio.getCausale () );

                if ( provvisorio.getDataFine () != null ) {
                    dto.setDataFine ( DateUtils.dateToXMLGregorianCalendar ( provvisorio.getDataFine () ) );
                }

                if ( provvisorio.getDataMovimento () != null ) {
                    dto.setDataMovimento ( DateUtils.dateToXMLGregorianCalendar ( provvisorio.getDataMovimento () ) );
                }

                dto.setDescrizione ( provvisorio.getDescrizione () );
                dto.setIdentificativoFlusso ( provvisorio.getIdentificativoFlusso () );
                dto.setImportoDisponibilita ( provvisorio.getImportoDisponibilita () );
                dto.setImportoProvvisorio ( provvisorio.getImportoProvvisorio () );
                dto.setNumeroProvvisorio ( provvisorio.getNumeroProvvisorio () );
                dto.setStato ( provvisorio.getStato () );
                dto.setTipoMovimento ( provvisorio.getTipoMovimento () );
                dto.setId ( provvisorio.getId () );

                provvisoriDto.add ( dto );
            }

        } catch ( Exception e ) {
            response.setEsito ( esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_DEFAULT ) );
            logger.error ( "ERRORE DURANTE LA CHIAMATA AL WS DI RICERCA PROVVISORI", e );
        }

        return response;
    }

    @Override
    public List<Provvisori> richiediProvvisori ( Configurazione conf, String identificativoEnte, List<String> causaliVersamento, Date dataInizio, Date dataFine,
        BigInteger numeroProvvisorioDa, BigInteger numeroProvvisorioA ) {

        if ( StringUtils.isEmpty ( identificativoEnte ) ) {
            throw new IllegalArgumentException ( "L'identificativo ente deve essere valorizzato" );
        }

        if ( ( null == dataInizio && null != dataFine ) || ( null != dataInizio && null == dataFine ) ) {
            throw new IllegalArgumentException ( "Le date di inizio e fine devono essere valorizzate contemporaneamente" );
        }

        if ( null != dataInizio && dataFine.before ( dataInizio ) ) {
            throw new IllegalStateException ( "La data di fine deve essere uguale o successiva a quella di inizio" );
        }

        if ( ( null == numeroProvvisorioDa && null != numeroProvvisorioA ) || ( null != numeroProvvisorioDa && null == numeroProvvisorioA ) ) {
            throw new IllegalArgumentException ( "Numero provvisorio A e numero provvisorio Da devono essere valorizzati contemporaneamente" );
        }

        if ( null != numeroProvvisorioDa && numeroProvvisorioDa.compareTo ( numeroProvvisorioA ) > 0 ) {
            throw new IllegalStateException ( "Il numero provvisorio di partenza deve essere minore o uguale a quello di arrivo" );
        }

        if ( null == dataInizio && null == numeroProvvisorioA && ( null == causaliVersamento || causaliVersamento.size () == 0 ) ) {
            throw new IllegalArgumentException ( "Valorizzare almeno un parametro di input" );
        }

        Ente ente = enteManager.leggiPerCodiceFiscaleEnte ( identificativoEnte );

        if ( null == ente ) {
            throw new IllegalStateException ( "Ente non esistente" );
        }

        if ( ! ( ente.isFlagRiconciliazione () ) ) {
            throw new IllegalStateException ( "L'ente deve avere flag riconciliazione a true" );
        }

        if ( !ModalitaAcquisizioneProvvisoriEnum.SERVIZI.equals ( ente.getModalitaAcquisizioneProvvisori () ) ) {
            throw new IllegalStateException ( "L'ente deve essere configurato per ricevere i provvisori \"a servizi\"" );
        }

        RicercaProvvisoriPagoPARequest request = new RicercaProvvisoriPagoPARequest ();

        request.setCFEnteCreditore ( identificativoEnte );
        if ( null != dataInizio ) {
            try {
                request.setDataProvvisorioDal ( DateUtils.dateToXMLGregorianCalendar ( dataInizio ) );
                request.setDataProvvisorioAl ( DateUtils.dateToXMLGregorianCalendar ( dataFine ) );
            } catch ( DatatypeConfigurationException e ) {
                logger.error ( "Errore in fase di creazione delle date inizo e fine validita' provvisorio!", e );
            }
        }

        request.setNumeroProvvisorioAl ( numeroProvvisorioA );
        request.setNumeroProvvisorioDal ( numeroProvvisorioDa );

        ElencoCausaliVersamenti elencoCausaliVersamenti = new RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti ();

        for ( String causale: causaliVersamento ) {
            elencoCausaliVersamenti.getCausaleVersamento ().add ( causale );
        }

        request.setElencoCausaliVersamenti ( elencoCausaliVersamenti );

        request.setStato ( StatoProvvisorioType.VALIDO );

        List<Provvisori> provvisoriDaSalvare = new ArrayList<> ();

        try {
            RicercaProvvisoriPagoPAResponse response
            = riconciliazioneVersamentiFacade
            .ricercaProvvisoriPagoPA ( /* getRes ().getString ( "service.riconciliazioneversamenti.endpoint" ), */conf.getValore (), request );

            List<ProvvisorioType> provvisoriResponse = new ArrayList<> ();

            if ( null != response && null != response.getElencoProvvisori () && null != response.getElencoProvvisori ().getProvvisorio () ) {
                provvisoriResponse = response.getElencoProvvisori ().getProvvisorio ();
            }
            
            //Da utilizzare per provare in locale
//            List<ProvvisorioType> provvisoriResponse = getProvvisoriFake();

            for ( ProvvisorioType provvisorioType: provvisoriResponse ) {
                provvisoriDaSalvare.add ( ProvvisoriUtility.getProvvisorioFromProvvisorioType ( provvisorioType, ente.getIdEnte () ) );
            }

            aggiornaProvvisoriLocal ( provvisoriDaSalvare, ente.getId (), null);
        } catch ( Exception e ) {
            //TODO TROVARE-UN-MODO-DIVERSO
            logger.error ( "ERRORE DURANTE LA CHIAMATA AL WS DI RICERCA PROVVISORI", e );
        }

        return provvisoriDaSalvare;
    }

	private List<ProvvisorioType> getProvvisoriFake() {
		
		 List<ProvvisorioType> provvisoriResponse = new ArrayList<> ();
		 ProvvisorioType provvisorio1= new ProvvisorioType();
		 
		 provvisorio1.setAnnoEsercizio(new BigInteger("2021"));
		 provvisorio1.setImporto(new BigDecimal("1000"));
		 provvisorio1.setCausaleVersamento("/PUR/LGPE-RIVERSAMENTO/URI/RF05202160003AAA100000001");
		 provvisorio1.setNumero(new BigInteger("1"));
		 try {
			provvisorio1.setData( DateUtils.dateToXMLGregorianCalendar ( new Date() ) );
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 provvisorio1.setSoggetto("prova");
		 
		 provvisorio1.setStato(StatoProvvisorioType.VALIDO);
		 
		 
		 
		 
		 provvisoriResponse.add(provvisorio1);
		// TODO Auto-generated method stub
		return provvisoriResponse;
	}

}
