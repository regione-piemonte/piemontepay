/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.business;

import java.util.List;

import it.csi.epay.epaywsosrv.dto.ConfigAppDto;
import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.EnteDto;
import it.csi.epay.epaywsosrv.dto.EsitoInvioDto;
import it.csi.epay.epaywsosrv.dto.EsitoStatoAggregatoDto;
import it.csi.epay.epaywsosrv.dto.PaginazioneDto;
import it.csi.epay.epaywsosrv.dto.RicercaStatoAggregatoDto;
import it.csi.epay.epaywsosrv.dto.RichiestaBaseDto;
import it.csi.epay.epaywsosrv.dto.RichiestaDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightFilterDto;
import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.BusinessException;

//@formatter:off
/** logica di business */
public interface EPaywsoBusiness {

	/** ottiene l'ente corrispondente all'codice fiscale, se non lo trova o non risulta valido emette eccezione se boolean true */
	public EnteDto getEnte(String codFiscale, boolean throwsExceptionIfNotFound) throws BusinessException;

	/** ottiene la/le configurazion(e/i) dell'applicativo ottenuta incrociando il codice fiscale ente, il codice versamento e il tipo di richiesta */
	public ConfigAppDto getConfigApp(String codFiscaleEnte, String codVersamento, TipoRichiestaEnum tipoRichiestaEnum) throws BusinessException;
	
	public List<ConfigAppDto> getConfigAppList(String codFiscaleEnte, List<String> codVersamentoList, TipoRichiestaEnum tipoRichiestaEnum) throws BusinessException;

	/** lettura della richiesta dal database */
	public RichiestaDto getRichiesta(String messageUUID) throws BusinessException;

	/** inserimento della nuova richiesta nel database */
	public Long insertRichiesta(RichiestaBaseDto richiestaDto) throws BusinessException;

	/** aggiornamento della richiesta nel database */
	public void updateRichiesta(RichiestaDto richiestaDto) throws BusinessException;
	public void updateRichiestaESingoloEsitoInvio(RichiestaDto richiestaDto, EsitoInvioDto esitoInvioDto) throws BusinessException;

	/** aggiornamento di un esito invio di una richiesta nel database */
	public void saveEsitoInvio(String messageUUID, EsitoInvioDto dto) throws BusinessException;

	/** aggiornamento dell'esito complessivo della richiesta sulla base degli esiti invio correnti */
	public void aggiornaEsitoComplessivo(String messageUUID) throws BusinessException;

	/** ottiene il numero delle richieste selezionate in base al filtro di selezione */
	public Long countRichieste(RichiestaLightFilterDto filter) throws BusinessException;

	/** ottiene la lista delle richieste "snelle" in base al filtro di selezione, criteri di ordinamento e impostazioni di paginazione */
	public List<RichiestaLightDto> getRichiestaLightList(RichiestaLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList, PaginazioneDto pag) throws BusinessException;

	/** ottiene il successivo endpoint da richiamare nella fase di invio del flusso di rendicontazione */
	public ConfigAppDto determinaProssimoEndpoint(String messageUUID, List<String> codVersamentoList, int tolleranzaSec) throws BusinessException;

	/**  */
	List<EsitoStatoAggregatoDto> ricercaStatoAggregato(List<RicercaStatoAggregatoDto> listRicerca, Integer idTipoRichiesta) throws BusinessException;

    public void updateRichiestaLight ( RichiestaDto dto ) throws BusinessException;

    public boolean existRichieste ( RichiestaLightFilterDto filter ) throws BusinessException;
    
    

}
//@formatter:on
