/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetMdpGroupResponse  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups mdpGroup;

    public SetMdpGroupResponse() {
    }

    public SetMdpGroupResponse(
           it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups mdpGroup) {
           this.mdpGroup = mdpGroup;
    }


    /**
     * Gets the mdpGroup value for this SetMdpGroupResponse.
     * 
     * @return mdpGroup
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups getMdpGroup() {
        return mdpGroup;
    }


    /**
     * Sets the mdpGroup value for this SetMdpGroupResponse.
     * 
     * @param mdpGroup
     */
    public void setMdpGroup(it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups mdpGroup) {
        this.mdpGroup = mdpGroup;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetMdpGroupResponse)) return false;
        SetMdpGroupResponse other = (SetMdpGroupResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mdpGroup==null && other.getMdpGroup()==null) || 
             (this.mdpGroup!=null &&
              this.mdpGroup.equals(other.getMdpGroup())));
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
        if (getMdpGroup() != null) {
            _hashCode += getMdpGroup().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetMdpGroupResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroupResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mdpGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mdpGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroups"));
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
