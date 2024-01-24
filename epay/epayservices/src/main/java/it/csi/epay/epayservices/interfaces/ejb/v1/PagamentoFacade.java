/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb.v1;

import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.exception.PosizioneDebitoriaException;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoLight;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.epay.epayservices.model.v1.Transaction;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface PagamentoFacade {

    /**
     * Cerca un Pagamento per id
     */
    public Pagamento ricerca(Long id) throws NoDataException, IllegalArgumentException;

    /**
     * Cerca un Pagamento per iuv
     */
    public Pagamento ricerca(String iuv) throws NoDataException, IllegalArgumentException;

    /**
     * Cerca un Pagamento per iuv senza caricare le componenti del pagamento
     */
    public Pagamento ricercaOttimizzata(String iuv) throws NoDataException, IllegalArgumentException;

    /**
     * Cerca un Pagamento per iuv (attivo e non pagato)
     */
    public Pagamento ricercaSoloAttivi(String iuv) throws NoDataException, IllegalArgumentException;

    /**
     * Cerca l'ultimo pagamento per iuv (7 - Transazione avviata e 4 - Successo)
     */
    public RegistroVersamenti ricercaUltimaTransazioneByIUV(String iuv) throws NoDataException, IllegalArgumentException;

    /**
     * Cerca tutti i Pagamenti attivi (e non pagati) per un codice fiscale
     */
    public List<Pagamento> ricercaPagamentiAttiviPerCF(String codiceFiscale) throws IllegalArgumentException;

    /**
     * Cerca tutti i Pagamenti attivi (e non pagati) e non spontanei per un codice fiscale
     */
    public List<Pagamento> ricercaPagamentiAttiviNonSpontaneiPerCF(String codiceFiscale) throws IllegalArgumentException;

    /**
     * Cerca tutti i Pagamenti per un codice fiscale
     */
    public List<Pagamento> ricercaPagamentiAllPerCF(String codiceFiscale) throws IllegalArgumentException;

    /**
     * Inserisce pagamento
     */
    public Long inserisci(Pagamento pagamento) throws IllegalArgumentException;

    /**
     * Aggiorna le note di un pagamento
     */
    public void aggiornaNote(Long idPagamento, String note) throws NoDataException, IllegalArgumentException;

    /**
     * Aggiorna le note di un pagamento
     */
    public void aggiornaConsensoPrivacy(Long idPagamento, boolean consensoPrivacy) throws NoDataException, IllegalArgumentException;

    /**
     * Aggiorna Iuv Esterno
     */
    public void aggiornaCodiceAvviso(Long idPagamento, CodiceAvviso codiceAvviso) throws NoDataException, IllegalArgumentException;

    /**
     * Aggiorna Dati del Pagatore
     */
    public void aggiornaPagatore(Long idPagamento, Anagrafica anagrafica) throws NoDataException, IllegalArgumentException;


    /**
     * Ricerca tipo pagamento
     */
    public TipoPagamento ricercaTipoPagamento(Long id) throws NoDataException, IllegalArgumentException;

    /**
     * Ricerca tipi pagamento per Ente (attivi)
     */
    List<TipoPagamentoLight> elencoTipoPagamentoPerEnte(Ente ente) throws IllegalArgumentException;


    /**
     * Ricerca transazione mdp
     */
    public TransazioneMdp ricercaTransazioneMdp(String idTransazione) throws NoDataException, IllegalArgumentException;

    /**
     * Salva dati della transazione mdp
     */
    public void inserisciTransazioneMdp(TransazioneMdp transazione) throws IllegalArgumentException;

    /**
     * Aggiorna dati della transazione mdp
     */
    public void aggiornaTransazioneMdp(TransazioneMdp transazione) throws IllegalArgumentException;


    /**
     * Ricerca ultimo risultato pagamento per idPagamento
     * @throws NoDataException
     */
    public RegistroVersamenti ricercaUltimaRegistrazioneVersamento(Long idPagamento, StatoPagamento stato) throws NoDataException;

    /**
     * Ricerca ricevuta telematica di pagamento per idPagamento
     * @throws NoDataException
     */
    public Rt ricercaRt(Long idPagamento, StatoPagamento stato) throws NoDataException;

    /**
     * Ricerca ricevuta telematica di pagamento per iuv
     * @throws NoDataException
     */
    public Rt ricercaRtByIuv(String iuv) throws NoDataException;


    /**
     * Inserisci risultato pagamento
     */
    public Long inserisciRegistroVersamentiEGestioneStato(RegistroVersamenti risultatoPagamento);

    /**
     * genera Avviso Pagamento
     */
    byte[] generaAvvisoPagamento(Pagamento pagamento);

    /*@Deprecated
	public void aggiornaPagamento(Pagamento pagamento);*/

    /**
     * Restituisce l'elenco completo dei pagamenti effettuati
     */
    //public List<Pagamento> elencoPagamentiEffettuatiPerCF(String codiceFiscale) throws IllegalArgumentException;

    /**
     * Restituisce l'elenco degli ultimi 10 pagamenti effettuati
     */
    public List<Pagamento> elencoPagamentiEffettuatiPerCFeUltimi10(String codiceFiscale) throws IllegalArgumentException;

    /**
     * Restituisce l'elenco dei pagamenti effettuati in un intervallo di tempo
     */
    public List<Pagamento> elencoPagamentiEffettuatiPerCFeIntervallo(String codiceFiscale, Date dataDa, Date dataA) throws IllegalArgumentException;

    /**
     * Restituisce l'elenco dei pagamenti per iuv e identificativo pagamento
     */
    public Pagamento ricercaPagamentoByIuvAndIdentificativoDominio(String iuvNumeroAvviso, String identificativoDominio) throws IllegalArgumentException;

    /**
     * Restituisce l'elenco dei pagamenti per iuv e identificativo pagamento
     * @throws MoreResultException
     */
    public PagamentoSecondario ricercaPagamentoSecondarioByIdPagamentoPrincipale(Long  idPagamentoPrincipale) throws IllegalArgumentException, MoreResultException;

    public Rt ricercaRtPerPagamentoEPagamentoSecondario ( Long idPagamento, Long idPagamentoSecondario ) throws  NoDataException;

    public Rt ricercaRtPerPagamentoPrimario ( Long idPagamento ) throws  NoDataException;


    public RegistroVersamenti ricercaUltimoByIdPagamentoAndIdPagamentoSecondario ( Long idPagamento, Long idPagamentoSecondario )  throws  IllegalArgumentException, NoDataException;

	public boolean verificaOrigineChiamata ( Long idPagamento, String idTransazione, Integer idOrigineChiamata ) throws IllegalArgumentException;

    public EsitiRicevuti ricercaEsitiRicevutiByIUV ( String iuv ) throws IllegalArgumentException, NoDataException;

    public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput param) throws TassonomiaServiceException;

    /* RDI-54 MODELLO UNICO - BEGIN */
    public Transaction getTransaction ( List<Pagamento> pagamentoList, String email, String origineInserimento, TracciabilitaChiamanteEsterno chiamataEsterna ) throws PosizioneDebitoriaException;
    
    public TracciabilitaChiamanteEsterno tracciaChiamataEsterna ( TracciabilitaChiamanteEsterno chiamanteEsterno, String iuv, String idTransazione );
    /* RDI-54 MODELLO UNICO - END */
}
