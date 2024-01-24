/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetGatewayResponse  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Gateway gateway;

    public SetGatewayResponse() {
    }

    public SetGatewayResponse(
           it.csi.mdp.mdpboweb.business.ws.Gateway gateway) {
           this.gateway = gateway;
    }


    /**
     * Gets the gateway value for this SetGatewayResponse.
     * 
     * @return gateway
     */
    public it.csi.mdp.mdpboweb.business.ws.Gateway getGateway() {
        return gateway;
    }


    /**
     * Sets the gateway value for this SetGatewayResponse.
     * 
     * @param gateway
     */
    public void setGateway(it.csi.mdp.mdpboweb.business.ws.Gateway gateway) {
        this.gateway = gateway;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetGatewayResponse)) return false;
        SetGatewayResponse other = (SetGatewayResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.gateway==null && other.getGateway()==null) || 
             (this.gateway!=null &&
              this.gateway.equals(other.getGateway())));
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
        if (getGateway() != null) {
            _hashCode += getGateway().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetGatewayResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gateway");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gateway"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gateway"));
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
