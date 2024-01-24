/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.ConfigurazioneDto;
import it.csi.epay.epaypaweb.dto.DatiAvvisoPagamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.StatoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.TipoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.enumeration.DirezioneEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;

//@formatter:off
/** cuscinetto intermedio tra business e dao, contiene semplice logica di accesso ai dati */
public interface CommonDad {

	public TipoFlussoDto findTipoFlussoByEnum(TipoFlussoEnum tipoFlussoEnum) throws PersistenceException;
	public StatoFlussoDto findStatoFlussoByEnum(StatoFlussoEnum statoFlussoEnum) throws PersistenceException;
	public TipoAggiornamentoPosizioneDebitoriaDto findTipoAggiornamentoPosizioneDebitoria(TipoAggiornamentoPosizioneDebitoriaEnum posizioneDebitoriaDaAggiornareEnum) throws PersistenceException;
	public TipoFormatoFileDto findTipoFormatoFileByEnum(TipoFormatoFileEnum tipoFormatoEnum) throws PersistenceException;
	//
	public List<TipoFlussoDto> findAllTipoFlusso() throws PersistenceException;
	public List<StatoFlussoDto> findAllStatoFlusso() throws PersistenceException;
	public List<StatoFlussoDto> findAllStatoFlussoByDirezione(DirezioneEnum direzione) throws PersistenceException;
	public List<TipoAggiornamentoPosizioneDebitoriaDto> findAllTipoAggiornamentoPosizioniDebitorie() throws PersistenceException;
	public List<TipoFormatoFileDto> findAllTipoFormatoFile() throws PersistenceException;

	public boolean existsEnteById(Integer idEnte) throws PersistenceException;
	public boolean existsEnteByCodFiscale(String codFiscaleEnte) throws PersistenceException;
	public EnteDto findEnteById(Integer idEnte) throws PersistenceException;
	public EnteDto findEnteByCodFiscale(String codFiscaleEnte) throws PersistenceException;

	public boolean existsCodiceVersamentoById(Integer idCodVersamento) throws PersistenceException;
	public boolean existsCodiceVersamentoByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException;
	public CodiceVersamentoDto findCodiceVersamentoById(Integer idCodVersamento) throws PersistenceException;
	public CodiceVersamentoDto findCodiceVersamentoByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException;

    public DatiAvvisoPagamentoDto findDatiEnteAvvisoPagamentoByCodVersamento ( String codFiscaleEnte, String codVersamento ) throws PersistenceException;
    public ConfigurazioneDto findConfigurazioneByCodiceAndCodFiscaleEnte ( String codice, String codFiscaleEnte ) throws PersistenceException;
}
//@formatter:on
