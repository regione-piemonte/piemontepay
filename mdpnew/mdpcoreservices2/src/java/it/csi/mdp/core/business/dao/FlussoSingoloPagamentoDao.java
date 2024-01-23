/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.FlussoSingoloPagamento;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.util.Date;
import java.util.List;

public interface  FlussoSingoloPagamentoDao {

	public List<FlussoSingoloPagamento> getFlussoSingoloPagamentoByParam(
														Integer id,
														Integer idFlusso,
														String   iuv,
														String   identificativounivocoriscossione,
														Double   singoloimportopagato,
														String   codiceesitosingolopagamento,
														Date     dataesitosingolopagamento,
														Date     datainserimento,
														Date     datamodifica,
														String   applicationId,
														Date      dataregolamentoDa,
														Date      dataregolamentoA) throws DaoException;
	
	
	
}
