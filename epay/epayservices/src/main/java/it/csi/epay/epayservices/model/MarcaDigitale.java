/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class MarcaDigitale implements Serializable {

    private static final long serialVersionUID = 2376290200845290418L;

    private Long idMarca;

    private String esito;

    private String hashDocumento;

    private String iubdMarca;

    private String iuvMarca;
    
    private String provincia;
    
    private byte[] xmlReceiptMb;

    private Pagamento epayTPagamentoDocumentoAssociato;

    private Pagamento epayTPagamentoMarcaBollo;

    public Long getIdMarca () {
        return idMarca;
    }

    public void setIdMarca ( Long idMarca ) {
        this.idMarca = idMarca;
    }

    public String getEsito () {
        return esito;
    }

    public void setEsito ( String esito ) {
        this.esito = esito;
    }

    public String getHashDocumento () {
        return hashDocumento;
    }

    public void setHashDocumento ( String hashDocumento ) {
        this.hashDocumento = hashDocumento;
    }

    public String getIubdMarca () {
        return iubdMarca;
    }

    public void setIubdMarca ( String iubdMarca ) {
        this.iubdMarca = iubdMarca;
    }

    public String getIuvMarca () {
        return iuvMarca;
    }

    public void setIuvMarca ( String iuvMarca ) {
        this.iuvMarca = iuvMarca;
    }
    
    public String getProvincia () {
        return provincia;
    }

    
    public void setProvincia ( String provincia ) {
        this.provincia = provincia;
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

    public Pagamento getEpayTPagamentoDocumentoAssociato () {
        return epayTPagamentoDocumentoAssociato;
    }

    public void setEpayTPagamentoDocumentoAssociato ( Pagamento epayTPagamentoDocumentoAssociato ) {
        this.epayTPagamentoDocumentoAssociato = epayTPagamentoDocumentoAssociato;
    }

    public Pagamento getEpayTPagamentoMarcaBollo () {
        return epayTPagamentoMarcaBollo;
    }

    public void setEpayTPagamentoMarcaBollo ( Pagamento epayTPagamentoMarcaBollo ) {
        this.epayTPagamentoMarcaBollo = epayTPagamentoMarcaBollo;
    }

}
