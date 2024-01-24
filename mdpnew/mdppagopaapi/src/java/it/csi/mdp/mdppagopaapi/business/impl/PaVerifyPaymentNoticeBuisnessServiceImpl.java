/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import it.csi.mdp.clientmod3.UnrecoverableException;
import it.csi.mdp.mdppagopaapi.business.ConfigurazioneService;
import it.csi.mdp.mdppagopaapi.business.GdeService;
import it.csi.mdp.mdppagopaapi.business.IuvOtticiService;
import it.csi.mdp.mdppagopaapi.business.PaVerifyPaymentNoticeBuisnessService;
import it.csi.mdp.mdppagopaapi.dto.exception.PaymentException;
import it.csi.mdp.mdppagopaapi.dto.exception.VerificaDatiPagamentoException;
import it.csi.mdp.mdppagopaapi.dto.exception.VerifyPaymentException;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationcustomfield;
import it.csi.mdp.mdppagopaapi.integration.domain.Gde;
import it.csi.mdp.mdppagopaapi.integration.domain.IuvOttici;
import it.csi.mdp.mdppagopaapi.integration.dto.ConfigDTO;
import it.csi.mdp.mdppagopaapi.integration.repository.ApplicationCustomFieldRepository;
import it.csi.mdp.mdppagopaapi.pa.pafornode.*;
import it.csi.mdp.mdppagopaapi.util.*;
import it.csi.mdp.mdppagopaapi.util.clientws.Serviziofruitoremdp;
import it.csi.mdp.mdppagopaapi.util.clientws.UnrecoverableException_Exception;
import it.csi.mdp.mdppagopaapi.util.clientws.VerificaDatiPagamentoException_Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//CDU di riferimento:EPAY-363-CDU-01-V01-RDI45-paVerifyPayment_bozza (1)_ufficiale.docx


@Service
public class PaVerifyPaymentNoticeBuisnessServiceImpl implements PaVerifyPaymentNoticeBuisnessService {

    protected static final Logger logger = LoggerFactory.getLogger ( PaVerifyPaymentNoticeBuisnessServiceImpl.class );

    @Autowired
    private IuvOtticiService iuvOtticiService;

    @Autowired
    private ApplicationCustomFieldRepository applicationCustomFieldRepository;


    @Autowired
    private GdeService gdeService;

    @Autowired
    private ConfigurazioneService configurazioneService;
    
	@Autowired
	private ConfigServiceImpl configService;

    @Override
    @Transactional ( propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class )
    public PaVerifyPaymentNoticeRes paVerifyPaymentNotice ( PaVerifyPaymentNoticeReq paVerifyPaymentNoticeReq ) {
        try {
            return excec ( paVerifyPaymentNoticeReq );
        } catch ( VerifyPaymentException e ) {
            logger.error ( "Errore VerifyPayment: ", e );
            PaVerifyPaymentNoticeRes response = new PaVerifyPaymentNoticeRes ();
            response.setFault ( e.getCtFault () );
            response.setOutcome ( StOutcome.KO );
            return response;
        } catch ( PaymentException e ) {
            logger.error ( "Errore Payment: ", e );
            PaVerifyPaymentNoticeRes response = new PaVerifyPaymentNoticeRes ();
            response.setFault ( e.getCtFault () );
            response.setOutcome ( StOutcome.KO );
            return response;
        } catch ( Exception e ) {
            logger.error ( "Errore VerifyPayment: ", e );
            PaVerifyPaymentNoticeRes response = new PaVerifyPaymentNoticeRes ();
			response.setFault ( CtFaultBean.builder ()
							.withFaultCode ( FaultBeanEnum.PAA_SYSTEM_ERROR.name () )
							.withDescription ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
							.withFaultString ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
							.withId ( paVerifyPaymentNoticeReq.getIdPA () )
							.build () );
            response.setOutcome ( StOutcome.KO );
            return response;
        }
    }

	private void scriviErroreGDE ( Throwable e, PaVerifyPaymentNoticeReq paVerifyPaymentNoticeReq, IuvOttici iuvOttico ) {
		Date date = new Date ();
		Gde gde = Gde.builder ()
						//.withId ( sequence )
						.withInsertDate ( date )
						.withDataoraevento ( date )
						.withIdentificativodominio ( paVerifyPaymentNoticeReq.getIdPA () )
						.withIuv ( !StringUtils.hasText ( iuvOttico.getIuvOttico () ) ? Constants.NOT_AVAILABLE : iuvOttico.getIuvOttico () )
						.withCodiceContesto ( Constants.NOT_AVAILABLE )
						.withIdPsp ( Constants.NOT_AVAILABLE )
						.withTipoversamento ( Constants.NOT_AVAILABLE )
						.withComponente ( Constants.COMPONENT_NAME )
						.withCategoriaevento ( Constants.INTERFACCIA )
						.withTipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
						.withSottotipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
						.withIdentificativofruitore (
										null == paVerifyPaymentNoticeReq.getIdPA () ? Constants.NOT_AVAILABLE : paVerifyPaymentNoticeReq.getIdPA () )
						.withIdentificativoerogatore (
										null == paVerifyPaymentNoticeReq.getIdBrokerPA () ? Constants.NOT_AVAILABLE :
														paVerifyPaymentNoticeReq.getIdBrokerPA () )
						.withIdentificativostazioneintermediariopa ( paVerifyPaymentNoticeReq.getIdStation () )
						.withIdIntPsp ( Constants.NOT_AVAILABLE )
						.withCanalepagamento ( null )
						.withParametrispecificiinterfaccia ( e.getLocalizedMessage () )
						.withEsito ( Constants.KO )
						.withApplicationId ( iuvOttico.getApplication ().getId () )
						.withTransactionid ( Constants.NOT_AVAILABLE )
						.build ();
		gdeService.inserisciEventoGiornale ( gde );
	}

    private PaVerifyPaymentNoticeRes excec ( PaVerifyPaymentNoticeReq paVerifyPaymentNoticeReq )
                    throws PaymentException, VerificaDatiPagamentoException_Exception {
        //validazione dei parametri di input
        //non ci sono validazioni dei parametri di input perche tutti obbligatori
        //        validazione di business in base ai dati presenti su DB
        // controllo se lo iuv e' presente quindi staccato da noi.
        logger.info ( "PaVerifyPaymentNotice inizio ricerca iuv ottico" );
		IuvOttici iuvOttico = iuvOtticiService.findTopByIuvOttico ( BusinessUtil.fixIUV ( paVerifyPaymentNoticeReq.getQrCode ().getNoticeNumber () ) );
        try {
            if ( null == iuvOttico || iuvOttico.getApplication () == null ) {
				logger.info ( "PaVerifyPaymentNotice Nessuno iuv trovato" );
				String error = FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.name () + ";" + FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getDescription ();
				gdeService.inserisciEventoGiornale ( createGdeErrore ( paVerifyPaymentNoticeReq, error ) );
                throw new VerifyPaymentException ( CtFaultBean.builder ()
                    .withFaultCode ( FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.name () )
                    .withDescription ( FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getDescription () )
                    .withId ( paVerifyPaymentNoticeReq.getIdPA () )
                    .withFaultString ( FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getDescription () )
                    .build () );
            }
            logger.info ( "Trovato iuv: " + iuvOttico.getIuvOttico () );
            /*
             * Test ricerca application attivo
             */

            List<Applicationcustomfield> customFields = applicationCustomFieldRepository.findAllByApplicationEnabled ( iuvOttico.getApplication ().getId () );

            //controllo sull'application field
            // recupero i dati grazie alle relazioni sulle entities
            if ( !CollectionUtils.isEmpty ( customFields ) ) {

                controlliPreliminariDiBusiness ( customFields, iuvOttico, paVerifyPaymentNoticeReq.getIdPA (), paVerifyPaymentNoticeReq.getIdBrokerPA (),
                    paVerifyPaymentNoticeReq.getIdStation (), configurazioneService.getSkeyDb () );
                String endPointFruitore
                    = getValueByNameApplicationCustomField ( customFields,
                        ApplicationcustomfieldsEnum.ENDPOINTSERVIZINODO.getCodice () );
                //String endPointFruitore = "http://localhost:8080/epaymdpservices/EpayMdpWs?wsdl"; //da togliere
                //controllo correttezza wsdl
                if ( !endPointFruitore.contains ( Constants.WSDL ) ) {
                    endPointFruitore = endPointFruitore.concat ( Constants.WSDL );
                }
                //chiamata al fruitore

                //*******************************************************************************************
                try {
                    Serviziofruitoremdp iPagNodo = (Serviziofruitoremdp) Utils.getProxyAPIService(Serviziofruitoremdp.class, endPointFruitore ,null);
                    it.csi.mdp.mdppagopaapi.util.clientws.EsitoVerificaDatiPagamento datiPag;

                    SimpleDateFormat sdfTimestamp = new SimpleDateFormat ( "ddMMyyyy-hh:mm:ss:ms" );
                    it.csi.mdp.mdppagopaapi.util.clientws.ParametriVerificaDatiPagamento parametriVerificaDatiPagamento
                    = new it.csi.mdp.mdppagopaapi.util.clientws.ParametriVerificaDatiPagamento ();
                    parametriVerificaDatiPagamento.setIuv ( iuvOttico.getIuvOttico () );
                    // calcolo mac
                    String passphrase = getValueByNameApplicationCustomField ( customFields, ApplicationcustomfieldsEnum.PASSPHRASE.getCodice () );
                    String timestamp = sdfTimestamp.format ( new Date () );
                    parametriVerificaDatiPagamento.setMac ( MacUtil.generaMacVerificaDatiPagamento ( passphrase, iuvOttico.getIuvOttico (),
                        iuvOttico.getApplication ().getId (), timestamp ) );

                    parametriVerificaDatiPagamento.setTimestamp ( timestamp );
                    //EsitoVerificaDatiPagamento esitoVerificaDatiPagamento = mockService.verificaDatiPagamento ( parametriVerificaDatiPagamento );
                    PaVerifyPaymentNoticeRes response = new PaVerifyPaymentNoticeRes ();
					try {
						datiPag = iPagNodo.verificaDatiPagamento ( parametriVerificaDatiPagamento );
					} catch ( VerificaDatiPagamentoException_Exception e ) {
						logger.error ( "Errore VerificaDatiPagamentoException: ", e );
						// scrittura gde mancata risposta fruitore
						scriviErroreGDE ( e, paVerifyPaymentNoticeReq, iuvOttico );
						throw e;
					} catch ( Exception e ) {
						logger.error ( "Errore Exception: ", e );
						// scrittura gde mancata risposta fruitore
						scriviErroreGDE ( e, paVerifyPaymentNoticeReq, iuvOttico );
						throw e;
					}

                    logger.info ( "chiediDatiPagamento.result=" + datiPag );
                    //controllo se risposta fruitore contiene KO
                    if ( datiPag.getEsito ().equals ( Constants.KO ) ) {
                        //scrittura gde esito fruitore KO
                        Date date = new Date ();
                        Gde gde = Gde.builder ()
                                        .withInsertDate ( date )
                                        .withLastUpdate ( date )
                                        .withDataoraevento ( date )
                                        .withIdentificativodominio ( paVerifyPaymentNoticeReq.getIdPA () )
                                        .withIuv ( iuvOttico.getIuvOttico () )
                                        .withCodiceContesto ( Constants.NOT_AVAILABLE )
                                        .withIdPsp ( Constants.NOT_AVAILABLE )
                                        .withTipoversamento ( Constants.NOT_AVAILABLE )
                                        .withComponente ( Constants.COMPONENT_NAME )
                                        .withCategoriaevento ( Constants.INTERFACCIA )
                                        .withTipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
                                        .withSottotipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
                                        .withIdentificativofruitore (
                                            null == paVerifyPaymentNoticeReq.getIdPA () ? Constants.NOT_AVAILABLE : paVerifyPaymentNoticeReq.getIdPA () )
                                        .withIdentificativoerogatore (
                                            null == paVerifyPaymentNoticeReq.getIdBrokerPA () ? Constants.NOT_AVAILABLE : paVerifyPaymentNoticeReq.getIdBrokerPA () )
                                        .withIdentificativostazioneintermediariopa ( paVerifyPaymentNoticeReq.getIdStation () )
                                        .withIdIntPsp ( Constants.NOT_AVAILABLE )
                                        .withCanalepagamento ( null )
                                        .withParametrispecificiinterfaccia ( "Il fruitore ha rifiutato il pagamento: " + datiPag.getMessaggioErrore () )
                                        .withEsito ( Constants.KO )
                                        .withApplicationId ( iuvOttico.getApplication ().getId () )
                                        .withTransactionid ( Constants.NOT_AVAILABLE )
                                        .build ();
                        gdeService.inserisciEventoGiornale ( gde );
                        throw new VerificaDatiPagamentoException ( CtFaultBean.builder ()
                            .withFaultCode ( datiPag.getCodiceErrore () )
                            .withDescription (
                                "La chiamata al servizio remoto per la verifica del pagamento ha restituito valore nullo relativamente ai dati del seguente pagamento: "
                                                + iuvOttico.getIuvOttico () )
                            .withId ( paVerifyPaymentNoticeReq.getIdPA () )
                            .withFaultString ( datiPag.getMessaggioErrore () )
                            .build () );
                    }
                    response = composeResponseForPaVerifyPaymentOK ( datiPag, customFields );
                    //scrittura gde esito ok
                    Date date = new Date ();
                    Gde gde = Gde.builder ()
                                    //.withId ( sequence )
                                    .withInsertDate ( date )
                                    .withLastUpdate ( date )
                                    .withDataoraevento ( date )
                                    .withIdentificativodominio ( paVerifyPaymentNoticeReq.getIdPA () )
                                    .withIuv ( iuvOttico.getIuvOttico () )
                                    .withCodiceContesto ( Constants.NOT_AVAILABLE )
                                    .withIdPsp ( Constants.NOT_AVAILABLE )
                                    .withTipoversamento ( Constants.NOT_AVAILABLE )
                                    .withComponente ( Constants.COMPONENT_NAME )
                                    .withCategoriaevento ( Constants.INTERFACCIA )
                                    .withTipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
                                    .withSottotipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
                                    .withIdentificativofruitore ( null == paVerifyPaymentNoticeReq.getIdPA () ? Constants.NOT_AVAILABLE : paVerifyPaymentNoticeReq.getIdPA () )
                                    .withIdentificativoerogatore (
                                        null == paVerifyPaymentNoticeReq.getIdBrokerPA () ? Constants.NOT_AVAILABLE : paVerifyPaymentNoticeReq.getIdBrokerPA () )
                                    .withIdentificativostazioneintermediariopa ( paVerifyPaymentNoticeReq.getIdStation () )
                                    .withIdIntPsp ( Constants.NOT_AVAILABLE )
                                    .withCanalepagamento ( null )
                                    .withParametrispecificiinterfaccia ( null )
                                    .withEsito ( Constants.OK )
                                    .withApplicationId ( iuvOttico.getApplication ().getId () )
                                    .withTransactionid ( Constants.NOT_AVAILABLE )
                                    .build ();
                    gdeService.inserisciEventoGiornale ( gde );
                    return response;
                } catch ( UnrecoverableException | UnrecoverableException_Exception e ) {
                    e.printStackTrace ();
                }
            }
        } catch ( PaymentException paymentException ) {
            logger.error ( "Errore PaymentException: ", paymentException );

            //            Date date = new Date ();
            //            Gde gde = Gde.builder ()
            //                            .withInsertDate ( date )
            //                            .withDataoraevento ( date )
            //                            .withIdentificativodominio ( paVerifyPaymentNoticeReq.getIdPA () )
            //                            .withIuv ( null == iuvOttico ? Constants.NOT_AVAILABLE : iuvOttico.getIuvOttico () )
            //                            .withCodiceContesto ( Constants.NOT_AVAILABLE )
            //                            .withIdPsp ( Constants.NOT_AVAILABLE )
            //                            .withTipoversamento ( Constants.NOT_AVAILABLE )
            //                            .withComponente ( Constants.COMPONENT_NAME )
            //                            .withCategoriaevento ( Constants.INTERFACCIA )
            //                            .withTipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
            //                            .withSottotipoevento ( Constants.PA_VERIFY_PAYMENT_NOTICE )
            //                            .withIdentificativofruitore ( null == iuvOttico ? Constants.NOT_AVAILABLE : iuvOttico.getApplication ().getId () )
            //                            // TODO INSERIRE IL VALORE DA APPLICATIONDETAIL
            //                            .withIdentificativoerogatore ( Constants.NOT_AVAILABLE )
            //                            .withIdentificativostazioneintermediariopa ( paVerifyPaymentNoticeReq.getIdBrokerPA () )
            //                            .withIdIntPsp ( Constants.NOT_AVAILABLE )
            //                            .withCanalepagamento ( null )
            //                            .withParametrispecificiinterfaccia ( paymentException.getCtCodiceDescrizione () )
            //                            .withEsito ( Constants.KO )
            //                            .withApplicationId ( null == iuvOttico ? null : iuvOttico.getApplication ().getId () )
            //                            .withTransactionid ( Constants.NOT_AVAILABLE )
            //                            .build ();
            //            gdeService.inserisciEventoGiornale ( gde );
            throw paymentException;
        }

        return null;

    }

	private Gde createGdeErrore ( PaVerifyPaymentNoticeReq paVerifyPaymentNoticeReq, String error ) {

		Date dataInvocazioneServizio = new Date ( System.currentTimeMillis () );
		String iuv = paVerifyPaymentNoticeReq.getQrCode ().getNoticeNumber ().substring ( 1 );

		return Gde.builder ()
			.withInsertDate ( dataInvocazioneServizio )
			.withDataoraevento ( dataInvocazioneServizio )
			.withIdentificativodominio ( paVerifyPaymentNoticeReq.getIdPA () )
			.withIuv ( iuv )
			.withCodiceContesto ( Constants.NOT_AVAILABLE )
			.withIdPsp ( Constants.NOT_AVAILABLE )
			.withTipoversamento ( Constants.NOT_AVAILABLE )
			.withComponente ( Constants.COMPONENT_NAME )
			.withCategoriaevento ( Constants.COMPONENT_NAME )
			.withTipoevento ( Constants.TIPO_EVENTO_GET_PAYMENT )
			.withSottotipoevento ( Constants.TIPO_EVENTO_GET_PAYMENT )
			.withIdentificativofruitore ( paVerifyPaymentNoticeReq.getIdPA () )
			.withIdentificativoerogatore ( paVerifyPaymentNoticeReq.getIdBrokerPA () )
			.withIdentificativostazioneintermediariopa ( paVerifyPaymentNoticeReq.getIdStation () )
			.withIdIntPsp ( Constants.NOT_AVAILABLE )
			.withParametrispecificiinterfaccia ( error )
			.withEsito ( Constants.KO )
			.withApplicationId ( Constants.NOT_AVAILABLE )
			.build ();
	}

    private String getValueByNameApplicationCustomField ( List<Applicationcustomfield> appCustomFields, String nameField ) {
        Optional<Applicationcustomfield> trovato = appCustomFields.stream ().filter ( new Predicate<Applicationcustomfield> () {

            @Override
            public boolean test ( Applicationcustomfield t ) {

                return t.getFieldname ().equals ( nameField );
            }
        } ).findFirst ();

        return EncryptionDecryptionUtil.decrypt ( trovato.get ().getFieldvalue (), configurazioneService.getSkeyDb () );
    }

    private PaVerifyPaymentNoticeRes composeResponseForPaVerifyPaymentOK (
        it.csi.mdp.mdppagopaapi.util.clientws.EsitoVerificaDatiPagamento esitoVerificaDatiPagamento,
					List<Applicationcustomfield> appCustomFields ) {
        boolean found = false;
        PaVerifyPaymentNoticeRes paVerifyPaymentNoticeResponse = new PaVerifyPaymentNoticeRes ();
        paVerifyPaymentNoticeResponse.setOutcome ( StOutcome.OK );
        CtPaymentOptionsDescriptionListPA descriptionList = new CtPaymentOptionsDescriptionListPA ();
        CtPaymentOptionDescriptionPA paymentDescription = new CtPaymentOptionDescriptionPA ();
//        Correzione per importi con decimali diversi da 2
        paymentDescription.setAmount (null!= esitoVerificaDatiPagamento.getImportoDovuto ()? 
                        esitoVerificaDatiPagamento.getImportoDovuto ().setScale (  2, RoundingMode.HALF_UP  ):
                            new BigDecimal ( "0" ).setScale ( 2, RoundingMode.HALF_UP ));
        paymentDescription.setOptions ( StAmountOption.EQ ); //valore di default
        XMLGregorianCalendar dueDate = null;
        try {
            dueDate = DatatypeFactory.newInstance ()
                .newXMLGregorianCalendarDate ( 2201, 12, 31,
                    DatatypeConstants.FIELD_UNDEFINED );
        } catch ( DatatypeConfigurationException e ) {
            logger.error ( "Errore DatatypeConfigurationException", e );
        }
        if ( null != esitoVerificaDatiPagamento.getDataFineValiditaPagamento_0020 () )
            try {
                dueDate = DatatypeFactory.newInstance ()
                    .newXMLGregorianCalendarDate ( esitoVerificaDatiPagamento.getDataFineValiditaPagamento_0020 ().getYear (),
                        esitoVerificaDatiPagamento.getDataFineValiditaPagamento_0020 ().getMonth (),
                        esitoVerificaDatiPagamento.getDataFineValiditaPagamento_0020 ().getDay (),
                        DatatypeConstants.FIELD_UNDEFINED );
            } catch ( DatatypeConfigurationException e1 ) {
                logger.error ( "Errore DatatypeConfigurationException", e1 );
            }

        paymentDescription.setDueDate ( dueDate );

        paymentDescription.setDetailDescription ( esitoVerificaDatiPagamento.getCausaleVersamento () );

        //algoritmo codice contesto pagamento
        //contoPoste
        final String enryptSi = EncryptionDecryptionUtil.encrypt ( "SI", configurazioneService.getSkeyDb () );
        for ( Applicationcustomfield appl: appCustomFields ) {
            if ( null != appl.getFieldname () && appl.getFieldname ().equals ( Constants.CONTO_POSTE ) && null != appl.getFieldvalue ()
                            && appl.getFieldvalue ().equals ( enryptSi ) ) {
                paymentDescription.setAllCCP ( true );
                found = true;
                break;
            }
        }
        if ( Boolean.FALSE.equals ( found ) )
            paymentDescription.setAllCCP ( false );

		// Settaggio del valore del campo allCCP della paVerifyPaymentResponse
		ConfigDTO config = configService.getConfig ( Constants.NUOVO_MODELLO_3_IDENTIFICATIVO_CONTI_POSTALI );
		if ( null != config && config.getValue () != null ) {
			String [] parts = config.getValue ().split ( "," );
			for ( String part: parts ) {
				for ( Applicationcustomfield appl: appCustomFields ) {
					if ( null != appl.getFieldname ()
						&& ( appl.getFieldname ().equals ( Constants.IBAN_ACCREDITO ) || appl.getFieldname ().equals ( Constants.IBAN_APPOGGIO ) )
						&& null != appl.getFieldvalue ()
						&& (EncryptionDecryptionUtil.decrypt(appl.getFieldvalue (), configurazioneService.getSkeyDb ())).contains ( part )) {
						paymentDescription.setAllCCP ( true );
						found = true;
						break;
					}
				}
				if ( Boolean.TRUE.equals ( found ) ) {
					break;
				}
			}
		}
		if ( Boolean.FALSE.equals ( found ) ) {
			paymentDescription.setAllCCP ( false );
		}

        //codiceFiscale
        for ( Applicationcustomfield appl: appCustomFields ) {
            if ( null != appl.getFieldname () && appl.getFieldname ().equals ( Constants.IDENTIFICATIVO_DOMINIO ) ) {
                paVerifyPaymentNoticeResponse.setFiscalCodePA ( EncryptionDecryptionUtil.decrypt ( appl.getFieldvalue (), configurazioneService.getSkeyDb () ) );
                break;
            }
        }
        //denominazioneBeneficiario
        for ( Applicationcustomfield appl: appCustomFields ) {
            if ( null != appl.getFieldname () && appl.getFieldname ().equals ( Constants.DENOMINAZIONE_BENEFICIARIO ) ) {
                paVerifyPaymentNoticeResponse.setCompanyName ( EncryptionDecryptionUtil.decrypt ( appl.getFieldvalue (), configurazioneService.getSkeyDb () ) );
                break;
            }
        }
        //stabilimento
        for ( Applicationcustomfield appl: appCustomFields ) {
            if ( null != appl.getFieldname () && appl.getFieldname ().equals ( Constants.STABILIMENTO ) ) {
				String valore = EncryptionDecryptionUtil.decrypt ( appl.getFieldvalue (), configurazioneService.getSkeyDb () );
				if ( !valore.isEmpty () ) {
					paVerifyPaymentNoticeResponse.setOfficeName (
									EncryptionDecryptionUtil.decrypt ( appl.getFieldvalue (), configurazioneService.getSkeyDb () ) );
				}
				break;
            }
        }
        descriptionList.setPaymentOptionDescription ( paymentDescription );
        paVerifyPaymentNoticeResponse.setPaymentList ( descriptionList );
        paVerifyPaymentNoticeResponse.setPaymentDescription ( paymentDescription.getDetailDescription () );
        return paVerifyPaymentNoticeResponse;
    }
}
