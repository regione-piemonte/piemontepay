/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import it.csi.mdp.clientmod3.UnrecoverableException;
import it.csi.mdp.mdppagopaapi.business.ApplicationCustomFieldService;
import it.csi.mdp.mdppagopaapi.business.ConfigurazioneService;
import it.csi.mdp.mdppagopaapi.business.ElementoMultiversamentoService;
import it.csi.mdp.mdppagopaapi.business.GdeService;
import it.csi.mdp.mdppagopaapi.business.GetPaymentService;
import it.csi.mdp.mdppagopaapi.business.IuvOtticiService;
import it.csi.mdp.mdppagopaapi.business.MdpReceiptService;
import it.csi.mdp.mdppagopaapi.business.MdpSingoloTransferService;
import it.csi.mdp.mdppagopaapi.business.PaSendRTBusinessService;
import it.csi.mdp.mdppagopaapi.business.ReceiptCodaInvioService;
import it.csi.mdp.mdppagopaapi.business.TransazioneService;
import it.csi.mdp.mdppagopaapi.dto.exception.PaSendRTException;
import it.csi.mdp.mdppagopaapi.integration.domain.Application;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationcustomfield;
import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamentoLight;
import it.csi.mdp.mdppagopaapi.integration.domain.Gde;
import it.csi.mdp.mdppagopaapi.integration.domain.IuvOttici;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpGetpayment;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpReceipt;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpSingoloTransfer;
import it.csi.mdp.mdppagopaapi.integration.domain.ReceiptCodaInvio;
import it.csi.mdp.mdppagopaapi.integration.domain.ReceiptCodaInvioPK;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtFaultBean;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtMapEntry;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtTransferPA;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTRes;
import it.csi.mdp.mdppagopaapi.pa.pafornode.StOutcome;
import it.csi.mdp.mdppagopaapi.util.ApplicationcustomfieldsEnum;
import it.csi.mdp.mdppagopaapi.util.BusinessUtil;
import it.csi.mdp.mdppagopaapi.util.Constants;
import it.csi.mdp.mdppagopaapi.util.EncryptionDecryptionUtil;
import it.csi.mdp.mdppagopaapi.util.FaultBeanEnum;
import it.csi.mdp.mdppagopaapi.util.MacUtil;
import it.csi.mdp.mdppagopaapi.util.StatoTransazioneEnum;
import it.csi.mdp.mdppagopaapi.util.Utils;
import it.csi.mdp.mdppagopaapi.util.clientws.ChiaveValore;
import it.csi.mdp.mdppagopaapi.util.clientws.Serviziofruitoremdp;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PreDestroy;
import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static it.csi.mdp.mdppagopaapi.util.Constants.KO;
import static it.csi.mdp.mdppagopaapi.util.Constants.OK;


@Service
public class PaSendRTBuisnessServiceImpl implements PaSendRTBusinessService {


    private static final Logger LOGGER = LoggerFactory.getLogger ( PaSendRTBuisnessServiceImpl.class );
    private static final String RECEIPT_GIA_PRESENTE = "Receipt gia' pervenuta - Inviato OK a PagoPA - I dati sono memorizzati sulla tabella MDP_RECIPT - receiptId di riferimento: ";

    @Autowired
    private IuvOtticiService iuvOtticiService;

    @Autowired
    private MdpReceiptService mdpReceiptService;

    @Autowired
    private MdpSingoloTransferService singoloTransferService;

    @Autowired
    private GdeService gdeService;

    @Autowired
    private ElementoMultiversamentoService elemService;

    @Autowired
    private ApplicationCustomFieldService applCustService;

    @Autowired
    private GetPaymentService getPaymentService;

    @Autowired
    private ConfigurazioneService configurazioneService;
    
    @Autowired
    private ReceiptCodaInvioService receiptCodaInvioService;
    
    
    @Autowired
    TransazioneService transazioneService;

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor ( 5, 5, 300, TimeUnit.MILLISECONDS, new LinkedTransferQueue<Runnable> () {

        /**
         *
         */
        private static final long serialVersionUID = 3598312414601757128L;

        @Override
        public boolean offer ( Runnable e ) {
            return tryTransfer ( e );
        }
    } );

    static {
        executor.setRejectedExecutionHandler ( new RejectedExecutionHandler () {

            @Override
            public void rejectedExecution ( Runnable r, ThreadPoolExecutor executor ) {
                LOGGER.info ( "coda piena e max thread raggiunti, aggiungo in coda!" );
                try {
                    executor.getQueue ().put ( r );
                } catch ( InterruptedException e ) {
                    Thread.currentThread ().interrupt ();
                }
            }
        } );
    }

    @PreDestroy
    public void onDestroInterruptThreads () {
        // per terminare quando si chiude il contesto (dentro un metodo Close)
        executor.shutdown ();
        try {
            if ( !executor.awaitTermination ( 5000, TimeUnit.MILLISECONDS ) ) {
                executor.shutdownNow ();
            }
        } catch ( InterruptedException e ) {
            Thread.currentThread ().interrupt ();
        }
    }

    @Override
    @Transactional ( rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW )
    public PaSendRTRes paSendRT ( PaSendRTReq paSendRTReq ) {
    	PaSendRTRes response = new PaSendRTRes();
    	try {
            return excec ( paSendRTReq );
        } catch ( Exception e ) {
            LOGGER.error ( "Errore generico", e);
            String stacktrace = ExceptionUtils.getStackTrace(e);
			String error = "Errore generico: " +stacktrace.substring ( 0, Math.min ( 450, stacktrace.length () ) );
			
            Gde gde = createGde(paSendRTReq, paSendRTReq.getReceipt ().getNoticeNumber (), null, null, error, true);
//            Gde gde = createGdeErrore(paSendRTReq, paSendRTReq.getReceipt ().getNoticeNumber (), paSendRTReq.getIdPA (), false);//::TODO verificare sul gde quale flag va bene
            gdeService.inserisciEventoGiornale(gde);
//            throw e;
            return makeFaultResponse(response, FaultBeanEnum.PAA_SYSTEM_ERROR.name (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription (), 
            		paSendRTReq.getIdPA (), FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription ());
        }
    }

    

	private PaSendRTRes excec(PaSendRTReq paSendRTReq) {
		
		validaInput(paSendRTReq);
		PaSendRTRes response = new PaSendRTRes ();
		//memorizzo il timestamp di ricezione della request
		Date requestDate = new Date(System.currentTimeMillis());
		MdpReceipt mr;
		//recupero lo iuv dalla request (noticeNumber)
		String reqIuv = paSendRTReq.getReceipt().getNoticeNumber();
		reqIuv = BusinessUtil.fixIUV(reqIuv);
		//validazione dei parametri di input
		//non ci sono validazioni dei parametri di input perche tutti obbligatori
		//        validazione di business in base ai dati presenti su DB

		//cerco la receipt associata allo iuv nella tabella mdp_receipt
		MdpReceipt mdpReceipt = mdpReceiptService.findTopByCreditorReferenceIdAndIdPA(reqIuv, paSendRTReq.getIdPA());
		//controllo presenza IUV ottico preliminare (per valorizzare alcuni campi)

		IuvOttici iuvOttici = iuvOtticiService.findTopByIuvOttico(reqIuv);
		String transactionId = transactionIdCheck(paSendRTReq);
		
		
		if (null == iuvOttici) {
		// IUV NON trovato
			//TRACCIARE SU GDE, USARE COME IDENTIFICATIVODOMINIO QUESTO <noticeNumber>324220871157266216</noticeNumber> <fiscalCode>80087670016</fiscalCode>
			LOGGER.info ( "IUV non trovato!" );
			response.setOutcome( StOutcome.OK );

			Gde gde;
			if ( null != mdpReceipt ) {
				//se gia presente, inviare risposta a pagoPA, scrivere errore sul GDE e terminare
				LOGGER.info ( " Receipt gia' presente " );
				gde = createGde ( paSendRTReq, reqIuv, transactionId, null, RECEIPT_GIA_PRESENTE+paSendRTReq.getReceipt ().getReceiptId (), true );
			} else {
				gde = createGde ( paSendRTReq, reqIuv, transactionId, null, null, false );
			}
			gdeService.inserisciEventoGiornale(gde);
			saveAnywayReceipt(paSendRTReq, requestDate); //salvare lo stesso la request
			return response;
		}
		
		if (null != mdpReceipt) {
			//se gia presente, inviare risposta a pagoPA, scrivere errore sul GDE e terminare
			LOGGER.info("Receipt gia' presente");
			response.setOutcome(StOutcome.OK);
			Gde gde = createGde(paSendRTReq, reqIuv, transactionId, null, RECEIPT_GIA_PRESENTE+paSendRTReq.getReceipt ().getReceiptId (), true);
			gdeService.inserisciEventoGiornale(gde);
			return response;
		}
		
		

		//arrivati qui, primo controllo superato
		
		

		boolean eseguito;
		boolean iuvSconosciuto = false;

		eseguito = "OK".equals(paSendRTReq.getReceipt().getOutcome().value());

		ElementoMultiversamentoLight elem = elemService.findByTransactionIdAndIuvLight ( transactionId, iuvOttici.getIuvOttico() );
		String applicationId = iuvOttici.getApplication().getId();
		//scrittura request di input su tabella mdp_receipt

		mr = MdpReceipt.builder()
				.withReceiptId(paSendRTReq.getReceipt().getReceiptId())
				.withCreditorReferenceid(paSendRTReq.getReceipt().getCreditorReferenceId())
				.withNoticeNumber(paSendRTReq.getReceipt().getNoticeNumber())
				.withIuvSconosciuto(iuvSconosciuto)
				.withApplicationId(applicationId)
				.withPagamentoInviato ( eseguito )
				.withPaymentAmount(paSendRTReq.getReceipt().getPaymentAmount())
				// .withXmlReceipt ( encodedBytes )
				.withTransactionId ( transactionId )
				//.withPaymantDatetime ( paSendRTReq.getReceipt ().getPaymentDataTime () )
				.withPaymentDatetime(null != paSendRTReq.getReceipt().getPaymentDateTime()
						? paSendRTReq.getReceipt().getPaymentDateTime().toGregorianCalendar().getTime() : null)
				.withPaymentMethod(
						null != paSendRTReq.getReceipt().getPaymentMethod() ? paSendRTReq.getReceipt().getPaymentMethod() : Constants.NOT_AVAILABLE)
				//.withTransferDate ( paSendRTReq.getReceipt ().getTransferDate () )
				.withTransferDate(
						null != paSendRTReq.getReceipt().getTransferDate() ? paSendRTReq.getReceipt().getTransferDate().toGregorianCalendar().getTime() : null)
				.withIdElementoMultiversamento(null == elem ? null : BigDecimal.valueOf(elem.getId()))
				.withDataOraInsert(new Date(System.currentTimeMillis()))
				.withDataOraUpdate(null) //si valorizzera' dopo
				.withDataOraRicezione(requestDate)
				.withDataInvioFruitore(null)//si valorizzera' dopo
				.withDataOraInvioFallito(null)
				.withDataOraRetry(null)
				.withFeePsp(paSendRTReq.getReceipt().getFee())
				.withIdPsp(paSendRTReq.getReceipt().getIdPSP())
				.withPspComanyName(paSendRTReq.getReceipt().getPSPCompanyName())
				.withApplicationDate(
						null != paSendRTReq.getReceipt().getApplicationDate() ? paSendRTReq.getReceipt().getApplicationDate().toGregorianCalendar().getTime()
								: null)
				.withIdPa(paSendRTReq.getIdPA())
				.withIdBrokerPa(paSendRTReq.getIdBrokerPA())
				.withIdStation(paSendRTReq.getIdStation())
				.build();

		//inserisco record sul GDE (scrittura positiva)
		try {
			LOGGER.info("Scrittura GDE positiva");
			Gde gde = createGde(paSendRTReq, reqIuv, transactionId, applicationId, null, false);
			//Gde gde = createGdePositivo(paSendRTReq, applicationId, transactionId, paSendRTReq.getIdPA (), reqIuv);
			gdeService.inserisciEventoGiornale(gde);
			

//          aggiorno transazione con stato 4 e finish date 
			transazioneService.aggiornaStatoTransazione ( transazioneService.findById ( transactionId ), StatoTransazioneEnum.SUCCESFUL.getCodice () );
			
		} catch (Exception e) {
			LOGGER.warn ( "Errore in fase di inserimento dell'evento sul giornale eventi", e);
		}

		setMdpGetpayment(iuvOttici, mr);

		//recupero di lista application_id da mdp_singolo_versamento
		List<Application> applications = new ArrayList<>();
		applications.add ( iuvOttici.getApplication () );

		//invio risposta a pagoPa e start nuovo thread...
		LOGGER.debug("Ritorno risposta a PAGOPA e continuo in async con i miei controlli");
		//start thread
		final MdpReceipt mr2 = mr;
		final ElementoMultiversamentoLight elem2 = elem;
		executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					threadMethod(paSendRTReq, mr2, elem2, applications,  iuvOttici);
				} catch ( UnrecoverableException e ) {
					throw new RuntimeException ( e );
				} catch ( PaSendRTException e ) {
					throw new RuntimeException ( e );
				}
			}
		});

		//invio response
		response.setOutcome(StOutcome.OK);
		return response;
	}

	private String transactionIdCheck ( PaSendRTReq paSendRTReq ) {
		String transactionId = null;
		if ( null != paSendRTReq.getReceipt ().getMetadata () ) {
			Optional<CtMapEntry> trovato = paSendRTReq.getReceipt ().getMetadata ().getMapEntry ().stream ().filter ( new Predicate<CtMapEntry> () {

				@Override
				public boolean test ( CtMapEntry t ) {
					return Constants.TRANSACTION_ID.equals ( t.getKey () );
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				transactionId = trovato.get ().getValue ();
			}
		}
		return transactionId;
	}
	private void saveAnywayReceipt ( PaSendRTReq paSendRTReq, Date requestDate ) {
		String transactionId = transactionIdCheck ( paSendRTReq );

		MdpReceipt mr = MdpReceipt.builder ()
			.withReceiptId ( paSendRTReq.getReceipt ().getReceiptId () )
			.withCreditorReferenceid ( paSendRTReq.getReceipt ().getCreditorReferenceId () )
			.withNoticeNumber ( paSendRTReq.getReceipt ().getNoticeNumber () )
			.withIuvSconosciuto ( true )
			.withApplicationId ( null )
			.withPagamentoInviato( "OK".equals ( paSendRTReq.getReceipt ().getOutcome ().value () ) )
			.withPaymentAmount ( paSendRTReq.getReceipt ().getPaymentAmount () )
			.withTransactionId ( transactionId ) // anche se puo' essere null, va bene comunque, altrimenti rompe la chiave ERROR: insert or update on table "mdp_receipt" violates foreign key constraint "transaction_id_fk"
			.withPaymentDatetime ( null != paSendRTReq.getReceipt ().getPaymentDateTime ()
							? paSendRTReq.getReceipt ().getPaymentDateTime ().toGregorianCalendar ().getTime () : null )
			.withPaymentMethod (
				null != paSendRTReq.getReceipt ().getPaymentMethod () ? paSendRTReq.getReceipt ().getPaymentMethod () : Constants.NOT_AVAILABLE )
			.withTransferDate (
				null != paSendRTReq.getReceipt ().getTransferDate () ? paSendRTReq.getReceipt ().getTransferDate ().toGregorianCalendar ().getTime () : null )
			.withIdElementoMultiversamento ( null )
			.withDataOraInsert ( new Date ( System.currentTimeMillis () ) )
			.withDataOraUpdate ( null )
			.withDataOraRicezione ( requestDate )
			.withDataInvioFruitore ( null )
			.withDataOraInvioFallito ( null )
			.withDataOraRetry ( null )
			.withFeePsp ( paSendRTReq.getReceipt ().getFee () )
			.withIdPsp ( paSendRTReq.getReceipt ().getIdPSP () )
			.withPspComanyName ( paSendRTReq.getReceipt ().getPSPCompanyName () )
			.withApplicationDate (
				null != paSendRTReq.getReceipt ().getApplicationDate () ? paSendRTReq.getReceipt ().getApplicationDate ().toGregorianCalendar ().getTime ()
								: null )
			.withIdPa ( paSendRTReq.getIdPA () )
			.withIdBrokerPa ( paSendRTReq.getIdBrokerPA () )
			.withIdStation ( paSendRTReq.getIdStation () )
			.build ();

		StringWriter sw = new StringWriter ();
		JAXB.marshal ( paSendRTReq, sw );
		byte [] encodedBytes = Base64.encodeBase64 ( sw.toString ().getBytes () );
		mr.setXmlReceipt ( encodedBytes );

		mr = mdpReceiptService.inserisciRecord ( mr );

		List<CtTransferPA> transferList = paSendRTReq.getReceipt ().getTransferList ().getTransfer ();
		for (CtTransferPA ctTransferPA : transferList) {
			MdpSingoloTransfer dst = MdpSingoloTransfer.builder()
					.withMdpReceipt(mr)
					.withTransferAmount(ctTransferPA.getTransferAmount().toString())
					.withTransferCategory( ctTransferPA.getTransferCategory() )
					.withFiscalCodepa(ctTransferPA.getFiscalCodePA())
					.withIban(ctTransferPA.getIBAN())
					.withDataOraInsert(new Date(System.currentTimeMillis()))
					.withIdGetpayment(null)
					.withPagopaIdtransfer(ctTransferPA.getIdTransfer() + "")
					.build();
			singoloTransferService.inserisciSingoloTransfer(dst);
		}
	}

    private void threadMethod ( PaSendRTReq paSendRTReq, MdpReceipt mr, ElementoMultiversamentoLight elem,
        List<Application> applications, IuvOttici iuvOttici ) throws UnrecoverableException, PaSendRTException {
		try {
			LOGGER.debug ( "Partenza della parte ASYNC" );
			//marshalling spostato nel thread
			StringWriter sw = new StringWriter ();
			JAXB.marshal ( paSendRTReq, sw );
			byte[] encodedBytes = Base64.encodeBase64 ( sw.toString ().getBytes () );
			mr.setXmlReceipt ( encodedBytes );

			Application appl = iuvOttici.getApplication ();
            //aggiornamento mdp_getpayment
            MdpGetpayment gp = setMdpGetpayment ( iuvOttici, mr );
            //aggiornamento mdp_Receipt
            mr.setApplicationId ( appl.getId () );
			if ( null != elem ) {
				mr.setIdElementoMultiversamento ( BigDecimal.valueOf ( elem.getId () ) );
			}
			mr.setDataOraUpdate ( new Date ( System.currentTimeMillis () ) );
			mr.setStatoInvioFruitore ( OK );
			mdpReceiptService.inserisciRecord ( mr );
	         //aggiornare tabella Mdp_singolo_Transfer per ogni elelemento di transfer_list
            List<CtTransferPA> transferList = paSendRTReq.getReceipt ().getTransferList ().getTransfer ();
            for ( CtTransferPA ctTransferPA : transferList ) {
                MdpSingoloTransfer dst = MdpSingoloTransfer.builder ()
                                .withMdpReceipt ( mr )
                                .withTransferAmount ( ctTransferPA.getTransferAmount ().toString () )
                                .withTransferCategory ( ctTransferPA.getTransferCategory () )
                                .withFiscalCodepa ( ctTransferPA.getFiscalCodePA () )
                                .withIban ( ctTransferPA.getIBAN () )
                                .withDataOraInsert ( new Date ( System.currentTimeMillis () ) )
                                .withIdGetpayment ( null ) //inizialmente null, dopo potro' aggiornarlo
                                .withPagopaIdtransfer ( ctTransferPA.getIdTransfer () + "" )
                                .withIdGetpayment ( null != gp ? gp.getIdGetpayment () : null )
                                .build ();
                singoloTransferService.inserisciSingoloTransfer ( dst );
            }
            
            //valorizzazione riceviEsito
			for ( int i = 0; i < applications.size (); i++ ) {
			    List<Applicationcustomfield> applicationcustomfields
                = applCustService.findAllByApplicationInAndFieldnameIs(applications, ApplicationcustomfieldsEnum.ENDPOINTSERVIZINODO.getCodice());
			    Applicationcustomfield applicationcustomfield = applicationcustomfields.get ( i );
			    Assert.notNull ( applicationcustomfield, "applicationcustomfields non valorizzata!" );
				String appid = applications.get ( i ).getId ();
				it.csi.mdp.mdppagopaapi.util.clientws.ParametriRiceviEsito p = valorizzazioneRiceviEsito ( paSendRTReq, appid, mr, iuvOttici );
				mr.setDataInvioFruitore ( new Date ( System.currentTimeMillis () ) );
				mr.setDataOraUpdate ( new Date ( System.currentTimeMillis () ) );
				//chiamata fruitore
				//***************************************************************************
                // si recupera endPoint
                String endPointFruitore = EncryptionDecryptionUtil.decrypt ( applicationcustomfield.getFieldvalue (), configurazioneService.getSkeyDb () );
				//controllo correttezza wsdl
				Assert.notNull ( endPointFruitore, "endPointFruitore non valorizzata!" );
				if ( !endPointFruitore.contains ( Constants.WSDL ) ) {
					endPointFruitore = endPointFruitore.concat ( Constants.WSDL );
				}
//				endPointFruitore = "http://localhost:8080/epaymdpservices/EpayMdpWs?wsdl";
				Serviziofruitoremdp iPagNodo = (Serviziofruitoremdp) Utils.getProxyAPIService ( Serviziofruitoremdp.class, endPointFruitore, null );

				it.csi.mdp.mdppagopaapi.util.clientws.EsitoRiceviEsito datiRiceviEsito;

				try {
//				    ATTENZIONE codice da utilizzare solo in locale per testare il fallimento della richiesta 
//				    if (true)
//				    {
//				        throw new PaSendRTException ( CtFaultBean.builder ()
//                            .withFaultCode ( FaultBeanEnum.PAA_SYSTEM_ERROR.name () )
//                            .withDescription ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
//                            .withId ( paSendRTReq.getIdPA () )
//                            .withFaultString ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
//                            .build () );
//				    }
					datiRiceviEsito = iPagNodo.riceviEsito ( p );
//					datiRiceviEsito.setEsito ( KO );
					
					LOGGER.info ( "riceviEsito.result=" + datiRiceviEsito );
					if ( datiRiceviEsito == null ) {
						LOGGER.error("Esito ricevuto e' NULL" );
						mr.setDataOraInvioFallito ( new Date () );
						mr.setMsgInvioFallito ( "Esito Ricevuto e' null!!!" );
						mr.setStatoInvioFruitore ( KO );
                        insertInReceiptCodaInvio ( mr );
					}else if ( datiRiceviEsito.getEsito ().equalsIgnoreCase ( KO ) ) {
						LOGGER.warn ( "Esito KO da riceviti esito" );
						mr.setDataOraInvioFallito ( new Date () );
//						ATTEZIONE: se il messaggio e' errore PAA_PAGAMENTO_DUPLICATO; IUV 22230908631712985 relativo ad un pagamento gia' eseguito non va inserito nella tabell
						mr.setMsgInvioFallito ( StringUtils.substring ( datiRiceviEsito.getCodiceErrore () + "; " + datiRiceviEsito.getMessaggioErrore (), 0, 250 ) );
						mr.setStatoInvioFruitore ( KO );
//                      TODO Salvataggio su nuova tabella delle code
						if (!Constants.PAA_PAGAMENTO_DUPLICATO.equals ( datiRiceviEsito.getCodiceErrore () ))
						{
						    insertInReceiptCodaInvio ( mr );
						}
					}
				} catch ( Exception e ) {
					mr.setDataOraInvioFallito ( new Date () );
					Throwable cause = e.getCause ();
					String causa = "";
					if (cause != null) {
						causa = cause.getMessage ();
					}
					mr.setMsgInvioFallito ( StringUtils.substring ( e.getMessage () + "; " + causa, 0, 250 ) );
					mr.setStatoInvioFruitore ( KO );
					mdpReceiptService.inserisciRecord ( mr );
					insertInReceiptCodaInvio ( mr );
					LOGGER.error ( "Errore ", e );
//					 L'eccezione non provoca il rollback 
					throw new PaSendRTException ( CtFaultBean.builder ()
									.withFaultCode ( FaultBeanEnum.PAA_SYSTEM_ERROR.name () )
									.withDescription ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
									.withId ( paSendRTReq.getIdPA () )
									.withFaultString ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
									.build () );
				}
				mr.setDataInvioFruitore ( new Date () );
				mdpReceiptService.inserisciRecord ( mr );
				//fine chiamata fruitore
				//*******************************************************************************
			}


        } catch ( Exception e ) {
            LOGGER.error ( "Errore in fase di esecuzione della parte async", e );
			throw e;
        } finally {
            LOGGER.debug ( "Fine della parte ASYNC" );
        }
    }

    private void insertInReceiptCodaInvio ( MdpReceipt mr ) {
        ReceiptCodaInvio coda = new ReceiptCodaInvio ();
        
        ReceiptCodaInvioPK codaPk = new ReceiptCodaInvioPK ();
        codaPk.setIuv ( mr.getCreditorReferenceid () );
        codaPk.setTransactionId ( mr.getTransactionId () );
        coda.setApplicationId ( mr.getApplicationId ());
        coda.setDataInserimento (  new Timestamp ( System.currentTimeMillis () )  );
        coda.setNumGiorniTentativiKo ( 0 );
        coda.setContatoreTentativi ( 0 );
        coda.setId ( codaPk );

        receiptCodaInvioService.insert ( coda );
    }


        private MdpGetpayment setMdpGetpayment ( IuvOttici iuvOttici, MdpReceipt mr ) {
			MdpGetpayment gp = getPaymentService.findByCreditorReferenceidAndTransactionId ( iuvOttici.getIuvOttico (), mr.getTransactionId () );
			if ( null != gp ) { //da flusso deve per forza trovare un record
				gp.setIdStatoGetpayment ( String.valueOf ( Constants.RECEIPT_RICEVUTA ) );
				try {
					getPaymentService.inserisciRecord ( gp );
				} catch ( Exception e ) {
                    //:TODO verificare e gestire meglio questa eccezione
				    LOGGER.warn ( "Errore in fase di aggiornamento della MdpGetpayment: " + gp.getIdGetpayment (), e);
				}
			}
			return gp;
		}

    private Gde createGde ( PaSendRTReq paSendRTReq, String iuvOttici, String transactionId, String applicationId, String error, boolean isError ) {

        Date date = new Date ( System.currentTimeMillis () );
        return Gde.builder ()
                        .withInsertDate ( date )
                        .withLastUpdate ( date )
                        .withDataoraevento ( date )
                        .withIdentificativodominio ( paSendRTReq.getIdPA () )
                        .withIuv ( iuvOttici )
                        .withCodiceContesto ( Constants.NOT_AVAILABLE )
                        .withIdPsp ( Constants.NOT_AVAILABLE )
                        .withTipoversamento ( Constants.NOT_AVAILABLE )
                        .withComponente ( Constants.COMPONENT_NAME )
                        .withCategoriaevento ( Constants.INTERFACCIA )
                        .withTipoevento ( Constants.PA_SEND_RT )
                        .withSottotipoevento ( Constants.PA_SEND_RT )
                        .withIdentificativofruitore ( paSendRTReq.getIdPA () )
                        .withIdentificativoerogatore ( paSendRTReq.getIdBrokerPA () )
                        .withIdentificativostazioneintermediariopa ( paSendRTReq.getIdStation () )
                        .withIdIntPsp ( Constants.NOT_AVAILABLE )
                        .withCanalepagamento ( null )
                        .withParametrispecificiinterfaccia (isError? error:null)
                        .withEsito ( isError ? KO: Constants.OK )
                        .withApplicationId ( applicationId )
                        .withTransactionid ( transactionId )
                        .build ();
    }
    

//	private Gde createGdePositivo ( PaSendRTReq paSendRTReq, String applicationId, String transactionId, String identificativoDominio, String reqIuv ) {
//        return Gde.builder ()
//                        .withInsertDate ( new Date ( System.currentTimeMillis () ) )
//                        .withLastUpdate ( new Date ( System.currentTimeMillis () ) )
//                        .withDataoraevento ( new Date ( System.currentTimeMillis () ) )
//                        .withIdentificativodominio ( identificativoDominio )
//                        .withIuv ( reqIuv )
//                        .withCodiceContesto ( Constants.NOT_AVAILABLE )
//                        .withIdPsp ( Constants.NOT_AVAILABLE )
//                        .withTipoversamento ( Constants.NOT_AVAILABLE )
//                        .withComponente ( Constants.COMPONENT_NAME )
//                        .withCategoriaevento ( Constants.INTERFACCIA )
//                        .withTipoevento ( Constants.PA_SEND_RT )
//                        .withSottotipoevento ( Constants.PA_SEND_RT )
//                        .withIdentificativofruitore ( paSendRTReq.getIdPA () )
//                        .withIdentificativoerogatore ( paSendRTReq.getIdBrokerPA () )
//                        .withIdentificativostazioneintermediariopa ( paSendRTReq.getIdStation () )
//                        .withIdIntPsp ( Constants.NOT_AVAILABLE )
//                        .withCanalepagamento ( null )
//                        .withParametrispecificiinterfaccia ( null )
//                        .withEsito ( Constants.OK )
//                        .withApplicationId ( applicationId )
//                        .withTransactionid ( transactionId )
//                        .build ();
//    }
	

    private it.csi.mdp.mdppagopaapi.util.clientws.ParametriRiceviEsito valorizzazioneRiceviEsito (  PaSendRTReq paSendRTReq, String applicationId, MdpReceipt mr,
        IuvOttici iuvOttici ) {
        it.csi.mdp.mdppagopaapi.util.clientws.ParametriRiceviEsito p = new it.csi.mdp.mdppagopaapi.util.clientws.ParametriRiceviEsito ();
        SimpleDateFormat sdfTimestamp = new SimpleDateFormat ("ddMMyyyy-hh:mm:ss:ms");
        String timestamp = sdfTimestamp.format ( new Date () );
        Applicationcustomfield applicationcustomfield = applCustService.findTopByApplicationAndFieldnameIs ( iuvOttici.getApplication (), ApplicationcustomfieldsEnum.PASSPHRASE.getCodice () );
        Assert.notNull ( applicationcustomfield, "applicationcustomfield non valorizzata!" );
        p.setApplicationId ( applicationId );
        p.setTransactionId ( mr.getTransactionId () );
        GregorianCalendar gregory = new GregorianCalendar ();
        gregory.setTime ( mr.getDataOraRicezione () );
        XMLGregorianCalendar calendar;
        try {
            calendar = DatatypeFactory.newInstance ()
                            .newXMLGregorianCalendar (
                                gregory );
			calendar.setTimezone ( DatatypeConstants.FIELD_UNDEFINED );
            p.setDataOraMsgRicevuta ( calendar );
        } catch ( DatatypeConfigurationException e ) {
            LOGGER.warn ( "Errore in fase di conversione della now per il campo DataOraMsgRicevuta. ", e);
        }

        p.setIuv ( iuvOttici.getIuvOttico () );
        p.setCodEsitoPagamento ( Constants.CODICE_ESITO_PAGAMENTO_MOD_3 );
        p.setDescEsitoPagamento ( Constants.DESC_PAGAMENTO_MOD_3 );
        p.setTimestamp ( timestamp );
        //algoritmo valorizzazione idmsg
        final SecureRandom random = new SecureRandom ();
        String stringa35Random = new BigInteger ( 160, random ).toString ( 32 );
        if ( stringa35Random.length () > 32 ) {
            stringa35Random = stringa35Random.substring ( 32 );
        }
        stringa35Random = "MDP" + stringa35Random;
        p.setIdMsgRicevuta ( stringa35Random );
        
        p.setMac ( MacUtil.generaMacRiceviEsito ( applicationId, EncryptionDecryptionUtil.decrypt ( applicationcustomfield.getFieldvalue (), configurazioneService.getSkeyDb () ), iuvOttici.getIuvOttico (),
            timestamp, stringa35Random ) );
        p.setIdentificativoUnivocoRiscossione ( paSendRTReq.getReceipt ().getReceiptId () );
        p.setImportoPagato ( paSendRTReq.getReceipt ().getPaymentAmount () );

		Map<String, String> map = new ArrayList<CtMapEntry>().stream ().collect ( Collectors.toMap ( new Function<CtMapEntry, String> () {

			@Override
			public String apply ( CtMapEntry t ) {
				return t.getKey ();
			}
		}, new Function<CtMapEntry, String> () {

			@Override
			public String apply ( CtMapEntry t ) {
				return t.getValue ();
			}
		} ) );
        if (null != paSendRTReq.getReceipt ().getMetadata ()) {
			map = paSendRTReq.getReceipt ().getMetadata ().getMapEntry ().stream ().collect ( Collectors.toMap ( new Function<CtMapEntry, String> () {

				@Override
				public String apply ( CtMapEntry t ) {
					return t.getKey ();
				}
			}, new Function<CtMapEntry, String> () {

				@Override
				public String apply ( CtMapEntry t ) {
					return t.getValue ();
				}
			} ) );
		}

        // id_modalita_ricezione_esito corrispondente a 'receipt da pagoPA' se il campo con chiave = FlagReceipt della ElencoParametriAggiuntivi della riceviEsito ha come Valore = Ture
        map.put ( Constants.FLAG_RECEIPT, "true" );
        List<ChiaveValore> listChiaveVal = new ArrayList<> ();
        for ( Map.Entry<String, String> entry: map.entrySet () ) {
            ChiaveValore c = new ChiaveValore ();
            c.setChiave ( entry.getKey () );
            c.setValore ( entry.getValue () );
            listChiaveVal.add ( c );
        }
        if ( null != paSendRTReq.getReceipt ().getPaymentDateTime () ) {
            ChiaveValore c = new ChiaveValore ();
            c.setChiave ( "paymentDateTime" );
            c.setValore ( paSendRTReq.getReceipt ().getPaymentDateTime ().toString () );
            listChiaveVal.add ( c );
        }
        p.setElencoParametriAggiuntivi ( listChiaveVal );
        p.setIdentificativoPSP ( paSendRTReq.getReceipt ().getIdPSP () );
        p.setDenominazionePSP ( paSendRTReq.getReceipt ().getPSPCompanyName () );
        if ( null != mr.getPaymentDatetime () ) {
            p.setDataEsitoSingoloPagamento ( paSendRTReq.getReceipt ().getPaymentDateTime () );
        }
        return p;
    }
    
    private PaSendRTRes makeFaultResponse(PaSendRTRes response, String faultCode, String description, String idPA, String faultString) {
    	response.setFault (  CtFaultBean.builder ()
                .withFaultCode ( faultCode )
                .withDescription (description)
                .withId ( idPA )
				.withFaultString ( faultString)
                .build () );
    	response.setOutcome ( StOutcome.KO );
		return response;
	}

    /**
     * Metodo per validare l'input che arriva da pagopa
     *
     */
    private void validaInput ( PaSendRTReq paSendRTReq ) {
        Assert.notNull ( paSendRTReq, "Request non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt (), "Receipt non valorizzata!" );
        Assert.notNull ( paSendRTReq.getIdStation (), "IdStation non valorizzata!" );
        Assert.notNull ( paSendRTReq.getIdPA (), "IdPA non valorizzata!" );
        Assert.notNull ( paSendRTReq.getIdBrokerPA (), "IdBrokerPA non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt ().getReceiptId (), "ReceiptId non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt ().getNoticeNumber (), "NoticeNumber non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt ().getFiscalCode (), "FiscalCode non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt ().getCreditorReferenceId (), "CreditorReferenceId non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt ().getDebtor (), "Debtor non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt ().getIdChannel (), "IdChanne non valorizzata!" );
        Assert.notNull ( paSendRTReq.getReceipt ().getTransferList (), "TransferList non valorizzata!" );
    }
}
