/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class StatisticaApplicazioneTransazioneDTO  extends it.csi.mdp.mdpboweb.business.ws.BaseDto  implements java.io.Serializable {
    private java.lang.String applicationId;

    private java.lang.Integer attesaRT;

    private java.lang.Integer canceled;

    private java.lang.Integer configured;

    private java.lang.Integer initialized;

    private java.lang.Integer notInitialized;

    private java.lang.Integer refunded;

    private java.lang.Integer started;

    private java.lang.Integer successful;

    private java.lang.Integer toBeConfirmed;

    private java.lang.Integer unsuccessful;

    public StatisticaApplicazioneTransazioneDTO() {
    }

    public StatisticaApplicazioneTransazioneDTO(
           java.lang.String applicationId,
           java.lang.Integer attesaRT,
           java.lang.Integer canceled,
           java.lang.Integer configured,
           java.lang.Integer initialized,
           java.lang.Integer notInitialized,
           java.lang.Integer refunded,
           java.lang.Integer started,
           java.lang.Integer successful,
           java.lang.Integer toBeConfirmed,
           java.lang.Integer unsuccessful) {
        this.applicationId = applicationId;
        this.attesaRT = attesaRT;
        this.canceled = canceled;
        this.configured = configured;
        this.initialized = initialized;
        this.notInitialized = notInitialized;
        this.refunded = refunded;
        this.started = started;
        this.successful = successful;
        this.toBeConfirmed = toBeConfirmed;
        this.unsuccessful = unsuccessful;
    }


    /**
     * Gets the applicationId value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the attesaRT value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return attesaRT
     */
    public java.lang.Integer getAttesaRT() {
        return attesaRT;
    }


    /**
     * Sets the attesaRT value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param attesaRT
     */
    public void setAttesaRT(java.lang.Integer attesaRT) {
        this.attesaRT = attesaRT;
    }


    /**
     * Gets the canceled value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return canceled
     */
    public java.lang.Integer getCanceled() {
        return canceled;
    }


    /**
     * Sets the canceled value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param canceled
     */
    public void setCanceled(java.lang.Integer canceled) {
        this.canceled = canceled;
    }


    /**
     * Gets the configured value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return configured
     */
    public java.lang.Integer getConfigured() {
        return configured;
    }


    /**
     * Sets the configured value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param configured
     */
    public void setConfigured(java.lang.Integer configured) {
        this.configured = configured;
    }


    /**
     * Gets the initialized value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return initialized
     */
    public java.lang.Integer getInitialized() {
        return initialized;
    }


    /**
     * Sets the initialized value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param initialized
     */
    public void setInitialized(java.lang.Integer initialized) {
        this.initialized = initialized;
    }


    /**
     * Gets the notInitialized value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return notInitialized
     */
    public java.lang.Integer getNotInitialized() {
        return notInitialized;
    }


    /**
     * Sets the notInitialized value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param notInitialized
     */
    public void setNotInitialized(java.lang.Integer notInitialized) {
        this.notInitialized = notInitialized;
    }


    /**
     * Gets the refunded value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return refunded
     */
    public java.lang.Integer getRefunded() {
        return refunded;
    }


    /**
     * Sets the refunded value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param refunded
     */
    public void setRefunded(java.lang.Integer refunded) {
        this.refunded = refunded;
    }


    /**
     * Gets the started value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return started
     */
    public java.lang.Integer getStarted() {
        return started;
    }


    /**
     * Sets the started value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param started
     */
    public void setStarted(java.lang.Integer started) {
        this.started = started;
    }


    /**
     * Gets the successful value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return successful
     */
    public java.lang.Integer getSuccessful() {
        return successful;
    }


    /**
     * Sets the successful value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param successful
     */
    public void setSuccessful(java.lang.Integer successful) {
        this.successful = successful;
    }


    /**
     * Gets the toBeConfirmed value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return toBeConfirmed
     */
    public java.lang.Integer getToBeConfirmed() {
        return toBeConfirmed;
    }


    /**
     * Sets the toBeConfirmed value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param toBeConfirmed
     */
    public void setToBeConfirmed(java.lang.Integer toBeConfirmed) {
        this.toBeConfirmed = toBeConfirmed;
    }


    /**
     * Gets the unsuccessful value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @return unsuccessful
     */
    public java.lang.Integer getUnsuccessful() {
        return unsuccessful;
    }


    /**
     * Sets the unsuccessful value for this StatisticaApplicazioneTransazioneDTO.
     * 
     * @param unsuccessful
     */
    public void setUnsuccessful(java.lang.Integer unsuccessful) {
        this.unsuccessful = unsuccessful;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticaApplicazioneTransazioneDTO)) return false;
        StatisticaApplicazioneTransazioneDTO other = (StatisticaApplicazioneTransazioneDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.attesaRT==null && other.getAttesaRT()==null) || 
             (this.attesaRT!=null &&
              this.attesaRT.equals(other.getAttesaRT()))) &&
            ((this.canceled==null && other.getCanceled()==null) || 
             (this.canceled!=null &&
              this.canceled.equals(other.getCanceled()))) &&
            ((this.configured==null && other.getConfigured()==null) || 
             (this.configured!=null &&
              this.configured.equals(other.getConfigured()))) &&
            ((this.initialized==null && other.getInitialized()==null) || 
             (this.initialized!=null &&
              this.initialized.equals(other.getInitialized()))) &&
            ((this.notInitialized==null && other.getNotInitialized()==null) || 
             (this.notInitialized!=null &&
              this.notInitialized.equals(other.getNotInitialized()))) &&
            ((this.refunded==null && other.getRefunded()==null) || 
             (this.refunded!=null &&
              this.refunded.equals(other.getRefunded()))) &&
            ((this.started==null && other.getStarted()==null) || 
             (this.started!=null &&
              this.started.equals(other.getStarted()))) &&
            ((this.successful==null && other.getSuccessful()==null) || 
             (this.successful!=null &&
              this.successful.equals(other.getSuccessful()))) &&
            ((this.toBeConfirmed==null && other.getToBeConfirmed()==null) || 
             (this.toBeConfirmed!=null &&
              this.toBeConfirmed.equals(other.getToBeConfirmed()))) &&
            ((this.unsuccessful==null && other.getUnsuccessful()==null) || 
             (this.unsuccessful!=null &&
              this.unsuccessful.equals(other.getUnsuccessful())));
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
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getAttesaRT() != null) {
            _hashCode += getAttesaRT().hashCode();
        }
        if (getCanceled() != null) {
            _hashCode += getCanceled().hashCode();
        }
        if (getConfigured() != null) {
            _hashCode += getConfigured().hashCode();
        }
        if (getInitialized() != null) {
            _hashCode += getInitialized().hashCode();
        }
        if (getNotInitialized() != null) {
            _hashCode += getNotInitialized().hashCode();
        }
        if (getRefunded() != null) {
            _hashCode += getRefunded().hashCode();
        }
        if (getStarted() != null) {
            _hashCode += getStarted().hashCode();
        }
        if (getSuccessful() != null) {
            _hashCode += getSuccessful().hashCode();
        }
        if (getToBeConfirmed() != null) {
            _hashCode += getToBeConfirmed().hashCode();
        }
        if (getUnsuccessful() != null) {
            _hashCode += getUnsuccessful().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatisticaApplicazioneTransazioneDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statisticaApplicazioneTransazioneDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attesaRT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attesaRT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("configured");
        elemField.setXmlName(new javax.xml.namespace.QName("", "configured"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("initialized");
        elemField.setXmlName(new javax.xml.namespace.QName("", "initialized"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notInitialized");
        elemField.setXmlName(new javax.xml.namespace.QName("", "notInitialized"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("started");
        elemField.setXmlName(new javax.xml.namespace.QName("", "started"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("successful");
        elemField.setXmlName(new javax.xml.namespace.QName("", "successful"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toBeConfirmed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "toBeConfirmed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unsuccessful");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unsuccessful"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
