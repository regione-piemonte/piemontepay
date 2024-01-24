/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.StrumentoPagamentoDao;
import it.csi.mdp.core.business.dto.StrumentoPagamento;
import it.csi.mdp.core.business.exceptions.StrumentoPagamentoDaoException;
import java.util.List;

public interface StrumentoPagamentoDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(StrumentoPagamento dto);

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria ''.
	 */
	public List<StrumentoPagamento> findAll() throws StrumentoPagamentoDaoException;

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'SP_ID = :spId'.
	 */
	public List<StrumentoPagamento> findWhereSpIdEquals(long spId) throws StrumentoPagamentoDaoException;

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	public List<StrumentoPagamento> findWhereTransactionIdEquals(String transactionId) throws StrumentoPagamentoDaoException;

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'NOME = :nome'.
	 */
	public List<StrumentoPagamento> findWhereNomeEquals(String nome) throws StrumentoPagamentoDaoException;

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'COD = :cod'.
	 */
	public List<StrumentoPagamento> findWhereCodEquals(String cod) throws StrumentoPagamentoDaoException;

}
