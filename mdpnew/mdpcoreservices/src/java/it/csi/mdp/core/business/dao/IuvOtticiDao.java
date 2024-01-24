/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.IuvOttici;
import it.csi.mdp.core.business.exceptions.DaoException;

public interface IuvOtticiDao
{
	public IuvOttici find (IuvOttici filtro) throws DaoException;

}
