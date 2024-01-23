/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class StrumentoPagamento implements Serializable
{
	/** 
	 * This attribute maps to the column SP_ID in the STRUMENTO_PAGAMENTO table.
	 */
	protected long spId;

	/** 
	 * This attribute maps to the column TRANSACTION_ID in the STRUMENTO_PAGAMENTO table.
	 */
	protected String transactionId;

	/** 
	 * This attribute maps to the column NOME in the STRUMENTO_PAGAMENTO table.
	 */
	protected String nome;

	/** 
	 * This attribute maps to the column COD in the STRUMENTO_PAGAMENTO table.
	 */
	protected String cod;

	/**
	 * Method 'StrumentoPagamento'
	 * 
	 */
	public StrumentoPagamento()
	{
	}

	/**
	 * Method 'getSpId'
	 * 
	 * @return long
	 */
	public long getSpId()
	{
		return spId;
	}

	/**
	 * Method 'setSpId'
	 * 
	 * @param spId
	 */
	public void setSpId(long spId)
	{
		this.spId = spId;
	}

	/**
	 * Method 'getTransactionId'
	 * 
	 * @return String
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * Method 'setTransactionId'
	 * 
	 * @param transactionId
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	/**
	 * Method 'getNome'
	 * 
	 * @return String
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Method 'setNome'
	 * 
	 * @param nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Method 'getCod'
	 * 
	 * @return String
	 */
	public String getCod()
	{
		return cod;
	}

	/**
	 * Method 'setCod'
	 * 
	 * @param cod
	 */
	public void setCod(String cod)
	{
		this.cod = cod;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof StrumentoPagamento)) {
			return false;
		}
		
		final StrumentoPagamento _cast = (StrumentoPagamento) _other;
		if (spId != _cast.spId) {
			return false;
		}
		
		if (transactionId == null ? _cast.transactionId != transactionId : !transactionId.equals( _cast.transactionId )) {
			return false;
		}
		
		if (nome == null ? _cast.nome != nome : !nome.equals( _cast.nome )) {
			return false;
		}
		
		if (cod == null ? _cast.cod != cod : !cod.equals( _cast.cod )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + (int) (spId ^ (spId >>> 32));
		if (transactionId != null) {
			_hashCode = 29 * _hashCode + transactionId.hashCode();
		}
		
		if (nome != null) {
			_hashCode = 29 * _hashCode + nome.hashCode();
		}
		
		if (cod != null) {
			_hashCode = 29 * _hashCode + cod.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.StrumentoPagamento: " );
		ret.append( "spId=" + spId );
		ret.append( ", transactionId=" + transactionId );
		ret.append( ", nome=" + nome );
		ret.append( ", cod=" + cod );
		return ret.toString();
	}

}
