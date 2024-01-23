/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import java.util.List;

import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.exceptions.DaoException;

public interface NodoErroriDao
{
	/**
	 * Inserisce un errore
	 * @param dto
	 * @return
	 */
	public Integer insert(NodoErrore dto) throws DaoException;
	
	public List<NodoErrore> find (NodoErrore filtro) throws DaoException;


}
