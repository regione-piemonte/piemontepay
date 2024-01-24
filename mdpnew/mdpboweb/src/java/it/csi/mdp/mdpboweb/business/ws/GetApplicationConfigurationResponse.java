/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetApplicationConfigurationResponse  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.ApplicationConfiguration applicationConfiguration;

    public GetApplicationConfigurationResponse() {
    }

    public GetApplicationConfigurationResponse(
           it.csi.mdp.mdpboweb.business.ws.ApplicationConfiguration applicationConfiguration) {
           this.applicationConfiguration = applicationConfiguration;
    }


    /**
     * Gets the applicationConfiguration value for this GetApplicationConfigurationResponse.
     * 
     * @return applicationConfiguration
     */
    public it.csi.mdp.mdpboweb.business.ws.ApplicationConfiguration getApplicationConfiguration() {
        return applicationConfiguration;
    }


    /**
     * Sets the applicationConfiguration value for this GetApplicationConfigurationResponse.
     * 
     * @param applicationConfiguration
     */
    public void setApplicationConfiguration(it.csi.mdp.mdpboweb.business.ws.ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetApplicationConfigurationResponse)) return false;
        GetApplicationConfigurationResponse other = (GetApplicationConfigurationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationConfiguration==null && other.getApplicationConfiguration()==null) || 
             (this.applicationConfiguration!=null &&
              this.applicationConfiguration.equals(other.getApplicationConfiguration())));
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
        if (getApplicationConfiguration() != null) {
            _hashCode += getApplicationConfiguration().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetApplicationConfigurationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationConfigurationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationConfiguration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationConfiguration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationConfiguration"));
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
