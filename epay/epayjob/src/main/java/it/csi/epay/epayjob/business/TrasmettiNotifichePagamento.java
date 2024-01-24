/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Predicate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.xml.sax.SAXException;

import it.csi.epay.epayjob.business.mail.InviaMailTrasmissioneNotifiche;
import it.csi.epay.epayjob.rest.client.TassonomiaAdapterClient;
import it.csi.epay.epayjob.utilities.CodiciEsito;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayjob.utilities.XmlUtil;
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioneFacade;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.RegistroElaborazioni;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.sportello2epaywso.CorpoNotifichePagamentoType;
import it.csi.sportello2epaywso.DatiTransazionePSPType;
import it.csi.sportello2epaywso.NotificaPagamentoType;
import it.csi.sportello2epaywso.PersonaFisicaType;
import it.csi.sportello2epaywso.PersonaGiuridicaType;
import it.csi.sportello2epaywso.ResponseType;
import it.csi.sportello2epaywso.ResultType;
import it.csi.sportello2epaywso.SoggettoType;
import it.csi.sportello2epaywso.Sportello2EPaywso;
import it.csi.sportello2epaywso.Sportello2EPaywso_Service;
import it.csi.sportello2epaywso.TestataNotifichePagamentoType;
import it.csi.sportello2epaywso.TrasmettiNotifichePagamentoRequest;

public class TrasmettiNotifichePagamento extends RtXml {
	
	private static final String CONFIG_PROPERTIES = "config.properties";
	
	private final LogUtil log;
	private final JobFacade jobFacade;
	private final MailFacade mailFacade;
	private final String url;
	private final ConfigurazioneFacade configurazioneFacade ;
	
	public TrasmettiNotifichePagamento(JobFacade jobFacade, MailFacade mailFacade,ConfigurazioneFacade configurazioneFacade ) throws IllegalArgumentException {
		super();
		final String methodName = "TrasmettiNotifichePagamento";
		log = new LogUtil(this.getClass());
		this.jobFacade = jobFacade;
		this.mailFacade = mailFacade;
		 this.configurazioneFacade = configurazioneFacade;
		
		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
				Properties properties = new Properties();
				properties.load(inputStream);
				url = properties.getProperty("sportello2epaywso.wsdl");
				log.info(methodName, "url wso : " + url);
				System.out.println (methodName+ "url wso : " + url );
			} catch (IOException e) {
				log.debug(methodName, "errore lettura parametri. " + e.getMessage());
				System.out.println (methodName+ "errore lettura parametri. " + e.getMessage() );
				throw new RuntimeException("Errore lettura parametri: " + e.getMessage());
			}
	}

	public void execute() throws Exception {
		final String methodName = "execute";
		log.infoStart(methodName);
		
		try {
			List<Ente> enti = jobFacade.ricercaEntiPerInvioPagamento();
			for (Ente ente : enti) {
				log.info(methodName, "elaborazione ente " + ente.getNome());
				System.out.println (methodName+ "elaborazione ente " + ente.getNome() );
				try {
					List<TipoPagamento> tipiPagamento = jobFacade.ricercaTipoPagamentoPerInvioPagamento(ente);
				
					for (TipoPagamento tipoPagamento : tipiPagamento) {
						log.info(methodName, "elaborazione tipo pagamento " + tipoPagamento.getDescrizionePortale());
						System.out.println (methodName+ "elaborazione tipo pagamento " + tipoPagamento.getDescrizionePortale() );
						boolean elaboraPagamentiSpontanei = true;
						boolean elaboraPagamentiAttesi = true;
						
						//pagamenti spontanei
						try {
							List<Pagamento> pagamentiSpontanei = jobFacade.ricercaPagamentoPerInvioPagamento(tipoPagamento, true);
							System.out.println ( "Eelenco Pagamenti spontanei da elaborare" );
							logPagamenti ( pagamentiSpontanei );
							if (pagamentiSpontanei.isEmpty()) {
								throw new NoDataException();
							}
							requestSoap(ente, tipoPagamento, pagamentiSpontanei, true);
						} catch (NoDataException nde) {
							elaboraPagamentiSpontanei = false;
						}
						
					    //pagamenti Attesi
						try {
							List<Pagamento> pagamentiAttesi = jobFacade.ricercaPagamentoPerInvioPagamento(tipoPagamento, false);
							System.out.println ( "Eelenco Pagamenti attesi da elaborare" );
                            logPagamenti ( pagamentiAttesi);
							if (pagamentiAttesi.isEmpty()) {
								throw new NoDataException();
							}
							requestSoap(ente, tipoPagamento, pagamentiAttesi, false);
						} catch (NoDataException nde) {
							elaboraPagamentiAttesi = false;
						}
						
						if ( !elaboraPagamentiSpontanei && !elaboraPagamentiAttesi) {
							//inserisciRegistroElaborazioniNoData(jobFacade, ente, tipoPagamento);
							log.info(methodName, "nessun pagamento da elaborare");
							System.out.println (methodName+ "nessun pagamento da elaborare" );
						}
					}
				
				
				} catch (NoDataException nde) {
					//inserisciRegistroElaborazioniNoData(jobFacade, ente, null);
					log.info(methodName, "nessun tipo pagamento da elaborare");
					System.out.println (methodName+ "nessun tipo pagamento da elaborare" );
				}
			}
				
		} catch (NoDataException nde) {
			//inserisciRegistroElaborazioniNoData(jobFacade,null, null);
			log.info(methodName, "nessun ente da elaborare");
			System.out.println (methodName+ "nessun ente da elaborare" );
		}
		
		log.infoEnd(methodName);
		System.out.println ("END"+methodName );
	}

	private void logPagamenti(List<Pagamento> pagamenti) {
	    if (pagamenti == null || pagamenti.isEmpty()) {
            log.debug ( "LOG PAGAMENTO" , "######################################################");
            log.debug ( "LOG PAGAMENTO" , "# LISTA VUOTA #");
            log.debug ( "LOG PAGAMENTO" , "######################################################");
            System.out.println ("LOG PAGAMENTO"+ "######################################################" );
            System.out.println ("LOG PAGAMENTO"+ "# LISTA VUOTA #" );
            System.out.println ("LOG PAGAMENTO"+ "######################################################" );
	    } else {
			for ( Pagamento pagamento : pagamenti ) {
				log.debug ( "LOG PAGAMENTO", "######################################################" );
				log.debug ( "AUX DIGIT", pagamento.getAuxDigit () );
				log.debug ( "IUV", pagamento.getIuv () );
				log.debug ( "COD IMPORTO", pagamento.getImporto () );
				log.debug ( "LOG PAGAMENTO", "######################################################" );
				System.out.println ("LOG PAGAMENTO"+ "######################################################" );
				System.out.println ("AUX DIGIT"+ pagamento.getAuxDigit () );
				System.out.println ("IUV"+ pagamento.getIuv ()  );
				System.out.println ("COD IMPORTO"+ pagamento.getImporto () );
				System.out.println ("LOG PAGAMENTO"+ "######################################################" );
			}
		}
	}
	
	private void requestSoap(Ente ente, TipoPagamento tipoPagamento, List<Pagamento> pagamenti, Boolean pagamentoSpontaneo) throws XPathExpressionException {
		final String methodName = "requestSoap: ";
		if (pagamenti == null || pagamenti.isEmpty()) {
			return;
		}
		
		RegistroElaborazioni registroElaborazioni = inserisciRegistroElaborazioniInizio(jobFacade, ente, tipoPagamento, pagamentoSpontaneo);
		CorpoNotifichePagamentoType.ElencoNotifichePagamento elenco = new CorpoNotifichePagamentoType.ElencoNotifichePagamento();
		List<NotificaPagamentoType> listaNotifiche = elenco.getNotificaPagamento();
		
//		String datospecificoRiscossione = getDatoSpecificoRiscossione ( tipoPagamento );
		
		for (Pagamento pagamento : pagamenti) {
			log.info(methodName, "elaborazione pagamento " + pagamento.getIuv() + " (" + (pagamento.getPagamentoSpontaneo()?"spontaneo":"atteso") + ")");
			System.out.println (methodName+"elaborazione pagamento "+ pagamento.getIuv() + " (" + (pagamento.getPagamentoSpontaneo()?"spontaneo":"atteso") + ")");
			if (registroElaborazioni.getNumPagamenti() == 1000) {
				log.debug(methodName, "raggiunto limite di 1000 record");
				System.out.println (methodName+ "raggiunto limite di 1000 record");
				break;
			}
			registroElaborazioni.getPagamenti().add(pagamento);
			registroElaborazioni.setNumPagamenti(registroElaborazioni.getNumPagamenti() + 1);
			registroElaborazioni.setImportoTotPagamenti(registroElaborazioni.getImportoTotPagamenti().add(pagamento.getImporto()));
			
			SoggettoType soggettoPagatore = creaSoggetto(pagamento.getPagatore());
			SoggettoType soggettoVersante = null;
			RegistroVersamenti ultimaRegistrazioneVersamento;
			try {
				ultimaRegistrazioneVersamento = jobFacade.ricercaUltimaRegistrazioneVersamento(pagamento.getIdPagamento(), StatoPagamento.findById(pagamento.getIdStatoCorrente()));
				if (ultimaRegistrazioneVersamento.getAnagraficaVersante() != null) {
					soggettoVersante = creaSoggetto(ultimaRegistrazioneVersamento.getAnagraficaVersante());
				}
			} catch (NoDataException nde) {
				log.debug(methodName, ExceptionUtils.getStackTrace(nde));
				System.out.println (methodName+ ExceptionUtils.getStackTrace(nde) );
				throw new RuntimeException("Registrazione pagamento non trovato per pagamento " + pagamento.getIdPagamento());
			}
			
			DatiTransazionePSPType datiPsp = new DatiTransazionePSPType();
			XMLGregorianCalendar dataEsitoPagamento;
			if ("MDPSERVICES-riceviRT".equals(ultimaRegistrazioneVersamento.getOrigineInserimento())) 
			{
				log.debug(methodName, "dati presi da rt");
				System.out.println (methodName+ "dati presi da rt" );
				/*rt*/
				try {
					setDoc(jobFacade.ricercaRt(pagamento.getIdPagamento(), StatoPagamento.findById(pagamento.getIdStatoCorrente())).getRtXml());
					log.debug(methodName, "Pagamento id: " + pagamento.getIdPagamento() + " - Importo: " + pagamento.getImporto());
					System.out.println (methodName+ "Pagamento id: " + pagamento.getIdPagamento() + " - Importo: " + pagamento.getImporto() );
					//CSI_PAG-383 setto l'importo dall'ultima RT ricevuta
					String importoStr = getNodes ( XmlField.importo );
                    if ( importoStr != null ) {
                        log.debug ( methodName, "IMPORTO TROVATO:" + importoStr );
                        System.out.println (methodName+  "IMPORTO TROVATO:" + importoStr );
                        pagamento.setImporto ( new BigDecimal ( importoStr ) );
                    }
                    
				} catch (SAXException | IOException | ParserConfigurationException | NoDataException e) {
					log.debug(methodName, ExceptionUtils.getStackTrace(e));
					System.out.println (methodName+ "errore lettura parametri. " + e.getMessage() );
				}

				try {
					TransazioneMdp transazioneMdp = jobFacade.ricercaTransazioneMdp(ultimaRegistrazioneVersamento.getIdTransazione());
					datiPsp.setIdPSP(transazioneMdp.getIdentificativoPsp());
					datiPsp.setRagioneSocialePSP(transazioneMdp.getRagioneSocialePsp());
					datiPsp.setTipoVersamento(transazioneMdp.getTipoVersamentoPsp());
					datiPsp.setIdFlussoRendicontazionePSP(null);
				} catch (NoDataException e) {
					log.debug(methodName, e.getMessage());
					System.out.println (methodName+ "errore lettura parametri. " + e.getMessage() );
				}
				
				/* letto da xml!!!
				 * try {
					RegistroVersamenti reg2 = jobFacade.ricercaUltimaRegistrazioneVersamento(pagamento.getIdPagamento(), 2);
					datiPsp.setDataOraAvvioTransazione(date2xmlGregorianCalandar(reg2.getDataOperazione()));
				} catch (NoDataException e) {
					log.debug(methodName, ExceptionUtils.getStackTrace(e));
				}*/
				datiPsp.setDataOraAvvioTransazione(string2xmlGregorianCalandar(getFirstNode()));
				datiPsp.setIUR(getNodes(XmlField.iur));
				datiPsp.setDataOraAutorizzazione(null);
				datiPsp.setTipoSicurezza(null);
				datiPsp.setImportoTransato(pagamento.getImporto());
				datiPsp.setImportoCommissioni(getNodesBigDecimal());
				dataEsitoPagamento = datiPsp.getDataOraAvvioTransazione();
			} else {
				/*ricevi esito*/
				log.debug(methodName, "dati presi da flusso esiti");
				
				EsitiRicevuti esito;
				try {
					esito = jobFacade.ricercaEsitiRicevutiPerIdPagamentoEStato(pagamento.getIdPagamento(), StatoPagamento.findById(pagamento.getIdStatoCorrente()));
				} catch (NoDataException e) {
					esito = new EsitiRicevuti();
				}
				
				datiPsp.setIdPSP(esito.getIdentificativoPsp());
				datiPsp.setRagioneSocialePSP(esito.getRagioneSocialePsp());
				datiPsp.setTipoVersamento("PO");
				datiPsp.setIdFlussoRendicontazionePSP(null);
				datiPsp.setDataOraAvvioTransazione(timestamp2xmlGregorianCalandar(esito.getDataPagamento()));
				datiPsp.setIUR(esito.getIur());
				datiPsp.setDataOraAutorizzazione(null);
				datiPsp.setTipoSicurezza(null);
				datiPsp.setImportoTransato(esito.getImporto());
				datiPsp.setImportoCommissioni(null);
				
				dataEsitoPagamento = timestamp2xmlGregorianCalandar(esito.getDataPagamento());
			}			
			NotificaPagamentoType notifica = new NotificaPagamentoType();
			notifica.setIdPosizioneDebitoria(pagamento.getCodicePagamentoRifEnte());
			/* Pagamento model emette IllegalArgumentException che viene ora gestita - BEGIN */
			String codiceAvviso;
			try {
				codiceAvviso = pagamento.getCodiceAvviso ();
			} catch ( IllegalArgumentException e ) {
				codiceAvviso = null;
			}
			notifica.setCodiceAvviso ( codiceAvviso );
			/* Pagamento model emette IllegalArgumentException che viene ora gestita - END */
			notifica.setAnnoDiRiferimento(pagamento.getAnnoRiferimento());
			//CSI_PAG-383 lo iuv e' sempre quello del pagamento
			notifica.setIUV(pagamento.getIuvRegistroVersamenti());
			//notifica.setIUV(pagamento.getIuv())
			notifica.setImportoPagato(pagamento.getImporto());
			notifica.setDataScadenza(date2xmlGregorianCalandar(pagamento.getDataScadenza()));
			if (StringUtils.isNotBlank(pagamento.getCausale())) {
				notifica.setDescrizioneCausaleVersamento(pagamento.getCausale());
			}
			notifica.setDataEsitoPagamento(dataEsitoPagamento);
			notifica.setSoggettoDebitore(soggettoPagatore);
			notifica.setSoggettoVersante(soggettoVersante);
			notifica.setDatiTransazionePSP(datiPsp);
			
			if (pagamento.getComponenti ()!= null && pagamento.getComponenti ().size ()>0)
			    {
			    System.out.println ("datiSpecifici Riscossione "+ pagamento.getComponenti ().get ( 0 ).getDatiSpecificiRiscossione () );
			    notifica.setDatiSpecificiRiscossione(pagamento.getComponenti ().get ( 0 ).getDatiSpecificiRiscossione ());
			    }
			else
			{
			    System.out.println ("datiSpecifici Riscossione non trovati in componente ");
			    notifica.setDatiSpecificiRiscossione("Dato non disponibile");
			}
			
//			
//			if(!StringUtils.isNotBlank(notifica.getDatiSpecificiRiscossione ())) {
//			    notifica.setDatiSpecificiRiscossione ( "9/000" );
//			}
			
			
			
			if (StringUtils.isNotBlank(pagamento.getNote())) {
				notifica.setNote(pagamento.getNote());
			}
			listaNotifiche.add(notifica);

		}
		TestataNotifichePagamentoType testata = new TestataNotifichePagamentoType();
		testata.setIdMessaggio(registroElaborazioni.getIdMessaggio());
		testata.setCFEnteCreditore(ente.getCodiceFiscale());
		testata.setCodiceVersamento(tipoPagamento.getCodiceVersamento());
		testata.setPagamentiSpontanei(pagamentoSpontaneo);
		testata.setNumeroPagamenti(registroElaborazioni.getNumPagamenti());
		testata.setImportoTotalePagamenti(registroElaborazioni.getImportoTotPagamenti());
		
		CorpoNotifichePagamentoType corpo = new CorpoNotifichePagamentoType();
		corpo.setElencoNotifichePagamento(elenco);
		
		TrasmettiNotifichePagamentoRequest request = new TrasmettiNotifichePagamentoRequest();
		request.setTestata(testata);
		request.setCorpoNotifichePagamento(corpo);
		
		log.info(methodName, XmlUtil.obj2Xml(request));
		System.out.println (methodName+ XmlUtil.obj2Xml(request) );
		
		ResultType result = callService(request);
		
		log.info(methodName, XmlUtil.obj2Xml(result));
		System.out.println (methodName+  XmlUtil.obj2Xml(result));
		
		aggiornaRegistroElaborazioniFine(jobFacade, registroElaborazioni, result);
		
		log.debug(methodName, XmlUtil.obj2Xml(result));	
		System.out.println (methodName+ XmlUtil.obj2Xml(result) );
	}
	
	private SoggettoType creaSoggetto(Anagrafica anagrafica) {
		SoggettoType soggetto = new SoggettoType();
		soggetto.setIdentificativoUnivocoFiscale(anagrafica.getCodiceFiscale());
		if (anagrafica.getFlagPersonaFisica()) {
			soggetto.setPersonaFisica(new PersonaFisicaType());
			soggetto.getPersonaFisica().setNome(anagrafica.getNome());
			soggetto.getPersonaFisica().setCognome(anagrafica.getCognome());
        } else {
            soggetto.setPersonaGiuridica ( new PersonaGiuridicaType () );
            soggetto.getPersonaGiuridica ().setRagioneSociale ( StringUtils.substring ( anagrafica.getRagioneSociale (), 0, 70 ) );
        }
        if ( StringUtils.isNotBlank ( anagrafica.getIndirizzo () ) ) {
            soggetto.setIndirizzo ( anagrafica.getIndirizzo () );
        }
        if ( StringUtils.isNotBlank ( anagrafica.getCivico () ) ) {
            soggetto.setCivico ( anagrafica.getCivico () );
        }
        if ( StringUtils.isNotBlank ( anagrafica.getCap () ) ) {
            soggetto.setCAP ( anagrafica.getCap () );
        }
        if ( StringUtils.isNotBlank ( anagrafica.getLocalita () ) ) {
            soggetto.setLocalita ( anagrafica.getLocalita () );
        }
        if ( StringUtils.isNotBlank ( anagrafica.getProvincia () ) ) {
            soggetto.setProvincia ( anagrafica.getProvincia () );
        }
        if ( StringUtils.isNotBlank ( anagrafica.getNazione () ) ) {
            soggetto.setNazione ( anagrafica.getNazione () );
        }
        if ( StringUtils.isNotBlank ( anagrafica.getEmail () ) ) {
            soggetto.setEMail ( anagrafica.getEmail ().trim () );
        }
		return soggetto;
	}

	private static RegistroElaborazioni inserisciRegistroElaborazioniInizio(JobFacade jobFacade, Ente ente, TipoPagamento tipoPagamento, boolean pagamentoSpontaneo) {
		Date data = new Date();
		RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni();
		registroElaborazioni.setDataInizio(new Timestamp(data.getTime()));
		registroElaborazioni.setOperazione("TrasmettiNotifichePagamento");
		registroElaborazioni.setEsito("elaborazione");
		registroElaborazioni.setIdEnte(ente.getIdEnte());
		registroElaborazioni.setIdTipoPagamento(tipoPagamento.getIdTipoPagamento());
		registroElaborazioni.setPagamentiSpontanei(pagamentoSpontaneo);
		registroElaborazioni.setIdMessaggio(getIdMessaggioRandom());
		registroElaborazioni.setNumPagamenti(0);
		registroElaborazioni.setImportoTotPagamenti(BigDecimal.ZERO);
		registroElaborazioni.setPagamenti(new ArrayList<Pagamento>());
		Long id = jobFacade.inserisciRegistroElaborazioni(registroElaborazioni);
		registroElaborazioni.setId(id);
		return registroElaborazioni;
	}
	
	private void aggiornaRegistroElaborazioniFine(JobFacade jobFacade, RegistroElaborazioni registroElaborazioni, ResultType result) {
		Date data = new Date();
		registroElaborazioni.setDataFine(new Timestamp(data.getTime()));
		registroElaborazioni.setMessageFault(result.getCodice() + " - " + result.getMessaggio());
		if (Integer.parseInt(result.getCodice()) < 100 ) {
			registroElaborazioni.setEsito("successo");
			jobFacade.aggiornaRegistroElaborazioni(registroElaborazioni, true);
			
		} else {
			registroElaborazioni.setEsito("errore");
			jobFacade.aggiornaRegistroElaborazioni(registroElaborazioni, false);
		}

		if (Integer.parseInt(result.getCodice()) != 0) {
			InviaMailTrasmissioneNotifiche inviaMail  = new InviaMailTrasmissioneNotifiche(mailFacade);
			if (Integer.parseInt(result.getCodice()) < 100) {
				inviaMail.inviaMailResponceWarning(registroElaborazioni, result);
			} else {
				inviaMail.inviaMailResponceError(registroElaborazioni, result);
			}
		}
			
	}
	
	private static void inserisciRegistroElaborazioniNoData(JobFacade jobFacade, Ente ente, TipoPagamento tipoPagamento) {
		Date data = new Date();
		RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni();
		registroElaborazioni.setDataInizio(new Timestamp(data.getTime()));
		registroElaborazioni.setDataFine(new Timestamp(data.getTime()));
		registroElaborazioni.setOperazione("TrasmettiNotifichePagamento");
		registroElaborazioni.setEsito("noData");
		
		if (ente == null) {
			registroElaborazioni.setMessageFault("Nessun ente abilitato all'invio delle notifiche dei pagamenti");
		} else { 
			registroElaborazioni.setIdEnte(ente.getIdEnte());
			if (tipoPagamento == null) {
				registroElaborazioni.setMessageFault("Nessuna tipologia di pagamento abilitata all'invio delle notifiche dei pagamenti per l'ente " + ente.getNome());
			} else {
				registroElaborazioni.setIdTipoPagamento(tipoPagamento.getIdTipoPagamento());
				registroElaborazioni.setMessageFault("Nessun pagamento per ente "+ente.getNome()+" e tipo pagamento "+tipoPagamento.getDescrizionePortale());
			}
		}
		jobFacade.inserisciRegistroElaborazioni(registroElaborazioni);
	}	
	
	private static String getIdMessaggioRandom() {
		return "TNP".concat(UUID.randomUUID().toString().replace("-", ""));
	}
	
	private ResultType callService(TrasmettiNotifichePagamentoRequest trasmettiNotifichePagamentoRequest) {
		URL urlService = null;
		try {
			urlService = new URL(url);
		} catch (MalformedURLException e) {
			System.exit(-3);
		}
		Sportello2EPaywso_Service service = new Sportello2EPaywso_Service(urlService);
		Sportello2EPaywso sportello2EPaywso = service.getSportello2EPaywsoSOAP();
		ResponseType responseType = sportello2EPaywso.trasmettiNotifichePagamento(trasmettiNotifichePagamentoRequest);
		return responseType.getResult();
	}
	
	private static XMLGregorianCalendar date2xmlGregorianCalandar(Date date) {
		if (date == null) {
			return null;
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param date stringa nel formato YYYY-MM-DD
	 * @return data in formato gregoriano
	 */
	private static XMLGregorianCalendar string2xmlGregorianCalandar(String date) {
		if (date == null) {
			return null;
		}
		String[] split = date.split("-");
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, Integer.parseInt(split[0]));
		gc.set(GregorianCalendar.MONTH, Integer.parseInt(split[1])-1);
		gc.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(split[2]));
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static XMLGregorianCalendar timestamp2xmlGregorianCalandar(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		try {
			Date date = new Date(timestamp.getTime());
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			return null;
		}
	}
	
	
//	private String getDatoSpecificoRiscossione ( TipoPagamento tipoPagamento) 
//	{
//	    try {
//	    TassonomiaAdapterClient client = new TassonomiaAdapterClient ( configurazioneFacade );
//	    DatiSpecificiRiscossioneInput request = client.buildDatiSpecificiRiscossioneInput ( tipoPagamento.getEpayTEnti ().getCodiceFiscale (), tipoPagamento.getCodiceVersamento () );
//	   
//	    DatiSpecificiRiscossioneOutput  dsr = client.getDatiSpecificiRiscossione ( request );
//
//	        Optional<DatiSpecificiRiscossione> trovato = dsr.getElencoDatiSpecifici ().stream ()
//	                        .filter ( new Predicate<DatiSpecificiRiscossione> () {
//
//	                            @Override
//	                            public boolean test ( DatiSpecificiRiscossione s ) {
//	                                // Cerco il dato specifico riscossione con anno accertamento e numero accertamento per evitare che l'equals vada in errore
//	                                if ( null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () ) ) {
//	                                    return ( null == tipoPagamento.getAnnoAccertamento () && StringUtils.isEmpty ( tipoPagamento.getNumeroAccertamento () ) );
//	                                }
//	                                return s.getAnnoAccertamento ().equals ( tipoPagamento.getAnnoAccertamento () != null? tipoPagamento.getAnnoAccertamento ().intValue () : new Integer ( 0 )) &&
//	                                                s.getNumeroAccertamento ().equals ( tipoPagamento.getNumeroAccertamento () );
//	                            }
//	                        } )
//	                        .findFirst ();
//	        if ( trovato.isPresent () ) {
//	            return trovato.get ().getCodice ();
//	            //                componente.setDatiSpecificiRiscossione ( trovato.get ().getCodice () );
//	            //                componente.setCodiceTributo ( trovato.get ().getCodiceTributo () );
//	        } else {
//	            // Cerco il DSR di default
//	            Optional<DatiSpecificiRiscossione> trovatoDefault = dsr.getElencoDatiSpecifici ().stream ()
//	                            .filter ( new Predicate<DatiSpecificiRiscossione> () {
//
//	                                @Override
//	                                public boolean test ( DatiSpecificiRiscossione s ) {
//	                                    return null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () );
//	                                }
//	                            } )
//	                            .findFirst ();
//	            if ( trovatoDefault.isPresent () ) {
//	                return trovatoDefault.get ().getCodice ();
//	                //                    componente.setDatiSpecificiRiscossione ( trovatoDefault.get ().getCodice () );
//	                //                    componente.setCodiceTributo ( trovatoDefault.get ().getCodiceTributo () );
//	            } else {
//	                CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
//	                throw new TassonomiaServiceException ( CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getCodice (),
//	                    ce.getCodice () + ", per la componente con anno e numero accertamento: " + tipoPagamento.getAnnoAccertamento ()
//	                    + " - " + tipoPagamento.getNumeroAccertamento () + " non sono presenti valori su Catalogo!");
//	            }
//	        }
//	    }  catch ( TassonomiaServiceException e ) { 
//	        log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
//	        System.out.println ( "Errore in fase di reperimento del dato specifico riscossione " + e.getMessage() );
////	        throw new RuntimeException ( e.getMessage (), e );
//	        return "";
//	    } catch ( Exception e ) { 
//	        log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
//	        System.out.println ( "Errore in fase di reperimento del dato specifico riscossione"  + e.getMessage() );
////	        throw new RuntimeException ( "Errore in fase di reperimento del dato specifico riscossione", e );
//	        return "";
//	    }
//	}

}
