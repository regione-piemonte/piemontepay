/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetMdpCurrencyByKey  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.String gatewayid;

    private java.lang.String mdpcurrencycode;

    private java.lang.String gtwcurrencycode;

    public GetMdpCurrencyByKey() {
    }

    public GetMdpCurrencyByKey(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.String gatewayid,
           java.lang.String mdpcurrencycode,
           java.lang.String gtwcurrencycode) {
           this.auth = auth;
           this.gatewayid = gatewayid;
           this.mdpcurrencycode = mdpcurrencycode;
           this.gtwcurrencycode = gtwcurrencycode;
    }


    /**
     * Gets the auth value for this GetMdpCurrencyByKey.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetMdpCurrencyByKey.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the gatewayid value for this GetMdpCurrencyByKey.
     * 
     * @return gatewayid
     */
    public java.lang.String getGatewayid() {
        return gatewayid;
    }


    /**
     * Sets the gatewayid value for this GetMdpCurrencyByKey.
     * 
     * @param gatewayid
     */
    public void setGatewayid(java.lang.String gatewayid) {
        this.gatewayid = gatewayid;
    }


    /**
     * Gets the mdpcurrencycode value for this GetMdpCurrencyByKey.
     * 
     * @return mdpcurrencycode
     */
    public java.lang.String getMdpcurrencycode() {
        return mdpcurrencycode;
    }


    /**
     * Sets the mdpcurrencycode value for this GetMdpCurrencyByKey.
     * 
     * @param mdpcurrencycode
     */
    public void setMdpcurrencycode(java.lang.String mdpcurrencycode) {
        this.mdpcurrencycode = mdpcurrencycode;
    }


    /**
     * Gets the gtwcurrencycode value for this GetMdpCurrencyByKey.
     * 
     * @return gtwcurrencycode
     */
    public java.lang.String getGtwcurrencycode() {
        return gtwcurrencycode;
    }


    /**
     * Sets the gtwcurrencycode value for this GetMdpCurrencyByKey.
     * 
     * @param gtwcurrencycode
     */
    public void setGtwcurrencycode(java.lang.String gtwcurrencycode) {
        this.gtwcurrencycode = gtwcurrencycode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMdpCurrencyByKey)) return false;
        GetMdpCurrencyByKey other = (GetMdpCurrencyByKey) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auth==null && other.getAuth()==null) || 
             (this.auth!=null &&
              this.auth.equals(other.getAuth()))) &&
            ((this.gatewayid==null && other.getGatewayid()==null) || 
             (this.gatewayid!=null &&
              this.gatewayid.equals(other.getGatewayid()))) &&
            ((this.mdpcurrencycode==null && other.getMdpcurrencycode()==null) || 
             (this.mdpcurrencycode!=null &&
              this.mdpcurrencycode.equals(other.getMdpcurrencycode()))) &&
            ((this.gtwcurrencycode==null && other.getGtwcurrencycode()==null) || 
             (this.gtwcurrencycode!=null &&
              this.gtwcurrencycode.equals(other.getGtwcurrencycode())));
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
        if (getAuth() != null) {
            _hashCode += getAuth().hashCode();
        }
        if (getGatewayid() != null) {
            _hashCode += getGatewayid().hashCode();
        }
        if (getMdpcurrencycode() != null) {
            _hashCode += getMdpcurrencycode().hashCode();
        }
        if (getGtwcurrencycode() != null) {
            _hashCode += getGtwcurrencycode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMdpCurrencyByKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
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
        elemField.setFieldName("mdpcurrencycode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mdpcurrencycode"));
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
