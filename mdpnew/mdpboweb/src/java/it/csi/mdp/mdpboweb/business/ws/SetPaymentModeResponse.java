/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetPaymentModeResponse  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Paymentmode paymentmode;

    public SetPaymentModeResponse() {
    }

    public SetPaymentModeResponse(
           it.csi.mdp.mdpboweb.business.ws.Paymentmode paymentmode) {
           this.paymentmode = paymentmode;
    }


    /**
     * Gets the paymentmode value for this SetPaymentModeResponse.
     * 
     * @return paymentmode
     */
    public it.csi.mdp.mdpboweb.business.ws.Paymentmode getPaymentmode() {
        return paymentmode;
    }


    /**
     * Sets the paymentmode value for this SetPaymentModeResponse.
     * 
     * @param paymentmode
     */
    public void setPaymentmode(it.csi.mdp.mdpboweb.business.ws.Paymentmode paymentmode) {
        this.paymentmode = paymentmode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetPaymentModeResponse)) return false;
        SetPaymentModeResponse other = (SetPaymentModeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentmode==null && other.getPaymentmode()==null) || 
             (this.paymentmode!=null &&
              this.paymentmode.equals(other.getPaymentmode())));
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
        if (getPaymentmode() != null) {
            _hashCode += getPaymentmode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetPaymentModeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setPaymentModeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentmode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentmode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "paymentmode"));
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
