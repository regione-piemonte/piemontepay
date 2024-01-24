/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Transazione  implements java.io.Serializable {
    private java.lang.Double amount;

    private java.lang.String applicationId;

    private java.lang.String authornumber;

    private java.lang.String basketId;

    private java.lang.String buyerEmail;

    private java.lang.String buyercodfisc;

    private java.lang.String buyername;

    private java.lang.String ccy;

    private java.util.Calendar changestatedate;

    private java.lang.String clientipaddress;

    private java.lang.Long codStato;

    private java.lang.Double commissioniApplicate;

    private boolean commissioniApplicateNull;

    private java.lang.String errcode;

    private java.util.Calendar finishDate;

    private java.lang.String gatewayId;

    private java.lang.String gatewaypaymodeid;

    private java.lang.String incassokoerrormessage;

    private java.util.Calendar initDate;

    private java.lang.String intestatariocc;

    private java.lang.String language;

    private java.lang.String merchantId;

    private java.lang.Long mscsorderid;

    private boolean mscsorderidNull;

    private java.lang.Long oldstate;

    private java.lang.String opernumber;

    private java.lang.String paymentid;

    private java.lang.String payurl;

    private java.lang.String pgresultcode;

    private java.lang.String providertimestamp;

    private java.lang.String rispcomp;

    private java.util.Calendar startDate;

    private java.lang.String transactionId;

    private java.lang.String userhaschange;

    public Transazione() {
    }

    public Transazione(
           java.lang.Double amount,
           java.lang.String applicationId,
           java.lang.String authornumber,
           java.lang.String basketId,
           java.lang.String buyerEmail,
           java.lang.String buyercodfisc,
           java.lang.String buyername,
           java.lang.String ccy,
           java.util.Calendar changestatedate,
           java.lang.String clientipaddress,
           java.lang.Long codStato,
           java.lang.Double commissioniApplicate,
           boolean commissioniApplicateNull,
           java.lang.String errcode,
           java.util.Calendar finishDate,
           java.lang.String gatewayId,
           java.lang.String gatewaypaymodeid,
           java.lang.String incassokoerrormessage,
           java.util.Calendar initDate,
           java.lang.String intestatariocc,
           java.lang.String language,
           java.lang.String merchantId,
           java.lang.Long mscsorderid,
           boolean mscsorderidNull,
           java.lang.Long oldstate,
           java.lang.String opernumber,
           java.lang.String paymentid,
           java.lang.String payurl,
           java.lang.String pgresultcode,
           java.lang.String providertimestamp,
           java.lang.String rispcomp,
           java.util.Calendar startDate,
           java.lang.String transactionId,
           java.lang.String userhaschange) {
           this.amount = amount;
           this.applicationId = applicationId;
           this.authornumber = authornumber;
           this.basketId = basketId;
           this.buyerEmail = buyerEmail;
           this.buyercodfisc = buyercodfisc;
           this.buyername = buyername;
           this.ccy = ccy;
           this.changestatedate = changestatedate;
           this.clientipaddress = clientipaddress;
           this.codStato = codStato;
           this.commissioniApplicate = commissioniApplicate;
           this.commissioniApplicateNull = commissioniApplicateNull;
           this.errcode = errcode;
           this.finishDate = finishDate;
           this.gatewayId = gatewayId;
           this.gatewaypaymodeid = gatewaypaymodeid;
           this.incassokoerrormessage = incassokoerrormessage;
           this.initDate = initDate;
           this.intestatariocc = intestatariocc;
           this.language = language;
           this.merchantId = merchantId;
           this.mscsorderid = mscsorderid;
           this.mscsorderidNull = mscsorderidNull;
           this.oldstate = oldstate;
           this.opernumber = opernumber;
           this.paymentid = paymentid;
           this.payurl = payurl;
           this.pgresultcode = pgresultcode;
           this.providertimestamp = providertimestamp;
           this.rispcomp = rispcomp;
           this.startDate = startDate;
           this.transactionId = transactionId;
           this.userhaschange = userhaschange;
    }


    /**
     * Gets the amount value for this Transazione.
     * 
     * @return amount
     */
    public java.lang.Double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this Transazione.
     * 
     * @param amount
     */
    public void setAmount(java.lang.Double amount) {
        this.amount = amount;
    }


    /**
     * Gets the applicationId value for this Transazione.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this Transazione.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the authornumber value for this Transazione.
     * 
     * @return authornumber
     */
    public java.lang.String getAuthornumber() {
        return authornumber;
    }


    /**
     * Sets the authornumber value for this Transazione.
     * 
     * @param authornumber
     */
    public void setAuthornumber(java.lang.String authornumber) {
        this.authornumber = authornumber;
    }


    /**
     * Gets the basketId value for this Transazione.
     * 
     * @return basketId
     */
    public java.lang.String getBasketId() {
        return basketId;
    }


    /**
     * Sets the basketId value for this Transazione.
     * 
     * @param basketId
     */
    public void setBasketId(java.lang.String basketId) {
        this.basketId = basketId;
    }


    /**
     * Gets the buyerEmail value for this Transazione.
     * 
     * @return buyerEmail
     */
    public java.lang.String getBuyerEmail() {
        return buyerEmail;
    }


    /**
     * Sets the buyerEmail value for this Transazione.
     * 
     * @param buyerEmail
     */
    public void setBuyerEmail(java.lang.String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }


    /**
     * Gets the buyercodfisc value for this Transazione.
     * 
     * @return buyercodfisc
     */
    public java.lang.String getBuyercodfisc() {
        return buyercodfisc;
    }


    /**
     * Sets the buyercodfisc value for this Transazione.
     * 
     * @param buyercodfisc
     */
    public void setBuyercodfisc(java.lang.String buyercodfisc) {
        this.buyercodfisc = buyercodfisc;
    }


    /**
     * Gets the buyername value for this Transazione.
     * 
     * @return buyername
     */
    public java.lang.String getBuyername() {
        return buyername;
    }


    /**
     * Sets the buyername value for this Transazione.
     * 
     * @param buyername
     */
    public void setBuyername(java.lang.String buyername) {
        this.buyername = buyername;
    }


    /**
     * Gets the ccy value for this Transazione.
     * 
     * @return ccy
     */
    public java.lang.String getCcy() {
        return ccy;
    }


    /**
     * Sets the ccy value for this Transazione.
     * 
     * @param ccy
     */
    public void setCcy(java.lang.String ccy) {
        this.ccy = ccy;
    }


    /**
     * Gets the changestatedate value for this Transazione.
     * 
     * @return changestatedate
     */
    public java.util.Calendar getChangestatedate() {
        return changestatedate;
    }


    /**
     * Sets the changestatedate value for this Transazione.
     * 
     * @param changestatedate
     */
    public void setChangestatedate(java.util.Calendar changestatedate) {
        this.changestatedate = changestatedate;
    }


    /**
     * Gets the clientipaddress value for this Transazione.
     * 
     * @return clientipaddress
     */
    public java.lang.String getClientipaddress() {
        return clientipaddress;
    }


    /**
     * Sets the clientipaddress value for this Transazione.
     * 
     * @param clientipaddress
     */
    public void setClientipaddress(java.lang.String clientipaddress) {
        this.clientipaddress = clientipaddress;
    }


    /**
     * Gets the codStato value for this Transazione.
     * 
     * @return codStato
     */
    public java.lang.Long getCodStato() {
        return codStato;
    }


    /**
     * Sets the codStato value for this Transazione.
     * 
     * @param codStato
     */
    public void setCodStato(java.lang.Long codStato) {
        this.codStato = codStato;
    }


    /**
     * Gets the commissioniApplicate value for this Transazione.
     * 
     * @return commissioniApplicate
     */
    public java.lang.Double getCommissioniApplicate() {
        return commissioniApplicate;
    }


    /**
     * Sets the commissioniApplicate value for this Transazione.
     * 
     * @param commissioniApplicate
     */
    public void setCommissioniApplicate(java.lang.Double commissioniApplicate) {
        this.commissioniApplicate = commissioniApplicate;
    }


    /**
     * Gets the commissioniApplicateNull value for this Transazione.
     * 
     * @return commissioniApplicateNull
     */
    public boolean isCommissioniApplicateNull() {
        return commissioniApplicateNull;
    }


    /**
     * Sets the commissioniApplicateNull value for this Transazione.
     * 
     * @param commissioniApplicateNull
     */
    public void setCommissioniApplicateNull(boolean commissioniApplicateNull) {
        this.commissioniApplicateNull = commissioniApplicateNull;
    }


    /**
     * Gets the errcode value for this Transazione.
     * 
     * @return errcode
     */
    public java.lang.String getErrcode() {
        return errcode;
    }


    /**
     * Sets the errcode value for this Transazione.
     * 
     * @param errcode
     */
    public void setErrcode(java.lang.String errcode) {
        this.errcode = errcode;
    }


    /**
     * Gets the finishDate value for this Transazione.
     * 
     * @return finishDate
     */
    public java.util.Calendar getFinishDate() {
        return finishDate;
    }


    /**
     * Sets the finishDate value for this Transazione.
     * 
     * @param finishDate
     */
    public void setFinishDate(java.util.Calendar finishDate) {
        this.finishDate = finishDate;
    }


    /**
     * Gets the gatewayId value for this Transazione.
     * 
     * @return gatewayId
     */
    public java.lang.String getGatewayId() {
        return gatewayId;
    }


    /**
     * Sets the gatewayId value for this Transazione.
     * 
     * @param gatewayId
     */
    public void setGatewayId(java.lang.String gatewayId) {
        this.gatewayId = gatewayId;
    }


    /**
     * Gets the gatewaypaymodeid value for this Transazione.
     * 
     * @return gatewaypaymodeid
     */
    public java.lang.String getGatewaypaymodeid() {
        return gatewaypaymodeid;
    }


    /**
     * Sets the gatewaypaymodeid value for this Transazione.
     * 
     * @param gatewaypaymodeid
     */
    public void setGatewaypaymodeid(java.lang.String gatewaypaymodeid) {
        this.gatewaypaymodeid = gatewaypaymodeid;
    }


    /**
     * Gets the incassokoerrormessage value for this Transazione.
     * 
     * @return incassokoerrormessage
     */
    public java.lang.String getIncassokoerrormessage() {
        return incassokoerrormessage;
    }


    /**
     * Sets the incassokoerrormessage value for this Transazione.
     * 
     * @param incassokoerrormessage
     */
    public void setIncassokoerrormessage(java.lang.String incassokoerrormessage) {
        this.incassokoerrormessage = incassokoerrormessage;
    }


    /**
     * Gets the initDate value for this Transazione.
     * 
     * @return initDate
     */
    public java.util.Calendar getInitDate() {
        return initDate;
    }


    /**
     * Sets the initDate value for this Transazione.
     * 
     * @param initDate
     */
    public void setInitDate(java.util.Calendar initDate) {
        this.initDate = initDate;
    }


    /**
     * Gets the intestatariocc value for this Transazione.
     * 
     * @return intestatariocc
     */
    public java.lang.String getIntestatariocc() {
        return intestatariocc;
    }


    /**
     * Sets the intestatariocc value for this Transazione.
     * 
     * @param intestatariocc
     */
    public void setIntestatariocc(java.lang.String intestatariocc) {
        this.intestatariocc = intestatariocc;
    }


    /**
     * Gets the language value for this Transazione.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this Transazione.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the merchantId value for this Transazione.
     * 
     * @return merchantId
     */
    public java.lang.String getMerchantId() {
        return merchantId;
    }


    /**
     * Sets the merchantId value for this Transazione.
     * 
     * @param merchantId
     */
    public void setMerchantId(java.lang.String merchantId) {
        this.merchantId = merchantId;
    }


    /**
     * Gets the mscsorderid value for this Transazione.
     * 
     * @return mscsorderid
     */
    public java.lang.Long getMscsorderid() {
        return mscsorderid;
    }


    /**
     * Sets the mscsorderid value for this Transazione.
     * 
     * @param mscsorderid
     */
    public void setMscsorderid(java.lang.Long mscsorderid) {
        this.mscsorderid = mscsorderid;
    }


    /**
     * Gets the mscsorderidNull value for this Transazione.
     * 
     * @return mscsorderidNull
     */
    public boolean isMscsorderidNull() {
        return mscsorderidNull;
    }


    /**
     * Sets the mscsorderidNull value for this Transazione.
     * 
     * @param mscsorderidNull
     */
    public void setMscsorderidNull(boolean mscsorderidNull) {
        this.mscsorderidNull = mscsorderidNull;
    }


    /**
     * Gets the oldstate value for this Transazione.
     * 
     * @return oldstate
     */
    public java.lang.Long getOldstate() {
        return oldstate;
    }


    /**
     * Sets the oldstate value for this Transazione.
     * 
     * @param oldstate
     */
    public void setOldstate(java.lang.Long oldstate) {
        this.oldstate = oldstate;
    }


    /**
     * Gets the opernumber value for this Transazione.
     * 
     * @return opernumber
     */
    public java.lang.String getOpernumber() {
        return opernumber;
    }


    /**
     * Sets the opernumber value for this Transazione.
     * 
     * @param opernumber
     */
    public void setOpernumber(java.lang.String opernumber) {
        this.opernumber = opernumber;
    }


    /**
     * Gets the paymentid value for this Transazione.
     * 
     * @return paymentid
     */
    public java.lang.String getPaymentid() {
        return paymentid;
    }


    /**
     * Sets the paymentid value for this Transazione.
     * 
     * @param paymentid
     */
    public void setPaymentid(java.lang.String paymentid) {
        this.paymentid = paymentid;
    }


    /**
     * Gets the payurl value for this Transazione.
     * 
     * @return payurl
     */
    public java.lang.String getPayurl() {
        return payurl;
    }


    /**
     * Sets the payurl value for this Transazione.
     * 
     * @param payurl
     */
    public void setPayurl(java.lang.String payurl) {
        this.payurl = payurl;
    }


    /**
     * Gets the pgresultcode value for this Transazione.
     * 
     * @return pgresultcode
     */
    public java.lang.String getPgresultcode() {
        return pgresultcode;
    }


    /**
     * Sets the pgresultcode value for this Transazione.
     * 
     * @param pgresultcode
     */
    public void setPgresultcode(java.lang.String pgresultcode) {
        this.pgresultcode = pgresultcode;
    }


    /**
     * Gets the providertimestamp value for this Transazione.
     * 
     * @return providertimestamp
     */
    public java.lang.String getProvidertimestamp() {
        return providertimestamp;
    }


    /**
     * Sets the providertimestamp value for this Transazione.
     * 
     * @param providertimestamp
     */
    public void setProvidertimestamp(java.lang.String providertimestamp) {
        this.providertimestamp = providertimestamp;
    }


    /**
     * Gets the rispcomp value for this Transazione.
     * 
     * @return rispcomp
     */
    public java.lang.String getRispcomp() {
        return rispcomp;
    }


    /**
     * Sets the rispcomp value for this Transazione.
     * 
     * @param rispcomp
     */
    public void setRispcomp(java.lang.String rispcomp) {
        this.rispcomp = rispcomp;
    }


    /**
     * Gets the startDate value for this Transazione.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this Transazione.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the transactionId value for this Transazione.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Transazione.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the userhaschange value for this Transazione.
     * 
     * @return userhaschange
     */
    public java.lang.String getUserhaschange() {
        return userhaschange;
    }


    /**
     * Sets the userhaschange value for this Transazione.
     * 
     * @param userhaschange
     */
    public void setUserhaschange(java.lang.String userhaschange) {
        this.userhaschange = userhaschange;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Transazione)) return false;
        Transazione other = (Transazione) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.authornumber==null && other.getAuthornumber()==null) || 
             (this.authornumber!=null &&
              this.authornumber.equals(other.getAuthornumber()))) &&
            ((this.basketId==null && other.getBasketId()==null) || 
             (this.basketId!=null &&
              this.basketId.equals(other.getBasketId()))) &&
            ((this.buyerEmail==null && other.getBuyerEmail()==null) || 
             (this.buyerEmail!=null &&
              this.buyerEmail.equals(other.getBuyerEmail()))) &&
            ((this.buyercodfisc==null && other.getBuyercodfisc()==null) || 
             (this.buyercodfisc!=null &&
              this.buyercodfisc.equals(other.getBuyercodfisc()))) &&
            ((this.buyername==null && other.getBuyername()==null) || 
             (this.buyername!=null &&
              this.buyername.equals(other.getBuyername()))) &&
            ((this.ccy==null && other.getCcy()==null) || 
             (this.ccy!=null &&
              this.ccy.equals(other.getCcy()))) &&
            ((this.changestatedate==null && other.getChangestatedate()==null) || 
             (this.changestatedate!=null &&
              this.changestatedate.equals(other.getChangestatedate()))) &&
            ((this.clientipaddress==null && other.getClientipaddress()==null) || 
             (this.clientipaddress!=null &&
              this.clientipaddress.equals(other.getClientipaddress()))) &&
            ((this.codStato==null && other.getCodStato()==null) || 
             (this.codStato!=null &&
              this.codStato.equals(other.getCodStato()))) &&
            ((this.commissioniApplicate==null && other.getCommissioniApplicate()==null) || 
             (this.commissioniApplicate!=null &&
              this.commissioniApplicate.equals(other.getCommissioniApplicate()))) &&
            this.commissioniApplicateNull == other.isCommissioniApplicateNull() &&
            ((this.errcode==null && other.getErrcode()==null) || 
             (this.errcode!=null &&
              this.errcode.equals(other.getErrcode()))) &&
            ((this.finishDate==null && other.getFinishDate()==null) || 
             (this.finishDate!=null &&
              this.finishDate.equals(other.getFinishDate()))) &&
            ((this.gatewayId==null && other.getGatewayId()==null) || 
             (this.gatewayId!=null &&
              this.gatewayId.equals(other.getGatewayId()))) &&
            ((this.gatewaypaymodeid==null && other.getGatewaypaymodeid()==null) || 
             (this.gatewaypaymodeid!=null &&
              this.gatewaypaymodeid.equals(other.getGatewaypaymodeid()))) &&
            ((this.incassokoerrormessage==null && other.getIncassokoerrormessage()==null) || 
             (this.incassokoerrormessage!=null &&
              this.incassokoerrormessage.equals(other.getIncassokoerrormessage()))) &&
            ((this.initDate==null && other.getInitDate()==null) || 
             (this.initDate!=null &&
              this.initDate.equals(other.getInitDate()))) &&
            ((this.intestatariocc==null && other.getIntestatariocc()==null) || 
             (this.intestatariocc!=null &&
              this.intestatariocc.equals(other.getIntestatariocc()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.merchantId==null && other.getMerchantId()==null) || 
             (this.merchantId!=null &&
              this.merchantId.equals(other.getMerchantId()))) &&
            ((this.mscsorderid==null && other.getMscsorderid()==null) || 
             (this.mscsorderid!=null &&
              this.mscsorderid.equals(other.getMscsorderid()))) &&
            this.mscsorderidNull == other.isMscsorderidNull() &&
            ((this.oldstate==null && other.getOldstate()==null) || 
             (this.oldstate!=null &&
              this.oldstate.equals(other.getOldstate()))) &&
            ((this.opernumber==null && other.getOpernumber()==null) || 
             (this.opernumber!=null &&
              this.opernumber.equals(other.getOpernumber()))) &&
            ((this.paymentid==null && other.getPaymentid()==null) || 
             (this.paymentid!=null &&
              this.paymentid.equals(other.getPaymentid()))) &&
            ((this.payurl==null && other.getPayurl()==null) || 
             (this.payurl!=null &&
              this.payurl.equals(other.getPayurl()))) &&
            ((this.pgresultcode==null && other.getPgresultcode()==null) || 
             (this.pgresultcode!=null &&
              this.pgresultcode.equals(other.getPgresultcode()))) &&
            ((this.providertimestamp==null && other.getProvidertimestamp()==null) || 
             (this.providertimestamp!=null &&
              this.providertimestamp.equals(other.getProvidertimestamp()))) &&
            ((this.rispcomp==null && other.getRispcomp()==null) || 
             (this.rispcomp!=null &&
              this.rispcomp.equals(other.getRispcomp()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId()))) &&
            ((this.userhaschange==null && other.getUserhaschange()==null) || 
             (this.userhaschange!=null &&
              this.userhaschange.equals(other.getUserhaschange())));
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
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getAuthornumber() != null) {
            _hashCode += getAuthornumber().hashCode();
        }
        if (getBasketId() != null) {
            _hashCode += getBasketId().hashCode();
        }
        if (getBuyerEmail() != null) {
            _hashCode += getBuyerEmail().hashCode();
        }
        if (getBuyercodfisc() != null) {
            _hashCode += getBuyercodfisc().hashCode();
        }
        if (getBuyername() != null) {
            _hashCode += getBuyername().hashCode();
        }
        if (getCcy() != null) {
            _hashCode += getCcy().hashCode();
        }
        if (getChangestatedate() != null) {
            _hashCode += getChangestatedate().hashCode();
        }
        if (getClientipaddress() != null) {
            _hashCode += getClientipaddress().hashCode();
        }
        if (getCodStato() != null) {
            _hashCode += getCodStato().hashCode();
        }
        if (getCommissioniApplicate() != null) {
            _hashCode += getCommissioniApplicate().hashCode();
        }
        _hashCode += (isCommissioniApplicateNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getErrcode() != null) {
            _hashCode += getErrcode().hashCode();
        }
        if (getFinishDate() != null) {
            _hashCode += getFinishDate().hashCode();
        }
        if (getGatewayId() != null) {
            _hashCode += getGatewayId().hashCode();
        }
        if (getGatewaypaymodeid() != null) {
            _hashCode += getGatewaypaymodeid().hashCode();
        }
        if (getIncassokoerrormessage() != null) {
            _hashCode += getIncassokoerrormessage().hashCode();
        }
        if (getInitDate() != null) {
            _hashCode += getInitDate().hashCode();
        }
        if (getIntestatariocc() != null) {
            _hashCode += getIntestatariocc().hashCode();
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getMerchantId() != null) {
            _hashCode += getMerchantId().hashCode();
        }
        if (getMscsorderid() != null) {
            _hashCode += getMscsorderid().hashCode();
        }
        _hashCode += (isMscsorderidNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOldstate() != null) {
            _hashCode += getOldstate().hashCode();
        }
        if (getOpernumber() != null) {
            _hashCode += getOpernumber().hashCode();
        }
        if (getPaymentid() != null) {
            _hashCode += getPaymentid().hashCode();
        }
        if (getPayurl() != null) {
            _hashCode += getPayurl().hashCode();
        }
        if (getPgresultcode() != null) {
            _hashCode += getPgresultcode().hashCode();
        }
        if (getProvidertimestamp() != null) {
            _hashCode += getProvidertimestamp().hashCode();
        }
        if (getRispcomp() != null) {
            _hashCode += getRispcomp().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        if (getUserhaschange() != null) {
            _hashCode += getUserhaschange().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Transazione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "Transazione"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authornumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authornumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("basketId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "basketId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buyerEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "buyerEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buyercodfisc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "buyercodfisc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buyername");
        elemField.setXmlName(new javax.xml.namespace.QName("", "buyername"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ccy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ccy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changestatedate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changestatedate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientipaddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientipaddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codStato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codStato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commissioniApplicate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commissioniApplicate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commissioniApplicateNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commissioniApplicateNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finishDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "finishDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewaypaymodeid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewaypaymodeid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incassokoerrormessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "incassokoerrormessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("initDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "initDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intestatariocc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "intestatariocc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "merchantId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mscsorderid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mscsorderid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mscsorderidNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mscsorderidNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oldstate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oldstate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opernumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "opernumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payurl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payurl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pgresultcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pgresultcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("providertimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "providertimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rispcomp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rispcomp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userhaschange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userhaschange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
