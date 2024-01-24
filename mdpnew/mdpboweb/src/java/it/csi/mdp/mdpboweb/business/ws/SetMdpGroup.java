/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetMdpGroup  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups group;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping[] appslist;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckrolesgroupmap grouprole;

    public SetMdpGroup() {
    }

    public SetMdpGroup(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups group,
           it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping[] appslist,
           it.csi.mdp.mdpboweb.business.ws.MdpBckrolesgroupmap grouprole) {
           this.auth = auth;
           this.group = group;
           this.appslist = appslist;
           this.grouprole = grouprole;
    }


    /**
     * Gets the auth value for this SetMdpGroup.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this SetMdpGroup.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the group value for this SetMdpGroup.
     * 
     * @return group
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups getGroup() {
        return group;
    }


    /**
     * Sets the group value for this SetMdpGroup.
     * 
     * @param group
     */
    public void setGroup(it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups group) {
        this.group = group;
    }


    /**
     * Gets the appslist value for this SetMdpGroup.
     * 
     * @return appslist
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping[] getAppslist() {
        return appslist;
    }


    /**
     * Sets the appslist value for this SetMdpGroup.
     * 
     * @param appslist
     */
    public void setAppslist(it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping[] appslist) {
        this.appslist = appslist;
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping getAppslist(int i) {
        return this.appslist[i];
    }

    public void setAppslist(int i, it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping _value) {
        this.appslist[i] = _value;
    }


    /**
     * Gets the grouprole value for this SetMdpGroup.
     * 
     * @return grouprole
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckrolesgroupmap getGrouprole() {
        return grouprole;
    }


    /**
     * Sets the grouprole value for this SetMdpGroup.
     * 
     * @param grouprole
     */
    public void setGrouprole(it.csi.mdp.mdpboweb.business.ws.MdpBckrolesgroupmap grouprole) {
        this.grouprole = grouprole;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetMdpGroup)) return false;
        SetMdpGroup other = (SetMdpGroup) obj;
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
            ((this.group==null && other.getGroup()==null) || 
             (this.group!=null &&
              this.group.equals(other.getGroup()))) &&
            ((this.appslist==null && other.getAppslist()==null) || 
             (this.appslist!=null &&
              java.util.Arrays.equals(this.appslist, other.getAppslist()))) &&
            ((this.grouprole==null && other.getGrouprole()==null) || 
             (this.grouprole!=null &&
              this.grouprole.equals(other.getGrouprole())));
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
        if (getGroup() != null) {
            _hashCode += getGroup().hashCode();
        }
        if (getAppslist() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAppslist());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAppslist(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGrouprole() != null) {
            _hashCode += getGrouprole().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetMdpGroup.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroup"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroups"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appslist");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appslist"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroupappmapping"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grouprole");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grouprole"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckrolesgroupmap"));
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
