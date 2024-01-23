/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.CodiciEsitoPagamento;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.CodiciEsitoPagamentoDaoException;

import java.util.Date;
import java.util.List;

public interface CodiciEsitoPagamentoDao
{
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<CodiciEsitoPagamento> getCodiciEsitoPagamentoAll() throws CodiciEsitoPagamentoDaoException;


}
