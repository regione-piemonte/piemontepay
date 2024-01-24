/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetApplicationCustomFields  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] appcustfields;

    public SetApplicationCustomFields() {
    }

    public SetApplicationCustomFields(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] appcustfields) {
           this.auth = auth;
           this.appcustfields = appcustfields;
    }


    /**
     * Gets the auth value for this SetApplicationCustomFields.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this SetApplicationCustomFields.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the appcustfields value for this SetApplicationCustomFields.
     * 
     * @return appcustfields
     */
    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] getAppcustfields() {
        return appcustfields;
    }


    /**
     * Sets the appcustfields value for this SetApplicationCustomFields.
     * 
     * @param appcustfields
     */
    public void setAppcustfields(it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] appcustfields) {
        this.appcustfields = appcustfields;
    }

    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields getAppcustfields(int i) {
        return this.appcustfields[i];
    }

    public void setAppcustfields(int i, it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields _value) {
        this.appcustfields[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetApplicationCustomFields)) return false;
        SetApplicationCustomFields other = (SetApplicationCustomFields) obj;
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
            ((this.appcustfields==null && other.getAppcustfields()==null) || 
             (this.appcustfields!=null &&
              java.util.Arrays.equals(this.appcustfields, other.getAppcustfields())));
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
        if (getAppcustfields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAppcustfields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAppcustfields(), i);
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
        new org.apache.axis.description.TypeDesc(SetApplicationCustomFields.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationCustomFields"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appcustfields");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appcustfields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationcustomfields"));
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
