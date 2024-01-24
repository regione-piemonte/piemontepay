/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class IbanEnteApplicationDTO  extends it.csi.mdp.mdpboweb.business.ws.BaseDto  implements java.io.Serializable {
    private java.lang.String applicationId;

    private java.lang.String attivo;

    private java.util.Calendar dataFineValidita;

    private java.util.Calendar dataInizioValidita;

    private java.lang.String iban;

    private java.lang.Integer id;

    private java.lang.String idEnte;

    private java.lang.String identificativopsp;

    private java.lang.String tipoversamento;

    public IbanEnteApplicationDTO() {
    }

    public IbanEnteApplicationDTO(
           java.lang.String applicationId,
           java.lang.String attivo,
           java.util.Calendar dataFineValidita,
           java.util.Calendar dataInizioValidita,
           java.lang.String iban,
           java.lang.Integer id,
           java.lang.String idEnte,
           java.lang.String identificativopsp,
           java.lang.String tipoversamento) {
        this.applicationId = applicationId;
        this.attivo = attivo;
        this.dataFineValidita = dataFineValidita;
        this.dataInizioValidita = dataInizioValidita;
        this.iban = iban;
        this.id = id;
        this.idEnte = idEnte;
        this.identificativopsp = identificativopsp;
        this.tipoversamento = tipoversamento;
    }


    /**
     * Gets the applicationId value for this IbanEnteApplicationDTO.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this IbanEnteApplicationDTO.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the attivo value for this IbanEnteApplicationDTO.
     * 
     * @return attivo
     */
    public java.lang.String getAttivo() {
        return attivo;
    }


    /**
     * Sets the attivo value for this IbanEnteApplicationDTO.
     * 
     * @param attivo
     */
    public void setAttivo(java.lang.String attivo) {
        this.attivo = attivo;
    }


    /**
     * Gets the dataFineValidita value for this IbanEnteApplicationDTO.
     * 
     * @return dataFineValidita
     */
    public java.util.Calendar getDataFineValidita() {
        return dataFineValidita;
    }


    /**
     * Sets the dataFineValidita value for this IbanEnteApplicationDTO.
     * 
     * @param dataFineValidita
     */
    public void setDataFineValidita(java.util.Calendar dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }


    /**
     * Gets the dataInizioValidita value for this IbanEnteApplicationDTO.
     * 
     * @return dataInizioValidita
     */
    public java.util.Calendar getDataInizioValidita() {
        return dataInizioValidita;
    }


    /**
     * Sets the dataInizioValidita value for this IbanEnteApplicationDTO.
     * 
     * @param dataInizioValidita
     */
    public void setDataInizioValidita(java.util.Calendar dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }


    /**
     * Gets the iban value for this IbanEnteApplicationDTO.
     * 
     * @return iban
     */
    public java.lang.String getIban() {
        return iban;
    }


    /**
     * Sets the iban value for this IbanEnteApplicationDTO.
     * 
     * @param iban
     */
    public void setIban(java.lang.String iban) {
        this.iban = iban;
    }


    /**
     * Gets the id value for this IbanEnteApplicationDTO.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this IbanEnteApplicationDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the idEnte value for this IbanEnteApplicationDTO.
     * 
     * @return idEnte
     */
    public java.lang.String getIdEnte() {
        return idEnte;
    }


    /**
     * Sets the idEnte value for this IbanEnteApplicationDTO.
     * 
     * @param idEnte
     */
    public void setIdEnte(java.lang.String idEnte) {
        this.idEnte = idEnte;
    }


    /**
     * Gets the identificativopsp value for this IbanEnteApplicationDTO.
     * 
     * @return identificativopsp
     */
    public java.lang.String getIdentificativopsp() {
        return identificativopsp;
    }


    /**
     * Sets the identificativopsp value for this IbanEnteApplicationDTO.
     * 
     * @param identificativopsp
     */
    public void setIdentificativopsp(java.lang.String identificativopsp) {
        this.identificativopsp = identificativopsp;
    }


    /**
     * Gets the tipoversamento value for this IbanEnteApplicationDTO.
     * 
     * @return tipoversamento
     */
    public java.lang.String getTipoversamento() {
        return tipoversamento;
    }


    /**
     * Sets the tipoversamento value for this IbanEnteApplicationDTO.
     * 
     * @param tipoversamento
     */
    public void setTipoversamento(java.lang.String tipoversamento) {
        this.tipoversamento = tipoversamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IbanEnteApplicationDTO)) return false;
        IbanEnteApplicationDTO other = (IbanEnteApplicationDTO) obj;
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
            ((this.attivo==null && other.getAttivo()==null) || 
             (this.attivo!=null &&
              this.attivo.equals(other.getAttivo()))) &&
            ((this.dataFineValidita==null && other.getDataFineValidita()==null) || 
             (this.dataFineValidita!=null &&
              this.dataFineValidita.equals(other.getDataFineValidita()))) &&
            ((this.dataInizioValidita==null && other.getDataInizioValidita()==null) || 
             (this.dataInizioValidita!=null &&
              this.dataInizioValidita.equals(other.getDataInizioValidita()))) &&
            ((this.iban==null && other.getIban()==null) || 
             (this.iban!=null &&
              this.iban.equals(other.getIban()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idEnte==null && other.getIdEnte()==null) || 
             (this.idEnte!=null &&
              this.idEnte.equals(other.getIdEnte()))) &&
            ((this.identificativopsp==null && other.getIdentificativopsp()==null) || 
             (this.identificativopsp!=null &&
              this.identificativopsp.equals(other.getIdentificativopsp()))) &&
            ((this.tipoversamento==null && other.getTipoversamento()==null) || 
             (this.tipoversamento!=null &&
              this.tipoversamento.equals(other.getTipoversamento())));
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
        if (getAttivo() != null) {
            _hashCode += getAttivo().hashCode();
        }
        if (getDataFineValidita() != null) {
            _hashCode += getDataFineValidita().hashCode();
        }
        if (getDataInizioValidita() != null) {
            _hashCode += getDataInizioValidita().hashCode();
        }
        if (getIban() != null) {
            _hashCode += getIban().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdEnte() != null) {
            _hashCode += getIdEnte().hashCode();
        }
        if (getIdentificativopsp() != null) {
            _hashCode += getIdentificativopsp().hashCode();
        }
        if (getTipoversamento() != null) {
            _hashCode += getTipoversamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IbanEnteApplicationDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "ibanEnteApplicationDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataFineValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("iban");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iban"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("idEnte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEnte"));
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
        elemField.setFieldName("tipoversamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoversamento"));
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
