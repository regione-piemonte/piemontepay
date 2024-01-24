/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetFlussoSingoloPagamentoByParam  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.Integer id;

    private java.lang.Integer idFlusso;

    private java.lang.String iuv;

    private java.lang.String identificativounivocoriscossione;

    private java.lang.Double singoloimportopagato;

    private java.lang.String codiceesitosingolopagamento;

    private java.util.Calendar dataesitosingolopagamento;

    private java.util.Calendar datainserimento;

    private java.util.Calendar datamodifica;

    private java.lang.String applicationId;

    private java.util.Calendar dataregolamentoDa;

    private java.util.Calendar dataregolamentoA;

    public GetFlussoSingoloPagamentoByParam() {
    }

    public GetFlussoSingoloPagamentoByParam(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.Integer id,
           java.lang.Integer idFlusso,
           java.lang.String iuv,
           java.lang.String identificativounivocoriscossione,
           java.lang.Double singoloimportopagato,
           java.lang.String codiceesitosingolopagamento,
           java.util.Calendar dataesitosingolopagamento,
           java.util.Calendar datainserimento,
           java.util.Calendar datamodifica,
           java.lang.String applicationId,
           java.util.Calendar dataregolamentoDa,
           java.util.Calendar dataregolamentoA) {
           this.auth = auth;
           this.id = id;
           this.idFlusso = idFlusso;
           this.iuv = iuv;
           this.identificativounivocoriscossione = identificativounivocoriscossione;
           this.singoloimportopagato = singoloimportopagato;
           this.codiceesitosingolopagamento = codiceesitosingolopagamento;
           this.dataesitosingolopagamento = dataesitosingolopagamento;
           this.datainserimento = datainserimento;
           this.datamodifica = datamodifica;
           this.applicationId = applicationId;
           this.dataregolamentoDa = dataregolamentoDa;
           this.dataregolamentoA = dataregolamentoA;
    }


    /**
     * Gets the auth value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the id value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the idFlusso value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return idFlusso
     */
    public java.lang.Integer getIdFlusso() {
        return idFlusso;
    }


    /**
     * Sets the idFlusso value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param idFlusso
     */
    public void setIdFlusso(java.lang.Integer idFlusso) {
        this.idFlusso = idFlusso;
    }


    /**
     * Gets the iuv value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the identificativounivocoriscossione value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return identificativounivocoriscossione
     */
    public java.lang.String getIdentificativounivocoriscossione() {
        return identificativounivocoriscossione;
    }


    /**
     * Sets the identificativounivocoriscossione value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param identificativounivocoriscossione
     */
    public void setIdentificativounivocoriscossione(java.lang.String identificativounivocoriscossione) {
        this.identificativounivocoriscossione = identificativounivocoriscossione;
    }


    /**
     * Gets the singoloimportopagato value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return singoloimportopagato
     */
    public java.lang.Double getSingoloimportopagato() {
        return singoloimportopagato;
    }


    /**
     * Sets the singoloimportopagato value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param singoloimportopagato
     */
    public void setSingoloimportopagato(java.lang.Double singoloimportopagato) {
        this.singoloimportopagato = singoloimportopagato;
    }


    /**
     * Gets the codiceesitosingolopagamento value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return codiceesitosingolopagamento
     */
    public java.lang.String getCodiceesitosingolopagamento() {
        return codiceesitosingolopagamento;
    }


    /**
     * Sets the codiceesitosingolopagamento value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param codiceesitosingolopagamento
     */
    public void setCodiceesitosingolopagamento(java.lang.String codiceesitosingolopagamento) {
        this.codiceesitosingolopagamento = codiceesitosingolopagamento;
    }


    /**
     * Gets the dataesitosingolopagamento value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return dataesitosingolopagamento
     */
    public java.util.Calendar getDataesitosingolopagamento() {
        return dataesitosingolopagamento;
    }


    /**
     * Sets the dataesitosingolopagamento value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param dataesitosingolopagamento
     */
    public void setDataesitosingolopagamento(java.util.Calendar dataesitosingolopagamento) {
        this.dataesitosingolopagamento = dataesitosingolopagamento;
    }


    /**
     * Gets the datainserimento value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return datainserimento
     */
    public java.util.Calendar getDatainserimento() {
        return datainserimento;
    }


    /**
     * Sets the datainserimento value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param datainserimento
     */
    public void setDatainserimento(java.util.Calendar datainserimento) {
        this.datainserimento = datainserimento;
    }


    /**
     * Gets the datamodifica value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return datamodifica
     */
    public java.util.Calendar getDatamodifica() {
        return datamodifica;
    }


    /**
     * Sets the datamodifica value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param datamodifica
     */
    public void setDatamodifica(java.util.Calendar datamodifica) {
        this.datamodifica = datamodifica;
    }


    /**
     * Gets the applicationId value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the dataregolamentoDa value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return dataregolamentoDa
     */
    public java.util.Calendar getDataregolamentoDa() {
        return dataregolamentoDa;
    }


    /**
     * Sets the dataregolamentoDa value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param dataregolamentoDa
     */
    public void setDataregolamentoDa(java.util.Calendar dataregolamentoDa) {
        this.dataregolamentoDa = dataregolamentoDa;
    }


    /**
     * Gets the dataregolamentoA value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @return dataregolamentoA
     */
    public java.util.Calendar getDataregolamentoA() {
        return dataregolamentoA;
    }


    /**
     * Sets the dataregolamentoA value for this GetFlussoSingoloPagamentoByParam.
     * 
     * @param dataregolamentoA
     */
    public void setDataregolamentoA(java.util.Calendar dataregolamentoA) {
        this.dataregolamentoA = dataregolamentoA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFlussoSingoloPagamentoByParam)) return false;
        GetFlussoSingoloPagamentoByParam other = (GetFlussoSingoloPagamentoByParam) obj;
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
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idFlusso==null && other.getIdFlusso()==null) || 
             (this.idFlusso!=null &&
              this.idFlusso.equals(other.getIdFlusso()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.identificativounivocoriscossione==null && other.getIdentificativounivocoriscossione()==null) || 
             (this.identificativounivocoriscossione!=null &&
              this.identificativounivocoriscossione.equals(other.getIdentificativounivocoriscossione()))) &&
            ((this.singoloimportopagato==null && other.getSingoloimportopagato()==null) || 
             (this.singoloimportopagato!=null &&
              this.singoloimportopagato.equals(other.getSingoloimportopagato()))) &&
            ((this.codiceesitosingolopagamento==null && other.getCodiceesitosingolopagamento()==null) || 
             (this.codiceesitosingolopagamento!=null &&
              this.codiceesitosingolopagamento.equals(other.getCodiceesitosingolopagamento()))) &&
            ((this.dataesitosingolopagamento==null && other.getDataesitosingolopagamento()==null) || 
             (this.dataesitosingolopagamento!=null &&
              this.dataesitosingolopagamento.equals(other.getDataesitosingolopagamento()))) &&
            ((this.datainserimento==null && other.getDatainserimento()==null) || 
             (this.datainserimento!=null &&
              this.datainserimento.equals(other.getDatainserimento()))) &&
            ((this.datamodifica==null && other.getDatamodifica()==null) || 
             (this.datamodifica!=null &&
              this.datamodifica.equals(other.getDatamodifica()))) &&
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.dataregolamentoDa==null && other.getDataregolamentoDa()==null) || 
             (this.dataregolamentoDa!=null &&
              this.dataregolamentoDa.equals(other.getDataregolamentoDa()))) &&
            ((this.dataregolamentoA==null && other.getDataregolamentoA()==null) || 
             (this.dataregolamentoA!=null &&
              this.dataregolamentoA.equals(other.getDataregolamentoA())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdFlusso() != null) {
            _hashCode += getIdFlusso().hashCode();
        }
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getIdentificativounivocoriscossione() != null) {
            _hashCode += getIdentificativounivocoriscossione().hashCode();
        }
        if (getSingoloimportopagato() != null) {
            _hashCode += getSingoloimportopagato().hashCode();
        }
        if (getCodiceesitosingolopagamento() != null) {
            _hashCode += getCodiceesitosingolopagamento().hashCode();
        }
        if (getDataesitosingolopagamento() != null) {
            _hashCode += getDataesitosingolopagamento().hashCode();
        }
        if (getDatainserimento() != null) {
            _hashCode += getDatainserimento().hashCode();
        }
        if (getDatamodifica() != null) {
            _hashCode += getDatamodifica().hashCode();
        }
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getDataregolamentoDa() != null) {
            _hashCode += getDataregolamentoDa().hashCode();
        }
        if (getDataregolamentoA() != null) {
            _hashCode += getDataregolamentoA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFlussoSingoloPagamentoByParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoSingoloPagamentoByParam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
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
        elemField.setFieldName("idFlusso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idFlusso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iuv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iuv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativounivocoriscossione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativounivocoriscossione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("singoloimportopagato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "singoloimportopagato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceesitosingolopagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceesitosingolopagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataesitosingolopagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataesitosingolopagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datainserimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datainserimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datamodifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datamodifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("dataregolamentoDa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataregolamentoDa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataregolamentoA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataregolamentoA"));
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
