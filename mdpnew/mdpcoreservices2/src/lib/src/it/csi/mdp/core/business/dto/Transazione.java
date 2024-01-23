/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


//@XmlRootElement(name = "Transazione")
@XmlType(name = "Transazione", propOrder = {})

public class Transazione implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5510921861058375774L;

	/** 
	 * This attribute maps to the column TRANSACTION_ID in the TRANSAZIONE table.
	 */
	protected String transactionId;

	/** 
	 * This attribute maps to the column APPLICATION_ID in the TRANSAZIONE table.
	 */
	protected String applicationId;

	/** 
	 * This attribute maps to the column LANGUAGE in the TRANSAZIONE table.
	 */
	protected String language;

	/** 
	 * This attribute maps to the column CCY in the TRANSAZIONE table.
	 */
	protected String ccy;

	/** 
	 * This attribute maps to the column BUYER_EMAIL in the TRANSAZIONE table.
	 */
	protected String buyerEmail;

	/** 
	 * This attribute maps to the column BASKET_ID in the TRANSAZIONE table.
	 */
	
	protected String basketId;

	/** 
	 * This attribute maps to the column COD_STATO in the TRANSAZIONE table.
	 */
	
	private long codStato;

	/** 
	 * This attribute maps to the column COMMISSIONI_APPLICATE in the TRANSAZIONE table.
	 */
	
	private double commissioniApplicate;

	/** 
	 * This attribute represents whether the primitive attribute commissioniApplicate is null.
	 */
	protected boolean commissioniApplicateNull = true;

	/** 
	 * This attribute maps to the column INIT_DATE in the TRANSAZIONE table.
	 */
	protected Date initDate;

	/** 
	 * This attribute maps to the column START_DATE in the TRANSAZIONE table.
	 */
	protected Date startDate;

	/** 
	 * This attribute maps to the column FINISH_DATE in the TRANSAZIONE table.
	 */
	protected Date finishDate;

	/** 
	 * This attribute maps to the column GATEWAY_ID in the TRANSAZIONE table.
	 */
	protected String gatewayId;

	/** 
	 * This attribute maps to the column GATEWAYPAYMODEID in the TRANSAZIONE table.
	 */
	protected String gatewaypaymodeid;

	/** 
	 * This attribute maps to the column AMOUNT in the TRANSAZIONE table.
	 */
	protected double amount;

	/** 
	 * This attribute maps to the column MSCSORDERID in the TRANSAZIONE table.
	 */
	protected long mscsorderid;

	
	/** 
	 * This attribute represents whether the primitive attribute mscsorderid is null.
	 */
	protected boolean mscsorderidNull = true;

	/** 
	 * This attribute maps to the column MERCHANT_ID in the TRANSAZIONE table.
	 */
	protected String merchantId;

	/** 
	 * This attribute maps to the column PGRESULTCODE in the TRANSAZIONE table.
	 */
	protected String pgresultcode;

	/** 
	 * This attribute maps to the column PROVIDERTIMESTAMP in the TRANSAZIONE table.
	 */
	protected String providertimestamp;

	/** 
	 * This attribute maps to the column AUTHORNUMBER in the TRANSAZIONE table.
	 */
	protected String authornumber;

	/** 
	 * This attribute maps to the column OPERNUMBER in the TRANSAZIONE table.
	 */
	protected String opernumber;

	/** 
	 * This attribute maps to the column RISPCOMP in the TRANSAZIONE table.
	 */
	protected String rispcomp;

	/** 
	 * This attribute maps to the column ERRCODE in the TRANSAZIONE table.
	 */
	protected String errcode;

	/** 
	 * This attribute maps to the column BUYERNAME in the TRANSAZIONE table.
	 */
	protected String buyername;

	/** 
	 * This attribute maps to the column BUYERCODFISC in the TRANSAZIONE table.
	 */
	protected String buyercodfisc;

	/** 
	 * This attribute maps to the column OLDSTATE in the TRANSAZIONE table.
	 */
	protected long oldstate;

	/** 
	 * This attribute maps to the column CHANGESTATEDATE in the TRANSAZIONE table.
	 */
	protected Date changestatedate;

	/** 
	 * This attribute maps to the column USERHASCHANGE in the TRANSAZIONE table.
	 */
	protected String userhaschange;

	/** 
	 * This attribute maps to the column CLIENTIPADDRESS in the TRANSAZIONE table.
	 */
	protected String clientipaddress;

	/** 
	 * This attribute maps to the column INCASSOKOERRORMESSAGE in the TRANSAZIONE table.
	 */
	protected String incassokoerrormessage;

	/** 
	 * This attribute maps to the column INTESTATARIOCC in the TRANSAZIONE table.
	 */
	protected String intestatariocc;

	/** 
	 * This attribute maps to the column PAYMENTID in the TRANSAZIONE table.
	 */
	protected String paymentid;

	/**
	 * Method 'Transazione'
	 * 
	 */
	
	/** 
	 * This attribute maps to the column PAYURL in the TRANSAZIONE table.
	 */
	protected String payurl;

	/**
	 * Method 'Transazione'
	 * 
	 */
	public Transazione()
	{
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
	 * Method 'getLanguage'
	 * 
	 * @return String
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Method 'setLanguage'
	 * 
	 * @param language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Method 'getCcy'
	 * 
	 * @return String
	 */
	public String getCcy()
	{
		return ccy;
	}

	/**
	 * Method 'setCcy'
	 * 
	 * @param ccy
	 */
	public void setCcy(String ccy)
	{
		this.ccy = ccy;
	}

	/**
	 * Method 'getBuyerEmail'
	 * 
	 * @return String
	 */
	public String getBuyerEmail()
	{
		return buyerEmail;
	}

	/**
	 * Method 'setBuyerEmail'
	 * 
	 * @param buyerEmail
	 */
	public void setBuyerEmail(String buyerEmail)
	{
		this.buyerEmail = buyerEmail;
	}

	/**
	 * Method 'getBasketId'
	 * 
	 * @return String
	 */
	public String getBasketId()
	{
		return basketId;
	}

	/**
	 * Method 'setBasketId'
	 * 
	 * @param basketId
	 */
	public void setBasketId(String basketId)
	{
		this.basketId = basketId;
	}

	/**
	 * Method 'getCodStato'
	 * 
	 * @return long
	 */
	@XmlElement(name = "codStato",nillable = true, required = false)
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
	}

	/**
	 * Method 'getCommissioniApplicate'
	 * 
	 * @return long
	 */
	@XmlElement(name = "commissioniApplicate",nillable = true, required = false)
	public double getCommissioniApplicate()
	{
		return commissioniApplicate;
	}

	/**
	 * Method 'setCommissioniApplicate'
	 * 
	 * @param commissioniApplicate
	 */
	public void setCommissioniApplicate(double commissioniApplicate)
	{
		this.commissioniApplicate = commissioniApplicate;
		this.commissioniApplicateNull = false;
	}

	/**
	 * Method 'setCommissioniApplicateNull'
	 * 
	 * @param value
	 */
	public void setCommissioniApplicateNull(boolean value)
	{
		this.commissioniApplicateNull = value;
	}

	/**
	 * Method 'isCommissioniApplicateNull'
	 * 
	 * @return boolean
	 */
	public boolean isCommissioniApplicateNull()
	{
		return commissioniApplicateNull;
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
	 * @return long
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
	 * Method 'getGatewaypaymodeid'
	 * 
	 * @return long
	 */
	public String getGatewaypaymodeid()
	{
		return gatewaypaymodeid;
	}

	/**
	 * Method 'setGatewaypaymodeid'
	 * 
	 * @param gatewaypaymodeid
	 */
	public void setGatewaypaymodeid(String gatewaypaymodeid)
	{
		this.gatewaypaymodeid = gatewaypaymodeid;
	}

	/**
	 * Method 'getAmount'
	 * 
	 * @return long
	 */
	@XmlElement(name = "amount",nillable = true, required = false)
	public double getAmount()
	{
		return amount;
	}

	/**
	 * Method 'setAmount'
	 * 
	 * @param amount
	 */
	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	/**
	 * Method 'getMscsorderid'
	 * 
	 * @return long
	 */
	@XmlElement(name = "mscsorderid",nillable = true, required = false)
	public long getMscsorderid()
	{
		return mscsorderid;
	}

	/**
	 * Method 'setMscsorderid'
	 * 
	 * @param mscsorderid
	 */
	public void setMscsorderid(long mscsorderid)
	{
		this.mscsorderid = mscsorderid;
		this.mscsorderidNull = false;
	}

	/**
	 * Method 'setMscsorderidNull'
	 * 
	 * @param value
	 */
	public void setMscsorderidNull(boolean value)
	{
		this.mscsorderidNull = value;
	}

	/**
	 * Method 'isMscsorderidNull'
	 * 
	 * @return boolean
	 */
	public boolean isMscsorderidNull()
	{
		return mscsorderidNull;
	}

	/**
	 * Method 'getMerchantId'
	 * 
	 * @return String
	 */
	public String getMerchantId()
	{
		return merchantId;
	}

	/**
	 * Method 'setMerchantId'
	 * 
	 * @param merchantId
	 */
	public void setMerchantId(String merchantId)
	{
		this.merchantId = merchantId;
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
	 * Method 'getProvidertimestamp'
	 * 
	 * @return String
	 */
	public String getProvidertimestamp()
	{
		return providertimestamp;
	}

	/**
	 * Method 'setProvidertimestamp'
	 * 
	 * @param providertimestamp
	 */
	public void setProvidertimestamp(String providertimestamp)
	{
		this.providertimestamp = providertimestamp;
	}

	/**
	 * Method 'getAuthornumber'
	 * 
	 * @return String
	 */
	public String getAuthornumber()
	{
		return authornumber;
	}

	/**
	 * Method 'setAuthornumber'
	 * 
	 * @param authornumber
	 */
	public void setAuthornumber(String authornumber)
	{
		this.authornumber = authornumber;
	}

	/**
	 * Method 'getOpernumber'
	 * 
	 * @return String
	 */
	public String getOpernumber()
	{
		return opernumber;
	}

	/**
	 * Method 'setOpernumber'
	 * 
	 * @param opernumber
	 */
	public void setOpernumber(String opernumber)
	{
		this.opernumber = opernumber;
	}

	/**
	 * Method 'getRispcomp'
	 * 
	 * @return String
	 */
	public String getRispcomp()
	{
		return rispcomp;
	}

	/**
	 * Method 'setRispcomp'
	 * 
	 * @param rispcomp
	 */
	public void setRispcomp(String rispcomp)
	{
		this.rispcomp = rispcomp;
	}

	/**
	 * Method 'getErrcode'
	 * 
	 * @return String
	 */
	public String getErrcode()
	{
		return errcode;
	}

	/**
	 * Method 'setErrcode'
	 * 
	 * @param errcode
	 */
	public void setErrcode(String errcode)
	{
		this.errcode = errcode;
	}

	/**
	 * Method 'getBuyername'
	 * 
	 * @return String
	 */
	public String getBuyername()
	{
		return buyername;
	}

	/**
	 * Method 'setBuyername'
	 * 
	 * @param buyername
	 */
	public void setBuyername(String buyername)
	{
		this.buyername = buyername;
	}

	/**
	 * Method 'getBuyercodfisc'
	 * 
	 * @return String
	 */
	public String getBuyercodfisc()
	{
		return buyercodfisc;
	}

	/**
	 * Method 'setBuyercodfisc'
	 * 
	 * @param buyercodfisc
	 */
	public void setBuyercodfisc(String buyercodfisc)
	{
		this.buyercodfisc = buyercodfisc;
	}

	/**
	 * Method 'getOldstate'
	 * 
	 * @return String
	 */
	@XmlElement(name = "oldstate",nillable = true, required = false)
	public long getOldstate()
	{
		return oldstate;
	}

	/**
	 * Method 'setOldstate'
	 * 
	 * @param oldstate
	 */
	public void setOldstate(long oldstate)
	{
		this.oldstate = oldstate;
	}

	/**
	 * Method 'getChangestatedate'
	 * 
	 * @return Date
	 */
	public Date getChangestatedate()
	{
		return changestatedate;
	}

	/**
	 * Method 'setChangestatedate'
	 * 
	 * @param changestatedate
	 */
	public void setChangestatedate(Date changestatedate)
	{
		this.changestatedate = changestatedate;
	}

	/**
	 * Method 'getUserhaschange'
	 * 
	 * @return String
	 */
	public String getUserhaschange()
	{
		return userhaschange;
	}

	/**
	 * Method 'setUserhaschange'
	 * 
	 * @param userhaschange
	 */
	public void setUserhaschange(String userhaschange)
	{
		this.userhaschange = userhaschange;
	}

	/**
	 * Method 'getClientipaddress'
	 * 
	 * @return String
	 */
	public String getClientipaddress()
	{
		return clientipaddress;
	}

	/**
	 * Method 'setClientipaddress'
	 * 
	 * @param clientipaddress
	 */
	public void setClientipaddress(String clientipaddress)
	{
		this.clientipaddress = clientipaddress;
	}

	/**
	 * Method 'getIncassokoerrormessage'
	 * 
	 * @return String
	 */
	public String getIncassokoerrormessage()
	{
		return incassokoerrormessage;
	}

	/**
	 * Method 'setIncassokoerrormessage'
	 * 
	 * @param incassokoerrormessage
	 */
	public void setIncassokoerrormessage(String incassokoerrormessage)
	{
		this.incassokoerrormessage = incassokoerrormessage;
	}

	/**
	 * Method 'getIntestatariocc'
	 * 
	 * @return String
	 */
	public String getIntestatariocc()
	{
		return intestatariocc;
	}

	/**
	 * Method 'setIntestatariocc'
	 * 
	 * @param intestatariocc
	 */
	public void setIntestatariocc(String intestatariocc)
	{
		this.intestatariocc = intestatariocc;
	}

	/**
	 * Method 'getPaymentid'
	 * 
	 * @return String
	 */
	public String getPaymentid()
	{
		return paymentid;
	}

	/**
	 * Method 'setPaymentid'
	 * 
	 * @param paymentid
	 */
	public void setPaymentid(String paymentid)
	{
		this.paymentid = paymentid;
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
		
		if (!(_other instanceof Transazione)) {
			return false;
		}
		
		final Transazione _cast = (Transazione) _other;
		if (transactionId == null ? _cast.transactionId != transactionId : !transactionId.equals( _cast.transactionId )) {
			return false;
		}
		
		if (applicationId == null ? _cast.applicationId != applicationId : !applicationId.equals( _cast.applicationId )) {
			return false;
		}
		
		if (language == null ? _cast.language != language : !language.equals( _cast.language )) {
			return false;
		}
		
		if (ccy == null ? _cast.ccy != ccy : !ccy.equals( _cast.ccy )) {
			return false;
		}
		
		if (buyerEmail == null ? _cast.buyerEmail != buyerEmail : !buyerEmail.equals( _cast.buyerEmail )) {
			return false;
		}
		
		if (basketId == null ? _cast.basketId != basketId : !basketId.equals( _cast.basketId )) {
			return false;
		}
		
		if (codStato != _cast.codStato) {
			return false;
		}
		
		if (commissioniApplicate != _cast.commissioniApplicate) {
			return false;
		}
		
		if (commissioniApplicateNull != _cast.commissioniApplicateNull) {
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
		
		if (gatewayId != _cast.gatewayId) {
			return false;
		}
		
		if (gatewaypaymodeid != _cast.gatewaypaymodeid) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (mscsorderid != _cast.mscsorderid) {
			return false;
		}
		
		if (mscsorderidNull != _cast.mscsorderidNull) {
			return false;
		}
		
		
		if (merchantId == null ? _cast.merchantId != merchantId : !merchantId.equals( _cast.merchantId )) {
			return false;
		}
		
		if (pgresultcode == null ? _cast.pgresultcode != pgresultcode : !pgresultcode.equals( _cast.pgresultcode )) {
			return false;
		}
		
		if (providertimestamp == null ? _cast.providertimestamp != providertimestamp : !providertimestamp.equals( _cast.providertimestamp )) {
			return false;
		}
		
		if (authornumber == null ? _cast.authornumber != authornumber : !authornumber.equals( _cast.authornumber )) {
			return false;
		}
		
		if (opernumber == null ? _cast.opernumber != opernumber : !opernumber.equals( _cast.opernumber )) {
			return false;
		}
		
		if (rispcomp == null ? _cast.rispcomp != rispcomp : !rispcomp.equals( _cast.rispcomp )) {
			return false;
		}
		
		if (errcode == null ? _cast.errcode != errcode : !errcode.equals( _cast.errcode )) {
			return false;
		}
		
		if (buyername == null ? _cast.buyername != buyername : !buyername.equals( _cast.buyername )) {
			return false;
		}
		
		if (buyercodfisc == null ? _cast.buyercodfisc != buyercodfisc : !buyercodfisc.equals( _cast.buyercodfisc )) {
			return false;
		}
		

		
		if (changestatedate == null ? _cast.changestatedate != changestatedate : !changestatedate.equals( _cast.changestatedate )) {
			return false;
		}
		
		if (userhaschange == null ? _cast.userhaschange != userhaschange : !userhaschange.equals( _cast.userhaschange )) {
			return false;
		}
		
		if (clientipaddress == null ? _cast.clientipaddress != clientipaddress : !clientipaddress.equals( _cast.clientipaddress )) {
			return false;
		}
		
		if (incassokoerrormessage == null ? _cast.incassokoerrormessage != incassokoerrormessage : !incassokoerrormessage.equals( _cast.incassokoerrormessage )) {
			return false;
		}
		
		if (intestatariocc == null ? _cast.intestatariocc != intestatariocc : !intestatariocc.equals( _cast.intestatariocc )) {
			return false;
		}
		
		if (paymentid == null ? _cast.paymentid != paymentid : !paymentid.equals( _cast.paymentid )) {
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
		if (transactionId != null) {
			_hashCode = 29 * _hashCode + transactionId.hashCode();
		}
		
		if (applicationId != null) {
			_hashCode = 29 * _hashCode + applicationId.hashCode();
		}
		
		if (language != null) {
			_hashCode = 29 * _hashCode + language.hashCode();
		}
		
		if (ccy != null) {
			_hashCode = 29 * _hashCode + ccy.hashCode();
		}
		
		if (buyerEmail != null) {
			_hashCode = 29 * _hashCode + buyerEmail.hashCode();
		}
		
		if (basketId != null) {
			_hashCode = 29 * _hashCode + basketId.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (codStato ^ (codStato >>> 32));
		
		_hashCode = 29 * _hashCode + (commissioniApplicateNull ? 1 : 0);
		if (initDate != null) {
			_hashCode = 29 * _hashCode + initDate.hashCode();
		}
		
		
		if (startDate != null) {
			_hashCode = 29 * _hashCode + startDate.hashCode();
		}
		
		if (finishDate != null) {
			_hashCode = 29 * _hashCode + finishDate.hashCode();
		}
		
		if (gatewayId != null)
			_hashCode = 29 * _hashCode + gatewayId.hashCode();
		if (gatewaypaymodeid != null)
			_hashCode = 29 * _hashCode + gatewaypaymodeid.hashCode();
		
		_hashCode = 29 * _hashCode + (int) (mscsorderid ^ (mscsorderid >>> 32));
		_hashCode = 29 * _hashCode + (mscsorderidNull ? 1 : 0);
		if (merchantId != null) {
			_hashCode = 29 * _hashCode + merchantId.hashCode();
		}
		
		if (pgresultcode != null) {
			_hashCode = 29 * _hashCode + pgresultcode.hashCode();
		}
		
		if (providertimestamp != null) {
			_hashCode = 29 * _hashCode + providertimestamp.hashCode();
		}
		
		if (authornumber != null) {
			_hashCode = 29 * _hashCode + authornumber.hashCode();
		}
		
		if (opernumber != null) {
			_hashCode = 29 * _hashCode + opernumber.hashCode();
		}
		
		if (rispcomp != null) {
			_hashCode = 29 * _hashCode + rispcomp.hashCode();
		}
		
		if (errcode != null) {
			_hashCode = 29 * _hashCode + errcode.hashCode();
		}
		
		if (buyername != null) {
			_hashCode = 29 * _hashCode + buyername.hashCode();
		}
		
		if (buyercodfisc != null) {
			_hashCode = 29 * _hashCode + buyercodfisc.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (oldstate ^ (oldstate >>> 32));
		
		if (changestatedate != null) {
			_hashCode = 29 * _hashCode + changestatedate.hashCode();
		}
		
		if (userhaschange != null) {
			_hashCode = 29 * _hashCode + userhaschange.hashCode();
		}
		
		if (clientipaddress != null) {
			_hashCode = 29 * _hashCode + clientipaddress.hashCode();
		}
		
		if (incassokoerrormessage != null) {
			_hashCode = 29 * _hashCode + incassokoerrormessage.hashCode();
		}
		
		if (intestatariocc != null) {
			_hashCode = 29 * _hashCode + intestatariocc.hashCode();
		}
		
		if (paymentid != null) {
			_hashCode = 29 * _hashCode + paymentid.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return TransazionePk
	 */
	public TransazionePk createPk()
	{
		return new TransazionePk(transactionId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Transazione: " );
		ret.append( "transactionId=" + transactionId );
		ret.append( ", applicationId=" + applicationId );
		ret.append( ", language=" + language );
		ret.append( ", ccy=" + ccy );
		ret.append( ", buyerEmail=" + buyerEmail );
		ret.append( ", basketId=" + basketId );
		ret.append( ", codStato=" + codStato );
		ret.append( ", commissioniApplicate=" + commissioniApplicate );
		ret.append( ", initDate=" + initDate );
		ret.append( ", startDate=" + startDate );
		ret.append( ", finishDate=" + finishDate );
		ret.append( ", gatewayId=" + gatewayId );
		ret.append( ", gatewaypaymodeid=" + gatewaypaymodeid );
		ret.append( ", amount=" + amount );
		ret.append( ", mscsorderid=" + mscsorderid );
		ret.append( ", merchantId=" + merchantId );
		ret.append( ", pgresultcode=" + pgresultcode );
		ret.append( ", providertimestamp=" + providertimestamp );
		ret.append( ", authornumber=" + authornumber );
		ret.append( ", opernumber=" + opernumber );
		ret.append( ", rispcomp=" + rispcomp );
		ret.append( ", errcode=" + errcode );
		ret.append( ", buyername=" + buyername );
		ret.append( ", buyercodfisc=" + buyercodfisc );
		ret.append( ", oldstate=" + oldstate );
		ret.append( ", changestatedate=" + changestatedate );
		ret.append( ", userhaschange=" + userhaschange );
		ret.append( ", clientipaddress=" + clientipaddress );
		ret.append( ", incassokoerrormessage=" + incassokoerrormessage );
		ret.append( ", intestatariocc=" + intestatariocc );
		ret.append( ", paymentid=" + paymentid );
		ret.append( ", payurl=" + payurl );
		return ret.toString();
	}

	public String getPayurl()
	{
		return payurl;
	}

	public void setPayurl(String payurl)
	{
		this.payurl = payurl;
	}

	

}
