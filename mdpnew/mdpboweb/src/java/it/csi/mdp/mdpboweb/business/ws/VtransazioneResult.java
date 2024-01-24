/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class VtransazioneResult  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Vtransazione[] listTransazioni;

    private int totTransactions;

    private int totTransactionsFound;

    public VtransazioneResult() {
    }

    public VtransazioneResult(
           it.csi.mdp.mdpboweb.business.ws.Vtransazione[] listTransazioni,
           int totTransactions,
           int totTransactionsFound) {
           this.listTransazioni = listTransazioni;
           this.totTransactions = totTransactions;
           this.totTransactionsFound = totTransactionsFound;
    }


    /**
     * Gets the listTransazioni value for this VtransazioneResult.
     * 
     * @return listTransazioni
     */
    public it.csi.mdp.mdpboweb.business.ws.Vtransazione[] getListTransazioni() {
        return listTransazioni;
    }


    /**
     * Sets the listTransazioni value for this VtransazioneResult.
     * 
     * @param listTransazioni
     */
    public void setListTransazioni(it.csi.mdp.mdpboweb.business.ws.Vtransazione[] listTransazioni) {
        this.listTransazioni = listTransazioni;
    }

    public it.csi.mdp.mdpboweb.business.ws.Vtransazione getListTransazioni(int i) {
        return this.listTransazioni[i];
    }

    public void setListTransazioni(int i, it.csi.mdp.mdpboweb.business.ws.Vtransazione _value) {
        this.listTransazioni[i] = _value;
    }


    /**
     * Gets the totTransactions value for this VtransazioneResult.
     * 
     * @return totTransactions
     */
    public int getTotTransactions() {
        return totTransactions;
    }


    /**
     * Sets the totTransactions value for this VtransazioneResult.
     * 
     * @param totTransactions
     */
    public void setTotTransactions(int totTransactions) {
        this.totTransactions = totTransactions;
    }


    /**
     * Gets the totTransactionsFound value for this VtransazioneResult.
     * 
     * @return totTransactionsFound
     */
    public int getTotTransactionsFound() {
        return totTransactionsFound;
    }


    /**
     * Sets the totTransactionsFound value for this VtransazioneResult.
     * 
     * @param totTransactionsFound
     */
    public void setTotTransactionsFound(int totTransactionsFound) {
        this.totTransactionsFound = totTransactionsFound;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VtransazioneResult)) return false;
        VtransazioneResult other = (VtransazioneResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listTransazioni==null && other.getListTransazioni()==null) || 
             (this.listTransazioni!=null &&
              java.util.Arrays.equals(this.listTransazioni, other.getListTransazioni()))) &&
            this.totTransactions == other.getTotTransactions() &&
            this.totTransactionsFound == other.getTotTransactionsFound();
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
        if (getListTransazioni() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListTransazioni());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListTransazioni(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getTotTransactions();
        _hashCode += getTotTransactionsFound();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VtransazioneResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "vtransazioneResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listTransazioni");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listTransazioni"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "vtransazione"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totTransactions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totTransactions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totTransactionsFound");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totTransactionsFound"));
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
