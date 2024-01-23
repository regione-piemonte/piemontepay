/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.util.List;

public interface ElementoMultiVersamentoDao
{
	public void insert(ElementoMultiversamento toInsert) throws DaoException;

	public List<ElementoMultiversamento> find(ElementoMultiversamento filtro) throws DaoException;
	
	public int delete(ElementoMultiversamento filtro) throws DaoException;
	
	public int update(ElementoMultiversamento filtro) throws DaoException;
	
}
