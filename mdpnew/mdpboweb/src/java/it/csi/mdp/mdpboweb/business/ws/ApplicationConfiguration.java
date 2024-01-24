/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class ApplicationConfiguration  extends it.csi.mdp.mdpboweb.business.ws.Application  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration[] applicationGatewayConfigurationsList;

    public ApplicationConfiguration() {
    }

    public ApplicationConfiguration(
           java.lang.String applicationname,
           java.lang.String cliente,
           java.lang.String esercemail,
           java.lang.String id,
           java.lang.String note,
           java.lang.String numeroverde,
           java.lang.String progetto,
           long redirectNewmdp,
           java.lang.String referentecsi,
           java.util.Calendar validoAl,
           java.util.Calendar validoDal,
           it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration[] applicationGatewayConfigurationsList) {
        super(
            applicationname,
            cliente,
            esercemail,
            id,
            note,
            numeroverde,
            progetto,
            redirectNewmdp,
            referentecsi,
            validoAl,
            validoDal);
        this.applicationGatewayConfigurationsList = applicationGatewayConfigurationsList;
    }


    /**
     * Gets the applicationGatewayConfigurationsList value for this ApplicationConfiguration.
     * 
     * @return applicationGatewayConfigurationsList
     */
    public it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration[] getApplicationGatewayConfigurationsList() {
        return applicationGatewayConfigurationsList;
    }


    /**
     * Sets the applicationGatewayConfigurationsList value for this ApplicationConfiguration.
     * 
     * @param applicationGatewayConfigurationsList
     */
    public void setApplicationGatewayConfigurationsList(it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration[] applicationGatewayConfigurationsList) {
        this.applicationGatewayConfigurationsList = applicationGatewayConfigurationsList;
    }

    public it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration getApplicationGatewayConfigurationsList(int i) {
        return this.applicationGatewayConfigurationsList[i];
    }

    public void setApplicationGatewayConfigurationsList(int i, it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration _value) {
        this.applicationGatewayConfigurationsList[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApplicationConfiguration)) return false;
        ApplicationConfiguration other = (ApplicationConfiguration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.applicationGatewayConfigurationsList==null && other.getApplicationGatewayConfigurationsList()==null) || 
             (this.applicationGatewayConfigurationsList!=null &&
              java.util.Arrays.equals(this.applicationGatewayConfigurationsList, other.getApplicationGatewayConfigurationsList())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getApplicationGatewayConfigurationsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApplicationGatewayConfigurationsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApplicationGatewayConfigurationsList(), i);
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
        new org.apache.axis.description.TypeDesc(ApplicationConfiguration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationConfiguration"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationGatewayConfigurationsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationGatewayConfigurationsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationGatewayConfiguration"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
