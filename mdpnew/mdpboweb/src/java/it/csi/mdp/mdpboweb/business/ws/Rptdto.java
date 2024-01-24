/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Rptdto  extends it.csi.mdp.mdpboweb.business.ws.BaseDto  implements java.io.Serializable {
    private java.lang.String applicationId;

    private java.lang.String authSoggetto;

    private java.util.Calendar dataMsgRichiesta;

    private java.lang.String descStatoRpt;

    private java.lang.Integer id;

    private java.lang.String idCanale;

    private java.lang.String idIntermPsp;

    private java.lang.String idMsgRichiesta;

    private java.lang.String idPsp;

    private java.lang.Integer idStatiRpt;

    private java.lang.String identificativoDominio;

    private java.lang.String identificativoIntermediarioPa;

    private java.lang.String identificativoStazioneIntermediarioPa;

    private java.util.Calendar insertDate;

    private java.lang.String iuv;

    private java.util.Calendar lastUpdate;

    private java.lang.String rptXml;

    private java.lang.String transactionId;

    public Rptdto() {
    }

    public Rptdto(
           java.lang.String applicationId,
           java.lang.String authSoggetto,
           java.util.Calendar dataMsgRichiesta,
           java.lang.String descStatoRpt,
           java.lang.Integer id,
           java.lang.String idCanale,
           java.lang.String idIntermPsp,
           java.lang.String idMsgRichiesta,
           java.lang.String idPsp,
           java.lang.Integer idStatiRpt,
           java.lang.String identificativoDominio,
           java.lang.String identificativoIntermediarioPa,
           java.lang.String identificativoStazioneIntermediarioPa,
           java.util.Calendar insertDate,
           java.lang.String iuv,
           java.util.Calendar lastUpdate,
           java.lang.String rptXml,
           java.lang.String transactionId) {
        this.applicationId = applicationId;
        this.authSoggetto = authSoggetto;
        this.dataMsgRichiesta = dataMsgRichiesta;
        this.descStatoRpt = descStatoRpt;
        this.id = id;
        this.idCanale = idCanale;
        this.idIntermPsp = idIntermPsp;
        this.idMsgRichiesta = idMsgRichiesta;
        this.idPsp = idPsp;
        this.idStatiRpt = idStatiRpt;
        this.identificativoDominio = identificativoDominio;
        this.identificativoIntermediarioPa = identificativoIntermediarioPa;
        this.identificativoStazioneIntermediarioPa = identificativoStazioneIntermediarioPa;
        this.insertDate = insertDate;
        this.iuv = iuv;
        this.lastUpdate = lastUpdate;
        this.rptXml = rptXml;
        this.transactionId = transactionId;
    }


    /**
     * Gets the applicationId value for this Rptdto.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this Rptdto.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the authSoggetto value for this Rptdto.
     * 
     * @return authSoggetto
     */
    public java.lang.String getAuthSoggetto() {
        return authSoggetto;
    }


    /**
     * Sets the authSoggetto value for this Rptdto.
     * 
     * @param authSoggetto
     */
    public void setAuthSoggetto(java.lang.String authSoggetto) {
        this.authSoggetto = authSoggetto;
    }


    /**
     * Gets the dataMsgRichiesta value for this Rptdto.
     * 
     * @return dataMsgRichiesta
     */
    public java.util.Calendar getDataMsgRichiesta() {
        return dataMsgRichiesta;
    }


    /**
     * Sets the dataMsgRichiesta value for this Rptdto.
     * 
     * @param dataMsgRichiesta
     */
    public void setDataMsgRichiesta(java.util.Calendar dataMsgRichiesta) {
        this.dataMsgRichiesta = dataMsgRichiesta;
    }


    /**
     * Gets the descStatoRpt value for this Rptdto.
     * 
     * @return descStatoRpt
     */
    public java.lang.String getDescStatoRpt() {
        return descStatoRpt;
    }


    /**
     * Sets the descStatoRpt value for this Rptdto.
     * 
     * @param descStatoRpt
     */
    public void setDescStatoRpt(java.lang.String descStatoRpt) {
        this.descStatoRpt = descStatoRpt;
    }


    /**
     * Gets the id value for this Rptdto.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this Rptdto.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the idCanale value for this Rptdto.
     * 
     * @return idCanale
     */
    public java.lang.String getIdCanale() {
        return idCanale;
    }


    /**
     * Sets the idCanale value for this Rptdto.
     * 
     * @param idCanale
     */
    public void setIdCanale(java.lang.String idCanale) {
        this.idCanale = idCanale;
    }


    /**
     * Gets the idIntermPsp value for this Rptdto.
     * 
     * @return idIntermPsp
     */
    public java.lang.String getIdIntermPsp() {
        return idIntermPsp;
    }


    /**
     * Sets the idIntermPsp value for this Rptdto.
     * 
     * @param idIntermPsp
     */
    public void setIdIntermPsp(java.lang.String idIntermPsp) {
        this.idIntermPsp = idIntermPsp;
    }


    /**
     * Gets the idMsgRichiesta value for this Rptdto.
     * 
     * @return idMsgRichiesta
     */
    public java.lang.String getIdMsgRichiesta() {
        return idMsgRichiesta;
    }


    /**
     * Sets the idMsgRichiesta value for this Rptdto.
     * 
     * @param idMsgRichiesta
     */
    public void setIdMsgRichiesta(java.lang.String idMsgRichiesta) {
        this.idMsgRichiesta = idMsgRichiesta;
    }


    /**
     * Gets the idPsp value for this Rptdto.
     * 
     * @return idPsp
     */
    public java.lang.String getIdPsp() {
        return idPsp;
    }


    /**
     * Sets the idPsp value for this Rptdto.
     * 
     * @param idPsp
     */
    public void setIdPsp(java.lang.String idPsp) {
        this.idPsp = idPsp;
    }


    /**
     * Gets the idStatiRpt value for this Rptdto.
     * 
     * @return idStatiRpt
     */
    public java.lang.Integer getIdStatiRpt() {
        return idStatiRpt;
    }


    /**
     * Sets the idStatiRpt value for this Rptdto.
     * 
     * @param idStatiRpt
     */
    public void setIdStatiRpt(java.lang.Integer idStatiRpt) {
        this.idStatiRpt = idStatiRpt;
    }


    /**
     * Gets the identificativoDominio value for this Rptdto.
     * 
     * @return identificativoDominio
     */
    public java.lang.String getIdentificativoDominio() {
        return identificativoDominio;
    }


    /**
     * Sets the identificativoDominio value for this Rptdto.
     * 
     * @param identificativoDominio
     */
    public void setIdentificativoDominio(java.lang.String identificativoDominio) {
        this.identificativoDominio = identificativoDominio;
    }


    /**
     * Gets the identificativoIntermediarioPa value for this Rptdto.
     * 
     * @return identificativoIntermediarioPa
     */
    public java.lang.String getIdentificativoIntermediarioPa() {
        return identificativoIntermediarioPa;
    }


    /**
     * Sets the identificativoIntermediarioPa value for this Rptdto.
     * 
     * @param identificativoIntermediarioPa
     */
    public void setIdentificativoIntermediarioPa(java.lang.String identificativoIntermediarioPa) {
        this.identificativoIntermediarioPa = identificativoIntermediarioPa;
    }


    /**
     * Gets the identificativoStazioneIntermediarioPa value for this Rptdto.
     * 
     * @return identificativoStazioneIntermediarioPa
     */
    public java.lang.String getIdentificativoStazioneIntermediarioPa() {
        return identificativoStazioneIntermediarioPa;
    }


    /**
     * Sets the identificativoStazioneIntermediarioPa value for this Rptdto.
     * 
     * @param identificativoStazioneIntermediarioPa
     */
    public void setIdentificativoStazioneIntermediarioPa(java.lang.String identificativoStazioneIntermediarioPa) {
        this.identificativoStazioneIntermediarioPa = identificativoStazioneIntermediarioPa;
    }


    /**
     * Gets the insertDate value for this Rptdto.
     * 
     * @return insertDate
     */
    public java.util.Calendar getInsertDate() {
        return insertDate;
    }


    /**
     * Sets the insertDate value for this Rptdto.
     * 
     * @param insertDate
     */
    public void setInsertDate(java.util.Calendar insertDate) {
        this.insertDate = insertDate;
    }


    /**
     * Gets the iuv value for this Rptdto.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this Rptdto.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the lastUpdate value for this Rptdto.
     * 
     * @return lastUpdate
     */
    public java.util.Calendar getLastUpdate() {
        return lastUpdate;
    }


    /**
     * Sets the lastUpdate value for this Rptdto.
     * 
     * @param lastUpdate
     */
    public void setLastUpdate(java.util.Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    /**
     * Gets the rptXml value for this Rptdto.
     * 
     * @return rptXml
     */
    public java.lang.String getRptXml() {
        return rptXml;
    }


    /**
     * Sets the rptXml value for this Rptdto.
     * 
     * @param rptXml
     */
    public void setRptXml(java.lang.String rptXml) {
        this.rptXml = rptXml;
    }


    /**
     * Gets the transactionId value for this Rptdto.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Rptdto.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Rptdto)) return false;
        Rptdto other = (Rptdto) obj;
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
            ((this.authSoggetto==null && other.getAuthSoggetto()==null) || 
             (this.authSoggetto!=null &&
              this.authSoggetto.equals(other.getAuthSoggetto()))) &&
            ((this.dataMsgRichiesta==null && other.getDataMsgRichiesta()==null) || 
             (this.dataMsgRichiesta!=null &&
              this.dataMsgRichiesta.equals(other.getDataMsgRichiesta()))) &&
            ((this.descStatoRpt==null && other.getDescStatoRpt()==null) || 
             (this.descStatoRpt!=null &&
              this.descStatoRpt.equals(other.getDescStatoRpt()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idCanale==null && other.getIdCanale()==null) || 
             (this.idCanale!=null &&
              this.idCanale.equals(other.getIdCanale()))) &&
            ((this.idIntermPsp==null && other.getIdIntermPsp()==null) || 
             (this.idIntermPsp!=null &&
              this.idIntermPsp.equals(other.getIdIntermPsp()))) &&
            ((this.idMsgRichiesta==null && other.getIdMsgRichiesta()==null) || 
             (this.idMsgRichiesta!=null &&
              this.idMsgRichiesta.equals(other.getIdMsgRichiesta()))) &&
            ((this.idPsp==null && other.getIdPsp()==null) || 
             (this.idPsp!=null &&
              this.idPsp.equals(other.getIdPsp()))) &&
            ((this.idStatiRpt==null && other.getIdStatiRpt()==null) || 
             (this.idStatiRpt!=null &&
              this.idStatiRpt.equals(other.getIdStatiRpt()))) &&
            ((this.identificativoDominio==null && other.getIdentificativoDominio()==null) || 
             (this.identificativoDominio!=null &&
              this.identificativoDominio.equals(other.getIdentificativoDominio()))) &&
            ((this.identificativoIntermediarioPa==null && other.getIdentificativoIntermediarioPa()==null) || 
             (this.identificativoIntermediarioPa!=null &&
              this.identificativoIntermediarioPa.equals(other.getIdentificativoIntermediarioPa()))) &&
            ((this.identificativoStazioneIntermediarioPa==null && other.getIdentificativoStazioneIntermediarioPa()==null) || 
             (this.identificativoStazioneIntermediarioPa!=null &&
              this.identificativoStazioneIntermediarioPa.equals(other.getIdentificativoStazioneIntermediarioPa()))) &&
            ((this.insertDate==null && other.getInsertDate()==null) || 
             (this.insertDate!=null &&
              this.insertDate.equals(other.getInsertDate()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.lastUpdate==null && other.getLastUpdate()==null) || 
             (this.lastUpdate!=null &&
              this.lastUpdate.equals(other.getLastUpdate()))) &&
            ((this.rptXml==null && other.getRptXml()==null) || 
             (this.rptXml!=null &&
              this.rptXml.equals(other.getRptXml()))) &&
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
        if (getAuthSoggetto() != null) {
            _hashCode += getAuthSoggetto().hashCode();
        }
        if (getDataMsgRichiesta() != null) {
            _hashCode += getDataMsgRichiesta().hashCode();
        }
        if (getDescStatoRpt() != null) {
            _hashCode += getDescStatoRpt().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdCanale() != null) {
            _hashCode += getIdCanale().hashCode();
        }
        if (getIdIntermPsp() != null) {
            _hashCode += getIdIntermPsp().hashCode();
        }
        if (getIdMsgRichiesta() != null) {
            _hashCode += getIdMsgRichiesta().hashCode();
        }
        if (getIdPsp() != null) {
            _hashCode += getIdPsp().hashCode();
        }
        if (getIdStatiRpt() != null) {
            _hashCode += getIdStatiRpt().hashCode();
        }
        if (getIdentificativoDominio() != null) {
            _hashCode += getIdentificativoDominio().hashCode();
        }
        if (getIdentificativoIntermediarioPa() != null) {
            _hashCode += getIdentificativoIntermediarioPa().hashCode();
        }
        if (getIdentificativoStazioneIntermediarioPa() != null) {
            _hashCode += getIdentificativoStazioneIntermediarioPa().hashCode();
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
        if (getRptXml() != null) {
            _hashCode += getRptXml().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Rptdto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "rptdto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authSoggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authSoggetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataMsgRichiesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataMsgRichiesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descStatoRpt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descStatoRpt"));
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
        elemField.setFieldName("idCanale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCanale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idIntermPsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idIntermPsp"));
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
        elemField.setFieldName("idPsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPsp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idStatiRpt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idStatiRpt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoDominio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoDominio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoIntermediarioPa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoIntermediarioPa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoStazioneIntermediarioPa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoStazioneIntermediarioPa"));
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
        elemField.setFieldName("rptXml");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rptXml"));
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
