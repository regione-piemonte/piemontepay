/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

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
import org.apache.commons.lang3.time.StopWatch;

import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
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
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.NotificationPriceAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.NotificationPriceServiceException;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.NotificationPriceOutput;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaOutput;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoLight;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.epay.epayservices.model.v1.Esito;
import it.csi.epay.epayservices.model.v1.Stato;
import it.csi.epay.epayservices.utilities.PdfGenerator;

@Stateless ( name = "PagamentoFacade", mappedName = "Pagamento" )
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

    private static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA = "ENDPOINT_SERVICE_TASSONOMIA";

    
//    private static final String CONFIG_ENDPOINT_SERVICE_NOTIFICATION_PRICE = "ENDPOINT_SERVICE_NOTIFICATION_PRICE";
    
    private static final String COD_ENTE_DEFALUT = "PagoPa"; // codice 0
    
    private static final int MAX_ERROR_MESSAGE_WIDTH = 200;
    
    @Override
    public Pagamento ricerca(Long id) throws NoDataException, IllegalArgumentException {
		return pagamentoManager.getPagamento(id);
    }

    @Override
    public Pagamento ricerca(String iuv) throws NoDataException, IllegalArgumentException {
        Pagamento pagamento = pagamentoManager.getPagamento(iuv);
        if (pagamento == null) throw new NoDataException();
        return pagamento;
    }

    @Override
    public Pagamento ricercaOttimizzata(String iuv) throws NoDataException, IllegalArgumentException {
        Pagamento pagamento = pagamentoManager.getPagamentoOttimizzata(iuv);
        if (pagamento == null) throw new NoDataException();
        return pagamento;
    }

    @Override
    public Pagamento ricercaSoloAttivi(String iuv) throws NoDataException, IllegalArgumentException {
		return pagamentoManager.getPagamentoAttivo(iuv);
    }

    @Override
    public List<Pagamento> ricercaPagamentiAttiviPerCF(String codiceFiscale) throws IllegalArgumentException {
		return pagamentoManager.getPagamentiPerCFAndActive(codiceFiscale);
    }

    @Override
    public List<Pagamento> ricercaPagamentiAttiviNonSpontaneiPerCF(String codiceFiscale) throws IllegalArgumentException {
		return pagamentoManager.getPagamentiPerCFAndActiveAndNotSpontaneous(codiceFiscale);
    }

    @Override
    public List<Pagamento> ricercaPagamentiAllPerCF(String codiceFiscale) throws IllegalArgumentException {
		return pagamentoManager.getPagamentiAllPerCF(codiceFiscale);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long inserisci(Pagamento pagamento) throws IllegalArgumentException {

        //lollo atterro qui solo se arrivo da inserimento pagamento spontaneo

        if  (pagamento.getPagatore() != null) {
            Anagrafica newAnagrafica = anagraficaManager.inserisci(pagamento.getPagatore());
            pagamento.setPagatore(newAnagrafica);
        }

		return pagamentoManager.inserisci(pagamento);
    }

    @Override
    public void aggiornaNote(Long idPagamento, String note) throws NoDataException, IllegalArgumentException {
        pagamentoManager.aggiornaNote(idPagamento, note);
    }

    @Override
    public void aggiornaConsensoPrivacy(Long idPagamento, boolean consensoPrivacy)
                    throws NoDataException, IllegalArgumentException {
        pagamentoManager.aggiornaConsensoPrivacy(idPagamento, consensoPrivacy);
    }

	@Override
    public void aggiornaCodiceAvviso(Long idPagamento, CodiceAvviso codiceAvviso )
                    throws NoDataException, IllegalArgumentException {
        pagamentoManager.aggiornaCodiceAvviso(idPagamento, codiceAvviso);
    }

    @Override
    public void aggiornaPagatore(Long idPagamento, Anagrafica anagrafica) throws NoDataException, IllegalArgumentException {
        anagraficaManager.aggiorna(idPagamento, anagrafica);
    }


    @Override
    public TipoPagamento ricercaTipoPagamento(Long id) throws NoDataException, IllegalArgumentException {
        TipoPagamento tipoPagamento = tipoPagamentoManager.getById(id);
        if (tipoPagamento == null) throw new NoDataException();
        return tipoPagamento;
    }

    @Override
    public List<TipoPagamentoLight> elencoTipoPagamentoPerEnte(Ente ente) throws IllegalArgumentException {
		return tipoPagamentoManager.getListaByEnte(ente);
    }
    
    @Override
    public List<TipoPagamentoLight> elencoTipoPagamentoVisibiliPerEnte(Ente ente) throws IllegalArgumentException {
        return tipoPagamentoManager.getListaVisibiliByEnte(ente);
    }
    
    

    @Override
    public TransazioneMdp ricercaTransazioneMdp(String idTransazione) throws NoDataException, IllegalArgumentException {
        return transazioneMdpManager.ricerca(idTransazione);
    }

    @Override
    public void inserisciTransazioneMdp(TransazioneMdp transazione) throws IllegalArgumentException {
        transazioneMdpManager.inserisci(transazione);
    }

    @Override
    public void aggiornaTransazioneMdp(TransazioneMdp transazione) throws IllegalArgumentException {
        transazioneMdpManager.aggiorna(transazione);
    }


    @Override
    public RegistroVersamenti ricercaUltimaRegistrazioneVersamento(Long idPagamento, StatoPagamento stato) throws NoDataException {
        return registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato(idPagamento, stato.getId());
    }

    @Override
    public Rt ricercaRt(Long idPagamento, StatoPagamento stato) throws NoDataException {
        RegistroVersamenti registroVersamenti = registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato(idPagamento, stato.getId());
        return rtManager.ricercaRtByIdRegistro(registroVersamenti.getIdRegistro());
    }

    @Override
    public Rt ricercaRtPerPagamentoEPagamentoSecondario(Long idPagamento, Long idPagamentoSecondario) throws NoDataException {
        RegistroVersamenti registroVersamenti = registroVersamentiManager.ricercaUltimoByIdPagamentoAndIdPagamentoSecondario(idPagamento, idPagamentoSecondario);
        if (registroVersamenti == null) throw new NoDataException();
        return rtManager.ricercaRtByIdRegistro(registroVersamenti.getIdRegistro());
    }

    @Override
    public Rt ricercaRtPerPagamentoPrimario ( Long idPagamento ) throws IllegalArgumentException, NoDataException {
        RegistroVersamenti registroVersamenti = registroVersamentiManager.ricercaUltimoByIdPagamentoPrimario (idPagamento);
        if (registroVersamenti == null) throw new NoDataException();
        return rtManager.ricercaRtByIdRegistro(registroVersamenti.getIdRegistro());
    }




    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long  inserisciRegistroVersamentiEGestioneStato(RegistroVersamenti risultatoPagamento) {
        if (risultatoPagamento == null) {
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
    public boolean verificaOrigineChiamata (Long idPagamento, String idTransazione, Integer idOrigineChiamata) throws IllegalArgumentException {
        return registroVersamentiManager.verificaOrigineChiamata ( idPagamento, idTransazione, idOrigineChiamata );
    }

    @Override
    public Rt ricercaRtByIuv(String iuv) throws NoDataException {
        return rtManager.ricercaRtByIuv(iuv);
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
    
    
  //TODO [AF] lavori in corso
    /**
     * Invoca il servizio rest NotificationPrice per verificare se e' necessario attualizzare l'importo
     */
    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public NotificationPriceOutput chiamaNotificationPrice ( String organization, String iuv ,String url , byte [] credenziali ) throws NotificationPriceServiceException{
    	NotificationPriceOutput risposta;
    	
    	try {
//    		Configurazione serviceNotificationPrice = configurazioneManager.getConfigurazione ( CONFIG_ENDPOINT_SERVICE_NOTIFICATION_PRICE, COD_ENTE_DEFALUT );
//    		if ( serviceNotificationPrice == null ) {
//    			log.error ( "chiamaNotificationPrice",
//    					"Errore in fase di reperimento dell'aggiornamento di prezzo, configurazione  CONFIG_ENDPOINT_SERVICE_NOTIFICATION_PRICE mancante" );
//    			throw new RuntimeException (
//    					"Errore in fase di reperimento dell'aggiornamento di prezzo, configurazione  CONFIG_ENDPOINT_SERVICE_NOTIFICATION_PRICE mancante" );
//    		}
//    		String url = serviceNotificationPrice.getValore ();
    		url = StringUtils.replace(url, "{organization}", organization);
    		url = StringUtils.replace(url, "{iuv}", iuv);
    		
    		StopWatch stopWatch = new StopWatch();
    		stopWatch.start();
    		try {
	    		NotificationPriceAdapterClient client = new NotificationPriceAdapterClient ( url );
	    		risposta = client.getNotificationPrice(organization, iuv, credenziali);
    		} finally {
    			stopWatch.stop();
    			log.info("getNotificationPrice" , "Tempo trascorso per l'invocazione: " + stopWatch.getTime() + " ms");
    		}
    	} catch(NotificationPriceServiceException e ) {
    		log.error (  "Errore in fase di reperimento del dato specifico riscossione" , e );
    		throw e;
    	} catch ( Exception e ) { // DA FARE gestire correttamente eccezioni e stati
    		log.error ( "Errore in fase di reperimento dell'aggiornamento di prezzo", e );
    		throw new RuntimeException ( "Errore in fase di reperimento dell'aggiornamento di prezzo", e );
    	}
    	return risposta;
    }

	@Override
	public void attualizzaImporto(Long idPagamento, java.math.BigDecimal importo, Boolean requiresCostUpdate)
			throws NoDataException, IllegalArgumentException {
		pagamentoManager.attualizzaImporto(idPagamento, importo, requiresCostUpdate);
		
	}

	@Override
	public StatoPosizioneDebitoriaOutput getStatoPosizioneDebitoria ( StatoPosizioneDebitoriaInput input ) {
		final String methodName = "getStatoPosizioneDebitoria";
		log.debugStart ( methodName );

		EpayTPagamento tPagamento;
		try {
			tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( input.getCodiceFiscaleEnte (), input.getPaymentType (), input.getIuv () );

			if ( tPagamento == null ) {
				return StatoPosizioneDebitoriaOutput.builder ()
					.withResult ( Esito.builder ().withCode ( CodiciEsito.PAGAMENTO_NON_TROVATO_PER_IDENTIFICATIVO_PAGAMENTO.getCodice () )
						.withDescription ( CodiciEsito.PAGAMENTO_NON_TROVATO_PER_IDENTIFICATIVO_PAGAMENTO.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH,
							input.getCodiceFiscaleEnte (), input.getPaymentType (), input.getIuv () ) )
						.build () )
					.build ();
			}

			int idStato = tPagamento.getEpayDStatoPagamento ().getIdStato ();
			Stato stato;
			switch ( idStato ) {
			case 4 :
			case 11 :
				stato = Stato.builder ().withCode ( "0" ).withDescription ( "Pagato" ).build ();
				break;
			case -1 :
			case 0 :
			case 1 :
			case 2 :
			case 3 :
			case 5 :
			case 6 :
			case 7 :
			case 8 :
			case 10 :
				stato = Stato.builder ().withCode ( "1" ).withDescription ( "Non Pagato" ).build ();
				break;
			case 9 :
				stato = Stato.builder ().withCode ( "2" ).withDescription ( "Annullato dall'ente" ).build ();
				break;
			default :
				stato = Stato.builder ().withCode ( "-1" ).withDescription ( "Indefinito" ).build ();
				break;
			}

			return StatoPosizioneDebitoriaOutput.builder ().withCode ( stato.getCode () )
				.withDescription ( stato.getDescription () )
				.withResult ( Esito.builder ().withCode ( CodiciEsito.ESECUZIONE_OK.getCodice () )
					.withDescription ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) ).build () )
				.build ();

		} catch ( NoDataException e ) {

			return StatoPosizioneDebitoriaOutput.builder ()
				.withResult ( Esito.builder ().withCode ( CodiciEsito.PAGAMENTO_NON_TROVATO_PER_IDENTIFICATIVO_PAGAMENTO.getCodice () )
					.withDescription ( CodiciEsito.PAGAMENTO_NON_TROVATO_PER_IDENTIFICATIVO_PAGAMENTO.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH,
						input.getCodiceFiscaleEnte (), input.getPaymentType (), input.getIuv () ) )
					.build () )
				.build ();
		} catch ( MoreResultException e ) {
			return StatoPosizioneDebitoriaOutput.builder ()
				.withResult ( Esito.builder ().withCode ( CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA.getCodice () )
					.withDescription ( CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH,
						input.getCodiceFiscaleEnte (), input.getPaymentType (), input.getIuv () ) )
					.build () )
				.build ();
		} catch ( Exception e ) { // DA FARE gestire correttamente eccezioni e stati
			return StatoPosizioneDebitoriaOutput.builder ()
				.withResult ( Esito.builder ().withCode ( CodiciEsito.COOP_ERRORE_GENERICO.getCodice () )
					.withDescription ( CodiciEsito.COOP_ERRORE_GENERICO.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, e.getMessage () ) ).build () )
				.build ();
		}
	}
}
