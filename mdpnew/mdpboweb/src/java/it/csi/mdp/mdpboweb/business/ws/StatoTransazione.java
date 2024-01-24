/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class StatoTransazione  implements java.io.Serializable {
    private int codStato;

    private java.lang.String descrizione;

    private java.lang.String descrizoneestesa;

    public StatoTransazione() {
    }

    public StatoTransazione(
           int codStato,
           java.lang.String descrizione,
           java.lang.String descrizoneestesa) {
           this.codStato = codStato;
           this.descrizione = descrizione;
           this.descrizoneestesa = descrizoneestesa;
    }


    /**
     * Gets the codStato value for this StatoTransazione.
     * 
     * @return codStato
     */
    public int getCodStato() {
        return codStato;
    }


    /**
     * Sets the codStato value for this StatoTransazione.
     * 
     * @param codStato
     */
    public void setCodStato(int codStato) {
        this.codStato = codStato;
    }


    /**
     * Gets the descrizione value for this StatoTransazione.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this StatoTransazione.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the descrizoneestesa value for this StatoTransazione.
     * 
     * @return descrizoneestesa
     */
    public java.lang.String getDescrizoneestesa() {
        return descrizoneestesa;
    }


    /**
     * Sets the descrizoneestesa value for this StatoTransazione.
     * 
     * @param descrizoneestesa
     */
    public void setDescrizoneestesa(java.lang.String descrizoneestesa) {
        this.descrizoneestesa = descrizoneestesa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatoTransazione)) return false;
        StatoTransazione other = (StatoTransazione) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codStato == other.getCodStato() &&
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.descrizoneestesa==null && other.getDescrizoneestesa()==null) || 
             (this.descrizoneestesa!=null &&
              this.descrizoneestesa.equals(other.getDescrizoneestesa())));
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
        _hashCode += getCodStato();
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getDescrizoneestesa() != null) {
            _hashCode += getDescrizoneestesa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatoTransazione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statoTransazione"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codStato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codStato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizoneestesa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizoneestesa"));
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
