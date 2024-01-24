/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the transazione database table.
 *
 */
@Entity
@NamedQuery(name="Transazione.findAll", query="SELECT t FROM Transazione t")
public class Transazione implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //@SequenceGenerator ( name = "TRANSAZIONE_TRANSACTIONID_GENERATOR", sequenceName = "seq_transazione", allocationSize = 1 )
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSAZIONE_TRANSACTIONID_GENERATOR")
    @Column(name="transaction_id")
    private String transactionId;

    private BigDecimal amount;

    @Column(name="application_id")
    private String applicationId;

    private String authornumber;

    @Column(name="basket_id")
    private String basketId;

    @Column(name="buyer_email")
    private String buyerEmail;

    private String buyercodfisc;

    private String buyername;

    private String ccy;

    private Date changestatedate;

    private String clientipaddress;

    @Column(name="commissioni_applicate")
    private BigDecimal commissioniApplicate;

    private String errcode;

    @Column(name="finish_date")
    private Date finishDate;

    private String incassokoerrormessage;

    @Column(name="init_date")
    private Date initDate;

    private String intestatariocc;

    private String language;

    @Column(name="merchant_id")
    private String merchantId;

    private BigDecimal mscsorderid;

    private BigDecimal oldstate;

    private String opernumber;

    private String paymentid;

    private String payurl;

    private String pgresultcode;

    private String providertimestamp;

    private String rispcomp;

    @Column(name="start_date")
    private Date startDate;

    private String userhaschange;

    //bi-directional many-to-one association to MdpErrori
    @OneToMany(mappedBy="transazione")
    private List<MdpErrori> mdpErroris;

    //bi-directional many-to-one association to OptAttr
    @OneToMany(mappedBy="transazione")
    private List<OptAttr> optAttrs;

    //bi-directional many-to-one association to Gateway
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gateway_id")
    private Gateway gateway;

    //bi-directional many-to-one association to Paymentmode
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gatewaypaymodeid")
    private Paymentmode paymentmode;

    //bi-directional many-to-one association to StatoTransazione
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_stato")
    private StatoTransazione statoTransazione;

    //bi-directional many-to-one association to TransazioneExtraAttribute
    @OneToMany(mappedBy="transazione")
    private List<TransazioneExtraAttribute> transazioneExtraAttributes;

    @Generated ( "SparkTools" )
    private Transazione ( Builder builder ) {
        this.transactionId = builder.transactionId;
        this.amount = builder.amount;
        this.applicationId = builder.applicationId;
        this.authornumber = builder.authornumber;
        this.basketId = builder.basketId;
        this.buyerEmail = builder.buyerEmail;
        this.buyercodfisc = builder.buyercodfisc;
        this.buyername = builder.buyername;
        this.ccy = builder.ccy;
        this.changestatedate = builder.changestatedate;
        this.clientipaddress = builder.clientipaddress;
        this.commissioniApplicate = builder.commissioniApplicate;
        this.errcode = builder.errcode;
        this.finishDate = builder.finishDate;
        this.incassokoerrormessage = builder.incassokoerrormessage;
        this.initDate = builder.initDate;
        this.intestatariocc = builder.intestatariocc;
        this.language = builder.language;
        this.merchantId = builder.merchantId;
        this.mscsorderid = builder.mscsorderid;
        this.oldstate = builder.oldstate;
        this.opernumber = builder.opernumber;
        this.paymentid = builder.paymentid;
        this.payurl = builder.payurl;
        this.pgresultcode = builder.pgresultcode;
        this.providertimestamp = builder.providertimestamp;
        this.rispcomp = builder.rispcomp;
        this.startDate = builder.startDate;
        this.userhaschange = builder.userhaschange;
        this.mdpErroris = builder.mdpErroris;
        this.optAttrs = builder.optAttrs;
        this.gateway = builder.gateway;
        this.paymentmode = builder.paymentmode;
        this.statoTransazione = builder.statoTransazione;
        this.transazioneExtraAttributes = builder.transazioneExtraAttributes;
    }

    public Transazione() {
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getAuthornumber() {
        return this.authornumber;
    }

    public void setAuthornumber(String authornumber) {
        this.authornumber = authornumber;
    }

    public String getBasketId() {
        return this.basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getBuyerEmail() {
        return this.buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyercodfisc() {
        return this.buyercodfisc;
    }

    public void setBuyercodfisc(String buyercodfisc) {
        this.buyercodfisc = buyercodfisc;
    }

    public String getBuyername() {
        return this.buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public String getCcy() {
        return this.ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public Date getChangestatedate () {
        return this.changestatedate;
    }

    public void setChangestatedate ( Date changestatedate ) {
        this.changestatedate = changestatedate;
    }

    public String getClientipaddress() {
        return this.clientipaddress;
    }

    public void setClientipaddress(String clientipaddress) {
        this.clientipaddress = clientipaddress;
    }

    public BigDecimal getCommissioniApplicate() {
        return this.commissioniApplicate;
    }

    public void setCommissioniApplicate(BigDecimal commissioniApplicate) {
        this.commissioniApplicate = commissioniApplicate;
    }

    public String getErrcode() {
        return this.errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public Date getFinishDate () {
        return this.finishDate;
    }

    public void setFinishDate ( Date finishDate ) {
        this.finishDate = finishDate;
    }

    public String getIncassokoerrormessage() {
        return this.incassokoerrormessage;
    }

    public void setIncassokoerrormessage(String incassokoerrormessage) {
        this.incassokoerrormessage = incassokoerrormessage;
    }

    public Date getInitDate () {
        return this.initDate;
    }

    public void setInitDate ( Date initDate ) {
        this.initDate = initDate;
    }

    public String getIntestatariocc() {
        return this.intestatariocc;
    }

    public void setIntestatariocc(String intestatariocc) {
        this.intestatariocc = intestatariocc;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getMscsorderid() {
        return this.mscsorderid;
    }

    public void setMscsorderid(BigDecimal mscsorderid) {
        this.mscsorderid = mscsorderid;
    }

    public BigDecimal getOldstate() {
        return this.oldstate;
    }

    public void setOldstate(BigDecimal oldstate) {
        this.oldstate = oldstate;
    }

    public String getOpernumber() {
        return this.opernumber;
    }

    public void setOpernumber(String opernumber) {
        this.opernumber = opernumber;
    }

    public String getPaymentid() {
        return this.paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getPayurl() {
        return this.payurl;
    }

    public void setPayurl(String payurl) {
        this.payurl = payurl;
    }

    public String getPgresultcode() {
        return this.pgresultcode;
    }

    public void setPgresultcode(String pgresultcode) {
        this.pgresultcode = pgresultcode;
    }

    public String getProvidertimestamp() {
        return this.providertimestamp;
    }

    public void setProvidertimestamp(String providertimestamp) {
        this.providertimestamp = providertimestamp;
    }

    public String getRispcomp() {
        return this.rispcomp;
    }

    public void setRispcomp(String rispcomp) {
        this.rispcomp = rispcomp;
    }

    public Date getStartDate () {
        return this.startDate;
    }

    public void setStartDate ( Date startDate ) {
        this.startDate = startDate;
    }

    public String getUserhaschange() {
        return this.userhaschange;
    }

    public void setUserhaschange(String userhaschange) {
        this.userhaschange = userhaschange;
    }

    public List<MdpErrori> getMdpErroris() {
        return this.mdpErroris;
    }

    public void setMdpErroris(List<MdpErrori> mdpErroris) {
        this.mdpErroris = mdpErroris;
    }

    public MdpErrori addMdpErrori(MdpErrori mdpErrori) {
        getMdpErroris().add(mdpErrori);
        mdpErrori.setTransazione(this);

        return mdpErrori;
    }

    public MdpErrori removeMdpErrori(MdpErrori mdpErrori) {
        getMdpErroris().remove(mdpErrori);
        mdpErrori.setTransazione(null);

        return mdpErrori;
    }

    public List<OptAttr> getOptAttrs() {
        return this.optAttrs;
    }

    public void setOptAttrs(List<OptAttr> optAttrs) {
        this.optAttrs = optAttrs;
    }

    public OptAttr addOptAttr(OptAttr optAttr) {
        getOptAttrs().add(optAttr);
        optAttr.setTransazione(this);

        return optAttr;
    }

    public OptAttr removeOptAttr(OptAttr optAttr) {
        getOptAttrs().remove(optAttr);
        optAttr.setTransazione(null);

        return optAttr;
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

    public StatoTransazione getStatoTransazione() {
        return this.statoTransazione;
    }

    public void setStatoTransazione(StatoTransazione statoTransazione) {
        this.statoTransazione = statoTransazione;
    }

    public List<TransazioneExtraAttribute> getTransazioneExtraAttributes() {
        return this.transazioneExtraAttributes;
    }

    public void setTransazioneExtraAttributes(List<TransazioneExtraAttribute> transazioneExtraAttributes) {
        this.transazioneExtraAttributes = transazioneExtraAttributes;
    }

    public TransazioneExtraAttribute addTransazioneExtraAttribute(TransazioneExtraAttribute transazioneExtraAttribute) {
        getTransazioneExtraAttributes().add(transazioneExtraAttribute);
        transazioneExtraAttribute.setTransazione(this);

        return transazioneExtraAttribute;
    }

    public TransazioneExtraAttribute removeTransazioneExtraAttribute(TransazioneExtraAttribute transazioneExtraAttribute) {
        getTransazioneExtraAttributes().remove(transazioneExtraAttribute);
        transazioneExtraAttribute.setTransazione(null);

        return transazioneExtraAttribute;
    }

    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    @Generated ( "SparkTools" )
    public static final class Builder {

        private String transactionId;

        private BigDecimal amount;

        private String applicationId;

        private String authornumber;

        private String basketId;

        private String buyerEmail;

        private String buyercodfisc;

        private String buyername;

        private String ccy;

        private Date changestatedate;

        private String clientipaddress;

        private BigDecimal commissioniApplicate;

        private String errcode;

        private Date finishDate;

        private String incassokoerrormessage;

        private Date initDate;

        private String intestatariocc;

        private String language;

        private String merchantId;

        private BigDecimal mscsorderid;

        private BigDecimal oldstate;

        private String opernumber;

        private String paymentid;

        private String payurl;

        private String pgresultcode;

        private String providertimestamp;

        private String rispcomp;

        private Date startDate;

        private String userhaschange;

        private List<MdpErrori> mdpErroris = Collections.emptyList ();

        private List<OptAttr> optAttrs = Collections.emptyList ();

        private Gateway gateway;

        private Paymentmode paymentmode;

        private StatoTransazione statoTransazione;

        private List<TransazioneExtraAttribute> transazioneExtraAttributes = Collections.emptyList ();

        private Builder () {
        }

        public Builder withTransactionId ( String transactionId ) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withAmount ( BigDecimal amount ) {
            this.amount = amount;
            return this;
        }

        public Builder withApplicationId ( String applicationId ) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder withAuthornumber ( String authornumber ) {
            this.authornumber = authornumber;
            return this;
        }

        public Builder withBasketId ( String basketId ) {
            this.basketId = basketId;
            return this;
        }

        public Builder withBuyerEmail ( String buyerEmail ) {
            this.buyerEmail = buyerEmail;
            return this;
        }

        public Builder withBuyercodfisc ( String buyercodfisc ) {
            this.buyercodfisc = buyercodfisc;
            return this;
        }

        public Builder withBuyername ( String buyername ) {
            this.buyername = buyername;
            return this;
        }

        public Builder withCcy ( String ccy ) {
            this.ccy = ccy;
            return this;
        }

        public Builder withChangestatedate ( Date changestatedate ) {
            this.changestatedate = changestatedate;
            return this;
        }

        public Builder withClientipaddress ( String clientipaddress ) {
            this.clientipaddress = clientipaddress;
            return this;
        }

        public Builder withCommissioniApplicate ( BigDecimal commissioniApplicate ) {
            this.commissioniApplicate = commissioniApplicate;
            return this;
        }

        public Builder withErrcode ( String errcode ) {
            this.errcode = errcode;
            return this;
        }

        public Builder withFinishDate ( Date finishDate ) {
            this.finishDate = finishDate;
            return this;
        }

        public Builder withIncassokoerrormessage ( String incassokoerrormessage ) {
            this.incassokoerrormessage = incassokoerrormessage;
            return this;
        }

        public Builder withInitDate ( Date initDate ) {
            this.initDate = initDate;
            return this;
        }

        public Builder withIntestatariocc ( String intestatariocc ) {
            this.intestatariocc = intestatariocc;
            return this;
        }

        public Builder withLanguage ( String language ) {
            this.language = language;
            return this;
        }

        public Builder withMerchantId ( String merchantId ) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder withMscsorderid ( BigDecimal mscsorderid ) {
            this.mscsorderid = mscsorderid;
            return this;
        }

        public Builder withOldstate ( BigDecimal oldstate ) {
            this.oldstate = oldstate;
            return this;
        }

        public Builder withOpernumber ( String opernumber ) {
            this.opernumber = opernumber;
            return this;
        }

        public Builder withPaymentid ( String paymentid ) {
            this.paymentid = paymentid;
            return this;
        }

        public Builder withPayurl ( String payurl ) {
            this.payurl = payurl;
            return this;
        }

        public Builder withPgresultcode ( String pgresultcode ) {
            this.pgresultcode = pgresultcode;
            return this;
        }

        public Builder withProvidertimestamp ( String providertimestamp ) {
            this.providertimestamp = providertimestamp;
            return this;
        }

        public Builder withRispcomp ( String rispcomp ) {
            this.rispcomp = rispcomp;
            return this;
        }

        public Builder withStartDate ( Date startDate ) {
            this.startDate = startDate;
            return this;
        }

        public Builder withUserhaschange ( String userhaschange ) {
            this.userhaschange = userhaschange;
            return this;
        }

        public Builder withMdpErroris ( List<MdpErrori> mdpErroris ) {
            this.mdpErroris = mdpErroris;
            return this;
        }

        public Builder withOptAttrs ( List<OptAttr> optAttrs ) {
            this.optAttrs = optAttrs;
            return this;
        }

        public Builder withGateway ( Gateway gateway ) {
            this.gateway = gateway;
            return this;
        }

        public Builder withPaymentmode ( Paymentmode paymentmode ) {
            this.paymentmode = paymentmode;
            return this;
        }

        public Builder withStatoTransazione ( StatoTransazione statoTransazione ) {
            this.statoTransazione = statoTransazione;
            return this;
        }

        public Builder withTransazioneExtraAttributes ( List<TransazioneExtraAttribute> transazioneExtraAttributes ) {
            this.transazioneExtraAttributes = transazioneExtraAttributes;
            return this;
        }

        public Transazione build () {
            return new Transazione ( this );
        }
    }

}
