/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetTransazioneViewPagedResponse  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.VtransazioneResult transactionResult;

    public GetTransazioneViewPagedResponse() {
    }

    public GetTransazioneViewPagedResponse(
           it.csi.mdp.mdpboweb.business.ws.VtransazioneResult transactionResult) {
           this.transactionResult = transactionResult;
    }


    /**
     * Gets the transactionResult value for this GetTransazioneViewPagedResponse.
     * 
     * @return transactionResult
     */
    public it.csi.mdp.mdpboweb.business.ws.VtransazioneResult getTransactionResult() {
        return transactionResult;
    }


    /**
     * Sets the transactionResult value for this GetTransazioneViewPagedResponse.
     * 
     * @param transactionResult
     */
    public void setTransactionResult(it.csi.mdp.mdpboweb.business.ws.VtransazioneResult transactionResult) {
        this.transactionResult = transactionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTransazioneViewPagedResponse)) return false;
        GetTransazioneViewPagedResponse other = (GetTransazioneViewPagedResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transactionResult==null && other.getTransactionResult()==null) || 
             (this.transactionResult!=null &&
              this.transactionResult.equals(other.getTransactionResult())));
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
        if (getTransactionResult() != null) {
            _hashCode += getTransactionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTransazioneViewPagedResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewPagedResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "vtransazioneResult"));
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
