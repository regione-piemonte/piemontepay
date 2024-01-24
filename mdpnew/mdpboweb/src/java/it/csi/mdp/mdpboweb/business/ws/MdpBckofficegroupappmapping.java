/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBckofficegroupappmapping  implements java.io.Serializable {
    private java.lang.String idapp;

    private int idgroup;

    public MdpBckofficegroupappmapping() {
    }

    public MdpBckofficegroupappmapping(
           java.lang.String idapp,
           int idgroup) {
           this.idapp = idapp;
           this.idgroup = idgroup;
    }


    /**
     * Gets the idapp value for this MdpBckofficegroupappmapping.
     * 
     * @return idapp
     */
    public java.lang.String getIdapp() {
        return idapp;
    }


    /**
     * Sets the idapp value for this MdpBckofficegroupappmapping.
     * 
     * @param idapp
     */
    public void setIdapp(java.lang.String idapp) {
        this.idapp = idapp;
    }


    /**
     * Gets the idgroup value for this MdpBckofficegroupappmapping.
     * 
     * @return idgroup
     */
    public int getIdgroup() {
        return idgroup;
    }


    /**
     * Sets the idgroup value for this MdpBckofficegroupappmapping.
     * 
     * @param idgroup
     */
    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdpBckofficegroupappmapping)) return false;
        MdpBckofficegroupappmapping other = (MdpBckofficegroupappmapping) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idapp==null && other.getIdapp()==null) || 
             (this.idapp!=null &&
              this.idapp.equals(other.getIdapp()))) &&
            this.idgroup == other.getIdgroup();
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
        if (getIdapp() != null) {
            _hashCode += getIdapp().hashCode();
        }
        _hashCode += getIdgroup();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MdpBckofficegroupappmapping.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroupappmapping"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idapp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idapp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idgroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idgroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
