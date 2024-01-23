/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class PartAnComune implements Serializable
{
	/** 
	 * This attribute maps to the column ID_COMUNE in the PART_AN_COMUNE table.
	 */
	protected long idComune;

	/** 
	 * This attribute maps to the column R_STATUS in the PART_AN_COMUNE table.
	 */
	protected String rStatus;

	/** 
	 * This attribute maps to the column ID_COMUNE_NEXT in the PART_AN_COMUNE table.
	 */
	protected long idComuneNext;

	/** 
	 * This attribute represents whether the primitive attribute idComuneNext is null.
	 */
	protected boolean idComuneNextNull = true;

	/** 
	 * This attribute maps to the column ID_COMUNE_PREV in the PART_AN_COMUNE table.
	 */
	protected long idComunePrev;

	/** 
	 * This attribute represents whether the primitive attribute idComunePrev is null.
	 */
	protected boolean idComunePrevNull = true;

	/** 
	 * This attribute maps to the column D_START in the PART_AN_COMUNE table.
	 */
	protected Date dStart;

	/** 
	 * This attribute maps to the column D_STOP in the PART_AN_COMUNE table.
	 */
	protected Date dStop;

	/** 
	 * This attribute maps to the column ISTAT_COMUNE in the PART_AN_COMUNE table.
	 */
	protected String istatComune;

	/** 
	 * This attribute maps to the column DESC_COMUNE in the PART_AN_COMUNE table.
	 */
	protected String descComune;

	/** 
	 * This attribute maps to the column CAP in the PART_AN_COMUNE table.
	 */
	protected String cap;

	/** 
	 * This attribute maps to the column ISTAT_PROVINCIA in the PART_AN_COMUNE table.
	 */
	protected String istatProvincia;

	/** 
	 * This attribute maps to the column DESC_PROVINCIA in the PART_AN_COMUNE table.
	 */
	protected String descProvincia;

	/** 
	 * This attribute maps to the column SIGLA_PROV in the PART_AN_COMUNE table.
	 */
	protected String siglaProv;

	/** 
	 * This attribute maps to the column ISTAT_REGIONE in the PART_AN_COMUNE table.
	 */
	protected String istatRegione;

	/** 
	 * This attribute maps to the column DESC_REGIONE in the PART_AN_COMUNE table.
	 */
	protected String descRegione;

	/**
	 * Method 'PartAnComune'
	 * 
	 */
	public PartAnComune()
	{
	}

	/**
	 * Method 'getIdComune'
	 * 
	 * @return long
	 */
	public long getIdComune()
	{
		return idComune;
	}

	/**
	 * Method 'setIdComune'
	 * 
	 * @param idComune
	 */
	public void setIdComune(long idComune)
	{
		this.idComune = idComune;
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
	 * Method 'getIdComuneNext'
	 * 
	 * @return long
	 */
	public long getIdComuneNext()
	{
		return idComuneNext;
	}

	/**
	 * Method 'setIdComuneNext'
	 * 
	 * @param idComuneNext
	 */
	public void setIdComuneNext(long idComuneNext)
	{
		this.idComuneNext = idComuneNext;
		this.idComuneNextNull = false;
	}

	/**
	 * Method 'setIdComuneNextNull'
	 * 
	 * @param value
	 */
	public void setIdComuneNextNull(boolean value)
	{
		this.idComuneNextNull = value;
	}

	/**
	 * Method 'isIdComuneNextNull'
	 * 
	 * @return boolean
	 */
	public boolean isIdComuneNextNull()
	{
		return idComuneNextNull;
	}

	/**
	 * Method 'getIdComunePrev'
	 * 
	 * @return long
	 */
	public long getIdComunePrev()
	{
		return idComunePrev;
	}

	/**
	 * Method 'setIdComunePrev'
	 * 
	 * @param idComunePrev
	 */
	public void setIdComunePrev(long idComunePrev)
	{
		this.idComunePrev = idComunePrev;
		this.idComunePrevNull = false;
	}

	/**
	 * Method 'setIdComunePrevNull'
	 * 
	 * @param value
	 */
	public void setIdComunePrevNull(boolean value)
	{
		this.idComunePrevNull = value;
	}

	/**
	 * Method 'isIdComunePrevNull'
	 * 
	 * @return boolean
	 */
	public boolean isIdComunePrevNull()
	{
		return idComunePrevNull;
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
	 * Method 'getIstatComune'
	 * 
	 * @return String
	 */
	public String getIstatComune()
	{
		return istatComune;
	}

	/**
	 * Method 'setIstatComune'
	 * 
	 * @param istatComune
	 */
	public void setIstatComune(String istatComune)
	{
		this.istatComune = istatComune;
	}

	/**
	 * Method 'getDescComune'
	 * 
	 * @return String
	 */
	public String getDescComune()
	{
		return descComune;
	}

	/**
	 * Method 'setDescComune'
	 * 
	 * @param descComune
	 */
	public void setDescComune(String descComune)
	{
		this.descComune = descComune;
	}

	/**
	 * Method 'getCap'
	 * 
	 * @return String
	 */
	public String getCap()
	{
		return cap;
	}

	/**
	 * Method 'setCap'
	 * 
	 * @param cap
	 */
	public void setCap(String cap)
	{
		this.cap = cap;
	}

	/**
	 * Method 'getIstatProvincia'
	 * 
	 * @return String
	 */
	public String getIstatProvincia()
	{
		return istatProvincia;
	}

	/**
	 * Method 'setIstatProvincia'
	 * 
	 * @param istatProvincia
	 */
	public void setIstatProvincia(String istatProvincia)
	{
		this.istatProvincia = istatProvincia;
	}

	/**
	 * Method 'getDescProvincia'
	 * 
	 * @return String
	 */
	public String getDescProvincia()
	{
		return descProvincia;
	}

	/**
	 * Method 'setDescProvincia'
	 * 
	 * @param descProvincia
	 */
	public void setDescProvincia(String descProvincia)
	{
		this.descProvincia = descProvincia;
	}

	/**
	 * Method 'getSiglaProv'
	 * 
	 * @return String
	 */
	public String getSiglaProv()
	{
		return siglaProv;
	}

	/**
	 * Method 'setSiglaProv'
	 * 
	 * @param siglaProv
	 */
	public void setSiglaProv(String siglaProv)
	{
		this.siglaProv = siglaProv;
	}

	/**
	 * Method 'getIstatRegione'
	 * 
	 * @return String
	 */
	public String getIstatRegione()
	{
		return istatRegione;
	}

	/**
	 * Method 'setIstatRegione'
	 * 
	 * @param istatRegione
	 */
	public void setIstatRegione(String istatRegione)
	{
		this.istatRegione = istatRegione;
	}

	/**
	 * Method 'getDescRegione'
	 * 
	 * @return String
	 */
	public String getDescRegione()
	{
		return descRegione;
	}

	/**
	 * Method 'setDescRegione'
	 * 
	 * @param descRegione
	 */
	public void setDescRegione(String descRegione)
	{
		this.descRegione = descRegione;
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
		
		if (!(_other instanceof PartAnComune)) {
			return false;
		}
		
		final PartAnComune _cast = (PartAnComune) _other;
		if (idComune != _cast.idComune) {
			return false;
		}
		
		if (rStatus == null ? _cast.rStatus != rStatus : !rStatus.equals( _cast.rStatus )) {
			return false;
		}
		
		if (idComuneNext != _cast.idComuneNext) {
			return false;
		}
		
		if (idComuneNextNull != _cast.idComuneNextNull) {
			return false;
		}
		
		if (idComunePrev != _cast.idComunePrev) {
			return false;
		}
		
		if (idComunePrevNull != _cast.idComunePrevNull) {
			return false;
		}
		
		if (dStart == null ? _cast.dStart != dStart : !dStart.equals( _cast.dStart )) {
			return false;
		}
		
		if (dStop == null ? _cast.dStop != dStop : !dStop.equals( _cast.dStop )) {
			return false;
		}
		
		if (istatComune == null ? _cast.istatComune != istatComune : !istatComune.equals( _cast.istatComune )) {
			return false;
		}
		
		if (descComune == null ? _cast.descComune != descComune : !descComune.equals( _cast.descComune )) {
			return false;
		}
		
		if (cap == null ? _cast.cap != cap : !cap.equals( _cast.cap )) {
			return false;
		}
		
		if (istatProvincia == null ? _cast.istatProvincia != istatProvincia : !istatProvincia.equals( _cast.istatProvincia )) {
			return false;
		}
		
		if (descProvincia == null ? _cast.descProvincia != descProvincia : !descProvincia.equals( _cast.descProvincia )) {
			return false;
		}
		
		if (siglaProv == null ? _cast.siglaProv != siglaProv : !siglaProv.equals( _cast.siglaProv )) {
			return false;
		}
		
		if (istatRegione == null ? _cast.istatRegione != istatRegione : !istatRegione.equals( _cast.istatRegione )) {
			return false;
		}
		
		if (descRegione == null ? _cast.descRegione != descRegione : !descRegione.equals( _cast.descRegione )) {
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
		_hashCode = 29 * _hashCode + (int) (idComune ^ (idComune >>> 32));
		if (rStatus != null) {
			_hashCode = 29 * _hashCode + rStatus.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (idComuneNext ^ (idComuneNext >>> 32));
		_hashCode = 29 * _hashCode + (idComuneNextNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) (idComunePrev ^ (idComunePrev >>> 32));
		_hashCode = 29 * _hashCode + (idComunePrevNull ? 1 : 0);
		if (dStart != null) {
			_hashCode = 29 * _hashCode + dStart.hashCode();
		}
		
		if (dStop != null) {
			_hashCode = 29 * _hashCode + dStop.hashCode();
		}
		
		if (istatComune != null) {
			_hashCode = 29 * _hashCode + istatComune.hashCode();
		}
		
		if (descComune != null) {
			_hashCode = 29 * _hashCode + descComune.hashCode();
		}
		
		if (cap != null) {
			_hashCode = 29 * _hashCode + cap.hashCode();
		}
		
		if (istatProvincia != null) {
			_hashCode = 29 * _hashCode + istatProvincia.hashCode();
		}
		
		if (descProvincia != null) {
			_hashCode = 29 * _hashCode + descProvincia.hashCode();
		}
		
		if (siglaProv != null) {
			_hashCode = 29 * _hashCode + siglaProv.hashCode();
		}
		
		if (istatRegione != null) {
			_hashCode = 29 * _hashCode + istatRegione.hashCode();
		}
		
		if (descRegione != null) {
			_hashCode = 29 * _hashCode + descRegione.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.PartAnComune: " );
		ret.append( "idComune=" + idComune );
		ret.append( ", rStatus=" + rStatus );
		ret.append( ", idComuneNext=" + idComuneNext );
		ret.append( ", idComunePrev=" + idComunePrev );
		ret.append( ", dStart=" + dStart );
		ret.append( ", dStop=" + dStop );
		ret.append( ", istatComune=" + istatComune );
		ret.append( ", descComune=" + descComune );
		ret.append( ", cap=" + cap );
		ret.append( ", istatProvincia=" + istatProvincia );
		ret.append( ", descProvincia=" + descProvincia );
		ret.append( ", siglaProv=" + siglaProv );
		ret.append( ", istatRegione=" + istatRegione );
		ret.append( ", descRegione=" + descRegione );
		return ret.toString();
	}

}
