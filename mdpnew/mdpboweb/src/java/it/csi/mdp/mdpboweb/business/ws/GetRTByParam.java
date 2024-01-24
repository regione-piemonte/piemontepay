/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetRTByParam  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.Integer id;

    private java.lang.String applicationId;

    private java.lang.String transactionId;

    private java.util.Calendar lastUpdateDa;

    private java.util.Calendar lastUpdateA;

    private java.util.Calendar insertUpdateDa;

    private java.util.Calendar insertUpdateA;

    private java.lang.String iuv;

    private java.lang.String idEsitoPagamento;

    private java.lang.String idMsgRichiesta;

    public GetRTByParam() {
    }

    public GetRTByParam(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.Integer id,
           java.lang.String applicationId,
           java.lang.String transactionId,
           java.util.Calendar lastUpdateDa,
           java.util.Calendar lastUpdateA,
           java.util.Calendar insertUpdateDa,
           java.util.Calendar insertUpdateA,
           java.lang.String iuv,
           java.lang.String idEsitoPagamento,
           java.lang.String idMsgRichiesta) {
           this.auth = auth;
           this.id = id;
           this.applicationId = applicationId;
           this.transactionId = transactionId;
           this.lastUpdateDa = lastUpdateDa;
           this.lastUpdateA = lastUpdateA;
           this.insertUpdateDa = insertUpdateDa;
           this.insertUpdateA = insertUpdateA;
           this.iuv = iuv;
           this.idEsitoPagamento = idEsitoPagamento;
           this.idMsgRichiesta = idMsgRichiesta;
    }


    /**
     * Gets the auth value for this GetRTByParam.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetRTByParam.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the id value for this GetRTByParam.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this GetRTByParam.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the applicationId value for this GetRTByParam.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this GetRTByParam.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the transactionId value for this GetRTByParam.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this GetRTByParam.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the lastUpdateDa value for this GetRTByParam.
     * 
     * @return lastUpdateDa
     */
    public java.util.Calendar getLastUpdateDa() {
        return lastUpdateDa;
    }


    /**
     * Sets the lastUpdateDa value for this GetRTByParam.
     * 
     * @param lastUpdateDa
     */
    public void setLastUpdateDa(java.util.Calendar lastUpdateDa) {
        this.lastUpdateDa = lastUpdateDa;
    }


    /**
     * Gets the lastUpdateA value for this GetRTByParam.
     * 
     * @return lastUpdateA
     */
    public java.util.Calendar getLastUpdateA() {
        return lastUpdateA;
    }


    /**
     * Sets the lastUpdateA value for this GetRTByParam.
     * 
     * @param lastUpdateA
     */
    public void setLastUpdateA(java.util.Calendar lastUpdateA) {
        this.lastUpdateA = lastUpdateA;
    }


    /**
     * Gets the insertUpdateDa value for this GetRTByParam.
     * 
     * @return insertUpdateDa
     */
    public java.util.Calendar getInsertUpdateDa() {
        return insertUpdateDa;
    }


    /**
     * Sets the insertUpdateDa value for this GetRTByParam.
     * 
     * @param insertUpdateDa
     */
    public void setInsertUpdateDa(java.util.Calendar insertUpdateDa) {
        this.insertUpdateDa = insertUpdateDa;
    }


    /**
     * Gets the insertUpdateA value for this GetRTByParam.
     * 
     * @return insertUpdateA
     */
    public java.util.Calendar getInsertUpdateA() {
        return insertUpdateA;
    }


    /**
     * Sets the insertUpdateA value for this GetRTByParam.
     * 
     * @param insertUpdateA
     */
    public void setInsertUpdateA(java.util.Calendar insertUpdateA) {
        this.insertUpdateA = insertUpdateA;
    }


    /**
     * Gets the iuv value for this GetRTByParam.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this GetRTByParam.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the idEsitoPagamento value for this GetRTByParam.
     * 
     * @return idEsitoPagamento
     */
    public java.lang.String getIdEsitoPagamento() {
        return idEsitoPagamento;
    }


    /**
     * Sets the idEsitoPagamento value for this GetRTByParam.
     * 
     * @param idEsitoPagamento
     */
    public void setIdEsitoPagamento(java.lang.String idEsitoPagamento) {
        this.idEsitoPagamento = idEsitoPagamento;
    }


    /**
     * Gets the idMsgRichiesta value for this GetRTByParam.
     * 
     * @return idMsgRichiesta
     */
    public java.lang.String getIdMsgRichiesta() {
        return idMsgRichiesta;
    }


    /**
     * Sets the idMsgRichiesta value for this GetRTByParam.
     * 
     * @param idMsgRichiesta
     */
    public void setIdMsgRichiesta(java.lang.String idMsgRichiesta) {
        this.idMsgRichiesta = idMsgRichiesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRTByParam)) return false;
        GetRTByParam other = (GetRTByParam) obj;
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
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId()))) &&
            ((this.lastUpdateDa==null && other.getLastUpdateDa()==null) || 
             (this.lastUpdateDa!=null &&
              this.lastUpdateDa.equals(other.getLastUpdateDa()))) &&
            ((this.lastUpdateA==null && other.getLastUpdateA()==null) || 
             (this.lastUpdateA!=null &&
              this.lastUpdateA.equals(other.getLastUpdateA()))) &&
            ((this.insertUpdateDa==null && other.getInsertUpdateDa()==null) || 
             (this.insertUpdateDa!=null &&
              this.insertUpdateDa.equals(other.getInsertUpdateDa()))) &&
            ((this.insertUpdateA==null && other.getInsertUpdateA()==null) || 
             (this.insertUpdateA!=null &&
              this.insertUpdateA.equals(other.getInsertUpdateA()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.idEsitoPagamento==null && other.getIdEsitoPagamento()==null) || 
             (this.idEsitoPagamento!=null &&
              this.idEsitoPagamento.equals(other.getIdEsitoPagamento()))) &&
            ((this.idMsgRichiesta==null && other.getIdMsgRichiesta()==null) || 
             (this.idMsgRichiesta!=null &&
              this.idMsgRichiesta.equals(other.getIdMsgRichiesta())));
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
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        if (getLastUpdateDa() != null) {
            _hashCode += getLastUpdateDa().hashCode();
        }
        if (getLastUpdateA() != null) {
            _hashCode += getLastUpdateA().hashCode();
        }
        if (getInsertUpdateDa() != null) {
            _hashCode += getInsertUpdateDa().hashCode();
        }
        if (getInsertUpdateA() != null) {
            _hashCode += getInsertUpdateA().hashCode();
        }
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getIdEsitoPagamento() != null) {
            _hashCode += getIdEsitoPagamento().hashCode();
        }
        if (getIdMsgRichiesta() != null) {
            _hashCode += getIdMsgRichiesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRTByParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRTByParam"));
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
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUpdateDa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastUpdateDa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUpdateA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastUpdateA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertUpdateDa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "insertUpdateDa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertUpdateA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "insertUpdateA"));
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
        elemField.setFieldName("idEsitoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEsitoPagamento"));
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
