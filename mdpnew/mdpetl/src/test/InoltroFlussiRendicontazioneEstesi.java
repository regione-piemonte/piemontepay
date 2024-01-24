/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */



import java.io.ByteArrayInputStream;
import java.io.StringReader;
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
import javax.xml.bind.JAXB;
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
import org.apache.ws.security.util.Base64;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.core.util.LoggerUtil;
import it.csi.mdp.generatedvo.flussoriversamento.CtDatiSingoliPagamenti;
import it.csi.mdp.generatedvo.flussoriversamento.CtFlussoRiversamento;
import it.csi.mdp.generatedvo.pagamenti.CtRichiestaPagamentoTelematico;
import it.csi.mdp.generatedvo.pagamenti.StTipoIdentificativoUnivocoPersFG;
import it.csi.mdp.generatedvo.pagamentimod3.CtSubject;
import it.csi.mdp.generatedvo.pagamentimod3.PaGetPaymentRes;
import it.csi.mdp.generatedvo.pagamentimod3.PaSendRTReq;
import it.csi.mdp.mdpetl.dto.DatiRichiesta;
import it.csi.mdp.mdpetl.dto.DatiRichiestaGetPayment;
import it.csi.mdp.mdpetl.dto.DatiRichiestaReceipt;
import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.dto.LoggingFlusso;
import it.csi.mdp.mdpetl.dto.LoggingFlussoEsteso;
import it.csi.mdp.mdpetl.dto.SingoloPagamentoMultiVersamentoDTO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiFlussiRiversamento;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiGetPaymentPerIuvDominioDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiMultiversamentoPerIuvAndTransactionIdDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiReceiptPerIuvDominioDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiReceiptPerIuvSconosciutoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRichiestePagamentoPerIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.FiltroFlussiFlagInvioEnum;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciLoggingFlussoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ModificaStatoInvioFlussiDAO;
import it.csi.mdp.mdpetl.integration.util.dao.StatoInvioFlussoEnum;
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
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TrasmettiFlussoRendicontazioneExtRequestType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati;

public class InoltroFlussiRendicontazioneEstesi {
	static LogUtil log = new LogUtil(InoltroFlussiRendicontazioneEstesi.class);

	public void inoltraFlussi(String urlEndpointServizio, byte key[])
			throws SerialException, SQLException, NamingException, Exception {
		LoggerUtil.begin();

		XStream xs = new XStream();
		List<String> elencoIuvNonTrovati = null ;

		List<FlussoRiversamento> elencoFlussi = reperisciFlussiPerPArametri();

		LoggerUtil.info("ELENCO FLUSSI ESTRATTI" + elencoFlussi.size());
		System.out.println("ELENCO FLUSSI ESTRATTI" + elencoFlussi.size());
		
		if (CollectionUtils.isEmpty(elencoFlussi)) {
		    LoggerUtil.info("Nessun flusso da Elaborare Fine esecuzione batch");
	        System.out.println("Nessun flusso da Elaborare Fine esecuzione batch");
			return;
		}

		ServiziRendicontazioneExt iServizio = inizializzaServizioESB(urlEndpointServizio);

		Unmarshaller unmarshallerFlusso = inizializzaUnMarshallerFlusso();

		int numeroIuvOttici = 0;

		for (FlussoRiversamento singoloFlusso : elencoFlussi) {

				// AO --> reinizializzarlo per la logging flusso
				elencoIuvNonTrovati = new ArrayList<String>();
				
				// LF MDPNEW-69 - Sposto dentro al for la dichiarazione dell'importo totale
				// pagamenti intermediati
				BigDecimal importoTotalePagamentiIntermediati = BigDecimal.ZERO ;
	
				LoggingFlusso logFlusso = new LoggingFlussoEsteso();
				TrasmettiFlussoRendicontazioneExtRequestType trasmetti = new TrasmettiFlussoRendicontazioneExtRequestType();
	
				StatoInvioRTEnum statoInvioRTEnum = null;
	
				try {

				    // creazione testata
					logFlusso.setDataOraInvio(new Timestamp(System.currentTimeMillis()));
					logFlusso.setIdFlusso(singoloFlusso.getIdentificativoflusso());
					logFlusso.setIstitutoMittente(singoloFlusso.getDenominazionemittente());
	
					LoggerUtil.info("\nINIZIO ELABORAZIONE FLUSSO NUMERO " + singoloFlusso.getIdentificativoflusso()
							+ "\nISTITUTO " + singoloFlusso.getIdentificativoistitutomittente());
	
					System.out.println("\nINIZIO ELABORAZIONE FLUSSO NUMERO " + singoloFlusso.getIdentificativoflusso()
					+ "\nISTITUTO " + singoloFlusso.getIdentificativoistitutomittente());
					
					Set<String> distictCodiciVersamento = new HashSet<String>();
	
					CtFlussoRiversamento flusso = (CtFlussoRiversamento) unmarshallerFlusso
							.unmarshal(new StringReader(singoloFlusso.getXmlflusso()));
	
					TestataFlussoRendicontazioneExtType testata = datiTestata(singoloFlusso, flusso);
	
					List<PagamentoIntermediatoType> elencoPagamentiIntermediati = new ArrayList<PagamentoIntermediatoType>(
							singoloFlusso.getNumerototalepagamenti().intValue());
	
					PagamentiIntermediati wrapperPagamentiIntermediati = new PagamentiIntermediati();
					DatiAggiuntiviType datiAggiuntivi = new DatiAggiuntiviType();
	
					numeroIuvOttici = gestioneSingoliPagamenti(elencoIuvNonTrovati, numeroIuvOttici,
							distictCodiciVersamento, flusso, trasmetti, testata);
					wrapperPagamentiIntermediati.getPagamentoIntermediato().addAll(elencoPagamentiIntermediati);
					trasmetti.setPagamentiIntermediati(wrapperPagamentiIntermediati);
	
					it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType.ElencoCodiciVersamento elencoCodiciVersamento = new it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType.ElencoCodiciVersamento();
					elencoCodiciVersamento.getCodiceVersamento().addAll(distictCodiciVersamento);
					datiAggiuntivi.setElencoCodiciVersamento(elencoCodiciVersamento);
	
					// LF MDPNEW-69
					// testata.setNumeroTotalePagamentiIntermediati(BigInteger.valueOf(elencoPagamentiIntermediati.size()));
	
	//				for (PagamentoIntermediatoType pagamentoSingolo : elencoPagamentiIntermediati) {
	//					importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati.add(pagamentoSingolo.getDatiSingoliPagamenti().getDatiSingoloPagamento().getSingoloImportoPagato());
	//				}
	//				testata.setImportoTotalePagamentiIntermediati(importoTotalePagamentiIntermediati);
					trasmetti.setTestata(testata);
	
					trasmetti.setFlussoRiversamento(singoloFlusso.getXmlflusso().getBytes());
	
					log.info("inoltraFlussi", "RISULTATO FLUSSO \n" + xs.toXML(trasmetti));
					log.info("inoltraFlussi", "NUMERO IUV TOTALI " + numeroIuvOttici + " NUMERO NON TROVATI "
							+ elencoIuvNonTrovati.size() + "\nELENCO NON TROVATI " + xs.toXML(elencoIuvNonTrovati));
	                if ( null == trasmetti.getPagamentiIntermediati ()
	                    || CollectionUtils.isEmpty ( trasmetti.getPagamentiIntermediati ().getPagamentoIntermediato () ) ) {
	                    log.info("inoltraFlussi", "Nessun IUV trovato, lo stato del flusso: " + trasmetti.getTestata ().getIdentificativoFlusso () + " viene impostato a 0.");
	                    new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (), StatoInvioFlussoEnum.NESSUNA_OPERAZIONE.getCodStato (),
	                        FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO ).executeUpdate ();
	                    logFlusso.setEsito ( Constants.ESITO_OK );
						logFlusso.setWarning ( "Nessun IUV trovato, lo stato del flusso: " + trasmetti.getTestata ().getIdentificativoFlusso () + " viene impostato a 0." );
	                    statoInvioRTEnum = StatoInvioRTEnum.INVIATA_FLUSSO;
	                } else {
	                	
	                    ResponseType res = iServizio.trasmettiFlussoRendicontazioneExt ( trasmetti );
	                    // LF - 13/12/2018 - il servizio di acquisizione del flusso esteso restituisce
	                    // 000 quando l'operazione è andata a buon fine
	                    if ( "000".equalsIgnoreCase ( res.getResult ().getCodice () ) ) {
	                        //				if ("OK".equalsIgnoreCase(res.getResult().getMessaggio())) {
	                        // Lorenzo: se l'invio e' avvenuto correttamente aggiorno il relativo stato a INVIATO
	                        new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (), StatoInvioFlussoEnum.INVIATO.getCodStato (),
	                            FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO ).executeUpdate ();
	                        logFlusso.setEsito ( Constants.ESITO_OK );
	                        statoInvioRTEnum = StatoInvioRTEnum.INVIATA_FLUSSO;
	                    } else {
	                        // Se abbiamo un errore non bloccante allora lo registro come OK e valorizzo la colonna del warning
	                        if(StringUtils.startsWith ( res.getResult ().getCodice (), "0" ) ) {
	                            new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (), StatoInvioFlussoEnum.INVIATO.getCodStato (),
	                                FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO ).executeUpdate ();
	                            logFlusso.setEsito ( Constants.ESITO_OK );
	                            logFlusso.setWarning ( StringUtils.substring(res.getResult ().getMessaggio (), 0, 255) );
	                            statoInvioRTEnum = StatoInvioRTEnum.INVIATA_FLUSSO;
	                        } else {
	                            // Lorenzo: se l'invio e' avvenuto in modo erroneo aggiorno il relativo stato a NON_INVIATO
	                            new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (),
	                                StatoInvioFlussoEnum.NON_INVIATO.getCodStato (), FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO )
	                                    .executeUpdate ();
	    
	                            statoInvioRTEnum = StatoInvioRTEnum.ERRORE_INVIO_FLUSSO;
	                            logFlusso.setEsito ( Constants.ESITO_KO );
	                            logFlusso.setWarning ( StringUtils.substring(res.getResult ().getMessaggio (), 0, 255) );
	                        }
	                	}				
					}
				} catch (Exception e) {
					log.error("inoltraFlussi", "ERRORE", e);
					logFlusso.setErrori(StringUtils.substring(e.getMessage(), 0, 255));
	
					// Lorenzo: se l'invio e' avvenuto in modo erroneo aggiorno il relativo stato a NON_INVIATO
					new ModificaStatoInvioFlussiDAO(singoloFlusso.getId(), StatoInvioFlussoEnum.NON_INVIATO.getCodStato(),
							FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO).executeUpdate();
					statoInvioRTEnum = StatoInvioRTEnum.ERRORE_INVIO_FLUSSO;
	
				} finally {
					if (!CollectionUtils.isEmpty(elencoIuvNonTrovati)) {
						logFlusso.setWarning(StringUtils.substring(logFlusso.getWarning () + " - " +"IUV NON TROVATI: " + xs.toXML(elencoIuvNonTrovati), 0, 255));
					}
	                new InserisciLoggingFlussoDAO ( logFlusso ).executeUpdate ();
	                /* cambiamo la logica dell'invio singole RT - eliminiamo questi aggiornamenti
	                // LF 22/02/2019 - RDI-023
	                //inserisciLoggingRT ( logFlusso, singoloFlusso, unmarshallerFlusso );
	                // LF 21/02/2019 - marco tutte le RT associate agli IUV
	                aggiornaStatoInvioRT(statoInvioRTEnum, singoloFlusso, unmarshallerFlusso);
					*/
					//Thread.sleep(10000);
				}	
		}
	}

    /*private void aggiornaStatoInvioRT ( StatoInvioRTEnum statoInvioRTEnum, FlussoRiversamento singoloFlusso,
        Unmarshaller unmarshallerFlusso ) throws SerialException, NamingException, SQLException, Exception {

        StringBuilder sb = new StringBuilder ();

        CtFlussoRiversamento flusso = (CtFlussoRiversamento) unmarshallerFlusso
            .unmarshal ( new StringReader ( singoloFlusso.getXmlflusso () ) );
        for ( CtDatiSingoliPagamenti singoloPagamento: flusso.getDatiSingoliPagamenti () ) {
            sb.append ( "'" ).append ( singoloPagamento.getIdentificativoUnivocoVersamento () ).append ( "'," );
        }
        String levaUltimaVisrgola = sb.toString ();
        if ( levaUltimaVisrgola.length () != 0 ) {
            levaUltimaVisrgola = levaUltimaVisrgola.substring ( 0, levaUltimaVisrgola.length () - 1 );

            new AggiornaStatoInvioRT ( statoInvioRTEnum, levaUltimaVisrgola ).executeUpdate ();
        }
    }

	private void inserisciLoggingRT(LoggingFlusso logFlusso, FlussoRiversamento singoloFlusso,
			Unmarshaller unmarshallerFlusso) throws SerialException, NamingException, SQLException, Exception {
		CtFlussoRiversamento flusso = (CtFlussoRiversamento) unmarshallerFlusso
				.unmarshal(new StringReader(singoloFlusso.getXmlflusso()));
		for (CtDatiSingoliPagamenti singoloPagamento : flusso.getDatiSingoliPagamenti()) {
			LoggingRT loggingRT = new LoggingRT();
			loggingRT.setDataOraInvio(logFlusso.getDataOraInvio());
			loggingRT.setErrori(StringUtils.substring ( logFlusso.getErrori(), 0, 255 ));
			loggingRT.setEsito(logFlusso.getEsito());
			loggingRT.setIstitutoMittente(logFlusso.getIstitutoMittente());
			loggingRT.setIuv(singoloPagamento.getIdentificativoUnivocoVersamento());
			loggingRT.setWarning(StringUtils.substring ( logFlusso.getWarning(), 0, 255 ));
			new InserisciLoggingRTDAO(loggingRT).executeUpdate();
		}
	} */

	private int gestioneSingoliPagamenti(List<String> elencoIuvNonTrovati, int numeroIuvOttici,
			Set<String> distictCodiciVersamento, CtFlussoRiversamento flusso,
			TrasmettiFlussoRendicontazioneExtRequestType flussoExt, TestataFlussoRendicontazioneExtType testata)
			throws SerialException, SQLException, NamingException, Exception, JAXBException {

		BigDecimal importoTotale = BigDecimal.ZERO;
		BigInteger numeroTotale = BigInteger.ZERO;
		int indice = 0;
		
		
		for (CtDatiSingoliPagamenti singoloPagamento : flusso.getDatiSingoliPagamenti()) {
		    PagamentoIntermediatoType pagamento = new PagamentoIntermediatoType ();
		    
			LoggerUtil.info("SINGOLO PAGAMENTO: " + singoloPagamento.getIdentificativoUnivocoVersamento());
			System.out.println("SINGOLO PAGAMENTO: " + singoloPagamento.getIdentificativoUnivocoVersamento());
			
			DatiRichiesta rptDb = null;
			DatiRichiestaReceipt receiptDb = null;
			DatiRichiestaGetPayment getPaymentDb = null;
			
			EstraiRichiestePagamentoPerIuvDAO dao = new EstraiRichiestePagamentoPerIuvDAO(
			singoloPagamento.getIdentificativoUnivocoVersamento());
			rptDb = dao.executeQuery();
		
			System.out.println("1. Cerco RPT/receipt/getPayment");
			
			if (rptDb == null) {
				// aggiunto AO **
				EstraiGetPaymentPerIuvDominioDAO getPaymentDao = new EstraiGetPaymentPerIuvDominioDAO(
				singoloPagamento.getIdentificativoUnivocoVersamento(), flusso.getIstitutoRicevente().getIdentificativoUnivocoRicevente().getCodiceIdentificativoUnivoco()); // idDominio);
				getPaymentDb = getPaymentDao.executeQuery();
			
				if (getPaymentDb == null) {
					// aggiunto AO **
					EstraiReceiptPerIuvDominioDAO receiptDao = new EstraiReceiptPerIuvDominioDAO(singoloPagamento.getIdentificativoUnivocoVersamento()); 
					receiptDb = receiptDao.executeQuery();
					
					if (receiptDb == null) {
					    flussoExt.getPagamentiNonIntermediati ().getPagamentoIntermediato ().add ( pagamento );
						// aggiunto AO **
						EstraiReceiptPerIuvSconosciutoDAO receiptIuvSconosciutoDao = new EstraiReceiptPerIuvSconosciutoDAO(singoloPagamento.getIdentificativoUnivocoVersamento()); 
						receiptDb = receiptIuvSconosciutoDao.executeQuery();							
					} else {
					    flussoExt.getPagamentiIntermediati ().getPagamentoIntermediato ().add ( pagamento );
					}
				}
			}	
			
			// AO Controllo se c'e' RPT / getPayment / receipt
			if (rptDb != null || getPaymentDb != null || receiptDb != null) {
				
				
				System.out.println("2. Trovata RPT/receipt/getPayment");
				
				CtRichiestaPagamentoTelematico richiesta =null;
			    PaGetPaymentRes richiesta1=null;
			    PaSendRTReq richiesta2 = null;
				
				List<SingoloPagamentoMultiVersamentoDTO> pag =null;
					
				int datiVersamentoSize=0;
				String datiSpecPrimoElem = null;
				String causalePrimoElem = null;
				
			   SoggettoType pagatore = new SoggettoType ();
                 //pagatore.setEMail ( soggettoPag.getEMailPagatore () );
                 //pagatore.setIdentificativoUnivocoFiscale ( soggettoPag.getIdentificativoUnivocoPagatore ().getCodiceIdentificativoUnivoco () );
                
				
				if (rptDb != null) {
					
					System.out.println("2.1 Trovata RPT");
					flussoExt.getPagamentiIntermediati ().getPagamentoIntermediato ().add ( pagamento );
					indice = singoloPagamento.getIndiceDatiSingoloPagamento() != null ? singoloPagamento.getIndiceDatiSingoloPagamento() - 1 : 0;
					
					richiesta = JAXB.unmarshal ( new ByteArrayInputStream(rptDb.getRptXml().getBytes ()), CtRichiestaPagamentoTelematico.class );
					
					EstraiMultiversamentoPerIuvAndTransactionIdDAO estraiMultiversamentoPerIuvAndTransactionIdDAO = new EstraiMultiversamentoPerIuvAndTransactionIdDAO(
						singoloPagamento.getIdentificativoUnivocoVersamento(), rptDb.getTransactionId());
				
					pag = estraiMultiversamentoPerIuvAndTransactionIdDAO.executeQuery();
					//datiVersamento = richiesta.getDatiVersamento();
					datiVersamentoSize= richiesta.getDatiVersamento().getDatiSingoloVersamento().size();
					datiSpecPrimoElem = richiesta.getDatiVersamento().getDatiSingoloVersamento().get(indice) != null?
							richiesta.getDatiVersamento().getDatiSingoloVersamento().get(indice).getDatiSpecificiRiscossione():null;
					causalePrimoElem = richiesta.getDatiVersamento().getDatiSingoloVersamento().get(indice).getCausaleVersamento();
					
					pagatore.setEMail (richiesta.getSoggettoPagatore().getEMailPagatore());
					pagatore.setIdentificativoUnivocoFiscale(richiesta.getSoggettoPagatore().getIdentificativoUnivocoPagatore().getCodiceIdentificativoUnivoco());
					if (richiesta.getSoggettoPagatore().getIdentificativoUnivocoPagatore().getTipoIdentificativoUnivoco().equals(StTipoIdentificativoUnivocoPersFG.F)) {
						PersonaFisicaType pf = new PersonaFisicaType();
						pf.setCognome(richiesta.getSoggettoPagatore().getAnagraficaPagatore());
						pagatore.setPersonaFisica(pf);
					} else {
						PersonaGiuridicaType pf = new PersonaGiuridicaType();
						pf.setRagioneSociale(richiesta.getSoggettoPagatore().getAnagraficaPagatore());
						pagatore.setPersonaGiuridica(pf);
					}
				} else if(getPaymentDb != null) {
					System.out.println("2.2 Trovata getPayment");
					flussoExt.getPagamentiIntermediati ().getPagamentoIntermediato ().add ( pagamento );
					try {
					    //:TODO capire come impostare la logica dopo test
					    indice = singoloPagamento.getIndiceDatiSingoloPagamento() != null ? singoloPagamento.getIndiceDatiSingoloPagamento() - 1 : 0;
					// AO getPayment 
					// fare una CtGetPayment che fa l'unmashall della getpayment con il suo schema
					System.out.println("2.2.1 Inizio Unmarshall getPayment");
					richiesta1 = JAXB.unmarshal ( new ByteArrayInputStream(Base64.decode(new String(getPaymentDb.getRptXml()))), PaGetPaymentRes.class );
					System.out.println("2.2.2 Unmarshall eseguita correttamente.");
					} catch (Exception e) {
					    System.out.println("2.2.3 Errore unmarshall.");
						e.printStackTrace();
    				} catch (Throwable e) {
    				    System.out.println("2.2.4 Throwable unmarshall.");
    				    e.printStackTrace();
    				}
					System.out.println("2.2.5 Fine Unmarshall.");
					EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO estraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO = new EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO(singoloPagamento.getIdentificativoUnivocoVersamento(),
							flusso.getIstitutoRicevente().getIdentificativoUnivocoRicevente().getCodiceIdentificativoUnivoco());
					pag = estraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO.executeQuery();
					
					//datiVersamento = richiesta1.getDatiVersamento();
					datiVersamentoSize=richiesta1.getData().getTransferList().getTransfer().size();
					datiSpecPrimoElem = richiesta1.getData().getTransferList().getTransfer().get(indice) != null?richiesta1.getData().getTransferList().getTransfer().get(indice).getTransferCategory():null;
					causalePrimoElem = richiesta1.getData().getTransferList().getTransfer().get(indice).getRemittanceInformation();
					
					pagatore.setEMail (richiesta1.getData().getDebtor().getEMail());
					pagatore.setIdentificativoUnivocoFiscale(richiesta1.getData().getDebtor().getUniqueIdentifier().getEntityUniqueIdentifierValue());
					 if (richiesta1.getData().getDebtor().getUniqueIdentifier().getEntityUniqueIdentifierType().equals(StTipoIdentificativoUnivocoPersFG.F)) {
						PersonaFisicaType pf = new PersonaFisicaType();
						pf.setCognome(richiesta1.getData().getDebtor().getFullName());
						pagatore.setPersonaFisica(pf);
					 } else {
						PersonaGiuridicaType pf = new PersonaGiuridicaType();
						pf.setRagioneSociale(richiesta1.getData().getDebtor().getFullName());
						pagatore.setPersonaGiuridica(pf);
					}							
				} else {
						
					System.out.println("2.3 Trovata receipt");
					
                    //:TODO capire come impostare la logica dopo test
                    indice = singoloPagamento.getIndiceDatiSingoloPagamento() != null ? singoloPagamento.getIndiceDatiSingoloPagamento() - 1 : 0;
                    
					// AO receipt 
					
					try {
						richiesta2 = JAXB.unmarshal ( new ByteArrayInputStream(Base64.decode(new String(receiptDb.getRptXml()))), PaSendRTReq.class );
					} catch(Exception e) {
						e.printStackTrace();
					}
						
					
					System.out.println("2.3.1 richiesta2:"+richiesta2.getReceipt ().getReceiptId ());
					
					// fare una CtReceipt che fa l'unmashall della receipt con il suo schema
					EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO estraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO = new EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO(
							singoloPagamento.getIdentificativoUnivocoVersamento(),
							flusso.getIstitutoRicevente().getIdentificativoUnivocoRicevente().getCodiceIdentificativoUnivoco());
					pag = estraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO.executeQuery();
					
					System.out.println ("2.3.2 Estratto Multiversamento ");
				
//					da correggere lolloso
					//datiVersamento = richiesta2.getDatiVersamento();
					datiVersamentoSize= richiesta2.getReceipt ().getTransferList().getTransfer().size();
					datiSpecPrimoElem = richiesta2.getReceipt ().getTransferList().getTransfer().get(indice) != null? richiesta2.getReceipt ().getTransferList().getTransfer().get(indice).getTransferCategory():null;
					causalePrimoElem = richiesta2.getReceipt ().getTransferList().getTransfer().get(indice).getRemittanceInformation();
					

					pagatore.setEMail (richiesta2.getReceipt ().getDebtor().getEMail());
					pagatore.setIdentificativoUnivocoFiscale(richiesta2.getReceipt ().getDebtor().getUniqueIdentifier().getEntityUniqueIdentifierValue());
					 if (richiesta2.getReceipt ().getDebtor().getUniqueIdentifier().getEntityUniqueIdentifierType().equals(StTipoIdentificativoUnivocoPersFG.F)) {
						PersonaFisicaType pf = new PersonaFisicaType();
						pf.setCognome(richiesta2.getReceipt ().getDebtor().getFullName());
						pagatore.setPersonaFisica(pf);
					 } else {
						PersonaGiuridicaType pf = new PersonaGiuridicaType();
						pf.setRagioneSociale(richiesta2.getReceipt ().getDebtor().getFullName());
						pagatore.setPersonaGiuridica(pf);
					}							
					//soggettoVer = soggettoPag;

//					intermediato = rptDb.getIntermediato();
//					secondario = rptDb.getSecondario();
				}
				
				
				// XStream xs = new XStream();
				// System.out.println("MULTIVERSAMENTI ESTRATTI \n"+xs.toXML(pag));
				// se multiversamento sdoppio le righe. senno' ne costruisco uno solo per
				// riutilizzare lo stasso codice dopo.
				if (pag.size() == 0) {
					SingoloPagamentoMultiVersamentoDTO singoloFarlocco = new SingoloPagamentoMultiVersamentoDTO();
					//LoggerUtil.info("SINGOLO PAGAMENTO: " + datiVersamento.getDatiSingoloVersamento().size());
					LoggerUtil.info("SINGOLO PAGAMENTO PAG.SIZE() == 0: " + datiVersamentoSize);
					if (datiSpecPrimoElem != null) {
						singoloFarlocco.setDatiSpecificiRiscossione(datiSpecPrimoElem);
					} else {
						singoloFarlocco.setDatiSpecificiRiscossione("9/000");
					}

					singoloFarlocco.setPosizione(singoloPagamento.getIndiceDatiSingoloPagamento());
					singoloFarlocco.setCausale(causalePrimoElem);
					singoloFarlocco.setImporto(singoloPagamento.getSingoloImportoPagato());
					singoloFarlocco.setIuv ( singoloPagamento.getIdentificativoUnivocoVersamento () );
					singoloFarlocco.setDatiSpecificiRiscossione ( datiSpecPrimoElem );
					pag.add(singoloFarlocco);
				}

				// LF - MDPNEW-69 - Sposto qua la somma sul numero pagamenti intermediati (non
				// deve tener conto dello spacchettamento dell'importo in piu' componenti)
				numeroTotale = numeroTotale.add(BigInteger.ONE);

				for (SingoloPagamentoMultiVersamentoDTO singoloPagamentoMulti : pag) {
					PagamentoIntermediatoType primoLivello = new PagamentoIntermediatoType();
					DatiSingoliPagamenti secondoLivello = new DatiSingoliPagamenti();
					DatiSingoloPagamentoType terzoLivello = new DatiSingoloPagamentoType();
					secondoLivello.setDatiSingoloPagamento(terzoLivello);
					terzoLivello.setIUR(singoloPagamento.getIdentificativoUnivocoRiscossione());
					terzoLivello.setIUV(singoloPagamento.getIdentificativoUnivocoVersamento());
					terzoLivello.setTransactionId(null != rptDb ? rptDb.getTransactionId(): null); // :TODO rivedere logica in quanto il transacionID non ci sarà mai se il pagamento non è effettuato da noi. Calcolarlo prima di arrivare a questo punto del codice.

					primoLivello.setDatiSingoliPagamenti(secondoLivello);

					importoTotale = importoTotale.add(singoloPagamentoMulti.getImporto());


					terzoLivello.setAnagraficaPagatore(pagatore);
					terzoLivello.setAnagraficaVersante(pagatore);

                    /*
                    if ( StringUtils.isNotBlank ( soggettoPag.getCapPagatore () ) ) {
                        pagatore.setCAP ( soggettoPag.getCapPagatore () );
                    }
                    if ( StringUtils.isNotBlank ( soggettoPag.getCivicoPagatore () ) ) {
                        pagatore.setCivico ( soggettoPag.getCivicoPagatore () );
                    }
                     if ( StringUtils.isNotBlank ( soggettoPag.getIndirizzoPagatore () ) ) {
                        pagatore.setIndirizzo ( soggettoPag.getIndirizzoPagatore () );
                    }
                    if ( StringUtils.isNotBlank ( soggettoPag.getLocalitaPagatore () ) ) {
                        pagatore.setLocalita ( soggettoPag.getLocalitaPagatore () );
                    }
                    if ( StringUtils.isNotBlank ( soggettoPag.getNazionePagatore () ) ) {
                        pagatore.setNazione ( soggettoPag.getNazionePagatore () );
                    }
					// pagatore.setCAP("FAKE");
					// pagatore.setCivico("FAKE");
					// pagatore.setEMail("FAKE");
					// pagatore.setIdentificativoUnivocoFiscale("FAKE");
					// pagatore.setIndirizzo("FAKE");
					// pagatore.setLocalita("FAKE");
					// pagatore.setNazione("FAKE");
					// pagatore.setProvincia(soggettoPag.getProvinciaPagatore());
					
					
					if (soggettoVer != null) {
						SoggettoType versante = new SoggettoType();
                        if ( StringUtils.isNotBlank ( soggettoVer.getCapVersante () ) ) {
                            versante.setCAP ( soggettoVer.getCapVersante () );
                        }
                        if ( StringUtils.isNotBlank ( soggettoVer.getCapVersante () ) ) {
                            versante.setCivico ( soggettoVer.getCivicoVersante () );
                        }
                        versante.setEMail ( soggettoVer.getEMailVersante () );
                        versante.setIdentificativoUnivocoFiscale ( soggettoVer
                            .getIdentificativoUnivocoVersante ().getCodiceIdentificativoUnivoco () );
                        if ( StringUtils.isNotBlank ( soggettoVer.getIndirizzoVersante () ) ) {
                            versante.setIndirizzo ( soggettoVer.getIndirizzoVersante () );
                        }
                        if ( StringUtils.isNotBlank ( soggettoVer.getLocalitaVersante () ) ) {
                            versante.setLocalita ( soggettoVer.getLocalitaVersante () );
                        }
                        if ( StringUtils.isNotBlank ( soggettoVer.getNazioneVersante () ) ) {
                            versante.setNazione ( soggettoVer.getNazioneVersante () );
                        }
                        if ( StringUtils.isNotBlank ( soggettoVer.getProvinciaVersante () ) ) {
                            versante.setProvincia ( soggettoVer.getProvinciaVersante () );
                        }
						// versante.setCAP("FAKE");
						// versante.setCivico("FAKE");
						// versante.setEMail("FAKE");
						// versante.setIdentificativoUnivocoFiscale("FAKE");
						// versante.setIndirizzo("FAKE");
						// versante.setLocalita("FAKE");
						// versante.setNazione("FAKE");
						// versante.setProvincia("FAKE");

						if (soggettoVer.getIdentificativoUnivocoVersante()
								.getTipoIdentificativoUnivoco().equals(StTipoIdentificativoUnivocoPersFG.F)) {
							PersonaFisicaType pf = new PersonaFisicaType();
							// pf.setCognome("FAKE");
							pf.setCognome(soggettoVer.getAnagraficaVersante());
							versante.setPersonaFisica(pf);
						} else {
							PersonaGiuridicaType pf = new PersonaGiuridicaType();
							pf.setRagioneSociale(soggettoVer.getAnagraficaVersante());
							versante.setPersonaGiuridica(pf);
						}
						
						terzoLivello.setAnagraficaVersante(versante);
					}
					*/
					terzoLivello.setCodiceEsitoPagamento(singoloPagamento.getCodiceEsitoSingoloPagamento());
					terzoLivello.setDataEsitoSingoloPagamento(singoloPagamento.getDataEsitoSingoloPagamento());
					/*
					 * if (singoloPagamento.getIdentificativoUnivocoVersamento().length() > 17) {
					 * terzoLivello.setCodiceVersamento(singoloPagamento.
					 * getIdentificativoUnivocoVersamento().substring(13, 17));
					 * distictCodiciVersamento.add(terzoLivello.getCodiceVersamento()); }
					 */
					if (singoloPagamento.getIdentificativoUnivocoVersamento().length() > 17) {
						// AO soluzione workaround per codice_Versamento estratto dagli iuv di modello1
						// andrebbe fatto su auxDigit 3 ma Ã¨ un dato di configurazione
						try {
							// se il carattere al tredicesimo posto Ã¨ un numero si tratta del nuovo formato
							Integer.parseInt(singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 14));
							terzoLivello.setCodiceVersamento(
									singoloPagamento.getIdentificativoUnivocoVersamento().substring(15, 19));
						} catch (NumberFormatException e) {
							terzoLivello.setCodiceVersamento(
									singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 17));
						}
						distictCodiciVersamento.add(terzoLivello.getCodiceVersamento());
					} else {
						numeroIuvOttici++;
						IuvOttico iuvDto = new EstraiApplicationIdDaIuvDAO(
								singoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery();
						if (iuvDto != null) {
							terzoLivello.setCodiceVersamento(iuvDto.getCodiceVersamento());
							// AO aggiunta distinct codice versamento per modello 3
	                        distictCodiciVersamento.add(terzoLivello.getCodiceVersamento());
						}
						else
							elencoIuvNonTrovati.add(singoloPagamento.getIdentificativoUnivocoVersamento());
					}
					if (singoloPagamentoMulti.getDatiSpecificiRiscossione() == null
							|| StringUtils.isBlank(singoloPagamentoMulti.getDatiSpecificiRiscossione())) {
						throw new Exception(String.format("Dati specifici di riscossione per lo IUV %s non presenti",
								singoloPagamento.getIdentificativoUnivocoVersamento()));
					}
					terzoLivello.setDatiSpecificiRiscossione(singoloPagamentoMulti.getDatiSpecificiRiscossione());
					terzoLivello.setDescrizioneCausaleVersamento(singoloPagamentoMulti.getCausale());
					// AO cambiata query per la posizione
					if (singoloPagamentoMulti.getPosizione() != null) {
						terzoLivello.setIndiceDatiPagamento(singoloPagamentoMulti.getPosizione());
					} else {
						terzoLivello.setIndiceDatiPagamento(1);
					}

					terzoLivello.setIUR(singoloPagamento.getIdentificativoUnivocoRiscossione());
					terzoLivello.setIUV(singoloPagamento.getIdentificativoUnivocoVersamento());
					terzoLivello.setSingoloImportoPagato(singoloPagamentoMulti.getImporto());
					terzoLivello.setTransactionId(null != rptDb ? rptDb.getTransactionId(): null);// :TODO rivedere logica in quanto il transacionID non ci sarà mai se il pagamento non è effettuato da noi. Calcolarlo prima di arrivare a questo punto del codice.

					// LF 03/01/2019 Aggiunta anno e numero accertamento (RDI-005)
					if (singoloPagamentoMulti.getAnnoAccertamento() != null
							&& singoloPagamentoMulti.getAnnoAccertamento() > 0) {

						terzoLivello.setAnnoAccertamento(singoloPagamentoMulti.getAnnoAccertamento());
					}

					if (null != singoloPagamentoMulti.getNumeroAccertamento()
							&& singoloPagamentoMulti.getNumeroAccertamento() > 0) {
						terzoLivello.setNumeroAccertamento(singoloPagamentoMulti.getNumeroAccertamento());

					}
					// AO: a tendere discrimineremo intermediato e non. Ora aggiungiamo tutto a intermediati
					//if (intermediato) {
//					elencoPagamentiIntermediati.add(primoLivello);
					/*} else {
						elencoPagamentiNONIntermediati.add(primoLivello);
					}*/
				}
			} else {
				System.out.println("3. NON Trovata RPT/receipt/getPayment");
				
				// AO Non ho trovato RPT / getPayment /receipt ma il pagamento è nel flusso
				// il controllo sul codice 9 lo estenderei all'esistenza su iuv ottici
				// se c'è su iuv ottici è nostro anche se non esiste RPT / RT /receipt
				// MDPNEW-84 INIZIO
				boolean findIuvOttico = false;
				IuvOttico iuvDto = new EstraiApplicationIdDaIuvDAO(singoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery();
				if (iuvDto != null)
					findIuvOttico = true;
				
				if (singoloPagamento.getCodiceEsitoSingoloPagamento().equals("9") || findIuvOttico) {
					System.out.println("3.1 Codice 9 o IUV nostro");
					
					
					DatiSingoloPagamentoType datisingoloPagamento = new DatiSingoloPagamentoType();

					datisingoloPagamento.setTransactionId("PRD00000000XXX");
					datisingoloPagamento.setDatiSpecificiRiscossione("9/000");

					SoggettoType soggettoPagatore = new SoggettoType();
					PersonaGiuridicaType personaGiuridica = new PersonaGiuridicaType();
					personaGiuridica.setRagioneSociale("Assenza RPT");
					soggettoPagatore.setPersonaGiuridica(personaGiuridica);
					soggettoPagatore.setIdentificativoUnivocoFiscale("01111111111");
					datisingoloPagamento.setAnagraficaPagatore(soggettoPagatore);
					
					PagamentoIntermediatoType primoLivello = new PagamentoIntermediatoType();
					DatiSingoliPagamenti pagamentoIndermediato = new DatiSingoliPagamenti();

					if (singoloPagamento.getIdentificativoUnivocoVersamento().length() > 17) {
						// soluzione workaround per codice_Versamento estratto dagli iuv di modello1
						// andrebbe fatto su auxDigit 3 ma Ã¨ un dato di configurazione
						try {
							// se il carattere al tredicesimo posto Ã¨ un numero si tratta del nuovo formato
							Integer.parseInt(singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 14));
							datisingoloPagamento.setCodiceVersamento(
									singoloPagamento.getIdentificativoUnivocoVersamento().substring(15, 19));
						} catch (NumberFormatException e) {
							datisingoloPagamento.setCodiceVersamento(
									singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 17));
						}
						distictCodiciVersamento.add(datisingoloPagamento.getCodiceVersamento());
					} else {
						numeroIuvOttici++;
						//IuvOttico iuvDto = new EstraiApplicationIdDaIuvDAO(singoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery();
						if (iuvDto != null) {
							datisingoloPagamento.setCodiceVersamento(iuvDto.getCodiceVersamento());
							LoggerUtil.info("dati: " + datisingoloPagamento.getCodiceVersamento());
							
							//INCREMENTO IL TOTALE DELL'IMPORTO E DEL NUMERO DI PAGAMENTI
							importoTotale = importoTotale.add(singoloPagamento.getSingoloImportoPagato());
							numeroTotale = numeroTotale.add(BigInteger.ONE);
						} else {
							elencoIuvNonTrovati.add(singoloPagamento.getIdentificativoUnivocoVersamento());
						}
					}

					datisingoloPagamento.setIndiceDatiPagamento(1);
					datisingoloPagamento.setIUR(singoloPagamento.getIdentificativoUnivocoRiscossione());
					datisingoloPagamento.setIUV(singoloPagamento.getIdentificativoUnivocoVersamento());
					datisingoloPagamento.setSingoloImportoPagato(singoloPagamento.getSingoloImportoPagato());
					datisingoloPagamento.setCodiceEsitoPagamento(singoloPagamento.getCodiceEsitoSingoloPagamento());
					datisingoloPagamento.setDataEsitoSingoloPagamento(singoloPagamento.getDataEsitoSingoloPagamento());
					datisingoloPagamento.setDescrizioneCausaleVersamento(
							"/RFB/" + singoloPagamento.getIdentificativoUnivocoVersamento() + "/"
									+ singoloPagamento.getSingoloImportoPagato() + "/TXT/Assenza RPT");
					pagamentoIndermediato.setDatiSingoloPagamento(datisingoloPagamento);

					// aggiunta distinct codice versamento per modello 3

					primoLivello.setDatiSingoliPagamenti(pagamentoIndermediato);
					distictCodiciVersamento.add(datisingoloPagamento.getCodiceVersamento());
//					elencoPagamentiIntermediati.add(primoLivello);

					// MDPNEW-84 FINE
				} else {
					elencoIuvNonTrovati.add(singoloPagamento.getIdentificativoUnivocoVersamento());
				}
			}
		}
		testata.setImportoTotalePagamentiIntermediati(importoTotale);
		testata.setNumeroTotalePagamentiIntermediati(numeroTotale);
		it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento co = new it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento();
		// System.out.println("codici: "+distictCodiciVersamento);
		co.getCodiceVersamento().addAll(distictCodiciVersamento);
		testata.setElencoCodiciVersamento(co);
		return numeroIuvOttici;
		
	}

	private Unmarshaller inizializzaUnMarshallerFlusso() throws JAXBException, SAXException {
		JAXBContext contextFlussoJAXB = JAXBContext.newInstance(CtFlussoRiversamento.class);
		Unmarshaller unmarshallerFlusso = contextFlussoJAXB.createUnmarshaller();
		unmarshallerFlusso.setEventHandler(new ValidationEventHandler() {

			public boolean handleEvent(ValidationEvent event) {
				System.out.println("ERRORE DI VALIDAZIONE " + event.getMessage() + " " + event.getSeverity() + " "
						+ event.getLinkedException());
				return true;
			}
		});
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Source so = new StreamSource(this.getClass().getResourceAsStream("/FlussoRiversamento_1_0_4.xsd"));
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

	private TestataFlussoRendicontazioneExtType datiTestata(FlussoRiversamento singoloFlusso,
			CtFlussoRiversamento flusso) {
		TestataFlussoRendicontazioneExtType testata = new TestataFlussoRendicontazioneExtType();

		testata.setIdMessaggio(singoloFlusso.getIdentificativoflusso());

		testata.setIdPSP(singoloFlusso.getIdentificativoistitutomittente());

		/* DATI TESTATA */
		testata.setDataOraMessaggio(flusso.getDataOraFlusso());
		testata.setDataRegolamento(flusso.getDataRegolamento());

		// EPAY-130 - INIZIO
		testata.setDenominazioneEnte(flusso.getIstitutoRicevente() != null
				? sanitizzaInput(flusso.getIstitutoRicevente().getDenominazioneRicevente())
				: "ND");
		testata.setDenominazionePSP(flusso.getIstitutoMittente() != null
				? sanitizzaInput(flusso.getIstitutoMittente().getDenominazioneMittente())
				: "ND");
		// EPAY-130 - FINE
		testata.setCFEnteCreditore(flusso.getIstitutoRicevente() != null
				? flusso.getIstitutoRicevente().getIdentificativoUnivocoRicevente().getCodiceIdentificativoUnivoco()
				: "ND");
		testata.setIdentificativoFlusso(singoloFlusso.getIdentificativoflusso());
		testata.setIdentificativoUnivocoRegolamento(flusso.getIdentificativoUnivocoRegolamento());
		testata.setNumeroTotalePagamentiFlusso(
				flusso.getNumeroTotalePagamenti() != null ? flusso.getNumeroTotalePagamenti().toBigInteger()
						: BigInteger.ONE);
		testata.setImportoTotalePagamentiFlusso(BigDecimal.valueOf(singoloFlusso.getImportototalepagamenti()));

		return testata;
	}

	// EPAY-130 - INIZIO
	private String sanitizzaInput(String input) {
		if (null == input || input.trim().length() == 0) {
			input = "ND";
		}

		return input;
	}

	private List<FlussoRiversamento> reperisciFlussiPerPArametri()
			throws SerialException, SQLException, NamingException, Exception {

		return new EstraiFlussiRiversamento(FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO).executeQuery();
	}
}
