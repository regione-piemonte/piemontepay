/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBckofficegroups  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Application[] appgrp;

    private java.lang.String description;

    private int idgroup;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckroles[] roles;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckusers[] users;

    public MdpBckofficegroups() {
    }

    public MdpBckofficegroups(
           it.csi.mdp.mdpboweb.business.ws.Application[] appgrp,
           java.lang.String description,
           int idgroup,
           it.csi.mdp.mdpboweb.business.ws.MdpBckroles[] roles,
           it.csi.mdp.mdpboweb.business.ws.MdpBckusers[] users) {
           this.appgrp = appgrp;
           this.description = description;
           this.idgroup = idgroup;
           this.roles = roles;
           this.users = users;
    }


    /**
     * Gets the appgrp value for this MdpBckofficegroups.
     * 
     * @return appgrp
     */
    public it.csi.mdp.mdpboweb.business.ws.Application[] getAppgrp() {
        return appgrp;
    }


    /**
     * Sets the appgrp value for this MdpBckofficegroups.
     * 
     * @param appgrp
     */
    public void setAppgrp(it.csi.mdp.mdpboweb.business.ws.Application[] appgrp) {
        this.appgrp = appgrp;
    }

    public it.csi.mdp.mdpboweb.business.ws.Application getAppgrp(int i) {
        return this.appgrp[i];
    }

    public void setAppgrp(int i, it.csi.mdp.mdpboweb.business.ws.Application _value) {
        this.appgrp[i] = _value;
    }


    /**
     * Gets the description value for this MdpBckofficegroups.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this MdpBckofficegroups.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the idgroup value for this MdpBckofficegroups.
     * 
     * @return idgroup
     */
    public int getIdgroup() {
        return idgroup;
    }


    /**
     * Sets the idgroup value for this MdpBckofficegroups.
     * 
     * @param idgroup
     */
    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }


    /**
     * Gets the roles value for this MdpBckofficegroups.
     * 
     * @return roles
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckroles[] getRoles() {
        return roles;
    }


    /**
     * Sets the roles value for this MdpBckofficegroups.
     * 
     * @param roles
     */
    public void setRoles(it.csi.mdp.mdpboweb.business.ws.MdpBckroles[] roles) {
        this.roles = roles;
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckroles getRoles(int i) {
        return this.roles[i];
    }

    public void setRoles(int i, it.csi.mdp.mdpboweb.business.ws.MdpBckroles _value) {
        this.roles[i] = _value;
    }


    /**
     * Gets the users value for this MdpBckofficegroups.
     * 
     * @return users
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckusers[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this MdpBckofficegroups.
     * 
     * @param users
     */
    public void setUsers(it.csi.mdp.mdpboweb.business.ws.MdpBckusers[] users) {
        this.users = users;
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckusers getUsers(int i) {
        return this.users[i];
    }

    public void setUsers(int i, it.csi.mdp.mdpboweb.business.ws.MdpBckusers _value) {
        this.users[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdpBckofficegroups)) return false;
        MdpBckofficegroups other = (MdpBckofficegroups) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appgrp==null && other.getAppgrp()==null) || 
             (this.appgrp!=null &&
              java.util.Arrays.equals(this.appgrp, other.getAppgrp()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.idgroup == other.getIdgroup() &&
            ((this.roles==null && other.getRoles()==null) || 
             (this.roles!=null &&
              java.util.Arrays.equals(this.roles, other.getRoles()))) &&
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers())));
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
        if (getAppgrp() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAppgrp());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAppgrp(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getIdgroup();
        if (getRoles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRoles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRoles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
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
        new org.apache.axis.description.TypeDesc(MdpBckofficegroups.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroups"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appgrp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appgrp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "application"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roles");
        elemField.setXmlName(new javax.xml.namespace.QName("", "roles"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckroles"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("", "users"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusers"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
