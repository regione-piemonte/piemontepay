/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.interf;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.EsitoAvvisoPagamentoDto;
import it.csi.epay.epaypaweb.dto.EsitoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.ParametriInserimentoFlussoDto;
import it.csi.epay.epaypaweb.dto.ParseResultDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteIUVDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareLightDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaLightDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.SingolaRevocaDto;
import it.csi.epay.epaypaweb.dto.StatoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.common.AvvisoPagamentoRequestDto;
import it.csi.epay.epaypaweb.dto.common.EliminaFlussoRequestDto;
import it.csi.epay.epaypaweb.dto.common.EliminaPosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.dto.common.EliminaPosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.dto.common.FlussoNotifichePagamentoRequestDto;
import it.csi.epay.epaypaweb.dto.common.FlussoPosizioniDebitorieRequestDto;
import it.csi.epay.epaypaweb.dto.common.InserisciListadicaricoRequestDto;
import it.csi.epay.epaypaweb.dto.common.InviaListadicaricoRequestDto;
import it.csi.epay.epaypaweb.dto.common.NotificaPagamentoRequestDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaCaricaRequestDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.dto.common.RegistraEsitoInserimentoListaDiCaricoRequestDto;
import it.csi.epay.epaypaweb.dto.common.RegistraEsitoPosizioniDebitorieAggiornateRequestDto;
import it.csi.epay.epaypaweb.dto.common.SalvaPosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.dto.common.SalvaPosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.dto.common.SalvaTestataFlussoPosizioniDebitorieRequestDto;
import it.csi.epay.epaypaweb.dto.common.TotalSizeAndFlussoNotificheListRequestDto;
import it.csi.epay.epaypaweb.dto.common.TotalSizeAndFlussoRevocheListRequestDto;
import it.csi.epay.epaypaweb.dto.common.TotalSizeAndPosizioneDebitoriaLightListRequestDto;
import it.csi.epay.epaypaweb.dto.common.TrasmettiFlussoAvvisoScadutoRequestDto;
import it.csi.epay.epaypaweb.dto.common.TrasmettiFlussoNotifichePagamentoRequestDto;
import it.csi.epay.epaypaweb.dto.common.TrasmettiFlussoRichiesteDiRevocaRequestDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAvvisoScadutoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum;
import it.csi.epay.epaypaweb.enumeration.DirezioneEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;

import javax.ejb.Local;
import java.util.List;


//@formatter:off
/** logica di business */
@Local
public interface GestioneFlussiBusiness {

	/** ottiene la lista degli stati del flusso */
	List<StatoFlussoDto> getStatoFlussoList(DirezioneEnum direzione) throws BusinessException;

	/** ottiene la lista dei tipi di aggiornamento delle posizioni debitorie */
	List<TipoAggiornamentoPosizioneDebitoriaDto> getTipoAggiornamentoPosizioneDebitoriaList() throws BusinessException;

	/** inserisce un flusso di notifiche di pagamento sul database */
	void trasmettiFlussoNotifichePagamento(TrasmettiFlussoNotifichePagamentoRequestDto trasmettiFlussoNotifichePagamentoRequestDto) throws BusinessException;

	/** inserisce un flusso di richieste di revoca sul database */
	void trasmettiFlussoRichiesteDiRevoca(TrasmettiFlussoRichiesteDiRevocaRequestDto trasmettiFlussoRichiesteDiRevocaRequestDto) throws BusinessException;

	/** inserisce un flusso di avvisi scaduti sul database */
	void trasmettiFlussoAvvisoScaduto(TrasmettiFlussoAvvisoScadutoRequestDto trasmettiFlussoAvvisoScadutoRequestDto) throws BusinessException;

	/** inserisce un flusso rendicontazione sul database */
	void trasmettiFlussoRendicontazione(FlussoCompletoDto<RiversamentoDto> flussoCompleto) throws BusinessException;

	/** verifica se esiste un flusso */
	boolean existsFlusso(Long idFlusso) throws BusinessException;

	/**
	 * ottiene il numero dei flussi selezionati in base al filtro di selezione
	 * 
	 * @param codUtente
	 */
	Long countFlussi(FlussoLightFilterDto filter, String codUtente, List<CodiceVersamentoDto> listCodiciVersamento) throws BusinessException;

	/**
	 * ottiene la lista dei flussi sintetici in base al filtro di selezione, criteri di ordinamento e impostazioni di paginazione
	 * 
	 * @param codUtente
	 */
	List<FlussoLightDto> getFlussoLightList(FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag,
		String codUtente, List<CodiceVersamentoDto> listCodiciVersamento) throws BusinessException;

	/**
	 * ottiene il numero dei flussi selezionati in base al filtro di selezione e la lista dei flussi in base al filtro di selezione, criteri di ordinamento
	 * eimpostazioni di paginazione
	 * 
	 * @param codUtente
	 */
	TotalSizeAndLightListDto<FlussoLightDto> getTotalSizeAndFlussoLightList(FlussoLightFilterDto filter,
		List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag, String codUtente, List<CodiceVersamentoDto> listCodiciVersamento)
						throws BusinessException;

	TotalSizeAndLightListDto<FlussoLightDto> getTotalSizeAndFlussoNotificheList(TotalSizeAndFlussoNotificheListRequestDto totalSizeAndFlussoNotificheListRequestDto) throws BusinessException;

	/** ottiene il flusso sintetico per l'id specificato **/
	public FlussoLightDto getFlussoLight ( Long idFlusso ) throws BusinessException;

	/** ottiene il flusso delle notifiche di pagamento per l'id specificato **/
	FlussoCompletoDto<NotificaPagamentoDto> getFlussoNotifichePagamento(FlussoNotifichePagamentoRequestDto flussoNotifichePagamentoRequestDto, boolean isDownload) throws BusinessException;


	/** ottiene il flusso delle posizioni debitorie (liste di carico) per l'id specificato **/
	FlussoCompletoDto<PosizioneDebitoriaDto> getFlussoPosizioniDebitorie(FlussoPosizioniDebitorieRequestDto flussoPosizioniDebitorieRequestDto, boolean isDownload) throws BusinessException;

	FlussoCompletoDto<PosizioneDebitoriaDto> getFlussoPosizioniDebitorie(Long idFlusso) throws BusinessException;


	/** ottiene il flusso delle posizioni debitorie aggiornate per l'id specificato **/
	FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> getFlussoPosizioniDebitorieDaAggiornare(Long idFlusso) throws BusinessException;

	/** ottiene il flusso degli avvisi scaduti per l'id specificato **/
	FlussoCompletoDto<AvvisoScadutoDto> getFlussoAvvisiScaduti(Long idFlusso) throws BusinessException;

	/** ottiene il flusso di rendicontazione per l'id specificato **/
	FlussoCompletoDto<RiversamentoDto> getFlussoRendicontazione(Long idFlusso) throws BusinessException;

	/** ottiene il numero delle notifiche di pagamento in base all'id flusso */
	Long countNotifichePagamento(Long idFLusso) throws BusinessException;

	/** ottiene la lista delle notifiche di pagamento sintetiche in base all'id flusso, criteri di ordinamento e impostazioni di paginazione */
	List<NotificaPagamentoLightDto> getNotificaPagamentoLightList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord,
		PaginazioneDto pag) throws BusinessException;

	/**
	 * ottiene il numero delle notifiche di pagamento sintetiche in base all'id flusso e la lista delle notifiche di pagamento in base all'id flusso, criteri di
	 * ordinamento e impostazioni di paginazione
	 */
	TotalSizeAndLightListDto<NotificaPagamentoLightDto> getTotalSizeAndNotificaPagamentoLightList(Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag) throws BusinessException;

	/** ottiene la notifica di pagamento sintetica corrispondente all'id specificato */
	NotificaPagamentoLightDto getNotificaPagamentoLight(NotificaPagamentoRequestDto notificaPagamentoRequestDto) throws BusinessException;

	/** ottiene la notifica di pagamento corrispondente all'id specificato */
	NotificaPagamentoDto getNotificaPagamento(NotificaPagamentoRequestDto notificaPagamentoRequestDto) throws BusinessException;


	/** ottiene il numero delle posizioni debitorie in base all'id flusso */
	Long countPosizioniDebitorie(Long idFLusso) throws BusinessException;

	/** ottiene la lista delle posizioni debitorie sintetiche in base all'id flusso, criteri di ordinamento e impostazioni di paginazione */
	List<PosizioneDebitoriaLightDto> getPosizioneDebitoriaLightList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord,
		PaginazioneDto pag) throws BusinessException;


	TotalSizeAndLightListDto<PosizioneDebitoriaLightDto> getTotalSizeAndPosizioneDebitoriaLightList(TotalSizeAndPosizioneDebitoriaLightListRequestDto totalSizeAndPosizioneDebitoriaLightListRequestDto) throws BusinessException;

	/** ottiene la posizione debitoria sintetica corrispondente all'id specificato */
	PosizioneDebitoriaLightDto getPosizioneDebitoriaLight(Long idPosizioneDebitoria) throws BusinessException;

	/** ottiene la posizione debitoria corrispondente all'id specificato */
	PosizioneDebitoriaDto getPosizioneDebitoria ( PosizioneDebitoriaRequestDto posizioeDebitoriaRequestDto ) throws BusinessException;

	PosizioneDebitoriaDto getPosizioneDebitoria ( PosizioneDebitoriaCaricaRequestDto posizioneDebitoriaCaricaRequestDto ) throws BusinessException;

	/** ottiene il numero delle posizioni debitorie da aggiornare in base all'id flusso */
	Long countPosizioniDebitorieDaAggiornare(Long idFLusso) throws BusinessException;

	/** ottiene la lista delle posizioni debitorie da aggiornare sintetiche in base all'id flusso, criteri di ordinamento e impostazioni di paginazione */
	List<PosizioneDebitoriaDaAggiornareLightDto> getPosizioneDebitoriaDaAggiornareLightList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag) throws BusinessException;

	/**
	 * ottiene il numero delle posizioni debitorie da aggiornare sintetuche in base all'id flusso e la lista degli aggiornamenti delle posizioni debitorie in
	 * base all'idFlusso, criteri di ordinamento e impostazioni di paginazione
	 */
	TotalSizeAndLightListDto<PosizioneDebitoriaDaAggiornareLightDto> getTotalSizeAndPosizioneDebitoriaDaAggiornareLightList(Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag) throws BusinessException;

	/** ottiene la posizione debitoria da aggiornare sintetica corrispondente all'id specificato */
	PosizioneDebitoriaDaAggiornareLightDto getPosizioneDebitoriaDaAggiornareLight(Long idPosizioneDebitoriaDaAggiornare) throws BusinessException;

	/** ottiene la posizione debitoria da aggiornare corrispondente all'id specificato */
	PosizioneDebitoriaDaAggiornareDto getPosizioneDebitoriaDaAggiornare(PosizioneDebitoriaDaAggiornareRequestDto posizioneDebitoriaDaAggiornareRequestDto) throws BusinessException;

	/** ottiene il pdf da stampare per l'avviso di pagamento */
	EsitoAvvisoPagamentoDto getAvvisoPagamento(AvvisoPagamentoRequestDto avvisoPagamentoRequestDto) throws BusinessException;

	/** ottiene il numero degli avvisi scadutiin base all'id flusso */
	Long countAvvisiScaduti(Long idFLusso) throws BusinessException;

	/** ottiene la lista degli avvisi scaduti in base all'id flusso, criteri di ordinamento e impostazioni di paginazione */
	List<AvvisoScadutoDto> getAvvisoScadutoList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord, PaginazioneDto pag)
					throws BusinessException;

	/** ottiene il numero degli avvisi scaduti in base all'id flusso */
	TotalSizeAndLightListDto<AvvisoScadutoDto> getTotalSizeAndAvvisoScadutoList(Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord, PaginazioneDto pag) throws BusinessException;

	/** ottiene l'avviso scaduto all'id specificato */
	AvvisoScadutoDto getAvvisoScaduto(Long idAvvisoScaduto) throws BusinessException;

	/** ottiene il numero dei dati di riversamento in base all'id flusso */
	Long countRiversamenti(Long idFLusso) throws BusinessException;

	/** ottiene la lista dei dati di riversamento in base all'id flusso, criteri di ordinamento e impostazioni di paginazione */
	List<RiversamentoDto> getRiversamentoList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag)
					throws BusinessException;

	/** ottiene la lista dei dati di riversamento in base all'id flusso, criteri di ordinamento e impostazioni di paginazione */
	TotalSizeAndLightListDto<RiversamentoDto> getTotalSizeAndRiversamentoList(Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag) throws BusinessException;

	/** ottiene l'avviso scaduto all'id specificato */
	RiversamentoDto getRiversamento(Long idRiversamento) throws BusinessException;

	/** elimina un flusso e tutte le sue dipendenze */
	boolean eliminaFlusso(EliminaFlussoRequestDto eliminaFlussoRequestDto) throws BusinessException;

	/** elimina una posizione debitoria e tutte le sue dipendenze */
	boolean eliminaPosizioneDebitoria(EliminaPosizioneDebitoriaRequestDto eliminaPosizioneDebitoriaRequestDto) throws BusinessException;

	/** elimina una posizione debitoria da aggiornare e tutte le sue dipendenze */
	boolean eliminaPosizioneDebitoriaDaAggiornare(EliminaPosizioneDebitoriaDaAggiornareRequestDto eliminaPosizioneDebitoriaDaAggiornareRequestDto) throws BusinessException;


	/** invia una lista di carico */
	EsitoDto inviaListadicarico(InviaListadicaricoRequestDto inviaListadicaricoRequestDto) throws BusinessException;


	/** invia un flusso delle posizioni debitorie da aggiornare */
	public EsitoDto inviaFlussoAggiornamentoPosizioniDebitorie ( Long idFlusso, String codUtente, String serviceEndpoint ) throws BusinessException;

	/** inserisce una lista di carico */
	List<ParseResultDto> inserisciListadicarico(InserisciListadicaricoRequestDto inserisciListadicaricoRequestDto) throws BusinessException;


	/** inserisce un flusso delle posizioni debitorie da aggiornare */
	List<ParseResultDto> inserisciFlussoPosizioniDebitorieDaAggiornare(ParametriInserimentoFlussoDto params, List<List<Object>> lines)
					throws BusinessException;

	/** salva (inserisce o modifica) una posizione debitoria */
	Long salvaPosizioneDebitoria(SalvaPosizioneDebitoriaRequestDto salvaPosizioneDebitoriaRequestDto) throws BusinessException;


	/** salva (inserisce o modifica) una posizione debitoria da aggiornare */
	Long salvaPosizioneDebitoriaDaAggiornare(SalvaPosizioneDebitoriaDaAggiornareRequestDto salvaPosizioneDebitoriaDaAggiornareRequestDto) throws BusinessException;

	/** salva la testata del flusso e aggiorna le date inizio e fine validita su tutte le posizioni debitorie di quel flusso */
	boolean salvaTestataFlussoPosizioniDebitorie(SalvaTestataFlussoPosizioniDebitorieRequestDto salvaTestataFlussoPosizioniDebitorieRequestDto) throws BusinessException;

	/** salva la testata del flusso e aggiorna le date inizio e fine validita su tutte le posizioni debitorie da aggiornare di quel flusso */
	boolean salvaTestataFlussoPosizioniDebitorieDaAggiornare(FlussoDto testataFlusso) throws BusinessException;

	/** aggiorna la lista di carico inserita con gli esiti provenienti dallo sportello pagamenti per ogni posizione debitoria */
	void registraEsitoInserimentoListaDiCarico(RegistraEsitoInserimentoListaDiCaricoRequestDto registraEsitoInserimentoListaDiCaricoRequestDto) throws BusinessException;


	/** aggiorna la lista di carico inserita con gli esiti provenienti dallo sportello pagamenti per ogni posizione debitoria */
	void registraEsitoPosizioniDebitorieAggiornate(RegistraEsitoPosizioniDebitorieAggiornateRequestDto registraEsitoPosizioniDebitorieAggiornateRequestDto) throws BusinessException;

	TotalSizeAndLightListDto<RichiestaRevocheFilterDto> getTotalSizeAndFlussoRevocheList(TotalSizeAndFlussoRevocheListRequestDto totalSizeAndFlussoRevocheListRequestDto) throws BusinessException;

	List<SingolaRevocaDto> getDettaglioRevoca(Long idRr) throws BusinessException;

	Long getCountNumMaxFlussiReport() throws BusinessException;

	Boolean isNumeroFlussiElaborabile(FlussoLightFilterDto filter, String codiceUtente) throws BusinessException;

	Long getCountTotaleFlussi(FlussoLightFilterDto filter, String codiceUtente)throws BusinessException;

	PosizioneDebitoriaAutocompleteDto getPosizioneDebitoriaAutoComplete ( String partialPosizioneDebitoria, Integer enteId, Integer idCodVersamento )
					throws BusinessException;

	PosizioneDebitoriaAutocompleteIUVDto getPosizioneDebitoriaAutoCompleteIUV ( String partialIUV, Integer enteId, Integer idCodVersamento ) throws BusinessException;

	Long countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( String posizioneDebitoriaEst, Integer enteId, Integer idCodVersamento ) throws BusinessException;
}
//@formatter:on
