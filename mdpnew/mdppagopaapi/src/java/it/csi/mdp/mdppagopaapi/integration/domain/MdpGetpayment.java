/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the mdp_getpayment database table.
 *
 */
@Entity
@Table(name="mdp_getpayment")
@NamedQuery(name="MdpGetpayment.findAll", query="SELECT m FROM MdpGetpayment m")
public class MdpGetpayment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "MDP_GETPAYMENT_IDGETPAYMENT_GENERATOR", sequenceName = "MDP_GETPAYMENT_ID_GETPAYMENT_SEQ", allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MDP_GETPAYMENT_IDGETPAYMENT_GENERATOR")
    @Column(name="id_getpayment")
    private Integer idGetpayment;

    @Column(name="application_id")
    private String applicationId;

    @Column(name="creditor_referenceid")
    private String creditorReferenceid;

    @Column(name="data_ora_insert")
    private Date dataOraInsert;

    @Column(name="data_ora_invio")
    private Date dataOraInvio;

    @Column(name="data_ora_update")
    private Date dataOraUpdate;

    private String description;

    @Column(name="due_date")
    private Date dueDate;

    @Column ( name = "cod_stato_getpayment" )
    private String idStatoGetpayment;

    @Column(name="payment_amount")
    private BigDecimal paymentAmount;

    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="xml_getpayment")
    private byte[] xmlGetpayment;

		@Column(name="id_pa")
		private String idPa;

		@Column(name="id_broker_pa")
		private String idBrokerPa;

		@Column(name="id_station")
		private String idStation;

    //    //bi-directional many-to-one association to ElementoMultiversamento
    //    @ManyToOne(fetch=FetchType.LAZY)
    //    @JoinColumn(name="id_elemento_multiversamento")
    //    private ElementoMultiversamento elementoMultiversamento;

    @Column ( name = "id_elemento_multiversamento" )
    private int idMultiversamento;


    @Generated ( "SparkTools" )
    private MdpGetpayment ( Builder builder ) {
        this.idGetpayment = builder.idGetpayment;
        this.applicationId = builder.applicationId;
        this.creditorReferenceid = builder.creditorReferenceid;
        this.dataOraInsert = builder.dataOraInsert;
        this.dataOraInvio = builder.dataOraInvio;
        this.dataOraUpdate = builder.dataOraUpdate;
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.idStatoGetpayment = builder.idStatoGetpayment;
        this.paymentAmount = builder.paymentAmount;
        this.transactionId = builder.transactionId;
        this.xmlGetpayment = builder.xmlGetpayment;
        this.idMultiversamento = builder.idMultiversamento;
				this.idPa = builder.idPa;
				this.idBrokerPa = builder.idBrokerPa;
				this.idStation = builder.idStation;
    }


    public int getIdMultiversamento () {
        return idMultiversamento;
    }

    public void setIdMultiversamento ( int idMultiversamento ) {
        this.idMultiversamento = idMultiversamento;
    }

    public MdpGetpayment() {
    }

    public Integer getIdGetpayment() {
        return this.idGetpayment;
    }

    public void setIdGetpayment(Integer idGetpayment) {
        this.idGetpayment = idGetpayment;
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

    public Date getDataOraInsert () {
        return this.dataOraInsert;
    }

    public void setDataOraInsert ( Date dataOraInsert ) {
        this.dataOraInsert = dataOraInsert;
    }

    public Date getDataOraInvio () {
        return this.dataOraInvio;
    }

    public void setDataOraInvio ( Date dataOraInvio ) {
        this.dataOraInvio = dataOraInvio;
    }

    public Date getDataOraUpdate () {
        return this.dataOraUpdate;
    }

    public void setDataOraUpdate ( Date dataOraUpdate ) {
        this.dataOraUpdate = dataOraUpdate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate () {
        return this.dueDate;
    }

    public void setDueDate ( Date dueDate ) {
        this.dueDate = dueDate;
    }

    public String getIdStatoGetpayment () {
        return this.idStatoGetpayment;
    }

    public void setIdStatoGetpayment ( String idStatoGetpayment ) {
        this.idStatoGetpayment = idStatoGetpayment;
    }

    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public byte[] getXmlGetpayment() {
        return this.xmlGetpayment;
    }

    public void setXmlGetpayment(byte[] xmlGetpayment) {
        this.xmlGetpayment = xmlGetpayment;
    }

	public String getIdPa() {
		return idPa;
	}

	public void setIdPa(String idPa) {
		this.idPa = idPa;
	}

	public String getIdBrokerPa() {
		return idBrokerPa;
	}

	public void setIdBrokerPa(String idBrokerPa) {
		this.idBrokerPa = idBrokerPa;
	}

	public String getIdStation() {
		return idStation;
	}

	public void setIdStation(String idStation) {
		this.idStation = idStation;
	}

	@Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    @Generated ( "SparkTools" )
    public static final class Builder {

        private Integer idGetpayment;

        private String applicationId;

        private String creditorReferenceid;

        private Date dataOraInsert;

        private Date dataOraInvio;

        private Date dataOraUpdate;

        private String description;

        private Date dueDate;

        private String idStatoGetpayment;

        private BigDecimal paymentAmount;

        private String transactionId;

        private byte [] xmlGetpayment;

        private int idMultiversamento;

				private String idPa;

				private String idBrokerPa;

				private String idStation;

        private Builder () {
        }

        public Builder withIdGetpayment ( Integer idGetpayment ) {
            this.idGetpayment = idGetpayment;
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

        public Builder withDataOraInsert ( Date dataOraInsert ) {
            this.dataOraInsert = dataOraInsert;
            return this;
        }

        public Builder withDataOraInvio ( Date dataOraInvio ) {
            this.dataOraInvio = dataOraInvio;
            return this;
        }

        public Builder withDataOraUpdate ( Date dataOraUpdate ) {
            this.dataOraUpdate = dataOraUpdate;
            return this;
        }

        public Builder withDescription ( String description ) {
            this.description = description;
            return this;
        }

        public Builder withDueDate ( Date dueDate ) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder withIdStatoGetpayment ( String idStatoGetpayment ) {
            this.idStatoGetpayment = idStatoGetpayment;
            return this;
        }

        public Builder withPaymentAmount ( BigDecimal paymentAmount ) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public Builder withTransactionId ( String transactionId ) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withXmlGetpayment ( byte [] xmlGetpayment ) {
            this.xmlGetpayment = xmlGetpayment;
            return this;
        }

        public Builder withIdMultiversamento ( int idMultiversamento ) {
            this.idMultiversamento = idMultiversamento;
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

        public MdpGetpayment build () {
            return new MdpGetpayment ( this );
        }
    }

    //    public ElementoMultiversamento getElementoMultiversamento() {
    //        return this.elementoMultiversamento;
    //    }
    //
    //    public void setElementoMultiversamento(ElementoMultiversamento elementoMultiversamento) {
    //        this.elementoMultiversamento = elementoMultiversamento;
    //    }

}
