/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.Date;

public class StatiEsteriMin implements Serializable
{
	/** 
	 * This attribute maps to the column ID_STATO_MINISTERO in the STATI_ESTERI_MIN table.
	 */
	protected long idStatoMinistero;

	/** 
	 * This attribute represents whether the primitive attribute idStatoMinistero is null.
	 */
	protected boolean idStatoMinisteroNull = true;

	/** 
	 * This attribute maps to the column CONTINENTE in the STATI_ESTERI_MIN table.
	 */
	protected String continente;

	/** 
	 * This attribute maps to the column STATO in the STATI_ESTERI_MIN table.
	 */
	protected String stato;

	/** 
	 * This attribute maps to the column TERRITORIO in the STATI_ESTERI_MIN table.
	 */
	protected String territorio;

	/** 
	 * This attribute maps to the column CODICE in the STATI_ESTERI_MIN table.
	 */
	protected String codice;

	/** 
	 * This attribute maps to the column D_START in the STATI_ESTERI_MIN table.
	 */
	protected Date dStart;

	/** 
	 * This attribute maps to the column D_STOP in the STATI_ESTERI_MIN table.
	 */
	protected Date dStop;

	/** 
	 * This attribute maps to the column CODRIF in the STATI_ESTERI_MIN table.
	 */
	protected String codrif;

	/** 
	 * This attribute maps to the column CODICE_PREV in the STATI_ESTERI_MIN table.
	 */
	protected String codicePrev;

	/** 
	 * This attribute maps to the column CODICE_NEXT in the STATI_ESTERI_MIN table.
	 */
	protected String codiceNext;

	/** 
	 * This attribute maps to the column R_STATUS in the STATI_ESTERI_MIN table.
	 */
	protected String rStatus;

	/**
	 * Method 'StatiEsteriMin'
	 * 
	 */
	public StatiEsteriMin()
	{
	}

	/**
	 * Method 'getIdStatoMinistero'
	 * 
	 * @return long
	 */
	public long getIdStatoMinistero()
	{
		return idStatoMinistero;
	}

	/**
	 * Method 'setIdStatoMinistero'
	 * 
	 * @param idStatoMinistero
	 */
	public void setIdStatoMinistero(long idStatoMinistero)
	{
		this.idStatoMinistero = idStatoMinistero;
		this.idStatoMinisteroNull = false;
	}

	/**
	 * Method 'setIdStatoMinisteroNull'
	 * 
	 * @param value
	 */
	public void setIdStatoMinisteroNull(boolean value)
	{
		this.idStatoMinisteroNull = value;
	}

	/**
	 * Method 'isIdStatoMinisteroNull'
	 * 
	 * @return boolean
	 */
	public boolean isIdStatoMinisteroNull()
	{
		return idStatoMinisteroNull;
	}

	/**
	 * Method 'getContinente'
	 * 
	 * @return String
	 */
	public String getContinente()
	{
		return continente;
	}

	/**
	 * Method 'setContinente'
	 * 
	 * @param continente
	 */
	public void setContinente(String continente)
	{
		this.continente = continente;
	}

	/**
	 * Method 'getStato'
	 * 
	 * @return String
	 */
	public String getStato()
	{
		return stato;
	}

	/**
	 * Method 'setStato'
	 * 
	 * @param stato
	 */
	public void setStato(String stato)
	{
		this.stato = stato;
	}

	/**
	 * Method 'getTerritorio'
	 * 
	 * @return String
	 */
	public String getTerritorio()
	{
		return territorio;
	}

	/**
	 * Method 'setTerritorio'
	 * 
	 * @param territorio
	 */
	public void setTerritorio(String territorio)
	{
		this.territorio = territorio;
	}

	/**
	 * Method 'getCodice'
	 * 
	 * @return String
	 */
	public String getCodice()
	{
		return codice;
	}

	/**
	 * Method 'setCodice'
	 * 
	 * @param codice
	 */
	public void setCodice(String codice)
	{
		this.codice = codice;
	}

	/**
	 * Method 'getDStart'
	 * 
	 * @return Date
	 */
	public Date getDStart()
	{
		return dStart;
	}

	/**
	 * Method 'setDStart'
	 * 
	 * @param dStart
	 */
	public void setDStart(Date dStart)
	{
		this.dStart = dStart;
	}

	/**
	 * Method 'getDStop'
	 * 
	 * @return Date
	 */
	public Date getDStop()
	{
		return dStop;
	}

	/**
	 * Method 'setDStop'
	 * 
	 * @param dStop
	 */
	public void setDStop(Date dStop)
	{
		this.dStop = dStop;
	}

	/**
	 * Method 'getCodrif'
	 * 
	 * @return String
	 */
	public String getCodrif()
	{
		return codrif;
	}

	/**
	 * Method 'setCodrif'
	 * 
	 * @param codrif
	 */
	public void setCodrif(String codrif)
	{
		this.codrif = codrif;
	}

	/**
	 * Method 'getCodicePrev'
	 * 
	 * @return String
	 */
	public String getCodicePrev()
	{
		return codicePrev;
	}

	/**
	 * Method 'setCodicePrev'
	 * 
	 * @param codicePrev
	 */
	public void setCodicePrev(String codicePrev)
	{
		this.codicePrev = codicePrev;
	}

	/**
	 * Method 'getCodiceNext'
	 * 
	 * @return String
	 */
	public String getCodiceNext()
	{
		return codiceNext;
	}

	/**
	 * Method 'setCodiceNext'
	 * 
	 * @param codiceNext
	 */
	public void setCodiceNext(String codiceNext)
	{
		this.codiceNext = codiceNext;
	}

	/**
	 * Method 'getRStatus'
	 * 
	 * @return String
	 */
	public String getRStatus()
	{
		return rStatus;
	}

	/**
	 * Method 'setRStatus'
	 * 
	 * @param rStatus
	 */
	public void setRStatus(String rStatus)
	{
		this.rStatus = rStatus;
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
		
		if (!(_other instanceof StatiEsteriMin)) {
			return false;
		}
		
		final StatiEsteriMin _cast = (StatiEsteriMin) _other;
		if (idStatoMinistero != _cast.idStatoMinistero) {
			return false;
		}
		
		if (idStatoMinisteroNull != _cast.idStatoMinisteroNull) {
			return false;
		}
		
		if (continente == null ? _cast.continente != continente : !continente.equals( _cast.continente )) {
			return false;
		}
		
		if (stato == null ? _cast.stato != stato : !stato.equals( _cast.stato )) {
			return false;
		}
		
		if (territorio == null ? _cast.territorio != territorio : !territorio.equals( _cast.territorio )) {
			return false;
		}
		
		if (codice == null ? _cast.codice != codice : !codice.equals( _cast.codice )) {
			return false;
		}
		
		if (dStart == null ? _cast.dStart != dStart : !dStart.equals( _cast.dStart )) {
			return false;
		}
		
		if (dStop == null ? _cast.dStop != dStop : !dStop.equals( _cast.dStop )) {
			return false;
		}
		
		if (codrif == null ? _cast.codrif != codrif : !codrif.equals( _cast.codrif )) {
			return false;
		}
		
		if (codicePrev == null ? _cast.codicePrev != codicePrev : !codicePrev.equals( _cast.codicePrev )) {
			return false;
		}
		
		if (codiceNext == null ? _cast.codiceNext != codiceNext : !codiceNext.equals( _cast.codiceNext )) {
			return false;
		}
		
		if (rStatus == null ? _cast.rStatus != rStatus : !rStatus.equals( _cast.rStatus )) {
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
		_hashCode = 29 * _hashCode + (int) (idStatoMinistero ^ (idStatoMinistero >>> 32));
		_hashCode = 29 * _hashCode + (idStatoMinisteroNull ? 1 : 0);
		if (continente != null) {
			_hashCode = 29 * _hashCode + continente.hashCode();
		}
		
		if (stato != null) {
			_hashCode = 29 * _hashCode + stato.hashCode();
		}
		
		if (territorio != null) {
			_hashCode = 29 * _hashCode + territorio.hashCode();
		}
		
		if (codice != null) {
			_hashCode = 29 * _hashCode + codice.hashCode();
		}
		
		if (dStart != null) {
			_hashCode = 29 * _hashCode + dStart.hashCode();
		}
		
		if (dStop != null) {
			_hashCode = 29 * _hashCode + dStop.hashCode();
		}
		
		if (codrif != null) {
			_hashCode = 29 * _hashCode + codrif.hashCode();
		}
		
		if (codicePrev != null) {
			_hashCode = 29 * _hashCode + codicePrev.hashCode();
		}
		
		if (codiceNext != null) {
			_hashCode = 29 * _hashCode + codiceNext.hashCode();
		}
		
		if (rStatus != null) {
			_hashCode = 29 * _hashCode + rStatus.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.StatiEsteriMin: " );
		ret.append( "idStatoMinistero=" + idStatoMinistero );
		ret.append( ", continente=" + continente );
		ret.append( ", stato=" + stato );
		ret.append( ", territorio=" + territorio );
		ret.append( ", codice=" + codice );
		ret.append( ", dStart=" + dStart );
		ret.append( ", dStop=" + dStop );
		ret.append( ", codrif=" + codrif );
		ret.append( ", codicePrev=" + codicePrev );
		ret.append( ", codiceNext=" + codiceNext );
		ret.append( ", rStatus=" + rStatus );
		return ret.toString();
	}

}
