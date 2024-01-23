/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.StatiRpt;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.StatiRptDaoException;

import java.util.List;

public interface StatiRptDao
{
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<StatiRpt> getStatiRptAll() throws StatiRptDaoException;


}
