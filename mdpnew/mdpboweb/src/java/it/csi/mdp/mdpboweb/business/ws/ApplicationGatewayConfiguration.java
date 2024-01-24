/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class ApplicationGatewayConfiguration  extends it.csi.mdp.mdpboweb.business.ws.ApplicationDetail  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] appcustfields;

    private java.lang.String gatewaydescription;

    private java.lang.String paymodedescription;

    public ApplicationGatewayConfiguration() {
    }

    public ApplicationGatewayConfiguration(
           java.lang.String applicationid,
           java.lang.String applicationurlback,
           java.lang.String applicationurlcancel,
           java.lang.String applicationurlerror,
           java.lang.String applicationurlresponseko,
           java.lang.String applicationurlresponseok,
           java.lang.String codiceistat,
           java.lang.String contocorrenteposte,
           java.lang.String enabled,
           java.lang.String flagreturlmdp,
           java.lang.String gatewayid,
           java.lang.String macAvvio,
           java.lang.String macEsito,
           java.lang.String mail2Buyerko,
           java.lang.String mail2Buyerok,
           java.lang.String mail2Esercko,
           java.lang.String mail2Esercok,
           java.lang.String mail2Sysko,
           java.lang.String mail2Sysok,
           java.lang.String merchantid,
           java.lang.String merchantidpassword,
           java.lang.String paymentmodeid,
           long pgactioncode,
           boolean pgactioncodeNull,
           long pgcontabcode,
           boolean pgcontabcodeNull,
           float sogliaa,
           boolean sogliaaNull,
           float sogliada,
           boolean sogliadaNull,
           java.lang.String tipobollettinoposte,
           java.lang.String tipocommissione,
           java.lang.String tipodocumentoposte,
           float valorecommissionemax,
           boolean valorecommissionemaxNull,
           float valorecommissionemin,
           it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] appcustfields,
           java.lang.String gatewaydescription,
           java.lang.String paymodedescription) {
        super(
            applicationid,
            applicationurlback,
            applicationurlcancel,
            applicationurlerror,
            applicationurlresponseko,
            applicationurlresponseok,
            codiceistat,
            contocorrenteposte,
            enabled,
            flagreturlmdp,
            gatewayid,
            macAvvio,
            macEsito,
            mail2Buyerko,
            mail2Buyerok,
            mail2Esercko,
            mail2Esercok,
            mail2Sysko,
            mail2Sysok,
            merchantid,
            merchantidpassword,
            paymentmodeid,
            pgactioncode,
            pgactioncodeNull,
            pgcontabcode,
            pgcontabcodeNull,
            sogliaa,
            sogliaaNull,
            sogliada,
            sogliadaNull,
            tipobollettinoposte,
            tipocommissione,
            tipodocumentoposte,
            valorecommissionemax,
            valorecommissionemaxNull,
            valorecommissionemin);
        this.appcustfields = appcustfields;
        this.gatewaydescription = gatewaydescription;
        this.paymodedescription = paymodedescription;
    }


    /**
     * Gets the appcustfields value for this ApplicationGatewayConfiguration.
     * 
     * @return appcustfields
     */
    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] getAppcustfields() {
        return appcustfields;
    }


    /**
     * Sets the appcustfields value for this ApplicationGatewayConfiguration.
     * 
     * @param appcustfields
     */
    public void setAppcustfields(it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] appcustfields) {
        this.appcustfields = appcustfields;
    }

    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields getAppcustfields(int i) {
        return this.appcustfields[i];
    }

    public void setAppcustfields(int i, it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields _value) {
        this.appcustfields[i] = _value;
    }


    /**
     * Gets the gatewaydescription value for this ApplicationGatewayConfiguration.
     * 
     * @return gatewaydescription
     */
    public java.lang.String getGatewaydescription() {
        return gatewaydescription;
    }


    /**
     * Sets the gatewaydescription value for this ApplicationGatewayConfiguration.
     * 
     * @param gatewaydescription
     */
    public void setGatewaydescription(java.lang.String gatewaydescription) {
        this.gatewaydescription = gatewaydescription;
    }


    /**
     * Gets the paymodedescription value for this ApplicationGatewayConfiguration.
     * 
     * @return paymodedescription
     */
    public java.lang.String getPaymodedescription() {
        return paymodedescription;
    }


    /**
     * Sets the paymodedescription value for this ApplicationGatewayConfiguration.
     * 
     * @param paymodedescription
     */
    public void setPaymodedescription(java.lang.String paymodedescription) {
        this.paymodedescription = paymodedescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApplicationGatewayConfiguration)) return false;
        ApplicationGatewayConfiguration other = (ApplicationGatewayConfiguration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.appcustfields==null && other.getAppcustfields()==null) || 
             (this.appcustfields!=null &&
              java.util.Arrays.equals(this.appcustfields, other.getAppcustfields()))) &&
            ((this.gatewaydescription==null && other.getGatewaydescription()==null) || 
             (this.gatewaydescription!=null &&
              this.gatewaydescription.equals(other.getGatewaydescription()))) &&
            ((this.paymodedescription==null && other.getPaymodedescription()==null) || 
             (this.paymodedescription!=null &&
              this.paymodedescription.equals(other.getPaymodedescription())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAppcustfields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAppcustfields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAppcustfields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGatewaydescription() != null) {
            _hashCode += getGatewaydescription().hashCode();
        }
        if (getPaymodedescription() != null) {
            _hashCode += getPaymodedescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApplicationGatewayConfiguration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationGatewayConfiguration"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appcustfields");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appcustfields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationcustomfields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewaydescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewaydescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymodedescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymodedescription"));
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
