/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.TipoVersamento;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.util.Date;
import java.util.List;

public interface InformativePSPDao
{
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<InformativePSP> findAll() throws DaoException;
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<InformativePSP> findAllAttive() throws DaoException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public InformativePSP findWhereKeyidEquals(Integer id) throws DaoException;

	/**
	 * 
	 * @param idinformativapsp
	 * @param identificativoFlusso
	 * @param identificativoPSP
	 * @param ragioneSociale
	 * @param dataPubblicazione
	 * @param dataInizioValidita
	 * @param urlInformazioniPSP
	 * @param stornoPagamento
	 * @param identificativoIntermediario
	 * @param identificativoCanale
	 * @param tipoVersamento
	 * @param modelloPagamento
	 * @param priorita
	 * @param disponibilitaServizio
	 * @param descrizioneServizio
	 * @param condizioniEconomicheMassime
	 * @param urlInformazioniCanale
	 * @param datainserimento
	 * @param statoinserimento
	 * @param ordinamento
	 * @param origine
	 * @return
	 * @throws DaoException
	 */
	public List<InformativePSP> getInformativePSPByParam(
			Integer idinformativapsp,
			String identificativoFlusso,
			String identificativoPSP,
			String ragioneSociale,
			Date dataPubblicazione,
			Date dataInizioValidita,
			String urlInformazioniPSP,
			Integer stornoPagamento,
			String identificativoIntermediario,
			String identificativoCanale,
			String tipoVersamento,
			Integer modelloPagamento,
			Integer priorita,
			String disponibilitaServizio,
			String descrizioneServizio,
			String condizioniEconomicheMassime,
			String urlInformazioniCanale,
			Date datainserimento,
			String statoinserimento,
			Integer ordinamento,
			String origine
			
	) throws DaoException;

	public List<TipoVersamento> getListaTipoversamento() throws DaoException;
	
	public List<String> getListaIdentificativopsp()throws DaoException;
	
	public InformativePSP recuperaInformativaPSPSceltaWisp(String applicationId, String transactionId) throws DaoException;
	
}
