/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business.v1;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayservices.business._BaseBean;
import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EsitiRicevutiManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.RtManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TransazioneMdpManager;
import it.csi.epay.epayservices.interfaces.ejb.v1.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.exception.PosizioneDebitoriaException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.TransactionAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.EsitoChiamataEsterna;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoLight;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.epay.epayservices.model.v1.Cart;
import it.csi.epay.epayservices.model.v1.Transaction;
import it.csi.epay.epayservices.utilities.PdfGenerator;
import it.csi.epay.epayservices.utilities.XmlUtil;


@Stateless ( name = "PagamentoFacadeV1", mappedName = "PagamentoV1" )
public class PagamentoBean extends _BaseBean implements PagamentoFacade {

	@EJB
	private EnteManager enteManager;

	@EJB
	private PagamentoManager pagamentoManager;

	@EJB
	private TipoPagamentoManager tipoPagamentoManager;

	@EJB
	private AnagraficaManager anagraficaManager;

	@EJB
	private RegistroVersamentiManager registroVersamentiManager;

	@EJB
	private TransazioneMdpManager transazioneMdpManager;

	@EJB
	private RtManager rtManager;

	@EJB
	private PdfGenerator pdfGenerator;

	@EJB
	private EsitiRicevutiManager EsitiRicevutiManager;

	@EJB
	private ConfigurazioneManager configurazioneManager;

	@EJB
	private ChiamataEsternaManager chiamataEsternaManager;

	@Override
	public Pagamento ricerca ( Long id ) throws NoDataException, IllegalArgumentException {
		return pagamentoManager.getPagamento ( id );
	}

	@Override
	public Pagamento ricerca ( String iuv ) throws NoDataException, IllegalArgumentException {
		Pagamento pagamento = pagamentoManager.getPagamento ( iuv );
		if ( pagamento == null )
			throw new NoDataException ();
		return pagamento;
	}

	@Override
	public Pagamento ricercaOttimizzata ( String iuv ) throws NoDataException, IllegalArgumentException {
		Pagamento pagamento = pagamentoManager.getPagamentoOttimizzata ( iuv );
		if ( pagamento == null )
			throw new NoDataException ();
		return pagamento;
	}

	@Override
	public Pagamento ricercaSoloAttivi ( String iuv ) throws NoDataException, IllegalArgumentException {
		return pagamentoManager.getPagamentoAttivo ( iuv );
	}

	@Override
	public List<Pagamento> ricercaPagamentiAttiviPerCF ( String codiceFiscale ) throws IllegalArgumentException {
		return pagamentoManager.getPagamentiPerCFAndActive ( codiceFiscale );
	}

	@Override
	public List<Pagamento> ricercaPagamentiAttiviNonSpontaneiPerCF ( String codiceFiscale ) throws IllegalArgumentException {
		return pagamentoManager.getPagamentiPerCFAndActiveAndNotSpontaneous ( codiceFiscale );
	}

	@Override
	public List<Pagamento> ricercaPagamentiAllPerCF ( String codiceFiscale ) throws IllegalArgumentException {
		return pagamentoManager.getPagamentiAllPerCF ( codiceFiscale );
	}

	@Override
	@TransactionAttribute ( TransactionAttributeType.REQUIRED )
	public Long inserisci ( Pagamento pagamento ) throws IllegalArgumentException {

		//lollo atterro qui solo se arrivo da inserimento pagamento spontaneo

		if ( pagamento.getPagatore () != null ) {
			Anagrafica newAnagrafica = anagraficaManager.inserisci ( pagamento.getPagatore () );
			pagamento.setPagatore ( newAnagrafica );
		}

		return pagamentoManager.inserisci ( pagamento );
	}

	@Override
	public void aggiornaNote ( Long idPagamento, String note ) throws NoDataException, IllegalArgumentException {
		pagamentoManager.aggiornaNote ( idPagamento, note );
	}

	@Override
	public void aggiornaConsensoPrivacy ( Long idPagamento, boolean consensoPrivacy )
					throws NoDataException, IllegalArgumentException {
		pagamentoManager.aggiornaConsensoPrivacy ( idPagamento, consensoPrivacy );
	}

	@Override
	public void aggiornaCodiceAvviso ( Long idPagamento, CodiceAvviso codiceAvviso )
					throws NoDataException, IllegalArgumentException {
		pagamentoManager.aggiornaCodiceAvviso ( idPagamento, codiceAvviso );
	}

	@Override
	public void aggiornaPagatore ( Long idPagamento, Anagrafica anagrafica ) throws NoDataException, IllegalArgumentException {
		anagraficaManager.aggiorna ( idPagamento, anagrafica );
	}

	@Override
	public TipoPagamento ricercaTipoPagamento ( Long id ) throws NoDataException, IllegalArgumentException {
		TipoPagamento tipoPagamento = tipoPagamentoManager.getById ( id );
		if ( tipoPagamento == null )
			throw new NoDataException ();
		return tipoPagamento;
	}

	@Override
	public List<TipoPagamentoLight> elencoTipoPagamentoPerEnte ( Ente ente ) throws IllegalArgumentException {
		return tipoPagamentoManager.getListaByEnte ( ente );
	}

	@Override
	public TransazioneMdp ricercaTransazioneMdp ( String idTransazione ) throws NoDataException, IllegalArgumentException {
		return transazioneMdpManager.ricerca ( idTransazione );
	}

	@Override
	public void inserisciTransazioneMdp ( TransazioneMdp transazione ) throws IllegalArgumentException {
		transazioneMdpManager.inserisci ( transazione );
	}

	@Override
	public void aggiornaTransazioneMdp ( TransazioneMdp transazione ) throws IllegalArgumentException {
		transazioneMdpManager.aggiorna ( transazione );
	}

	@Override
	public RegistroVersamenti ricercaUltimaRegistrazioneVersamento ( Long idPagamento, StatoPagamento stato ) throws NoDataException {
		return registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato ( idPagamento, stato.getId () );
	}

	@Override
	public Rt ricercaRt ( Long idPagamento, StatoPagamento stato ) throws NoDataException {
		RegistroVersamenti registroVersamenti = registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato ( idPagamento, stato.getId () );
		return rtManager.ricercaRtByIdRegistro ( registroVersamenti.getIdRegistro () );
	}

	@Override
	public Rt ricercaRtPerPagamentoEPagamentoSecondario ( Long idPagamento, Long idPagamentoSecondario ) throws NoDataException {
		RegistroVersamenti registroVersamenti
			= registroVersamentiManager.ricercaUltimoByIdPagamentoAndIdPagamentoSecondario ( idPagamento, idPagamentoSecondario );
		if ( registroVersamenti == null )
			throw new NoDataException ();
		return rtManager.ricercaRtByIdRegistro ( registroVersamenti.getIdRegistro () );
	}

	@Override
	public Rt ricercaRtPerPagamentoPrimario ( Long idPagamento ) throws IllegalArgumentException, NoDataException {
		RegistroVersamenti registroVersamenti = registroVersamentiManager.ricercaUltimoByIdPagamentoPrimario ( idPagamento );
		if ( registroVersamenti == null )
			throw new NoDataException ();
		return rtManager.ricercaRtByIdRegistro ( registroVersamenti.getIdRegistro () );
	}

	@Override
	@TransactionAttribute ( TransactionAttributeType.REQUIRED )
	public Long inserisciRegistroVersamentiEGestioneStato ( RegistroVersamenti risultatoPagamento ) {
		if ( risultatoPagamento == null ) {
			return -1L;
		}

		if ( risultatoPagamento.getAnagraficaVersante () != null ) {
			Anagrafica newAnagrafica = anagraficaManager.inserisci ( risultatoPagamento.getAnagraficaVersante () );
			risultatoPagamento.setAnagraficaVersante ( newAnagrafica );
		}
		return registroVersamentiManager.inserisci ( risultatoPagamento );
	}

	@Override
	public byte [] generaAvvisoPagamento ( Pagamento ppagamento ) {

		Pagamento pagamento = pagamentoManager.getPagamento ( ppagamento.getIdPagamento () );
		Ente ente = pagamento.getEnte ();
		Anagrafica pagatore = pagamento.getPagatore ();
		String nome
			= StringUtils.isNotBlank ( pagatore.getRagioneSociale () ) ? pagatore.getRagioneSociale () : pagatore.getNome () + " " + pagatore.getCognome ();

		Map<ParamNameXPdf, Object> param = new EnumMap<ParamNameXPdf, Object> ( ParamNameXPdf.class );
		param.put ( ParamNameXPdf.A1_LOGO_ENTE, enteManager.getLogo ( ente.getIdEnte () ) );
		param.put ( ParamNameXPdf.A2_LOGO_PAGOPA, enteManager.getLogo ( 0L ) );
		param.put ( ParamNameXPdf.B2_DESCRIZIONE_TASSA, pagamento.getTipoPagamento ().getDescrizionePortale () );
		param.put ( ParamNameXPdf.C1_DESCRIZIONE_ENTE, ente.getNome () );
		param.put ( ParamNameXPdf.C2_CF_ENTE, ente.getCodiceFiscale () );
		param.put ( ParamNameXPdf.C3_IMPORTO, pagamento.getImporto () );
		/* Pagamento model emette IllegalArgumentException che viene ora gestita - BEGIN */
		String codiceAvviso;
		try {
			codiceAvviso = pagamento.getCodiceAvviso ();
		} catch ( IllegalArgumentException e ) {
			codiceAvviso = null;
		}
		param.put ( ParamNameXPdf.C4_CODICE_AVVISO, codiceAvviso );
		/* Pagamento model emette IllegalArgumentException che viene ora gestita - END */
		param.put ( ParamNameXPdf.C5_IUV, pagamento.getIuv () );
		//param.put(ParamNameXPdf.C6_NOTE, pagamento.getTipoPagamento().getCompilazioneNote());
		param.put ( ParamNameXPdf.C6_NOTE, pagamento.getNote () );
		param.put ( ParamNameXPdf.D1_NOME, nome );
		param.put ( ParamNameXPdf.D2_CF, pagatore.getCodiceFiscale () );
		param.put ( ParamNameXPdf.F1_GLN_CODE, ente.getCodiceGs1Gln () );

		return pdfGenerator.creaAvvisoPdf ( param );
	}

	public List<Pagamento> elencoPagamentiEffettuatiPerCF ( String codiceFiscale ) throws IllegalArgumentException {
		final String methodName = "elencoPagamentiEffettuatiPerCF";
		List<Pagamento> pagamentiEffettuati = pagamentoManager.getPagamentiEffettuatiPerCF ( codiceFiscale );
		log.debug ( methodName, "numero pagamenti : " + pagamentiEffettuati.size () );
		for ( Pagamento pagamento: pagamentiEffettuati ) {
			try {
				RegistroVersamenti rv
					= ricercaUltimaRegistrazioneVersamento ( pagamento.getIdPagamento (), StatoPagamento.findById ( pagamento.getIdStatoCorrente () ) );
				pagamento.setDataStatoCorrente ( rv.getDataOperazione () );
			} catch ( NoDataException e ) {
				pagamento.setDataStatoCorrente ( new Date () );
			}
		}
		return pagamentiEffettuati;
	}

	private class OrdinaPagamentiPerDataComparator implements Comparator<Pagamento> {

		@Override
		public int compare ( Pagamento p1, Pagamento p2 ) {
			if ( p1.getDataStatoCorrente () == null ) {
				return -1;
			}

			if ( p1.getDataStatoCorrente () == null ) {
				return 1;
			}
			return p1.getDataStatoCorrente ().compareTo ( p2.getDataStatoCorrente () );
		}
	}

	@Override
	public List<Pagamento> elencoPagamentiEffettuatiPerCFeUltimi10 ( String codiceFiscale )
					throws IllegalArgumentException {
		List<Pagamento> pagamenti = elencoPagamentiEffettuatiPerCF ( codiceFiscale );
		if ( pagamenti.size () <= 10 ) {
			return pagamenti;
		}
		Collections.sort ( pagamenti, new OrdinaPagamentiPerDataComparator () );
		Collections.reverse ( pagamenti );
		for ( int i = pagamenti.size () - 1; i >= 10; i-- ) {
			pagamenti.remove ( i );
		}
		return pagamenti;
	}

	@Override
	public List<Pagamento> elencoPagamentiEffettuatiPerCFeIntervallo ( String codiceFiscale, Date dataDa, Date dataA )
					throws IllegalArgumentException {
		List<Pagamento> pagamenti = elencoPagamentiEffettuatiPerCF ( codiceFiscale );

		Iterator<Pagamento> pagamentoIterator = pagamenti.iterator ();
		while ( pagamentoIterator.hasNext () ) {
			Pagamento pagamento = pagamentoIterator.next ();
			if ( pagamento.getDataStatoCorrente ().before ( dataDa ) || pagamento.getDataStatoCorrente ().after ( dataA ) ) {
				pagamentoIterator.remove ();
			}
		}

		return pagamenti;
	}

	@Override
	public RegistroVersamenti ricercaUltimaTransazioneByIUV ( String iuv ) throws NoDataException, IllegalArgumentException {
		return registroVersamentiManager.ricercaUltimoRegistroByIUV ( iuv );
	}

	@Override
	public RegistroVersamenti ricercaUltimoByIdPagamentoAndIdPagamentoSecondario ( Long idPagamento, Long idPagamentoSecondario )
					throws NoDataException, IllegalArgumentException {
		return registroVersamentiManager.ricercaUltimoByIdPagamentoAndIdPagamentoSecondario ( idPagamento, idPagamentoSecondario );
	}

	@Override
	public boolean verificaOrigineChiamata ( Long idPagamento, String idTransazione, Integer idOrigineChiamata ) throws IllegalArgumentException {
		return registroVersamentiManager.verificaOrigineChiamata ( idPagamento, idTransazione, idOrigineChiamata );
	}

	@Override
	public Rt ricercaRtByIuv ( String iuv ) throws NoDataException {
		return rtManager.ricercaRtByIuv ( iuv );
	}

	@Override
	public Pagamento ricercaPagamentoByIuvAndIdentificativoDominio ( String iuvNumeroAvviso, String identificativoDominio )
					throws IllegalArgumentException {
		return pagamentoManager.getPagamentoByIuvAndIdentificativoDominio ( iuvNumeroAvviso, identificativoDominio );
	}

	@Override
	public PagamentoSecondario ricercaPagamentoSecondarioByIdPagamentoPrincipale ( Long idPagamentoPrincipale )
					throws IllegalArgumentException, MoreResultException {
		try {
			return pagamentoManager.getPagamentoSecondarioByIdPagamentoPrincipale ( idPagamentoPrincipale );
		} catch ( NoDataException e ) {
			return null;
		}
	}

	@Override
	public EsitiRicevuti ricercaEsitiRicevutiByIUV ( String iuv ) throws IllegalArgumentException, NoDataException {
		try {
			return EsitiRicevutiManager.ricercaEsitiRicevutiByIUV ( iuv );
		} catch ( NoDataException e ) {
			return null;
		}
	}

	@Override
	public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput param ) throws TassonomiaServiceException {
		DatiSpecificiRiscossioneOutput dsr = null;
		try {
			TassonomiaAdapterClient client = new TassonomiaAdapterClient ( configurazioneManager );
			dsr = client.getDatiSpecificiRiscossione ( param );

		} catch ( TassonomiaServiceException et ) { // DA FARE gestire correttamente eccezioni e stati
			log.error ( "Errore in fase di reperimento del dato specifico riscossione", et );
			throw et;
		} catch ( Exception e ) { // DA FARE gestire correttamente eccezioni e stati
			log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
			throw new TassonomiaServiceException ( CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getCodice (),
				CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getMessaggio ( 200 ), e );
		}
		return dsr;
	}

	/* RDI-54 MODELLO UNICO - BEGIN */
	@Override
	public Transaction getTransaction ( List<Pagamento> pagamentoList, String email, String origineInserimento, TracciabilitaChiamanteEsterno chiamataEsterna )
					throws PosizioneDebitoriaException {
		String methodName = "getTransaction";
		log.info ( methodName, "BEGIN" );

		Transaction transaction = null;
		try {
			TransactionAdapterClient client = new TransactionAdapterClient ( configurazioneManager );
			Cart request = client.buildCart ( pagamentoList, email );
			transaction = client.getTransaction ( request );

			// registra la transazione come avviata
			String idTransazione = transaction.getIdTransaction ();
			TransazioneMdp transazioneMdp = new TransazioneMdp ();
			transazioneMdp.setIdTransazione ( idTransazione );
			transazioneMdp.setIuv ( pagamentoList.get ( 0 ).getIuv () );
            transazioneMdpManager.inserisci ( transazioneMdp );
			
//			for ( Pagamento pagamento: pagamentoList ) {
//				tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_AVVIATA, transazioneMdp, origineInserimento );
//				log.info ( methodName, "inserita traccia transazione:\n" + XmlUtil.obj2Xml ( transazioneMdp ) );
//			}
			if ( null != chiamataEsterna ) {
				tracciaChiamataEsterna ( chiamataEsterna, pagamentoList.get ( 0 ).getIuv (), idTransazione );
			} else {
				log.info ( methodName, "nessun chiamante esterno per " + idTransazione );
			}
			log.info ( methodName, "transazione avviata con idTransazione:" + idTransazione );
			
		} catch ( Exception e ) {
			for ( Pagamento pagamento: pagamentoList ) {
				tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_ERRORE, null, origineInserimento );
			}
			if ( transaction == null ) {
				throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GENERICO, "errore di accesso a mdppagopacheckout" );
			} else {
				throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GENERICO, "errore nel registrare la transazione avviata" );
			}
		}

		log.info ( methodName, "END" );
		return transaction;
	}

	private Long tracciaRegistroPagamento ( Pagamento pagamento, StatoPagamento stato, TransazioneMdp transazioneMdp, String origineInserimento ) {
		RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
		registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
		registroVersamenti.setRisultato ( stato.getDescrizione () );
		if ( transazioneMdp != null ) {
			registroVersamenti.setIdTransazione ( transazioneMdp.getIdTransazione () );
		}
		registroVersamenti
			.setIuv ( StringUtils.isNotEmpty ( pagamento.getIuvRegistroVersamenti () ) ? pagamento.getIuvRegistroVersamenti () : pagamento.getIuv () );
		registroVersamenti.setIdStato ( stato.getId () );
		registroVersamenti.setOrigineInserimento ( origineInserimento );

		if ( registroVersamenti.getAnagraficaVersante () != null ) {
			Anagrafica newAnagrafica = anagraficaManager.inserisci ( registroVersamenti.getAnagraficaVersante () );
			registroVersamenti.setAnagraficaVersante ( newAnagrafica );
		}

		Long result = registroVersamentiManager.inserisci ( registroVersamenti );
		registroVersamenti.setIdRegistro ( result );

		log.debug ( "tracciaRegistroPagamento", "inserita voce di tracciatura versamento : \n" + XmlUtil.obj2Xml ( registroVersamenti ) );

		return result;
	}

	public TracciabilitaChiamanteEsterno tracciaChiamataEsterna ( TracciabilitaChiamanteEsterno chiamanteEsterno, String iuv, String idTransazione ) {
		if ( chiamanteEsterno == null ) {
			throw new RuntimeException ( "ATTENZIONE il parametro chiamante esterno deve essere creato da fuori" );
		}
		chiamanteEsterno.setIdTransazione ( idTransazione );
		chiamanteEsterno.setIuv ( iuv );
		//
		if ( chiamanteEsterno.getIdChiamata () == null ) {
			try {
				Long id = chiamataEsternaManager.inserisci ( chiamanteEsterno );
				chiamanteEsterno.setIdChiamata ( id );
				log.info ( "tracciaChiamataEsterna", "inserita voce di tracciatura chiamata esterna:\n" + XmlUtil.obj2Xml ( chiamanteEsterno ) );
			} catch ( Exception e ) {
				throw new RuntimeException ( "Errore nell'inserimento della tracciabilita'", e );
			}

		} else {
			try {
				chiamataEsternaManager.aggiorna ( chiamanteEsterno );
				log.info ( "tracciaChiamataEsterna", "aggiornata voce di tracciatura chiamata esterna:\n" + XmlUtil.obj2Xml ( chiamanteEsterno ) );
			} catch ( Exception e ) {
				throw new RuntimeException ( "Errore nell'aggiornamento della tracciabilita'", e );
			}
		}
		return chiamanteEsterno;
	}
	/* RDI-54 MODELLO UNICO - END */
}
