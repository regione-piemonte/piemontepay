/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBckusers  implements java.io.Serializable {
    private java.lang.String codfisc;

    private java.lang.String email;

    private java.lang.String firstname;

    private int iduser;

    private java.lang.String lastname;

    private java.lang.String pwdservizio;

    private it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] usergrp;

    public MdpBckusers() {
    }

    public MdpBckusers(
           java.lang.String codfisc,
           java.lang.String email,
           java.lang.String firstname,
           int iduser,
           java.lang.String lastname,
           java.lang.String pwdservizio,
           it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] usergrp) {
           this.codfisc = codfisc;
           this.email = email;
           this.firstname = firstname;
           this.iduser = iduser;
           this.lastname = lastname;
           this.pwdservizio = pwdservizio;
           this.usergrp = usergrp;
    }


    /**
     * Gets the codfisc value for this MdpBckusers.
     * 
     * @return codfisc
     */
    public java.lang.String getCodfisc() {
        return codfisc;
    }


    /**
     * Sets the codfisc value for this MdpBckusers.
     * 
     * @param codfisc
     */
    public void setCodfisc(java.lang.String codfisc) {
        this.codfisc = codfisc;
    }


    /**
     * Gets the email value for this MdpBckusers.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this MdpBckusers.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the firstname value for this MdpBckusers.
     * 
     * @return firstname
     */
    public java.lang.String getFirstname() {
        return firstname;
    }


    /**
     * Sets the firstname value for this MdpBckusers.
     * 
     * @param firstname
     */
    public void setFirstname(java.lang.String firstname) {
        this.firstname = firstname;
    }


    /**
     * Gets the iduser value for this MdpBckusers.
     * 
     * @return iduser
     */
    public int getIduser() {
        return iduser;
    }


    /**
     * Sets the iduser value for this MdpBckusers.
     * 
     * @param iduser
     */
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }


    /**
     * Gets the lastname value for this MdpBckusers.
     * 
     * @return lastname
     */
    public java.lang.String getLastname() {
        return lastname;
    }


    /**
     * Sets the lastname value for this MdpBckusers.
     * 
     * @param lastname
     */
    public void setLastname(java.lang.String lastname) {
        this.lastname = lastname;
    }


    /**
     * Gets the pwdservizio value for this MdpBckusers.
     * 
     * @return pwdservizio
     */
    public java.lang.String getPwdservizio() {
        return pwdservizio;
    }


    /**
     * Sets the pwdservizio value for this MdpBckusers.
     * 
     * @param pwdservizio
     */
    public void setPwdservizio(java.lang.String pwdservizio) {
        this.pwdservizio = pwdservizio;
    }


    /**
     * Gets the usergrp value for this MdpBckusers.
     * 
     * @return usergrp
     */
    public it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] getUsergrp() {
        return usergrp;
    }


    /**
     * Sets the usergrp value for this MdpBckusers.
     * 
     * @param usergrp
     */
    public void setUsergrp(it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup[] usergrp) {
        this.usergrp = usergrp;
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup getUsergrp(int i) {
        return this.usergrp[i];
    }

    public void setUsergrp(int i, it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup _value) {
        this.usergrp[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdpBckusers)) return false;
        MdpBckusers other = (MdpBckusers) obj;
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
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.firstname==null && other.getFirstname()==null) || 
             (this.firstname!=null &&
              this.firstname.equals(other.getFirstname()))) &&
            this.iduser == other.getIduser() &&
            ((this.lastname==null && other.getLastname()==null) || 
             (this.lastname!=null &&
              this.lastname.equals(other.getLastname()))) &&
            ((this.pwdservizio==null && other.getPwdservizio()==null) || 
             (this.pwdservizio!=null &&
              this.pwdservizio.equals(other.getPwdservizio()))) &&
            ((this.usergrp==null && other.getUsergrp()==null) || 
             (this.usergrp!=null &&
              java.util.Arrays.equals(this.usergrp, other.getUsergrp())));
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
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFirstname() != null) {
            _hashCode += getFirstname().hashCode();
        }
        _hashCode += getIduser();
        if (getLastname() != null) {
            _hashCode += getLastname().hashCode();
        }
        if (getPwdservizio() != null) {
            _hashCode += getPwdservizio().hashCode();
        }
        if (getUsergrp() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsergrp());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsergrp(), i);
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
        new org.apache.axis.description.TypeDesc(MdpBckusers.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusers"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codfisc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codfisc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iduser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iduser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pwdservizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pwdservizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usergrp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "usergrp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusersgroup"));
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
