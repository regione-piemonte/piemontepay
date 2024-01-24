/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the applicationdetail_log database table.
 * 
 */
@Entity
@Table(name="applicationdetail_log")
@NamedQuery(name="ApplicationdetailLog.findAll", query="SELECT a FROM ApplicationdetailLog a")
public class ApplicationdetailLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICATIONDETAIL_LOG_SEQ_GENERATOR", sequenceName="APPLICATIONDETAIL_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICATIONDETAIL_LOG_SEQ_GENERATOR")
	private Long seq;

	private String applicationid;

	private String applicationurlback;

	private String applicationurlcancel;

	private String applicationurlerror;

	private String applicationurlresponseko;

	private String applicationurlresponseok;

	private String codiceistat;

	private String contocorrenteposte;

	private String enabled;

	private String flagreturlmdp;

	private String gatewayid;

	@Column(name="mac_avvio")
	private String macAvvio;

	@Column(name="mac_esito")
	private String macEsito;

	private String mail2buyerko;

	private String mail2buyerok;

	private String mail2esercko;

	private String mail2esercok;

	private String mail2sysko;

	private String mail2sysok;

	private String merchantid;

	private String merchantidpassword;

	private String paymentmodeid;

	private BigDecimal pgactioncode;

	private BigDecimal pgcontabcode;

	private BigDecimal sogliaa;

	private BigDecimal sogliada;

	private String tipobollettinoposte;

	private String tipocommissione;

	private String tipodocumentoposte;

	private BigDecimal valorecommissionemax;

	private BigDecimal valorecommissionemin;

	public ApplicationdetailLog() {
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getApplicationurlback() {
		return this.applicationurlback;
	}

	public void setApplicationurlback(String applicationurlback) {
		this.applicationurlback = applicationurlback;
	}

	public String getApplicationurlcancel() {
		return this.applicationurlcancel;
	}

	public void setApplicationurlcancel(String applicationurlcancel) {
		this.applicationurlcancel = applicationurlcancel;
	}

	public String getApplicationurlerror() {
		return this.applicationurlerror;
	}

	public void setApplicationurlerror(String applicationurlerror) {
		this.applicationurlerror = applicationurlerror;
	}

	public String getApplicationurlresponseko() {
		return this.applicationurlresponseko;
	}

	public void setApplicationurlresponseko(String applicationurlresponseko) {
		this.applicationurlresponseko = applicationurlresponseko;
	}

	public String getApplicationurlresponseok() {
		return this.applicationurlresponseok;
	}

	public void setApplicationurlresponseok(String applicationurlresponseok) {
		this.applicationurlresponseok = applicationurlresponseok;
	}

	public String getCodiceistat() {
		return this.codiceistat;
	}

	public void setCodiceistat(String codiceistat) {
		this.codiceistat = codiceistat;
	}

	public String getContocorrenteposte() {
		return this.contocorrenteposte;
	}

	public void setContocorrenteposte(String contocorrenteposte) {
		this.contocorrenteposte = contocorrenteposte;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getFlagreturlmdp() {
		return this.flagreturlmdp;
	}

	public void setFlagreturlmdp(String flagreturlmdp) {
		this.flagreturlmdp = flagreturlmdp;
	}

	public String getGatewayid() {
		return this.gatewayid;
	}

	public void setGatewayid(String gatewayid) {
		this.gatewayid = gatewayid;
	}

	public String getMacAvvio() {
		return this.macAvvio;
	}

	public void setMacAvvio(String macAvvio) {
		this.macAvvio = macAvvio;
	}

	public String getMacEsito() {
		return this.macEsito;
	}

	public void setMacEsito(String macEsito) {
		this.macEsito = macEsito;
	}

	public String getMail2buyerko() {
		return this.mail2buyerko;
	}

	public void setMail2buyerko(String mail2buyerko) {
		this.mail2buyerko = mail2buyerko;
	}

	public String getMail2buyerok() {
		return this.mail2buyerok;
	}

	public void setMail2buyerok(String mail2buyerok) {
		this.mail2buyerok = mail2buyerok;
	}

	public String getMail2esercko() {
		return this.mail2esercko;
	}

	public void setMail2esercko(String mail2esercko) {
		this.mail2esercko = mail2esercko;
	}

	public String getMail2esercok() {
		return this.mail2esercok;
	}

	public void setMail2esercok(String mail2esercok) {
		this.mail2esercok = mail2esercok;
	}

	public String getMail2sysko() {
		return this.mail2sysko;
	}

	public void setMail2sysko(String mail2sysko) {
		this.mail2sysko = mail2sysko;
	}

	public String getMail2sysok() {
		return this.mail2sysok;
	}

	public void setMail2sysok(String mail2sysok) {
		this.mail2sysok = mail2sysok;
	}

	public String getMerchantid() {
		return this.merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getMerchantidpassword() {
		return this.merchantidpassword;
	}

	public void setMerchantidpassword(String merchantidpassword) {
		this.merchantidpassword = merchantidpassword;
	}

	public String getPaymentmodeid() {
		return this.paymentmodeid;
	}

	public void setPaymentmodeid(String paymentmodeid) {
		this.paymentmodeid = paymentmodeid;
	}

	public BigDecimal getPgactioncode() {
		return this.pgactioncode;
	}

	public void setPgactioncode(BigDecimal pgactioncode) {
		this.pgactioncode = pgactioncode;
	}

	public BigDecimal getPgcontabcode() {
		return this.pgcontabcode;
	}

	public void setPgcontabcode(BigDecimal pgcontabcode) {
		this.pgcontabcode = pgcontabcode;
	}

	public BigDecimal getSogliaa() {
		return this.sogliaa;
	}

	public void setSogliaa(BigDecimal sogliaa) {
		this.sogliaa = sogliaa;
	}

	public BigDecimal getSogliada() {
		return this.sogliada;
	}

	public void setSogliada(BigDecimal sogliada) {
		this.sogliada = sogliada;
	}

	public String getTipobollettinoposte() {
		return this.tipobollettinoposte;
	}

	public void setTipobollettinoposte(String tipobollettinoposte) {
		this.tipobollettinoposte = tipobollettinoposte;
	}

	public String getTipocommissione() {
		return this.tipocommissione;
	}

	public void setTipocommissione(String tipocommissione) {
		this.tipocommissione = tipocommissione;
	}

	public String getTipodocumentoposte() {
		return this.tipodocumentoposte;
	}

	public void setTipodocumentoposte(String tipodocumentoposte) {
		this.tipodocumentoposte = tipodocumentoposte;
	}

	public BigDecimal getValorecommissionemax() {
		return this.valorecommissionemax;
	}

	public void setValorecommissionemax(BigDecimal valorecommissionemax) {
		this.valorecommissionemax = valorecommissionemax;
	}

	public BigDecimal getValorecommissionemin() {
		return this.valorecommissionemin;
	}

	public void setValorecommissionemin(BigDecimal valorecommissionemin) {
		this.valorecommissionemin = valorecommissionemin;
	}

}
