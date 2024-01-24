/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_marca_digitale database table.
 *
 */
@Entity
@Table ( name = "epay_t_marca_digitale" )
public class EpayTMarcaDigitale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "epay_t_marca_digitale_GENERATOR", allocationSize = 1, sequenceName = "epay_t_marca_digitale_id_marca_seq" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "epay_t_marca_digitale_GENERATOR" )
    @Column ( name = "id_marca" )
    private Long idMarca;

    private String esito;

    @Column ( name = "hash_documento" )
    private String hashDocumento;

    @Column ( name = "iubd_marca" )
    private String iubdMarca;

    @Column ( name = "iuv_marca" )
    private String iuvMarca;
    
    private String provincia;

    @Column ( name = "xml_receipt_mb" )
    private byte [] xmlReceiptMb;
    

    //bi-directional many-to-one association to EpayTPagamento
    @ManyToOne
    @JoinColumn ( name = "id_pagamento_documento" )
    private EpayTPagamento epayTPagamentoDocumentoAssociato;

    //bi-directional many-to-one association to EpayTPagamento
    @ManyToOne
    @JoinColumn ( name = "id_pagamento_marca" )
    private EpayTPagamento epayTPagamentoMarcaBollo;

    public EpayTMarcaDigitale () {
    }

    public Long getIdMarca () {
        return this.idMarca;
    }

    public void setIdMarca ( Long idMarca ) {
        this.idMarca = idMarca;
    }

    public String getEsito () {
        return this.esito;
    }

    public void setEsito ( String esito ) {
        this.esito = esito;
    }

    public String getHashDocumento () {
        return this.hashDocumento;
    }

    public void setHashDocumento ( String hashDocumento ) {
        this.hashDocumento = hashDocumento;
    }

    public String getIubdMarca () {
        return this.iubdMarca;
    }

    public void setIubdMarca ( String iubdMarca ) {
        this.iubdMarca = iubdMarca;
    }

    public String getIuvMarca () {
        return this.iuvMarca;
    }
    
    
    /**
     * @return the xmlReceiptMb
     */
    public byte [] getXmlReceiptMb () {
        return xmlReceiptMb;
    }

    
    /**
     * @param xmlReceiptMb the xmlReceiptMb to set
     */
    public void setXmlReceiptMb ( byte [] xmlReceiptMb ) {
        this.xmlReceiptMb = xmlReceiptMb;
    }

    
    /**
     * @param iuvMarca the iuvMarca to set
     */
    public void setIuvMarca ( String iuvMarca ) {
        this.iuvMarca = iuvMarca;
    }

    public String getProvincia () {
        return provincia;
    }

    
    public void setProvincia ( String provincia ) {
        this.provincia = provincia;
    }

    public EpayTPagamento getEpayTPagamentoDocumentoAssociato () {
        return epayTPagamentoDocumentoAssociato;
    }

    public void setEpayTPagamentoDocumentoAssociato ( EpayTPagamento epayTPagamentoDocumentoAssociato ) {
        this.epayTPagamentoDocumentoAssociato = epayTPagamentoDocumentoAssociato;
    }

    public EpayTPagamento getEpayTPagamentoMarcaBollo () {
        return epayTPagamentoMarcaBollo;
    }

    public void setEpayTPagamentoMarcaBollo ( EpayTPagamento epayTPagamentoMarcaBollo ) {
        this.epayTPagamentoMarcaBollo = epayTPagamentoMarcaBollo;
    }

}
