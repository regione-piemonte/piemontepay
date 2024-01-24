/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class MdpTApplicationDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    String applicationid;

    String gatewayid;

    String paymentmodeid;

    String merchantid;

    String merchantidpassword;

    BigDecimal pgactioncode;

    String flagreturlmdp;

    String applicationurlresponseok;

    String applicationurlresponseko;

    String tipocommissione;

    BigDecimal valorecommissionemin;

    BigDecimal sogliada;

    BigDecimal sogliaa;

    BigDecimal valorecommissionemax;

    String enabled;

    BigDecimal pgcontabcode;

    String applicationurlback;

    String mac_avvio;

    String mac_esito;

    String codiceistat;

    String tipobollettinoposte;

    String contocorrenteposte;

    String tipodocumentoposte;

    String mail2buyerok;

    String mail2buyerko;

    String mail2esercko;

    String mail2esercok;

    String mail2sysko;

    String mail2sysok;

    String applicationurlcancel;

    String applicationurlerror;

    public String getApplicationid () {
        return applicationid;
    }

    public void setApplicationid ( String applicationid ) {
        this.applicationid = applicationid;
    }

    public String getGatewayid () {
        return gatewayid;
    }

    public void setGatewayid ( String gatewayid ) {
        this.gatewayid = gatewayid;
    }

    public String getPaymentmodeid () {
        return paymentmodeid;
    }

    public void setPaymentmodeid ( String paymentmodeid ) {
        this.paymentmodeid = paymentmodeid;
    }

    public String getMerchantid () {
        return merchantid;
    }

    public void setMerchantid ( String merchantid ) {
        this.merchantid = merchantid;
    }

    public String getMerchantidpassword () {
        return merchantidpassword;
    }

    public void setMerchantidpassword ( String merchantidpassword ) {
        this.merchantidpassword = merchantidpassword;
    }

    public BigDecimal getPgactioncode () {
        return pgactioncode;
    }

    public void setPgactioncode ( BigDecimal pgactioncode ) {
        this.pgactioncode = pgactioncode;
    }

    public String getFlagreturlmdp () {
        return flagreturlmdp;
    }

    public void setFlagreturlmdp ( String flagreturlmdp ) {
        this.flagreturlmdp = flagreturlmdp;
    }

    public String getApplicationurlresponseok () {
        return applicationurlresponseok;
    }

    public void setApplicationurlresponseok ( String applicationurlresponseok ) {
        this.applicationurlresponseok = applicationurlresponseok;
    }

    public String getApplicationurlresponseko () {
        return applicationurlresponseko;
    }

    public void setApplicationurlresponseko ( String applicationurlresponseko ) {
        this.applicationurlresponseko = applicationurlresponseko;
    }

    public String getTipocommissione () {
        return tipocommissione;
    }

    public void setTipocommissione ( String tipocommissione ) {
        this.tipocommissione = tipocommissione;
    }

    public BigDecimal getValorecommissionemin () {
        return valorecommissionemin;
    }

    public void setValorecommissionemin ( BigDecimal valorecommissionemin ) {
        this.valorecommissionemin = valorecommissionemin;
    }

    public BigDecimal getSogliada () {
        return sogliada;
    }

    public void setSogliada ( BigDecimal sogliada ) {
        this.sogliada = sogliada;
    }

    public BigDecimal getSogliaa () {
        return sogliaa;
    }

    public void setSogliaa ( BigDecimal sogliaa ) {
        this.sogliaa = sogliaa;
    }

    public BigDecimal getValorecommissionemax () {
        return valorecommissionemax;
    }

    public void setValorecommissionemax ( BigDecimal valorecommissionemax ) {
        this.valorecommissionemax = valorecommissionemax;
    }

    public String getEnabled () {
        return enabled;
    }

    public void setEnabled ( String enabled ) {
        this.enabled = enabled;
    }

    public BigDecimal getPgcontabcode () {
        return pgcontabcode;
    }

    public void setPgcontabcode ( BigDecimal pgcontabcode ) {
        this.pgcontabcode = pgcontabcode;
    }

    public String getApplicationurlback () {
        return applicationurlback;
    }

    public void setApplicationurlback ( String applicationurlback ) {
        this.applicationurlback = applicationurlback;
    }

    public String getMac_avvio () {
        return mac_avvio;
    }

    public void setMac_avvio ( String mac_avvio ) {
        this.mac_avvio = mac_avvio;
    }

    public String getMac_esito () {
        return mac_esito;
    }

    public void setMac_esito ( String mac_esito ) {
        this.mac_esito = mac_esito;
    }

    public String getCodiceistat () {
        return codiceistat;
    }

    public void setCodiceistat ( String codiceistat ) {
        this.codiceistat = codiceistat;
    }

    public String getTipobollettinoposte () {
        return tipobollettinoposte;
    }

    public void setTipobollettinoposte ( String tipobollettinoposte ) {
        this.tipobollettinoposte = tipobollettinoposte;
    }

    public String getContocorrenteposte () {
        return contocorrenteposte;
    }

    public void setContocorrenteposte ( String contocorrenteposte ) {
        this.contocorrenteposte = contocorrenteposte;
    }

    public String getTipodocumentoposte () {
        return tipodocumentoposte;
    }

    public void setTipodocumentoposte ( String tipodocumentoposte ) {
        this.tipodocumentoposte = tipodocumentoposte;
    }

    public String getMail2buyerok () {
        return mail2buyerok;
    }

    public void setMail2buyerok ( String mail2buyerok ) {
        this.mail2buyerok = mail2buyerok;
    }

    public String getMail2buyerko () {
        return mail2buyerko;
    }

    public void setMail2buyerko ( String mail2buyerko ) {
        this.mail2buyerko = mail2buyerko;
    }

    public String getMail2esercko () {
        return mail2esercko;
    }

    public void setMail2esercko ( String mail2esercko ) {
        this.mail2esercko = mail2esercko;
    }

    public String getMail2esercok () {
        return mail2esercok;
    }

    public void setMail2esercok ( String mail2esercok ) {
        this.mail2esercok = mail2esercok;
    }

    public String getMail2sysko () {
        return mail2sysko;
    }

    public void setMail2sysko ( String mail2sysko ) {
        this.mail2sysko = mail2sysko;
    }

    public String getMail2sysok () {
        return mail2sysok;
    }

    public void setMail2sysok ( String mail2sysok ) {
        this.mail2sysok = mail2sysok;
    }

    public String getApplicationurlcancel () {
        return applicationurlcancel;
    }

    public void setApplicationurlcancel ( String applicationurlcancel ) {
        this.applicationurlcancel = applicationurlcancel;
    }

    public String getApplicationurlerror () {
        return applicationurlerror;
    }

    public void setApplicationurlerror ( String applicationurlerror ) {
        this.applicationurlerror = applicationurlerror;
    }

    @Override
    public String toString () {
        return "MdpTApplicationDetail [applicationid=" + applicationid + ", gatewayid=" + gatewayid + ", paymentmodeid=" + paymentmodeid + ", merchantid="
            + merchantid + ", merchantidpassword=" + merchantidpassword + ", pgactioncode=" + pgactioncode + ", flagreturlmdp=" + flagreturlmdp
            + ", applicationurlresponseok=" + applicationurlresponseok + ", applicationurlresponseko=" + applicationurlresponseko + ", tipocommissione="
            + tipocommissione + ", valorecommissionemin=" + valorecommissionemin + ", sogliada=" + sogliada + ", sogliaa=" + sogliaa + ", valorecommissionemax="
            + valorecommissionemax + ", enabled=" + enabled + ", pgcontabcode=" + pgcontabcode + ", applicationurlback=" + applicationurlback + ", mac_avvio="
            + mac_avvio + ", mac_esito=" + mac_esito + ", codiceistat=" + codiceistat + ", tipobollettinoposte=" + tipobollettinoposte + ", contocorrenteposte="
            + contocorrenteposte + ", tipodocumentoposte=" + tipodocumentoposte + ", mail2buyerok=" + mail2buyerok + ", mail2buyerko=" + mail2buyerko
            + ", mail2esercko=" + mail2esercko + ", mail2esercok=" + mail2esercok + ", mail2sysko=" + mail2sysko + ", mail2sysok=" + mail2sysok
            + ", applicationurlcancel=" + applicationurlcancel + ", applicationurlerror=" + applicationurlerror + "]";
    }

}
