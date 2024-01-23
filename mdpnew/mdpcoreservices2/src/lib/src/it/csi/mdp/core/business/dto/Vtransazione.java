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

public class Vtransazione implements Serializable
{
	/** 
	 * This attribute maps to the column transaction_id in the vtransazione table.
	 */
	protected String transactionId;

	/** 
	 * This attribute maps to the column application_id in the vtransazione table.
	 */
	protected String applicationId;

	/** 
	 * This attribute maps to the column language in the vtransazione table.
	 */
	protected String language;

	/** 
	 * This attribute maps to the column buyer_email in the vtransazione table.
	 */
	protected String buyerEmail;

	/** 
	 * This attribute maps to the column basket_id in the vtransazione table.
	 */
	protected String basketId;

	/** 
	 * This attribute maps to the column commissioni_applicate in the vtransazione table.
	 */
	protected float commissioniApplicate;

	/** 
	 * This attribute represents whether the primitive attribute commissioniApplicate is null.
	 */
	protected boolean commissioniApplicateNull = true;

	/** 
	 * This attribute maps to the column init_date in the vtransazione table.
	 */
	protected Date initDate;

	/** 
	 * This attribute maps to the column start_date in the vtransazione table.
	 */
	protected Date startDate;

	/** 
	 * This attribute maps to the column finish_date in the vtransazione table.
	 */
	protected Date finishDate;

	/** 
	 * This attribute maps to the column gatewaypaymodeid in the vtransazione table.
	 */
	protected String gatewaypaymodeid;

	/** 
	 * This attribute maps to the column amount in the vtransazione table.
	 */
	protected float amount;

	/** 
	 * This attribute represents whether the primitive attribute amount is null.
	 */
	protected boolean amountNull = true;

	/** 
	 * This attribute maps to the column mscsorderid in the vtransazione table.
	 */
	protected long mscsorderid;

	/** 
	 * This attribute represents whether the primitive attribute mscsorderid is null.
	 */
	protected boolean mscsorderidNull = true;

	/** 
	 * This attribute maps to the column merchant_id in the vtransazione table.
	 */
	protected String merchantId;

	/** 
	 * This attribute maps to the column pgresultcode in the vtransazione table.
	 */
	protected String pgresultcode;

	/** 
	 * This attribute maps to the column providertimestamp in the vtransazione table.
	 */
	protected String providertimestamp;

	/** 
	 * This attribute maps to the column authornumber in the vtransazione table.
	 */
	protected String authornumber;

	/** 
	 * This attribute maps to the column opernumber in the vtransazione table.
	 */
	protected String opernumber;

	/** 
	 * This attribute maps to the column rispcomp in the vtransazione table.
	 */
	protected String rispcomp;

	/** 
	 * This attribute maps to the column errcode in the vtransazione table.
	 */
	protected String errcode;

	/** 
	 * This attribute maps to the column buyername in the vtransazione table.
	 */
	protected String buyername;

	/** 
	 * This attribute maps to the column buyercodfisc in the vtransazione table.
	 */
	protected String buyercodfisc;

	/** 
	 * This attribute maps to the column oldstate in the vtransazione table.
	 */
	protected long oldstate;

	/** 
	 * This attribute represents whether the primitive attribute oldstate is null.
	 */
	protected boolean oldstateNull = true;

	/** 
	 * This attribute maps to the column changestatedate in the vtransazione table.
	 */
	protected Date changestatedate;

	/** 
	 * This attribute maps to the column userhaschange in the vtransazione table.
	 */
	protected String userhaschange;

	/** 
	 * This attribute maps to the column clientipaddress in the vtransazione table.
	 */
	protected String clientipaddress;

	/** 
	 * This attribute maps to the column incassokoerrormessage in the vtransazione table.
	 */
	protected String incassokoerrormessage;

	/** 
	 * This attribute maps to the column intestatariocc in the vtransazione table.
	 */
	protected String intestatariocc;

	/** 
	 * This attribute maps to the column paymentid in the vtransazione table.
	 */
	protected String paymentid;

	/** 
	 * This attribute maps to the column payurl in the vtransazione table.
	 */
	protected String payurl;

	/** 
	 * This attribute maps to the column mdplanguagecode in the vtransazione table.
	 */
	protected String mdplanguagecode;

	/** 
	 * This attribute maps to the column ccy in the vtransazione table.
	 */
	protected String ccy;

	/** 
	 * This attribute maps to the column currencydescr in the vtransazione table.
	 */
	protected String currencydescr;

	/** 
	 * This attribute maps to the column gateway_id in the vtransazione table.
	 */
	protected String gatewayId;

	/** 
	 * This attribute maps to the column descrizione in the vtransazione table.
	 */
	protected String descrizione;

	/** 
	 * This attribute maps to the column cod_stato in the vtransazione table.
	 */
	protected long codStato;

	/** 
	 * This attribute represents whether the primitive attribute codStato is null.
	 */
	protected boolean codStatoNull = true;

	/** 
	 * This attribute maps to the column gateway_description in the vtransazione table.
	 */
	protected String gatewayDescription;

	/** 
	 * This attribute maps to the column paymentmode_description in the vtransazione table.
	 */
	protected String paymentmodeDescription;

	/** 
	 * This attribute maps to the column applicationname in the vtransazione table.
	 */
	protected String applicationname;

	/** 
	 * This attribute maps to the column cliente in the vtransazione table.
	 */
	protected String cliente;

	/** 
	 * This attribute maps to the column progetto in the vtransazione table.
	 */
	protected String progetto;

	/**
	 * Method 'Vtransazione'
	 * 
	 */
	public Vtransazione()
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
	 * Method 'getCommissioniApplicate'
	 * 
	 * @return float
	 */
	public float getCommissioniApplicate()
	{
		return commissioniApplicate;
	}

	/**
	 * Method 'setCommissioniApplicate'
	 * 
	 * @param commissioniApplicate
	 */
	public void setCommissioniApplicate(float commissioniApplicate)
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
	 * Method 'getGatewaypaymodeid'
	 * 
	 * @return String
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
	 * Method 'getMscsorderid'
	 * 
	 * @return long
	 */
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
	 * @return long
	 */
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
		this.oldstateNull = false;
	}

	/**
	 * Method 'setOldstateNull'
	 * 
	 * @param value
	 */
	public void setOldstateNull(boolean value)
	{
		this.oldstateNull = value;
	}

	/**
	 * Method 'isOldstateNull'
	 * 
	 * @return boolean
	 */
	public boolean isOldstateNull()
	{
		return oldstateNull;
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
	 * Method 'getMdplanguagecode'
	 * 
	 * @return String
	 */
	public String getMdplanguagecode()
	{
		return mdplanguagecode;
	}

	/**
	 * Method 'setMdplanguagecode'
	 * 
	 * @param mdplanguagecode
	 */
	public void setMdplanguagecode(String mdplanguagecode)
	{
		this.mdplanguagecode = mdplanguagecode;
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
		
		if (!(_other instanceof Vtransazione)) {
			return false;
		}
		
		final Vtransazione _cast = (Vtransazione) _other;
		if (transactionId == null ? _cast.transactionId != transactionId : !transactionId.equals( _cast.transactionId )) {
			return false;
		}
		
		if (applicationId == null ? _cast.applicationId != applicationId : !applicationId.equals( _cast.applicationId )) {
			return false;
		}
		
		if (language == null ? _cast.language != language : !language.equals( _cast.language )) {
			return false;
		}
		
		if (buyerEmail == null ? _cast.buyerEmail != buyerEmail : !buyerEmail.equals( _cast.buyerEmail )) {
			return false;
		}
		
		if (basketId == null ? _cast.basketId != basketId : !basketId.equals( _cast.basketId )) {
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
		
		if (gatewaypaymodeid == null ? _cast.gatewaypaymodeid != gatewaypaymodeid : !gatewaypaymodeid.equals( _cast.gatewaypaymodeid )) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (amountNull != _cast.amountNull) {
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
		
		if (oldstate != _cast.oldstate) {
			return false;
		}
		
		if (oldstateNull != _cast.oldstateNull) {
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
		
		if (payurl == null ? _cast.payurl != payurl : !payurl.equals( _cast.payurl )) {
			return false;
		}
		
		if (mdplanguagecode == null ? _cast.mdplanguagecode != mdplanguagecode : !mdplanguagecode.equals( _cast.mdplanguagecode )) {
			return false;
		}
		
		if (ccy == null ? _cast.ccy != ccy : !ccy.equals( _cast.ccy )) {
			return false;
		}
		
		if (currencydescr == null ? _cast.currencydescr != currencydescr : !currencydescr.equals( _cast.currencydescr )) {
			return false;
		}
		
		if (gatewayId == null ? _cast.gatewayId != gatewayId : !gatewayId.equals( _cast.gatewayId )) {
			return false;
		}
		
		if (descrizione == null ? _cast.descrizione != descrizione : !descrizione.equals( _cast.descrizione )) {
			return false;
		}
		
		if (codStato != _cast.codStato) {
			return false;
		}
		
		if (codStatoNull != _cast.codStatoNull) {
			return false;
		}
		
		if (gatewayDescription == null ? _cast.gatewayDescription != gatewayDescription : !gatewayDescription.equals( _cast.gatewayDescription )) {
			return false;
		}
		
		if (paymentmodeDescription == null ? _cast.paymentmodeDescription != paymentmodeDescription : !paymentmodeDescription.equals( _cast.paymentmodeDescription )) {
			return false;
		}
		
		if (applicationname == null ? _cast.applicationname != applicationname : !applicationname.equals( _cast.applicationname )) {
			return false;
		}
		
		if (cliente == null ? _cast.cliente != cliente : !cliente.equals( _cast.cliente )) {
			return false;
		}
		
		if (progetto == null ? _cast.progetto != progetto : !progetto.equals( _cast.progetto )) {
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
		
		if (buyerEmail != null) {
			_hashCode = 29 * _hashCode + buyerEmail.hashCode();
		}
		
		if (basketId != null) {
			_hashCode = 29 * _hashCode + basketId.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(commissioniApplicate);
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
		
		if (gatewaypaymodeid != null) {
			_hashCode = 29 * _hashCode + gatewaypaymodeid.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(amount);
		_hashCode = 29 * _hashCode + (amountNull ? 1 : 0);
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
		_hashCode = 29 * _hashCode + (oldstateNull ? 1 : 0);
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
		
		if (payurl != null) {
			_hashCode = 29 * _hashCode + payurl.hashCode();
		}
		
		if (mdplanguagecode != null) {
			_hashCode = 29 * _hashCode + mdplanguagecode.hashCode();
		}
		
		if (ccy != null) {
			_hashCode = 29 * _hashCode + ccy.hashCode();
		}
		
		if (currencydescr != null) {
			_hashCode = 29 * _hashCode + currencydescr.hashCode();
		}
		
		if (gatewayId != null) {
			_hashCode = 29 * _hashCode + gatewayId.hashCode();
		}
		
		if (descrizione != null) {
			_hashCode = 29 * _hashCode + descrizione.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (codStato ^ (codStato >>> 32));
		_hashCode = 29 * _hashCode + (codStatoNull ? 1 : 0);
		if (gatewayDescription != null) {
			_hashCode = 29 * _hashCode + gatewayDescription.hashCode();
		}
		
		if (paymentmodeDescription != null) {
			_hashCode = 29 * _hashCode + paymentmodeDescription.hashCode();
		}
		
		if (applicationname != null) {
			_hashCode = 29 * _hashCode + applicationname.hashCode();
		}
		
		if (cliente != null) {
			_hashCode = 29 * _hashCode + cliente.hashCode();
		}
		
		if (progetto != null) {
			_hashCode = 29 * _hashCode + progetto.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.Vtransazione: " );
		ret.append( "transactionId=" + transactionId );
		ret.append( ", applicationId=" + applicationId );
		ret.append( ", language=" + language );
		ret.append( ", buyerEmail=" + buyerEmail );
		ret.append( ", basketId=" + basketId );
		ret.append( ", commissioniApplicate=" + commissioniApplicate );
		ret.append( ", initDate=" + initDate );
		ret.append( ", startDate=" + startDate );
		ret.append( ", finishDate=" + finishDate );
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
		ret.append( ", mdplanguagecode=" + mdplanguagecode );
		ret.append( ", ccy=" + ccy );
		ret.append( ", currencydescr=" + currencydescr );
		ret.append( ", gatewayId=" + gatewayId );
		ret.append( ", descrizione=" + descrizione );
		ret.append( ", codStato=" + codStato );
		ret.append( ", gatewayDescription=" + gatewayDescription );
		ret.append( ", paymentmodeDescription=" + paymentmodeDescription );
		ret.append( ", applicationname=" + applicationname );
		ret.append( ", cliente=" + cliente );
		ret.append( ", progetto=" + progetto );
		return ret.toString();
	}

}
