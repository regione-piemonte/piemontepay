/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ElaborazioneDto;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EntePassphrase;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.MarcaDigitale;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoInvioPEC;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayservices.model.QuietanzaDaElaborareDto;
import it.csi.epay.epayservices.model.RegistroElaborazioni;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.RevocaPerWso2;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatistichePagamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoPassphrase;
import it.csi.epay.epayservices.model.TracciaturaNotify;
import it.csi.epay.epayservices.model.TracciaturaNotifyCittaFacile;
import it.csi.epay.epayservices.model.TransazioneMdp;

@Remote
public interface JobFacade {

    /**
     * Elenco rt senza ricevuta pdf
     */
	List<Rt> elencoRtSenzaRicevutaPdf();


    /**
     * legge rt (xml)
     */
	byte[] readRtXml(final Long id);


    /*
     * legge ente da codice fiscale
     */
	Ente getEnteByCF(final String codiceFiscale) throws NoDataException;


    /*
     * legge pagamento da iuv
     */
	Pagamento getPagamento(final String iuv);

    /*
     * legge pagamento da iuv ottimizzata. Non vengono caricati i componenti del pagamento
     */
	Pagamento getPagamentoOttimizzata(final String iuv);

    /*
     * legge logo dell'ente
     */
	byte[] getLogoEnte(Long id);

    /*
     * crea ricevuta telematica in Pdf
     */
	byte[] creaRicevutaPdf(Map<ParamNameXPdf, Object> param);

    /*
     * salva la ricevuta telematica in pdf
     */
	void saveRtPdf(final Long id, final byte[] rt_pdf);

    /*
     * legge tutti i parametri di un gruppo
     */
	List<Parametro> elencoParametriPerGruppo(String gruppo);

    /*
     * ricerca un parametro
     */
	Parametro ricercaParametro(String gruppo, String codice) throws NoDataException, IllegalArgumentException;

    /*
     * ricerca enti
     */
	List<Ente> ricercaEntiPerInvioPagamento() throws NoDataException;

    List<Ente> ricercaEntiPerInvioRichiesteDiRevoca() throws NoDataException;


    /*
     * ricerca tipi pagamento
     */
	List<TipoPagamento> ricercaTipoPagamentoPerInvioPagamento(Ente ente) throws NoDataException;

    /*
     * ricerca pagamenti
     */
	List<Pagamento> ricercaPagamentoPerInvioPagamento(TipoPagamento tipoPagamento, Boolean pagamentoSpontaneo) throws NoDataException;

    /*
     * ricerca revoche
     */
	List<RevocaPerWso2> ricercaRichiesteRevocaPerEnte(Ente ente) throws NoDataException;

    void aggiornaRevocaInviataWso2(RevocaPerWso2 richiesta);

    /*
     * inserisciRegistroElaborazioni
     */
	Long inserisciRegistroElaborazioni(RegistroElaborazioni registroElaborazioni);

    /*
     * aggiornaRegistroElaborazioni
     */
	void aggiornaRegistroElaborazioni(RegistroElaborazioni registroElaborazioni);

    /*
     * aggiornaRegistroElaborazioni
     */
	void aggiornaRegistroElaborazioni(RegistroElaborazioni registroElaborazioni, Boolean fine);

    /**
     * Ricerca ricevuta telematica di pagamento per idPagamento
	 */
	Rt ricercaRt(Long idPagamento, StatoPagamento stato) throws NoDataException;

    /**
     * Ricerca ultimo risultato pagamento per idPagamento
	 */
	RegistroVersamenti ricercaUltimaRegistrazioneVersamento(Long idPagamento, StatoPagamento stato) throws NoDataException;

    /**
     * Ricerca ultimo risultato pagamento per idPagamento e idStato
	 */
	RegistroVersamenti ricercaUltimaRegistrazioneVersamento(Long idPagamento, Integer idStato) throws NoDataException;


    /*
     * ricerca ultima tranzazione mdp
     */
	TransazioneMdp ricercaUltimaTransazioneMdp(Long idPagamento) throws NoDataException;

    /*
     * ricerca ultima tranzazione mdp
     */
	TransazioneMdp ricercaTransazioneMdp(String idTransazione) throws NoDataException;

    /*
     * ricerca esito ricezione per un pagamento
     */
	EsitiRicevuti ricercaEsitiRicevutiPerIdPagamento(Long idPagamento) throws NoDataException;

    /*
     * ricerca esito ricezione per un pagamento e lo stato
     */
	EsitiRicevuti ricercaEsitiRicevutiPerIdPagamentoEStato(Long idPagamento, StatoPagamento stato) throws NoDataException;

    /*
     *
     */
    StatistichePagamenti getStatistichePagamenti ();

    List<PagamentoInvioPEC> ricercaPagamentoPerInvioPEC ( Long numLimit ) throws NoDataException;


    void marcaInviatiAlPEC ( List<Long> idPagamenti);


    /**
     * Ricerca gli enti con invio notifiche attivo e pagamenti validi
	 */
	List<EntePassphrase> ricercaEntiPerInvioNotifiche() throws NoDataException;

    /**
     * Ricerca i pagamenti validi per id ente
	 */

	List<Pagamento> ricercaPagamentiValidiPerEnte(Long idente) throws NoDataException ;


	/**
     * Ricerca i pagamenti validi per id ente
     */

    List<Pagamento> ricercaPagamentiValidiPerTipoPagamento(Long idente) throws NoDataException ;


    /**
     * Inserisce un record sulla tabella di tracciatura notify
	 */
	void inserisciTracciatureNotify(List<TracciaturaNotify> tracciature)  ;

    //MM
    /**
     * Elenco esiti senza quietanza
     *
	 */
	List<EsitiRicevuti> elencoEsitiSenzaQuietanza () throws NoDataException;

    /*
     * legge ente da codice id pagamento
     */
    //public Ente getEnteByIdPagamento ( final Long idPagamento ) throws NoDataException;

    byte [] creaQuietanzaPdf ( Map<ParamNameXPdf, Object> param );

    /*
     * inserisciNuovaQuietanza
     */
	Long inserisciNuovaQuietanza ( byte [] quietanzaPdf );

    Long aggiornaEsitoRicevuto ( EsitiRicevuti esito, Long idQuietanza );

    PagamentoSecondario getPagamentoSecondario ( Long idPagamento );

	/*
	 * servizio creato per il job 800+ aggiornamento posizioni debitorie RDI41
	 */
	boolean ricercaAndUpdateComponentiPosizioneValida ( String tassonomiaEsistente, String tassonomiaNuova );


	void chiudiPosiszioniDebitoriePagate();
	
	void apriPosiszioniDebitoriePagabili();
	
	/**
     * Ricerca gli enti per invio notifiche tipizzate, con invio notifiche attivo e pagamenti validi
     */
    List<EntePassphrase> ricercaEntiNotify() throws NoDataException;
    
    /**
     * Ricerca gli enti per invio notifiche tipizzate a citta' facile, con flag citta' facile, invio notifiche attivo e pagamenti validi
     */
    
    List<EntePassphrase> ricercaEntiNotifyCittaFacile() throws NoDataException;


    List<TipoPagamentoPassphrase> ricercaTipiPagamentoNotifyPerEnte (Long idEnte) throws NoDataException;

    /**
     * Inserisce un record sulla tabella di tracciatura notify cittafacile
     */

    void inserisciTracciatureNotifyCittaFacile ( List<TracciaturaNotifyCittaFacile> tracciature );


    List<TipoPagamentoPassphrase> ricercaTipiPagamentoNotifyCittaFacilePerEnte ( Long idEnte ) throws NoDataException;


    List<Pagamento> ricercaPagamentiCittaFacileValidiPerTipoPagamento ( Long idTipoPagamento ) throws NoDataException;


	/*
	 * Per batch docme
	 */

	/**
	 * verifica se il pagatore ha dato il consenso, in questo caso, all'archiviazione della quietanza
	 */
    boolean existsConsensoPagatore ( Long idEnte, String codiceFiscalePagatore );

	/**
	 * verifica se esiste gia una quietanza con IUV e codice Ipa tra quelle da elaborare, in questo caso, da archiviare
	 */
	boolean existsQuietanza ( String iuv, String codiceIpa );

	/**
	 * inserisce la quietanza tra quelle da elaborare, in questo caso, da archiviare
	 */
    Long inserisciQuietanza ( QuietanzaDaElaborareDto quietanza );

	/**
	 * chiamata inizio batch docme
	 */
	List<QuietanzaDaElaborareDto> getListaQuietanzeDaElaborare ( int nrTentativiGiornalieri, int nrTotaleGiorniTentativi );

	/**
	 * chiamata aggiornamento batch docme
	 */
	ElaborazioneDto aggiornaQuietanzaDaElaborare ( QuietanzaDaElaborareDto q, ElaborazioneDto elaborazioneDto );

	/**
	 * chiamata fine batch docme
	 */
	ElaborazioneDto saveElaborazione ( ElaborazioneDto elaborazioneDto );
	
	MarcaDigitale ricercaMarcaDaBolloByByIuvAndIdPagamento ( String iuv, Long IdPagamento ) throws NoDataException;

}
