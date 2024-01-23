/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the stato_transazione table.
 */
public class StatoTransazionePk implements Serializable
{
	protected int codStato;

	/** 
	 * This attribute represents whether the primitive attribute codStato is null.
	 */
	protected boolean codStatoNull;

	/** 
	 * Sets the value of codStato
	 */
	public void setCodStato(int codStato)
	{
		this.codStato = codStato;
	}

	/** 
	 * Gets the value of codStato
	 */
	public int getCodStato()
	{
		return codStato;
	}

	/**
	 * Method 'StatoTransazionePk'
	 * 
	 */
	public StatoTransazionePk()
	{
	}

	/**
	 * Method 'StatoTransazionePk'
	 * 
	 * @param codStato
	 */
	public StatoTransazionePk(final int codStato)
	{
		this.codStato = codStato;
	}

	/** 
	 * Sets the value of codStatoNull
	 */
	public void setCodStatoNull(boolean codStatoNull)
	{
		this.codStatoNull = codStatoNull;
	}

	/** 
	 * Gets the value of codStatoNull
	 */
	public boolean isCodStatoNull()
	{
		return codStatoNull;
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
		
		if (!(_other instanceof StatoTransazionePk)) {
			return false;
		}
		
		final StatoTransazionePk _cast = (StatoTransazionePk) _other;
		if (codStato != _cast.codStato) {
			return false;
		}
		
		if (codStatoNull != _cast.codStatoNull) {
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
		_hashCode = 29 * _hashCode + codStato;
		_hashCode = 29 * _hashCode + (codStatoNull ? 1 : 0);
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
		ret.append( "it.csi.mdp.core.business.dto.StatoTransazionePk: " );
		ret.append( "codStato=" + codStato );
		return ret.toString();
	}

}
