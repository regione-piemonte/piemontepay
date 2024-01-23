/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.List;

public class ApplicationGatewayConfiguration extends ApplicationDetail implements Serializable
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6255262158025972335L;
	private  List<Applicationcustomfields>  appcustfieldslist = null;
	private String gatewaydescription = "";
	private String paymodedescription = ""; 

	public ApplicationGatewayConfiguration()
	{
		super();
	}
	public ApplicationGatewayConfiguration(ApplicationDetail appd)
	{
		super();
		this.applicationid = appd.applicationid;
		this.applicationurlback = appd.applicationurlback;
		this.applicationurlcancel = appd.applicationurlcancel;
		this.applicationurlerror = appd.applicationurlerror;
		this.applicationurlresponseko = appd.applicationurlresponseko;
		this.applicationurlresponseok = appd.applicationurlresponseok;
		this.codiceistat = appd.codiceistat;
		this.contocorrenteposte = appd.contocorrenteposte;
		this.enabled = appd.enabled;
		this.flagreturlmdp = appd.flagreturlmdp;
		this.gatewayid = appd.gatewayid;
		this.macAvvio = appd.macAvvio;
		this.macEsito = appd.macEsito;
		this.mail2buyerko = appd.mail2buyerko;
		this.mail2buyerok = appd.mail2buyerok;
		this.mail2esercko = appd.mail2esercko;
		this.mail2esercok = appd.mail2esercok;
		this.mail2sysko = appd.mail2sysko;
		this.mail2sysok = appd.mail2sysok;
		this.merchantid = appd.merchantid;
		this.merchantidpassword = appd.merchantidpassword;
		this.paymentmodeid = appd.paymentmodeid;
		this.pgactioncode = appd.pgactioncode;
		this.pgactioncodeNull = appd.pgactioncodeNull;
		this.pgcontabcode = appd.pgcontabcode;
		this.pgcontabcodeNull = appd.pgcontabcodeNull;
		this.sogliaa = appd.sogliaa;
		this.sogliaaNull = appd.sogliaaNull;
		this.sogliada = appd.sogliada;
		this.sogliadaNull = appd.sogliadaNull;
		this.tipobollettinoposte = appd.tipobollettinoposte;
		this.tipocommissione  = appd.tipocommissione;
		this.tipodocumentoposte = appd.tipodocumentoposte;
		this.valorecommissionemax = appd.valorecommissionemax;
		this.valorecommissionemaxNull = appd.valorecommissionemaxNull;
		this.valorecommissionemin =appd.valorecommissionemin;
		
	}
	public List<Applicationcustomfields> getAppcustfields()
	{
		return appcustfieldslist;
	}
	public void setAppcustfields(List<Applicationcustomfields> appcustfieldslist)
	{
		this.appcustfieldslist = appcustfieldslist;
	}
	
	public void setGatewaydescription(String gatewaydescription)
	{
		this.gatewaydescription = gatewaydescription;
	}
	public String getGatewaydescription()
	{
		return gatewaydescription;
	}
	public void setPaymodedescription(String paymodedescription)
	{
		this.paymodedescription = paymodedescription;
	}
	public String getPaymodedescription()
	{
		return paymodedescription;
	}
	

}
