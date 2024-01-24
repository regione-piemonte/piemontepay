/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Credentials  implements java.io.Serializable {
    private java.lang.String codfisc;

    private int group;

    private java.lang.String pwdAuth;

    private int role;

    private int userAuth;

    public Credentials() {
    }

    public Credentials(
           java.lang.String codfisc,
           int group,
           java.lang.String pwdAuth,
           int role,
           int userAuth) {
           this.codfisc = codfisc;
           this.group = group;
           this.pwdAuth = pwdAuth;
           this.role = role;
           this.userAuth = userAuth;
    }


    /**
     * Gets the codfisc value for this Credentials.
     * 
     * @return codfisc
     */
    public java.lang.String getCodfisc() {
        return codfisc;
    }


    /**
     * Sets the codfisc value for this Credentials.
     * 
     * @param codfisc
     */
    public void setCodfisc(java.lang.String codfisc) {
        this.codfisc = codfisc;
    }


    /**
     * Gets the group value for this Credentials.
     * 
     * @return group
     */
    public int getGroup() {
        return group;
    }


    /**
     * Sets the group value for this Credentials.
     * 
     * @param group
     */
    public void setGroup(int group) {
        this.group = group;
    }


    /**
     * Gets the pwdAuth value for this Credentials.
     * 
     * @return pwdAuth
     */
    public java.lang.String getPwdAuth() {
        return pwdAuth;
    }


    /**
     * Sets the pwdAuth value for this Credentials.
     * 
     * @param pwdAuth
     */
    public void setPwdAuth(java.lang.String pwdAuth) {
        this.pwdAuth = pwdAuth;
    }


    /**
     * Gets the role value for this Credentials.
     * 
     * @return role
     */
    public int getRole() {
        return role;
    }


    /**
     * Sets the role value for this Credentials.
     * 
     * @param role
     */
    public void setRole(int role) {
        this.role = role;
    }


    /**
     * Gets the userAuth value for this Credentials.
     * 
     * @return userAuth
     */
    public int getUserAuth() {
        return userAuth;
    }


    /**
     * Sets the userAuth value for this Credentials.
     * 
     * @param userAuth
     */
    public void setUserAuth(int userAuth) {
        this.userAuth = userAuth;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Credentials)) return false;
        Credentials other = (Credentials) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codfisc==null && other.getCodfisc()==null) || 
             (this.codfisc!=null &&
              this.codfisc.equals(other.getCodfisc()))) &&
            this.group == other.getGroup() &&
            ((this.pwdAuth==null && other.getPwdAuth()==null) || 
             (this.pwdAuth!=null &&
              this.pwdAuth.equals(other.getPwdAuth()))) &&
            this.role == other.getRole() &&
            this.userAuth == other.getUserAuth();
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
        if (getCodfisc() != null) {
            _hashCode += getCodfisc().hashCode();
        }
        _hashCode += getGroup();
        if (getPwdAuth() != null) {
            _hashCode += getPwdAuth().hashCode();
        }
        _hashCode += getRole();
        _hashCode += getUserAuth();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Credentials.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codfisc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codfisc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pwdAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pwdAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("role");
        elemField.setXmlName(new javax.xml.namespace.QName("", "role"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userAuth"));
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
