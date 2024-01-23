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

public class CsiLogAudit implements Serializable
{
	/** 
	 * This attribute maps to the column data_ora in the csi_log_audit table.
	 */
	protected Date dataOra;

	/** 
	 * This attribute maps to the column descrizione in the csi_log_audit table.
	 */
	protected String descrizione;

	/** 
	 * This attribute maps to the column transactionid in the csi_log_audit table.
	 */
	protected String transactionid;

	/** 
	 * This attribute maps to the column applicationid in the csi_log_audit table.
	 */
	protected String applicationid;

	/** 
	 * This attribute maps to the column utente in the csi_log_audit table.
	 */
	protected int utente;

	/** 
	 * This attribute maps to the column gatewayid in the csi_log_audit table.
	 */
	protected String gatewayid;

	/** 
	 * This attribute maps to the column codappmodify in the csi_log_audit table.
	 */
	protected String codappmodify;

	/** 
	 * This attribute maps to the column idaction in the csi_log_audit table.
	 */
	protected String idaction;

	/** 
	 * This attribute maps to the column id_app in the csi_log_audit table.
	 */
	protected String idApp;

	/** 
	 * This attribute maps to the column operazione in the csi_log_audit table.
	 */
	protected String operazione;

	/** 
	 * This attribute maps to the column ogg_oper in the csi_log_audit table.
	 */
	protected String oggOper;

	/** 
	 * This attribute maps to the column key_oper in the csi_log_audit table.
	 */
	protected String keyOper;

	/** 
	 * This attribute maps to the column ip_address in the csi_log_audit table.
	 */
	protected String ipAddress;

	/** 
	 * This attribute maps to the column uniqueid in the csi_log_audit table.
	 */
	protected int uniqueid;

	/** 
	 * This attribute maps to the column codfisc in the csi_log_audit table.
	 */
	protected String codfisc;

	/**
	 * Method 'CsiLogAudit'
	 * 
	 */
	public CsiLogAudit()
	{
	}

	/**
	 * Method 'getDataOra'
	 * 
	 * @return Date
	 */
	public Date getDataOra()
	{
		return dataOra;
	}

	/**
	 * Method 'setDataOra'
	 * 
	 * @param dataOra
	 */
	public void setDataOra(Date dataOra)
	{
		this.dataOra = dataOra;
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
	 * Method 'getTransactionid'
	 * 
	 * @return String
	 */
	public String getTransactionid()
	{
		return transactionid;
	}

	/**
	 * Method 'setTransactionid'
	 * 
	 * @param transactionid
	 */
	public void setTransactionid(String transactionid)
	{
		this.transactionid = transactionid;
	}

	/**
	 * Method 'getApplicationid'
	 * 
	 * @return String
	 */
	public String getApplicationid()
	{
		return applicationid;
	}

	/**
	 * Method 'setApplicationid'
	 * 
	 * @param applicationid
	 */
	public void setApplicationid(String applicationid)
	{
		this.applicationid = applicationid;
	}

	/**
	 * Method 'getUtente'
	 * 
	 * @return int
	 */
	public int getUtente()
	{
		return utente;
	}

	/**
	 * Method 'setUtente'
	 * 
	 * @param utente
	 */
	public void setUtente(int utente)
	{
		this.utente = utente;
	}

	/**
	 * Method 'getGatewayid'
	 * 
	 * @return String
	 */
	public String getGatewayid()
	{
		return gatewayid;
	}

	/**
	 * Method 'setGatewayid'
	 * 
	 * @param gatewayid
	 */
	public void setGatewayid(String gatewayid)
	{
		this.gatewayid = gatewayid;
	}

	/**
	 * Method 'getCodappmodify'
	 * 
	 * @return String
	 */
	public String getCodappmodify()
	{
		return codappmodify;
	}

	/**
	 * Method 'setCodappmodify'
	 * 
	 * @param codappmodify
	 */
	public void setCodappmodify(String codappmodify)
	{
		this.codappmodify = codappmodify;
	}

	/**
	 * Method 'getIdaction'
	 * 
	 * @return String
	 */
	public String getIdaction()
	{
		return idaction;
	}

	/**
	 * Method 'setIdaction'
	 * 
	 * @param idaction
	 */
	public void setIdaction(String idaction)
	{
		this.idaction = idaction;
	}

	/**
	 * Method 'getIdApp'
	 * 
	 * @return String
	 */
	public String getIdApp()
	{
		return idApp;
	}

	/**
	 * Method 'setIdApp'
	 * 
	 * @param idApp
	 */
	public void setIdApp(String idApp)
	{
		this.idApp = idApp;
	}

	/**
	 * Method 'getOperazione'
	 * 
	 * @return String
	 */
	public String getOperazione()
	{
		return operazione;
	}

	/**
	 * Method 'setOperazione'
	 * 
	 * @param operazione
	 */
	public void setOperazione(String operazione)
	{
		this.operazione = operazione;
	}

	/**
	 * Method 'getOggOper'
	 * 
	 * @return String
	 */
	public String getOggOper()
	{
		return oggOper;
	}

	/**
	 * Method 'setOggOper'
	 * 
	 * @param oggOper
	 */
	public void setOggOper(String oggOper)
	{
		this.oggOper = oggOper;
	}

	/**
	 * Method 'getKeyOper'
	 * 
	 * @return String
	 */
	public String getKeyOper()
	{
		return keyOper;
	}

	/**
	 * Method 'setKeyOper'
	 * 
	 * @param keyOper
	 */
	public void setKeyOper(String keyOper)
	{
		this.keyOper = keyOper;
	}

	/**
	 * Method 'getIpAddress'
	 * 
	 * @return String
	 */
	public String getIpAddress()
	{
		return ipAddress;
	}

	/**
	 * Method 'setIpAddress'
	 * 
	 * @param ipAddress
	 */
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	/**
	 * Method 'getUniqueid'
	 * 
	 * @return int
	 */
	public int getUniqueid()
	{
		return uniqueid;
	}

	/**
	 * Method 'setUniqueid'
	 * 
	 * @param uniqueid
	 */
	public void setUniqueid(int uniqueid)
	{
		this.uniqueid = uniqueid;
	}

	/**
	 * Method 'getCodfisc'
	 * 
	 * @return String
	 */
	public String getCodfisc()
	{
		return codfisc;
	}

	/**
	 * Method 'setCodfisc'
	 * 
	 * @param codfisc
	 */
	public void setCodfisc(String codfisc)
	{
		this.codfisc = codfisc;
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
		
		if (!(_other instanceof CsiLogAudit)) {
			return false;
		}
		
		final CsiLogAudit _cast = (CsiLogAudit) _other;
		if (dataOra == null ? _cast.dataOra != dataOra : !dataOra.equals( _cast.dataOra )) {
			return false;
		}
		
		if (descrizione == null ? _cast.descrizione != descrizione : !descrizione.equals( _cast.descrizione )) {
			return false;
		}
		
		if (transactionid == null ? _cast.transactionid != transactionid : !transactionid.equals( _cast.transactionid )) {
			return false;
		}
		
		if (applicationid == null ? _cast.applicationid != applicationid : !applicationid.equals( _cast.applicationid )) {
			return false;
		}
		
		if (utente != _cast.utente) {
			return false;
		}
		
		if (gatewayid == null ? _cast.gatewayid != gatewayid : !gatewayid.equals( _cast.gatewayid )) {
			return false;
		}
		
		if (codappmodify == null ? _cast.codappmodify != codappmodify : !codappmodify.equals( _cast.codappmodify )) {
			return false;
		}
		
		if (idaction == null ? _cast.idaction != idaction : !idaction.equals( _cast.idaction )) {
			return false;
		}
		
		if (idApp == null ? _cast.idApp != idApp : !idApp.equals( _cast.idApp )) {
			return false;
		}
		
		if (operazione == null ? _cast.operazione != operazione : !operazione.equals( _cast.operazione )) {
			return false;
		}
		
		if (oggOper == null ? _cast.oggOper != oggOper : !oggOper.equals( _cast.oggOper )) {
			return false;
		}
		
		if (keyOper == null ? _cast.keyOper != keyOper : !keyOper.equals( _cast.keyOper )) {
			return false;
		}
		
		if (ipAddress == null ? _cast.ipAddress != ipAddress : !ipAddress.equals( _cast.ipAddress )) {
			return false;
		}
		
		if (uniqueid != _cast.uniqueid) {
			return false;
		}
		
		if (codfisc == null ? _cast.codfisc != codfisc : !codfisc.equals( _cast.codfisc )) {
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
		if (dataOra != null) {
			_hashCode = 29 * _hashCode + dataOra.hashCode();
		}
		
		if (descrizione != null) {
			_hashCode = 29 * _hashCode + descrizione.hashCode();
		}
		
		if (transactionid != null) {
			_hashCode = 29 * _hashCode + transactionid.hashCode();
		}
		
		if (applicationid != null) {
			_hashCode = 29 * _hashCode + applicationid.hashCode();
		}
		
		_hashCode = 29 * _hashCode + utente;
		if (gatewayid != null) {
			_hashCode = 29 * _hashCode + gatewayid.hashCode();
		}
		
		if (codappmodify != null) {
			_hashCode = 29 * _hashCode + codappmodify.hashCode();
		}
		
		if (idaction != null) {
			_hashCode = 29 * _hashCode + idaction.hashCode();
		}
		
		if (idApp != null) {
			_hashCode = 29 * _hashCode + idApp.hashCode();
		}
		
		if (operazione != null) {
			_hashCode = 29 * _hashCode + operazione.hashCode();
		}
		
		if (oggOper != null) {
			_hashCode = 29 * _hashCode + oggOper.hashCode();
		}
		
		if (keyOper != null) {
			_hashCode = 29 * _hashCode + keyOper.hashCode();
		}
		
		if (ipAddress != null) {
			_hashCode = 29 * _hashCode + ipAddress.hashCode();
		}
		
		_hashCode = 29 * _hashCode + uniqueid;
		if (codfisc != null) {
			_hashCode = 29 * _hashCode + codfisc.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return CsiLogAuditPk
	 */
	public CsiLogAuditPk createPk()
	{
		return new CsiLogAuditPk(uniqueid);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.CsiLogAudit: " );
		ret.append( "dataOra=" + dataOra );
		ret.append( ", descrizione=" + descrizione );
		ret.append( ", transactionid=" + transactionid );
		ret.append( ", applicationid=" + applicationid );
		ret.append( ", utente=" + utente );
		ret.append( ", gatewayid=" + gatewayid );
		ret.append( ", codappmodify=" + codappmodify );
		ret.append( ", idaction=" + idaction );
		ret.append( ", idApp=" + idApp );
		ret.append( ", operazione=" + operazione );
		ret.append( ", oggOper=" + oggOper );
		ret.append( ", keyOper=" + keyOper );
		ret.append( ", ipAddress=" + ipAddress );
		ret.append( ", uniqueid=" + uniqueid );
		ret.append( ", codfisc=" + codfisc );
		return ret.toString();
	}

}
