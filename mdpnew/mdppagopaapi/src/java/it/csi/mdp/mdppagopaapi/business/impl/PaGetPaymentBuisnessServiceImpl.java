/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import static it.csi.mdp.mdppagopaapi.util.Constants.POSTAL;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import it.csi.mdp.mdppagopaapi.business.ApplicationService;
import it.csi.mdp.mdppagopaapi.business.ConfigurazioneService;
import it.csi.mdp.mdppagopaapi.business.DatiSingoloVersamentoService;
import it.csi.mdp.mdppagopaapi.business.ElementoMultiversamentoService;
import it.csi.mdp.mdppagopaapi.business.GatewayService;
import it.csi.mdp.mdppagopaapi.business.GdeService;
import it.csi.mdp.mdppagopaapi.business.IuvOtticiService;
import it.csi.mdp.mdppagopaapi.business.MdpGetpaymentService;
import it.csi.mdp.mdppagopaapi.business.MdpSingoloTransferService;
import it.csi.mdp.mdppagopaapi.business.PaGetPaymentBuisnessService;
import it.csi.mdp.mdppagopaapi.business.PaymentModeService;
import it.csi.mdp.mdppagopaapi.business.StatoTransazioneService;
import it.csi.mdp.mdppagopaapi.business.TransazioneService;
import it.csi.mdp.mdppagopaapi.dto.exception.GetPaymentException;
import it.csi.mdp.mdppagopaapi.dto.exception.PaymentException;
import it.csi.mdp.mdppagopaapi.integration.domain.Application;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationcustomfield;
import it.csi.mdp.mdppagopaapi.integration.domain.DatiSingoloVersamento;
import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamento;
import it.csi.mdp.mdppagopaapi.integration.domain.Gde;
import it.csi.mdp.mdppagopaapi.integration.domain.IuvOttici;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpGetpayment;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpSingoloTransfer;
import it.csi.mdp.mdppagopaapi.integration.domain.Transazione;
import it.csi.mdp.mdppagopaapi.integration.dto.ConfigDTO;
import it.csi.mdp.mdppagopaapi.integration.repository.ApplicationCustomFieldRepository;
import it.csi.mdp.mdppagopaapi.interfacews.DatiSingoloVersamentoRPT;
import it.csi.mdp.mdppagopaapi.interfacews.ElencoDatiVersamento;
import it.csi.mdp.mdppagopaapi.interfacews.ErroriList;
import it.csi.mdp.mdppagopaapi.interfacews.MapEntry;
import it.csi.mdp.mdppagopaapi.interfacews.Metadata;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtEntityUniqueIdentifier;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtFaultBean;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtMapEntry;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtMetadata;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtPaymentPA;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtSubject;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtTransferListPA;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtTransferPA;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaGetPaymentReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaGetPaymentRes;
import it.csi.mdp.mdppagopaapi.pa.pafornode.StEntityUniqueIdentifierType;
import it.csi.mdp.mdppagopaapi.pa.pafornode.StOutcome;
import it.csi.mdp.mdppagopaapi.util.ApplicationcustomfieldsEnum;
import it.csi.mdp.mdppagopaapi.util.BusinessUtil;
import it.csi.mdp.mdppagopaapi.util.Constants;
import it.csi.mdp.mdppagopaapi.util.CostantiNodoSpc;
import it.csi.mdp.mdppagopaapi.util.EncryptionDecryptionUtil;
import it.csi.mdp.mdppagopaapi.util.FaultBeanEnum;
import it.csi.mdp.mdppagopaapi.util.MacUtil;
import it.csi.mdp.mdppagopaapi.util.ParametriApplicativo;
import it.csi.mdp.mdppagopaapi.util.StatoTransazioneEnum;
import it.csi.mdp.mdppagopaapi.util.Utils;
import it.csi.mdp.mdppagopaapi.util.clientws.ChiediDatiPagamentoException_Exception;
import it.csi.mdp.mdppagopaapi.util.clientws.Serviziofruitoremdp;
import it.csi.mdp.mdppagopaapi.util.clientws.TransazioneExtraAttribute;
import it.csi.mdp.mdppagopaapi.util.clientws.UnrecoverableException_Exception;

@Service
public class PaGetPaymentBuisnessServiceImpl implements PaGetPaymentBuisnessService {

    protected static final Logger logger = LoggerFactory.getLogger ( PaGetPaymentBuisnessServiceImpl.class );
    private static final String CHIEDI_DATI_PAGAMENTO_KO = "La chiamata al servizio remoto per la verifica del pagamento ha restituito valore nullo o KO relativamente ai dati del seguente pagamento: ";
    private static final String IMPORTO_KO = "L'importo totale arrivato dal gestionale e' pari a 0";

    @Autowired
    private GdeService gdeService;

    @Autowired
    private ConfigurazioneService configurazioneService ;

	@Autowired
	private ConfigServiceImpl configService;

    @Autowired
    private IuvOtticiService iuvOtticiService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private TransazioneService transazioneService;

    @Autowired
    private MdpSingoloTransferService mdpSingoloTransferService;

    @Autowired
    private MdpGetpaymentService mdpGetpaymentService;

    @Autowired
    private ElementoMultiversamentoService elementoMultiversamentoService;

    @Autowired
    private DatiSingoloVersamentoService datiSingoloVersamentoService;

    @Autowired
    private ApplicationCustomFieldRepository applicationCustomFieldRepository;
    
    @Autowired
    private StatoTransazioneService statoTransazioneService;
    
    @Autowired
    private GatewayService gatewayService;
    
    @Autowired
    private PaymentModeService paymentModeService;
    
    
    
    private static JAXBContext jContextElencoDatiVersamento;
    private static JAXBContext jContextMetadata;
    
    {
    	try {
    		jContextElencoDatiVersamento = JAXBContext.newInstance ( ElencoDatiVersamento.class );
	        logger.info("Instanziato unmarshallerElencoDatiVersamento");
			
	        jContextMetadata = JAXBContext.newInstance ( Metadata.class );
	        logger.info("Instanziato unmarshallerMetaData");
			
		} catch (JAXBException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("Errore in fase di inizializzazione del contesto Spring, impossibile instanziare i Marshaller");
		}
    }
    
    @Override
    @Transactional ( propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class )
    public PaGetPaymentRes paGetPayment ( PaGetPaymentReq paGetPaymentReq ) {
        PaGetPaymentRes response = new PaGetPaymentRes ();
        try {
            return exec ( paGetPaymentReq );
        } catch ( PaymentException e ) {
            logger.error ( "Errore PaymentException", e );
            // gestisco il fault da restituire.
            response.setFault ( e.getCtFault () );
            response.setOutcome ( StOutcome.KO );
            return response;
        } catch ( Exception e ) {
            logger.error ( "Errore Exception", e );
            // gestisco il fault da restituire.
            return makeFaultResponse(response, FaultBeanEnum.PAA_SYSTEM_ERROR.name (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription (), 
				     paGetPaymentReq.getIdPA (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription ());
        }
    }

    /**
     *
     */
    private PaGetPaymentRes exec ( PaGetPaymentReq paGetPaymentReq ) throws PaymentException {
        PaGetPaymentRes response = new PaGetPaymentRes ();
        // validazione dei parametri di input
        // non ci sono validazioni dei parametri di input perche tutti obbligatori
        // validazione di business in base ai dati presenti su DB
        // step 1 controllo se lo iuv e' presente quindi staccato da noi.

        
		String iuv = BusinessUtil.fixIUV ( paGetPaymentReq.getQrCode ().getNoticeNumber () );
        IuvOttici iuvOttici = iuvOtticiService.findTopByIuvOttico ( iuv );
        ElencoDatiVersamento elencoDatiVersamento;
        it.csi.mdp.mdppagopaapi.util.clientws.ParametriChiediDatiPagamento parametriChiediDatiPagamento = null;
        Metadata metadata;
        Map<String, String> mappaTea;
        //inserisco riga in gd che puoi verra' aggiornata in base all'occorrenza
        Gde gde = gdeService.inserisciEventoGiornale ( createGde ( paGetPaymentReq, null, iuvOttici!=null?iuvOttici.getApplication ().getId ():null, null, iuv, false ) );

        boolean multibeneficiario = false;
        if ( null == iuvOttici || null == iuvOttici.getApplication ()) {
            //scrittura GDE
            logger.info ( "PaGetPayment Nessuno iuv trovato" );
            String error = FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.name () + ";" + FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getDescription ();
            aggiornaGde(gde, Constants.KO, error);
            return makeFaultResponse(response, FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.name (), FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getDescription (), 
					     paGetPaymentReq.getIdPA (), FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getDescription ());
        }
        List<Applicationcustomfield> customFields = applicationCustomFieldRepository.findAllByApplicationEnabled ( iuvOttici.getApplication ().getId () );
        String keyDb= configurazioneService.getSkeyDb () ;
        
        //controllo sull'application field
        // recupero i dati grazie alle relazioni sulle entities
        if ( !CollectionUtils.isEmpty ( customFields ) ) {
            try {
                //step 1 dati appliction
                controlliPreliminariDiBusiness (customFields, iuvOttici, paGetPaymentReq.getIdPA (), paGetPaymentReq.getIdBrokerPA (), paGetPaymentReq.getIdStation (), keyDb);

                //step 3 chiamata fruitore si recupera endPoint
                String endPointFruitore = getValueByNameApplicationCustomField ( customFields, ApplicationcustomfieldsEnum.ENDPOINTSERVIZINODO.getCodice () );

                //controllo correttezza wsdl
//	            assert endPointFruitore != null;
                Assert.notNull(endPointFruitore, "EndPoint non valorizzato");
	            if ( !endPointFruitore.contains ( Constants.WSDL ) ) {
                    endPointFruitore = endPointFruitore.concat ( Constants.WSDL );
                }

                // step 4 chiedi dati pagamento al fruitore
                // paGetPaymentReq.getAmount  non e' un campo abbligatorio mentre importoVersamento della chiedi dati pagamento si, per cui lo setto a zero de arriva null
                if ( null == paGetPaymentReq.getAmount () ) {
					paGetPaymentReq.setAmount ( BigDecimal.ZERO.setScale ( 2, RoundingMode.HALF_UP ) );
				}
				String passphrase = getValueByNameApplicationCustomField ( customFields, ApplicationcustomfieldsEnum.PASSPHRASE.getCodice () );
				Transazione transazione= initTransazione ( iuvOttici.getApplication ().getId () );
                parametriChiediDatiPagamento  = createRequestChiediDatiPagamento ( iuv, paGetPaymentReq.getAmount (), passphrase,  transazione.getTransactionId ());
                //imposto transactionId sul gde, al prossimo update verra' aggiornato il campo
                gde.setTransactionid(parametriChiediDatiPagamento.getTransactionId());
                //chiamata al fruitore

                //*******************************************************************************************

                Serviziofruitoremdp iPagNodo = (Serviziofruitoremdp) Utils.getProxyAPIService ( Serviziofruitoremdp.class, endPointFruitore, null );

                it.csi.mdp.mdppagopaapi.util.clientws.EsitoChiediDatiPagamento datiPag = null;
                try {
//                   
                    datiPag = iPagNodo.chiediDatiPagamento ( parametriChiediDatiPagamento );
                    logger.info ( "chiediDatiPagamento.result=" + datiPag );
                } catch ( UnrecoverableException_Exception e ) {
                    logger.info ( "Expected exception: UnrecoverableException has occurred." );
                    logger.info ( e.toString () );
                } catch ( ChiediDatiPagamentoException_Exception e ) {
                    logger.info ( "Expected exception: ChiediDatiPagamentoException has occurred." );
                    logger.info ( e.toString () );
                }
                //*******************************************************************************************

//	            Se datipag sono null registrare errore su transazione, con stato 5 e finish date
                if (null == datiPag)
                {
                    transazioneService.aggiornaStatoTransazione ( transazione, StatoTransazioneEnum.UNSUCCESFUL.getCodice ());
                }
               
	            Assert.notNull(datiPag, "datiPag non valorizzato");
	           
	            
//	            ----------------------
				String multiversamentoXml;
	            if ( datiPag.getEsito ().equals ( Constants.OK ) || ( datiPag.getEsito ().equals ( Constants.KO )
                                && FaultBeanEnum.PAA_ATTIVA_RPT_IMPORTO_NON_VALIDO.name ().equals ( datiPag.getCodErrore () ) ) ) {

					mappaTea = costruisciMAppaTea ( datiPag.getTea () );
					
					BigDecimal importoTotaleDaVersare = new BigDecimal ( "0" ).setScale ( 2, RoundingMode.HALF_UP );
                    String importoTotaleDaVersareString = mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE );
                    if ( null != importoTotaleDaVersareString ) {
                        importoTotaleDaVersare = new BigDecimal ( importoTotaleDaVersareString ).setScale ( 2, RoundingMode.HALF_UP );
                    }
					transazione.setAmount ( importoTotaleDaVersare );
//		             Se ok registrare  su transazione, con stato 3 e start date , se ci sono i dati metto 9
					transazioneService.aggiornaStatoTransazione ( transazione,datiPag.getEsito ().equals ( Constants.OK )?
					                StatoTransazioneEnum.STARTED.getCodice () :  StatoTransazioneEnum.ATTESA_RT.getCodice ());

					// check importo totale vuoto
					if ( Float.parseFloat ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) ) == 0 ) {
						logger.debug ( "Errore nel controllo dell'importo totale da versare: e' a zero" );
						aggiornaGde(gde, Constants.KO, "Errore nel controllo dell'importo totale da versare: e' a zero");
			            return makeFaultResponse(response, FaultBeanEnum.PAA_SYSTEM_ERROR.name (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () + IMPORTO_KO, 
       					     paGetPaymentReq.getIdPA (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription ());
					}

					multiversamentoXml = mappaTea.get ( CostantiNodoSpc.TEA_MULTIVERSAMENTO );
					String datiAggiuntiviXML = mappaTea.get ( CostantiNodoSpc.TEA_DATI_AGGIUNTIVI );
					

                    if ( null != mappaTea.get ( CostantiNodoSpc.TEA_MULTIBENEFICIARIO )
                                    && mappaTea.get ( CostantiNodoSpc.TEA_MULTIBENEFICIARIO ).equals ( "true" ) )
                        multibeneficiario = true;

                    //controlli sui valori dei TEA , obbligatorieta' - lunghezza e contenuto dove richiesto
                    List<String> errori = controlliFormali ( mappaTea, customFields );
                    if ( !CollectionUtils.isEmpty ( errori ) ) {
                        logger.debug ( "ERRORI NEI CONTROLLI FORMALI" );
                        aggiornaGde(gde, Constants.KO, errori.toString());
                        
			            return makeFaultResponse(response, FaultBeanEnum.PAA_SYSTEM_ERROR.name (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () + errori, 
			            					     paGetPaymentReq.getIdPA (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription ());
                    }
                    // controlla i dati del multiversamento dentro l'xml
                    elencoDatiVersamento = gestioneMultiversamentoUniversale ( multiversamentoXml, importoTotaleDaVersare, multibeneficiario, customFields );
                    metadata = gestioneDatiAggiuntivi ( datiAggiuntiviXML );
                    //vado a metetre il transactioniId nel map
                    MapEntry entry = new MapEntry ();
                    entry.setKey ( Constants.TRANSACTION_ID );
                    entry.setValue ( parametriChiediDatiPagamento.getTransactionId () );
                    if ( null == metadata ) {
                        metadata = new Metadata ();
                        List<MapEntry> listMap = new ArrayList<> ();
                        metadata.setMapEntry ( listMap );
                    }
                    //transaction_id lo metto sempre come primo elemento
                    metadata.getMapEntry ().add ( 0, entry );
                } else {
                    //chiediDatiPagamento KO
                    //scrittura GDE
                    FaultBeanEnum fault = FaultBeanEnum.PAA_SYSTEM_ERROR;
                    try {
                        fault = FaultBeanEnum.valueOf ( datiPag.getCodErrore () );
                    } catch ( Exception e ) {
						//empty catch
                    }
                    String error = fault.name () + ";" + datiPag.getMessaggioErrore ();
                    aggiornaGde(gde, Constants.KO, error);
                    transazioneService.aggiornaStatoTransazione ( transazione, StatoTransazioneEnum.UNSUCCESFUL.getCodice ());
                    return makeFaultResponse(response, fault.name (), CHIEDI_DATI_PAGAMENTO_KO + iuv , paGetPaymentReq.getIdPA (), datiPag.getMessaggioErrore ());
                }

				//scrittura record nella tabella  elementomultiversamento
	            logger.info("elementoMultiversamentoService.insert");
				ElementoMultiversamento elementoMultiversamento = elementoMultiversamentoService.insert (
								createElementoMultiversamento ( iuvOttici, parametriChiediDatiPagamento.getTransactionId (), multiversamentoXml ) );

				CtTransferListPA list;
				if ( null != elencoDatiVersamento ) {
					list = createTransferListMultibeneficiario ( customFields, elencoDatiVersamento, mappaTea, paGetPaymentReq, iuv, keyDb );
				} else {
					int numSingoliVersamenti = 1;
					BigDecimal transferAmount = new BigDecimal ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) ).setScale ( 2, RoundingMode.HALF_UP );
					list = createTransferList ( customFields, mappaTea, numSingoliVersamenti, transferAmount, paGetPaymentReq, iuv , keyDb);
				}
				String teaCodiceIdentificativoUnivocoPagatore = mappaTea.get ( CostantiNodoSpc.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE );
				int counter = 1;
				logger.info("controllo su elencoDatiVersamento != null");
				if (elencoDatiVersamento != null) {
					for ( DatiSingoloVersamentoRPT d : elencoDatiVersamento.getDatiSingoloVersamento () ) {
						datiSingoloVersamentoService.insert (
										createDatiSingoloVersamento ( elementoMultiversamento, d, counter, teaCodiceIdentificativoUnivocoPagatore, iuvOttici ) );
						counter++;
					}
				} else {
					DatiSingoloVersamentoRPT d = new DatiSingoloVersamentoRPT ();
					BigDecimal amount = new BigDecimal ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) ).setScale ( 2, RoundingMode.HALF_UP );
					d.setApplicationId ( iuvOttici.getApplication ().getId () );
					if ( mappaTea.get ( CostantiNodoSpc.TEA_CAUSALE_VERSAMENTO ) != null ) {
						d.setCausaleVersamento ( mappaTea.get ( CostantiNodoSpc.TEA_CAUSALE_VERSAMENTO ) );
					} else {
						d.setCausaleVersamento ( BusinessUtil.componiCausaleVersamento ( iuv, amount.doubleValue (), "" ) );
					}
					d.setDatiAccertamento ( null );
					d.setImportoSingoloVersamento ( amount );
					if ( mappaTea.get ( CostantiNodoSpc.TEA_DATI_SPECIFICI_RISCOSSIONE ) != null ) {
						d.setDatiSpecificiRiscossione ( mappaTea.get ( CostantiNodoSpc.TEA_DATI_SPECIFICI_RISCOSSIONE ) );
					} else {
						d.setDatiSpecificiRiscossione ( getValueByNameApplicationCustomField ( customFields, ApplicationcustomfieldsEnum.DATISPECIFICIRISCOSSIONE.getCodice () ) );
					}
					datiSingoloVersamentoService.insert ( createDatiSingoloVersamento ( elementoMultiversamento, d, 1, teaCodiceIdentificativoUnivocoPagatore, iuvOttici ) );
				}

				// risposta verso PA
				logger.info("createResponseGetPayment");
				response = createResponseGetPayment ( elencoDatiVersamento, paGetPaymentReq, mappaTea, metadata, customFields , keyDb);
				//scrittura record su tabella mdpgetpayment
				MdpGetpayment mdpGetpayment = mdpGetpaymentService.insert ( createMdpGetpayment ( response, iuvOttici, parametriChiediDatiPagamento.getTransactionId (),
												elementoMultiversamento, paGetPaymentReq ) );

				for ( CtTransferPA t : list.getTransfer () ) {
					//scrittura record su tabella mdptransfer
					mdpSingoloTransferService.insert ( createMdpSingoloTransfer ( t, mdpGetpayment.getIdGetpayment () ) );
				}

			} catch ( PaymentException e ) {
				logger.error ( "errore PaymentException", e );
				//invio di system error a pagoPA
				String errorMessage = e.getMessage () != null ? e.getMessage () : "Errore PaymentException";
				if ( e.getCtFault () != null ) {
					errorMessage = e.getCtFault ().getFaultCode () + "; " + e.getCtFault ().getDescription ();
				}
				String error = errorMessage.substring ( 0, Math.min ( 500, errorMessage.length () ) );
				aggiornaGde(gde, Constants.KO, error);
				return makeFaultResponse(response, FaultBeanEnum.PAA_SYSTEM_ERROR.name (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () + error,
						 paGetPaymentReq.getIdPA (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription ());
			} catch ( Exception e ) {
				//scrittura GDE
				logger.error ( "Errore generico",e);
				String stacktrace = ExceptionUtils.getStackTrace(e);
				String error = FaultBeanEnum.PAA_SYSTEM_ERROR.name () + ";" + stacktrace.substring ( 0, Math.min ( 450, stacktrace.length () ) );
				logger.info("Parametri prima della scrittura su GDE - FaultBeanEnum.PAA_SYSTEM_ERROR.name:"+FaultBeanEnum.PAA_SYSTEM_ERROR.name()
				           +" - FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription:"+FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription()
				           +" - paGetPaymentReq.getIdPA:"+paGetPaymentReq.getIdPA());
				aggiornaGde(gde, Constants.KO, error);
	            return makeFaultResponse(response, FaultBeanEnum.PAA_SYSTEM_ERROR.name (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () + "Errore generico",
	            						 paGetPaymentReq.getIdPA (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription ());
			}
		}

		aggiornaGde(gde, Constants.OK, "");
		
		return response;
    }
    
   

    private PaGetPaymentRes makeFaultResponse(PaGetPaymentRes response, String faultCode, String description, String idPA, String faultString) {
    	response.setFault (  CtFaultBean.builder ()
                .withFaultCode ( faultCode )
                .withDescription (description)
                .withId ( idPA )
				.withFaultString ( faultString)
                .build () );
    	response.setOutcome ( StOutcome.KO );
		return response;
	}

	private void aggiornaGde(Gde gde, String esito, String errore) {
		gde.setEsito ( esito );
		gde.setParametrispecificiinterfaccia(errore);
		gde.setLastUpdate ( new Date ( System.currentTimeMillis () ) );
		gdeService.inserisciEventoGiornale(gde);
	}

    /**
     * metodo che costruisce la risposat verso PA
     *
     */
    private PaGetPaymentRes createResponseGetPayment (ElencoDatiVersamento elencoDatiVersamento, PaGetPaymentReq paGetPaymentReq,
                                                      Map<String, String> mappaTea, Metadata metadata, List<Applicationcustomfield> applicationcustomfields, String keyDb ) throws GetPaymentException {
        PaGetPaymentRes response = new PaGetPaymentRes ();
        DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
        response.setOutcome ( StOutcome.OK );

        CtPaymentPA data = new CtPaymentPA ();
		BigDecimal transferAmount = new BigDecimal ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) ).setScale ( 2, RoundingMode.HALF_UP );
		BigDecimal amount = transferAmount.setScale ( 2, RoundingMode.HALF_UP );
		String iuv = BusinessUtil.fixIUV ( paGetPaymentReq.getQrCode ().getNoticeNumber () );
		data.setCreditorReferenceId ( iuv );
        if ( null != mappaTea.get ( CostantiNodoSpc.TEA_DUE_DATE ) ) {
            try {
                GregorianCalendar gc = new GregorianCalendar ();
                gc.setTime ( df.parse ( mappaTea.get ( CostantiNodoSpc.TEA_DUE_DATE ) ) );
                data.setDueDate ( DatatypeFactory.newInstance ()
                    .newXMLGregorianCalendar ( gc ) );
            } catch ( ParseException e ) {
                // non dovrebbe capitare, gia' parsificata
                logger.error ( "Errore ParseException", e );
            } catch ( DatatypeConfigurationException e ) {
                logger.error ( "Errore DatatypeConfigurationException", e );
            }
        }
        else {
            try {
                data.setDueDate ( DatatypeFactory.newInstance ().newXMLGregorianCalendarDate ( 2201, 12, 31, DatatypeConstants.FIELD_UNDEFINED ) );
            } catch ( DatatypeConfigurationException e ) {
                logger.error ( "Errore DatatypeConfigurationException", e );
            }
        }
		String causaleVersamento;
		if ( null != mappaTea.get ( CostantiNodoSpc.TEA_DESCRIZIONE_CAUSALE_VERSAMENTO ) ) {
			causaleVersamento = CostantiNodoSpc.TEA_DESCRIZIONE_CAUSALE_VERSAMENTO;
		} else {
			causaleVersamento = CostantiNodoSpc.TEA_CAUSALE_VERSAMENTO;
		}
		causaleVersamento = BusinessUtil.componiCausaleVersamento ( iuv, amount.doubleValue (), mappaTea.get ( causaleVersamento ) );

		data.setDescription ( causaleVersamento );
        data.setCompanyName ( getValueByNameApplicationCustomField ( applicationcustomfields,
            ApplicationcustomfieldsEnum.DENOMINZIONEBENEFICIARIO.getCodice () ) );
		String officeName = getValueByNameApplicationCustomField ( applicationcustomfields, ApplicationcustomfieldsEnum.STABILIMENTO.getCodice () );
		if ( !org.apache.commons.lang3.StringUtils.isEmpty ( officeName ) ) {
			data.setOfficeName ( officeName );
		}
        response.setData ( data );

        CtSubject debtor = new CtSubject ();
        CtEntityUniqueIdentifier uniqueIdentifier = new CtEntityUniqueIdentifier ();
        uniqueIdentifier.setEntityUniqueIdentifierType (
            StEntityUniqueIdentifierType.fromValue ( mappaTea.get ( CostantiNodoSpc.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE ) ) );
        uniqueIdentifier.setEntityUniqueIdentifierValue ( mappaTea.get ( CostantiNodoSpc.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE ) );
        debtor.setUniqueIdentifier ( uniqueIdentifier );
        debtor.setFullName ( mappaTea.get ( CostantiNodoSpc.TEA_ANAGRAFICA_PAGATORE ) );
        debtor.setStreetName ( mappaTea.get ( CostantiNodoSpc.TEA_INDIRIZZO_PAGATORE ) );
        debtor.setCivicNumber ( mappaTea.get ( CostantiNodoSpc.TEA_CIVICO_PAGATORE ) );
        debtor.setPostalCode ( mappaTea.get ( CostantiNodoSpc.TEA_CAP_PAGATORE ) );
        debtor.setCity ( mappaTea.get ( CostantiNodoSpc.TEA_LOCALITA_PAGATORE ) );
        debtor.setStateProvinceRegion ( mappaTea.get ( CostantiNodoSpc.TEA_PROVINCIA_PAGATORE ) );
        debtor.setCountry ( mappaTea.get ( CostantiNodoSpc.TEA_NAZIONE_PAGATORE ) );
        debtor.setEMail ( mappaTea.get ( CostantiNodoSpc.TEA_MAIL_PAGATORE ) );
        data.setDebtor ( debtor );
        int numSingoliVersamenti;

        if ( null != elencoDatiVersamento ) {
            data.setTransferList ( createTransferListMultibeneficiario ( applicationcustomfields, elencoDatiVersamento, mappaTea, paGetPaymentReq, iuv, keyDb ) );
        } else {
            numSingoliVersamenti = 1;
            data.setTransferList ( createTransferList ( applicationcustomfields, mappaTea, numSingoliVersamenti, transferAmount, paGetPaymentReq, iuv, keyDb ) );
        }
        data.setPaymentAmount ( amount );
        data.setMetadata ( null != metadata ? fromMetadataToCtMetadata ( metadata ) : null );
        return response;
    }

	/**
	 * Metodo che costruisce la lista di transfer per multibeneficiario == FALSE
	 */
	private CtTransferListPA createTransferList ( List<Applicationcustomfield> applicationcustomfields, Map<String, String> mappaTea, int numSingoliVersamenti,
					BigDecimal transferAmount, PaGetPaymentReq paGetPaymentReq, String iuv, String keyDb ) throws GetPaymentException {
		CtTransferListPA retValue = new CtTransferListPA ();
		List<CtTransferPA> transferList = retValue.getTransfer ();

		String firstIBAN = getValueByNameApplicationCustomField ( applicationcustomfields, ApplicationcustomfieldsEnum.IBANACCREDITO.getCodice () );
		BigDecimal totale = new BigDecimal( 0 );
		for ( int i = 0; i < numSingoliVersamenti; i++ ) {
			CtTransferPA t = new CtTransferPA ();
			t.setIdTransfer ( i + 1 );
			totale = totale.add ( transferAmount );
			t.setTransferAmount ( totale );
			t.setFiscalCodePA ( getValueByNameApplicationCustomField ( applicationcustomfields, ApplicationcustomfieldsEnum.IDENTIFICATIVODOMINIO.getCodice () ) );
			t.setIBAN ( firstIBAN );

			String causaleVersamento;
			if ( null != mappaTea.get ( CostantiNodoSpc.TEA_DESCRIZIONE_CAUSALE_VERSAMENTO ) ) {
				causaleVersamento = CostantiNodoSpc.TEA_DESCRIZIONE_CAUSALE_VERSAMENTO;
			} else {
				causaleVersamento = CostantiNodoSpc.TEA_CAUSALE_VERSAMENTO;
			}
			causaleVersamento = BusinessUtil.componiCausaleVersamento ( iuv, totale.doubleValue (), mappaTea.get ( causaleVersamento ) );

			t.setRemittanceInformation ( causaleVersamento );
			String datiSpecifici = mappaTea.get ( CostantiNodoSpc.TEA_DATI_SPECIFICI_RISCOSSIONE );
			if ( null == datiSpecifici ) {
				datiSpecifici = getValueByNameApplicationCustomField ( applicationcustomfields, ApplicationcustomfieldsEnum.DATISPECIFICIRISCOSSIONE.getCodice () );
			}
			t.setTransferCategory ( datiSpecifici );

			if ( null != paGetPaymentReq.getTransferType () && paGetPaymentReq.getTransferType ().value ().equalsIgnoreCase ( POSTAL ) ) {
				if ( gotPostalIban ( applicationcustomfields , keyDb, t) ) {
					if ( i == numSingoliVersamenti - 1 ) {
						transferList.add ( t );
					}
				}
			} else {
				if ( i == numSingoliVersamenti - 1 ) {
					transferList.add ( t );
				}
			}
		}

		checkPostalException ( paGetPaymentReq, transferList );

		return retValue;
	}

    /**
     * Metodo che costruisce la lista di transfer per multibeneficiario == TRUE
     *
     */
	private CtTransferListPA createTransferListMultibeneficiario ( List<Applicationcustomfield> applicationcustomfields, ElencoDatiVersamento elencoDatiVersamento,
					Map<String, String> mappaTea, PaGetPaymentReq paGetPaymentReq, String iuv, String keyDb ) throws GetPaymentException {
		CtTransferListPA retValue = new CtTransferListPA ();
		List<CtTransferPA> transferList = retValue.getTransfer ();

		for ( int i = 0; i < elencoDatiVersamento.getDatiSingoloVersamento ().size (); i++ ) {
			DatiSingoloVersamentoRPT elem = elencoDatiVersamento.getDatiSingoloVersamento ().get ( i );
			CtTransferPA t = new CtTransferPA ();
			t.setIdTransfer ( i + 1 );
			Application application = applicationService.isExists ( elem.getApplicationId () );
			if ( null == application ) {
				//prendo l'applicationId dell'elemento iesimo del blocco singoli versamento
				application = applicationcustomfields.get ( 0 ).getApplication ();
			}
			t.setTransferAmount ( elem.getImportoSingoloVersamento ().setScale ( 2, RoundingMode.HALF_UP ) );

			// verificare - FiscalCodePA dovra' essere salvato nella tabella mdp_singolo_transfer.fiscal_codepa che e' lungo 35, quindi si staffa a 35
			List<Applicationcustomfield> foundAppList = applicationCustomFieldRepository.findAllByApplicationEnabled ( application.getId () );
			String identificativoDominio = getValueByNameApplicationCustomField ( foundAppList, ApplicationcustomfieldsEnum.IDENTIFICATIVODOMINIO.getCodice () );
			t.setFiscalCodePA ( StringUtils.hasText ( identificativoDominio ) ? identificativoDominio : null );

			t.setIBAN ( getValueByNameApplicationCustomField ( foundAppList , ApplicationcustomfieldsEnum.IBANACCREDITO.getCodice () ) );

			String causale = StringUtils.hasText ( elem.getCausaleVersamento () ) ? elem.getCausaleVersamento () : mappaTea.get ( CostantiNodoSpc.TEA_CAUSALE_VERSAMENTO );
			causale = BusinessUtil.componiCausaleVersamento ( iuv, t.getTransferAmount ().doubleValue (), causale );

			t.setRemittanceInformation ( causale );
			String datiSpecifici = mappaTea.get ( CostantiNodoSpc.TEA_DATI_SPECIFICI_RISCOSSIONE );
			if ( null == datiSpecifici ) {
				datiSpecifici = getValueByNameApplicationCustomField ( applicationcustomfields, ApplicationcustomfieldsEnum.DATISPECIFICIRISCOSSIONE.getCodice () );
			}
			t.setTransferCategory ( StringUtils.hasText ( elem.getDatiSpecificiRiscossione () ) ? elem.getDatiSpecificiRiscossione () : datiSpecifici );

			if ( null != paGetPaymentReq.getTransferType () && paGetPaymentReq.getTransferType ().value ().equalsIgnoreCase ( POSTAL ) ) {
				if ( gotPostalIban ( applicationcustomfields, keyDb, t ) ) {
					transferList.add ( t );
				}
			} else {
				transferList.add ( t );
			}
		}

		String causaleVersamento;
		if ( null != mappaTea.get ( CostantiNodoSpc.TEA_DESCRIZIONE_CAUSALE_VERSAMENTO ) ) {
			causaleVersamento = CostantiNodoSpc.TEA_DESCRIZIONE_CAUSALE_VERSAMENTO;
		} else {
			causaleVersamento = CostantiNodoSpc.TEA_CAUSALE_VERSAMENTO;
		}

		checkPostalException ( paGetPaymentReq, transferList );

		Map<String, CtTransferPA> transfersByIBAN = transferList.stream ()
						.collect ( Collectors.toMap ( new Function<CtTransferPA, String> () {

														  @Override
														  public String apply ( CtTransferPA t ) {
															  return t.getIBAN ();
														  }
													  }, Function.identity (),
										new BinaryOperator<CtTransferPA> () {

											@Override
											public CtTransferPA apply ( CtTransferPA b1, CtTransferPA b2 ) {
												b1.addAmount ( b2 );
												String causale = BusinessUtil.componiCausaleVersamento ( iuv, b1.getTransferAmount ().doubleValue (), mappaTea.get ( causaleVersamento ) );
												b1.setRemittanceInformation ( causale );
												return b1;
											}
										} ) );

		transferList = new ArrayList<> ( transfersByIBAN.values () );
		// riordinare prima di ritornare la lista, e forzare gli indici in maniera progressiva
		transferList.sort ( new Comparator<CtTransferPA> () {

			@Override
			public int compare ( CtTransferPA c1, CtTransferPA c2 ) {
				return Integer.compare ( c1.getIdTransfer (), c2.getIdTransfer () );
			}
		} );

		for ( int i = 0; i < transferList.size (); i++ ) {
			transferList.get ( i ).setIdTransfer ( i + 1 );
		}

		retValue.setTransfer ( transferList );

		return retValue;
	}

	private void checkPostalException ( PaGetPaymentReq req, List<CtTransferPA> transferList ) throws GetPaymentException {
		if ( null != req.getTransferType () && req.getTransferType ().value ().equalsIgnoreCase ( POSTAL ) && transferList.isEmpty () ) {
			throw new GetPaymentException ( CtFaultBean.builder ()
					.withFaultCode ( FaultBeanEnum.PAA_SYSTEM_ERROR.name () )
					.withDescription ( FaultBeanEnum.PAA_TROVATO_POSTAL_CODE_MA_NESSUN_IBAN_ASSOCIATO.getDescription () )
					.withId ( req.getIdPA () )
					.withFaultString ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
					.build () );
		}
	}

	private boolean gotPostalIban ( List<Applicationcustomfield> applicationcustomfields,String keyDb, CtTransferPA transfer ) {
		ConfigDTO config = configService.getConfig ( Constants.NUOVO_MODELLO_3_IDENTIFICATIVO_CONTI_POSTALI );
		if ( null == config || org.apache.commons.lang3.StringUtils.isEmpty ( config.getValue ()  ) ) {
			logger.error ( "Errore PaGetPayment: assenza parametro di configurazione sul db: " + Constants.NUOVO_MODELLO_3_IDENTIFICATIVO_CONTI_POSTALI );
			return false;
		}
//			assert config != null;
		Assert.notNull ( config, "Request non valorizzata!" );
		String [] parts = config.getValue ().split ( "," );
		for ( String part: parts ) {
			for ( Applicationcustomfield appl: applicationcustomfields ) {
				if ( null != appl.getFieldname ()
						&& ( appl.getFieldname ().equals ( Constants.IBAN_ACCREDITO ) || appl.getFieldname ().equals ( Constants.IBAN_APPOGGIO ) )
						&& null != appl.getFieldvalue ()
						&& EncryptionDecryptionUtil.decrypt ( appl.getFieldvalue (), keyDb ).contains ( part) )
				{
					if(appl.getFieldname ().equals ( Constants.IBAN_ACCREDITO )) {
						transfer.setIBAN ( getValueByNameApplicationCustomField ( applicationcustomfields , ApplicationcustomfieldsEnum.IBANACCREDITO.getCodice () ) );
					}else {
						transfer.setIBAN ( getValueByNameApplicationCustomField ( applicationcustomfields , ApplicationcustomfieldsEnum.IBANAPPOGGIO.getCodice () ) );
					}
					return true;
				}
			}
		}
		return false;
	}
    /**
     * creazione entity per scrittura su db del MdpSingoloTransfer
     *
     */
	private MdpSingoloTransfer createMdpSingoloTransfer ( CtTransferPA t, Integer pagopaIdtransfer ) {
		return MdpSingoloTransfer.builder ()
						.withDataOraInsert ( new Date ( System.currentTimeMillis () ) )
						.withFiscalCodepa ( t.getFiscalCodePA () )
						.withIban ( t.getIBAN () )
						.withIdGetpayment ( pagopaIdtransfer )
						.withPagopaIdtransfer ( t.getIdTransfer () + "" )
						.withTransferAmount ( t.getTransferAmount ().toString () )
						.withTransferCategory ( t.getTransferCategory () )
						.build ();
	}

    /**
     * creazione entity per scrittura su db del DatiSingoloVersamento
     */
	private DatiSingoloVersamento createDatiSingoloVersamento ( ElementoMultiversamento elementoMultiversamento,
					DatiSingoloVersamentoRPT d, int posizione, String teaCodiceIdentificativoUnivocoPagatore, IuvOttici iuvOttici ) {
		//vedere che manca la marca da bollo
		return DatiSingoloVersamento.builder ()
						.withPosizione ( posizione )
						.withImportosingoloversamento ( d.getImportoSingoloVersamento () )
						.withCredenzialipagatore ( teaCodiceIdentificativoUnivocoPagatore )
						.withCausaleversamento ( d.getCausaleVersamento () )
						.withDatispecificiriscossione ( d.getDatiSpecificiRiscossione () )
						.withMultiId ( elementoMultiversamento.getId () )
						.withApplicationid ( d.getApplicationId () == null ? iuvOttici.getApplication ().getId () : d.getApplicationId () )
						.withAnnoaccertamento ( d.getDatiAccertamento () != null ? d.getDatiAccertamento ().getAnnoAccertamento () : null )
						.withNumeroaccertamento ( d.getDatiAccertamento () != null ? d.getDatiAccertamento ().getNumeroAccertamento () : null )
						.build ();
	}

    /**
     * creazione entity per scrittura su db del ElementoMultiversamento
     *
     */
	private ElementoMultiversamento createElementoMultiversamento ( IuvOttici iuvOttici, String transactionId, String multiversamentoXml ) {
		if ( null != multiversamentoXml ) {
			multiversamentoXml = new String ( Base64.decodeBase64 ( multiversamentoXml ) );
		}
		return ElementoMultiversamento.builder ()
						.withApplicationId ( iuvOttici.getApplication ().getId () )
						.withIuv ( iuvOttici.getIuvOttico () )
						.withTransactionId ( transactionId )
						.withPosizione ( 0 )
						.withXmlElemento ( multiversamentoXml )
						.withModellopagamento ( "3" )
						.withInsertDate ( new Timestamp ( System.currentTimeMillis () ) )
						.build ();
	}

    /**
     * creazione entity per scrittura su db del MdpGetpayment
     *
     */
    private MdpGetpayment createMdpGetpayment ( PaGetPaymentRes paGetPaymentRes, IuvOttici iuvOttici, String transactionId,
        ElementoMultiversamento elementoMultiversamento, PaGetPaymentReq paGetPaymentReq ) {
        StringWriter sw = new StringWriter ();
        JAXB.marshal ( paGetPaymentRes, sw );
        byte [] encodedBytes = Base64.encodeBase64 ( sw.toString ().getBytes () );

        return MdpGetpayment.builder ()
                        .withApplicationId (iuvOttici.getApplication ().getId ()  )
                        .withCreditorReferenceid ( iuvOttici.getIuvOttico () )
                        .withDataOraInsert (  new Date ( ) )
                        .withDataOraInvio ( new Date ()  )
                        .withDescription ( paGetPaymentRes.getData ().getDescription () )
                        .withDueDate (
                            null != paGetPaymentRes.getData ().getDueDate () ? paGetPaymentRes.getData ().getDueDate ().toGregorianCalendar ().getTime () : null )
                        .withIdStatoGetpayment ( String.valueOf ( Constants.GETPAYMENT_INVIATO_OK ) )
                        .withPaymentAmount ( paGetPaymentRes.getData ().getPaymentAmount () )
                        .withTransactionId (transactionId )
                        .withXmlGetpayment ( encodedBytes )
                        .withIdMultiversamento (elementoMultiversamento.getId())
		        .withIdPa ( paGetPaymentReq.getIdPA ( ) )
		        .withIdBrokerPa ( paGetPaymentReq.getIdBrokerPA ( ) )
		        .withIdStation ( paGetPaymentReq.getIdStation ( ) )
                        .build ();
    }

    /**
     * Esegue i controlli formali sui parametri passati dal fruitore collezionando gli eventuali errori per una successiva gestione nei confronti del fruitore.
     *
     * @return Elenco con gli errori, vuoto se non ce ne sono stati
     */
    private List<String> controlliFormali ( Map<String, String> mappaTea, List<Applicationcustomfield> customFields ) {
        List<String> elencoErrori = new ErroriList<> ();

        if ( mappaTea.get ( CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO ) == null ) {
            elencoErrori.add ( "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE AUTENTICAZIONE SOGGETTO OBBLIGATORIO" );
        } else if ( !"CNS".equals ( mappaTea.get ( CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO ) ) &&
                        !"USR".equals ( mappaTea.get ( CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO ) ) &&
                        !"OTH".equals ( mappaTea.get ( CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO ) ) &&
                        !"N/A".equals ( mappaTea.get ( CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO ) ) ) {
            elencoErrori.add ( "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE AUTENTICAZIONE SOGGETTO DEVE AVERE UNO DEI SEGUENTI VALORI: CNS, USR, OTH, N/A " );
        }

        if ( mappaTea.get ( CostantiNodoSpc.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE ) == null ) {
            elencoErrori.add ( "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE TIPO IDENTIFICATIVO OBBLIGATORIO" );
        } else if ( !"G".equals ( mappaTea.get ( CostantiNodoSpc.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE ) ) &&
                        !"F".equals ( mappaTea.get ( CostantiNodoSpc.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE ) ) ) {
            elencoErrori.add ( "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE TIPO IDENTIFICATIVO DEVE AVERE UNO DEI SEGUENTI VALORI: F o G " );
        }

        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE ), true, 16,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CODICE IDENTIFICATIVO UNIVOCO PAGATORE" ) );

        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_ANAGRAFICA_PAGATORE ), true, 70,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE ANAGRAFICA PAGATORE" ) );

        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_INDIRIZZO_PAGATORE ), false, 70,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE INDIRIZZO PAGATORE" ) );

        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_CIVICO_PAGATORE ), false, 16,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CIVICO PAGATORE" ) );

        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_CAP_PAGATORE ), false, 16,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CAP PAGATORE" ) );

        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_LOCALITA_PAGATORE ), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE LOCALITA' PAGATORE" ) );

        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_PROVINCIA_PAGATORE ), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE PROVINCIA PAGATORE" ) );

        // cntrollo formale che sia una mail
        if ( mappaTea.get ( CostantiNodoSpc.TEA_MAIL_PAGATORE ) != null ) {
            //String expressionPlus = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";       //validazione presa da internet
            String expressionPlus = "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*"; //validazione presa da analisi CDU

            Pattern pPlus = Pattern.compile ( expressionPlus, Pattern.CASE_INSENSITIVE );
            Matcher mPlus = pPlus.matcher ( mappaTea.get ( CostantiNodoSpc.TEA_MAIL_PAGATORE ) );
            boolean matchFoundPlus = mPlus.matches ();
            if ( !matchFoundPlus ) {
	            elencoErrori.add("PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE E-MAIL PAGATORE ERRATO");
            }
        }


        elencoErrori.add ( campoErrato ( mappaTea.get ( CostantiNodoSpc.TEA_IDENTIFICATIVO_UNIVOCO_VERSAMENTO ), true, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE IDENTIFICATIVO UNIVOCO VERSAMENTO" ) );

        if ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) != null ) {
            double importoTotaleVerasemente = Double.parseDouble ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) );
            if ( ( importoTotaleVerasemente < 0.00 ) || ( importoTotaleVerasemente > 999999999.99 ) ) {
                elencoErrori.add ( "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE IMPORTO TOTALE DA VERSARE DEVE AVERE VALORI DA 0.00 A 999999999.99" );
            }
        }
        Map<String, String> mappaAppCustomFields = new HashMap<> ();
        for ( Applicationcustomfield appl: customFields ) {
            mappaAppCustomFields.put ( appl.getFieldname (), appl.getFieldvalue () );
        }
        if ( mappaTea.get ( CostantiNodoSpc.TEA_DATI_SPECIFICI_RISCOSSIONE ) == null ) {
            if ( mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE ) == null ) {
                elencoErrori.add ( "PARAMETRO APPLICAZIONE DATI SPECIFICI RISCOSSIONE OBBLIGATORIO" );
            } else if ( !EncryptionDecryptionUtil.decrypt ( mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE ), configurazioneService.getSkeyDb () )
                            .matches ( "[0129]/\\S{3,138}" ) ) {
                elencoErrori.add ( "PARAMETRO APPLICAZIONE DATI SPECIFICI RISCOSSIONE IN FORMATO NON CORRETTO; FORMATO CORRETTO: [0129]{1}/\\S{3,138}" );
            }
        }

        if ( mappaTea.get ( CostantiNodoSpc.TEA_DUE_DATE) != null ) {
            try {
                DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" ); //"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
                df.parse ( mappaTea.get ( CostantiNodoSpc.TEA_DUE_DATE ) );
            } catch ( ParseException e ) {
                logger.error ( "Errore ParseException", e );
                elencoErrori.add ( "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE DUE DATE ERRATO" );
            }
        }

        if ( mappaTea.get ( CostantiNodoSpc.TEA_RETENTION_DATE ) != null ) {
            try {
                DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" ); //"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
                df.parse ( mappaTea.get ( CostantiNodoSpc.TEA_RETENTION_DATE ) );
            } catch ( ParseException e ) {
                logger.error ( "Errore ParseException", e );
                elencoErrori.add ( "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE RETENTION DATE ERRATO" );
            }
        }

        logger.debug ( "ELENCO DEGLI ERRORI: " +  elencoErrori );
        return elencoErrori;
    }

    /**
     * Controlla che il campo rispetti i vincoli di obbligatorieta' e lunghezza massima specificati e compone il messaggio di errore specifico Un campo puo'
     * essere obbligatorio, in quel caso avra' anche una lunghezza massima Un campo puo' essere facoltativo, ma in caso sia specificato la lunghezza massima
     * deve essere rispettata
     *
     * @return null se la validazione e' stata superata, il messaggio specifico se sono stati trovati errori
     */
    private String campoErrato ( String campo, boolean obbligatorio, int lunghezzaCampo, String nomeCampoMessaggio ) {
        if ( obbligatorio && null == campo ) {
	        return nomeCampoMessaggio + " OBBLIGATORIO";
        }
        if ( null != campo && lunghezzaCampo > 0 && campo.length () > lunghezzaCampo ) {
	        return " LUNGHEZZA CAMPO " + campo + "|" + nomeCampoMessaggio + " SUPERA IL LIMITE DI " + lunghezzaCampo;
        }
        return null;
    }

    
    /**
     * metodo che gestisce la parte datiAggiuntivi xml
     *
     */
    private Metadata gestioneDatiAggiuntivi ( String datiAggiuntiviXML )
                    throws PaymentException {
        logger.info ( "Entrato nel metodo gestioneDatiAggiuntivi" );

        Metadata datiAggiuntivi = null;
        if ( StringUtils.hasText ( datiAggiuntiviXML ) ) {
            try {
                Unmarshaller unmarshallerMetaData = jContextMetadata.createUnmarshaller ();
                SchemaFactory sfMetaData = SchemaFactory.newInstance ( "http://www.w3.org/2001/XMLSchema" );
                Source soMetaData = new StreamSource ( this.getClass ().getResourceAsStream ( "/META-INF/Metadata.xsd" ) );
                Schema sMetaData = sfMetaData.newSchema ( soMetaData );
                unmarshallerMetaData.setSchema ( sMetaData );
                
                datiAggiuntiviXML = new String ( Base64.decodeBase64 ( datiAggiuntiviXML ) );
                XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance ();
                XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader ( new ByteArrayInputStream ( datiAggiuntiviXML.getBytes () ) );
                datiAggiuntivi = (Metadata) unmarshallerMetaData.unmarshal ( xmlStreamReader );
            } catch ( Exception e ) {
                logger.error ( "Errore in gestioneDatiAggiuntivi", e );
                throw new GetPaymentException ( CtFaultBean.builder ()
                    .withFaultCode ( FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.name () )
                    .withDescription ( FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.getDescription () )
                    .withFaultString ( FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.getDescription () )
                    .build () );
            } finally {
                logger.info ( "VERIFICA CORRETTEZZA FORMALE gestioneDatiAggiuntivi" );
            }
        }
        return datiAggiuntivi;
    }

    /**
     * metodo che gestisce la parte multiversamento xml
     *
     */
    private ElencoDatiVersamento gestioneMultiversamentoUniversale ( String multiversamentoXml, BigDecimal importoTotaleDaVersare, boolean multibeneficiario,
                                                                     List<Applicationcustomfield> customFields )  throws PaymentException {
        ElencoDatiVersamento verificaCorrettezzaFormale = null;
        logger.info ( "MULTIVERSAMENTO :" + multiversamentoXml );

        if ( StringUtils.hasText(multiversamentoXml)) {
            logger.debug ( "MULTIVERSAMENTO XML BEFORE:" + multiversamentoXml );
            //multiversamentoXml arriva in base 64
            multiversamentoXml = new String ( Base64.decodeBase64 ( multiversamentoXml ) );
            logger.debug ( "MULTIVERSAMENTO XML AFTER:" + multiversamentoXml );

            try {
                Unmarshaller unmarshallerElencoDatiVersamento = jContextElencoDatiVersamento.createUnmarshaller ();
                SchemaFactory sfElencoDatiVersamento = SchemaFactory.newInstance ( "http://www.w3.org/2001/XMLSchema" );
                Source soElencoDatiVersamento = new StreamSource ( this.getClass ().getResourceAsStream ( "/META-INF/MultiVersamento_1_0_0.xsd" ) );
                Schema sElencoDatiVersamento = sfElencoDatiVersamento.newSchema ( soElencoDatiVersamento );
                unmarshallerElencoDatiVersamento.setSchema ( sElencoDatiVersamento );
                XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance ();
                logger.debug ( "xmlInputFactory:" + xmlInputFactory );
                XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader ( new ByteArrayInputStream ( multiversamentoXml.getBytes () ) );
                logger.debug ( "xmlStreamReader:" + xmlStreamReader );
                logger.debug ( "unmarshallerElencoDatiVersamento:" + unmarshallerElencoDatiVersamento );
                verificaCorrettezzaFormale  = (ElencoDatiVersamento) unmarshallerElencoDatiVersamento.unmarshal ( xmlStreamReader );
            } catch (Exception e ) {
            	logger.error("Errore in gestioneMultiversamentoUniversale", e);
                throw new GetPaymentException ( CtFaultBean.builder ()
                    .withFaultCode ( FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.name () )
                    .withDescription ( FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.getDescription () )
                    .withFaultString ( FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.getDescription () )
                    .build () );
            }
            logger.debug ( "VERIFICA CORRETTEZZA FORMALE" );

            BigDecimal tot = BigDecimal.ZERO;
            tot = tot.setScale(2, RoundingMode.HALF_UP);
//	        assert verificaCorrettezzaFormale != null;
	        Assert.notNull ( verificaCorrettezzaFormale, "verificaCorrettezzaFormale non valorizzata!" );
	        int numDatiVersamenti = verificaCorrettezzaFormale.getDatiSingoloVersamento ().size ();

            logger.debug ( "DatiSingoloVersamentoRPT SIZE:" + numDatiVersamenti );

            //se e' mutibeneficiario deve eavere piu di un elemento
            if ( multibeneficiario && numDatiVersamenti <= 1 ) {
                throw new GetPaymentException ( CtFaultBean.builder ()
                    .withFaultCode ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.name () )
                    .withDescription ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.getDescription () )
                    .withFaultString ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.getDescription () )
                    .build () );
            }

            // controllo da verificare.
            Map<String, String> mappaAppCustomFields = new HashMap<> ();
            for ( Applicationcustomfield appl: customFields ) {
                mappaAppCustomFields.put ( appl.getFieldname (), appl.getFieldvalue () );
            }

            for ( DatiSingoloVersamentoRPT elem: verificaCorrettezzaFormale.getDatiSingoloVersamento () ) {
                DatiSingoloVersamentoRPT singoloPag = new DatiSingoloVersamentoRPT ();

                singoloPag.setCausaleVersamento ( elem.getCausaleVersamento () );
                singoloPag.setDatiSpecificiRiscossione ( StringUtils.hasText ( elem.getDatiSpecificiRiscossione () ) ? elem.getDatiSpecificiRiscossione ()
                                : mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE ) );
                singoloPag.setImportoSingoloVersamento ( elem.getImportoSingoloVersamento () );
                tot = tot.add ( elem.getImportoSingoloVersamento () );

                //nel caso di multibeneficiario ci deve essere l'applciationID
                if ( multibeneficiario ) {
                    if ( null == elem.getApplicationId () ) {
                        throw new GetPaymentException ( CtFaultBean.builder ()
                            .withFaultCode ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.name () )
                            .withDescription ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.getDescription () + " - ApplicationID non presente" )
                            .withFaultString ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.getDescription () )
                            .build () );
                    } else {
                        //verificare che l'application id sia esistente
                        if (applicationService.isExists ( elem.getApplicationId () ) == null) {
                            throw new GetPaymentException ( CtFaultBean.builder ()
                                .withFaultCode ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.name () )
                                .withDescription ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.getDescription () + " - ApplicationID non esiste" )
                                .withFaultString ( FaultBeanEnum.PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO.getDescription () )
                                .build () );
                        }
                    }
                }
            } //fine for sui dati singolo versamento

            //gli importi parziali sommati devono coincidere con l'importo totale da versare
            if ( !tot.equals ( importoTotaleDaVersare ) ) {
                throw new GetPaymentException ( CtFaultBean.builder ()
                    .withFaultCode ( FaultBeanEnum.PAA_GETPAYMENT_CONTROLLO_IMPORTI.name () )
                    .withDescription ( FaultBeanEnum.PAA_GETPAYMENT_CONTROLLO_IMPORTI.getDescription () + " - importoTotaleDaVersare " + importoTotaleDaVersare
                        + " - totale singoli versamenti " + tot )
                    .withFaultString ( FaultBeanEnum.PAA_GETPAYMENT_CONTROLLO_IMPORTI.getDescription () )
                    .build () );
            }

        } //fine multiversamentoXml=null

        return verificaCorrettezzaFormale;
    }

    /**
     *
     */
	private it.csi.mdp.mdppagopaapi.util.clientws.ParametriChiediDatiPagamento createRequestChiediDatiPagamento ( String iuv, BigDecimal importoVersamento,
					String passphrase, String transactionId) {
		it.csi.mdp.mdppagopaapi.util.clientws.ParametriChiediDatiPagamento input = new it.csi.mdp.mdppagopaapi.util.clientws.ParametriChiediDatiPagamento ();
		input.setIuv ( iuv );
		input.setImportoVersamento ( importoVersamento.setScale ( 2, RoundingMode.CEILING ) );
		// creazione Timestamp presa da PagamentiTelematiciCCPImpl
		String timestamp;
		SimpleDateFormat sdf = new SimpleDateFormat ( "ddMMyyyy-hh:mm:ss:ms" );
		timestamp = sdf.format ( new Date () );
		input.setTimestamp ( timestamp );

		// creazione Mac presa da PagamentiTelematiciCCPImpl
		input.setMac ( MacUtil.generaMacChiediDatiPagamento ( passphrase, iuv, importoVersamento.setScale ( 2, RoundingMode.CEILING ), timestamp ) );
		input.setTransactionId ( transactionId ); //esempio  TST000000000088543

		return input;
	}

    /**
     * Costruisce la mappa dei TEA preso da PagamentiTelematiciCCPImpl
     */
    private Map<String, String> costruisciMAppaTea ( List<TransazioneExtraAttribute> elencoValori ) {
        Map<String, String> mappaTea = new HashMap<>();
        for ( TransazioneExtraAttribute elem: elencoValori ) {
            mappaTea.put ( elem.getName (), elem.getValue () );
        }
        return mappaTea;
    }

    /**
     * Metodo che stacca una nuova transazioen
     */
    private Transazione initTransazione ( String appId ) {
        String newPrimaryKey = configurazioneService.getConfig ( ParametriApplicativo.ENV_TRANSACTION ).getValue ();

        DecimalFormat df = new DecimalFormat ( "000000000000000" );

        newPrimaryKey = newPrimaryKey + df.format ( transazioneService.getSequenceNextVal () );
        return transazioneService.initTransazione ( Transazione.builder ().withTransactionId ( newPrimaryKey )
            .withApplicationId ( appId )
            .withLanguage ( Constants.LANGUAGE_TRANSACTION )
            .withInitDate ( new Date ( System.currentTimeMillis () ) )
            .withStatoTransazione ( statoTransazioneService.findByCodStato (StatoTransazioneEnum.INITIALIZED.getCodice ()))  //   cod stato = 1 
//            .withStartDate ( new Date ( System.currentTimeMillis () ) ) solo quando diventa stato 3
//            .withGateway ( gatewayService.findByIdGateway ( Constants.GATEWAY_ID_PAGOPA_V2) )  // - sempre quello di pagopa v2 al momeno non inserisco nulla
//            .withPaymentmode ( paymentModeService.findByIdPaymentmode (Constants.PAYMENTMODE_ID_MODELLO3) ) //  - fisso pagamenti modello 3 - al momento non iserisco nulla
//            .withMerchantId ( "" )  // Per ora non lo valorizzo
//            .withAmount ( null ) //- solo quando mi ritorna dal fruitore (dopo la chiedi dati pagamento response)
//            .withChangestatedate (  new Date ( System.currentTimeMillis () ) ) inizialmente non la valorizzo, ma solo quando cambia  stato
            .build () );
        
    }
    
   

    /**
     * Metodo per estrarre i campi dall'application custom fields
     */
    private String getValueByNameApplicationCustomField ( List<Applicationcustomfield> appCustomFields, String nameField ) {
        try {
            Optional<Applicationcustomfield> trovato = appCustomFields.stream ().filter ( new Predicate<Applicationcustomfield> () {

                @Override
                public boolean test ( Applicationcustomfield t ) {

                    return t.getFieldname ().equals ( nameField );
                }
            } ).findFirst ();

            return EncryptionDecryptionUtil.decrypt ( trovato.get ().getFieldvalue (), configurazioneService.getSkeyDb () );
        } catch ( NoSuchElementException e ) {
            logger.error ( "Errore NoSuchElementException in Applicationcustomfield: " + nameField, e );
            return null;
        }

    }

    /**
     * Metodo di grazione riga GDE in caso di errore al primo contollo dello IUV
     */
    private Gde createGde( PaGetPaymentReq paGetPaymentReq, String error, String applicationId, String transactionId, String iuv, boolean isError ) {

        Date dataInvocazioneServizio = new Date ( System.currentTimeMillis () );

        return Gde.builder ()
                        .withInsertDate ( dataInvocazioneServizio )
                        .withDataoraevento ( dataInvocazioneServizio )
                        .withIdentificativodominio ( paGetPaymentReq.getIdPA () )
                        .withIuv ( iuv )
                        .withCodiceContesto ( Constants.NOT_AVAILABLE )
                        .withIdPsp ( Constants.NOT_AVAILABLE )
                        .withTipoversamento ( Constants.NOT_AVAILABLE )
                        .withComponente ( Constants.COMPONENT_NAME )
                        .withCategoriaevento ( Constants.INTERFACCIA )
                        .withTipoevento ( Constants.TIPO_EVENTO_GET_PAYMENT )
                        .withSottotipoevento ( Constants.TIPO_EVENTO_GET_PAYMENT )
                        .withIdentificativofruitore (  paGetPaymentReq.getIdPA () )
                        .withIdentificativoerogatore (  paGetPaymentReq.getIdBrokerPA  () )
                        .withIdentificativostazioneintermediariopa ( paGetPaymentReq.getIdStation () )
                        .withIdIntPsp ( Constants.NOT_AVAILABLE )
                        .withParametrispecificiinterfaccia (isError?error:"" )
                        .withEsito ( isError?Constants.KO:Constants.OK )
                        .withApplicationId ( applicationId != null ? applicationId : Constants.NOT_AVAILABLE )
						.withTransactionid ( transactionId != null ? transactionId : "" )
                        .build ();
    }

    private CtMetadata fromMetadataToCtMetadata ( Metadata metadata ) {
        CtMetadata ctMetadata = new CtMetadata ();
        for ( MapEntry m: metadata.getMapEntry () ) {
            CtMapEntry ctm = new CtMapEntry ();
            ctm.setKey ( m.getKey () );
            ctm.setValue ( m.getValue () );
            ctMetadata.getMapEntry ().add ( ctm );
        }
        return ctMetadata;
    }
}
