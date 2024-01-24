/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class FlussoSingoloPagamentoDTO  extends it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO  implements java.io.Serializable {
    private java.lang.String applicationId;

    private java.lang.String applicationname;

    private java.lang.String codiceesitosingolopagamento;

    private java.util.Calendar dataesitosingolopagamento;

    private java.lang.Integer idFlusso;

    private java.lang.String identificativounivocoriscossione;

    private java.lang.String iuv;

    private java.lang.Integer singoloimportopagato;

    public FlussoSingoloPagamentoDTO() {
    }

    public FlussoSingoloPagamentoDTO(
           java.util.Calendar datainserimento,
           java.util.Calendar datamodifica,
           java.util.Calendar dataoraflusso,
           java.util.Calendar dataregolamento,
           java.lang.String denominazionemittente,
           java.lang.String denominazionericevente,
           java.lang.Integer id,
           java.lang.String identificativoflusso,
           java.lang.String identificativoistitutomittente,
           java.lang.String identificativoistitutoricevente,
           java.lang.String identificativopsp,
           java.lang.String identificativounivocoregolamento,
           java.lang.Integer importototalepagamenti,
           java.lang.Integer numerototalepagamenti,
           java.lang.String versioneoggetto,
           java.lang.String xmlflusso,
           java.lang.String applicationId,
           java.lang.String applicationname,
           java.lang.String codiceesitosingolopagamento,
           java.util.Calendar dataesitosingolopagamento,
           java.lang.Integer idFlusso,
           java.lang.String identificativounivocoriscossione,
           java.lang.String iuv,
           java.lang.Integer singoloimportopagato) {
        super(
            datainserimento,
            datamodifica,
            dataoraflusso,
            dataregolamento,
            denominazionemittente,
            denominazionericevente,
            id,
            identificativoflusso,
            identificativoistitutomittente,
            identificativoistitutoricevente,
            identificativopsp,
            identificativounivocoregolamento,
            importototalepagamenti,
            numerototalepagamenti,
            versioneoggetto,
            xmlflusso);
        this.applicationId = applicationId;
        this.applicationname = applicationname;
        this.codiceesitosingolopagamento = codiceesitosingolopagamento;
        this.dataesitosingolopagamento = dataesitosingolopagamento;
        this.idFlusso = idFlusso;
        this.identificativounivocoriscossione = identificativounivocoriscossione;
        this.iuv = iuv;
        this.singoloimportopagato = singoloimportopagato;
    }


    /**
     * Gets the applicationId value for this FlussoSingoloPagamentoDTO.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this FlussoSingoloPagamentoDTO.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the applicationname value for this FlussoSingoloPagamentoDTO.
     * 
     * @return applicationname
     */
    public java.lang.String getApplicationname() {
        return applicationname;
    }


    /**
     * Sets the applicationname value for this FlussoSingoloPagamentoDTO.
     * 
     * @param applicationname
     */
    public void setApplicationname(java.lang.String applicationname) {
        this.applicationname = applicationname;
    }


    /**
     * Gets the codiceesitosingolopagamento value for this FlussoSingoloPagamentoDTO.
     * 
     * @return codiceesitosingolopagamento
     */
    public java.lang.String getCodiceesitosingolopagamento() {
        return codiceesitosingolopagamento;
    }


    /**
     * Sets the codiceesitosingolopagamento value for this FlussoSingoloPagamentoDTO.
     * 
     * @param codiceesitosingolopagamento
     */
    public void setCodiceesitosingolopagamento(java.lang.String codiceesitosingolopagamento) {
        this.codiceesitosingolopagamento = codiceesitosingolopagamento;
    }


    /**
     * Gets the dataesitosingolopagamento value for this FlussoSingoloPagamentoDTO.
     * 
     * @return dataesitosingolopagamento
     */
    public java.util.Calendar getDataesitosingolopagamento() {
        return dataesitosingolopagamento;
    }


    /**
     * Sets the dataesitosingolopagamento value for this FlussoSingoloPagamentoDTO.
     * 
     * @param dataesitosingolopagamento
     */
    public void setDataesitosingolopagamento(java.util.Calendar dataesitosingolopagamento) {
        this.dataesitosingolopagamento = dataesitosingolopagamento;
    }


    /**
     * Gets the idFlusso value for this FlussoSingoloPagamentoDTO.
     * 
     * @return idFlusso
     */
    public java.lang.Integer getIdFlusso() {
        return idFlusso;
    }


    /**
     * Sets the idFlusso value for this FlussoSingoloPagamentoDTO.
     * 
     * @param idFlusso
     */
    public void setIdFlusso(java.lang.Integer idFlusso) {
        this.idFlusso = idFlusso;
    }


    /**
     * Gets the identificativounivocoriscossione value for this FlussoSingoloPagamentoDTO.
     * 
     * @return identificativounivocoriscossione
     */
    public java.lang.String getIdentificativounivocoriscossione() {
        return identificativounivocoriscossione;
    }


    /**
     * Sets the identificativounivocoriscossione value for this FlussoSingoloPagamentoDTO.
     * 
     * @param identificativounivocoriscossione
     */
    public void setIdentificativounivocoriscossione(java.lang.String identificativounivocoriscossione) {
        this.identificativounivocoriscossione = identificativounivocoriscossione;
    }


    /**
     * Gets the iuv value for this FlussoSingoloPagamentoDTO.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this FlussoSingoloPagamentoDTO.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the singoloimportopagato value for this FlussoSingoloPagamentoDTO.
     * 
     * @return singoloimportopagato
     */
    public java.lang.Integer getSingoloimportopagato() {
        return singoloimportopagato;
    }


    /**
     * Sets the singoloimportopagato value for this FlussoSingoloPagamentoDTO.
     * 
     * @param singoloimportopagato
     */
    public void setSingoloimportopagato(java.lang.Integer singoloimportopagato) {
        this.singoloimportopagato = singoloimportopagato;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FlussoSingoloPagamentoDTO)) return false;
        FlussoSingoloPagamentoDTO other = (FlussoSingoloPagamentoDTO) obj;
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
            ((this.applicationname==null && other.getApplicationname()==null) || 
             (this.applicationname!=null &&
              this.applicationname.equals(other.getApplicationname()))) &&
            ((this.codiceesitosingolopagamento==null && other.getCodiceesitosingolopagamento()==null) || 
             (this.codiceesitosingolopagamento!=null &&
              this.codiceesitosingolopagamento.equals(other.getCodiceesitosingolopagamento()))) &&
            ((this.dataesitosingolopagamento==null && other.getDataesitosingolopagamento()==null) || 
             (this.dataesitosingolopagamento!=null &&
              this.dataesitosingolopagamento.equals(other.getDataesitosingolopagamento()))) &&
            ((this.idFlusso==null && other.getIdFlusso()==null) || 
             (this.idFlusso!=null &&
              this.idFlusso.equals(other.getIdFlusso()))) &&
            ((this.identificativounivocoriscossione==null && other.getIdentificativounivocoriscossione()==null) || 
             (this.identificativounivocoriscossione!=null &&
              this.identificativounivocoriscossione.equals(other.getIdentificativounivocoriscossione()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.singoloimportopagato==null && other.getSingoloimportopagato()==null) || 
             (this.singoloimportopagato!=null &&
              this.singoloimportopagato.equals(other.getSingoloimportopagato())));
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
        if (getApplicationname() != null) {
            _hashCode += getApplicationname().hashCode();
        }
        if (getCodiceesitosingolopagamento() != null) {
            _hashCode += getCodiceesitosingolopagamento().hashCode();
        }
        if (getDataesitosingolopagamento() != null) {
            _hashCode += getDataesitosingolopagamento().hashCode();
        }
        if (getIdFlusso() != null) {
            _hashCode += getIdFlusso().hashCode();
        }
        if (getIdentificativounivocoriscossione() != null) {
            _hashCode += getIdentificativounivocoriscossione().hashCode();
        }
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getSingoloimportopagato() != null) {
            _hashCode += getSingoloimportopagato().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FlussoSingoloPagamentoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "flussoSingoloPagamentoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("idFlusso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idFlusso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("iuv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iuv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("singoloimportopagato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "singoloimportopagato"));
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
