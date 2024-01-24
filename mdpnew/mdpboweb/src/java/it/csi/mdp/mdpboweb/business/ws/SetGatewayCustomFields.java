/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetGatewayCustomFields  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[] gatewayCustomFieldsList;

    public SetGatewayCustomFields() {
    }

    public SetGatewayCustomFields(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[] gatewayCustomFieldsList) {
           this.auth = auth;
           this.gatewayCustomFieldsList = gatewayCustomFieldsList;
    }


    /**
     * Gets the auth value for this SetGatewayCustomFields.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this SetGatewayCustomFields.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the gatewayCustomFieldsList value for this SetGatewayCustomFields.
     * 
     * @return gatewayCustomFieldsList
     */
    public it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[] getGatewayCustomFieldsList() {
        return gatewayCustomFieldsList;
    }


    /**
     * Sets the gatewayCustomFieldsList value for this SetGatewayCustomFields.
     * 
     * @param gatewayCustomFieldsList
     */
    public void setGatewayCustomFieldsList(it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[] gatewayCustomFieldsList) {
        this.gatewayCustomFieldsList = gatewayCustomFieldsList;
    }

    public it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields getGatewayCustomFieldsList(int i) {
        return this.gatewayCustomFieldsList[i];
    }

    public void setGatewayCustomFieldsList(int i, it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields _value) {
        this.gatewayCustomFieldsList[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetGatewayCustomFields)) return false;
        SetGatewayCustomFields other = (SetGatewayCustomFields) obj;
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
            ((this.gatewayCustomFieldsList==null && other.getGatewayCustomFieldsList()==null) || 
             (this.gatewayCustomFieldsList!=null &&
              java.util.Arrays.equals(this.gatewayCustomFieldsList, other.getGatewayCustomFieldsList())));
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
        if (getGatewayCustomFieldsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGatewayCustomFieldsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGatewayCustomFieldsList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetGatewayCustomFields.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayCustomFields"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayCustomFieldsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayCustomFieldsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gatewaycustomfields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
