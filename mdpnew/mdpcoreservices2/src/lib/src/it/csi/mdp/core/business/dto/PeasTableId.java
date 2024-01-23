/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class PeasTableId implements Serializable
{
	/** 
	 * This attribute maps to the column TABLE_PK in the PEAS_TABLE_ID table.
	 */
	protected String tablePk;

	/** 
	 * This attribute maps to the column TABLE_ID in the PEAS_TABLE_ID table.
	 */
	protected String tableId;

	/**
	 * Method 'PeasTableId'
	 * 
	 */
	public PeasTableId()
	{
	}

	/**
	 * Method 'getTablePk'
	 * 
	 * @return String
	 */
	public String getTablePk()
	{
		return tablePk;
	}

	/**
	 * Method 'setTablePk'
	 * 
	 * @param tablePk
	 */
	public void setTablePk(String tablePk)
	{
		this.tablePk = tablePk;
	}

	/**
	 * Method 'getTableId'
	 * 
	 * @return String
	 */
	public String getTableId()
	{
		return tableId;
	}

	/**
	 * Method 'setTableId'
	 * 
	 * @param tableId
	 */
	public void setTableId(String tableId)
	{
		this.tableId = tableId;
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
		
		if (!(_other instanceof PeasTableId)) {
			return false;
		}
		
		final PeasTableId _cast = (PeasTableId) _other;
		if (tablePk == null ? _cast.tablePk != tablePk : !tablePk.equals( _cast.tablePk )) {
			return false;
		}
		
		if (tableId == null ? _cast.tableId != tableId : !tableId.equals( _cast.tableId )) {
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
		if (tablePk != null) {
			_hashCode = 29 * _hashCode + tablePk.hashCode();
		}
		
		if (tableId != null) {
			_hashCode = 29 * _hashCode + tableId.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.PeasTableId: " );
		ret.append( "tablePk=" + tablePk );
		ret.append( ", tableId=" + tableId );
		return ret.toString();
	}

}
