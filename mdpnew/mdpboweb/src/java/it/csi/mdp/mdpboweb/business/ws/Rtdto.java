/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Rtdto  extends it.csi.mdp.mdpboweb.business.ws.BaseDto  implements java.io.Serializable {
    private java.lang.String applicationId;

    private java.util.Calendar dataMsgRicevuta;

    private java.lang.String descEsitoPagamento;

    private java.lang.Integer id;

    private java.lang.Integer idEsitoPagamento;

    private java.lang.String idMsgRicevuta;

    private java.lang.String idMsgRichiesta;

    private java.util.Calendar insertDate;

    private java.lang.String iuv;

    private java.util.Calendar lastUpdate;

    private byte[] rtData;

    private java.lang.String tipoFirma;

    private java.lang.String transactionId;

    public Rtdto() {
    }

    public Rtdto(
           java.lang.String applicationId,
           java.util.Calendar dataMsgRicevuta,
           java.lang.String descEsitoPagamento,
           java.lang.Integer id,
           java.lang.Integer idEsitoPagamento,
           java.lang.String idMsgRicevuta,
           java.lang.String idMsgRichiesta,
           java.util.Calendar insertDate,
           java.lang.String iuv,
           java.util.Calendar lastUpdate,
           byte[] rtData,
           java.lang.String tipoFirma,
           java.lang.String transactionId) {
        this.applicationId = applicationId;
        this.dataMsgRicevuta = dataMsgRicevuta;
        this.descEsitoPagamento = descEsitoPagamento;
        this.id = id;
        this.idEsitoPagamento = idEsitoPagamento;
        this.idMsgRicevuta = idMsgRicevuta;
        this.idMsgRichiesta = idMsgRichiesta;
        this.insertDate = insertDate;
        this.iuv = iuv;
        this.lastUpdate = lastUpdate;
        this.rtData = rtData;
        this.tipoFirma = tipoFirma;
        this.transactionId = transactionId;
    }


    /**
     * Gets the applicationId value for this Rtdto.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this Rtdto.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the dataMsgRicevuta value for this Rtdto.
     * 
     * @return dataMsgRicevuta
     */
    public java.util.Calendar getDataMsgRicevuta() {
        return dataMsgRicevuta;
    }


    /**
     * Sets the dataMsgRicevuta value for this Rtdto.
     * 
     * @param dataMsgRicevuta
     */
    public void setDataMsgRicevuta(java.util.Calendar dataMsgRicevuta) {
        this.dataMsgRicevuta = dataMsgRicevuta;
    }


    /**
     * Gets the descEsitoPagamento value for this Rtdto.
     * 
     * @return descEsitoPagamento
     */
    public java.lang.String getDescEsitoPagamento() {
        return descEsitoPagamento;
    }


    /**
     * Sets the descEsitoPagamento value for this Rtdto.
     * 
     * @param descEsitoPagamento
     */
    public void setDescEsitoPagamento(java.lang.String descEsitoPagamento) {
        this.descEsitoPagamento = descEsitoPagamento;
    }


    /**
     * Gets the id value for this Rtdto.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this Rtdto.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the idEsitoPagamento value for this Rtdto.
     * 
     * @return idEsitoPagamento
     */
    public java.lang.Integer getIdEsitoPagamento() {
        return idEsitoPagamento;
    }


    /**
     * Sets the idEsitoPagamento value for this Rtdto.
     * 
     * @param idEsitoPagamento
     */
    public void setIdEsitoPagamento(java.lang.Integer idEsitoPagamento) {
        this.idEsitoPagamento = idEsitoPagamento;
    }


    /**
     * Gets the idMsgRicevuta value for this Rtdto.
     * 
     * @return idMsgRicevuta
     */
    public java.lang.String getIdMsgRicevuta() {
        return idMsgRicevuta;
    }


    /**
     * Sets the idMsgRicevuta value for this Rtdto.
     * 
     * @param idMsgRicevuta
     */
    public void setIdMsgRicevuta(java.lang.String idMsgRicevuta) {
        this.idMsgRicevuta = idMsgRicevuta;
    }


    /**
     * Gets the idMsgRichiesta value for this Rtdto.
     * 
     * @return idMsgRichiesta
     */
    public java.lang.String getIdMsgRichiesta() {
        return idMsgRichiesta;
    }


    /**
     * Sets the idMsgRichiesta value for this Rtdto.
     * 
     * @param idMsgRichiesta
     */
    public void setIdMsgRichiesta(java.lang.String idMsgRichiesta) {
        this.idMsgRichiesta = idMsgRichiesta;
    }


    /**
     * Gets the insertDate value for this Rtdto.
     * 
     * @return insertDate
     */
    public java.util.Calendar getInsertDate() {
        return insertDate;
    }


    /**
     * Sets the insertDate value for this Rtdto.
     * 
     * @param insertDate
     */
    public void setInsertDate(java.util.Calendar insertDate) {
        this.insertDate = insertDate;
    }


    /**
     * Gets the iuv value for this Rtdto.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this Rtdto.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the lastUpdate value for this Rtdto.
     * 
     * @return lastUpdate
     */
    public java.util.Calendar getLastUpdate() {
        return lastUpdate;
    }


    /**
     * Sets the lastUpdate value for this Rtdto.
     * 
     * @param lastUpdate
     */
    public void setLastUpdate(java.util.Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    /**
     * Gets the rtData value for this Rtdto.
     * 
     * @return rtData
     */
    public byte[] getRtData() {
        return rtData;
    }


    /**
     * Sets the rtData value for this Rtdto.
     * 
     * @param rtData
     */
    public void setRtData(byte[] rtData) {
        this.rtData = rtData;
    }


    /**
     * Gets the tipoFirma value for this Rtdto.
     * 
     * @return tipoFirma
     */
    public java.lang.String getTipoFirma() {
        return tipoFirma;
    }


    /**
     * Sets the tipoFirma value for this Rtdto.
     * 
     * @param tipoFirma
     */
    public void setTipoFirma(java.lang.String tipoFirma) {
        this.tipoFirma = tipoFirma;
    }


    /**
     * Gets the transactionId value for this Rtdto.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Rtdto.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Rtdto)) return false;
        Rtdto other = (Rtdto) obj;
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
            ((this.dataMsgRicevuta==null && other.getDataMsgRicevuta()==null) || 
             (this.dataMsgRicevuta!=null &&
              this.dataMsgRicevuta.equals(other.getDataMsgRicevuta()))) &&
            ((this.descEsitoPagamento==null && other.getDescEsitoPagamento()==null) || 
             (this.descEsitoPagamento!=null &&
              this.descEsitoPagamento.equals(other.getDescEsitoPagamento()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idEsitoPagamento==null && other.getIdEsitoPagamento()==null) || 
             (this.idEsitoPagamento!=null &&
              this.idEsitoPagamento.equals(other.getIdEsitoPagamento()))) &&
            ((this.idMsgRicevuta==null && other.getIdMsgRicevuta()==null) || 
             (this.idMsgRicevuta!=null &&
              this.idMsgRicevuta.equals(other.getIdMsgRicevuta()))) &&
            ((this.idMsgRichiesta==null && other.getIdMsgRichiesta()==null) || 
             (this.idMsgRichiesta!=null &&
              this.idMsgRichiesta.equals(other.getIdMsgRichiesta()))) &&
            ((this.insertDate==null && other.getInsertDate()==null) || 
             (this.insertDate!=null &&
              this.insertDate.equals(other.getInsertDate()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.lastUpdate==null && other.getLastUpdate()==null) || 
             (this.lastUpdate!=null &&
              this.lastUpdate.equals(other.getLastUpdate()))) &&
            ((this.rtData==null && other.getRtData()==null) || 
             (this.rtData!=null &&
              java.util.Arrays.equals(this.rtData, other.getRtData()))) &&
            ((this.tipoFirma==null && other.getTipoFirma()==null) || 
             (this.tipoFirma!=null &&
              this.tipoFirma.equals(other.getTipoFirma()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId())));
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
        if (getDataMsgRicevuta() != null) {
            _hashCode += getDataMsgRicevuta().hashCode();
        }
        if (getDescEsitoPagamento() != null) {
            _hashCode += getDescEsitoPagamento().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdEsitoPagamento() != null) {
            _hashCode += getIdEsitoPagamento().hashCode();
        }
        if (getIdMsgRicevuta() != null) {
            _hashCode += getIdMsgRicevuta().hashCode();
        }
        if (getIdMsgRichiesta() != null) {
            _hashCode += getIdMsgRichiesta().hashCode();
        }
        if (getInsertDate() != null) {
            _hashCode += getInsertDate().hashCode();
        }
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getLastUpdate() != null) {
            _hashCode += getLastUpdate().hashCode();
        }
        if (getRtData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRtData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRtData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTipoFirma() != null) {
            _hashCode += getTipoFirma().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Rtdto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "rtdto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataMsgRicevuta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataMsgRicevuta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descEsitoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descEsitoPagamento"));
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
        elemField.setFieldName("idEsitoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEsitoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMsgRicevuta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idMsgRicevuta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMsgRichiesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idMsgRichiesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "insertDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("lastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rtData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rtData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFirma");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoFirma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionId"));
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
