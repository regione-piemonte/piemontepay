/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetAuditing  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private it.csi.mdp.mdpboweb.business.ws.Application[] applicationListFilter;

    private java.util.Calendar datestartFilter;

    private java.util.Calendar dateendFilter;

    private java.lang.String transactionidFilter;

    private java.lang.String[] actionsFilter;

    private java.lang.String[] usersFilter;

    public GetAuditing() {
    }

    public GetAuditing(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           it.csi.mdp.mdpboweb.business.ws.Application[] applicationListFilter,
           java.util.Calendar datestartFilter,
           java.util.Calendar dateendFilter,
           java.lang.String transactionidFilter,
           java.lang.String[] actionsFilter,
           java.lang.String[] usersFilter) {
           this.auth = auth;
           this.applicationListFilter = applicationListFilter;
           this.datestartFilter = datestartFilter;
           this.dateendFilter = dateendFilter;
           this.transactionidFilter = transactionidFilter;
           this.actionsFilter = actionsFilter;
           this.usersFilter = usersFilter;
    }


    /**
     * Gets the auth value for this GetAuditing.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetAuditing.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the applicationListFilter value for this GetAuditing.
     * 
     * @return applicationListFilter
     */
    public it.csi.mdp.mdpboweb.business.ws.Application[] getApplicationListFilter() {
        return applicationListFilter;
    }


    /**
     * Sets the applicationListFilter value for this GetAuditing.
     * 
     * @param applicationListFilter
     */
    public void setApplicationListFilter(it.csi.mdp.mdpboweb.business.ws.Application[] applicationListFilter) {
        this.applicationListFilter = applicationListFilter;
    }

    public it.csi.mdp.mdpboweb.business.ws.Application getApplicationListFilter(int i) {
        return this.applicationListFilter[i];
    }

    public void setApplicationListFilter(int i, it.csi.mdp.mdpboweb.business.ws.Application _value) {
        this.applicationListFilter[i] = _value;
    }


    /**
     * Gets the datestartFilter value for this GetAuditing.
     * 
     * @return datestartFilter
     */
    public java.util.Calendar getDatestartFilter() {
        return datestartFilter;
    }


    /**
     * Sets the datestartFilter value for this GetAuditing.
     * 
     * @param datestartFilter
     */
    public void setDatestartFilter(java.util.Calendar datestartFilter) {
        this.datestartFilter = datestartFilter;
    }


    /**
     * Gets the dateendFilter value for this GetAuditing.
     * 
     * @return dateendFilter
     */
    public java.util.Calendar getDateendFilter() {
        return dateendFilter;
    }


    /**
     * Sets the dateendFilter value for this GetAuditing.
     * 
     * @param dateendFilter
     */
    public void setDateendFilter(java.util.Calendar dateendFilter) {
        this.dateendFilter = dateendFilter;
    }


    /**
     * Gets the transactionidFilter value for this GetAuditing.
     * 
     * @return transactionidFilter
     */
    public java.lang.String getTransactionidFilter() {
        return transactionidFilter;
    }


    /**
     * Sets the transactionidFilter value for this GetAuditing.
     * 
     * @param transactionidFilter
     */
    public void setTransactionidFilter(java.lang.String transactionidFilter) {
        this.transactionidFilter = transactionidFilter;
    }


    /**
     * Gets the actionsFilter value for this GetAuditing.
     * 
     * @return actionsFilter
     */
    public java.lang.String[] getActionsFilter() {
        return actionsFilter;
    }


    /**
     * Sets the actionsFilter value for this GetAuditing.
     * 
     * @param actionsFilter
     */
    public void setActionsFilter(java.lang.String[] actionsFilter) {
        this.actionsFilter = actionsFilter;
    }

    public java.lang.String getActionsFilter(int i) {
        return this.actionsFilter[i];
    }

    public void setActionsFilter(int i, java.lang.String _value) {
        this.actionsFilter[i] = _value;
    }


    /**
     * Gets the usersFilter value for this GetAuditing.
     * 
     * @return usersFilter
     */
    public java.lang.String[] getUsersFilter() {
        return usersFilter;
    }


    /**
     * Sets the usersFilter value for this GetAuditing.
     * 
     * @param usersFilter
     */
    public void setUsersFilter(java.lang.String[] usersFilter) {
        this.usersFilter = usersFilter;
    }

    public java.lang.String getUsersFilter(int i) {
        return this.usersFilter[i];
    }

    public void setUsersFilter(int i, java.lang.String _value) {
        this.usersFilter[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAuditing)) return false;
        GetAuditing other = (GetAuditing) obj;
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
            ((this.applicationListFilter==null && other.getApplicationListFilter()==null) || 
             (this.applicationListFilter!=null &&
              java.util.Arrays.equals(this.applicationListFilter, other.getApplicationListFilter()))) &&
            ((this.datestartFilter==null && other.getDatestartFilter()==null) || 
             (this.datestartFilter!=null &&
              this.datestartFilter.equals(other.getDatestartFilter()))) &&
            ((this.dateendFilter==null && other.getDateendFilter()==null) || 
             (this.dateendFilter!=null &&
              this.dateendFilter.equals(other.getDateendFilter()))) &&
            ((this.transactionidFilter==null && other.getTransactionidFilter()==null) || 
             (this.transactionidFilter!=null &&
              this.transactionidFilter.equals(other.getTransactionidFilter()))) &&
            ((this.actionsFilter==null && other.getActionsFilter()==null) || 
             (this.actionsFilter!=null &&
              java.util.Arrays.equals(this.actionsFilter, other.getActionsFilter()))) &&
            ((this.usersFilter==null && other.getUsersFilter()==null) || 
             (this.usersFilter!=null &&
              java.util.Arrays.equals(this.usersFilter, other.getUsersFilter())));
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
        if (getApplicationListFilter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApplicationListFilter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApplicationListFilter(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDatestartFilter() != null) {
            _hashCode += getDatestartFilter().hashCode();
        }
        if (getDateendFilter() != null) {
            _hashCode += getDateendFilter().hashCode();
        }
        if (getTransactionidFilter() != null) {
            _hashCode += getTransactionidFilter().hashCode();
        }
        if (getActionsFilter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActionsFilter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActionsFilter(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUsersFilter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsersFilter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsersFilter(), i);
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
        new org.apache.axis.description.TypeDesc(GetAuditing.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditing"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationListFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationListFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "application"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datestartFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datestartFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateendFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateendFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionidFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionidFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionsFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actionsFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usersFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "usersFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
