/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class UpdateIbanEnteApplication  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials arg0;

    private java.lang.Integer id;

    private java.lang.String applicationId;

    private java.lang.String idEnte;

    private java.lang.String tipoversamento;

    private java.lang.String identificativopsp;

    private java.lang.String iban;

    private java.util.Calendar dataInizioValidita;

    private java.util.Calendar dataFineValidita;

    private java.lang.String attivo;

    public UpdateIbanEnteApplication() {
    }

    public UpdateIbanEnteApplication(
           it.csi.mdp.mdpboweb.business.ws.Credentials arg0,
           java.lang.Integer id,
           java.lang.String applicationId,
           java.lang.String idEnte,
           java.lang.String tipoversamento,
           java.lang.String identificativopsp,
           java.lang.String iban,
           java.util.Calendar dataInizioValidita,
           java.util.Calendar dataFineValidita,
           java.lang.String attivo) {
           this.arg0 = arg0;
           this.id = id;
           this.applicationId = applicationId;
           this.idEnte = idEnte;
           this.tipoversamento = tipoversamento;
           this.identificativopsp = identificativopsp;
           this.iban = iban;
           this.dataInizioValidita = dataInizioValidita;
           this.dataFineValidita = dataFineValidita;
           this.attivo = attivo;
    }


    /**
     * Gets the arg0 value for this UpdateIbanEnteApplication.
     * 
     * @return arg0
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getArg0() {
        return arg0;
    }


    /**
     * Sets the arg0 value for this UpdateIbanEnteApplication.
     * 
     * @param arg0
     */
    public void setArg0(it.csi.mdp.mdpboweb.business.ws.Credentials arg0) {
        this.arg0 = arg0;
    }


    /**
     * Gets the id value for this UpdateIbanEnteApplication.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this UpdateIbanEnteApplication.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the applicationId value for this UpdateIbanEnteApplication.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this UpdateIbanEnteApplication.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the idEnte value for this UpdateIbanEnteApplication.
     * 
     * @return idEnte
     */
    public java.lang.String getIdEnte() {
        return idEnte;
    }


    /**
     * Sets the idEnte value for this UpdateIbanEnteApplication.
     * 
     * @param idEnte
     */
    public void setIdEnte(java.lang.String idEnte) {
        this.idEnte = idEnte;
    }


    /**
     * Gets the tipoversamento value for this UpdateIbanEnteApplication.
     * 
     * @return tipoversamento
     */
    public java.lang.String getTipoversamento() {
        return tipoversamento;
    }


    /**
     * Sets the tipoversamento value for this UpdateIbanEnteApplication.
     * 
     * @param tipoversamento
     */
    public void setTipoversamento(java.lang.String tipoversamento) {
        this.tipoversamento = tipoversamento;
    }


    /**
     * Gets the identificativopsp value for this UpdateIbanEnteApplication.
     * 
     * @return identificativopsp
     */
    public java.lang.String getIdentificativopsp() {
        return identificativopsp;
    }


    /**
     * Sets the identificativopsp value for this UpdateIbanEnteApplication.
     * 
     * @param identificativopsp
     */
    public void setIdentificativopsp(java.lang.String identificativopsp) {
        this.identificativopsp = identificativopsp;
    }


    /**
     * Gets the iban value for this UpdateIbanEnteApplication.
     * 
     * @return iban
     */
    public java.lang.String getIban() {
        return iban;
    }


    /**
     * Sets the iban value for this UpdateIbanEnteApplication.
     * 
     * @param iban
     */
    public void setIban(java.lang.String iban) {
        this.iban = iban;
    }


    /**
     * Gets the dataInizioValidita value for this UpdateIbanEnteApplication.
     * 
     * @return dataInizioValidita
     */
    public java.util.Calendar getDataInizioValidita() {
        return dataInizioValidita;
    }


    /**
     * Sets the dataInizioValidita value for this UpdateIbanEnteApplication.
     * 
     * @param dataInizioValidita
     */
    public void setDataInizioValidita(java.util.Calendar dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }


    /**
     * Gets the dataFineValidita value for this UpdateIbanEnteApplication.
     * 
     * @return dataFineValidita
     */
    public java.util.Calendar getDataFineValidita() {
        return dataFineValidita;
    }


    /**
     * Sets the dataFineValidita value for this UpdateIbanEnteApplication.
     * 
     * @param dataFineValidita
     */
    public void setDataFineValidita(java.util.Calendar dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }


    /**
     * Gets the attivo value for this UpdateIbanEnteApplication.
     * 
     * @return attivo
     */
    public java.lang.String getAttivo() {
        return attivo;
    }


    /**
     * Sets the attivo value for this UpdateIbanEnteApplication.
     * 
     * @param attivo
     */
    public void setAttivo(java.lang.String attivo) {
        this.attivo = attivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateIbanEnteApplication)) return false;
        UpdateIbanEnteApplication other = (UpdateIbanEnteApplication) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arg0==null && other.getArg0()==null) || 
             (this.arg0!=null &&
              this.arg0.equals(other.getArg0()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.idEnte==null && other.getIdEnte()==null) || 
             (this.idEnte!=null &&
              this.idEnte.equals(other.getIdEnte()))) &&
            ((this.tipoversamento==null && other.getTipoversamento()==null) || 
             (this.tipoversamento!=null &&
              this.tipoversamento.equals(other.getTipoversamento()))) &&
            ((this.identificativopsp==null && other.getIdentificativopsp()==null) || 
             (this.identificativopsp!=null &&
              this.identificativopsp.equals(other.getIdentificativopsp()))) &&
            ((this.iban==null && other.getIban()==null) || 
             (this.iban!=null &&
              this.iban.equals(other.getIban()))) &&
            ((this.dataInizioValidita==null && other.getDataInizioValidita()==null) || 
             (this.dataInizioValidita!=null &&
              this.dataInizioValidita.equals(other.getDataInizioValidita()))) &&
            ((this.dataFineValidita==null && other.getDataFineValidita()==null) || 
             (this.dataFineValidita!=null &&
              this.dataFineValidita.equals(other.getDataFineValidita()))) &&
            ((this.attivo==null && other.getAttivo()==null) || 
             (this.attivo!=null &&
              this.attivo.equals(other.getAttivo())));
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
        if (getArg0() != null) {
            _hashCode += getArg0().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getIdEnte() != null) {
            _hashCode += getIdEnte().hashCode();
        }
        if (getTipoversamento() != null) {
            _hashCode += getTipoversamento().hashCode();
        }
        if (getIdentificativopsp() != null) {
            _hashCode += getIdentificativopsp().hashCode();
        }
        if (getIban() != null) {
            _hashCode += getIban().hashCode();
        }
        if (getDataInizioValidita() != null) {
            _hashCode += getDataInizioValidita().hashCode();
        }
        if (getDataFineValidita() != null) {
            _hashCode += getDataFineValidita().hashCode();
        }
        if (getAttivo() != null) {
            _hashCode += getAttivo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateIbanEnteApplication.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateIbanEnteApplication"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arg0");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arg0"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("idEnte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEnte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoversamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoversamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativopsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativopsp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iban");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iban"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataInizioValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataFineValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attivo"));
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
