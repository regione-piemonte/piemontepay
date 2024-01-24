/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpCurrency  implements java.io.Serializable {
    private java.lang.String currencydescr;

    private java.lang.String enabled;

    private java.lang.String gatewayid;

    private java.lang.String gtwcurrencycode;

    private java.lang.String mdpcurrencycode;

    public MdpCurrency() {
    }

    public MdpCurrency(
           java.lang.String currencydescr,
           java.lang.String enabled,
           java.lang.String gatewayid,
           java.lang.String gtwcurrencycode,
           java.lang.String mdpcurrencycode) {
           this.currencydescr = currencydescr;
           this.enabled = enabled;
           this.gatewayid = gatewayid;
           this.gtwcurrencycode = gtwcurrencycode;
           this.mdpcurrencycode = mdpcurrencycode;
    }


    /**
     * Gets the currencydescr value for this MdpCurrency.
     * 
     * @return currencydescr
     */
    public java.lang.String getCurrencydescr() {
        return currencydescr;
    }


    /**
     * Sets the currencydescr value for this MdpCurrency.
     * 
     * @param currencydescr
     */
    public void setCurrencydescr(java.lang.String currencydescr) {
        this.currencydescr = currencydescr;
    }


    /**
     * Gets the enabled value for this MdpCurrency.
     * 
     * @return enabled
     */
    public java.lang.String getEnabled() {
        return enabled;
    }


    /**
     * Sets the enabled value for this MdpCurrency.
     * 
     * @param enabled
     */
    public void setEnabled(java.lang.String enabled) {
        this.enabled = enabled;
    }


    /**
     * Gets the gatewayid value for this MdpCurrency.
     * 
     * @return gatewayid
     */
    public java.lang.String getGatewayid() {
        return gatewayid;
    }


    /**
     * Sets the gatewayid value for this MdpCurrency.
     * 
     * @param gatewayid
     */
    public void setGatewayid(java.lang.String gatewayid) {
        this.gatewayid = gatewayid;
    }


    /**
     * Gets the gtwcurrencycode value for this MdpCurrency.
     * 
     * @return gtwcurrencycode
     */
    public java.lang.String getGtwcurrencycode() {
        return gtwcurrencycode;
    }


    /**
     * Sets the gtwcurrencycode value for this MdpCurrency.
     * 
     * @param gtwcurrencycode
     */
    public void setGtwcurrencycode(java.lang.String gtwcurrencycode) {
        this.gtwcurrencycode = gtwcurrencycode;
    }


    /**
     * Gets the mdpcurrencycode value for this MdpCurrency.
     * 
     * @return mdpcurrencycode
     */
    public java.lang.String getMdpcurrencycode() {
        return mdpcurrencycode;
    }


    /**
     * Sets the mdpcurrencycode value for this MdpCurrency.
     * 
     * @param mdpcurrencycode
     */
    public void setMdpcurrencycode(java.lang.String mdpcurrencycode) {
        this.mdpcurrencycode = mdpcurrencycode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdpCurrency)) return false;
        MdpCurrency other = (MdpCurrency) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.currencydescr==null && other.getCurrencydescr()==null) || 
             (this.currencydescr!=null &&
              this.currencydescr.equals(other.getCurrencydescr()))) &&
            ((this.enabled==null && other.getEnabled()==null) || 
             (this.enabled!=null &&
              this.enabled.equals(other.getEnabled()))) &&
            ((this.gatewayid==null && other.getGatewayid()==null) || 
             (this.gatewayid!=null &&
              this.gatewayid.equals(other.getGatewayid()))) &&
            ((this.gtwcurrencycode==null && other.getGtwcurrencycode()==null) || 
             (this.gtwcurrencycode!=null &&
              this.gtwcurrencycode.equals(other.getGtwcurrencycode()))) &&
            ((this.mdpcurrencycode==null && other.getMdpcurrencycode()==null) || 
             (this.mdpcurrencycode!=null &&
              this.mdpcurrencycode.equals(other.getMdpcurrencycode())));
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
        if (getCurrencydescr() != null) {
            _hashCode += getCurrencydescr().hashCode();
        }
        if (getEnabled() != null) {
            _hashCode += getEnabled().hashCode();
        }
        if (getGatewayid() != null) {
            _hashCode += getGatewayid().hashCode();
        }
        if (getGtwcurrencycode() != null) {
            _hashCode += getGtwcurrencycode().hashCode();
        }
        if (getMdpcurrencycode() != null) {
            _hashCode += getMdpcurrencycode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MdpCurrency.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpCurrency"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencydescr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currencydescr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("gtwcurrencycode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gtwcurrencycode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mdpcurrencycode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mdpcurrencycode"));
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
