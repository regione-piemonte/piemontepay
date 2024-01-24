/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.DatiSingolaRevocaDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteIUVDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareLightDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaLightDto;
import it.csi.epay.epaypaweb.dto.RichiestaDiRevocaDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.SingolaRevocaDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAvvisoScadutoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;

import java.sql.Timestamp;
import java.util.List;

//@formatter:off
/** cuscinetto intermedio tra business e dao, contiene semplice logica di accesso ai dati */
public interface GestioneFlussiDad extends EPaypaDadBase {

	boolean existsFlussoById(Long idFlusso) throws PersistenceException;
	Long countAllFlussoByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException;
	List<FlussoLightDto> findAllFlussoLightByFilter(FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag, String codUtente) throws PersistenceException;
	FlussoLightDto findFlussoLightById(Long idFlusso) throws PersistenceException;
	List<Long> findIdFlussoByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException;

	FlussoCompletoDto<NotificaPagamentoDto> findFlussoNotifichePagamento(Long idFlusso) throws PersistenceException;
	Long insertFlussoNotifichePagamento(FlussoCompletoDto<NotificaPagamentoDto> flussoCompletoDto, Timestamp timestamp) throws PersistenceException;
	long updateFlussoNotifichePagamento(FlussoCompletoDto<NotificaPagamentoDto> flussoCompletoDto) throws PersistenceException;

	long  updateFlussoNotifichePagamento (List<NotificaPagamentoDto> itemList) throws PersistenceException;
	//
	Long countAllNotificaPagamentoByIdFlusso(Long idFlusso) throws PersistenceException;
	List<NotificaPagamentoLightDto> findAllNotificaPagamentoLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag) throws PersistenceException;
	NotificaPagamentoLightDto findNotificaPagamentoLightById(Long idNotificaPagamento) throws PersistenceException;
	NotificaPagamentoDto findNotificaPagamentoById(Long idNotificaPagamento) throws PersistenceException;
	List<SingolaRevocaDto> singolaRevocaByIdRr(Long  idRr) throws PersistenceException;

	FlussoCompletoDto<PosizioneDebitoriaDto> findFlussoPosizioniDebitorie(Long idFlusso) throws PersistenceException;
	Long insertFlussoPosizioniDebitorie(FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompletoDto, Timestamp timestamp) throws PersistenceException;
	long updateFlussoPosizioniDebitorie(FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompletoDto, boolean clearBeforeAllNullableModifiables) throws PersistenceException;
	//
	Long countAllPosizioneDebitoriaByIdFlusso(Long idFLusso) throws PersistenceException;
	List<PosizioneDebitoriaLightDto> findAllPosizioneDebitoriaLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord, PaginazioneDto pag) throws PersistenceException;
	PosizioneDebitoriaLightDto findPosizioneDebitoriaLightById(Long idPosizioneDebitoria) throws PersistenceException;
	PosizioneDebitoriaDto findPosizioneDebitoriaById(Long idPosizioneDebitoria) throws PersistenceException;

	PosizioneDebitoriaDto findPosizioneDebitoriaByIdPosizioneDebitoriaEsternaAndIUV ( String iDposizioneDebitoriaEsterna, String iuv, Integer idEnte, Integer idCodiceVersamento )
					throws PersistenceException;

	FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> findFlussoPosizioniDebitorieDaAggiornare(Long idFlusso) throws PersistenceException;
	Long insertFlussoPosizioniDebitorieDaAggiornare(FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompletoDto, Timestamp timestamp) throws PersistenceException;
	long updateFlussoPosizioniDebitorieDaAggiornare(FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompletoDto, boolean clearBeforeAllNullableModifiables) throws PersistenceException;
	//
	Long countAllPosizioneDebitoriaDaAggiornareByIdFlusso(Long idFLusso) throws PersistenceException;
	List<PosizioneDebitoriaDaAggiornareLightDto> findAllPosizioneDebitoriaDaAggiornareLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag) throws PersistenceException;
	PosizioneDebitoriaDaAggiornareLightDto findPosizioneDebitoriaDaAggiornareLightById(Long idPosizioneDebitoriaDaAggiornare) throws PersistenceException;
	PosizioneDebitoriaDaAggiornareDto findPosizioneDebitoriaDaAggiornareById(Long idPosizioneDebitoriaDaAggiornare) throws PersistenceException;

	FlussoCompletoDto<AvvisoScadutoDto> findFlussoAvvisiScaduti(Long idFlusso) throws PersistenceException;
	Long insertFlussoAvvisiScaduti (FlussoCompletoDto<AvvisoScadutoDto> flussoCompletoDto, Timestamp timestampNow) throws PersistenceException;
	// -- updateFlussoAvvisiScaduti assente
	//
	Long countAllAvvisoScadutoByIdFlusso(Long idFLusso) throws PersistenceException;
	List<AvvisoScadutoDto> findAllAvvisoScadutoByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord, PaginazioneDto pag) throws PersistenceException;
	AvvisoScadutoDto findAvvisoScadutoById(Long idAvvisiScaduto) throws PersistenceException;

	FlussoCompletoDto<RiversamentoDto> findFlussoRendicontazione(Long idFlusso ) throws PersistenceException;
	FlussoCompletoDto<RiversamentoDto> findFlussoRendicontazione(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag) throws PersistenceException;
	Long insertFlussoRendicontazioni(FlussoCompletoDto<RiversamentoDto> flussoCompletoDto, Timestamp timestamp) throws PersistenceException;
	// -- updateFlussoRendicontazioni assente
	//
	Long countAllRiversamentoByIdFlusso(Long idFLusso) throws PersistenceException;
	List<RiversamentoDto> findAllRiversamentoByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag) throws PersistenceException;
	RiversamentoDto findRiversamentoById(Long idRiversamento) throws PersistenceException;

	boolean updateFlusso(FlussoDto flussoDto) throws PersistenceException;
	boolean removeFlussoById(Long idFlusso) throws PersistenceException;
	//
	long updateFlussoPosizioniDebitorie(FlussoDto dto, Timestamp timestampInizioValidita, Timestamp timestampFineValidita) throws PersistenceException;
	PosizioneDebitoriaDto removePosizioneDebitoriaById(Long idPosizioneDebitoria) throws PersistenceException;
	//
	boolean updateFlussoPosizioniDebitorieDaAggiornare(FlussoDto dto) throws PersistenceException;
	PosizioneDebitoriaDaAggiornareDto removePosizioneDebitoriaDaAggiornareById(Long idPosizioneDebitoriaDaAggiornare) throws PersistenceException;
	Long countAllFlussoNotificheByFilter ( FlussoLightFilterDto filter, String codUtente ) throws PersistenceException;
	List<FlussoLightDto> findAllFlussoNotificheByFilter ( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord,
		PaginazioneDto pag, String codUtente ) throws PersistenceException;
	Long countAllFlussoRevocheByFilter ( RichiestaRevocheFilterDto filter, String codUtente ) throws PersistenceException;
	List<RichiestaRevocheFilterDto> findAllFlussoRevocheByFilter ( RichiestaRevocheFilterDto filter,
		PaginazioneDto pag, String codUtente ) throws PersistenceException;
	// void insertER(FlussoCompletoDto<RichiestaDiRevocaDto> flussoCompletoDto);
	Long insertRR(RichiestaDiRevocaDto flussoCompletoDto,EpaypaTTipoRevoca tipoRevocaEntity) throws PersistenceException;
	void insertDettaglioRR(DatiSingolaRevocaDto listaDettagliRevoca, Long idRR ) throws PersistenceException;
	EpaypaTTipoRevoca getEpaypaTTipoRevocaById ( int tipoRevoca ) throws PersistenceException;

	PosizioneDebitoriaAutocompleteDto getPosizioneDebitoriaAutoComplete ( String partialPosizioneDebitoria, Integer enteId, Integer idCodVersamento ) throws PersistenceException;

	PosizioneDebitoriaAutocompleteIUVDto getPosizioneDebitoriaIUVAutoComplete ( String partialIUV, Integer enteId, Integer idCodVersamento ) throws PersistenceException;
    Long countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( String posizioneDebitoriaEst, Integer enteId, Integer idCodVersamento ) throws PersistenceException;
}
//@formatter:on
