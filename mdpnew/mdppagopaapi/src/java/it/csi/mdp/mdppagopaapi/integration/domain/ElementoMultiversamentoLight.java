/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the elemento_multiversamento database table.
 *
 */
@Entity
@Table ( name = "elemento_multiversamento" )
public class ElementoMultiversamentoLight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "ELEMENTO_MULTIVERSAMENTO_ID_GENERATOR", sequenceName = "multiversamento_keyid_seq", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "ELEMENTO_MULTIVERSAMENTO_ID_GENERATOR" )
    private Integer id;

    @Column ( name = "application_id" )
    private String applicationId;

    @Column ( name = "insert_date" )
    private Timestamp insertDate;

    private String iuv;

    private String modellopagamento;

    private Integer posizione;

    @Column ( name = "transaction_id" )
    private String transactionId;

    @Generated ( "SparkTools" )
    private ElementoMultiversamentoLight ( Builder builder ) {
        this.id = builder.id;
        this.applicationId = builder.applicationId;
        this.insertDate = builder.insertDate;
        this.iuv = builder.iuv;
        this.modellopagamento = builder.modellopagamento;
        this.posizione = builder.posizione;
        this.transactionId = builder.transactionId;
    }

    //bi-directional many-to-one association to MdpGetpayment
    //    @OneToMany ( mappedBy = "elementoMultiversamento" )
    //    private List<MdpGetpayment> mdpGetpayments;

    public ElementoMultiversamentoLight () {
    }

    public Integer getId () {
        return this.id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public String getApplicationId () {
        return this.applicationId;
    }

    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    public Timestamp getInsertDate () {
        return this.insertDate;
    }

    public void setInsertDate ( Timestamp insertDate ) {
        this.insertDate = insertDate;
    }

    public String getIuv () {
        return this.iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getModellopagamento () {
        return this.modellopagamento;
    }

    public void setModellopagamento ( String modellopagamento ) {
        this.modellopagamento = modellopagamento;
    }

    public Integer getPosizione () {
        return this.posizione;
    }

    public void setPosizione ( Integer posizione ) {
        this.posizione = posizione;
    }

    public String getTransactionId () {
        return this.transactionId;
    }

    public void setTransactionId ( String transactionId ) {
        this.transactionId = transactionId;
    }

    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    @Generated ( "SparkTools" )
    public static final class Builder {

        private Integer id;

        private String applicationId;

        private Timestamp insertDate;

        private String iuv;

        private String modellopagamento;

        private Integer posizione;

        private String transactionId;

        private Builder () {
        }

        public Builder withId ( Integer id ) {
            this.id = id;
            return this;
        }

        public Builder withApplicationId ( String applicationId ) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder withInsertDate ( Timestamp insertDate ) {
            this.insertDate = insertDate;
            return this;
        }

        public Builder withIuv ( String iuv ) {
            this.iuv = iuv;
            return this;
        }

        public Builder withModellopagamento ( String modellopagamento ) {
            this.modellopagamento = modellopagamento;
            return this;
        }

        public Builder withPosizione ( Integer posizione ) {
            this.posizione = posizione;
            return this;
        }

        public Builder withTransactionId ( String transactionId ) {
            this.transactionId = transactionId;
            return this;
        }

        public ElementoMultiversamentoLight build () {
            return new ElementoMultiversamentoLight ( this );
        }
    }

    //    public List<MdpGetpayment> getMdpGetpayments () {
    //        return this.mdpGetpayments;
    //    }
    //
    //    public void setMdpGetpayments ( List<MdpGetpayment> mdpGetpayments ) {
    //        this.mdpGetpayments = mdpGetpayments;
    //    }

    //    public MdpGetpayment addMdpGetpayment ( MdpGetpayment mdpGetpayment ) {
    //        getMdpGetpayments ().add ( mdpGetpayment );
    //        //.setElementoMultiversamento ( this );
    //
    //        return mdpGetpayment;
    //    }

    //    public MdpGetpayment removeMdpGetpayment ( MdpGetpayment mdpGetpayment ) {
    //        getMdpGetpayments ().remove ( mdpGetpayment );
    //        //mdpGetpayment.setElementoMultiversamento ( null );
    //
    //        return mdpGetpayment;
    //    }

}
