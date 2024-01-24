/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the mdp_receipt database table.
 *
 */
@Entity
@Table(name="mdp_receipt")
@NamedQuery(name="MdpReceipt.findAll", query="SELECT m FROM MdpReceipt m")
public class MdpReceipt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "MDP_RECEIPT_ID_GENERATOR", sequenceName = "mdp_receipt_id_seq", allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MDP_RECEIPT_ID_GENERATOR")
    private Long id;

    @Column(name="application_id")
    private String applicationId;

    @Column(name="creditor_referenceid")
    private String creditorReferenceid;

    @Column(name="data_invio_fruitore")
    private Date dataInvioFruitore;

    @Column(name="data_ora_insert")
    private Date dataOraInsert;

    @Column(name="data_ora_invio_fallito")
    private Date dataOraInvioFallito;

    @Column(name="data_ora_retry")
    private Date dataOraRetry;

    @Column(name="data_ora_ricezione")
    private Date dataOraRicezione;

    @Column(name="data_ora_update")
    private Date dataOraUpdate;

    @Column(name="fee_psp")
    private BigDecimal feePsp;

    // :TODO sistemare relazione tra le entities (questa e ElementoMultiversamento)
    @Column(name="id_elemento_multiversamento")
    private BigDecimal idElementoMultiversamento;

    @Column(name="id_psp")
    private String idPsp;

    @Column(name="iuv_sconosciuto")
    private Boolean iuvSconosciuto;

    @Column(name="notice_number")
    private String noticeNumber;

    @Column(name="pagamento_inviato")
    private Boolean pagamentoInviato;

    @Column ( name = "payment_datetime" )
    private Date paymentDatetime;

    @Column(name="payment_amount")
    private BigDecimal paymentAmount;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="psp_comany_name")
    private String pspComanyName;

    @Column(name="receipt_id")
    private String receiptId;

    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="transfer_date")
    private Date transferDate;

    @Column(name="xml_receipt")
    private byte[] xmlReceipt;

    //bi-directional many-to-one association to MdpSingoloTransfer
    @OneToMany(mappedBy="mdpReceipt")
    private List<MdpSingoloTransfer> mdpSingoloTransfers;

    @Column ( name = "application_date" )
    private Date applicationDate;

	@Column ( name = "id_pa" )
	private String idPa;

	@Column ( name = "id_broker_pa" )
	private String idBrokerPa;

	@Column ( name = "id_station" )
	private String idStation;

	@Column(name="msg_invio_fallito" )
	private String msgInvioFallito;

	@Column ( name = "stato_invio_fruitore" )
	private String statoInvioFruitore;

    @Generated ( "SparkTools" )
    private MdpReceipt ( Builder builder ) {
        this.id = builder.id;
        this.applicationId = builder.applicationId;
        this.creditorReferenceid = builder.creditorReferenceid;
        this.dataInvioFruitore = builder.dataInvioFruitore;
        this.dataOraInsert = builder.dataOraInsert;
        this.dataOraInvioFallito = builder.dataOraInvioFallito;
        this.dataOraRetry = builder.dataOraRetry;
        this.dataOraRicezione = builder.dataOraRicezione;
        this.dataOraUpdate = builder.dataOraUpdate;
        this.feePsp = builder.feePsp;
        this.idElementoMultiversamento = builder.idElementoMultiversamento;
        this.idPsp = builder.idPsp;
        this.iuvSconosciuto = builder.iuvSconosciuto;
        this.noticeNumber = builder.noticeNumber;
        this.pagamentoInviato = builder.pagamentoInviato;
        this.paymentDatetime = builder.paymentDatetime;
        this.paymentAmount = builder.paymentAmount;
        this.paymentMethod = builder.paymentMethod;
        this.pspComanyName = builder.pspComanyName;
        this.receiptId = builder.receiptId;
        this.transactionId = builder.transactionId;
        this.transferDate = builder.transferDate;
        this.xmlReceipt = builder.xmlReceipt;
        this.mdpSingoloTransfers = builder.mdpSingoloTransfers;
        this.applicationDate = builder.applicationDate;
		this.idPa = builder.idPa;
		this.idBrokerPa = builder.idBrokerPa;
		this.idStation = builder.idStation;
		this.msgInvioFallito = builder.msgInvioFallito;
		this.statoInvioFruitore = builder.statoInvioFruitore;
    }

    public Date getApplicationDate () {
        return applicationDate;
    }

    public void setApplicationDate ( Date applicationDate ) {
        this.applicationDate = applicationDate;
    }

    public MdpReceipt() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCreditorReferenceid() {
        return this.creditorReferenceid;
    }

    public void setCreditorReferenceid(String creditorReferenceid) {
        this.creditorReferenceid = creditorReferenceid;
    }

    public Date getDataInvioFruitore () {
        return this.dataInvioFruitore;
    }

    public void setDataInvioFruitore ( Date dataInvioFruitore ) {
        this.dataInvioFruitore = dataInvioFruitore;
    }

    public Date getDataOraInsert () {
        return this.dataOraInsert;
    }

    public void setDataOraInsert ( Date dataOraInsert ) {
        this.dataOraInsert = dataOraInsert;
    }

    public Date getDataOraInvioFallito () {
        return this.dataOraInvioFallito;
    }

    public void setDataOraInvioFallito ( Date dataOraInvioFallito ) {
        this.dataOraInvioFallito = dataOraInvioFallito;
    }

    public Date getDataOraRetry () {
        return this.dataOraRetry;
    }

    public void setDataOraRetry ( Date dataOraRetry ) {
        this.dataOraRetry = dataOraRetry;
    }

    public Date getDataOraRicezione () {
        return this.dataOraRicezione;
    }

    public void setDataOraRicezione ( Date dataOraRicezione ) {
        this.dataOraRicezione = dataOraRicezione;
    }

    public Date getDataOraUpdate () {
        return this.dataOraUpdate;
    }

    public void setDataOraUpdate ( Date dataOraUpdate ) {
        this.dataOraUpdate = dataOraUpdate;
    }

    public BigDecimal getFeePsp() {
        return this.feePsp;
    }

    public void setFeePsp(BigDecimal feePsp) {
        this.feePsp = feePsp;
    }

    public BigDecimal getIdElementoMultiversamento() {
        return this.idElementoMultiversamento;
    }

    public void setIdElementoMultiversamento(BigDecimal idElementoMultiversamento) {
        this.idElementoMultiversamento = idElementoMultiversamento;
    }


    public String getIdPsp() {
        return this.idPsp;
    }

    public void setIdPsp(String idPsp) {
        this.idPsp = idPsp;
    }

    public Boolean getIuvSconosciuto() {
        return this.iuvSconosciuto;
    }

    public void setIuvSconosciuto(Boolean iuvSconosciuto) {
        this.iuvSconosciuto = iuvSconosciuto;
    }

    public String getNoticeNumber() {
        return this.noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public Boolean getPagamentoInviato() {
        return this.pagamentoInviato;
    }

    public void setPagamentoInviato(Boolean pagamentoInviato) {
        this.pagamentoInviato = pagamentoInviato;
    }

    public Date getPaymentDatetime () {
        return this.paymentDatetime;
    }

    public void setPaymentDatetime ( Date paymentDatetime ) {
        this.paymentDatetime = paymentDatetime;
    }

    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPspComanyName() {
        return this.pspComanyName;
    }

    public void setPspComanyName(String pspComanyName) {
        this.pspComanyName = pspComanyName;
    }

    public String getReceiptId() {
        return this.receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }


    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransferDate () {
        return this.transferDate;
    }

    public void setTransferDate ( Date transferDate ) {
        this.transferDate = transferDate;
    }

    public byte[] getXmlReceipt() {
        return this.xmlReceipt;
    }

    public void setXmlReceipt(byte[] xmlReceipt) {
        this.xmlReceipt = xmlReceipt;
    }

    public List<MdpSingoloTransfer> getMdpSingoloTransfers() {
        return this.mdpSingoloTransfers;
    }

    public void setMdpSingoloTransfers(List<MdpSingoloTransfer> mdpSingoloTransfers) {
        this.mdpSingoloTransfers = mdpSingoloTransfers;
    }

    public MdpSingoloTransfer addMdpSingoloTransfer(MdpSingoloTransfer mdpSingoloTransfer) {
        getMdpSingoloTransfers().add(mdpSingoloTransfer);
        mdpSingoloTransfer.setMdpReceipt(this);

        return mdpSingoloTransfer;
    }

    public MdpSingoloTransfer removeMdpSingoloTransfer(MdpSingoloTransfer mdpSingoloTransfer) {
        getMdpSingoloTransfers().remove(mdpSingoloTransfer);
        mdpSingoloTransfer.setMdpReceipt(null);

        return mdpSingoloTransfer;
    }

	public String getIdPa () {
		return idPa;
	}

	public void setIdPa ( String idPa ) {
		this.idPa = idPa;
	}

	public String getIdBrokerPa () {
		return idBrokerPa;
	}

	public void setIdBrokerPa ( String idBrokerPa ) {
		this.idBrokerPa = idBrokerPa;
	}

	public String getIdStation () {
		return idStation;
	}

	public void setIdStation ( String idStation ) {
		this.idStation = idStation;
	}

	public String getMsgInvioFallito () {
		return msgInvioFallito;
	}

	public void setMsgInvioFallito ( String msgInvioFallito ) {
		this.msgInvioFallito = msgInvioFallito;
	}

	public String getStatoInvioFruitore () {
		return statoInvioFruitore;
	}

	public void setStatoInvioFruitore ( String statoInvioFruitore ) {
		this.statoInvioFruitore = statoInvioFruitore;
	}

	@Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long id;

        private String applicationId;

        private String creditorReferenceid;

        private Date dataInvioFruitore;

        private Date dataOraInsert;

        private Date dataOraInvioFallito;

        private Date dataOraRetry;

        private Date dataOraRicezione;

        private Date dataOraUpdate;

        private BigDecimal feePsp;

        private BigDecimal idElementoMultiversamento;

        private String idPsp;

        private Boolean iuvSconosciuto;

        private String noticeNumber;

        private Boolean pagamentoInviato;

        private Date paymentDatetime;

        private BigDecimal paymentAmount;

        private String paymentMethod;

        private String pspComanyName;

        private String receiptId;

        private String transactionId;

        private Date transferDate;

        private byte [] xmlReceipt;

        private List<MdpSingoloTransfer> mdpSingoloTransfers = Collections.emptyList ();

        private Date applicationDate;

		private String idPa;

		private String idBrokerPa;

		private String idStation;

		private String msgInvioFallito;

		private String statoInvioFruitore;

        private Builder () {
        }

        public Builder withId ( Long id ) {
            this.id = id;
            return this;
        }

        public Builder withApplicationId ( String applicationId ) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder withCreditorReferenceid ( String creditorReferenceid ) {
            this.creditorReferenceid = creditorReferenceid;
            return this;
        }

        public Builder withDataInvioFruitore ( Date dataInvioFruitore ) {
            this.dataInvioFruitore = dataInvioFruitore;
            return this;
        }

        public Builder withDataOraInsert ( Date dataOraInsert ) {
            this.dataOraInsert = dataOraInsert;
            return this;
        }

        public Builder withDataOraInvioFallito ( Date dataOraInvioFallito ) {
            this.dataOraInvioFallito = dataOraInvioFallito;
            return this;
        }

        public Builder withDataOraRetry ( Date dataOraRetry ) {
            this.dataOraRetry = dataOraRetry;
            return this;
        }

        public Builder withDataOraRicezione ( Date dataOraRicezione ) {
            this.dataOraRicezione = dataOraRicezione;
            return this;
        }

        public Builder withDataOraUpdate ( Date dataOraUpdate ) {
            this.dataOraUpdate = dataOraUpdate;
            return this;
        }

        public Builder withFeePsp ( BigDecimal feePsp ) {
            this.feePsp = feePsp;
            return this;
        }

        public Builder withIdElementoMultiversamento ( BigDecimal idElementoMultiversamento ) {
            this.idElementoMultiversamento = idElementoMultiversamento;
            return this;
        }

        public Builder withIdPsp ( String idPsp ) {
            this.idPsp = idPsp;
            return this;
        }

        public Builder withIuvSconosciuto ( Boolean iuvSconosciuto ) {
            this.iuvSconosciuto = iuvSconosciuto;
            return this;
        }

        public Builder withNoticeNumber ( String noticeNumber ) {
            this.noticeNumber = noticeNumber;
            return this;
        }

        public Builder withPagamentoInviato(Boolean pagamentoInviato ) {
            this.pagamentoInviato = pagamentoInviato;
            return this;
        }

        public Builder withPaymentDatetime ( Date paymentDatetime ) {
            this.paymentDatetime = paymentDatetime;
            return this;
        }

        public Builder withPaymentAmount ( BigDecimal paymentAmount ) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public Builder withPaymentMethod ( String paymentMethod ) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder withPspComanyName ( String pspComanyName ) {
            this.pspComanyName = pspComanyName;
            return this;
        }

        public Builder withReceiptId ( String receiptId ) {
            this.receiptId = receiptId;
            return this;
        }

        public Builder withTransactionId ( String transactionId ) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withTransferDate ( Date transferDate ) {
            this.transferDate = transferDate;
            return this;
        }

        public Builder withXmlReceipt ( byte [] xmlReceipt ) {
            this.xmlReceipt = xmlReceipt;
            return this;
        }

        public Builder withMdpSingoloTransfers ( List<MdpSingoloTransfer> mdpSingoloTransfers ) {
            this.mdpSingoloTransfers = mdpSingoloTransfers;
            return this;
        }

        public Builder withApplicationDate ( Date applicationDate ) {
            this.applicationDate = applicationDate;
            return this;
        }

		public Builder withIdPa ( String idPa ) {
			this.idPa = idPa;
			return this;
		}

		public Builder withIdBrokerPa ( String idBrokerPa ) {
			this.idBrokerPa = idBrokerPa;
			return this;
		}

		public Builder withIdStation ( String idStation ) {
			this.idStation = idStation;
			return this;
		}

		public Builder withMsgInvioFallito ( String msgInvioFallito ) {
			this.msgInvioFallito = msgInvioFallito;
			return this;
		}

		public Builder withStatoInvioFruitore ( String statoInvioFruitore ) {
			this.statoInvioFruitore = statoInvioFruitore;
			return this;
		}

        public MdpReceipt build () {
            return new MdpReceipt ( this );
        }
    }

}
