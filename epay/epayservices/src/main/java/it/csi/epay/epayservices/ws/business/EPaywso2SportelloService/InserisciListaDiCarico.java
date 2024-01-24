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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayservices.integration.db.entities.EpayRTipoPagamentoCollegato;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroElaborazioniManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TipologiaPagamentoManager;
import it.csi.epay.epayservices.integration.mdp.MdpException;
import it.csi.epay.epayservices.integration.mdp.MultiIuv;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.RegistroElaborazioni;
import it.csi.epay.epayservices.model.RegistroElaborazioniFault;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipologiaPagamento;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.ComponenteImportoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.EsitoInserimentoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.EsitoInserimentoType.ElencoPosizioniDebitorieInserite;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.InserisciListaDiCaricoRequest;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.InserisciListaDiCaricoResponse;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.PosizioneDaInserireType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.PosizioneDebitoriaType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.ResultType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.RiferimentoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.SoggettoType;
import it.csi.epay.epayservices.ws.EPaywso2SportelloService.TestataListaCarico;
import it.csi.epay.epayservices.ws.exceptions.PosizioneDebitoriaException;
import it.csi.epay.epayservices.ws.exceptions.TestataException;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.TipoPagamentoType;
import it.csi.mdpmultiiuv.IuvComplex;


@Singleton ( name = "InserisciListaDiCarico", mappedName = "InserisciListaDiCarico" )
public class InserisciListaDiCarico {

    protected LogUtil log = new LogUtil ( this.getClass () );

    private static final String OPERARIONE = "InserisciListaDiCaricoRequest";
    private static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA = "ENDPOINT_SERVICE_TASSONOMIA";
    private static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA_LOCAL = "ENDPOINT_SERVICE_TASSONOMIA_LOCAL"; // endpoint da usare per le prove in locale
	private static final String COD_ENTE_DEFALUT = "PagoPa"; // codice 0

	private static final int maxErrorMessageWidth = 200;

    @EJB
    MultiIuv multiIuv;

    @EJB
    PagamentoManager pagamentoManager;

    @EJB
    RegistroVersamentiManager registroVersamentiManager;

    @EJB
    AnagraficaManager anagraficaMenager;

    @EJB
    RegistroElaborazioniManager registroElaborazioniManager;

    @EJB
    EnteManager enteManager;

    @EJB
    TipoPagamentoManager tipoPagamentoManager;

    @EJB
    TipologiaPagamentoManager tipologiaPagamentoManager;
      
	@EJB
	ConfigurazioneManager configurazioneManager;

    private Function<String, String> stringTrim = ( s ) -> s == null ? null : s.trim ();

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public InserisciListaDiCaricoResponse exec ( InserisciListaDiCaricoRequest parameters )  {
        final String methodName = "exec";
        log.debugStart ( methodName );

        InserisciListaDiCaricoResponse res = new InserisciListaDiCaricoResponse ();
        TestataListaCarico testata = parameters.getTestata ();
        Ente ente = null;
        TipoPagamento tipoPagamento = null;
        TipoPagamento tipoPagamentoSecondario = null;
        TipoPagamento tipoPagamentoDaUsare = null;
        try {
            boolean bMultibeneficiario = testata.getMultiBeneficiario () != null ? testata.getMultiBeneficiario ().booleanValue () : false;
        	
            verificaNumeroTrasmissione ( testata.getIdMessaggio () );

            List<PosizioneDaInserireType> posizioniDebitorie = parameters.getListaDiCarico ().getPosizioniDaInserire ().getPosizioneDaInserire ();

            ente = verificaTestataEnte ( testata.getCFEnteCreditore () );
            //CSI_PAG-1887 INIZIO
            tipoPagamento = verificaEsistenzaPagamentoPrincipale ( ente, testata.getCodiceVersamento () );
            tipoPagamentoDaUsare = tipoPagamento;
            verificaCompatibilitaFlagMultibeneficiario ( tipoPagamento, testata.getMultiBeneficiario () );
            verificaTestataTipoPagamento ( tipoPagamento );
            tipoPagamentoDaUsare = tipoPagamento;
            //CSI_PAG-1887 FINE
            verificaTestataNumeroPosizioniEImportoTotale ( testata, posizioniDebitorie );
        	
        	// RDI-41
    		Configurazione serviceTassonomia= configurazioneManager.getConfigurazione(CONFIG_ENDPOINT_SERVICE_TASSONOMIA, COD_ENTE_DEFALUT);
//    		Configurazione serviceTassonomia= configurazioneManager.getConfigurazione(CONFIG_ENDPOINT_SERVICE_TASSONOMIA_LOCAL, COD_ENTE_DEFALUT);
    		

            ElencoPosizioniDebitorieInserite elencoPosizioniDebitorieInserite = new ElencoPosizioniDebitorieInserite ();

            // spostarlo all'inizio prima della verifica dell'ente.
    		if (serviceTassonomia==null) {
    			log.error ( methodName, "Errore in fase di reperimento del dato specifico riscossione, per la lista di carico: " + parameters.getTestata ().getIdMessaggio ()+    					
    									"configurazione  CONFIG_ENDPOINT_SERVICE_TASSONOMIA mancante");
                CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
                throw new TassonomiaServiceException (ce.getCodice (), ce.getMessaggio(maxErrorMessageWidth) + " , per la lista di carico: " + parameters.getTestata ().getIdMessaggio () +    					
										"configurazione  CONFIG_ENDPOINT_SERVICE_TASSONOMIA mancante");            
    		}
    		
    		// inizializzazione servizio REST
            String url=serviceTassonomia.getValore();

            TassonomiaAdapterClient client = new TassonomiaAdapterClient( configurazioneManager );
            DatiSpecificiRiscossioneOutput dsrSec = null;
            if ( bMultibeneficiario ) {
                tipoPagamentoSecondario = verificaEsistenzaPagamentoCollegato ( tipoPagamento );
                tipoPagamentoDaUsare = tipoPagamentoSecondario;
                verificaTestataTipoPagamento ( tipoPagamentoSecondario );
                // reperimento DSR cov secondario in caso di multibeneficiario
				DatiSpecificiRiscossioneInput requestSec = client.buildDatiSpecificiRiscossioneInput (
					tipoPagamentoSecondario.getEpayTEnti ().getCodiceFiscale (), tipoPagamentoSecondario.getCodiceVersamento () );
                dsrSec = ricercaDatiSpecificiRiscossione ( parameters, methodName, client, requestSec );
            }
            //invocazione servizio REST per il DSR, il servizio ricerca tutti i dati specifici riscossione per ente, codice versamento e anno corrente
            DatiSpecificiRiscossioneInput request = new DatiSpecificiRiscossioneInput();
            request.setCodiceFiscaleEnte(testata.getCFEnteCreditore ());
            request.setTipoPagamento(testata.getCodiceVersamento ());
            request.setAnnoEsercizio ( Calendar.getInstance ().get ( Calendar.YEAR ) );
            
            DatiSpecificiRiscossioneOutput dsr = ricercaDatiSpecificiRiscossione ( parameters, methodName, client, request );
            
 
            
            RegistroElaborazioni registroElaborazioni = inserisciRegistroElaborazioniInizio ( testata, ente, tipoPagamento );
            List<Pagamento> pagamenti = registroElaborazioni.getPagamenti ();
            List<RegistroElaborazioniFault> faults = registroElaborazioni.getFaults ();

            List<PosizioneDebitoriaType> listaPosizioneDebitoriaResponse = elencoPosizioniDebitorieInserite.getPosizioneDebitoriaInserita ();	
            List<IuvComplex> iuvs = multiIuv.generateNewIuv ( tipoPagamento, testata.getNumeroPosizioniDebitorie (), 3 );

            int index = 0;
            
            for ( PosizioneDaInserireType posizione: posizioniDebitorie ) { //OLD BUT LOLLO
                PosizioneDebitoriaType posizioneDebitoriaResponse = new PosizioneDebitoriaType ();
                try {
                    BigDecimal importoTotale = null;
                    BigDecimal importoTotalePrincipale = BigDecimal.ZERO;                    
                    BigDecimal importoTotaleSecondario = BigDecimal.ZERO;                    
                    verificaElementiObbligatori ( posizione );
                    verificaCodicePagamentoRifEnte ( ente, tipoPagamento, posizione );
                    importoTotalePrincipale = importoTotalePrincipale.add ( verificaComponentiPagamento ( tipoPagamento, posizione, true, bMultibeneficiario ) );
                    verificaValiditaPagamento ( tipoPagamento, posizione );
                    verificaRiferimentiPagamento ( posizione );
                    //CSI_PAG-1887 INIZIO
                    if ( bMultibeneficiario ) {
                        verificaImportoSecondarioAltroEnte ( posizione );
                        importoTotaleSecondario = importoTotaleSecondario.add ( verificaComponentiPagamento ( tipoPagamentoSecondario, posizione, false, bMultibeneficiario ) );
                    }
                    importoTotale = importoTotalePrincipale.add ( importoTotaleSecondario );
                    if ( importoTotale.compareTo ( posizione.getImportoTotale () ) != 0 ) {
                        log.debug ( methodName, "KO" );
                        CodiciEsito ce = null;
                        if ( !bMultibeneficiario ) {
                            ce = CodiciEsito.ERRORE_TOTALE_IMPORTO_COMPONENTI;
                            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio (
                                maxErrorMessageWidth,
                                ente.getCodiceFiscale (),
                                tipoPagamento.getCodiceVersamento (),
                                posizione.getIdPosizioneDebitoria (),
                                posizione.getImportoTotale ().toString (),
                                importoTotale.toString () ) );
                        } else {
                            ce = CodiciEsito.ERRORE_SOMMA_IMPORTI_PRINCIPALE_E_SECONDARIO;
                            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio (
                                maxErrorMessageWidth ) );
                        }
                    }
                    //CSI_PAG-1887 FINE


                    CodiceAvviso codiceAvviso
                    = new CodiceAvviso ( iuvs.get ( index ).getAuxDigit (), iuvs.get ( index ).getApplicationCode (), iuvs.get ( index ).getIuvOttico () );
                    Pagamento pagamento = caricaPosizioneDebitoria ( ente, tipoPagamento, posizione, codiceAvviso, tipoPagamentoSecondario, dsr, dsrSec, bMultibeneficiario);

                    posizioneDebitoriaResponse.setCodiceEsito ( CodiciEsito.ESECUZIONE_OK.getCodice () );
                    posizioneDebitoriaResponse.setDescrizioneEsito ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( maxErrorMessageWidth ) );
                    posizioneDebitoriaResponse.setIUV ( codiceAvviso.getIuv () );
                    posizioneDebitoriaResponse.setIdPosizioneDebitoria ( pagamento.getCodicePagamentoRifEnte () );
                    posizioneDebitoriaResponse.setCodiceAvviso ( codiceAvviso.toString () );
                    pagamenti.add ( pagamento );
                } catch ( PosizioneDebitoriaException poe ) {
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, poe );
                    posizioneDebitoriaResponse.setIdPosizioneDebitoria ( posizione.getIdPosizioneDebitoria () );
                    posizioneDebitoriaResponse.setCodiceEsito ( poe.getCode () );
                    posizioneDebitoriaResponse.setDescrizioneEsito ( poe.getMessage () );
                    faults.add ( new RegistroElaborazioniFault ( null, posizione.getIdPosizioneDebitoria (), poe.getCode (), poe.getMessage () ) );
                } catch ( IllegalArgumentException e ) {
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, e );
                    CodiciEsito ce = CodiciEsito.INSERIMENTO_PAGAMENTO_FALLITO;
                    posizioneDebitoriaResponse.setIdPosizioneDebitoria ( posizione.getIdPosizioneDebitoria () );
                    posizioneDebitoriaResponse.setCodiceEsito ( ce.getCodice () );
                    posizioneDebitoriaResponse.setDescrizioneEsito ( ce.getMessaggio ( maxErrorMessageWidth ) );
                    faults.add ( new RegistroElaborazioniFault ( null, posizione.getIdPosizioneDebitoria (), ce.getCodice (),
                        ce.getMessaggio ( maxErrorMessageWidth ) ) );
                }
                listaPosizioneDebitoriaResponse.add ( posizioneDebitoriaResponse );
                index++;
            }
			
            ResultType result = new ResultType ();
            if ( faults.isEmpty () ) {
                result.setCodice ( CodiciEsito.ESECUZIONE_OK.getCodice () );
                result.setMessaggio ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( maxErrorMessageWidth ) );
            } else if ( faults.size () == posizioniDebitorie.size () ) {
                result.setCodice ( CodiciEsito.ESECUZIONE_TUTTA_KO.getCodice () );
                result.setMessaggio ( CodiciEsito.ESECUZIONE_TUTTA_KO.getMessaggio ( maxErrorMessageWidth ) );
            } else {
                result.setCodice ( CodiciEsito.ESECUZIONE_PARZIALMENTE_OK.getCodice () );
                result.setMessaggio ( CodiciEsito.ESECUZIONE_PARZIALMENTE_OK.getMessaggio ( maxErrorMessageWidth ) );
            }
            res.setResult ( result );

            EsitoInserimentoType esitoInserimento = new EsitoInserimentoType ();
            esitoInserimento.setElencoPosizioniDebitorieInserite ( elencoPosizioniDebitorieInserite );
            res.setEsitoInserimento ( esitoInserimento );

            aggiornaRegistroElaborazioniFine ( registroElaborazioni );
        } catch ( TassonomiaServiceException tse ) {
            log.error ( methodName, "TassonomiaServiceException", tse );
            ResultType result = new ResultType ();           
            result.setCodice ( tse.getCode() );
            result.setMessaggio ( tse.getMessage() );
            res.setResult ( result );
            inserisciRegistroElaborazioniErrore ( testata, ente, tipoPagamento, tse.getMessage() );
        } catch ( TestataException te ) {
            //CSI_PAG-1887 Cambiato tipoPagamento in tipoPagamentoDaUsare
            log.error ( methodName, "errore in testata del messsagio", te );
            ResultType result = new ResultType ();
            result.setCodice ( te.getCode () );
            result.setMessaggio ( te.getMessage () );
            res.setResult ( result );
            String message = te.getCode () + " - " + te.getMessage ();
            inserisciRegistroElaborazioniErrore ( testata, ente, tipoPagamentoDaUsare, message );
        } catch ( MdpException me ) {
            log.error ( methodName, "errore Mdp", me );
            ResultType result = new ResultType ();
            CodiciEsito ce = CodiciEsito.MDP_SERVICES_ERRORE;
            String message = ce.getMessaggio ( maxErrorMessageWidth, me.getMessage () );
            result.setCodice ( ce.getCodice () );
            result.setMessaggio ( message );
            res.setResult ( result );
            inserisciRegistroElaborazioniErrore ( testata, ente, tipoPagamentoDaUsare, message );
        } catch ( Throwable t ) {
            log.error ( methodName, "errore inatteso", t );
            throw t;
         }
        log.debugEnd ( methodName );
        return res;
    }

    private void inserisciDatoSpecificoRiscossioneInComponente ( PagamentoComponenti componente, DatiSpecificiRiscossioneOutput dsr )
                    throws TassonomiaServiceException {
        if ( componente.getImporto () != null ) {
          //:TODO ottimizzare le performance
            Optional<DatiSpecificiRiscossione> trovato = dsr.getElencoDatiSpecifici ().stream ()
                .filter ( new Predicate<DatiSpecificiRiscossione> () {

                    @Override
                    public boolean test ( DatiSpecificiRiscossione s ) {
                        // Cerco il dato specifico riscossione con anno accertamento e numero accertamento per evitare che l'equals vada in errore
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
                // Cerco il DSR di default
                Optional<DatiSpecificiRiscossione> trovatoDefault = dsr.getElencoDatiSpecifici ().stream ()
                    .filter ( new Predicate<DatiSpecificiRiscossione> () {

                        @Override
                        public boolean test ( DatiSpecificiRiscossione s ) {
                            return null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () );
                        }
                    } )
                    .findFirst ();
                if ( trovatoDefault.isPresent () ) {
                    componente.setDatiSpecificiRiscossione ( trovatoDefault.get ().getCodice () );
                    componente.setCodiceTributo (  trovatoDefault.get ().getCodiceTributo () );
                } else {
                    CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
                    throw new TassonomiaServiceException ( CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getCodice (),
                        ce.getMessaggio ( maxErrorMessageWidth ) + " , per la componente con anno e numero accertamento : " + componente.getAnnoAccertamento ()
                            + " - " + componente.getNumeroAccertamento () + " non sono presenti valori su Catalogo!");
                }
            }
        }
    }

    private DatiSpecificiRiscossioneOutput ricercaDatiSpecificiRiscossione ( InserisciListaDiCaricoRequest parameters, final String methodName,
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

   

    //csi_pag-1887 aggiunto parametro tipoPagamentoSecondario
    private Pagamento caricaPosizioneDebitoria ( Ente ente, TipoPagamento tipoPagamento, PosizioneDaInserireType posizione, CodiceAvviso codiceAvviso,
        TipoPagamento tipoPagamentoSecondario, DatiSpecificiRiscossioneOutput dsr, DatiSpecificiRiscossioneOutput dsrSecondario, boolean bMultibeneficiario)
                        throws PosizioneDebitoriaException {
        final String methodName = "caricaPosizioneDebitoria";

        log.debugStart ( methodName );
        try {
            Anagrafica pagatore = inserisciAnagrafica ( posizione.getSoggettoPagatore () );

            Pagamento pagamento = new Pagamento ();
            pagamento.setAuxDigit ( stringTrim.apply ( codiceAvviso.getAuxDigit () ) );
            pagamento.setApplicationCode ( stringTrim.apply ( codiceAvviso.getApplicationCode () ) );
            pagamento.setIuv ( stringTrim.apply ( codiceAvviso.getIuv () ) );
            pagamento.setIuvRegistroVersamenti ( stringTrim.apply ( codiceAvviso.getIuv () ) );
            pagamento.setEnte ( ente );
            pagamento.setTipoPagamento ( tipoPagamento );
            pagamento.setIdStatoCorrente ( StatoPagamento.DA_PAGARE.getId () );

            pagamento.setAnnoRiferimento ( posizione.getAnnoRiferimento () );
            pagamento.setDataScadenza ( posizione.getDataScadenza () == null ? null : posizione.getDataScadenza ().toGregorianCalendar ().getTime () );
            pagamento
            .setInizioValidita ( posizione.getDataInizioValidita () == null ? null : posizione.getDataInizioValidita ().toGregorianCalendar ().getTime () );
            pagamento.setFineValidita ( posizione.getDataFineValidita () == null ? null : posizione.getDataFineValidita ().toGregorianCalendar ().getTime () );
            pagamento.setCausale ( stringTrim.apply ( posizione.getDescrizioneCausaleVersamento () ) );
            pagamento.setNumeroRata ( stringTrim.apply ( posizione.getDescrizioneRata () ) );
            pagamento.setCodicePagamentoRifEnte ( stringTrim.apply ( posizione.getIdPosizioneDebitoria () ) );
            pagamento.setImporto ( posizione.getImportoTotale () );
            pagamento.setImportoPrincipale ( posizione.getImportoPrincipale () );
            pagamento.setNote ( stringTrim.apply ( posizione.getNotePerIlPagatore () ) );
            pagamento.setPagatore ( pagatore );
            pagamento.setUtenteUltimaModifica ( "InserisciListaDiCarico" );
            pagamento.setIdentificativoDominio ( ente.getCodiceFiscale () );
            pagamento.setRequiresCostUpdate ( posizione.getRequiresCostUpdate () );

            if ( posizione.getComponentiImporto () != null &&
                            posizione.getComponentiImporto ().getComponenteImporto () != null &&
                            !posizione.getComponentiImporto ().getComponenteImporto ().isEmpty () ) {
                int i = 0;
                for ( ComponenteImportoType ci: posizione.getComponentiImporto ().getComponenteImporto () ) {
                    if ( i++ > 5 ) {
                        CodiciEsito ce = CodiciEsito.TROPPI_DETTAGLI_PAGAMENTO;
                        throw new PosizioneDebitoriaException ( ce.getCodice (),
                            ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
                                pagamento.getCodicePagamentoRifEnte () ) );
                    }
                    if ( null != ci.getImporto () ) {
                        PagamentoComponenti componente = new PagamentoComponenti ();
                        componente.setProgressivo ( i );
                        componente.setCausale ( StringUtils.substring (ci.getCausaleDescrittiva (), 0, 140) );
                        componente.setImporto ( ci.getImporto () );
                        componente
                            .setAnnoAccertamento ( ( null != ci.getAnnoAccertamento () && ci.getAnnoAccertamento () > 0 ) ? ci.getAnnoAccertamento () : null );
                        componente.setNumeroAccertamento ( StringUtils.isBlank ( ci.getNumeroAccertamento () ) ? null : ci.getNumeroAccertamento () );
                        componente.setUtenteUltimaModifica ( "InserisciListaDiCarico" );
                        inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );
                        pagamento.getComponenti ().add ( componente );
                    }
                }
            } else {
                //gestione DSR senza componenti inserisco componente di default.
                pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, posizione, bMultibeneficiario ) );
            }

            if ( posizione.getRiferimentiPagamento () != null &&
                            posizione.getRiferimentiPagamento ().getRiferimento () != null &&
                            !posizione.getRiferimentiPagamento ().getRiferimento ().isEmpty () ) {
                int i = 0;
                for ( RiferimentoType ci: posizione.getRiferimentiPagamento ().getRiferimento () ) {
                    if ( i++ > 5 ) {
                        CodiciEsito ce = CodiciEsito.TROPPI_DETTAGLI_PAGAMENTO;
                        throw new PosizioneDebitoriaException ( ce.getCodice (),
                            ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
                                pagamento.getCodicePagamentoRifEnte () ) );
                    }
                    PagamentoRiferimenti riferimento = new PagamentoRiferimenti ();
                    riferimento.setProgressivo ( i );
                    riferimento.setNome ( ci.getNome () );
                    riferimento.setValore ( ci.getValore () );
                    riferimento.setUtenteUltimaModifica ( "InserisciListaDiCarico" );
                    pagamento.getRiferimenti ().add ( riferimento );
                }
            }
            
            //:TODO in un futuro di stabilita' unire le seguenti linee di codice di inserte ed update in un unica insert.
            EpayTPagamento pagamentoEntity = pagamentoManager.inserisciAndRetEntity ( pagamento );
            pagamentoManager.aggiornaCodiceAvviso ( pagamentoEntity.getIdPagamento (), codiceAvviso );

            //CSI_PAG-1887 INIZIO
            Long idPagamentoSecondario = null;
            if ( tipoPagamentoSecondario != null ) {
                //Caso multibeneficiario, si inserisce anche il pagamento secondario.
                PagamentoSecondario pagamentoSecondario = new PagamentoSecondario ();
                pagamentoSecondario.setIdPagamento ( pagamentoEntity.getIdPagamento () );
                pagamentoSecondario.setCausale ( StringUtils.substring (tipoPagamentoSecondario.getDescrizionePortale (), 0, 140) );
                pagamentoSecondario.setTipoPagamento ( tipoPagamentoSecondario );
                BigDecimal importoComplessivoPagamentoSecondario = BigDecimal.ZERO;
                //Ci deve essere uno e uno solo componente importo secondario (vedi metodo verificaImportoSecondarioAltroEnte)
                int i = 1;
                for ( ComponenteImportoType ci: posizione.getComponentiImportoSecondario ().getComponenteImporto () ) {
                    if ( ci.getImporto () != null ) {
                        PagamentoComponenti componente = new PagamentoComponenti ();
                        componente.setProgressivo ( i++ );
                        componente.setCausale ( ci.getCausaleDescrittiva () );
                        componente.setImporto ( ci.getImporto () );
                        importoComplessivoPagamentoSecondario = importoComplessivoPagamentoSecondario.add ( ci.getImporto () );
//                        In caso di pagamento secondario anno e numero accertamento saranno sempre null, 
//                        il dato specifico riscossione va reperito come per il componente di default: 
                        List<DatiSpecificiRiscossione> listDati = selezionaDatoSpecificoRiscossioneConAnnoENumeroAccertamentoNonNulli ( posizione, dsrSecondario ); 
                        
                        componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
                        componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
                        componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
                        componente.setCodiceTributo (  listDati.get ( 0 ).getCodiceTributo () );
                        
                        componente.setUtenteUltimaModifica ( "InserisciListaDiCarico" );
                        pagamentoSecondario.getComponenti ().add ( componente );
                    }
                }
                pagamentoSecondario.setImportoComplessivo ( importoComplessivoPagamentoSecondario );
                pagamentoSecondario.setIdentificativoDominio ( tipoPagamentoSecondario.getEpayTEnti ().getCodiceFiscale () );
                idPagamentoSecondario = pagamentoManager.inserisciPagamentoSecondario ( pagamentoSecondario, pagamentoEntity );
            }
            //CSI_PAG-1887 FINE

            RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
            registroVersamenti.setIdPagamento ( pagamentoEntity.getIdPagamento () );
            registroVersamenti.setIdPagamentoSecondario ( idPagamentoSecondario );
            registroVersamenti.setRisultato ( StatoPagamento.DA_PAGARE.getDescrizione () );
            registroVersamenti.setIuv ( codiceAvviso.getIuv () );
            registroVersamenti.setIdStato ( StatoPagamento.DA_PAGARE.getId () );
            registroVersamenti.setOrigineInserimento ( this.getClass ().getName () );
            registroVersamentiManager.inserisci ( registroVersamenti );

            return pagamentoManager.mappaPagamentoEsteso ( pagamentoEntity);
        } catch ( TassonomiaServiceException e ) {
            log.debug ( methodName, "ko : " + e.getMessage () );
            log.debugEnd ( methodName );
            throw new PosizioneDebitoriaException ( e.getCode (), e.getMessage (), e.getCause () );
        } catch ( Exception e ) {
            log.debug ( methodName, "ko : " + e.getMessage () );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.INSERIMENTO_PAGAMENTO_FALLITO;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ), e.getCause () );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private List<DatiSpecificiRiscossione> selezionaDatoSpecificoRiscossioneConAnnoENumeroAccertamentoNonNulli ( PosizioneDaInserireType posizione,
        DatiSpecificiRiscossioneOutput dsr ) throws TassonomiaServiceException {
        List<DatiSpecificiRiscossione> listDati = new LinkedList<> ();
        for ( DatiSpecificiRiscossione dato: dsr.getElencoDatiSpecifici () ) {
            if ( null != dato.getAnnoAccertamento () && null != dato.getNumeroAccertamento () ) {
                listDati.add ( dato );
            }

        }
        if ( CollectionUtils.isEmpty ( listDati ) || listDati.size () > 1 ) {
            CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
            throw new TassonomiaServiceException ( ce.getCodice (),
                ce.getMessaggio ( maxErrorMessageWidth ) + " , per la posizione debitoria: " + posizione.getIdPosizioneDebitoria ()
                    + ", non essendo specificate componenti viene richiesto un'unico riferimento contabile, con anno e numero accertamento specificato, valido per l'anno in corso su Catalogo!" );
        }
        return listDati;
    }

    private PagamentoComponenti creaComponenteDiDefault ( DatiSpecificiRiscossioneOutput dsr, PosizioneDaInserireType posizione, boolean bMultibeneficiario )
                    throws TassonomiaServiceException {

        PagamentoComponenti componente = new PagamentoComponenti ();
        componente.setProgressivo ( 1 );
        componente.setImporto ( bMultibeneficiario ? posizione.getImportoPrincipale () : posizione.getImportoTotale () );
        componente.setUtenteUltimaModifica ( "InserisciListaDiCarico" );
        List<DatiSpecificiRiscossione> listDati = selezionaDatoSpecificoRiscossioneConAnnoENumeroAccertamentoNonNulli ( posizione, dsr ); 
        componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
        componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
        componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
        componente.setCodiceTributo (  listDati.get ( 0 ).getCodiceTributo () );
        if ( StringUtils.isNotBlank ( posizione.getDescrizioneCausaleVersamento () ) ) {
            componente.setCausale ( StringUtils.substring (posizione.getDescrizioneCausaleVersamento (), 0, 140) );
        } else {
            componente.setCausale ( StringUtils.substring (listDati.get ( 0 ).getDescrizione (), 0, 140) );
        }
        return componente;
    }
    
    private Anagrafica inserisciAnagrafica ( SoggettoType soggettoPagatore ) {
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
            anagrafica = anagraficaMenager.inserisci ( anagrafica );
            return anagrafica;
        } finally {
            log.debugEnd ( methodName );
        }

    }

    private void verificaNumeroTrasmissione ( String idMessaggio ) throws TestataException {
        final String methodName = "verificaNumeroTrasmissione";

        log.debugStart ( methodName );
        if ( StringUtils.isBlank ( idMessaggio ) ) {
            log.debug ( methodName, "KO" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.NUMERO_TRASMISSIONE_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, idMessaggio ) );
        }

        try {
            List<RegistroElaborazioni> registriElaborazioni
            = registroElaborazioniManager.getRegistroElaborazioniByOperazioneEIdMessaggio ( OPERARIONE, idMessaggio );
            /*
             * for (RegistroElaborazioni registroElaborazioni : registriElaborazioni) { if ("successo".equals(registroElaborazioni.getEsito())) {
             * log.debug(methodName, "KO"); log.debugEnd(methodName); CodiciEsito ce = CodiciEsito.NUMERO_TRASMISSIONE_ERRORE; throw new
             * TestataException(ce.getCodice(), ce.getMessaggio(idMessaggio)); } }
             */
            if ( !registriElaborazioni.isEmpty () ) {
                log.debug ( methodName, "KO" );
                CodiciEsito ce = CodiciEsito.NUMERO_TRASMISSIONE_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, idMessaggio ) );
            }
            log.debug ( methodName, "OK" );
        } catch ( NoDataException e ) {
            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private void verificaValiditaPagamento ( TipoPagamento tipoPagamento, PosizioneDaInserireType posizione ) throws PosizioneDebitoriaException {
        final String methodName = "verificaValiditaPagamento";
        log.debugStart ( methodName );
        if ( posizione.getDataFineValidita () != null &&
                        posizione.getDataFineValidita ().toGregorianCalendar ().getTime ().before ( new Date () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_SCADUTO;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }

        if ( tipoPagamento.getInizioValidita () != null &&
                        posizione.getDataFineValidita () != null &&
                        posizione.getDataFineValidita ().toGregorianCalendar ().getTime ().before ( tipoPagamento.getInizioValidita () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        //EPAY-155
        //		if (tipoPagamento.getFineValidita() != null &&
        //		    posizione.getDataInizioValidita()!= null &&
        //		    posizione.getDataInizioValidita().toGregorianCalendar().getTime().after(tipoPagamento.getInizioValidita())) {
        //				CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
        //				throw new PosizioneDebitoriaException(ce.getCodice(), ce.getMessaggio(maxErrorMessageWidth));
        //		}

        log.debugEnd ( methodName );
    }

    //CSI_PAG-1888 Metodo con parametri di input cambiati. Non c'e' piu' ente e c'e' il flag bPagamentoPrincipale
    private BigDecimal verificaComponentiPagamento ( TipoPagamento tipoPagamento, PosizioneDaInserireType posizione, boolean bPagamentoPrincipale,
        boolean bMultiBeneficiario )
                        throws PosizioneDebitoriaException {
        final String methodName = "verificaComponentiPagamento";

        log.debugStart ( methodName );
        try {
            if ( bMultiBeneficiario && null == posizione.getImportoPrincipale () ) {
                throw new PosizioneDebitoriaException ( CodiciEsito.ERRORE_IMPORTO_PRINCIPALE_ASSENTE.getCodice (),
                    CodiciEsito.ERRORE_IMPORTO_PRINCIPALE_ASSENTE.getMessaggio ( maxErrorMessageWidth ) );
            }
            if ( bPagamentoPrincipale
                && ( null == posizione.getComponentiImporto () || CollectionUtils.isEmpty ( posizione.getComponentiImporto ().getComponenteImporto () ) ) ) {
                log.debug ( methodName, "OK" );
                return bMultiBeneficiario? posizione.getImportoPrincipale (): posizione.getImportoTotale ();
            }

            List<ComponenteImportoType> componenteImportoType = bPagamentoPrincipale ? posizione.getComponentiImporto ().getComponenteImporto ()
                            : posizione.getComponentiImportoSecondario ().getComponenteImporto ();
            BigDecimal importo = BigDecimal.ZERO;
            for ( ComponenteImportoType componente: componenteImportoType ) {
                if ( null != componente.getImporto () ) {
                    
                    importo = importo.add ( componente.getImporto () );

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

            log.debug ( methodName, "OK" );
            if ( bMultiBeneficiario ) {
                if ( bPagamentoPrincipale && importo.compareTo ( posizione.getImportoPrincipale () ) != 0 ) {
                    CodiciEsito ce = CodiciEsito.ERRORE_SOMMA_IMPORTI_PRINCIPALE;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio (
                        maxErrorMessageWidth ) );
                }
                if ( !bPagamentoPrincipale && importo.compareTo ( posizione.getImportoSecondarioAltroEnte () ) != 0 ) {
                    CodiciEsito ce = CodiciEsito.ERRORE_SOMMA_IMPORTI_SECONDARIO;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio (
                        maxErrorMessageWidth ) );
                }
            }
            return importo;
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private void verificaRiferimentiPagamento ( PosizioneDaInserireType posizione ) throws PosizioneDebitoriaException {
        final String methodName = "verificaRiferimentiPagamento";

        log.debugStart ( methodName );

        try {
            if ( posizione.getRiferimentiPagamento () == null || posizione.getRiferimentiPagamento ().getRiferimento () == null
                            || posizione.getRiferimentiPagamento ().getRiferimento ().isEmpty () ) {
                log.debug ( methodName, "OK" );
                return;
            }

            List<RiferimentoType> riferimentoType = posizione.getRiferimentiPagamento ().getRiferimento ();
            for ( RiferimentoType riferimento: riferimentoType ) {
                if ( ( !StringUtils.isBlank ( riferimento.getNome () ) && StringUtils.isBlank ( riferimento.getValore () ) )
                                || ( StringUtils.isBlank ( riferimento.getNome () ) && !StringUtils.isBlank ( riferimento.getValore () ) ) ) {
                    CodiciEsito ce = CodiciEsito.RIFERIMENTO_PAGAMENTO_VUOTO;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
                }
            }

            // Verificare che se ci sono riferimenti pagamento, il soggetto ha codice fiscale e, a seconda
            // del tipo di soggetto, che codice fiscale, nome, cognome o ragione sociale,come anonimo?
            if ( posizione.getSoggettoPagatore ().getPersonaFisica () != null && posizione.getSoggettoPagatore ().getPersonaGiuridica () == null ) {
                if ( !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () )
                                || !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getPersonaFisica ().getNome () )
                                || !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getPersonaFisica ().getCognome () ) ) {
                    CodiciEsito ce = CodiciEsito.RIFERIMENTO_PAGAMENTO_PERSONA_FISICA_ERRATA;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );

                }
            } else if ( posizione.getSoggettoPagatore ().getPersonaFisica () == null && posizione.getSoggettoPagatore ().getPersonaGiuridica () != null ) {
                if ( !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () )
                                || !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getPersonaGiuridica ().getRagioneSociale () ) ) {
                    CodiciEsito ce = CodiciEsito.RIFERIMENTO_PAGAMENTO_PERSONA_GIURIDICA_ERRATA;
                    throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );

                }
            }
            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private void verificaCodicePagamentoRifEnte ( Ente ente, TipoPagamento tipoPagamento, PosizioneDaInserireType posizione )
                    throws PosizioneDebitoriaException {
        final String methodName = "verificaCodicePagamentoRifEnte";

        log.debugStart ( methodName );
        try {
//            Pagamento pagamento = pagamentoManager.getPagamentoPerCodRifEnte ( ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
//                posizione.getIdPosizioneDebitoria () );
            
            Long idPagamento = pagamentoManager.getIdPagamentoPerCodRifEnte ( ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
                posizione.getIdPosizioneDebitoria () );
            if ( idPagamento != null ) {
                log.debug ( methodName, "KO" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.CODICE_PAGAMENTO_RIF_ENTE_DUPLICATO;
                throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
            }
            log.debug ( methodName, "OK" );
        } catch ( MoreResultException e ) {
            log.debug ( methodName, "KO" );
            CodiciEsito ce = CodiciEsito.CODICE_PAGAMENTO_RIF_ENTE_DUPLICATO;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (),
                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
        } catch ( NoDataException e ) {
            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private Ente verificaTestataEnte ( String cfEnte ) throws TestataException {
        final String methodName = "verificaTestataEnte";

        log.debugStart ( methodName );

        try {
            Ente ente = enteManager.getByCF ( cfEnte );
            if ( ente == null ) {
                log.debug ( methodName, "KO" );
                CodiciEsito ce = CodiciEsito.CF_ENTE_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, cfEnte ) );
            }
            log.debug ( methodName, "OK" );
            return ente;
        } catch ( NoDataException e ) {
            log.debug ( methodName, "KO" );
            CodiciEsito ce = CodiciEsito.CF_ENTE_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, cfEnte ) );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    //CSI_PAG-1887 Tolto da questo metodo il controllo del codice versamento
    private TipoPagamento verificaTestataTipoPagamento ( TipoPagamento tipoPagamento ) throws TestataException {
        final String methodName = "verificaTestataTipoPagamento";

        log.debugStart ( methodName );
        if ( tipoPagamento.getFineValidita () != null ) {
            if ( tipoPagamento.getFineValidita ().before ( new Date () ) ) {
                log.debug ( methodName, "KO3" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_ATTIVO_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }
        }

        // RDI 02 2019
        if ( tipoPagamento.getIdTipoPagamento () != null ) {
            TipologiaPagamento tipologiaPagamentoReds = tipologiaPagamentoManager.getTipologiaPagamentoByCodice ( TipoPagamentoType.REDS.value());
            if ( tipologiaPagamentoReds != null && tipologiaPagamentoReds.getId ().equals ( tipoPagamento.getIdTipoPagamento () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_PERMESSO_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }
        }

        //RDI-34
        if ( tipoPagamento.getIdTipoPagamento () != null && tipoPagamento.getTipologiaPagamento() != null ) {
            if ( TipoPagamentoType.MABL.value().equalsIgnoreCase(tipoPagamento.getTipologiaPagamento().getCodice())) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_MARCA_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }
            //EPAY-347
            if ( TipoPagamentoType.PS.value().equalsIgnoreCase(tipoPagamento.getTipologiaPagamento().getCodice())) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SPONTANEO_ERRORE;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }

        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return tipoPagamento;
    }

    private void verificaTestataNumeroPosizioniEImportoTotale ( TestataListaCarico testata, List<PosizioneDaInserireType> posizioniDebitorie )
                    throws TestataException {
        final String methodName = "verificaTestataNumeroPosizioniEImportoTotale";

        log.debugStart ( methodName );
        BigDecimal importoTotale = new BigDecimal ( 0 );
        int numeroPosizioni = 0;
        for ( PosizioneDaInserireType posizione: posizioniDebitorie ) {
            if ( posizione.getImportoTotale () == null ) {
                log.debug ( methodName, "KO0" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_NULLO;
                throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
            }
            numeroPosizioni++;
            importoTotale = importoTotale.add ( posizione.getImportoTotale () );
        }
        if ( numeroPosizioni != testata.getNumeroPosizioniDebitorie () ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.POSIZIONE_DEBITORIE_NUMERO_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        if ( importoTotale.compareTo ( testata.getImportoTotaleListaDiCarico () ) != 0 ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_ERRORE;
            throw new TestataException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
    }

    private void verificaElementiObbligatori ( PosizioneDaInserireType posizione ) throws PosizioneDebitoriaException {
        testField ( posizione.getIdPosizioneDebitoria (), CodiciEsito.CODICE_PAGAMENTO_RIF_ENTE_OBBLIGATORIO );
        testField ( posizione.getDescrizioneCausaleVersamento (), CodiciEsito.CAUSALE_OBBLIGATORIA );
        testField ( posizione.getSoggettoPagatore (), CodiciEsito.ANAGRAFICA_OBBLIGATORIA );
        if ( posizione.getSoggettoPagatore ().getPersonaFisica () == null &&
                        posizione.getSoggettoPagatore ().getPersonaGiuridica () == null ) {
            CodiciEsito ce = CodiciEsito.FLAG_PERSONA_FISICA_OBBLIGATORIO;
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        if ( posizione.getSoggettoPagatore ().getPersonaFisica () != null ) {
            testField ( posizione.getSoggettoPagatore ().getPersonaFisica ().getNome (), CodiciEsito.NOME_OBBLIGATORIO );
            testField ( posizione.getSoggettoPagatore ().getPersonaFisica ().getCognome (), CodiciEsito.COGNOME_OBBLIGATORIO );
            if ( !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () )
                            || !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getPersonaFisica ().getNome () )
                            || !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getPersonaFisica ().getCognome () ) ) {
                testField ( posizione.getImportoTotale (), CodiciEsito.IMPORTO_OBBLIGATORIO );
            }
        }
        if ( posizione.getSoggettoPagatore ().getPersonaGiuridica () != null ) {
            testField ( posizione.getSoggettoPagatore ().getPersonaGiuridica ().getRagioneSociale (), CodiciEsito.RAGIONESOCIALE_OBBLIGATORIA );
            if ( !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () )
                            || !"ANONIMO".equals ( posizione.getSoggettoPagatore ().getPersonaGiuridica ().getRagioneSociale () ) ) {
                testField ( posizione.getImportoTotale (), CodiciEsito.IMPORTO_OBBLIGATORIO );
            }
        }
        testField ( posizione.getSoggettoPagatore ().getIdentificativoUnivocoFiscale (), CodiciEsito.CODICE_FISCALE_OBBLIGATORIO );
    }

    private void testField ( final String str, final CodiciEsito ce ) throws PosizioneDebitoriaException {
        if ( StringUtils.isBlank ( str ) ) {
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
    }

    private void testField ( final Object obj, final CodiciEsito ce ) throws PosizioneDebitoriaException {
        if ( obj == null ) {
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
    }

    private void testField ( final BigDecimal bd, final CodiciEsito ce ) throws PosizioneDebitoriaException {
        if ( bd == null || bd.compareTo ( BigDecimal.ZERO ) <= 0 ) {
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) );
        }
    }

    private void inserisciRegistroElaborazioniErrore ( final TestataListaCarico testata, final Ente ente, final TipoPagamento tipoPagamento,
        final String message ) {
        final String methodName = "inserisciRegistroElaborazioniErrore";

        log.debugStart ( methodName );
        try {
            Long idEnte = ( ente == null ) ? -1 : ente.getIdEnte ();
            Long idTipoPagamento = ( tipoPagamento == null ) ? -1 : tipoPagamento.getIdTipoPagamento ();
            inserisciRegistroElaborazioniErrore ( testata.getIdMessaggio (), idEnte, idTipoPagamento, message );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private void inserisciRegistroElaborazioniErrore ( final String idMessaggio, final Long idEnte, final Long idTipoPagamento, final String message ) {
        final String methodName = "inserisciRegistroElaborazioniErrore";

        log.debugStart ( methodName );
        try {
            Date data = new Date ();
            RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni ();
            registroElaborazioni.setDataInizio ( new Timestamp ( data.getTime () ) );
            registroElaborazioni.setDataFine ( new Timestamp ( data.getTime () ) );
            registroElaborazioni.setOperazione ( OPERARIONE );
            registroElaborazioni.setEsito ( "errore" );

            registroElaborazioni.setIdMessaggio ( idMessaggio );
            registroElaborazioni.setIdEnte ( idEnte );
            registroElaborazioni.setIdTipoPagamento ( idTipoPagamento );

            registroElaborazioni.setMessageFault ( message );

            registroElaborazioniManager.inserisci ( registroElaborazioni );

        } finally {
            log.debugEnd ( methodName );
        }
    }

    private RegistroElaborazioni inserisciRegistroElaborazioniInizio ( final TestataListaCarico testata, final Ente ente, final TipoPagamento tipoPagamento ) {
        final String methodName = "inserisciRegistroElaborazioniInizio";

        log.debugStart ( methodName );
        try {
            Date data = new Date ();
            RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni ();
            registroElaborazioni.setDataInizio ( new Timestamp ( data.getTime () ) );
            registroElaborazioni.setOperazione ( OPERARIONE );
            registroElaborazioni.setEsito ( "elaborazione" );
            registroElaborazioni.setIdEnte ( ente.getIdEnte () );
            registroElaborazioni.setIdTipoPagamento ( tipoPagamento.getIdTipoPagamento () );
            registroElaborazioni.setPagamentiSpontanei ( Boolean.FALSE );
            registroElaborazioni.setIdMessaggio ( testata.getIdMessaggio () );
            registroElaborazioni.setNumPagamenti ( testata.getNumeroPosizioniDebitorie () );
            registroElaborazioni.setImportoTotPagamenti ( testata.getImportoTotaleListaDiCarico () );
            registroElaborazioni.setPagamenti ( new ArrayList<Pagamento> () );

            Long id = registroElaborazioniManager.inserisci ( registroElaborazioni );
            registroElaborazioni.setId ( id );
            return registroElaborazioni;
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private void aggiornaRegistroElaborazioniFine ( RegistroElaborazioni registroElaborazioni ) {
        final String methodName = "aggiornaRegistroElaborazioniFine";

        log.debugStart ( methodName );
        try {
            Date data = new Date ();
            registroElaborazioni.setDataFine ( new Timestamp ( data.getTime () ) );
            registroElaborazioni.setEsito ( registroElaborazioni.getFaults ().isEmpty () ? "successo" : "successo parziale" );
            registroElaborazioniManager.aggiorna ( registroElaborazioni );
        } finally {
            log.debugEnd ( methodName );
        }
    }
    //CSI_PAG-1887 INIZIO
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

    private void verificaImportoSecondarioAltroEnte ( PosizioneDaInserireType posizione ) throws PosizioneDebitoriaException {
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
    //CSI_PAG-1887 FINE
}
