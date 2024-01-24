/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBckroles  implements java.io.Serializable {
    private int idrole;

    private java.lang.String roledescr;

    public MdpBckroles() {
    }

    public MdpBckroles(
           int idrole,
           java.lang.String roledescr) {
           this.idrole = idrole;
           this.roledescr = roledescr;
    }


    /**
     * Gets the idrole value for this MdpBckroles.
     * 
     * @return idrole
     */
    public int getIdrole() {
        return idrole;
    }


    /**
     * Sets the idrole value for this MdpBckroles.
     * 
     * @param idrole
     */
    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }


    /**
     * Gets the roledescr value for this MdpBckroles.
     * 
     * @return roledescr
     */
    public java.lang.String getRoledescr() {
        return roledescr;
    }


    /**
     * Sets the roledescr value for this MdpBckroles.
     * 
     * @param roledescr
     */
    public void setRoledescr(java.lang.String roledescr) {
        this.roledescr = roledescr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdpBckroles)) return false;
        MdpBckroles other = (MdpBckroles) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idrole == other.getIdrole() &&
            ((this.roledescr==null && other.getRoledescr()==null) || 
             (this.roledescr!=null &&
              this.roledescr.equals(other.getRoledescr())));
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
        _hashCode += getIdrole();
        if (getRoledescr() != null) {
            _hashCode += getRoledescr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MdpBckroles.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckroles"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idrole");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idrole"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roledescr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "roledescr"));
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
