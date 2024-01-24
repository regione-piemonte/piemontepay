/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import it.csi.epay.epayservices.integration.db.entities.EpayTRegistroVersamenti;
import it.csi.epay.epayservices.integration.db.entities.EpayTRt;
import it.csi.epay.epayservices.integration.db.entities.EpayTRtPdf;
import it.csi.epay.epayservices.integration.db.entities.EpayTRtXml;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Attached;
import it.csi.epay.epayservices.model.CodiceEsitoPagamento;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Mail;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.QuietanzaDaElaborareDto;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.utilities.PdfGenerator;


@Stateless ( name = "RtManager", mappedName = "RtManager" )
public class RtManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	PdfGenerator pdfGenerator;

	@EJB
	private MailFacade mailFacade;

	@EJB
	private EnteManager enteManager;

	@EJB
	private PagamentoManager pagamentoManager;

	@EJB
	private PagamentoSecondarioManager pagamentoSecondarioManager;

	@EJB
	private RegistroVersamentiManager registroVersamentiManager;

	@EJB
	private ElaborazioneManager elaborazioneManager;

	static final Integer idStatoPagamentoIN_ATTESA_SECONDA_RT = StatoPagamento.IN_ATTESA_SECONDA_RT.getId ();

	static final Integer idStatoPagamentoSUCCESSO = StatoPagamento.SUCCESSO.getId ();

	static final Integer idStatoPagamentoFALLITO = StatoPagamento.FALLITO.getId ();

	static final Integer CODICE_ESITO_PAGAMENTO_ESEGUITO = CodiceEsitoPagamento.PAGAMENTO_ESEGUITO.getId ();

	public Long inserisci ( Rt rt ) {
		EpayTRt tRt = map ( rt, EpayTRt.class );
		tRt.setIdRt ( null );
		tRt.setEpayTRegistroVersamenti ( entityManager.find ( EpayTRegistroVersamenti.class, rt.getIdRegistro () ) );
		entityManager.persist ( tRt );
		return tRt.getIdRt ();
	}

	@TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
	public void savePdf ( final Long id, final byte [] rt_pdf ) {
		EpayTRt tRt = entityManager.find ( EpayTRt.class, id );
		tRt.setRicevutaPdf ( rt_pdf );
	}

	public byte [] readXml ( final Long id ) {
		return entityManager.find ( EpayTRt.class, id ).getRtXml ();
	}

	@TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
	public List<Rt> elencoRtSenzaRicevutaPdf () {
		TypedQuery<EpayTRt> query = entityManager.createNamedQuery ( "EpayTRt.elencoRtSenzaRicevutaPdf", EpayTRt.class );
		query.setMaxResults ( 50 );
		List<EpayTRt> tRts = query.getResultList ();
		List<Rt> rts = new ArrayList<> ();
		for ( EpayTRt tRt: tRts ) {
			Rt rt = map ( tRt, Rt.class );
			rt.setIdRegistro ( tRt.getEpayTRegistroVersamenti ().getIdRegistro () );
			rts.add ( rt );
		}
		return rts;
	}

	@TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
	public Rt getRtInAttesaSecondaRT ( String iuv, Long idRegistro ) {
		TypedQuery<EpayTRt> query = entityManager.createNamedQuery ( "EpayTRt.getRtInAttesaSecondaRT", EpayTRt.class );
		query.setParameter ( "iuv", iuv );
		query.setParameter ( "idRegistro", idRegistro );
		EpayTRt tRt = query.getSingleResult ();
		Rt rt = map ( tRt, Rt.class );
		rt.setIdRegistro ( tRt.getEpayTRegistroVersamenti ().getIdRegistro () );
		return rt;
	}

	@TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
	public void generatePdfRt ( Rt rt, Map<String, XPathExpression> xPathExpressions, boolean flagInviaMailRicezioneRt, Properties mailRows ) {
		final String methodName = "generatePdfRt";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "idRt:" + rt.getIdRt ().toString () );
		log.info ( methodName, "idTransazione:" + rt.getIdTransazione () );
		log.info ( methodName, "idApplicazione:" + rt.getIdApplicazione () );
		log.info ( methodName, "idRegistro: " + rt.getIdRegistro ().toString () );

		boolean sendMail = false;
		try {
			byte [] rt_pdf = null;

			// eventuali per 2rt
			Map<ParamNameXPdf, Object> param2Rt = null;
			Rt rt2 = null;
			byte [] rt2_pdf = null;
			PagamentoSecondario pagamento2 = null;

			// ottiene il pagamento collegato alla registro versamenti
			Map<ParamNameXPdf, Object> param = new EnumMap<> ( ParamNameXPdf.class );
			Pagamento pagamento = pagamentoManager.getPagamentoByIdRegistroVersamento ( rt.getIdRegistro () ); // modificato per migliorare le performance.

			Integer idStatoCorrente = pagamento.getIdStatoCorrente ();
			if ( idStatoPagamentoIN_ATTESA_SECONDA_RT.equals ( idStatoCorrente ) ) {
				// se lo stato del pagamento e' in attesa di seconda RT non deve fare il pdf (per la seconda RT - n.d.r. [ms])
				log.info ( methodName, "statoCorrente:" + StatoPagamento.IN_ATTESA_SECONDA_RT.getDescrizione () );

				rt_pdf = creaPdfFromRt ( rt, xPathExpressions, pagamento, param );
				rt.setRicevutaPdf ( rt_pdf );
				this.savePdf ( rt.getIdRt (), rt_pdf );

			} else if ( idStatoPagamentoSUCCESSO.equals ( idStatoCorrente ) || idStatoPagamentoFALLITO.equals ( idStatoCorrente ) ) {
				// verifica nella registro versamenti se il pagamento e' passato dallo stato in attesa di RT
				log.info ( methodName, "statoCorrente:" + ( StatoPagamento.SUCCESSO.getId ().equals ( idStatoCorrente )
								? StatoPagamento.SUCCESSO.getDescrizione ()
								: StatoPagamento.FALLITO.getDescrizione () ) );

				RegistroVersamenti registroVersamenti = registroVersamentiManager.ricercaByIdPagamentoInAttesaSecondaRT ( pagamento.getIdPagamento () );
				log.info ( methodName, "registroVersamenti:" + registroVersamenti );
				if ( registroVersamenti != null ) {
					// in caso affermativo recupera le due RT
					try {
						// PRIMA RT stato successo, crea PDF
						log.info ( methodName, "PRIMA RT" );
						rt_pdf = creaPdfFromRt ( rt, xPathExpressions, pagamento, param );
						rt.setRicevutaPdf ( rt_pdf );
						this.savePdf ( rt.getIdRt (), rt_pdf );

						// SECONDA RT stato in attesa di seconda RT, recupera il pdf dal db (se e' gia' stato generato)
						log.info ( methodName, "SECONDA RT" );
						rt2 = getRtInAttesaSecondaRT ( pagamento.getIuv (), registroVersamenti.getIdRegistro () );
						rt2_pdf = rt2.getRicevutaPdf ();
						//
						// se il pdf non era ancora stato generato, lo fa ora
						if ( rt2_pdf == null ) {
							log.info ( methodName, "genera il pdf della seconda RT" );

							// N.B. scorrendo la lista, ha processato prima l'ok di quello in attesa di seconda RT
							param2Rt = new EnumMap<> ( ParamNameXPdf.class );
							rt2_pdf = creaPdfFromRt ( rt2, xPathExpressions, pagamento, param2Rt );

							rt2.setRicevutaPdf ( rt2_pdf );
							this.savePdf ( rt2.getIdRt (), rt2_pdf );

						} else {
							log.info ( methodName, "il pdf della seconda RT era gia stato generato" );
						}

						sendMail = true;
						pagamento2 = pagamentoSecondarioManager.getPagamentoSecondario ( pagamento.getIdPagamento () );

					} catch ( NoResultException e ) {
						// questo non dovrebbe capitare
						log.warn ( methodName, "eccezione non prevista", e );
					}
				} else { // caso vecchio
					rt_pdf = creaPdfFromRt ( rt, xPathExpressions, pagamento, param );
					rt.setRicevutaPdf ( rt_pdf );
					this.savePdf ( rt.getIdRt (), rt_pdf );
					sendMail = true;
				}
			} else {
				log.info ( methodName, "idStatoCorrente:" + idStatoCorrente + " non trattato per generare pdf" );
			}

			if ( flagInviaMailRicezioneRt && StringUtils.isNotBlank ( pagamento.getPagatore ().getEmail () ) ) {
				if ( sendMail ) {
					inviaMailRicezioneRt ( param, rt, param2Rt, rt2, pagamento, mailRows );
				}
			}

			/* prepara l'eventuale elaborazione (per ora solo archiviazione) delle ricevute di pagamento */
			log.info ( methodName, "inserisce le ricevute di pagamento da elaborare - BEGIN" );

			String codiceFiscalePagatore = pagamento.getPagatore ().getCodiceFiscale ();
			String iuv = pagamento.getIuv ();
			if ( rt.getRicevutaPdf () != null ) {
				log.info ( methodName, "prepara elaborazione per RT(1).idRt:" + rt.getIdRt () );
				Ente ente = pagamento.getEnte ();
				String causale = pagamento.getCausale ();
				preparaElaborazione ( iuv, ente, codiceFiscalePagatore, causale, rt );
			}
			else
			{
			    log.info ( methodName, "non presente rt per RT(1).idRt:" + rt.getIdRt () );
			}
			if ( rt2!= null && rt2.getRicevutaPdf () != null && pagamento2 != null ) {
				log.info ( methodName, "prepara elaborazione per RT(2).idRt:" + rt2.getIdRt () );
				Ente ente2 = pagamento2.getTipoPagamento ().getEpayTEnti ();
				String causale2 = pagamento2.getCausale ();
				preparaElaborazione ( iuv, ente2, codiceFiscalePagatore, causale2, rt2 );
			}
			else
            {
                log.info ( methodName, "non presente rt2 per RT(1).idRt:" + rt.getIdRt () );
            }
			
			log.info ( methodName, "inserisce le ricevute di pagamento da elaborare - END" );

		} catch ( Throwable e ) {
			log.error ( methodName, e );

		} finally {
			log.info ( methodName, "END" );
		}
	}

	private void preparaElaborazione ( String iuv, Ente ente, String codiceFiscalePagatore, String causale, Rt rt ) {
		final String methodName = "preparaElaborazione";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "iuv: " + iuv );
		log.info ( methodName, "idEnte: " + ente.getIdEnte () );
		log.info ( methodName, "codiceFiscalePagatore: " + codiceFiscalePagatore );
		log.info ( methodName, "causale: " + causale );
		log.info ( methodName, "idRt: " + rt.getIdRt () );

		// verifica se il pagamento risulta eseguito
		boolean isPagamentoEseguito = CODICE_ESITO_PAGAMENTO_ESEGUITO.equals ( rt.getCodEsitoPagamento () );
		log.info ( methodName, "isPagamentoEseguito:" + isPagamentoEseguito );
		if ( isPagamentoEseguito ) {
			// verifica se l'ente ha aderito a CittaFacile
			boolean adesioneCittaFacile = ente.getFlagAdesioneCittaFacile () != null && ente.getFlagAdesioneCittaFacile ().equals ( Boolean.TRUE );
			log.info ( methodName, "adesioneCittaFacile:" + adesioneCittaFacile );
			if ( adesioneCittaFacile ) {
				Long idEnte = ente.getIdEnte ();

				// verifica se il pagatore ha dato il consenso
				boolean consensoPagatore = elaborazioneManager.existsConsensoPagatore ( idEnte, codiceFiscalePagatore );
				log.info ( methodName, "consensoPagatore:" + consensoPagatore + ", CF Pagatore:" + codiceFiscalePagatore + ", idEnte:" + idEnte );
				if ( consensoPagatore ) {
					String codiceIpa = ente.getCodiceIpa ();

					// verifica se la quietanza non sia gia stata inserita tra quelle da elaborare
					boolean existsQuietanza = elaborazioneManager.existsQuietanza ( iuv, codiceIpa );
					log.info ( methodName, "existsQuietanza:" + existsQuietanza );
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
						quietanzaDaElaborare.setCfCittadino ( codiceFiscalePagatore );
						quietanzaDaElaborare.setTerminiScaduti ( Boolean.FALSE );
						quietanzaDaElaborare.setIdRt ( rt.getIdRt () );
						quietanzaDaElaborare.setRicevutaPdf ( rt.getRicevutaPdf () );
						quietanzaDaElaborare.setDataInizioPagamento ( rt.getDataoraMsgRicevuta () );
						quietanzaDaElaborare.setCodiceIpa ( codiceIpa );
						elaborazioneManager.inserisciQuietanza ( quietanzaDaElaborare );

						log.info ( methodName, "quietanzaDaElaborare inserita:" + quietanzaDaElaborare );

					} else {
						log.info ( methodName, "non inserisce la quietanza da elaborare in quanto gia presente per iuv:" + iuv + " e codiceIpa:" + codiceIpa );
					}
				}
			}
		}
		log.info ( methodName, "END" );
	}

	private byte [] creaPdfFromRt ( Rt rt, Map<String, XPathExpression> xPathExpressions, Pagamento pagamento, Map<ParamNameXPdf, Object> param ) {
		final String methodName = "creaPdfFromRt";
		log.info ( methodName, "BEGIN" );

		byte [] rtPdf = null;

		try {
			byte [] rt_xml = this.readXml ( rt.getIdRt () );
			Document docRt = DocumentBuilderFactory.newInstance ().newDocumentBuilder ().parse ( new ByteArrayInputStream ( rt_xml ) );
			if ( docRt == null ) {
				log.warn ( methodName, "PARSED XML DOCUMENT IS NULL: " + new String ( rt_xml ) );
			} else if ( log.isDebugEnabled () ) {
				log.debug ( methodName, "INPUT XML DOCUMENT IS: " + new String ( rt_xml ) );
			}

			String cfEnte = getNode ( "cfEnte", docRt, xPathExpressions );
			if ( cfEnte == null ) {
				log.warn ( methodName, "CF ENTE IS NULL FOR RT " + rt.getIdRt () );
			} else {
				Ente ente = enteManager.getByCF ( cfEnte );
				String idTransazione = getNode ( "msgRichiesta", docRt, xPathExpressions );
				String nomeEnte = getNode ( "nomeEnte", docRt, xPathExpressions );
				String nomePSP = getNode ( "nomePSP", docRt, xPathExpressions );
				String dataOra = convertDateTime ( getNode ( "dataOra", docRt, xPathExpressions ) );
				String cfPagatore = getNode ( "cfPagatore", docRt, xPathExpressions );
				String nomePagatore = getNode ( "nomePagatore", docRt, xPathExpressions );
				BigDecimal importo = new BigDecimal ( getNode ( "importo", docRt, xPathExpressions ) );
				String iuv = getNode ( "iuv", docRt, xPathExpressions );
				String codiceEsitoPagamento = getNode ( "codiceEsitoPagamento", docRt, xPathExpressions );
				String iur = getNodes ( "iur", docRt, xPathExpressions );
				String esitoSingoloPagamento = getNodes ( "esitoSingoloPagamento", docRt, xPathExpressions );
				String dataEsito = convertDate ( getNodes ( "dataEsitoSingoloPagamento", docRt, xPathExpressions ) );

				param.put ( ParamNameXPdf.A1_LOGO_ENTE, ente.getLogo () );
				param.put ( ParamNameXPdf.A2_LOGO_PAGOPA, enteManager.getLogo ( 0L ) );
				param.put ( ParamNameXPdf.B2_DESCRIZIONE_TASSA, pagamento.getTipoPagamento ().getDescrizionePortale () );
				param.put ( ParamNameXPdf.B3_STATO_TRANSAZIONE, "0".equals ( codiceEsitoPagamento )
								? "Pagamento eseguito"
								: "2".equals ( codiceEsitoPagamento )
												? "Pagamento eseguito parzialmente"
												: "Pagamento NON eseguito" );
				param.put ( ParamNameXPdf.C1_DESCRIZIONE_ENTE, nomeEnte );
				param.put ( ParamNameXPdf.C2_CF_ENTE, cfEnte );
				param.put ( ParamNameXPdf.C3_IMPORTO, importo );
				/* Pagamento model emette IllegalArgumentException che viene ora gestita - BEGIN */
				String codiceAvviso;
				try {
					codiceAvviso = pagamento.getCodiceAvviso ();
				} catch ( IllegalArgumentException e ) {
					codiceAvviso = null;
				}
				param.put ( ParamNameXPdf.C4_CODICE_AVVISO, codiceAvviso );
				/* Pagamento model emette IllegalArgumentException che viene ora gestita - END */
				param.put ( ParamNameXPdf.C5_IUV, iuv );
				param.put ( ParamNameXPdf.D1_NOME, nomePagatore );
				param.put ( ParamNameXPdf.D2_CF, cfPagatore );
				param.put ( ParamNameXPdf.E1_ID_TRANSAZIONE, idTransazione );
				param.put ( ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE, nomePSP );
				param.put ( ParamNameXPdf.E3_DATA_ORA, dataOra );
				param.put ( ParamNameXPdf.E4_IDENTIFICATIVO_UNIVOCO_RISCOSSIONE, iur );
				param.put ( ParamNameXPdf.E5_ESITO_SINGOLO_PAGAMENTO, esitoSingoloPagamento );
				param.put ( ParamNameXPdf.E3_DATA_ESITO_PAGAMENTO, dataEsito );
				param.put ( ParamNameXPdf.C6_NOTE_PAGAMENTO, pagamento.getNote () );
				param.put ( ParamNameXPdf.C6_CAUSALE, pagamento.getCausale () );

				rtPdf = pdfGenerator.creaRicevutaPdf ( param );
			}
		} catch ( XPathExpressionException e ) {
			log.error ( methodName, e );
		} catch ( Throwable e ) {
			log.error ( methodName, e );
		} finally {
			log.info ( methodName, "END" );
		}
		return rtPdf;
	}

	private String getNode ( String nome, Object item, Map<String, XPathExpression> xPathExpressions ) throws XPathExpressionException {
		Element element = (Element) xPathExpressions.get ( nome ).evaluate ( item, XPathConstants.NODE );
		if ( element == null ) {
			log.warn ( "getNode", String.format ( "XML node not found for parameters %s, %s",
				nome != null ? nome.toString () : "NULL",
				item != null ? item.toString () : "NULL" ) );
			return null;
		}
		return element.getTextContent ();
	}

	private String getNodes ( String nome, Object item, Map<String, XPathExpression> xPathExpressions ) throws XPathExpressionException {
		NodeList nodes = (NodeList) xPathExpressions.get ( nome ).evaluate ( item, XPathConstants.NODESET );
		StringBuffer sb = new StringBuffer ();
		for ( int i = 0; i < nodes.getLength (); i++ ) {
			if ( i != 0 ) {
				sb.append ( " - " );
			}
			sb.append ( ( (Element) nodes.item ( i ) ).getTextContent () );
		}
		return sb.toString ();
	}

	public Rt ricercaRtByIdRegistro ( final Long idRegistro ) throws NoDataException {
		try {
			TypedQuery<EpayTRt> query = entityManager.createNamedQuery ( "EpayTRt.ricercaRtByIdRegistro", EpayTRt.class );
			query.setParameter ( "idRegistro", idRegistro );
			EpayTRt tRt = query.getSingleResult ();
			return map ( tRt, Rt.class );
		} catch ( NoResultException e ) {
			throw new NoDataException ( "Rt non trovata (id registro = " + idRegistro + ")" );
		}
	}

	public Rt ricercaRtXmlByIdRegistro ( final Long idRegistro, final String codiceFiscaleEnte ) throws NoDataException {
		try {
			TypedQuery<EpayTRtXml> query = entityManager.createNamedQuery ( "EpayTRtXml.ricercaRtByIdRegistroAndCodiceFiscaleEnte", EpayTRtXml.class );
			query.setParameter ( "idRegistro", idRegistro );
			query.setParameter ( "codiceFiscaleEnte", codiceFiscaleEnte );
			EpayTRtXml tRt = query.getSingleResult ();
			return map ( tRt, Rt.class );
		} catch ( NoResultException e ) {
			throw new NoDataException ( "Rt non trovata (id registro = " + idRegistro + ")" );
		}
	}

	public Rt ricercaRtPdfByIdRegistro ( final Long idRegistro, final String codiceFiscaleEnte ) throws NoDataException {
		try {
			TypedQuery<EpayTRtPdf> query = entityManager.createNamedQuery ( "EpayTRtPdf.ricercaRtByIdRegistroAndCodiceFiscaleEnte", EpayTRtPdf.class );
			query.setParameter ( "idRegistro", idRegistro );
			query.setParameter ( "codiceFiscaleEnte", codiceFiscaleEnte );
			EpayTRtPdf tRt = query.getSingleResult ();
			return map ( tRt, Rt.class );
		} catch ( NoResultException e ) {
			throw new NoDataException ( "Rt non trovata (id registro = " + idRegistro + ")" );
		}
	}

	public Rt ricercaRtByIuv ( final String iuv ) throws NoDataException {
		try {
			TypedQuery<EpayTRt> query = entityManager.createNamedQuery ( "EpayTRt.ricercaRtByIuv", EpayTRt.class );
			query.setParameter ( "iuv", iuv );
			List<EpayTRt> tRts = query.getResultList ();
			if ( !CollectionUtils.isEmpty ( tRts ) ) {
				return map ( tRts.get ( 0 ), Rt.class );
			} else {
				return null;
			}
		} catch ( NoResultException | NullPointerException e ) {
			throw new NoDataException ( "Rt non trovata (iuv = " + iuv + ")" );
		}
	}

	private String convertDateTime ( String dateIn ) {
		if ( dateIn == null || dateIn.trim ().isEmpty () ) {
			return null;
		}
		SimpleDateFormat sdfIn = new SimpleDateFormat ( "yyyy-MM-dd'T'HH:mm:ss" );
		SimpleDateFormat sdfOut = new SimpleDateFormat ( "dd/MM/yyyy HH:mm:ss" );
		String dateOut = "";
		try {
			Date date = sdfIn.parse ( dateIn );
			dateOut = sdfOut.format ( date );
		} catch ( ParseException e ) {
			System.out.println ( "Exception " + e );
		}
		return dateOut;
	}

	private String convertDate ( String dateIn ) {
		if ( dateIn == null || dateIn.trim ().isEmpty () ) {
			return null;
		}
		SimpleDateFormat sdfIn = new SimpleDateFormat ( "yyyy-MM-dd" );
		SimpleDateFormat sdfOut = new SimpleDateFormat ( "dd/MM/yyyy" );
		String dateOut = "";
		try {
			Date date = sdfIn.parse ( dateIn );
			dateOut = sdfOut.format ( date );
		} catch ( ParseException e ) {
			System.out.println ( "Exception " + e );
		}
		return dateOut;
	}

	private void inviaMailRicezioneRt ( Map<ParamNameXPdf, Object> param, Rt rt, Map<ParamNameXPdf, Object> param2rt, Rt rt2rt, Pagamento pagamento,
		Properties mailRows ) {
		final String methodName = "inviaMailRicezioneRt";
		log.info ( methodName, "BEGIN - executed at " + new Date () );

		String iuv;
		String esitoPagamento;
		String ente;
		String cfEnte;
		String tipoPagamento;
		String idTRansazione;
		String codiceAvviso;
		String iuvTransazione;
		String ragioneSocialePSP;
		String dataOra;
		String importo;

		StringBuilder sb;
		Attached mailAttachedPdf;
		Attached mailAttachedXml;
		Attached mailAttachedPdf2rt = null;
		Attached mailAttachedXml2rt = null;
		try {
			iuv = (String) param.get ( ParamNameXPdf.C5_IUV );
			esitoPagamento = rt.getDescEsitoPagamento ();
			ente = (String) param.get ( ParamNameXPdf.C1_DESCRIZIONE_ENTE );
			cfEnte = (String) param.get ( ParamNameXPdf.C2_CF_ENTE );
			tipoPagamento = (String) param.get ( ParamNameXPdf.B2_DESCRIZIONE_TASSA );
			idTRansazione = (String) param.get ( ParamNameXPdf.E1_ID_TRANSAZIONE );
			codiceAvviso = (String) param.get ( ParamNameXPdf.C4_CODICE_AVVISO );
			iuvTransazione = rt.getIuv ();
			ragioneSocialePSP = (String) param.get ( ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE );
			dataOra = (String) param.get ( ParamNameXPdf.E3_DATA_ORA );
			importo = ( (BigDecimal) param.get ( ParamNameXPdf.C3_IMPORTO ) ).toPlainString ();

			sb = new StringBuilder ();
			sb.append ( getMessaggio ( mailRows, "riga1" ) );
			sb.append ( getMessaggio ( mailRows, "riga2", iuv ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "esito", esitoPagamento ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "dati" ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "dati01", ente ) );
			sb.append ( getMessaggio ( mailRows, "dati02", cfEnte ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "dati03", tipoPagamento ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "dati04", idTRansazione ) );
			if ( StringUtils.isNotBlank ( codiceAvviso ) ) {
				sb.append ( getMessaggio ( mailRows, "dati05", codiceAvviso ) );
			}
			sb.append ( getMessaggio ( mailRows, "dati06", iuvTransazione ) );
			sb.append ( getMessaggio ( mailRows, "dati07", ragioneSocialePSP ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "dati08", dataOra ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "dati09", importo ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "saluti" ) );
			sb.append ( "\n" );
			sb.append ( getMessaggio ( mailRows, "firma" ) );

			mailAttachedPdf = new Attached ();
			mailAttachedPdf.setName ( "rt_" + rt.getIuv () + ".pdf" );
			mailAttachedPdf.setType ( Attached.MimeType.PDF );
			mailAttachedPdf.setData ( rt.getRicevutaPdf () );

			mailAttachedXml = new Attached ();
			mailAttachedXml.setName ( "rt_" + rt.getIuv () + ".xml" );
			mailAttachedXml.setType ( Attached.MimeType.XML );
			mailAttachedXml.setData ( rt.getRtXml () );

			// controllo se c'è anche il secondo RT, caso di doppi allegati
			if ( null != param2rt ) {
				iuv = (String) param2rt.get ( ParamNameXPdf.C5_IUV );
				esitoPagamento = rt2rt.getDescEsitoPagamento ();
				ente = (String) param2rt.get ( ParamNameXPdf.C1_DESCRIZIONE_ENTE );
				cfEnte = (String) param2rt.get ( ParamNameXPdf.C2_CF_ENTE );
				tipoPagamento = (String) param2rt.get ( ParamNameXPdf.B2_DESCRIZIONE_TASSA );
				idTRansazione = (String) param2rt.get ( ParamNameXPdf.E1_ID_TRANSAZIONE );
				codiceAvviso = (String) param2rt.get ( ParamNameXPdf.C4_CODICE_AVVISO );
				iuvTransazione = rt2rt.getIuv ();
				ragioneSocialePSP = (String) param2rt.get ( ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE );
				dataOra = (String) param2rt.get ( ParamNameXPdf.E3_DATA_ORA );
				importo = ( (BigDecimal) param2rt.get ( ParamNameXPdf.C3_IMPORTO ) ).toPlainString ();

				sb.append ( getMessaggio ( mailRows, "riga1" ) );
				sb.append ( getMessaggio ( mailRows, "riga2", iuv ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "esito", esitoPagamento ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "dati" ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "dati01", ente ) );
				sb.append ( getMessaggio ( mailRows, "dati02", cfEnte ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "dati03", tipoPagamento ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "dati04", idTRansazione ) );
				if ( StringUtils.isNotBlank ( codiceAvviso ) ) {
					sb.append ( getMessaggio ( mailRows, "dati05", codiceAvviso ) );
				}
				sb.append ( getMessaggio ( mailRows, "dati06", iuvTransazione ) );
				sb.append ( getMessaggio ( mailRows, "dati07", ragioneSocialePSP ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "dati08", dataOra ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "dati09", importo ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "saluti" ) );
				sb.append ( "\n" );
				sb.append ( getMessaggio ( mailRows, "firma" ) );

				mailAttachedPdf2rt = new Attached ();
				mailAttachedPdf.setName ( "rt2rt_" + rt.getIuv () + ".pdf" );
				mailAttachedPdf.setType ( Attached.MimeType.PDF );
				mailAttachedPdf.setData ( rt.getRicevutaPdf () );

				mailAttachedXml2rt = new Attached ();
				mailAttachedXml.setName ( "rt2rt_" + rt.getIuv () + ".xml" );
				mailAttachedXml.setType ( Attached.MimeType.XML );
				mailAttachedXml.setData ( rt.getRtXml () );
			}

			Mail mail = new Mail ();
			mail.setTo ( pagamento.getPagatore ().getEmail () );
			mail.setSubject ( getMessaggio ( mailRows, "oggetto", iuv ) );
			mail.setText ( sb.toString () );
			mail.getAttachedFiles ().add ( mailAttachedPdf );
			mail.getAttachedFiles ().add ( mailAttachedXml );
			if ( null != param2rt ) {
				mail.getAttachedFiles ().add ( mailAttachedPdf2rt );
				mail.getAttachedFiles ().add ( mailAttachedXml2rt );
			}
			mailFacade.inviaMail ( mail );

		} catch ( AddressException e ) {
			e.printStackTrace ();
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace ();
		} catch ( MessagingException e ) {
			e.printStackTrace ();
		} finally {
			log.info ( methodName, "END" );
		}
	}

	private String getMessaggio ( Properties mailRows, String riga, String... parametri ) {
		try {
			String row = mailRows.getProperty ( riga );
			int i = 0;
			for ( String parametro: parametri ) {
				row = row.replace ( "$" + i++, parametro );
			}
			return row + "\n";
		} catch ( Exception e ) {
			throw new RuntimeException ( "Errore preparazione riga della mail con RT (row = " + riga + ")", e.getCause () );
		}
	}

	public EpayTRt findById ( Long idRt ) {
		return entityManager.find ( EpayTRt.class, idRt );
	}
}
