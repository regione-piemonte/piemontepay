/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class CodiciEsitoPagamentoDTO  extends it.csi.mdp.mdpboweb.business.ws.BaseDto  implements java.io.Serializable {
    private java.lang.String descrizione;

    private java.lang.Integer idEsitoPagamento;

    public CodiciEsitoPagamentoDTO() {
    }

    public CodiciEsitoPagamentoDTO(
           java.lang.String descrizione,
           java.lang.Integer idEsitoPagamento) {
        this.descrizione = descrizione;
        this.idEsitoPagamento = idEsitoPagamento;
    }


    /**
     * Gets the descrizione value for this CodiciEsitoPagamentoDTO.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this CodiciEsitoPagamentoDTO.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the idEsitoPagamento value for this CodiciEsitoPagamentoDTO.
     * 
     * @return idEsitoPagamento
     */
    public java.lang.Integer getIdEsitoPagamento() {
        return idEsitoPagamento;
    }


    /**
     * Sets the idEsitoPagamento value for this CodiciEsitoPagamentoDTO.
     * 
     * @param idEsitoPagamento
     */
    public void setIdEsitoPagamento(java.lang.Integer idEsitoPagamento) {
        this.idEsitoPagamento = idEsitoPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodiciEsitoPagamentoDTO)) return false;
        CodiciEsitoPagamentoDTO other = (CodiciEsitoPagamentoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.idEsitoPagamento==null && other.getIdEsitoPagamento()==null) || 
             (this.idEsitoPagamento!=null &&
              this.idEsitoPagamento.equals(other.getIdEsitoPagamento())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getIdEsitoPagamento() != null) {
            _hashCode += getIdEsitoPagamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodiciEsitoPagamentoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "codiciEsitoPagamentoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEsitoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEsitoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
