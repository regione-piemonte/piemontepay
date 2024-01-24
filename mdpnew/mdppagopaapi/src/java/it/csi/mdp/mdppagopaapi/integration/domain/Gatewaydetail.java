/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gatewaydetail database table.
 * 
 */
@Entity
@NamedQuery(name="Gatewaydetail.findAll", query="SELECT g FROM Gatewaydetail g")
public class Gatewaydetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GatewaydetailPK id;

	private String backofficeurl;

	private String contextpg;

	private String defaultpaymenturl;

	private String enabled;

	private String errorurl;

	private BigDecimal httpport;

	private String mdpgatewaypage;

	private String receipturl;

	private String returnurlmdp;

	private String sendmailonresponse;

	private Boolean verificaesito;

	//bi-directional many-to-one association to Gateway
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gateway_id", insertable=false, updatable=false)
	private Gateway gateway;

	//bi-directional many-to-one association to Paymentmode
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="paymentmode_id", insertable=false, updatable=false)
	private Paymentmode paymentmode;

	public Gatewaydetail() {
	}

	public GatewaydetailPK getId() {
		return this.id;
	}

	public void setId(GatewaydetailPK id) {
		this.id = id;
	}

	public String getBackofficeurl() {
		return this.backofficeurl;
	}

	public void setBackofficeurl(String backofficeurl) {
		this.backofficeurl = backofficeurl;
	}

	public String getContextpg() {
		return this.contextpg;
	}

	public void setContextpg(String contextpg) {
		this.contextpg = contextpg;
	}

	public String getDefaultpaymenturl() {
		return this.defaultpaymenturl;
	}

	public void setDefaultpaymenturl(String defaultpaymenturl) {
		this.defaultpaymenturl = defaultpaymenturl;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getErrorurl() {
		return this.errorurl;
	}

	public void setErrorurl(String errorurl) {
		this.errorurl = errorurl;
	}

	public BigDecimal getHttpport() {
		return this.httpport;
	}

	public void setHttpport(BigDecimal httpport) {
		this.httpport = httpport;
	}

	public String getMdpgatewaypage() {
		return this.mdpgatewaypage;
	}

	public void setMdpgatewaypage(String mdpgatewaypage) {
		this.mdpgatewaypage = mdpgatewaypage;
	}

	public String getReceipturl() {
		return this.receipturl;
	}

	public void setReceipturl(String receipturl) {
		this.receipturl = receipturl;
	}

	public String getReturnurlmdp() {
		return this.returnurlmdp;
	}

	public void setReturnurlmdp(String returnurlmdp) {
		this.returnurlmdp = returnurlmdp;
	}

	public String getSendmailonresponse() {
		return this.sendmailonresponse;
	}

	public void setSendmailonresponse(String sendmailonresponse) {
		this.sendmailonresponse = sendmailonresponse;
	}

	public Boolean getVerificaesito() {
		return this.verificaesito;
	}

	public void setVerificaesito(Boolean verificaesito) {
		this.verificaesito = verificaesito;
	}

	public Gateway getGateway() {
		return this.gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

	public Paymentmode getPaymentmode() {
		return this.paymentmode;
	}

	public void setPaymentmode(Paymentmode paymentmode) {
		this.paymentmode = paymentmode;
	}

}
