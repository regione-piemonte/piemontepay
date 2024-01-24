/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetTransazioneViewWithFiltersPaged  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.String applicationId;

    private long codStatoTransazione;

    private java.util.Calendar datastartFilter;

    private java.util.Calendar dataendFilter;

    private int numpagina;

    private int transazioniPerPagina;

    public GetTransazioneViewWithFiltersPaged() {
    }

    public GetTransazioneViewWithFiltersPaged(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.String applicationId,
           long codStatoTransazione,
           java.util.Calendar datastartFilter,
           java.util.Calendar dataendFilter,
           int numpagina,
           int transazioniPerPagina) {
           this.auth = auth;
           this.applicationId = applicationId;
           this.codStatoTransazione = codStatoTransazione;
           this.datastartFilter = datastartFilter;
           this.dataendFilter = dataendFilter;
           this.numpagina = numpagina;
           this.transazioniPerPagina = transazioniPerPagina;
    }


    /**
     * Gets the auth value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the applicationId value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the codStatoTransazione value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @return codStatoTransazione
     */
    public long getCodStatoTransazione() {
        return codStatoTransazione;
    }


    /**
     * Sets the codStatoTransazione value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @param codStatoTransazione
     */
    public void setCodStatoTransazione(long codStatoTransazione) {
        this.codStatoTransazione = codStatoTransazione;
    }


    /**
     * Gets the datastartFilter value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @return datastartFilter
     */
    public java.util.Calendar getDatastartFilter() {
        return datastartFilter;
    }


    /**
     * Sets the datastartFilter value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @param datastartFilter
     */
    public void setDatastartFilter(java.util.Calendar datastartFilter) {
        this.datastartFilter = datastartFilter;
    }


    /**
     * Gets the dataendFilter value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @return dataendFilter
     */
    public java.util.Calendar getDataendFilter() {
        return dataendFilter;
    }


    /**
     * Sets the dataendFilter value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @param dataendFilter
     */
    public void setDataendFilter(java.util.Calendar dataendFilter) {
        this.dataendFilter = dataendFilter;
    }


    /**
     * Gets the numpagina value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @return numpagina
     */
    public int getNumpagina() {
        return numpagina;
    }


    /**
     * Sets the numpagina value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @param numpagina
     */
    public void setNumpagina(int numpagina) {
        this.numpagina = numpagina;
    }


    /**
     * Gets the transazioniPerPagina value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @return transazioniPerPagina
     */
    public int getTransazioniPerPagina() {
        return transazioniPerPagina;
    }


    /**
     * Sets the transazioniPerPagina value for this GetTransazioneViewWithFiltersPaged.
     * 
     * @param transazioniPerPagina
     */
    public void setTransazioniPerPagina(int transazioniPerPagina) {
        this.transazioniPerPagina = transazioniPerPagina;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTransazioneViewWithFiltersPaged)) return false;
        GetTransazioneViewWithFiltersPaged other = (GetTransazioneViewWithFiltersPaged) obj;
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
            this.codStatoTransazione == other.getCodStatoTransazione() &&
            ((this.datastartFilter==null && other.getDatastartFilter()==null) || 
             (this.datastartFilter!=null &&
              this.datastartFilter.equals(other.getDatastartFilter()))) &&
            ((this.dataendFilter==null && other.getDataendFilter()==null) || 
             (this.dataendFilter!=null &&
              this.dataendFilter.equals(other.getDataendFilter()))) &&
            this.numpagina == other.getNumpagina() &&
            this.transazioniPerPagina == other.getTransazioniPerPagina();
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
        _hashCode += new Long(getCodStatoTransazione()).hashCode();
        if (getDatastartFilter() != null) {
            _hashCode += getDatastartFilter().hashCode();
        }
        if (getDataendFilter() != null) {
            _hashCode += getDataendFilter().hashCode();
        }
        _hashCode += getNumpagina();
        _hashCode += getTransazioniPerPagina();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTransazioneViewWithFiltersPaged.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewWithFiltersPaged"));
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
        elemField.setFieldName("codStatoTransazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codStatoTransazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datastartFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datastartFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataendFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataendFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numpagina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numpagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transazioniPerPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transazioniPerPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
