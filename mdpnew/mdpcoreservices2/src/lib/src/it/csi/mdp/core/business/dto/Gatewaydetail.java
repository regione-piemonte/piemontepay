/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;


public class Gatewaydetail implements Serializable
{
	/** 
	 * This attribute maps to the column GATEWAY_ID in the GATEWAYDETAIL table.
	 */
	protected String gatewayId;

	/** 
	 * This attribute maps to the column PAYMENTMODE_ID in the GATEWAYDETAIL table.
	 */
	protected String paymentmodeId;

	/** 
	 * This attribute maps to the column DEFAULTPAYMENTURL in the GATEWAYDETAIL table.
	 */
	protected String defaultpaymenturl;

	/** 
	 * This attribute maps to the column BACKOFFICEURL in the GATEWAYDETAIL table.
	 */
	protected String backofficeurl;

	/** 
	 * This attribute maps to the column MDPGATEWAYPAGE in the GATEWAYDETAIL table.
	 */
	protected String mdpgatewaypage;

	/** 
	 * This attribute maps to the column HTTPPORT in the GATEWAYDETAIL table.
	 */
	protected long httpport;

	/** 
	 * This attribute represents whether the primitive attribute httpport is null.
	 */
	protected boolean httpportNull = true;

	/** 
	 * This attribute maps to the column CONTEXTPG in the GATEWAYDETAIL table.
	 */
	protected String contextpg;

	/** 
	 * This attribute maps to the column RETURNURLMDP in the GATEWAYDETAIL table.
	 */
	protected String returnurlmdp;

	/** 
	 * This attribute maps to the column RECEIPTURL in the GATEWAYDETAIL table.
	 */
	protected String receipturl;

	/** 
	 * This attribute maps to the column ERRORURL in the GATEWAYDETAIL table.
	 */
	protected String errorurl;

	/** 
	 * This attribute maps to the column SENDMAILONRESPONSE in the GATEWAYDETAIL table.
	 */
	protected String sendmailonresponse;

	/** 
	 * This attribute maps to the column ENABLED in the GATEWAYDETAIL table.
	 */
	protected String enabled;

	/** 
	 * This attribute maps to the column VEIRIFICAESITO in the GATEWAYDETAIL table.
	 */
	protected boolean verificaesito;
	

	/**
	 * Method 'Gatewaydetail'
	 * 
	 */
	public Gatewaydetail()
	{
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
	 * Method 'getPaymentmodeId'
	 * 
	 * @return String
	 */
	public String getPaymentmodeId()
	{
		return paymentmodeId;
	}

	/**
	 * Method 'setPaymentmodeId'
	 * 
	 * @param paymentmodeId
	 */
	public void setPaymentmodeId(String paymentmodeId)
	{
		this.paymentmodeId = paymentmodeId;
	}

	/**
	 * Method 'getDefaultpaymenturl'
	 * 
	 * @return String
	 */
	public String getDefaultpaymenturl()
	{
		return defaultpaymenturl;
	}

	/**
	 * Method 'setDefaultpaymenturl'
	 * 
	 * @param defaultpaymenturl
	 */
	public void setDefaultpaymenturl(String defaultpaymenturl)
	{
		this.defaultpaymenturl = defaultpaymenturl;
	}

	/**
	 * Method 'getBackofficeurl'
	 * 
	 * @return String
	 */
	public String getBackofficeurl()
	{
		return backofficeurl;
	}

	/**
	 * Method 'setBackofficeurl'
	 * 
	 * @param backofficeurl
	 */
	public void setBackofficeurl(String backofficeurl)
	{
		this.backofficeurl = backofficeurl;
	}

	/**
	 * Method 'getMdpgatewaypage'
	 * 
	 * @return String
	 */
	public String getMdpgatewaypage()
	{
		return mdpgatewaypage;
	}

	/**
	 * Method 'setMdpgatewaypage'
	 * 
	 * @param mdpgatewaypage
	 */
	public void setMdpgatewaypage(String mdpgatewaypage)
	{
		this.mdpgatewaypage = mdpgatewaypage;
	}

	/**
	 * Method 'getHttpport'
	 * 
	 * @return long
	 */
	public long getHttpport()
	{
		return httpport;
	}

	/**
	 * Method 'setHttpport'
	 * 
	 * @param httpport
	 */
	public void setHttpport(long httpport)
	{
		this.httpport = httpport;
		this.httpportNull = false;
	}

	/**
	 * Method 'setHttpportNull'
	 * 
	 * @param value
	 */
	public void setHttpportNull(boolean value)
	{
		this.httpportNull = value;
	}

	/**
	 * Method 'isHttpportNull'
	 * 
	 * @return boolean
	 */
	public boolean isHttpportNull()
	{
		return httpportNull;
	}

	/**
	 * Method 'getContextpg'
	 * 
	 * @return String
	 */
	public String getContextpg()
	{
		return contextpg;
	}

	/**
	 * Method 'setContextpg'
	 * 
	 * @param contextpg
	 */
	public void setContextpg(String contextpg)
	{
		this.contextpg = contextpg;
	}

	/**
	 * Method 'getReturnurlmdp'
	 * 
	 * @return String
	 */
	public String getReturnurlmdp()
	{
		return returnurlmdp;
	}

	/**
	 * Method 'setReturnurlmdp'
	 * 
	 * @param returnurlmdp
	 */
	public void setReturnurlmdp(String returnurlmdp)
	{
		this.returnurlmdp = returnurlmdp;
	}

	/**
	 * Method 'getReceipturl'
	 * 
	 * @return String
	 */
	public String getReceipturl()
	{
		return receipturl;
	}

	/**
	 * Method 'setReceipturl'
	 * 
	 * @param receipturl
	 */
	public void setReceipturl(String receipturl)
	{
		this.receipturl = receipturl;
	}

	/**
	 * Method 'getErrorurl'
	 * 
	 * @return String
	 */
	public String getErrorurl()
	{
		return errorurl;
	}

	/**
	 * Method 'setErrorurl'
	 * 
	 * @param errorurl
	 */
	public void setErrorurl(String errorurl)
	{
		this.errorurl = errorurl;
	}

	/**
	 * Method 'getSendmailonresponse'
	 * 
	 * @return String
	 */
	public String getSendmailonresponse()
	{
		return sendmailonresponse;
	}

	/**
	 * Method 'setSendmailonresponse'
	 * 
	 * @param sendmailonresponse
	 */
	public void setSendmailonresponse(String sendmailonresponse)
	{
		this.sendmailonresponse = sendmailonresponse;
	}

	/**
	 * Method 'getEnabled'
	 * 
	 * @return String
	 */
	public String getEnabled()
	{
		return enabled;
	}

	/**
	 * Method 'setEnabled'
	 * 
	 * @param enabled
	 */
	public void setEnabled(String enabled)
	{
		this.enabled = enabled;
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
		
		if (!(_other instanceof Gatewaydetail)) {
			return false;
		}
		
		final Gatewaydetail _cast = (Gatewaydetail) _other;
		if (gatewayId == null ? _cast.gatewayId != gatewayId : !gatewayId.equals( _cast.gatewayId )) {
			return false;
		}
		
		if (paymentmodeId == null ? _cast.paymentmodeId != paymentmodeId : !paymentmodeId.equals( _cast.paymentmodeId )) {
			return false;
		}
		
		if (defaultpaymenturl == null ? _cast.defaultpaymenturl != defaultpaymenturl : !defaultpaymenturl.equals( _cast.defaultpaymenturl )) {
			return false;
		}
		
		if (backofficeurl == null ? _cast.backofficeurl != backofficeurl : !backofficeurl.equals( _cast.backofficeurl )) {
			return false;
		}
		
		if (mdpgatewaypage == null ? _cast.mdpgatewaypage != mdpgatewaypage : !mdpgatewaypage.equals( _cast.mdpgatewaypage )) {
			return false;
		}
		
		if (httpport != _cast.httpport) {
			return false;
		}
		
		if (httpportNull != _cast.httpportNull) {
			return false;
		}
		
		if (contextpg == null ? _cast.contextpg != contextpg : !contextpg.equals( _cast.contextpg )) {
			return false;
		}
		
		if (returnurlmdp == null ? _cast.returnurlmdp != returnurlmdp : !returnurlmdp.equals( _cast.returnurlmdp )) {
			return false;
		}
		
		if (receipturl == null ? _cast.receipturl != receipturl : !receipturl.equals( _cast.receipturl )) {
			return false;
		}
		
		if (errorurl == null ? _cast.errorurl != errorurl : !errorurl.equals( _cast.errorurl )) {
			return false;
		}
		
		if (sendmailonresponse == null ? _cast.sendmailonresponse != sendmailonresponse : !sendmailonresponse.equals( _cast.sendmailonresponse )) {
			return false;
		}
		
		if (enabled == null ? _cast.enabled != enabled : !enabled.equals( _cast.enabled )) {
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
		if (gatewayId != null) {
			_hashCode = 29 * _hashCode + gatewayId.hashCode();
		}
		
		if (paymentmodeId != null) {
			_hashCode = 29 * _hashCode + paymentmodeId.hashCode();
		}
		
		if (defaultpaymenturl != null) {
			_hashCode = 29 * _hashCode + defaultpaymenturl.hashCode();
		}
		
		if (backofficeurl != null) {
			_hashCode = 29 * _hashCode + backofficeurl.hashCode();
		}
		
		if (mdpgatewaypage != null) {
			_hashCode = 29 * _hashCode + mdpgatewaypage.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (httpport ^ (httpport >>> 32));
		_hashCode = 29 * _hashCode + (httpportNull ? 1 : 0);
		if (contextpg != null) {
			_hashCode = 29 * _hashCode + contextpg.hashCode();
		}
		
		if (returnurlmdp != null) {
			_hashCode = 29 * _hashCode + returnurlmdp.hashCode();
		}
		
		if (receipturl != null) {
			_hashCode = 29 * _hashCode + receipturl.hashCode();
		}
		
		if (errorurl != null) {
			_hashCode = 29 * _hashCode + errorurl.hashCode();
		}
		
		if (sendmailonresponse != null) {
			_hashCode = 29 * _hashCode + sendmailonresponse.hashCode();
		}
		
		if (enabled != null) {
			_hashCode = 29 * _hashCode + enabled.hashCode();
		}
		
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return GatewaydetailPk
	 */
	public GatewaydetailPk createPk()
	{
		return new GatewaydetailPk(gatewayId, paymentmodeId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Gatewaydetail: " );
		ret.append( "gatewayId=" + gatewayId );
		ret.append( ", paymentmodeId=" + paymentmodeId );
		ret.append( ", defaultpaymenturl=" + defaultpaymenturl );
		ret.append( ", backofficeurl=" + backofficeurl );
		ret.append( ", mdpgatewaypage=" + mdpgatewaypage );
		ret.append( ", httpport=" + httpport );
		ret.append( ", contextpg=" + contextpg );
		ret.append( ", returnurlmdp=" + returnurlmdp );
		ret.append( ", receipturl=" + receipturl );
		ret.append( ", errorurl=" + errorurl );
		ret.append( ", sendmailonresponse=" + sendmailonresponse );
		ret.append( ", enabled=" + enabled );
		ret.append( ", verificaesito=" + verificaesito );
		return ret.toString();
	}

	public boolean isVerificaesito()
	{
		return verificaesito;
	}

	public void setVerificaesito(boolean verificaesito)
	{
		this.verificaesito = verificaesito;
	}

}
