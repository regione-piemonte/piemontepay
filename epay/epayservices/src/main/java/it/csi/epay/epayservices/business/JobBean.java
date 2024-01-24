/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.ElaborazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EsitiRicevutiManager;
import it.csi.epay.epayservices.integration.db.manager.MarcaBolloManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoComponentiManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoSecondarioManager;
import it.csi.epay.epayservices.integration.db.manager.ParametriManager;
import it.csi.epay.epayservices.integration.db.manager.QuietanzaDaElaborareManager;
import it.csi.epay.epayservices.integration.db.manager.QuietanzaEsitoManager;
import it.csi.epay.epayservices.integration.db.manager.QuietanzaManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroElaborazioniManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.RtManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TracciaturaNotifyCittaFacileManager;
import it.csi.epay.epayservices.integration.db.manager.TracciaturaNotifyManager;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ElaborazioneDto;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EntePassphrase;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.MarcaDigitale;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoInvioPEC;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayservices.model.QuietanzaDaElaborareDto;
import it.csi.epay.epayservices.model.QuietanzaEsito;
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
import it.csi.epay.epayservices.utilities.PdfGenerator;

@Stateless(name="JobFacade", mappedName = "Job")
public class JobBean extends _BaseBean implements JobFacade {

    @EJB
    private RtManager rtManager;

    @EJB
    private EnteManager enteManager;

    @EJB
    private PagamentoManager pagamentoManager;

    @EJB
    private PdfGenerator pdfGenerator;

    @EJB
    private ParametriManager parametriManager;

    @EJB
    private TipoPagamentoManager tipoPagamentoManager;

    @EJB
    private RegistroElaborazioniManager registroElaborazioniManager;

    @EJB
    private RegistroVersamentiManager registroVersamentiManager;

    @EJB
    private EsitiRicevutiManager esitiRicevutiManager;

    @EJB
    private TracciaturaNotifyManager tracciaturaNotifyManager;
    
    @EJB
    private TracciaturaNotifyCittaFacileManager tracciaturaNotifyCittaFacileManager;

    @EJB
    private QuietanzaManager quietanzaManager;

	@EJB
	private PagamentoSecondarioManager pagamentoSecondarioManager;

	@EJB
	private PagamentoComponentiManager pagamentoComponentiManager;
	

	@EJB
	private QuietanzaDaElaborareManager quietanzaDaElaborareManager;

	@EJB
	private ElaborazioneManager elaborazioneManager;

	@EJB
	private QuietanzaEsitoManager quietanzaEsitoManager;
	
	 @EJB
	    private MarcaBolloManager marcaBolloManager;

    @Override
    public List<Rt> elencoRtSenzaRicevutaPdf() {
        return rtManager.elencoRtSenzaRicevutaPdf();
    }

    @Override
    public byte[] readRtXml(final Long id) {
        return rtManager.readXml(id);
    }

    @Override
    public Ente getEnteByCF(final String codiceFiscale) throws NoDataException {
        return enteManager.getByCF(codiceFiscale);
    }

    @Override
    public Pagamento getPagamento(final String iuv) {
        return pagamentoManager.getPagamento(iuv);
    }

    @Override
    public Pagamento getPagamentoOttimizzata(final String iuv) {
        return pagamentoManager.getPagamentoOttimizzata ( iuv);
    }

    @Override
    public byte[] getLogoEnte(Long id) {
        return enteManager.getLogo(id);
    }

    @Override
    public byte[] creaRicevutaPdf(Map<ParamNameXPdf, Object> param) {
        return pdfGenerator.creaRicevutaPdf(param);
    }

    @Override
    public void saveRtPdf(Long id, byte[] rt_pdf) {
        rtManager.savePdf(id, rt_pdf);
    }

    @Override
    public List<Parametro> elencoParametriPerGruppo(String gruppo) {
        return parametriManager.elencoPerGruppo(gruppo);
    }

    @Override
    public Parametro ricercaParametro(String gruppo, String codice) throws NoDataException, IllegalArgumentException {
        return parametriManager.getParametro(gruppo, codice);
    }

    @Override
    public List<Ente> ricercaEntiPerInvioRichiesteDiRevoca() throws NoDataException {
        return enteManager.getListaEntiPerInvioRichiesteDiRevoca();
    }

    @Override
    public List<Ente> ricercaEntiPerInvioPagamento() throws NoDataException {
        return enteManager.getListaEntiPerInvioPagamenti();
    }


    @Override
    public List<RevocaPerWso2> ricercaRichiesteRevocaPerEnte(Ente ente) throws NoDataException {
        return pagamentoManager.getListaPerInvioRevoche(ente);
    }

    @Override
    public void aggiornaRevocaInviataWso2(RevocaPerWso2 richiesta) {
        pagamentoManager.aggiornaRevocaInviataWso2(richiesta);
    }

    @Override
    public List<TipoPagamento> ricercaTipoPagamentoPerInvioPagamento(Ente ente) throws NoDataException {
        return tipoPagamentoManager.getListaPerInvioPagamenti(ente);
    }

    @Override
    public List<Pagamento> ricercaPagamentoPerInvioPagamento(TipoPagamento tipoPagamento, Boolean pagamentoSpontaneo) throws NoDataException {
        return pagamentoManager.getListaPerInvioPagamenti(tipoPagamento, pagamentoSpontaneo);
    }

    @Override
    public Long inserisciRegistroElaborazioni(RegistroElaborazioni registroElaborazioni) {
        return registroElaborazioniManager.inserisci(registroElaborazioni);
    }

    @Override
    public void aggiornaRegistroElaborazioni(RegistroElaborazioni registroElaborazioni) {
        registroElaborazioniManager.aggiorna(registroElaborazioni, false);
    }

    @Override
    public void aggiornaRegistroElaborazioni(RegistroElaborazioni registroElaborazioni, Boolean flagInviato) {
        registroElaborazioniManager.aggiorna(registroElaborazioni, flagInviato);
    }

    @Override
    public Rt ricercaRt(Long idPagamento, StatoPagamento stato) throws NoDataException {
        RegistroVersamenti registroVersamenti = registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato(idPagamento, stato.getId());
        return rtManager.ricercaRtByIdRegistro(registroVersamenti.getIdRegistro());
    }

    @Override
    public RegistroVersamenti ricercaUltimaRegistrazioneVersamento(Long idPagamento, StatoPagamento stato) throws NoDataException {
        return registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato(idPagamento, stato.getId());
    }

    @Override
    public RegistroVersamenti ricercaUltimaRegistrazioneVersamento(Long idPagamento, Integer idStato)
                    throws NoDataException {
        return registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato(idPagamento, idStato);
    }

    @Override
    public TransazioneMdp ricercaUltimaTransazioneMdp(Long idPagamento) throws NoDataException {
        return registroVersamentiManager.ricercaUltimaTransazioneMdp(idPagamento);
    }

    @Override
    public TransazioneMdp ricercaTransazioneMdp(String idTransazione) throws NoDataException {
        return registroVersamentiManager.ricercaTransazioneMdp(idTransazione);
    }

    @Override
    public EsitiRicevuti ricercaEsitiRicevutiPerIdPagamento(Long idPagamento) throws NoDataException {
        return esitiRicevutiManager.ricercaEsitiRicevutiByIdPagamento(idPagamento);
    }

    @Override
    public EsitiRicevuti ricercaEsitiRicevutiPerIdPagamentoEStato(Long idPagamento, StatoPagamento stato) throws NoDataException {
        return esitiRicevutiManager.ricercaEsitiRicevutiByIdPagamentoEStato(idPagamento, stato.getId ());
    }

    /*
     * RDI 23
     */
    @Override
    public StatistichePagamenti getStatistichePagamenti () {
        return pagamentoManager.getStatistichePagamenti ();
    }

    /*
     * RDI 23
     */
    @Override
    public List<PagamentoInvioPEC> ricercaPagamentoPerInvioPEC ( Long numLimit ) throws NoDataException {
        return pagamentoManager.ricercaPagamentiPerInvioPEC ( numLimit );
    }

    /*
     * RDI 23
     */

	@Override
    public void marcaInviatiAlPEC ( List<Long> idPagamenti ) {
        pagamentoManager.marcaInviatiAlPEC ( idPagamenti );
    }

    /*
     * RDI 43
     */

    @Override
    public List<Pagamento> ricercaPagamentiValidiPerEnte(Long idente) throws NoDataException {
        return pagamentoManager.getPagamentiValidiPerEnte(idente);
    }

    @Override
    public List<EntePassphrase> ricercaEntiPerInvioNotifiche() throws NoDataException {
        return enteManager.getListaEntiPerInvioNotifiche();
    }

    @Override
    public void inserisciTracciatureNotify(List<TracciaturaNotify> tracciature)
    {
        tracciaturaNotifyManager.inserisciMulti(tracciature);
    }

    //MM
    @Override
    public List<EsitiRicevuti> elencoEsitiSenzaQuietanza () throws NoDataException {
        return esitiRicevutiManager.elencoEsitiSenzaQuietanza ();
    }

    @Override
    public byte [] creaQuietanzaPdf ( Map<ParamNameXPdf, Object> param ) {
        return pdfGenerator.creaQuietanzaPdf ( param );
    }

    @Override
    public Long inserisciNuovaQuietanza ( byte [] quietanzaPdf ) {
        QuietanzaEsito qe = new QuietanzaEsito ();
        qe.setRicevutaPdf ( quietanzaPdf );
        qe.setDataOraCreazione ( new Date () );
        qe.setOrigineInserimento ( "GeneratePdfReceipt" );
        return quietanzaManager.inserisciNuovaQuietanza ( qe );
    }

    @Override
    public Long aggiornaEsitoRicevuto ( EsitiRicevuti esito, Long idQuietanza ) {
        return esitiRicevutiManager.aggiornaEsitoRicevuto ( esito, idQuietanza );
    }
    //MM

    @Override
    public PagamentoSecondario getPagamentoSecondario ( Long idPagamento ) {
        return pagamentoSecondarioManager.getPagamentoSecondario ( idPagamento );
    }

	@Override public boolean ricercaAndUpdateComponentiPosizioneValida ( String tassonomiaEsistente, String tassonomiaNuova ) {
		List<PagamentoComponenti> componenti;
		try {
			componenti = pagamentoComponentiManager.getComponentiPerBatchAggPosDeb ( tassonomiaEsistente );
		} catch ( NoDataException e ) {
			log.info ( "ricercaAndUpdateComponentiPosizioneValida", "Componenti non trovati per tassonomia esistente " + tassonomiaEsistente );
			return false;
		}
		// update
		pagamentoComponentiManager.aggiornaComponentiPerBatchAggPosDeb ( componenti, tassonomiaEsistente, tassonomiaNuova );
		log.info ( "ricercaAndUpdateComponentiPosizioneValida", "Tutti i componenti sono stati aggiornati per tassonomiaEsistente: " + tassonomiaEsistente );
		return true;
	}

    @Override
    public void chiudiPosiszioniDebitoriePagate () {
        
        pagamentoManager.sbloccaPosiszioniDebitorieInAttesa ( StatoPagamento.SUCCESSO.getId (), StatoPagamento.SUCCESSO.getId () );
        
    }
    
    @Override
    public void apriPosiszioniDebitoriePagabili() {
        pagamentoManager.sbloccaPosiszioniDebitorieInAttesa ( StatoPagamento.FALLITO.getId (), StatoPagamento.FALLITO.getId () );
        
        
    }

    @Override
    public List<EntePassphrase> ricercaEntiNotify () throws NoDataException {
        return enteManager.getListaEntiPerInvioNotificheTipizzate();
    }
    
    @Override
    public List<EntePassphrase> ricercaEntiNotifyCittaFacile () throws NoDataException {
        return enteManager.getListaEntiPerInvioNotificheTipizzateCittaFacile();
    }

    @Override
    public List<TipoPagamentoPassphrase> ricercaTipiPagamentoNotifyPerEnte (Long idEnte) throws NoDataException {
        return tipoPagamentoManager.getListaTipiPagamentoPerInvioNotificheTipizzatePerEnte( idEnte);
    }
    
    @Override
    public List<TipoPagamentoPassphrase> ricercaTipiPagamentoNotifyCittaFacilePerEnte (Long idEnte) throws NoDataException {
        return tipoPagamentoManager.getListaTipiPagamentoPerInvioNotificheTipizzateCittaFacilePerEnte( idEnte);
    }

	@Override
    public List<Pagamento> ricercaPagamentiValidiPerTipoPagamento ( Long idTipoPagamento ) throws NoDataException {
        return pagamentoManager.getPagamentiValidiPerTipoPagamento(idTipoPagamento);
    }

    @Override
    public List<Pagamento> ricercaPagamentiCittaFacileValidiPerTipoPagamento ( Long idTipoPagamento ) throws NoDataException {
        return pagamentoManager.getPagamentiCittaFacileValidiPerTipoPagamento(idTipoPagamento);
    }

    @Override
    public void inserisciTracciatureNotifyCittaFacile ( List<TracciaturaNotifyCittaFacile> tracciature ) {
        tracciaturaNotifyCittaFacileManager.inserisciMulti(tracciature);
    }

	@Override public List<QuietanzaDaElaborareDto> getListaQuietanzeDaElaborare ( int nrTentativiGiornalieri, int nrTotaleGiorniTentativi ) {
		return quietanzaDaElaborareManager.getListaQuietanzeDaElaborare ( nrTentativiGiornalieri, nrTotaleGiorniTentativi );
	}

	@Override public ElaborazioneDto aggiornaQuietanzaDaElaborare ( QuietanzaDaElaborareDto q, ElaborazioneDto elaborazioneDto ) {
		ElaborazioneDto epayTElaborazione = elaborazioneManager.createOrUpdate ( elaborazioneDto );
		return quietanzaDaElaborareManager.aggiornaQuietanzaDaElaborare ( q, epayTElaborazione );
	}

	@Override public ElaborazioneDto saveElaborazione ( ElaborazioneDto elaborazioneDto ) {
		return elaborazioneManager.saveElaborazione ( elaborazioneDto );
	}

	public boolean existsConsensoPagatore ( Long idEnte, String codiceFiscalePagatore ) {
		return elaborazioneManager.existsConsensoPagatore ( idEnte, codiceFiscalePagatore );
	}

	@Override
	public boolean existsQuietanza ( String iuv, String codiceIpa ) {
		return elaborazioneManager.existsQuietanza ( iuv, codiceIpa );
	}

	@Override
	public Long inserisciQuietanza ( QuietanzaDaElaborareDto quietanza ) {
		return elaborazioneManager.inserisciQuietanza ( quietanza );
	}
	
	@Override
    public MarcaDigitale ricercaMarcaDaBolloByByIuvAndIdPagamento ( String iuv, Long idPagamento ) throws NoDataException {
        return marcaBolloManager.ricercaMarcaDaBolloByByIuvAndIdPagamento  ( iuv , idPagamento);
        
    }
}
