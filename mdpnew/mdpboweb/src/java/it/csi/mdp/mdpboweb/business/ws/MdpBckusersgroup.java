/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBckusersgroup  implements java.io.Serializable {
    private int idgroup;

    private int iduser;

    public MdpBckusersgroup() {
    }

    public MdpBckusersgroup(
           int idgroup,
           int iduser) {
           this.idgroup = idgroup;
           this.iduser = iduser;
    }


    /**
     * Gets the idgroup value for this MdpBckusersgroup.
     * 
     * @return idgroup
     */
    public int getIdgroup() {
        return idgroup;
    }


    /**
     * Sets the idgroup value for this MdpBckusersgroup.
     * 
     * @param idgroup
     */
    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }


    /**
     * Gets the iduser value for this MdpBckusersgroup.
     * 
     * @return iduser
     */
    public int getIduser() {
        return iduser;
    }


    /**
     * Sets the iduser value for this MdpBckusersgroup.
     * 
     * @param iduser
     */
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdpBckusersgroup)) return false;
        MdpBckusersgroup other = (MdpBckusersgroup) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idgroup == other.getIdgroup() &&
            this.iduser == other.getIduser();
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
        _hashCode += getIdgroup();
        _hashCode += getIduser();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MdpBckusersgroup.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusersgroup"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idgroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idgroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iduser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iduser"));
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
