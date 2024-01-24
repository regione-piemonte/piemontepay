/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.EPaywso2SportelloService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayservices.integration.db.entities.EpayDStatoPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayRTipoPagamentoCollegato;
import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroElaborazioniManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.mdp.MdpException;
import it.csi.epay.epayservices.integration.mdp.MultiIuv;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.RegistroElaborazioni;
import it.csi.epay.epayservices.model.RegistroElaborazioniFault;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.AggiornaPosizioniDebitorieRequest;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.AggiornaPosizioniDebitorieResponse;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.ComponenteImportoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.EsitoAggiornamentoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.PosizioneDaAggiornareType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.PosizioneDebitoriaType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.ResultType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.RiferimentoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.SoggettoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.TestataAggiornaPosizioniDebitorie;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.TipoAggiornamentoType;
import it.csi.epay.epayservices.ws.coopapplicativapec.TipoPagamentoType;
import it.csi.epay.epayservices.ws.exceptions.PosizioneDebitoriaException;
import it.csi.epay.epayservices.ws.exceptions.TestataException;

@Singleton(name="AggiornaPosizioniDebitorie", mappedName = "AggiornaPosizioniDebitorie")
public class AggiornaPosizioniDebitorie {

    protected LogUtil log = new LogUtil(this.getClass());

    static final private String OPERARIONE = "AggiornaPosizioniDebitorieRequest";
    private static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA = "ENDPOINT_SERVICE_TASSONOMIA";
    private static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA_LOCAL = "ENDPOINT_SERVICE_TASSONOMIA_LOCAL"; // endpoint da usare per le prove in locale
    private static final String COD_ENTE_DEFALUT = "PagoPa"; // codice 0


    static final private int maxErrorMessageWidth = 200;

    @EJB
    MultiIuv multiIuv;

    @EJB
    PagamentoManager pagamentoManager;

    @EJB
    RegistroVersamentiManager registroVersamentiManager;

    @EJB
    AnagraficaManager anagraficaManager;

    @EJB
    RegistroElaborazioniManager registroElaborazioniManager;

    @EJB
    EnteManager enteManager;

    @EJB
    TipoPagamentoManager tipoPagamentoManager;

    @EJB
    ConfigurazioneManager configurazioneManager;

    @PersistenceContext
    private EntityManager entityManager;

    public AggiornaPosizioniDebitorieResponse exec ( AggiornaPosizioniDebitorieRequest parameters ) {
        final String methodName = "exec";
        log.debugStart ( methodName );

        AggiornaPosizioniDebitorieResponse res = new AggiornaPosizioniDebitorieResponse ();
        TestataAggiornaPosizioniDebitorie testata = parameters.getTestata ();
        Ente ente = null;
        TipoPagamento tipoPagamento = null;
        TipoPagamento tipoPagamentoSecondario = null;
        TipoPagamento tipoPagamentoDaUsare = null;

        try {

            verificaNumeroTrasmissione ( testata.getIdMessaggio () );

            List<PosizioneDaAggiornareType> posizioniDaAggiornare
            = parameters.getElencoPosizioniDaAggiornare ().getPosizioniDaAggiornare ().getPosizioneDaAggiornare ();

            ente = verificaTestataEnte ( testata.getCFEnteCreditore () );
            //CSI_PAG-1888 INIZIO
            boolean bMultibeneficiario = testata.getMultiBeneficiario () != null ? testata.getMultiBeneficiario ().booleanValue () : false;
            tipoPagamento = verificaEsistenzaPagamentoPrincipale ( ente, testata.getCodiceVersamento () );
            tipoPagamentoDaUsare = tipoPagamento;
            verificaCompatibilitaFlagMultibeneficiario ( tipoPagamento, testata.getMultiBeneficiario () );
            verificaTestataTipoPagamento ( tipoPagamento );
            tipoPagamentoDaUsare = tipoPagamento;
            //CSI_PAG-1888 FINE
            verificaTestataNumeroPosizioni ( testata, posizioniDaAggiornare );

            // RDI-41
            Configurazione serviceTassonomia = configurazioneManager.getConfigurazione ( CONFIG_ENDPOINT_SERVICE_TASSONOMIA, COD_ENTE_DEFALUT );

            // spostarlo all'inizio prima della verifica dell'ente.
            if ( serviceTassonomia == null ) {
                log.error ( methodName,
                    "Errore in fase di reperimento del dato specifico riscossione, per aggiornamento posizioni debitorie: "
                                    + parameters.getTestata ().getIdMessaggio () +
                                "configurazione  CONFIG_ENDPOINT_SERVICE_TASSONOMIA mancante" );
                CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
                throw new TassonomiaServiceException ( ce.getCodice (),
                    ce.getMessaggio ( maxErrorMessageWidth ) + " , per aggiornamento posizioni debitorie: " + parameters.getTestata ().getIdMessaggio () +
                                "configurazione  CONFIG_ENDPOINT_SERVICE_TASSONOMIA mancante" );
            }

            String url = serviceTassonomia.getValore ();

            TassonomiaAdapterClient client = new TassonomiaAdapterClient ( configurazioneManager );
            DatiSpecificiRiscossioneOutput dsrSec = null;

            if ( bMultibeneficiario ) {
                tipoPagamentoSecondario = verificaEsistenzaPagamentoCollegato ( tipoPagamento );
                tipoPagamentoDaUsare = tipoPagamentoSecondario;
                verificaTestataTipoPagamento ( tipoPagamentoSecondario );
            }

			DatiSpecificiRiscossioneInput request = client.buildDatiSpecificiRiscossioneInput ( testata.getCFEnteCreditore (), testata.getCodiceVersamento () );

//			DatiSpecificiRiscossioneOutput dsr = ricercaDatiSpecificiRiscossione ( parameters, methodName, client, request );
            DatiSpecificiRiscossioneOutput dsr = null;

            RegistroElaborazioni registroElaborazioni = inserisciRegistroElaborazioniInizio ( testata, ente, tipoPagamento );

            EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate elencoPosizioniDebitorieAggiornate
            = new EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate ();
            List<PosizioneDebitoriaType> listaPosizioneDebitoriaResponse = elencoPosizioniDebitorieAggiornate.getPosizioneDebitoriaAggiornata ();

            for ( PosizioneDaAggiornareType posizione: posizioniDaAggiornare ) {
                PosizioneDebitoriaType posizioneDebitoriaResponse = new PosizioneDebitoriaType ();
                Pagamento pagamento = null;
                PagamentoSecondario pagamentoSecondario = null;
                try {
                    //a) Facciamo le verifiche sul pagamento principale e su quello secondario.
                    verificaElementiObbligatori ( posizione );
                    pagamento = verifichePagamentoPrincipale ( posizione, tipoPagamento );
                    verificaStato ( pagamento );
                    if ( bMultibeneficiario && TipoAggiornamentoType.MODIFICA.equals ( posizione.getTipoAggiornamento () ) ) {
                        pagamentoSecondario = verifichePagamentoSecondario ( posizione, tipoPagamentoSecondario, pagamento.getIdPagamento () );
                        verificaImportoSecondarioAltroEnte ( posizione );
                    }

                    //b) Facciamo gli aggiornamenti per il pagamento principale
                    switch ( posizione.getTipoAggiornamento () ) {
                    case ANNULLAMENTO :
                        //pagamentoManager.aggiornaDateValidita(pagamento.getIdPagamento(), pagamento.getInizioValidita(), new Date());
                        pagamentoManager.aggiornaNote ( pagamento.getIdPagamento (), posizione.getMotivazione () );
                        pagamentoManager.aggiornaStato ( pagamento.getIdPagamento (), StatoPagamento.INVALIDATO );

                        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
                        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
                        registroVersamenti.setDataOperazione ( null );
                        registroVersamenti.setIdStato ( StatoPagamento.INVALIDATO.getId () );
                        registroVersamenti.setRisultato ( StatoPagamento.INVALIDATO.getDescrizione () );
                        registroVersamenti.setOrigineInserimento ( this.getClass ().getName () );
                        registroVersamentiManager.inserisci ( registroVersamenti );

                        break;
                    case MODIFICA :
                        //In questo metodo vengono anche impostati gli importi dei due tipi di pagamento.
                        verificaTotaliImportoPagamento ( tipoPagamento, posizione, pagamento, pagamentoSecondario, bMultibeneficiario );

                        //Nelle verifiche fatte in precedenza i componenti degli importi e i riferimenti erano a posto.
                        boolean hasComponenti
                        = ! ( posizione.getComponentiImporto () == null || posizione.getComponentiImporto ().getComponenteImporto () == null
                        || posizione.getComponentiImporto ().getComponenteImporto ().isEmpty () );
                        boolean hasRiferimenti
                        = ! ( posizione.getRiferimentiPagamento () == null || posizione.getRiferimentiPagamento ().getRiferimento () == null
                        || posizione.getRiferimentiPagamento ().getRiferimento ().isEmpty () );
                        if ( StringUtils.isNoneEmpty ( posizione.getDescrizioneCausaleVersamento () ) ) {
                            pagamentoManager.aggiornaCausale ( pagamento.getIdPagamento (), posizione.getDescrizioneCausaleVersamento () );
                        }

                        if ( StringUtils.isNoneEmpty ( posizione.getMotivazione () ) ) {
                            pagamentoManager.aggiornaNote ( pagamento.getIdPagamento (), posizione.getMotivazione () );
                        }

                        if ( hasRiferimenti ) {
                            Pagamento pagamentoImporti = new Pagamento ();
                            pagamentoImporti.setIdPagamento ( pagamento.getIdPagamento () );
                            pagamentoImporti.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                            for ( RiferimentoType riferimentoType: posizione.getRiferimentiPagamento ().getRiferimento () ) {
                                PagamentoRiferimenti riferimento = new PagamentoRiferimenti ();
                                riferimento.setNome ( riferimentoType.getNome () );
                                riferimento.setValore ( riferimentoType.getValore () );
                                riferimento.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                pagamentoImporti.getRiferimenti ().add ( riferimento );
                            }
                            pagamentoManager.aggiornaRiferimenti ( pagamentoImporti );
                        }

                        // Importo Totale e Componenti
                        //solo componenti
                        Pagamento pagamentoImporti = new Pagamento ();
                        pagamentoImporti.setIdPagamento ( pagamento.getIdPagamento () );
                        pagamentoImporti.setImporto ( pagamento.getImporto () );
                        pagamentoImporti.setImportoPrincipale ( pagamento.getImportoPrincipale () );
                        pagamentoImporti.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                        if(null != posizione.getRequiresCostUpdate ()) {
                            pagamentoImporti.setRequiresCostUpdate ( posizione.getRequiresCostUpdate () );
                        }
                        if ( hasComponenti ) {
                            if ( null == dsr ) {
                                dsr = ricercaDatiSpecificiRiscossione ( parameters, methodName, client, request );
                            }
                            int progrComponente = 1;
                            for ( ComponenteImportoType componenteImportoType: posizione.getComponentiImporto ().getComponenteImporto () ) {
                                PagamentoComponenti componente = new PagamentoComponenti ();
                                componente.setProgressivo ( progrComponente++ );
                                componente.setImporto ( componenteImportoType.getImporto () );
                                componente.setCausale ( StringUtils.substring (componenteImportoType.getCausaleDescrittiva (), 0, 140) );
                                componente.setAnnoAccertamento (
                                    componenteImportoType.getAnnoAccertamento () != null ? componenteImportoType.getAnnoAccertamento () : null );
                                componente.setNumeroAccertamento (
                                    StringUtils.isBlank ( componenteImportoType.getNumeroAccertamento () ) ? null
                                                    : componenteImportoType.getNumeroAccertamento () );
                                componente.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );
                                pagamentoImporti.getComponenti ().add ( componente );
                                pagamentoManager.aggiornaImporti ( pagamentoImporti );
                            }
                        } else if ( CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
                            if ( null == dsr ) {
                                dsr = ricercaDatiSpecificiRiscossione ( parameters, methodName, client, request );
                            }
                            pagamentoImporti.getComponenti ().add ( creaComponenteDiDefault ( dsr, posizione, bMultibeneficiario ) );
                            pagamentoManager.aggiornaImporti ( pagamentoImporti );
                        } else {
                            if ( pagamento.getComponenti ().size () == 1 ) {
                                pagamento.getComponenti ().get ( 0 ).setImporto ( pagamento.getImporto () );
                                pagamentoImporti.getComponenti ().addAll ( pagamento.getComponenti () );
                            }
                            pagamentoManager.aggiornaImporti ( pagamentoImporti );
                        }

                        if ( posizione.getDataInizioValidita () != null || posizione.getDataFineValidita () != null || posizione.getDataScadenza () != null ) {
                            pagamentoManager.aggiornaDate ( pagamento.getIdPagamento (), posizione.getDataInizioValidita (), posizione.getDataFineValidita (),
                                posizione.getDataScadenza () );
                        }

                        if ( null != posizione.getSoggettoPagatore () && ( null != posizione.getSoggettoPagatore ().getPersonaFisica ()
                                        || null != posizione.getSoggettoPagatore ().getPersonaGiuridica () ) ) {
                            Anagrafica anagraficaNew = toAnagrafica ( posizione.getSoggettoPagatore () );
                            anagraficaNew.setIdAnagrafica ( pagamento.getPagatore ().getIdAnagrafica () );
                            anagraficaManager.aggiorna ( pagamento.getIdPagamento (), anagraficaNew );

                        }

                        //c) Aggiornamento dell'importo secondario
                        if ( bMultibeneficiario ) {
                            //Nelle verifiche fatte in precedenza i componenti degli importi e i riferimenti erano a posto.
                            boolean hasComponentiSecondari
                            = ! ( posizione.getComponentiImportoSecondario () == null
                            || posizione.getComponentiImportoSecondario ().getComponenteImporto () == null
                            || posizione.getComponentiImportoSecondario ().getComponenteImporto ().isEmpty () );
                            PagamentoSecondario pagamentoImportiSecondario = new PagamentoSecondario ();
                            pagamentoImportiSecondario.setIdPagamentoSecondario ( pagamentoSecondario.getIdPagamentoSecondario () );
                            pagamentoImportiSecondario.setIdPagamento ( pagamentoSecondario.getIdPagamento () );
                            pagamentoImportiSecondario.setImportoComplessivo ( pagamentoSecondario.getImportoComplessivo () );
                            pagamentoImportiSecondario.setCausale ( StringUtils.substring (pagamentoSecondario.getCausale (), 0, 140) );
                            if ( hasComponentiSecondari ) { //ce ne deve essere solo uno
                                if ( null == dsrSec ) {
                                    DatiSpecificiRiscossioneInput requestSec = new DatiSpecificiRiscossioneInput ();
                                    requestSec.setCodiceFiscaleEnte ( tipoPagamentoSecondario.getEpayTEnti ().getCodiceFiscale () );
                                    requestSec.setTipoPagamento ( tipoPagamentoSecondario.getCodiceVersamento () );
                                    requestSec.setAnnoEsercizio ( Calendar.getInstance ().get ( Calendar.YEAR ) );
                                    dsrSec = ricercaDatiSpecificiRiscossione ( parameters, methodName, client, requestSec );
                                }
                                for ( ComponenteImportoType componenteImportoType: posizione.getComponentiImportoSecondario ().getComponenteImporto () ) {
                                    PagamentoComponenti componente = new PagamentoComponenti ();
                                    componente.setImporto ( componenteImportoType.getImporto () );
                                    componente.setCausale ( StringUtils.substring (componenteImportoType.getCausaleDescrittiva (), 0, 140) );
                                    componente.setAnnoAccertamento (
                                        componenteImportoType.getAnnoAccertamento () != null ? componenteImportoType.getAnnoAccertamento () : null );
                                    componente.setNumeroAccertamento (
                                        StringUtils.isBlank ( componenteImportoType.getNumeroAccertamento () ) ? null
                                                        : componenteImportoType.getNumeroAccertamento () );
                                    componente.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsrSec );
                                    pagamentoImportiSecondario.getComponenti ().add ( componente );
                                }
                            }
                            pagamentoManager.aggiornaImportiSecondari ( pagamentoImportiSecondario );
                        }
                        break;
                    default :
                        CodiciEsito ce = CodiciEsito.TIPOAGGIORNAMENTO_SCONOSCIUTO;
                        throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
                    }

                    posizioneDebitoriaResponse.setCodiceEsito ( CodiciEsito.ESECUZIONE_OK.getCodice () );
                    posizioneDebitoriaResponse.setDescrizioneEsito ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( maxErrorMessageWidth ) );

                    posizioneDebitoriaResponse.setIdPosizioneDebitoria ( posizione.getIdPosizioneDebitoria () );
                    posizioneDebitoriaResponse.setIUV ( pagamento.getIuv () );
                    /* Pagamento model emette IllegalArgumentException che viene ora gestita - BEGIN */
                    String codiceAvviso;
            		try {
            			codiceAvviso = pagamento.getCodiceAvviso ();
            		} catch ( IllegalArgumentException e ) {
            			codiceAvviso = null;
            		}
                    posizioneDebitoriaResponse.setCodiceAvviso ( codiceAvviso );
                    /* Pagamento model emette IllegalArgumentException che viene ora gestita - END */
                    registroElaborazioni.getPagamenti ().add ( pagamento );
                } catch ( PosizioneDebitoriaException poe ) {
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, poe );
                    posizioneDebitoriaResponse.setCodiceEsito ( poe.getCode () );
                    posizioneDebitoriaResponse.setDescrizioneEsito ( poe.getMessage () );
                    posizioneDebitoriaResponse.setIdPosizioneDebitoria ( posizione.getIdPosizioneDebitoria () );
                    registroElaborazioni.getFaults ().add ( createRegistroElaborazioniFault ( pagamento, posizione, poe.getCode (), poe.getMessage () ) );
                } catch ( Exception e ) {
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, e );
                    CodiciEsito ce = CodiciEsito.AGGIORNAMENTO_PAGAMENTO_FALLITO;
                    posizioneDebitoriaResponse.setCodiceEsito ( ce.getCodice () );
                    posizioneDebitoriaResponse.setDescrizioneEsito ( ce.getMessaggio ( maxErrorMessageWidth ) );
                    posizioneDebitoriaResponse.setIdPosizioneDebitoria ( posizione.getIdPosizioneDebitoria () );
                    registroElaborazioni.getFaults ()
                    .add ( createRegistroElaborazioniFault ( pagamento, posizione, ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) ) );
                }
                listaPosizioneDebitoriaResponse.add ( posizioneDebitoriaResponse );
            }

            ResultType result = new ResultType ();
            if ( registroElaborazioni.getFaults ().isEmpty () ) {
                result.setCodice ( CodiciEsito.ESECUZIONE_OK.getCodice () );
                result.setMessaggio ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( maxErrorMessageWidth ) );
            } else if ( registroElaborazioni.getFaults ().size () == posizioniDaAggiornare
                            .size () ) {
                result.setCodice ( CodiciEsito.ESECUZIONE_TUTTA_KO.getCodice () );
                result.setMessaggio ( CodiciEsito.ESECUZIONE_TUTTA_KO.getMessaggio ( maxErrorMessageWidth ) );
            } else {
                result.setCodice ( CodiciEsito.ESECUZIONE_PARZIALMENTE_OK.getCodice () );
                result.setMessaggio ( CodiciEsito.ESECUZIONE_PARZIALMENTE_OK.getMessaggio ( maxErrorMessageWidth ) );
            }
            res.setResult ( result );

            EsitoAggiornamentoType esitoAggiornamento = new EsitoAggiornamentoType ();
            esitoAggiornamento.setElencoPosizioniDebitorieAggiornate ( elencoPosizioniDebitorieAggiornate );
            res.setEsitoAggiornamento ( esitoAggiornamento );

            aggiornaRegistroElaborazioniFine ( registroElaborazioni );
        } catch ( TassonomiaServiceException tse ) {
            log.error ( methodName, "TassonomiaServiceException", tse );
            ResultType result = new ResultType ();
            result.setCodice ( tse.getCode () );
            result.setMessaggio ( tse.getMessage () );
            res.setResult ( result );
            inserisciRegistroElaborazioniErrore ( testata, ente, tipoPagamento, tse.getMessage () );
        } catch ( TestataException te ) {
            //CSI_PAG-1888 Cambiato tipoPagamento in tipoPagamentoDaUsare
            log.error ( methodName, "Errore in testate", te );
            ResultType result = new ResultType ();
            result.setCodice ( te.getCode () );
            result.setMessaggio ( te.getMessage () );
            res.setResult ( result );
            String message = te.getCode () + " - " + te.getMessage ();
            inserisciRegistroElaborazioniErrore ( testata, ente, tipoPagamentoDaUsare, message );
        } catch ( MdpException me ) {
            //CSI_PAG-1888 Cambiato tipoPagamento in tipoPagamentoDaUsare
            log.error ( methodName, "Errore in MDP", me );
            ResultType result = new ResultType ();
            CodiciEsito ce = CodiciEsito.MDP_SERVICES_ERRORE;
            result.setCodice ( ce.getCodice () );
            result.setMessaggio ( StringUtils.abbreviate ( ce.getMessaggio ( maxErrorMessageWidth ), 500 ) );
            res.setResult ( result );
            String message = me.getMessage ();
            inserisciRegistroElaborazioniErrore ( testata, ente, tipoPagamentoDaUsare, message );
        } catch ( Throwable t ) {
            log.error ( methodName, "Errore inatteso", t );
            throw t;
        }

        log.debugEnd ( methodName );
        return res;
    }

    private DatiSpecificiRiscossioneOutput ricercaDatiSpecificiRiscossione ( AggiornaPosizioniDebitorieRequest parameters, final String methodName,
        TassonomiaAdapterClient client, DatiSpecificiRiscossioneInput request ) throws TassonomiaServiceException {
        DatiSpecificiRiscossioneOutput dsr = null;
        try {
            dsr = client.getDatiSpecificiRiscossione(request);
            //associare la risposta alla posizione
        }catch(Exception e ) { // DA FARE gestire correttamente eccezioni e stati
            log.error ( methodName, "Errore in fase di reperimento del dato specifico riscossione, per la lista di carico: " + parameters.getTestata ().getIdMessaggio () , e );
            CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
            throw new TassonomiaServiceException (ce.getCodice (), e.getMessage() + " , per la lista di carico: " + parameters.getTestata ().getIdMessaggio () );
        }
        return dsr;
    }

    private void verificaAnagrafica(SoggettoType soggettoType, Pagamento pagamento) throws PosizioneDebitoriaException {

        if (null != soggettoType && (null!= soggettoType.getPersonaFisica() || null!= soggettoType.getPersonaGiuridica() ))
        {
            if (!"ANONIMO".equals(pagamento.getPagatore().getCodiceFiscale())
                            ||  !"ANONIMO".equals(pagamento.getPagatore().getCognome())
                            || !"ANONIMO".equals(pagamento.getPagatore().getNome()))
            {

                throw new PosizioneDebitoriaException(CodiciEsito.SOGGETTO_PAGATORE_NON_ANONIMO.getCodice(),
                    CodiciEsito.SOGGETTO_PAGATORE_NON_ANONIMO.getMessaggio(maxErrorMessageWidth));
            }
            if (new BigDecimal(0).compareTo(pagamento.getImporto())!=0)
            {
                throw new PosizioneDebitoriaException(CodiciEsito.IMPORTO_MAGGIORE_DI_ZERO.getCodice(),
                    CodiciEsito.IMPORTO_MAGGIORE_DI_ZERO.getMessaggio(maxErrorMessageWidth));
            }
            if (null!= soggettoType.getPersonaFisica())
            {
                testField ( soggettoType.getPersonaFisica ().getNome (), CodiciEsito.NOME_OBBLIGATORIO );
                testField ( soggettoType.getPersonaFisica ().getCognome (), CodiciEsito.COGNOME_OBBLIGATORIO );

            }
            else if (null!= soggettoType.getPersonaGiuridica())
            {
                testField ( soggettoType.getPersonaGiuridica ().getRagioneSociale (), CodiciEsito.RAGIONESOCIALE_OBBLIGATORIA );
            }
            testField ( soggettoType.getIdentificativoUnivocoFiscale (), CodiciEsito.CODICE_FISCALE_OBBLIGATORIO );

        }

    }

    private Function<String, String> stringTrim = ( s ) -> s == null ? null : s.trim ();

    private Anagrafica toAnagrafica ( SoggettoType soggettoPagatore ) {
        final String methodName = "inserisciAnagrafica";

        log.debugStart ( methodName );
        try {
            Anagrafica anagrafica = new Anagrafica ();
            if ( soggettoPagatore.getPersonaFisica () != null && soggettoPagatore.getPersonaGiuridica () == null ) {
                anagrafica.setFlagPersonaFisica ( Boolean.TRUE );
                anagrafica.setNome ( stringTrim.apply ( soggettoPagatore.getPersonaFisica ().getNome () ) );
                anagrafica.setCognome ( stringTrim.apply ( soggettoPagatore.getPersonaFisica ().getCognome () ) );
            }
            if ( soggettoPagatore.getPersonaFisica () == null && soggettoPagatore.getPersonaGiuridica () != null ) {
                anagrafica.setFlagPersonaFisica ( Boolean.FALSE );
                anagrafica.setRagioneSociale ( stringTrim.apply ( soggettoPagatore.getPersonaGiuridica ().getRagioneSociale () ) );
            }
            anagrafica.setCodiceFiscale ( stringTrim.apply ( soggettoPagatore.getIdentificativoUnivocoFiscale () ) );
            if ( StringUtils.isNotBlank ( soggettoPagatore.getIndirizzo () ) ) {
                anagrafica.setIndirizzo ( stringTrim.apply ( soggettoPagatore.getIndirizzo () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getCivico () ) ) {
                anagrafica.setCivico ( stringTrim.apply ( soggettoPagatore.getCivico () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getCAP () ) ) {
                anagrafica.setCap ( stringTrim.apply ( soggettoPagatore.getCAP () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getLocalita () ) ) {
                anagrafica.setLocalita ( stringTrim.apply ( soggettoPagatore.getLocalita () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getProvincia () ) ) {
                anagrafica.setProvincia ( stringTrim.apply ( soggettoPagatore.getProvincia () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getNazione () ) ) {
                anagrafica.setNazione ( stringTrim.apply ( soggettoPagatore.getNazione () ) );
            }
            anagrafica.setEmail ( stringTrim.apply ( soggettoPagatore.getEMail () ) );

            return anagrafica;
        } finally {
            log.debugEnd ( methodName );
        }

    }





    private void verificaStato(Pagamento pagamento) throws PosizioneDebitoriaException {
        StatoPagamento statoPagamento = StatoPagamento.findById(pagamento.getIdStatoCorrente());
        CodiciEsito ce = null;
        switch (statoPagamento) {
        case IN_ATTESA:
        case TRANSAZIONE_INIZIALIZZATA:
        case TRANSAZIONE_AVVIATA:
            ce = CodiciEsito.PAGAMENTO_IN_ATTESA;
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        case SUCCESSO:
            ce = CodiciEsito.PAGAMENTO_GIA_EFFETTUATO;
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        case INVALIDATO:
            ce = CodiciEsito.PAGAMENTO_GIA_ANNULLATO;
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        default:
            EpayDStatoPagamento sp = entityManager.find(EpayDStatoPagamento.class, statoPagamento.getId());
            if (!sp.getModificabile()) {
                ce = CodiciEsito.PAGAMENTO_NON_MODIFICABILE;
                throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth, pagamento.getCodicePagamentoRifEnte()));
            }
            break;
        }
    }


    //CSI_PAG-1888 Metodo con parametri di input cambiati. Non c'e' piu' ente e c'e' il flag bPagamentoPrincipale
    private boolean verificaComponentiPagamento ( TipoPagamento tipoPagamento, PosizioneDaAggiornareType posizione, boolean bPagamentoPrincipale )
                    throws PosizioneDebitoriaException {
        final String methodName = "verificaComponentiPagamento";

        log.debugStart(methodName);
        try {
            if ( bPagamentoPrincipale ) {
                if ( posizione.getComponentiImporto () == null || posizione.getComponentiImporto ().getComponenteImporto () == null
                                || posizione.getComponentiImporto ().getComponenteImporto ().isEmpty () ) {
                    log.debug ( methodName, "OK" );
                    return false;
                }
            } else {
                if ( posizione.getComponentiImportoSecondario () == null || posizione.getComponentiImportoSecondario ().getComponenteImporto () == null
                                || posizione.getComponentiImportoSecondario ().getComponenteImporto ().isEmpty () ) {
                    log.debug ( methodName, "OK" );
                    return false;
                }
            }

            List<ComponenteImportoType> componenteImportoType = bPagamentoPrincipale ? posizione.getComponentiImporto ().getComponenteImporto ()
                            : posizione.getComponentiImportoSecondario ().getComponenteImporto ();
            for (ComponenteImportoType componente : componenteImportoType) {

                if ( ( !StringUtils.isBlank ( componente.getNumeroAccertamento () ) && (componente.getAnnoAccertamento() == null || componente.getAnnoAccertamento () == 0 )) ) {
                    CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_ANNO_ACCERTAMENTO;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
                }
                if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) && componente.getAnnoAccertamento() != null && componente.getAnnoAccertamento () > 0 ) {
                    CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_NUMERO_ACCETAMENTO;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
                }
            }

            log.debug(methodName, "OK");
        } finally {
            log.debugEnd(methodName);
        }
        return true;
    }

    private boolean verificaRiferimentiPagamento ( PosizioneDaAggiornareType posizione ) throws PosizioneDebitoriaException {
        final String methodName = "verificaRiferimentiPagamento";

        log.debugStart ( methodName );

        try {
            if ( posizione.getRiferimentiPagamento () == null || posizione.getRiferimentiPagamento ().getRiferimento () == null
                            || posizione.getRiferimentiPagamento ().getRiferimento ().isEmpty () ) {
                log.debug ( methodName, "OK" );
                return false;
            }

            List<RiferimentoType> riferimentoType = posizione.getRiferimentiPagamento ().getRiferimento ();
            for ( RiferimentoType riferimento: riferimentoType ) {
                if ( ( !StringUtils.isBlank ( riferimento.getNome () ) && StringUtils.isBlank ( riferimento.getValore () ) )
                                || ( StringUtils.isBlank ( riferimento.getNome () ) && !StringUtils.isBlank ( riferimento.getValore () ) ) ) {
                    CodiciEsito ce = CodiciEsito.RIFERIMENTO_PAGAMENTO_VUOTO;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
                }
            }

            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
        return true;
    }

    private List<Date>  verificaDataIniziFineValidita (Ente ente, TipoPagamento tipoPagamento, PosizioneDaAggiornareType posizione, Pagamento pagamento) throws PosizioneDebitoriaException {
        final String methodName = "verificaDataIniziFineValidita";
        log.debugStart(methodName);

        List<Date> dateValidita = new ArrayList<>();
        dateValidita.add(posizione.getDataInizioValidita() == null ? pagamento.getInizioValidita() : posizione.getDataInizioValidita().toGregorianCalendar().getTime());
        dateValidita.add(posizione.getDataFineValidita() == null ? pagamento.getFineValidita() : posizione.getDataFineValidita().toGregorianCalendar().getTime());

        if (dateValidita.get(0) == null) {
            log.debug(methodName, "OK");
            return dateValidita;
        }

        if (dateValidita.get(1) == null) {
            log.debug(methodName, "OK");
            return dateValidita;
        }

        if (dateValidita.get(1).before(dateValidita.get(0))) {
            log.debug(methodName, "KO");
            CodiciEsito ce = CodiciEsito.ERRORE_DATE_VALIDITA;
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth, ente.getCodiceFiscale(), tipoPagamento.getCodiceVersamento(), posizione.getIdPosizioneDebitoria()));
        }

        log.debug(methodName, "OK");
        log.debugEnd(methodName);
        return dateValidita;
    }

    private void  verificaValiditaPagamento (TipoPagamento tipoPagamento, List<Date> dateValidita) throws PosizioneDebitoriaException {
        final String methodName = "verificaValiditaPagamento";
        log.debugStart(methodName);
        if (dateValidita.get(1)!= null &&
                        dateValidita.get(1).before(new Date())) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_SCADUTO;
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        }

        if (tipoPagamento.getInizioValidita() != null &&
                        dateValidita.get(1)!= null &&
                        dateValidita.get(1).before(tipoPagamento.getInizioValidita())) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        }

        if (tipoPagamento.getFineValidita() != null &&
                        dateValidita.get(0)!= null &&
                        dateValidita.get(0).after(tipoPagamento.getInizioValidita())) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        }

        log.debugEnd(methodName);
    }

    private RegistroElaborazioniFault createRegistroElaborazioniFault(final Pagamento pagamento, final PosizioneDaAggiornareType posizione, final String code, final String message) {
        Long idPagamento = (pagamento == null) ? null : pagamento.getIdPagamento();
        String codicePagamentoRifEnte = "IUV:".concat(posizione.getIdPosizioneDebitoria());
        return new RegistroElaborazioniFault(idPagamento, codicePagamentoRifEnte, code, message);
    }

    private void verificaNumeroTrasmissione(String idMessaggio) throws TestataException{
        final String methodName = "verificaNumeroTrasmissione";

        log.debugStart(methodName);
        if (StringUtils.isBlank(idMessaggio)) {
            log.debug(methodName, "KO");
            log.debugEnd(methodName);
            CodiciEsito ce = CodiciEsito.NUMERO_TRASMISSIONE_ERRORE;
            throw new TestataException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth, idMessaggio));
        }

        try {
            List<RegistroElaborazioni> registriElaborazioni = registroElaborazioniManager.getRegistroElaborazioniByOperazioneEIdMessaggio(OPERARIONE, idMessaggio);
            if (!registriElaborazioni.isEmpty()) {
                log.debug(methodName, "KO");
                CodiciEsito ce = CodiciEsito.NUMERO_TRASMISSIONE_ERRORE;
                throw new TestataException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth, idMessaggio));
            }
            log.debug(methodName, "OK");
        } catch (NoDataException e) {
            log.debug(methodName, "OK");
        } finally {
            log.debugEnd(methodName);
        }
    }

    private Ente verificaTestataEnte(String cfEnte) throws TestataException {
        final String methodName = "verificaTestataEnte";

        log.debugStart(methodName);

        try {
            Ente ente = enteManager.getByCF(cfEnte);
            if (ente == null) {
                log.debug(methodName, "KO");
                CodiciEsito ce = CodiciEsito.CF_ENTE_ERRORE;
                throw new TestataException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth, cfEnte));
            }
            log.debug(methodName, "OK");
            return ente;
        } catch (NoDataException e) {
            log.debug(methodName, "KO");
            CodiciEsito ce = CodiciEsito.CF_ENTE_ERRORE;
            throw new TestataException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth, cfEnte));
        } finally {
            log.debugEnd(methodName);
        }
    }

    //CSI_PAG-1888 Tolto da questo metodo il controllo del codice versamento
    private TipoPagamento verificaTestataTipoPagamento ( TipoPagamento tipoPagamento ) throws TestataException {
        final String methodName = "verificaTestataTipoPagamento";

        log.debugStart(methodName);
        if (tipoPagamento.getFineValidita() != null) {
            if (tipoPagamento.getFineValidita().before(new Date())) {
                log.debug(methodName, "KO3");
                log.debugEnd(methodName);
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_ATTIVO_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }
        }

        //EPAY-347
        if ( tipoPagamento.getIdTipoPagamento () != null && tipoPagamento.getTipologiaPagamento() != null ) {

            if ( TipoPagamentoType.REDS.value().equalsIgnoreCase(tipoPagamento.getTipologiaPagamento().getCodice())) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_PERMESSO_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }

            if ( TipoPagamentoType.MABL.value().equalsIgnoreCase(tipoPagamento.getTipologiaPagamento().getCodice())) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_MARCA_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }

            if ( TipoPagamentoType.PS.value().equalsIgnoreCase(tipoPagamento.getTipologiaPagamento().getCodice())) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SPONTANEO_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }

        }
        log.debug(methodName, "OK");
        log.debugEnd(methodName);
        return tipoPagamento;
    }
    //CSI_PAG-1888 INIZIO
    private TipoPagamento verificaEsistenzaPagamentoPrincipale ( Ente ente, String codiceVersamento ) throws TestataException {
        final String methodName = "verificaEsistenzaPagamentoPrincipale";
        log.debugStart ( methodName );
        List<TipoPagamento> listaTipoPagamento = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, codiceVersamento );
        if ( listaTipoPagamento == null || listaTipoPagamento.isEmpty () ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.CODICE_VERSAMENTO_NON_TROVATO_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
        }
        if ( listaTipoPagamento.size () > 1 ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.CODICE_VERSAMENTO_NON_UNIVOCO_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return listaTipoPagamento.get ( 0 );
    }
    private TipoPagamento verificaEsistenzaPagamentoCollegato ( TipoPagamento tipoPagamentoPrincipale ) throws TestataException {
        final String methodName = "verificaEsistenzaPagamentoCollegato";

        log.debugStart ( methodName );
        List<EpayRTipoPagamentoCollegato> listaTipoPagamentoCollegato
        = tipoPagamentoManager.getPagamentoSecondarioByidPagamentoPrincipale ( tipoPagamentoPrincipale );
        if ( listaTipoPagamentoCollegato == null || listaTipoPagamentoCollegato.isEmpty () ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SECONDARIO_NON_TROVATO_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamentoPrincipale.getCodiceVersamento () ) );
        }
        if ( listaTipoPagamentoCollegato.size () > 1 ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SECONDARIO_NON_UNIVOCO_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamentoPrincipale.getCodiceVersamento () ) );
        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return tipoPagamentoManager.getById ( listaTipoPagamentoCollegato.get ( 0 ).getIdTipoPagamentoSecondario () );
    }

    private void verificaCompatibilitaFlagMultibeneficiario ( TipoPagamento tipoPagamento, Boolean flagMultiBeneficiario ) throws TestataException {
        final String methodName = "verificaCompatibilitaFlagMultibeneficiario";
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        //Il pagamento principale deve avere il flagMultibeneficiario
        //Uguale a quello passato nella richiesta
        boolean btipoPagFlagMulti = tipoPagamento.getFlagMultibeneficiario () == null ? false : tipoPagamento.getFlagMultibeneficiario ();
        boolean bFlagMultiDaRequest = flagMultiBeneficiario == null ? false : flagMultiBeneficiario;
        if ( bFlagMultiDaRequest && !btipoPagFlagMulti ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            //Da scatenare solo per i principali
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_NON_MULTIBENEFICIARIO;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
        }
        if ( btipoPagFlagMulti && !bFlagMultiDaRequest ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            //Da scatenare solo per i principali
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
        }
    }


    private Pagamento verifichePagamentoPrincipale ( PosizioneDaAggiornareType posizione, TipoPagamento tipoPagamento ) throws PosizioneDebitoriaException {
        final String methodName = "verifichePagamentoPrincipale";
        Pagamento pagamento = null;

        log.debugStart ( methodName );
        log.debugEnd ( methodName );
        try {
            pagamento = pagamentoManager.getPagamentoPerCodRifEnte ( tipoPagamento.getEpayTEnti ().getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
                posizione.getIdPosizioneDebitoria () );
            if ( pagamento == null ) {
                throw new NoDataException ();
            }
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getEpayTEnti ().getCodiceFiscale (),
                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getEpayTEnti ().getCodiceFiscale (),
                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
        }
        switch ( posizione.getTipoAggiornamento () ) {
        case ANNULLAMENTO :
            break;
        case MODIFICA :
            List<Date> dateValidita = verificaDataIniziFineValidita ( tipoPagamento.getEpayTEnti (), tipoPagamento, posizione, pagamento );
            verificaComponentiPagamento ( tipoPagamento, posizione, true );
            verificaRiferimentiPagamento ( posizione );
            verificaValiditaPagamento ( tipoPagamento, dateValidita );
            verificaAnagrafica ( posizione.getSoggettoPagatore (), pagamento );
            break;
        default :
            CodiciEsito ce = CodiciEsito.TIPOAGGIORNAMENTO_SCONOSCIUTO;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        return pagamento;
    }

    private PagamentoSecondario verifichePagamentoSecondario ( PosizioneDaAggiornareType posizione, TipoPagamento tipoPagamento, Long idPagamentoPrincipale )
                    throws PosizioneDebitoriaException {
        final String methodName = "verifichePagamentoPrincipale";
        PagamentoSecondario pagamento = null;

        log.debugStart ( methodName );
        log.debugEnd ( methodName );
        try {
            pagamento = pagamentoManager.getPagamentoSecondarioByIdPagamentoPrincipale ( idPagamentoPrincipale );
            if ( pagamento == null ) {
                throw new NoDataException ();
            }
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getEpayTEnti ().getCodiceFiscale (),
                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getEpayTEnti ().getCodiceFiscale (),
                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
        }
        verificaComponentiPagamento ( tipoPagamento, posizione, false );
        return pagamento;
    }

    private void verificaTotaliImportoPagamento ( TipoPagamento tipoPagamento, PosizioneDaAggiornareType posizione, Pagamento pagamento,
        PagamentoSecondario pagamentoSecondario, boolean bMultiBeneficiario ) throws PosizioneDebitoriaException {
        final String methodName = "verificaTotaliImportoPagamento";
        try {
            if ( bMultiBeneficiario && null == posizione.getImportoPrincipale () ) {
                throw new PosizioneDebitoriaException ( CodiciEsito.ERRORE_IMPORTO_PRINCIPALE_ASSENTE.getCodice (),
                    CodiciEsito.ERRORE_IMPORTO_PRINCIPALE_ASSENTE.getMessaggio ( maxErrorMessageWidth ) );
            }
            BigDecimal importoTotaleComponenti = BigDecimal.ZERO;
            if ( null != posizione.getComponentiImporto () && !CollectionUtils.isEmpty ( posizione.getComponentiImporto ().getComponenteImporto () ) ) {
                for ( ComponenteImportoType componente: posizione.getComponentiImporto ().getComponenteImporto () ) {
                    if ( null != componente.getImporto () ) {

                        importoTotaleComponenti = importoTotaleComponenti.add ( componente.getImporto () );

                        if ( StringUtils.isNotBlank ( componente.getNumeroAccertamento () )
                                        && ( null == componente.getAnnoAccertamento () || componente.getAnnoAccertamento () == 0 ) ) {
                            CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_ANNO_ACCERTAMENTO;
                            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
                        }

                        if ( StringUtils.isBlank ( componente.getNumeroAccertamento () )
                                        && ( null != componente.getAnnoAccertamento () && componente.getAnnoAccertamento () > 0 ) ) {
                            CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_NUMERO_ACCETAMENTO;
                            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
                        }
                    }
                }
            } else {
                importoTotaleComponenti = bMultiBeneficiario ? posizione.getImportoPrincipale () : posizione.getImportoTotale ();
            }
            BigDecimal importoTotaleComponentiSecondari = BigDecimal.ZERO;
            if ( bMultiBeneficiario ) {
                if ( null != posizione.getComponentiImportoSecondario ()
                                && !CollectionUtils.isEmpty ( posizione.getComponentiImportoSecondario ().getComponenteImporto () ) ) {
                    for ( ComponenteImportoType componente: posizione.getComponentiImportoSecondario ().getComponenteImporto () ) {
                        if ( null != componente.getImporto () ) {
                            importoTotaleComponenti = importoTotaleComponenti.add ( componente.getImporto () );
                        }
                    }
                } else {
                    importoTotaleComponentiSecondari
                    = null != posizione.getImportoSecondarioAltroEnte () ? posizione.getImportoSecondarioAltroEnte () : BigDecimal.ZERO;
                }
            }
            if ( posizione.getImportoTotale ().compareTo ( importoTotaleComponenti.add ( importoTotaleComponentiSecondari ) ) != 0 ) {
                log.debug ( methodName, "KO" );
                CodiciEsito ce = null;
                if ( pagamentoSecondario == null ) {
                    ce = CodiciEsito.ERRORE_TOTALE_IMPORTO_COMPONENTI;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio (
                        maxErrorMessageWidth,
                        tipoPagamento.getEpayTEnti ().getCodiceFiscale (),
                        tipoPagamento.getCodiceVersamento (),
                        posizione.getIdPosizioneDebitoria (),
                        //CSI_PAG-1888 Si deve mettere importoTotaleAtteso
                        //posizione.getImportoTotale().toString(),
                        posizione.getImportoTotale ().toString (),
                        importoTotaleComponenti.toString () ) );
                } else {
                    ce = CodiciEsito.ERRORE_SOMMA_IMPORTI_PRINCIPALE_E_SECONDARIO;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio (
                        maxErrorMessageWidth ) );
                }
            }
            pagamento.setImporto ( posizione.getImportoTotale () );
            pagamento.setImportoPrincipale ( posizione.getImportoPrincipale () );
            if ( pagamentoSecondario != null ) {
                pagamentoSecondario.setImportoComplessivo ( importoTotaleComponentiSecondari );
            }
        } finally {
            log.debugEnd ( methodName );
        }
    }
    //CSI_PAG-1888 FINE

    private void verificaTestataNumeroPosizioni(TestataAggiornaPosizioniDebitorie testata, List<PosizioneDaAggiornareType> posizioniDaAggiornare) throws TestataException {
        final String methodName = "verificaTestataNumeroPosizioni";

        log.debugStart(methodName);

        if (posizioniDaAggiornare.size() != testata.getNumeroPosizioniDebitorie()) {
            log.debug(methodName, "KO1");
            log.debugEnd(methodName);
            CodiciEsito ce = CodiciEsito.POSIZIONE_DEBITORIE_NUMERO_ERRORE;
            throw new TestataException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        }
        log.debug(methodName, "OK");
        log.debugEnd(methodName);
    }

    private void verificaElementiObbligatori(PosizioneDaAggiornareType posizione) throws PosizioneDebitoriaException {
        testField(posizione.getIdPosizioneDebitoria(), CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_OBBLIGATORIO);
        //testField(posizione.getMotivazione(), CodiciEsito.MOTIVAZIONE_OBBLIGATORIO);
        testField(posizione.getTipoAggiornamento(), CodiciEsito.TIPOAGGIORNAMENTO_OBBLIGATORIO);
    }

    private void testField(final String str, final CodiciEsito ce) throws PosizioneDebitoriaException {
        if (StringUtils.isBlank(str)) {
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        }
    }

    private void testField(final Object obj, final CodiciEsito ce) throws PosizioneDebitoriaException {
        if (obj == null) {
            throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        }
    }

    private void inserisciRegistroElaborazioniErrore(final TestataAggiornaPosizioniDebitorie testata, final Ente ente, final TipoPagamento tipoPagamento, final String message) {
        final String methodName = "inserisciRegistroElaborazioniErrore";

        log.debugStart(methodName);
        try {
            Long idEnte = (ente == null) ? -1 : ente.getIdEnte();
            Long idTipoPagamento = (tipoPagamento == null) ? -1 : tipoPagamento.getIdTipoPagamento();
            inserisciRegistroElaborazioniErrore(testata.getIdMessaggio(), idEnte, idTipoPagamento, message);
        } finally {
            log.debugEnd(methodName);
        }
    }

    private void inserisciRegistroElaborazioniErrore(final String idMessaggio, final Long idEnte, final Long idTipoPagamento, final String message) {
        final String methodName = "inserisciRegistroElaborazioniErrore";

        log.debugStart(methodName);
        try {
            Date data = new Date();
            RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni();
            registroElaborazioni.setDataInizio(new Timestamp(data.getTime()));
            registroElaborazioni.setDataFine(new Timestamp(data.getTime()));
            registroElaborazioni.setOperazione(OPERARIONE);
            registroElaborazioni.setEsito("errore");

            registroElaborazioni.setIdMessaggio(idMessaggio);
            registroElaborazioni.setIdEnte(idEnte);
            registroElaborazioni.setIdTipoPagamento(idTipoPagamento);

            registroElaborazioni.setMessageFault(message);

            registroElaborazioniManager.inserisci(registroElaborazioni);

        } finally {
            log.debugEnd(methodName);
        }
    }

    private RegistroElaborazioni inserisciRegistroElaborazioniInizio(final TestataAggiornaPosizioniDebitorie testata, final Ente ente, final TipoPagamento tipoPagamento) {
        final String methodName = "inserisciRegistroElaborazioniInizio";

        log.debugStart(methodName);
        try {
            Date data = new Date();
            RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni();
            registroElaborazioni.setDataInizio(new Timestamp(data.getTime()));
            registroElaborazioni.setOperazione(OPERARIONE);
            registroElaborazioni.setEsito("elaborazione");
            registroElaborazioni.setIdEnte(ente.getIdEnte());
            registroElaborazioni.setIdTipoPagamento(tipoPagamento.getIdTipoPagamento());
            registroElaborazioni.setPagamentiSpontanei(Boolean.FALSE);
            registroElaborazioni.setIdMessaggio(testata.getIdMessaggio());
            registroElaborazioni.setNumPagamenti(testata.getNumeroPosizioniDebitorie());
            registroElaborazioni.setPagamenti(new ArrayList<Pagamento>());

            Long id = registroElaborazioniManager.inserisci(registroElaborazioni);
            registroElaborazioni.setId(id);
            return registroElaborazioni;
        } finally {
            log.debugEnd(methodName);
        }
    }

    private void aggiornaRegistroElaborazioniFine(RegistroElaborazioni registroElaborazioni) {
        final String methodName = "aggiornaRegistroElaborazioniFine";

        log.debugStart(methodName);
        try {
            Date data = new Date();
            registroElaborazioni.setDataFine(new Timestamp(data.getTime()));
            registroElaborazioni.setEsito(registroElaborazioni.getFaults().isEmpty() ? "successo" : "successo parziale");
            registroElaborazioniManager.aggiorna(registroElaborazioni);
        } finally {
            log.debugEnd(methodName);
        }
    }

    private PagamentoComponenti creaComponenteDiDefault ( DatiSpecificiRiscossioneOutput dsr, PosizioneDaAggiornareType posiszione, boolean bMultibeneficiario )
                    throws PosizioneDebitoriaException {
        PagamentoComponenti componente = new PagamentoComponenti ();
        componente.setProgressivo ( 1 );

        componente.setImporto ( bMultibeneficiario ? posiszione.getImportoPrincipale () : posiszione.getImportoTotale () );
        componente.setUtenteUltimaModifica ( "InserisciListaDiCarico" );
        List<DatiSpecificiRiscossione> listDati = new LinkedList<> ();
        for ( DatiSpecificiRiscossione dato: dsr.getElencoDatiSpecifici () ) {
            if ( null != dato.getAnnoAccertamento () && null != dato.getNumeroAccertamento () ) {
                listDati.add ( dato );
            }

        }
        if ( CollectionUtils.isEmpty ( listDati ) || listDati.size () > 1 ) {
            CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
            throw new PosizioneDebitoriaException ( ce.getCodice (),
                ce.getMessaggio ( maxErrorMessageWidth ) + " , per la posizione debitoria: " + posiszione.getIdPosizioneDebitoria ()
                + ", non essendo specificate componenti viene richiesto un'unico riferimento contabile, con anno e numero accertamento specificato, valido per l'anno in corso su Catalogo!" );
        } else {
            componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
            componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
            componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
            componente.setCodiceTributo ( listDati.get ( 0 ).getCodiceTributo () );
            
            if(StringUtils.isNotBlank ( posiszione.getDescrizioneCausaleVersamento () )) {
                componente.setCausale ( StringUtils.substring (posiszione.getDescrizioneCausaleVersamento (), 0, 140) );
            } else {
                componente.setCausale ( StringUtils.substring (listDati.get ( 0 ).getDescrizione (), 0, 140) );
            }
        }
        return componente;
    }

    private void inserisciDatoSpecificoRiscossioneInComponente ( PagamentoComponenti componente, DatiSpecificiRiscossioneOutput dsr )
                    throws PosizioneDebitoriaException {
        if ( componente.getImporto () != null ) {
            //:TODO ottimizzare le performance
            Optional<DatiSpecificiRiscossione> trovato = dsr.getElencoDatiSpecifici ().stream ()
                            .filter ( new Predicate<DatiSpecificiRiscossione> () {

                                @Override
                                public boolean test ( DatiSpecificiRiscossione s ) {
                                    // Cerco il dato specifico riscossione con annno accertamento e numero accertamento per evitare che l'equals vada in errore
                                    if ( null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () ) ) {
                                        return ( null == componente.getAnnoAccertamento () && StringUtils.isEmpty ( componente.getNumeroAccertamento () ) );
                                    }
                                    return s.getAnnoAccertamento ().equals ( componente.getAnnoAccertamento () ) &&
                                                    s.getNumeroAccertamento ().equals ( componente.getNumeroAccertamento () );
                                }
                            } )
                            .findFirst ();
            if ( trovato.isPresent () ) {
                componente.setDatiSpecificiRiscossione ( trovato.get ().getCodice () );
                componente.setCodiceTributo (  trovato.get ().getCodiceTributo () );
            } else {
                Optional<DatiSpecificiRiscossione> trovatoDef = dsr.getElencoDatiSpecifici ().stream ()
                                .filter ( new Predicate<DatiSpecificiRiscossione> () {

                                    @Override
                                    public boolean test ( DatiSpecificiRiscossione s ) {
                                        return null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () );
                                    }
                                } )
                                .findFirst ();
                if ( trovatoDef.isPresent () ) {
                    componente.setDatiSpecificiRiscossione ( trovatoDef.get ().getCodice () );
                    componente.setCodiceTributo (  trovato.get ().getCodiceTributo () );
                } else {
                    CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
                    throw new PosizioneDebitoriaException ( CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getCodice (),
                        ce.getMessaggio ( maxErrorMessageWidth ) + " , per la componente con anno e numero accertamento : " + componente.getAnnoAccertamento ()
                        + " - " + componente.getNumeroAccertamento ()  + " non sono presenti valori su Catalogo!");
                }
            }
        }
    }

    private void verificaImportoSecondarioAltroEnte ( PosizioneDaAggiornareType posizione ) throws PosizioneDebitoriaException {
        if ( posizione.getImportoSecondarioAltroEnte () == null ) {
            CodiciEsito ce = CodiciEsito.ERRORE_IMPORTO_SECONDARIO_ALTRO_ENTE_ASSENTE;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        if ( posizione.getComponentiImportoSecondario () == null
                        || CollectionUtils.isEmpty ( posizione.getComponentiImportoSecondario ().getComponenteImporto () ) ) {
            CodiciEsito ce = CodiciEsito.ERRORE_COMPONENTE_IMPORTO_SECONDARIO_ASSENTE;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        if ( posizione.getComponentiImportoSecondario ().getComponenteImporto ().size () != 1 ) {
            CodiciEsito ce = CodiciEsito.ERRORE_UN_SOLO_IMPORTO_SECONDARIO;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
    }
}
