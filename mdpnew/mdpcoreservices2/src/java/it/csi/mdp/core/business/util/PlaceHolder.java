/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.util;

public class PlaceHolder
{


	private String transId;
	private String merchantID;
	private String esito;
	private String dataOp;
	private String amount;
	private String ccy;
	private String numAutor;
	private String numOper;
	private String payMode;
	private String buyerEmail;
	private String buyerName;
	private String provider;
	private String numVerde;
	private String iuv;


//	private String _applicationID;
	

	

	public PlaceHolder(String esito, String transId, String merchantID, String provider, String paymode, String dataOp, String amount, String ccy, String numAutor, String numoper, String buyerEmail, String buyerName, String numVerde)
	{
		this.transId = transId;
		this.merchantID = merchantID;
		this.esito = esito;
		this.dataOp = dataOp;
		this.amount = amount;
		this.ccy = ccy;
		this.numAutor = numAutor;
		this.numOper = numoper;
		this.buyerEmail = buyerEmail;
		this.buyerName = buyerName;
		this.provider= provider;
		this.payMode=paymode;
		this.numVerde = numVerde;
	}

	public PlaceHolder(String esito, String transId, String merchantID, String provider, String paymode, String dataOp, String amount, String ccy, String numAutor, String numoper, String buyerEmail, String buyerName)
	{
		this.transId = transId;
		this.merchantID = merchantID;
		this.esito = esito;
		this.dataOp = dataOp;
		this.amount = amount;
		this.ccy = ccy;
		this.numAutor = numAutor;
		this.numOper = numoper;
		this.buyerEmail = buyerEmail;
		this.buyerName = buyerName;
		this.provider= provider;
		this.payMode=paymode;
	}
	

	public PlaceHolder(String esito, String transId, String merchantID, String provider, String paymode, String dataOp, String buyerEmail, String buyerName, String numVerde)
	{
		this.transId = transId;
		this.merchantID = merchantID;
		this.esito = esito;
		this.dataOp = dataOp;
		this.buyerEmail = buyerEmail;
		this.buyerName = buyerName;
		this.provider= provider;
		this.payMode=paymode;
		this.numVerde = numVerde;
	}
	public String getTransId()
	{
		return transId;
	}
	public void setTransId(String transId)
	{
		this.transId = transId;
	}
	public String getMerchantID()
	{
		return merchantID;
	}
	public void setMerchantID(String merchantID)
	{
		this.merchantID = merchantID;
	}
	public String getEsito()
	{
		return esito;
	}
	public void setEsito(String esito)
	{
		this.esito = esito;
	}
	public String getDataOp()
	{
		return dataOp;
	}
	public void setDataOp(String dataOp)
	{
		this.dataOp = dataOp;
	}
	public String getAmount()
	{
		return amount;
	}
	public void setAmount(String amount)
	{
		this.amount = amount;
	}
	public String getCcy()
	{
		return ccy;
	}
	public void setCcy(String ccy)
	{
		this.ccy = ccy;
	}
	public String getNumAutor()
	{
		return numAutor;
	}
	public void setNumAutor(String numAutor)
	{
		this.numAutor = numAutor;
	}
	public String getNumOper()
	{
		return numOper;
	}
	public void setNumOper(String numOper)
	{
		this.numOper = numOper;
	}
	public String getPayMode()
	{
		return payMode;
	}
	public void setPayMode(String payMode)
	{
		this.payMode = payMode;
	}
	public String getBuyerEmail()
	{
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail)
	{
		this.buyerEmail = buyerEmail;
	}
	public String getBuyerName()
	{
		return buyerName;
	}
	public void setBuyerName(String buyerName)
	{
		this.buyerName = buyerName;
	}
	public String getProvider()
	{
		return provider;
	}
	public void setProvider(String provider)
	{
		this.provider = provider;
	}
	public String getNumVerde()
	{
		return numVerde;
	}
	public void setNumVerde(String numVerde)
	{
		this.numVerde = numVerde;
	}
	
	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}


	}
