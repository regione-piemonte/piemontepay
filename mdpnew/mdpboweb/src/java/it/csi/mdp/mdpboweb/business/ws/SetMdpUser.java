/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class SetMdpUser  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckusers user;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] usergrouplist;

    public SetMdpUser() {
    }

    public SetMdpUser(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           it.csi.mdp.mdpboweb.business.ws.MdpBckusers user,
           it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] usergrouplist) {
           this.auth = auth;
           this.user = user;
           this.usergrouplist = usergrouplist;
    }


    /**
     * Gets the auth value for this SetMdpUser.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this SetMdpUser.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the user value for this SetMdpUser.
     * 
     * @return user
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckusers getUser() {
        return user;
    }


    /**
     * Sets the user value for this SetMdpUser.
     * 
     * @param user
     */
    public void setUser(it.csi.mdp.mdpboweb.business.ws.MdpBckusers user) {
        this.user = user;
    }


    /**
     * Gets the usergrouplist value for this SetMdpUser.
     * 
     * @return usergrouplist
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] getUsergrouplist() {
        return usergrouplist;
    }


    /**
     * Sets the usergrouplist value for this SetMdpUser.
     * 
     * @param usergrouplist
     */
    public void setUsergrouplist(it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] usergrouplist) {
        this.usergrouplist = usergrouplist;
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup getUsergrouplist(int i) {
        return this.usergrouplist[i];
    }

    public void setUsergrouplist(int i, it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup _value) {
        this.usergrouplist[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetMdpUser)) return false;
        SetMdpUser other = (SetMdpUser) obj;
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
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.usergrouplist==null && other.getUsergrouplist()==null) || 
             (this.usergrouplist!=null &&
              java.util.Arrays.equals(this.usergrouplist, other.getUsergrouplist())));
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
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getUsergrouplist() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsergrouplist());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsergrouplist(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetMdpUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUser"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("", "user"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusers"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usergrouplist");
        elemField.setXmlName(new javax.xml.namespace.QName("", "usergrouplist"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusersgroup"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
