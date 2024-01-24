/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.util.LoggerUtil;
import it.csi.mdp.generatedvo.pagamenti.CtDatiSingoloPagamentoRT;
import it.csi.mdp.generatedvo.pagamenti.CtRicevutaTelematica;
import it.csi.mdp.generatedvo.pagamenti.StTipoIdentificativoUnivocoPersFG;
import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.dto.LoggingRT;
import it.csi.mdp.mdpetl.dto.SingoloPagamentoMultiVersamentoDTO;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaStatoInvioRT;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiConfigurazioneDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiMultiversamentoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRTDaInviareDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciLoggingRTDAO;
import it.csi.mdp.mdpetl.integration.util.dao.StatoInvioRTEnum;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.DatiSingoloPagamentoType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PagamentoIntermediatoType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PagamentoIntermediatoType.DatiSingoliPagamenti;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PersonaFisicaType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PersonaGiuridicaType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.ResponseType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.ServiziRendicontazioneExt;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.SoggettoType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TrasmettiRTExtRequestType;

public class InoltroRT {
	static LogUtil log = new LogUtil(InoltroRT.class);

    private final String KEY_DATA_INVIO_RT = "data.avvio.invio.rt";

    private final String KEY_SOGLIA_GIORNI_RICEZIONE_PAGAMENTO = "soglia.giorni.ricezione.pagamento";

	private JAXBContext contestoJAXBRt = JAXBContext.newInstance(CtRicevutaTelematica.class);
	private Unmarshaller unmarshallerRT = contestoJAXBRt.createUnmarshaller();
	
	public InoltroRT () throws JAXBException {
		contestoJAXBRt = JAXBContext.newInstance(CtRicevutaTelematica.class);
		unmarshallerRT = contestoJAXBRt.createUnmarshaller();
	}
	
    //EstraiConfigurazioneDAO

	public void inoltraRT(String urlEndpointServizio, byte key[]) throws SerialException, SQLException, NamingException, Exception {
		LoggerUtil.begin();
		
		XStream xs = new XStream();
		List<String> elencoIuvNonTrovati = new ArrayList<String>();
        List<RT> elencoRT = reperisciRTPerPArametri ();//FAI COSE GIUSTE
        LoggerUtil.info ( "ELENCO RT ESTRATTE" + elencoRT.size () );
		
        if ( CollectionUtils.isEmpty ( elencoRT ) )
            return;
		
		ServiziRendicontazioneExt iServizio = inizializzaServizioESB(urlEndpointServizio);
		
        Unmarshaller unmarshallerRT = inizializzaUnMarshallerRT ();
		
		int numeroIuvOttici = 0;
		
		//CSI_PAG-502 (2)
		Set<String> iuvTrasmessi = new HashSet<String>();

        for ( RT singolaRT: elencoRT ) {
        	
        	if(iuvTrasmessi.contains(singolaRT.getIuv())) {
        		continue;
        	}
			
			//LF MDPNEW-69 - Sposto dentro al for la dichiarazione dell'importo totale pagamenti intermediati
			BigDecimal importoTotalePagamentiIntermediati = BigDecimal.ZERO;
			
            LoggingRT loggingRT = new LoggingRT ();
            TrasmettiRTExtRequestType trasmettiRT = new TrasmettiRTExtRequestType ();

            StatoInvioRTEnum statoInvioRTEnum = null;
			
			try {
				
                loggingRT.setDataOraInvio ( new Timestamp ( System.currentTimeMillis () ) );
                loggingRT.setIuv ( singolaRT.getIuv () );
                //                logFlusso.setIstitutoMittente ( singolaRT.getDenominazionemittente () ); //:TODO finire valorizzazione campo
				
                LoggerUtil.info ( "\nINIZIO ELABORAZIONE RT NUMERO " + singolaRT.getIuv () );
				
                Set<String> distictCodiciVersamento = new HashSet<String> ();
				
                CtRicevutaTelematica ricevutaTelematica
                    = (CtRicevutaTelematica) unmarshallerRT.unmarshal ( new ByteArrayInputStream ( singolaRT.getRtData () ) );
				
                TestataFlussoRendicontazioneExtType testata = datiTestata ( singolaRT, ricevutaTelematica );
				
                List<PagamentoIntermediatoType> elencoPagamentiIntermediati = new ArrayList<PagamentoIntermediatoType> ( 1 );
				
                TrasmettiRTExtRequestType.PagamentiIntermediati wrapperPagamentiIntermediati = new TrasmettiRTExtRequestType.PagamentiIntermediati ();
				DatiAggiuntiviType datiAggiuntivi = new DatiAggiuntiviType();

                numeroIuvOttici = gestioneSingoliPagamenti ( elencoIuvNonTrovati, numeroIuvOttici, distictCodiciVersamento, ricevutaTelematica,
                    singolaRT.getTransactionId (), elencoPagamentiIntermediati, testata );
				wrapperPagamentiIntermediati.getPagamentoIntermediato().addAll(elencoPagamentiIntermediati);
                trasmettiRT.setPagamentiIntermediati ( wrapperPagamentiIntermediati );
				
				it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType.ElencoCodiciVersamento elencoCodiciVersamento = new it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType.ElencoCodiciVersamento();
				elencoCodiciVersamento.getCodiceVersamento().addAll(distictCodiciVersamento);
				datiAggiuntivi.setElencoCodiciVersamento(elencoCodiciVersamento);
				
				//LF MDPNEW-69
				//testata.setNumeroTotalePagamentiIntermediati(BigInteger.valueOf(elencoPagamentiIntermediati.size()));
				
				for (PagamentoIntermediatoType pagamentoSingolo : elencoPagamentiIntermediati) {
					importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati.add(pagamentoSingolo.getDatiSingoliPagamenti().getDatiSingoloPagamento().getSingoloImportoPagato());
				}
				testata.setImportoTotalePagamentiIntermediati(importoTotalePagamentiIntermediati);
                trasmettiRT.setTestata ( testata );
				
				
                trasmettiRT.setFlussoRT ( singolaRT.getRtData () );
				
                log.info ( "inoltraFlussi", "RISULTATO FLUSSO \n" + xs.toXML ( trasmettiRT ) );
				log.info("inoltraFlussi", "NUMERO IUV TOTALI " + numeroIuvOttici + " NUMERO NON TROVATI " + elencoIuvNonTrovati.size() +  "\nELENCO NON TROVATI " + xs.toXML(elencoIuvNonTrovati));
			
                ResponseType res = iServizio.trasmettiRT ( trasmettiRT );
				
				//LF - 13/12/2018 - il servizio di acquisizione del flusso esteso restituisce 000 quando l'operazione ? andata a buon fine
				if ("000".equalsIgnoreCase(res.getResult().getCodice())) {
//				if ("OK".equalsIgnoreCase(res.getResult().getMessaggio())) {
					//Lorenzo: se l'invio ? avvenuto correttamente aggiorno il relativo stato a INVIATO
                    loggingRT.setEsito(Constants.ESITO_OK);
                    statoInvioRTEnum = StatoInvioRTEnum.INVIATA;
				}
				else {
					
					//Lorenzo: se l'invio e' avvenuto in modo erroneo aggiorno il relativo stato a NON_INVIATO

                    statoInvioRTEnum = StatoInvioRTEnum.ERRORE_INVIO;
                    loggingRT.setEsito(Constants.ESITO_KO);
                    loggingRT.setWarning(res.getResult().getMessaggio());
				}
				
				iuvTrasmessi.add(singolaRT.getIuv());
			} catch (Exception e) {
				log.error("inoltraFlussi", "ERRORE", e);
                loggingRT.setEsito ( Constants.ESITO_KO );
				loggingRT.setErrori(StringUtils.substring(e.getMessage(), 0, 255));
				statoInvioRTEnum = StatoInvioRTEnum.ERRORE_INVIO;
				//Lorenzo: se l'invio ? avvenuto in modo erroneo aggiorno il relativo stato a NON_INVIATO

				
			} finally {
				if (!CollectionUtils.isEmpty(elencoIuvNonTrovati)) {
				    loggingRT.setWarning(StringUtils.substring("IUV NON TROVATI: " + xs.toXML(elencoIuvNonTrovati), 0, 255));
				}
				new InserisciLoggingRTDAO(loggingRT).executeUpdate();
                aggiornaStatoInvioRT ( statoInvioRTEnum, singolaRT );
			}
		}
	}

    private void aggiornaStatoInvioRT ( StatoInvioRTEnum statoInvioRTEnum, RT rt )
                    throws SerialException, NamingException, SQLException, Exception {
        new AggiornaStatoInvioRT ( statoInvioRTEnum, "'" + rt.getIuv () + "'" ).executeUpdate ();
    }

    private int gestioneSingoliPagamenti ( List<String> elencoIuvNonTrovati,
        int numeroIuvOttici, Set<String> distictCodiciVersamento,
        CtRicevutaTelematica ricevuta, String transactionID,
        List<PagamentoIntermediatoType> elencoPagamentiIntermediati, TestataFlussoRendicontazioneExtType testata )
                    throws SerialException, SQLException, NamingException, Exception,
                    JAXBException {

        if ( null == ricevuta.getDatiPagamento () || ricevuta.getDatiPagamento ().getDatiSingoloPagamento ().size () == 0 ) {
            throw new IllegalArgumentException (
                "Sono presenti più pagamenti per la stessa RT. IUV: " + ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento () );
        }

        BigDecimal importoTotale = BigDecimal.ZERO;
        BigInteger numeroTotale = BigInteger.ZERO;

        //LF - MDPNEW-69 - Sposto qua la somma sul numero pagamenti intermediati (non deve tener conto dello spacchettamento dell'importo in piu' componenti) 
        numeroTotale = numeroTotale.add ( BigInteger.valueOf ( ricevuta.getDatiPagamento ().getDatiSingoloPagamento ().size () ) );
        for ( CtDatiSingoloPagamentoRT singoloPagamento: ricevuta.getDatiPagamento ().getDatiSingoloPagamento () ) {
            LoggerUtil.info ( "SINGOLO PAGAMENTO: " + ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento () );


                EstraiMultiversamentoDAO dao2 = new EstraiMultiversamentoDAO ( ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento () );
                List<SingoloPagamentoMultiVersamentoDTO> pag = dao2.executeQuery ();
                XStream xs = new XStream ();
                System.out.println ( "MULTIVERSAMENTI ESTRATTI \n" + xs.toXML ( pag ) );
                // se multiversamento sdoppio le righe. senno' ne costruisco uno solo per riutilizzare lo stasso codice dopo.
                //                if ( pag.size () == 0 ) {
                //                    SingoloPagamentoMultiVersamentoDTO singoloFarlocco = new SingoloPagamentoMultiVersamentoDTO ();
                //                    singoloFarlocco.setDatiSpecificiRiscossione ( richiesta.getDatiVersamento ().getDatiSingoloVersamento ()
                //                        .get ( singoloPagamento.getIndiceDatiSingoloPagamento () - 1 ).getDatiSpecificiRiscossione () );
                //                    singoloFarlocco.setPosizione ( singoloPagamento.getIndiceDatiSingoloPagamento () );
                //                    singoloFarlocco.setCausale ( richiesta.getDatiVersamento ().getDatiSingoloVersamento ()
                //                        .get ( singoloPagamento.getIndiceDatiSingoloPagamento () - 1 ).getCausaleVersamento () );
                //                    singoloFarlocco.setImporto ( singoloPagamento.getSingoloImportoPagato () );
                //                    pag.add ( singoloFarlocco );
                //                }

                for ( SingoloPagamentoMultiVersamentoDTO singoloPagamentoMulti: pag ) {
                    PagamentoIntermediatoType primoLivello = new PagamentoIntermediatoType ();
                    DatiSingoliPagamenti secondoLivello = new DatiSingoliPagamenti ();
                    DatiSingoloPagamentoType terzoLivello = new DatiSingoloPagamentoType ();
                    secondoLivello.setDatiSingoloPagamento ( terzoLivello );
                    terzoLivello.setIUR ( singoloPagamento.getIdentificativoUnivocoRiscossione () );
                    terzoLivello.setIUV ( ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento () );
                    terzoLivello.setTransactionId ( transactionID );

                    terzoLivello.setAnagraficaPagatore ( new SoggettoType () );
                    terzoLivello.getAnagraficaPagatore ().setCAP ( ricevuta.getSoggettoPagatore ().getCapPagatore () );
                    terzoLivello.getAnagraficaPagatore ().setCivico ( ricevuta.getSoggettoPagatore ().getCivicoPagatore () );
                    terzoLivello.getAnagraficaPagatore ().setEMail ( ricevuta.getSoggettoPagatore ().getEMailPagatore () );
                    terzoLivello.getAnagraficaPagatore ()
                        .setIdentificativoUnivocoFiscale ( ricevuta.getSoggettoPagatore ().getIdentificativoUnivocoPagatore ().getCodiceIdentificativoUnivoco () );
                    terzoLivello.getAnagraficaPagatore ().setIndirizzo ( ricevuta.getSoggettoPagatore ().getIndirizzoPagatore () );
                    terzoLivello.getAnagraficaPagatore ().setLocalita ( ricevuta.getSoggettoPagatore ().getLocalitaPagatore () );
                    terzoLivello.getAnagraficaPagatore ().setNazione ( ricevuta.getSoggettoPagatore ().getNazionePagatore () );
                    if ( StTipoIdentificativoUnivocoPersFG.F
                        .equals ( ricevuta.getSoggettoPagatore ().getIdentificativoUnivocoPagatore ().getTipoIdentificativoUnivoco () ) ) {
                        terzoLivello.getAnagraficaPagatore ().setPersonaFisica ( new PersonaFisicaType () );
                        terzoLivello.getAnagraficaPagatore ().getPersonaFisica ().setCognome ( ricevuta.getSoggettoPagatore ().getAnagraficaPagatore () );
                    } else {
                        terzoLivello.getAnagraficaPagatore ().setPersonaGiuridica ( new PersonaGiuridicaType () );
                        terzoLivello.getAnagraficaPagatore ().getPersonaGiuridica ()
                            .setRagioneSociale ( ricevuta.getSoggettoPagatore ().getAnagraficaPagatore () );
                    }
                    terzoLivello.getAnagraficaPagatore ().setProvincia ( ricevuta.getSoggettoPagatore ().getProvinciaPagatore () );
                    terzoLivello.setCodiceEsitoPagamento ( ricevuta.getDatiPagamento ().getCodiceEsitoPagamento () );

                    terzoLivello
                        .setDataEsitoSingoloPagamento ( singoloPagamento.getDataEsitoSingoloPagamento () );
                    terzoLivello.setDescrizioneCausaleVersamento ( singoloPagamento.getCausaleVersamento () );
                    terzoLivello.setIUR ( singoloPagamento.getIdentificativoUnivocoRiscossione () );
                    terzoLivello.setIUV ( ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento () );
                    terzoLivello.setSingoloImportoPagato ( singoloPagamento.getSingoloImportoPagato () );
                    terzoLivello.setTransactionId ( transactionID );
                    
                    
                    primoLivello.setDatiSingoliPagamenti ( secondoLivello );

                    importoTotale = importoTotale.add ( singoloPagamentoMulti.getImporto () );


                    terzoLivello.setCodiceEsitoPagamento ( ricevuta.getDatiPagamento ().getCodiceEsitoPagamento () );
                    terzoLivello.setDataEsitoSingoloPagamento ( singoloPagamento.getDataEsitoSingoloPagamento () );
                    if ( ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento ().length () > 17 ) {
                        terzoLivello.setCodiceVersamento ( ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento ().substring ( 13, 17 ) );
                        distictCodiciVersamento.add ( terzoLivello.getCodiceVersamento () );
                    } else {
                        numeroIuvOttici++;
                        IuvOttico iuvDto
                            = new EstraiApplicationIdDaIuvDAO ( ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento () ).executeQuery ();
                        if ( iuvDto != null ) {
                            terzoLivello.setCodiceVersamento ( iuvDto.getCodiceVersamento () );
                            //CSI_PAG-502 (3.4)
                            distictCodiciVersamento.add(iuvDto.getCodiceVersamento());
                        }else
                            elencoIuvNonTrovati.add ( ricevuta.getDatiPagamento ().getIdentificativoUnivocoVersamento () );
                    }

                    terzoLivello.setDatiSpecificiRiscossione ( singoloPagamentoMulti.getDatiSpecificiRiscossione () );
                    terzoLivello.setDescrizioneCausaleVersamento ( singoloPagamentoMulti.getCausale () );
                    terzoLivello.setIndiceDatiPagamento ( singoloPagamentoMulti.getPosizione () );
                    terzoLivello.setSingoloImportoPagato ( singoloPagamentoMulti.getImporto () );

                    //LF 03/01/2019 Aggiunta anno e numero accertamento (RDI-005)
			        if (singoloPagamentoMulti.getAnnoAccertamento () != null && singoloPagamentoMulti.getAnnoAccertamento () > 0) {
			        	
			        	terzoLivello.setAnnoAccertamento ( singoloPagamentoMulti.getAnnoAccertamento () );
			        }
			        
			        if (null != singoloPagamentoMulti.getNumeroAccertamento() && singoloPagamentoMulti.getNumeroAccertamento() > 0)  {
			        	terzoLivello.setNumeroAccertamento(singoloPagamentoMulti.getNumeroAccertamento());

			        }
                    elencoPagamentiIntermediati.add ( primoLivello );
                }

        }
        testata.setImportoTotalePagamentiIntermediati ( importoTotale );
        testata.setImportoTotalePagamentiFlusso ( importoTotale );
        testata.setNumeroTotalePagamentiIntermediati ( numeroTotale );
        it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento co
            = new it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento ();
        co.getCodiceVersamento ().addAll ( distictCodiciVersamento );
        testata.setElencoCodiciVersamento ( co );
        return numeroIuvOttici;
    }

	private Unmarshaller inizializzaUnMarshallerRT() throws JAXBException,
			SAXException {
		JAXBContext contextFlussoJAXB = JAXBContext.newInstance(CtRicevutaTelematica.class);
		Unmarshaller unmarshallerFlusso = contextFlussoJAXB.createUnmarshaller();
		unmarshallerFlusso.setEventHandler(
			    new ValidationEventHandler() {
					
                    public boolean handleEvent(ValidationEvent event ) {
			            System.out.println("ERRORE DI VALIDAZIONE " + event.getMessage() + " " + event.getSeverity() + " " + event.getLinkedException());
			            return true;
			        }
			});
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Source so = new StreamSource(this.getClass().getResourceAsStream("/PagInf_RPT_RT_6_2_0.xsd"));
		Schema s = sf.newSchema(so);
		
		unmarshallerFlusso.setSchema(s);
		return unmarshallerFlusso;
	}

	private ServiziRendicontazioneExt inizializzaServizioESB(String urlEndpointServizio) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ServiziRendicontazioneExt.class);
		factory.setAddress(urlEndpointServizio);
		ServiziRendicontazioneExt iServizio = (ServiziRendicontazioneExt) factory.create();
		return iServizio;
	}

	private TestataFlussoRendicontazioneExtType datiTestata(
			RT singoloRT, CtRicevutaTelematica ricevuta) {
		TestataFlussoRendicontazioneExtType testata = new TestataFlussoRendicontazioneExtType();
		
        testata.setIdMessaggio ( singoloRT.getIdMsgRicevuta () );
        testata.setIdPSP ( ( null != ricevuta.getIstitutoAttestante () &&
            null != ricevuta.getIstitutoAttestante ().getIdentificativoUnivocoAttestante () &&
            StringUtils.isNotEmpty ( ricevuta.getIstitutoAttestante ().getIdentificativoUnivocoAttestante ().getCodiceIdentificativoUnivoco () ) )
                            ? ricevuta.getIstitutoAttestante ().getIdentificativoUnivocoAttestante ().getCodiceIdentificativoUnivoco () : "ND" );
		
		/* DATI TESTATA */
//        ricevuta.get
//        singoloRT.get
        testata.setDataOraMessaggio ( ricevuta.getDataOraMessaggioRicevuta () );
        testata.setDataRegolamento ( ricevuta.getDataOraMessaggioRicevuta ()  ); //:TODO verificare matching.
        
        //EPAY-130 - INIZIO
        testata.setDenominazioneEnte ( ricevuta.getEnteBeneficiario () != null ? sanitizzaInput(ricevuta.getEnteBeneficiario ().getDenominazioneBeneficiario ()) : "ND" );
        testata.setDenominazionePSP ( ricevuta.getIstitutoAttestante () != null ? sanitizzaInput(ricevuta.getIstitutoAttestante ().getDenominazioneAttestante ()) : "ND" );
        //EPAY-130 - FINE
        testata.setCFEnteCreditore ( (null != ricevuta.getEnteBeneficiario () &&
            null != ricevuta.getEnteBeneficiario ().getIdentificativoUnivocoBeneficiario () &&
            StringUtils.isNotEmpty ( ricevuta.getEnteBeneficiario ().getIdentificativoUnivocoBeneficiario ().getCodiceIdentificativoUnivoco () ) )
                        ? ricevuta.getEnteBeneficiario ().getIdentificativoUnivocoBeneficiario ().getCodiceIdentificativoUnivoco () : "ND" );
        testata.setIdentificativoFlusso ( singoloRT.getIuv () );
        testata.setIdentificativoUnivocoRegolamento ( "ND" );//:TODO verificare valore corretto.
        testata.setNumeroTotalePagamentiFlusso ( BigInteger.ONE );
        testata.setImportoTotalePagamentiFlusso (
            ( null != ricevuta.getDatiPagamento () ) ? ricevuta.getDatiPagamento ().getImportoTotalePagato () : BigDecimal.ZERO );
		
		return testata;
	}
	
	//EPAY-130 - INIZIO
	private String sanitizzaInput(String input) {
		if (null == input || input.trim().length() == 0) {
			input = "ND";
		}
		
		return input;
	}

    private List<RT> reperisciRTPerPArametri () throws SerialException, SQLException, NamingException, Exception {

        return new EstraiRTDaInviareDAO ( new EstraiConfigurazioneDAO ( KEY_DATA_INVIO_RT ).executeQuery ().getValue (),
            new EstraiConfigurazioneDAO ( KEY_SOGLIA_GIORNI_RICEZIONE_PAGAMENTO ).executeQuery ().getValue () )
				.executeQuery();
	}
	
	
}
