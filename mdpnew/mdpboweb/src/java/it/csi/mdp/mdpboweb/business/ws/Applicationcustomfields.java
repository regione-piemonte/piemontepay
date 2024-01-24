/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Applicationcustomfields  implements java.io.Serializable {
    private java.lang.String applicationid;

    private java.lang.String fielddescription;

    private java.lang.String fieldname;

    private java.lang.String fieldvalue;

    private java.lang.String gatewayId;

    private java.lang.String keyid;

    public Applicationcustomfields() {
    }

    public Applicationcustomfields(
           java.lang.String applicationid,
           java.lang.String fielddescription,
           java.lang.String fieldname,
           java.lang.String fieldvalue,
           java.lang.String gatewayId,
           java.lang.String keyid) {
           this.applicationid = applicationid;
           this.fielddescription = fielddescription;
           this.fieldname = fieldname;
           this.fieldvalue = fieldvalue;
           this.gatewayId = gatewayId;
           this.keyid = keyid;
    }


    /**
     * Gets the applicationid value for this Applicationcustomfields.
     * 
     * @return applicationid
     */
    public java.lang.String getApplicationid() {
        return applicationid;
    }


    /**
     * Sets the applicationid value for this Applicationcustomfields.
     * 
     * @param applicationid
     */
    public void setApplicationid(java.lang.String applicationid) {
        this.applicationid = applicationid;
    }


    /**
     * Gets the fielddescription value for this Applicationcustomfields.
     * 
     * @return fielddescription
     */
    public java.lang.String getFielddescription() {
        return fielddescription;
    }


    /**
     * Sets the fielddescription value for this Applicationcustomfields.
     * 
     * @param fielddescription
     */
    public void setFielddescription(java.lang.String fielddescription) {
        this.fielddescription = fielddescription;
    }


    /**
     * Gets the fieldname value for this Applicationcustomfields.
     * 
     * @return fieldname
     */
    public java.lang.String getFieldname() {
        return fieldname;
    }


    /**
     * Sets the fieldname value for this Applicationcustomfields.
     * 
     * @param fieldname
     */
    public void setFieldname(java.lang.String fieldname) {
        this.fieldname = fieldname;
    }


    /**
     * Gets the fieldvalue value for this Applicationcustomfields.
     * 
     * @return fieldvalue
     */
    public java.lang.String getFieldvalue() {
        return fieldvalue;
    }


    /**
     * Sets the fieldvalue value for this Applicationcustomfields.
     * 
     * @param fieldvalue
     */
    public void setFieldvalue(java.lang.String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }


    /**
     * Gets the gatewayId value for this Applicationcustomfields.
     * 
     * @return gatewayId
     */
    public java.lang.String getGatewayId() {
        return gatewayId;
    }


    /**
     * Sets the gatewayId value for this Applicationcustomfields.
     * 
     * @param gatewayId
     */
    public void setGatewayId(java.lang.String gatewayId) {
        this.gatewayId = gatewayId;
    }


    /**
     * Gets the keyid value for this Applicationcustomfields.
     * 
     * @return keyid
     */
    public java.lang.String getKeyid() {
        return keyid;
    }


    /**
     * Sets the keyid value for this Applicationcustomfields.
     * 
     * @param keyid
     */
    public void setKeyid(java.lang.String keyid) {
        this.keyid = keyid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Applicationcustomfields)) return false;
        Applicationcustomfields other = (Applicationcustomfields) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationid==null && other.getApplicationid()==null) || 
             (this.applicationid!=null &&
              this.applicationid.equals(other.getApplicationid()))) &&
            ((this.fielddescription==null && other.getFielddescription()==null) || 
             (this.fielddescription!=null &&
              this.fielddescription.equals(other.getFielddescription()))) &&
            ((this.fieldname==null && other.getFieldname()==null) || 
             (this.fieldname!=null &&
              this.fieldname.equals(other.getFieldname()))) &&
            ((this.fieldvalue==null && other.getFieldvalue()==null) || 
             (this.fieldvalue!=null &&
              this.fieldvalue.equals(other.getFieldvalue()))) &&
            ((this.gatewayId==null && other.getGatewayId()==null) || 
             (this.gatewayId!=null &&
              this.gatewayId.equals(other.getGatewayId()))) &&
            ((this.keyid==null && other.getKeyid()==null) || 
             (this.keyid!=null &&
              this.keyid.equals(other.getKeyid())));
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
        if (getApplicationid() != null) {
            _hashCode += getApplicationid().hashCode();
        }
        if (getFielddescription() != null) {
            _hashCode += getFielddescription().hashCode();
        }
        if (getFieldname() != null) {
            _hashCode += getFieldname().hashCode();
        }
        if (getFieldvalue() != null) {
            _hashCode += getFieldvalue().hashCode();
        }
        if (getGatewayId() != null) {
            _hashCode += getGatewayId().hashCode();
        }
        if (getKeyid() != null) {
            _hashCode += getKeyid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Applicationcustomfields.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationcustomfields"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fielddescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fielddescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldvalue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldvalue"));
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
        elemField.setFieldName("keyid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keyid"));
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
