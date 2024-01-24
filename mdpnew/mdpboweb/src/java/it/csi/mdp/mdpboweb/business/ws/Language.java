/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Language  implements java.io.Serializable {
    private java.lang.String enabled;

    private java.lang.String gatewayid;

    private java.lang.String gtwlanguagecode;

    private java.lang.String languagedescr;

    private java.lang.String mdplanguagecode;

    public Language() {
    }

    public Language(
           java.lang.String enabled,
           java.lang.String gatewayid,
           java.lang.String gtwlanguagecode,
           java.lang.String languagedescr,
           java.lang.String mdplanguagecode) {
           this.enabled = enabled;
           this.gatewayid = gatewayid;
           this.gtwlanguagecode = gtwlanguagecode;
           this.languagedescr = languagedescr;
           this.mdplanguagecode = mdplanguagecode;
    }


    /**
     * Gets the enabled value for this Language.
     * 
     * @return enabled
     */
    public java.lang.String getEnabled() {
        return enabled;
    }


    /**
     * Sets the enabled value for this Language.
     * 
     * @param enabled
     */
    public void setEnabled(java.lang.String enabled) {
        this.enabled = enabled;
    }


    /**
     * Gets the gatewayid value for this Language.
     * 
     * @return gatewayid
     */
    public java.lang.String getGatewayid() {
        return gatewayid;
    }


    /**
     * Sets the gatewayid value for this Language.
     * 
     * @param gatewayid
     */
    public void setGatewayid(java.lang.String gatewayid) {
        this.gatewayid = gatewayid;
    }


    /**
     * Gets the gtwlanguagecode value for this Language.
     * 
     * @return gtwlanguagecode
     */
    public java.lang.String getGtwlanguagecode() {
        return gtwlanguagecode;
    }


    /**
     * Sets the gtwlanguagecode value for this Language.
     * 
     * @param gtwlanguagecode
     */
    public void setGtwlanguagecode(java.lang.String gtwlanguagecode) {
        this.gtwlanguagecode = gtwlanguagecode;
    }


    /**
     * Gets the languagedescr value for this Language.
     * 
     * @return languagedescr
     */
    public java.lang.String getLanguagedescr() {
        return languagedescr;
    }


    /**
     * Sets the languagedescr value for this Language.
     * 
     * @param languagedescr
     */
    public void setLanguagedescr(java.lang.String languagedescr) {
        this.languagedescr = languagedescr;
    }


    /**
     * Gets the mdplanguagecode value for this Language.
     * 
     * @return mdplanguagecode
     */
    public java.lang.String getMdplanguagecode() {
        return mdplanguagecode;
    }


    /**
     * Sets the mdplanguagecode value for this Language.
     * 
     * @param mdplanguagecode
     */
    public void setMdplanguagecode(java.lang.String mdplanguagecode) {
        this.mdplanguagecode = mdplanguagecode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Language)) return false;
        Language other = (Language) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enabled==null && other.getEnabled()==null) || 
             (this.enabled!=null &&
              this.enabled.equals(other.getEnabled()))) &&
            ((this.gatewayid==null && other.getGatewayid()==null) || 
             (this.gatewayid!=null &&
              this.gatewayid.equals(other.getGatewayid()))) &&
            ((this.gtwlanguagecode==null && other.getGtwlanguagecode()==null) || 
             (this.gtwlanguagecode!=null &&
              this.gtwlanguagecode.equals(other.getGtwlanguagecode()))) &&
            ((this.languagedescr==null && other.getLanguagedescr()==null) || 
             (this.languagedescr!=null &&
              this.languagedescr.equals(other.getLanguagedescr()))) &&
            ((this.mdplanguagecode==null && other.getMdplanguagecode()==null) || 
             (this.mdplanguagecode!=null &&
              this.mdplanguagecode.equals(other.getMdplanguagecode())));
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
        if (getEnabled() != null) {
            _hashCode += getEnabled().hashCode();
        }
        if (getGatewayid() != null) {
            _hashCode += getGatewayid().hashCode();
        }
        if (getGtwlanguagecode() != null) {
            _hashCode += getGtwlanguagecode().hashCode();
        }
        if (getLanguagedescr() != null) {
            _hashCode += getLanguagedescr().hashCode();
        }
        if (getMdplanguagecode() != null) {
            _hashCode += getMdplanguagecode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Language.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "language"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enabled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gtwlanguagecode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gtwlanguagecode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("languagedescr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "languagedescr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mdplanguagecode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mdplanguagecode"));
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
