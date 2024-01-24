/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Paymentmode  implements java.io.Serializable {
    private java.lang.String paymentmodeDescription;

    private java.lang.String paymentmodeId;

    private java.util.Calendar validoAl;

    private java.util.Calendar validoDal;

    public Paymentmode() {
    }

    public Paymentmode(
           java.lang.String paymentmodeDescription,
           java.lang.String paymentmodeId,
           java.util.Calendar validoAl,
           java.util.Calendar validoDal) {
           this.paymentmodeDescription = paymentmodeDescription;
           this.paymentmodeId = paymentmodeId;
           this.validoAl = validoAl;
           this.validoDal = validoDal;
    }


    /**
     * Gets the paymentmodeDescription value for this Paymentmode.
     * 
     * @return paymentmodeDescription
     */
    public java.lang.String getPaymentmodeDescription() {
        return paymentmodeDescription;
    }


    /**
     * Sets the paymentmodeDescription value for this Paymentmode.
     * 
     * @param paymentmodeDescription
     */
    public void setPaymentmodeDescription(java.lang.String paymentmodeDescription) {
        this.paymentmodeDescription = paymentmodeDescription;
    }


    /**
     * Gets the paymentmodeId value for this Paymentmode.
     * 
     * @return paymentmodeId
     */
    public java.lang.String getPaymentmodeId() {
        return paymentmodeId;
    }


    /**
     * Sets the paymentmodeId value for this Paymentmode.
     * 
     * @param paymentmodeId
     */
    public void setPaymentmodeId(java.lang.String paymentmodeId) {
        this.paymentmodeId = paymentmodeId;
    }


    /**
     * Gets the validoAl value for this Paymentmode.
     * 
     * @return validoAl
     */
    public java.util.Calendar getValidoAl() {
        return validoAl;
    }


    /**
     * Sets the validoAl value for this Paymentmode.
     * 
     * @param validoAl
     */
    public void setValidoAl(java.util.Calendar validoAl) {
        this.validoAl = validoAl;
    }


    /**
     * Gets the validoDal value for this Paymentmode.
     * 
     * @return validoDal
     */
    public java.util.Calendar getValidoDal() {
        return validoDal;
    }


    /**
     * Sets the validoDal value for this Paymentmode.
     * 
     * @param validoDal
     */
    public void setValidoDal(java.util.Calendar validoDal) {
        this.validoDal = validoDal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Paymentmode)) return false;
        Paymentmode other = (Paymentmode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentmodeDescription==null && other.getPaymentmodeDescription()==null) || 
             (this.paymentmodeDescription!=null &&
              this.paymentmodeDescription.equals(other.getPaymentmodeDescription()))) &&
            ((this.paymentmodeId==null && other.getPaymentmodeId()==null) || 
             (this.paymentmodeId!=null &&
              this.paymentmodeId.equals(other.getPaymentmodeId()))) &&
            ((this.validoAl==null && other.getValidoAl()==null) || 
             (this.validoAl!=null &&
              this.validoAl.equals(other.getValidoAl()))) &&
            ((this.validoDal==null && other.getValidoDal()==null) || 
             (this.validoDal!=null &&
              this.validoDal.equals(other.getValidoDal())));
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
        if (getPaymentmodeDescription() != null) {
            _hashCode += getPaymentmodeDescription().hashCode();
        }
        if (getPaymentmodeId() != null) {
            _hashCode += getPaymentmodeId().hashCode();
        }
        if (getValidoAl() != null) {
            _hashCode += getValidoAl().hashCode();
        }
        if (getValidoDal() != null) {
            _hashCode += getValidoDal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Paymentmode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "paymentmode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentmodeDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentmodeDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentmodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentmodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validoAl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validoAl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validoDal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validoDal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
