/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import java.util.List;

import it.csi.mdp.core.business.dto.ParametroWisp;
import it.csi.mdp.core.business.exceptions.DaoException;

public interface WispParamsDao
{
	public void insert(ParametroWisp toInsert) throws DaoException;

	public List<ParametroWisp> find(ParametroWisp filtro) throws DaoException;
	
	public int delete(ParametroWisp filtro) throws DaoException;
	
	public int update(ParametroWisp filtro) throws DaoException;
	
}
