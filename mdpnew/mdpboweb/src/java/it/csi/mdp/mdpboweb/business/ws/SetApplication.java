/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetApplication  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private it.csi.mdp.mdpboweb.business.ws.Application app;

    private boolean overwrite;

    public SetApplication() {
    }

    public SetApplication(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           it.csi.mdp.mdpboweb.business.ws.Application app,
           boolean overwrite) {
           this.auth = auth;
           this.app = app;
           this.overwrite = overwrite;
    }


    /**
     * Gets the auth value for this SetApplication.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this SetApplication.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the app value for this SetApplication.
     * 
     * @return app
     */
    public it.csi.mdp.mdpboweb.business.ws.Application getApp() {
        return app;
    }


    /**
     * Sets the app value for this SetApplication.
     * 
     * @param app
     */
    public void setApp(it.csi.mdp.mdpboweb.business.ws.Application app) {
        this.app = app;
    }


    /**
     * Gets the overwrite value for this SetApplication.
     * 
     * @return overwrite
     */
    public boolean isOverwrite() {
        return overwrite;
    }


    /**
     * Sets the overwrite value for this SetApplication.
     * 
     * @param overwrite
     */
    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetApplication)) return false;
        SetApplication other = (SetApplication) obj;
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
            ((this.app==null && other.getApp()==null) || 
             (this.app!=null &&
              this.app.equals(other.getApp()))) &&
            this.overwrite == other.isOverwrite();
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
        if (getApp() != null) {
            _hashCode += getApp().hashCode();
        }
        _hashCode += (isOverwrite() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetApplication.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplication"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("app");
        elemField.setXmlName(new javax.xml.namespace.QName("", "app"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "application"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overwrite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "overwrite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
