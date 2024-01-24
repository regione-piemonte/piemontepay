/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dad;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import it.csi.epay.epaywsosrv.dto.AppDto;
import it.csi.epay.epaywsosrv.dto.CodVersDto;
import it.csi.epay.epaywsosrv.dto.ConfigAppDto;
import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.EndpointAppDto;
import it.csi.epay.epaywsosrv.dto.EndpointDto;
import it.csi.epay.epaywsosrv.dto.EnteDto;
import it.csi.epay.epaywsosrv.dto.EsitoInvioDto;
import it.csi.epay.epaywsosrv.dto.PaginazioneDto;
import it.csi.epay.epaywsosrv.dto.RichiestaBaseDto;
import it.csi.epay.epaywsosrv.dto.RichiestaDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightFilterDto;
import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;

//@formatter:off
/** cuscinetto intermedio tra business e dao, contiene semplice logica di accesso ai dati */
public interface EPaywsoDad {

	// verifica se il database risulta raggiungibile oppure eccezione
	public void ping() throws PersistenceException;

	public boolean existsEnteById(Integer idEnte, Timestamp timestamp) throws PersistenceException;
	public boolean existsEnteByCodFiscale(String codFiscaleEnte, Timestamp timestamp) throws PersistenceException;
	public EnteDto findEnteById(Integer idEnte, Timestamp timestamp) throws PersistenceException;
	public EnteDto findEnteByCodFiscale(String codFiscaleEnte, Timestamp timestamp) throws PersistenceException;

	public boolean existsAppById(Integer idApp, Timestamp timestamp) throws PersistenceException;
	public boolean existsAppByIdEnte(Integer idEnte, Timestamp timestamp) throws PersistenceException;
	public AppDto findAppById(Integer idApp, Timestamp timestamp) throws PersistenceException;
	public List<AppDto> findAllAppByIdEnte(Integer idEnte, Timestamp timestamp) throws PersistenceException;

	public boolean existsCodVerById(Integer idCodVers, Timestamp timestamp) throws PersistenceException;
	public boolean existsCodVerByCod(String codVers, Timestamp timestamp) throws PersistenceException;
	public CodVersDto findCodVersById(Integer idCodVers, Timestamp timestamp) throws PersistenceException;
	public List<CodVersDto> findAllCodVersByCod(String codVers, Timestamp timestamp) throws PersistenceException;
	public List<CodVersDto> findAllSpecialCodVers(Timestamp timestamp) throws PersistenceException;
	
	public boolean existsEndpointById(Integer idEndpoint, Timestamp timestamp) throws PersistenceException;
	public EndpointDto findEndpointById(Integer idEndpoint, Timestamp timestamp) throws PersistenceException;

	public boolean existsEndpointAppByKey(TipoRichiestaEnum tipoRichiestaEnum, Integer idApp, Integer idEndpoint) throws PersistenceException;
	public EndpointAppDto findEndpointAppByKey(TipoRichiestaEnum tipoRichiestaEnum, Integer idApp, Integer idEndpoint) throws PersistenceException;

	public List<EndpointAppDto> findAllEndpointAppByIdApp(Integer idApp, Timestamp timestamp) throws PersistenceException;
	public List<EndpointAppDto> findAllEndpointAppByTipoRichiesta(TipoRichiestaEnum tipoRichiestaEnum, Timestamp timestamp) throws PersistenceException;
	public List<EndpointAppDto> findAllEndpointAppByTipoRichiestaAndIdApp(TipoRichiestaEnum tipoRichiestaEnum, Integer idApp, Timestamp timestamp) throws PersistenceException;
	public List<EndpointAppDto> findAllEndpointAppByTipoRichiestaAndIdApps(TipoRichiestaEnum tipoRichiestaEnum, List<Integer> idApps, Timestamp timestamp) throws PersistenceException;
	
	public List<ConfigAppDto> findAllConfigAppBy(String codFiscaleEnte, List<String> codVersamentoList, TipoRichiestaEnum tipoRichiesta, Timestamp timestamp) throws PersistenceException;

	public boolean existsRichiestaById(Long idRichiesta) throws PersistenceException;
	public boolean existsRichiestaByMessageUUID(String messageUUID) throws PersistenceException;
	public Long findIdRichiestaByMessageUUID(String messageUUID) throws PersistenceException;
	public RichiestaDto findRichiestaById(Long idRichiesta) throws PersistenceException;
	public RichiestaDto findRichiestaByMessageUUID(String messageUUID) throws PersistenceException;
	public RichiestaLightDto findRichiestaByMessageUUIDLight(String messageUUID) throws PersistenceException;
	
	//
	// N.B. per le seguenti ricerche, usato nei job di monitaraggio, non si ottiene il dettaglio degli esiti di invio per ciascuna richiesta, se il cod-esito di primo livello indica un errore, gli esiti invio li si cercheranno poi a mano sul database
	public List<RichiestaDto> findAllRichiesteInErrore(Date dataUltimaEsecuzioneJob, Long secondi) throws PersistenceException;
	public List<RichiestaDto> findAllRichiesteScartate(Date dataUltimaEsecuzioneJob, Long secondi) throws PersistenceException;
	public List<RichiestaDto> findAllRichiesteNonInviate(Long secondiRDE, Long secondiREE) throws PersistenceException;
	//
	public Long insertRichiesta(RichiestaBaseDto richiestaDto) throws PersistenceException;
	public boolean updateRichiesta(RichiestaDto richiestaDto) throws PersistenceException;
	public boolean saveEsitoInvio(Long idRichiesta, EsitoInvioDto esitoInvioDto) throws PersistenceException;
	//
	public Long countAllRichiestaByFilter(RichiestaLightFilterDto filter) throws PersistenceException;
	public List<RichiestaLightDto> findAllRichiestaLightByFilter(RichiestaLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList, PaginazioneDto pag) throws PersistenceException;
	//
	public List<EsitoInvioDto> findAllEsitoInvioByIdRichiesta(Long idRichiesta) throws PersistenceException;
	
	public List<RichiestaDto> findAllRichiesteByMessaggioEnteTipoRichiesta(List<String> idMessaggioList,  Integer idTipoRichiesta) throws PersistenceException;
	
	public List<RichiestaDto> findAllRichiesteByMessaggioEnte(List<String> idMessaggioList) throws PersistenceException;

    boolean updateRichiestaLight ( RichiestaDto dto ) throws PersistenceException;

    public boolean existRichiestaByFilter ( RichiestaLightFilterDto filter ) throws PersistenceException;


}
//@formatter:on
