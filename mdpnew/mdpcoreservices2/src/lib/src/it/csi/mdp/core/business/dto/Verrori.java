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

public class Verrori implements Serializable
{
	/** 
	 * This attribute maps to the column application_id in the verrori table.
	 */
	protected String applicationId;

	/** 
	 * This attribute maps to the column transaction_id in the verrori table.
	 */
	protected String transactionId;

	/** 
	 * This attribute maps to the column data in the verrori table.
	 */
	protected Date data;

	/** 
	 * This attribute maps to the column descrizione in the verrori table.
	 */
	protected String descrizione;

	/** 
	 * This attribute maps to the column atewaypaymodeid in the verrori table.
	 */
	protected String atewaypaymodeid;

	/** 
	 * This attribute maps to the column init_date in the verrori table.
	 */
	protected Date initDate;

	/** 
	 * This attribute maps to the column start_date in the verrori table.
	 */
	protected Date startDate;

	/** 
	 * This attribute maps to the column finish_date in the verrori table.
	 */
	protected Date finishDate;

	/** 
	 * This attribute maps to the column gateway_id in the verrori table.
	 */
	protected String gatewayId;

	/** 
	 * This attribute maps to the column gateway_description in the verrori table.
	 */
	protected String gatewayDescription;

	/** 
	 * This attribute maps to the column paymentmode_description in the verrori table.
	 */
	protected String paymentmodeDescription;

	/** 
	 * This attribute maps to the column cod_stato in the verrori table.
	 */
	protected long codStato;

	/** 
	 * This attribute represents whether the primitive attribute codStato is null.
	 */
	protected boolean codStatoNull = true;

	/** 
	 * This attribute maps to the column descrizionestatotrans in the verrori table.
	 */
	protected String descrizionestatotrans;

	/** 
	 * This attribute maps to the column amount in the verrori table.
	 */
	protected float amount;

	/** 
	 * This attribute represents whether the primitive attribute amount is null.
	 */
	protected boolean amountNull = true;

	/** 
	 * This attribute maps to the column payurl in the verrori table.
	 */
	protected String payurl;

	/** 
	 * This attribute maps to the column currencydescr in the verrori table.
	 */
	protected String currencydescr;

	/** 
	 * This attribute maps to the column pgresultcode in the verrori table.
	 */
	protected String pgresultcode;

	/** 
	 * This attribute maps to the column applicationname in the verrori table.
	 */
	protected String applicationname;

	/**
	 * Method 'Verrori'
	 * 
	 */
	public Verrori()
	{
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
	 * Method 'getAtewaypaymodeid'
	 * 
	 * @return String
	 */
	public String getAtewaypaymodeid()
	{
		return atewaypaymodeid;
	}

	/**
	 * Method 'setAtewaypaymodeid'
	 * 
	 * @param atewaypaymodeid
	 */
	public void setAtewaypaymodeid(String atewaypaymodeid)
	{
		this.atewaypaymodeid = atewaypaymodeid;
	}

	/**
	 * Method 'getInitDate'
	 * 
	 * @return Date
	 */
	public Date getInitDate()
	{
		return initDate;
	}

	/**
	 * Method 'setInitDate'
	 * 
	 * @param initDate
	 */
	public void setInitDate(Date initDate)
	{
		this.initDate = initDate;
	}

	/**
	 * Method 'getStartDate'
	 * 
	 * @return Date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Method 'setStartDate'
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Method 'getFinishDate'
	 * 
	 * @return Date
	 */
	public Date getFinishDate()
	{
		return finishDate;
	}

	/**
	 * Method 'setFinishDate'
	 * 
	 * @param finishDate
	 */
	public void setFinishDate(Date finishDate)
	{
		this.finishDate = finishDate;
	}

	/**
	 * Method 'getGatewayId'
	 * 
	 * @return String
	 */
	public String getGatewayId()
	{
		return gatewayId;
	}

	/**
	 * Method 'setGatewayId'
	 * 
	 * @param gatewayId
	 */
	public void setGatewayId(String gatewayId)
	{
		this.gatewayId = gatewayId;
	}

	/**
	 * Method 'getGatewayDescription'
	 * 
	 * @return String
	 */
	public String getGatewayDescription()
	{
		return gatewayDescription;
	}

	/**
	 * Method 'setGatewayDescription'
	 * 
	 * @param gatewayDescription
	 */
	public void setGatewayDescription(String gatewayDescription)
	{
		this.gatewayDescription = gatewayDescription;
	}

	/**
	 * Method 'getPaymentmodeDescription'
	 * 
	 * @return String
	 */
	public String getPaymentmodeDescription()
	{
		return paymentmodeDescription;
	}

	/**
	 * Method 'setPaymentmodeDescription'
	 * 
	 * @param paymentmodeDescription
	 */
	public void setPaymentmodeDescription(String paymentmodeDescription)
	{
		this.paymentmodeDescription = paymentmodeDescription;
	}

	/**
	 * Method 'getCodStato'
	 * 
	 * @return long
	 */
	public long getCodStato()
	{
		return codStato;
	}

	/**
	 * Method 'setCodStato'
	 * 
	 * @param codStato
	 */
	public void setCodStato(long codStato)
	{
		this.codStato = codStato;
		this.codStatoNull = false;
	}

	/**
	 * Method 'setCodStatoNull'
	 * 
	 * @param value
	 */
	public void setCodStatoNull(boolean value)
	{
		this.codStatoNull = value;
	}

	/**
	 * Method 'isCodStatoNull'
	 * 
	 * @return boolean
	 */
	public boolean isCodStatoNull()
	{
		return codStatoNull;
	}

	/**
	 * Method 'getDescrizionestatotrans'
	 * 
	 * @return String
	 */
	public String getDescrizionestatotrans()
	{
		return descrizionestatotrans;
	}

	/**
	 * Method 'setDescrizionestatotrans'
	 * 
	 * @param descrizionestatotrans
	 */
	public void setDescrizionestatotrans(String descrizionestatotrans)
	{
		this.descrizionestatotrans = descrizionestatotrans;
	}

	/**
	 * Method 'getAmount'
	 * 
	 * @return float
	 */
	public float getAmount()
	{
		return amount;
	}

	/**
	 * Method 'setAmount'
	 * 
	 * @param amount
	 */
	public void setAmount(float amount)
	{
		this.amount = amount;
		this.amountNull = false;
	}

	/**
	 * Method 'setAmountNull'
	 * 
	 * @param value
	 */
	public void setAmountNull(boolean value)
	{
		this.amountNull = value;
	}

	/**
	 * Method 'isAmountNull'
	 * 
	 * @return boolean
	 */
	public boolean isAmountNull()
	{
		return amountNull;
	}

	/**
	 * Method 'getPayurl'
	 * 
	 * @return String
	 */
	public String getPayurl()
	{
		return payurl;
	}

	/**
	 * Method 'setPayurl'
	 * 
	 * @param payurl
	 */
	public void setPayurl(String payurl)
	{
		this.payurl = payurl;
	}

	/**
	 * Method 'getCurrencydescr'
	 * 
	 * @return String
	 */
	public String getCurrencydescr()
	{
		return currencydescr;
	}

	/**
	 * Method 'setCurrencydescr'
	 * 
	 * @param currencydescr
	 */
	public void setCurrencydescr(String currencydescr)
	{
		this.currencydescr = currencydescr;
	}

	/**
	 * Method 'getPgresultcode'
	 * 
	 * @return String
	 */
	public String getPgresultcode()
	{
		return pgresultcode;
	}

	/**
	 * Method 'setPgresultcode'
	 * 
	 * @param pgresultcode
	 */
	public void setPgresultcode(String pgresultcode)
	{
		this.pgresultcode = pgresultcode;
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
		
		if (!(_other instanceof Verrori)) {
			return false;
		}
		
		final Verrori _cast = (Verrori) _other;
		if (applicationId == null ? _cast.applicationId != applicationId : !applicationId.equals( _cast.applicationId )) {
			return false;
		}
		
		if (transactionId == null ? _cast.transactionId != transactionId : !transactionId.equals( _cast.transactionId )) {
			return false;
		}
		
		if (data == null ? _cast.data != data : !data.equals( _cast.data )) {
			return false;
		}
		
		if (descrizione == null ? _cast.descrizione != descrizione : !descrizione.equals( _cast.descrizione )) {
			return false;
		}
		
		if (atewaypaymodeid == null ? _cast.atewaypaymodeid != atewaypaymodeid : !atewaypaymodeid.equals( _cast.atewaypaymodeid )) {
			return false;
		}
		
		if (initDate == null ? _cast.initDate != initDate : !initDate.equals( _cast.initDate )) {
			return false;
		}
		
		if (startDate == null ? _cast.startDate != startDate : !startDate.equals( _cast.startDate )) {
			return false;
		}
		
		if (finishDate == null ? _cast.finishDate != finishDate : !finishDate.equals( _cast.finishDate )) {
			return false;
		}
		
		if (gatewayId == null ? _cast.gatewayId != gatewayId : !gatewayId.equals( _cast.gatewayId )) {
			return false;
		}
		
		if (gatewayDescription == null ? _cast.gatewayDescription != gatewayDescription : !gatewayDescription.equals( _cast.gatewayDescription )) {
			return false;
		}
		
		if (paymentmodeDescription == null ? _cast.paymentmodeDescription != paymentmodeDescription : !paymentmodeDescription.equals( _cast.paymentmodeDescription )) {
			return false;
		}
		
		if (codStato != _cast.codStato) {
			return false;
		}
		
		if (codStatoNull != _cast.codStatoNull) {
			return false;
		}
		
		if (descrizionestatotrans == null ? _cast.descrizionestatotrans != descrizionestatotrans : !descrizionestatotrans.equals( _cast.descrizionestatotrans )) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (amountNull != _cast.amountNull) {
			return false;
		}
		
		if (payurl == null ? _cast.payurl != payurl : !payurl.equals( _cast.payurl )) {
			return false;
		}
		
		if (currencydescr == null ? _cast.currencydescr != currencydescr : !currencydescr.equals( _cast.currencydescr )) {
			return false;
		}
		
		if (pgresultcode == null ? _cast.pgresultcode != pgresultcode : !pgresultcode.equals( _cast.pgresultcode )) {
			return false;
		}
		
		if (applicationname == null ? _cast.applicationname != applicationname : !applicationname.equals( _cast.applicationname )) {
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
		if (applicationId != null) {
			_hashCode = 29 * _hashCode + applicationId.hashCode();
		}
		
		if (transactionId != null) {
			_hashCode = 29 * _hashCode + transactionId.hashCode();
		}
		
		if (data != null) {
			_hashCode = 29 * _hashCode + data.hashCode();
		}
		
		if (descrizione != null) {
			_hashCode = 29 * _hashCode + descrizione.hashCode();
		}
		
		if (atewaypaymodeid != null) {
			_hashCode = 29 * _hashCode + atewaypaymodeid.hashCode();
		}
		
		if (initDate != null) {
			_hashCode = 29 * _hashCode + initDate.hashCode();
		}
		
		if (startDate != null) {
			_hashCode = 29 * _hashCode + startDate.hashCode();
		}
		
		if (finishDate != null) {
			_hashCode = 29 * _hashCode + finishDate.hashCode();
		}
		
		if (gatewayId != null) {
			_hashCode = 29 * _hashCode + gatewayId.hashCode();
		}
		
		if (gatewayDescription != null) {
			_hashCode = 29 * _hashCode + gatewayDescription.hashCode();
		}
		
		if (paymentmodeDescription != null) {
			_hashCode = 29 * _hashCode + paymentmodeDescription.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (codStato ^ (codStato >>> 32));
		_hashCode = 29 * _hashCode + (codStatoNull ? 1 : 0);
		if (descrizionestatotrans != null) {
			_hashCode = 29 * _hashCode + descrizionestatotrans.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(amount);
		_hashCode = 29 * _hashCode + (amountNull ? 1 : 0);
		if (payurl != null) {
			_hashCode = 29 * _hashCode + payurl.hashCode();
		}
		
		if (currencydescr != null) {
			_hashCode = 29 * _hashCode + currencydescr.hashCode();
		}
		
		if (pgresultcode != null) {
			_hashCode = 29 * _hashCode + pgresultcode.hashCode();
		}
		
		if (applicationname != null) {
			_hashCode = 29 * _hashCode + applicationname.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.Verrori: " );
		ret.append( "applicationId=" + applicationId );
		ret.append( ", transactionId=" + transactionId );
		ret.append( ", data=" + data );
		ret.append( ", descrizione=" + descrizione );
		ret.append( ", atewaypaymodeid=" + atewaypaymodeid );
		ret.append( ", initDate=" + initDate );
		ret.append( ", startDate=" + startDate );
		ret.append( ", finishDate=" + finishDate );
		ret.append( ", gatewayId=" + gatewayId );
		ret.append( ", gatewayDescription=" + gatewayDescription );
		ret.append( ", paymentmodeDescription=" + paymentmodeDescription );
		ret.append( ", codStato=" + codStato );
		ret.append( ", descrizionestatotrans=" + descrizionestatotrans );
		ret.append( ", amount=" + amount );
		ret.append( ", payurl=" + payurl );
		ret.append( ", currencydescr=" + currencydescr );
		ret.append( ", pgresultcode=" + pgresultcode );
		ret.append( ", applicationname=" + applicationname );
		return ret.toString();
	}

}
