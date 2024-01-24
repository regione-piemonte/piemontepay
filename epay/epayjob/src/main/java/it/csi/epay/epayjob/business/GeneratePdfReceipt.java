/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import it.csi.epay.epayjob.business.mail.InviaMailRicezioneQuietanza;
import it.csi.epay.epayjob.model.ErData;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayservices.model.QuietanzaDaElaborareDto;
import it.csi.epay.epayservices.model.TipoPagamento;

import javax.naming.NamingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class GeneratePdfReceipt extends RtXml {

	private final LogUtil log;

	private final JobFacade jobFacade;

	private final MailFacade mailFacade;

	private static final String ND = "n.d.";

	public GeneratePdfReceipt ( JobFacade jobFacade, MailFacade mailFacade ) {
		super ();

		log = new LogUtil ( this.getClass () );
		this.jobFacade = jobFacade;
		this.mailFacade = mailFacade;
	}

	public List<ErData> execute () throws NamingException, NoDataException {
		final String methodName = "execute";

		log.debugStart ( methodName );
		List<ErData> erDatas = new ArrayList<> ();
		List<EsitiRicevuti> esitiSenzaQuietanza;
		try {
			// recupera esiti senza quietanza dalla tabella epay_t_esiti_ricevuti
			esitiSenzaQuietanza = jobFacade.elencoEsitiSenzaQuietanza ();

		} catch ( NoDataException e ) {
			log.warn ( methodName, "Nessuna quietanza trovata." );
			System.out.println ( "Nessuna quietanza trovata." );
			log.debugEnd ( methodName );
			return Collections.emptyList ();
		}
		if ( null != esitiSenzaQuietanza && !esitiSenzaQuietanza.isEmpty () ) {
			for ( EsitiRicevuti er: esitiSenzaQuietanza ) {
				try {
					log.info ( methodName, "Elaborazione esito idEsito:" + er.getIdEsito () );
					System.out.println ( "Elaborazione esito idEsito:" + er.getIdEsito () );

					// recupera il pagamento tramite lo iuv dell'i-esimo record EsitiRicevuti
					Pagamento pagamento = jobFacade.getPagamento ( er.getIuv () );
					if ( null == pagamento ) {
						throw new Exception ( "Pagamento con iuv:" + er.getIuv () + " e idEsito:" + er.getIdEsito () + " non trovato" );
					}
					TipoPagamento tipoPagamento = pagamento.getTipoPagamento ();
					Ente ente = jobFacade.getEnteByCF ( tipoPagamento.getEpayTEnti ().getCodiceFiscale () );
					Integer codiceEsitoPagamento = er.getCodEsitoPagamento ();
					Anagrafica anagrafica = pagamento.getPagatore ();

					Map<ParamNameXPdf, Object> param = new EnumMap<> ( ParamNameXPdf.class );
					param.put ( ParamNameXPdf.A1_LOGO_ENTE, ente.getLogo () );
					param.put ( ParamNameXPdf.A2_LOGO_PAGOPA, jobFacade.getLogoEnte ( 0L ) );
					param.put ( ParamNameXPdf.B2_DESCRIZIONE_TASSA, pagamento.getTipoPagamento ().getDescrizionePortale () );
					param.put ( ParamNameXPdf.B3_STATO_TRANSAZIONE, codiceEsitoPagamento == 0 || codiceEsitoPagamento == 9
									? "Pagamento eseguito"
									: codiceEsitoPagamento == 2
													? "Pagamento eseguito parzialmente"
													: "Pagamento NON eseguito" );
					param.put ( ParamNameXPdf.C1_DESCRIZIONE_ENTE, null == ente.getNome () ? "non disponibile" : ente.getNome () );
					param.put ( ParamNameXPdf.C2_CF_ENTE, null == ente.getCodiceFiscale () ? "non disponibile" : ente.getCodiceFiscale () );
					param.put ( ParamNameXPdf.C3_IMPORTO, er.getImporto () );
			        /* Pagamento model emette IllegalArgumentException che viene ora gestita - BEGIN */
			        String codiceAvviso;
					try {
						codiceAvviso = pagamento.getCodiceAvviso ();
					} catch ( IllegalArgumentException e ) {
						codiceAvviso = null;
					}
					param.put ( ParamNameXPdf.C4_CODICE_AVVISO, codiceAvviso );
					/* Pagamento model emette IllegalArgumentException che viene ora gestita - END */
					param.put ( ParamNameXPdf.C5_IUV, er.getIuv () );
					param.put ( ParamNameXPdf.D1_NOME, null == anagrafica.getNome () || null == anagrafica.getCognome ()
									? "non disponibile"
									: anagrafica.getNome () + " " + anagrafica.getCognome () );
					param.put ( ParamNameXPdf.D2_CF, anagrafica.getCodiceFiscale () );
					param.put ( ParamNameXPdf.E1_ID_TRANSAZIONE, er.getIdTransazione () );
					param.put ( ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE, er.getRagioneSocialePsp () );
					SimpleDateFormat formatter = new SimpleDateFormat ( "dd/MM/yyyy hh:mm:ss" );
					if ( null != er.getDataOraOperazione () ) {
						String dateP = formatter.format ( er.getDataOraOperazione () );
						param.put ( ParamNameXPdf.E3_DATA_ORA_OPERAZIONE, dateP );
						param.put ( ParamNameXPdf.E3_DATA_ORA, dateP );
					} else {
						param.put ( ParamNameXPdf.E3_DATA_ORA_OPERAZIONE, ND );
					}
					if ( null != er.getDataPagamento () ) {
						formatter = new SimpleDateFormat ( "dd/MM/yyyy" );
						String dateP = formatter.format ( er.getDataPagamento () );
						if ( null != er.getDataPagamento () ) {
							param.put ( ParamNameXPdf.E3_DATA_ESITO_PAGAMENTO, dateP );
						} else {
							param.put ( ParamNameXPdf.E3_DATA_ESITO_PAGAMENTO, null );
						}
					} else {
						param.put ( ParamNameXPdf.E3_DATA_ESITO_PAGAMENTO, null );
					}

					param.put ( ParamNameXPdf.E4_IDENTIFICATIVO_UNIVOCO_RISCOSSIONE, er.getIur () );
					param.put ( ParamNameXPdf.E5_ESITO_SINGOLO_PAGAMENTO, er.getDescEsitoPagamento () );
					param.put ( ParamNameXPdf.C6_NOTE, pagamento.getNote () );
					param.put ( ParamNameXPdf.C6_CAUSALE, pagamento.getCausale () );
					// controllo valorizzazione ulteriori parametri (potrebbero non esserlo se il pagamento e' non-multibeneficiario)
					boolean existsPagamentoSecondario = false;
					PagamentoSecondario pagamentoSecondario = jobFacade.getPagamentoSecondario ( pagamento.getIdPagamento () );
					if ( null != pagamentoSecondario ) {
						if ( null != pagamentoSecondario.getTipoPagamento ().getEpayTEnti ().getCodiceFiscale () ) {
							// parametri per multibeneficiario (se presente)
							Ente enteSecondario = jobFacade.getEnteByCF ( pagamentoSecondario.getTipoPagamento ().getEpayTEnti ().getCodiceFiscale () );
							param.put ( ParamNameXPdf.F1_DESCRIZIONE_ENTE_SECONDARIO, enteSecondario.getNome () );
							param.put ( ParamNameXPdf.F2_CF_ENTE_SECONDARIO, enteSecondario.getCodiceFiscale () );
							param.put ( ParamNameXPdf.F3_DESCRIZIONE_TASSA_SECONDARIO, pagamentoSecondario.getTipoPagamento ().getDescrizionePortale () );
							param.put ( ParamNameXPdf.F4_IMPORTO_SECONDARIO, pagamentoSecondario.getImportoComplessivo () );
							param.put ( ParamNameXPdf.F4_IMPORTO_PRINCIPALE, pagamento.getImportoPrincipale () );
							existsPagamentoSecondario = true;
						}
					}

					byte [] quietanzaPdf = jobFacade.creaQuietanzaPdf ( param );
					ErData erData = new ErData ();
					erData.setPagamento ( pagamento );
					erData.setParam ( param );
					erData.setEr ( er );
					erData.setQuietanza ( quietanzaPdf );
					erDatas.add ( erData );

					Parametro inviaMailParam = jobFacade.ricercaParametro ( "job_generaPdfReceipt", "inviaMailRicezioneQuietanza" );
					if ( Boolean.parseBoolean ( inviaMailParam.getValore () ) ) {
						InviaMailRicezioneQuietanza mail = new InviaMailRicezioneQuietanza ( jobFacade, mailFacade );
						mail.execute ( erData );
					}
					// valorizza epay_t_quietanza_esito
					Long idQuietanza = jobFacade.inserisciNuovaQuietanza ( erData.getQuietanza () );
					log.info ( methodName, "idQuietanza inserita:" + idQuietanza );
					System.out.println ( "idQuietanza inserita:" + idQuietanza );

					// salva l'id della quietanza nella epay_t_esiti_ricevuti
					jobFacade.aggiornaEsitoRicevuto ( erData.getEr (), idQuietanza );
					log.info ( methodName, "Aggiornamento EsitoRicevuto avvenuto per idQuietanza:" + idQuietanza );
					System.out.println ( "Aggiornamento EsitoRicevuto avvenuto per idQuietanza:" + idQuietanza );

					/* prepara l'eventuale elaborazione (per ora solo archiviazione) della ricevuta di pagamento */

					String iuv = pagamento.getIuv ();
					String codiceFiscalePagatore = pagamento.getPagatore ().getCodiceFiscale ();
					Timestamp dataPagamento = erData.getEr ().getDataPagamento ();
					{
						log.info ( methodName, "Prepara elaborazione per ER(1).idEsito:" + er.getIdEsito () );
						System.out.println ( "Prepara elaborazione per ER(1).idEsito:" + er.getIdEsito () );
						String causale = pagamento.getCausale ();
						preparaElaborazione ( iuv, ente, codiceFiscalePagatore, causale, dataPagamento, idQuietanza, quietanzaPdf );
					}
					if ( existsPagamentoSecondario ) {
						// N.B. il pdf generato e' lo stesso del primo esito, ma viene salvato una seconda volta perche' afferente a un ente secondario 
						log.info ( methodName, "Prepara elaborazione per ER(2).idEsito:" + er.getIdEsito () );
						System.out.println ( "Prepara elaborazione per ER(2).idEsito:" + er.getIdEsito () );
						Ente ente2 = pagamentoSecondario.getTipoPagamento ().getEpayTEnti ();
						String causale2 = pagamentoSecondario.getCausale ();
						preparaElaborazione ( iuv, ente2, codiceFiscalePagatore, causale2, dataPagamento, idQuietanza, quietanzaPdf );
					}

				} catch ( Exception ex ) {
					log.error ( methodName, "Errore in fase di generazione della quietanze con idEsito:" + er.getIdEsito (), ex );
					System.err.println ( "Errore in fase di generazione della quietanze con idEsito:" + er.getIdEsito () );
				} finally {
					log.info ( methodName, "Fine Elaborazione esito con idEsito:" + er.getIdEsito () );
					System.out.println ( "Fine Elaborazione esito con idEsito:" + er.getIdEsito () );
				}
			}
		}
		log.debugEnd ( methodName );
		return erDatas;
	}

	private void preparaElaborazione ( String iuv, Ente ente, String cfPagatore, String causale, Timestamp dataPagamento, Long idQuietanza, byte [] pdf ) {
		final String methodName = "preparaElaborazione";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "iuv: " + iuv );
		log.info ( methodName, "idEnte: " + ente.getIdEnte () );
		log.info ( methodName, "cfPagatore: " + cfPagatore );
		log.info ( methodName, "causale: " + causale );
		log.info ( methodName, "dataPagamento: " + dataPagamento );
		log.info ( methodName, "idQuietanza: " + idQuietanza );
		log.info ( methodName, "pdf" + ( pdf != null ? ".length: " + pdf.length : " NULL" ) );

		// verifica se l'ente ha aderito a CittaFacile
		boolean adesioneCittaFacile = ente.getFlagAdesioneCittaFacile () != null && ente.getFlagAdesioneCittaFacile ().equals ( Boolean.TRUE );
		log.info ( methodName, "Adesione a CittaFacile:" + adesioneCittaFacile );
		System.out.println ( "Adesione a CittaFacile? " + adesioneCittaFacile );
		if ( adesioneCittaFacile ) {
			Long idEnte = ente.getIdEnte ();
			log.info ( methodName, "idEnte:" + idEnte );
			System.out.println ( "idEnte:" + idEnte );

			// verifica se il pagatore ha dato il consenso
			boolean consensoPagatore = jobFacade.existsConsensoPagatore ( idEnte, cfPagatore );
			log.info ( methodName, "Consenso:" + consensoPagatore + ", CF Pagatore:" + cfPagatore + ", idEnte:" + idEnte );
			System.out.println ( "Consenso:" + consensoPagatore + ", CF Pagatore:" + cfPagatore + ", idEnte:" + idEnte );
			if ( consensoPagatore ) {
				String codiceIpa = ente.getCodiceIpa ();
				log.info ( methodName, "codiceIpa:" + codiceIpa );
				System.out.println ( "codiceIpa:" + codiceIpa );

				// verifica se la quietanza non sia gia stata inserita tra quelle da elaborare
				boolean existsQuietanza = jobFacade.existsQuietanza ( iuv, codiceIpa );
				log.info ( methodName, "Quietanza da elaborare gia inserita:" + existsQuietanza );
				System.out.println ( "Quietanza da elaborare gia inserita? " + existsQuietanza );
				if ( !existsQuietanza ) {
					// Inserisce la quietanza
					log.info ( methodName, "prepara l'inserimento della quietanza da elaborare" );

					QuietanzaDaElaborareDto quietanzaDaElaborare = new QuietanzaDaElaborareDto ();
					quietanzaDaElaborare.setFruitoreEsterno ( "Città Facile" );
					quietanzaDaElaborare.setTipoElaborazione ( "INVIO_DOCME" );
					quietanzaDaElaborare.setDataInserimento ( new Date () );
					quietanzaDaElaborare.setInviare ( Boolean.TRUE );
					quietanzaDaElaborare.setIuv ( iuv );
					quietanzaDaElaborare.setCausalePagamento ( causale );
					quietanzaDaElaborare.setCfCittadino ( cfPagatore );
					quietanzaDaElaborare.setTerminiScaduti ( Boolean.FALSE );
					quietanzaDaElaborare.setIdQuietanzaEsito ( idQuietanza );
					quietanzaDaElaborare.setRicevutaPdf ( pdf );
					quietanzaDaElaborare.setDataInizioPagamento ( dataPagamento );
					quietanzaDaElaborare.setCodiceIpa ( codiceIpa );
					jobFacade.inserisciQuietanza ( quietanzaDaElaborare );

					log.info ( methodName, "Quietanza da elaborare inserita:" + quietanzaDaElaborare );
					System.out.println ( "Quietanza da elaborare inserita:" + quietanzaDaElaborare );

				} else {
					log.info ( methodName, "Non inserisce la quietanza da elaborare in quanto gia presente per iuv:" + iuv + " e codiceIpa:" + codiceIpa );
					System.out.println ( "Non inserisce la quietanza da elaborare in quanto gia presente per iuv:" + iuv + " e codiceIpa:" + codiceIpa );
				}
			}
		}
		log.info ( methodName, "END" );
	}
}
