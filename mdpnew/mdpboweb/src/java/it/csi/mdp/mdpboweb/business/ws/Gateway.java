/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Gateway  implements java.io.Serializable {
    private java.lang.String gatewayDescription;

    private java.lang.String gatewayId;

    private java.lang.String gatewayProvider;

    private java.lang.String gatewayservicename;

    private java.util.Calendar validoAl;

    private java.util.Calendar validoDal;

    public Gateway() {
    }

    public Gateway(
           java.lang.String gatewayDescription,
           java.lang.String gatewayId,
           java.lang.String gatewayProvider,
           java.lang.String gatewayservicename,
           java.util.Calendar validoAl,
           java.util.Calendar validoDal) {
           this.gatewayDescription = gatewayDescription;
           this.gatewayId = gatewayId;
           this.gatewayProvider = gatewayProvider;
           this.gatewayservicename = gatewayservicename;
           this.validoAl = validoAl;
           this.validoDal = validoDal;
    }


    /**
     * Gets the gatewayDescription value for this Gateway.
     * 
     * @return gatewayDescription
     */
    public java.lang.String getGatewayDescription() {
        return gatewayDescription;
    }


    /**
     * Sets the gatewayDescription value for this Gateway.
     * 
     * @param gatewayDescription
     */
    public void setGatewayDescription(java.lang.String gatewayDescription) {
        this.gatewayDescription = gatewayDescription;
    }


    /**
     * Gets the gatewayId value for this Gateway.
     * 
     * @return gatewayId
     */
    public java.lang.String getGatewayId() {
        return gatewayId;
    }


    /**
     * Sets the gatewayId value for this Gateway.
     * 
     * @param gatewayId
     */
    public void setGatewayId(java.lang.String gatewayId) {
        this.gatewayId = gatewayId;
    }


    /**
     * Gets the gatewayProvider value for this Gateway.
     * 
     * @return gatewayProvider
     */
    public java.lang.String getGatewayProvider() {
        return gatewayProvider;
    }


    /**
     * Sets the gatewayProvider value for this Gateway.
     * 
     * @param gatewayProvider
     */
    public void setGatewayProvider(java.lang.String gatewayProvider) {
        this.gatewayProvider = gatewayProvider;
    }


    /**
     * Gets the gatewayservicename value for this Gateway.
     * 
     * @return gatewayservicename
     */
    public java.lang.String getGatewayservicename() {
        return gatewayservicename;
    }


    /**
     * Sets the gatewayservicename value for this Gateway.
     * 
     * @param gatewayservicename
     */
    public void setGatewayservicename(java.lang.String gatewayservicename) {
        this.gatewayservicename = gatewayservicename;
    }


    /**
     * Gets the validoAl value for this Gateway.
     * 
     * @return validoAl
     */
    public java.util.Calendar getValidoAl() {
        return validoAl;
    }


    /**
     * Sets the validoAl value for this Gateway.
     * 
     * @param validoAl
     */
    public void setValidoAl(java.util.Calendar validoAl) {
        this.validoAl = validoAl;
    }


    /**
     * Gets the validoDal value for this Gateway.
     * 
     * @return validoDal
     */
    public java.util.Calendar getValidoDal() {
        return validoDal;
    }


    /**
     * Sets the validoDal value for this Gateway.
     * 
     * @param validoDal
     */
    public void setValidoDal(java.util.Calendar validoDal) {
        this.validoDal = validoDal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Gateway)) return false;
        Gateway other = (Gateway) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.gatewayDescription==null && other.getGatewayDescription()==null) || 
             (this.gatewayDescription!=null &&
              this.gatewayDescription.equals(other.getGatewayDescription()))) &&
            ((this.gatewayId==null && other.getGatewayId()==null) || 
             (this.gatewayId!=null &&
              this.gatewayId.equals(other.getGatewayId()))) &&
            ((this.gatewayProvider==null && other.getGatewayProvider()==null) || 
             (this.gatewayProvider!=null &&
              this.gatewayProvider.equals(other.getGatewayProvider()))) &&
            ((this.gatewayservicename==null && other.getGatewayservicename()==null) || 
             (this.gatewayservicename!=null &&
              this.gatewayservicename.equals(other.getGatewayservicename()))) &&
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
        if (getGatewayDescription() != null) {
            _hashCode += getGatewayDescription().hashCode();
        }
        if (getGatewayId() != null) {
            _hashCode += getGatewayId().hashCode();
        }
        if (getGatewayProvider() != null) {
            _hashCode += getGatewayProvider().hashCode();
        }
        if (getGatewayservicename() != null) {
            _hashCode += getGatewayservicename().hashCode();
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
        new org.apache.axis.description.TypeDesc(Gateway.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gateway"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayProvider");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayProvider"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayservicename");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayservicename"));
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
