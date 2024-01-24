/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Commission  implements java.io.Serializable {
    private java.lang.String commissionDescription;

    private java.lang.String commissionId;

    public Commission() {
    }

    public Commission(
           java.lang.String commissionDescription,
           java.lang.String commissionId) {
           this.commissionDescription = commissionDescription;
           this.commissionId = commissionId;
    }


    /**
     * Gets the commissionDescription value for this Commission.
     * 
     * @return commissionDescription
     */
    public java.lang.String getCommissionDescription() {
        return commissionDescription;
    }


    /**
     * Sets the commissionDescription value for this Commission.
     * 
     * @param commissionDescription
     */
    public void setCommissionDescription(java.lang.String commissionDescription) {
        this.commissionDescription = commissionDescription;
    }


    /**
     * Gets the commissionId value for this Commission.
     * 
     * @return commissionId
     */
    public java.lang.String getCommissionId() {
        return commissionId;
    }


    /**
     * Sets the commissionId value for this Commission.
     * 
     * @param commissionId
     */
    public void setCommissionId(java.lang.String commissionId) {
        this.commissionId = commissionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Commission)) return false;
        Commission other = (Commission) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.commissionDescription==null && other.getCommissionDescription()==null) || 
             (this.commissionDescription!=null &&
              this.commissionDescription.equals(other.getCommissionDescription()))) &&
            ((this.commissionId==null && other.getCommissionId()==null) || 
             (this.commissionId!=null &&
              this.commissionId.equals(other.getCommissionId())));
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
        if (getCommissionDescription() != null) {
            _hashCode += getCommissionDescription().hashCode();
        }
        if (getCommissionId() != null) {
            _hashCode += getCommissionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Commission.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "commission"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commissionDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commissionDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commissionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commissionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
