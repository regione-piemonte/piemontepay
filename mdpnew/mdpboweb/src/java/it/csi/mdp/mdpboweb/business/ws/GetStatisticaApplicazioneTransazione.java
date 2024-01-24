/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetStatisticaApplicazioneTransazione  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.String applicationId;

    private java.util.Calendar dateDa;

    private java.util.Calendar dateA;

    public GetStatisticaApplicazioneTransazione() {
    }

    public GetStatisticaApplicazioneTransazione(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.String applicationId,
           java.util.Calendar dateDa,
           java.util.Calendar dateA) {
           this.auth = auth;
           this.applicationId = applicationId;
           this.dateDa = dateDa;
           this.dateA = dateA;
    }


    /**
     * Gets the auth value for this GetStatisticaApplicazioneTransazione.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetStatisticaApplicazioneTransazione.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the applicationId value for this GetStatisticaApplicazioneTransazione.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this GetStatisticaApplicazioneTransazione.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the dateDa value for this GetStatisticaApplicazioneTransazione.
     * 
     * @return dateDa
     */
    public java.util.Calendar getDateDa() {
        return dateDa;
    }


    /**
     * Sets the dateDa value for this GetStatisticaApplicazioneTransazione.
     * 
     * @param dateDa
     */
    public void setDateDa(java.util.Calendar dateDa) {
        this.dateDa = dateDa;
    }


    /**
     * Gets the dateA value for this GetStatisticaApplicazioneTransazione.
     * 
     * @return dateA
     */
    public java.util.Calendar getDateA() {
        return dateA;
    }


    /**
     * Sets the dateA value for this GetStatisticaApplicazioneTransazione.
     * 
     * @param dateA
     */
    public void setDateA(java.util.Calendar dateA) {
        this.dateA = dateA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetStatisticaApplicazioneTransazione)) return false;
        GetStatisticaApplicazioneTransazione other = (GetStatisticaApplicazioneTransazione) obj;
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
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.dateDa==null && other.getDateDa()==null) || 
             (this.dateDa!=null &&
              this.dateDa.equals(other.getDateDa()))) &&
            ((this.dateA==null && other.getDateA()==null) || 
             (this.dateA!=null &&
              this.dateA.equals(other.getDateA())));
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
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getDateDa() != null) {
            _hashCode += getDateDa().hashCode();
        }
        if (getDateA() != null) {
            _hashCode += getDateA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetStatisticaApplicazioneTransazione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatisticaApplicazioneTransazione"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateDa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateDa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
