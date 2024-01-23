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

public class ApplicationDetail implements Serializable
{
    private static final long serialVersionUID = 3941449515975069182L;
    
	/** 
	 * This attribute maps to the column applicationid in the applicationdetail table.
	 */
	protected String applicationid;

	/** 
	 * This attribute maps to the column gatewayid in the applicationdetail table.
	 */
	protected String gatewayid;

	/** 
	 * This attribute maps to the column paymentmodeid in the applicationdetail table.
	 */
	protected String paymentmodeid;

	/** 
	 * This attribute maps to the column merchantid in the applicationdetail table.
	 */
	protected String merchantid;

	/** 
	 * This attribute maps to the column merchantidpassword in the applicationdetail table.
	 */
	protected String merchantidpassword;

	/** 
	 * This attribute maps to the column pgactioncode in the applicationdetail table.
	 */
	protected long pgactioncode;

	/** 
	 * This attribute represents whether the primitive attribute pgactioncode is null.
	 */
	protected boolean pgactioncodeNull = true;

	/** 
	 * This attribute maps to the column flagreturlmdp in the applicationdetail table.
	 */
	protected String flagreturlmdp;

	/** 
	 * This attribute maps to the column applicationurlresponseok in the applicationdetail table.
	 */
	protected String applicationurlresponseok;

	/** 
	 * This attribute maps to the column applicationurlresponseko in the applicationdetail table.
	 */
	protected String applicationurlresponseko;

	/** 
	 * This attribute maps to the column tipocommissione in the applicationdetail table.
	 */
	protected String tipocommissione;

	/** 
	 * This attribute maps to the column valorecommissionemin in the applicationdetail table.
	 */
	protected float valorecommissionemin;

	/** 
	 * This attribute maps to the column sogliada in the applicationdetail table.
	 */
	protected float sogliada;

	/** 
	 * This attribute represents whether the primitive attribute sogliada is null.
	 */
	protected boolean sogliadaNull = true;

	/** 
	 * This attribute maps to the column sogliaa in the applicationdetail table.
	 */
	protected float sogliaa;

	/** 
	 * This attribute represents whether the primitive attribute sogliaa is null.
	 */
	protected boolean sogliaaNull = true;

	/** 
	 * This attribute maps to the column valorecommissionemax in the applicationdetail table.
	 */
	protected float valorecommissionemax;

	/** 
	 * This attribute represents whether the primitive attribute valorecommissionemax is null.
	 */
	protected boolean valorecommissionemaxNull = true;

	/** 
	 * This attribute maps to the column enabled in the applicationdetail table.
	 */
	protected String enabled;

	/** 
	 * This attribute maps to the column pgcontabcode in the applicationdetail table.
	 */
	protected long pgcontabcode;

	/** 
	 * This attribute represents whether the primitive attribute pgcontabcode is null.
	 */
	protected boolean pgcontabcodeNull = true;

	/** 
	 * This attribute maps to the column applicationurlback in the applicationdetail table.
	 */
	protected String applicationurlback;

	/** 
	 * This attribute maps to the column mac_avvio in the applicationdetail table.
	 */
	protected String macAvvio;

	/** 
	 * This attribute maps to the column mac_esito in the applicationdetail table.
	 */
	protected String macEsito;

	/** 
	 * This attribute maps to the column codiceistat in the applicationdetail table.
	 */
	protected String codiceistat;

	/** 
	 * This attribute maps to the column tipobollettinoposte in the applicationdetail table.
	 */
	protected String tipobollettinoposte;

	/** 
	 * This attribute maps to the column contocorrenteposte in the applicationdetail table.
	 */
	protected String contocorrenteposte;

	/** 
	 * This attribute maps to the column tipodocumentoposte in the applicationdetail table.
	 */
	protected String tipodocumentoposte;

	/** 
	 * This attribute maps to the column mail2buyerok in the applicationdetail table.
	 */
	protected String mail2buyerok;

	/** 
	 * This attribute maps to the column mail2buyerko in the applicationdetail table.
	 */
	protected String mail2buyerko;

	/** 
	 * This attribute maps to the column mail2esercko in the applicationdetail table.
	 */
	protected String mail2esercko;

	/** 
	 * This attribute maps to the column mail2esercok in the applicationdetail table.
	 */
	protected String mail2esercok;

	/** 
	 * This attribute maps to the column mail2sysko in the applicationdetail table.
	 */
	protected String mail2sysko;

	/** 
	 * This attribute maps to the column mail2sysok in the applicationdetail table.
	 */
	protected String mail2sysok;

	/** 
	 * This attribute maps to the column applicationurlcancel in the applicationdetail table.
	 */
	protected String applicationurlcancel;

	/** 
	 * This attribute maps to the column applicationurlerror in the applicationdetail table.
	 */
	protected String applicationurlerror;

	
	protected Boolean rrAbilitata;

	/**
	 * Method 'Applicationdetail'
	 * 
	 */
	public ApplicationDetail()
	{
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
	 * Method 'getPaymentmodeid'
	 * 
	 * @return String
	 */
	public String getPaymentmodeid()
	{
		return paymentmodeid;
	}

	/**
	 * Method 'setPaymentmodeid'
	 * 
	 * @param paymentmodeid
	 */
	public void setPaymentmodeid(String paymentmodeid)
	{
		this.paymentmodeid = paymentmodeid;
	}

	/**
	 * Method 'getMerchantid'
	 * 
	 * @return String
	 */
	public String getMerchantid()
	{
		return merchantid;
	}

	/**
	 * Method 'setMerchantid'
	 * 
	 * @param merchantid
	 */
	public void setMerchantid(String merchantid)
	{
		this.merchantid = merchantid;
	}

	/**
	 * Method 'getMerchantidpassword'
	 * 
	 * @return String
	 */
	public String getMerchantidpassword()
	{
		return merchantidpassword;
	}

	/**
	 * Method 'setMerchantidpassword'
	 * 
	 * @param merchantidpassword
	 */
	public void setMerchantidpassword(String merchantidpassword)
	{
		this.merchantidpassword = merchantidpassword;
	}

	/**
	 * Method 'getPgactioncode'
	 * 
	 * @return long
	 */
	public long getPgactioncode()
	{
		return pgactioncode;
	}

	/**
	 * Method 'setPgactioncode'
	 * 
	 * @param pgactioncode
	 */
	public void setPgactioncode(long pgactioncode)
	{
		this.pgactioncode = pgactioncode;
		this.pgactioncodeNull = false;
	}

	/**
	 * Method 'setPgactioncodeNull'
	 * 
	 * @param value
	 */
	public void setPgactioncodeNull(boolean value)
	{
		this.pgactioncodeNull = value;
	}

	/**
	 * Method 'isPgactioncodeNull'
	 * 
	 * @return boolean
	 */
	public boolean isPgactioncodeNull()
	{
		return pgactioncodeNull;
	}

	/**
	 * Method 'getFlagreturlmdp'
	 * 
	 * @return String
	 */
	public String getFlagreturlmdp()
	{
		return flagreturlmdp;
	}

	/**
	 * Method 'setFlagreturlmdp'
	 * 
	 * @param flagreturlmdp
	 */
	public void setFlagreturlmdp(String flagreturlmdp)
	{
		this.flagreturlmdp = flagreturlmdp;
	}

	/**
	 * Method 'getApplicationurlresponseok'
	 * 
	 * @return String
	 */
	public String getApplicationurlresponseok()
	{
		return applicationurlresponseok;
	}

	/**
	 * Method 'setApplicationurlresponseok'
	 * 
	 * @param applicationurlresponseok
	 */
	public void setApplicationurlresponseok(String applicationurlresponseok)
	{
		this.applicationurlresponseok = applicationurlresponseok;
	}

	/**
	 * Method 'getApplicationurlresponseko'
	 * 
	 * @return String
	 */
	public String getApplicationurlresponseko()
	{
		return applicationurlresponseko;
	}

	/**
	 * Method 'setApplicationurlresponseko'
	 * 
	 * @param applicationurlresponseko
	 */
	public void setApplicationurlresponseko(String applicationurlresponseko)
	{
		this.applicationurlresponseko = applicationurlresponseko;
	}

	/**
	 * Method 'getTipocommissione'
	 * 
	 * @return String
	 */
	public String getTipocommissione()
	{
		return tipocommissione;
	}

	/**
	 * Method 'setTipocommissione'
	 * 
	 * @param tipocommissione
	 */
	public void setTipocommissione(String tipocommissione)
	{
		this.tipocommissione = tipocommissione;
	}

	/**
	 * Method 'getValorecommissionemin'
	 * 
	 * @return float
	 */
	public float getValorecommissionemin()
	{
		return valorecommissionemin;
	}

	/**
	 * Method 'setValorecommissionemin'
	 * 
	 * @param valorecommissionemin
	 */
	public void setValorecommissionemin(float valorecommissionemin)
	{
		this.valorecommissionemin = valorecommissionemin;
	}

	/**
	 * Method 'getSogliada'
	 * 
	 * @return float
	 */
	public float getSogliada()
	{
		return sogliada;
	}

	/**
	 * Method 'setSogliada'
	 * 
	 * @param sogliada
	 */
	public void setSogliada(float sogliada)
	{
		this.sogliada = sogliada;
		this.sogliadaNull = false;
	}

	/**
	 * Method 'setSogliadaNull'
	 * 
	 * @param value
	 */
	public void setSogliadaNull(boolean value)
	{
		this.sogliadaNull = value;
	}

	/**
	 * Method 'isSogliadaNull'
	 * 
	 * @return boolean
	 */
	public boolean isSogliadaNull()
	{
		return sogliadaNull;
	}

	/**
	 * Method 'getSogliaa'
	 * 
	 * @return float
	 */
	public float getSogliaa()
	{
		return sogliaa;
	}

	/**
	 * Method 'setSogliaa'
	 * 
	 * @param sogliaa
	 */
	public void setSogliaa(float sogliaa)
	{
		this.sogliaa = sogliaa;
		this.sogliaaNull = false;
	}

	/**
	 * Method 'setSogliaaNull'
	 * 
	 * @param value
	 */
	public void setSogliaaNull(boolean value)
	{
		this.sogliaaNull = value;
	}

	/**
	 * Method 'isSogliaaNull'
	 * 
	 * @return boolean
	 */
	public boolean isSogliaaNull()
	{
		return sogliaaNull;
	}

	/**
	 * Method 'getValorecommissionemax'
	 * 
	 * @return float
	 */
	public float getValorecommissionemax()
	{
		return valorecommissionemax;
	}

	/**
	 * Method 'setValorecommissionemax'
	 * 
	 * @param valorecommissionemax
	 */
	public void setValorecommissionemax(float valorecommissionemax)
	{
		this.valorecommissionemax = valorecommissionemax;
		this.valorecommissionemaxNull = false;
	}

	/**
	 * Method 'setValorecommissionemaxNull'
	 * 
	 * @param value
	 */
	public void setValorecommissionemaxNull(boolean value)
	{
		this.valorecommissionemaxNull = value;
	}

	/**
	 * Method 'isValorecommissionemaxNull'
	 * 
	 * @return boolean
	 */
	public boolean isValorecommissionemaxNull()
	{
		return valorecommissionemaxNull;
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
	 * Method 'getPgcontabcode'
	 * 
	 * @return long
	 */
	public long getPgcontabcode()
	{
		return pgcontabcode;
	}

	/**
	 * Method 'setPgcontabcode'
	 * 
	 * @param pgcontabcode
	 */
	public void setPgcontabcode(long pgcontabcode)
	{
		this.pgcontabcode = pgcontabcode;
		this.pgcontabcodeNull = false;
	}

	/**
	 * Method 'setPgcontabcodeNull'
	 * 
	 * @param value
	 */
	public void setPgcontabcodeNull(boolean value)
	{
		this.pgcontabcodeNull = value;
	}

	/**
	 * Method 'isPgcontabcodeNull'
	 * 
	 * @return boolean
	 */
	public boolean isPgcontabcodeNull()
	{
		return pgcontabcodeNull;
	}

	/**
	 * Method 'getApplicationurlback'
	 * 
	 * @return String
	 */
	public String getApplicationurlback()
	{
		return applicationurlback;
	}

	/**
	 * Method 'setApplicationurlback'
	 * 
	 * @param applicationurlback
	 */
	public void setApplicationurlback(String applicationurlback)
	{
		this.applicationurlback = applicationurlback;
	}

	/**
	 * Method 'getMacAvvio'
	 * 
	 * @return String
	 */
	public String getMacAvvio()
	{
		return macAvvio;
	}

	/**
	 * Method 'setMacAvvio'
	 * 
	 * @param macAvvio
	 */
	public void setMacAvvio(String macAvvio)
	{
		this.macAvvio = macAvvio;
	}

	/**
	 * Method 'getMacEsito'
	 * 
	 * @return String
	 */
	public String getMacEsito()
	{
		return macEsito;
	}

	/**
	 * Method 'setMacEsito'
	 * 
	 * @param macEsito
	 */
	public void setMacEsito(String macEsito)
	{
		this.macEsito = macEsito;
	}

	/**
	 * Method 'getCodiceistat'
	 * 
	 * @return String
	 */
	public String getCodiceistat()
	{
		return codiceistat;
	}

	/**
	 * Method 'setCodiceistat'
	 * 
	 * @param codiceistat
	 */
	public void setCodiceistat(String codiceistat)
	{
		this.codiceistat = codiceistat;
	}

	/**
	 * Method 'getTipobollettinoposte'
	 * 
	 * @return String
	 */
	public String getTipobollettinoposte()
	{
		return tipobollettinoposte;
	}

	/**
	 * Method 'setTipobollettinoposte'
	 * 
	 * @param tipobollettinoposte
	 */
	public void setTipobollettinoposte(String tipobollettinoposte)
	{
		this.tipobollettinoposte = tipobollettinoposte;
	}

	/**
	 * Method 'getContocorrenteposte'
	 * 
	 * @return String
	 */
	public String getContocorrenteposte()
	{
		return contocorrenteposte;
	}

	/**
	 * Method 'setContocorrenteposte'
	 * 
	 * @param contocorrenteposte
	 */
	public void setContocorrenteposte(String contocorrenteposte)
	{
		this.contocorrenteposte = contocorrenteposte;
	}

	/**
	 * Method 'getTipodocumentoposte'
	 * 
	 * @return String
	 */
	public String getTipodocumentoposte()
	{
		return tipodocumentoposte;
	}

	/**
	 * Method 'setTipodocumentoposte'
	 * 
	 * @param tipodocumentoposte
	 */
	public void setTipodocumentoposte(String tipodocumentoposte)
	{
		this.tipodocumentoposte = tipodocumentoposte;
	}

	/**
	 * Method 'getMail2buyerok'
	 * 
	 * @return String
	 */
	public String getMail2buyerok()
	{
		return mail2buyerok;
	}

	/**
	 * Method 'setMail2buyerok'
	 * 
	 * @param mail2buyerok
	 */
	public void setMail2buyerok(String mail2buyerok)
	{
		this.mail2buyerok = mail2buyerok;
	}

	/**
	 * Method 'getMail2buyerko'
	 * 
	 * @return String
	 */
	public String getMail2buyerko()
	{
		return mail2buyerko;
	}

	/**
	 * Method 'setMail2buyerko'
	 * 
	 * @param mail2buyerko
	 */
	public void setMail2buyerko(String mail2buyerko)
	{
		this.mail2buyerko = mail2buyerko;
	}

	/**
	 * Method 'getMail2esercko'
	 * 
	 * @return String
	 */
	public String getMail2esercko()
	{
		return mail2esercko;
	}

	/**
	 * Method 'setMail2esercko'
	 * 
	 * @param mail2esercko
	 */
	public void setMail2esercko(String mail2esercko)
	{
		this.mail2esercko = mail2esercko;
	}

	/**
	 * Method 'getMail2esercok'
	 * 
	 * @return String
	 */
	public String getMail2esercok()
	{
		return mail2esercok;
	}

	/**
	 * Method 'setMail2esercok'
	 * 
	 * @param mail2esercok
	 */
	public void setMail2esercok(String mail2esercok)
	{
		this.mail2esercok = mail2esercok;
	}

	/**
	 * Method 'getMail2sysko'
	 * 
	 * @return String
	 */
	public String getMail2sysko()
	{
		return mail2sysko;
	}

	/**
	 * Method 'setMail2sysko'
	 * 
	 * @param mail2sysko
	 */
	public void setMail2sysko(String mail2sysko)
	{
		this.mail2sysko = mail2sysko;
	}

	/**
	 * Method 'getMail2sysok'
	 * 
	 * @return String
	 */
	public String getMail2sysok()
	{
		return mail2sysok;
	}

	/**
	 * Method 'setMail2sysok'
	 * 
	 * @param mail2sysok
	 */
	public void setMail2sysok(String mail2sysok)
	{
		this.mail2sysok = mail2sysok;
	}

	/**
	 * Method 'getApplicationurlcancel'
	 * 
	 * @return String
	 */
	public String getApplicationurlcancel()
	{
		return applicationurlcancel;
	}

	/**
	 * Method 'setApplicationurlcancel'
	 * 
	 * @param applicationurlcancel
	 */
	public void setApplicationurlcancel(String applicationurlcancel)
	{
		this.applicationurlcancel = applicationurlcancel;
	}

	/**
	 * Method 'getApplicationurlerror'
	 * 
	 * @return String
	 */
	public String getApplicationurlerror()
	{
		return applicationurlerror;
	}

	/**
	 * Method 'setApplicationurlerror'
	 * 
	 * @param applicationurlerror
	 */
	public void setApplicationurlerror(String applicationurlerror)
	{
		this.applicationurlerror = applicationurlerror;
	}

	/**
     * Method 'getRrAbilitata'
     * 
     */
    public Boolean getRrAbilitata () {
        return rrAbilitata;
    }

    /**
     * Method 'setRrAbilitata'
     * 
     * @param rrAbilitata
     */
    public void setRrAbilitata ( Boolean rrAbilitata ) {
        this.rrAbilitata = rrAbilitata;
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
		
		if (!(_other instanceof ApplicationDetail)) {
			return false;
		}
		
		final ApplicationDetail _cast = (ApplicationDetail) _other;
		if (applicationid == null ? _cast.applicationid != applicationid : !applicationid.equals( _cast.applicationid )) {
			return false;
		}
		
		if (gatewayid == null ? _cast.gatewayid != gatewayid : !gatewayid.equals( _cast.gatewayid )) {
			return false;
		}
		
		if (paymentmodeid == null ? _cast.paymentmodeid != paymentmodeid : !paymentmodeid.equals( _cast.paymentmodeid )) {
			return false;
		}
		
		if (merchantid == null ? _cast.merchantid != merchantid : !merchantid.equals( _cast.merchantid )) {
			return false;
		}
		
		if (merchantidpassword == null ? _cast.merchantidpassword != merchantidpassword : !merchantidpassword.equals( _cast.merchantidpassword )) {
			return false;
		}
		
		if (pgactioncode != _cast.pgactioncode) {
			return false;
		}
		
		if (pgactioncodeNull != _cast.pgactioncodeNull) {
			return false;
		}
		
		if (flagreturlmdp == null ? _cast.flagreturlmdp != flagreturlmdp : !flagreturlmdp.equals( _cast.flagreturlmdp )) {
			return false;
		}
		
		if (applicationurlresponseok == null ? _cast.applicationurlresponseok != applicationurlresponseok : !applicationurlresponseok.equals( _cast.applicationurlresponseok )) {
			return false;
		}
		
		if (applicationurlresponseko == null ? _cast.applicationurlresponseko != applicationurlresponseko : !applicationurlresponseko.equals( _cast.applicationurlresponseko )) {
			return false;
		}
		
		if (tipocommissione == null ? _cast.tipocommissione != tipocommissione : !tipocommissione.equals( _cast.tipocommissione )) {
			return false;
		}
		
		if (valorecommissionemin != _cast.valorecommissionemin) {
			return false;
		}
		
		if (sogliada != _cast.sogliada) {
			return false;
		}
		
		if (sogliadaNull != _cast.sogliadaNull) {
			return false;
		}
		
		if (sogliaa != _cast.sogliaa) {
			return false;
		}
		
		if (sogliaaNull != _cast.sogliaaNull) {
			return false;
		}
		
		if (valorecommissionemax != _cast.valorecommissionemax) {
			return false;
		}
		
		if (valorecommissionemaxNull != _cast.valorecommissionemaxNull) {
			return false;
		}
		
		if (enabled == null ? _cast.enabled != enabled : !enabled.equals( _cast.enabled )) {
			return false;
		}
		
		if (pgcontabcode != _cast.pgcontabcode) {
			return false;
		}
		
		if (pgcontabcodeNull != _cast.pgcontabcodeNull) {
			return false;
		}
		
		if (applicationurlback == null ? _cast.applicationurlback != applicationurlback : !applicationurlback.equals( _cast.applicationurlback )) {
			return false;
		}
		
		if (macAvvio == null ? _cast.macAvvio != macAvvio : !macAvvio.equals( _cast.macAvvio )) {
			return false;
		}
		
		if (macEsito == null ? _cast.macEsito != macEsito : !macEsito.equals( _cast.macEsito )) {
			return false;
		}
		
		if (codiceistat == null ? _cast.codiceistat != codiceistat : !codiceistat.equals( _cast.codiceistat )) {
			return false;
		}
		
		if (tipobollettinoposte == null ? _cast.tipobollettinoposte != tipobollettinoposte : !tipobollettinoposte.equals( _cast.tipobollettinoposte )) {
			return false;
		}
		
		if (contocorrenteposte == null ? _cast.contocorrenteposte != contocorrenteposte : !contocorrenteposte.equals( _cast.contocorrenteposte )) {
			return false;
		}
		
		if (tipodocumentoposte == null ? _cast.tipodocumentoposte != tipodocumentoposte : !tipodocumentoposte.equals( _cast.tipodocumentoposte )) {
			return false;
		}
		
		if (mail2buyerok == null ? _cast.mail2buyerok != mail2buyerok : !mail2buyerok.equals( _cast.mail2buyerok )) {
			return false;
		}
		
		if (mail2buyerko == null ? _cast.mail2buyerko != mail2buyerko : !mail2buyerko.equals( _cast.mail2buyerko )) {
			return false;
		}
		
		if (mail2esercko == null ? _cast.mail2esercko != mail2esercko : !mail2esercko.equals( _cast.mail2esercko )) {
			return false;
		}
		
		if (mail2esercok == null ? _cast.mail2esercok != mail2esercok : !mail2esercok.equals( _cast.mail2esercok )) {
			return false;
		}
		
		if (mail2sysko == null ? _cast.mail2sysko != mail2sysko : !mail2sysko.equals( _cast.mail2sysko )) {
			return false;
		}
		
		if (mail2sysok == null ? _cast.mail2sysok != mail2sysok : !mail2sysok.equals( _cast.mail2sysok )) {
			return false;
		}
		
		if (applicationurlcancel == null ? _cast.applicationurlcancel != applicationurlcancel : !applicationurlcancel.equals( _cast.applicationurlcancel )) {
			return false;
		}
		
		if (applicationurlerror == null ? _cast.applicationurlerror != applicationurlerror : !applicationurlerror.equals( _cast.applicationurlerror )) {
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
		if (applicationid != null) {
			_hashCode = 29 * _hashCode + applicationid.hashCode();
		}
		
		if (gatewayid != null) {
			_hashCode = 29 * _hashCode + gatewayid.hashCode();
		}
		
		if (paymentmodeid != null) {
			_hashCode = 29 * _hashCode + paymentmodeid.hashCode();
		}
		
		if (merchantid != null) {
			_hashCode = 29 * _hashCode + merchantid.hashCode();
		}
		
		if (merchantidpassword != null) {
			_hashCode = 29 * _hashCode + merchantidpassword.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (pgactioncode ^ (pgactioncode >>> 32));
		_hashCode = 29 * _hashCode + (pgactioncodeNull ? 1 : 0);
		if (flagreturlmdp != null) {
			_hashCode = 29 * _hashCode + flagreturlmdp.hashCode();
		}
		
		if (applicationurlresponseok != null) {
			_hashCode = 29 * _hashCode + applicationurlresponseok.hashCode();
		}
		
		if (applicationurlresponseko != null) {
			_hashCode = 29 * _hashCode + applicationurlresponseko.hashCode();
		}
		
		if (tipocommissione != null) {
			_hashCode = 29 * _hashCode + tipocommissione.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(valorecommissionemin);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(sogliada);
		_hashCode = 29 * _hashCode + (sogliadaNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(sogliaa);
		_hashCode = 29 * _hashCode + (sogliaaNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(valorecommissionemax);
		_hashCode = 29 * _hashCode + (valorecommissionemaxNull ? 1 : 0);
		if (enabled != null) {
			_hashCode = 29 * _hashCode + enabled.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (pgcontabcode ^ (pgcontabcode >>> 32));
		_hashCode = 29 * _hashCode + (pgcontabcodeNull ? 1 : 0);
		if (applicationurlback != null) {
			_hashCode = 29 * _hashCode + applicationurlback.hashCode();
		}
		
		if (macAvvio != null) {
			_hashCode = 29 * _hashCode + macAvvio.hashCode();
		}
		
		if (macEsito != null) {
			_hashCode = 29 * _hashCode + macEsito.hashCode();
		}
		
		if (codiceistat != null) {
			_hashCode = 29 * _hashCode + codiceistat.hashCode();
		}
		
		if (tipobollettinoposte != null) {
			_hashCode = 29 * _hashCode + tipobollettinoposte.hashCode();
		}
		
		if (contocorrenteposte != null) {
			_hashCode = 29 * _hashCode + contocorrenteposte.hashCode();
		}
		
		if (tipodocumentoposte != null) {
			_hashCode = 29 * _hashCode + tipodocumentoposte.hashCode();
		}
		
		if (mail2buyerok != null) {
			_hashCode = 29 * _hashCode + mail2buyerok.hashCode();
		}
		
		if (mail2buyerko != null) {
			_hashCode = 29 * _hashCode + mail2buyerko.hashCode();
		}
		
		if (mail2esercko != null) {
			_hashCode = 29 * _hashCode + mail2esercko.hashCode();
		}
		
		if (mail2esercok != null) {
			_hashCode = 29 * _hashCode + mail2esercok.hashCode();
		}
		
		if (mail2sysko != null) {
			_hashCode = 29 * _hashCode + mail2sysko.hashCode();
		}
		
		if (mail2sysok != null) {
			_hashCode = 29 * _hashCode + mail2sysok.hashCode();
		}
		
		if (applicationurlcancel != null) {
			_hashCode = 29 * _hashCode + applicationurlcancel.hashCode();
		}
		
		if (applicationurlerror != null) {
			_hashCode = 29 * _hashCode + applicationurlerror.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return ApplicationdetailPk
	 */
	public ApplicationDetailPk createPk()
	{
		return new ApplicationDetailPk(applicationid, gatewayid, paymentmodeid);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Applicationdetail: " );
		ret.append( "applicationid=" + applicationid );
		ret.append( ", gatewayid=" + gatewayid );
		ret.append( ", paymentmodeid=" + paymentmodeid );
		ret.append( ", merchantid=" + merchantid );
		ret.append( ", merchantidpassword=" + merchantidpassword );
		ret.append( ", pgactioncode=" + pgactioncode );
		ret.append( ", flagreturlmdp=" + flagreturlmdp );
		ret.append( ", applicationurlresponseok=" + applicationurlresponseok );
		ret.append( ", applicationurlresponseko=" + applicationurlresponseko );
		ret.append( ", tipocommissione=" + tipocommissione );
		ret.append( ", valorecommissionemin=" + valorecommissionemin );
		ret.append( ", sogliada=" + sogliada );
		ret.append( ", sogliaa=" + sogliaa );
		ret.append( ", valorecommissionemax=" + valorecommissionemax );
		ret.append( ", enabled=" + enabled );
		ret.append( ", pgcontabcode=" + pgcontabcode );
		ret.append( ", applicationurlback=" + applicationurlback );
		ret.append( ", macAvvio=" + macAvvio );
		ret.append( ", macEsito=" + macEsito );
		ret.append( ", codiceistat=" + codiceistat );
		ret.append( ", tipobollettinoposte=" + tipobollettinoposte );
		ret.append( ", contocorrenteposte=" + contocorrenteposte );
		ret.append( ", tipodocumentoposte=" + tipodocumentoposte );
		ret.append( ", mail2buyerok=" + mail2buyerok );
		ret.append( ", mail2buyerko=" + mail2buyerko );
		ret.append( ", mail2esercko=" + mail2esercko );
		ret.append( ", mail2esercok=" + mail2esercok );
		ret.append( ", mail2sysko=" + mail2sysko );
		ret.append( ", mail2sysok=" + mail2sysok );
		ret.append( ", applicationurlcancel=" + applicationurlcancel );
		ret.append( ", applicationurlerror=" + applicationurlerror );
		ret.append( ", rrabilitata=" + rrAbilitata );
		return ret.toString();
	}

}
