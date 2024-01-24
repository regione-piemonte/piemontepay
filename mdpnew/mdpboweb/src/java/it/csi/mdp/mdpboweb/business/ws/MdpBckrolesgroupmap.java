/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBckrolesgroupmap  implements java.io.Serializable {
    private int idgroup;

    private int idrole;

    public MdpBckrolesgroupmap() {
    }

    public MdpBckrolesgroupmap(
           int idgroup,
           int idrole) {
           this.idgroup = idgroup;
           this.idrole = idrole;
    }


    /**
     * Gets the idgroup value for this MdpBckrolesgroupmap.
     * 
     * @return idgroup
     */
    public int getIdgroup() {
        return idgroup;
    }


    /**
     * Sets the idgroup value for this MdpBckrolesgroupmap.
     * 
     * @param idgroup
     */
    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }


    /**
     * Gets the idrole value for this MdpBckrolesgroupmap.
     * 
     * @return idrole
     */
    public int getIdrole() {
        return idrole;
    }


    /**
     * Sets the idrole value for this MdpBckrolesgroupmap.
     * 
     * @param idrole
     */
    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdpBckrolesgroupmap)) return false;
        MdpBckrolesgroupmap other = (MdpBckrolesgroupmap) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idgroup == other.getIdgroup() &&
            this.idrole == other.getIdrole();
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
        _hashCode += getIdrole();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MdpBckrolesgroupmap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckrolesgroupmap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idgroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idgroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idrole");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idrole"));
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
