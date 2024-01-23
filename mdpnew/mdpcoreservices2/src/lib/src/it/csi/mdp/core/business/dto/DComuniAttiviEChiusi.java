/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.Date;

public class DComuniAttiviEChiusi implements Serializable
{
	/** 
	 * This attribute maps to the column ID_COMUNE in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected long idComune;

	/** 
	 * This attribute maps to the column COD_CATASTO in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String codCatasto;

	/** 
	 * This attribute maps to the column ISTAT_COMUNE in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String istatComune;

	/** 
	 * This attribute maps to the column DESC_COMUNE in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String descComune;

	/** 
	 * This attribute maps to the column CAP in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String cap;

	/** 
	 * This attribute maps to the column ALTITUDINE in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected long altitudine;

	/** 
	 * This attribute represents whether the primitive attribute altitudine is null.
	 */
	protected boolean altitudineNull = true;

	/** 
	 * This attribute maps to the column SUPERFICIE_HM2 in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected long superficieHm2;

	/** 
	 * This attribute represents whether the primitive attribute superficieHm2 is null.
	 */
	protected boolean superficieHm2Null = true;

	/** 
	 * This attribute maps to the column ISTAT_PROVINCIA in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String istatProvincia;

	/** 
	 * This attribute maps to the column ISTAT_ZONA_ALTIMETRICA in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String istatZonaAltimetrica;

	/** 
	 * This attribute maps to the column DESC_ZONA_ALTIMETRICA in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String descZonaAltimetrica;

	/** 
	 * This attribute maps to the column ISTAT_REGIONE in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String istatRegione;

	/** 
	 * This attribute maps to the column DATA_FINE_VALIDITA in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected Date dataFineValidita;

	/** 
	 * This attribute maps to the column ATTIVA in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected String attiva;

	/** 
	 * This attribute maps to the column DATA_UPD in the D_COMUNI_ATTIVI_E_CHIUSI table.
	 */
	protected Date dataUpd;

	/**
	 * Method 'DComuniAttiviEChiusi'
	 * 
	 */
	public DComuniAttiviEChiusi()
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
	 * Method 'getCodCatasto'
	 * 
	 * @return String
	 */
	public String getCodCatasto()
	{
		return codCatasto;
	}

	/**
	 * Method 'setCodCatasto'
	 * 
	 * @param codCatasto
	 */
	public void setCodCatasto(String codCatasto)
	{
		this.codCatasto = codCatasto;
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
	 * Method 'getAltitudine'
	 * 
	 * @return long
	 */
	public long getAltitudine()
	{
		return altitudine;
	}

	/**
	 * Method 'setAltitudine'
	 * 
	 * @param altitudine
	 */
	public void setAltitudine(long altitudine)
	{
		this.altitudine = altitudine;
		this.altitudineNull = false;
	}

	/**
	 * Method 'setAltitudineNull'
	 * 
	 * @param value
	 */
	public void setAltitudineNull(boolean value)
	{
		this.altitudineNull = value;
	}

	/**
	 * Method 'isAltitudineNull'
	 * 
	 * @return boolean
	 */
	public boolean isAltitudineNull()
	{
		return altitudineNull;
	}

	/**
	 * Method 'getSuperficieHm2'
	 * 
	 * @return long
	 */
	public long getSuperficieHm2()
	{
		return superficieHm2;
	}

	/**
	 * Method 'setSuperficieHm2'
	 * 
	 * @param superficieHm2
	 */
	public void setSuperficieHm2(long superficieHm2)
	{
		this.superficieHm2 = superficieHm2;
		this.superficieHm2Null = false;
	}

	/**
	 * Method 'setSuperficieHm2Null'
	 * 
	 * @param value
	 */
	public void setSuperficieHm2Null(boolean value)
	{
		this.superficieHm2Null = value;
	}

	/**
	 * Method 'isSuperficieHm2Null'
	 * 
	 * @return boolean
	 */
	public boolean isSuperficieHm2Null()
	{
		return superficieHm2Null;
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
	 * Method 'getIstatZonaAltimetrica'
	 * 
	 * @return String
	 */
	public String getIstatZonaAltimetrica()
	{
		return istatZonaAltimetrica;
	}

	/**
	 * Method 'setIstatZonaAltimetrica'
	 * 
	 * @param istatZonaAltimetrica
	 */
	public void setIstatZonaAltimetrica(String istatZonaAltimetrica)
	{
		this.istatZonaAltimetrica = istatZonaAltimetrica;
	}

	/**
	 * Method 'getDescZonaAltimetrica'
	 * 
	 * @return String
	 */
	public String getDescZonaAltimetrica()
	{
		return descZonaAltimetrica;
	}

	/**
	 * Method 'setDescZonaAltimetrica'
	 * 
	 * @param descZonaAltimetrica
	 */
	public void setDescZonaAltimetrica(String descZonaAltimetrica)
	{
		this.descZonaAltimetrica = descZonaAltimetrica;
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
	 * Method 'getDataFineValidita'
	 * 
	 * @return Date
	 */
	public Date getDataFineValidita()
	{
		return dataFineValidita;
	}

	/**
	 * Method 'setDataFineValidita'
	 * 
	 * @param dataFineValidita
	 */
	public void setDataFineValidita(Date dataFineValidita)
	{
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * Method 'getAttiva'
	 * 
	 * @return String
	 */
	public String getAttiva()
	{
		return attiva;
	}

	/**
	 * Method 'setAttiva'
	 * 
	 * @param attiva
	 */
	public void setAttiva(String attiva)
	{
		this.attiva = attiva;
	}

	/**
	 * Method 'getDataUpd'
	 * 
	 * @return Date
	 */
	public Date getDataUpd()
	{
		return dataUpd;
	}

	/**
	 * Method 'setDataUpd'
	 * 
	 * @param dataUpd
	 */
	public void setDataUpd(Date dataUpd)
	{
		this.dataUpd = dataUpd;
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
		
		if (!(_other instanceof DComuniAttiviEChiusi)) {
			return false;
		}
		
		final DComuniAttiviEChiusi _cast = (DComuniAttiviEChiusi) _other;
		if (idComune != _cast.idComune) {
			return false;
		}
		
		if (codCatasto == null ? _cast.codCatasto != codCatasto : !codCatasto.equals( _cast.codCatasto )) {
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
		
		if (altitudine != _cast.altitudine) {
			return false;
		}
		
		if (altitudineNull != _cast.altitudineNull) {
			return false;
		}
		
		if (superficieHm2 != _cast.superficieHm2) {
			return false;
		}
		
		if (superficieHm2Null != _cast.superficieHm2Null) {
			return false;
		}
		
		if (istatProvincia == null ? _cast.istatProvincia != istatProvincia : !istatProvincia.equals( _cast.istatProvincia )) {
			return false;
		}
		
		if (istatZonaAltimetrica == null ? _cast.istatZonaAltimetrica != istatZonaAltimetrica : !istatZonaAltimetrica.equals( _cast.istatZonaAltimetrica )) {
			return false;
		}
		
		if (descZonaAltimetrica == null ? _cast.descZonaAltimetrica != descZonaAltimetrica : !descZonaAltimetrica.equals( _cast.descZonaAltimetrica )) {
			return false;
		}
		
		if (istatRegione == null ? _cast.istatRegione != istatRegione : !istatRegione.equals( _cast.istatRegione )) {
			return false;
		}
		
		if (dataFineValidita == null ? _cast.dataFineValidita != dataFineValidita : !dataFineValidita.equals( _cast.dataFineValidita )) {
			return false;
		}
		
		if (attiva == null ? _cast.attiva != attiva : !attiva.equals( _cast.attiva )) {
			return false;
		}
		
		if (dataUpd == null ? _cast.dataUpd != dataUpd : !dataUpd.equals( _cast.dataUpd )) {
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
		if (codCatasto != null) {
			_hashCode = 29 * _hashCode + codCatasto.hashCode();
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
		
		_hashCode = 29 * _hashCode + (int) (altitudine ^ (altitudine >>> 32));
		_hashCode = 29 * _hashCode + (altitudineNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) (superficieHm2 ^ (superficieHm2 >>> 32));
		_hashCode = 29 * _hashCode + (superficieHm2Null ? 1 : 0);
		if (istatProvincia != null) {
			_hashCode = 29 * _hashCode + istatProvincia.hashCode();
		}
		
		if (istatZonaAltimetrica != null) {
			_hashCode = 29 * _hashCode + istatZonaAltimetrica.hashCode();
		}
		
		if (descZonaAltimetrica != null) {
			_hashCode = 29 * _hashCode + descZonaAltimetrica.hashCode();
		}
		
		if (istatRegione != null) {
			_hashCode = 29 * _hashCode + istatRegione.hashCode();
		}
		
		if (dataFineValidita != null) {
			_hashCode = 29 * _hashCode + dataFineValidita.hashCode();
		}
		
		if (attiva != null) {
			_hashCode = 29 * _hashCode + attiva.hashCode();
		}
		
		if (dataUpd != null) {
			_hashCode = 29 * _hashCode + dataUpd.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.DComuniAttiviEChiusi: " );
		ret.append( "idComune=" + idComune );
		ret.append( ", codCatasto=" + codCatasto );
		ret.append( ", istatComune=" + istatComune );
		ret.append( ", descComune=" + descComune );
		ret.append( ", cap=" + cap );
		ret.append( ", altitudine=" + altitudine );
		ret.append( ", superficieHm2=" + superficieHm2 );
		ret.append( ", istatProvincia=" + istatProvincia );
		ret.append( ", istatZonaAltimetrica=" + istatZonaAltimetrica );
		ret.append( ", descZonaAltimetrica=" + descZonaAltimetrica );
		ret.append( ", istatRegione=" + istatRegione );
		ret.append( ", dataFineValidita=" + dataFineValidita );
		ret.append( ", attiva=" + attiva );
		ret.append( ", dataUpd=" + dataUpd );
		return ret.toString();
	}

}
