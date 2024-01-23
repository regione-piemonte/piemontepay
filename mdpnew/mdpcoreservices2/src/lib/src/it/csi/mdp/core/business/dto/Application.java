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

public class Application implements Serializable
{
	/** 
	 * This attribute maps to the column id in the application table.
	 */
	protected String id;

	/** 
	 * This attribute maps to the column applicationname in the application table.
	 */
	protected String applicationname;

	/** 
	 * This attribute maps to the column referentecsi in the application table.
	 */
	protected String referentecsi;

	/** 
	 * This attribute maps to the column cliente in the application table.
	 */
	protected String cliente;

	/** 
	 * This attribute maps to the column progetto in the application table.
	 */
	protected String progetto;

	/** 
	 * This attribute maps to the column note in the application table.
	 */
	protected String note;

	/** 
	 * This attribute maps to the column esercemail in the application table.
	 */
	protected String esercemail;

	/** 
	 * This attribute maps to the column numeroverde in the application table.
	 */
	protected String numeroverde;

	/** 
	 * This attribute maps to the column valido_dal in the application table.
	 */
	protected Date validoDal;

	/** 
	 * This attribute maps to the column valido_al in the application table.
	 */
	protected Date validoAl;

	/** 
	 * This attribute maps to the column redirect_newmdp in the application table.
	 */
	protected long redirectNewmdp;



	/**
	 * Method 'Application'
	 * 
	 */
	public Application()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return String
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Method 'getApplicationname'
	 * 
	 * @return String
	 */
	public String getApplicationname()
	{
		return applicationname;
	}

	/**
	 * Method 'setApplicationname'
	 * 
	 * @param applicationname
	 */
	public void setApplicationname(String applicationname)
	{
		this.applicationname = applicationname;
	}

	/**
	 * Method 'getReferentecsi'
	 * 
	 * @return String
	 */
	public String getReferentecsi()
	{
		return referentecsi;
	}

	/**
	 * Method 'setReferentecsi'
	 * 
	 * @param referentecsi
	 */
	public void setReferentecsi(String referentecsi)
	{
		this.referentecsi = referentecsi;
	}

	/**
	 * Method 'getCliente'
	 * 
	 * @return String
	 */
	public String getCliente()
	{
		return cliente;
	}

	/**
	 * Method 'setCliente'
	 * 
	 * @param cliente
	 */
	public void setCliente(String cliente)
	{
		this.cliente = cliente;
	}

	/**
	 * Method 'getProgetto'
	 * 
	 * @return String
	 */
	public String getProgetto()
	{
		return progetto;
	}

	/**
	 * Method 'setProgetto'
	 * 
	 * @param progetto
	 */
	public void setProgetto(String progetto)
	{
		this.progetto = progetto;
	}

	/**
	 * Method 'getNote'
	 * 
	 * @return String
	 */
	public String getNote()
	{
		return note;
	}

	/**
	 * Method 'setNote'
	 * 
	 * @param note
	 */
	public void setNote(String note)
	{
		this.note = note;
	}

	/**
	 * Method 'getEsercemail'
	 * 
	 * @return String
	 */
	public String getEsercemail()
	{
		return esercemail;
	}

	/**
	 * Method 'setEsercemail'
	 * 
	 * @param esercemail
	 */
	public void setEsercemail(String esercemail)
	{
		this.esercemail = esercemail;
	}

	/**
	 * Method 'getNumeroverde'
	 * 
	 * @return String
	 */
	public String getNumeroverde()
	{
		return numeroverde;
	}

	/**
	 * Method 'setNumeroverde'
	 * 
	 * @param numeroverde
	 */
	public void setNumeroverde(String numeroverde)
	{
		this.numeroverde = numeroverde;
	}

	/**
	 * Method 'getValidoDal'
	 * 
	 * @return Date
	 */
	public Date getValidoDal()
	{
		return validoDal;
	}

	/**
	 * Method 'setValidoDal'
	 * 
	 * @param validoDal
	 */
	public void setValidoDal(Date validoDal)
	{
		this.validoDal = validoDal;
	}

	/**
	 * Method 'getValidoAl'
	 * 
	 * @return Date
	 */
	public Date getValidoAl()
	{
		return validoAl;
	}

	/**
	 * Method 'setValidoAl'
	 * 
	 * @param validoAl
	 */
	public void setValidoAl(Date validoAl)
	{
		this.validoAl = validoAl;
	}

	/**
	 * Method 'getRedirectNewmdp'
	 * 
	 * @return long
	 */
	public long getRedirectNewmdp()
	{
		return redirectNewmdp;
	}

	/**
	 * Method 'setRedirectNewmdp'
	 * 
	 * @param redirectNewmdp
	 */
	public void setRedirectNewmdp(long redirectNewmdp)
	{
		this.redirectNewmdp = redirectNewmdp;
		
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
		
		if (!(_other instanceof Application)) {
			return false;
		}
		
		final Application _cast = (Application) _other;
		if (id == null ? _cast.id != id : !id.equals( _cast.id )) {
			return false;
		}
		
		if (applicationname == null ? _cast.applicationname != applicationname : !applicationname.equals( _cast.applicationname )) {
			return false;
		}
		
		if (referentecsi == null ? _cast.referentecsi != referentecsi : !referentecsi.equals( _cast.referentecsi )) {
			return false;
		}
		
		if (cliente == null ? _cast.cliente != cliente : !cliente.equals( _cast.cliente )) {
			return false;
		}
		
		if (progetto == null ? _cast.progetto != progetto : !progetto.equals( _cast.progetto )) {
			return false;
		}
		
		if (note == null ? _cast.note != note : !note.equals( _cast.note )) {
			return false;
		}
		
		if (esercemail == null ? _cast.esercemail != esercemail : !esercemail.equals( _cast.esercemail )) {
			return false;
		}
		
		if (numeroverde == null ? _cast.numeroverde != numeroverde : !numeroverde.equals( _cast.numeroverde )) {
			return false;
		}
		
		if (validoDal == null ? _cast.validoDal != validoDal : !validoDal.equals( _cast.validoDal )) {
			return false;
		}
		
		if (validoAl == null ? _cast.validoAl != validoAl : !validoAl.equals( _cast.validoAl )) {
			return false;
		}
		
		if (redirectNewmdp != _cast.redirectNewmdp) {
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
		if (id != null) {
			_hashCode = 29 * _hashCode + id.hashCode();
		}
		
		if (applicationname != null) {
			_hashCode = 29 * _hashCode + applicationname.hashCode();
		}
		
		if (referentecsi != null) {
			_hashCode = 29 * _hashCode + referentecsi.hashCode();
		}
		
		if (cliente != null) {
			_hashCode = 29 * _hashCode + cliente.hashCode();
		}
		
		if (progetto != null) {
			_hashCode = 29 * _hashCode + progetto.hashCode();
		}
		
		if (note != null) {
			_hashCode = 29 * _hashCode + note.hashCode();
		}
		
		if (esercemail != null) {
			_hashCode = 29 * _hashCode + esercemail.hashCode();
		}
		
		if (numeroverde != null) {
			_hashCode = 29 * _hashCode + numeroverde.hashCode();
		}
		
		if (validoDal != null) {
			_hashCode = 29 * _hashCode + validoDal.hashCode();
		}
		
		if (validoAl != null) {
			_hashCode = 29 * _hashCode + validoAl.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (redirectNewmdp ^ (redirectNewmdp >>> 32));
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return ApplicationPk
	 */
	public ApplicationPk createPk()
	{
		return new ApplicationPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Application: " );
		ret.append( "id=" + id );
		ret.append( ", applicationname=" + applicationname );
		ret.append( ", referentecsi=" + referentecsi );
		ret.append( ", cliente=" + cliente );
		ret.append( ", progetto=" + progetto );
		ret.append( ", note=" + note );
		ret.append( ", esercemail=" + esercemail );
		ret.append( ", numeroverde=" + numeroverde );
		ret.append( ", validoDal=" + validoDal );
		ret.append( ", validoAl=" + validoAl );
		ret.append( ", redirectNewmdp=" + redirectNewmdp );
		return ret.toString();
	}

}
