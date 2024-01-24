/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.GiornaleEventoDaoException;

import java.util.Date;
import java.util.List;

public interface GiornaleEventoDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(GiornaleEvento dto) throws GiornaleEventoDaoException;
	
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<GiornaleEvento> findAll() throws GiornaleEventoDaoException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public GiornaleEvento getGiornaleEventoById(Integer id) throws GiornaleEventoDaoException;
	
	/**
	 * 
	 * @param iuv
	 * @param dataOraEvento
	 * @param identificativodominio
	 * @param identificativofruitore
	 * @param codiceContesto
	 * @return
	 * @throws DaoException
	 */
	public List<GiornaleEvento> getGiornaleEventoByParam(String iuv,
			Date dataOraEvento, String identificativodominio,
			String identificativofruitore, String codiceContesto) throws GiornaleEventoDaoException;
}
