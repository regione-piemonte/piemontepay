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
import java.util.Date;

public class MdpErrori implements Serializable
{
	/** 
	 * This attribute maps to the column id in the mdp_errori table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column descrizione in the mdp_errori table.
	 */
	protected String descrizione;

	/** 
	 * This attribute maps to the column application_id in the mdp_errori table.
	 */
	protected String applicationId;

	/** 
	 * This attribute maps to the column data in the mdp_errori table.
	 */
	protected Date data;

	/** 
	 * This attribute maps to the column transaction_id in the mdp_errori table.
	 */
	protected String transactionId;

	/**
	 * Method 'MdpErrori'
	 * 
	 */
	public MdpErrori()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return int
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
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
	 * Method 'getApplicationId'
	 * 
	 * @return String
	 */
	public String getApplicationId()
	{
		return applicationId;
	}

	/**
	 * Method 'setApplicationId'
	 * 
	 * @param applicationId
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * Method 'getData'
	 * 
	 * @return Date
	 */
	public Date getData()
	{
		return data;
	}

	/**
	 * Method 'setData'
	 * 
	 * @param data
	 */
	public void setData(Date data)
	{
		this.data = data;
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
		
		if (!(_other instanceof MdpErrori)) {
			return false;
		}
		
		final MdpErrori _cast = (MdpErrori) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (descrizione == null ? _cast.descrizione != descrizione : !descrizione.equals( _cast.descrizione )) {
			return false;
		}
		
		if (applicationId == null ? _cast.applicationId != applicationId : !applicationId.equals( _cast.applicationId )) {
			return false;
		}
		
		if (data == null ? _cast.data != data : !data.equals( _cast.data )) {
			return false;
		}
		
		if (transactionId == null ? _cast.transactionId != transactionId : !transactionId.equals( _cast.transactionId )) {
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
		_hashCode = 29 * _hashCode + id;
		if (descrizione != null) {
			_hashCode = 29 * _hashCode + descrizione.hashCode();
		}
		
		if (applicationId != null) {
			_hashCode = 29 * _hashCode + applicationId.hashCode();
		}
		
		if (data != null) {
			_hashCode = 29 * _hashCode + data.hashCode();
		}
		
		if (transactionId != null) {
			_hashCode = 29 * _hashCode + transactionId.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return MdpErroriPk
	 */
	public MdpErroriPk createPk()
	{
		return new MdpErroriPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.MdpErrori: " );
		ret.append( "id=" + id );
		ret.append( ", descrizione=" + descrizione );
		ret.append( ", applicationId=" + applicationId );
		ret.append( ", data=" + data );
		ret.append( ", transactionId=" + transactionId );
		return ret.toString();
	}

}
