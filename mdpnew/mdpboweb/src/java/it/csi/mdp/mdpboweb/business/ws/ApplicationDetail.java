/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class ApplicationDetail  implements java.io.Serializable {
    private java.lang.String applicationid;

    private java.lang.String applicationurlback;

    private java.lang.String applicationurlcancel;

    private java.lang.String applicationurlerror;

    private java.lang.String applicationurlresponseko;

    private java.lang.String applicationurlresponseok;

    private java.lang.String codiceistat;

    private java.lang.String contocorrenteposte;

    private java.lang.String enabled;

    private java.lang.String flagreturlmdp;

    private java.lang.String gatewayid;

    private java.lang.String macAvvio;

    private java.lang.String macEsito;

    private java.lang.String mail2Buyerko;

    private java.lang.String mail2Buyerok;

    private java.lang.String mail2Esercko;

    private java.lang.String mail2Esercok;

    private java.lang.String mail2Sysko;

    private java.lang.String mail2Sysok;

    private java.lang.String merchantid;

    private java.lang.String merchantidpassword;

    private java.lang.String paymentmodeid;

    private long pgactioncode;

    private boolean pgactioncodeNull;

    private long pgcontabcode;

    private boolean pgcontabcodeNull;

    private float sogliaa;

    private boolean sogliaaNull;

    private float sogliada;

    private boolean sogliadaNull;

    private java.lang.String tipobollettinoposte;

    private java.lang.String tipocommissione;

    private java.lang.String tipodocumentoposte;

    private float valorecommissionemax;

    private boolean valorecommissionemaxNull;

    private float valorecommissionemin;

    public ApplicationDetail() {
    }

    public ApplicationDetail(
           java.lang.String applicationid,
           java.lang.String applicationurlback,
           java.lang.String applicationurlcancel,
           java.lang.String applicationurlerror,
           java.lang.String applicationurlresponseko,
           java.lang.String applicationurlresponseok,
           java.lang.String codiceistat,
           java.lang.String contocorrenteposte,
           java.lang.String enabled,
           java.lang.String flagreturlmdp,
           java.lang.String gatewayid,
           java.lang.String macAvvio,
           java.lang.String macEsito,
           java.lang.String mail2Buyerko,
           java.lang.String mail2Buyerok,
           java.lang.String mail2Esercko,
           java.lang.String mail2Esercok,
           java.lang.String mail2Sysko,
           java.lang.String mail2Sysok,
           java.lang.String merchantid,
           java.lang.String merchantidpassword,
           java.lang.String paymentmodeid,
           long pgactioncode,
           boolean pgactioncodeNull,
           long pgcontabcode,
           boolean pgcontabcodeNull,
           float sogliaa,
           boolean sogliaaNull,
           float sogliada,
           boolean sogliadaNull,
           java.lang.String tipobollettinoposte,
           java.lang.String tipocommissione,
           java.lang.String tipodocumentoposte,
           float valorecommissionemax,
           boolean valorecommissionemaxNull,
           float valorecommissionemin) {
           this.applicationid = applicationid;
           this.applicationurlback = applicationurlback;
           this.applicationurlcancel = applicationurlcancel;
           this.applicationurlerror = applicationurlerror;
           this.applicationurlresponseko = applicationurlresponseko;
           this.applicationurlresponseok = applicationurlresponseok;
           this.codiceistat = codiceistat;
           this.contocorrenteposte = contocorrenteposte;
           this.enabled = enabled;
           this.flagreturlmdp = flagreturlmdp;
           this.gatewayid = gatewayid;
           this.macAvvio = macAvvio;
           this.macEsito = macEsito;
           this.mail2Buyerko = mail2Buyerko;
           this.mail2Buyerok = mail2Buyerok;
           this.mail2Esercko = mail2Esercko;
           this.mail2Esercok = mail2Esercok;
           this.mail2Sysko = mail2Sysko;
           this.mail2Sysok = mail2Sysok;
           this.merchantid = merchantid;
           this.merchantidpassword = merchantidpassword;
           this.paymentmodeid = paymentmodeid;
           this.pgactioncode = pgactioncode;
           this.pgactioncodeNull = pgactioncodeNull;
           this.pgcontabcode = pgcontabcode;
           this.pgcontabcodeNull = pgcontabcodeNull;
           this.sogliaa = sogliaa;
           this.sogliaaNull = sogliaaNull;
           this.sogliada = sogliada;
           this.sogliadaNull = sogliadaNull;
           this.tipobollettinoposte = tipobollettinoposte;
           this.tipocommissione = tipocommissione;
           this.tipodocumentoposte = tipodocumentoposte;
           this.valorecommissionemax = valorecommissionemax;
           this.valorecommissionemaxNull = valorecommissionemaxNull;
           this.valorecommissionemin = valorecommissionemin;
    }


    /**
     * Gets the applicationid value for this ApplicationDetail.
     * 
     * @return applicationid
     */
    public java.lang.String getApplicationid() {
        return applicationid;
    }


    /**
     * Sets the applicationid value for this ApplicationDetail.
     * 
     * @param applicationid
     */
    public void setApplicationid(java.lang.String applicationid) {
        this.applicationid = applicationid;
    }


    /**
     * Gets the applicationurlback value for this ApplicationDetail.
     * 
     * @return applicationurlback
     */
    public java.lang.String getApplicationurlback() {
        return applicationurlback;
    }


    /**
     * Sets the applicationurlback value for this ApplicationDetail.
     * 
     * @param applicationurlback
     */
    public void setApplicationurlback(java.lang.String applicationurlback) {
        this.applicationurlback = applicationurlback;
    }


    /**
     * Gets the applicationurlcancel value for this ApplicationDetail.
     * 
     * @return applicationurlcancel
     */
    public java.lang.String getApplicationurlcancel() {
        return applicationurlcancel;
    }


    /**
     * Sets the applicationurlcancel value for this ApplicationDetail.
     * 
     * @param applicationurlcancel
     */
    public void setApplicationurlcancel(java.lang.String applicationurlcancel) {
        this.applicationurlcancel = applicationurlcancel;
    }


    /**
     * Gets the applicationurlerror value for this ApplicationDetail.
     * 
     * @return applicationurlerror
     */
    public java.lang.String getApplicationurlerror() {
        return applicationurlerror;
    }


    /**
     * Sets the applicationurlerror value for this ApplicationDetail.
     * 
     * @param applicationurlerror
     */
    public void setApplicationurlerror(java.lang.String applicationurlerror) {
        this.applicationurlerror = applicationurlerror;
    }


    /**
     * Gets the applicationurlresponseko value for this ApplicationDetail.
     * 
     * @return applicationurlresponseko
     */
    public java.lang.String getApplicationurlresponseko() {
        return applicationurlresponseko;
    }


    /**
     * Sets the applicationurlresponseko value for this ApplicationDetail.
     * 
     * @param applicationurlresponseko
     */
    public void setApplicationurlresponseko(java.lang.String applicationurlresponseko) {
        this.applicationurlresponseko = applicationurlresponseko;
    }


    /**
     * Gets the applicationurlresponseok value for this ApplicationDetail.
     * 
     * @return applicationurlresponseok
     */
    public java.lang.String getApplicationurlresponseok() {
        return applicationurlresponseok;
    }


    /**
     * Sets the applicationurlresponseok value for this ApplicationDetail.
     * 
     * @param applicationurlresponseok
     */
    public void setApplicationurlresponseok(java.lang.String applicationurlresponseok) {
        this.applicationurlresponseok = applicationurlresponseok;
    }


    /**
     * Gets the codiceistat value for this ApplicationDetail.
     * 
     * @return codiceistat
     */
    public java.lang.String getCodiceistat() {
        return codiceistat;
    }


    /**
     * Sets the codiceistat value for this ApplicationDetail.
     * 
     * @param codiceistat
     */
    public void setCodiceistat(java.lang.String codiceistat) {
        this.codiceistat = codiceistat;
    }


    /**
     * Gets the contocorrenteposte value for this ApplicationDetail.
     * 
     * @return contocorrenteposte
     */
    public java.lang.String getContocorrenteposte() {
        return contocorrenteposte;
    }


    /**
     * Sets the contocorrenteposte value for this ApplicationDetail.
     * 
     * @param contocorrenteposte
     */
    public void setContocorrenteposte(java.lang.String contocorrenteposte) {
        this.contocorrenteposte = contocorrenteposte;
    }


    /**
     * Gets the enabled value for this ApplicationDetail.
     * 
     * @return enabled
     */
    public java.lang.String getEnabled() {
        return enabled;
    }


    /**
     * Sets the enabled value for this ApplicationDetail.
     * 
     * @param enabled
     */
    public void setEnabled(java.lang.String enabled) {
        this.enabled = enabled;
    }


    /**
     * Gets the flagreturlmdp value for this ApplicationDetail.
     * 
     * @return flagreturlmdp
     */
    public java.lang.String getFlagreturlmdp() {
        return flagreturlmdp;
    }


    /**
     * Sets the flagreturlmdp value for this ApplicationDetail.
     * 
     * @param flagreturlmdp
     */
    public void setFlagreturlmdp(java.lang.String flagreturlmdp) {
        this.flagreturlmdp = flagreturlmdp;
    }


    /**
     * Gets the gatewayid value for this ApplicationDetail.
     * 
     * @return gatewayid
     */
    public java.lang.String getGatewayid() {
        return gatewayid;
    }


    /**
     * Sets the gatewayid value for this ApplicationDetail.
     * 
     * @param gatewayid
     */
    public void setGatewayid(java.lang.String gatewayid) {
        this.gatewayid = gatewayid;
    }


    /**
     * Gets the macAvvio value for this ApplicationDetail.
     * 
     * @return macAvvio
     */
    public java.lang.String getMacAvvio() {
        return macAvvio;
    }


    /**
     * Sets the macAvvio value for this ApplicationDetail.
     * 
     * @param macAvvio
     */
    public void setMacAvvio(java.lang.String macAvvio) {
        this.macAvvio = macAvvio;
    }


    /**
     * Gets the macEsito value for this ApplicationDetail.
     * 
     * @return macEsito
     */
    public java.lang.String getMacEsito() {
        return macEsito;
    }


    /**
     * Sets the macEsito value for this ApplicationDetail.
     * 
     * @param macEsito
     */
    public void setMacEsito(java.lang.String macEsito) {
        this.macEsito = macEsito;
    }


    /**
     * Gets the mail2Buyerko value for this ApplicationDetail.
     * 
     * @return mail2Buyerko
     */
    public java.lang.String getMail2Buyerko() {
        return mail2Buyerko;
    }


    /**
     * Sets the mail2Buyerko value for this ApplicationDetail.
     * 
     * @param mail2Buyerko
     */
    public void setMail2Buyerko(java.lang.String mail2Buyerko) {
        this.mail2Buyerko = mail2Buyerko;
    }


    /**
     * Gets the mail2Buyerok value for this ApplicationDetail.
     * 
     * @return mail2Buyerok
     */
    public java.lang.String getMail2Buyerok() {
        return mail2Buyerok;
    }


    /**
     * Sets the mail2Buyerok value for this ApplicationDetail.
     * 
     * @param mail2Buyerok
     */
    public void setMail2Buyerok(java.lang.String mail2Buyerok) {
        this.mail2Buyerok = mail2Buyerok;
    }


    /**
     * Gets the mail2Esercko value for this ApplicationDetail.
     * 
     * @return mail2Esercko
     */
    public java.lang.String getMail2Esercko() {
        return mail2Esercko;
    }


    /**
     * Sets the mail2Esercko value for this ApplicationDetail.
     * 
     * @param mail2Esercko
     */
    public void setMail2Esercko(java.lang.String mail2Esercko) {
        this.mail2Esercko = mail2Esercko;
    }


    /**
     * Gets the mail2Esercok value for this ApplicationDetail.
     * 
     * @return mail2Esercok
     */
    public java.lang.String getMail2Esercok() {
        return mail2Esercok;
    }


    /**
     * Sets the mail2Esercok value for this ApplicationDetail.
     * 
     * @param mail2Esercok
     */
    public void setMail2Esercok(java.lang.String mail2Esercok) {
        this.mail2Esercok = mail2Esercok;
    }


    /**
     * Gets the mail2Sysko value for this ApplicationDetail.
     * 
     * @return mail2Sysko
     */
    public java.lang.String getMail2Sysko() {
        return mail2Sysko;
    }


    /**
     * Sets the mail2Sysko value for this ApplicationDetail.
     * 
     * @param mail2Sysko
     */
    public void setMail2Sysko(java.lang.String mail2Sysko) {
        this.mail2Sysko = mail2Sysko;
    }


    /**
     * Gets the mail2Sysok value for this ApplicationDetail.
     * 
     * @return mail2Sysok
     */
    public java.lang.String getMail2Sysok() {
        return mail2Sysok;
    }


    /**
     * Sets the mail2Sysok value for this ApplicationDetail.
     * 
     * @param mail2Sysok
     */
    public void setMail2Sysok(java.lang.String mail2Sysok) {
        this.mail2Sysok = mail2Sysok;
    }


    /**
     * Gets the merchantid value for this ApplicationDetail.
     * 
     * @return merchantid
     */
    public java.lang.String getMerchantid() {
        return merchantid;
    }


    /**
     * Sets the merchantid value for this ApplicationDetail.
     * 
     * @param merchantid
     */
    public void setMerchantid(java.lang.String merchantid) {
        this.merchantid = merchantid;
    }


    /**
     * Gets the merchantidpassword value for this ApplicationDetail.
     * 
     * @return merchantidpassword
     */
    public java.lang.String getMerchantidpassword() {
        return merchantidpassword;
    }


    /**
     * Sets the merchantidpassword value for this ApplicationDetail.
     * 
     * @param merchantidpassword
     */
    public void setMerchantidpassword(java.lang.String merchantidpassword) {
        this.merchantidpassword = merchantidpassword;
    }


    /**
     * Gets the paymentmodeid value for this ApplicationDetail.
     * 
     * @return paymentmodeid
     */
    public java.lang.String getPaymentmodeid() {
        return paymentmodeid;
    }


    /**
     * Sets the paymentmodeid value for this ApplicationDetail.
     * 
     * @param paymentmodeid
     */
    public void setPaymentmodeid(java.lang.String paymentmodeid) {
        this.paymentmodeid = paymentmodeid;
    }


    /**
     * Gets the pgactioncode value for this ApplicationDetail.
     * 
     * @return pgactioncode
     */
    public long getPgactioncode() {
        return pgactioncode;
    }


    /**
     * Sets the pgactioncode value for this ApplicationDetail.
     * 
     * @param pgactioncode
     */
    public void setPgactioncode(long pgactioncode) {
        this.pgactioncode = pgactioncode;
    }


    /**
     * Gets the pgactioncodeNull value for this ApplicationDetail.
     * 
     * @return pgactioncodeNull
     */
    public boolean isPgactioncodeNull() {
        return pgactioncodeNull;
    }


    /**
     * Sets the pgactioncodeNull value for this ApplicationDetail.
     * 
     * @param pgactioncodeNull
     */
    public void setPgactioncodeNull(boolean pgactioncodeNull) {
        this.pgactioncodeNull = pgactioncodeNull;
    }


    /**
     * Gets the pgcontabcode value for this ApplicationDetail.
     * 
     * @return pgcontabcode
     */
    public long getPgcontabcode() {
        return pgcontabcode;
    }


    /**
     * Sets the pgcontabcode value for this ApplicationDetail.
     * 
     * @param pgcontabcode
     */
    public void setPgcontabcode(long pgcontabcode) {
        this.pgcontabcode = pgcontabcode;
    }


    /**
     * Gets the pgcontabcodeNull value for this ApplicationDetail.
     * 
     * @return pgcontabcodeNull
     */
    public boolean isPgcontabcodeNull() {
        return pgcontabcodeNull;
    }


    /**
     * Sets the pgcontabcodeNull value for this ApplicationDetail.
     * 
     * @param pgcontabcodeNull
     */
    public void setPgcontabcodeNull(boolean pgcontabcodeNull) {
        this.pgcontabcodeNull = pgcontabcodeNull;
    }


    /**
     * Gets the sogliaa value for this ApplicationDetail.
     * 
     * @return sogliaa
     */
    public float getSogliaa() {
        return sogliaa;
    }


    /**
     * Sets the sogliaa value for this ApplicationDetail.
     * 
     * @param sogliaa
     */
    public void setSogliaa(float sogliaa) {
        this.sogliaa = sogliaa;
    }


    /**
     * Gets the sogliaaNull value for this ApplicationDetail.
     * 
     * @return sogliaaNull
     */
    public boolean isSogliaaNull() {
        return sogliaaNull;
    }


    /**
     * Sets the sogliaaNull value for this ApplicationDetail.
     * 
     * @param sogliaaNull
     */
    public void setSogliaaNull(boolean sogliaaNull) {
        this.sogliaaNull = sogliaaNull;
    }


    /**
     * Gets the sogliada value for this ApplicationDetail.
     * 
     * @return sogliada
     */
    public float getSogliada() {
        return sogliada;
    }


    /**
     * Sets the sogliada value for this ApplicationDetail.
     * 
     * @param sogliada
     */
    public void setSogliada(float sogliada) {
        this.sogliada = sogliada;
    }


    /**
     * Gets the sogliadaNull value for this ApplicationDetail.
     * 
     * @return sogliadaNull
     */
    public boolean isSogliadaNull() {
        return sogliadaNull;
    }


    /**
     * Sets the sogliadaNull value for this ApplicationDetail.
     * 
     * @param sogliadaNull
     */
    public void setSogliadaNull(boolean sogliadaNull) {
        this.sogliadaNull = sogliadaNull;
    }


    /**
     * Gets the tipobollettinoposte value for this ApplicationDetail.
     * 
     * @return tipobollettinoposte
     */
    public java.lang.String getTipobollettinoposte() {
        return tipobollettinoposte;
    }


    /**
     * Sets the tipobollettinoposte value for this ApplicationDetail.
     * 
     * @param tipobollettinoposte
     */
    public void setTipobollettinoposte(java.lang.String tipobollettinoposte) {
        this.tipobollettinoposte = tipobollettinoposte;
    }


    /**
     * Gets the tipocommissione value for this ApplicationDetail.
     * 
     * @return tipocommissione
     */
    public java.lang.String getTipocommissione() {
        return tipocommissione;
    }


    /**
     * Sets the tipocommissione value for this ApplicationDetail.
     * 
     * @param tipocommissione
     */
    public void setTipocommissione(java.lang.String tipocommissione) {
        this.tipocommissione = tipocommissione;
    }


    /**
     * Gets the tipodocumentoposte value for this ApplicationDetail.
     * 
     * @return tipodocumentoposte
     */
    public java.lang.String getTipodocumentoposte() {
        return tipodocumentoposte;
    }


    /**
     * Sets the tipodocumentoposte value for this ApplicationDetail.
     * 
     * @param tipodocumentoposte
     */
    public void setTipodocumentoposte(java.lang.String tipodocumentoposte) {
        this.tipodocumentoposte = tipodocumentoposte;
    }


    /**
     * Gets the valorecommissionemax value for this ApplicationDetail.
     * 
     * @return valorecommissionemax
     */
    public float getValorecommissionemax() {
        return valorecommissionemax;
    }


    /**
     * Sets the valorecommissionemax value for this ApplicationDetail.
     * 
     * @param valorecommissionemax
     */
    public void setValorecommissionemax(float valorecommissionemax) {
        this.valorecommissionemax = valorecommissionemax;
    }


    /**
     * Gets the valorecommissionemaxNull value for this ApplicationDetail.
     * 
     * @return valorecommissionemaxNull
     */
    public boolean isValorecommissionemaxNull() {
        return valorecommissionemaxNull;
    }


    /**
     * Sets the valorecommissionemaxNull value for this ApplicationDetail.
     * 
     * @param valorecommissionemaxNull
     */
    public void setValorecommissionemaxNull(boolean valorecommissionemaxNull) {
        this.valorecommissionemaxNull = valorecommissionemaxNull;
    }


    /**
     * Gets the valorecommissionemin value for this ApplicationDetail.
     * 
     * @return valorecommissionemin
     */
    public float getValorecommissionemin() {
        return valorecommissionemin;
    }


    /**
     * Sets the valorecommissionemin value for this ApplicationDetail.
     * 
     * @param valorecommissionemin
     */
    public void setValorecommissionemin(float valorecommissionemin) {
        this.valorecommissionemin = valorecommissionemin;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApplicationDetail)) return false;
        ApplicationDetail other = (ApplicationDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationid==null && other.getApplicationid()==null) || 
             (this.applicationid!=null &&
              this.applicationid.equals(other.getApplicationid()))) &&
            ((this.applicationurlback==null && other.getApplicationurlback()==null) || 
             (this.applicationurlback!=null &&
              this.applicationurlback.equals(other.getApplicationurlback()))) &&
            ((this.applicationurlcancel==null && other.getApplicationurlcancel()==null) || 
             (this.applicationurlcancel!=null &&
              this.applicationurlcancel.equals(other.getApplicationurlcancel()))) &&
            ((this.applicationurlerror==null && other.getApplicationurlerror()==null) || 
             (this.applicationurlerror!=null &&
              this.applicationurlerror.equals(other.getApplicationurlerror()))) &&
            ((this.applicationurlresponseko==null && other.getApplicationurlresponseko()==null) || 
             (this.applicationurlresponseko!=null &&
              this.applicationurlresponseko.equals(other.getApplicationurlresponseko()))) &&
            ((this.applicationurlresponseok==null && other.getApplicationurlresponseok()==null) || 
             (this.applicationurlresponseok!=null &&
              this.applicationurlresponseok.equals(other.getApplicationurlresponseok()))) &&
            ((this.codiceistat==null && other.getCodiceistat()==null) || 
             (this.codiceistat!=null &&
              this.codiceistat.equals(other.getCodiceistat()))) &&
            ((this.contocorrenteposte==null && other.getContocorrenteposte()==null) || 
             (this.contocorrenteposte!=null &&
              this.contocorrenteposte.equals(other.getContocorrenteposte()))) &&
            ((this.enabled==null && other.getEnabled()==null) || 
             (this.enabled!=null &&
              this.enabled.equals(other.getEnabled()))) &&
            ((this.flagreturlmdp==null && other.getFlagreturlmdp()==null) || 
             (this.flagreturlmdp!=null &&
              this.flagreturlmdp.equals(other.getFlagreturlmdp()))) &&
            ((this.gatewayid==null && other.getGatewayid()==null) || 
             (this.gatewayid!=null &&
              this.gatewayid.equals(other.getGatewayid()))) &&
            ((this.macAvvio==null && other.getMacAvvio()==null) || 
             (this.macAvvio!=null &&
              this.macAvvio.equals(other.getMacAvvio()))) &&
            ((this.macEsito==null && other.getMacEsito()==null) || 
             (this.macEsito!=null &&
              this.macEsito.equals(other.getMacEsito()))) &&
            ((this.mail2Buyerko==null && other.getMail2Buyerko()==null) || 
             (this.mail2Buyerko!=null &&
              this.mail2Buyerko.equals(other.getMail2Buyerko()))) &&
            ((this.mail2Buyerok==null && other.getMail2Buyerok()==null) || 
             (this.mail2Buyerok!=null &&
              this.mail2Buyerok.equals(other.getMail2Buyerok()))) &&
            ((this.mail2Esercko==null && other.getMail2Esercko()==null) || 
             (this.mail2Esercko!=null &&
              this.mail2Esercko.equals(other.getMail2Esercko()))) &&
            ((this.mail2Esercok==null && other.getMail2Esercok()==null) || 
             (this.mail2Esercok!=null &&
              this.mail2Esercok.equals(other.getMail2Esercok()))) &&
            ((this.mail2Sysko==null && other.getMail2Sysko()==null) || 
             (this.mail2Sysko!=null &&
              this.mail2Sysko.equals(other.getMail2Sysko()))) &&
            ((this.mail2Sysok==null && other.getMail2Sysok()==null) || 
             (this.mail2Sysok!=null &&
              this.mail2Sysok.equals(other.getMail2Sysok()))) &&
            ((this.merchantid==null && other.getMerchantid()==null) || 
             (this.merchantid!=null &&
              this.merchantid.equals(other.getMerchantid()))) &&
            ((this.merchantidpassword==null && other.getMerchantidpassword()==null) || 
             (this.merchantidpassword!=null &&
              this.merchantidpassword.equals(other.getMerchantidpassword()))) &&
            ((this.paymentmodeid==null && other.getPaymentmodeid()==null) || 
             (this.paymentmodeid!=null &&
              this.paymentmodeid.equals(other.getPaymentmodeid()))) &&
            this.pgactioncode == other.getPgactioncode() &&
            this.pgactioncodeNull == other.isPgactioncodeNull() &&
            this.pgcontabcode == other.getPgcontabcode() &&
            this.pgcontabcodeNull == other.isPgcontabcodeNull() &&
            this.sogliaa == other.getSogliaa() &&
            this.sogliaaNull == other.isSogliaaNull() &&
            this.sogliada == other.getSogliada() &&
            this.sogliadaNull == other.isSogliadaNull() &&
            ((this.tipobollettinoposte==null && other.getTipobollettinoposte()==null) || 
             (this.tipobollettinoposte!=null &&
              this.tipobollettinoposte.equals(other.getTipobollettinoposte()))) &&
            ((this.tipocommissione==null && other.getTipocommissione()==null) || 
             (this.tipocommissione!=null &&
              this.tipocommissione.equals(other.getTipocommissione()))) &&
            ((this.tipodocumentoposte==null && other.getTipodocumentoposte()==null) || 
             (this.tipodocumentoposte!=null &&
              this.tipodocumentoposte.equals(other.getTipodocumentoposte()))) &&
            this.valorecommissionemax == other.getValorecommissionemax() &&
            this.valorecommissionemaxNull == other.isValorecommissionemaxNull() &&
            this.valorecommissionemin == other.getValorecommissionemin();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getApplicationid() != null) {
            _hashCode += getApplicationid().hashCode();
        }
        if (getApplicationurlback() != null) {
            _hashCode += getApplicationurlback().hashCode();
        }
        if (getApplicationurlcancel() != null) {
            _hashCode += getApplicationurlcancel().hashCode();
        }
        if (getApplicationurlerror() != null) {
            _hashCode += getApplicationurlerror().hashCode();
        }
        if (getApplicationurlresponseko() != null) {
            _hashCode += getApplicationurlresponseko().hashCode();
        }
        if (getApplicationurlresponseok() != null) {
            _hashCode += getApplicationurlresponseok().hashCode();
        }
        if (getCodiceistat() != null) {
            _hashCode += getCodiceistat().hashCode();
        }
        if (getContocorrenteposte() != null) {
            _hashCode += getContocorrenteposte().hashCode();
        }
        if (getEnabled() != null) {
            _hashCode += getEnabled().hashCode();
        }
        if (getFlagreturlmdp() != null) {
            _hashCode += getFlagreturlmdp().hashCode();
        }
        if (getGatewayid() != null) {
            _hashCode += getGatewayid().hashCode();
        }
        if (getMacAvvio() != null) {
            _hashCode += getMacAvvio().hashCode();
        }
        if (getMacEsito() != null) {
            _hashCode += getMacEsito().hashCode();
        }
        if (getMail2Buyerko() != null) {
            _hashCode += getMail2Buyerko().hashCode();
        }
        if (getMail2Buyerok() != null) {
            _hashCode += getMail2Buyerok().hashCode();
        }
        if (getMail2Esercko() != null) {
            _hashCode += getMail2Esercko().hashCode();
        }
        if (getMail2Esercok() != null) {
            _hashCode += getMail2Esercok().hashCode();
        }
        if (getMail2Sysko() != null) {
            _hashCode += getMail2Sysko().hashCode();
        }
        if (getMail2Sysok() != null) {
            _hashCode += getMail2Sysok().hashCode();
        }
        if (getMerchantid() != null) {
            _hashCode += getMerchantid().hashCode();
        }
        if (getMerchantidpassword() != null) {
            _hashCode += getMerchantidpassword().hashCode();
        }
        if (getPaymentmodeid() != null) {
            _hashCode += getPaymentmodeid().hashCode();
        }
        _hashCode += new Long(getPgactioncode()).hashCode();
        _hashCode += (isPgactioncodeNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Long(getPgcontabcode()).hashCode();
        _hashCode += (isPgcontabcodeNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Float(getSogliaa()).hashCode();
        _hashCode += (isSogliaaNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Float(getSogliada()).hashCode();
        _hashCode += (isSogliadaNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTipobollettinoposte() != null) {
            _hashCode += getTipobollettinoposte().hashCode();
        }
        if (getTipocommissione() != null) {
            _hashCode += getTipocommissione().hashCode();
        }
        if (getTipodocumentoposte() != null) {
            _hashCode += getTipodocumentoposte().hashCode();
        }
        _hashCode += new Float(getValorecommissionemax()).hashCode();
        _hashCode += (isValorecommissionemaxNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Float(getValorecommissionemin()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApplicationDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationurlback");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationurlback"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationurlcancel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationurlcancel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationurlerror");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationurlerror"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationurlresponseko");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationurlresponseko"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationurlresponseok");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationurlresponseok"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceistat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceistat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contocorrenteposte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contocorrenteposte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enabled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagreturlmdp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagreturlmdp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("macAvvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "macAvvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("macEsito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "macEsito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail2Buyerko");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mail2buyerko"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail2Buyerok");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mail2buyerok"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail2Esercko");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mail2esercko"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail2Esercok");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mail2esercok"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail2Sysko");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mail2sysko"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail2Sysok");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mail2sysok"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "merchantid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantidpassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "merchantidpassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentmodeid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentmodeid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pgactioncode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pgactioncode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pgactioncodeNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pgactioncodeNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pgcontabcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pgcontabcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pgcontabcodeNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pgcontabcodeNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sogliaa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sogliaa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sogliaaNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sogliaaNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sogliada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sogliada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sogliadaNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sogliadaNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipobollettinoposte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipobollettinoposte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipocommissione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipocommissione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipodocumentoposte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipodocumentoposte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorecommissionemax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorecommissionemax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorecommissionemaxNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorecommissionemaxNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorecommissionemin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorecommissionemin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
