/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the mdp_singolo_transfer database table.
 *
 */
@Entity
@Table(name="mdp_singolo_transfer")
@NamedQuery(name="MdpSingoloTransfer.findAll", query="SELECT m FROM MdpSingoloTransfer m")
public class MdpSingoloTransfer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "MDP_SINGOLO_TRANSFER_IDSINGOLOTRANSFER_GENERATOR", sequenceName = "MDP_SINGOLO_TRANSFER_ID_SINGOLO_TRANSFER_SEQ",
                    allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MDP_SINGOLO_TRANSFER_IDSINGOLOTRANSFER_GENERATOR")
    @Column(name="id_singolo_transfer")
    private Integer idSingoloTransfer;

    @Column(name="data_ora_insert")
    private Date dataOraInsert;

    @Column(name="fiscal_codepa")
    private String fiscalCodepa;

    private String iban;

    @Column(name="id_getpayment")
    private Integer idGetpayment;

    @Column(name="pagopa_idtransfer")
    private String pagopaIdtransfer;

    @Column(name="transfer_amount")
    private String transferAmount;

    @Column(name="transfer_category")
    private String transferCategory;

    //bi-directional many-to-one association to MdpReceipt
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_receipt")
    private MdpReceipt mdpReceipt;

    @Generated ( "SparkTools" )
    private MdpSingoloTransfer ( Builder builder ) {
        this.idSingoloTransfer = builder.idSingoloTransfer;
        this.dataOraInsert = builder.dataOraInsert;
        this.fiscalCodepa = builder.fiscalCodepa;
        this.iban = builder.iban;
        this.idGetpayment = builder.idGetpayment;
        this.pagopaIdtransfer = builder.pagopaIdtransfer;
        this.transferAmount = builder.transferAmount;
        this.transferCategory = builder.transferCategory;
        this.mdpReceipt = builder.mdpReceipt;
    }

    public MdpSingoloTransfer() {
    }

    public Integer getIdSingoloTransfer() {
        return this.idSingoloTransfer;
    }

    public void setIdSingoloTransfer(Integer idSingoloTransfer) {
        this.idSingoloTransfer = idSingoloTransfer;
    }

    public Date getDataOraInsert () {
        return this.dataOraInsert;
    }

    public void setDataOraInsert ( Date dataOraInsert ) {
        this.dataOraInsert = dataOraInsert;
    }

    public String getFiscalCodepa() {
        return this.fiscalCodepa;
    }

    public void setFiscalCodepa(String fiscalCodepa) {
        this.fiscalCodepa = fiscalCodepa;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Integer getIdGetpayment() {
        return this.idGetpayment;
    }

    public void setIdGetpayment(Integer idGetpayment) {
        this.idGetpayment = idGetpayment;
    }

    public String getPagopaIdtransfer() {
        return this.pagopaIdtransfer;
    }

    public void setPagopaIdtransfer(String pagopaIdtransfer) {
        this.pagopaIdtransfer = pagopaIdtransfer;
    }

    public String getTransferAmount() {
        return this.transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferCategory() {
        return this.transferCategory;
    }

    public void setTransferCategory(String transferCategory) {
        this.transferCategory = transferCategory;
    }

    public MdpReceipt getMdpReceipt() {
        return this.mdpReceipt;
    }

    public void setMdpReceipt(MdpReceipt mdpReceipt) {
        this.mdpReceipt = mdpReceipt;
    }

    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    @Generated ( "SparkTools" )
    public static final class Builder {

        private Integer idSingoloTransfer;

        private Date dataOraInsert;

        private String fiscalCodepa;

        private String iban;

        private Integer idGetpayment;

        private String pagopaIdtransfer;

        private String transferAmount;

        private String transferCategory;

        private MdpReceipt mdpReceipt;

        private Builder () {
        }

        public Builder withIdSingoloTransfer ( Integer idSingoloTransfer ) {
            this.idSingoloTransfer = idSingoloTransfer;
            return this;
        }

        public Builder withDataOraInsert ( Date dataOraInsert ) {
            this.dataOraInsert = dataOraInsert;
            return this;
        }

        public Builder withFiscalCodepa ( String fiscalCodepa ) {
            this.fiscalCodepa = fiscalCodepa;
            return this;
        }

        public Builder withIban ( String iban ) {
            this.iban = iban;
            return this;
        }

        public Builder withIdGetpayment ( Integer idGetpayment ) {
            this.idGetpayment = idGetpayment;
            return this;
        }

        public Builder withPagopaIdtransfer ( String pagopaIdtransfer ) {
            this.pagopaIdtransfer = pagopaIdtransfer;
            return this;
        }

        public Builder withTransferAmount ( String transferAmount ) {
            this.transferAmount = transferAmount;
            return this;
        }

        public Builder withTransferCategory ( String transferCategory ) {
            this.transferCategory = transferCategory;
            return this;
        }

        public Builder withMdpReceipt ( MdpReceipt mdpReceipt ) {
            this.mdpReceipt = mdpReceipt;
            return this;
        }

        public MdpSingoloTransfer build () {
            return new MdpSingoloTransfer ( this );
        }
    }

}
