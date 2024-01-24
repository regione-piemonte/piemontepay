/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetGiornaleEventoByParam  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.String iuv;

    private java.util.Calendar dataOraEvento;

    private java.lang.String identificativodominio;

    private java.lang.String identificativofruitore;

    private java.lang.String codiceContesto;

    public GetGiornaleEventoByParam() {
    }

    public GetGiornaleEventoByParam(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.String iuv,
           java.util.Calendar dataOraEvento,
           java.lang.String identificativodominio,
           java.lang.String identificativofruitore,
           java.lang.String codiceContesto) {
           this.auth = auth;
           this.iuv = iuv;
           this.dataOraEvento = dataOraEvento;
           this.identificativodominio = identificativodominio;
           this.identificativofruitore = identificativofruitore;
           this.codiceContesto = codiceContesto;
    }


    /**
     * Gets the auth value for this GetGiornaleEventoByParam.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetGiornaleEventoByParam.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the iuv value for this GetGiornaleEventoByParam.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this GetGiornaleEventoByParam.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the dataOraEvento value for this GetGiornaleEventoByParam.
     * 
     * @return dataOraEvento
     */
    public java.util.Calendar getDataOraEvento() {
        return dataOraEvento;
    }


    /**
     * Sets the dataOraEvento value for this GetGiornaleEventoByParam.
     * 
     * @param dataOraEvento
     */
    public void setDataOraEvento(java.util.Calendar dataOraEvento) {
        this.dataOraEvento = dataOraEvento;
    }


    /**
     * Gets the identificativodominio value for this GetGiornaleEventoByParam.
     * 
     * @return identificativodominio
     */
    public java.lang.String getIdentificativodominio() {
        return identificativodominio;
    }


    /**
     * Sets the identificativodominio value for this GetGiornaleEventoByParam.
     * 
     * @param identificativodominio
     */
    public void setIdentificativodominio(java.lang.String identificativodominio) {
        this.identificativodominio = identificativodominio;
    }


    /**
     * Gets the identificativofruitore value for this GetGiornaleEventoByParam.
     * 
     * @return identificativofruitore
     */
    public java.lang.String getIdentificativofruitore() {
        return identificativofruitore;
    }


    /**
     * Sets the identificativofruitore value for this GetGiornaleEventoByParam.
     * 
     * @param identificativofruitore
     */
    public void setIdentificativofruitore(java.lang.String identificativofruitore) {
        this.identificativofruitore = identificativofruitore;
    }


    /**
     * Gets the codiceContesto value for this GetGiornaleEventoByParam.
     * 
     * @return codiceContesto
     */
    public java.lang.String getCodiceContesto() {
        return codiceContesto;
    }


    /**
     * Sets the codiceContesto value for this GetGiornaleEventoByParam.
     * 
     * @param codiceContesto
     */
    public void setCodiceContesto(java.lang.String codiceContesto) {
        this.codiceContesto = codiceContesto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetGiornaleEventoByParam)) return false;
        GetGiornaleEventoByParam other = (GetGiornaleEventoByParam) obj;
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
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.dataOraEvento==null && other.getDataOraEvento()==null) || 
             (this.dataOraEvento!=null &&
              this.dataOraEvento.equals(other.getDataOraEvento()))) &&
            ((this.identificativodominio==null && other.getIdentificativodominio()==null) || 
             (this.identificativodominio!=null &&
              this.identificativodominio.equals(other.getIdentificativodominio()))) &&
            ((this.identificativofruitore==null && other.getIdentificativofruitore()==null) || 
             (this.identificativofruitore!=null &&
              this.identificativofruitore.equals(other.getIdentificativofruitore()))) &&
            ((this.codiceContesto==null && other.getCodiceContesto()==null) || 
             (this.codiceContesto!=null &&
              this.codiceContesto.equals(other.getCodiceContesto())));
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
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getDataOraEvento() != null) {
            _hashCode += getDataOraEvento().hashCode();
        }
        if (getIdentificativodominio() != null) {
            _hashCode += getIdentificativodominio().hashCode();
        }
        if (getIdentificativofruitore() != null) {
            _hashCode += getIdentificativofruitore().hashCode();
        }
        if (getCodiceContesto() != null) {
            _hashCode += getCodiceContesto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetGiornaleEventoByParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByParam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
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
        elemField.setFieldName("dataOraEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataOraEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativodominio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativodominio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativofruitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativofruitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceContesto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceContesto"));
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
