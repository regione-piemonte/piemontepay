/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import it.csi.mdp.core.business.dao.*;
import it.csi.mdp.core.business.factory.*;
import it.csi.mdp.core.business.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class StatoTransazione implements Serializable
{
	/** 
	 * This attribute maps to the column descrizione in the stato_transazione table.
	 */
	protected String descrizione;

	/** 
	 * This attribute maps to the column cod_stato in the stato_transazione table.
	 */
	protected int codStato;

	/** 
	 * This attribute maps to the column descrizoneestesa in the stato_transazione table.
	 */
	protected String descrizoneestesa;

	/**
	 * Method 'StatoTransazione'
	 * 
	 */
	public StatoTransazione()
	{
	}

	/**
	 * Method 'getDescrizione'
	 * 
	 * @return String
	 */
	public String getDescrizione()
	{
		return descrizione;
	}

	/**
	 * Method 'setDescrizione'
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	/**
	 * Method 'getCodStato'
	 * 
	 * @return int
	 */
	public int getCodStato()
	{
		return codStato;
	}

	/**
	 * Method 'setCodStato'
	 * 
	 * @param codStato
	 */
	public void setCodStato(int codStato)
	{
		this.codStato = codStato;
	}

	/**
	 * Method 'getDescrizoneestesa'
	 * 
	 * @return String
	 */
	public String getDescrizoneestesa()
	{
		return descrizoneestesa;
	}

	/**
	 * Method 'setDescrizoneestesa'
	 * 
	 * @param descrizoneestesa
	 */
	public void setDescrizoneestesa(String descrizoneestesa)
	{
		this.descrizoneestesa = descrizoneestesa;
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
		
		if (!(_other instanceof StatoTransazione)) {
			return false;
		}
		
		final StatoTransazione _cast = (StatoTransazione) _other;
		if (descrizione == null ? _cast.descrizione != descrizione : !descrizione.equals( _cast.descrizione )) {
			return false;
		}
		
		if (codStato != _cast.codStato) {
			return false;
		}
		
		if (descrizoneestesa == null ? _cast.descrizoneestesa != descrizoneestesa : !descrizoneestesa.equals( _cast.descrizoneestesa )) {
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
		if (descrizione != null) {
			_hashCode = 29 * _hashCode + descrizione.hashCode();
		}
		
		_hashCode = 29 * _hashCode + codStato;
		if (descrizoneestesa != null) {
			_hashCode = 29 * _hashCode + descrizoneestesa.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return StatoTransazionePk
	 */
	public StatoTransazionePk createPk()
	{
		return new StatoTransazionePk(codStato);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.StatoTransazione: " );
		ret.append( "descrizione=" + descrizione );
		ret.append( ", codStato=" + codStato );
		ret.append( ", descrizoneestesa=" + descrizoneestesa );
		return ret.toString();
	}

}
