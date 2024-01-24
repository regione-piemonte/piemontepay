/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetMdpUserResponse  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.MdpBckusers mdpUser;

    public SetMdpUserResponse() {
    }

    public SetMdpUserResponse(
           it.csi.mdp.mdpboweb.business.ws.MdpBckusers mdpUser) {
           this.mdpUser = mdpUser;
    }


    /**
     * Gets the mdpUser value for this SetMdpUserResponse.
     * 
     * @return mdpUser
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckusers getMdpUser() {
        return mdpUser;
    }


    /**
     * Sets the mdpUser value for this SetMdpUserResponse.
     * 
     * @param mdpUser
     */
    public void setMdpUser(it.csi.mdp.mdpboweb.business.ws.MdpBckusers mdpUser) {
        this.mdpUser = mdpUser;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetMdpUserResponse)) return false;
        SetMdpUserResponse other = (SetMdpUserResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mdpUser==null && other.getMdpUser()==null) || 
             (this.mdpUser!=null &&
              this.mdpUser.equals(other.getMdpUser())));
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
        if (getMdpUser() != null) {
            _hashCode += getMdpUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetMdpUserResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUserResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mdpUser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mdpUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusers"));
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
