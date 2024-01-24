/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetTransazioneViewById  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.String idTransazione;

    public GetTransazioneViewById() {
    }

    public GetTransazioneViewById(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.String idTransazione) {
           this.auth = auth;
           this.idTransazione = idTransazione;
    }


    /**
     * Gets the auth value for this GetTransazioneViewById.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetTransazioneViewById.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the idTransazione value for this GetTransazioneViewById.
     * 
     * @return idTransazione
     */
    public java.lang.String getIdTransazione() {
        return idTransazione;
    }


    /**
     * Sets the idTransazione value for this GetTransazioneViewById.
     * 
     * @param idTransazione
     */
    public void setIdTransazione(java.lang.String idTransazione) {
        this.idTransazione = idTransazione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTransazioneViewById)) return false;
        GetTransazioneViewById other = (GetTransazioneViewById) obj;
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
            ((this.idTransazione==null && other.getIdTransazione()==null) || 
             (this.idTransazione!=null &&
              this.idTransazione.equals(other.getIdTransazione())));
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
        if (getIdTransazione() != null) {
            _hashCode += getIdTransazione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTransazioneViewById.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewById"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTransazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idTransazione"));
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